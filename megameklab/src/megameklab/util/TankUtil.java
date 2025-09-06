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

import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
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

        if (eq instanceof WeaponType weapon) {
            if (!weapon.hasFlag(WeaponType.F_TANK_WEAPON) || UnitUtil.isNonMekOrTankWeapon(unit, weapon)) {
                return false;
            }
            return TestTank.legalForMotiveType(weapon, unit.getMovementMode(), unit.isSupportVehicle());
        }
        return false;
    }

    /**
     * Tests whether equipment should be shown on the equipment tab for the unit. This is used for both combat vehicles
     * and non-aerospace support vehicles.
     *
     * @param eq   The equipment to show
     * @param tank The tank
     *
     * @return Whether the equipment should show on the table
     */
    public static boolean isTankEquipment(EquipmentType eq, Tank tank) {
        return isTankMiscEquipment(eq, tank) || isTankWeapon(eq, tank);
    }

    /**
     * Tests whether equipment should be shown on the equipment tab for the unit as non-weapon equipment. This is used
     * for both combat vehicles and non-aerospace support vehicles.
     *
     * @param eq   The equipment to show
     * @param tank The tank
     *
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
