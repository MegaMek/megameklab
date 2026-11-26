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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Sensor;
import megamek.common.game.Game;
import megamek.common.rules.RulesManager;
import megamek.common.rules.core.CoreRulesManager;
import megamek.common.rules.totalwarfare.TWRulesManager;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.CConfig;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGRectElement;

@ExtendWith(value = InitializeTypes.class)
class CewsRecordSheetTest {
    private RulesManager originalRulesManager;
    private String originalScaleFactor;

    @BeforeEach
    void setUp() {
        originalRulesManager = Game.rulesManager;
        originalScaleFactor = CConfig.getParam(CConfig.RS_SCALE_FACTOR);
        Game.rulesManager = new CoreRulesManager();
        CConfig.setParam(CConfig.RS_SCALE_FACTOR, "1");
    }

    @AfterEach
    void tearDown() {
        Game.rulesManager = originalRulesManager;
        CConfig.setParam(CConfig.RS_SCALE_FACTOR, originalScaleFactor);
    }

    @Test
    void coreShowsStandardProbeAndEcmRanges() throws Exception {
        StandardInventoryEntry entry = entry(Sensor.WATCHDOG);

        assertEquals("5 (PRB) 6 (ECM)", entry.getRangeLabel(0));
        assertEquals("5", entry.getLongField(0));
        assertEquals("", entry.getRangeLabel(1));
    }

    @Test
    void totalWarfareRetainsLightProbeRange() throws Exception {
        Game.rulesManager = new TWRulesManager();
        StandardInventoryEntry entry = entry(Sensor.WATCHDOG);

        assertEquals("3 (PRB) 6 (ECM)", entry.getRangeLabel(0));
        assertEquals("3", entry.getLongField(0));
    }

    @ParameterizedTest
    @ValueSource(booleans = { false, true })
    void novaUsesStandardProbeRangeUnderBothRulesets(boolean totalWarfare) throws Exception {
        Game.rulesManager = totalWarfare ? new TWRulesManager() : new CoreRulesManager();
        StandardInventoryEntry entry = entry(Sensor.NOVA);

        assertEquals("5 (PRB) 6 (ECM)", entry.getRangeLabel(0));
        assertEquals("5", entry.getLongField(0));
        assertEquals("", entry.getRangeLabel(1));
    }

    @Test
    void scalesBothRanges() throws Exception {
        CConfig.setParam(CConfig.RS_SCALE_FACTOR, "30");

        assertEquals("150 (PRB) 180 (ECM)", entry(Sensor.WATCHDOG).getRangeLabel(0));
        assertEquals("150 (PRB) 180 (ECM)", entry(Sensor.NOVA).getRangeLabel(0));
    }

    @Test
    void leavesStandaloneElectronicWarfareRangesUnchanged() throws Exception {
        StandardInventoryEntry probe = entry(Sensor.CLAN_AP);
        StandardInventoryEntry ecm = entry("CLECMSuite");

        assertEquals("", probe.getRangeLabel(0));
        assertEquals("5", probe.getLongField(0));
        assertEquals("", ecm.getRangeLabel(0));
        assertEquals("6", ecm.getLongField(0));
        assertEquals(InventoryEntry.DASH, ecm.getShortField(0));
    }

    @ParameterizedTest
    @ValueSource(strings = { Sensor.WATCHDOG, Sensor.NOVA })
    void leavesAerospaceAttackValueColumnsUnchanged(String equipmentId) throws Exception {
        AeroSpaceFighter fighter = new AeroSpaceFighter();
        StandardInventoryEntry entry = new StandardInventoryEntry(
              fighter.addEquipment(EquipmentType.get(equipmentId), Aero.LOC_NOSE));

        assertEquals("", entry.getRangeLabel(0));
        assertEquals(InventoryEntry.DASH, entry.getShortField(0));
        assertEquals(InventoryEntry.DASH, entry.getMediumField(0));
        assertEquals(InventoryEntry.DASH, entry.getLongField(0));
        assertEquals(InventoryEntry.DASH, entry.getExtremeField(0));
    }

    @ParameterizedTest
    @EnumSource(RecordSheetOptions.HitModStyle.class)
    void rendersOneLabelAcrossAllGroundRangeColumns(RecordSheetOptions.HitModStyle hitModStyle) throws Exception {
        BipedMek mek = mek();
        mek.addEquipment(EquipmentType.get(Sensor.WATCHDOG), Mek.LOC_RIGHT_TORSO);
        mek.addEquipment(EquipmentType.get(Sensor.NOVA), Mek.LOC_CENTER_TORSO);
        mek.addEquipment(EquipmentType.get("Medium Laser"), Mek.LOC_LEFT_TORSO);
        RecordSheetOptions options = new RecordSheetOptions();
        options.setIntrinsicPhysicalAttacks(RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE);
        options.setIncludeHitMod(hitModStyle);
        PrintMek sheet = new PrintMek(mek, 0, options) {
            @Override
            public double getTextLength(String text, float fontSize, String weight, String fontStyle) {
                return text.length() * fontSize / 2;
            }

            @Override
            public float getFontHeight(float fontSize) {
                return fontSize * 1.2f;
            }
        };
        Document document = SVGDOMImplementation.getDOMImplementation().createDocument(
              SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_SVG_TAG, null);
        sheet.setSVGDocument(document);
        Element canvas = document.getDocumentElement();
        SVGRectElement region = (SVGRectElement) document.createElementNS(
              SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
        region.setAttribute("x", "20");
        region.setAttribute("y", "30");
        region.setAttribute("width", "400");
        region.setAttribute("height", "200");
        canvas.appendChild(region);

        new InventoryWriter(sheet, region).writeEquipment();

        assertCombinedRangeLabel(sheet, inventoryRow(document, Sensor.WATCHDOG));
        assertCombinedRangeLabel(sheet, inventoryRow(document, Sensor.NOVA));
        Element laser = inventoryRow(document, "Medium Laser");
        assertEquals(InventoryEntry.DASH, fieldText(laser, "range_min"));
        assertEquals("3", fieldText(laser, "range_short"));
        assertEquals("6", fieldText(laser, "range_medium"));
        assertEquals("9", fieldText(laser, "range_long"));
    }

    private void assertCombinedRangeLabel(PrintMek sheet, Element row) {
        NodeList fields = row.getElementsByTagName(SVGConstants.SVG_TEXT_TAG);
        Element label = null;
        int rangeFields = 0;
        for (int i = 0; i < fields.getLength(); i++) {
            Element field = (Element) fields.item(i);
            if (field.getAttribute("class").startsWith("range_")) {
                rangeFields++;
                label = field;
            }
        }
        assertEquals(1, rangeFields);
        assertNotNull(label);
        assertEquals("range_combined", label.getAttribute("class"));
        assertEquals("5 (PRB) 6 (ECM)", label.getTextContent());
        assertFalse(label.hasAttribute("textLength"));
        double center = Double.parseDouble(label.getAttribute("x"));
        float fontSize = Float.parseFloat(label.getAttribute("font-size").replace("px", ""));
        double halfWidth = sheet.getTextLength(label.getTextContent(), fontSize) / 2;
        assertTrue(center - halfWidth > 20 + 400 * InventoryWriter.Column.MIN.groundX);
        assertTrue(center + halfWidth < 420);
    }

    private StandardInventoryEntry entry(String equipmentId) throws Exception {
        return new StandardInventoryEntry(mek().addEquipment(EquipmentType.get(equipmentId), Mek.LOC_RIGHT_TORSO));
    }

    private BipedMek mek() {
        BipedMek mek = new BipedMek();
        mek.setWeight(50);
        mek.setEngine(new Engine(200, Engine.NORMAL_ENGINE, 0));
        return mek;
    }

    private Element inventoryRow(Document document, String equipmentId) {
        NodeList groups = document.getElementsByTagName(SVGConstants.SVG_G_TAG);
        for (int i = 0; i < groups.getLength(); i++) {
            Element group = (Element) groups.item(i);
            if (group.getAttribute("id").startsWith(equipmentId + "@")) {
                return group;
            }
        }
        throw new AssertionError("Inventory row not found: " + equipmentId);
    }

    private String fieldText(Element row, String className) {
        NodeList fields = row.getElementsByTagName(SVGConstants.SVG_TEXT_TAG);
        for (int i = 0; i < fields.getLength(); i++) {
            Element field = (Element) fields.item(i);
            if (className.equals(field.getAttribute("class"))) {
                return field.getTextContent();
            }
        }
        throw new AssertionError("Inventory field not found: " + className);
    }
}
