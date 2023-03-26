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
package megameklab.printing.reference;

import megameklab.printing.PrintRecordSheet;
import megameklab.util.CConfig;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

import java.awt.geom.Rectangle2D;
import java.util.*;

import static megameklab.printing.PrintRecordSheet.svgNS;

/**
 * Base class for quick reference tables for record sheets
 */
abstract public class ReferenceTableBase {
    private static final double bevelX = 5.475;
    private static final double bevelY = 8.214;
    private static final float FONT_SIZE_LABEL = PrintRecordSheet.FONT_SIZE_MEDIUM;
    private static final double STROKE_WIDTH = 1.6;
    static final double PADDING = 3.0;

    protected final ResourceBundle bundle = ResourceBundle.getBundle(getClass().getName());
    final PrintRecordSheet sheet;

    public static double getMargins(PrintRecordSheet sheet) {
        return 15 + sheet.getFontHeight(FONT_SIZE_LABEL) * 2;
    }

    public ReferenceTableBase(PrintRecordSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * @return The text for the title bar
     */
    protected final String getTitle() {
        return bundle.getString("title");
    }

    public Element createTable(double x, double y, double width, double height) {
        final Element g = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(%f %f)", SVGConstants.SVG_TRANSLATE_VALUE, x, y));
        double labelWidth = width - bevelX * 2 - 6.0f;
        final Element label = createLabel(2.5f, 3.0f, getTitle(), labelWidth);
        final Element shadow = createCellBorder(2.0, 2.0, width - 4.0, height - 4.0,
                PrintRecordSheet.FILL_SHADOW, PrintRecordSheet.FILL_SHADOW);
        final Element border = createCellBorder(0.0, 0.0, width - 5.0, height - 5.0,
                PrintRecordSheet.FILL_BLACK, PrintRecordSheet.FILL_WHITE);
        g.appendChild(shadow);
        g.appendChild(border);
        g.appendChild(label);
        g.appendChild(createTableBody(3.0, PADDING * 1.5 + sheet.getFontHeight(FONT_SIZE_LABEL) * 2,
                width - 8.0, height - 15.0, PrintRecordSheet.FONT_SIZE_VSMALL));
        return g;
    }

    public Element createTable(Rectangle2D bbox) {
        return createTable(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
    }

    private Element createLabel(double x, double y, String title, double labelWidth) {
        final double textHeight = sheet.getFontHeight(FONT_SIZE_LABEL) * 0.625f;
        final double textWidth = sheet.getBoldTextLength(title, FONT_SIZE_LABEL);
        final double rectMargin = textWidth * 0.05f;
        final double taperWidth = textHeight * bevelX / bevelY;
        final Element g = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(%f %f)", SVGConstants.SVG_TRANSLATE_VALUE, x, y));
        final Element background = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        background.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, PrintRecordSheet.FILL_BLACK);
        background.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE,
                String.format("M %f,%f l %f,%f h %f l %f,%f l %f,%f h %f Z",
                        0.0, textHeight, taperWidth, -textHeight, labelWidth,
                        taperWidth, textHeight, -taperWidth, textHeight, -labelWidth));
        g.appendChild(background);

        final Element text = createTextElement(taperWidth + labelWidth * 0.5,
                textHeight * 1.5, title, FONT_SIZE_LABEL, SVGConstants.SVG_BOLD_VALUE,
                PrintRecordSheet.FILL_WHITE, SVGConstants.SVG_MIDDLE_VALUE, false,
                labelWidth - rectMargin * 2);
        g.appendChild(text);

        return g;
    }

    protected Element createTextElement(double x, double y, String text, float fontSize, String fontWeight) {
        return createTextElement(x, y, text, fontSize, fontWeight, PrintRecordSheet.FILL_BLACK,
                SVGConstants.SVG_START_VALUE, false, null);
    }

    protected Element createTextElement(double x, double y, String text, float fontSize,
                                      String fontWeight, String fill, String anchor,
                                      boolean fixedWidth, Double width) {
        final Element t = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        t.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        t.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        t.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, formatStyle(fontSize, fontWeight));
        t.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        t.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        double textLength = fontWeight.equals(SVGConstants.SVG_BOLD_VALUE) ?
                sheet.getBoldTextLength(text, fontSize) : sheet.getTextLength(text, fontSize);
        if (fixedWidth || ((width != null) && (textLength > width))) {
            if (width != null && (textLength > width)) {
                textLength = width;
            }
            t.setAttributeNS(null, SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE, SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
            t.setAttributeNS(null, SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE,
                    String.valueOf(textLength));
        }
        t.setTextContent(text);

        return t;
    }

    protected String formatStyle(float fontSize, String fontWeight) {
        final StringJoiner sj = new StringJoiner(";");
        sj.add(SVGConstants.CSS_FONT_FAMILY_PROPERTY + ":"
                + CConfig.getParam(CConfig.RS_FONT, PrintRecordSheet.DEFAULT_TYPEFACE));
        sj.add(SVGConstants.CSS_FONT_SIZE_PROPERTY + ":" + fontSize + "px");
        sj.add(SVGConstants.CSS_FONT_WEIGHT_PROPERTY + ":" + fontWeight);
        return sj.toString();
    }

    private Element createCellBorder(double x, double y, double width, double height, String stroke, String fill) {
        final Element path = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.CSS_FILL_PROPERTY, fill);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_PROPERTY, stroke);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(STROKE_WIDTH));
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_LINEJOIN_PROPERTY, SVGConstants.SVG_ROUND_VALUE);
        path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE,
                String.format("M %f,%f l %f,%f h %f l %f,%f v %f l %f,%f h %f l %f,%f Z",
                        x, y + bevelY, bevelX, -bevelY, width - bevelX * 2,
                        bevelX, bevelY, height - bevelY * 2, -bevelX, bevelY,
                        -(width - bevelX * 2), -bevelX, -bevelY));

        return path;
    }

    protected Element createRoundedBorder(double x, double y, double width, double height) {
        // Constants used for bezier curves on corners
        final double RADIUS = 1.315;
        final double CONTROL = 0.726;
        final String BEZIER = "c %f,%f %f,%f %f,%f";
        StringJoiner d = new StringJoiner(" ");
        d.add(String.format("M %f,%f", x, y + RADIUS));
        d.add(String.format(BEZIER, 0.0, -CONTROL,
                RADIUS - CONTROL, -RADIUS,
                RADIUS,-RADIUS));
        d.add(String.format("h %f", width - RADIUS * 2));
        d.add(String.format(BEZIER, CONTROL, 0.0,
                RADIUS, RADIUS - CONTROL,
                RADIUS, RADIUS));
        d.add(String.format("v %f", height - RADIUS * 2));
        d.add(String.format(BEZIER, 0.0, CONTROL,
                CONTROL - RADIUS, RADIUS,
                -RADIUS, RADIUS));
        d.add(String.format("h %f", -width + RADIUS * 2));
        d.add(String.format(BEZIER, -CONTROL, 0.0,
                -RADIUS, CONTROL - RADIUS,
                -RADIUS, -RADIUS));
        d.add("Z");
        Element path = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.CSS_FILL_PROPERTY, PrintRecordSheet.FILL_WHITE);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_PROPERTY, PrintRecordSheet.FILL_BLACK);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_WIDTH_PROPERTY, "1.0");
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_LINEJOIN_PROPERTY, SVGConstants.SVG_ROUND_VALUE);
        path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, d.toString());

        return path;
    }

    abstract protected Element createTableBody(double x, double y, double width, double height, float fontSize);
}
