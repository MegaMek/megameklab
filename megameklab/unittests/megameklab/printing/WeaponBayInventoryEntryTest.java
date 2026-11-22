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

    /** Improved Magnetic Pulse missiles don't count toward the ATM bay profile (TW p. 304), so they print under it */
    @Test
    void aProfileBayPrintsItsImprovedMagneticPulseLineUnderTheProfile() {
        WeaponBayText bay = bay("CLIATM12");
        load(bay, "CLIATM12", "Clan Ammo iATM-12", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 ER", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 HE", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 5);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals(2, entry.nRows());
        assertEquals("1 Improved ATM 12 (5 Standard, 5 Extended Range, 5 High Explosive)", entry.getNameField(0));
        assertEquals(List.of("4 (36)", "2 (24)", "1 (12)", "1 (12)"), ranges(entry, 0));
        assertEquals("Improved Magnetic Pulse (5 rounds)", entry.getNameField(1));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(entry, 1));
    }

    /** An ATM can't fire an iATM's Improved Magnetic Pulse missiles (IO:AE p. 61), so it adds nothing to their line */
    @Test
    void aLauncherAddsNothingToAMunitionLineItCannotFire() {
        WeaponBayText bay = bay("CLATM12", "CLIATM12");
        load(bay, "CLATM12", "Clan Ammo ATM-12 HE", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12", 5);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 5);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals(5, entry.nRows());
        assertEquals("Standard (5 rounds)", entry.getNameField(2));
        assertEquals(List.of("2 (24)", "2 (24)", "—", "—"), ranges(entry, 2));
        assertEquals("High Explosive (5 rounds)", entry.getNameField(3));
        assertEquals(List.of("3 (30)", "—", "—", "—"), ranges(entry, 3));
        assertEquals("Improved Magnetic Pulse (5 rounds)", entry.getNameField(4));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(entry, 4));
    }

    /** A bay's only munition prints by name, as its values are that munition's, unless it is the launcher's first */
    @Test
    void aBaysOnlyMunitionPrintsByNameUnlessItIsTheFirst() {
        WeaponBayText lrm = bay("ISMML9");
        load(lrm, "ISMML9", "ISMML9 LRM Ammo", 13);
        assertEquals("1 MML 9 (13 rounds)", new WeaponBayInventoryEntry(ship, 0, lrm, false).getNameField(0));

        WeaponBayText srm = bay("ISMML9");
        load(srm, "ISMML9", "ISMML9 SRM Ammo", 11);
        WeaponBayInventoryEntry srmEntry = new WeaponBayInventoryEntry(ship, 0, srm, false);
        assertEquals(1, srmEntry.nRows());
        assertEquals("1 MML 9 (11 SRM)", srmEntry.getNameField(0));
        assertEquals(List.of("1 (10)", "—", "—", "—"), ranges(srmEntry, 0));

        WeaponBayText extendedRange = bay("CLATM12", "CLATM12");
        load(extendedRange, "CLATM12", "Clan Ammo ATM-12 ER", 10);
        WeaponBayInventoryEntry extendedRangeEntry = new WeaponBayInventoryEntry(ship, 0, extendedRange, false);
        assertEquals("2 ATM 12 (10 Extended Range)", extendedRangeEntry.getNameField(0));
        assertEquals(List.of("2 (20)", "2 (20)", "2 (20)", "2 (20)"), ranges(extendedRangeEntry, 0));

        WeaponBayText pulse = bay("CLIATM12");
        load(pulse, "CLIATM12", "Clan Ammo iATM-12 IMP", 5);
        WeaponBayInventoryEntry pulseEntry = new WeaponBayInventoryEntry(ship, 0, pulse, false);
        assertEquals("1 Improved ATM 12 (5 Improved Magnetic Pulse)", pulseEntry.getNameField(0));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(pulseEntry, 0));
    }

    /** A bay's name carries all the ammo allocated to it (TM p. 195), one total per munition, not its first bin */
    @Test
    void aBayNamePrintsEveryBinsRounds() {
        WeaponBayText twoBins = bay("CLATM12", "CLATM12");
        load(twoBins, "CLATM12", "Clan Ammo ATM-12", 25);
        load(twoBins, "CLATM12", "Clan Ammo ATM-12", 25);
        assertEquals("2 ATM 12 (50 rounds)", new WeaponBayInventoryEntry(ship, 0, twoBins, false).getNameField(0));

        WeaponBayText profile = bay("CLATM12", "CLATM12");
        load(profile, "CLATM12", "Clan Ammo ATM-12", 10);
        load(profile, "CLATM12", "Clan Ammo ATM-12 ER", 10);
        load(profile, "CLATM12", "Clan Ammo ATM-12 HE", 10);
        assertEquals("2 ATM 12 (10 Standard, 10 Extended Range, 10 High Explosive)",
              new WeaponBayInventoryEntry(ship, 0, profile, false).getNameField(0));

        WeaponBayText ar10 = bay("AR10");
        load(ar10, "AR10", "Ammo AR10 Barracuda", 10);
        load(ar10, "AR10", "Ammo AR10 Killer Whale", 5);
        load(ar10, "AR10", "Ammo AR10 Barracuda", 10);
        assertEquals("1 AR10 (20 B, 5 KW)", new WeaponBayInventoryEntry(ship, 0, ar10, true).getNameField(0));
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

    /** Each launcher that fires spends a round, so one SRM round left lets one of three MML 9s fire it (TW p. 103) */
    @Test
    void aMunitionLineFiresNoMoreLaunchersThanItHasRounds() {
        WeaponBayText bay = bay("ISMML9", "ISMML9", "ISMML9");
        load(bay, "ISMML9", "ISMML9 LRM Ammo", 13);
        load(bay, "ISMML9", "ISMML9 SRM Ammo", 1);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals("SRM (1 rounds)", entry.getNameField(2));
        assertEquals(List.of("1 (10)", "—", "—", "—"), ranges(entry, 2));
    }

    /**
     * A bay fires its rapid-fire weapons at their maximum rate (TW p. 238), each shot making the weapon's heat (TW p.
     * 114): two rotary AC/5s make 12, two Ultra AC/5s 4
     */
    @Test
    void aBayFiresItsRapidFireWeaponsAtTheirMaximumRate() {
        WeaponBayText rotary = bay("ISRotaryAC5", "ISRotaryAC5");
        WeaponBayText ultra = bay("ISUltraAC5", "ISUltraAC5");

        assertEquals("12", new WeaponBayInventoryEntry(ship, 0, rotary, false).getHeatField(0));
        assertEquals("4", new WeaponBayInventoryEntry(ship, 0, ultra, false).getHeatField(0));
    }

    /** An AR10's heat, like its damage, depends on the missile it fires (TW p. 304) */
    @Test
    void anAr10BayPrintsItsHeatPerMissile() {
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay("AR10"), true);

        assertEquals("*", entry.getHeatField(0));
        assertEquals("*", entry.getShortField(0));
    }
}
