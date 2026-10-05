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

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import megamek.common.bays.FirstClassQuartersCargoBay;
import megamek.common.board.CubeCoords;
import megamek.common.enums.BuildingType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.BLKStructureFile;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.BuildingDesign;
import megamek.common.units.BuildingEntity;
import megamek.common.units.IBuilding;
import megamek.common.util.BuildingBlock;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.BuildingUtil;
import megameklab.util.UnitPrintManager;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGPolygonElement;
import org.w3c.dom.svg.SVGRectElement;

@ExtendWith(InitializeTypes.class)
class PrintBuildingTest {
    @Test
    void marksReciprocalCutEdgesWithoutMarkingExteriorWallsOrShorterNeighboringFloors() throws Exception {
        var labels = List.of("0102", "0103", "0201", "0202", "0203", "0204", "0302", "0303", "0304", "0305",
              "0401", "0402", "0403", "0404", "0502", "0503", "0504", "0604", "0705", "0805", "0906");
        var hexes = labels.stream().map(label -> {
            int q = Integer.parseInt(label.substring(0, 2)) - 1;
            int r = Integer.parseInt(label.substring(2)) - 1 - q / 2;
            return new CubeCoords(q, r, -q - r);
        }).toList();
        var building = BuildingUtil.newMobileStructure();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, hexes);
        var footprint = building.getInternalBuilding().getOriginalCoordsList();
        footprint.stream().filter(hex -> hex.q() >= 5).forEach(hex -> BuildingUtil.setHexHeight(building, hex, 1));
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(footprint.get(16), 20, Map.of(0, 4, 1, 0)));
        var grid = BuildingUtil.sheetGrid(footprint);
        for (var paper : PaperSize.values()) {
            var sheet = sheet(building, paper);
            var sections = new java.util.HashMap<String, Integer>();
            var actual = new java.util.HashSet<String>();
            int section = 0;
            for (int page = sheet.getRecordPageCount(); page < sheet.getPageCount(); page++) {
                assertTrue(sheet.createDocument(page, pageFormat(paper), true));
                assertTemplateGeometry(sheet, pageFormat(paper));
                for (var floor : elements(sheet, "g", "building-template-floor")) {
                    String level = floor.getAttribute("data-building-floor");
                    var polygons = floor.getElementsByTagName("polygon");
                    for (int i = 0; i < polygons.getLength(); i++) {
                        var polygon = (Element) polygons.item(i);
                        if (polygon.getAttribute("class").equals("building-template-hex")) {
                            sections.put(level + "/" + polygon.getAttribute("data-building-hex"), section);
                        }
                    }
                    var groups = floor.getElementsByTagName("g");
                    for (int i = 0; i < groups.getLength(); i++) {
                        var marker = (Element) groups.item(i);
                        if (!marker.getAttribute("class").equals("building-template-continuation")) { continue; }
                        assertEquals("0", level, "The smaller upper floor is intact; its missing neighbors are exterior");
                        assertTrue(actual.add(level + "/" + marker.getAttribute("data-continuation-from") + "/"
                              + marker.getAttribute("data-continuation-to")));
                        var reference = (org.w3c.dom.svg.SVGLocatable) marker.getElementsByTagName("text").item(0);
                        for (int j = 0; j < polygons.getLength(); j++) {
                            var polygon = (Element) polygons.item(j);
                            if (polygon.getAttribute("data-building-symbol").equals("elevator-door")) {
                                assertFalse(templateBounds(reference).intersects(templateBounds((org.w3c.dom.svg.SVGLocatable) polygon)),
                                      "Continuation references must clear elevator arrows");
                            }
                        }
                    }
                    section++;
                }
                render(sheet, "building-continuations-" + paper.name() + "-" + page);
            }
            var expected = new java.util.HashSet<String>();
            for (int level : BuildingConstruction.mapLevels(building)) {
                for (var hex : footprint) {
                    var from = level + "/" + grid.label(hex);
                    if (!sections.containsKey(from)) { continue; }
                    for (var neighbor : hex.neighbors()) {
                        var to = level + "/" + grid.label(neighbor);
                        if (sections.containsKey(to) && !sections.get(from).equals(sections.get(to))) {
                            expected.add(from + "/" + grid.label(neighbor));
                        }
                    }
                }
            }
            assertFalse(expected.isEmpty());
            assertEquals(expected, actual, "Each cut connection must be marked at both ends, including across pages");
        }
    }

    @Test
    void minimizesConnectedPiecesAndNestsThemInReadingOrderWithFlexibleCaptions() throws Exception {
        var labels = List.of("0102", "0103", "0201", "0202", "0203", "0204", "0302", "0303", "0304", "0401", "0402", "0403",
              "0502", "0503", "0504", "0505", "0506", "0602", "0603", "0604", "0703", "0705", "0805", "0906", "1006", "1107",
              "1207", "1308", "1408", "1509", "1609", "1710", "1810", "1911");
        var branched = labels.stream().map(label -> {
            int q = Integer.parseInt(label.substring(0, 2)) - 1;
            int r = Integer.parseInt(label.substring(2)) - 1 - q / 2;
            return new CubeCoords(q, r, -q - r);
        }).toList();
        var corridor = java.util.stream.IntStream.range(0, 11).mapToObj(q -> new CubeCoords(q, 0, -q)).toList();
        for (var hexes : List.of(corridor, branched)) {
            var building = BuildingUtil.newBuilding();
            BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 1, 80, 0, hexes);
            var grid = BuildingUtil.sheetGrid(hexes);
            var coordinates = hexes.stream().collect(java.util.stream.Collectors.toMap(grid::label, hex -> hex));
            for (var paper : PaperSize.values()) {
                var sheet = sheet(building, paper);
                assertEquals(hexes == corridor ? 1 : 2, sheet.getPageCount() - sheet.getRecordPageCount());
                var seen = new ArrayList<String>();
                var starts = new ArrayList<String>();
                int pieces = 0;
                for (int page = sheet.getRecordPageCount(); page < sheet.getPageCount(); page++) {
                    assertTrue(sheet.createDocument(page, pageFormat(paper), true));
                    assertTemplateGeometry(sheet, pageFormat(paper));
                    double previousY = Double.NEGATIVE_INFINITY;
                    for (var floor : elements(sheet, "g", "building-template-floor")) {
                        pieces++;
                        var cells = new ArrayList<String>();
                        var polygons = floor.getElementsByTagName("polygon");
                        for (int i = 0; i < polygons.getLength(); i++) {
                            cells.add(((Element) polygons.item(i)).getAttribute("data-building-hex"));
                        }
                        assertTrue(cells.size() > 1);
                        var pending = new java.util.HashSet<>(cells.stream().map(coordinates::get).toList());
                        var connected = new ArrayList<CubeCoords>();
                        connected.add(pending.iterator().next());
                        pending.remove(connected.getFirst());
                        for (int i = 0; i < connected.size(); i++) {
                            for (var neighbor : connected.get(i).neighbors()) { if (pending.remove(neighbor)) { connected.add(neighbor); } }
                        }
                        assertTrue(pending.isEmpty(), "Every printed section stays connected");
                        seen.addAll(cells);
                        starts.add(cells.stream().sorted().findFirst().orElseThrow());
                        var caption = (org.w3c.dom.svg.SVGLocatable) floor.getFirstChild();
                        double y = caption.getCTM().getF() + caption.getBBox().getY() * caption.getCTM().getD();
                        assertTrue(y >= previousY, "Section captions retain reading order");
                        previousY = y;
                    }
                    render(sheet, "building-" + (hexes == corridor ? "paint" : "branches") + "-" + paper.name() + "-" + page);
                }
                assertEquals(hexes == corridor ? 2 : 4, pieces);
                assertEquals(starts.stream().sorted().toList(), starts);
                assertEquals(coordinates.keySet().stream().sorted().toList(), seen.stream().sorted().toList());
            }
        }
    }

    @Test
    void rejoinsTheRemainingCorridorByPuttingItsCaptionBesideTheEarlierSection() throws Exception {
        var building = BuildingUtil.newBuilding();
        var labels = List.of("0102", "0103", "0201", "0202", "0203", "0204", "0302", "0303", "0304", "0305",
              "0401", "0402", "0403", "0404", "0502", "0503", "0504", "0604", "0605", "0606", "0704", "0705",
              "0803", "0805", "0906", "1006", "1107", "1207", "1308", "1408", "1509", "1609");
        var hexes = labels.stream().map(label -> {
            int q = Integer.parseInt(label.substring(0, 2)) - 1;
            int r = Integer.parseInt(label.substring(2)) - 1 - q / 2;
            return new CubeCoords(q, r, -q - r);
        }).toList();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 1, 80, 0, hexes);
        for (var paper : PaperSize.values()) {
            var sheet = sheet(building, paper);
            assertEquals(2, sheet.getPageCount() - sheet.getRecordPageCount());
            assertTrue(sheet.createDocument(sheet.getPageCount() - 1, pageFormat(paper), true));
            var floors = elements(sheet, "g", "building-template-floor");
            assertEquals(2, floors.size());
            var tail = List.of("1308", "1408", "1509", "1609");
            var polygons = floors.getLast().getElementsByTagName("polygon");
            var actual = new ArrayList<String>();
            for (int i = 0; i < polygons.getLength(); i++) {
                actual.add(((Element) polygons.item(i)).getAttribute("data-building-hex"));
            }
            assertTrue(actual.containsAll(tail), "Keep the final four hexes together even when an earlier hex can join them");
            assertTemplateGeometry(sheet, pageFormat(paper));
            render(sheet, "building-rejoined-tail-" + paper.name());
        }
    }

    @Test
    void splitsTheLongBranchedFootprintIntoConnectedOrderedSectionsUsingTwoTemplatePages() throws Exception {
        var building = BuildingUtil.newBuilding();
        var labels = List.of("0101", "0201", "0202", "0301", "0302", "0303", "0401", "0402", "0403", "0404",
              "0502", "0503", "0504", "0601", "0602", "0603", "0604", "0703", "0704", "0804", "0905", "1005",
              "1106", "1206", "1307", "1407", "1508", "1608", "1709", "1809", "1910", "2010");
        var hexes = labels.stream().map(label -> {
            int q = Integer.parseInt(label.substring(0, 2)) - 1;
            int r = Integer.parseInt(label.substring(2)) - 1 - q / 2;
            return new CubeCoords(q, r, -q - r);
        }).toList();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 1, 80, 0, hexes);
        for (var paper : PaperSize.values()) {
            var sheet = sheet(building, paper);
            assertEquals(2, sheet.getPageCount() - sheet.getRecordPageCount());
            var seen = new java.util.HashSet<String>();
            String previous = "";
            for (int page = sheet.getRecordPageCount(); page < sheet.getPageCount(); page++) {
                assertTrue(sheet.createDocument(page, pageFormat(paper), true));
                assertTemplateGeometry(sheet, pageFormat(paper));
                for (var floor : elements(sheet, "g", "building-template-floor")) {
                    var cells = new ArrayList<CubeCoords>();
                    var polygons = floor.getElementsByTagName("polygon");
                    var cellLabels = new ArrayList<String>();
                    for (int i = 0; i < polygons.getLength(); i++) {
                        var polygon = (Element) polygons.item(i);
                        if (!polygon.getAttribute("class").equals("building-template-hex")) { continue; }
                        String label = polygon.getAttribute("data-building-hex");
                        assertTrue(seen.add(label));
                        cellLabels.add(label);
                        cells.add(hexes.get(labels.indexOf(label)));
                    }
                    String first = cellLabels.stream().sorted().findFirst().orElseThrow();
                    assertTrue(previous.compareTo(first) < 0, "Sections remain in spatial order across pages");
                    previous = first;
                    var connected = new java.util.HashSet<CubeCoords>();
                    connected.add(cells.getFirst());
                    while (connected.size() < cells.size()) {
                        var next = cells.stream().filter(hex -> !connected.contains(hex)
                              && connected.stream().anyMatch(other -> other.distanceTo(hex) == 1)).findFirst();
                        assertTrue(next.isPresent(), "Each section is connected");
                        connected.add(next.orElseThrow());
                    }
                }
                render(sheet, "building-connected-sections-" + paper.name() + "-" + page);
            }
            assertEquals(new java.util.HashSet<>(labels), seen);
        }
    }

    @Test
    void centersPackedFloorplansInBothDirectionsWithoutScalingThem() {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO));
        for (double width : new double[] { 300, 560 }) {
            var layout = new BuildingTemplateLayout(building, width, 700);
            assertEquals(1, layout.pages().size());
            var page = layout.pages().getFirst();
            double left = page.stream().mapToDouble(p -> p.x() + Math.min(0, p.caption().getX())).min().orElseThrow();
            double right = page.stream().mapToDouble(p -> p.x() + Math.max(p.floor().width(), p.caption().getMaxX())).max().orElseThrow();
            double top = page.stream().mapToDouble(p -> p.y() + Math.min(0, p.caption().getY())).min().orElseThrow();
            double bottom = page.stream().mapToDouble(p -> p.y() + Math.max(p.floor().height(), p.caption().getMaxY())).max().orElseThrow();
            assertEquals(width / 2, (left + right) / 2, .001);
            assertEquals(350, (top + bottom) / 2, .001);
        }
    }

    @Test
    void packsSmallerUpperFloorsBesideTallGroundFloors() throws Exception {
        var building = BuildingUtil.newMobileStructure();
        var hexes = java.util.stream.IntStream.range(0, 6).mapToObj(r -> new CubeCoords(0, r, -r)).toList();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, hexes);
        hexes.stream().skip(1).forEach(hex -> BuildingUtil.setHexHeight(building, hex, 1));
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertEquals(2, sheet.getPageCount(), "Both floors fit side by side on one template page");
        assertTrue(sheet.createDocument(1, pageFormat(PaperSize.US_LETTER), true));
        assertEquals(2, elements(sheet, "g", "building-template-floor").size());
        assertTemplateGeometry(sheet, pageFormat(PaperSize.US_LETTER));
        render(sheet, "building-packed-variable-floors");
    }

    @Test
    void retainedInvalidLinkedDoorsDoNotCrashPrinting() throws Exception {
        var building = BuildingUtil.newBuilding();
        var northeast = new CubeCoords(1, -1, 0);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 1, 80, 0, List.of(CubeCoords.ZERO, northeast));
        // The latter two invalid doors share an internal edge, so their centers coincide.
        building.getDesign().getDoors().addAll(List.of(
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 0, 1, 1),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 1, 1),
              new BuildingDesign.Door(new BuildingDesign.Position(northeast, 0), 4, 1, 1)));
        var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(building)).getEntity();
        var sheet = sheet(loaded, PaperSize.US_LETTER);
        assertEquals(2, sheet.getPageCount());
        for (int page = 0; page < sheet.getPageCount(); page++) {
            assertTrue(sheet.createDocument(page, pageFormat(PaperSize.US_LETTER), true));
            assertNotNull(sheet.build());
        }
    }

    @Test
    void templateScaleAndMarginsAreIndependentOfDecimalLocaleAndOrientation() {
        var previous = java.util.Locale.getDefault();
        try {
            java.util.Locale.setDefault(java.util.Locale.GERMANY);
            for (int orientation : new int[] { PageFormat.PORTRAIT, PageFormat.LANDSCAPE }) {
                var format = pageFormat(PaperSize.US_LETTER);
                format.setOrientation(orientation);
                var options = new RecordSheetOptions();
                options.setPaperSize(PaperSize.US_LETTER);
                var sheet = new PrintBuilding(BuildingUtil.newBuilding(), 3, options, format) {
                    @Override
                    String getSVGDirectoryName(boolean testDirectory) {
                        return "../../mm-data/data/images/recordsheets/templates_us";
                    }
                };
                assertTrue(sheet.createDocument(3 + sheet.getRecordPageCount(), format, true));
                assertTemplateGeometry(sheet, format);
            }
        } finally {
            java.util.Locale.setDefault(previous);
        }
    }

    @Test
    void appendsPackedFullScaleTemplatesForEveryLevelOnBothPaperSizes() throws Exception {
        var building = BuildingUtil.newBuilding();
        building.setChassis("Reinforced Weapons Bunker");
        building.setModel("");
        var south = new CubeCoords(0, 1, -1);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 0, List.of(CubeCoords.ZERO, south));
        building.getDesign().setBaseLevel(-1);
        building.getDesign().getDoors().addAll(List.of(
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 2, 1),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 2, 2, 1)));
        for (var paper : PaperSize.values()) {
            var sheet = sheet(building, paper);
            assertEquals(2, sheet.getPageCount());
            assertTrue(sheet.createDocument(0, pageFormat(paper), true));
            assertEquals("STRUCTURE RECORD SHEET", sheet.getSVGDocument().getElementById("title").getTextContent());
            assertTrue(sheet.createDocument(1, pageFormat(paper), true));
            assertEquals("REINFORCED WEAPONS BUNKER TEMPLATE", sheet.getSVGDocument().getElementById("title").getTextContent());
            assertNull(sheet.getSVGDocument().getElementById("pageNumber"));
            var floors = elements(sheet, "g", "building-template-floor");
            assertEquals(List.of("2", "1", "0"), floors.stream().map(e -> e.getAttribute("data-building-floor")).toList());
            assertTrue(floors.getFirst().getTextContent().startsWith("Level: 1"));
            assertTrue(floors.getLast().getTextContent().startsWith("Level: -1"));
            assertEquals(6, elements(sheet, "polygon", "building-template-hex").size());
            assertEquals(4, elements(sheet, "polyline", "linked-door-opening").size());
            assertTemplateGeometry(sheet, pageFormat(paper));
            render(sheet, "building-full-scale-" + paper.name());
            if (paper == PaperSize.US_LETTER) {
                Path output = Path.of("build", "building-review", "building-full-scale.pdf");
                try (var pdf = sheet.exportPDF(1, pageFormat(paper))) {
                    assertNotNull(pdf);
                    Files.copy(pdf, output, StandardCopyOption.REPLACE_EXISTING);
                }
                try (var pdf = org.apache.pdfbox.Loader.loadPDF(output.toFile())) {
                    assertEquals(1, pdf.getNumberOfPages());
                    assertEquals(612, pdf.getPage(0).getMediaBox().getWidth(), .01);
                    assertEquals(792, pdf.getPage(0).getMediaBox().getHeight(), .01);
                }
            }
        }
    }

    @Test
    void mobileTemplatesKeepOnlyOccupiedFloorsAndUsePrinterMarginsWithoutShrinkingHexes() throws Exception {
        var mobile = BuildingUtil.newMobileStructure();
        var hexes = List.copyOf(mobile.getInternalBuilding().getOriginalCoordsList());
        BuildingUtil.configure(mobile, BuildingType.MEDIUM, IBuilding.FORTRESS, 3, 40, 0, hexes);
        BuildingUtil.setHexHeight(mobile, hexes.get(1), 1);
        PageFormat format = new PageFormat();
        format.setPaper(PaperSize.US_LETTER.createPaper(36, 40, 36, 40));
        var options = new RecordSheetOptions();
        options.setPaperSize(PaperSize.US_LETTER);
        var sheet = new PrintBuilding(mobile, 5, options, format) {
            @Override
            String getSVGDirectoryName(boolean testDirectory) {
                return "../../mm-data/data/images/recordsheets/templates_us";
            }
        };
        var cells = new ArrayList<String>();
        for (int page = sheet.getRecordPageCount(); page < sheet.getPageCount(); page++) {
            assertTrue(sheet.createDocument(page + 5, format, true));
            assertEquals((mobile.getShortNameRaw() + " TEMPLATE").toUpperCase(java.util.Locale.ROOT),
                  sheet.getSVGDocument().getElementById("title").getTextContent());
            for (var floor : elements(sheet, "g", "building-template-floor")) {
                var polygons = floor.getElementsByTagName("polygon");
                for (int i = 0; i < polygons.getLength(); i++) {
                    var polygon = (Element) polygons.item(i);
                    if (polygon.getAttribute("class").equals("building-template-hex")) {
                        cells.add(floor.getAttribute("data-building-floor") + "/" + polygon.getAttribute("data-building-hex"));
                    }
                }
            }
            assertTemplateGeometry(sheet, format);
            render(sheet, "mobile-full-scale-margins-" + page);
        }
        assertEquals(4, cells.size());
        assertEquals(4, new java.util.HashSet<>(cells).size());
    }

    @Test
    void oversizedFloorplansSplitAcrossPagesWithoutDroppingOrDuplicatingHexes() throws Exception {
        var building = BuildingUtil.newBuilding();
        var hexes = java.util.stream.IntStream.range(0, 24)
              .mapToObj(i -> new CubeCoords(i % 8 - 10, i / 8 - 10, 20 - i % 8 - i / 8)).toList();
        BuildingUtil.configure(building, BuildingType.HARDENED, IBuilding.CASTLE_BRIAN, 2, 100, 0, hexes);
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.getPageCount() > sheet.getRecordPageCount() + 1);
        var seen = new java.util.HashSet<String>();
        int count = 0;
        for (int page = sheet.getRecordPageCount(); page < sheet.getPageCount(); page++) {
            assertTrue(sheet.createDocument(page, pageFormat(PaperSize.US_LETTER), true));
            for (var floor : elements(sheet, "g", "building-template-floor")) {
                assertTrue(floor.getTextContent().contains(" · "));
                var polygons = floor.getElementsByTagName("polygon");
                for (int i = 0; i < polygons.getLength(); i++) {
                    var polygon = (Element) polygons.item(i);
                    if (polygon.getAttribute("class").equals("building-template-hex")) {
                        assertTrue(seen.add(floor.getAttribute("data-building-floor") + "/" + polygon.getAttribute("data-building-hex")));
                        count++;
                    }
                }
            }
            assertTemplateGeometry(sheet, pageFormat(PaperSize.US_LETTER));
            if (page == sheet.getRecordPageCount()) {
                render(sheet, "building-full-scale-section");
            }
        }
        assertEquals(48, count);
    }

    private void assertTemplateGeometry(PrintBuilding sheet, PageFormat format) {
        assertNotNull(sheet.build());
        var area = (SVGRectElement) sheet.getSVGDocument().getElementById("buildingTemplate");
        var areaMatrix = area.getCTM();
        double left = areaMatrix.getE() + area.getX().getBaseVal().getValue() * areaMatrix.getA();
        double top = areaMatrix.getF() + area.getY().getBaseVal().getValue() * areaMatrix.getD();
        double right = Math.min(left + area.getWidth().getBaseVal().getValue() * areaMatrix.getA(), format.getImageableX() + format.getImageableWidth());
        double bottom = Math.min(top + area.getHeight().getBaseVal().getValue() * areaMatrix.getD(), format.getImageableY() + format.getImageableHeight());
        for (var polygon : elements(sheet, "polygon", "building-template-hex")) {
            var hex = (SVGPolygonElement) polygon;
            var points = hex.getPoints();
            var matrix = hex.getCTM();
            assertEquals(90, (points.getItem(1).getY() - points.getItem(5).getY()) * matrix.getD(), .001,
                  "A hex is exactly 1.25 inches flat-to-flat in the final print transform");
            assertEquals(180 / Math.sqrt(3), (points.getItem(0).getX() - points.getItem(3).getX()) * matrix.getA(), .001);
        }
        var occupied = new ArrayList<java.awt.geom.Rectangle2D>();
        for (var element : elements(sheet, "g", "building-template-floor")) {
            var floor = (SVGGElement) element;
            var bounds = floor.getBBox();
            var matrix = floor.getCTM();
            var box = new java.awt.geom.Rectangle2D.Double(matrix.getE() + bounds.getX() * matrix.getA(),
                  matrix.getF() + bounds.getY() * matrix.getD(), bounds.getWidth() * matrix.getA(), bounds.getHeight() * matrix.getD());
            assertTrue(box.getMinX() >= left - .01 && box.getMaxX() <= right + .01);
            assertTrue(box.getMinY() >= top - .01 && box.getMaxY() <= bottom + .01);
            var drawn = new ArrayList<java.awt.geom.Rectangle2D>();
            var children = floor.getElementsByTagName("*");
            for (int i = 0; i < children.getLength(); i++) {
                var child = (Element) children.item(i);
                if (!(child instanceof org.w3c.dom.svg.SVGLocatable shape)
                      || !List.of("text", "polygon", "line", "polyline", "path").contains(child.getTagName())) { continue; }
                var ink = templateBounds(shape);
                assertTrue(occupied.stream().noneMatch(ink::intersects), "Hexes, doors and captions from different sections must not overlap");
                drawn.add(ink);
            }
            assertTrue(drawn.stream().skip(1).noneMatch(drawn.getFirst()::intersects), "A section's caption must also clear its own drawing");
            occupied.addAll(drawn);
        }
    }

    private java.awt.geom.Rectangle2D templateBounds(org.w3c.dom.svg.SVGLocatable shape) {
        var bounds = shape.getBBox();
        var matrix = shape.getCTM();
        return new java.awt.geom.AffineTransform(matrix.getA(), matrix.getB(), matrix.getC(), matrix.getD(), matrix.getE(), matrix.getF())
              .createTransformedShape(new java.awt.geom.Rectangle2D.Float(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()))
              .getBounds2D();
    }

    @Test
    void linkedDoorsDrawContinuousThickOpeningsOnEveryOccupiedFloor() throws Exception {
        var building = BuildingUtil.newBuilding();
        var south = new CubeCoords(0, 1, -1);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, List.of(CubeCoords.ZERO, south));
        building.getDesign().getDoors().addAll(List.of(
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 1, 2, 1),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 2, 2, 1),
              new BuildingDesign.Door(new BuildingDesign.Position(south, 0), 1, 2, 1)));
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertEquals(6, elements(sheet, "polyline", "linked-door-opening").size());
        var doors = elements(sheet, "g", "building-inventory-entry").stream()
              .filter(row -> row.getAttribute("data-equipment-id").equals("door")).toList();
        assertEquals(1, doors.size());
        assertEquals("0101-0102/G: Door E: 2 levels high", doors.getFirst().getTextContent());
        var key = elements(sheet, "g", "building-map-key").getFirst();
        assertEquals("Large Door", key.getTextContent());
        var largeDoor = (Element) key.getFirstChild();
        assertEquals("large-door", largeDoor.getAttribute("data-building-symbol"));
        assertEquals(2, largeDoor.getElementsByTagName("polygon").getLength());
        assertEquals(1, largeDoor.getElementsByTagName("line").getLength());
        assertTrue(elements(sheet, "polyline", "linked-door-opening").stream()
              .allMatch(line -> Double.parseDouble(line.getAttribute("stroke-width")) > 2));
        render(sheet, "building-linked-doors");
    }

    @Test
    void linkedDoorInventoryUsesUniqueHexesAndTheRenderedArrowDirectionPerOpening() throws Exception {
        var building = BuildingUtil.newBuilding();
        var east = new CubeCoords(1, 0, -1);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0,
              List.of(new CubeCoords(0, -2, 2), new CubeCoords(0, -1, 1), CubeCoords.ZERO, east));
        building.getDesign().getDoors().addAll(List.of(
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 4, 1, 7),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), 3, 1, 7),
              new BuildingDesign.Door(new BuildingDesign.Position(east, 0), 4, 1, 7),
              new BuildingDesign.Door(new BuildingDesign.Position(east, 0), 2, 2),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 1), 4, 1, 7),
              new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 1), 3, 1, 7)));
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        var doors = elements(sheet, "g", "building-inventory-entry").stream()
              .filter(row -> row.getAttribute("data-equipment-id").equals("door")).map(Element::getTextContent).toList();
        assertEquals(List.of("0103-0203/G: Door SW: 1 level high", "0203/G: Door SE: 2 levels high", "0103/1: Door SW: 1 level high"), doors);
        render(sheet, "building-linked-door-inventory");
    }

    @Test
    void fitsTheStandardGridBeforeMakingWideOrTallFootprintsDenserWithinTheMapArea() throws Exception {
        var tight = java.util.stream.IntStream.range(0, 14)
              .mapToObj(i -> new CubeCoords(i / 7, i % 7, -i / 7 - i % 7)).toList();
        var wide = java.util.stream.IntStream.range(0, 13)
              .mapToObj(q -> new CubeCoords(q, -q / 2, -q + q / 2)).toList();
        var tall = java.util.stream.IntStream.range(0, 12).mapToObj(r -> new CubeCoords(0, r, -r)).toList();
        var both = new ArrayList<>(wide.subList(0, 11));
        both.addAll(tall.subList(1, 9));
        var footprints = List.of(tight, wide, tall, both);
        int[] cells = { 9 * 7, 13 * 7, 9 * 12, 11 * 9 };
        for (var paper : List.of(PaperSize.US_LETTER, PaperSize.ISO_A4)) {
            double standardHexWidth = 0;
            for (int index = 0; index < footprints.size(); index++) {
                var hexes = footprints.get(index);
                var building = BuildingUtil.newBuilding();
                BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.CASTLE_BRIAN, 2, 40, 0, hexes);
                var mount = building.addEquipment(EquipmentType.get("ISMediumLaser"), (hexes.size() - 1) * 2);
                var sheet = sheet(building, paper);
                assertTrue(sheet.createDocument(0, pageFormat(paper), true));
                assertEquals(cells[index] * 2, elements(sheet, "polygon", "building-hex").size());
                assertEquals(hexes.size() * 2, elements(sheet, "polygon", "occupied").size());
                var region = (SVGRectElement) sheet.getSVGDocument().getElementById("structureMap");
                for (var element : elements(sheet, "g", "building-map-layer")) {
                    var layer = (SVGGElement) element;
                    var translation = layer.getTransform().getBaseVal().consolidate().getMatrix();
                    var polygons = layer.getElementsByTagName("polygon");
                    for (int i = 0; i < polygons.getLength(); i++) {
                        var points = ((SVGPolygonElement) polygons.item(i)).getPoints();
                        for (int j = 0; j < points.getNumberOfItems(); j++) {
                            double x = translation.getE() + points.getItem(j).getX();
                            double y = translation.getF() + points.getItem(j).getY();
                            assertTrue(x >= region.getX().getBaseVal().getValue() - .01);
                            assertTrue(x <= region.getX().getBaseVal().getValue() + region.getWidth().getBaseVal().getValue() + .01);
                            assertTrue(y >= region.getY().getBaseVal().getValue() - .01);
                            assertTrue(y <= region.getY().getBaseVal().getValue() + region.getHeight().getBaseVal().getValue() + .01);
                        }
                    }
                }
                var points = ((SVGPolygonElement) elements(sheet, "polygon", "building-hex").getFirst()).getPoints();
                double hexWidth = points.getItem(3).getX() - points.getItem(0).getX();
                if (index == 0) {
                    standardHexWidth = hexWidth;
                    assertTrue(elements(sheet, "g", "building-inventory-entry").getFirst().getTextContent().contains("0207/G"));
                } else {
                    assertTrue(hexWidth < standardHexWidth, "A larger grid uses smaller hexes in the same map area");
                }
                assertEquals(hexes, building.getInternalBuilding().getOriginalCoordsList());
                assertEquals((hexes.size() - 1) * 2, mount.getLocation());
                if (paper == PaperSize.US_LETTER && (index == 0 || index == 3)) {
                    render(sheet, "building-fit-grid-" + index);
                }
            }
        }
    }

    @Test
    void printsWallSidesAndOnlyTheActualBridgeDeckElevations() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.MEDIUM, IBuilding.WALL, 2, 40, 32, List.of(CubeCoords.ZERO));
        building.getDesign().getWallSides().put(CubeCoords.ZERO, 3);
        var wall = sheet(building, PaperSize.US_LETTER);
        assertTrue(wall.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertEquals(2, elements(wall, "g", "building-map-layer").size());
        assertTrue(wall.getSVGDocument().getDocumentElement().getTextContent().contains("0101/N"));
        assertTrue(wall.getSVGDocument().getDocumentElement().getTextContent().contains("0101/NE"));
        int sides = 0;
        var lines = wall.getSVGDocument().getElementsByTagName("line");
        for (int i = 0; i < lines.getLength(); i++) {
            if (((Element) lines.item(i)).hasAttribute("data-building-side")) {
                sides++;
            }
        }
        assertEquals(4, sides);
        render(wall, "wall-hexside-classification");
        var hexes = java.util.stream.IntStream.rangeClosed(0, 8).mapToObj(q -> new CubeCoords(q, 0, -q)).toList();
        BuildingUtil.configure(building, BuildingType.RAIL, IBuilding.BRIDGE, 1, 650, 0, hexes);
        BuildingConstruction.setBridgeSlope(building, 5, 7);
        var bridge = sheet(building, PaperSize.US_LETTER);
        assertTrue(bridge.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertEquals(List.of("7", "6", "5"), elements(bridge, "g", "building-map-layer").stream()
              .map(layer -> layer.getAttribute("data-building-floor")).toList());
        assertEquals(9, elements(bridge, "polygon", "occupied").size());
        render(bridge, "bridge-deck-classification");
    }

    @Test
    void largeCastleBrianKeepsEveryLevelAndProtectionRowReadableAcrossPages() throws Exception {
        var building = BuildingUtil.newBuilding();
        var hexes = java.util.stream.IntStream.range(0, 70).mapToObj(index ->
              new CubeCoords(index % 10, index / 10, -(index % 10) - index / 10)).toList();
        BuildingUtil.configure(building, BuildingType.HARDENED, IBuilding.CASTLE_BRIAN, 15, 150, 100, hexes);
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertEquals(3, sheet.getRecordPageCount());
        var levels = new ArrayList<String>();
        for (int page = 0; page < sheet.getRecordPageCount(); page++) {
            assertTrue(sheet.createDocument(page, pageFormat(PaperSize.US_LETTER), true));
            var layers = elements(sheet, "g", "building-map-layer");
            assertTrue(layers.size() <= 6);
            levels.addAll(layers.stream().map(layer -> layer.getAttribute("data-building-floor")).toList());
            render(sheet, "castle-brian-classification-" + page);
        }
        assertEquals(java.util.stream.IntStream.range(0, 15).mapToObj(index -> Integer.toString(14 - index)).toList(), levels);
    }

    private PrintBuilding sheet(megamek.common.units.AbstractBuildingEntity building, PaperSize size) {
        var options = new RecordSheetOptions();
        options.setPaperSize(size);
        options.setReferenceCharts(false);
        return new PrintBuilding(building, 0, options) {
            @Override
            String getSVGDirectoryName(boolean testDirectory) {
                // The actual mm-data assets are the source of truth for both test and release rendering.
                return "../../mm-data/data/images/recordsheets/" + size.dirName;
            }
        };
    }

    @Test
    void mobileSheetPreservesSpeedAndIndividualHexHeights() throws Exception {
        var mobile = BuildingUtil.newMobileStructure();
        var hexes = List.copyOf(mobile.getInternalBuilding().getOriginalCoordsList());
        BuildingUtil.configure(mobile, BuildingType.MEDIUM, IBuilding.FORTRESS, 3, 40, 0, hexes);
        BuildingUtil.setHexHeight(mobile, hexes.get(1), 1);
        mobile.setMaximumMP(1.25);
        var sheet = sheet(mobile, PaperSize.ISO_A4);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.ISO_A4), true));
        assertEquals(3, elements(sheet, "g", "building-map-layer").size());
        assertEquals(4, elements(sheet, "polygon", "occupied").size());
        assertTrue(sheet.getSVGDocument().getDocumentElement().getTextContent().contains("1.25"));
        render(sheet, "mobile-variable-height");
    }

    @Test
    void compressesBeforeOverflowAndKeepsAllEquipmentOnContinuationPages() throws Exception {
        var building = BuildingUtil.newBuilding();
        for (int index = 0; index < 30; index++) {
            building.addEquipment(printableItem(index), 0);
        }
        var compact = sheet(building, PaperSize.US_LETTER);
        assertEquals(1, compact.getRecordPageCount());
        assertTrue(compact.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        render(compact, "building-dense-inventory");
        for (int index = 30; index < 110; index++) {
            building.addEquipment(printableItem(index), 0);
        }
        var overflow = sheet(building, PaperSize.US_LETTER);
        assertTrue(overflow.getRecordPageCount() > 1);
        var seen = new ArrayList<String>();
        for (int page = 0; page < overflow.getRecordPageCount(); page++) {
            assertTrue(overflow.createDocument(page, pageFormat(PaperSize.US_LETTER), true));
            for (var entry : elements(overflow, "g", "building-inventory-entry")) {
                assertEquals(0, entry.getElementsByTagName("line").getLength(), "No ruled inventory placeholders");
                if (Integer.parseInt(entry.getAttribute("data-location")) >= 0) {
                    seen.add(entry.getAttribute("data-equipment-id"));
                }
            }
            render(overflow, "building-inventory-overflow-" + page);
        }
        assertEquals(110, seen.size());
        assertEquals(110, new java.util.HashSet<>(seen).size());
    }

    private MiscType printableItem(int index) {
        return new MiscType() {
            {
                name = "Equipment " + index;
                setInternalName(name);
                tonnage = 1;
                criticalSlots = 1;
            }
        };
    }

    @Test
    void projectedDoorsStayCenteredAndFeatureColorsRemainAlongsideSymbols() throws Exception {
        var building = BuildingUtil.newBuilding();
        for (int side = 0; side < 6; side++) {
            building.getDesign().getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(CubeCoords.ZERO, 0), side, 1));
        }
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        var hex = (SVGPolygonElement) elements(sheet, "polygon", "occupied").getFirst();
        var layer = elements(sheet, "g", "building-map-layer").getFirst();
        var polygons = layer.getElementsByTagName("polygon");
        int doors = 0;
        for (int index = 0; index < polygons.getLength(); index++) {
            var door = (SVGPolygonElement) polygons.item(index);
            if (!door.getAttribute("data-building-symbol").equals("door")) {
                continue;
            }
            doors++;
            int side = Integer.parseInt(door.getAttribute("data-building-facing"));
            var a = hex.getPoints().getItem((side + 1) % 6);
            var b = hex.getPoints().getItem((side + 2) % 6);
            var left = door.getPoints().getItem(1);
            var right = door.getPoints().getItem(2);
            var tip = door.getPoints().getItem(0);
            assertEquals((a.getX() + b.getX()) / 2, (tip.getX() + left.getX() + right.getX()) / 3, .01);
            assertEquals((a.getY() + b.getY()) / 2, (tip.getY() + left.getY() + right.getY()) / 3, .01);
            assertEquals(0, (right.getX() - left.getX()) * (b.getY() - a.getY())
                  - (right.getY() - left.getY()) * (b.getX() - a.getX()), .01);
        }
        assertEquals(6, doors);
        var keyPolygons = elements(sheet, "g", "building-map-key").getFirst().getElementsByTagName("polygon");
        assertEquals(1, keyPolygons.getLength(), "The Door legend has no hex swatch");
        assertEquals(3, ((SVGPolygonElement) keyPolygons.item(0)).getPoints().getNumberOfItems());
        render(sheet, "building-six-door-directions");
        building.getDesign().getDoors().clear();
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 4, 1, 4)));
        for (var mode : RecordSheetOptions.ColorMode.values()) {
            var colored = sheet(building, PaperSize.US_LETTER);
            colored.options.setColor(mode);
            assertTrue(colored.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
            assertTrue(elements(colored, "g", "building-map-layer").getFirst().getTextContent().contains("E0101"));
            var key = elements(colored, "g", "building-map-key").getFirst();
            assertTrue(key.getTextContent().contains("EElevator"));
            assertEquals("#efcb8d", elements(colored, "polygon", "occupied").getFirst().getAttribute("fill"));
            assertEquals("#efcb8d", ((Element) key.getElementsByTagName("polygon").item(0)).getAttribute("fill"));
            if (mode == RecordSheetOptions.ColorMode.LOGO_ONLY) {
                render(colored, "building-feature-colors");
            }
        }
    }

    @Test
    void elevatorDoorsPrintOnTheirConfiguredSidesAndLevelsIncludingContinuationPages() throws Exception {
        var building = BuildingUtil.newBuilding();
        var hexes = new ArrayList<>(CubeCoords.ZERO.neighbors());
        hexes.add(CubeCoords.ZERO);
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 7, 80, 0, hexes);
        building.getDesign().getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20,
              Map.of(0, 5, 2, 63, 6, 32, 7, 8)));
        var expectedSides = Map.of(0, List.of(0, 2), 2, List.of(0, 1, 2, 3, 4, 5), 6, List.of(5));
        String label = BuildingUtil.sheetGrid(hexes).label(CubeCoords.ZERO);
        for (var paper : List.of(PaperSize.US_LETTER, PaperSize.ISO_A4)) {
            var sheet = sheet(building, paper);
            for (int page = 0; page < 2; page++) {
                assertTrue(sheet.createDocument(page, pageFormat(paper), true));
                var hex = (SVGPolygonElement) elements(sheet, "polygon", "occupied").stream()
                      .filter(cell -> cell.getAttribute("data-building-hex").equals(label)).findFirst().orElseThrow();
                for (var layer : elements(sheet, "g", "building-map-layer")) {
                    int floor = Integer.parseInt(layer.getAttribute("data-building-floor"));
                    var sides = new ArrayList<Integer>();
                    var polygons = layer.getElementsByTagName("polygon");
                    for (int index = 0; index < polygons.getLength(); index++) {
                        var marker = (SVGPolygonElement) polygons.item(index);
                        if (!marker.getAttribute("data-building-symbol").equals("elevator-door")) {
                            continue;
                        }
                        int side = Integer.parseInt(marker.getAttribute("data-building-facing"));
                        sides.add(side);
                        assertEquals("#efcb8d", marker.getAttribute("fill"));
                        assertEquals(3, marker.getPoints().getNumberOfItems());
                        var a = hex.getPoints().getItem((side + 1) % 6);
                        var b = hex.getPoints().getItem((side + 2) % 6);
                        var tip = marker.getPoints().getItem(0);
                        var left = marker.getPoints().getItem(1);
                        var right = marker.getPoints().getItem(2);
                        assertEquals((a.getX() + b.getX()) / 2, (tip.getX() + left.getX() + right.getX()) / 3, .01);
                        assertEquals((a.getY() + b.getY()) / 2, (tip.getY() + left.getY() + right.getY()) / 3, .01);
                    }
                    assertEquals(expectedSides.getOrDefault(floor, List.of()), sides, "Access sides on level " + floor);
                }
                var key = elements(sheet, "g", "building-map-key").getFirst();
                assertTrue(key.getTextContent().contains("Elevator door"));
                var groups = key.getElementsByTagName("g");
                boolean found = false;
                for (int index = 0; index < groups.getLength(); index++) {
                    var group = (Element) groups.item(index);
                    if (group.getAttribute("data-building-symbol").equals("elevator-door")) {
                        var marker = (SVGPolygonElement) group.getElementsByTagName("polygon").item(0);
                        assertEquals(3, marker.getPoints().getNumberOfItems());
                        assertEquals("#efcb8d", marker.getAttribute("fill"));
                        found = true;
                    }
                }
                assertTrue(found, "The legend must include the amber elevator-door triangle");
                render(sheet, "building-elevator-doors-" + paper.name() + "-" + page);
            }
        }
    }

    private PageFormat pageFormat(PaperSize size) {
        Paper paper = new Paper();
        paper.setSize(size.pxWidth, size.pxHeight);
        paper.setImageableArea(18, 18, size.pxWidth - 36, size.pxHeight - 36);
        PageFormat format = new PageFormat();
        format.setPaper(paper);
        return format;
    }

    private List<Element> elements(PrintBuilding sheet, String tag, String className) {
        List<Element> result = new ArrayList<>();
        var nodes = sheet.getSVGDocument().getElementsByTagName(tag);
        for (int i = 0; i < nodes.getLength(); i++) {
            var element = (Element) nodes.item(i);
            if (List.of(element.getAttribute("class").split(" ")).contains(className)) {
                result.add(element);
            }
        }
        return result;
    }

    @Test
    void centersTheSameFootprintOnEveryFloorWithoutChangingLabelsOrAdjacency() throws Exception {
        var footprint = List.of(CubeCoords.ZERO, new CubeCoords(1, 0, -1),
              new CubeCoords(2, 0, -2), new CubeCoords(2, 1, -3));
        for (var origin : List.of(CubeCoords.ZERO, new CubeCoords(37, -51, 14))) {
            var translated = footprint.stream().map(hex -> new CubeCoords(
                  hex.q() + origin.q(), hex.r() + origin.r(), hex.s() + origin.s())).toList();
            for (var hexes : List.of(translated, translated.reversed())) {
                var building = BuildingUtil.newBuilding();
                BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 2, 80, 0, hexes);
                var originalHexes = List.copyOf(building.getInternalBuilding().getOriginalCoordsList());
                var sheet = sheet(building, PaperSize.US_LETTER);
                assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
                var layers = elements(sheet, "g", "building-map-layer");
                assertEquals(2, layers.size());
                for (var layer : layers) {
                    assertHexAt(layer, "0101", 3, 2);
                    assertHexAt(layer, "0201", 4, 3);
                    assertHexAt(layer, "0302", 5, 3);
                    assertHexAt(layer, "0303", 5, 4);
                    var polygons = layer.getElementsByTagName("polygon");
                    assertEquals(63, polygons.getLength());
                    var occupied = new ArrayList<SVGPolygonElement>();
                    for (int i = 0; i < polygons.getLength(); i++) {
                        if (((Element) polygons.item(i)).getAttribute("class").contains("occupied")) {
                            occupied.add((SVGPolygonElement) polygons.item(i));
                        }
                    }
                    assertEquals(4, occupied.size());
                    for (int i = 1; i < occupied.size(); i++) {
                        int shared = 0;
                        for (int a = 0; a < 6; a++) {
                            var point = occupied.get(i).getPoints().getItem(a);
                            for (int b = 0; b < 6; b++) {
                                var other = occupied.get(i - 1).getPoints().getItem(b);
                                if (Math.hypot(point.getX() - other.getX(), point.getY() - other.getY()) < .01) {
                                    shared++;
                                }
                            }
                        }
                        assertEquals(2, shared, "Adjacent hexes retain a shared edge after centering");
                    }
                }
                assertEquals(originalHexes, building.getInternalBuilding().getOriginalCoordsList());
                if (origin.equals(CubeCoords.ZERO) && hexes.equals(footprint)) {
                    render(sheet, "building-centered-footprint");
                }
            }
        }
    }

    private void assertHexAt(Element layer, String label, int column, int row) {
        var polygons = layer.getElementsByTagName("polygon");
        var first = ((SVGPolygonElement) polygons.item(0)).getPoints();
        double scale = (first.getItem(3).getX() - first.getItem(0).getX()) / 40;
        for (int i = 0; i < polygons.getLength(); i++) {
            if (label.equals(((Element) polygons.item(i)).getAttribute("data-building-hex"))) {
                var points = ((SVGPolygonElement) polygons.item(i)).getPoints();
                double staggeredRow = row + (column & 1) * .5;
                // These fixtures leave wireframe cell 0,0 empty, so its vertex is the placement origin.
                assertEquals((30 * column - 6 * staggeredRow) * scale, points.getItem(0).getX() - first.getItem(0).getX(), .01);
                assertEquals(12 * staggeredRow * scale, points.getItem(0).getY() - first.getItem(0).getY(), .01);
                return;
            }
        }
        fail("Missing printed hex " + label);
    }

    @Test
    void singleFloorShowsWholeGridButOnlyOccupiedHexIsLabeled() throws Exception {
        var building = BuildingUtil.newBuilding();
        building.setChassis("Control Tower");
        var laser = EquipmentType.get("ISMediumLaser");
        building.addEquipment(laser, 0);
        building.addEquipment(laser, 0);
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertEquals(1, elements(sheet, "g", "building-map-layer").size());
        assertEquals(63, elements(sheet, "polygon", "building-hex").size());
        assertEquals(1, elements(sheet, "polygon", "occupied").size());
        assertEquals("0101", elements(sheet, "polygon", "occupied").getFirst().getAttribute("data-building-hex"));
        var layer = elements(sheet, "g", "building-map-layer").getFirst();
        assertHexAt(layer, "0101", 4, 3);
        assertEquals("0101Level: G", layer.getTextContent());
        assertTrue(layer.getAttribute("transform").contains("127.0"), "Top aligned with 24 points of spare header space");
        assertEquals(1, sheet.inventoryGroups().size());
        assertEquals(2, sheet.inventoryGroups().getFirst().size());
        assertTrue(elements(sheet, "g", "building-inventory-entry").getFirst().getTextContent().contains("0101/G"));
        render(sheet, "building-letter-single");
    }

    @Test
    void sheetsPrintGroundRelativeFloorsInDescendingOrderAndKeepNativeLocations() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 4, 80, 0, List.of(CubeCoords.ZERO));
        building.getDesign().setBaseLevel(-2);
        building.addEquipment(EquipmentType.get("ISMediumLaser"), 1);
        var sheet = sheet(building, PaperSize.ISO_A4);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.ISO_A4), true));
        var layers = elements(sheet, "g", "building-map-layer");
        assertEquals(List.of("3", "2", "1", "0"), layers.stream().map(e -> e.getAttribute("data-building-floor")).toList());
        assertEquals(List.of("1", "G", "-1", "-2"), layers.stream()
              .map(e -> e.getTextContent().substring(e.getTextContent().lastIndexOf("Level: ") + 7)).toList());
        assertTrue(elements(sheet, "g", "building-inventory-entry").stream()
              .anyMatch(row -> row.getTextContent().contains("0101/-1") && row.getAttribute("data-location").equals("1")));
        render(sheet, "building-ground-reference");
        building.getDesign().setBaseLevel(null);
        building.getDesign().setSite(BuildingDesign.Site.UNDERGROUND);
        building.getDesign().setDepth(1);
        sheet = sheet(building, PaperSize.ISO_A4);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.ISO_A4), true));
        assertTrue(elements(sheet, "g", "building-map-layer").getLast().getTextContent().contains("Level: -5"));
    }

    @Test
    void rulesAtriumExampleRetainsItsEmptyCenterAndUniformHeightThroughBlkAndPrinting() throws Exception {
        // TO:AR p. 128: a Medium Standard mall, CF 40, six hexes around an open atrium, three levels tall.
        var building = BuildingUtil.newBuilding();
        building.setChassis("Atrium Mall");
        BuildingUtil.configure(building, BuildingType.MEDIUM, IBuilding.STANDARD, 3, 40, 0,
              List.of(new CubeCoords(0, -1, 1), new CubeCoords(1, -1, 0), new CubeCoords(1, 0, -1),
                    new CubeCoords(0, 1, -1), new CubeCoords(-1, 1, 0), new CubeCoords(-1, 0, 1)));
        var loaded = (BuildingEntity) new BLKStructureFile(BLKFile.getBlock(building)).getEntity();
        assertEquals(6, loaded.getInternalBuilding().getCoordsList().size());
        assertEquals(18, loaded.locations());
        assertEquals(720, loaded.getWeight(), "Six hexes at 120 tons per hex, without adding an atrium hex");
        assertTrue(loaded.getEquipment().isEmpty());
        for (var hex : loaded.getInternalBuilding().getCoordsList()) {
            assertEquals(3, loaded.getInternalBuilding().getHeight(hex));
        }
        var sheet = sheet(loaded, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        var layers = elements(sheet, "g", "building-map-layer");
        assertEquals(List.of("2", "1", "0"), layers.stream()
              .map(layer -> layer.getAttribute("data-building-floor")).toList());
        for (var layer : layers) {
            var polygons = layer.getElementsByTagName("polygon");
            assertEquals(63, polygons.getLength());
            List<String> occupied = new ArrayList<>();
            for (int i = 0; i < polygons.getLength(); i++) {
                String label = ((Element) polygons.item(i)).getAttribute("data-building-hex");
                if (!label.isEmpty()) {
                    occupied.add(label);
                }
            }
            assertEquals(List.of("0102", "0103", "0201", "0203", "0302", "0303"), occupied);
            assertFalse(layer.getTextContent().contains("0202"), "The central atrium is empty on every floor");
        }
        render(sheet, "building-atrium-rules-example");
    }

    @Test
    void layersAndInventoryUseExactLevelsAndContinueOnNextPage() throws Exception {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 8, 80, 32,
              List.of(CubeCoords.ZERO, new CubeCoords(1, 0, -1), new CubeCoords(0, 1, -1)));
        for (int loc = 0; loc < building.locations(); loc++) {
            building.addEquipment(EquipmentType.get("ISMediumLaser"), loc);
        }
        var sheet = sheet(building, PaperSize.ISO_A4);
        assertEquals(2, sheet.getRecordPageCount());
        assertEquals(24, sheet.inventoryGroups().size());
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.ISO_A4), true));
        assertEquals(6, elements(sheet, "g", "building-map-layer").size());
        assertEquals(List.of("7", "6", "5", "4", "3", "2"), elements(sheet, "g", "building-map-layer").stream()
              .map(e -> e.getAttribute("data-building-floor")).toList());
        assertEquals(24, elements(sheet, "g", "building-inventory-entry").stream()
              .filter(row -> Integer.parseInt(row.getAttribute("data-location")) >= 0).count());
        render(sheet, "building-a4-six-layers");
        assertTrue(sheet.createDocument(1, pageFormat(PaperSize.ISO_A4), true));
        assertEquals(List.of("1", "0"), elements(sheet, "g", "building-map-layer").stream()
              .map(e -> e.getAttribute("data-building-floor")).toList());
        assertEquals(0, elements(sheet, "g", "building-inventory-entry").size());
        render(sheet, "building-a4-continuation");
    }

    @Test
    void printQueueAccountsForAllBuildingPages() {
        var building = BuildingUtil.newBuilding();
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 8, 80, 0, List.of(CubeCoords.ZERO));
        var sheets = UnitPrintManager.createSheets(List.of(building, BuildingUtil.newBuilding()), true, new RecordSheetOptions());
        assertEquals(2, sheets.size());
        assertInstanceOf(PrintBuilding.class, sheets.getFirst());
        assertEquals(3, sheets.getFirst().getPageCount());
        assertEquals(2, sheets.getLast().getPageCount());
        assertEquals(3, sheets.getLast().getFirstPage());
    }

    @Test
    void capitalWeaponsPrintTheirUpwardArcWithoutAnInventedWallFacing() throws Exception {
        var building = BuildingUtil.newBuilding();
        building.addEquipment(EquipmentType.get("Naval Autocannon (NAC/10)"), 0).setFacing(-1);
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertTrue(elements(sheet, "g", "building-inventory-entry").stream()
              .anyMatch(row -> row.getTextContent().contains("Upward (capital)")));
    }

    @Test
    void ammoQuantitiesQuartersAndPdfUseTheNativePrintPipeline() throws Exception {
        var building = BuildingUtil.newBuilding();
        building.addTransporter(new FirstClassQuartersCargoBay(2));
        var ammo = EquipmentType.get("IS Ammo AC/5");
        building.addEquipment(ammo, 0).setOriginalShots(17);
        building.addEquipment(ammo, 0).setOriginalShots(10);
        var sheet = sheet(building, PaperSize.US_LETTER);
        assertTrue(sheet.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        var rows = elements(sheet, "g", "building-inventory-entry");
        assertEquals(3, rows.size());
        assertTrue(rows.getFirst().getTextContent().contains("(27)"));
        assertTrue(rows.get(1).getTextContent().contains("Quarters"));
        // The name may wrap around a separate location cell in SVG document order.
        assertTrue(rows.get(1).getTextContent().contains("(20"));
        assertTrue(rows.get(1).getTextContent().contains("t)"));
        Path output = Path.of("build", "building-review", "building-letter.pdf");
        Files.createDirectories(output.getParent());
        Level fontLogLevel = LogManager.getLogger("org.apache.fop").getLevel();
        Configurator.setLevel("org.apache.fop", Level.WARN);
        try {
            try (var pdf = sheet.exportPDF(0, pageFormat(PaperSize.US_LETTER))) {
                assertNotNull(pdf);
                Files.copy(pdf, output, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Configurator.setLevel("org.apache.fop", fontLogLevel);
        }
        assertTrue(Files.size(output) > 1000);
    }

    @Test
    void defaultBuildingAmmoPrintsStartingLoadAndCurrentRoundsSeparately() throws Exception {
        var building = BuildingUtil.newBuilding();
        building.addEquipment(EquipmentType.get("IS Ammo AC/5"), 0);
        String[] nativeLines = java.util.Arrays.stream(BLKFile.getBlock(building).getAllDataAsString())
              .map(line -> line.replace(":Shots20#", "")).toArray(String[]::new);
        var loaded = (BuildingEntity) new BLKStructureFile(new BuildingBlock(nativeLines)).getEntity();
        loaded.getAmmo().getFirst().setShotsLeft(7);

        var clean = sheet(loaded, PaperSize.US_LETTER);
        clean.options.setDamage(false);
        assertTrue(clean.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertTrue(elements(clean, "g", "building-inventory-entry").getFirst().getTextContent().contains("(20)"));

        var current = sheet(loaded, PaperSize.US_LETTER);
        current.options.setDamage(true);
        assertTrue(current.createDocument(0, pageFormat(PaperSize.US_LETTER), true));
        assertTrue(elements(current, "g", "building-inventory-entry").getFirst().getTextContent().contains("(7)"));
    }

    @Test
    void constructionServicesAndWeaponPlacementRemainReadableAcrossInventoryPages() throws Exception {
        var building = BuildingUtil.newBuilding();
        var east = new CubeCoords(1, 0, -1);
        var west = new CubeCoords(-1, 0, 1);
        building.setChassis("Underground Control Center");
        BuildingUtil.configure(building, BuildingType.HEAVY, IBuilding.FORTRESS, 3, 80, 32,
              List.of(CubeCoords.ZERO, east, west));
        var design = building.getDesign();
        design.setEnvironmentalSealing(true);
        design.setHeavyMetal(true);
        design.setCeiling(BuildingDesign.Ceiling.LOW);
        design.setSite(BuildingDesign.Site.UNDERGROUND);
        design.setRoofClearance(true);
        design.setDepth(2);
        var generator = building.addEquipment(EquipmentType.get("FUSION PowerGenerator"), 4);
        generator.setSize(10);
        design.getEquipmentSpace().put(generator,
              List.of(new BuildingDesign.Position(east, 1), new BuildingDesign.Position(west, 1)));
        for (int i = 0; i < 3; i++) {
            var laser = building.addEquipment(EquipmentType.get("ISMediumLaser"), 3);
            laser.setFacing(2);
            if (i < 2) {
                design.getAutomatedWeapons().add(laser);
            }
        }
        var quarters = new FirstClassQuartersCargoBay(2);
        building.addTransporter(quarters);
        design.getBaySpace().put(quarters, List.of(new BuildingDesign.Space(new BuildingDesign.Position(west, 0), 20)));
        design.getDoors().add(new BuildingDesign.Door(new BuildingDesign.Position(east, 0), 2, 2));
        design.getElevators().add(new BuildingDesign.Elevator(CubeCoords.ZERO, 20, Map.of(0, 36, 1, 36, 2, 36, 3, 36)));
        assertTrue(BuildingUtil.constructionIssues(building).isEmpty(), BuildingUtil.constructionIssues(building).toString());
        var sheet = sheet(building, PaperSize.ISO_A4);
        assertEquals(1, sheet.getRecordPageCount(), "Fit the complete service inventory before adding another page");
        assertEquals(3, sheet.inventoryGroups().get(1).size(), "Same equipment/hex/floor retains one quantity group");
        var text = new StringBuilder();
        for (int page = 0; page < sheet.getRecordPageCount(); page++) {
            assertTrue(sheet.createDocument(page, pageFormat(PaperSize.ISO_A4), true));
            var rows = elements(sheet, "g", "building-inventory-entry");
            assertTrue(rows.size() > 18, "A full inventory is no longer limited to 18 rows");
            assertFalse(elements(sheet, "g", "building-map-key").isEmpty());
            rows.forEach(row -> text.append(row.getTextContent()).append('\n'));
            render(sheet, "building-construction-details-" + (page + 1));
        }
        assertTrue(text.toString().contains("2 × SE fixed; auto, Gunnery 5"));
        assertTrue(text.toString().contains("Mass share: 5.00 t"));
        assertTrue(text.toString().contains("Environmental sealing"));
        assertTrue(text.toString().contains("Door SE: 2 levels high"));
        assertTrue(text.toString().contains("Lift access: SE, NW"));
        assertTrue(text.toString().contains("/Roof"));
        assertTrue(text.toString().contains("Current elevator level:"));
        assertFalse(text.toString().contains("Unallocated"));
    }

    /** Keep review images in build/ rather than committing snapshots of generated artwork. */
    private void render(PrintBuilding sheet, String name) throws Exception {
        Path directory = Path.of("build", "building-review");
        Files.createDirectories(directory);
        PNGTranscoder renderer = new PNGTranscoder();
        renderer.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 1224f);
        renderer.addTranscodingHint(PNGTranscoder.KEY_BACKGROUND_COLOR, Color.WHITE);
        try (OutputStream output = Files.newOutputStream(directory.resolve(name + ".png"))) {
            renderer.transcode(new TranscoderInput(sheet.getSVGDocument()), new TranscoderOutput(output));
        }
    }
}
