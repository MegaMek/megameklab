/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

import megamek.common.board.CubeCoords;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megameklab.util.BuildingUtil;

/** Packs full-size floorplans in physical printer points (72 per inch), without scaling hexes to fit. */
final class BuildingTemplateLayout {
    static final double FLAT_TO_FLAT = 1.25 * 72;
    static final double RADIUS = FLAT_TO_FLAT / Math.sqrt(3);
    static final double PADDING = 18;
    static final double CAPTION = 18;
    static final double GAP = 12;
    private static final int CUT_SEARCH_STATES = 2000;

    record Floor(int level, List<CubeCoords> hexes, Rectangle2D bounds, String caption) {
        double width() { return bounds.getWidth() + 2 * PADDING; }
        double height() { return bounds.getHeight() + 2 * PADDING; }
    }

    record Placement(Floor floor, double x, double y, Rectangle2D caption) { }

    private static final Comparator<CubeCoords> HEX_ORDER = Comparator.comparingDouble(BuildingTemplateLayout::centerX)
          .thenComparingDouble(BuildingTemplateLayout::centerY);

    private final List<List<Placement>> pages;
    private final AbstractBuildingEntity building;
    private final BuildingUtil.SheetGrid grid;

    BuildingTemplateLayout(AbstractBuildingEntity building, double width, double height) {
        this.building = building;
        grid = BuildingUtil.sheetGrid(building.getInternalBuilding().getOriginalCoordsList());
        List<Floor> floors = new ArrayList<>();
        for (int level : BuildingConstruction.mapLevels(building)) {
            var hexes = building.getInternalBuilding().getOriginalCoordsList().stream()
                  .filter(hex -> BuildingConstruction.occupiesMapLevel(building, hex, level)
                        && BuildingConstruction.segmentsInHex(building, hex) > 0).toList();
            if (!hexes.isEmpty()) {
                floors.add(floor(level, hexes, false));
            }
        }
        List<List<Placement>> packed = new ArrayList<>();
        // Order complete floors by size, but never reorder the sections of a split floor.
        floors.sort(Comparator.comparingDouble(Floor::height).reversed().thenComparing(Comparator.comparingDouble(Floor::width).reversed()));
        for (Floor floor : floors) {
            if (width < 2 * RADIUS + 2 * PADDING || height < FLAT_TO_FLAT + 2 * PADDING + CAPTION) {
                throw new IllegalArgumentException("The printable buildingTemplate area must fit one full-size hex and its level caption");
            }
            List<Floor> sections = placementIn(List.of(), floor, width, height, true) != null ? List.of(floor)
                  : split(floor(floor.level(), floor.hexes().stream().sorted(HEX_ORDER).toList(), true),
                        width, height, new HashMap<>(), true).pieces().stream()
                        .sorted(Comparator.comparing(part -> part.hexes().getFirst(), HEX_ORDER)).toList();
            placeSections(packed, sections, width, height);
        }
        pages = packed.stream().map(page -> {
            var ink = page.stream().flatMap(p -> occupied(p).stream()).toList();
            double left = ink.stream().mapToDouble(Rectangle2D::getX).min().orElse(0);
            double top = ink.stream().mapToDouble(Rectangle2D::getY).min().orElse(0);
            double right = ink.stream().mapToDouble(Rectangle2D::getMaxX).max().orElse(0);
            double bottom = ink.stream().mapToDouble(Rectangle2D::getMaxY).max().orElse(0);
            return page.stream().map(p -> new Placement(p.floor(), p.x() + (width - right - left) / 2,
                  p.y() + (height - bottom - top) / 2, p.caption())).toList();
        }).toList();
    }

    List<List<Placement>> pages() { return pages; }

    static double centerX(CubeCoords hex) { return 1.5 * RADIUS * hex.q(); }
    static double centerY(CubeCoords hex) { return FLAT_TO_FLAT * (hex.r() + hex.q() / 2.0); }

    private Floor floor(int level, List<CubeCoords> hexes, boolean section) {
        String caption = "Level: " + building.getLevelLabel(level, true);
        if (section) {
            var labels = hexes.stream().map(grid::label).sorted().toList();
            caption += " · " + labels.getFirst() + (labels.size() > 1 ? "–" + labels.getLast() : "");
        }
        return new Floor(level, List.copyOf(hexes), bounds(hexes), caption);
    }

    private record Plan(List<Floor> pieces, int cuts) { }

    /** Compare whole-floor cuts before packing: fewest connected pieces, then page count and severed hex edges. */
    private Plan split(Floor source, double width, double height, Map<List<CubeCoords>, Plan> plans, boolean wholeFloor) {
        var hexes = source.hexes();
        if (plans.containsKey(hexes)) { return plans.get(hexes); }
        Plan best;
        var components = components(hexes);
        if (components.size() > 1) {
            var children = components.stream().map(part -> split(floor(source.level(), part, true), width, height, plans, false)).toList();
            best = new Plan(children.stream().flatMap(child -> child.pieces().stream()).toList(), children.stream().mapToInt(Plan::cuts).sum());
        } else if (placementIn(List.of(), source, width, height, true) != null) {
            best = new Plan(List.of(source), 0);
        } else {
            best = new Plan(hexes.stream().map(hex -> floor(source.level(), List.of(hex), true)).toList(), Integer.MAX_VALUE);
            int bestPages = Integer.MAX_VALUE;
            int columnsPerPiece = (int) Math.floor((width - 2 * PADDING - 2 * RADIUS) / (1.5 * RADIUS)) + 1;
            int minimumPieces = Math.max(2, (int) Math.ceil(hexes.stream().mapToDouble(CubeCoords::q).distinct().count() / (double) columnsPerPiece));
            // Beyond the local budget, solve subproblems with full-page bands; still compare all whole-floor cuts.
            boolean bounded = !wholeFloor && plans.size() >= CUT_SEARCH_STATES;
            ToDoubleFunction<CubeCoords> horizontal = CubeCoords::q, vertical = hex -> 2 * hex.r() + hex.q();
            var axes = bounded ? List.of(source.width() > width ? horizontal : vertical)
                  : List.of(horizontal, vertical, (ToDoubleFunction<CubeCoords>) CubeCoords::r, CubeCoords::s);
            search: for (var coordinate : axes) {
                var positions = hexes.stream().mapToDouble(coordinate).distinct().sorted().toArray();
                for (int index = positions.length - 2; index >= 0; index--) {
                    double cut = positions[index];
                    double span = coordinate == horizontal ? columnsPerPiece - 1 : (height - 2 * PADDING - FLAT_TO_FLAT) / (FLAT_TO_FLAT / 2);
                    if (bounded && index > 0 && cut > positions[0] + span) { continue; }
                    var left = hexes.stream().filter(hex -> coordinate.applyAsDouble(hex) <= cut).toList();
                    var right = hexes.stream().filter(hex -> coordinate.applyAsDouble(hex) > cut).toList();
                    Plan a = split(floor(source.level(), left, true), width, height, plans, false);
                    Plan b = split(floor(source.level(), right, true), width, height, plans, false);
                    var pieces = new ArrayList<>(a.pieces());
                    pieces.addAll(b.pieces());
                    if (pieces.size() > best.pieces().size()) { continue; }
                    pieces.sort(Comparator.comparing(part -> part.hexes().getFirst(), HEX_ORDER));
                    var rightHexes = new HashSet<>(right);
                    int cuts = a.cuts() + b.cuts() + (int) left.stream().flatMap(hex -> hex.neighbors().stream()).filter(rightHexes::contains).count();
                    List<List<Placement>> candidatePages = new ArrayList<>();
                    if (wholeFloor) { placeSections(candidatePages, pieces, width, height); }
                    int smallest = pieces.stream().mapToInt(piece -> piece.hexes().size()).min().orElseThrow();
                    int bestSmallest = best.pieces().stream().mapToInt(piece -> piece.hexes().size()).min().orElseThrow();
                    if (pieces.size() < best.pieces().size() || candidatePages.size() < bestPages
                          || (candidatePages.size() == bestPages && (smallest > bestSmallest || (smallest == bestSmallest && cuts < best.cuts())))) {
                        best = new Plan(pieces, cuts);
                        bestPages = candidatePages.size();
                    }
                    // A connected floor needs at least one severed edge per additional piece.
                    if (best.pieces().size() == minimumPieces && best.cuts() == minimumPieces - 1
                          && best.pieces().stream().mapToInt(piece -> piece.hexes().size()).min().orElseThrow() == hexes.size() / minimumPieces
                          && (!wholeFloor || bestPages == 1)) { break search; }
                    if (bounded) { break; }
                }
            }
        }
        // Different cut sequences reach the same subset; this memo belongs only to the current floor.
        plans.put(hexes, best);
        return best;
    }

    private static List<List<CubeCoords>> components(List<CubeCoords> hexes) {
        var remaining = new LinkedHashSet<>(hexes);
        List<List<CubeCoords>> result = new ArrayList<>();
        while (!remaining.isEmpty()) {
            List<CubeCoords> part = new ArrayList<>();
            part.add(remaining.getFirst());
            remaining.remove(part.getFirst());
            for (int i = 0; i < part.size(); i++) {
                for (CubeCoords neighbor : part.get(i).neighbors()) {
                    if (remaining.remove(neighbor)) { part.add(neighbor); }
                }
            }
            part.sort(HEX_ORDER);
            result.add(part);
        }
        return result;
    }

    private static List<Rectangle2D> footprint(Floor floor) {
        List<Rectangle2D> result = new ArrayList<>();
        for (CubeCoords hex : floor.hexes()) {
            double x = PADDING - floor.bounds().getX() + centerX(hex);
            double y = PADDING - floor.bounds().getY() + centerY(hex);
            result.add(new Rectangle2D.Double(x - RADIUS - PADDING, y - FLAT_TO_FLAT / 2 - PADDING,
                  2 * RADIUS + 2 * PADDING, FLAT_TO_FLAT + 2 * PADDING));
        }
        return result;
    }

    private static List<Rectangle2D> occupied(Placement placement) {
        var result = footprint(placement.floor());
        result.add(placement.caption());
        return result.stream().map(box -> (Rectangle2D) new Rectangle2D.Double(box.getX() + placement.x(), box.getY() + placement.y(),
              box.getWidth(), box.getHeight())).toList();
    }

    /** Captions can occupy empty corners above, below or beside the outline, rather than a full-width header. */
    private static List<Rectangle2D> captionSpots(Floor floor, List<Rectangle2D> ink) {
        double width = Math.min(floor.width(), floor.caption().length() * 6.0);
        List<Rectangle2D> spots = new ArrayList<>();
        for (Rectangle2D box : ink) {
            double x = box.getX() + (box.getWidth() - width) / 2;
            // Half a hex of extra clearance can clear the neighboring column's staggered edge.
            for (double clearance : new double[] { GAP, GAP + FLAT_TO_FLAT / 2 }) {
                spots.add(new Rectangle2D.Double(x, box.getY() - CAPTION - clearance, width, CAPTION));
                spots.add(new Rectangle2D.Double(box.getX() - width - clearance, box.getY(), width, CAPTION));
                spots.add(new Rectangle2D.Double(box.getMaxX() + clearance, box.getY(), width, CAPTION));
                spots.add(new Rectangle2D.Double(x, box.getMaxY() + clearance, width, CAPTION));
            }
        }
        return spots.stream().filter(box -> ink.stream().noneMatch(box::intersects))
              .sorted(Comparator.comparingDouble(Rectangle2D::getY).thenComparingDouble(Rectangle2D::getX)).toList();
    }

    private static Rectangle2D bounds(List<CubeCoords> hexes) {
        var x = hexes.stream().mapToDouble(BuildingTemplateLayout::centerX).summaryStatistics();
        var y = hexes.stream().mapToDouble(BuildingTemplateLayout::centerY).summaryStatistics();
        return new Rectangle2D.Double(x.getMin() - RADIUS, y.getMin() - FLAT_TO_FLAT / 2,
              x.getMax() - x.getMin() + 2 * RADIUS, y.getMax() - y.getMin() + FLAT_TO_FLAT);
    }

    /** Search empty space around actual hexes and captions, keeping successive sections in reading order. */
    private static Placement placementIn(List<Placement> page, Floor floor, double width, double height, boolean firstFit) {
        Placement previous = page.isEmpty() ? null : page.getLast();
        var other = page.stream().flatMap(p -> occupied(p).stream()).toList();
        double maxX = width - floor.width(), maxY = height - floor.height();
        if (maxX < 0 || maxY < 0) { return null; }
        var ink = footprint(floor);
        var captions = captionSpots(floor, ink);
        var first = ink.getFirst();
        double bottom = other.stream().mapToDouble(Rectangle2D::getMaxY).max().orElse(0);
        Placement best = null;
        double bestScore = Double.POSITIVE_INFINITY;
        // Include the page edges as well as the 12-point search grid for near-exact fits.
        for (double y = previous == null ? 0 : previous.y(); y <= maxY; y = y < maxY ? Math.min(y + GAP, maxY) : Double.POSITIVE_INFINITY) {
            if (y + floor.height() > bestScore) { break; }
            for (double x = 0; x <= maxX; x = x < maxX ? Math.min(x + GAP, maxX) : Double.POSITIVE_INFINITY) {
                if (previous != null && y == previous.y() && x < previous.x()) { continue; }
                boolean overlaps = false;
                for (Rectangle2D box : ink) {
                    for (Rectangle2D rect : other) {
                        if (rect.intersects(x + box.getX(), y + box.getY(), box.getWidth(), box.getHeight())) {
                            overlaps = true;
                            break;
                        }
                    }
                    if (overlaps) { break; }
                }
                if (overlaps) { continue; }
                for (Rectangle2D caption : captions) {
                    var rect = new Rectangle2D.Double(x + caption.getX(), y + caption.getY(), caption.getWidth(), caption.getHeight());
                    if (rect.getX() < 0 || rect.getY() < 0 || rect.getMaxX() > width || rect.getMaxY() > height) { continue; }
                    if (previous != null && (rect.getY() < previous.y() + previous.caption().getY()
                          || (rect.getY() == previous.y() + previous.caption().getY() && rect.getX() < previous.x() + previous.caption().getX()))) { continue; }
                    if (other.stream().anyMatch(rect::intersects)) { continue; }
                    if (firstFit) { return new Placement(floor, x, y, caption); }
                    // Keep captions near the section's start while compacting its occupied space.
                    double distance = Math.abs(caption.getCenterX() - first.getCenterX()) + Math.abs(caption.getY() - first.getY());
                    double score = Math.max(bottom, Math.max(y + floor.height(), rect.getMaxY())) + distance / 4;
                    if (score < bestScore) { best = new Placement(floor, x, y, caption); bestScore = score; }
                }
            }
        }
        return best;
    }

    private static void placeSections(List<List<Placement>> pages, List<Floor> sections, double width, double height) {
        for (Floor floor : sections) {
            Placement placement = pages.isEmpty() ? null : placementIn(pages.getLast(), floor, width, height, false);
            if (placement == null) {
                var first = placementIn(List.of(), floor, width, height, false);
                if (first == null) { throw new IllegalArgumentException("The template page must fit one full-size hex and its level caption"); }
                pages.add(new ArrayList<>(List.of(first)));
            }
            else { pages.getLast().add(placement); }
        }
    }
}
