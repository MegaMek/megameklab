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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private final TreeMap<Double, Rectangle2D> regions = new TreeMap<>();

    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
                        PrintRecordSheet.PipType pipType, double strokeWidth) {
        ArmorPipLayout layout = new ArmorPipLayout(sheet, group, pipCount,
                pipType, strokeWidth);
        layout.process();
    }

    private ArmorPipLayout(PrintRecordSheet sheet, Element group, int pipCount, PrintRecordSheet.PipType pipType,
                   double strokeWidth) {
        this.sheet = sheet;
        this.group = group;
        this.pipCount = pipCount;
        this.pipType = pipType;
        this.strokeWidth = strokeWidth;
        bounds = processRegions();
        avgHeight = regions.values().stream().mapToDouble(Rectangle2D::getHeight).average().orElse(0.0);
        avgWidth = regions.values().stream().mapToDouble(Rectangle2D::getWidth).average().orElse(0.0);
    }

    private Bounds processRegions() {
        double left = Double.MAX_VALUE;
        double top = Double.MAX_VALUE;
        double right = 0.0;
        double bottom = 0.0;
        for (int i = 0; i < group.getChildNodes().getLength(); i++) {
            final Node r = group.getChildNodes().item(i);
            if (r instanceof SVGRectElement) {
                Rectangle2D bbox = PrintRecordSheet.getRectBBox((SVGRectElement) r);
                if (bbox.getX() < left) {
                    left = bbox.getX();
                }
                if (bbox.getY() < top) {
                    top = bbox.getY();
                }
                if (bbox.getX() + bbox.getWidth() > right) {
                    right = bbox.getX() + bbox.getWidth();
                }
                if (bbox.getY() + bbox.getHeight() > bottom) {
                    bottom = bbox.getY() + bbox.getHeight();
                }
                regions.put(bbox.getY(), bbox);
            }
        }
        return new Bounds(left, top, right, bottom);
    }

    void process() {
        int nRows = (int) Math.sqrt(pipCount / (bounds.width() / bounds.height()));
        int nCols = pipCount / nRows;
        while (nCols * nRows < pipCount) {
            nRows++;
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
        // Expand the spacing between rows geometrically
        spacing = Math.sqrt(spacing * nRows / bounds.height()) * bounds.height() / nRows;
        double ypos = bounds.top + (bounds.height() - spacing * nRows) / 2.0;
        for (int r = 0; r < nRows; r++) {
            Rectangle2D upper = regions.floorEntry(ypos).getValue();
            Map.Entry<Double, Rectangle2D> lowerEntry= regions.ceilingEntry(ypos);
            Rectangle2D lower = lowerEntry == null ? upper : lowerEntry.getValue();
            Bounds row = new Bounds(Math.max(upper.getMinX(), lower.getMinX()),
                    ypos, Math.min(upper.getMaxX(), lower.getMaxX()), ypos + spacing);
            rows.add(row);
            // First we figure out how much width we have to work with on this row.
            if (staggered) {
                int count = (int) (nCols * (row.width() / avgWidth) * 0.5);
                if (r % 2 == 0) {
                    count++;
                    if (count * spacing * 2 > row.width()) {
                        count -= 2;
                    }
                }
                rowCount.add(count);
            } else {
                rowCount.add((int) (nCols * (row.width() / avgWidth)));
            }
            ypos += spacing;
        }
        drawPips(rows, rowCount, spacing, staggered, radius);
    }

    private void drawPips(List<Bounds> rows, List<Integer> rowCount,
                          double spacing, boolean staggered, double radius) {
        // Start by centering the top row and fit subsequent rows into the same grid if possible.
        // If the rows shift in a way that the pips cannot fit within the gird, shift the center.
        double centerX = rows.get(0).centerX();
        // The offset needed to center the pip in the cell.
        double xPadding = spacing * 0.5 - radius;
        double dx = staggered ? spacing * 2 : spacing;
        // Check whether all the pips will fit within their assigned rows. If not, compress
        // the horizontal spacing
        for (int r = 0; r < rows.size(); r++) {
            if (rows.get(r).width() < dx * rowCount.get(r)) {
                dx = rows.get(r).width() / rowCount.get(r);
            }
        }
        for (int r = 0; r < rows.size(); r++) {
            double xpos;
            xpos = calcRowStartX(centerX, rowCount.get(r), dx) + xPadding;
            while (xpos < rows.get(r).left) {
                xpos += dx;
            }
            while (xpos + dx * rowCount.get(r) > rows.get(r).right) {
                xpos -= dx;
            }
            if (xpos < rows.get(r).left) {
                centerX = rows.get(r).centerX();
                xpos = calcRowStartX(centerX, rowCount.get(r), dx) + xPadding;
            }
            for (int i = 0; i < rowCount.get(r); i++) {
                Element pip = sheet.createPip(xpos, rows.get(r).top, radius, strokeWidth, pipType);
                group.appendChild(pip);
                xpos += dx;
            }
        }
    }

    private double calcRowStartX(double center, int pipCount, double cellWidth) {
        double xpos = center - cellWidth * (pipCount / 2);
        if (pipCount % 2 == 1) {
            xpos -= cellWidth * 0.5;
        }
        return xpos;
    }

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
