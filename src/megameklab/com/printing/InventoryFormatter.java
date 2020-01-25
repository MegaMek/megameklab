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
package megameklab.com.printing;

import megamek.common.*;
import megameklab.com.util.RecordSheetEquipmentLine;
import megameklab.com.util.UnitUtil;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.util.*;
import java.util.stream.Collectors;

import static megameklab.com.printing.PrintRecordSheet.svgNS;

/**
 * Formats text for the record sheet's "Weapons and Equipment Inventory" section. To use, create an instance
 * and call {@link #write(SVGRectElement)}.
 */
public class InventoryFormatter {

    // Proportion of the region width to indent subsequent lines of the same equipment entry
    private static final double INDENT = 0.02;
    /**
     * The minimum font size to use when scaling inventory text to fit into
     * available space
     */
    private static final float MIN_FONT_SIZE = 5.0f;
    /**
     * The amount of space between lines, as a factor of the font height determined
     * by {@link java.awt.FontMetrics}
     */
    public static final float MIN_LINE_SPACING = 0.8f;

    enum Column {
        QUANTITY ("Qty", 0.037),
        NAME ("Type", 0.075),
        BAY ("Bay", 0.075),
        LOCATION ("Loc", 0.41, 0.5),
        LOCATION_NO_HEAT ("Loc", 0.46, 0.55),
        HEAT ("Ht", 0.48, 0.6),
        DAMAGE ("Dmg", 0.53),
        MIN ("Min", 0.75),
        SHORT ("Sht", 0.82),
        MEDIUM ("Med", 0.89),
        LONG ("Lng", 0.96),
        SRV ("SRV", 0.68),
        MRV ("MRV", 0.76),
        LRV ("LVR", 0.84),
        ERV ("ERV", 0.92);

        final String header;
        final double groundX;
        final double aeroX;

        Column(String header, double groundX, double aeroX) {
            this.header = header;
            this.groundX = groundX;
            this.aeroX = aeroX;
        }

        Column(String header, double x) {
            this(header, x, x);
        }

        static Column[] colsFor(Entity en) {
            if (en.isAero()) {
                if (en.tracksHeat() || en.isLargeCraft()) {
                    return new Column[] {QUANTITY, NAME, LOCATION, HEAT, SRV, MRV, LRV, ERV};
                } else {
                    return new Column[] {QUANTITY, NAME, LOCATION_NO_HEAT, SRV, MRV, LRV, ERV};
                }
            } else {
                if (en.tracksHeat()) {
                    return new Column[] {QUANTITY, NAME, LOCATION, HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG};
                } else {
                    return new Column[]{QUANTITY, NAME, LOCATION_NO_HEAT, DAMAGE, MIN, SHORT, MEDIUM, LONG};
                }
            }
        }
    }

    private final Map<Integer, Map<RecordSheetEquipmentLine, Integer>> eqMap = new TreeMap<>();
    private final Map<String, Integer> ammo = new TreeMap<>();
    private final PrintEntity sheet;
    private final Column[] columnTypes;
    private final double[] colX;
    private final String featuresText;
    private final String quirksText;

    public InventoryFormatter(PrintEntity sheet) {
        this.sheet = sheet;
        columnTypes = Column.colsFor(sheet.getEntity());
        colX = new double[columnTypes.length];
        featuresText = sheet.formatFeatures();
        quirksText = sheet.formatQuirks();
        parseEquipment();
    }

    private void parseEquipment() {
        for (Mounted m : sheet.getEntity().getEquipment()) {
            if ((m.getType() instanceof AmmoType)
                    && (((AmmoType) m.getType()).getAmmoType() != AmmoType.T_COOLANT_POD)) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    String shortName = m.getType().getShortName().replace("Ammo", "");
                    shortName = shortName.replace("(Clan)", "");
                    String munition = ((AmmoType) m.getType()).getSubMunitionName().replace("(Clan) ", "");
                    shortName = shortName.replace(munition, "");
                    ammo.merge(shortName.trim(), m.getBaseShotsLeft(), Integer::sum);
                }
                continue;
            }
            if ((m.getType() instanceof AmmoType)
                    || (m.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(m.getType(), sheet.getEntity() instanceof Mech)) {
                continue;
            }
            if ((sheet.getEntity() instanceof QuadVee)
                    && (m.getType() instanceof MiscType)
                    && m.getType().hasFlag(MiscType.F_TRACKS)) {
                continue;
            }
            eqMap.putIfAbsent(m.getLocation(), new HashMap<>());
            RecordSheetEquipmentLine line = new RecordSheetEquipmentLine(m);
            eqMap.get(m.getLocation()).merge(line, 1, Integer::sum);
        }
    }

    /**
     * Uses the provided region to compute text size and position and inserts the text elements into the
     * parent element of <code>svgRect</code>.
     *
     * @param svgRect Defines the bounds of the region for the text
     */
    public void write(SVGRectElement svgRect) {
        final Element canvas = (Element) svgRect.getParentNode();
        double viewWidth = svgRect.getWidth().getBaseVal().getValue();
        double viewHeight = svgRect.getHeight().getBaseVal().getValue();
        double viewX = svgRect.getX().getBaseVal().getValue();
        double viewY = svgRect.getY().getBaseVal().getValue();
        double indent = viewWidth * INDENT;

        float fontSize = PrintRecordSheet.FONT_SIZE_MEDIUM;
        float lineHeight = sheet.getFontHeight(fontSize) * 1.2f;
        double currY = viewY + 10;

        for (int i = 0; i < columnTypes.length; i++) {
            colX[i] = viewX + viewWidth * (sheet.getEntity().isAero() ? columnTypes[i].aeroX : columnTypes[i].groundX);
            String anchor;
            if (Column.NAME.equals(columnTypes[i])
                    || Column.BAY.equals(columnTypes[i])
                    || Column.DAMAGE.equals(columnTypes[i])) {
                anchor = SVGConstants.SVG_START_VALUE;
            } else {
                anchor = SVGConstants.SVG_MIDDLE_VALUE;
            }
            sheet.addTextElement(canvas, colX[i], currY, columnTypes[i].header, fontSize,
                    anchor, SVGConstants.SVG_BOLD_VALUE);
        }
        currY += lineHeight * 1.2;

        int lines = calcLineCount(fontSize, viewWidth);
        /*
         * If the lines do not fit in the available space, reduce the font size.
         * We take it in steps of -0.5 instead of scaling proportionately because not
         * only is the relationship between font size and height not directly proportional,
         * but a smaller reduction may be sufficient to reduce the number of line required
         * for longer fields.
         */
        float lineSpacing = 1.2f;
        double remainingHeight = viewHeight - (currY - viewY);
        while ((fontSize > MIN_FONT_SIZE) && (lineSpacing > MIN_LINE_SPACING)
                && ((lineHeight * lines) >= remainingHeight)) {
            if (fontSize > MIN_FONT_SIZE) {
                fontSize = Math.max(MIN_FONT_SIZE, fontSize - 0.5f);
                // A smaller font may allow fewer lines
                lines = calcLineCount(fontSize, viewWidth);
            } else {
                lineSpacing -= 0.1f;
            }
            lineHeight = sheet.getFontHeight(fontSize) * lineSpacing;
        }

        writeEquipmentTable(canvas, fontSize, currY, lineHeight, indent);

        if (ammo.size() + featuresText.length() + quirksText.length() > 0) {
            Element svgGroup = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            canvas.appendChild(svgGroup);
            lines = 0;
            if (ammo.size() > 0) {
                lines = sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, 0, viewWidth * 0.95, lineHeight,
                        "Ammo: " + ammo.entrySet().stream()
                                .map(e -> String.format("(%s) %d", e.getKey(), e.getValue()))
                                .collect(Collectors.joining(", ")), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (featuresText.length() > 0) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025,
                        lines * lineHeight, viewWidth * 0.95, lineHeight,
                        "Features " + featuresText, fontSize,
                        SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            if (quirksText.length() > 0) {
                lines += sheet.addMultilineTextElement(svgGroup, viewX + viewWidth * 0.025, lines * lineHeight,
                        viewWidth * 0.95, lineHeight,
                        "Quirks: " + quirksText, fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
            }
            svgGroup.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                    String.format("%s(0,%f)", SVGConstants.SVG_TRANSLATE_VALUE,
                            viewY + viewHeight - lines * lineHeight));
        }
    }

    private int calcLineCount(float fontSize, double viewWidth) {
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
        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                int rows = line.nRows();
                // If the name or damage field is too long to fit in the space, make sure there is a second row
                if ((rows == 1) &&
                        ((sheet.getTextLength(line.getNameField(0, sheet.getEntity().isMixedTech()),
                                fontSize) > nameWidth) ||
                            sheet.getTextLength(line.getDamageField(0), fontSize) > dmgWidth - fontSize)) {
                    rows++;
                }
                lines += rows;
            }
        }
        String features = sheet.formatFeatures();
        String quirksText = sheet.formatQuirks();
        lines += sheet.getTextLength(features, fontSize) / viewWidth;
        lines += sheet.getTextLength(quirksText, fontSize) / viewWidth;
        return lines;
    }

    /**
     * Print each entry in the equipment map. An entry that does not fit into the allocated space
     *  will wrap to the next line. This is tracked using the repurposed lines local variable. Some
     *  entries are already given multiple lines (such as missile launchers with Artemis), which
     *  will be handled in the inner loop. We need to compare the two to make sure we don't add
     *  extra linefeeds. This algorithm works on the assumption that presplitting values into multiple
     *  rows ensures that they will fit and not need to wrap.
     *
     * @param canvas The parent element of the added text elements
     * @param fontSize The size of font to use for printing the table
     * @param ypos The starting y coordinate relative to the parent element
     * @param lineHeight The amount to add to the y coordinate for each line
     * @param indent The amount to indent the name field in successive lines
     */
    private void writeEquipmentTable(Element canvas, float fontSize, double ypos, double lineHeight,
                                       double indent) {
        int lines = 0;
        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                for (int row = 0; row < line.nRows(); row++) {
                    for (int i = 0; i < columnTypes.length; i++) {
                        switch (columnTypes[i]) {
                            case QUANTITY:
                                if (row == 0) {
                                    sheet.addTextElement(canvas, colX[i], ypos, String.valueOf(eqMap.get(loc).get(line)),
                                            fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                }
                                break;
                            case NAME:
                            case BAY:
                                if (row == 0) {
                                    lines = sheet.addMultilineTextElement(canvas, colX[i], ypos,
                                            colX[i + 1] - colX[i] - indent, lineHeight,
                                            line.getNameField(row, sheet.getEntity().isMixedTech()),
                                            fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                } else {
                                    lines = sheet.addMultilineTextElement(canvas, colX[i] + indent, ypos,
                                            colX[i + 1] - colX[i] - indent, lineHeight,
                                            line.getNameField(row, sheet.getEntity().isMixedTech()),
                                            fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                }
                                break;
                            case LOCATION:
                            case LOCATION_NO_HEAT:
                                sheet.addTextElement(canvas, colX[i],  ypos, line.getLocationField(row), fontSize,
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
                                sheet.addTextElement(canvas, colX[i], ypos, line.getShortField(row), fontSize,
                                        SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                break;
                            case MEDIUM:
                            case MRV:
                                sheet.addTextElement(canvas, colX[i], ypos, line.getMediumField(row), fontSize,
                                        SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                break;
                            case LONG:
                            case LRV:
                                sheet.addTextElement(canvas, colX[i], ypos, line.getLongField(row), fontSize,
                                        SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                break;
                            case ERV:
                                sheet.addTextElement(canvas, colX[i], ypos, line.getExtremeField(row), fontSize,
                                        SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                                break;
                        }
                    }
                    ypos += lineHeight;
                }
                if (lines > line.nRows()) {
                    ypos += lineHeight * (lines - line.nRows());
                }
            }
        }
    }
}
