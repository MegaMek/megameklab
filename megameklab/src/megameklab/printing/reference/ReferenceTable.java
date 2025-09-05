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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megameklab.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;

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
            fontSize -= 0.1F;
            lineHeight = sheet.getFontHeight(fontSize);
        }
        double yPosition = 0.0;
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
                double useY = yPosition + lineHeight * (lineCount - lines.length);
                for (String line : lines) {
                    g.appendChild(createTextElement(width * colOffsets.get(col), useY,
                          line, fontSize, SVGConstants.SVG_BOLD_VALUE,
                          PrintRecordSheet.FILL_BLACK, anchor.getOrDefault(col, defaultAnchor),
                          false, null));
                    useY += lineHeight;
                }
            }
            g.appendChild(text);
            yPosition += rowSpacing + lineHeight * (lineCount - 1);
        }

        int rowParity = 0;

        for (int i = 0, dataSize = data.size(); i < dataSize; i++) {
            List<String> row = data.get(i);

            boolean sectionHeader = false;
            boolean noShade = false;
            if (!row.isEmpty() && row.get(0).startsWith(SECTION_HEADER)) {
                sectionHeader = true;
                row.set(0, row.get(0).replace(SECTION_HEADER, ""));
            }
            if (!row.isEmpty() && row.get(0).startsWith(NO_SHADE)) {
                noShade = true;
                row.set(0, row.get(0).replace(NO_SHADE, ""));
            }

            if (sectionHeader || noShade) {
                rowParity = (i + 1) % 2;
            } else {
                if (i % 2 == rowParity) {
                    g.appendChild(createShadeElement(1,
                          yPosition - fontSize / 3 - rowSpacing / 2,
                          width - 5,
                          rowSpacing * lineCount(row)));
                }
            }

            for (int c = 0; c < row.size(); c++) {
                if (c == colOffsets.size()) {
                    break;
                }
                String[] lines = row.get(c).split("\\n");
                for (int l = 0; l < lines.length; l++) {
                    g.appendChild(createTextElement(width * colOffsets.get(c),
                          yPosition + rowSpacing * l,
                          lines[l],
                          fontSize,
                          fontWeight.getOrDefault(c,
                                sectionHeader ? SVGConstants.SVG_BOLD_VALUE : SVGConstants.SVG_NORMAL_VALUE),
                          PrintRecordSheet.FILL_BLACK,
                          anchor.getOrDefault(c, defaultAnchor),
                          false,
                          null));
                }
            }
            yPosition += rowSpacing * lineCount(row);
        }
        yPosition += (rowSpacing / 2);
        for (String note : notes) {
            String[] lines = note.split("\\n");
            for (String line : lines) {
                g.appendChild(createTextElement(PADDING, yPosition, line, fontSize,
                      SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_NORMAL_VALUE,
                      PrintRecordSheet.FILL_BLACK, false, width - PADDING));
                yPosition += lineHeight;
            }
            yPosition += rowSpacing - lineHeight;
        }
        return g;
    }
}
