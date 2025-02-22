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

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Tank;
import megamek.common.VTOL;
import megamek.common.WeaponType;
import megamek.common.verifier.TestTank;
import megamek.common.weapons.c3.ISC3M;
import megamek.common.weapons.c3.ISC3MBS;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.tag.CLLightTAG;
import megamek.common.weapons.tag.CLTAG;
import megamek.common.weapons.tag.ISTAG;

public final class TankUtil {

    public static boolean isTankWeapon(EquipmentType eq, Entity unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }
        // Some weapons such as TAG and C3M should show as non-weapon equipment
        if (isTankMiscEquipment(eq, unit)) {
            return false;
        }

        if (eq instanceof WeaponType) {
            WeaponType weapon = (WeaponType) eq;
            if (!weapon.hasFlag(WeaponType.F_TANK_WEAPON) || UnitUtil.isNonMekOrTankWeapon(unit, weapon)) {
                return false;
            }
            return TestTank.legalForMotiveType(weapon, unit.getMovementMode(), unit.isSupportVehicle());
        }
        return false;
    }

    /**
     * Tests whether equipment should be shown on the equipment tab for the unit.
     * This is
     * used for both combat vehicles and non-aerospace support vehicles.
     *
     * @param eq   The equipment to show
     * @param tank The tank
     * @return Whether the equipment should show on the table
     */
    public static boolean isTankEquipment(EquipmentType eq, Tank tank) {
        return isTankMiscEquipment(eq, tank) || isTankWeapon(eq, tank);
    }

    /**
     * Tests whether equipment should be shown on the equipment tab for the unit as
     * non-weapon
     * equipment. This is used for both combat vehicles and non-aerospace support
     * vehicles.
     *
     * @param eq   The equipment to show
     * @param tank The tank
     * @return Whether the equipment should show on the table
     */
    public static boolean isTankMiscEquipment(EquipmentType eq, Entity tank) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        // Display AMS as equipment (even though it's a weapon)
        if ((eq instanceof WeaponType) && eq.hasFlag(WeaponType.F_AMS) && eq.hasFlag(WeaponType.F_TANK_WEAPON)) {
            return true;
        }

        if ((eq instanceof CLTAG) || (eq instanceof ISC3M) || (eq instanceof ISC3MBS)
                || (eq instanceof ISTAG) || (eq instanceof CLLightTAG)) {
            return true;
        }

        if (eq instanceof MiscType) {
            if (!TestTank.legalForMotiveType(eq, tank.getMovementMode(), tank.isSupportVehicle())) {
                return false;
            }
            // Can't use supercharger with solar or external power pickup
            if (eq.hasFlag(MiscType.F_MASC) && (!tank.hasEngine()
                    || tank.getEngine().getEngineType() == Engine.SOLAR
                    || tank.getEngine().getEngineType() == Engine.EXTERNAL)) {
                return false;
            }
            // External fuel tanks are only allowed on ICE and fuel cell engines
            if (eq.hasFlag(MiscType.F_FUEL) && (!tank.hasEngine()
                    || (tank.getEngine().getEngineType() != Engine.COMBUSTION_ENGINE
                            && tank.getEngine().getEngineType() != Engine.FUEL_CELL))) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_VTOL_EQUIPMENT) && (tank instanceof VTOL)) {
                return true;
            }
            if (tank.isSupportVehicle()) {
                return eq.hasFlag(MiscType.F_SUPPORT_TANK_EQUIPMENT);
            } else {
                return eq.hasFlag(MiscType.F_TANK_EQUIPMENT);
            }
        }
        return false;
    }

    private TankUtil() {
    }
}
