/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
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
package megameklab.printing;

import megamek.common.*;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.print.PageFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Record sheet layout for Dropships, base class for other large craft
 */
public class PrintDropship extends PrintAero {

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
    private static final int BLOCK_FOOTER = 5;
    private static final int NUM_BLOCKS = 6;
    // The order in which to move blocks to the second page
    private static final int[] SWITCH_PAGE_ORDER = { BLOCK_STANDARD, BLOCK_GRAV_DECK, BLOCK_BAYS, BLOCK_FOOTER, BLOCK_AR10_AMMO };

    /**
     * The ship being printed
     */
    private final Aero ship;

    private final InventoryWriter inventory;
    private final int[] linesPerBlock = new int[NUM_BLOCKS];
    private final boolean[] blockOnReverse = new boolean[NUM_BLOCKS];
    private boolean secondPage = false;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param ship      The ship to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintDropship(Aero ship, int startPage, RecordSheetOptions options) {
        super(ship, startPage, options);
        this.ship = ship;
        inventory = new InventoryWriter(this);
        distributeEquipmentBlocks();
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param ship      The ship to print
     * @param startPage The print job page number for this sheet
     */
    public PrintDropship(Aero ship, int startPage) {
        this(ship, startPage, new RecordSheetOptions());
    }

    @Override
    public Entity getEntity() {
        return ship;
    }

    @Override
    public int getPageCount() {
        return secondPage ? 2 : 1;
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (pageNumber > 0) {
            return "advaero_reverse.svg";
        } else if (ship.isSpheroid()) {
            return "dropship_spheroid_default.svg";
        } else {
            return "dropship_aerodyne_default.svg";
        }
    }

    @Override
    protected String getRecordSheetTitle() {
        if (ship.isSpheroid()) {
            return "Spheroid Dropship Record Sheet";
        } else {
            return "Aerodyne Dropship Record Sheet";
        }
    }

    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        if (pageNum > 0) {
            Element element = getSVGDocument().getElementById(COPYRIGHT);
            if (null != element) {
                element.setTextContent(String.format(element.getTextContent(), LocalDate.now().getYear()));
            }
            setTextField(TITLE, getRecordSheetTitle().toUpperCase() + " (REVERSE)");
            setTextField(TYPE, getEntity().getShortNameRaw());
            setTextField(FLUFF_NAME, ""); // TODO: fluff name needs MM support
            element = getSVGDocument().getElementById(INVENTORY);
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
        setTextField(FLUFF_NAME, ""); // TODO: fluff name needs MM support
        // when fluff name is available, hide blankFluffName
        setTextField(N_CREW, ship.getNCrew());
        setTextField(N_MARINES, ship.getNMarines());
        setTextField(N_PASSENGERS, ship.getNPassenger());
        setTextField(LBL_BATTLEARMOR, ship.isClan() ? "Elementals:" : "BattleArmor:");
        setTextField(N_BATTLEARMOR, ship.getNBattleArmor());
        if (ship instanceof Jumpship) {
            setTextField(N_OTHER, ((Jumpship) ship).getNOtherCrew());
        } else if (ship instanceof Dropship) {
            setTextField(N_OTHER, ((Dropship) ship).getNOtherPassenger());
        }
        Element element = getSVGDocument().getElementById(LIFE_BOATS);
        if (null != element) {
            element.setTextContent(String.format(element.getTextContent(),
                    ship.getLifeBoats(), ship.getEscapePods()));
        }
        setTextField(HS_COUNT, ship.getHeatSinks());
        if (ship.getHeatType() == Aero.HEAT_DOUBLE) {
            setTextField(DOUBLE_HS_COUNT, "(" + (ship.getHeatSinks() * 2) + ")");
        } else {
            hideElement(DOUBLE_HS_COUNT);
        }
        setTextField(NOSE_HEAT, noseHeat());
        setTextField(FORE_SIDES_HEAT, foreLeftHeat() + "/" + foreRightHeat());
        setTextField(AFT_SIDES_HEAT, aftLeftHeat() + "/" + aftRightHeat());
        setTextField(AFT_HEAT, aftHeat());
        if (ship instanceof Warship) {
            setTextField(BROADSIDES_HEAT, broadsidesLeftHeat() + "/" + broadsidesRightHeat());
        }
    }

    int noseHeat() {
        return ship.getHeatInArc(Dropship.LOC_NOSE, false);
    }

    int foreLeftHeat() {
        return ship.getHeatInArc(Dropship.LOC_LWING, false);
    }

    int foreRightHeat() {
        return ship.getHeatInArc(Dropship.LOC_RWING, false);
    }

    int aftLeftHeat() {
        return ship.getHeatInArc(Dropship.LOC_LWING, true);
    }

    int aftRightHeat() {
        return ship.getHeatInArc(Dropship.LOC_RWING, true);
    }

    int aftHeat() {
        return ship.getHeatInArc(Dropship.LOC_AFT, false);
    }

    int broadsidesLeftHeat() {
        return 0;
    }

    int broadsidesRightHeat() {
        return 0;
    }

    private void distributeEquipmentBlocks() {
        linesPerBlock[BLOCK_CAPITAL] = inventory.capitalBayLines();
        linesPerBlock[BLOCK_STANDARD] = inventory.standardBayLines();
        // Most units will have 1-2 lines in the footer for fuel and non-weapon equipment.
        // This is always at the bottom so we don't need to account for a following empty line.
        linesPerBlock[BLOCK_FOOTER] = 2;
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
        if (ship instanceof Jumpship && !((Jumpship) ship).getGravDecks().isEmpty()) {
            linesPerBlock[BLOCK_GRAV_DECK] = ((((Jumpship) ship).getGravDecks().size() + 1) / 2) + 2;
        }
        // Add lines equal to number of transport bays and one each for section title
        // and following empty line
        if (inventory.transportBayLines() > 0) {
            linesPerBlock[BLOCK_BAYS] = inventory.transportBayLines() + 2;
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

    private int calcActualLines(boolean reverse, float fontSize) {
        int lines = 0;
        for (int block = 0; block < NUM_BLOCKS; block++) {
            if (blockOnReverse[block] == reverse) {
                lines += linesPerBlock[block];
            }
        }
        if (!reverse && blockOnReverse[BLOCK_STANDARD]) {
            lines += 2;
        }
        if (reverse == blockOnReverse[BLOCK_STANDARD] && linesPerBlock[BLOCK_STANDARD] > 0) {
            lines += inventory.extraStandardBayLines(fontSize);
        }
        if (reverse == blockOnReverse[BLOCK_CAPITAL] && linesPerBlock[BLOCK_CAPITAL] > 0) {
            lines += inventory.extraCapitalBayLines(fontSize);
        }
        if (reverse == blockOnReverse[BLOCK_FOOTER] && linesPerBlock[BLOCK_FOOTER] > 0) {
            lines += inventory.footerLines(fontSize);
        }
        return lines;
    }

    /**
     * Prints up to four equipment sections: capital weapons, standard scale, grav
     * decks, and bays. If there is too much to fit on a single page, the standard
     * scale weapons are moved to the second page (which is considered the reverse).
     *
     * @param svgRect The rectangle element that provides the dimensions of the space to
     *                print
     * @param reverse Whether this is printing on the reverse side.
     */
    private void writeEquipment(SVGRectElement svgRect, boolean reverse) {
        inventory.setRegion(svgRect);
        float[] metrics = inventory.scaleText(f -> calcActualLines(reverse, f));
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
            currY = inventory.printGravDecks((Jumpship) ship, fontSize, lineHeight, currY);
        }
        if ((linesPerBlock[BLOCK_BAYS] > 0) && (blockOnReverse[BLOCK_BAYS] == reverse)) {
            inventory.printBayInfo(fontSize, lineHeight, currY);
        }
        if ((linesPerBlock[BLOCK_FOOTER] > 0) && (blockOnReverse[BLOCK_FOOTER] == reverse)) {
            inventory.writeFooterBlock(fontSize, lineHeight);
        }
    }

    @Override
    public String formatFeatures() {
        StringJoiner sj = new StringJoiner(", ");
        Map<String, Integer> eqCount = new HashMap<>();
        for (Mounted mount : ship.getMisc()) {
            if (PrintUtil.isPrintableEquipment(mount.getType())) {
                eqCount.merge(mount.getShortName(), 1, Integer::sum);
            }
        }
        if (ship instanceof Jumpship && ((Jumpship) ship).hasLF()) {
            sj.add("LF Battery");
        }
        for (String eq : eqCount.keySet()) {
            if (eqCount.get(eq) > 1) {
                sj.add(eqCount.get(eq) + "x" + eq);
            } else {
                sj.add(eq);
            }
        }
        return sj.toString();
    }
}