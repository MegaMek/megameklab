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
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.print.PageFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import megamek.common.equipment.EquipmentType;
import megamek.common.game.Game;
import megamek.common.loaders.MekFileParser;
import megamek.common.options.OptionsConstants;
import megamek.common.rules.RulesManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megamek.common.units.Tank;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ExtendWith(InitializeTypes.class)
class MekRecordSheetRulesTest {
    private String originalRulesSystem;
    private String originalScaleUnits;
    private String originalTacOpsHeat;
    private RulesManager originalRulesManager;

    @BeforeEach
    void rememberSettings() {
        originalRulesSystem = CConfig.getParam(CConfig.MISC_RULES_SYSTEM);
        originalScaleUnits = CConfig.getParam(CConfig.RS_SCALE_UNITS);
        originalTacOpsHeat = CConfig.getParam(CConfig.RS_TAC_OPS_HEAT);
        originalRulesManager = Game.rulesManager;
        CConfig.setParam(CConfig.RS_SCALE_UNITS, RSScale.HEXES.toString());
    }

    @AfterEach
    void restoreSettings() {
        CConfig.setParam(CConfig.MISC_RULES_SYSTEM, originalRulesSystem);
        CConfig.setParam(CConfig.RS_SCALE_UNITS, originalScaleUnits);
        CConfig.setParam(CConfig.RS_TAC_OPS_HEAT, originalTacOpsHeat);
        Game.rulesManager = originalRulesManager;
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void rendersSelectedRulesInReferenceTables(boolean core) throws Exception {
        selectRules(core);
        Document document = renderSheet("Black Hawk-KU BHKU-OE.mtf");

        List<String> physicals = tableCells(document, "PHYSICAL ATTACKS");
        assertRow(physicals, "Punch", core ? "+0" : "+1", "6");
        assertRow(physicals, "Kick", core ? "-1" : "-2", "12");
        assertRow(physicals, "Charge", "+0*", core ? "12\u00D7(TMM+1)" : "6/Hex");
        assertRow(physicals, "DFA", "+0*", "18");

        List<String> punch = tableCells(document, "PUNCH LOCATION TABLE");
        assertRow(punch, "1", "LT", core ? "RA" : "LA", "RT");
        assertRow(punch, "2", "LT", core ? "RT" : "LT", "RT");
        assertRow(punch, "4", "LA", core ? "LT" : "RT", "RA");
        assertRow(punch, "5", "LA", core ? "LA" : "RA", "RA");

        List<String> condensed = tableCells(document, "PUNCH/KICK LOCATION TABLE");
        assertRow(condensed, "1", "LT", core ? "RA" : "LA", "RT", "LL", "RL", "RL");
        assertRow(condensed, "5", "LA", core ? "LA" : "RA", "RA", "LL", "LL", "RL");

        List<String> falls = tableCells(document, "FACING AFTER FALL");
        assertRow(falls, "1", "Same Direction", core ? "Rear" : "Front");
        if (core) {
            assertRow(falls, "2-6", "Same Direction", "Front");
            assertFalse(falls.contains("Opposite Direction"));
        } else {
            assertRow(falls, "4", "Opposite Direction", "Rear");
        }

        List<String> toHit = tableCells(document, "TO-HIT MODIFIERS");
        assertEquals(core ? 0 : 2, Collections.frequency(toHit, "Skidding"));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void preservesFrontAndRearLegAbbreviationsForQuads(boolean core) throws Exception {
        selectRules(core);
        List<String> punch = tableCells(renderSheet("Harpagos.mtf"), "PUNCH LOCATION TABLE");
        assertRow(punch, "1", "LT", core ? "RFL/RRL" : "LFL/LRL", "RT");
        assertRow(punch, "5", "LRL", core ? "LFL/LRL" : "RFL/RRL", "RRL");
    }

    @ParameterizedTest
    @CsvSource({
          "US_LETTER, Black Hawk-KU BHKU-OE.mtf, false, ALL, ASTERISK",
          "US_LETTER, Black Hawk-KU BHKU-OE.mtf, true, LOGO_ONLY, ARROW",
          "ISO_A4, Harpagos.mtf, false, NONE, ARROW",
          "ISO_A4, Harpagos.mtf, true, ALL, ASTERISK"
    })
    void rendersSelectedHeatLayout(PaperSize size, String fileName, boolean tacOps, RecordSheetOptions.ColorMode color,
          RecordSheetOptions.HeatScaleMarker marker) throws Exception {
        Mek mek = (Mek) new MekFileParser(new File("testresources", fileName)).getEntity();
        CConfig.setParam(CConfig.RS_TAC_OPS_HEAT, Boolean.toString(tacOps));
        RecordSheetOptions options = new RecordSheetOptions();
        options.setPaperSize(size);
        options.setColor(color);
        options.setHeatScaleMarker(marker);
        options.setReferenceCharts(false);
        PageFormat page = new PageFormat();
        page.setPaper(size.createPaper());
        PrintMek sheet = new PrintMek(mek, 0, options);
        assertTrue(sheet.createDocument(0, page, false));
        Document document = sheet.getSVGDocument();

        Element heatScale = document.getElementById("heatScale");
        assertNotNull(heatScale);
        assertFalse("none".equals(heatScale.getAttribute("display")));
        assertNull(document.getElementById("tacOps_heatScale"));
        assertNull(document.getElementById("tacOps_heatDataPanel"));
        String visibleVariant = color == RecordSheetOptions.ColorMode.ALL ? "heatScaleColor" : "heatScaleBW";
        String unusedVariant = color == RecordSheetOptions.ColorMode.ALL ? "heatScaleBW" : "heatScaleColor";
        Element scale = document.getElementById(visibleVariant);
        assertNotNull(scale);
        assertNull(document.getElementById(unusedVariant));
        assertEquals("visible", scale.getAttribute("visibility"));
        int maxHeat = tacOps ? 50 : 30;
        var cells = scale.getElementsByTagNameNS(PrintRecordSheet.svgNS, "rect");
        assertEquals(maxHeat + 1, cells.getLength());
        assertEquals(Integer.toString(maxHeat), ((Element) cells.item(0)).getAttribute("heat"));
        String scaleText = scale.getTextContent();
        assertEquals(marker == RecordSheetOptions.HeatScaleMarker.ASTERISK,
              scaleText.contains("*") || scaleText.contains("\u2217"));

        Element panel = document.getElementById("heatDataPanel");
        assertNotNull(panel);
        assertFalse("none".equals(panel.getAttribute("display")));
        assertEquals(tacOps, document.getElementById("minus9MP") != null);
        assertEquals("-1 Movement Points", document.getElementById("minus1MP").getTextContent());
        assertTrue(sheet.build().getBounds().getWidth() > 0);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void vehicleSheetsRetainTotalWarfareSkiddingModifiers(boolean core) throws Exception {
        selectRules(core);
        Tank tank = (Tank) new MekFileParser(new File("testresources", "Sleipnir APC.blk")).getEntity();
        PrintTank sheet = new PrintTank(tank, 0, referenceOptions());
        assertTrue(sheet.createDocument(0, new PageFormat(), false));
        List<String> toHit = tableCells(sheet.getSVGDocument(), "TO-HIT MODIFIERS");
        assertRow(toHit, "Skidding", "+1");
        assertRow(toHit, "Skidding", "-2");
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void inventoryUsesSelectedModifiersWithAndWithoutAHandActuator(boolean core) throws Exception {
        selectRules(core);
        BipedMek mek = new BipedMek();
        mek.setWeight(70);
        assertEquals(core ? InventoryEntry.MINUS + "1" : "", punchModifier(mek, "LA"));
        mek.setCritical(Mek.LOC_LEFT_ARM, 3, null);
        assertEquals(core ? InventoryEntry.DASH : "+1", punchModifier(mek, "LA"));
        assertEquals(core ? InventoryEntry.MINUS + "1" : InventoryEntry.MINUS + "2",
              IntrinsicPhysicalInventoryEntry.getEntriesFor(mek).stream()
                    .filter(entry -> entry.getNameField(0).equals("Kick"))
                    .findFirst().orElseThrow().getModField(0));
        var claw = mek.addEquipment(EquipmentType.get("ISClaw"), Mek.LOC_LEFT_ARM);
        var clawEntry = new StandardInventoryEntry(claw);
        assertEquals(core ? "0" : "+1", clawEntry.getModField(0, true));
        assertEquals(core ? "" : "+1", clawEntry.getModField(0));
    }

    private static void selectRules(boolean core) {
        CConfig.setParam(CConfig.MISC_RULES_SYSTEM, core ? OptionsConstants.RULES_CORE : OptionsConstants.RULES_TW);
        CConfig.applyRulesSystem();
    }

    private static String punchModifier(Mek mek, String location) {
        return IntrinsicPhysicalInventoryEntry.getEntriesFor(mek).stream()
              .filter(entry -> entry.getNameField(0).equals("Punch") && entry.getLocationField(0).equals(location))
              .findFirst().orElseThrow().getModField(0);
    }

    private static Document renderSheet(String fileName) throws Exception {
        Mek mek = (Mek) new MekFileParser(new File("testresources", fileName)).getEntity();
        PrintMek sheet = new PrintMek(mek, 0, referenceOptions());
        assertTrue(sheet.createDocument(0, new PageFormat(), false));
        return sheet.getSVGDocument();
    }

    private static RecordSheetOptions referenceOptions() {
        RecordSheetOptions options = new RecordSheetOptions();
        options.setReferenceCharts(true);
        options.setCondensedReferenceCharts(true);
        return options;
    }

    private static List<String> tableCells(Document document, String title) {
        var groups = document.getElementsByTagNameNS(PrintRecordSheet.svgNS, "g");
        for (int i = 0; i < groups.getLength(); i++) {
            Element group = (Element) groups.item(i);
            if (group.getAttribute("class").equals("referenceTable")) {
                var text = group.getElementsByTagNameNS(PrintRecordSheet.svgNS, "text");
                if (text.item(0).getTextContent().equals(title)) {
                    List<String> cells = new ArrayList<>();
                    for (int j = 1; j < text.getLength(); j++) {
                        cells.add(text.item(j).getTextContent());
                    }
                    return cells;
                }
            }
        }
        throw new AssertionError("Missing reference table: " + title);
    }

    private static void assertRow(List<String> cells, String... expected) {
        var row = List.of(expected);
        assertTrue(Collections.indexOfSubList(cells, row) >= 0, () -> "Expected row " + row + " in " + cells);
    }
}
