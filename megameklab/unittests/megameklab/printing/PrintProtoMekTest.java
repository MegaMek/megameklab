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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static megameklab.printing.IdConstants.ARMOR_PIPS;
import static megameklab.printing.IdConstants.ERA_ICON;
import static megameklab.printing.IdConstants.INVENTORY;
import static megameklab.printing.IdConstants.LBL_JUMP;
import static megameklab.printing.IdConstants.LBL_ROLE;
import static megameklab.printing.IdConstants.MAIN_GUN_ARMOR;
import static megameklab.printing.IdConstants.MAIN_GUN_SHADOW;
import static megameklab.printing.IdConstants.MAIN_GUN_TEXT;
import static megameklab.printing.IdConstants.MP_GROUND;
import static megameklab.printing.IdConstants.MP_JUMP;
import static megameklab.printing.IdConstants.MP_RUN;
import static megameklab.printing.IdConstants.MP_WALK;
import static megameklab.printing.IdConstants.ROLE;
import static megameklab.printing.IdConstants.STRUCTURE_PIPS;
import static megameklab.printing.IdConstants.TONNAGE;
import static megameklab.printing.IdConstants.TYPE;
import static megameklab.printing.IdConstants.TYPE2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import megamek.common.equipment.EquipmentType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.ProtoMek;
import megamek.common.units.UnitRole;
import megamek.common.verifier.TestProtoMek;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGRectElement;
import org.w3c.dom.svg.SVGTextElement;

@ExtendWith(InitializeTypes.class)
class PrintProtoMekTest {
    private static final String TONS_LABEL = "text5";
    private static final String MOVEMENT_HEADING = "text6";
    private static final double CONDENSED_MOVEMENT_ROW_SPACING = 7.6;
    private static final double TOLERANCE = 0.001;

    @ParameterizedTest
    @CsvSource({
          "US_LETTER, false, false, true",
          "US_LETTER, true, false, true",
          "US_LETTER, false, true, true",
          "ISO_A4, false, false, true",
          "ISO_A4, true, false, true",
          "ISO_A4, false, true, true",
          "US_LETTER, false, false, false"
    })
    void wrappedNameGetsItsOwnRow(PaperSize size, boolean quad, boolean glider, boolean bold) {
        Document doc = render("Sprite Ultraheavy ProtoMech", "2", size, quad, glider, bold);

        assertEquals("Sprite Ultraheavy", doc.getElementById(TYPE).getTextContent());
        assertEquals("ProtoMech 2", doc.getElementById(TYPE2).getTextContent());
        assertEquals(doc.getElementById(TYPE).getAttribute("x"), doc.getElementById(TYPE2).getAttribute("x"));
        String weight = bold ? "bold" : "normal";
        assertEquals(weight, PrintRecordSheet.parseStyle(doc.getElementById(TYPE), "font-weight"));
        assertEquals(weight, PrintRecordSheet.parseStyle(doc.getElementById(TYPE2), "font-weight"));

        double lineHeight = baseline(doc, TYPE2) - baseline(doc, TYPE);
        assertTrue(lineHeight > 0);
        assertEquals(lineHeight, baseline(doc, TONNAGE) - baseline(doc, TYPE2), TOLERANCE);
        assertEquals(baseline(doc, TONNAGE), baseline(doc, TONS_LABEL), TOLERANCE);
        assertEquals(baseline(doc, ROLE), baseline(doc, LBL_ROLE), TOLERANCE);
        assertRowsInOrder(doc, TONNAGE, ROLE, MOVEMENT_HEADING, glider ? MP_GROUND : MP_WALK,
              glider ? MP_WALK : MP_RUN, glider ? MP_RUN : MP_JUMP);
        assertCondensedMovementSpacing(doc);
        SVGTextElement lastLabel = (SVGTextElement) doc.getElementById(glider ? "text9" : LBL_JUMP);
        var bounds = lastLabel.getBBox();
        // The Pilot Data border starts at y=88.191 (Letter) or y=98.191 (A4), with a 1 pt stroke.
        double borderTop = size == PaperSize.US_LETTER ? 87.691 : 97.691;
        double labelBottom = bounds.getY() + bounds.getHeight() + lastLabel.getCTM().getF();
        assertTrue(labelBottom < borderTop, () -> "Movement label extends to y=" + labelBottom);
    }

    @Test
    void shortNameUsesCondensedSpacingWithoutAddingAnEmptyRow() {
        Document doc = render("Sprite", "2", PaperSize.US_LETTER, false, false, true);

        assertEquals("Sprite 2", doc.getElementById(TYPE).getTextContent());
        assertTrue(doc.getElementById(TYPE2).getTextContent().isEmpty());
        assertEquals(baseline(doc, TYPE2), baseline(doc, TONNAGE), TOLERANCE);
        assertCondensedMovementSpacing(doc);
        for (String id : List.of(MAIN_GUN_ARMOR, MAIN_GUN_SHADOW, MAIN_GUN_TEXT)) {
            assertEquals(SVGConstants.CSS_HIDDEN_VALUE,
                  doc.getElementById(id).getAttribute(SVGConstants.CSS_VISIBILITY_PROPERTY));
        }
    }

    @ParameterizedTest
    @EnumSource(PaperSize.class)
    void longNameWithoutSpacesStillFitsOnOneRow(PaperSize size) {
        String name = "Unbroken".repeat(4);
        Document doc = render(name, "", size, false, false, true);

        SVGTextElement nameField = (SVGTextElement) doc.getElementById(TYPE);
        assertEquals(name, nameField.getTextContent());
        assertTrue(nameField.hasAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE));
        assertTrue(doc.getElementById(TYPE2).getTextContent().isEmpty());
        assertEquals(baseline(doc, TYPE2), baseline(doc, TONNAGE), TOLERANCE);
        assertCondensedMovementSpacing(doc);

        var nameBounds = nameField.getBBox();
        double nameRight = nameBounds.getX() + nameBounds.getWidth() + nameField.getCTM().getE();
        double inventoryBorder = ((SVGRectElement) doc.getElementById(INVENTORY)).getCTM().getE();
        assertTrue(nameRight + 0.5 < inventoryBorder, "Name must clear the inventory border");
    }

    @ParameterizedTest
    @CsvSource({
          "US_LETTER, false, false",
          "US_LETTER, true, false",
          "US_LETTER, false, true",
          "ISO_A4, false, false",
          "ISO_A4, true, false",
          "ISO_A4, false, true"
    })
    void armorDiagramSeparatesArmorAndStructurePips(PaperSize size, boolean quad, boolean glider) {
        ProtoMek proto = createProtoMek("Sprite Ultraheavy ProtoMech", "2", quad, glider);
        proto.setHasMainGun(true);
        proto.autoSetInternal();
        for (int loc = 0; loc < proto.locations(); loc++) {
            proto.initializeArmor(TestProtoMek.maxArmorFactor(proto, loc), loc);
        }
        RecordSheetOptions options = createOptions(size, true);
        options.setFancyPips(false);
        Document doc = render(proto, options);
        SVGTextElement headLabel = (SVGTextElement) doc.getElementById("text3040");
        Rectangle2D headLabelBounds = sheetBounds(headLabel);
        for (int loc = 0; loc < proto.locations(); loc++) {
            String abbr = proto.getLocationAbbr(loc);
            List<Element> armor = findPips(doc, "armor", abbr);
            List<Element> structure = findPips(doc, "structure", abbr);
            assertEquals(Math.max(0, proto.getOArmor(loc)), armor.size(), abbr + " armor");
            assertEquals(Math.max(0, proto.getOInternal(loc)), structure.size(), abbr + " structure");
            assertTrue(armor.stream().allMatch(pip -> SVGConstants.SVG_CIRCLE_TAG.equals(pip.getLocalName())));
            assertTrue(structure.stream().allMatch(pip -> SVGConstants.SVG_CIRCLE_TAG.equals(pip.getLocalName())
                  && "#ffffff".equals(pip.getAttribute(SVGConstants.SVG_FILL_ATTRIBUTE))));
            if (!structure.isEmpty()) {
                assertStructurePipClearances(structure, armor, loc == ProtoMek.LOC_TORSO ? 1.8 : 2);
                double minimumGap = loc == ProtoMek.LOC_TORSO ? (quad ? 0.4 : 0.15) : 0.3;
                assertPipGap(structure, minimumGap);
                assertShadedStructureRegion(doc.getElementById("structureRegion" + abbr), structure, armor);
            }
            if (loc == ProtoMek.LOC_HEAD) {
                Element armorRegion = doc.getElementById(ARMOR_PIPS + abbr);
                Element structureRegion = doc.getElementById(STRUCTURE_PIPS + abbr);
                assertTrue(armor.stream().allMatch(pip -> pip.getParentNode() == armorRegion),
                      "Head armor pips must share a single region");
                assertTrue(structure.stream().allMatch(pip -> pip.getParentNode() == structureRegion),
                      "Head structure pips must share a single region");
                double armorBottom = armor.stream().mapToDouble(pip -> sheetBounds((SVGLocatable) pip).getMaxY())
                      .max().orElseThrow();
                double structureTop = structure.stream().mapToDouble(pip -> sheetBounds((SVGLocatable) pip).getMinY())
                      .min().orElseThrow();
                assertTrue(armorBottom + 0.5 < structureTop, "Head structure pips must sit below all armor pips");
                String torso = proto.getLocationAbbr(ProtoMek.LOC_TORSO);
                assertMatchingPipStroke(findPips(doc, "armor", torso).getFirst(), armor);
                assertMatchingPipStroke(findPips(doc, "structure", torso).getFirst(), structure);
                Element outline = doc.getElementById(quad ? "path2762" : "path2580");
                assertPipsClearOutline(outline, structure);
                assertPipsClearOutline(outline, armor);
                Element panel = doc.getElementById("structureRegion" + abbr);
                var panelShape = (SVGLocatable) panel;
                double dividerBottom = sheetBounds(panelShape).getMinY()
                      + pipCoordinate(panel, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE)
                      * Math.abs(sheetTransform(panelShape).getScaleY()) / 2;
                double structureInkTop = structure.stream().mapToDouble(pip -> pipInk(pip).getBounds2D().getMinY())
                      .min().orElseThrow();
                assertTrue(structureInkTop - dividerBottom >= 0.35,
                      () -> "Head structure pip clearance from the divider: " + (structureInkTop - dividerBottom));
                assertArmorPipGap(armor);
                for (Element pip : armor) {
                    Rectangle2D armorBounds = sheetBounds((SVGLocatable) pip);
                    assertTrue(armorBounds.getWidth() > 2, "Head armor pips should be large enough to mark");
                    assertTrue(!armorBounds.intersects(headLabelBounds), "Head label must clear the armor pips");
                }
            } else if (loc == ProtoMek.LOC_TORSO) {
                assertArmorSpansAtLeast(armor, quad ? 11 : 17);
                Element outline = doc.getElementById(quad ? "path2750" : "path2560");
                assertPipsClearOutline(outline, armor);
                assertPipsClearOutline(outline, structure, quad ? 0.15f : 0);
                if (quad) {
                    assertPipsClearArtwork(doc.getElementById("path2762"), armor);
                    assertTrue(structure.stream().allMatch(pip -> pipCoordinate(pip, SVGConstants.SVG_R_ATTRIBUTE)
                          >= 1.25), "Quad torso structure pips must be at least 2.5 units wide at full capacity");
                } else {
                    assertPipsClearArtwork(doc.getElementById("path2568"), armor);
                    assertPipGap(armor, 0.4);
                    Element diagram = (Element) doc.getElementById("armor_diagram_protomek_biped").getParentNode();
                    assertTrue(sheetBounds(findText(diagram, "Torso")).getMinY() > sheetBounds((SVGLocatable) outline)
                          .getMaxY(), "Torso label must clear the expanded outline");
                }
            } else if (loc == ProtoMek.LOC_LEG) {
                assertArmorSpansAtLeast(armor, quad ? 16 : 17);
                assertMirroredPips(armor);
                assertMirroredPips(structure);
                if (quad) {
                    assertPipsClearOutline(doc.getElementById("path2742"), armor);
                }
            } else if (loc == ProtoMek.LOC_MAIN_GUN) {
                assertMainGunClearances(doc, armor, structure, quad);
            } else if (!quad && (loc == ProtoMek.LOC_LEFT_ARM || loc == ProtoMek.LOC_RIGHT_ARM)) {
                assertArmorSpansAtLeast(armor, 15);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = { false, true })
    void legArmorStaysInsideTheOutlineAndMirrorsEvenCounts(boolean quad) {
        ProtoMek proto = createProtoMek("ProtoMek", "", quad, false);
        proto.autoSetInternal();
        RecordSheetOptions options = createOptions(PaperSize.ISO_A4, true);
        options.setFancyPips(false);
        String leg = proto.getLocationAbbr(ProtoMek.LOC_LEG);
        assertAll(IntStream.rangeClosed(1, TestProtoMek.maxArmorFactor(proto, ProtoMek.LOC_LEG))
              .mapToObj(count -> () -> {
                  proto.initializeArmor(count, ProtoMek.LOC_LEG);
                  Document doc = render(proto, options);
                  List<Element> armor = findPips(doc, "armor", leg);
                  assertEquals(count, armor.size());
                  assertPipsClearOutline(doc.getElementById(quad ? "path2742" : "path2536"), armor);
                  assertShadedStructureRegion(doc.getElementById("structureRegion" + leg),
                        findPips(doc, "structure", leg), armor);
                  if (count % 2 == 0) {
                      assertMirroredPips(armor);
                  }
              }));
    }

    @ParameterizedTest
    @CsvSource({
          "US_LETTER, false, false, EDGE",
          "US_LETTER, true, false, EDGE",
          "US_LETTER, false, true, EDGE",
          "ISO_A4, false, false, EDGE",
          "ISO_A4, true, false, EDGE",
          "ISO_A4, false, true, EDGE",
          "ISO_A4, false, false, COLUMN"
    })
    void inventoryColumnsLeaveRoomForHeadingsAndHitModifiers(PaperSize size, boolean quad, boolean glider,
          RecordSheetOptions.HitModStyle hitModStyle) throws LocationFullException {
        ProtoMek proto = createProtoMek("Sprite Ultraheavy ProtoMech", "2", quad, glider);
        proto.addEquipment(EquipmentType.get("ISSmallPulseLaser"), ProtoMek.LOC_TORSO);

        RecordSheetOptions options = createOptions(size, true);
        options.setIncludeHitMod(hitModStyle);
        Document doc = render(proto, options);
        Element inventory = (Element) doc.getElementById(INVENTORY).getParentNode();

        List<String> headings = hitModStyle == RecordSheetOptions.HitModStyle.COLUMN
              ? List.of("Tn", "Min", "Sht", "Med", "Lng") : List.of("Min", "Sht", "Med", "Lng");
        for (int i = 1; i < headings.size(); i++) {
            assertHorizontalGap(findText(inventory, headings.get(i - 1)), findText(inventory, headings.get(i)));
        }
        assertHorizontalGap(findText(inventory, "#"), findText(inventory, "Type"));

        SVGTextElement quantity = descendantsByTag(inventory, "text")
              .filter(text -> "quantity".equals(text.getAttribute("class")))
              .map(SVGTextElement.class::cast)
              .findFirst().orElseThrow();
        assertEquals("1", quantity.getTextContent());
        Element row = (Element) quantity.getParentNode();
        SVGTextElement name = descendantsByTag(row, "text")
              .filter(text -> "name".equals(((Element) text.getParentNode()).getAttribute("class")))
              .map(SVGTextElement.class::cast)
              .findFirst().orElseThrow();
        assertHorizontalGap(quantity, name);
        if (hitModStyle == RecordSheetOptions.HitModStyle.EDGE) {
            SVGRectElement badge = descendantsByTag(row, "rect").map(SVGRectElement.class::cast)
                  .findFirst().orElseThrow();
            double badgeLeft = badge.getX().getBaseVal().getValue();
            double badgeRight = badgeLeft + badge.getWidth().getBaseVal().getValue();
            assertTrue(badgeRight + 0.5 < quantity.getBBox().getX(), "Quantity must clear the hit modifier");

            var eraIcon = (SVGRectElement) doc.getElementById(ERA_ICON);
            double iconRight = eraIcon.getX().getBaseVal().getValue() + eraIcon.getWidth().getBaseVal().getValue();
            assertTrue(iconRight + eraIcon.getCTM().getE() + 0.5 < badgeLeft + badge.getCTM().getE(),
                  "Hit modifier must clear the era icon");
        }
    }

    private static Document render(String chassis, String model, PaperSize size, boolean quad, boolean glider,
          boolean bold) {
        return render(createProtoMek(chassis, model, quad, glider), createOptions(size, bold));
    }

    private static ProtoMek createProtoMek(String chassis, String model, boolean quad, boolean glider) {
        ProtoMek proto = new ProtoMek();
        proto.setChassis(chassis);
        proto.setModel(model);
        proto.setWeight(15);
        proto.setIsQuad(quad);
        proto.setIsGlider(glider);
        proto.setUnitRole(UnitRole.SNIPER);
        return proto;
    }

    private static RecordSheetOptions createOptions(PaperSize size, boolean bold) {
        RecordSheetOptions options = new RecordSheetOptions();
        options.setPaperSize(size);
        options.setBoldType(bold);
        options.setRole(true);
        options.setEraIcon(false);
        options.setReferenceCharts(false);
        return options;
    }

    private static Document render(ProtoMek proto, RecordSheetOptions options) {
        PageFormat page = new PageFormat();
        page.setPaper(options.getPaperSize().createPaper());
        PrintProtoMek sheet = new PrintProtoMek(proto, 0, 0, options) {
            @Override
            String getSVGDirectoryName(boolean useUnitTestTemplateDirectory) {
                // Use the canonical templates without depending on staged local assets.
                return Path.of("..", "..", "mm-data").resolve(super.getSVGDirectoryName(false)).toString();
            }
        };
        assertTrue(sheet.createDocument(0, page, false));
        sheet.build();
        return sheet.getSVGDocument();
    }

    private static Stream<Element> descendantsByTag(Element parent, String tag) {
        NodeList matches = parent.getElementsByTagNameNS(PrintRecordSheet.svgNS, tag);
        return IntStream.range(0, matches.getLength()).mapToObj(index -> (Element) matches.item(index));
    }

    private static List<Element> findPips(Document doc, String kind, String location) {
        return descendantsByTag(doc.getDocumentElement(), "*")
              .filter(element -> ("pip " + kind).equals(element.getAttribute("class"))
                    && location.equals(element.getAttribute("loc")))
              .toList();
    }

    private static Rectangle2D sheetBounds(SVGLocatable element) {
        var bounds = element.getBBox();
        return sheetTransform(element).createTransformedShape(new Rectangle2D.Double(bounds.getX(), bounds.getY(),
              bounds.getWidth(), bounds.getHeight())).getBounds2D();
    }

    private static AffineTransform sheetTransform(SVGLocatable element) {
        var transform = element.getCTM();
        return new AffineTransform(transform.getA(), transform.getB(), transform.getC(), transform.getD(),
              transform.getE(), transform.getF());
    }

    private static Shape svgPath(Element element) {
        var producer = new AWTPathProducer();
        producer.setWindingRule(Path2D.WIND_NON_ZERO);
        var parser = new PathParser();
        parser.setPathHandler(producer);
        parser.parse(element.getAttribute(SVGConstants.SVG_D_ATTRIBUTE));
        return producer.getShape();
    }

    private static void assertPipsClearOutline(Element outline, List<Element> pips) {
        assertPipsClearOutline(outline, pips, 0.15f);
    }

    private static void assertPipsClearOutline(Element outline, List<Element> pips, float clearance) {
        Shape contour;
        float width;
        if (SVGConstants.SVG_ELLIPSE_TAG.equals(outline.getLocalName())) {
            var bounds = ((SVGLocatable) outline).getBBox();
            contour = new Ellipse2D.Float(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            width = (float) pipCoordinate(outline, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE);
        } else {
            contour = svgPath(outline);
            width = Float.parseFloat(PrintRecordSheet.parseStyle(outline, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE));
        }
        var interior = new Area(contour);
        interior.subtract(new Area(new BasicStroke(width + 2 * clearance).createStrokedShape(contour)));
        interior.transform(sheetTransform((SVGLocatable) outline));

        for (Element pip : pips) {
            Area ink = pipInk(pip);
            ink.subtract(interior);
            assertTrue(ink.isEmpty(), () -> pip.getAttribute("loc") + " " + pip.getAttribute("class")
                  + " at " + sheetBounds((SVGLocatable) pip) + " must clear the outline");
        }
    }

    private static void assertShadedStructureRegion(Element panel, List<Element> structure, List<Element> armor) {
        assertEquals("#c7c7c7", panel.getAttribute(SVGConstants.SVG_FILL_ATTRIBUTE));
        assertEquals("#000000", panel.getAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE));
        Shape contour = svgPath(panel);
        float width = Float.parseFloat(panel.getAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE));
        var transform = sheetTransform((SVGLocatable) panel);
        var divider = new Area(transform.createTransformedShape(new BasicStroke(width).createStrokedShape(contour)));
        var interior = new Area(transform.createTransformedShape(contour));
        var shadedArea = new Area(interior);
        shadedArea.add(divider);
        interior.subtract(divider);
        for (Element pip : structure) {
            Area ink = pipInk(pip);
            ink.subtract(interior);
            assertTrue(ink.isEmpty(), () -> panel.getAttribute("id") + " structure pip at "
                  + sheetBounds((SVGLocatable) pip) + " touches the panel edge");
        }
        for (Element pip : armor) {
            Area ink = pipInk(pip);
            ink.intersect(shadedArea);
            assertTrue(ink.isEmpty(), () -> panel.getAttribute("id") + " shading touches an armor pip");
        }
    }

    private static void assertPipsClearArtwork(Element artwork, List<Element> pips) {
        Shape contour = svgPath(artwork);
        float width = Float.parseFloat(PrintRecordSheet.parseStyle(artwork, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE));
        var ink = new Area(contour);
        ink.add(new Area(new BasicStroke(width).createStrokedShape(contour)));
        ink.transform(sheetTransform((SVGLocatable) artwork));
        for (Element pip : pips) {
            Area overlap = pipInk(pip);
            overlap.intersect(ink);
            assertTrue(overlap.isEmpty(), () -> "Torso armor pip at " + sheetBounds((SVGLocatable) pip)
                  + " must clear " + artwork.getAttribute("id"));
        }
    }

    private static Area pipInk(Element pip) {
        SVGLocatable locatable = (SVGLocatable) pip;
        var bounds = locatable.getBBox();
        Shape shape = SVGConstants.SVG_CIRCLE_TAG.equals(pip.getLocalName())
              ? new Ellipse2D.Float(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight())
              : svgPath(pip);
        var ink = new Area(shape);
        ink.add(new Area(new BasicStroke((float) pipCoordinate(pip, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE))
              .createStrokedShape(shape)));
        ink.transform(sheetTransform(locatable));
        return ink;
    }

    private static void assertMirroredPips(List<Element> pips) {
        var bounds = pips.stream().map(pip -> ((SVGLocatable) pip).getBBox()).toList();
        var left = bounds.stream().filter(pip -> pip.getX() + pip.getWidth() / 2 < -TOLERANCE).toList();
        var right = bounds.stream().filter(pip -> pip.getX() + pip.getWidth() / 2 > TOLERANCE).toList();
        assertEquals(pips.size(), left.size() + right.size(), "Even counts should not place a pip on the centerline");
        assertEquals(left.size(), right.size(), "Both legs should have the same pip count");
        for (var pip : left) {
            double reflectedX = -pip.getX() - pip.getWidth();
            assertTrue(right.stream().anyMatch(other -> Math.abs(reflectedX - other.getX()) < TOLERANCE
                  && Math.abs(pip.getY() - other.getY()) < TOLERANCE
                  && Math.abs(pip.getWidth() - other.getWidth()) < TOLERANCE),
                  () -> "No matching pip across the centerline for x=" + pip.getX() + ", y=" + pip.getY());
        }
    }

    private static void assertMainGunClearances(Document doc, List<Element> armor, List<Element> structure,
          boolean quad) {
        Element gunOutline = descendantsByTag(doc.getElementById(MAIN_GUN_ARMOR), SVGConstants.SVG_ELLIPSE_TAG)
              .findFirst().orElseThrow();
        Rectangle2D outline = sheetBounds((SVGLocatable) gunOutline);
        Rectangle2D head = sheetBounds((SVGLocatable) doc.getElementById(quad ? "path2758" : "path2576"));
        assertTrue(outline.getWidth() < head.getWidth() && outline.getHeight() < head.getHeight() * 0.75,
              "The Main Gun should be noticeably smaller than the head");
        Rectangle2D label = sheetBounds((SVGLocatable) doc.getElementById(MAIN_GUN_TEXT));
        assertTrue(label.getMaxX() + 0.5 < outline.getMinX(), "Main Gun label must clear the outline");

        assertPipsClearOutline(gunOutline, Stream.concat(armor.stream(), structure.stream()).toList());
        assertArmorPipGap(armor);
    }

    private static void assertArmorPipGap(List<Element> pips) {
        assertPipGap(pips, 0.2);
    }

    private static void assertPipGap(List<Element> pips, double minimumGap) {
        for (int i = 0; i < pips.size(); i++) {
            Element first = pips.get(i);
            double x = pipCoordinate(first, SVGConstants.SVG_CX_ATTRIBUTE);
            double y = pipCoordinate(first, SVGConstants.SVG_CY_ATTRIBUTE);
            for (Element second : pips.subList(i + 1, pips.size())) {
                double distance = Math.hypot(x - pipCoordinate(second, SVGConstants.SVG_CX_ATTRIBUTE),
                      y - pipCoordinate(second, SVGConstants.SVG_CY_ATTRIBUTE));
                double gap = distance - pipOuterRadius(first) - pipOuterRadius(second);
                assertTrue(gap > minimumGap,
                      () -> first.getAttribute("loc") + " " + first.getAttribute("class")
                            + " outlines need a visible gap; found " + gap);
            }
        }
    }

    private static void assertStructurePipClearances(List<Element> structure, List<Element> armor, double minSide) {
        List<Rectangle2D> armorBounds = armor.stream().map(pip -> sheetBounds((SVGLocatable) pip)).toList();
        for (Element pip : structure) {
            var bounds = ((SVGLocatable) pip).getBBox();
            assertTrue(bounds.getWidth() >= minSide, "Structure pips must be large enough to mark");
            Rectangle2D printedBounds = sheetBounds((SVGLocatable) pip);
            assertTrue(armorBounds.stream().noneMatch(printedBounds::intersects),
                  "Armor and structure pips must not overlap");
        }
    }

    private static void assertMatchingPipStroke(Element reference, List<Element> pips) {
        var transform = ((SVGLocatable) reference).getCTM();
        double stroke = pipCoordinate(reference, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE);
        for (Element pip : pips) {
            var pipTransform = ((SVGLocatable) pip).getCTM();
            double pipStroke = pipCoordinate(pip, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE);
            assertEquals(stroke * Math.hypot(transform.getA(), transform.getB()),
                  pipStroke * Math.hypot(pipTransform.getA(), pipTransform.getB()), TOLERANCE, "Horizontal stroke");
            assertEquals(stroke * Math.hypot(transform.getC(), transform.getD()),
                  pipStroke * Math.hypot(pipTransform.getC(), pipTransform.getD()), TOLERANCE, "Vertical stroke");
        }
    }

    private static double pipOuterRadius(Element pip) {
        return pipCoordinate(pip, SVGConstants.SVG_R_ATTRIBUTE)
              + pipCoordinate(pip, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE) / 2;
    }

    private static double pipCoordinate(Element pip, String attribute) {
        return Double.parseDouble(pip.getAttribute(attribute));
    }

    private static void assertArmorSpansAtLeast(List<Element> pips, int height) {
        Rectangle2D bounds = pips.stream().map(pip -> sheetBounds((SVGLocatable) pip))
              .reduce(Rectangle2D::createUnion).orElseThrow();
        assertTrue(bounds.getHeight() >= height, () -> "Armor cluster should use more of the location: " + bounds);
    }

    private static SVGTextElement findText(Element parent, String label) {
        return descendantsByTag(parent, "text").filter(text -> label.equals(text.getTextContent()))
              .map(SVGTextElement.class::cast).findFirst().orElseThrow(() -> new AssertionError("Missing " + label));
    }

    private static void assertHorizontalGap(SVGTextElement left, SVGTextElement right) {
        var leftBounds = left.getBBox();
        double gap = right.getBBox().getX() - leftBounds.getX() - leftBounds.getWidth();
        assertTrue(gap > 0.5, () -> left.getTextContent() + " / " + right.getTextContent() + " gap: " + gap);
    }

    private static float baseline(Document doc, String id) {
        return ((SVGTextElement) doc.getElementById(id)).getY().getBaseVal().getItem(0).getValue();
    }

    private static void assertCondensedMovementSpacing(Document doc) {
        assertEquals(CONDENSED_MOVEMENT_ROW_SPACING, baseline(doc, MP_RUN) - baseline(doc, MP_WALK), TOLERANCE);
    }

    private static void assertRowsInOrder(Document doc, String... ids) {
        for (int i = 1; i < ids.length; i++) {
            assertTrue(baseline(doc, ids[i - 1]) < baseline(doc, ids[i]));
        }
    }
}
