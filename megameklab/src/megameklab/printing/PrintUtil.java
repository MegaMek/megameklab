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
package megameklab.printing;

import static megamek.common.equipment.enums.MiscTypeFlag.*;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.weapons.attacks.LegAttack;
import megamek.common.weapons.attacks.StopSwarmAttack;
import megamek.common.weapons.attacks.SwarmAttack;
import megamek.common.weapons.attacks.SwarmWeaponAttack;
import megamek.common.weapons.infantry.rifle.InfantryRifleAutoRifleWeapon;
import megameklab.util.UnitUtil;

public final class PrintUtil {

    public static boolean isPrintableEquipment(EquipmentType eq, RecordSheetOptions options) {
        return isPrintableEquipment(eq, false, options);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq     The equipment to test The equipment
     * @param entity The Entity it's mounted on
     *
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, Entity entity, RecordSheetOptions options) {
        if (eq instanceof AmmoType) {
            return ((AmmoType) eq).getAmmoType() == AmmoType.AmmoTypeEnum.COOLANT_POD;
        } else if (entity instanceof BattleArmor) {
            return isPrintableBAEquipment(eq);
        } else {
            return isPrintableEquipment(eq, entity instanceof Mek, options);
        }
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq    The equipment to test The equipment
     * @param isMek Whether the equipment is mounted on a Mek
     *
     * @return Whether the equipment should be shown on the record sheet
     */
    public static boolean isPrintableEquipment(EquipmentType eq, boolean isMek, RecordSheetOptions options) {
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
              || eq.hasFlag(MiscType.F_BASIC_FIRE_CONTROL)
              || eq.hasFlag(MiscType.F_ADVANCED_FIRE_CONTROL)
              || eq.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE)
              || eq.hasFlag(MiscType.F_LASER_INSULATOR))) {
            return false;
        }

        if (eq instanceof MiscType mt && mt.hasFlag(MiscType.F_TALON)) {
            return RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE.equals(options.intrinsicPhysicalAttacks());
        }

        return !UnitUtil.isHeatSink(eq);
    }

    private PrintUtil() {
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq The equipment to test
     *
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
              && ((eq.hasFlag(F_AP_MOUNT) && !eq.hasFlag(F_BA_MANIPULATOR))
              || eq.hasAnyFlag(
              F_FIRE_RESISTANT,
              F_ARTEMIS,
              F_ARTEMIS_V,
              F_APOLLO,
              F_HARJEL,
              F_MASS,
              F_DETACHABLE_WEAPON_PACK,
              F_MODULAR_WEAPON_MOUNT
        ))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        return (!(eq instanceof LegAttack)) && (!(eq instanceof SwarmAttack))
              && (!(eq instanceof StopSwarmAttack))
              && (!(eq instanceof InfantryRifleAutoRifleWeapon))
              && (!(eq instanceof SwarmWeaponAttack));
    }
}
