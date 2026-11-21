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
class StringUtilsEquipmentTypeTest {

    /** E marks electronics only (TW p. 114): mine dispensers are PE (TO:AUE p. 220), magnetic clamps have no type. */
    @Test
    void onlyElectronicsPrintTheElectronicsType() throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_RIGHT_TORSO);
        Mounted<?> probe = mek.addEquipment(EquipmentType.get("ISBeagleActiveProbe"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> mines = mek.addEquipment(EquipmentType.get("ISVehicularMineDispenser"), Mek.LOC_RIGHT_TORSO);
        Mounted<?> clamps = mek.addEquipment(EquipmentType.get("ProtoMagneticClamp"), Mek.LOC_RIGHT_TORSO);

        assertEquals("[E]", StringUtils.getEquipmentInfo(mek, probe));
        assertEquals("[PE]", StringUtils.getEquipmentInfo(mek, mines));
        assertEquals("", StringUtils.getEquipmentInfo(mek, clamps));
        assertEquals("[PE]", StringUtils.getAeroEquipmentInfo(mines));
        assertEquals("", StringUtils.getAeroEquipmentInfo(clamps));
    }
}
