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
package megameklab.ui.generalUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import megamek.common.TechConstants;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class MovementViewTest {

    /** Six half-ton jets give a 60-ton FrankenMek Jump 3 (CO p. 213), so the jet spinner must reach six. */
    @Test
    void frankenMekJetSpinnerCountsTheMovementLighterJetsGive() throws Exception {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        mek.setWeight(60);
        mek.setEngine(new Engine(180, Engine.NORMAL_ENGINE, 0));
        mek.setFrankenMek(true);
        mek.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 60);
        mek.setFrankenMekStructureTonnage(Mek.LOC_RIGHT_TORSO, 35);
        for (int jet = 0; jet < 3; jet++) {
            mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.JUMP_JET), Mek.LOC_RIGHT_TORSO);
        }
        ITechManager techManager = mock(ITechManager.class);
        when(techManager.isLegal(any())).thenReturn(true);
        MovementView view = new MovementView(techManager);

        view.setFromEntity(mek);
        // Three half-ton jets give Jump 1, so two more jets may come for the two missing MP
        assertEquals(5, jetSpinnerMaximum(view));

        for (int jet = 0; jet < 3; jet++) {
            mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.JUMP_JET), Mek.LOC_RIGHT_TORSO);
        }
        view.setFromEntity(mek);
        assertEquals(3, mek.getOriginalJumpMP(true));
        assertEquals(6, jetSpinnerMaximum(view));
    }

    /**
     * A FrankenMek may mount only as many full-weight jets as its Walking MP (TM p. 51). MegaMek holds its Jump MP to
     * Walk 3, but four center torso jets are still over the limit, so the spinner cuts them to three.
     */
    @Test
    void frankenMekJetsOverTheLimitAreCut() throws Exception {
        Mek mek = new BipedMek();
        mek.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        mek.setWeight(60);
        mek.setEngine(new Engine(180, Engine.NORMAL_ENGINE, 0));
        mek.setFrankenMek(true);
        mek.setFrankenMekStructureTonnage(Mek.LOC_CENTER_TORSO, 60);
        for (int jet = 0; jet < 4; jet++) {
            mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.JUMP_JET), Mek.LOC_CENTER_TORSO);
        }
        ITechManager techManager = mock(ITechManager.class);
        when(techManager.isLegal(any())).thenReturn(true);
        MovementView view = new MovementView(techManager);

        view.setFromEntity(mek);

        assertEquals(3, mek.getOriginalJumpMP(true));
        assertEquals(3, jetSpinnerMaximum(view));
    }

    private static int jetSpinnerMaximum(MovementView view) {
        // The view's spinners, in layout order: walk, jump jets, mechanical jump boosters
        JSpinner jets = Arrays.stream(view.getComponents())
              .filter(JSpinner.class::isInstance)
              .map(JSpinner.class::cast)
              .skip(1)
              .findFirst()
              .orElseThrow();
        return ((Number) ((SpinnerNumberModel) jets.getModel()).getMaximum()).intValue();
    }
}
