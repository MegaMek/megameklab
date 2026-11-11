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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Editing an OmniMek's pods in MegaMekLab keeps its base chassis: the fixed equipment stays fixed and the pods stay
 * pods. A design whose fixed equipment silently became pods altered the base chassis, and a MekHQ refit to it became
 * Class F (MegaMekLab #2400, MekHQ #10981). Two edits did that: lowering the heat sinks below the fixed ones and raising
 * them again, and moving fixed equipment out of its slots and placing it again, as Reset and Fill do.
 *
 * <p>The Black Hawk-KU BHKU-OE, a pinned copy from mm-data, has 19 double heat sinks: 12 in the engine and one placed
 * in each side torso are fixed, the other five are pods. Its five jump jets are fixed.</p>
 */
@ExtendWith(value = InitializeTypes.class)
class OmniMekBaseChassisTest {
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

    private static long fixedJumpJets(Mek mek) {
        return mek.getMisc().stream().filter(UnitUtil::isJumpJet).filter(mounted -> !mounted.isOmniPodMounted()).count();
    }

    private static Mounted<?> firstPlacedHeatSink(Mek mek, int location, boolean isPod) {
        return mek.getMisc()
              .stream()
              .filter(UnitUtil::isHeatSink)
              .filter(mounted -> mounted.getLocation() == location)
              .filter(mounted -> mounted.isOmniPodMounted() == isPod)
              .findFirst()
              .orElseThrow();
    }

    private static void unplace(Mek mek, Mounted<?> mounted) {
        UnitUtil.removeCriticalSlots(mek, mounted);
        UnitUtil.changeMountStatus(mek, mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
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

    @Test
    void resettingTheSlotsAndFillingKeepsTheFixedEquipmentFixed() throws IOException, EntityLoadingException {
        Mek blackHawk = loadBlackHawk();

        UnitUtil.removeAllCriticalSlots(blackHawk);
        MekUtil.fillInAllEquipment(blackHawk);

        assertEquals(5, fixedJumpJets(blackHawk));
        assertEquals(2, placedFixedHeatSinkLocations(blackHawk).size());
        assertEquals(5, podHeatSinks(blackHawk), "The pod heat sinks stay pods");
    }

    @Test
    void placingAFixedHeatSinkAgainKeepsItFixed() throws IOException, EntityLoadingException, LocationFullException {
        Mek blackHawk = loadBlackHawk();
        Mounted<?> fixedHeatSink = firstPlacedHeatSink(blackHawk, Mek.LOC_LEFT_TORSO, false);

        unplace(blackHawk, fixedHeatSink);
        UnitUtil.addMounted(blackHawk, fixedHeatSink, Mek.LOC_LEFT_TORSO, false);

        assertFalse(fixedHeatSink.isOmniPodMounted());
    }

    @Test
    void placingAPodHeatSinkAgainKeepsItAPod() throws IOException, EntityLoadingException, LocationFullException {
        Mek blackHawk = loadBlackHawk();
        Mounted<?> podHeatSink = firstPlacedHeatSink(blackHawk, Mek.LOC_LEFT_TORSO, true);

        unplace(blackHawk, podHeatSink);
        UnitUtil.addMounted(blackHawk, podHeatSink, Mek.LOC_RIGHT_ARM, false);

        assertTrue(podHeatSink.isOmniPodMounted());
    }

    @Test
    void heatSinksAddedBeyondTheEngineArePods() throws IOException, EntityLoadingException {
        Mek blackHawk = loadBlackHawk();

        setHeatSinks(blackHawk, 14);
        setHeatSinks(blackHawk, 16);

        assertEquals(2, podHeatSinks(blackHawk));
        assertEquals(List.of(Mek.LOC_RIGHT_TORSO, Mek.LOC_LEFT_TORSO), placedFixedHeatSinkLocations(blackHawk));
    }
}
