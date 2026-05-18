/*
 * Copyright (C) 2024-2026 The MegaMek Team. All Rights Reserved.
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
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.units.BaConstructionUtil;
import megamek.common.units.Entity;
import megamek.common.weapons.attacks.LegAttack;
import megamek.common.weapons.attacks.StopSwarmAttack;
import megamek.common.weapons.attacks.SwarmAttack;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.logging.MMLogger;

import java.util.List;
import java.util.stream.IntStream;

import static megamek.common.units.BaConstructionUtil.*;

public final class BattleArmorUtil {

    private static final MMLogger LOGGER = MMLogger.create(BattleArmorUtil.class);

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

    /**
     * Mounts the given weapon on the given Detachable Weapon Pack. Any previously mounted weapon is removed from it,
     * becoming unallocated. Does nothing and logs a warning when the given dwp Mounted is not a DWP or the given weapon
     * may not be mounted on a DWP.
     *
     * @param weapon The weapon to mount on the DWP
     * @param dwp    The DWP to receive the weapon
     */
    public static void mountOnDwp(Mounted<?> weapon, Mounted<?> dwp) {
        if (!dwp.is(EquipmentTypeLookup.BA_DWP)) {
            LOGGER.warn("Trying to DWP-mount on a misc that is not a DWP!");
            return;
        }
        if (!weapon.getType().canBeMountedOnBaDwp()) {
            LOGGER.warn("Trying to DWP-mount invalid equipment!");
            return;
        }
        removeMountFromDwp(dwp);
        weapon.setLinkedBy(dwp);
        dwp.setLinked(weapon);
        weapon.setDWPMounted(true);
        weapon.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
    }

    /**
     * Empties the given Detachable Weapon Pack, removing any weapon mounted on it. Can be safely called (does nothing)
     * when there is no weapon on the DWP or the given Mounted is not a DWP (in this case, logs a warning).
     *
     * @param dwp The DWP to empty
     */
    public static void removeMountFromDwp(Mounted<?> dwp) {
        if (!dwp.is(EquipmentTypeLookup.BA_DWP)) {
            LOGGER.warn("Trying to DWP-mount on a misc that is not a DWP!");
            return;
        }
        if (dwp.getLinked() != null) {
            Mounted<?> weapon = dwp.getLinked();
            weapon.setLinkedBy(null);
            weapon.setDWPMounted(false);
            dwp.setLinked(null);
        }
    }

    /**
     * Empties the given APM (including armored glove) or DWP, removing any weapon or other equipment attached to it.
     * Can be safely called (does nothing) when there is no equipment on the given mount or the given mount is neither
     * an APM nor DWP (in this case, logs a warning).
     *
     * @param mount The APM/DWP to empty
     */
    public static void emptyDwpApm(Mounted<?> mount) {
        BaConstructionUtil.emptyDwpApm(mount);
    }

    /**
     * Unallocates (removes from arm/body etc to the unallocated equipment list) the given mounted. For special mounts
     * for other equipment (DWP etc), that other equipment is removed from this mount first, emptying the given mounted.
     * This method will unallocate regardless of the type of equipment, i.e., it does not check if this equipment should
     * ever go unallocated (e.g. fixed location equipment). It is therefore up to the caller to select equipment to
     * unallocate.
     *
     * @param mounted The equipment to unallocate
     */
    public static void unallocateMounted(BattleArmor battleArmor, Mounted<?> mounted) {
        BaConstructionUtil.unallocateMounted(battleArmor, mounted);
    }

    /**
     * Removes all critical slots for the given BA, unallocating all equipment (i.e., placing it into
     * BattleArmor.MOUNT_LOC_NONE and BattleArmor.LOC_SQUAD).
     */
    public static void removeAllCriticalSlotsFrom(BattleArmor battleArmor) {
        removeAllCriticalSlotsFrom(battleArmor, IntStream.range(0, battleArmor.locations()).boxed().toList());
    }

    /**
     * Removes all critical slots from the given locations for the given BA, unallocating all equipment in those
     * locations (i.e., placing it into BattleArmor.MOUNT_LOC_NONE and BattleArmor.LOC_SQUAD). Fixed location equipment
     * is not affected (it is left in place).
     */
    public static void removeAllCriticalSlotsFrom(BattleArmor battleArmor, List<Integer> locations) {
        battleArmor.getEquipment()
              .stream()
              .filter(m -> m.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE)
              .filter(m -> !UnitUtil.isFixedLocationSpreadEquipment(m.getType()))
              .filter(m -> locations.contains(m.getBaMountLoc()))
              .forEach(m -> unallocateMounted(battleArmor, m));
    }

    public static boolean isFilledWeaponMount(Mounted<?> mounted) {
        return isFilledDwp(mounted) || isFilledApm(mounted)
              || (mounted.getType().hasFlag(MiscType.F_AP_MOUNT) && mounted.getLinked() != null);
    }

    /**
     * Removes all manipulator equipment, including Modular Equipment Adaptors, from the unit.
     *
     * @param battleArmor The BA
     */
    public static void removeManipulatorEquipment(BattleArmor battleArmor) {
        UnitUtil.removeAllMounted(battleArmor, EquipmentType.get(EquipmentTypeLookup.BA_MODULAR_EQUIPMENT_ADAPTOR));
        // UnitUtil calls unallocateMounted to unallocate attached AP weapons
        UnitUtil.removeAllMiscMounted(battleArmor, MiscTypeFlag.F_BA_MANIPULATOR);
    }

    private BattleArmorUtil() {
    }
}
