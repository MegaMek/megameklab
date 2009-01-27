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

import java.util.Comparator;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.actions.ClubAttackAction;
import megamek.common.weapons.ACWeapon;
import megamek.common.weapons.ArtilleryWeapon;
import megamek.common.weapons.CLPlasmaCannon;
import megamek.common.weapons.EnergyWeapon;
import megamek.common.weapons.FlamerWeapon;
import megamek.common.weapons.HAGWeapon;
import megamek.common.weapons.ISC3M;
import megamek.common.weapons.ISHGaussRifle;
import megamek.common.weapons.ISPlasmaRifle;
import megamek.common.weapons.ISSilverBulletGauss;
import megamek.common.weapons.ISSnubNosePPC;
import megamek.common.weapons.LBXACWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.MGWeapon;
import megamek.common.weapons.MRMWeapon;
import megamek.common.weapons.PulseLaserWeapon;
import megamek.common.weapons.RLWeapon;
import megamek.common.weapons.SRMWeapon;
import megamek.common.weapons.UACWeapon;

public class StringUtils {

    public static Comparator<? super EquipmentType> equipmentTypeComparator() {
        return new Comparator<EquipmentType>() {
            public int compare(EquipmentType eq1, EquipmentType eq2) {
                String s1 = eq1.getName().toLowerCase();
                String s2 = eq2.getName().toLowerCase();
                return s1.compareTo(s2);
            }
        };
    }

    public static Comparator<? super EquipmentInfo> equipmentInfoComparator() {
        return new Comparator<EquipmentInfo>() {
            public int compare(EquipmentInfo eq1, EquipmentInfo eq2) {
                String s1 = eq1.name.toLowerCase();
                String s2 = eq2.name.toLowerCase();
                return s1.compareTo(s2);
            }
        };
    }

    public static String getEquipmentInfo(Entity unit, Mounted mount) {
        String info = "";

        if (mount.getType() instanceof WeaponType) {
            WeaponType weapon = (WeaponType) mount.getType();
            if (weapon instanceof ISC3M) {
                info = "  [E]";
            } else if (weapon.getDamage() < 0) {
                if (weapon instanceof SRMWeapon) {
                    info = "2/Msl [M,C]";
                } else if (weapon instanceof LRMWeapon || weapon instanceof RLWeapon) {
                    info = "1/Msl [M,C,S]";
                } else if (weapon instanceof MRMWeapon) {
                    info = "1/Msl [M,C]";
                } else if (weapon instanceof ISSnubNosePPC) {
                    info = "10/8/5 [DE,V]";
                } else if (weapon instanceof ISHGaussRifle) {
                    info = "25/20/10 [DB,X]";
                } else if (weapon instanceof ISPlasmaRifle) {
                    info = "10 [DE,H,AI]";
                } else if (weapon instanceof CLPlasmaCannon) {
                    info = "[DE,H,AI]";
                } else if (weapon instanceof HAGWeapon) {
                    info = Integer.toString(weapon.getRackSize());
                    info += " [C/F]";
                } else if (weapon instanceof ArtilleryWeapon) {
                    info = Integer.toString(weapon.getRackSize());
                    info += "[AE,S,F]";
                } else {
                    info = Integer.toString(weapon.getRackSize());
                }
            } else if (weapon instanceof UACWeapon) {
                info = Integer.toString(weapon.getDamage());
                info += "/Sht [DB,R,C]";
            } else {
                info = Integer.toString(weapon.getDamage());
                info += " [";

                if (weapon.hasFlag(WeaponType.F_BALLISTIC)) {
                    info += "DB,";
                }
                if (UnitUtil.isAMS(weapon)) {
                    info += "PD,";
                } else if (weapon instanceof PulseLaserWeapon) {
                    info += "P,";
                } else if (weapon instanceof EnergyWeapon) {
                    info += "DE,";
                }

                if (weapon instanceof LBXACWeapon) {
                    info += "C/F/";
                } else if (weapon instanceof ISSilverBulletGauss) {
                    info += "C/F,";
                }

                if (UnitUtil.hasSwitchableAmmo(weapon)) {
                    info += "S,";
                }

                if (weapon instanceof UACWeapon) {
                    info += "R,";
                }

                if (weapon instanceof MGWeapon) {
                    info += "AI,";
                }

                if (weapon instanceof FlamerWeapon) {
                    info += "H,AI,";
                }

                if (weapon.isExplosive() && !(weapon instanceof ACWeapon)) {
                    info += "X,";
                }

                info = info.substring(0, info.length() - 1) + "]";

            }
        } else if (mount.getType() instanceof MiscType && mount.getType().hasFlag(MiscType.F_CLUB)) {
            info = Integer.toString(ClubAttackAction.getDamageFor(unit, mount, false));
        } else {
            info = "  [E]";
        }
        return info;
    }
}
