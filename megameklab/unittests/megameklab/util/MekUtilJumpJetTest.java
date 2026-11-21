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
package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import megamek.common.TechConstants;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class MekUtilJumpJetTest {

    /** A 60-ton FrankenMek whose center torso comes from a 60-ton donor, at the given engine rating */
    private static Mek frankenMek(int engineRating) {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        mek.setWeight(60);
        mek.setEngine(new Engine(engineRating, Engine.NORMAL_ENGINE, 0));
        mek.setFrankenMek(true);
        mek.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 60);
        mek.setFrankenMekStructureTonnage(Mek.LOC_RIGHT_TORSO, 35);
        return mek;
    }

    private static void addJets(Mek mek, int location, int count) throws Exception {
        for (int jet = 0; jet < count; jet++) {
            mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.JUMP_JET), location);
        }
    }

    /** Six half-ton jets give a 60-ton FrankenMek Jump 3 (CO p. 213), so the spinner reaches six jets at Walk 3 */
    @Test
    void oneJetMayComeForEachMissingJumpMP() throws Exception {
        Mek mek = frankenMek(180);
        addJets(mek, Mek.LOC_RIGHT_TORSO, 3);
        // Three half-ton jets give Jump 1, so two more jets may come for the two missing MP
        assertEquals(5, MekUtil.maxJumpJets(mek, 3));

        addJets(mek, Mek.LOC_RIGHT_TORSO, 3);
        assertEquals(3, mek.getInstalledJumpJetMP());
        assertEquals(6, MekUtil.maxJumpJets(mek, 3));
    }

    /**
     * Over the limit, jets come off the front of the list until the rest stay within it: two full jets and four
     * half-ton ones give Jump 4; at Walk 3, losing one full jet leaves five jets for Jump 3 (TM p. 51, CO p. 213)
     */
    @Test
    void overTheLimitKeepsAsManyJetsAsStayWithinIt() throws Exception {
        Mek mek = frankenMek(240);
        addJets(mek, Mek.LOC_CENTER_TORSO, 2);
        addJets(mek, Mek.LOC_RIGHT_TORSO, 4);
        assertEquals(4, mek.getInstalledJumpJetMP());

        mek.setEngine(new Engine(180, Engine.NORMAL_ENGINE, 0));

        assertEquals(5, MekUtil.maxJumpJets(mek, 3));
    }

    /** Full-weight jets each give one MP, so at Walk 3 four center torso jets are cut to three (TM p. 51) */
    @Test
    void fullWeightJetsOverTheLimitAreCutToTheLimit() throws Exception {
        Mek mek = frankenMek(180);
        addJets(mek, Mek.LOC_CENTER_TORSO, 4);

        assertEquals(3, MekUtil.maxJumpJets(mek, 3));
    }

    /** Removing jump jets leaves a mechanical jump booster listed before them, which is not a jet */
    @Test
    void removingJumpJetsKeepsMechanicalJumpBoosters() throws Exception {
        Mek mek = new BipedMek();
        mek.setWeight(60);
        mek.setEngine(new Engine(180, Engine.NORMAL_ENGINE, 0));
        mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.MECHANICAL_JUMP_BOOSTER), Mek.LOC_LEFT_LEG);
        addJets(mek, Mek.LOC_CENTER_TORSO, 3);

        MekUtil.removeJumpJets(mek, mek.getJumpJetCount());

        assertEquals(0, mek.getJumpJetCount());
        assertEquals(1, mek.getMisc().stream().filter(misc -> misc.getType().hasFlag(MiscType.F_JUMP_BOOSTER)).count());
    }
}
