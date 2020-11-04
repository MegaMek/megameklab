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

import java.util.*;

import static megameklab.com.printing.PrintRecordSheet.svgNS;

/**
 * Base class for reference tables that format contents into a grid, with optional footnotes
 */
public abstract class ReferenceTable extends ReferenceTableBase {

    private String defaultAnchor = SVGConstants.SVG_MIDDLE_VALUE;

    private final List<String> headers;
    private final List<List<String>> data;
    private final List<Double> colOffsets;
    private final List<String> notes;
    private final Map<Integer, String> anchor;
    private final Map<Integer, String> fontWeight;

    public ReferenceTable(PrintRecordSheet sheet) {
        super(sheet);
        this.colOffsets = new ArrayList<>();
        this.headers = new ArrayList<>();
        this.data = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.anchor = new HashMap<>();
        this.fontWeight = new HashMap<>();
    }

    public ReferenceTable(PrintRecordSheet sheet, double... colOffsets) {
        this(sheet);
        for (double offset : colOffsets) {
            this.colOffsets.add(offset);
        }
    }

    protected void setColOffsets(Collection<Double> offsets) {
        colOffsets.clear();
        colOffsets.addAll(offsets);
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

    @Override
    protected Element createTableBody(double x, double y, double width, double height, float fontSize) {
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
                            line, fontSize, SVGConstants.SVG_BOLD_VALUE,
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
                if (c == colOffsets.size()) {
                    break;
                }
                String[] lines = row.get(c).split("\\n");
                for (int l = 0; l < lines.length; l++) {
                    g.appendChild(createTextElement(width * colOffsets.get(c), ypos + rowSpacing * l,
                            lines[l], fontSize, fontWeight.getOrDefault(c, SVGConstants.SVG_NORMAL_VALUE),
                            PrintRecordSheet.FILL_BLACK, anchor.getOrDefault(c, defaultAnchor), false, null));
                }
            }
            ypos += rowSpacing * lineCount(row);
        }
        for (String note : notes) {
            String[] lines = note.split("\\n");
            for (String line : lines) {
                g.appendChild(createTextElement(PADDING, ypos, line, fontSize,
                        SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                        PrintRecordSheet.FILL_BLACK, false, width - PADDING));
                ypos += lineHeight;
            }
            ypos += rowSpacing - lineHeight;
        }
        return g;
    }
}
