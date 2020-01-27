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

import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import megamek.common.AmmoType;
import megamek.common.Bay;
import megamek.common.Entity;
import megamek.common.Jumpship;
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
     * The maximum number of inventory lines to print as a single page. Ideally this
     * would be determined by the space allocated by the svg template, but we need
     * to determine how many pages we are printing before the template is loaded so
     * we predetermine the value.
     */
    public static final int MAX_SINGLE_PAGE_LINES = 42;

    /**
     * The maximum number of lines to put on the first page if there has to be a second. This
     * is lower than the maximum to avoid having to scale down the text too much on the first page
     * while having empty space on the second.
     */
    public static final int PREFERRED_SINGLE_PAGE_LINES = 36;

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

    private final InventoryFormatter inventory;
    private final int[] linesPerBlock = new int[NUM_BLOCKS];
    private final boolean[] blockOnReverse = new boolean[NUM_BLOCKS];
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
        inventory = new InventoryFormatter(this);
        distributeEquipmentBlocks();
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param ship
     *            The ship to print
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
    public void processImage(int pageNum, PageFormat pageFormat) {
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
            if (element instanceof SVGRectElement) {
                writeEquipment((SVGRectElement) element, true);
            }
        } else {
            super.processImage(pageNum, pageFormat);
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
            if (element instanceof SVGRectElement) {
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
        if (element instanceof SVGRectElement) {
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
            } else { // Portrait - stacked 1 atop another
                startX = ((int) bbox.getX() + (int) ((bbox.getWidth() / 2) + 0.5))
                        - ((PIPS_PER_ROW * IS_PIP_WIDTH) / 2);
            }
            startY = (int) bbox.getY() + IS_PIP_HEIGHT;
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
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, "#000000");
            box.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, String.valueOf(0.5));
        }
        box.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fillColor);
        return box;
    }

    private void distributeEquipmentBlocks() {
        linesPerBlock[BLOCK_CAPITAL] = inventory.capitalBayLines();
        linesPerBlock[BLOCK_STANDARD] = inventory.standardBayLines();
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
        List<Bay> printableBays = ship.getTransports().stream().filter(t -> t instanceof Bay)
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
                if (SWITCH_PAGE_ORDER[toSwitch] == BLOCK_STANDARD) {
                    linesOnFront += 2; // account for message about standard weapons on reverse
                }
                toSwitch++;
            } while ((linesOnFront > PREFERRED_SINGLE_PAGE_LINES) && (toSwitch < SWITCH_PAGE_ORDER.length));
            // Another tweak for situations where there are no capital weapons. If only the
            // grav decks
            // are moved to page two, move bays as well to prevent a second page with only
            // one or two lines
            if (!blockOnReverse[BLOCK_STANDARD] && !blockOnReverse[BLOCK_BAYS]
                    && blockOnReverse[BLOCK_GRAV_DECK]) {
                blockOnReverse[BLOCK_BAYS] = true;
            }
        }
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
        inventory.setRegion(svgRect);
        int lines = 0;
        for (int block = 0; block < NUM_BLOCKS; block++) {
            if (blockOnReverse[block] == reverse) {
                lines += linesPerBlock[block];
            }
        }
        if (!reverse && blockOnReverse[BLOCK_STANDARD]) {
            lines += 2;
        }
        float[] metrics = inventory.scaleText(lines);
        final float fontSize = metrics[0];
        final float lineHeight = metrics[1];
        double currY = inventory.startingY();
        if ((linesPerBlock[BLOCK_CAPITAL] > 0) && (blockOnReverse[BLOCK_CAPITAL] == reverse)) {
            currY = inventory.writeCapitalBays(fontSize, lineHeight, currY) + lineHeight;
        }
        if ((linesPerBlock[BLOCK_AR10_AMMO] > 0)
                && (blockOnReverse[BLOCK_AR10_AMMO] == reverse)) {
            currY = inventory.printAR10Block(fontSize, lineHeight, currY) + lineHeight;
        }
        if (linesPerBlock[BLOCK_STANDARD] > 0) {
            if (blockOnReverse[BLOCK_STANDARD] == reverse) {
                currY = inventory.writeStandardBays(fontSize, lineHeight, currY) + lineHeight;
            } else if (!reverse) {
                currY = inventory.printReverseSideMessage(lineHeight, currY);
            }
        }
        if ((linesPerBlock[BLOCK_GRAV_DECK] > 0) && (blockOnReverse[BLOCK_GRAV_DECK] == reverse)) {
            currY = inventory.printGravDecks(ship, fontSize, lineHeight, currY);
        }
        if ((linesPerBlock[BLOCK_BAYS] > 0) && (blockOnReverse[BLOCK_BAYS] == reverse)) {
            inventory.printBayInfo(fontSize, lineHeight, currY);
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
        if (rect instanceof SVGRectElement) {
            embedImage(ImageHelper.getFluffFile(ship, dir),
                    (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
        }
    }
}
