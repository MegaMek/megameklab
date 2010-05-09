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

import java.math.BigInteger;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
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
    public int minRange = 0;
    public int shtRange = 0;
    public int medRange = 0;
    public int longRange = 0;
    public int erRange = 0;
    public int heat = 0;
    public int techLevel = TechConstants.T_INTRO_BOXSET;
    public int secondaryLocation = Entity.LOC_NONE;
    // the following two are for BA ammo counting
    public int ammoCount = 0;
    public int location = 0;

    public String name = "";
    public String damage = "[E]";

    public boolean isWeapon = false;
    public boolean isMML = false;
    public boolean isATM = false;
    public boolean hasArtemis = false;
    public boolean hasApollo = false;
    public boolean hasArtemisV = false;
    public boolean hasAmmo = false;

    public int c3Level = 0;

    public static int C3S = 1;
    public static int C3M = 2;
    public static int C3I = 3;

    /**
     * base class.
     */
    public EquipmentInfo() {

    }

    /**
     * Info for Aeros
     * 
     * @param aero
     * @param mount
     */
    public EquipmentInfo(Aero aero, Mounted mount) {
        name = UnitUtil.getCritName(aero, mount.getType());
        if (mount.isRearMounted()) {
            name += "(R)";
        }

        count = 1;
        techLevel = mount.getType().getTechLevel();

        damage = StringUtils.getEquipmentInfo(aero, mount);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            WeaponType weapon = (WeaponType) mount.getType();
            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;

            shtRange = (int) weapon.shortAV;
            if (weapon.maxRange >= WeaponType.RANGE_MED) {
                medRange = (int) weapon.medAV;
            }
            if (weapon.maxRange >= WeaponType.RANGE_LONG) {
                longRange = (int) weapon.longAV;
            }
            if (weapon.maxRange >= WeaponType.RANGE_EXT) {
                erRange = (int) weapon.extAV;
            }
            heat = weapon.getHeat();
            secondaryLocation = mount.getSecondLocation();

        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3S)) {
            c3Level = C3S;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ECM)) {
            if (mount.getType().getInternalName().equals(Sensor.WATCHDOG)) {
                longRange = 4;
            } else {
                longRange = 6;
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BAP)) {
            if (mount.getType().getInternalName().equals(Sensor.BAP)) {
                longRange = 4;
            } else if (mount.getType().getInternalName().equals(Sensor.BLOODHOUND)) {
                longRange = 6;
            } else if (mount.getType().getInternalName().equals(Sensor.CLAN_AP)) {
                longRange = 5;
            }
        }

        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);

    }

    /**
     * Info for non Aero Entities.
     * 
     * @param unit
     * @param mount
     */
    public EquipmentInfo(Entity unit, Mounted mount) {

        name = UnitUtil.getCritName(unit, mount.getType());
        if (mount.isRearMounted()) {
            name += "(R)";
        }
        if (mount.isBodyMounted() && (unit instanceof BattleArmor) && ((((BattleArmor) unit).getChassisType() == BattleArmor.CHASSIS_TYPE_BIPED))) {
            name += " (Body)";
        }

        count = 1;
        techLevel = mount.getType().getTechLevel();

        damage = StringUtils.getEquipmentInfo(unit, mount);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }

            WeaponType weapon = (WeaponType) mount.getType();

            if ((unit instanceof BattleArmor) && (weapon.getAmmoType() != AmmoType.T_NA) && !weapon.hasFlag(WeaponType.F_ONESHOT)) {
                hasAmmo = true;
                ammoCount = UnitUtil.getBAAmmoCount(unit, weapon, mount.getLocation());
                location = mount.getLocation();
            }

            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;

            shtRange = weapon.shortRange;
            medRange = weapon.mediumRange;
            longRange = weapon.longRange;
            heat = weapon.getHeat();
            secondaryLocation = mount.getSecondLocation();
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3S)) {
            c3Level = C3S;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ECM)) {
            if (mount.getType().getInternalName().equals(Sensor.WATCHDOG)) {
                longRange = 4;
            } else {
                longRange = 6;
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BAP)) {
            if (mount.getType().getInternalName().equals(Sensor.BAP)) {
                longRange = 4;
            } else if (mount.getType().getInternalName().equals(Sensor.BLOODHOUND)) {
                longRange = 6;
            } else if (mount.getType().getInternalName().equals(Sensor.CLAN_AP)) {
                longRange = 5;
            }
        }

        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);

    }

    @Override
    public EquipmentInfo clone() {
        EquipmentInfo clone = new EquipmentInfo();

        clone.count = count;
        clone.minRange = minRange;
        clone.shtRange = shtRange;
        clone.medRange = medRange;
        clone.longRange = longRange;
        clone.erRange = erRange;
        clone.heat = heat;
        clone.techLevel = techLevel;
        clone.secondaryLocation = secondaryLocation;

        clone.name = name;
        clone.damage = damage;

        clone.isWeapon = isWeapon;
        clone.isMML = isMML;
        clone.isATM = isATM;

        clone.c3Level = c3Level;

        return clone;
    }

    private boolean hasLinkedEquipment(Mounted eq, BigInteger flag) {

        if ((eq.getLinkedBy() != null) && (eq.getLinkedBy().getType() instanceof MiscType) && eq.getLinkedBy().getType().hasFlag(flag)) {
            return true;
        }

        return false;
    }
}
