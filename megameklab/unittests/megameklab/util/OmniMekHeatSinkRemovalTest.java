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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Lowering an OmniMek's heat sinks and raising them again keeps its fixed heat sinks. The Black Hawk-KU BHKU-OE has 19
 * double heat sinks: 12 in the engine and one placed in each side torso are fixed, the other five are pods. Lowering it
 * to 10 used to take the two fixed side torso heat sinks before the engine's, and the heat sinks added back became
 * pods, so a MekHQ refit to the result was Class F (MegaMekLab #2400). The BHKU-OE is a pinned copy from mm-data.
 */
@ExtendWith(value = InitializeTypes.class)
class OmniMekHeatSinkRemovalTest {
    private static final String BLACK_HAWK_FILE = "Black Hawk-KU BHKU-OE.mtf";

    private Mek loadBlackHawk() throws IOException, EntityLoadingException {
        try (InputStream inputStream = getClass().getResourceAsStream("/" + BLACK_HAWK_FILE)) {
            assertNotNull(inputStream, BLACK_HAWK_FILE);
            return (Mek) new MekFileParser(inputStream, BLACK_HAWK_FILE).getEntity();
        }
    }

    private static void setHeatSinks(Mek mek, int count) {
        int currentSinks = MekUtil.countActualHeatSinks(mek);
        if (count < currentSinks) {
            MekUtil.removeHeatSinks(mek, currentSinks - count);
        } else if (count > currentSinks) {
            MekUtil.addHeatSinkMounts(mek, count - currentSinks, EquipmentType.get("ISDoubleHeatSink"));
        }
        mek.resetSinks();
    }

    private static List<Integer> placedFixedHeatSinkLocations(Mek mek) {
        return mek.getMisc()
              .stream()
              .filter(UnitUtil::isHeatSink)
              .filter(mounted -> mounted.getLocation() != Entity.LOC_NONE)
              .filter(mounted -> !mounted.isOmniPodMounted())
              .map(Mounted::getLocation)
              .sorted()
              .toList();
    }

    private static long podHeatSinks(Mek mek) {
        return mek.getMisc().stream().filter(UnitUtil::isHeatSink).filter(Mounted::isOmniPodMounted).count();
    }

    @Test
    void loweringBelowTheFixedHeatSinksAndRaisingAgainKeepsThem() throws IOException, EntityLoadingException {
        Mek blackHawk = loadBlackHawk();
        assertEquals(List.of(Mek.LOC_RIGHT_TORSO, Mek.LOC_LEFT_TORSO), placedFixedHeatSinkLocations(blackHawk));

        setHeatSinks(blackHawk, 10);
        setHeatSinks(blackHawk, 16);
        MekUtil.fillInAllEquipment(blackHawk);

        assertEquals(16, MekUtil.countActualHeatSinks(blackHawk));
        assertEquals(List.of(Mek.LOC_RIGHT_TORSO, Mek.LOC_LEFT_TORSO), placedFixedHeatSinkLocations(blackHawk));
        assertEquals(2, podHeatSinks(blackHawk), "Only the heat sinks beyond the base chassis are pods");
    }

    @Test
    void loweringAnOmniMekTakesItsPodHeatSinksFirst() throws IOException, EntityLoadingException {
        Mek blackHawk = loadBlackHawk();

        setHeatSinks(blackHawk, 14);

        assertEquals(0, podHeatSinks(blackHawk));
        assertEquals(List.of(Mek.LOC_RIGHT_TORSO, Mek.LOC_LEFT_TORSO), placedFixedHeatSinkLocations(blackHawk));
    }

    @Test
    void loweringAStandardMekStillTakesPlacedHeatSinksBeforeTheEngines() throws IOException, EntityLoadingException {
        Mek blackHawk = loadBlackHawk();
        blackHawk.setOmni(false);
        for (Mounted<?> mounted : blackHawk.getEquipment()) {
            mounted.setOmniPodMounted(false);
        }

        setHeatSinks(blackHawk, 12);

        assertEquals(List.of(), placedFixedHeatSinkLocations(blackHawk), "The freed slots come from placed heat sinks");
    }
}
