/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.printing;

import megamek.common.annotations.Nullable;
import megameklab.com.MegaMekLab;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static megameklab.com.printing.PrintRecordSheet.DEFAULT_PIP_SIZE;

/**
 * Utility for determining placement of armor and structure pips.
 * Assumes that the group includes a number of {@link SVGRectElement}s defining horizontal rows,
 * that the regions are continguous, and the rows themselves are contiguous.
 */
class ArmorPipLayout {

    private final PrintRecordSheet sheet;
    private final Element group;
    private final int pipCount;
    private final PrintRecordSheet.PipType pipType;
    private final double strokeWidth;
    private final Bounds bounds;
    private final double avgHeight;
    private final double avgWidth;
    private final TreeMap<Double, Bounds> regions = new TreeMap<>();
    /**
     * Rows with gaps where pips should not be drawn will have an additional
     * entry in this map.
     */
    private final Map<Double, Bounds> negativeRegions = new HashMap<>();

    /**
     * Processes the <code>rect</code> elements within a group to find the width of the region
     * at each marked point and adds pip elements to the group layed out in a symmetric pattern.
     *
     * @param sheet       The record sheet being printed.
     * @param group       The group element that contains the <code>rect</code> elements that
     *                    mark the dimensions of the area on the armor or structure diagram.
     * @param pipCount    The number of armor or structure pips to add
     * @param pipType     The shape of pip to add
     * @param strokeWidth The width of the pip outline stroke
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
                        PrintRecordSheet.PipType pipType, double strokeWidth) {
        if (pipCount > 0) {
            ArmorPipLayout layout = new ArmorPipLayout(sheet, group, pipCount,
                    pipType, strokeWidth);
            layout.process();
        }
    }

    /**
     * Processes the <code>rect</code> elements within a group to find the width of the region
     * at each marked point and adds pip elements to the group layed out in a symmetric pattern.
     *
     * @param sheet       The record sheet being printed.
     * @param group       The group element that contains the <code>rect</code> elements that
     *                    mark the dimensions of the area on the armor or structure diagram.
     * @param pipCount    The number of armor or structure pips to add
     * @param pipType     The shape of pip to add
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
                        PrintRecordSheet.PipType pipType) {
        addPips(sheet, group, pipCount, pipType, 0.5);
    }

    /**
     * Processes the <code>rect</code> elements within a group to find the width of the region
     * at each marked point and adds circular pip elements to the group layed out in a symmetric
     * pattern.
     *
     * @param sheet       The record sheet being printed.
     * @param group       The group element that contains the <code>rect</code> elements that
     *                    mark the dimensions of the area on the armor or structure diagram.
     * @param pipCount    The number of armor or structure pips to add
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount) {
        addPips(sheet, group, pipCount, PrintRecordSheet.PipType.CIRCLE, 0.5);
    }

    private ArmorPipLayout(PrintRecordSheet sheet, Element group, int pipCount, PrintRecordSheet.PipType pipType,
                   double strokeWidth) {
        this.sheet = sheet;
        this.group = group;
        this.pipCount = pipCount;
        this.pipType = pipType;
        this.strokeWidth = strokeWidth;
        bounds = processRegions();
        avgHeight = regions.values().stream().mapToDouble(Bounds::height).average().orElse(0.0);
        avgWidth = (regions.values().stream().mapToDouble(Bounds::width).sum()
                - negativeRegions.values().stream().mapToDouble(Bounds::width).sum()) / regions.size();
    }

    /**
     * Iterates through the <code>rect</code> elements in the group, sorts them into a {@link TreeMap}
     * keyed to the y coordinate of the top, and calculates the bounding box.
     *
     * @return The bounding box of the region
     */
    private Bounds processRegions() {
        double left = Double.MAX_VALUE;
        double top = Double.MAX_VALUE;
        double right = 0.0;
        double bottom = 0.0;
        for (int i = 0; i < group.getChildNodes().getLength(); i++) {
            final Node r = group.getChildNodes().item(i);
            if (r instanceof SVGRectElement) {
                Bounds bbox = new Bounds(PrintRecordSheet.getRectBBox((SVGRectElement) r));
                if (bbox.left < left) {
                    left = bbox.left;
                }
                if (bbox.top < top) {
                    top = bbox.top;
                }
                if (bbox.right > right) {
                    right = bbox.right;
                }
                if (bbox.bottom > bottom) {
                    bottom = bbox.bottom;
                }
                regions.put(bbox.top, bbox);
                Bounds gap = parseGap(bbox, (Element) r);
                if (null != gap) {
                    negativeRegions.put(bbox.top, gap);
                }
            }
        }
        return new Bounds(left, top, right, bottom);
    }

    /**
     * Checks the style attribute for a field in the format {@code mml-gap:x,y}, indicating
     * that pips should not be placed between those coordinates.
     *
     * @param bbox The dimensions of the rectangle bounding box defining the pip row
     * @param rect The SVG rect element
     * @return     The dimensions of the section of the row to leave blank, or null if no gap
     *             is defined or it is malformed.
     */
    private @Nullable Bounds parseGap(Bounds bbox, Element rect) {
        final String style = rect.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
        if (null != style) {
            for (String field : style.split(";")) {
                if (field.startsWith(IdConstants.MML_GAP)) {
                    final String[] dim = field.substring(field.indexOf(":") + 1).split(",");
                    try {
                        if (dim.length == 2) {
                            final double left = Double.parseDouble(dim[0]);
                            final double right = Double.parseDouble(dim[1]);
                            if (left < right
                                    && left > bbox.left
                                    && right < bbox.right) {
                                return new Bounds(left, bbox.top, right, bbox.bottom);
                            } else {
                                MegaMekLab.getLogger().error(getClass(), "parseGap(Rectangle2D, String)",
                                        "Gap is not contained within bounding rectangle in "
                                                + rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
                            }
                        } else {
                            MegaMekLab.getLogger().error(getClass(), "parseGap(Rectangle2D, String)",
                                    "Incorrect number of parameters to "
                                            + IdConstants.MML_GAP + " in "
                                            + rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
                        }
                    } catch (NumberFormatException ex) {
                        MegaMekLab.getLogger().error(getClass(), "parseGap(Rectangle2D, String)",
                            "NumberFormatException parsing gap paramets in "
                                    + rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
                    }
                }
            }
        }
        return null;
    }

    /**
     * Performs the calculations to lay out the pips and adds them to the document.
     */
    void process() {
        /* Estimate the number of rows required by finding the height of a rectangle
         * with an area of pipCount that has the same aspect ratio as the bounding box.
         */
        int nRows = Math.max(1, (int) Math.round(Math.sqrt(pipCount * bounds.height() / bounds.width())));

        // We don't want to allocate more rows than we have pips.
        if (nRows > pipCount) {
            nRows = pipCount;
        }
        // Calculate the average width of the rows in pips, but no more than the number that can
        // fit in the average row. avgHeight is the initial default cell size.
        int nCols = Math.min(pipCount / nRows, (int) (avgWidth / avgHeight));
        // If the initial number is not enough to hold the number of pips add more rows
        // or columns, attempting to keep close to the original aspect ratio.
        while ((nCols * nRows < pipCount) && (nRows <= pipCount)) {
            if (avgWidth / nCols > bounds.height() / nRows) {
                nCols++;
            } else {
                nRows++;
            }
        }
        // If staggered, successive rows are offset by a half pip to allow the rows to be closer
        // together
        boolean staggered = false;
        double radius = avgHeight * DEFAULT_PIP_SIZE;
        double spacing = Math.min(avgHeight, bounds.height() / nRows);
        // If the orthagonal arrangement is not possible, we may also have to scale down
        // this size of the pips.
        if (spacing < avgHeight) {
            staggered = true;
            radius = Math.min(radius, spacing * 0.5);
        }

        // Number of pips per row
        List<Integer> rowCount = new ArrayList<>();
        // The bounds of each actual row
        List<Bounds> rows = new ArrayList<>();
        // Space to be skipped
        List<Bounds> gaps = new ArrayList<>();
        // Expand the spacing between rows geometrically
        spacing = Math.sqrt(spacing * nRows / bounds.height()) * bounds.height() / nRows;
        double ypos = Math.max(bounds.top, bounds.top + (bounds.height() - spacing * nRows) / 2.0
                + spacing * 0.5 - radius);
        // As we add or remove pips to make the count come all even or odd (or alternating for staggered)
        // keep track of the shift and ajust up or down to keep the shift close to zero
        int shift = 0;
        // If staggered, toggle the parity after each row. Otherwise try to keep the same parity.
        int parity = nCols % 2;
        for (int r = 0; r < nRows; r++) {
            Map.Entry<Double, Bounds> upperEntry = regions.floorEntry(ypos);
            Bounds upper = upperEntry.getValue();
            Map.Entry<Double, Bounds> lowerEntry= regions.ceilingEntry(ypos);
            Bounds lower = lowerEntry == null ? upper : lowerEntry.getValue();
            Bounds row = new Bounds(Math.max(upper.left, lower.left),
                    ypos, Math.min(upper.right, lower.right), ypos + spacing);
            rows.add(row);
            Bounds gap = mergeGaps(row, negativeRegions.get(upperEntry.getKey()),
                    lowerEntry == null? null : negativeRegions.get(lowerEntry.getKey()));
            gaps.add(gap);
            // First we figure out how much width we have to work with on this row.
            int count = staggered ?
                    (int) (nCols * ((row.width() - gap.width()) / avgWidth) * 0.5) :
                    (int) (nCols * ((row.width() - gap.width()) / avgWidth));
            // If the row is split and the difference in width between the two sides is within
            // the margin of the presumed spacing, use an even number so it can be split
            // evenly between them.
            boolean mirror = gap.width() > 0
                    && Math.abs((gap.left - row.left) - (row.right - gap.right)) < spacing;
            if ((mirror && count % 2 == 1) || (!mirror && count % 2 != parity)) {
                if (shift <= 0 || count == 0) {
                    count++;
                    shift--;
                } else {
                    count--;
                    shift++;
                }
                if (count * spacing * 2 > row.width() && count >= 2) {
                    count -= 2;
                }
            }
            rowCount.add(count);
            ypos += spacing;
            if (staggered) {
                parity = 1 - parity;
            }
        }
        double xSpacing = adjustCount(rows, gaps, rowCount, staggered, spacing);
        drawPips(rows, gaps, rowCount, staggered, Math.min(radius, xSpacing * 0.4), xSpacing);
    }

    /**
     * Compares gaps in two adjacent rows and returns the union. If neither row has a gap,
     * returns a bounds of zero width.
     *
     * @param row   The row that's being constructed.
     * @param gap1  The gap in the upper row, if any
     * @param gap2  The gap in the lower row, if any
     * @return      The union between any gaps.
     */
    private Bounds mergeGaps(Bounds row, @Nullable Bounds gap1, @Nullable Bounds gap2) {
        double left = 0.0;
        double right = 0.0;
        if (null == gap1 && null == gap2) {
            return new Bounds(0.0, row.top, 0.0, row.bottom);
        } else if (null == gap2) {
            left = gap1.left;
            right = gap1.right;
        } else if (null == gap1) {
            left = gap2.left;
            right = gap2.right;
        } else {
            left = Math.min(gap1.left, gap2.left);
            right = Math.max(gap1.right, gap2.right);
        }
        return new Bounds(Math.max(left, row.left), row.top, Math.min(right, row.right), row.bottom);
    }

    /**
     * Takes the rough layout and adds or removes pips to match the required number.
     * Pips are added to the rows with the most extra space and removed from rows with
     * the least extra space.
     *
     * @param rows      A list of the bounding boxes of the selected rows
     * @param gaps      A list of the bounding boxes of any gaps to be left in the rows.
     * @param rowCount  The number of pips in each of the rows. This list needs to be
     *                  the same size as {@code rows}.
     * @param staggered If true, attempt to maintain the parity of each row.
     * @param spacing   The spacing between rows, used as the starting spacing between
     *                  pips in the row
     * @return          The ratio of the horizontal to vertical spacing
     */
    private double adjustCount(List<Bounds> rows, List<Bounds> gaps, List<Integer> rowCount,
                               boolean staggered, double spacing) {
        int current = rowCount.stream().mapToInt(Integer::intValue).sum();
        if (current == pipCount) {
            return spacing;
        }
        // Sort the indices from the most extra space to the least
        List<Integer> indices = IntStream.range(0, rows.size()).boxed()
                .sorted(Comparator.comparingDouble(i -> rowCount.get(i) / (rows.get(i).width() - gaps.get(i).width())))
                .collect(Collectors.toList());
        int row = 0;
        int dRowCount = staggered ? 2 : 1;
        if (current > pipCount) {
            Collections.reverse(indices);
            dRowCount = -dRowCount;
        }
        int full;
        do {
            full = 0;
            while ((current != pipCount) && full < rows.size()) {
                int index = indices.get(row % indices.size());
                int change;
                if (Math.abs(pipCount - current) == 1) {
                    change = pipCount - current;
                } else {
                    change = dRowCount;
                }
                if (spacing * (rowCount.get(index) + change) <= rows.get(index).width() - gaps.get(index).width()) {
                    rowCount.set(index, rowCount.get(index) + change);
                    current += change;
                } else {
                    full++;
                }
                row++;
            }
            if (full == rows.size()) {
                spacing *= 0.9;
            }
        } while (full == rows.size());
        return spacing;
    }

    /**
     * Calculates the actual position of each pip and adds it to the document.
     *
     * @param rows      A list of bounding rectangles defining the position and width of each row
     * @param gaps      A list of the bounding boxes of any gaps to be left in the rows.
     * @param rowCount  The number of pips to place in the row with the same index
     * @param staggered If true, the horizontal spacing will be double the verticle.
     * @param radius    The radius of each pip.
     */
    private void drawPips(List<Bounds> rows, List<Bounds> gaps, List<Integer> rowCount,
                          boolean staggered, double radius, double xSpacing) {
        double dx = staggered ? xSpacing * 2 : xSpacing;
        /* Find the row that takes up the largest perctage of its row. If it's over 100%,
         * reduce the horizontal spacing to make it fit. If lower, increase the horizontal
         * spacing by a factor of the geometric mean of the percentage and 1.0 to prevent
         * having a cluster of pips in the center and too much blank space on the sides. */
        double pct = 0.0;
        for (int r = 0; r < rows.size(); r++) {
            pct = Math.max(pct, (dx * rowCount.get(r)) / (rows.get(r).width() - gaps.get(r).width()));
        }
        if (pct > 1.0) {
            dx /= pct;
        } else {
            dx /= Math.sqrt(pct);
        }
        // Start by centering the top row and fit subsequent rows into the same grid if possible.
        // If the rows shift in a way that the pips cannot fit within the grid, shift the center.
        double centerX = rows.get(0).centerX();
        // The offset needed to center the pip in the cell.
        double xPadding = dx * 0.5 - radius;
        for (int r = 0; r < rows.size(); r++) {
            final Bounds row = rows.get(r);
            if (gaps.get(r).width() > 0) {
                Bounds left = new Bounds(row.left, row.top, gaps.get(r).left, row.bottom);
                Bounds right = new Bounds(gaps.get(r).right, row.top, row.right, row.bottom);
                int count = (int) Math.round(rowCount.get(r) * left.width() / (left.width() + right.width()));
                drawRow(left, count, radius, dx, centerX, xPadding);
                drawRow(right, rowCount.get(r) - count, radius, dx, centerX, xPadding);
                centerX = row.centerX();
            } else {
                centerX = drawRow(row, rowCount.get(r), radius, dx, centerX, xPadding);
            }
        }
    }

    private double drawRow(Bounds row, int count, double radius, double dx, double centerX, double xPadding) {
        double xpos = calcRowStartX(centerX, count, dx) + xPadding;
        while (xpos < row.left) {
            xpos += dx;
        }
        while (xpos + dx * count > row.right) {
            xpos -= dx;
        }
        if (xpos < row.left || count == 1) {
            centerX = row.centerX();
            xpos = calcRowStartX(centerX, count, dx) + xPadding;
        }
        for (int i = 0; i < count; i++) {
            Element pip = sheet.createPip(xpos, row.top, radius, strokeWidth, pipType);
            group.appendChild(pip);
            xpos += dx;
        }
        return centerX;
    }

    /**
     * Calculates the x coordinate of the leftmost pip to center the row on the provided anchor.
     *
     * @param center    The x coordinate of the center of the row
     * @param pipCount  The number of pips on the current row
     * @param cellWidth The width of each pip, including padding
     * @return          The x coordinate of the starting pip
     */
    private double calcRowStartX(double center, int pipCount, double cellWidth) {
        return center - cellWidth * (pipCount / 2.0);
    }

    /**
     * Data class for defining rows
     */
    private static class Bounds {
        final double left;
        final double top;
        final double right;
        final double bottom;

        Bounds(double left, double top, double right, double bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        Bounds(Rectangle2D rect) {
            this.left = rect.getMinX();
            this.top = rect.getMinY();
            this.right = rect.getMaxX();
            this.bottom = rect.getMaxY();
        }

        double width() {
            return right - left;
        }

        double height() {
            return bottom - top;
        }

        double centerX() {
            return left + width() * 0.5;
        }
    }
}
