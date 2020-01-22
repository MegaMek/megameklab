/*
 * MekBuilder - unit design companion of MegaMek
 * Copyright (C) 2017 The MegaMek Team
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
import megameklab.com.util.ImageHelper;
import megameklab.com.util.RecordSheetEquipmentLine;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.print.PageFormat;
import java.io.File;
import java.util.Map;

/**
 * Lays out record sheet for non-capital aerospace units
 */
public class PrintAero extends PrintEntity {

    /**
     * The aerospace unit being printed
     */
    private final Aero aero;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param aero      The aerospace unit to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintAero(Aero aero, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.aero = aero;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param aero      The aerospace unit to print
     * @param startPage The print job page number for this sheet
     */
    public PrintAero(Aero aero, int startPage) {
        this(aero, startPage, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "fighter_aerospace_default.svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        StringBuilder sb = new StringBuilder();
        if (aero instanceof SmallCraft) {
            if (aero.isSpheroid()) {
                sb.append("Spheroid");
            } else {
                sb.append("Aerodyne");
            }
            if (aero instanceof Dropship) {
                sb.append(" Dropship");
            } else {
                sb.append(" Small Craft");
            }
        } else if (aero.isSupportVehicle()) {
            if (aero.getMovementMode().equals(EntityMovementMode.STATION_KEEPING)) {
                sb.append("Satellite Support Vehicle");
            } else if (aero.getMovementMode().equals(EntityMovementMode.AIRSHIP)) {
                sb.append("Airship Support Vehicle");
            } else {
                sb.append("Fixed Wing Support Vehicle");

            }
        } else if (aero instanceof ConvFighter) {
            sb.append("Conventional Fighter");
        } else if (aero.isOmni()) {
            sb.append("OmniFighter");
        } else {
            sb.append("Aerospace Fighter");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }

    @Override
    protected Entity getEntity() {
        return aero;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return loc == Aero.LOC_NOSE || loc == Aero.LOC_AFT;
    }

    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        super.processImage(pageNum, pageFormat);
        if (aero.tracksHeat()) {
            Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
            if (hsRect instanceof SVGRectElement) {
                drawHeatSinkPips((SVGRectElement) hsRect, aero.getHeatSinks());
            }
        }
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(HS_TYPE, formatHeatSinkType());
        setTextField(HS_COUNT, formatHeatSinkCount());
    }

    @Override
    protected void drawArmorStructurePips() {
        super.drawArmorStructurePips();
        Element element = getSVGDocument().getElementById(SI_PIPS);
        if (null != element) {
            ArmorPipLayout.addPips(this, element, aero.get0SI(),
                    PipType.CIRCLE, 0.5);
        }
    }

    @Override
    void writeArmorStructureTextFields() {
        final String FORMAT = "%d ( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, aero.getThresh(loc), getEntity().getOArmor(loc)));
        }
        setTextField(TEXT_SI, aero.get0SI());
    }

    void writeEquipmentTable(Map<Integer, Map<RecordSheetEquipmentLine,Integer>> eqMap,
                             double x, double y, double width,
                             float fontSize, float lineHeight, Element canvas) {
        double qtyX = x + width * 0.037;
        double nameX = x + width * 0.075;
        double locX = x + width * 0.56;
        double heatX = x + width * 0.64;
        double srvX = x + width * 0.72;
        double mrvX = x + width * 0.80;
        double lrvX = x + width * 0.88;
        double ervX = x + width * 0.96;

        double indent = width * 0.02;

        double currY = y + 10.0;

        addTextElement(canvas, qtyX, currY, "Qty", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, nameX + indent, currY, "Type", fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, locX,  currY, "Loc", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        if (getEntity().tracksHeat() || getEntity().isLargeCraft()) {
            addTextElement(canvas, heatX, currY, "Ht", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        }
        addTextElement(canvas, srvX, currY, "SRV", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, mrvX, currY, "MRV", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, lrvX, currY, "LRV", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        addTextElement(canvas, ervX, currY, "ERV", fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_BOLD_VALUE);
        currY += lineHeight * 1.2;

        /* Print each entry in the equipment map. An entry that does not fit into the allocated space
         * will wrap to the next line. This is tracked using the repurposed lines local variable. Some
         * entries are already given multiple lines (such as missile launchers with Artemis), which
         * will be handled in the inner loop. We need to compare the two to make sure we don't add
         * extra linefeeds. This algorithm works on the assumption that presplitting values into multiple
         * rows ensures that they will fit and not need to wrap. */
        int lines = 0;
        for (Integer loc : eqMap.keySet()) {
            for (RecordSheetEquipmentLine line : eqMap.get(loc).keySet()) {
                for (int row = 0; row < line.nRows(); row++) {
                    if (row == 0) {
                        addTextElement(canvas, qtyX, currY, Integer.toString(eqMap.get(loc).get(line)), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                        lines = addMultilineTextElement(canvas, nameX, currY, locX - nameX - indent, lineHeight,
                                line.getNameField(row, getEntity().isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);

                    } else {
                        lines = addMultilineTextElement(canvas, nameX + indent, currY, locX - nameX - indent, lineHeight,
                                line.getNameField(row, getEntity().isMixedTech()), fontSize, SVGConstants.SVG_START_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    }
                    addTextElement(canvas, locX,  currY, line.getLocationField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    if (getEntity().tracksHeat() || getEntity().isLargeCraft()) {
                        addTextElement(canvas, heatX, currY, line.getHeatField(row), fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    }
                    addTextElement(canvas, srvX, currY, line.getSRV(row),
                            fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, mrvX, currY, line.getMRV(row),
                            fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, lrvX, currY, line.getLRV(row),
                            fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    addTextElement(canvas, ervX, currY, line.getERV(row),
                            fontSize, SVGConstants.SVG_MIDDLE_VALUE, SVGConstants.SVG_NORMAL_VALUE);
                    currY += lineHeight;
                }
                if (lines > line.nRows()) {
                    currY += lineHeight * (lines - line.nRows());
                }
            }
        }
    }

    @Override
    protected void drawFluffImage() {
        File f = ImageHelper.getFluffFile(aero, ImageHelper.imageAero);
        if (null != f) {
            Element rect = getSVGDocument().getElementById(FLUFF_IMAGE);
            if (rect instanceof SVGRectElement) {
                embedImage(f, (Element) rect.getParentNode(), getRectBBox((SVGRectElement) rect), true);
            }
            hideElement(getSVGDocument().getElementById(NOTES));
        }
    }

    private String formatHeatSinkType() {
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return "Double Heat Sinks:";
        } else {
            return "Heat Sinks:";
        }
    }

    private String formatHeatSinkCount() {
        int hsCount = aero.getHeatSinks();
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return String.format("%d (%d)", hsCount, hsCount * 2);
        } else {
            return Integer.toString(hsCount);
        }
    }


}