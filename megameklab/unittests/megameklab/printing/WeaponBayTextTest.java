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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Jumpship;
import megamek.common.units.Warship;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class WeaponBayTextTest {

    private final Warship ship = new Warship();

    private WeaponBayText lrmBay(int location, int... bins) {
        WeaponBayText bay = new WeaponBayText(location, false);
        bay.addBayWeapon(new WeaponMounted(ship, (WeaponType) EquipmentType.get("ISLRM20")));
        for (int shots : bins) {
            AmmoMounted ammo = (AmmoMounted) Mounted.createMounted(ship, EquipmentType.get("IS Ammo LRM-20"));
            ammo.setShotsLeft(shots);
            bay.addBayAmmo((WeaponType) EquipmentType.get("ISLRM20"), ammo);
        }
        return bay;
    }

    /** The sheet prints one total per munition, so opposite bays that total the same share a row */
    @Test
    void oppositeBaysWithEqualTotalsCombineHoweverTheirBinsSplit() {
        assertTrue(lrmBay(Jumpship.LOC_FLS, 10, 10).canCombine(lrmBay(Jumpship.LOC_FRS, 20)));
        assertFalse(lrmBay(Jumpship.LOC_FLS, 10, 10).canCombine(lrmBay(Jumpship.LOC_FRS, 10)));
    }
}
