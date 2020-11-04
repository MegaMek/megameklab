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
package megameklab.com.printing.reference;

import megameklab.com.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

import java.util.StringJoiner;

import static megameklab.com.printing.PrintRecordSheet.svgNS;

/**
 * Generates a table for tracking movement of ground units for each turn
 */
public class GroundMovementRecord extends ReferenceTableBase {

    private final boolean showHeat;
    private final boolean showFacing;

    public GroundMovementRecord(PrintRecordSheet sheet, boolean showHeat, boolean showFacing) {
        super(sheet);
        this.showHeat = showHeat;
        this.showFacing = showFacing;
    }

    @Override
    protected Element createTableBody(double x, double y, double width, double height, float fontSize) {
        final Element g = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(%f %f)", SVGConstants.SVG_TRANSLATE_VALUE, x, y));
        addTrack(PADDING * 1.5, PADDING,
                width - PADDING * 3, height * 0.3, 1, 10, g);
        addTrack(PADDING * 1.5, height * 0.5,
                width - PADDING * 3, height * 0.3, 11, 20, g);
        return g;
    }

    private void addTrack(double x, double y, double width, double height,
                          int fromTurn, int toTurn, Element parent) {
        Element outline = createRoundedBorder(x, y, width, height);
        parent.appendChild(outline);
        double colOffset = x + width * 0.2;
        double colWidth = (width - colOffset) / (toTurn - fromTurn + 1);
        StringJoiner path = new StringJoiner(" ");
        int rows = showHeat ? 4 : 3;
        for (int i = 0; i <= rows; i++) {
            path.add(String.format("M %f,%f h %f", x, y + height / rows * i, width));
        }
        for (int i = 0; i <= toTurn - fromTurn; i++) {
            path.add(String.format("M %f,%f v %f", colOffset + i * colWidth, y, height));
        }
        Element grid = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        grid.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, path.toString());
        grid.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
        grid.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, PrintRecordSheet.FILL_BLACK);
        grid.setAttributeNS(null, SVGConstants.CSS_STROKE_WIDTH_PROPERTY, "0.58");
        grid.setAttributeNS(null, SVGConstants.CSS_STROKE_LINEJOIN_PROPERTY, SVGConstants.SVG_MITER_VALUE);
        grid.setAttributeNS(null, SVGConstants.CSS_STROKE_LINECAP_PROPERTY, SVGConstants.SVG_ROUND_VALUE);
        parent.appendChild(grid);
        final float fontSize = PrintRecordSheet.FONT_SIZE_MEDIUM;
        double startX = colOffset + colWidth * 0.5;
        double startY = y + (height - sheet.getFontHeight(fontSize)) * 0.5 - 1 - height * 0.2;
        parent.appendChild(createTextElement(x + PADDING, startY, bundle.getString("turnNum"),
                fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, height / rows + startY,
                bundle.getString(showFacing ? "hexFacing" : "hex"),
                fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, height * 2.0 / rows + startY, bundle.getString("moveMode"),
                fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, height * 3.0 * rows + startY, bundle.getString("hexesMoved"),
                fontSize, SVGConstants.SVG_BOLD_VALUE));
        if (showHeat) {
            parent.appendChild(createTextElement(x + PADDING, height * 4.0 * rows + startY, bundle.getString("heat"),
                    fontSize, SVGConstants.SVG_BOLD_VALUE));
        }
        for (int i = 0; i <= toTurn - fromTurn; i++) {
            parent.appendChild(createTextElement(startX + i * colWidth, startY, String.valueOf(fromTurn + i), fontSize,
                    SVGConstants.SVG_BOLD_VALUE, PrintRecordSheet.FILL_BLACK, SVGConstants.SVG_MIDDLE_VALUE,
                    false, null));
        }
    }
}
