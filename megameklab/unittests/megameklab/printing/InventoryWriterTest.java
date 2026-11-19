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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class InventoryWriterTest {

    private static List<Mounted<?>> rightTorso(String... names) throws Exception {
        Mek mek = new BipedMek();
        mek.setWeight(50);
        mek.setEngine(new Engine(200, Engine.NORMAL_ENGINE, 0));
        for (String name : names) {
            mek.addEquipment(EquipmentType.get(name), Mek.LOC_RIGHT_TORSO);
        }
        MekFileParser.postLoadInit(mek);
        return mek.getEquipment().stream().filter(mount -> mount.getLocation() == Mek.LOC_RIGHT_TORSO).toList();
    }

    /** An ATM entry prints several rows although nothing is linked to the launcher */
    @Test
    void anAtmNamesNothingOnItsSecondRow() throws Exception {
        assertNull(InventoryWriter.secondRowMount(rightTorso("CLATM6").get(0)));
    }

    @Test
    void anMmlPrintsItsArtemisInline() throws Exception {
        assertNull(InventoryWriter.secondRowMount(rightTorso("ISMML5", "ISArtemisIV").get(0)));
    }

    @Test
    void aLaserWithAnInsulatorAndAPulseModuleNamesTheModule() throws Exception {
        List<Mounted<?>> mounts = rightTorso("Medium Laser", "ISLaserInsulator", "ISRISCLaserPulseModule");

        assertSame(mounts.get(2), InventoryWriter.secondRowMount(mounts.get(0)));
    }
}
