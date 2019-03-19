/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.printing;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import com.kitfox.svg.SVGException;

import megamek.common.AmmoType;
import megamek.common.Bay;
import megamek.common.Entity;
import megamek.common.Jumpship;
import megamek.common.Mounted;
import megamek.common.SpaceStation;
import megamek.common.UnitType;
import megamek.common.Warship;
import megamek.common.WeaponType;
import megameklab.com.MegaMekLab;
import megameklab.com.util.ImageHelper;

/**
 * Generates a record sheet image for jumpships, warships, and space stations.
 *
 * @author arlith
 * @author Neoancient
 *
 */
public class PrintCapitalShip extends PrintEntity {

    /** Default width for armor pip */
    public static final double ARMOR_PIP_WIDTH = 4.5;
    /** Default height for armor pip */
    public static final double ARMOR_PIP_HEIGHT = 4.5;
    /**
     * Amount to offset the armor block drop shadow as a fraction of pip
     * height/width
     */
    public static final double SHADOW_OFFSET = 0.3;

    /** Default width for structure pips */
    public static final int IS_PIP_WIDTH = 3;
    /** Default height for structure pips */
    public static final int IS_PIP_HEIGHT = 3;

    /** Default width of armor block in number of pips */
    public static final int PIPS_PER_ROW = 10;
    /** Default height of armor block in number of pips */
    public static final int MAX_PIP_ROWS = 10;

    /**
     * The minimum font size to use when scaling inventory text to fit into
     * available space
     */
    public static final float MIN_FONT_SIZE = 5.0f;
    /**
     * The amount of space between lines, as a factor of the font height determined
     * by {@link java.awt.FontMetrics}
     */
    public static final float LINE_SPACING = 1.2f;
    /**
     * The maximum number of inventory lines to print as a single page. Ideally this
     * would be determined by the space allocated by the svg template, but we need
     * to determine how many pages we are printing before the template is loaded so
     * we predetermine the value.
     */
    public static final int MAX_SINGLE_PAGE_LINES = 42;

    // Indices for arrays tracking computed block sizes and which page to print them
    // on.
    private static final int BLOCK_CAPITAL = 0;
    private static final int BLOCK_AR10_AMMO = 1;
    private static final int BLOCK_STANDARD = 2;
    private static final int BLOCK_GRAV_DECK = 3;
    private static final int BLOCK_BAYS = 4;
    private static final int NUM_BLOCKS = 5;
    // The order in which to move blocks to the second page
    private static final int[] SWITCH_PAGE_ORDER = { BLOCK_STANDARD, BLOCK_GRAV_DECK, BLOCK_BAYS, BLOCK_AR10_AMMO };

    /**
     * The ship being printed
     */
    private final Jumpship ship;

    private List<WeaponBayText> capitalWeapTexts;
    private List<WeaponBayText> standardWeapTexts;
    private final int[] linesPerBlock = new int[NUM_BLOCKS];
    private final boolean[] blockOnReverse = new boolean[NUM_BLOCKS];
    private List<Bay> printableBays;
    private boolean secondPage = false;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param ship
     *            The ship to print
     * @param startPage
     *            The print job page number for this sheet
     * @param options
     *            Overrides the global options for which elements are printed
     */
    public PrintCapitalShip(Jumpship ship, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.ship = ship;
        processWeapons();
    }

    private void processWeapons() {
        List<Mounted> standardWeapons = new ArrayList<>();
        List<Mounted> capitalWeapons = new ArrayList<>();
        for (Mounted m : ship.getWeaponList()) {
            WeaponType wtype = (WeaponType) m.getType();
            if (wtype.isCapital()) {
                capitalWeapons.add(m);
            } else {
                standardWeapons.add(m);
            }
        }
        capitalWeapTexts = computeWeaponBayTexts(capitalWeapons);
        standardWeapTexts = computeWeaponBayTexts(standardWeapons);
        for (WeaponBayText wbt : capitalWeapTexts) {
            linesPerBlock[BLOCK_CAPITAL] += wbt.weapons.size();
        }
        for (WeaponBayText wbt : standardWeapTexts) {
            linesPerBlock[BLOCK_STANDARD] += wbt.weapons.size();
        }
        // Add extra lines for column headers and trailing line break
        if (linesPerBlock[BLOCK_CAPITAL] > 0) {
            linesPerBlock[BLOCK_CAPITAL] += 3;
        }
        if (linesPerBlock[BLOCK_STANDARD] > 0) {
            linesPerBlock[BLOCK_STANDARD] += 3;
        }
        if (ship.getTotalWeaponList().stream()
                .anyMatch(w -> ((WeaponType) w.getType()).getAmmoType() == AmmoType.T_AR10)) {
            linesPerBlock[BLOCK_AR10_AMMO] = 5;
        }
        // Add lines equal to half the grav decks (rounded up) and one each for section
        // title and following empty line
        if (ship.getGravDecks().size() > 0) {
            linesPerBlock[BLOCK_GRAV_DECK] = ((ship.getGravDecks().size() + 1) / 2) + 2;
        }
        // Add lines equal to number of transport bays and one each for section title
        // and following empty line
        printableBays = ship.getTransports().stream().filter(t -> t instanceof Bay)
                .map(t -> (Bay) t).filter(b -> !b.isQuarters()).collect(Collectors.toList());
        if (printableBays.size() > 0) {
            linesPerBlock[BLOCK_BAYS] = printableBays.size() + 2;
        }

        int linesOnFront = Arrays.stream(linesPerBlock).sum();
        if (linesOnFront > MAX_SINGLE_PAGE_LINES) {
            secondPage = true;
            int toSwitch = 0;
            // If there are no capital weapons, don't move standard scale to second page.
            if (linesPerBlock[BLOCK_CAPITAL] == 0) {
                toSwitch++;
            }
            do {
                blockOnReverse[SWITCH_PAGE_ORDER[toSwitch]] = true;
                linesOnFront -= linesPerBlock[SWITCH_PAGE_ORDER[toSwitch]];
                toSwitch++;
            } while ((linesOnFront > MAX_SINGLE_PAGE_LINES) && (toSwitch < SWITCH_PAGE_ORDER.length));
            // Another tweak for situations where there are no capital weapons. If only the
            // grav decks
            // are moved to page two, move bays as well to prevent a second page with only
            // one or two lines
            if (!blockOnReverse[BLOCK_STANDARD] && !blockOnReverse[BLOCK_BAYS]
                    && blockOnReverse[BLOCK_GRAV_DECK]) {
                blockOnReverse[BLOCK_BAYS] = true;
                linesOnFront -= linesPerBlock[BLOCK_BAYS];
            }
        }
    }

    /**
     * Iterate through a list of weapons and create information about what weapons
     * belong in what bays, how many, the bay damage, and also condense entries when
     * possible.
     *
     * @param weapons
     * @return
     */
    private List<WeaponBayText> computeWeaponBayTexts(List<Mounted> weapons) {
        // Collection info on weapons to print
        List<WeaponBayText> weaponBayTexts = new ArrayList<>();
        for (Mounted bay : weapons) {
            WeaponBayText wbt = new WeaponBayText(bay.getLocation(), false);
            for (Integer wId : bay.getBayWeapons()) {
                Mounted weap = ship.getEquipment(wId);
                wbt.addBayWeapon(weap);
            }
            // Combine or add
            boolean combined = false;
            for (WeaponBayText combine : weaponBayTexts) {
                if (combine.canCombine(wbt)) {
                    combine.combine(wbt);
                    combined = true;
                    break;
                }
            }
            if (!combined) {
                weaponBayTexts.add(wbt);
            }
        }
        Collections.sort(weaponBayTexts);

        return weaponBayTexts;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param mech
     *            The mech to print
     * @param startPage
     *            The print job page number for this sheet
     */
    public PrintCapitalShip(Jumpship ship, int startPage) {
        this(ship, startPage, new RecordSheetOptions());
    }

    @Override
    protected Entity getEntity() {
        return ship;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return (loc == Jumpship.LOC_NOSE) || (loc == Jumpship.LOC_AFT);
    }

    @Override
    public int getPageCount() {
        return secondPage ? 2 : 1;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (pageNumber > 0) {
            return "advaero_reverse.svg";
        }
        if (ship instanceof Warship) {
            return "warship_default.svg";
        } else if (ship instanceof SpaceStation) {
            return "spacestation_default.svg";
        } else {
            return "jumpship_default.svg";
        }
    }

    @Override
    protected String getRecordSheetTitle() {
        return UnitType.getTypeDisplayableName(ship.getUnitType())
                + " Record Sheet";
    }

    @Override
    public void printImage(Graphics2D g2d, PageFormat pageFormat, int pageNum) {
        if (pageNum > 0) {
            Element element = getSVGDocument().getElementById("textCopyright");
            if (null != element) {
                element.setTextContent(String.format(element.getTextContent(),
                        Calendar.getInstance().get(Calendar.YEAR)));
            }
            setTextField("title", getRecordSheetTitle().toUpperCase() + " (REVERSE)");
            setTextField("type", getEntity().getShortNameRaw());
            setTextField("name", ""); // TODO: fluff name needs MM support
            element = getSVGDocument().getElementById("inventory");
            if ((null != element) && (element instanceof SVGRectElement)) {
                writeEquipment((SVGRectElement) element, true);
            }
        } else {
            super.printImage(g2d, pageFormat, pageNum);
        }
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField("name", ""); // TODO: fluff name needs MM support
        setTextField("crew", ship.getNCrew());
        setTextField("marines", ship.getNMarines());
        setTextField("passengers", ship.getNPassenger());
        setTextField("baLabel", ship.isClan() ? "Elementals" : "BattleArmor");
        setTextField("battleArmor", ship.getNBattleArmor());
        setTextField("otherOccupants", ship.getNOtherCrew());
        setTextField("lifeBoats", ship.getLifeBoats());
        setTextField("escapePods", ship.getEscapePods());
        setTextField("heatSinks", ship.getHeatSinks());
        setTextField("doubleHeatSinks",
                ship.getHeatType() == Jumpship.HEAT_DOUBLE ? "(" + (ship.getHeatSinks() * 2) + ")" : "");
        setTextField("noseHeat", ship.getHeatInArc(Jumpship.LOC_NOSE, false));
        setTextField("foreHeat", ship.getHeatInArc(Jumpship.LOC_FLS, false)
                + " / " + ship.getHeatInArc(Jumpship.LOC_FRS, false));
        setTextField("aftSidesHeat", ship.getHeatInArc(Jumpship.LOC_ALS, false)
                + " / " + ship.getHeatInArc(Jumpship.LOC_ARS, false));
        setTextField("aftHeat", ship.getHeatInArc(Jumpship.LOC_AFT, false));
        if (ship instanceof Warship) {
            setTextField("broadsideHeat", ship.getHeatInArc(Warship.LOC_RBS, false)
                    + " / " + ship.getHeatInArc(Warship.LOC_LBS, false));
        }
    }

    @Override
    protected void drawArmor() {
        for (int loc = firstArmorLocation(); loc < Jumpship.LOC_HULL; loc++) {
            setTextField("textThresholdArmor_" + getEntity().getLocationAbbr(loc),
                    String.format("%d (%d)", ship.getThresh(loc), ship.getOArmor(loc)));
        }
        drawArmorStructurePips();
    }

    @Override
    protected void drawStructure() {
        setTextField("siText", ship.get0SI());
        setTextField("kfText", ship.getKFIntegrity());
        setTextField("sailText", ship.getSailIntegrity());
        setTextField("dcText", ship.getDockingCollars().size());

        if (ship instanceof Warship) {
            printInternalRegion("siPips", ship.get0SI(), 100);
        }
        printInternalRegion("kfPips", ship.getKFIntegrity(), 30);
        printInternalRegion("sailPips", ship.getSailIntegrity(), 10);
        printInternalRegion("dcPips", ship.getDockingCollars().size(), 10);
    }

    @Override
    protected void drawArmorStructurePips() {
        for (int loc = ship.firstArmorIndex(); loc < Jumpship.LOC_HULL; loc++) {
            final String id = "armorPips_" + ship.getLocationAbbr(loc);
            Element element = getSVGDocument().getElementById(id);
            if ((null != element) && (element instanceof SVGRectElement)) {
                printArmorRegion((SVGRectElement) element, loc, ship.getOArmor(loc));
            } else {
                MegaMekLab.getLogger().error(getClass(), "drawArmorStructurePips()",
                        "No SVGRectElement found with id " + id);
            }
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param rectId
     *            The id of the rectangle element that describes the outline of the
     *            region to print pips
     * @param structure
     *            The number of structure pips
     * @param pipsPerBlock
     *            The maximum number of pips to draw in a single block
     */
    private void printInternalRegion(String rectId, int structure, int pipsPerBlock) {
        Element element = getSVGDocument().getElementById(rectId);
        if ((null != element) && (element instanceof SVGRectElement)) {
            printInternalRegion((SVGRectElement) element, structure, pipsPerBlock);
        } else {
            MegaMekLab.getLogger().error(getClass(), "printInternalRegion(String, int, int)",
                    "No SVGRectElement found with id " + rectId);
        }
    }

    /**
     * Print pips for some internal structure region.
     *
     * @param svgRect
     *            The rectangle that describes the outline of the region to print
     *            pips
     * @param structure
     *            The number of structure pips
     * @param pipsPerBlock
     *            The maximum number of pips to draw in a single block
     */
    private void printInternalRegion(SVGRectElement svgRect, int structure, int pipsPerBlock) {
        Rectangle2D bbox = getRectBBox(svgRect);

        // Print in two blocks
        if (structure > pipsPerBlock) {
            // Block 1
            int pips = structure / 2;
            int startX, startY;
            double aspectRatio = (bbox.getWidth() / bbox.getHeight());
            if (aspectRatio >= 1) { // Landscape - 2 columns
                startX = ((int) bbox.getX() + (int) ((bbox.getWidth() / 4) + 0.5))
                        - ((PIPS_PER_ROW * IS_PIP_WIDTH) / 2);
                startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            } else { // Portrait - stacked 1 atop another
                startX = ((int) bbox.getX() + (int) ((bbox.getWidth() / 2) + 0.5))
                        - ((PIPS_PER_ROW * IS_PIP_WIDTH) / 2);
                startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            }
            printPipBlock(startX, startY, (SVGElement) svgRect.getParentNode(), pips,
                    IS_PIP_WIDTH, IS_PIP_HEIGHT, "white", false);

            // Block 2
            if (aspectRatio >= 1) { // Landscape - 2 columns
                startX = ((int) bbox.getX() + (int) (((3 * bbox.getWidth()) / 4) + 0.5))
                        - ((PIPS_PER_ROW * IS_PIP_WIDTH) / 2);
            } else { // Portrait - stacked 1 atop another
                startY = (int) bbox.getY() + (IS_PIP_HEIGHT * ((pips / PIPS_PER_ROW) + 1));
            }
            pips = (int) Math.ceil(structure / 2.0);
            printPipBlock(startX, startY, (SVGElement) svgRect.getParentNode(), pips,
                    IS_PIP_WIDTH, IS_PIP_HEIGHT, "white", false);
        } else { // Print in one block
            int startX = ((int) bbox.getX() + (int) ((bbox.getWidth() / 2) + 0.5))
                    - ((PIPS_PER_ROW * IS_PIP_WIDTH) / 2);
            int startY = (int) bbox.getY() + IS_PIP_HEIGHT;
            printPipBlock(startX, startY, (SVGElement) svgRect.getParentNode(), structure,
                    IS_PIP_WIDTH, IS_PIP_HEIGHT, "white", false);
        }
    }

    /**
     * Method to determine rectangle grid for armor or internal pips and draw it.
     *
     * @param svgRect
     *            A rectangle that outlines the border of the space for the armor
     *            block.
     * @param loc
     *            The location index
     * @param armor
     *            The amount of armor in the location
     */
    private void printArmorRegion(SVGRectElement svgRect, int loc, int armor) {
        Rectangle2D bbox = getRectBBox(svgRect);

        double pipWidth = ARMOR_PIP_WIDTH;
        double pipHeight = ARMOR_PIP_HEIGHT;
        ;

        // Size of each block include 0.5 pip margin on each side
        double blockHeight = (MAX_PIP_ROWS + 1) * pipHeight;
        double blockWidth = (PIPS_PER_ROW + 1) * pipWidth;

        int numBlocks = (int) Math.ceil((float) armor / (MAX_PIP_ROWS * PIPS_PER_ROW));
        int rows = 1;
        int cols = 1;
        if (bbox.getWidth() > bbox.getHeight()) {
            // landscape; as many columns as needed to fit everything in one or two rows
            cols = numBlocks;
            if ((numBlocks * blockWidth) > bbox.getWidth()) {
                rows++;
                cols = (numBlocks + 1) / 2;
            }
        } else {
            // portrait; as many rows as needed to fit everything in one or two columns
            rows = numBlocks;
            if ((numBlocks * blockHeight) > bbox.getHeight()) {
                cols++;
                rows = (numBlocks + 1) / 2;
            }
        }
        // Check the ration of the space required to space available. If either exceeds,
        // scale both
        // dimensions down equally to fit.
        double ratio = Math.max((rows * blockHeight) / bbox.getHeight(), (cols * blockWidth) / bbox.getWidth());
        if (ratio > 1.0) {
            pipHeight /= ratio;
            pipWidth /= ratio;
            blockHeight /= ratio;
            blockWidth /= ratio;
        }
        // Center horizontally and vertically in the space
        final double startX = bbox.getX() + ((bbox.getWidth() - (blockWidth * cols)) / 2.0);
        final double startY = bbox.getY() + ((bbox.getHeight() - (blockHeight * rows)) / 2.0);
        double xpos = startX;
        double ypos = startY;
        int remainingBlocks = numBlocks;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                armor = printPipBlock(xpos, ypos, (SVGElement) svgRect.getParentNode(),
                        armor, pipWidth, pipHeight, "#ffffff", true);
                remainingBlocks--;
                xpos += blockWidth;
            }
            xpos = startX;
            ypos += blockWidth;
            // Check whether the last row is a short one.
            if (remainingBlocks < cols) {
                xpos += blockWidth / 2.0;
            }
        }
    }

    /**
     * Helper function to print a armor pip block. Can print up to 100 points of
     * armor. Any unprinted armor pips are returned.
     *
     * @param startX
     *            The x coordinate of the top left of the block
     * @param startY
     *            The y coordinate of the top left of the block
     * @param parent
     *            The parent node of the bounding rectangle
     * @param numPips
     *            The number of pips to print
     * @param shadow
     *            Whether to add a drop shadow
     * @return The Y location of the end of the block
     */
    private int printPipBlock(double startX, double startY, SVGElement parent, int numPips, double pipWidth,
            double pipHeight, String fillColor, boolean shadow) {

        final double shadowOffsetX = pipWidth * SHADOW_OFFSET;
        final double shadowOffsetY = pipHeight * SHADOW_OFFSET;
        double currX, currY;
        currY = startY;
        for (int row = 0; row < 10; row++) {
            int numRowPips = Math.min(numPips, PIPS_PER_ROW);
            // Adjust row start if it's not a complete row
            currX = startX + ((((10 - numRowPips) / 2f) * pipWidth) + 0.5);
            for (int col = 0; col < numRowPips; col++) {
                if (shadow) {
                    parent.appendChild(createPip(pipWidth, pipHeight, "#c8c7c7", currX + shadowOffsetX,
                            currY + shadowOffsetY, false));
                }
                parent.appendChild(createPip(pipWidth, pipHeight, fillColor, currX, currY, true));

                currX += pipWidth;
                numPips--;
                // Check to see if we're done
                if (numPips <= 0) {
                    return 0;
                }
            }
            currY += pipHeight;
        }
        return numPips;
    }

    private Element createPip(double pipWidth, double pipHeight, String fillColor,
            double currX, double currY, boolean stroke) {
        Element box = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_RECT_TAG);
        box.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(currX));
        box.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(currY));
        box.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pipWidth));
        box.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pipHeight));
        if (stroke) {
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, String.valueOf("#000000"));
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(0.5));
        }
        box.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fillColor);
        return box;
    }

    @Override
    protected void writeEquipment(SVGRectElement svgRect) {
        writeEquipment(svgRect, false);
    }

    /**
     * Prints up to four equipment sections: capital weapons, standard scale, grav
     * decks, and bays. If there is too much to fit on a single page, the standard
     * scale weapons are moved to the second page (which is considered the reverse).
     *
     * @param svgRect
     *            The rectangle element that provides the dimensions of the space to
     *            print
     * @param reverse
     *            Whether this is printing on the reverse side.
     */
    private void writeEquipment(SVGRectElement svgRect, boolean reverse) {
        int lines = 0;
        for (int block = 0; block < NUM_BLOCKS; block++) {
            if (blockOnReverse[block] == reverse) {
                lines += linesPerBlock[block];
            }
        }
        InventoryWriter iw = new InventoryWriter(svgRect, lines);
        if ((linesPerBlock[BLOCK_CAPITAL] > 0) && (blockOnReverse[BLOCK_CAPITAL] == reverse)) {
            iw.printCapitalHeader();
            for (WeaponBayText bay : capitalWeapTexts) {
                iw.printWeaponBay(bay, true);
            }
            iw.newLine();
        }
        if ((linesPerBlock[BLOCK_AR10_AMMO] > 0)
                && (blockOnReverse[BLOCK_AR10_AMMO] == reverse)) {
            iw.printAR10AmmoBlock();
            iw.newLine();
        }
        if (linesPerBlock[BLOCK_STANDARD] > 0) {
            if (blockOnReverse[BLOCK_STANDARD] == reverse) {
                iw.printStandardHeader();
                for (WeaponBayText bay : standardWeapTexts) {
                    iw.printWeaponBay(bay, false);
                }
            } else if (!reverse) {
                iw.printReverseSideMessage();
            }
            iw.newLine();
        }
        if ((linesPerBlock[BLOCK_GRAV_DECK] > 0) && (blockOnReverse[BLOCK_GRAV_DECK] == reverse)) {
            iw.printGravDecks();
        }
        if ((linesPerBlock[BLOCK_BAYS] > 0) && (blockOnReverse[BLOCK_BAYS] == reverse)) {
            iw.printBayInfo();
        }
    }

    private class InventoryWriter {
        Rectangle2D bbox;
        SVGElement canvas;

        double nameX;
        double locX;
        double htX;
        double srvX;
        double mrvX;
        double lrvX;
        double ervX;
        double indent;

        double currY;

        float fontSize = FONT_SIZE_MEDIUM;
        double lineHeight = getFontHeight(fontSize) * LINE_SPACING;

        InventoryWriter(SVGRectElement svgRect, int lines) {
            bbox = getRectBBox(svgRect);
            canvas = (SVGElement) svgRect.getParentNode();
            double viewX = bbox.getX();
            double viewWidth = bbox.getWidth();

            /*
             * The relationship between the font size and the height is not directly
             * proportional, so simply scaling by the ratio of the max height to the current
             * one is not guaranteed to give us the value we want. So we keep checking and
             * scaling down until we get the desired value or hit the minimum, making sure
             * that we decrease by at least 0.1f each iteration
             */
            while ((fontSize > MIN_FONT_SIZE) && ((lineHeight * lines) >= bbox.getHeight())) {
                float newSize = (float) Math.max(MIN_FONT_SIZE, (fontSize * bbox.getHeight()) / (lineHeight * lines));
                fontSize = Math.min(newSize, fontSize - 0.1f);
                lineHeight = getFontHeight(fontSize) * LINE_SPACING;
            }
            nameX = viewX;
            locX = viewX + (viewWidth * 0.50);
            htX = viewX + (viewWidth * 0.60);
            srvX = viewX + (viewWidth * 0.68);
            mrvX = viewX + (viewWidth * 0.76);
            lrvX = viewX + (viewWidth * 0.84);
            ervX = viewX + (viewWidth * 0.92);
            indent = viewWidth * 0.02;

            currY = bbox.getY() + 10f;
        }

        void newLine() {
            currY += lineHeight;
        }

        void printCapitalHeader() {
            double lineHeight = FONT_SIZE_MEDIUM * LINE_SPACING;
            addTextElement(canvas, nameX, currY, "Capital Scale", FONT_SIZE_MEDIUM, "start", "bold");
            addTextElement(canvas, srvX, currY, "(1-12)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, mrvX, currY, "(13-24)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, lrvX, currY, "(25-40)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, ervX, currY, "(41-50)", FONT_SIZE_VSMALL, "middle", "normal");
            currY += lineHeight;

            // Capital Bay Line
            addTextElement(canvas, nameX, currY, "Bay", FONT_SIZE_MEDIUM, "start", "bold");
            addTextElement(canvas, locX, currY, "Loc", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, htX, currY, "Ht", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, srvX, currY, "SRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "MRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "LRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, ervX, currY, "ERV", FONT_SIZE_MEDIUM, "middle", "bold");
            currY += lineHeight;
        }

        void printStandardHeader() {
            double lineHeight = FONT_SIZE_MEDIUM * LINE_SPACING;
            addTextElement(canvas, nameX, currY, "Standard Scale", FONT_SIZE_MEDIUM, "start", "bold");
            addTextElement(canvas, srvX, currY, "(1-6)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, mrvX, currY, "(7-12)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, lrvX, currY, "(13-20)", FONT_SIZE_VSMALL, "middle", "normal");
            addTextElement(canvas, ervX, currY, "(21-25)", FONT_SIZE_VSMALL, "middle", "normal");
            currY += lineHeight;

            addTextElement(canvas, nameX, currY, "Bay", FONT_SIZE_MEDIUM, "start", "bold");
            addTextElement(canvas, locX, currY, "Loc", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, htX, currY, "Ht", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, srvX, currY, "SRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "MRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "LRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, ervX, currY, "ERV", FONT_SIZE_MEDIUM, "middle", "bold");
            currY += lineHeight;
        }

        void printReverseSideMessage() {
            addTextElement(canvas, nameX, currY, "Standard Scale on Reverse", FONT_SIZE_MEDIUM, "start", "bold");
            currY += getFontHeight(FONT_SIZE_MEDIUM) * LINE_SPACING;
        }

        void printWeaponBay(WeaponBayText bay, boolean isCapital) {
            boolean first = true;
            int numBayWeapons = bay.weapons.size();
            int bayHeat = 0;
            double baySRV, bayMRV, bayLRV, bayERV;
            baySRV = bayMRV = bayLRV = bayERV = 0;
            double standardBaySRV, standardBayMRV, standardBayLRV, standardBayERV;
            standardBaySRV = standardBayMRV = standardBayLRV = standardBayERV = 0;
            for (WeaponType wtype : bay.weapons.keySet()) {
                int numWeapons = bay.weapons.get(wtype);
                bayHeat += wtype.getHeat() * numWeapons;
                if (isCapital) {
                    baySRV += wtype.getShortAV() * numWeapons;
                    bayMRV += wtype.getMedAV() * numWeapons;
                    bayLRV += wtype.getLongAV() * numWeapons;
                    bayERV += wtype.getExtAV() * numWeapons;
                } else {
                    baySRV += Math.round((wtype.getShortAV() * numWeapons) / 10);
                    bayMRV += Math.round((wtype.getMedAV() * numWeapons) / 10);
                    bayLRV += Math.round((wtype.getLongAV() * numWeapons) / 10);
                    bayERV += Math.round((wtype.getExtAV() * numWeapons) / 10);
                    standardBaySRV += wtype.getShortAV() * numWeapons;
                    standardBayMRV += wtype.getMedAV() * numWeapons;
                    standardBayLRV += wtype.getLongAV() * numWeapons;
                    standardBayERV += wtype.getExtAV() * numWeapons;
                }
            }

            for (WeaponType wtype : bay.weapons.keySet()) {
                String locString = "";
                for (int i = 0; i < bay.loc.size(); i++) {
                    locString += ship.getLocationAbbr(bay.loc.get(i));
                    if ((i + 1) < bay.loc.size()) {
                        locString += "/";
                    }
                }
                String nameString;
                if (bay.weaponAmmo.containsKey(wtype)) {
                    Mounted ammo = bay.weaponAmmo.get(wtype);
                    if (wtype.getAmmoType() == AmmoType.T_AR10) {
                        nameString = wtype.getShortName() + " (" + (int) ammo.getAmmoCapacity() + " ton capacity)";
                    } else if (wtype.isCapital() && wtype.hasFlag(WeaponType.F_MISSILE)) {
                        nameString = wtype.getShortName() + " (" + ammo.getBaseShotsLeft() + " missiles)";
                    } else {
                        nameString = wtype.getShortName() + " (" + ammo.getBaseShotsLeft() + " rounds)";
                    }
                } else {
                    nameString = wtype.getShortName();
                }
                if (first & (numBayWeapons > 1)) {
                    nameString += ",";
                }
                if (wtype.getAmmoType() == AmmoType.T_AR10) {
                    // Depends on missile type
                    addTextElement(canvas, first ? nameX : nameX + indent, currY,
                            bay.weapons.get(wtype) + " " + nameString,
                            fontSize, "start", "normal");
                    addTextElement(canvas, locX, currY, locString, fontSize, "middle", "normal");
                    addTextElement(canvas, htX, currY, "*", fontSize, "middle", "normal");
                    addTextElement(canvas, srvX, currY, "*", fontSize, "middle", "normal");
                    addTextElement(canvas, mrvX, currY, "*", fontSize, "middle", "normal");
                    addTextElement(canvas, lrvX, currY, "*", fontSize, "middle", "normal");
                    addTextElement(canvas, ervX, currY, "*", fontSize, "middle", "normal");
                    currY += lineHeight;
                } else {
                    addWeaponText(first, bay.weapons.get(wtype), nameString, isCapital,
                            locString, bayHeat, new double[] { baySRV, bayMRV, bayLRV, bayERV },
                            new double[] { standardBaySRV, standardBayMRV, standardBayLRV, standardBayERV });
                }
                first = false;
            }
        }

        void addWeaponText(boolean first, int num, String name, boolean isCapital,
                String loc, int bayHeat, double[] capitalAV, double[] standardAV) {
            String srvTxt, mrvTxt, lrvTxt, ervTxt;
            if (isCapital) { // Print out capital damage for weapon total
                srvTxt = capitalAV[0] == 0 ? "-" : (int) capitalAV[0] + "";
                mrvTxt = capitalAV[1] == 0 ? "-" : (int) capitalAV[1] + "";
                lrvTxt = capitalAV[2] == 0 ? "-" : (int) capitalAV[2] + "";
                ervTxt = capitalAV[3] == 0 ? "-" : (int) capitalAV[3] + "";
            } else { // Print out capital and standard damages
                srvTxt = capitalAV[0] == 0 ? "-" : (int) capitalAV[0] + " (" + (int) standardAV[0] + ")";
                mrvTxt = capitalAV[1] == 0 ? "-" : (int) capitalAV[1] + " (" + (int) standardAV[1] + ")";
                lrvTxt = capitalAV[2] == 0 ? "-" : (int) capitalAV[2] + " (" + (int) standardAV[2] + ")";
                ervTxt = capitalAV[3] == 0 ? "-" : (int) capitalAV[3] + " (" + (int) standardAV[3] + ")";
            }
            String nameString = num + "  " + name;
            String heatTxt;
            double localNameX = nameX;
            if (!first) {
                localNameX += indent;
                loc = "";
                heatTxt = "";
                srvTxt = mrvTxt = lrvTxt = ervTxt = "";
            } else {
                heatTxt = String.valueOf(bayHeat);
            }

            addTextElement(canvas, localNameX, currY, nameString, fontSize, "start", "normal");
            addTextElement(canvas, locX, currY, loc, fontSize, "middle", "normal");
            addTextElement(canvas, htX, currY, heatTxt, fontSize, "middle", "normal");
            addTextElementToFit(canvas, srvX, currY, mrvX - srvX, srvTxt, fontSize, "middle", "normal");
            addTextElementToFit(canvas, mrvX, currY, lrvX - mrvX, mrvTxt, fontSize, "middle", "normal");
            addTextElementToFit(canvas, lrvX, currY, ervX - lrvX, lrvTxt, fontSize, "middle", "normal");
            addTextElementToFit(canvas, ervX, currY, (bbox.getX() + bbox.getWidth()) - ervX,
                    ervTxt, fontSize, "middle", "normal");
            currY += lineHeight;
        }

        void printAR10AmmoBlock() {
            double lineHeight = FONT_SIZE_MEDIUM * LINE_SPACING;
            addTextElement(canvas, nameX, currY, "AR10 Munitions", FONT_SIZE_MEDIUM, "start", "bold");
            addTextElement(canvas, locX, currY, "Tons", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, htX, currY, "Ht", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, srvX, currY, "SRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, mrvX, currY, "MRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, lrvX, currY, "LRV", FONT_SIZE_MEDIUM, "middle", "bold");
            addTextElement(canvas, ervX, currY, "ERV", FONT_SIZE_MEDIUM, "middle", "bold");
            currY += lineHeight;

            printAR10MissileLine("Killer Whale", 50, 20, 4);
            printAR10MissileLine("White Shark", 40, 15, 3);
            printAR10MissileLine("Barracuda", 30, 10, 2);
        }

        void printAR10MissileLine(String name, int weight, int heat, int av) {
            addTextElement(canvas, nameX, currY, name, FONT_SIZE_MEDIUM, "start", "normal");
            addTextElement(canvas, locX, currY, String.valueOf(weight), FONT_SIZE_MEDIUM, "middle", "normal");
            addTextElement(canvas, htX, currY, String.valueOf(heat), FONT_SIZE_MEDIUM, "middle", "normal");
            addTextElement(canvas, srvX, currY, String.valueOf(av), FONT_SIZE_MEDIUM, "middle", "normal");
            addTextElement(canvas, mrvX, currY, String.valueOf(av), FONT_SIZE_MEDIUM, "middle", "normal");
            addTextElement(canvas, lrvX, currY, String.valueOf(av), FONT_SIZE_MEDIUM, "middle", "normal");
            addTextElement(canvas, ervX, currY, String.valueOf(av), FONT_SIZE_MEDIUM, "middle", "normal");
            currY += lineHeight;
        }

        /**
         * Convenience method for printing information related to grav decks
         * 
         * @param canvas
         * @param currY
         * @return
         */
        private void printGravDecks() {
            if (ship.getTotalGravDeck() > 0) {
                addTextElement(canvas, nameX, currY, "Grav Decks:", FONT_SIZE_MEDIUM, "start", "bold");
                currY += getFontHeight(FONT_SIZE_MEDIUM) * LINE_SPACING;
                double xpos = nameX;
                double ypos = currY;
                int count = 1;
                for (int size : ship.getGravDecks()) {
                    String gravString = "Grav Deck #" + count + ": " + size + "-meters";
                    addTextElement(canvas, xpos, ypos, gravString, fontSize, "start", "normal");
                    ypos += lineHeight;
                    if (count == (ship.getGravDecks().size() / 2)) {
                        ypos = currY;
                        xpos = nameX + (bbox.getWidth() / 2.0);
                    }
                    count++;
                }
                currY += lineHeight * (((ship.getGravDecks().size() + 1) / 2) + 1);
            }
        }

        /**
         * Convenience method for printing infor related to cargo & transport bays.
         *
         * @param canvas
         * @param currY
         * @return
         * @throws SVGException
         */
        private void printBayInfo() {
            if (ship.getTransportBays().size() > 0) {
                addTextElement(canvas, nameX, currY, "Cargo:", FONT_SIZE_MEDIUM, "start", "bold");
                currY += getFontHeight(FONT_SIZE_MEDIUM) * LINE_SPACING;
                // We can have multiple Bay instances within one conceptual bay on the ship
                // We need to gather all bays with the same ID to print out the string
                Map<Integer, List<Bay>> bayMap = new TreeMap<>();
                for (Bay bay : ship.getTransportBays()) {
                    if (bay.isQuarters()) {
                        continue;
                    }
                    List<Bay> bays = bayMap.get(bay.getBayNumber());
                    if (bays == null) {
                        bays = new ArrayList<>();
                        bays.add(bay);
                        bayMap.put(bay.getBayNumber(), bays);
                    } else {
                        bays.add(bay);
                    }
                }
                // Print each bay
                for (Integer bayNum : bayMap.keySet()) {
                    StringBuilder bayTypeString = new StringBuilder();
                    StringBuilder bayCapacityString = new StringBuilder();
                    bayCapacityString.append(" (");
                    List<Bay> bays = bayMap.get(bayNum);
                    // Display larger storage first
                    java.util.Collections.sort(bays, new Comparator<Bay>() {

                        @Override
                        public int compare(Bay b1, Bay b2) {
                            return (int) (b2.getCapacity() - b1.getCapacity());
                        }
                    });
                    int doors = 0;
                    for (int i = 0; i < bays.size(); i++) {
                        Bay b = bays.get(i);
                        bayTypeString.append(b.getType());
                        bayCapacityString.append(NumberFormat.getInstance().format(b.getCapacity()));
                        if ((i + 1) < bays.size()) {
                            bayTypeString.append("/");
                            bayCapacityString.append("/");
                        }
                        doors = Math.max(doors, b.getDoors());
                    }
                    bayCapacityString.append(")");
                    String bayString = "Bay " + bayNum + ": " + bayTypeString
                            + bayCapacityString + " (" + doors + (doors == 1 ? " Door)" : " Doors)");
                    addTextElement(canvas, nameX, currY, bayString, fontSize, "start", "normal");
                    currY += lineHeight;
                }
                currY += lineHeight;
            }
        }
    }

    @Override
    protected void drawFluffImage() {
        String dir;
        if (getEntity() instanceof Warship) {
            dir = ImageHelper.imageWarship;
        } else if (getEntity() instanceof SpaceStation) {
            dir = ImageHelper.imageSpaceStation;
        } else {
            dir = ImageHelper.imageJumpship;
        }
        Element rect = getSVGDocument().getElementById("fluffImage");
        if ((null != rect) && (rect instanceof SVGRectElement)) {
            embedImage(ImageHelper.getFluffFile(ship, dir),
                    (Element) ((Node) rect).getParentNode(), getRectBBox((SVGRectElement) rect), true);
        }
    }
}
