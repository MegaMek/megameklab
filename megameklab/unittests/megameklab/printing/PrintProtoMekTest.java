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

import static megameklab.printing.IdConstants.ERA_ICON;
import static megameklab.printing.IdConstants.INVENTORY;
import static megameklab.printing.IdConstants.LBL_JUMP;
import static megameklab.printing.IdConstants.LBL_ROLE;
import static megameklab.printing.IdConstants.MP_GROUND;
import static megameklab.printing.IdConstants.MP_JUMP;
import static megameklab.printing.IdConstants.MP_RUN;
import static megameklab.printing.IdConstants.MP_WALK;
import static megameklab.printing.IdConstants.ROLE;
import static megameklab.printing.IdConstants.TONNAGE;
import static megameklab.printing.IdConstants.TYPE;
import static megameklab.printing.IdConstants.TYPE2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.print.PageFormat;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import megamek.common.equipment.EquipmentType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.ProtoMek;
import megamek.common.units.UnitRole;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
