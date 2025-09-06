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

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.logging.MMLogger;

public final class InfantryUtil {
    private static final MMLogger logger = MMLogger.create(InfantryUtil.class);

    public static void replaceMainWeapon(Infantry unit, InfantryWeapon weapon, boolean secondary) {
        Mounted<?> existingInfantryMount = null;
        for (Mounted<?> m : unit.getWeaponList()) {
            if ((m.getType() instanceof InfantryWeapon)
                  && (m.getLocation() == Infantry.LOC_INFANTRY)) {
                existingInfantryMount = m;
                break;
            }
        }
        if (null != existingInfantryMount) {
            UnitUtil.removeMounted(unit, existingInfantryMount);
        }
        if (secondary) {
            unit.setSecondaryWeapon(weapon);
        } else {
            unit.setPrimaryWeapon(weapon);
        }
        // if there is more than one secondary weapon per squad, then add that
        // to the unit otherwise add the primary weapon unless the secondary
        // is TAG, in which case both are added.
        if (unit.getSecondaryWeapon() != null && unit.getSecondaryWeapon().hasFlag(WeaponType.F_TAG)) {
            try {
                unit.addEquipment(unit.getPrimaryWeapon(), Infantry.LOC_INFANTRY);
                unit.addEquipment(unit.getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ignored) {

            }
        } else if ((unit.getSecondaryWeaponsPerSquad() < 2) || (null == unit.getSecondaryWeapon())) {
            try {
                unit.addEquipment(unit.getPrimaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ignored) {

            }
        } else {
            try {
                unit.addEquipment(unit.getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ignored) {

            }
        }
    }

    public static void replaceFieldGun(Infantry unit, WeaponType fieldGun, int num) {
        List<Mounted<?>> toRemove = unit.getEquipment().stream()
              .filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
              .toList();

        for (Mounted<?> mounted : toRemove) {
            unit.getEquipment().remove(mounted);

            if (mounted instanceof WeaponMounted) {
                unit.getWeaponList().remove(mounted);
            }

            if (mounted instanceof AmmoMounted) {
                unit.getAmmo().remove(mounted);
            }
        }

        final EnumSet<AmmoType.Munitions> munition;
        if (fieldGun != null && num > 0) {
            if (fieldGun.getAmmoType() == AmmoType.AmmoTypeEnum.AC_LBX
                  || fieldGun.getAmmoType() == AmmoType.AmmoTypeEnum.AC_LBX_THB) {
                munition = EnumSet.of(AmmoType.Munitions.M_CLUSTER);
            } else {
                munition = EnumSet.of(AmmoType.Munitions.M_STANDARD);
            }
            Optional<AmmoType> ammo = AmmoType.getMunitionsFor(fieldGun.getAmmoType()).stream()
                  .filter(eq -> (eq.getMunitionType().equals(munition))
                        && (eq.getRackSize() == fieldGun.getRackSize()))
                  .findFirst();
            if (ammo.isEmpty()) {
                ammo = AmmoType.getMunitionsFor(fieldGun.getAmmoType()).stream().findFirst();
            }

            for (int i = 0; i < num; i++) {
                try {
                    unit.addEquipment(fieldGun, Infantry.LOC_FIELD_GUNS);
                    if (ammo.isPresent()) {
                        unit.addEquipment(ammo.get(), Infantry.LOC_FIELD_GUNS);
                    } else {
                        logger.error("Could not find ammo for field gun {}", fieldGun.getName());
                    }
                } catch (Exception ex) {
                    logger.error("", ex);
                }
            }
        }
    }

    public static String trimInfantryWeaponNames(String weaponName) {
        return weaponName.replace("Infantry ", "");
    }

    public static void resetInfantryArmor(Infantry unit) {
        unit.setArmorEncumbering(false);
        unit.setSpaceSuit(false);
        unit.setDEST(false);
        unit.setSneakCamo(false);
        unit.setSneakECM(false);
        unit.setSneakIR(false);
        unit.setCustomArmorDamageDivisor(1.0);
    }

    public static boolean isInfantryEquipment(EquipmentType eq, Entity unit) {
        // TODO: adjust for field guns and artillery
        return eq instanceof InfantryWeapon;
    }

    private InfantryUtil() {
    }
}
