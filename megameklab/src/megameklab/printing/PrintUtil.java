/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab
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
package megameklab.printing;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mek;
import megamek.common.MiscType;
import megamek.common.weapons.LegAttack;
import megamek.common.weapons.StopSwarmAttack;
import megamek.common.weapons.SwarmAttack;
import megamek.common.weapons.SwarmWeaponAttack;
import megamek.common.weapons.infantry.InfantryRifleAutoRifleWeapon;
import megameklab.util.UnitUtil;

public final class PrintUtil {

    public static boolean isPrintableEquipment(EquipmentType eq) {
        return isPrintableEquipment(eq, false);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq     The equipment to test The equipment
     * @param entity The Entity it's mounted on
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, Entity entity) {
        if (eq instanceof AmmoType) {
            return ((AmmoType) eq).getAmmoType() == AmmoType.T_COOLANT_POD;
        } else if (entity instanceof BattleArmor) {
            return isPrintableBAEquipment(eq);
        } else {
            return isPrintableEquipment(eq, entity instanceof Mek);
        }
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq    The equipment to test The equipment
     * @param isMek Whether the equipment is mounted on a Mek
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, boolean isMek) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isFixedLocationSpreadEquipment(eq)
                && !(eq instanceof MiscType) && eq.hasFlag(MiscType.F_TALON)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }
        if (!eq.isHittable() && isMek) {
            return false;
        }

        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_CASE)
                        || eq.hasFlag(MiscType.F_ARTEMIS)
                        || eq.hasFlag(MiscType.F_ARTEMIS_PROTO)
                        || eq.hasFlag(MiscType.F_ARTEMIS_V)
                        || eq.hasFlag(MiscType.F_APOLLO)
                        || eq.hasFlag(MiscType.F_PPC_CAPACITOR)
                        || (eq.hasFlag(MiscType.F_MASC) && isMek)
                        || eq.hasFlag(MiscType.F_HARJEL)
                        || eq.hasFlag(MiscType.F_MASS)
                        || eq.hasFlag(MiscType.F_CHASSIS_MODIFICATION)
                        || eq.hasFlag(MiscType.F_SPONSON_TURRET)
                        || eq.hasFlag(MiscType.F_EXTERNAL_STORES_HARDPOINT)
                        || eq.hasFlag(MiscType.F_BASIC_FIRECONTROL)
                        || eq.hasFlag(MiscType.F_ADVANCED_FIRECONTROL)
                        || eq.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE)
                        || eq.hasFlag(MiscType.F_LASER_INSULATOR))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        return true;
    }

    private PrintUtil() {
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq The equipment to test
     * @return True when it should appear on the record sheet
     */
    public static boolean isPrintableBAEquipment(EquipmentType eq) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }

        if ((eq instanceof MiscType)
                && ((eq.hasFlag(MiscType.F_AP_MOUNT) && !eq.hasFlag(MiscType.F_BA_MANIPULATOR))
                        || eq.hasFlag(MiscType.F_FIRE_RESISTANT)
                        || eq.hasFlag(MiscType.F_STEALTH)
                        || eq.hasFlag(MiscType.F_ARTEMIS)
                        || eq.hasFlag(MiscType.F_ARTEMIS_V)
                        || eq.hasFlag(MiscType.F_APOLLO)
                        || eq.hasFlag(MiscType.F_HARJEL)
                        || eq.hasFlag(MiscType.F_MASS)
                        || eq.hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        if ((eq instanceof LegAttack) || (eq instanceof SwarmAttack)
                || (eq instanceof StopSwarmAttack)
                || (eq instanceof InfantryRifleAutoRifleWeapon)
                || (eq instanceof SwarmWeaponAttack)) {
            return false;
        }

        return true;
    }
}
