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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.units.ProtoMek;
import megamek.common.units.SupportTank;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class PrintSmallUnitSheetTest {
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
        var entities = new ArrayList<>(List.of(new Infantry(), new Infantry()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new Infantry());
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new Infantry());
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
        var heterogeneousEntities = List.of(new Infantry(), new BattleArmor());
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
