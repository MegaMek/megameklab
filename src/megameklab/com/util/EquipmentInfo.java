/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.com.util;

import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Sensor;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.ATMWeapon;
import megamek.common.weapons.MMLWeapon;

public class EquipmentInfo {
    public int count = 0;
    public String name = "";
    public int minRange = 0;
    public int shtRange = 0;
    public int medRange = 0;
    public int longRange = 0;
    public String damage = "[E]";
    public int heat = 0;
    public int techLevel = TechConstants.T_INTRO_BOXSET;
    public boolean isWeapon = false;
    public boolean isMML = false;
    public boolean isATM = false;

    public int c3Level = 0;

    public int C3S = 1;
    public int C3M = 2;
    public int C3I = 3;

    public EquipmentInfo(Entity unit, Mounted mount) {

        name = UnitUtil.getCritName(unit, mount.getType());
        if (mount.isRearMounted()) {
            name += "(R)";
        }

        count = 1;
        techLevel = mount.getType().getTechLevel();

        damage = StringUtils.getEquipmentInfo(unit, mount);

        if (mount.getType() instanceof WeaponType) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            WeaponType weapon = (WeaponType) mount.getType();
            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;

            shtRange = weapon.shortRange;
            medRange = weapon.mediumRange;
            longRange = weapon.longRange;
            heat = weapon.getHeat();
        } else if (mount.getType() instanceof MiscType && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if (mount.getType() instanceof MiscType && mount.getType().hasFlag(MiscType.F_C3S)) {
            c3Level = C3S;
        } else if (mount.getType() instanceof MiscType && mount.getType().hasFlag(MiscType.F_ECM)) {
            if (mount.getType().getInternalName().equals(Sensor.WATCHDOG)) {
                longRange = 4;
            } else {
                longRange = 6;
            }
        } else if (mount.getType() instanceof MiscType && mount.getType().hasFlag(MiscType.F_BAP)) {
            if (mount.getType().getInternalName().equals(Sensor.BAP)) {
                longRange = 4;
            } else if (mount.getType().getInternalName().equals(Sensor.BLOODHOUND)) {
                longRange = 6;
            } else if (mount.getType().getInternalName().equals(Sensor.CLAN_AP)) {
                longRange = 5;
            }
        }
    }
}
