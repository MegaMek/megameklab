/*
 * MegaMekLab - unit design companion of MegaMek
 * Copyright (C) 2020 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.printing;

import static megameklab.printing.PrintRecordSheet.FONT_SIZE_MEDIUM;
import static megameklab.printing.PrintRecordSheet.FONT_SIZE_VSMALL;
import static megameklab.printing.PrintRecordSheet.svgNS;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.MiscMounted;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import megamek.common.*;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.equipment.WeaponMounted;
import megameklab.util.UnitUtil;

/**
 * Formats text for the record sheet's "Weapons and Equipment Inventory" section. For most units,
 * an instance is created for the sheet using {@link #InventoryWriter(PrintEntity, SVGRectElement)},
 * then the text is added to the SVG document using {@link #writeEquipment()}. This adds the appropriate
 * header, for the unit, equipment table, and quirks/features/ammo at the bottom of the space. Large
 * craft can have multiple sections and should invoke the appropriate section writers, using each return value
 * for the vertical placement of the next section. Multi-page record sheets can instantiate using
 * {@link #InventoryWriter(PrintEntity)} to process the equipment and find the size of each section,
 * then call {@link #setRegion(SVGRectElement)} for each page before calling the section writers.
 */
public class InventoryWriter {

    // Proportion of the region width to indent subsequent lines of the same equipment entry
    private static final double INDENT = 0.02;
    /**
     * The minimum font size to use when scaling inventory text to fit into
     * available space
     */
    private static final float MIN_FONT_SIZE = 4.5f;
    /**
     * The amount of space between lines, as a factor of the font height determined
     * by {@link java.awt.FontMetrics}
     */
    public static final float MIN_LINE_SPACING = 0.7f;

    enum Column {
        QUANTITY ("Qty", 0.037),
        NAME ("Type", 0.075),
        BAY ("Bay", 0.02),
        LOCATION ("Loc", 0.41, 0.5),
        LOCATION_NO_HEAT ("Loc", 0.46, 0.55),
        HEAT ("Ht", 0.48, 0.6),
        DAMAGE ("Dmg", 0.53, 0.53, 0.47),
        MIN ("Min", 0.75, 0.75, 0.69),
        SHORT ("Sht", 0.82, 0.82, 0.78),
        MEDIUM ("Med", 0.89, 0.89, 0.87),
        LONG ("Lng", 0.96),
        SRV ("SRV", 0.68),
        MRV ("MRV", 0.76),
        LRV ("LRV", 0.84),
        ERV ("ERV", 0.92);

        final String header;
        final double groundX;
        final double aeroX;
        final double baX;

        Column(String header, double groundX, double aeroX, double baX) {
            this.header = header;
            this.groundX = groundX;
            this.aeroX = aeroX;
            this.baX = baX;
        }

        Column(String header, double groundX, double aeroX) {
            this(header, groundX, aeroX, groundX);
        }

        Column(String header, double x) {
            this(header, x, x, x);
        }

        double xFor(Entity en) {
            if (en.isAero()) {
                return aeroX;
            } else if (en instanceof BattleArmor) {
                return baX;
            } else {
                return groundX;
            }
        }

        static Column[] colsFor(Entity en) {
            if (en.isAero()) {
                if (en.tracksHeat() || en.isLargeCraft()) {
                    return new Column[]{QUANTITY, NAME, LOCATION, HEAT, SRV, MRV, LRV, ERV};
                } else {
                    return new Column[]{QUANTITY, NAME, LOCATION_NO_HEAT, SRV, MRV, LRV, ERV};
                }
            } else if (en instanceof BattleArmor) {
                return new Column[]{QUANTITY, NAME, DAMAGE, MIN, SHORT, MEDIUM, LONG};
            } else {
                if (en.tracksHeat()) {
                    return new Column[] {QUANTITY, NAME, LOCATION, HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG};
                } else {
                    return new Column[]{QUANTITY, NAME, LOCATION_NO_HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG};
                }
            }
        }

        /**
         * Column types when printing weapon bays
         */
        final static Column[] BAY_COLUMNS = {BAY, LOCATION, HEAT, SRV, MRV, LRV, ERV};
    }

    private final List<StandardInventoryEntry> equipment;
    private final List<WeaponBayInventoryEntry> standardBays;
    private final List<WeaponBayInventoryEntry> capitalBays;
    private final List<String> bayFootnotes;
    private final Map<String, Integer> ammo;
    private final List<Bay> transportBays;
    private final PrintEntity sheet;
    private Element canvas;
    private double viewX;
    private double viewY;
    private double viewWidth;
    private double viewHeight;
    private double indent;
    private final Column[] columnTypes;
    private final double[] colX;
    private final double[] bayColX;
    private final String ammoText;
    private final String fuelText;
    private final String featuresText;
    private final String miscNotesText;
    private final String quirksText;

    /**
     * Creates a new instance, determines column positions, and parses equipment.
     *
     * @param sheet The record sheet layout instance
     */
    public InventoryWriter(PrintEntity sheet) {
        this.sheet = sheet;
        columnTypes = Column.colsFor(sheet.getEntity());
        colX = new double[columnTypes.length];
        bayColX = new double[Column.BAY_COLUMNS.length];

        this.equipment = new ArrayList<>();
        this.capitalBays = new ArrayList<>();
        this.standardBays = new ArrayList<>();
        this.bayFootnotes = new ArrayList<>();
        this.ammo = new TreeMap<>();
        this.transportBays = sheet.getEntity().getTransports().stream().filter(t -> t instanceof Bay)
                .map(t -> (Bay) t).filter(b -> !b.isQuarters()).collect(Collectors.toList());
        if (sheet.getEntity().usesWeaponBays()) {
            parseBays();
        } else {
            parseEquipment();
        }
        String str = ammo.entrySet().stream()
                .map(e -> String.format("(%s) %d", e.getKey(), e.getValue()))
                .collect(Collectors.joining(", "));
        String ammoPrefix = "Ammo: ";
        if (sheet.getEntity().hasWorkingMisc(MiscType.F_CASE)
                && ((sheet.getEntity().getEntityType() & Entity.ETYPE_MEK) == 0)) {
            ammoPrefix = "Ammo (CASE): ";
        }
        ammoText = str.isEmpty() ? str : ammoPrefix + str;
        fuelText = sheet.formatTacticalFuel();
        str = sheet.formatFeatures();
        featuresText = str.isEmpty() ? str : "Features " + str;
        miscNotesText = sheet.formatMiscNotes();
        str = sheet.formatQuirks();
        quirksText = str.isEmpty() ? str : "Quirks: " + str;
    }

    public InventoryWriter(PrintEntity sheet, SVGRectElement svgRect) {
        this(sheet);
        setRegion(svgRect);
    }

    /**
     * Recalculates column positions for a new page
     * @param svgRect The bounding rectangle of the print region
     */
    public void setRegion(SVGRectElement svgRect) {
        this.canvas = (Element) svgRect.getParentNode();
        this.viewWidth = svgRect.getWidth().getBaseVal().getValue();
        this.viewHeight = svgRect.getHeight().getBaseVal().getValue();
        this.viewX = svgRect.getX().getBaseVal().getValue();
        this.viewY = svgRect.getY().getBaseVal().getValue();
        this.indent = viewWidth * INDENT;
        for (int i = 0; i < columnTypes.length; i++) {
            colX[i] = viewX + viewWidth * columnTypes[i].xFor(sheet.getEntity());
        }
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            bayColX[i] = viewX + viewWidth * Column.BAY_COLUMNS[i].aeroX;
        }
    }

    private void parseEquipment() {
        WeaponSortOrder sortOrder = sheet.options.getWeaponsOrder();
        List<Mounted<?>> sortedMounted = sheet.getEntity().getEquipment();
        if (sortOrder != WeaponSortOrder.DEFAULT) {
            sortedMounted = new ArrayList<>(sortedMounted); // Copy to sort without affecting the original
            Comparator<WeaponMounted> weaponSortComparator = sortOrder.getWeaponSortComparator(sheet.getEntity());
            sortedMounted.sort((m1, m2) -> {
                boolean w1 = m1 instanceof WeaponMounted;
                boolean w2 = m2 instanceof WeaponMounted;
                if (w1 && w2) {
                    return weaponSortComparator.compare((WeaponMounted)m1, (WeaponMounted)m2);
                } else if (w1) {
                    return -1;
                } else if (w2) {
                    return 1;
                }
                return 0;
            });
        }
        for (Mounted<?> m : sortedMounted) {
            if (m.isWeaponGroup()) {
                continue;
            }
            if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = m.getType().getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    ammo.merge(shortName.trim(), m.getBaseShotsLeft(), Integer::sum);
                } else if ((sheet.getEntity() instanceof ProtoMek)
                        && (((AmmoType) m.getType()).getAmmoType() == AmmoType.T_IATM)) {
                    // Bit of an ugly hack to get fusillade ammo to show up and identify as fusillade
                    // instead of iATM3
                    ammo.merge("Fusillade", m.getBaseShotsLeft(), Integer::sum);
                }
                continue;
            }
            if (UnitUtil.isMineDispenser(m.getType()) || UnitUtil.isRemoteSensorDispenser(m.getType())) {
                ammo.merge(m.getShortName(), m.getBaseShotsLeft(), Integer::sum);
            }
            if ((m.getLocation() == Entity.LOC_NONE)
                    || !PrintUtil.isPrintableEquipment(m.getType(), sheet.getEntity())) {
                continue;
            }
            if ((sheet.getEntity() instanceof QuadVee)
                    && (m.getType() instanceof MiscType)
                    && m.getType().hasFlag(MiscType.F_TRACKS)) {
                continue;
            }
            /**
             * BattleArmor have their own special mount points.
             * We can't rely on LOC_NONE as a filter because it matches LOC_SQUAD (which is a valid location for BA).
             * The solution is to filter out everything that has critical slots (means can't be for squad only)
             * and is mounted on MOUNT_LOC_NONE
             */
            if ((sheet.getEntity() instanceof BattleArmor)
                && (m.getCriticals() > 0)
                && (m.getBaMountLoc() == BattleArmor.MOUNT_LOC_NONE)) {
                continue;
            }
            StandardInventoryEntry entry = new StandardInventoryEntry(m);
            StandardInventoryEntry same = equipment.stream().filter(entry::equals).findFirst().orElse(null);
            if (null == same) {
                equipment.add(entry);
            } else {
                same.incrementQty();
            }
        }

        // Special entries for mek features which aren't represented by a MiscType.
        if (sheet.getEntity() instanceof Mek mek && mek.hasRiscHeatSinkOverrideKit()) {
            var mounted = new MiscMounted(sheet.getEntity(), new MiscType() {{
                name = "RISC Heat Sink Override Kit";
                shortName = "RISC HS Override Kit";
                internalName = "RISC Heat Sink Override Kit";
            }});
            mounted.setLocation(Mek.LOC_NONE);
            equipment.add(new StandardInventoryEntry(mounted));
        }
        if (sheet.getEntity() instanceof Mek mek && mek.hasFullHeadEject()) {
            var mounted = new MiscMounted(sheet.getEntity(), new MiscType() {{
                name = "Full Head Ejection System";
                shortName = "Full Head Eject System";
                internalName = "Full Head Ejection System";
            }});
            mounted.setLocation(Mek.LOC_NONE);
            equipment.add(new StandardInventoryEntry(mounted));
        }
    }

    private void parseBays() {
        List<WeaponMounted> standardWeapons = new ArrayList<>();
        List<WeaponMounted> capitalWeapons = new ArrayList<>();
        for (WeaponMounted m : sheet.getEntity().getWeaponList()) {
            if (m.getType().isCapital()) {
                capitalWeapons.add(m);
            } else {
                standardWeapons.add(m);
            }
        }
        List<AmmoMounted> ammos = sheet.getEntity().getAmmo();
        List<WeaponBayText> list = computeWeaponBayTexts(capitalWeapons, ammos);
        for (WeaponBayText text : list) {
            capitalBays.add(new WeaponBayInventoryEntry((Aero) sheet.getEntity(), text, true));
        }
        list = computeWeaponBayTexts(standardWeapons, ammos);
        boolean artemisIV = false;
        boolean artemisV = false;
        boolean apollo = false;
        for (WeaponBayText text : list) {
            WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry((Aero) sheet.getEntity(), text, false);
            standardBays.add(entry);
            artemisIV |= entry.hasArtemisIV();
            artemisV |= entry.hasArtemisV();
            apollo |= entry.hasApollo();
        }
        if (artemisIV) {
            bayFootnotes.add("* w/Artemis IV");
        }
        if (artemisV) {
            bayFootnotes.add(InventoryEntry.DAGGER + " w/Artemis V (-1 to hit)");
        }
        if (apollo) {
            bayFootnotes.add(InventoryEntry.DOUBLE_DAGGER + " w/Apollo (ignore +1 to hit)");
        }
    }

    /**
     * Iterate through a list of weapons and create information about what weapons
     * belong in what bays, how many, the bay damage, and also condense entries when
     * possible.
     *
     * @param weapons A list of weapon bays
     * @return A list of bays condensed by weapon type and symmetric location
     */
    private List<WeaponBayText> computeWeaponBayTexts(List<WeaponMounted> weapons, List<AmmoMounted> ammos) {
        List<WeaponBayText> weaponBayTexts = new ArrayList<>();
        // Collection info on weapons to print
        for (WeaponMounted bay : weapons) {
            WeaponBayText wbt = new WeaponBayText(bay.getLocation(), bay.isRearMounted());
            for (WeaponMounted weaponMounted : bay.getBayWeapons()) {
                if (!wbt.addBayWeapon(weaponMounted)) continue;
                for (AmmoMounted ammo : ammos) {
                    if (ammo.getLocation() == weaponMounted.getLocation()
                    && weaponMounted.getType().getAmmoType() == ammo.getType().getAmmoType()) {
                        wbt.addBayAmmo(weaponMounted.getType(), ammo);
                    }
                }
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

    public double startingY() {
        return viewY + sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
    }

    public int capitalBayLines() {
        return capitalBays.stream().mapToInt(WeaponBayInventoryEntry::nRows).sum();
    }

    /**
     * Calculates the number of additional lines required to print the capital scale weapon bay block due
     * to wrapping long names to another line.
     *
     * @param fontSize The font size used to print
     * @return         The number of extra lines required by the table
     */
    public int extraCapitalBayLines(float fontSize) {
        return extraLines(equipment, bayColX, fontSize, 0);
    }

    /**
     * Calculates the number of additional lines required to print the standard scale weapon bay block due
     * to wrapping long names to another line.
     *
     * @param fontSize The font size used to print
     * @return         The number of extra lines required by the table
     */
    public int extraStandardBayLines(float fontSize) {
        return extraLines(standardBays, bayColX, fontSize, 0);
    }

    private int extraLines(List<? extends InventoryEntry> list, double[] colX, float fontSize, int nameIndex) {
        int lines = 0;
        for (InventoryEntry entry : list) {
            double width = colX[nameIndex + 1] - colX[nameIndex] - indent;
            double row1Width = width - sheet.getTextLength(entry.getLocationField(0), fontSize) * 0.5;
            for (int r = 0; r < entry.nRows(); r++) {
                if (sheet.getTextLength(entry.getNameField(r), fontSize) >= (r == 0 ? row1Width : width)) {
                    lines++;
                }
            }
            if (sheet.showQuirks() && entry.hasQuirks()) {
                lines++;
            }
        }
        return lines;
    }

    /**
     * @return The size of the transport bay block, not including header
     */
    public int transportBayLines() {
        return transportBays.size();
    }

    public int standardBayLines() {
        return standardBays.stream().mapToInt(WeaponBayInventoryEntry::nRows).sum()
                + bayFootnotes.size();
    }

    public void writeEquipment() {
        double yPosition = startingY();
        if (sheet.getEntity().isAero()) {
            yPosition = printAeroStandardHeader(yPosition);
        } else {
            yPosition = printColumnHeaders(yPosition);
        }
        float[] metrics = scaleText(viewHeight - (yPosition - viewY), this::calcLineCount);
        yPosition = printEquipmentTable(equipment, yPosition, metrics[0], metrics[1]);
        if ((sheet.getEntity() instanceof SmallCraft || sheet.getEntity() instanceof SupportTank) && !transportBays.isEmpty()) {
            yPosition = printBayInfo(metrics[0], metrics[1], yPosition);
        }
        if (sheet.showHeatProfile()) {
            sheet.addTextElement(canvas, viewX + viewWidth * 0.025,
                    yPosition, sheet.heatProfileText(),
                    FONT_SIZE_MEDIUM, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
        }
        writeFooterBlock(metrics[0], metrics[1]);
    }

    /**
     * Adds the text for the standard scale weapon bays.
     *
     * @param fontSize The size of font to size for the table text
     * @param lineHeight The amount to increase the y coordinate for each line
     * @param currY The starting y coordinate
     * @return The y coordinate of the bottom of the table
     */
    public double writeStandardBays(float fontSize, double lineHeight, double currY) {
        currY = printAeroStandardHeader(currY, Column.BAY_COLUMNS, bayColX);
        currY = printEquipmentTable(standardBays, currY, fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
        for (String note : bayFootnotes) {
            sheet.addTextElement(canvas, bayColX[0], currY, note, fontSize,
                    SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            currY += lineHeight;
        }
        return currY;
    }

    /**
     * Adds the text for the capital scale weapon bays.
     *
     * @param fontSize The size of font to size for the table text
     * @param lineHeight The amount to increase the y coordinate for each line
     * @param currY The starting y coordinate
     * @return The y coordinate of the bottom of the table
     */
    public double writeCapitalBays(float fontSize, double lineHeight, double currY) {
        currY = printCapitalHeader(currY);
        return printEquipmentTable(capitalBays, currY, fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
    }

    /**
     * Rescales text to allow space for a fixed number of lines using the entire region. Used for large
     * craft, which have a number of possible block types and may need a second page.
     *
     * @param calcLines A supplier for the number of lines. Since reducing the font size may allow for fewer
     *                  lines, the supplier gives an opportunity to recalculate after each resizing.
     * @return A tuple of the new font height and line height, in that order
     */
    public float[] scaleText(Function<Float, Integer> calcLines) {
        return scaleText(viewHeight, calcLines);
    }

    static private float INITIAL_LINE_SPACING = 1.2f; // the initial line spacing factor
    static private float LINE_SPACING_REDUCTION_STEP = 0.05f; // the amount we reduce the line spacing each attempt
    static private float FONT_SIZE_REDUCTION_STEP = 0.25f; // the amount we reduce the font each attempt
    // the minimum line spacing during the first "line spacing only" attempts
    // if we can't fit the text, we will rollback and we will start reducing the
    // font size and line spacing
    static private float MIN_LINE_SPACING_IN_SPECIAL_ATTEMPTS = 1.0f;
    // the ratio of attempts between font size and line spacing.
    // if this number is > 1, we will do more attempts to reduce line spacing compared to font size
    static private float RATIO_FONT_SIZE_LINE_SPACING_ATTEMPTS = 2;

    /**
     * If the lines do not fit in the available space, we will need to reduce the font size
     * and possible the amount of space between lines.
     * We first try to reduce the line spacing factor until a certain threshold,
     * and if it fails we start an alternate cycle of attempts between font size
     * and line spacing reduction.
     *
     * @param height The height of the region the text needs to fit in
     * @param calcLines A supplier for the number of lines. Since reducing the font size may allow for fewer
     *                  lines, the supplier gives an opportunity to recalculate after each resizing.
     * @return A tuple of the new font height and line height, in that order
     */
    public float[] scaleText(double height, Function<Float, Integer> calcLines) {
        float currentFontSize = FONT_SIZE_MEDIUM;
        float currentLineSpacingFactor = INITIAL_LINE_SPACING;
        int lines;
        float actualLineHeight;
        int alternateReductionState = 0; // 0 = prioritize font size, 1 = prioritize line spacing
        boolean attemptForLineSpacingOnly = true;
        while (true) {
            lines = calcLines.apply(currentFontSize);
            float fontMetricsHeight = sheet.getFontHeight(currentFontSize);
            actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;

            if (actualLineHeight * lines < height) {
                // Text fits! break the loop
                break;
            }

            // We try first to reduce the line spacing factor to see if it is enough
            // to fit the text. If it fails, we rollback and we start an alternate
            // cycle of attempts between font and line spacing.
            if (attemptForLineSpacingOnly) {
                attemptForLineSpacingOnly = false;
                boolean fitFoundInSpecialPhase = false;
                final float originalLineSpacingFactor = currentLineSpacingFactor;
                while (true) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING, currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    if (currentLineSpacingFactor < MIN_LINE_SPACING_IN_SPECIAL_ATTEMPTS) {
                        // Too much reduction, we failed
                        break;
                    }
                    actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;
                    if (actualLineHeight * lines < height) {
                        fitFoundInSpecialPhase = true;
                        break;
                    }
                }
                if (fitFoundInSpecialPhase) {
                    // We found the fit! The main loop will to the final check.
                    continue;
                } else {
                    // Rollback
                    currentLineSpacingFactor = originalLineSpacingFactor;
                    actualLineHeight = fontMetricsHeight * currentLineSpacingFactor;
                }
            }


            boolean reductionMade = false;
            // This is the alternate reduction cycle between font size and line spacing factor
            if (alternateReductionState == 0) { // Prioritize reducing font size
                if (currentFontSize > MIN_FONT_SIZE) {
                    currentFontSize = Math.max(MIN_FONT_SIZE, currentFontSize - FONT_SIZE_REDUCTION_STEP);
                    reductionMade = true;
                } else if (currentLineSpacingFactor > MIN_LINE_SPACING) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING, currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    reductionMade = true;
                }
            } else { // Prioritize reducing line spacing factor
                if (currentLineSpacingFactor > MIN_LINE_SPACING) {
                    currentLineSpacingFactor = Math.max(MIN_LINE_SPACING, currentLineSpacingFactor - LINE_SPACING_REDUCTION_STEP);
                    reductionMade = true;
                } else if (currentFontSize > MIN_FONT_SIZE) {
                    currentFontSize = Math.max(MIN_FONT_SIZE, currentFontSize - FONT_SIZE_REDUCTION_STEP);
                    reductionMade = true;
                }
            }
            
            alternateReductionState++;
            if (alternateReductionState > RATIO_FONT_SIZE_LINE_SPACING_ATTEMPTS) {
                alternateReductionState = 0;
            }

            if (!reductionMade) {
                // No more reductions possible, break the loop
                break;
            }
        }
        return new float[] { currentFontSize, actualLineHeight };
    }

    /**
     * Displays ammo, fuel, features, and quirks as free-flowing text at the bottom of the inventory box
     *
     * @param fontSize   The size of the font for the text
     * @param lineHeight The height of each line of text
     */
    public void writeFooterBlock(float fontSize, float lineHeight) {
        int lines;
        if (ammo.size() + fuelText.length() + featuresText.length() + miscNotesText.length() + quirksText.length() > 0) {
            Element svgGroup = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            canvas.appendChild(svgGroup);
            lines = 0;
            final double xPosition = (viewX + viewWidth * 0.025);
            final double textWidth = viewWidth * 0.95;
            if (!ammoText.isEmpty()) {
                lines = sheet.addMultilineTextElement(svgGroup, xPosition, 0, textWidth, lineHeight,
                        ammoText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!fuelText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, xPosition,
                        lines * lineHeight, textWidth, lineHeight,
                        fuelText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!featuresText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, xPosition,
                        lines * lineHeight, textWidth, lineHeight,
                        featuresText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!miscNotesText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, xPosition,
                        lines * lineHeight, textWidth, lineHeight,
                        miscNotesText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!quirksText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, xPosition, lines * lineHeight, textWidth, lineHeight,
                        quirksText, fontSize*0.9f, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_ITALIC_VALUE);
            }
            final double totalHeight = lines * lineHeight;
            svgGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                    String.format("%s(0,%f)", SVGConstants.SVG_TRANSLATE_VALUE,
                            viewY + viewHeight - totalHeight));
        }
    }

    private double printColumnHeaders(double currY) {
        return printColumnHeaders(currY, columnTypes, colX);
    }

    private double printColumnHeaders(double currY, Column[] columnTypes, double[] colX) {
        for (int i = 0; i < columnTypes.length; i++) {
            String anchor;
            if (Column.NAME.equals(columnTypes[i])
                    || Column.BAY.equals(columnTypes[i])
                    || Column.DAMAGE.equals(columnTypes[i])) {
                anchor = SVGConstants.SVG_START_VALUE;
            } else {
                anchor = SVGConstants.SVG_MIDDLE_VALUE;
            }
            sheet.addTextElement(canvas, colX[i], currY, columnTypes[i].header, FONT_SIZE_MEDIUM,
                    anchor, SVGConstants.SVG_BOLD_VALUE);
        }
        return currY + sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.1;
    }

    /**
     * Calculates the total line height at the given font size for units that have a single equipment
     * block and possible footer, e.g. those other than large craft. Because lines breaks are inserted between
     * words, it's possible that the number of lines may be more.
     *
     * @param fontSize The size of the font
     * @return The estimated line count.
     */
    private int calcLineCount(float fontSize) {
        // The width of the name field varies depending on aero/ground or whether there is a heat column,
        // but is always the difference between the second and third.
        double damageWidth = Double.MAX_VALUE;
        for (int i = 0; i < columnTypes.length; i++) {
            if (Column.DAMAGE.equals(columnTypes[i])) {
                damageWidth = colX[i + 1] - colX[i] - (fontSize / 2);
                break;
            }
        }
        int lines = 0;
        for (StandardInventoryEntry line : equipment) {
            int rows = line.nRows();
            lines += rows;
            double baseNameWidth = colX[2] - colX[1] - viewWidth * INDENT;
            double nameWidth = baseNameWidth;
            if (!(sheet.getEntity() instanceof BattleArmor)) {
                nameWidth -= sheet.getTextLength(line.getLocationField(0), fontSize) * 0.5;
            }
            if (sheet.getTextLength(line.getNameField(0), fontSize) > nameWidth) {
                lines++;
            } else
            if (sheet.getTextLength(line.getDamageField(0), fontSize) > damageWidth) {
                lines++;
            }
            if (sheet.showQuirks() && line.hasQuirks()) {
                lines += (int) Math.ceil(sheet.getItalicTextLength(line.getQuirksField(), fontSize) / (viewWidth * 0.96));
            }
        }
        if (sheet.getEntity() instanceof SmallCraft && !transportBays.isEmpty()) {
            lines += transportBays.size() + 1; // add extra for header
        }
        lines += footerLines(fontSize);
        if (sheet.showHeatProfile()) {
            lines++;
        }
        return lines;
    }

    public int footerLines(float fontSize) {
        return (int) Math.ceil(sheet.getTextLength(ammoText, fontSize) / viewWidth)
            + (int) Math.ceil(sheet.getTextLength(fuelText, fontSize) / viewWidth)
            + (int) Math.ceil(sheet.getTextLength(featuresText, fontSize) / viewWidth)
            + (int) Math.ceil(sheet.getTextLength(miscNotesText, fontSize) / viewWidth)
            + (int) Math.ceil(sheet.getItalicTextLength(quirksText, fontSize) / viewWidth);
    }

    /**
     * Adds a section to the inventory section. An entry that does not fit into the
     * allocated space
     * will wrap to the next line. This is tracked using the repurposed lines local
     * variable. Some
     * entries are already given multiple lines (such as missile launchers with
     * Artemis), which
     * will be handled in the inner loop. We need to compare the two to make sure we
     * don't add
     * extra line feeds. This algorithm works on the assumption that pre-splitting
     * values into multiple
     * rows ensures that they will fit and not need to wrap.
     *
     * @param list       The list of entries for this table
     * @param fontSize   The size of font to use for printing the table
     * @param yPosition  The starting y coordinate relative to the parent element
     * @param lineHeight The amount to add to the y coordinate for each line
     */
    private double printEquipmentTable(List<? extends InventoryEntry> list,
                                       double yPosition, float fontSize, double lineHeight) {
        return printEquipmentTable(list, yPosition, fontSize, lineHeight, columnTypes, colX);
    }

    /**
     * Adds a section to the inventory section. An entry that does not fit into the
     * allocated space
     * will wrap to the next line. This is tracked using the repurposed lines local
     * variable. Some
     * entries are already given multiple lines (such as missile launchers with
     * Artemis), which
     * will be handled in the inner loop. We need to compare the two to make sure we
     * don't add
     * extra line feeds. This algorithm works on the assumption that pre-splitting
     * values into multiple
     * rows ensures that they will fit and not need to wrap.
     *
     * @param list        The list of entries for this table
     * @param fontSize    The size of font to use for printing the table
     * @param yPosition   The starting y coordinate relative to the parent element
     * @param lineHeight  The amount to add to the y coordinate for each line
     * @param columnTypes The columns to include in the table. Used for overriding
     *                    when printing bays.
     * @param colX        The x coordinate of each column, corresponding to the same
     *                    index in <code>columnTypes</code>
     */
    private double printEquipmentTable(List<? extends InventoryEntry> list,
                                       double yPosition, float fontSize, double lineHeight, Column[] columnTypes, double[] colX) {
        for (InventoryEntry line : list) {
            for (int row = 0; row < line.nRows(); row++) {
                int lines = 1;
                for (int i = 0; i < columnTypes.length; i++) {
                    switch (columnTypes[i]) {
                        case QUANTITY:
                            if (row == 0) {
                                sheet.addTextElement(canvas, colX[i],
                                        yPosition, line.getQuantityField(row),
                                        fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            }
                            break;
                        case NAME:
                        case BAY:
                            // Calculate the width of the name field to determine if wrapping is required.
                            // The following column is always location, which is centered, so we need
                            // to subtract half the width of that field as well, plus a bit of extra space.
                            double width = colX[i + 1] - colX[i] - indent;
                            if (!(sheet.getEntity() instanceof BattleArmor)) {
                                width -= sheet.getTextLength(line.getLocationField(row), fontSize) * 0.5;
                            }
                            if (row == 0) {
                                lines = sheet.addMultilineTextElement(canvas, colX[i],
                                        yPosition, width, lineHeight,
                                        line.getNameField(row, sheet, width, fontSize), fontSize, SVGConstants.SVG_START_VALUE,
                                        SVGConstants.SVG_NORMAL_VALUE);
                            } else {
                                lines = sheet.addMultilineTextElement(canvas, line.indentMultiline() ?
                                        colX[i] + indent : colX[i],
                                        yPosition, width, lineHeight,
                                        line.getNameField(row, sheet, width, fontSize), fontSize, SVGConstants.SVG_START_VALUE,
                                        SVGConstants.SVG_NORMAL_VALUE);
                            }
                            break;
                        case LOCATION:
                        case LOCATION_NO_HEAT:
                            sheet.addTextElement(canvas, colX[i],
                                    yPosition, line.getLocationField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case HEAT:
                            sheet.addTextElement(canvas, colX[i],
                                    yPosition, line.getHeatField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case DAMAGE:
                            final float rightPadding = (fontSize / 2);
                            lines = Math.max(lines, sheet.addMultilineTextElement(canvas, colX[i],
                                    yPosition,
                                    colX[i + 1] - colX[i] - rightPadding, lineHeight, line.getDamageField(row),
                                    fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE));
                            break;
                        case MIN:
                            sheet.addTextElement(canvas, colX[i],
                                    yPosition, line.getMinField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case SHORT:
                        case SRV:
                            sheet.addTextElementToFit(canvas, colX[i],
                                    yPosition, colX[i + 1] - colX[i] - 1,
                                    line.getShortField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case MEDIUM:
                        case MRV:
                            sheet.addTextElementToFit(canvas, colX[i],
                                    yPosition, colX[i + 1] - colX[i] - 1,
                                    line.getMediumField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case LONG:
                        case LRV:
                            sheet.addTextElementToFit(canvas, colX[i],
                                    yPosition, colX[i] - colX[i - 1] - 1,
                                    line.getLongField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case ERV:
                            sheet.addTextElementToFit(canvas, colX[i],
                                    yPosition, colX[i] - colX[i - 1] - 1,
                                    line.getExtremeField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                    }
                }
                yPosition += lineHeight * lines;
            }
            if (sheet.showQuirks() && line.hasQuirks()) {
                int lines = sheet.addMultilineTextElement(canvas, colX[1] + indent,
                            yPosition, (viewWidth * 0.96) - (colX[0] + indent), lineHeight*0.9,
                            line.getQuirksField(), (float) (fontSize*0.9), SVGConstants.SVG_START_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_ITALIC_VALUE);
                yPosition += lineHeight * lines;
            }
        }
        return yPosition;
    }

    private double printCapitalHeader(double currY) {
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            switch (Column.BAY_COLUMNS[i]) {
                case NAME:
                case BAY:
                    sheet.addTextElement(canvas, bayColX[i], currY, "Capital Scale", FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case SRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                            "(1-12)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case MRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                            "(13-24)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case LRV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i + 1] - bayColX[i] - 1,
                            "(25-40)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case ERV:
                    sheet.addTextElementToFit(canvas, bayColX[i], currY, bayColX[i] - bayColX[i - 1] - 1,
                            "(41-50)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        return printColumnHeaders(currY, Column.BAY_COLUMNS, bayColX);
    }

    private double printAeroStandardHeader(double currY) {
        return printAeroStandardHeader(currY, columnTypes, colX);
    }

    private double printAeroStandardHeader(double currY, Column[] columnTypes, double[] colX) {
        for (int i = 0; i < columnTypes.length; i++) {
            switch (columnTypes[i]) {
                case NAME:
                case BAY:
                    // Use first bay field to left-justify whether this unit uses bays or not
                    sheet.addTextElement(canvas, bayColX[0], currY, "Standard Scale", FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case SRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                            "(1-6)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case MRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                            "(7-12)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case LRV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i + 1] - colX[i] - 1,
                            "(13-20)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                case ERV:
                    sheet.addTextElementToFit(canvas, colX[i], currY, colX[i] - colX[i - 1] - 1,
                            "(21-25)", FONT_SIZE_VSMALL, SVGConstants.SVG_MIDDLE_VALUE,
                            SVGConstants.SVG_NORMAL_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        return printColumnHeaders(currY);
    }

    /**
     * Adds a static table showing the details of the types of AR10 missiles.
     *
     * @param fontSize The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY The y coordinate of the top of the table
     * @return The y coordinate of the bottom of the table
     */
    public double printAR10Block(float fontSize, double lineHeight, double currY) {
        for (int i = 0; i < Column.BAY_COLUMNS.length; i++) {
            switch (Column.BAY_COLUMNS[i]) {
                case BAY:
                    sheet.addTextElement(canvas, bayColX[i], currY, "AR10 Munitions", FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case LOCATION:
                    sheet.addTextElement(canvas, bayColX[i], currY, "Tons", FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                case HEAT:
                case SRV:
                case MRV:
                case LRV:
                case ERV:
                    sheet.addTextElement(canvas, bayColX[i], currY, Column.BAY_COLUMNS[i].header, FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
                default:
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        currY = printEquipmentTable(Collections.singletonList(new AR10InventoryEntry()), currY,
                fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
        return currY;
   }

    /**
     * Prints the gravity deck block
     * @param ship The jumpship, warship, or space station
     * @param fontSize The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY The y coordinate of the top of the table
     * @return The y coordinate of the bottom of the table
     */
    public double printGravityDecks(Jumpship ship, float fontSize, double lineHeight, double currY) {
        if (ship.getTotalGravDeck() > 0) {
            double xPosition = bayColX[0];
            sheet.addTextElement(canvas,
                    xPosition, currY, "Grav Decks:",
                    FONT_SIZE_MEDIUM, SVGConstants.SVG_START_VALUE,
                    SVGConstants.SVG_BOLD_VALUE);
            currY += lineHeight;
            double yPosition = currY;
            int count = 1;
            for (int size : ship.getGravDecks()) {
                String gravityString = "Grav Deck #" + count + ": " + size + "-meters";
                sheet.addTextElement(canvas, xPosition, yPosition, gravityString, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                yPosition += lineHeight;
                if (count == (ship.getGravDecks().size() / 2)) {
                    yPosition = currY;
                    xPosition += viewWidth / 2.0;
                }
                count++;
            }
            currY += lineHeight * (((double) ((ship.getGravDecks().size() + 1) / 2)) + 1);
        }
        return currY;
    }

    /**
     * Convenience method for printing information related to cargo and transport bays.
     *
     * @param fontSize The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY The y coordinate of the top of the table
     * @return The y coordinate of the bottom of the table
     */
    public double printBayInfo(float fontSize, double lineHeight, double currY) {
        if (!transportBays.isEmpty()) {
            sheet.addTextElement(canvas, bayColX[0], currY, "Cargo:", FONT_SIZE_MEDIUM,
                    SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
            currY += lineHeight;
            // We can have multiple Bay instances within one conceptual bay on the ship
            // We need to gather all bays with the same ID to print out the string
            Map<Integer, List<Bay>> bayMap = new TreeMap<>();
            for (Bay bay : transportBays) {
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
                bays.sort(Comparator.comparing(Bay::getCapacity));
                int doors = 0;
                for (int i = 0; i < bays.size(); i++) {
                    Bay b = bays.get(i);
                    bayTypeString.append(b.getType());
                    // BA bays are shown per suit rather than squad
                    double capacity = b.getCapacity();
                    if (b instanceof BattleArmorBay) {
                        if (b.isClan()) {
                            capacity *= 5;
                        } else if (((BattleArmorBay) b).isComStar()) {
                            capacity *= 6;
                        } else {
                            capacity *= 4;
                        }
                    } else if (b instanceof InfantryBay) {
                        // Divide total weight by weight required by platoon to get platoon capacity
                        capacity /= ((InfantryBay) b).getPlatoonType().getWeight();
                    }
                    bayCapacityString.append(NumberFormat.getInstance().format(capacity));
                    if ((i + 1) < bays.size()) {
                        bayTypeString.append('/');
                        bayCapacityString.append('/');
                    }
                    doors = Math.max(doors, b.getDoors());
                }
                bayCapacityString.append(')');
                String bayString = "Bay " + bayNum + ": " + bayTypeString
                        + bayCapacityString + " (" + doors + (doors == 1 ? " Door)" : " Doors)");
                sheet.addTextElement(canvas, bayColX[0], currY, bayString, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                currY += lineHeight;
            }
            currY += lineHeight;
        }
        return currY;
    }

    /**
     * Add single line of text explaining where to find the standard scale bays for ships that
     * cannot fit both capital and standard on the obverse.
     *
     * @param lineHeight The height of each table row
     * @param currY The y coordinate of the top of the table
     * @return The y coordinate of the bottom of the table
     */
    public double printReverseSideMessage(double lineHeight, double currY) {
        sheet.addTextElement(canvas, bayColX[0],
                currY, "Standard Scale on Reverse", FONT_SIZE_MEDIUM,
                SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        return currY + lineHeight * 2;
    }
}
