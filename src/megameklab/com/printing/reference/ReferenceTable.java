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
import megameklab.com.util.CConfig;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

import java.util.*;

import static megameklab.com.printing.PrintRecordSheet.svgNS;

/**
 * Handles generation of quick reference tables for record sheets
 */
public class ReferenceTable {

    private static final double bevelX = 5.475;
    private static final double bevelY = 8.214;
    private static final float FONT_SIZE_LABEL = PrintRecordSheet.FONT_SIZE_MEDIUM;
    private static final double STROKE_WIDTH = 1.6;
    private static final double PADDING = 3.0;

    private String defaultAnchor = SVGConstants.SVG_MIDDLE_VALUE;

    private final PrintRecordSheet sheet;
    private final String title;
    private final List<String> headers;
    private final List<List<String>> data;
    private final List<Double> colOffsets;
    private final List<String> notes;
    private final Map<Integer, String> anchor;
    private final Map<Integer, String> fontWeight;

    public static double getMargins(PrintRecordSheet sheet) {
        return 15 + sheet.getFontHeight(FONT_SIZE_LABEL) * 2;
    }

    public ReferenceTable(PrintRecordSheet sheet, String title, double... colOffsets) {
        this.title = title;
        this.sheet = sheet;
        this.colOffsets = new ArrayList<>();
        this.headers = new ArrayList<>();
        this.data = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.anchor = new HashMap<>();
        this.fontWeight = new HashMap<>();
        for (double offset : colOffsets) {
            this.colOffsets.add(offset);
        }
    }

    public void setHeaders(String... items) {
        setHeaders(Arrays.asList(items));
    }

    public void setHeaders(List<String> headers) {
        if (headers.size() != colOffsets.size()) {
            throw new IllegalArgumentException("Column headers must match size of column offsets list");
        }
        this.headers.clear();
        this.headers.addAll(headers);
    }

    public void addRow(String... items) {
        addRow(Arrays.asList(items));
    }

    public void addRow(List<String> row) {
        if (row.size() != colOffsets.size()) {
            throw new IllegalArgumentException("Row size must match size of column offsets list");
        }
        data.add(new ArrayList<>(row));
    }

    public void setDefaultAnchor(String defaultAnchor) {
        this.defaultAnchor = defaultAnchor;
    }

    public void setColumnAnchor(int column, String anchor) {
        this.anchor.put(column, anchor);
    }

    public void setColumnWeight(int column, String weight) {
        fontWeight.put(column, weight);
    }

    public void addNote(String note) {
        notes.add(note);
    }

    public int lineCount() {
        int count = lineCount(headers);
        for (List<String> row : data) {
            count += lineCount(row);
        }
        for (String note : notes) {
            count += note.split("\\n").length;
        }
        return count;
    }

    private int lineCount(List<String> row) {
        int count = 0;
        for (String cell : row) {
            count = Math.max(count, cell.split("\\n").length);
        }
        return count;
    }

    public Element createTable(double x, double y, double width, double height) {
        final Element g = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(%f %f)", SVGConstants.SVG_TRANSLATE_VALUE, x, y));
        double labelWidth = width - bevelX * 2 - 6.0f;
        final Element label = createLabel(2.5f, 3.0f, title, labelWidth);
        final Element shadow = createCellBorder(2.0, 2.0, width - 6.0, height - 6.0,
                PrintRecordSheet.FILL_SHADOW);
        final Element border = createCellBorder(0.0, 0.0, width - 5.0, height - 5.0,
                PrintRecordSheet.FILL_BLACK);
        g.appendChild(shadow);
        g.appendChild(border);
        g.appendChild(label);
        final Element table = createTableBody(3.0, PADDING * 1.5 + sheet.getFontHeight(FONT_SIZE_LABEL) * 2,
                width - 8.0, height - 6.0, PrintRecordSheet.FONT_SIZE_VSMALL);
        g.appendChild(table);
        return g;
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
                SVGConstants.SVG_NORMAL_VALUE, PrintRecordSheet.FILL_WHITE,
                SVGConstants.SVG_MIDDLE_VALUE, false,
                labelWidth - rectMargin * 2);
        g.appendChild(text);

        return g;
    }

    private Element createTextElement(double x, double y, String text, float fontSize,
                                      String fontWeight, String fontStyle, String fill, String anchor,
                                      boolean fixedWidth, Double width) {
        final Element t = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        t.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        t.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        t.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, formatStyle(fontSize, fontWeight, fontStyle));
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

    private String formatStyle(float fontSize) {
        return formatStyle(fontSize, SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_NORMAL_VALUE);
    }

    private String formatStyle(float fontSize, String fontWeight) {
        return formatStyle(fontSize, fontWeight, SVGConstants.SVG_NORMAL_VALUE);
    }

    private String formatStyle(float fontSize, String fontWeight, String fontStyle) {
        final StringJoiner sj = new StringJoiner(";");
        sj.add(SVGConstants.CSS_FONT_FAMILY_PROPERTY + ":"
                + CConfig.getParam(CConfig.RS_FONT, PrintRecordSheet.DEFAULT_TYPEFACE));
        sj.add(SVGConstants.CSS_FONT_SIZE_PROPERTY + ":" + fontSize + "px");
        sj.add(SVGConstants.CSS_FONT_WEIGHT_PROPERTY + ":" + fontWeight);
        sj.add(SVGConstants.CSS_FONT_STYLE_PROPERTY + ":" + fontStyle);
        return sj.toString();
    }

    private Element createCellBorder(double x, double y, double width, double height, String stroke) {
        final Element path = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.CSS_FILL_PROPERTY, PrintRecordSheet.FILL_WHITE);
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

    private Element createTableBody(double x, double y, double width, double height, float fontSize) {
        double rowSpacing = height / (lineCount() + 2);
        double lineHeight = sheet.getFontHeight(fontSize);
        while ((fontSize >= 5.0f) && (rowSpacing < lineHeight)) {
            fontSize -= 0.1;
            lineHeight = sheet.getFontHeight(fontSize);
        }
        double ypos = 0.0;
        final Element g = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(%f %f)", SVGConstants.SVG_TRANSLATE_VALUE, x, y));
        if (!headers.isEmpty()) {
            Element text = sheet.getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
            text.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, "0");
            text.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, "0");
            text.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, PrintRecordSheet.FILL_BLACK);
            text.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE,
                    formatStyle(fontSize, SVGConstants.SVG_BOLD_VALUE));
            text.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, defaultAnchor);
            int lineCount = lineCount(headers);
            for (int col = 0; col < headers.size(); col++) {
                String[] lines = headers.get(col).split("\\n");
                double useY = ypos + lineHeight * (lineCount - lines.length);
                for (String line : lines) {
                    g.appendChild(createTextElement(width * colOffsets.get(col), useY,
                            line, fontSize, SVGConstants.SVG_BOLD_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                            PrintRecordSheet.FILL_BLACK, anchor.getOrDefault(col, defaultAnchor),
                            false, null));
                    useY += lineHeight;
                }
            }
            g.appendChild(text);
            ypos += rowSpacing + lineHeight * (lineCount - 1);
        }
        for (List<String> row : data) {
            for (int c = 0; c < row.size(); c++) {
                String[] lines = row.get(c).split("\\n");
                for (int l = 0; l < lines.length; l++) {
                    g.appendChild(createTextElement(width * colOffsets.get(c), ypos + rowSpacing * l,
                            lines[l], fontSize, fontWeight.getOrDefault(c, SVGConstants.SVG_NORMAL_VALUE),
                            SVGConstants.SVG_NORMAL_VALUE, PrintRecordSheet.FILL_BLACK,
                            anchor.getOrDefault(c, defaultAnchor), false, null));
                }
            }
            ypos += rowSpacing * lineCount(row);
        }
        for (String note : notes) {
            String[] lines = note.split("\\n");
            for (String line : lines) {
                g.appendChild(createTextElement(PADDING, ypos, line, fontSize,
                        SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                        PrintRecordSheet.FILL_BLACK, SVGConstants.SVG_START_VALUE,
                        false, width - PADDING));
                ypos += lineHeight;
            }
            ypos += rowSpacing - lineHeight;
        }
        return g;
    }
}
