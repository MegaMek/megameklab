/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import megamek.common.bays.Bay;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Aero;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.SmallCraft;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestSmallCraft;
import megamek.common.verifier.TestTank;
import megamek.common.weapons.artillery.ArrowIV;
import megamek.common.weapons.capitalWeapons.CapitalMissileWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.lrms.LRTWeapon;
import megamek.common.weapons.missiles.MRMWeapon;
import megamek.common.weapons.missiles.rocketLauncher.RLWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;

public final class AeroUtil {

    public static boolean isAeroWeapon(EquipmentType eq, Aero unit) {
        if (!(eq instanceof WeaponType weaponType) || (eq instanceof InfantryWeapon)) {
            return false;
        }
        // Fixed wing, airship, and satellite vehicles use vehicle construction rules.
        if (unit.isSupportVehicle()) {
            return eq.hasFlag(WeaponType.F_TANK_WEAPON)
                  && TestTank.legalForMotiveType(eq, unit.getMovementMode(), true);
        }

        if (weaponType.hasFlag(WeaponType.F_BOMB_WEAPON)) {
            return false;
        }

        // small craft only; lacks aero weapon flag
        if (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.C3_REMOTE_SENSOR) {
            return unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                  && !unit.hasETypeFlag(Entity.ETYPE_DROPSHIP);
        }

        if (weaponType.isSubCapital() || (weaponType instanceof CapitalMissileWeapon)
              || (weaponType.getAtClass() == WeaponType.CLASS_SCREEN)) {
            return unit.hasETypeFlag(Entity.ETYPE_DROPSHIP)
                  || unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP);
        }

        if (weaponType.isCapital()) {
            return unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP);
        }

        if (!weaponType.hasFlag(WeaponType.F_AERO_WEAPON)) {
            return false;
        }

        // TO:AUE p.216 and https://bg.battletech.com/forums/index.php?topic=72186
        if (weaponType.hasFlag(WeaponType.F_ARTILLERY)) {
            if (unit.isSmallCraft() || unit.isDropShip()) {
                return true;
            } else if (unit.isFighter()) {
                return weaponType.isAnyOf(EquipmentTypeLookup.THUMPER_ARTY, EquipmentTypeLookup.SNIPER_ARTY)
                      || (eq instanceof ArrowIV);
            } else {
                return false;
            }
        }

        if (weaponType.getTonnage(unit) <= 0) {
            return false;
        }

        if (((weaponType instanceof LRMWeapon) || (weaponType instanceof LRTWeapon))
              && (weaponType.getRackSize() != 5)
              && (weaponType.getRackSize() != 10)
              && (weaponType.getRackSize() != 15)
              && (weaponType.getRackSize() != 20)) {
            return false;
        }
        if (((weaponType instanceof SRMWeapon) || (weaponType instanceof SRTWeapon))
              && (weaponType.getRackSize() != 2)
              && (weaponType.getRackSize() != 4)
              && (weaponType.getRackSize() != 6)) {
            return false;
        }
        if ((weaponType instanceof MRMWeapon) && (weaponType.getRackSize() < 10)) {
            return false;
        }

        if ((weaponType instanceof RLWeapon) && (weaponType.getRackSize() < 10)) {
            return false;
        }

        if (weaponType.hasFlag(WeaponType.F_ENERGY)
              || (weaponType.hasFlag(WeaponType.F_PLASMA) && (weaponType.getAmmoType()
              == AmmoType.AmmoTypeEnum.PLASMA))) {
            return !weaponType.hasFlag(WeaponType.F_ENERGY)
                  || !weaponType.hasFlag(WeaponType.F_PLASMA)
                  || (weaponType.getAmmoType() != AmmoType.AmmoTypeEnum.NA);
        }
        return true;
    }

    public static boolean isAeroEquipment(EquipmentType eq, Aero unit) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof AmmoType)
              && (((AmmoType) eq).getAmmoType() == AmmoType.AmmoTypeEnum.COOLANT_POD)) {
            return !unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT);
        }

        if ((eq instanceof MiscType)) {
            if (unit.hasETypeFlag(Entity.ETYPE_SPACE_STATION)) {
                return eq.hasFlag(MiscType.F_SS_EQUIPMENT);
            } else if (unit.hasETypeFlag(Entity.ETYPE_WARSHIP)) {
                return eq.hasFlag(MiscType.F_WS_EQUIPMENT);
            } else if (unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
                return eq.hasFlag(MiscType.F_JS_EQUIPMENT);
            } else if (unit.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
                return eq.hasFlag(MiscType.F_DS_EQUIPMENT);
            } else if (unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                return eq.hasFlag(MiscType.F_SC_EQUIPMENT);
            } else if (eq.hasFlag(MiscType.F_FLOTATION_HULL)) {
                return unit.hasETypeFlag(Entity.ETYPE_CONV_FIGHTER)
                      && !unit.hasETypeFlag(Entity.ETYPE_FIXED_WING_SUPPORT);
            } else if (unit.isSupportVehicle()) {
                return eq.hasFlag(MiscType.F_SUPPORT_TANK_EQUIPMENT)
                      && TestTank.legalForMotiveType(eq, unit.getMovementMode(), true);
            } else {
                return eq.hasFlag(MiscType.F_FIGHTER_EQUIPMENT);
            }
        }

        if (eq instanceof AmmoType) {
            return ((AmmoType) eq).canAeroUse();
        }

        return isAeroWeapon(eq, unit);
    }

    /**
     * Adjusts the number of crew quarters of a given type on an aerospace vessel.
     *
     * @param aero     The aerospace unit to change crew quarters sizes for
     * @param quarters The type of crew quarters to change
     * @param size     The number of personnel that can be housed in the designated type of quarters
     */
    public static void setQuarters(Aero aero, TestAero.Quarters quarters, int size) {
        List<Bay> toRemove = new ArrayList<>();
        for (Bay bay : aero.getTransportBays()) {
            if (TestAero.Quarters.getQuartersForBay(bay) == quarters) {
                toRemove.add(bay);
            }
        }
        for (Bay bay : toRemove) {
            aero.removeTransporter(bay);
        }
        if (size > 0) {
            aero.addTransporter(quarters.newQuarters(size));
        }
    }

    /**
     * Adjusts the number of all types of crew quarters on an aerospace vessel.
     *
     * @param aero        The vessel
     * @param officer     The number of officer/first class quarters
     * @param standard    The number of standard crew quarters
     * @param secondClass The number second class passenger quarters
     * @param steerage    The number of steerage class crew/passenger quarters
     */
    public static void assignQuarters(Aero aero, int officer, int standard, int secondClass, int steerage) {
        Map<TestAero.Quarters, Integer> sizes = TestAero.Quarters.getQuartersByType(aero);
        if (sizes.get(TestAero.Quarters.FIRST_CLASS) != officer) {
            setQuarters(aero, TestAero.Quarters.FIRST_CLASS, officer);
        }
        if (sizes.get(TestAero.Quarters.STANDARD) != standard) {
            setQuarters(aero, TestAero.Quarters.STANDARD, standard);
        }
        if (sizes.get(TestAero.Quarters.SECOND_CLASS) != secondClass) {
            setQuarters(aero, TestAero.Quarters.SECOND_CLASS, secondClass);
        }
        if (sizes.get(TestAero.Quarters.STEERAGE) != steerage) {
            setQuarters(aero, TestAero.Quarters.STEERAGE, steerage);
        }
    }

    /**
     * Adjusts the number of quarters of each to match the crew and passenger needs. If no quarters are already
     * assigned, this will put all officers in officer/first class cabins, enlisted crew in standard crew quarters, and
     * passengers in second class cabins. If there are already more officer/first class cabins assigned than there are
     * officers, the extra will be used as first class passenger cabins. Any steerage quarters will be assigned first to
     * marines, then to passengers, then to remaining enlisted.
     *
     * @param aero The vessel to assign quarters for.
     */
    public static void autoAssignQuarters(Aero aero) {
        int marines = aero.getNMarines() + aero.getNBattleArmor();
        int enlistedNeeds = aero.getNCrew() + marines - aero.getBayPersonnel() - aero.getNOfficers();
        Map<TestAero.Quarters, Integer> quartersCount = TestAero.Quarters.getQuartersByType(aero);

        // Standard crew quarters should not be larger than the crew needs, but may be
        // smaller as
        // some crew may have officer or steerage housing.
        int standardCrew = Math.min(enlistedNeeds, quartersCount.get(TestAero.Quarters.STANDARD));
        // Limit the first class quarters to number of officers + passengers. It is
        // possible to house
        // enlisted in first class quarters, but that is beyond the scope of this and
        // will need to
        // be done by hand.
        int officer1stC = Math.min(aero.getNOfficers() + aero.getNPassenger(),
              quartersCount.get(TestAero.Quarters.FIRST_CLASS));
        officer1stC = Math.max(officer1stC, aero.getNOfficers());
        int firstClass = Math.max(0, officer1stC - aero.getNOfficers());
        int officer = officer1stC - firstClass;

        // Limit the steerage quarters to the number of crew that have not been assigned
        // standard
        // or officer quarters and passengers that have not been assigned first class.
        int steeragePassenger = Math.min(aero.getNPassenger() - firstClass + enlistedNeeds - standardCrew,
              quartersCount.get(TestAero.Quarters.STEERAGE));
        // Assign any existing steerage quarters first to marines that have not already
        // been assigned standard
        // quarters
        int steerageCrew = 0;
        if (enlistedNeeds > standardCrew) {
            steerageCrew = Math.min(steeragePassenger, marines);
            steeragePassenger -= steerageCrew;
        }
        // Assign any remaining steerage quarters to passengers first, then remaining
        // crew.
        if (steeragePassenger > aero.getNPassenger() - firstClass) {
            int excess = steeragePassenger - aero.getNPassenger() - firstClass;
            steerageCrew += excess;
            steeragePassenger -= excess;
        }

        // Any leftovers go to standard crew or second class
        standardCrew = enlistedNeeds - steerageCrew;
        int secondClass = aero.getNPassenger() - firstClass - steeragePassenger;

        assignQuarters(aero, officer + firstClass, standardCrew, secondClass, steerageCrew + steeragePassenger);
    }

    public static void updateLoadedAero(Aero unit) {
        if (unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            if (unit.getArmorType(Aero.LOC_NOSE) == EquipmentType.T_ARMOR_STANDARD) {
                unit.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
            } else if (unit.getArmorType(Aero.LOC_NOSE) == EquipmentType.T_ARMOR_PRIMITIVE) {
                unit.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
            }
            if (unit.isPrimitive() && (unit instanceof Dropship)) {
                if (unit.getYear() < Dropship.getCollarTA().getIntroductionDate()) {
                    ((Dropship) unit).setCollarType(Dropship.COLLAR_NO_BOOM);
                } else if ((unit.getYear() < Dropship.getCollarTA().getIntroductionDate())
                      && (((Dropship) unit).getCollarType() == Dropship.COLLAR_STANDARD)) {
                    ((Dropship) unit).setCollarType(Dropship.COLLAR_PROTOTYPE);
                }
            }
            // Minimum crew levels
            ((SmallCraft) unit).setNGunners(Math.max(unit.getNGunners(),
                  TestSmallCraft.requiredGunners(unit)));
            unit.setNCrew(Math.max(unit.getNCrew(),
                  unit.getNGunners() + unit.getBayPersonnel()
                        + TestSmallCraft.minimumBaseCrew((SmallCraft) unit)));
            if (unit.getNOfficers() == 0) {
                ((SmallCraft) unit).setNOfficers((int) Math.ceil((unit.getNCrew() - unit.getBayPersonnel()) / 5.0));
            }
            // Check whether there are any quarters allocated. If not, assign standard
            // levels
            if (unit.getTransportBays().stream().noneMatch(Bay::isQuarters)) {
                unit.addTransporter(TestAero.Quarters.FIRST_CLASS.newQuarters(unit.getNOfficers()));
                unit.addTransporter(TestAero.Quarters.SECOND_CLASS.newQuarters(unit.getNPassenger()));
                int std = unit.getNCrew() - unit.getBayPersonnel() - unit.getNOfficers()
                      + unit.getNMarines() + unit.getNBattleArmor();
                if (std > 0) {
                    unit.addTransporter(TestAero.Quarters.STANDARD.newQuarters(std));
                }
            }
        } else if (unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            if (unit.getArmorType(Aero.LOC_NOSE) == EquipmentType.T_ARMOR_STANDARD) {
                unit.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
            }
        } else {
            if (unit.getArmorType(Aero.LOC_NOSE) == EquipmentType.T_ARMOR_PRIMITIVE) {
                unit.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_FIGHTER);
            }
        }
        List<Mounted<?>> weaponGroups = new ArrayList<>(unit.getWeaponGroupList());
        for (Mounted<?> group : weaponGroups) {
            UnitUtil.removeMounted(unit, group);
        }
    }

    private AeroUtil() {}
}
