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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Dropship;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class InventoryWriterBayAmmoTest {

    /** A DropShip allocates its ammunition to the bays whose weapons use it (TM p. 195), not to every bay on a side */
    @Test
    void eachBayPrintsOnlyItsOwnAmmo() throws Exception {
        Dropship ship = new Dropship();
        WeaponMounted front = addLrmBay(ship, false, 6);
        WeaponMounted rear = addLrmBay(ship, true, 12);

        List<WeaponBayText> bays = InventoryWriter.computeWeaponBayTexts(List.of(front, rear));

        WeaponType lrm = (WeaponType) EquipmentType.get("ISLRM20");
        assertEquals(2, bays.size());
        assertEquals(Map.of("IS Ammo LRM-20", 6), totals(bays, false, lrm));
        assertEquals(Map.of("IS Ammo LRM-20", 12), totals(bays, true, lrm));
    }

    private static WeaponMounted addLrmBay(Dropship ship, boolean rear, int shots) throws Exception {
        WeaponMounted bay = (WeaponMounted) ship.addEquipment(EquipmentType.get(EquipmentTypeLookup.LRM_BAY),
              Dropship.LOC_LEFT_WING, rear);
        WeaponMounted lrm = (WeaponMounted) ship.addEquipment(EquipmentType.get("ISLRM20"), Dropship.LOC_LEFT_WING,
              rear);
        bay.addWeaponToBay(lrm);
        AmmoMounted ammo = (AmmoMounted) ship.addEquipment(EquipmentType.get("IS Ammo LRM-20"),
              Dropship.LOC_LEFT_WING);
        ammo.setShotsLeft(shots);
        bay.addAmmoToBay(ammo);
        return bay;
    }

    private static Map<String, Integer> totals(List<WeaponBayText> bays, boolean rear, WeaponType weaponType) {
        WeaponBayText bay = bays.stream().filter(text -> text.rear == rear).findFirst().orElseThrow();
        return bay.ammoTotals(weaponType, ammo -> ammo.getType().getInternalName());
    }
}
