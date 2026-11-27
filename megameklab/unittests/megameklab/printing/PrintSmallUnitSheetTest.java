/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.compute.Compute;
import megamek.common.equipment.EquipmentType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.ConvInfantry;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megamek.common.units.SupportTank;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Element;

@ExtendWith(value = InitializeTypes.class)
class PrintSmallUnitSheetTest {
    private static final String COMBINED_TABLE_TITLE = "HIT LOCATION AND CLUSTER TABLE";

    private RecordSheetOptions noTables, yesTables;

    @BeforeEach
    void setUp() {
        noTables = new RecordSheetOptions();
        noTables.setReferenceCharts(false);
        yesTables = new RecordSheetOptions();
        yesTables.setReferenceCharts(true);
    }

    @Test
    void fillsSheetProtoMek() {
        var entities = new ArrayList<>(List.of(new ProtoMek(), new ProtoMek(), new ProtoMek(), new ProtoMek()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new ProtoMek());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void condensedProtoMekTableListsBothRollsForEachChassisAndRackSizes() throws LocationFullException {
        ProtoMek biped = protoMek("Biped", true);
        biped.addEquipment(EquipmentType.get("CLSRM2"), ProtoMek.LOC_TORSO);
        ProtoMek glider = protoMek("Glider", true);
        glider.setIsGlider(true);
        ProtoMek quad = protoMek("Quad", false);
        quad.setIsQuad(true);
        quad.addEquipment(EquipmentType.get("CLLRM5"), ProtoMek.LOC_TORSO);
        noTables.setCondensedReferenceCharts(true);
        Element sheet = renderSmallUnitSheet(List.of(quad, glider, biped, protoMek("Biped", false)), noTables);
        List<String> cells = referenceCells(sheet);

        assertEquals(List.of(COMBINED_TABLE_TITLE, "2D6", "Biped", "Regular", "Biped", "Special",
              "Glider", "Regular", "Glider", "Special", "Quad", "Regular", "Quad", "Special", "2", "5"),
              cells.subList(0, 16));
        var locations = List.of(
              List.of("Main Gun*", "Main Gun*", "Main Gun", "Main Gun", "Torso", "Torso"),
              List.of("Near Miss", "Legs", "Wings", "Legs", "Near Miss", "Legs"),
              List.of("Right Arm", "Legs", "Right Arm", "Legs", "Legs", "Legs"),
              List.of("Legs", "Right Arm", "Legs", "Right Arm", "Legs", "Legs"),
              List.of("Torso", "Torso", "Torso", "Torso", "Torso", "Torso"),
              List.of("Torso", "Torso", "Torso", "Torso", "Torso", "Torso"),
              List.of("Torso", "Torso", "Torso", "Torso", "Torso", "Torso"),
              List.of("Legs", "Left Arm", "Legs", "Left Arm", "Legs", "Legs"),
              List.of("Left Arm", "Legs", "Left Arm", "Legs", "Legs", "Legs"),
              List.of("Near Miss", "Legs", "Wings", "Legs", "Near Miss", "Legs"),
              List.of("Head", "Head", "Head", "Head", "Head", "Head"));
        for (int roll = 2; roll <= 12; roll++) {
            int row = 16 + (roll - 2) * 9;
            assertEquals(String.valueOf(roll), cells.get(row));
            assertEquals(locations.get(roll - 2), cells.subList(row + 1, row + 7));
            assertEquals(String.valueOf(Compute.calculateClusterHitTableAmount(roll, 2)), cells.get(row + 7));
            assertEquals(String.valueOf(Compute.calculateClusterHitTableAmount(roll, 5)), cells.get(row + 8));
        }
        assertEquals("*Roll 2 hits the torso if the unit has no main gun.", cells.getLast());
    }

    @Test
    void condensedProtoMekTableShowsLocationsWithoutClusterWeaponsOnlyWhenEnabledAndThereIsRoom() {
        ProtoMek biped = protoMek("Biped", false);
        noTables.setCondensedReferenceCharts(false);
        assertTrue(referenceCells(renderSmallUnitSheet(List.of(biped), noTables)).isEmpty());

        noTables.setCondensedReferenceCharts(true);
        List<String> cells = referenceCells(renderSmallUnitSheet(List.of(biped), noTables));
        assertEquals(6 + 11 * 3, cells.size());
        assertEquals(List.of(COMBINED_TABLE_TITLE, "2D6", "Biped", "Regular", "Biped", "Special",
              "2", "Torso", "Torso"), cells.subList(0, 9));

        List<ProtoMek> fullSheet = IntStream.range(0, 5).mapToObj(index -> protoMek("Biped", false)).toList();
        assertTrue(referenceCells(renderSmallUnitSheet(fullSheet, noTables)).isEmpty());
    }

    private static ProtoMek protoMek(String chassis, boolean hasMainGun) {
        ProtoMek proto = new ProtoMek();
        proto.setChassis(chassis);
        proto.setModel("");
        proto.setWeight(15);
        proto.setHasMainGun(hasMainGun);
        return proto;
    }

    private static Element renderSmallUnitSheet(List<? extends Entity> entities, RecordSheetOptions options) {
        options.setPaperSize(PaperSize.US_LETTER);
        options.setEraIcon(false);
        PageFormat page = new PageFormat();
        page.setPaper(PaperSize.US_LETTER.createPaper());
        PrintSmallUnitSheet sheet = new PrintSmallUnitSheet(entities, 0, options);
        assertTrue(sheet.createDocument(0, page, false));
        return sheet.getSVGDocument().getDocumentElement();
    }

    private static List<String> referenceCells(Element sheet) {
        var groups = sheet.getElementsByTagNameNS(PrintRecordSheet.svgNS, SVGConstants.SVG_G_TAG);
        for (int i = 0; i < groups.getLength(); i++) {
            Element group = (Element) groups.item(i);
            if ("referenceTable".equals(group.getAttribute("class"))) {
                var cells = group.getElementsByTagNameNS(PrintRecordSheet.svgNS, SVGConstants.SVG_TEXT_TAG);
                return IntStream.range(0, cells.getLength()).mapToObj(index -> cells.item(index).getTextContent())
                      .filter(cell -> !cell.isBlank()).toList();
            }
        }
        return List.of();
    }

    @Test
    void fillsSheetBA() {
        var entities = new ArrayList<>(
              List.of(new BattleArmor(), new BattleArmor(), new BattleArmor(), new BattleArmor()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new BattleArmor());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetCI() {
        var entities = new ArrayList<>(List.of(new ConvInfantry(), new ConvInfantry()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new ConvInfantry());
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new ConvInfantry());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetEmpty() {
        var entities = List.<Entity>of();
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetIllegal() {
        var heterogeneousEntities = List.of(new ConvInfantry(), new BattleArmor());
        assertThrows(IllegalArgumentException.class,
              () -> PrintSmallUnitSheet.fillsSheet(heterogeneousEntities, noTables));

        var unsupportedEntities = List.of(new SupportTank());
        assertThrows(IllegalArgumentException.class,
              () -> PrintSmallUnitSheet.fillsSheet(unsupportedEntities, noTables));
    }

    /**
     * Verify that we can process the image (basically, create the record sheet output) for an Aero with a null Engine
     * object without throwing an NPE.
     */
    @Test
    void testAeroWithoutEngineDoesNotThrowNPE() {
        // Required setting objects
        PageFormat pf = new PageFormat();
        RecordSheetOptions rso = new RecordSheetOptions();

        // Set up DS entity with required attributes
        Dropship testDS = new Dropship();
        testDS.setChassis("Test Dropship");
        testDS.setModel("TDS-999");
        testDS.setEngine(null);

        // Create print object
        PrintAero pa = new PrintDropship(testDS, 1, rso);

        // Test:
        // A) Document is created,
        // B) Engine is null,
        // C) processImage() doesn't throw.
        assertTrue(pa.createDocument(1, pf, false, true));
        assertNull(testDS.getEngine());
        pa.processImage(1, pf);
        pa.applyCoreComponentsCriticalDamage();
    }
}
