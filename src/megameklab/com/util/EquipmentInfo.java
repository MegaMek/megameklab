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
package megameklab.com.util;

import java.math.BigInteger;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.FixedWingSupport;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Sensor;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.battlearmor.ISBACompactNarc;
import megamek.common.weapons.battlearmor.ISBAPopUpMineLauncher;
import megamek.common.weapons.capitalweapons.AR10Weapon;
import megamek.common.weapons.lasers.EnergyWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.missiles.ATMWeapon;
import megamek.common.weapons.missiles.MMLWeapon;
import megamek.common.weapons.mortars.CLVehicularGrenadeLauncher;
import megamek.common.weapons.mortars.ISVehicularGrenadeLauncher;
import megamek.common.weapons.other.ISCenturionWeaponSystem;
import megamek.common.weapons.srms.SRMWeapon;

public class EquipmentInfo {
    public int count = 0;
    public int minRange = 0;
    public int shtRange = 0;
    public int medRange = 0;
    public int longRange = 0;
    public int erRange = 0;
    public int heat = 0;
    public int techLevel = TechConstants.T_INTRO_BOXSET;
    // the following two are for BA ammo counting
    public int ammoCount = 0;
    public int location = 0;

    public String name = "";
    public String damage = "[E]";
    public String loc = "";

    public boolean isWeapon = false;
    public boolean isMML = false;
    public boolean isATM = false;
    public boolean isBAMineLayer = false;
    public boolean isCompactNarc = false;
    public boolean isBAPopUpMine = false;
    public boolean isManipulator = false;
    public boolean isBACargolifter = false;
    public boolean isAMS = false;
    public boolean isSponsonMounted = false;
    public boolean isPintleMounted = false;
    public boolean isMashCore = false;
    public boolean isDroneControl = false;
    public boolean isDestroyed = false;
    public boolean isCenturion = false;
    public boolean isAR10 = false;
    // for AR10, we need to check how many ammo
    public int ar10AmmoTypes = 0;
    public boolean hasArtemis = false;
    public boolean hasApollo = false;
    public boolean hasArtemisV = false;
    public boolean hasAmmo = false;

    public boolean shouldIndent = false;

    public int c3Level = 0;

    public static int C3S = 1;
    public static int C3M = 2;
    public static int C3I = 3;
    public static int C3SB = 4;
    public static int C3MB = 5;
    public static int C3REMOTESENSOR = 6;
    public static int C3EM = 7;

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
        loc = aero.getLocationAbbr(mount.getLocation());
        count = 1;
        techLevel = mount.getType().getTechLevel(mount.getEntity().getTechLevelYear());
        isDestroyed = mount.isDestroyed();

        damage = StringUtils.getEquipmentInfo(aero, mount);

        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            if (mount.getType().hasFlag(WeaponType.F_C3MBS)) {
                c3Level = C3MB;
            }
            WeaponType weapon = (WeaponType) mount.getType();
            if (weapon.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR) {
                c3Level = C3REMOTESENSOR;
            }
            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;
            isCenturion = weapon instanceof ISCenturionWeaponSystem;

            int bonus = 0;

            if (hasArtemis || hasArtemisV) {
                if (isMML) {
                    if (weapon.getRackSize() >= 7) {
                        bonus = 2;
                    } else if (weapon.getRackSize() >= 5) {
                        bonus = 1;
                    }
                } else if (weapon instanceof LRMWeapon) {
                    bonus = weapon.getRackSize() / 5;
                } else if (weapon instanceof SRMWeapon) {
                    bonus = 2;
                }
            }

            shtRange = (int) weapon.shortAV + bonus;
            if (weapon.maxRange >= WeaponType.RANGE_MED) {
                medRange = (int) weapon.medAV + bonus;
            }
            if (weapon.maxRange >= WeaponType.RANGE_LONG) {
                longRange = (int) weapon.longAV + bonus;
            }
            if (weapon.maxRange >= WeaponType.RANGE_EXT) {
                erRange = (int) weapon.extAV + bonus;
            }
            if (aero instanceof FixedWingSupport) {
                if (mount.getType() instanceof EnergyWeapon) {
                    heat = weapon.getHeat();
                } else {
                    heat = 0;
                }
            } else {
                heat = weapon.getHeat();
            }

            if (mount.getSecondLocation() != -1) {
                loc = String.format("%1$s/%2$s", aero.getLocationAbbr(mount.getLocation()),
                        aero.getLocationAbbr(mount.getSecondLocation()));
            }

        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3EM)))) {
            c3Level = C3EM;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3S)))) {
            c3Level = C3S;
        } else if (((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3SBS))) {
            c3Level = C3SB;
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
        } else if (mount.getType().hasFlag(MiscType.F_SEARCHLIGHT)) {
            shtRange = 0;
            medRange = 0;
            longRange = 170;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_PPC_CAPACITOR)) {
            heat = 5;
        }
    }

    /**
     *
     * @param dropship
     * @param mount
     */
    public EquipmentInfo(Dropship dropship, Mounted mount, Mounted bay) {
        name = UnitUtil.getCritName(dropship, mount.getType());
        damage = StringUtils.getEquipmentInfo(dropship, mount, bay);

        count = 1;
        techLevel = mount.getType().getTechLevel(dropship.getTechLevelYear());
        isDestroyed = mount.isDestroyed();

        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            if (mount.getType().hasFlag(WeaponType.F_C3MBS)) {
                c3Level = C3MB;
            }
            WeaponType weapon = (WeaponType) mount.getType();

            if (weapon.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR) {
                c3Level = C3REMOTESENSOR;
            }

            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isAR10 = (weapon instanceof AR10Weapon);

            if (isAR10) {
                int barracudaAmmo = 0;
                int killerwhaleAmmo = 0;
                int whitesharkAmmo = 0;
                for (int ammoIndex : bay.getBayAmmo()) {
                    Mounted ammoMount = dropship.getEquipment(ammoIndex);
                    try {
                        AmmoType aType = (AmmoType) ammoMount.getType();
                        if ((mount.getLinked() != null) && (aType.getRackSize() == weapon.getRackSize())
                                && (aType.getAmmoType() == weapon.getAmmoType())) {
                            if (aType.hasFlag(AmmoType.F_AR10_BARRACUDA)) {
                                barracudaAmmo += ammoMount.getUsableShotsLeft();
                            } else if (aType.hasFlag(AmmoType.F_AR10_KILLER_WHALE)) {
                                killerwhaleAmmo += ammoMount.getUsableShotsLeft();
                            } else if (aType.hasFlag(AmmoType.F_AR10_WHITE_SHARK)) {
                                whitesharkAmmo += ammoMount.getUsableShotsLeft();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (barracudaAmmo > 0) {
                    ar10AmmoTypes++;
                }
                if (killerwhaleAmmo > 0) {
                    ar10AmmoTypes++;
                }
                if (whitesharkAmmo > 0) {
                    ar10AmmoTypes++;
                }
            }
            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;
            isCenturion = weapon instanceof ISCenturionWeaponSystem;

            int bonus = 0;
            if (hasArtemis || hasArtemisV) {
                if (isMML) {
                    if (weapon.getRackSize() >= 7) {
                        bonus = 2;
                    } else if (weapon.getRackSize() >= 5) {
                        bonus = 1;
                    }
                } else if (weapon instanceof LRMWeapon) {
                    bonus = weapon.getRackSize() / 5;
                } else if (weapon instanceof SRMWeapon) {
                    bonus = 2;
                }
            }

            shtRange = (int) weapon.shortAV + bonus;

            if (weapon.hasFlag(WeaponType.F_AMS)) {
                isAMS = true;
                shtRange = 3;
            }
            if (weapon.maxRange >= WeaponType.RANGE_MED) {
                medRange = (int) weapon.medAV + bonus;
            }
            if (weapon.maxRange >= WeaponType.RANGE_LONG) {
                longRange = (int) weapon.longAV + bonus;
            }
            if (weapon.maxRange >= WeaponType.RANGE_EXT) {
                erRange = (int) weapon.extAV + bonus;
            }
            heat = weapon.getHeat();
            if (mount.getSecondLocation() != -1) {
                loc = String.format("%1$s/%2$s", dropship.getLocationAbbr(mount.getLocation()),
                        dropship.getLocationAbbr(mount.getSecondLocation()));
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3EM)))) {
            c3Level = C3EM;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3S)))) {
            c3Level = C3S;
        } else if (((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3SBS))) {
            c3Level = C3SB;
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
        } else if (mount.getType().hasFlag(MiscType.F_SEARCHLIGHT)) {
            shtRange = 0;
            medRange = 0;
            longRange = 170;
        }

        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);
        isMashCore = (mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_MASH);
        isDroneControl = (mount.getType() instanceof MiscType)
                && mount.getType().hasFlag(MiscType.F_DRONE_CARRIER_CONTROL);
    }

    /**
     * Info for non Aero Entities.
     *
     * @param unit
     * @param mount
     */
    public EquipmentInfo(Entity unit, Mounted mount) {

        name = UnitUtil.getCritName(unit, mount.getType());
        loc = unit.getLocationAbbr(mount.getLocation());
        if (mount.isRearMounted()) {
            name += "(R)";
        }
        if ((unit instanceof Tank) && !((Tank) unit).hasNoDualTurret()) {
            if (loc.equals("TU")) {
                loc = "RT";
            }
        }
        isSponsonMounted = mount.isSponsonTurretMounted();
        isPintleMounted = mount.isPintleTurretMounted();
        count = 1;
        techLevel = mount.getType().getTechLevel(unit.getTechLevelYear());
        isDestroyed = mount.isDestroyed();

        damage = StringUtils.getEquipmentInfo(unit, mount);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            if (mount.getType().hasFlag(WeaponType.F_C3MBS)) {
                c3Level = C3MB;
            }
            WeaponType weapon = (WeaponType) mount.getType();

            if (weapon.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR) {
                c3Level = C3REMOTESENSOR;
            }

            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;
            isCompactNarc = weapon instanceof ISBACompactNarc;
            isAMS = weapon.hasFlag(WeaponType.F_AMS);
            isCenturion = weapon instanceof ISCenturionWeaponSystem;

            shtRange = weapon.shortRange;
            medRange = weapon.mediumRange;
            longRange = weapon.longRange;

            if ((weapon.getAmmoType() == AmmoType.T_LRM_TORPEDO) || (weapon.getAmmoType() == AmmoType.T_SRM_TORPEDO)) {
                shtRange = weapon.waterShortRange;
                medRange = weapon.waterMediumRange;
                longRange = weapon.waterLongRange;
            }

            if (medRange >= longRange) {
                longRange = -1;
            }

            if (shtRange > medRange) {
                medRange = -1;
            }
            if ((mount.getType() instanceof ISVehicularGrenadeLauncher)
                    || (mount.getType() instanceof CLVehicularGrenadeLauncher)) {
                minRange = 0;
                shtRange = 0;
                medRange = 0;
                longRange = 1;
            }

            heat = weapon.getHeat();
            if (mount.getSecondLocation() != -1) {
                loc = String.format("%1$s/%2$s", unit.getLocationAbbr(mount.getLocation()),
                        unit.getLocationAbbr(mount.getSecondLocation()));
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3I)) {
            c3Level = C3I;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3EM)))) {
            c3Level = C3EM;
        } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3S)))) {
            c3Level = C3S;
        } else if (((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3SBS))) {
            c3Level = C3SB;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_ECM)) {
            if (mount.getType().getInternalName().equals(Sensor.WATCHDOG)) {
                longRange = 4;
            } else {
                longRange = 6;
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BAP)) {
            if (mount.getType().getInternalName().equals(Sensor.LIGHT_AP)) {
                longRange = 3;
            } else if (mount.getType().getInternalName().equals(Sensor.BAP)) {
                longRange = 4;
            } else if (mount.getType().getInternalName().equals(Sensor.BLOODHOUND)) {
                longRange = 6;
            } else if (mount.getType().getInternalName().equals(Sensor.CLAN_AP)) {
                longRange = 5;
            }
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_SEARCHLIGHT)) {
            shtRange = 0;
            medRange = 0;
            longRange = 170;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BA_SEARCHLIGHT)) {
            shtRange = 0;
            medRange = 0;
            longRange = 9;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_SPRAYER)) {
            shtRange = 0;
            medRange = 0;
            longRange = 1;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_CLUB)
                && (mount.getType().hasSubType(MiscType.S_VIBRO_LARGE)
                        || mount.getType().hasSubType(MiscType.S_VIBRO_MEDIUM)
                        || mount.getType().hasSubType(MiscType.S_VIBRO_SMALL))) {
            heat = unit.getActiveVibrobladeHeat(mount.getLocation(), true);
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_CLUB)
                && (mount.getType().hasSubType(MiscType.S_SPOT_WELDER))) {
            heat = 2;
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_PPC_CAPACITOR)) {
            heat = 5;
        } else if ((mount.getType() instanceof MiscType)
                && (mount.getType().hasFlag(MiscType.F_TALON) || (mount.getType().hasFlag(MiscType.F_TRACKS)))) {
            loc = "Legs";
        } else if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_PARTIAL_WING)) {
            loc = "LT/RT";
        } else if ((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_VOIDSIG)
                || mount.getType().hasFlag(MiscType.F_CHAMELEON_SHIELD) || mount.getType().hasFlag(MiscType.F_NULLSIG)
                || mount.getType().hasFlag(MiscType.F_BLUE_SHIELD))) {
            loc = "*";
        }

        isBAMineLayer = (mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_MINE)
                && mount.getType().hasFlag(MiscType.F_BA_EQUIPMENT);
        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);
        isMashCore = (mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_MASH);
        isDroneControl = (mount.getType() instanceof MiscType)
                && mount.getType().hasFlag(MiscType.F_DRONE_CARRIER_CONTROL);

    }

    public EquipmentInfo(BattleArmor unit, Mounted mount) {

        name = UnitUtil.getCritName(unit, mount.getType());
        loc = unit.getLocationAbbr(mount.getLocation());

        if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_BA_SEARCHLIGHT)) {
            name = "Searchlight";
        }

        if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_SINGLE_HEX_ECM)) {
            name = "ECM Suite";
        }

        if (mount.isBodyMounted() && (unit.getChassisType() == BattleArmor.CHASSIS_TYPE_BIPED)) {
            name += " (Body)";
        }

        if (mount.isDWPMounted()) {
            name += " (DWP)";
        }

        count = 1;
        techLevel = mount.getType().getTechLevel(unit.getTechLevelYear());
        isDestroyed = mount.isDestroyed();

        damage = StringUtils.getEquipmentInfo(unit, mount);

        if ((mount.getType() instanceof WeaponType) && !mount.getType().hasFlag(WeaponType.F_MGA)) {
            if (mount.getType().hasFlag(WeaponType.F_C3M)) {
                c3Level = C3M;
            }
            if (mount.getType().hasFlag(WeaponType.F_C3MBS)) {
                c3Level = C3MB;
            }

            WeaponType weapon = (WeaponType) mount.getType();

            isCompactNarc = weapon instanceof ISBACompactNarc;
            isBAPopUpMine = weapon instanceof ISBAPopUpMineLauncher;

            if (weapon.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR) {
                c3Level = C3REMOTESENSOR;
            }

            if ((weapon.getAmmoType() != AmmoType.T_NA) && !weapon.hasFlag(WeaponType.F_ONESHOT)) {
                hasAmmo = true;
                ammoCount = UnitUtil.getBAAmmoCount(unit, weapon, mount.getLocation())
                        / UnitUtil.getNumberOfEquipmentLikeThis(unit, weapon);
                if (isBAPopUpMine) {
                    ammoCount = 1;
                }
                location = mount.getLocation();
            }

            minRange = Math.max(0, weapon.minimumRange);
            isWeapon = true;

            isMML = weapon instanceof MMLWeapon;
            isATM = weapon instanceof ATMWeapon;

            shtRange = weapon.shortRange;
            medRange = weapon.mediumRange;
            longRange = weapon.longRange;

            if (medRange == longRange) {
                longRange = -1;
            }
            if (medRange == shtRange) {
                medRange = -1;
            }

            heat = weapon.getHeat();
        } else if (mount.getType() instanceof MiscType) {
            MiscType equipment = (MiscType) mount.getType();

            if (equipment.hasFlag(MiscType.F_C3I)) {
                c3Level = C3I;
            } else if (((mount.getType() instanceof MiscType) && (mount.getType().hasFlag(MiscType.F_C3S)))) {
                c3Level = C3S;
            } else if (((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_C3SBS))) {
                c3Level = C3SB;
            } else if (equipment.hasFlag(MiscType.F_ECM)) {
                if (equipment.getInternalName().equals(Sensor.WATCHDOG)) {
                    longRange = 4;
                } else if (equipment.hasFlag(MiscType.F_SINGLE_HEX_ECM)) {
                    longRange = 0;
                } else if (equipment.hasFlag(MiscType.F_ANGEL_ECM)) {
                    longRange = 2;
                } else {
                    longRange = 6;
                }
            } else if (equipment.hasFlag(MiscType.F_BAP)) {
                if (equipment.getInternalName().equals(Sensor.BAP)) {
                    longRange = 4;
                } else if (equipment.getInternalName().equals(Sensor.BLOODHOUND)) {
                    longRange = 6;
                } else if (equipment.getInternalName().equals(Sensor.CLAN_AP)) {
                    longRange = 5;
                } else if (equipment.getInternalName().equals(Sensor.CLBALIGHT_AP)) {
                    longRange = 3;
                } else if (equipment.getInternalName().equals(Sensor.ISIMPROVED)
                        || equipment.getInternalName().equals(Sensor.CLIMPROVED)) {
                    longRange = 2;
                }
            } else if (equipment.hasFlag(MiscType.F_BA_SEARCHLIGHT)) {
                shtRange = 0;
                medRange = 0;
                longRange = 9;
            }
            isBAMineLayer = equipment.hasFlag(MiscType.F_MINE) && equipment.hasFlag(MiscType.F_BA_EQUIPMENT);
        }

        hasArtemis = hasLinkedEquipment(mount, MiscType.F_ARTEMIS);
        hasArtemisV = hasLinkedEquipment(mount, MiscType.F_ARTEMIS_V);
        hasApollo = hasLinkedEquipment(mount, MiscType.F_APOLLO);
        isManipulator = UnitUtil.isManipulator(mount);
        isBACargolifter = (mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_CARGOLIFTER);
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
        clone.loc = loc;

        clone.name = name;
        clone.damage = damage;

        clone.isDestroyed = isDestroyed;

        clone.isWeapon = isWeapon;
        clone.isMML = isMML;
        clone.isAR10 = isAR10;
        clone.isATM = isATM;
        clone.isCompactNarc = isCompactNarc;
        clone.isBAPopUpMine = isBAPopUpMine;
        clone.isBAMineLayer = isBAMineLayer;

        clone.hasAmmo = hasAmmo;
        clone.hasApollo = hasApollo;
        clone.hasArtemis = hasArtemis;
        clone.hasArtemisV = hasArtemisV;

        clone.ammoCount = ammoCount;
        clone.location = location;

        clone.c3Level = c3Level;

        return clone;
    }

    private boolean hasLinkedEquipment(Mounted eq, BigInteger flag) {

        if ((eq.getLinkedBy() != null) && (eq.getLinkedBy().getType() instanceof MiscType)
                && eq.getLinkedBy().getType().hasFlag(flag)) {
            return true;
        }

        return false;
    }
}
