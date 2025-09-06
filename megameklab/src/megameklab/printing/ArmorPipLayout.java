/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import static megameklab.printing.PrintRecordSheet.DEFAULT_PIP_SIZE;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import megamek.common.annotations.Nullable;
import megamek.logging.MMLogger;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGRectElement;

/**
 * <p>
 * Utility for determining placement of armor and structure pips. The position and shape of the space is defined by
 * metadata in the svg document. This is used to calculate the number of rows of pips, how many to place in each row,
 * and how far apart to space them. This class is accessed through the static method
 * {@link ArmorPipLayout#addPips(PrintRecordSheet, Element, int, PipType, double, String, int, boolean) addPips}.
 * </p>
 *
 *
 * <p>
 * The region should be a {@link SVGGElement} group containing one or more {@link SVGRectElement}s. The rect elements
 * define horizontal bands that have their top segment fully contained within the region to define its position and
 * shape. The average height of the elements is used as the starting size of the pips in the region, though they may be
 * scaled down to fit. The width at any arbitrary y coordinate in the region is computed as the most restrictive of the
 * top segments of the rect element on either side. The rects may overlap to define steeply angled sides, or may be
 * spaced out where the sides are vertical.
 * </p>
 *
 * <p>
 * Gaps in the horizontal bands (such as to skip the cockpit on an aerospace fighter) can be indicated by a custom entry
 * in the rect element's style attribute in the format {@code mml-gap:x1,x2}, where x1 and x2 are the beginning and
 * ending x coordinate of the gap, respectively. Non-contiguous vertical sections (such as the side armor on VTOLs) are
 * indicated by grouping each section into its own g element and adding {@code mml-multisection:true} to the parent g
 * element's style attribute.
 * </p>
 */
class ArmorPipLayout {
    private static final MMLogger logger = MMLogger.create(ArmorPipLayout.class);

    /** Margin of error used for checking equality between floating point values */
    private static final double PRECISION = 0.01;

    private final PrintRecordSheet sheet;
    private final Element group;
    private final PipType pipType;
    private final double strokeWidth;
    private final String fill;
    private final Bounds bounds;
    private final double avgHeight;
    private final double avgWidth;
    private final TreeMap<Double, Bounds> regions = new TreeMap<>();
    /**
     * Rows with gaps where pips should not be drawn will have an additional entry in this map.
     */
    private final Map<Double, Bounds> negativeRegions = new HashMap<>();

    /**
     * Processes the rect elements within a group to find the width of the region at each marked point and adds pip
     * elements to the group laid out in a symmetric pattern.
     *
     * @param sheet           The record sheet being printed.
     * @param group           The group element that contains the rect elements that mark the dimensions of the area on
     *                        the armor or structure diagram.
     * @param pipCount        The number of armor or structure pips to add
     * @param pipType         The shape of pip to add
     * @param strokeWidth     The width of the pip outline stroke
     * @param fill            The color to use for the inside of the pip
     * @param damage          The amount of damage to show on the pip. 0 for no damage.
     * @param alternateMethod If the armor pips should be attempted to be grouped in 5s
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
          PipType pipType, double strokeWidth,
          String fill, int damage, boolean alternateMethod) {
        addPips(sheet, group, pipCount, pipType, strokeWidth, fill, damage, alternateMethod, null, null, false);
    }

    /**
     * Processes the rect elements within a group to find the width of the region at each marked point and adds pip
     * elements to the group laid out in a symmetric pattern.
     *
     * @param sheet           The record sheet being printed.
     * @param group           The group element that contains the rect elements that mark the dimensions of the area on
     *                        the armor or structure diagram.
     * @param pipCount        The number of armor or structure pips to add
     * @param pipType         The shape of pip to add
     * @param strokeWidth     The width of the pip outline stroke
     * @param fill            The color to use for the inside of the pip
     * @param damage          The amount of damage to show on the pip. 0 for no damage.
     * @param alternateMethod If the armor pips should be attempted to be grouped in 5s
     * @param className       The class name to apply to the pips
     * @param loc             The location of the pips, used for tooltips
     * @param rear            If the pips are for the rear armor
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
          PipType pipType, double strokeWidth,
          String fill, int damage, boolean alternateMethod, String className, String loc, boolean rear) {
        if (pipCount > 0) {
            boolean multi = false;
            final String multiVal = PrintRecordSheet.parseStyle(group, IdConstants.MML_MULTISECTION);
            if (null != multiVal) {
                multi = Boolean.parseBoolean(multiVal);
            }
            if (multi) {
                // If pips are to be split among multiple sections, instantiate each section and
                // estimate the area.
                // We will divide the pips proportionally to the area
                List<ArmorPipLayout> sections = new ArrayList<>();
                double area = 0.0;
                for (int i = 0; i < group.getChildNodes().getLength(); i++) {
                    final Node node = group.getChildNodes().item(i);
                    if (node instanceof SVGGElement) {
                        ArmorPipLayout section = new ArmorPipLayout(sheet, (Element) node, pipType, strokeWidth, fill);
                        if (!section.regions.isEmpty()) {
                            sections.add(section);
                            area += section.avgWidth * section.bounds.height();
                        }
                    }
                }
                List<Integer> pipCounts = new ArrayList<>();
                int allocated = 0;
                for (ArmorPipLayout section : sections) {
                    int pips = (int) Math.round(pipCount * (section.avgWidth * section.bounds.height() / area));
                    allocated += pips;
                    pipCounts.add(pips);
                }
                // Deal with rounding inaccuracies by distributing remaining pips starting with
                // the first
                // or removing extras starting with the last.
                int i = 0;
                while (pipCount > allocated) {
                    int row = i % sections.size();
                    pipCounts.set(row, pipCounts.get(row) + 1);
                    allocated++;
                    i++;
                }
                while (pipCount < allocated) {
                    int row = sections.size() - i % sections.size() - 1;
                    pipCounts.set(row, pipCounts.get(row) - 1);
                    allocated--;
                    i++;
                }
                for (int s = 0; s < sections.size(); s++) {
                    if (pipCounts.get(s) > 0) {
                        sections.get(s).process(pipCounts.get(s), damage, alternateMethod, className, loc, rear);
                    }
                }
            } else {
                ArmorPipLayout layout = new ArmorPipLayout(sheet, group,
                      pipType, strokeWidth, fill);
                if (!layout.regions.isEmpty()) {
                    layout.process(pipCount, damage, alternateMethod, className, loc, rear);
                }
            }
        }
    }

    /**
     * Processes the rect elements within a group to find the width of the region at each marked point and adds pip
     * elements to the group laid out in a symmetric pattern.
     *
     * @param sheet           The record sheet being printed.
     * @param group           The group element that contains the rect elements that mark the dimensions of the area on
     *                        the armor or structure diagram.
     * @param pipCount        The number of armor or structure pips to add
     * @param pipType         The shape of pip to add
     * @param strokeWidth     The width of the pip outline stroke
     * @param fill            The color to use for the inside of the pip
     * @param alternateMethod If the armor pips should be attempted to be grouped in 5s
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
          PipType pipType, double strokeWidth,
          String fill, boolean alternateMethod) {
        addPips(sheet, group, pipCount, pipType, strokeWidth, fill, 0, alternateMethod);
    }

    /**
     * Processes the rect elements within a group to find the width of the region at each marked point and adds pip
     * elements to the group laid out in a symmetric pattern.
     *
     * @param sheet           The record sheet being printed.
     * @param group           The group element that contains the rect elements that mark the dimensions of the area on
     *                        the armor or structure diagram.
     * @param pipCount        The number of armor or structure pips to add
     * @param pipType         The shape of pip to add
     * @param alternateMethod If the armor pips should be attempted to be grouped in 5s
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount,
          PipType pipType, boolean alternateMethod) {
        addPips(sheet,
              group,
              pipCount,
              pipType,
              PrintRecordSheet.DEFAULT_PIP_STROKE,
              PrintRecordSheet.FILL_WHITE,
              0,
              alternateMethod);
    }

    /**
     * Processes the rect elements within a group to find the width of the region at each marked point and adds circular
     * pip elements to the group laid out in a symmetric pattern.
     *
     * @param sheet           The record sheet being printed.
     * @param group           The group element that contains the rect elements that mark the dimensions of the area on
     *                        the armor or structure diagram.
     * @param pipCount        The number of armor or structure pips to add
     * @param alternateMethod If the armor pips should be attempted to be grouped in 5s
     */
    static void addPips(PrintRecordSheet sheet, Element group, int pipCount, boolean alternateMethod) {
        addPips(sheet, group, pipCount, PipType.CIRCLE, PrintRecordSheet.DEFAULT_PIP_STROKE,
              PrintRecordSheet.FILL_WHITE, 0, alternateMethod);
    }

    private ArmorPipLayout(PrintRecordSheet sheet, Element group, PipType pipType,
          double strokeWidth, String fill) {
        this.sheet = sheet;
        this.group = group;
        this.pipType = pipType;
        this.strokeWidth = strokeWidth;
        this.fill = fill;
        bounds = processRegions();
        avgHeight = regions.values().stream().mapToDouble(Bounds::height).average().orElse(0.0);
        avgWidth = (regions.values().stream().mapToDouble(Bounds::width).sum()
              - negativeRegions.values().stream().mapToDouble(Bounds::width).sum()) / regions.size();
    }

    /**
     * Iterates through the rect elements in the group, sorts them into a {@link TreeMap} keyed to the y coordinate of
     * the top, and calculates the bounding box.
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
     * Checks the style attribute for a field in the format {@code mml-gap:x1,x2}, indicating that pips should not be
     * placed between those coordinates.
     *
     * @param bbox The dimensions of the rectangle bounding box defining the pip row
     * @param rect The SVG rect element
     *
     * @return The dimensions of the section of the row to leave blank, or null if no gap is defined, or it is
     *       malformed.
     */
    private @Nullable Bounds parseGap(Bounds bbox, Element rect) {
        final String gap = PrintRecordSheet.parseStyle(rect, IdConstants.MML_GAP);
        if (null != gap) {
            final String[] dim = gap.split(",");
            try {
                if (dim.length == 2) {
                    final double left = Double.parseDouble(dim[0]);
                    final double right = Double.parseDouble(dim[1]);
                    if (left < right
                          && left >= bbox.left - PRECISION
                          && right <= bbox.right + PRECISION) {
                        return new Bounds(left, bbox.top, right, bbox.bottom);
                    } else {
                        logger.error("Gap is not contained within bounding rectangle in {}",
                              rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
                    }
                } else {
                    logger.error("Incorrect number of parameters to " + IdConstants.MML_GAP + " in {}",
                          rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
                }
            } catch (NumberFormatException ex) {
                logger.error("NumberFormatException parsing gap parameters in {}",
                      rect.getAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE));
            }
        }
        return null;
    }

    private Iterable<Bounds> iterateRegionsFromMiddle() {
        return () -> new Iterator<>() {
            private final Bounds[] r = regions.values().toArray(new Bounds[0]);
            private int left = regions.size() / 2 - 1;
            private int right = regions.size() / 2;
            private boolean nextRight = true;

            @Override
            public boolean hasNext() {
                if (nextRight) {
                    return right < r.length;
                } else {
                    return left >= 0;
                }
            }

            @Override
            public Bounds next() {
                Bounds ret;
                if (nextRight) {
                    ret = r[right++];
                } else {
                    ret = r[left--];
                }
                nextRight = !nextRight;
                return ret;
            }
        };
    }

    void process(int pipCount, int damage, boolean alternate, String className, String loc, boolean rear) {
        if (!alternate) {
            process(pipCount, damage, className, loc, rear);
            return;
        }
        int attempts = 0;
        double diameter = regions.firstEntry().getValue().height();
        double originalDiameter = diameter;

        int remaining;
        List<Point2D.Double> pips;
        do {
            remaining = pipCount;
            pips = new ArrayList<>(pipCount);
            for (Bounds bbox : iterateRegionsFromMiddle()) {
                int capacity = (int) (bbox.width() / diameter);
                capacity -= capacity / 6;

                int groups = Math.min(remaining / 5, capacity / 5);
                remaining -= groups * 5;
                capacity -= groups * 5;
                int leftovers = 0;
                if ((remaining % 5 != 0) && (capacity >= remaining % 5)) {
                    leftovers = remaining % 5;
                    remaining -= leftovers;
                }

                double totalWidth = groups * diameter * 6;
                if (leftovers > 0) {
                    totalWidth += leftovers * diameter;
                } else {
                    totalWidth -= diameter;
                }

                double posY = bbox.top;
                double posX = bbox.centerX() - totalWidth / 2;
                for (int i = 0; i < groups; i++) {
                    for (int j = 0; j < 5; j++) {
                        pips.add(new Point2D.Double(posX, posY));
                        posX += diameter;
                    }
                    posX += diameter;
                }
                for (int i = 0; i < leftovers; i++) {
                    pips.add(new Point2D.Double(posX, posY));
                    posX += diameter;
                }
                if (remaining == 0) {
                    break;
                }
            }
            if (remaining > 0) {
                attempts++;
                if (attempts > 5) {
                    process(pipCount, damage, className, loc, rear);
                    return;
                }
                diameter *= 0.9;
            }
        } while (remaining > 0);

        int remainingDamage = damage;
        for (var pip : pips) {
            boolean isDamaged = false;
            if (remainingDamage > 0) {
                remainingDamage--;
                isDamaged = true;
            }
            final String fillColor = (isDamaged) ? sheet.getDamageFillColor() : fill;
            group.appendChild(sheet.createPip(pip.x,
                  pip.y + (originalDiameter / 2 - diameter / 2.2),
                  diameter / 2.2,
                  strokeWidth,
                  pipType,
                  fillColor));
        }
    }

    /**
     * Performs the calculations to lay out the pips and adds them to the document.
     *
     * @param pipCount The number of pips to place in the region
     */
    private void process(int pipCount, int damage, String className, String loc, boolean rear) {
        /* Estimate the number of rows required by finding the height of a rectangle
         * with an area of pipCount that has the same aspect ratio as the bounding box.
         */
        int nRows = Math.max(1, (int) Math.round(Math.sqrt(pipCount * bounds.height() / bounds.width())));

        // We don't want to allocate more rows than we have pips.
        if (nRows > pipCount) {
            nRows = pipCount;
        }
        // Calculate the average width of the rows in pips, but no more than the number
        // that can
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
        // If staggered, successive rows are offset by a half pip to allow the rows to
        // be closer
        // together without touching
        boolean staggered = false;
        double radius = avgHeight * DEFAULT_PIP_SIZE;
        double spacing = Math.min(avgHeight, bounds.height() / nRows);
        // If the orthogonal arrangement is not possible, we may also have to scale down this size of the pips.
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
        // Expand the spacing between rows geometrically to reduce crowding in the
        // middle of the region
        spacing = Math.sqrt(spacing * nRows / bounds.height()) * bounds.height() / nRows;
        double yPosition = Math.max(bounds.top, bounds.top + (bounds.height() - spacing * nRows) / 2.0
              + spacing * 0.5 - radius);
        // As we add or remove pips to make the count come all even or odd (or alternating for staggered) keep track
        // of the shift and adjust up or down to keep the shift close to zero
        int shift = 0;
        // If staggered, toggle the parity after each row. Otherwise, try to keep the same parity.
        int parity = nCols % 2;
        for (int r = 0; r < nRows; r++) {
            Map.Entry<Double, Bounds> upperEntry = regions.floorEntry(yPosition);
            Bounds upper = upperEntry.getValue();
            Map.Entry<Double, Bounds> lowerEntry = regions.ceilingEntry(yPosition);
            Bounds lower = lowerEntry == null ? upper : lowerEntry.getValue();
            Bounds row = new Bounds(Math.max(upper.left, lower.left),
                  yPosition, Math.min(upper.right, lower.right), yPosition + spacing);
            Bounds gap = mergeGaps(row, negativeRegions.get(upperEntry.getKey()),
                  lowerEntry == null ? null : negativeRegions.get(lowerEntry.getKey()));
            if (gap.width() > 0 && gap.left <= row.left + PRECISION
                  && gap.right >= row.right - PRECISION) {
                yPosition += spacing;
                continue;
            }
            rows.add(row);
            gaps.add(gap);
            // First we figure out how much width we have to work with on this row.
            int count = staggered ? (int) (nCols * ((row.width() - gap.width()) / avgWidth) * 0.5)
                  : (int) (nCols * ((row.width() - gap.width()) / avgWidth));
            // If the row is split and the difference in width between the two sides is
            // within
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
            yPosition += spacing;
            if (staggered) {
                parity = 1 - parity;
            }
        }
        double xSpacing = adjustCount(pipCount, rows, gaps, rowCount, staggered, spacing);
        drawPips(rows,
              gaps,
              rowCount,
              staggered,
              Math.min(radius, xSpacing * 0.4),
              xSpacing,
              damage,
              className,
              loc,
              rear);
    }

    /**
     * Compares gaps in two adjacent rows and returns the union. If neither row has a gap, returns a bounds of zero
     * width.
     *
     * @param row  The row that's being constructed.
     * @param gap1 The gap in the upper row, if any
     * @param gap2 The gap in the lower row, if any
     *
     * @return The union between any gaps.
     */
    private Bounds mergeGaps(Bounds row, @Nullable Bounds gap1, @Nullable Bounds gap2) {
        double left;
        double right;
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
     * Takes the rough layout and adds or removes pips to match the required number. Pips are added starting with the
     * rows with the most extra space and removed starting with rows with the least extra space.
     *
     * @param pipCount  The total number of pips
     * @param rows      A list of the bounding boxes of the generated rows
     * @param gaps      A list of the bounding boxes of any gaps to be left in the rows. Each entry corresponds to the
     *                  same index in {@code rows}.
     * @param rowCount  The number of pips in each of the rows. This list needs to be the same size as {@code rows}.
     * @param staggered If true, attempt to maintain the parity of each row (which should be alternating).
     * @param spacing   The spacing between rows, used as the starting spacing between pips in the row
     *
     * @return The ratio of the horizontal to vertical spacing
     */
    private double adjustCount(int pipCount, List<Bounds> rows, List<Bounds> gaps, List<Integer> rowCount,
          boolean staggered, double spacing) {
        int current = rowCount.stream().mapToInt(Integer::intValue).sum();
        if (current == pipCount) {
            return spacing;
        }
        // Sort the indices from the most extra space to the least
        List<Integer> indices = IntStream.range(0, rows.size()).boxed()
              .sorted(Comparator.comparingDouble(i -> rowCount.get(i) / (rows.get(i).width() - gaps.get(i).width())))
              .toList();
        // If there is a gap in the row with equal space on either side (+/- a pip width), try to keep the same
        // number of pips on either side. If they're all mirrored, and we have an odd number of pips, the last one
        // will have to defy the symmetry.
        List<Boolean> mirrored = new ArrayList<>();
        for (int index = 0; index < rows.size(); index++) {
            mirrored.add(gaps.get(index).width() > 0
                  && Math.abs((gaps.get(index).left - rows.get(index).left)
                  - (rows.get(index).right - gaps.get(index).right)) < spacing);
        }
        boolean allMirrored = mirrored.stream().allMatch(Boolean::booleanValue);
        // The number to change per row. Try to keep staggered layouts alternating in
        // width
        int rowDelta = staggered ? 2 : 1;
        int row = 0;
        // Running count of rows that are skipped due to being full or having the
        // minimum number.
        // If we go through all the rows and skip all of them, either shrink the pips
        // (when adding) or
        // remove the minimum pip requirement (when subtracting) then try again.
        int skipped;
        // Keep a minimum of 1 pip per row, or 2 per split row if the parts are the same
        // size.
        // If this still leaves us with extra pips, remove this requirement.
        boolean minimum = true;
        do {
            skipped = 0;
            while ((current != pipCount) && skipped < rows.size()) {
                int index = indices.get(row % indices.size());
                // Keep the same number of pips in each half of split rows if the sizes are within a cell width,
                // unless all the rows are mirrored, and we're down to an odd pip.
                boolean mirror = mirrored.get(index) && (!allMirrored || Math.abs(pipCount - current) > 1);
                if (pipCount > current) {
                    int change;
                    if (pipCount - current == 1) {
                        change = mirror ? 0 : 1;
                    } else {
                        change = mirror ? 2 : rowDelta;
                    }
                    if (change > 0
                          && spacing * (rowCount.get(index) + change) <= rows.get(index).width()
                          - gaps.get(index).width()) {
                        rowCount.set(index, rowCount.get(index) + change);
                        current += change;
                    } else {
                        skipped++;
                    }
                } else {
                    int change;
                    if (current - pipCount == 1) {
                        change = (mirror && minimum) ? 0 : 1;
                    } else {
                        change = mirror ? 2 : rowDelta;
                    }
                    if (minimum && (rowCount.get(index) - change <= 0)) {
                        change = 0;
                    } else {
                        change = Math.min(change, rowCount.get(index));
                    }
                    if (change > 0) {
                        rowCount.set(index, rowCount.get(index) - change);
                        current -= change;
                    } else {
                        skipped++;
                    }
                }
                row++;
            }
            if (skipped == rows.size()) {
                if (current < pipCount) {
                    spacing *= 0.95;
                } else {
                    minimum = false;
                }
            }
        } while (skipped == rows.size());
        return spacing;
    }

    /**
     * Calculates the actual position of each pip and adds it to the document.
     *
     * @param rows      A list of bounding rectangles defining the position and width of each row
     * @param gaps      A list of the bounding boxes of any gaps to be left in the rows.
     * @param rowCount  The number of pips to place in the row with the same index
     * @param staggered If true, the horizontal spacing will be double the vertical.
     * @param radius    The radius of each pip.
     */
    private void drawPips(List<Bounds> rows, List<Bounds> gaps, List<Integer> rowCount,
          boolean staggered, double radius, double xSpacing, int damage, String className, String loc, boolean rear) {
        double dx = staggered ? xSpacing * 2 : xSpacing;
        /*
         * Find the row that takes up the largest percentage of its row. If it's over
         * 100%,
         * reduce the horizontal spacing to make it fit. If lower, increase the
         * horizontal
         * spacing by a factor of the geometric mean of the percentage and 1.0 to
         * prevent
         * having a cluster of pips in the center and too much blank space on the sides.
         */
        double pct = 0.0;
        for (int r = 0; r < rows.size(); r++) {
            // Since we're measuring space between pips, only include those with multiple
            // pips (per section for split)
            if (rowCount.get(r) > ((gaps.get(r).width() > 0.0) ? 2 : 1)) {
                pct = Math.max(pct, (dx * rowCount.get(r)) / (rows.get(r).width() - gaps.get(r).width()));
            }
        }
        if (pct > 1.0) {
            dx /= pct;
        } else if (pct > 0.0) {
            dx /= Math.sqrt(pct);
        }
        // Start by centering the top row and fit subsequent rows into the same grid if
        // possible.
        // If the rows shift in a way that the pips cannot fit within the grid, shift
        // the center.
        double centerX = rows.get(0).centerX();
        // The offset needed to center the pip in the cell.
        double xPadding = dx * 0.5 - radius;
        AtomicInteger remainingDamageCounter = new AtomicInteger(damage);
        for (int r = 0; r < rows.size(); r++) {
            final Bounds row = rows.get(r);
            if (gaps.get(r).width() > 0) {
                Bounds left = new Bounds(row.left, row.top, gaps.get(r).left, row.bottom);
                Bounds right = new Bounds(gaps.get(r).right, row.top, row.right, row.bottom);
                int count = (int) Math.round(rowCount.get(r) * left.width() / (left.width() + right.width()));
                drawRow(left, count, radius, dx, centerX, xPadding, remainingDamageCounter, className, loc, rear);
                drawRow(right,
                      rowCount.get(r) - count,
                      radius,
                      dx,
                      centerX,
                      xPadding,
                      remainingDamageCounter,
                      className,
                      loc,
                      rear);
                centerX = row.centerX();
            } else {
                centerX = drawRow(row,
                      rowCount.get(r),
                      radius,
                      dx,
                      centerX,
                      xPadding,
                      remainingDamageCounter,
                      className,
                      loc,
                      rear);
            }
        }
    }

    private double drawRow(Bounds row, int count, double radius, double dx, double centerX, double xPadding,
          AtomicInteger damageCounter, String className, String loc, boolean rear) {
        double xPosition = calcRowStartX(centerX, count, dx) + xPadding;
        while (xPosition < row.left) {
            xPosition += dx;
        }
        while (xPosition + dx * count > row.right) {
            xPosition -= dx;
        }
        if (xPosition < row.left || count == 1) {
            centerX = row.centerX();
            xPosition = calcRowStartX(centerX, count, dx) + xPadding;
        }
        for (int i = 0; i < count; i++) {
            boolean isDamaged = false;
            if (damageCounter.get() > 0) {
                if (damageCounter.decrementAndGet() >= 0) {
                    isDamaged = true;
                }
            }
            final String fillColor = (isDamaged) ? sheet.getDamageFillColor() : fill;
            Element pip = sheet.createPip(xPosition,
                  row.top,
                  radius,
                  strokeWidth,
                  pipType,
                  fillColor,
                  className,
                  loc,
                  rear);
            group.appendChild(pip);
            xPosition += dx;
        }
        return centerX;
    }

    /**
     * Calculates the x coordinate of the leftmost pip to center the row on the provided anchor.
     *
     * @param center    The x coordinate of the center of the row
     * @param pipCount  The number of pips on the current row
     * @param cellWidth The width of each pip, including padding
     *
     * @return The x coordinate of the starting pip
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
