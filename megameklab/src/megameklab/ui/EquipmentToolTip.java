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
package megameklab.ui;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.verifier.TestEntity;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;

public final class EquipmentToolTip {

    public static String getToolTipInfo(Entity unit, Mounted<?> eq) {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        unusualSymbols.setGroupingSeparator(',');
        DecimalFormat myFormatter = new DecimalFormat("#,##0", unusualSymbols);
        StringBuilder sb = new StringBuilder("<HTML>");
        sb.append(eq.getName());
        if (eq.getType() instanceof AmmoType) {
            sb.append(" (").append(eq.getBaseShotsLeft()).append(" shot");
            sb.append((eq.getBaseShotsLeft() == 1) ? ")" : "s)");
        }
        if ((eq.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                || eq.getType().hasFlag(MiscType.F_AP_MOUNT))
                && (eq.getLinked() != null)) {
            sb.append(" (attached ").append(eq.getLinked().getName()).append(")");
        }
        if (eq.isSquadSupportWeapon()) {
            sb.append(" (squad support weapon)");
        }
        if (eq.getType() instanceof InfantryWeapon) {
            sb.append("<br>Damage/Trooper: ");
            double infDamage = ((InfantryWeapon) eq.getType()).getInfantryDamage();
            sb.append(infDamage);
            sb.append("<br>Range Class: ");
            sb.append(((InfantryWeapon) eq.getType()).getInfantryRange());
        } else {
            sb.append("<br>Crits: ");
            sb.append(eq.getCriticals());
            sb.append("<br>Mass: ");
            if (TestEntity.usesKgStandard(unit)) {
                sb.append(Math.round(eq.getTonnage() * 1000));
                sb.append(" Kg");
            } else {
                sb.append(eq.getTonnage());
                sb.append(" tons");
            }

            if (eq.getType() instanceof WeaponType) {
                sb.append("<br>Heat: ");
                sb.append(eq.getType().getHeat());
                sb.append("<br>Maximum Damage: ");
                sb.append(getWeaponDamageInfo((WeaponType) eq.getType()));
            }
        }
        sb.append("<Br>Cost: ");

        double cost = eq.getType().getCost(unit, false, eq.getLocation());

        sb.append(myFormatter.format(cost));
        sb.append(" CBills");

        if (eq.isRearMounted()) {
            sb.append("<br>Rear Facing");
        }
        if (eq.isMekTurretMounted()) {
            sb.append("<br>Turret mounted");
        }
        if (eq.isArmored()) {
            sb.append("<br>Armored");
        }
        if ((unit instanceof BattleArmor)
                && eq.getType().hasFlag(WeaponType.F_INF_SUPPORT)) {
            sb.append("<br>* Infantry support weapons must be held in an " +
                    "Armored Glove");
        } else if ((unit instanceof BattleArmor)
                && eq.getType().hasFlag(WeaponType.F_INFANTRY)) {
            sb.append("<br>* Infantry weapons must be mounted in AP Mounts");
        }

        sb.append("</html>");
        return sb.toString();
    }

    private static String getWeaponDamageInfo(WeaponType wType) {
        if (wType.getDamage() == WeaponType.DAMAGE_BY_CLUSTERTABLE) {
            int perMissile = 1;
            if ((wType instanceof SRMWeapon) || (wType instanceof SRTWeapon) || (wType instanceof MMLWeapon)) {
                perMissile = 2;
            }
            return Integer.toString(wType.getRackSize() * perMissile);
        } else if (wType.getDamage() == WeaponType.DAMAGE_VARIABLE) {
            return Integer.toString(wType.getDamage(1));
        } else if (wType.getDamage() == WeaponType.DAMAGE_SPECIAL) {
            return "Special";
        } else if (wType.getDamage() == WeaponType.DAMAGE_ARTILLERY) {
            return Integer.toString(wType.getRackSize());
        } else {
            int damage = wType.getDamage();
            if (wType.getAmmoType() == AmmoType.T_AC_ROTARY) {
                damage *= 6;
            } else if ((wType.getAmmoType() == AmmoType.T_AC_ULTRA)
                    || (wType.getAmmoType() == AmmoType.T_AC_ULTRA_THB)) {
                damage *= 2;
            }
            return Integer.toString(damage);
        }
    }

    private EquipmentToolTip() {
    }
}
