/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing.reference;

import static megameklab.printing.PrintRecordSheet.svgNS;

import java.util.StringJoiner;

import megameklab.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

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
              width - PADDING * 3, height * 0.35, 1, 10, g);
        addTrack(PADDING * 1.5, height * 0.5,
              width - PADDING * 3, height * 0.35, 11, 20, g);
        return g;
    }

    private void addTrack(double x, double y, double width, double height,
          int fromTurn, int toTurn, Element parent) {
        Element outline = createRoundedBorder(x, y, width, height);
        parent.appendChild(outline);
        double colOffset = x + width * 0.2;
        double colWidth = (width - colOffset) / (toTurn - fromTurn + 1);
        StringJoiner path = new StringJoiner(" ");
        int rows = 4;
        if (showHeat) rows++;
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
        double rowHeight = height / rows;
        double fontH = sheet.getFontHeight(fontSize);
        double baselineOffset = fontH * 0.30;
        double startY = y + rowHeight * 0.5 + baselineOffset;
        parent.appendChild(createTextElement(x + PADDING, startY, bundle.getString("turnNum"),
              fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, rowHeight + startY,
              bundle.getString(showFacing ? "hexFacing" : "hex"),
              fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, (rowHeight * 2.0) + startY, bundle.getString("moveMode"),
              fontSize, SVGConstants.SVG_BOLD_VALUE));
        parent.appendChild(createTextElement(x + PADDING, (rowHeight * 3.0) + startY, bundle.getString(
              "hexesMoved"),
              fontSize, SVGConstants.SVG_BOLD_VALUE));
        if (showHeat) {
            parent.appendChild(createTextElement(x + PADDING, (rowHeight * 4.0) + startY, bundle.getString("heat"),
                  fontSize, SVGConstants.SVG_BOLD_VALUE));
        }
        for (int i = 0; i <= toTurn - fromTurn; i++) {
            parent.appendChild(createTextElement(startX + i * colWidth, startY, String.valueOf(fromTurn + i), fontSize,
                  SVGConstants.SVG_BOLD_VALUE, PrintRecordSheet.FILL_BLACK, SVGConstants.SVG_MIDDLE_VALUE,
                  false, null));
        }
    }
}
