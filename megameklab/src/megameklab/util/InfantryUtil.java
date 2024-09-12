/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megamek.common.WeaponType;
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
        // to the unit otherwise add the primary weapon (unless the secondary
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
                .collect(Collectors.toList());
        unit.getEquipment().removeAll(toRemove);
        unit.getWeaponList().removeAll(toRemove);
        unit.getAmmo().removeAll(toRemove);
        final EnumSet<AmmoType.Munitions> munition;
        if (fieldGun != null && num > 0) {
            if (fieldGun.getAmmoType() == AmmoType.T_AC_LBX
                    || fieldGun.getAmmoType() == AmmoType.T_AC_LBX_THB) {
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
                        logger.error("Could not find ammo for field gun " + fieldGun.getName());
                    }
                } catch (Exception ex) {
                    logger.error("", ex);
                }
            }
        }
    }

    public static String trimInfantryWeaponNames(String wname) {
        return wname.replace("Infantry ", "");
    }

    public static void resetInfantryArmor(Infantry unit) {
        unit.setArmorEncumbering(false);
        unit.setSpaceSuit(false);
        unit.setDEST(false);
        unit.setSneakCamo(false);
        unit.setSneakECM(false);
        unit.setSneakIR(false);
        unit.setArmorDamageDivisor(1.0);
    }

    public static boolean isInfantryEquipment(EquipmentType eq, Entity unit) {
        // TODO: adjust for field guns and artillery
        return eq instanceof InfantryWeapon;
    }

    private InfantryUtil() {
    }
}
