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
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class StringUtilsEquipmentTypeTest {

    /**
     * E marks electronics (TW p. 114): mine dispensers and the minesweeper are PE (TO:AUE p. 220), as is the ProtoMek
     * magnetic clamp system (IO:AE equipment table); battle armor magnetic clamps have no type.
     */
    @Test
    void onlyElectronicsPrintTheElectronicsType() throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_RIGHT_TORSO);
        Mounted<?> probe = mek.addEquipment(EquipmentType.get("ISBeagleActiveProbe"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> mines = mek.addEquipment(EquipmentType.get("ISVehicularMineDispenser"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> clampSystem = mek.addEquipment(EquipmentType.get("ProtoMagneticClamp"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> armorClamps = mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.BA_MAGNETIC_CLAMP),
              Mek.LOC_RIGHT_TORSO);
        Mounted<?> minesweeper = mek.addEquipment(EquipmentType.get("ISMineSweeper"), Mek.LOC_RIGHT_TORSO);

        assertEquals("[E]", StringUtils.getEquipmentInfo(mek, probe));
        assertEquals("[PE]", StringUtils.getEquipmentInfo(mek, mines));
        assertEquals("[PE]", StringUtils.getEquipmentInfo(mek, clampSystem));
        assertEquals("", StringUtils.getEquipmentInfo(mek, armorClamps));
        assertEquals("[PE]", StringUtils.getEquipmentInfo(mek, minesweeper));
        assertEquals("[PE]", StringUtils.getAeroEquipmentInfo(mines));
        assertEquals("[PE]", StringUtils.getAeroEquipmentInfo(clampSystem));
    }
}
