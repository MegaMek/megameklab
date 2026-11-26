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

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class StringUtilsOneShotTest {

    /** One-shot launchers carry the OS code (TW p. 114): a rocket launcher is "M, C, OS" (TW p. 303). */
    @Test
    void oneShotLaunchersPrintTheOneShotCode() throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_RIGHT_TORSO);
        Mounted<?> rocketLauncher = mek.addEquipment(EquipmentType.get("RL10"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> oneShotSrm = mek.addEquipment(EquipmentType.get("ISSRM6OS"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> srm = mek.addEquipment(EquipmentType.get("ISSRM6"), Mek.LOC_RIGHT_TORSO);

        assertEquals("1/Msl [M,C,OS]", StringUtils.getEquipmentInfo(mek, rocketLauncher));
        assertEquals("2/Msl [M,C,S,OS]", StringUtils.getEquipmentInfo(mek, oneShotSrm));
        assertEquals("2/Msl [M,C,S]", StringUtils.getEquipmentInfo(mek, srm));
        assertEquals("[M,C,OS]", StringUtils.getAeroEquipmentInfo(rocketLauncher));
        assertEquals("[M,C,S,OS]", StringUtils.getAeroEquipmentInfo(oneShotSrm));
    }
}
