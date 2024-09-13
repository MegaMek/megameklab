/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.ab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import megamek.common.*;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestSmallCraft;
import megamek.common.verifier.TestTank;
import megamek.common.weapons.capitalweapons.CapitalMissileWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.lrms.LRTWeapon;
import megamek.common.weapons.missiles.MRMWeapon;
import megamek.common.weapons.missiles.RLWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;

public final class AeroUtil {

    public static boolean isAeroWeapon(EquipmentType eq, Aero unit) {
        if (!(eq instanceof WeaponType)) {
            return false;

        }
        if (eq instanceof InfantryWeapon) {
            return false;
        }
        // Fixed wing, airship, and satellite vehicles use vehicle construction rules.
        if (unit.isSupportVehicle()) {
            return eq.hasFlag(WeaponType.F_TANK_WEAPON)
                    && TestTank.legalForMotiveType(eq, unit.getMovementMode(), true);
        }

        WeaponType weapon = (WeaponType) eq;

        if (weapon.hasFlag(WeaponType.F_BOMB_WEAPON)) {
            return false;
        }

        // small craft only; lacks aero weapon flag
        if (weapon.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR) {
            return unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                    && !unit.hasETypeFlag(Entity.ETYPE_DROPSHIP);
        }

        if (weapon.hasFlag(WeaponType.F_ARTILLERY) && !weapon.hasFlag(WeaponType.F_BA_WEAPON)) {
            return (weapon.getAmmoType() == AmmoType.T_ARROW_IV)
                    || unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                    || unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP);
        }

        if (weapon.isSubCapital() || (weapon instanceof CapitalMissileWeapon)
                || (weapon.getAtClass() == WeaponType.CLASS_SCREEN)) {
            return unit.hasETypeFlag(Entity.ETYPE_DROPSHIP)
                    || unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP);
        }

        if (weapon.isCapital()) {
            return unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP);
        }

        if (!weapon.hasFlag(WeaponType.F_AERO_WEAPON)) {
            return false;
        }

        if (weapon.getTonnage(unit) <= 0) {
            return false;
        }

        if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon))
                && (weapon.getRackSize() != 5)
                && (weapon.getRackSize() != 10)
                && (weapon.getRackSize() != 15)
                && (weapon.getRackSize() != 20)) {
            return false;
        }
        if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon))
                && (weapon.getRackSize() != 2)
                && (weapon.getRackSize() != 4)
                && (weapon.getRackSize() != 6)) {
            return false;
        }
        if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
            return false;
        }

        if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
            return false;
        }

        if (weapon.hasFlag(WeaponType.F_ENERGY)
                || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                        .getAmmoType() == AmmoType.T_PLASMA))) {

            return !weapon.hasFlag(WeaponType.F_ENERGY)
                    || !weapon.hasFlag(WeaponType.F_PLASMA)
                    || (weapon.getAmmoType() != AmmoType.T_NA);
        }
        return true;
    }

    public static boolean isAeroEquipment(EquipmentType eq, Aero unit) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof AmmoType)
                && (((AmmoType) eq).getAmmoType() == AmmoType.T_COOLANT_POD)) {
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
     * @param size     The number of personnel that can be housed in the designated
     *                 type of quarters
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
     * @param secondclass The number second class passenger quarters
     * @param steerage    The number of steerage class crew/passenger quarters
     */
    public static void assignQuarters(Aero aero, int officer, int standard, int secondclass, int steerage) {
        Map<TestAero.Quarters, Integer> sizes = TestAero.Quarters.getQuartersByType(aero);
        if (sizes.get(TestAero.Quarters.FIRST_CLASS) != officer) {
            setQuarters(aero, TestAero.Quarters.FIRST_CLASS, officer);
        }
        if (sizes.get(TestAero.Quarters.STANDARD) != standard) {
            setQuarters(aero, TestAero.Quarters.STANDARD, standard);
        }
        if (sizes.get(TestAero.Quarters.SECOND_CLASS) != secondclass) {
            setQuarters(aero, TestAero.Quarters.SECOND_CLASS, secondclass);
        }
        if (sizes.get(TestAero.Quarters.STEERAGE) != steerage) {
            setQuarters(aero, TestAero.Quarters.STEERAGE, steerage);
        }
    }

    /**
     * Adjusts the number of quarters of each to match the crew and passenger needs.
     * If no quarters
     * are already assigned, this will put all officers in officer/first class
     * cabins, enlisted crew
     * in standard crew quarters, and passengers in second class cabins. If there
     * are already more
     * officer/first class cabins assigned than there are officers, the extra will
     * be used as first
     * class passenger cabins. Any steerage quarters will be assigned first to
     * marines, then to passengers,
     * then to remaining enlisted.
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
        int steeragePsgr = Math.min(aero.getNPassenger() - firstClass + enlistedNeeds - standardCrew,
                quartersCount.get(TestAero.Quarters.STEERAGE));
        // Assign any existing steerage quarters first to marines that have not already
        // been assigned standard
        // quarters
        int steerageCrew = 0;
        if (enlistedNeeds > standardCrew) {
            steerageCrew = Math.min(steeragePsgr, marines);
            steeragePsgr -= steerageCrew;
        }
        // Assign any remaining steerage quarters to passengers first, then remaining
        // crew.
        if (steeragePsgr > aero.getNPassenger() - firstClass) {
            int excess = steeragePsgr - aero.getNPassenger() - firstClass;
            steerageCrew += excess;
            steeragePsgr -= excess;
        }

        // Any leftovers go to standard crew or second class
        standardCrew = enlistedNeeds - steerageCrew;
        int secondClass = aero.getNPassenger() - firstClass - steeragePsgr;

        assignQuarters(aero, officer + firstClass, standardCrew, secondClass, steerageCrew + steeragePsgr);
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

    private AeroUtil() {
    }
}
