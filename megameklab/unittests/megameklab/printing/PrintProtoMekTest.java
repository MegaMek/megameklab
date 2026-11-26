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

import megamek.common.units.ProtoMek;
import megamek.common.units.UnitRole;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.w3c.dom.Document;
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

    @Test
    void longNameWithoutSpacesStillFitsOnOneRow() {
        String name = "Unbroken".repeat(4);
        Document doc = render(name, "", PaperSize.US_LETTER, false, false, true);

        assertEquals(name, doc.getElementById(TYPE).getTextContent());
        assertTrue(doc.getElementById(TYPE).hasAttribute(SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE));
        assertTrue(doc.getElementById(TYPE2).getTextContent().isEmpty());
        assertEquals(baseline(doc, TYPE2), baseline(doc, TONNAGE), TOLERANCE);
        assertCondensedMovementSpacing(doc);
    }

    private static Document render(String chassis, String model, PaperSize size, boolean quad, boolean glider,
          boolean bold) {
        ProtoMek proto = new ProtoMek();
        proto.setChassis(chassis);
        proto.setModel(model);
        proto.setWeight(15);
        proto.setIsQuad(quad);
        proto.setIsGlider(glider);
        proto.setUnitRole(UnitRole.SNIPER);

        RecordSheetOptions options = new RecordSheetOptions();
        options.setPaperSize(size);
        options.setBoldType(bold);
        options.setRole(true);
        options.setEraIcon(false);
        options.setReferenceCharts(false);

        PageFormat page = new PageFormat();
        page.setPaper(size.createPaper());
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
