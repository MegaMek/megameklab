/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.util;

import megamek.common.*;
import megamek.common.actions.ClubAttackAction;
import megamek.common.actions.KickAttackAction;
import megamek.common.equipment.MiscMounted;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.autocannons.ACWeapon;
import megamek.common.weapons.autocannons.LBXACWeapon;
import megamek.common.weapons.autocannons.UACWeapon;
import megamek.common.weapons.battlearmor.*;
import megamek.common.weapons.c3.ISC3M;
import megamek.common.weapons.c3.ISC3RemoteSensorLauncher;
import megamek.common.weapons.defensivepods.BPodWeapon;
import megamek.common.weapons.flamers.FlamerWeapon;
import megamek.common.weapons.gaussrifles.HAGWeapon;
import megamek.common.weapons.gaussrifles.ISHGaussRifle;
import megamek.common.weapons.gaussrifles.ISSilverBulletGauss;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lasers.*;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.lrms.LRTWeapon;
import megamek.common.weapons.lrms.StreakLRMWeapon;
import megamek.common.weapons.mgs.MGWeapon;
import megamek.common.weapons.missiles.*;
import megamek.common.weapons.mortars.CLVehicularGrenadeLauncher;
import megamek.common.weapons.mortars.ISVehicularGrenadeLauncher;
import megamek.common.weapons.mortars.MekMortarWeapon;
import megamek.common.weapons.other.NarcWeapon;
import megamek.common.weapons.ppc.CLPlasmaCannon;
import megamek.common.weapons.ppc.ISPlasmaRifle;
import megamek.common.weapons.ppc.ISSnubNosePPC;
import megamek.common.weapons.ppc.PPCWeapon;
import megamek.common.weapons.prototypes.PrototypeRLWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;
import megamek.common.weapons.srms.StreakSRMWeapon;
import megamek.common.weapons.tag.TAGWeapon;

import java.util.Comparator;

public class StringUtils {

    public static Comparator<? super EquipmentType> equipmentTypeComparator() {
        return (Comparator<EquipmentType>) (eq1, eq2) -> {
            String s1 = eq1.getName().toLowerCase();
            String s2 = eq2.getName().toLowerCase();
            return s1.compareTo(s2);
        };
    }

    public static Comparator<Mounted> mountedComparator() {
        return (m1, m2) -> {
            String s1 = m1.getName().toLowerCase();
            String s2 = m2.getName().toLowerCase();
            return s1.compareTo(s2);
        };
    }

    public static String getEquipmentInfo(Entity unit, Mounted<?> mount) {
        String info = "";

        if (mount.getType() instanceof WeaponType) {
            WeaponType weapon = (WeaponType) mount.getType();
            if (weapon instanceof InfantryWeapon) {
                info = Integer.toString((int) Math.round(((InfantryWeapon) weapon).getInfantryDamage()));
                if (weapon.hasFlag(WeaponType.F_BALLISTIC)) {
                    info += " (B)";
                } else if (weapon.hasFlag(WeaponType.F_ENERGY)) {
                    info += " (E)";
                } else if (weapon.hasFlag(WeaponType.F_MISSILE)) {
                    info += " (M)";
                } else if (weapon.hasFlag(WeaponType.F_INF_POINT_BLANK)) {
                    info += " (P)";
                }
                if (weapon.hasFlag(WeaponType.F_INF_BURST)) {
                    info += "B";
                }
                if (weapon.hasFlag(WeaponType.F_INF_AA)) {
                    info += "A";
                }
                if (weapon.hasFlag(WeaponType.F_FLAMER)) {
                    info += "F";
                }
                if (weapon.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
                    info += "N";
                }
            } else if (weapon.hasFlag(WeaponType.F_MGA)) {
                info = "  [T]";
            } else if (weapon instanceof TAGWeapon) {
                info = "  [E]";
            } else if (weapon instanceof ISC3RemoteSensorLauncher) {
                info = "  [M,E]";
            } else if (weapon.getDamage() < 0) {
                if (weapon instanceof StreakSRMWeapon) {
                    info = "2/Msl [M,C]";
                } else if ((weapon instanceof SRMWeapon) || (weapon instanceof MekMortarWeapon)
                        || (weapon instanceof SRTWeapon)) {
                    info = "2/Msl [M,C,S]";
                } else if ((weapon instanceof StreakLRMWeapon)) {
                    info = "1/Msl [M,C]";
                } else if ((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon)) {
                    info = "1/Msl [M,C,S]";
                } else if ((weapon instanceof MRMWeapon) || (weapon instanceof RLWeapon)
                        || (weapon instanceof PrototypeRLWeapon)) {
                    info = "1/Msl [M,C]";
                } else if (weapon instanceof ISSnubNosePPC) {
                    info = "10/8/5 [DE,V]";
                } else if (weapon instanceof VariableSpeedPulseLaserWeapon) {
                    info = String.format("%d/%d/%d [P,V]",
                            weapon.getDamage(weapon.getShortRange()),
                            weapon.getDamage(weapon.getMediumRange()),
                            weapon.getDamage(weapon.getLongRange()));
                } else if (weapon instanceof ISHGaussRifle) {
                    info = "25/20/10 [DB,X]";
                } else if (weapon instanceof ISPlasmaRifle) {
                    info = "10 [DE,H,AI]";
                } else if (weapon instanceof CLPlasmaCannon) {
                    info = "[DE,H,AI]";
                } else if (weapon instanceof HAGWeapon) {
                    info = Integer.toString(weapon.getRackSize());
                    info += " [C,F,X]";
                } else if (weapon instanceof ArtilleryWeapon) {
                    info = Integer.toString(weapon.getRackSize());
                    info += "[AE,S,F]";
                } else if (weapon instanceof ArtilleryCannonWeapon) {
                    info = Integer.toString(weapon.getRackSize());
                    info += "[DB,AE]";
                } else if (weapon instanceof ThunderBoltWeapon) {
                    if (weapon instanceof ISThunderBolt5) {
                        info = "5";
                    } else if (weapon instanceof ISThunderBolt10) {
                        info = "10";
                    } else if (weapon instanceof ISThunderBolt15) {
                        info = "15";
                    } else if (weapon instanceof ISThunderBolt20) {
                        info = "20";
                    }
                    info += "[M]";
                } else if (weapon instanceof NarcWeapon) {
                    info = "[M]";
                } else if (weapon instanceof ISBAPopUpMineLauncher) {
                    info = "4";
                } else {
                    info = Integer.toString(weapon.getRackSize());
                }
            } else if (weapon instanceof UACWeapon) {
                info = Integer.toString(weapon.getDamage());
                info += "/Sht [DB,R/C]";
            } else if ((weapon instanceof ISVehicularGrenadeLauncher) || (weapon instanceof CLVehicularGrenadeLauncher)) {
                info = "[AE,OS]";
            } else {
                if (!UnitUtil.isAMS(weapon)) {
                    info = Integer.toString(weapon.getDamage());
                }
                info += " [";

                if (weapon.hasFlag(WeaponType.F_BALLISTIC) && !UnitUtil.isAMS(weapon)) {
                    info += "DB,";
                }
                if (UnitUtil.isAMS(weapon) || (weapon.hasFlag(WeaponType.F_B_POD))) {
                    info += "PB,";
                } else if (weapon.hasFlag(WeaponType.F_PULSE)) {
                    info += "P,";
                } else if (weapon.hasFlag(WeaponType.F_ENERGY) || weapon.hasFlag(WeaponType.F_PLASMA)) {
                    info += "DE,";
                }
                if (weapon instanceof ISBombastLaser) {
                    info += "V,";
                }
                if ((weapon instanceof LBXACWeapon) || (weapon instanceof ISSilverBulletGauss)) {
                    info += "C/F/";
                }
                if (weapon instanceof CLBALBX) {
                    info += "C,F,";
                }

                if (UnitUtil.hasSwitchableAmmo(weapon)) {
                    info += "S,";
                }

                if (weapon.hasFlag(WeaponType.F_FLAMER) || weapon.hasFlag(WeaponType.F_PLASMA)) {
                    info += "H,";
                }
                if ((weapon instanceof MGWeapon) || (weapon instanceof BPodWeapon) ||
                        (weapon instanceof CLERPulseLaserSmall) ||
                        (weapon instanceof CLBAERPulseLaserSmall) ||
                        (weapon instanceof ISXPulseLaserSmall) ||
                        (weapon instanceof ISPulseLaserSmall) ||
                        (weapon instanceof ISBALaserPulseSmall) || 
                        (weapon instanceof CLPulseLaserSmall) ||
                        (weapon instanceof CLBAPulseLaserSmall) ||
                        (weapon instanceof CLPulseLaserMicro) ||
                        (weapon instanceof CLBAPulseLaserMicro) ||
                        (weapon.hasFlag(WeaponType.F_FLAMER) ||
                        (weapon.hasFlag(WeaponType.F_BURST_FIRE)))) {
                    info += "AI,";
                }

                if (weapon.isExplosive(mount) && !(weapon instanceof ACWeapon) && (!(weapon instanceof PPCWeapon) || ((mount.getLinkedBy() != null) && mount.getLinkedBy().getType().hasFlag(MiscType.F_PPC_CAPACITOR)))) {
                    info += "X,";
                }

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    info += "OS,";
                }

                info = info.substring(0, info.length() - 1) + "]";

            }
        } else if ((mount instanceof MiscMounted) && (mount.getType().hasFlag(MiscType.F_CLUB)
                || mount.getType().hasFlag(MiscType.F_HAND_WEAPON))) {
            if (((MiscType) mount.getType()).isVibroblade()) {
                // manually set vibros to active to get correct damage
                mount.setMode(1);
            }
            if (mount.getType().hasSubType(MiscType.S_CLAW) || mount.getType().hasSubType(MiscType.S_CLAW_THB)) {
                info = Integer.toString((int) Math.ceil(unit.getWeight() / 7.0));
            } else {
                info = Integer.toString(ClubAttackAction.getDamageFor(unit, (MiscMounted) mount, false, false));
            }
        } else if ((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_AP_POD))) {
            info = "[PB,OS,AI]";
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TALON)) {
            info = Integer.toString(KickAttackAction.getDamageFor(unit, Mech.LOC_LLEG, false));
        } else if (mount.is(EquipmentTypeLookup.COOLANT_POD)) {
            info = "[PE,OS,X]";
        } else {
            info = "  [E]";
        }
        return info.trim();
    }

    public static String getAeroEquipmentInfo(Mounted<?> mount) {
        String info;

        if (mount.getType() instanceof WeaponType) {
            WeaponType weapon = (WeaponType) mount.getType();
            if (weapon instanceof InfantryWeapon) {
                info = "";
                if (weapon.hasFlag(WeaponType.F_BALLISTIC)) {
                    info += " (B)";
                } else if (weapon.hasFlag(WeaponType.F_ENERGY)) {
                    info += " (E)";
                } else if (weapon.hasFlag(WeaponType.F_MISSILE)) {
                    info += " (M)";
                } else if (weapon.hasFlag(WeaponType.F_INF_POINT_BLANK)) {
                    info += " (P)";
                }
                if (weapon.hasFlag(WeaponType.F_INF_BURST)) {
                    info += "B";
                }
                if (weapon.hasFlag(WeaponType.F_INF_AA)) {
                    info += "A";
                }
                if (weapon.hasFlag(WeaponType.F_FLAMER)) {
                    info += "F";
                }
                if (weapon.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
                    info += "N";
                }
            } else if (weapon.hasFlag(WeaponType.F_MGA)) {
                info = "[T]";
            } else if (weapon instanceof ISC3M) {
                info = "[E]";
            } else if (weapon.getDamage() < 0) {
                if ((weapon instanceof SRMWeapon) || (weapon instanceof LRMWeapon)
                        || (weapon instanceof MekMortarWeapon) || (weapon instanceof MMLWeapon)
                        || (weapon instanceof ATMWeapon)) {
                    info = "[M,C,S]";
                } else if ((weapon instanceof MRMWeapon) || (weapon instanceof RLWeapon)) {
                    info = "[M,C]";
                } else if ((weapon instanceof ISSnubNosePPC) || (weapon instanceof ISBombastLaser)) {
                    info = "[DE,V]";
                } else if (weapon instanceof ISVariableSpeedPulseLaserSmall) {
                    info = "[P,V]";
                } else if (weapon instanceof ISVariableSpeedPulseLaserMedium) {
                    info = "[P,V]";
                } else if (weapon instanceof ISVariableSpeedPulseLaserLarge) {
                    info = "[P,V]";
                } else if (weapon instanceof ISHGaussRifle) {
                    info = "[DB,X]";
                } else if (weapon instanceof ISPlasmaRifle) {
                    info = "[DE,H,AI]";
                } else if (weapon instanceof CLPlasmaCannon) {
                    info = "[DE,H,AI]";
                } else if (weapon instanceof HAGWeapon) {
                    info = "[C,F]";
                } else if (weapon instanceof ArtilleryWeapon) {
                    info = "[AE,S,F]";
                } else if (weapon instanceof ArtilleryCannonWeapon) {
                    info = "[DB,AE]";
                } else if (weapon instanceof ThunderBoltWeapon) {
                    info = "[M]";
                } else if (weapon instanceof NarcWeapon) {
                    info = "[M]";
                } else {
                    info = "";
                }
            } else if (weapon instanceof UACWeapon) {
                info = "[DB,R/C]";
            } else if (weapon instanceof TAGWeapon) {
                return "";
            } else {
                info = " [";

                if (weapon.hasFlag(WeaponType.F_BALLISTIC)) {
                    info += "DB,";
                }
                if (UnitUtil.isAMS(weapon) || (weapon instanceof BPodWeapon)) {
                    info += "PB,";
                } else if (weapon.hasFlag(WeaponType.F_PULSE)) {
                    info += "P,";
                } else if (weapon.hasFlag(WeaponType.F_ENERGY)) {
                    info += "DE,";
                }

                if ((weapon instanceof LBXACWeapon) || (weapon instanceof ISSilverBulletGauss)) {
                    info += "C/F,";
                }

                if (UnitUtil.hasSwitchableAmmo(weapon)) {
                    info += "S,";
                }

                if ((weapon instanceof MGWeapon) || (weapon instanceof BPodWeapon) ||
                        (weapon instanceof CLERPulseLaserSmall) ||
                        (weapon instanceof ISXPulseLaserSmall) ||
                        (weapon instanceof ISPulseLaserSmall) ||
                        (weapon instanceof CLPulseLaserSmall) ||
                        (weapon instanceof CLPulseLaserMicro)) {
                    info += "AI,";
                }

                if (weapon instanceof FlamerWeapon) {
                    info += "H,AI,";
                }

                if (weapon.isExplosive(mount) && !(weapon instanceof ACWeapon) && (!(weapon instanceof PPCWeapon) || ((mount.getLinkedBy() != null) && mount.getLinkedBy().getType().hasFlag(MiscType.F_PPC_CAPACITOR)))) {
                    info += "X,";
                }

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    info += "OS,";
                }

                info = info.substring(0, info.length() - 1) + "]";

            }
        } else if (mount.is(EquipmentTypeLookup.COOLANT_POD)) {
            info = "[PE,OS,X]";
        } else {
            info = "[E]";
        }
        return info;
    }
}
