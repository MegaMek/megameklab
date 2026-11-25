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
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Aero;
import megamek.common.units.Dropship;
import megamek.common.units.Jumpship;
import megamek.common.units.Warship;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.UnitUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(value = InitializeTypes.class)
class WeaponBayInventoryEntryTest {

    private final Warship ship = new Warship();

    private WeaponBayText bay(String... weapons) {
        WeaponBayText bay = new WeaponBayText(Jumpship.LOC_NOSE, false);
        for (String weapon : weapons) {
            bay.addBayWeapon(new WeaponMounted(ship, (WeaponType) EquipmentType.get(weapon)));
        }
        return bay;
    }

    private static WeaponBayInventoryEntry printedBay(Aero ship, WeaponMounted bay) {
        WeaponBayText text = InventoryWriter.computeWeaponBayTexts(List.of(bay)).getFirst();
        return new WeaponBayInventoryEntry(ship, 0, text, bay.getType().isCapital());
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
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12", 20);
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12 ER", 20);
        load(everyMunition, "CLATM12", "Clan Ammo ATM-12 HE", 20);
        assertEquals(List.of("6 (60)", "4 (40)", "2 (20)", "2 (20)"),
              ranges(new WeaponBayInventoryEntry(ship, 0, everyMunition, false), 0));
    }

    /**
     * A large craft's ATMs fire no special munitions without "at least 10 shots of each ammo type per ATM launcher"
     * (TM p. 194): the Arcadia's nose carries 10 of each for two ATM 12s, so it prints its standard missiles' values, as
     * its record sheet does
     */
    @Test
    void anAtmBayShortOfTenShotsPerLauncherFiresOnlyStandardMissiles() {
        WeaponBayText nose = bay("CLATM12", "CLATM12");
        load(nose, "CLATM12", "Clan Ammo ATM-12", 10);
        load(nose, "CLATM12", "Clan Ammo ATM-12 ER", 10);
        load(nose, "CLATM12", "Clan Ammo ATM-12 HE", 10);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, nose, false);

        assertEquals(1, entry.nRows());
        assertEquals(List.of("4 (40)", "4 (40)", "—", "—"), ranges(entry, 0));
    }

    /**
     * A bay's ATMs fire Extended Range and High Explosive missiles only as the bay profile, which needs a ton of each per
     * launcher (TW p. 304): two ATM 3s with 20 shots of each have 10 per launcher (TM p. 194) but one ton between them,
     * so they print their standard values, and with 40 of each the profile
     */
    @Test
    void anAtmBayWithoutATonOfEachPerLauncherPrintsItsStandardValues() {
        WeaponBayText oneTon = bay("CLATM3", "CLATM3");
        WeaponBayText twoTons = bay("CLATM3", "CLATM3");
        for (String munition : List.of("", " ER", " HE")) {
            load(oneTon, "CLATM3", "Clan Ammo ATM-3" + munition, 20);
            load(twoTons, "CLATM3", "Clan Ammo ATM-3" + munition, 40);
        }
        WeaponBayInventoryEntry standard = new WeaponBayInventoryEntry(ship, 0, oneTon, false);

        assertEquals(1, standard.nRows());
        assertEquals(List.of("1 (8)", "1 (8)", "—", "—"), ranges(standard, 0));
        assertEquals(List.of("1 (12)", "1 (8)"),
              ranges(new WeaponBayInventoryEntry(ship, 0, twoTons, false), 0).subList(0, 2));
    }

    /** Improved Magnetic Pulse missiles need 10 shots per launcher of their own (TM p. 194) before they print a line */
    @Test
    void improvedMagneticPulseNeedsTenShotsPerLauncher() {
        WeaponBayText bay = bay("CLIATM12");
        for (String munition : List.of("", " ER", " HE")) {
            load(bay, "CLIATM12", "Clan Ammo iATM-12" + munition, 10);
        }
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 5);

        assertEquals(1, new WeaponBayInventoryEntry(ship, 0, bay, false).nRows());
    }

    /**
     * Improved Magnetic Pulse missiles are not the high explosive missiles a large craft's ATMs need 10 shots of before
     * they fire special munitions (TM p. 194), so this bay fires only its standard missiles
     */
    @Test
    void improvedMagneticPulseIsNotHighExplosive() {
        WeaponBayText bay = bay("CLIATM12");
        load(bay, "CLIATM12", "Clan Ammo iATM-12", 10);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 ER", 10);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 10);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals(1, entry.nRows());
        assertEquals(List.of("2 (24)", "2 (24)", "—", "—"), ranges(entry, 0));
    }

    /** Improved Magnetic Pulse missiles don't count toward the ATM bay profile (TW p. 304), so they print under it */
    @Test
    void aProfileBayPrintsItsImprovedMagneticPulseLineUnderTheProfile() {
        WeaponBayText bay = bay("CLIATM12");
        load(bay, "CLIATM12", "Clan Ammo iATM-12", 10);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 ER", 10);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 HE", 10);
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 10);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals(2, entry.nRows());
        assertEquals("1 Improved ATM 12 (10 Standard, 10 Extended Range, 10 High Explosive)", entry.getNameField(0));
        assertEquals(List.of("4 (36)", "2 (24)", "1 (12)", "1 (12)"), ranges(entry, 0));
        assertEquals("Improved Magnetic Pulse (10 rounds)", entry.getNameField(1));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(entry, 1));
    }

    /** An ATM can't fire an iATM's Improved Magnetic Pulse missiles (IO:AE p. 61), so it adds nothing to their line */
    @Test
    void aLauncherAddsNothingToAMunitionLineItCannotFire() {
        WeaponBayText bay = bay("CLATM12", "CLIATM12");
        for (String munition : List.of("", " ER", " HE")) {
            load(bay, "CLATM12", "Clan Ammo ATM-12" + munition, 10);
            load(bay, "CLIATM12", "Clan Ammo iATM-12" + munition, 10);
        }
        load(bay, "CLIATM12", "Clan Ammo iATM-12 IMP", 10);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals("Improved Magnetic Pulse (10 rounds)", entry.getNameField(entry.nRows() - 1));
        assertEquals(List.of("1 (12)", "—", "—", "—"), ranges(entry, entry.nRows() - 1));
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

        // A large craft's ATMs need 10 shots of each type per launcher to fire special munitions (TM p. 194), so a bay
        // carrying only those keeps its listed standard values, as a launcher without ammo does
        WeaponBayText extendedRange = bay("CLATM12", "CLATM12");
        load(extendedRange, "CLATM12", "Clan Ammo ATM-12 ER", 10);
        WeaponBayInventoryEntry extendedRangeEntry = new WeaponBayInventoryEntry(ship, 0, extendedRange, false);
        assertEquals("2 ATM 12 (10 Extended Range)", extendedRangeEntry.getNameField(0));
        assertEquals(List.of("4 (40)", "4 (40)", "—", "—"), ranges(extendedRangeEntry, 0));
    }

    @ParameterizedTest
    @CsvSource({ "ISMML9, ISMML9 LRM Ammo, 1, 1 MML 9 (1 round)",
                 "Barracuda, Ammo Barracuda, 1, 1 Barracuda (1 missile)",
                 "Barracuda, Ammo Barracuda, 2, 1 Barracuda (2 missiles)" })
    void aWeaponRowUsesTheAmmoCountForItsLabel(String weapon, String ammo, int count, String expectedName)
          throws LocationFullException {
        WeaponType type = (WeaponType) EquipmentType.get(weapon);
        WeaponMounted bay = (WeaponMounted) ship.addEquipment(type.getBayType(), Jumpship.LOC_NOSE);
        bay.addWeaponToBay((WeaponMounted) ship.addEquipment(type, Jumpship.LOC_NOSE));
        AmmoMounted bin = (AmmoMounted) ship.addEquipment(EquipmentType.get(ammo), Jumpship.LOC_NOSE);
        bin.setShotsLeft(count);
        bay.addAmmoToBay(bin);

        assertEquals(expectedName, printedBay(ship, bay).getNameField(0));
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

    /** The bay's design AV includes installed Artemis IV even when the carried SRM rounds are ordinary. */
    @Test
    void anMmlBayPrintsItsArtemisRatingWithOrdinarySrmAmmo() throws LocationFullException {
        WeaponType mml = (WeaponType) EquipmentType.get("ISMML9");
        WeaponMounted bay = (WeaponMounted) ship.addEquipment(mml.getBayType(), Jumpship.LOC_NOSE);
        WeaponMounted launcher = (WeaponMounted) ship.addEquipment(mml, Jumpship.LOC_NOSE);
        bay.addWeaponToBay(launcher);
        ship.addEquipment(EquipmentType.get("ISArtemisIV"), Jumpship.LOC_NOSE).setLinked(launcher);
        AmmoMounted ammo = (AmmoMounted) ship.addEquipment(EquipmentType.get("ISMML9 SRM Ammo"), Jumpship.LOC_NOSE);
        bay.addAmmoToBay(ammo);

        assertEquals("1 (14)", printedBay(ship, bay).getShortField(0));
    }

    /** A launcher out of ammunition can't fire (TW p. 103), so it adds nothing to either munition line */
    @Test
    void aLauncherWithoutAmmoAddsNothingToAMunitionLine() {
        WeaponBayText bay = bay("ISMML9", "ISMML3");
        load(bay, "ISMML9", "ISMML9 LRM Ammo", 13);
        load(bay, "ISMML9", "ISMML9 SRM Ammo", 11);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        // Each launcher type names a row of its own, then each munition prints its line
        assertEquals("LRM (13 rounds)", entry.getNameField(2));
        assertEquals(List.of("1 (5)", "1 (5)", "1 (5)", "—"), ranges(entry, 2));
        assertEquals("SRM (11 rounds)", entry.getNameField(3));
        assertEquals(List.of("1 (10)", "—", "—", "—"), ranges(entry, 3));
    }

    /** Each launcher that fires spends a round, so one SRM round left lets one of three MML 9s fire it (TW p. 103) */
    @Test
    void aMunitionLineFiresNoMoreLaunchersThanItHasRounds() {
        WeaponBayText bay = bay("ISMML9", "ISMML9", "ISMML9");
        load(bay, "ISMML9", "ISMML9 LRM Ammo", 13);
        load(bay, "ISMML9", "ISMML9 SRM Ammo", 1);
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 0, bay, false);

        assertEquals("SRM (1 round)", entry.getNameField(2));
        assertEquals(List.of("1 (10)", "—", "—", "—"), ranges(entry, 2));
    }

    /** Aerospace rapid-fire weapons fire at maximum rate (TW p. 238), making heat per shot (TW p. 114). */
    @ParameterizedTest
    @CsvSource({ "ISRotaryAC5, 2, 12", "ISUltraAC5, 2, 4", "ISUltraAC20 (THB), 1, 20" })
    void aBayFiresItsRapidFireWeaponsAtTheirMaximumRate(String weaponName, int count, int expectedHeat)
          throws LocationFullException {
        Dropship dropship = new Dropship();
        WeaponType type = (WeaponType) EquipmentType.get(weaponName);
        WeaponMounted bay = (WeaponMounted) dropship.addEquipment(type.getBayType(), Dropship.LOC_NOSE);
        for (int i = 0; i < count; i++) {
            WeaponMounted weapon = new WeaponMounted(dropship, type);
            UnitUtil.addEquipmentWithModes(dropship, weapon, Dropship.LOC_NOSE, false);
            bay.addWeaponToBay(weapon);
        }
        assertEquals(expectedHeat, dropship.getHeatInArc(Dropship.LOC_NOSE, false));
        assertEquals(String.valueOf(expectedHeat), printedBay(dropship, bay).getHeatField(0));
    }

    /** A working capacitor adds 5 heat to its PPC in a bay (TO:AUE p. 149). */
    @Test
    void mirroredPpcBaysPrintSeparateRowsWhenOnlyOneCapacitorWorks() throws LocationFullException {
        WeaponType ppc = (WeaponType) EquipmentType.get("ISPPC");
        for (int location : List.of(Jumpship.LOC_FLS, Jumpship.LOC_FRS)) {
            WeaponMounted bay = (WeaponMounted) ship.addEquipment(ppc.getBayType(), location);
            WeaponMounted weapon = (WeaponMounted) ship.addEquipment(ppc, location);
            bay.addWeaponToBay(weapon);
            Mounted<?> capacitor = ship.addEquipment(EquipmentType.get("ISPPCCapacitor"), location);
            capacitor.setLinked(weapon);
            capacitor.setDestroyed(location == Jumpship.LOC_FRS);
        }
        List<WeaponBayText> bays = InventoryWriter.computeWeaponBayTexts(ship.getWeaponBayList());

        assertEquals(2, bays.size());
        assertEquals("15", new WeaponBayInventoryEntry(ship, 0, bays.getFirst(), false).getHeatField(0));
        assertEquals("10", new WeaponBayInventoryEntry(ship, 1, bays.getLast(), false).getHeatField(0));
    }

    /** AR10 heat depends on the missile (TW p. 304). A Barracuda generates 10 heat (SO:AA p. 104). */
    @Test
    void anAr10BayPrintsItsHeatPerMissile() throws LocationFullException {
        WeaponType type = (WeaponType) EquipmentType.get("AR10");
        WeaponMounted bay = (WeaponMounted) ship.addEquipment(type.getBayType(), Jumpship.LOC_NOSE);
        WeaponMounted launcher = (WeaponMounted) ship.addEquipment(type, Jumpship.LOC_NOSE);
        bay.addWeaponToBay(launcher);
        assertEquals("*", printedBay(ship, bay).getHeatField(0));

        AmmoMounted barracuda = (AmmoMounted) ship.addEquipment(EquipmentType.get("Ammo AR10 Barracuda"),
              Jumpship.LOC_NOSE);
        bay.addAmmoToBay(barracuda);
        ship.loadWeapon(launcher, barracuda);
        WeaponBayInventoryEntry entry = printedBay(ship, bay);

        assertEquals(10, ship.getHeatInArc(Jumpship.LOC_NOSE, false));
        assertEquals("*", entry.getHeatField(0));
        assertEquals("*", entry.getShortField(0));
    }
}
