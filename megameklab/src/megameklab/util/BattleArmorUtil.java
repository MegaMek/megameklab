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

import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megamek.common.weapons.attacks.LegAttack;
import megamek.common.weapons.attacks.StopSwarmAttack;
import megamek.common.weapons.attacks.SwarmAttack;
import megamek.common.weapons.infantry.InfantryWeapon;

public final class BattleArmorUtil {

    /**
     * @param eq A {@link WeaponType} or {@link MiscType}
     * @param ba The BattleArmor instance
     *
     * @return Whether the BA can use the equipment
     */
    public static boolean isBAEquipment(EquipmentType eq, BattleArmor ba) {
        if (eq instanceof MiscType) {
            return eq.hasFlag(MiscType.F_BA_EQUIPMENT);
        } else if (eq instanceof WeaponType) {
            return isBattleArmorWeapon(eq, ba);
        }
        // This leaves AmmoType, which is filtered according to having a weapon that can use it
        return false;
    }

    public static boolean isBattleArmorAPWeapon(@Nullable EquipmentType etype) {
        if (!(etype instanceof InfantryWeapon infWeapon)) {
            return false;
        } else {
            return infWeapon.hasFlag(WeaponType.F_INFANTRY)
                  && !infWeapon.hasFlag(WeaponType.F_INF_POINT_BLANK)
                  && !infWeapon.hasFlag(WeaponType.F_INF_ARCHAIC)
                  && (infWeapon.getCrew() < 2);
        }
    }

    public static boolean isBattleArmorWeapon(EquipmentType eq, Entity unit) {
        if (eq instanceof WeaponType weapon) {
            if (!weapon.hasFlag(WeaponType.F_BA_WEAPON)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if ((eq instanceof SwarmAttack) || (eq instanceof StopSwarmAttack)
                  || (eq instanceof LegAttack)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY)
                  || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                  .getAmmoType() == AmmoType.AmmoTypeEnum.PLASMA))) {
                return true;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY) && (weapon.hasFlag(WeaponType.F_PLASMA))
                  && (weapon.hasFlag(WeaponType.F_BA_WEAPON))) {
                return true;
            }

            return !weapon.hasFlag(WeaponType.F_ENERGY)
                  || !weapon.hasFlag(WeaponType.F_PLASMA)
                  || (weapon.getAmmoType() != AmmoType.AmmoTypeEnum.NA);
        }

        return false;
    }

    public static boolean canSwarm(BattleArmor ba) {
        for (Mounted<?> eq : ba.getEquipment()) {
            if ((eq.getType() instanceof SwarmAttack)
                  || (eq.getType() instanceof StopSwarmAttack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canLegAttack(BattleArmor ba) {
        for (Mounted<?> eq : ba.getEquipment()) {
            if (eq.getType() instanceof LegAttack) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBAMultiMount(EquipmentType equip) {
        return (equip instanceof WeaponType)
              && (equip.hasFlag(WeaponType.F_TASER) || (((WeaponType) equip).getAmmoType()
              == AmmoType.AmmoTypeEnum.NARC));
    }

    private BattleArmorUtil() {
    }
}
