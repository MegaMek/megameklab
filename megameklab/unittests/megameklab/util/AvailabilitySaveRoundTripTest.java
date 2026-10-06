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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.loaders.MtfFile;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.ForceGeneratorAvailability;
import megamek.common.units.Mek;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Proves that what the Availability tab puts on a unit actually reaches the file MegaMekLab writes.
 * <p>
 * MegaMekLab does not write units itself; it hands them to {@code UnitUtil.saveUnitToString()}. The tab's own tests
 * cover the table and the round-trip through the file format, but neither of them would notice if MegaMekLab's saver
 * dropped the lines on the way out. This drives the real save path.
 * </p>
 */
class AvailabilitySaveRoundTripTest {

    @BeforeAll
    static void beforeAll() {
        EquipmentType.initializeTypes();
    }

    private static Mek buildMek() {
        Mek mek = new BipedMek();
        mek.setChassis("Grimjack");
        mek.setModel("GRM-1A");
        mek.setYear(3049);
        mek.setWeight(20.0);
        mek.setEngine(new Engine(100, Engine.NORMAL_ENGINE, 0));

        return mek;
    }

    @Test
    void availabilitySetOnTheTabReachesTheSavedFile() {
        Mek mek = buildMek();
        mek.setForceGeneratorAvailability(List.of(
              ForceGeneratorAvailability.parse("FS:5,LA:3"),
              ForceGeneratorAvailability.parse("3067-3085 FS:7")));
        mek.setMissionRoles("fire_support,urban");

        String saved = UnitUtil.saveUnitToString(mek, false);

        assertTrue(saved.contains("availability:FS:5,LA:3"),
              "MegaMekLab's saver must write the availability line");
        assertTrue(saved.contains("availability:3067-3085 FS:7"));
        assertTrue(saved.contains("missionroles:fire_support,urban"));
    }

    @Test
    void theSavedFileLoadsBackWithTheSameAvailability() throws Exception {
        Mek mek = buildMek();
        mek.setForceGeneratorAvailability(List.of(ForceGeneratorAvailability.parse("3067-3085 FS:7,LA:6")));
        mek.setMissionRoles("fire_support");

        String saved = UnitUtil.saveUnitToString(mek, false);
        Entity reloaded = new MtfFile(new ByteArrayInputStream(saved.getBytes(StandardCharsets.UTF_8))).getEntity();

        assertEquals(1, reloaded.getForceGeneratorAvailability().size());
        ForceGeneratorAvailability entry = reloaded.getForceGeneratorAvailability().getFirst();
        assertEquals(3067, entry.startYear());
        assertEquals(3085, entry.endYear());
        assertEquals("FS:7,LA:6", entry.availabilityCodes());
        assertEquals("fire_support", reloaded.getMissionRoles());
    }

    @Test
    void aUnitWithNoAvailabilityWritesNoAvailabilityLine() {
        // Every existing unit goes through this saver. None of them should grow a new line.
        String saved = UnitUtil.saveUnitToString(buildMek(), false);

        assertFalse(saved.contains("availability:"));
        assertFalse(saved.contains("missionroles:"));
    }
}
