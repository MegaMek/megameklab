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

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Jumpship;
import megamek.common.units.Warship;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class WeaponBayInventoryEntryTest {

    private final Warship ship = new Warship();

    private WeaponBayText bay(String... weapons) {
        WeaponBayText bay = new WeaponBayText(Jumpship.LOC_NOSE, false);
        for (String weapon : weapons) {
            bay.addBayWeapon(Mounted.createMounted(ship, EquipmentType.get(weapon)));
        }
        return bay;
    }

    private void load(WeaponBayText bay, String weapon, String ammo, int shots) {
        AmmoMounted bin = (AmmoMounted) Mounted.createMounted(ship, EquipmentType.get(ammo));
        bin.setShotsLeft(shots);
        bay.addBayAmmo((WeaponType) EquipmentType.get(weapon), bin);
    }

    private static List<String> ranges(WeaponBayInventoryEntry entry, int row) {
        return List.of(entry.getShortField(row), entry.getMediumField(row), entry.getLongField(row),
              entry.getExtremeField(row));
    }

    /** A medium and a large laser total 13, which is 1 at capital scale, not 1 for each laser (SO pp. 10, 147) */
    @Test
    void aStandardBayConvertsItsCombinedValue() {
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay("ISMediumLaser", "ISLargeLaser"), false);

        // A medium laser reaches only short range
        assertEquals(List.of("1 (13)", "1 (8)", "—", "—"), ranges(entry, 0));
    }

    /** ATM bays get the bay profile only with a ton of each munition per launcher (TW p. 304) */
    @Test
    void anAtmBayNeedsEachMunitionForItsBayProfile() {
        WeaponBayText standardOnly = bay("CLATM12", "CLATM12");
        load(standardOnly, "CLATM12", "Clan Ammo ATM-12", 50);
        assertEquals(List.of("4 (40)", "4 (40)", "—", "—"),
              ranges(new WeaponBayInventoryEntry(ship, 0, standardOnly, false), 0));

        WeaponBayText everyMunition = bay("CLATM12", "CLATM12");
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12", 10);
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12 ER", 10);
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12 HE", 10);
        assertEquals(List.of("6 (60)", "4 (40)", "2 (20)", "2 (20)"),
              ranges(new WeaponBayInventoryEntry(ship, 0, everyMunition, false), 0));

        WeaponBayText shortOfHighExplosive = bay("CLATM12", "CLATM12");
        load(shortOfHighExplosive, "CLATM12", "Clan Ammo ATM-12", 10);
        load(shortOfHighExplosive, "CLATM12", "Clan Ammo ATM-12 HE", 5);
        WeaponBayInventoryEntry lines = new WeaponBayInventoryEntry(ship, 0, shortOfHighExplosive, false);
        assertEquals(3, lines.nRows());
        assertEquals(List.of("", "", "", ""), ranges(lines, 0));
        assertEquals("2 ATM 12", lines.getNameField(0));
        assertEquals("Standard (10 rounds)", lines.getNameField(1));
        assertEquals(List.of("4 (40)", "4 (40)", "—", "—"), ranges(lines, 1));
        assertEquals("High Explosive (5 rounds)", lines.getNameField(2));
        assertEquals(List.of("6 (60)", "—", "—", "—"), ranges(lines, 2));
    }

    /**
     * Improved Magnetic Pulse missiles have high explosive's range at 1 point per missile (IO:AE p. 61), so they are
     * neither high explosive values nor the high explosive the ATM bay profile needs (TW p. 304)
     */
    @Test
    void improvedMagneticPulseIsNotHighExplosive() {
        WeaponBayText bay = bay("CLIATM12");
        load(bay, "CLIATM12", "Clan Ammo iATM-12", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 ER", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 5);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals(4, entry.nRows());
        assertEquals("Standard (5 rounds)", entry.getNameField(1));
        assertEquals(List.of("2 (24)", "2 (24)", "—", "—"), ranges(entry, 1));
        assertEquals("Extended Range (5 rounds)", entry.getNameField(2));
        assertEquals("Improved Magnetic Pulse (5 rounds)", entry.getNameField(3));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(entry, 3));
    }

    /** An MML bay's LRM and SRM values print on lines of their own (TW p. 303) */
    @Test
    void anMmlBayPrintsEachMunitionOnItsOwnLine() {
        WeaponBayText bay = bay("ISMML9");
        load(bay, "ISMML9", "ISMML9 LRM Ammo", 13);
        load(bay, "ISMML9", "ISMML9 SRM Ammo", 11);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        // Each line carries its own rounds, so the name prints none
        assertEquals("1 MML 9", entry.getNameField(0));
        assertEquals("LRM (13 rounds)", entry.getNameField(1));
        assertEquals(List.of("1 (5)", "1 (5)", "1 (5)", "—"), ranges(entry, 1));
        assertEquals("SRM (11 rounds)", entry.getNameField(2));
        assertEquals(List.of("1 (10)", "—", "—", "—"), ranges(entry, 2));
    }

    /** An AR10's heat, like its damage, depends on the missile it fires (TW p. 304) */
    @Test
    void anAr10BayPrintsItsHeatPerMissile() {
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay("AR10"), true);

        assertEquals("*", entry.getHeatField(0));
        assertEquals("*", entry.getShortField(0));
    }
}
