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

import megamek.common.*;
import megamek.common.equipment.WeaponMounted;
import megameklab.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static megameklab.printing.PrintRecordSheet.*;

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
                && ((sheet.getEntity().getEntityType() & Entity.ETYPE_MECH) == 0)) {
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
        for (Mounted<?> m : sheet.getEntity().getEquipment()) {
            if (m.isWeaponGroup()) {
                continue;
            }
            if ((m.getType() instanceof AmmoType) && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = m.getType().getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    ammo.merge(shortName.trim(), m.getBaseShotsLeft(), Integer::sum);
                } else if ((sheet.getEntity() instanceof Protomech)
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
            StandardInventoryEntry entry = new StandardInventoryEntry(m);
            StandardInventoryEntry same = equipment.stream().filter(entry::equals).findFirst().orElse(null);
            if (null == same) {
                equipment.add(entry);
            } else {
                same.incrementQty();
            }
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
        List<WeaponBayText> list = computeWeaponBayTexts(capitalWeapons);
        for (WeaponBayText text : list) {
            capitalBays.add(new WeaponBayInventoryEntry((Aero) sheet.getEntity(), text, true));
        }
        list = computeWeaponBayTexts(standardWeapons);
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
    private List<WeaponBayText> computeWeaponBayTexts(List<WeaponMounted> weapons) {
        List<WeaponBayText> weaponBayTexts = new ArrayList<>();
        // Collection info on weapons to print
        for (WeaponMounted bay : weapons) {
            WeaponBayText wbt = new WeaponBayText(bay.getLocation(), bay.isRearMounted());
            for (WeaponMounted weap : bay.getBayWeapons()) {
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
        double ypos = startingY();
        if (sheet.getEntity().isAero()) {
            ypos = printAeroStandardHeader(ypos);
        } else {
            ypos = printColumnHeaders(ypos);
        }
        float[] metrics = scaleText(viewHeight - (ypos - viewY), this::calcLineCount);
        ypos = printEquipmentTable(equipment, ypos, metrics[0], metrics[1]);
        if (sheet.getEntity() instanceof SmallCraft && !transportBays.isEmpty()) {
            printBayInfo(metrics[0], metrics[1], ypos);
        }
        if (sheet.showHeatProfile()) {
            sheet.addTextElement(canvas, viewX + viewWidth * 0.025, ypos, sheet.heatProfileText(),
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

    /**
     * If the lines do not fit in the available space, we will need to reduce the font size
     * and possible the amount of space between lines. We take it in steps of -0.5 instead of
     * scaling proportionately because not only is the relationship between font size and height
     * not directly proportional, but a smaller reduction may be sufficient to reduce the number
     * of line required for longer fields.
     *
     * @param height The height of the region the text needs to fit in
     * @param calcLines A supplier for the number of lines. Since reducing the font size may allow for fewer
     *                  lines, the supplier gives an opportunity to recalculate after each resizing.
     * @return A tuple of the new font height and line height, in that order
     */
    public float[] scaleText(double height, Function<Float, Integer> calcLines) {
        float fontSize = FONT_SIZE_MEDIUM;
        float lineSpacing = 1.2f;
        float lineHeight = sheet.getFontHeight(fontSize) * lineSpacing;
        int lines = calcLines.apply(fontSize);
        while ((fontSize > MIN_FONT_SIZE) && (lineHeight * lines >= height)) {
            if (lineSpacing > MIN_LINE_SPACING) {
                lineSpacing -= 0.1f;
            } else {
                fontSize = Math.max(MIN_FONT_SIZE, fontSize - 0.5f);
            }
            lines = calcLines.apply(fontSize);
            lineHeight = sheet.getFontHeight(fontSize) * lineSpacing;
        }
        return new float[] { fontSize, lineHeight };
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
            if (!ammoText.isEmpty()) {
                lines = sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, 0, viewWidth * 0.95, lineHeight,
                        ammoText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!fuelText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025,
                        lines * lineHeight, viewWidth * 0.95, lineHeight,
                        fuelText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!featuresText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025,
                        lines * lineHeight, viewWidth * 0.95, lineHeight,
                        featuresText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!miscNotesText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025,
                        lines * lineHeight, viewWidth * 0.95, lineHeight,
                        miscNotesText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (!quirksText.isEmpty()) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, lines * lineHeight,
                        viewWidth * 0.95, lineHeight,
                        quirksText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            svgGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                    String.format("%s(0,%f)", SVGConstants.SVG_TRANSLATE_VALUE,
                            viewY + viewHeight - lines * lineHeight));
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
        final double nameWidth = colX[2] - colX[1] - viewWidth * INDENT;
        double dmgWidth = Double.MAX_VALUE;
        for (int i = 0; i < columnTypes.length; i++) {
            if (Column.DAMAGE.equals(columnTypes[i])) {
                dmgWidth = colX[i + 1] - colX[i];
                break;
            }
        }
        int lines = 0;
        for (StandardInventoryEntry line : equipment) {
            int rows = line.nRows();
            // If the name or damage field is too long to fit in the space, make sure there is a second row
            if ((rows == 1) &&
                    ((sheet.getTextLength(line.getNameField(0), fontSize) > nameWidth) ||
                        sheet.getTextLength(line.getDamageField(0), fontSize) > dmgWidth - fontSize)) {
                rows++;
            }
            lines += rows;
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
            + (int) Math.ceil(sheet.getTextLength(quirksText, fontSize) / viewWidth);
    }

    /**
     *  Adds a section to the inventory section. An entry that does not fit into the allocated space
     *  will wrap to the next line. This is tracked using the repurposed lines local variable. Some
     *  entries are already given multiple lines (such as missile launchers with Artemis), which
     *  will be handled in the inner loop. We need to compare the two to make sure we don't add
     *  extra linefeeds. This algorithm works on the assumption that presplitting values into multiple
     *  rows ensures that they will fit and not need to wrap.
     *
     * @param list The list of entries for this table
     * @param fontSize The size of font to use for printing the table
     * @param ypos The starting y coordinate relative to the parent element
     * @param lineHeight The amount to add to the y coordinate for each line
     */
    private double printEquipmentTable(List<? extends InventoryEntry> list,
                                       double ypos, float fontSize, double lineHeight) {
        return printEquipmentTable(list, ypos, fontSize, lineHeight, columnTypes, colX);
    }

    /**
     *  Adds a section to the inventory section. An entry that does not fit into the allocated space
     *  will wrap to the next line. This is tracked using the repurposed lines local variable. Some
     *  entries are already given multiple lines (such as missile launchers with Artemis), which
     *  will be handled in the inner loop. We need to compare the two to make sure we don't add
     *  extra linefeeds. This algorithm works on the assumption that presplitting values into multiple
     *  rows ensures that they will fit and not need to wrap.
     *
     * @param list The list of entries for this table
     * @param fontSize The size of font to use for printing the table
     * @param ypos The starting y coordinate relative to the parent element
     * @param lineHeight The amount to add to the y coordinate for each line
     * @param columnTypes The columns to include in the table. Used for overriding when printing bays.
     * @param colX The x coordinate of each column, corresponding to the same index in <code>columnTypes</code>
     */
    private double printEquipmentTable(List<? extends InventoryEntry> list,
                                       double ypos, float fontSize, double lineHeight, Column[] columnTypes, double[] colX) {
        for (InventoryEntry line : list) {
            for (int row = 0; row < line.nRows(); row++) {
                int lines = 1;
                for (int i = 0; i < columnTypes.length; i++) {
                    switch (columnTypes[i]) {
                        case QUANTITY:
                            if (row == 0) {
                                sheet.addTextElement(canvas, colX[i], ypos, line.getQuantityField(row),
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
                                lines = sheet.addMultilineTextElement(canvas, colX[i], ypos, width, lineHeight,
                                        line.getNameField(row), fontSize, SVGConstants.SVG_START_VALUE,
                                        SVGConstants.SVG_NORMAL_VALUE);
                            } else {
                                lines = sheet.addMultilineTextElement(canvas, line.indentMultiline() ?
                                        colX[i] + indent : colX[i], ypos, width, lineHeight,
                                        line.getNameField(row), fontSize, SVGConstants.SVG_START_VALUE,
                                        SVGConstants.SVG_NORMAL_VALUE);
                            }
                            break;
                        case LOCATION:
                        case LOCATION_NO_HEAT:
                            sheet.addTextElement(canvas, colX[i], ypos, line.getLocationField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case HEAT:
                            sheet.addTextElement(canvas, colX[i], ypos, line.getHeatField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case DAMAGE:
                            lines = Math.max(lines, sheet.addMultilineTextElement(canvas, colX[i], ypos,
                                    colX[i + 1] - colX[i] - fontSize, lineHeight, line.getDamageField(row),
                                    fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE));
                            break;
                        case MIN:
                            sheet.addTextElement(canvas, colX[i], ypos, line.getMinField(row), fontSize,
                                    SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case SHORT:
                        case SRV:
                            sheet.addTextElementToFit(canvas, colX[i], ypos, colX[i + 1] - colX[i] - 1,
                                    line.getShortField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case MEDIUM:
                        case MRV:
                            sheet.addTextElementToFit(canvas, colX[i], ypos, colX[i + 1] - colX[i] - 1,
                                    line.getMediumField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case LONG:
                        case LRV:
                            sheet.addTextElementToFit(canvas, colX[i], ypos, colX[i] - colX[i - 1] - 1,
                                    line.getLongField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                        case ERV:
                            sheet.addTextElementToFit(canvas, colX[i], ypos, colX[i] - colX[i - 1] - 1,
                                    line.getExtremeField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE,
                                    SVGConstants.SVG_NORMAL_VALUE);
                            break;
                    }
                }
                ypos += lineHeight * lines;
            }
        }
        return ypos;
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
                    sheet.addTextElement(canvas, bayColX[i], currY, columnTypes[i].header, FONT_SIZE_MEDIUM,
                            SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
                    break;
            }
        }
        currY += sheet.getFontHeight(FONT_SIZE_MEDIUM) * 1.2;
        currY = printEquipmentTable(Collections.singletonList(new AR10InventoryEntry()), currY,
                fontSize, lineHeight, Column.BAY_COLUMNS, bayColX);
        return currY;
   }

    /**
     * Prints the grav deck block
     * @param ship The jumpship, warship, or space station
     * @param fontSize The font size to use for the table rows
     * @param lineHeight The height of each table row
     * @param currY The y coordinate of the top of the table
     * @return The y coordinate of the bottom of the table
     */
    public double printGravDecks(Jumpship ship, float fontSize, double lineHeight, double currY) {
        if (ship.getTotalGravDeck() > 0) {
            double xpos = bayColX[0];
            sheet.addTextElement(canvas, xpos, currY, "Grav Decks:",
                    FONT_SIZE_MEDIUM, SVGConstants.SVG_START_VALUE,
                    SVGConstants.SVG_BOLD_VALUE);
            currY += lineHeight;
            double ypos = currY;
            int count = 1;
            for (int size : ship.getGravDecks()) {
                String gravString = "Grav Deck #" + count + ": " + size + "-meters";
                sheet.addTextElement(canvas, xpos, ypos, gravString, fontSize, "start", "normal");
                ypos += lineHeight;
                if (count == (ship.getGravDecks().size() / 2)) {
                    ypos = currY;
                    xpos += viewWidth / 2.0;
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
                        bayTypeString.append("/");
                        bayCapacityString.append("/");
                    }
                    doors = Math.max(doors, b.getDoors());
                }
                bayCapacityString.append(")");
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
