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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponMounted;
import megamek.common.units.Aero;
import megamek.common.units.Warship;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InitializeTypes.class)
class InventoryWriterBayAmmoTest {
    @Test
    void usesCompatibleAmmoFromTheActualBay() throws Exception {
        Warship ship = new Warship();
        WeaponMounted bay = (WeaponMounted) ship.addEquipment(
              EquipmentType.get("Capital AC Bay"), Aero.LOC_NOSE);
        WeaponMounted weapon = (WeaponMounted) ship.addEquipment(
              EquipmentType.get("NAC20"), Aero.LOC_NOSE);
        bay.addWeaponToBay(weapon);
        AmmoMounted differentRack = (AmmoMounted) ship.addEquipment(
              EquipmentType.get("Ammo NAC/35"), Aero.LOC_NOSE);
        differentRack.setShotsLeft(20);
        bay.addAmmoToBay(differentRack);
        AmmoMounted differentType = (AmmoMounted) ship.addEquipment(
              EquipmentType.get("IS Ammo AC/20"), Aero.LOC_NOSE);
        differentType.setShotsLeft(20);
        bay.addAmmoToBay(differentType);
        AmmoMounted unrelatedBay = (AmmoMounted) ship.addEquipment(
              EquipmentType.get("Ammo NAC/20"), Aero.LOC_NOSE);
        unrelatedBay.setShotsLeft(10);
        AmmoMounted correct = (AmmoMounted) ship.addEquipment(
              EquipmentType.get("Ammo NAC/20"), Aero.LOC_NOSE);
        correct.setShotsLeft(30);
        bay.addAmmoToBay(correct);

        List<WeaponBayText> rows = InventoryWriter.computeWeaponBayTexts(List.of(bay));
        assertEquals(1, rows.size());
        assertEquals(List.of(correct), rows.getFirst().weaponAmmo.get(weapon.getType()));
        assertEquals(30, rows.getFirst().weaponAmmo.get(weapon.getType()).getFirst().getBaseShotsLeft());
    }

    @Test
    void ammoMatchingIgnoresOrderButPreservesDuplicateBinCountsAndIndividualShots() throws Exception {
        Warship ship = new Warship();
        var left = bay(ship, Warship.LOC_LBS, false, 1, 10, 10, 20);
        var reordered = bay(ship, Warship.LOC_RBS, false, 1, 20, 10, 10);
        var differentCounts = bay(ship, Warship.LOC_RBS, false, 1, 20, 20, 10);
        var sameTotal = bay(ship, Warship.LOC_RBS, false, 1, 15, 15, 10);

        assertEquals(1, InventoryWriter.computeWeaponBayTexts(List.of(left, reordered)).size());
        assertEquals(2, InventoryWriter.computeWeaponBayTexts(List.of(left, differentCounts)).size());
        assertEquals(2, InventoryWriter.computeWeaponBayTexts(List.of(left, sameTotal)).size());
    }

    @Test
    void repeatedWeaponsDoNotDuplicateAmmoAndEmptyBinsAreOmitted() throws Exception {
        Warship ship = new Warship();
        var bay = bay(ship, Warship.LOC_LBS, false, 3, 0, 10, 20);
        var row = InventoryWriter.computeWeaponBayTexts(List.of(bay)).getFirst();
        var weapon = bay.getBayWeapons().getFirst().getType();

        assertEquals(3, row.weapons.get(weapon));
        assertEquals(bay.getBayAmmo().subList(1, 3), row.weaponAmmo.get(weapon));
    }

    @Test
    void weaponAndAugmentationCountsArePartOfTheCombination() throws Exception {
        Warship ship = new Warship();
        var left = bay(ship, Warship.LOC_LBS, false, 2, 10);
        var right = bay(ship, Warship.LOC_RBS, false, 2, 10);
        var single = bay(ship, Warship.LOC_RBS, false, 1, 10);
        var leftLink = ship.addEquipment(EquipmentType.get("ISPPCCapacitor"), Warship.LOC_LBS);
        leftLink.setLinked(left.getBayWeapons().getFirst());
        assertEquals(2, InventoryWriter.computeWeaponBayTexts(List.of(left, right)).size());
        var rightLink = ship.addEquipment(EquipmentType.get("ISPPCCapacitor"), Warship.LOC_RBS);
        rightLink.setLinked(right.getBayWeapons().getFirst());
        assertEquals(1, InventoryWriter.computeWeaponBayTexts(List.of(left, right)).size());
        var singleLink = ship.addEquipment(EquipmentType.get("ISPPCCapacitor"), Warship.LOC_RBS);
        singleLink.setLinked(single.getBayWeapons().getFirst());
        assertEquals(2, InventoryWriter.computeWeaponBayTexts(List.of(left, single)).size());
        var extraLink = ship.addEquipment(EquipmentType.get("ISPPCCapacitor"), Warship.LOC_RBS);
        extraLink.setLinked(right.getBayWeapons().getLast());
        assertEquals(2, InventoryWriter.computeWeaponBayTexts(List.of(left, right)).size());
    }

    @Test
    void frontSidesRequireMatchingRearFlagsAndRowsCombineOnlyOnce() throws Exception {
        Warship ship = new Warship();
        var left = bay(ship, Warship.LOC_FLS, false, 1);
        var rightRear = bay(ship, Warship.LOC_FRS, true, 1);
        var right = bay(ship, Warship.LOC_FRS, false, 1);
        var secondRight = bay(ship, Warship.LOC_FRS, false, 1);
        var rows = InventoryWriter.computeWeaponBayTexts(List.of(left, rightRear, right, secondRight));

        assertEquals(List.of(List.of(Warship.LOC_FLS, Warship.LOC_FRS), List.of(Warship.LOC_FRS),
              List.of(Warship.LOC_FRS)), rows.stream().map(row -> row.loc).toList());
        assertEquals(1, rows.stream().filter(row -> row.rear).count());
    }

    @Test
    void broadsidePairingRetainsTheFirstBayRegardlessOfRearFlag() throws Exception {
        Warship ship = new Warship();
        var leftRear = bay(ship, Warship.LOC_LBS, true, 1, 10, 20);
        var left = bay(ship, Warship.LOC_LBS, false, 1, 20, 10);
        var rightRear = bay(ship, Warship.LOC_RBS, true, 1, 20, 10);
        var right = bay(ship, Warship.LOC_RBS, false, 1, 10, 20);
        for (var bays : List.of(List.of(leftRear, left, right, rightRear), List.of(rightRear, right, left, leftRear))) {
            var rows = InventoryWriter.computeWeaponBayTexts(bays);
            assertEquals(2, rows.size());
            for (int i = 0; i < rows.size(); i++) {
                assertEquals(List.of(Warship.LOC_LBS, Warship.LOC_RBS), rows.get(i).loc);
                assertEquals(bays.get(i).isRearMounted(), rows.get(i).rear);
                assertEquals(bays.get(i).getBayAmmo(), rows.get(i).weaponAmmo.get(bays.get(i).getBayWeapons().getFirst().getType()));
            }
        }
    }

    private static WeaponMounted bay(Warship ship, int location, boolean rear, int weapons, int... shots)
          throws Exception {
        var bay = (WeaponMounted) ship.addEquipment(EquipmentType.get("Capital AC Bay"), location, rear);
        for (int i = 0; i < weapons; i++) {
            bay.addWeaponToBay((WeaponMounted) ship.addEquipment(EquipmentType.get("NAC20"), location));
        }
        for (int count : shots) {
            var ammo = (AmmoMounted) ship.addEquipment(EquipmentType.get("Ammo NAC/20"), location);
            ammo.setShotsLeft(count);
            bay.addAmmoToBay(ammo);
        }
        return bay;
    }

}
