/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.BPodWeapon;
import megamek.common.weapons.CLAMS;
import megamek.common.weapons.CLLaserAMS;
import megamek.common.weapons.EnergyWeapon;
import megamek.common.weapons.GaussWeapon;
import megamek.common.weapons.HAGWeapon;
import megamek.common.weapons.HVACWeapon;
import megamek.common.weapons.ISAMS;
import megamek.common.weapons.ISLaserAMS;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.LRTWeapon;
import megamek.common.weapons.LegAttack;
import megamek.common.weapons.MGWeapon;
import megamek.common.weapons.MPodWeapon;
import megamek.common.weapons.MRMWeapon;
import megamek.common.weapons.RLWeapon;
import megamek.common.weapons.SRMWeapon;
import megamek.common.weapons.SRTWeapon;
import megamek.common.weapons.StopSwarmAttack;
import megamek.common.weapons.SwarmAttack;
import megamek.common.weapons.ThunderBoltWeapon;
import megamek.common.weapons.UACWeapon;
import megamek.common.weapons.infantry.InfantryRifleAutoRifleWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;

public class UnitUtil {

    public static String TARGETINGCOMPUTER = "Targeting Computer";
    public static String ISTARGETINGCOMPUTER = "ISTargeting Computer";
    public static String CLTARGETINGCOMPUTER = "CLTargeting Computer";
    public static String TSM = "TSM";
    public static String INDUSTRIALTSM = "Industrial TSM";
    public static String ENVIROSEAL = "Environmental Sealing";
    public static String NULLSIG = "Null Signature System";
    public static String VOIDSIG = "Void Signature System";
    public static String TRACKS = "Tracks";
    public static String TALONS = "Talons";
    public static String CHAMELEON = "Chameleon Light Polarization Field";
    public static String PARTIALWING = "Partial Wing";
    public static String JUMPBOOSTER = "Jump Booster";
    public static String BLUESHIELD = "Blue Shield Particle Field Damper";
    public static String STEALTH = "Stealth Armor";

    public static int TECH_INTRO = 0;
    public static int TECH_STANDARD = 1;
    public static int TECH_ADVANCED = 2;
    public static int TECH_EXPERIMENTAL = 3;
    public static int TECH_UNOFFICAL = 4;

    private static Font euroFont = null;
    private static Font euroBoldFont = null;

    /**
     * tells is EquipementType is an equipment that uses crits/mounted and is
     * spread across multiple locations
     *
     * @param eq
     * @return
     */
    public static boolean isSpreadEquipment(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_JUMP_BOOSTER) || eq.hasFlag(MiscType.F_PARTIAL_WING) || eq.hasFlag(MiscType.F_NULLSIG) || eq.hasFlag(MiscType.F_VOIDSIG) || eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING) || eq.hasFlag(MiscType.F_TRACKS) || eq.hasFlag(MiscType.F_TALON) || eq.hasFlag(MiscType.F_CHAMELEON_SHIELD) || eq.hasFlag(MiscType.F_BLUE_SHIELD));
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq
     * @return
     */
    public static boolean isArmor(EquipmentType eq) {
        for (String armor : EquipmentType.armorNames) {
            if (eq.getName().equals(armor)) {
                return true;
            }
        }

        return false;
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq
     * @return
     */
    public static boolean isStructure(EquipmentType eq) {
        for (String armor : EquipmentType.structureNames) {
            if (eq.getName().equals(armor)) {
                return true;
            }
        }

        return false;
    }

    /**
     * tells if EquipmentType is TSM or TargetComp
     *
     * @param eq
     * @return
     */
    public static boolean isTSM(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_TSM) || eq.hasFlag((MiscType.F_INDUSTRIAL_TSM)));
    }

    /**
     * Returns the number of crits used by EquipmentType eq, 1 if armor or
     * structure EquipmentType
     *
     * @param unit
     * @param eq
     * @return
     */
    public static int getCritsUsed(Entity unit, EquipmentType eq) {

        if (!(unit instanceof Mech)) {
            return 0;
        }

        if (eq.getName().equals(UnitUtil.PARTIALWING)) {
            return 3;
        }

        if (eq.getName().equals(UnitUtil.JUMPBOOSTER) || eq.getName().equals(UnitUtil.TALONS)) {
            return 2;
        }

        if (UnitUtil.isSpreadEquipment(eq) || UnitUtil.isTSM(eq) || UnitUtil.isArmorOrStructure(eq)) {
            return 1;
        }

        return eq.getCriticals(unit);
    }

    public static void removeMounted(Mech mech, Mounted mount) {
        if (mount.getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeCrits(mech, UnitUtil.TSM);
            UnitUtil.removeMounts(mech, UnitUtil.TSM);
        } else if (mount.getName().equals(UnitUtil.INDUSTRIALTSM)) {
            UnitUtil.removeCrits(mech, UnitUtil.INDUSTRIALTSM);
            UnitUtil.removeMounts(mech, UnitUtil.INDUSTRIALTSM);
        } else if (mount.getName().equals(UnitUtil.ENVIROSEAL)) {
            EquipmentType.get(UnitUtil.ENVIROSEAL).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.ENVIROSEAL);
            UnitUtil.removeMounts(mech, UnitUtil.ENVIROSEAL);
        } else if (mount.getName().equals(UnitUtil.NULLSIG)) {
            UnitUtil.removeCrits(mech, UnitUtil.NULLSIG);
            UnitUtil.removeMounts(mech, UnitUtil.NULLSIG);
        } else if (mount.getName().equals(UnitUtil.VOIDSIG)) {
            UnitUtil.removeCrits(mech, UnitUtil.VOIDSIG);
            UnitUtil.removeMounts(mech, UnitUtil.VOIDSIG);
        } else if (mount.getName().equals(UnitUtil.TRACKS)) {
            EquipmentType.get(UnitUtil.TRACKS).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.TRACKS);
            UnitUtil.removeMounts(mech, UnitUtil.TRACKS);
        } else if (mount.getName().equals(UnitUtil.TALONS)) {
            EquipmentType.get(UnitUtil.TALONS).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.TALONS);
            UnitUtil.removeMounts(mech, UnitUtil.TALONS);
        } else if (mount.getName().equals(UnitUtil.PARTIALWING)) {
            EquipmentType.get(UnitUtil.PARTIALWING).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.PARTIALWING);
            UnitUtil.removeMounts(mech, UnitUtil.PARTIALWING);
        } else if (mount.getName().equals(UnitUtil.JUMPBOOSTER)) {
            EquipmentType.get(UnitUtil.JUMPBOOSTER).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.JUMPBOOSTER);
            UnitUtil.removeMounts(mech, UnitUtil.JUMPBOOSTER);
        } else if (mount.getName().equals(UnitUtil.BLUESHIELD)) {
            UnitUtil.removeCrits(mech, UnitUtil.BLUESHIELD);
            UnitUtil.removeMounts(mech, UnitUtil.BLUESHIELD);
        } else {
            UnitUtil.removeCriticals(mech, mount);
            mech.getEquipment().remove(mount);
            if (mount.getType() instanceof MiscType) {
                mech.getMisc().remove(mount);
            } else if (mount.getType() instanceof AmmoType) {
                mech.getAmmo().remove(mount);
            } else {
                mech.getWeaponList().remove(mount);
            }
        }

    }

    public static void removeMounted(Entity unit, Mounted mount) {

        if (mount.getName().equals(UnitUtil.ENVIROSEAL)) {
            EquipmentType.get(UnitUtil.ENVIROSEAL).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeMounts(unit, UnitUtil.ENVIROSEAL);
        } else {
            unit.getEquipment().remove(mount);
            if (mount.getType() instanceof MiscType) {
                unit.getMisc().remove(mount);
            } else if (mount.getType() instanceof AmmoType) {
                unit.getAmmo().remove(mount);
            } else {
                unit.getWeaponList().remove(mount);
            }
        }

    }

    /**
     * Removes a Mounted object from the units various equipment lists
     *
     * @param unit
     * @param eq
     */
    public static void removeMounted(Entity unit, EquipmentType eq) {

        if (!(unit instanceof Mech)) {
            return;
        }

        Mech mech = (Mech) unit;
        Mounted equipment = null;
        for (Mounted mount : mech.getEquipment()) {
            if (mount.getType().getInternalName().equals(eq.getInternalName())) {
                equipment = mount;
                break;
            }
        }

        if (equipment == null) {
            return;
        }

        if (equipment.getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeCrits(mech, UnitUtil.TSM);
            UnitUtil.removeMounts(mech, UnitUtil.TSM);
        } else if (equipment.getName().equals(UnitUtil.INDUSTRIALTSM)) {
            UnitUtil.removeCrits(mech, UnitUtil.INDUSTRIALTSM);
            UnitUtil.removeMounts(mech, UnitUtil.INDUSTRIALTSM);
        } else if (equipment.getName().equals(UnitUtil.ENVIROSEAL)) {
            EquipmentType.get(UnitUtil.ENVIROSEAL).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.ENVIROSEAL);
            UnitUtil.removeMounts(mech, UnitUtil.ENVIROSEAL);
        } else if (equipment.getName().equals(UnitUtil.NULLSIG)) {
            UnitUtil.removeCrits(mech, UnitUtil.NULLSIG);
            UnitUtil.removeMounts(mech, UnitUtil.NULLSIG);
        } else if (equipment.getName().equals(UnitUtil.VOIDSIG)) {
            UnitUtil.removeCrits(mech, UnitUtil.VOIDSIG);
            UnitUtil.removeMounts(mech, UnitUtil.VOIDSIG);
        } else if (equipment.getName().equals(UnitUtil.TRACKS)) {
            EquipmentType.get(UnitUtil.TRACKS).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.TRACKS);
            UnitUtil.removeMounts(mech, UnitUtil.TRACKS);
        } else if (equipment.getName().equals(UnitUtil.TALONS)) {
            EquipmentType.get(UnitUtil.TALONS).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.TALONS);
            UnitUtil.removeMounts(mech, UnitUtil.TALONS);
        } else if (equipment.getName().equals(UnitUtil.PARTIALWING)) {
            EquipmentType.get(UnitUtil.PARTIALWING).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.PARTIALWING);
            UnitUtil.removeMounts(mech, UnitUtil.PARTIALWING);
        } else if (equipment.getName().equals(UnitUtil.JUMPBOOSTER)) {
            EquipmentType.get(UnitUtil.JUMPBOOSTER).setTonnage(EquipmentType.TONNAGE_VARIABLE);
            UnitUtil.removeCrits(mech, UnitUtil.JUMPBOOSTER);
            UnitUtil.removeMounts(mech, UnitUtil.JUMPBOOSTER);
        } else if (equipment.getName().equals(UnitUtil.BLUESHIELD)) {
            UnitUtil.removeCrits(mech, UnitUtil.BLUESHIELD);
            UnitUtil.removeMounts(mech, UnitUtil.BLUESHIELD);
            EquipmentType.get(UnitUtil.BLUESHIELD).setTonnage(3);
        } else {
            UnitUtil.removeCriticals(mech, equipment);
            mech.getEquipment().remove(equipment);
            if (equipment.getType() instanceof MiscType) {
                mech.getMisc().remove(equipment);
            } else if (equipment.getType() instanceof AmmoType) {
                mech.getAmmo().remove(equipment);
            } else {
                mech.getWeaponList().remove(equipment);
            }
        }
    }

    /**
     * Removes mounts of a certain type from the Mek.
     *
     * @param Unit
     */
    public static void removeMounts(Entity unit, String mountName) {

        ListIterator<Mounted> iterator = unit.getEquipment().listIterator();
        while (iterator.hasNext()) {
            Mounted mount = iterator.next();
            if (mount.getType().getName().equals(mountName) || mount.getType().getInternalName().equals(mountName)) {
                iterator.remove();
                unit.getMisc().remove(mount);
            }
        }
    }

    /**
     * Removes all crits of a certain type from
     *
     * @param unit
     */
    public static void removeCrits(Mech unit, String critType) {

        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = crit.getMount();

                    if ((mount != null) && (mount.getType().getName().equals(critType) || mount.getType().getInternalName().equals(critType))) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

    /**
     * Sets the corresponding critical slots to null for the Mounted object.
     *
     * @param unit
     * @param eq
     */
    public static void removeCriticals(Mech unit, Mounted eq) {

        if (eq.getLocation() == Entity.LOC_NONE) {
            return;
        }
        if (UnitUtil.isTargettingComputer(eq)) {
            UnitUtil.removeTCCrits(unit);
        } else if ((eq.getType() instanceof WeaponType) && (eq.isSplitable() || eq.getType().isSpreadable())) {
            UnitUtil.removeSplitCriticals(unit, eq);
        } else {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            int location = eq.getLocation();
            for (int slot = 0; (slot < unit.getNumberOfCriticals(location)) && (critsUsed > 0); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getMount() == eq)) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                    --critsUsed;
                }
            }
        }
    }

    /**
     * Tells if param EQ is a targetting computer.
     *
     * @param eq
     *            Mounted that might be a targetting computer
     * @return True if is a targetting computer false if not.
     */
    public static boolean isTargettingComputer(Mounted eq) {
        if (eq.getType().getName().equals(UnitUtil.TARGETINGCOMPUTER) || eq.getType().getInternalName().equals(UnitUtil.CLTARGETINGCOMPUTER) || eq.getType().getInternalName().equals(UnitUtil.ISTARGETINGCOMPUTER)) {
            return true;
        }

        return false;
    }

    /**
     * Removes crits for weapons that have split locations
     *
     * @param unit
     * @param eq
     */
    public static void removeSplitCriticals(Mech unit, Mounted eq) {

        int location = eq.getLocation();
        if (location != Entity.LOC_NONE) {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            for (int slot = 0; (slot < unit.getNumberOfCriticals(location)) && (critsUsed > 0); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getMount().equals(eq))) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                    --critsUsed;
                }
            }
        }

        location = eq.getSecondLocation();
        if (location != Entity.LOC_NONE) {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            for (int slot = 0; (slot < unit.getNumberOfCriticals(location)) && (critsUsed > 0); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getMount().equals(eq))) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                    --critsUsed;
                }
            }
        }

    }

    /**
     * Reset all the Crits and Mounts on the Unit.
     *
     * @param unit
     */
    public static void resetCriticalsAndMounts(Mech unit) {
        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);

                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                }
            }
        }

        for (Mounted mount : unit.getEquipment()) {
            mount.setLocation(Entity.LOC_NONE, false);
        }

    }

    /**
     * Check to see if the unit is using Clan TC
     *
     * @param unit
     * @return
     */
    public static boolean hasClanTC(Mech unit) {

        for (Mounted mount : unit.getMisc()) {
            if (mount.getType().getInternalName().equals(CLTARGETINGCOMPUTER)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates TC Crits and Mounts based on weapons on a unit or if the TC has
     * been removed.
     *
     * @param unit
     */
    public static void updateTC(Mech unit, boolean isClan) {

        UnitUtil.removeTCCrits(unit);
        UnitUtil.removeTCMounts(unit);
        UnitUtil.createTCMounts(unit, isClan);
    }

    /**
     * Creates TC Mounts and Criticals for a Unit.
     *
     * @param unit
     */
    public static void createTCMounts(Mech unit, boolean isClan) {
        int tcCount = 0;
        String targetingComputerType = "";

        if (unit.isMixedTech()) {
            if (isClan) {
                targetingComputerType = UnitUtil.CLTARGETINGCOMPUTER;
            } else {
                targetingComputerType = UnitUtil.ISTARGETINGCOMPUTER;
            }
        } else if (unit.isClan()) {
            targetingComputerType = UnitUtil.CLTARGETINGCOMPUTER;
        } else {
            targetingComputerType = UnitUtil.ISTARGETINGCOMPUTER;
        }

        tcCount = EquipmentType.get(targetingComputerType).getCriticals(unit);

        if (tcCount < 1) {
            return;
        }

        try {
            unit.addEquipment(new Mounted(unit, EquipmentType.get(targetingComputerType)), Entity.LOC_NONE, false);
        } catch (Exception ex) {

        }
    }

    /**
     * Checks to see if unit can use the techlevel
     *
     * @param unit
     * @param techLevel
     * @return Boolean if the tech level is legal for the passed unit
     */
    public static boolean isLegal(Entity unit, int techLevel) {

        boolean legalTech = TechConstants.isLegal(unit.getTechLevel(), techLevel, true);

        if (!legalTech) {
            return legalTech;
        }

        if (unit.isMixedTech()) {
            return true;
        }

        if (unit.getTechLevel() >= TechConstants.T_IS_ADVANCED) {
            if (unit.isClan()) {
                if ((techLevel == TechConstants.T_INTRO_BOXSET) || (techLevel == TechConstants.T_IS_TW_NON_BOX) || (techLevel == TechConstants.T_IS_ADVANCED) || (techLevel == TechConstants.T_IS_EXPERIMENTAL) || (techLevel == TechConstants.T_IS_UNOFFICIAL)) {
                    return false;
                }
            } else {
                if ((techLevel == TechConstants.T_CLAN_TW) || (techLevel == TechConstants.T_CLAN_ADVANCED) || (techLevel == TechConstants.T_CLAN_EXPERIMENTAL) || (techLevel == TechConstants.T_CLAN_UNOFFICIAL)) {
                    return false;
                }
            }
        }

        return legalTech;
    }

    /**
     * Checks to see if the unit uses compact heat sinks
     *
     * @param unit
     * @return
     */
    public static boolean hasCompactHeatSinks(Mech unit) {

        if (!unit.hasDoubleHeatSinks() || unit.hasLaserHeatSinks()) {
            return false;
        }

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) || mounted.getType().hasFlag(MiscType.F_HEAT_SINK)) {

                if (mounted.getType().getInternalName().indexOf("Compact") > -1) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * Checks if the unit has laser heatsinks.
     *
     * @param unit
     * @return
     */
    public static boolean hasLaserHeatSinks(Mech unit) {

        if (!unit.hasDoubleHeatSinks()) {
            return false;
        }

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if Mounted is a heat sink
     *
     * @param eq
     * @return
     */
    public static boolean isHeatSink(Mounted eq) {
        if ((eq.getType() instanceof MiscType) && (eq.getType().hasFlag(MiscType.F_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_LASER_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK))) {
            return true;
        }

        return false;
    }

    /**
     * Checks if EquipmentType is a heat sink
     *
     * @param eq
     * @return
     */
    public static boolean isHeatSink(EquipmentType eq) {
        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_HEAT_SINK) || eq.hasFlag(MiscType.F_LASER_HEAT_SINK) || eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK))) {
            return true;
        }

        return false;
    }

    /**
     * Removes all heat sinks from the mek
     *
     * @param unit
     */
    public static void removeHeatSinks(Mech unit) {

        ConcurrentLinkedQueue<Mounted> equipmentList = new ConcurrentLinkedQueue<Mounted>(unit.getMisc());
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isHeatSink(eq)) {
                UnitUtil.removeCriticals(unit, eq);
            }
        }
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isHeatSink(eq)) {
                unit.getMisc().remove(eq);
                unit.getEquipment().remove(eq);
            }
        }
    }

    /**
     * adds all heat sinks to the mech
     *
     * @param unit
     * @param hsAmount
     * @param hsType
     */
    public static void addHeatSinkMounts(Mech unit, int hsAmount, int hsType) {
        int engineHSCapacity = UnitUtil.getBaseChassisHeatSinks(unit);

        int heatSinks = hsAmount - engineHSCapacity;
        EquipmentType sinkType;

        sinkType = EquipmentType.get(UnitUtil.getHeatSinkType(hsType, unit.isClan()));

        for (; heatSinks > 0; heatSinks--) {

            try {
                unit.addEquipment(new Mounted(unit, sinkType), Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getHeatSinkType(int type, boolean clan) {
        String heatSinkType = "Heat Sink";
        if (clan) {

            switch (type) {
                case 0:
                    heatSinkType = "Heat Sink";
                    break;
                case 1:
                    heatSinkType = "CLDoubleHeatSink";
                    break;
                case 2:
                    heatSinkType = "CLLaser Heat Sink";
                    break;
            }
        } else {
            switch (type) {
                case 0:
                    heatSinkType = "Heat Sink";
                    break;
                case 1:
                    heatSinkType = "ISDoubleHeatSink";
                    break;

                case 2:
                    heatSinkType = "IS2 Compact Heat Sinks";
                    break;
            }
        }

        return heatSinkType;
    }

    /**
     * updates the heat sinks.
     *
     * @param unit
     * @param hsAmount
     * @param hsType
     */
    public static void updateHeatSinks(Mech unit, int hsAmount, int hsType) {

        UnitUtil.removeHeatSinks(unit);

        unit.addEngineSinks(UnitUtil.getHeatSinkType(hsType, unit.isClan()), Math.min(hsAmount, UnitUtil.getBaseChassisHeatSinks(unit)));

        UnitUtil.addHeatSinkMounts(unit, hsAmount, hsType);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq
     * @return
     */
    public static boolean isPrintableEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isSpreadEquipment(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }
        if (!eq.isHittable()) {
            return false;
        }

        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_ARTEMIS) || eq.hasFlag(MiscType.F_ARTEMIS_V) || eq.hasFlag(MiscType.F_APOLLO) || eq.hasFlag(MiscType.F_MASC) || eq.hasFlag(MiscType.F_HARJEL) || eq.hasFlag(MiscType.F_MASS))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        if ((eq instanceof LegAttack) || (eq instanceof SwarmAttack) || (eq instanceof StopSwarmAttack) || (eq instanceof InfantryRifleAutoRifleWeapon)) {
            return false;
        }
        return true;

    }

    public static void changeMountStatus(Entity unit, Mounted eq, int location, int secondaryLocation, boolean rear) {
        eq.setLocation(location, rear);
        eq.setSecondLocation(secondaryLocation, rear);
        eq.setSplit(secondaryLocation > -1);
    }

    public static boolean hasTargComp(Entity unit) {

        for (Mounted mount : unit.getEquipment()) {
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TARGCOMP)) {
                return true;
            }
        }

        return false;
    }

    public static int[] getHighestContinuousNumberOfCritsArray(Mech unit) {
        int[] critSpaces = new int[]
            { 0, 0, 0, 0, 0, 0, 0, 0 };

        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            critSpaces[loc] = UnitUtil.getHighestContinuousNumberOfCrits(unit, loc);
        }

        return critSpaces;
    }

    public static int getHighestContinuousNumberOfCrits(Entity unit, int location) {
        int highestNumberOfCrits = 0;
        int currentCritCount = 0;

        for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
            if (unit.getCritical(location, slot) == null) {
                currentCritCount++;
            } else {
                currentCritCount = 0;
            }
            highestNumberOfCrits = Math.max(currentCritCount, highestNumberOfCrits);
        }

        return highestNumberOfCrits;
    }

    public static double getUnallocatedAmmoTonnage(Entity unit) {
        double tonnage = 0;

        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                tonnage += mount.getType().getTonnage(unit);
            }
        }

        return tonnage;
    }

    public static double getMaximumArmorTonnage(Entity unit) {

        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(), unit.getArmorTechLevel());
        double armorWeight = 0;

        if (unit.getArmorType() == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        if (unit instanceof Mech) {
            double points = (unit.getTotalInternal() * 2) + 3;
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof Tank) {
            armorWeight = Math.floor((unit.getWeight() * 3.5) + 40);
            armorWeight /= armorPerTon;
            armorWeight *= 10;
            armorWeight = Math.ceil(armorWeight);

            if (armorWeight % 5 != 0) {
                armorWeight += 5 - (armorWeight % 5);
            }

            armorWeight /= 10;

        }
        return armorWeight;
    }

    public static int getArmorPoints(Entity unit, double armorTons) {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(), unit.getArmorTechLevel());
        if (unit.getArmorType() == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        return (int) Math.floor(armorPerTon * armorTons);
    }

    public static void reIndexCrits(Mech unit) {

        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);

                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    cs.setIndex(unit.getEquipmentNum(cs.getMount()));
                }
            }
        }
    }

    public static void compactCriticals(Mech mech) {
        for (int loc = 0; loc < mech.locations(); loc++) {
            UnitUtil.compactCriticals(mech, loc);
        }
    }

    private static void compactCriticals(Mech mech, int loc) {
        if (loc == Mech.LOC_HEAD) {
            // This location has an empty slot inbetween systems crits
            // which will mess up parsing if compacted.
            return;
        }
        int firstEmpty = -1;
        for (int slot = 0; slot < mech.getNumberOfCriticals(loc); slot++) {
            CriticalSlot cs = mech.getCritical(loc, slot);

            if ((cs == null) && (firstEmpty == -1)) {
                firstEmpty = slot;
            }
            if ((firstEmpty != -1) && (cs != null)) {
                // move this to the first empty slot
                mech.setCritical(loc, firstEmpty, cs);
                // mark the old slot empty
                mech.setCritical(loc, slot, null);
                // restart just after the moved slot's new location
                slot = firstEmpty;
                firstEmpty = -1;
            }
        }
    }

    public static boolean isAMS(WeaponType weapon) {
        return (weapon instanceof ISAMS) || (weapon instanceof CLAMS) || (weapon instanceof CLLaserAMS) || (weapon instanceof ISLaserAMS);
    }

    public static boolean hasSwitchableAmmo(WeaponType weapon) {

        if (weapon instanceof EnergyWeapon) {
            return false;
        }

        if (weapon instanceof GaussWeapon) {
            return false;
        }

        if (weapon instanceof UACWeapon) {
            return false;
        }

        if (weapon instanceof HVACWeapon) {
            return false;
        }

        if (weapon instanceof HAGWeapon) {
            return false;
        }

        if (weapon instanceof MGWeapon) {
            return false;
        }

        if (UnitUtil.isAMS(weapon)) {
            return false;
        }

        if (weapon instanceof ThunderBoltWeapon) {
            return false;
        }

        if (weapon instanceof MPodWeapon) {
            return false;
        }

        if (weapon instanceof BPodWeapon) {
            return false;
        }
        return true;
    }

    /**
     * Expands crits that are a single mount by have multiple spreadable crits
     * Such as TSM, Endo Steel, Reactive armor.
     *
     * @param unit
     */
    public static void expandUnitMounts(Mech unit) {
        boolean partialWingDone = false;
        boolean jumpBoosterDone = false;
        boolean blueShieldDone = false;
        boolean tracksDone = false;
        boolean talonsDone = false;
        for (int location = 0; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }
                Mounted mount;
                if (cs.getMount() == null) {
                    mount = unit.getEquipment(cs.getIndex());
                } else {
                    mount = cs.getMount();
                }

                if (UnitUtil.isSpreadEquipment(mount.getType()) || UnitUtil.isTSM(mount.getType()) || UnitUtil.isArmorOrStructure(mount.getType())) {
                    // to match how we setup mounts for spreadable stuff that
                    // actually weigh something, we just remove all mounts and
                    // crits and add them back as if the user added it himself
                    if (mount.getType().hasFlag(MiscType.F_PARTIAL_WING)) {
                        if (!partialWingDone) {
                            unit.getEquipment().remove(mount);
                            unit.getMisc().remove(mount);
                            for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
                                for (int sl = 0; sl < unit.getNumberOfCriticals(loc); sl++) {
                                    CriticalSlot cs2 = unit.getCritical(loc, sl);
                                    if ((cs2 == null) || (cs2.getType() == CriticalSlot.TYPE_SYSTEM)) {
                                        continue;
                                    }
                                    if (cs2.getMount().equals(mount)) {
                                        unit.setCritical(loc, sl, null);
                                    }
                                }
                            }
                            UnitUtil.createSpreadMounts(unit, UnitUtil.PARTIALWING);
                            partialWingDone = true;
                        }
                    } else if (mount.getType().hasFlag(MiscType.F_TALON)) {
                        if (!talonsDone) {
                            unit.getEquipment().remove(mount);
                            unit.getMisc().remove(mount);
                            for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
                                for (int sl = 0; sl < unit.getNumberOfCriticals(loc); sl++) {
                                    CriticalSlot cs2 = unit.getCritical(loc, sl);
                                    if ((cs2 == null) || (cs2.getType() == CriticalSlot.TYPE_SYSTEM)) {
                                        continue;
                                    }
                                    if (cs2.getMount().equals(mount)) {
                                        unit.setCritical(loc, sl, null);
                                    }
                                }
                            }
                            UnitUtil.createSpreadMounts(unit, UnitUtil.TALONS);
                            talonsDone = true;
                        }
                    }else if (mount.getType().hasFlag(MiscType.F_JUMP_BOOSTER)) {
                        if (!jumpBoosterDone) {
                            unit.getEquipment().remove(mount);
                            unit.getMisc().remove(mount);
                            for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
                                for (int sl = 0; sl < unit.getNumberOfCriticals(loc); sl++) {
                                    CriticalSlot cs2 = unit.getCritical(loc, sl);
                                    if ((cs2 == null) || (cs2.getType() == CriticalSlot.TYPE_SYSTEM)) {
                                        continue;
                                    }
                                    if (cs2.getMount().equals(mount)) {
                                        unit.setCritical(loc, sl, null);
                                    }
                                }
                            }
                            UnitUtil.createSpreadMounts(unit, UnitUtil.JUMPBOOSTER);
                            jumpBoosterDone = true;
                        }
                    } else if (mount.getType().hasFlag(MiscType.F_BLUE_SHIELD)) {
                        if (!blueShieldDone) {
                            unit.getEquipment().remove(mount);
                            unit.getMisc().remove(mount);
                            for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
                                for (int sl = 0; sl < unit.getNumberOfCriticals(loc); sl++) {
                                    CriticalSlot cs2 = unit.getCritical(loc, sl);
                                    if ((cs2 == null) || (cs2.getType() == CriticalSlot.TYPE_SYSTEM)) {
                                        continue;
                                    }
                                    if (cs2.getMount().equals(mount)) {
                                        unit.setCritical(loc, sl, null);
                                    }
                                }
                            }
                            UnitUtil.createSpreadMounts(unit, UnitUtil.BLUESHIELD);
                            blueShieldDone = true;
                        }
                    } else if (mount.getType().hasFlag(MiscType.F_TRACKS)) {
                        if (!tracksDone) {
                            unit.getEquipment().remove(mount);
                            unit.getMisc().remove(mount);
                            for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
                                for (int sl = 0; sl < unit.getNumberOfCriticals(loc); sl++) {
                                    CriticalSlot cs2 = unit.getCritical(loc, sl);
                                    if ((cs2 == null) || (cs2.getType() == CriticalSlot.TYPE_SYSTEM)) {
                                        continue;
                                    }
                                    if (cs2.getMount().equals(mount)) {
                                        unit.setCritical(loc, sl, null);
                                    }
                                }
                            }
                            UnitUtil.createSpreadMounts(unit, UnitUtil.TRACKS);
                            tracksDone = true;
                        }
                    } else {
                        Mounted newMount = new Mounted(unit, mount.getType());
                        newMount.setLocation(location, mount.isRearMounted());
                        newMount.setArmored(mount.isArmored());
                        cs.setMount(newMount);
                        cs.setArmored(mount.isArmored());
                        unit.getEquipment().add(newMount);
                        unit.getMisc().add(newMount);
                        cs.setIndex(unit.getEquipmentNum(newMount));
                    }
                } else {
                    cs.setMount(mount);
                }
            }
        }
        System.out.println();
    }

    public static boolean createSpreadMounts(Mech unit, String equip) {
        // how many non-spreadable contigous blocks of crits?
        int blocks = 0;
        boolean isVariableTonnage = false;

        blocks = EquipmentType.get(equip).getCriticals(unit);

        if (blocks < 1) {
            return true;
        }

        List<Integer> locations = new ArrayList<Integer>();

        float tonnageAmount = 0;

        if (equip.equals(UnitUtil.INDUSTRIALTSM) || equip.equals(UnitUtil.TSM)) {
            // all crits user placeable
            for (int i = 0; i < EquipmentType.get(equip).getCriticals(unit); i++) {
                locations.add(Entity.LOC_NONE);
            }
        }
        if (equip.equals(UnitUtil.ENVIROSEAL)) {
            // 1 crit in each location
            for (int i = 0; i < unit.locations(); i++) {
                locations.add(i);
            }
            tonnageAmount = EquipmentType.get(equip).getTonnage(unit);
            tonnageAmount /= 8;
            isVariableTonnage = true;
        }
        if ((equip.equals(UnitUtil.TRACKS) || equip.equals(UnitUtil.TALONS))) {
            // 1 block in each leg
            locations.add(Mech.LOC_LLEG);
            locations.add(Mech.LOC_RLEG);
            if (unit instanceof QuadMech) {
                locations.add(Mech.LOC_LARM);
                locations.add(Mech.LOC_RARM);
            }
            blocks = (unit instanceof BipedMech ? 2 : 4);
            tonnageAmount = EquipmentType.get(equip).getTonnage(unit) / EquipmentType.get(equip).getCriticals(unit);
            isVariableTonnage = true;
        }

        if (equip.equals(UnitUtil.PARTIALWING)) {
            // one block in each side torso
            locations.add(Mech.LOC_LT);
            locations.add(Mech.LOC_RT);
            blocks = 2;
            tonnageAmount = EquipmentType.get(equip).getTonnage(unit) / 2;
            isVariableTonnage = true;
        }
        if (equip.equals(UnitUtil.JUMPBOOSTER)) {
            // 1 block in each leg
            locations.add(Mech.LOC_LLEG);
            locations.add(Mech.LOC_RLEG);
            if (unit instanceof QuadMech) {
                locations.add(Mech.LOC_LARM);
                locations.add(Mech.LOC_RARM);
            }
            blocks = (unit instanceof BipedMech ? 2 : 4);
            tonnageAmount = EquipmentType.get(equip).getTonnage(unit) / blocks;
            isVariableTonnage = true;
        }
        if (equip.equals(UnitUtil.VOIDSIG) || equip.equals(UnitUtil.NULLSIG) || equip.equals(UnitUtil.BLUESHIELD)) {
            // 1 crit in each location, except the head
            for (int i = 0; i < unit.locations(); i++) {
                if (i != Mech.LOC_HEAD) {
                    locations.add(i);
                }
            }
            if (equip.equals(UnitUtil.BLUESHIELD)) {
                tonnageAmount = EquipmentType.get(equip).getTonnage(unit) / 7;
                isVariableTonnage = true;
            }
        }

        if (equip.equals(UnitUtil.CHAMELEON)) {
            // 1 crit in each location except head and CT
            for (int i = 0; i < unit.locations(); i++) {
                if ((i != Mech.LOC_HEAD) && (i != Mech.LOC_CT)) {
                    locations.add(i);
                }
            }
        }

        for (; blocks > 0; blocks--) {
            Mounted mount = new Mounted(unit, EquipmentType.get(equip));
            // how many crits per block?
            int crits = UnitUtil.getCritsUsed(unit, EquipmentType.get(equip));
            if (isVariableTonnage && (blocks > 1)) {
                mount.getType().setTonnage(tonnageAmount);
            }
            for (int i = 0; i < crits; i++) {
                try {
                    unit.addEquipment(mount, locations.get(0), false);
                } catch (LocationFullException lfe) {
                    JOptionPane.showMessageDialog(null, lfe.getMessage(), mount.getName() + " does not fit into " + unit.getLocationName(locations.get(0)), JOptionPane.INFORMATION_MESSAGE);
                    unit.getMisc().remove(mount);
                    unit.getEquipment().remove(mount);
                    return false;
                }
            }
            locations.remove(0);
        }
        return true;
    }

    public static void loadFonts() {

        if ((euroFont != null) && (euroBoldFont != null)) {
            return;
        }

        String fName = "./data/fonts/Eurosti.TTF";
        try {
            File fontFile = new File(fName);
            InputStream is = new FileInputStream(fontFile);
            euroFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(fName + " not loaded.  Using Arial font.");
            euroFont = new Font("Arial", Font.PLAIN, 8);
        }

        fName = "./data/fonts/Eurostib.TTF";
        try {
            File fontFile = new File(fName);
            InputStream is = new FileInputStream(fontFile);
            euroBoldFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(fName + " not loaded.  Using Arial font.");
            euroBoldFont = new Font("Arial", Font.PLAIN, 8);
        }

    }

    public static Font deriveFont(float pointSize) {
        return UnitUtil.deriveFont(false, pointSize);
    }

    public static Font deriveFont(boolean boldFont, float pointSize) {

        UnitUtil.loadFonts();

        if (boldFont) {
            return euroBoldFont.deriveFont(pointSize);
        }

        return euroFont.deriveFont(pointSize);
    }

    public static Font getNewFont(Graphics2D g2d, String info, boolean bold, int stringWidth, float pointSize) {
        Font font = UnitUtil.deriveFont(bold, pointSize);

        while ((ImageHelper.getStringWidth(g2d, info, font) > stringWidth) && (pointSize > 0)) {
            pointSize -= .1;
            font = UnitUtil.deriveFont(bold, pointSize);
        }
        return font;
    }

    public static void removeOneShotAmmo(Entity unit) {
        ArrayList<Mounted> ammoList = new ArrayList<Mounted>();

        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                ammoList.add(mount);
            }
        }

        for (Mounted mount : ammoList) {
            int index = unit.getEquipment().indexOf(mount);
            unit.getEquipment().remove(mount);
            unit.getAmmo().remove(mount);

            for (int location = 0; location <= Mech.LOC_LLEG; location++) {
                for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
                    if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                        continue;
                    }

                    if (cs.getIndex() >= index) {
                        cs.setIndex(cs.getIndex() - 1);
                    }
                }
            }
        }
    }

    public static void removeClanCase(Entity unit) {
        ArrayList<Mounted> caseList = new ArrayList<Mounted>();

        for (Mounted mount : unit.getMisc()) {
            if (mount.getType().getInternalName().equals("CLCASE")) {
                caseList.add(mount);
            }
        }

        for (Mounted mount : caseList) {
            int index = unit.getEquipment().indexOf(mount);
            unit.getEquipment().remove(mount);
            unit.getMisc().remove(mount);

            for (int location = 0; location <= Mech.LOC_LLEG; location++) {
                for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
                    if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                        continue;
                    }

                    if (cs.getIndex() >= index) {
                        cs.setIndex(cs.getIndex() - 1);
                    }
                }
            }
        }
    }

    public static boolean hasAmmo(Entity unit, int location) {

        for (Mounted mount : unit.getEquipment()) {

            if (mount.getType().isExplosive() && ((mount.getLocation() == location) || (mount.getSecondLocation() == location))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks to see if something is a Jump Jet
     *
     * @param eq
     * @return
     */
    public static boolean isJumpJet(EquipmentType eq) {
        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_JUMP_BOOSTER) || eq.hasFlag(MiscType.F_JUMP_JET) || eq.hasFlag(MiscType.F_UMU))) {
            return true;
        }

        return false;
    }

    public static boolean isClanEquipment(EquipmentType eq) {
        switch (eq.getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_IS_TW_ALL:
            case TechConstants.T_IS_ADVANCED:
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_IS_UNOFFICIAL:
                return false;
            default:
                return true;
        }
    }

    public static int getBAAmmoCount(Entity ba, WeaponType weapon, int location) {
        int ammoCount = 0;

        for (Mounted mount : ba.getAmmo()) {
            if (mount.getLocation() != location) {
                continue;
            }
            AmmoType ammo = (AmmoType) mount.getType();

            if ((ammo.getRackSize() == weapon.getRackSize()) && (ammo.getAmmoType() == weapon.getAmmoType())) {
                ammoCount++;
            }
        }

        return ammoCount;
    }

    public static String getCritName(Entity unit, EquipmentType eq) {
        if (unit.isMixedTech() && (eq.getTechLevel() != TechConstants.T_ALLOWED_ALL)) {

            if (unit.isClan() && !UnitUtil.isClanEquipment(eq)) {
                return eq.getName() + " (IS)";
            }

            if (!unit.isClan() && UnitUtil.isClanEquipment(eq)) {
                return eq.getName() + " (Clan)";
            }
        }

        return eq.getName();
    }

    public static String getToolTipInfo(Entity unit, Mounted eq) {
        DecimalFormat myFormatter = new DecimalFormat("#,##0");
        StringBuilder sb = new StringBuilder("<HTML>");
        sb.append(eq.getName());
        sb.append("<br>Crits: ");
        sb.append(eq.getType().getCriticals(unit));
        sb.append("<br>Tonnage: ");
        sb.append(eq.getType().getTonnage(unit));
        if (eq.getType() instanceof WeaponType) {
            sb.append("<br>Heat: ");
            sb.append(((WeaponType) eq.getType()).getHeat());
        }
        sb.append("<Br>Cost: ");

        double cost = eq.getType().getCost(unit, false);
        if (cost == EquipmentType.COST_VARIABLE) {
            cost = eq.getType().resolveVariableCost(unit, false);
        }
        sb.append(myFormatter.format(cost));
        sb.append(" CBills");

        if (eq.isRearMounted()) {
            sb.append("<br>Rear Facing");

        }
        if (eq.isArmored()) {
            sb.append("<br>Armored");
        }
        sb.append("</html>");
        return sb.toString();
    }

    public static int getBaseChassisHeatSinks(Mech unit) {
        int engineHSCapacity = unit.getEngine().integralHeatSinkCapacity();

        if (unit.isOmni()) {
            engineHSCapacity = Math.min(engineHSCapacity, unit.getEngine().getBaseChassisHeatSinks());
        }

        return engineHSCapacity;
    }

    public static boolean isLastCrit(Entity unit, CriticalSlot cs, int slot, int location) {
        if (unit instanceof Mech) {
            return UnitUtil.isLastMechCrit((Mech) unit, cs, slot, location);
        }
        return true;
    }

    public static boolean isLastMechCrit(Mech unit, CriticalSlot cs, int slot, int location) {

        if (cs == null) {
            return true;
        }

        int lastIndex = 0;
        if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {

            for (int position = 0; position < unit.getNumberOfCriticals(location); position++) {
                if ((cs.getIndex() == Mech.SYSTEM_ENGINE) && (slot >= 3) && (position < 3)) {
                    position = 3;
                }
                CriticalSlot crit = unit.getCritical(location, position);

                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_SYSTEM) && (crit.getIndex() == cs.getIndex())) {
                    lastIndex = position;
                } else if (position > slot) {
                    break;
                }
            }

        } else {

            Mounted originalMount = cs.getMount();
            Mounted testMount = null;

            if (originalMount == null) {
                originalMount = unit.getEquipment(cs.getIndex());
            }

            if (originalMount == null) {
                return true;
            }

            int numberOfCrits = slot - (originalMount.getType().getCriticals(unit) - 1);

            if (numberOfCrits < 0) {
                return false;
            }
            for (int position = slot; position >= numberOfCrits; position--) {
                CriticalSlot crit = unit.getCritical(location, position);

                if ((crit == null) || (crit.getType() != CriticalSlot.TYPE_EQUIPMENT)) {
                    return false;
                }

                if ((testMount = crit.getMount()) == null) {
                    testMount = unit.getEquipment(crit.getIndex());
                }

                if (testMount == null) {
                    return false;
                }

                if (!testMount.equals(originalMount)) {
                    return false;
                }

            }
            return true;

        }

        return slot == lastIndex;
    }

    public static void updateCritsArmoredStatus(Entity unit, Mounted mount) {
        for (int position = 0; position < unit.getNumberOfCriticals(mount.getLocation()); position++) {
            CriticalSlot cs = unit.getCritical(mount.getLocation(), position);
            if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                continue;
            }

            if ((cs.getMount() != null) && cs.getMount().equals(mount)) {
                cs.setArmored(mount.isArmored());
            } else if ((unit.getEquipment(cs.getIndex()) != null) && unit.getEquipment(cs.getIndex()).equals(mount)) {
                cs.setArmored(mount.isArmored());
            }

        }

        if ((mount.isSplitable() || mount.getType().isSpreadable()) && (mount.getSecondLocation() != Entity.LOC_NONE)) {
            for (int position = 0; position < unit.getNumberOfCriticals(mount.getSecondLocation()); position++) {
                CriticalSlot cs = unit.getCritical(mount.getLocation(), position);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if ((cs.getMount() != null) && cs.getMount().equals(mount)) {
                    cs.setArmored(mount.isArmored());
                } else if ((unit.getEquipment(cs.getIndex()) != null) && unit.getEquipment(cs.getIndex()).equals(mount)) {
                    cs.setArmored(mount.isArmored());
                }

            }
        }
    }

    public static void updateCritsArmoredStatus(Entity unit, CriticalSlot cs, int location) {

        if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            return;
        }

        if (cs.getIndex() <= Mech.SYSTEM_GYRO) {
            for (int loc = Mech.LOC_HEAD; loc <= Mech.LOC_LT; loc++) {
                for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                    CriticalSlot newCrit = unit.getCritical(loc, slot);

                    if ((newCrit != null) && (newCrit.getType() == CriticalSlot.TYPE_SYSTEM) && (newCrit.getIndex() == cs.getIndex())) {
                        newCrit.setArmored(cs.isArmored());
                    }
                }
            }
        } else {
            // actuators
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot newCrit = unit.getCritical(location, slot);

                if ((newCrit != null) && (newCrit.getType() == CriticalSlot.TYPE_SYSTEM) && (newCrit.getIndex() == cs.getIndex())) {
                    newCrit.setArmored(cs.isArmored());
                }
            }
        }
    }

    public static boolean isArmorOrStructure(EquipmentType eq) {

        return UnitUtil.isArmor(eq) || UnitUtil.isStructure(eq);
    }

    public static boolean isArmorable(CriticalSlot cs) {
        if (cs == null) {
            return false;
        }

        if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
            return true;
        }

        Mounted mount = cs.getMount();
        if (mount == null) {
            return false;
        }

        if (UnitUtil.isArmorOrStructure(mount.getType())) {
            return false;
        }

        if (UnitUtil.isTSM(mount.getType())) {
            return false;
        }

        if (mount.getType() instanceof MiscType) {
            MiscType misc = (MiscType) mount.getType();
            if (misc.hasFlag(MiscType.F_HARJEL) || misc.hasFlag(MiscType.F_SPIKES) || misc.hasFlag(MiscType.F_REACTIVE) || misc.hasFlag(MiscType.F_MODULAR_ARMOR) || misc.isShield()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Removes any and all types of TC Crits
     *
     * @param unit
     */
    public static void removeTCCrits(Mech unit) {
        UnitUtil.removeCrits(unit, UnitUtil.TARGETINGCOMPUTER);
        UnitUtil.removeCrits(unit, UnitUtil.ISTARGETINGCOMPUTER);
        UnitUtil.removeCrits(unit, UnitUtil.CLTARGETINGCOMPUTER);
    }

    /**
     * Removes any and all types of TC Mounts
     *
     * @param unit
     */
    public static void removeTCMounts(Mech unit) {
        UnitUtil.removeMounts(unit, UnitUtil.TARGETINGCOMPUTER);
        UnitUtil.removeMounts(unit, UnitUtil.ISTARGETINGCOMPUTER);
        UnitUtil.removeMounts(unit, UnitUtil.CLTARGETINGCOMPUTER);
    }

    /**
     * Returns the units tech type.
     *
     * @param unit
     * @return
     */
    public static int getUnitTechType(Entity unit) {
        switch (unit.getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                return UnitUtil.TECH_INTRO;
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_IS_TW_ALL:
            case TechConstants.T_CLAN_TW:
                return UnitUtil.TECH_STANDARD;
            case TechConstants.T_IS_ADVANCED:
            case TechConstants.T_CLAN_ADVANCED:
                return UnitUtil.TECH_ADVANCED;
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_EXPERIMENTAL:
                return UnitUtil.TECH_EXPERIMENTAL;
            case TechConstants.T_IS_UNOFFICIAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
                return UnitUtil.TECH_UNOFFICAL;
        }
        return UnitUtil.TECH_INTRO;
    }

    public static void updateLoadedMech(Mech unit) {
        UnitUtil.removeOneShotAmmo(unit);
        UnitUtil.removeClanCase(unit);
        UnitUtil.expandUnitMounts(unit);
        UnitUtil.checkArmor(unit);
    }

    public static boolean isMechWeapon(EquipmentType eq, Mech unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }

        if (eq instanceof WeaponType) {

            WeaponType weapon = (WeaponType) eq;

            if (!weapon.hasFlag(WeaponType.F_MECH_WEAPON)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon)) && (weapon.getRackSize() != 5) && (weapon.getRackSize() != 10) && (weapon.getRackSize() != 15) && (weapon.getRackSize() != 20)) {
                return false;
            }
            if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon)) && (weapon.getRackSize() != 2) && (weapon.getRackSize() != 4) && (weapon.getRackSize() != 6)) {
                return false;
            }
            if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY) || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.T_PLASMA))) {

                if (weapon.hasFlag(WeaponType.F_ENERGY) && weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.T_NA)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isMechEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (eq.equals(EquipmentType.get("CLTAG")) || eq.equals(EquipmentType.get("ISC3MasterUnit")) || eq.equals(EquipmentType.get("ISTAG")) || eq.equals(EquipmentType.get("IS Coolant Pod")) || eq.equals(EquipmentType.get("Clan Coolant Pod")) || eq.equals(EquipmentType.get("CLLightTAG"))) {
            return true;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_MECH_EQUIPMENT) && !eq.hasFlag(MiscType.F_CLUB) && !eq.hasFlag(MiscType.F_HAND_WEAPON)) {
            return true;

        }

        return false;
    }

    public static boolean isTankWeapon(EquipmentType eq, Tank unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }

        if (eq instanceof WeaponType) {

            WeaponType weapon = (WeaponType) eq;

            if (!weapon.hasFlag(WeaponType.F_TANK_WEAPON)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon)) && (weapon.getRackSize() != 5) && (weapon.getRackSize() != 10) && (weapon.getRackSize() != 15) && (weapon.getRackSize() != 20)) {
                return false;
            }
            if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon)) && (weapon.getRackSize() != 2) && (weapon.getRackSize() != 4) && (weapon.getRackSize() != 6)) {
                return false;
            }
            if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY) || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.T_PLASMA))) {

                if (weapon.hasFlag(WeaponType.F_ENERGY) && weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.T_NA)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isTankEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (eq.equals(EquipmentType.get("CLTAG")) || eq.equals(EquipmentType.get("ISC3MasterUnit")) || eq.equals(EquipmentType.get("ISTAG")) || eq.equals(EquipmentType.get("IS Coolant Pod")) || eq.equals(EquipmentType.get("Clan Coolant Pod")) || eq.equals(EquipmentType.get("CLLightTAG"))) {
            return true;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_TANK_EQUIPMENT)) {
            return true;

        }

        return false;
    }

    public static boolean canSwarm(BattleArmor ba) {

        for (Mounted eq : ba.getEquipment()) {
            if ((eq.getType() instanceof SwarmAttack) || (eq.getType() instanceof StopSwarmAttack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canLegAttack(BattleArmor ba) {

        for (Mounted eq : ba.getEquipment()) {
            if (eq.getType() instanceof LegAttack) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasInfantryWeapons(BattleArmor ba) {

        for (Mounted eq : ba.getEquipment()) {
            if (eq.getType() instanceof InfantryWeapon) {
                return true;
            }
        }
        return false;
    }

    public static int getShieldDamageAbsorbtion(Mech mech, int location) {
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            CriticalSlot cs = mech.getCritical(location, slot);

            if (cs == null) {
                continue;
            }

            if (cs.getType() != CriticalSlot.TYPE_EQUIPMENT) {
                continue;
            }

            Mounted m = cs.getMount();

            if (m == null) {
                System.err.println("Null Mount index: " + cs.getIndex());
                m = mech.getEquipment(cs.getIndex());
            }

            EquipmentType type = m.getType();
            if ((type instanceof MiscType) && ((MiscType) type).isShield()) {
                return m.getBaseDamageAbsorptionRate();
            }
        }

        return 0;
    }

    public static int getShieldDamageCapacity(Mech mech, int location) {
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            CriticalSlot cs = mech.getCritical(location, slot);

            if (cs == null) {
                continue;
            }

            if (cs.getType() != CriticalSlot.TYPE_EQUIPMENT) {
                continue;
            }

            Mounted m = cs.getMount();

            if (m == null) {
                System.err.println("Null Mount index: " + cs.getIndex());
                m = mech.getEquipment(cs.getIndex());
            }

            EquipmentType type = m.getType();
            if ((type instanceof MiscType) && ((MiscType) type).isShield()) {
                return m.getBaseDamageCapacity();
            }
        }

        return 0;
    }

    public static Mounted getMounted(Entity unit, String mountName) {

        if (unit instanceof Tank) {
            return UnitUtil.getMounted((Tank) unit, mountName);
        }

        if (unit instanceof Mech) {
            return UnitUtil.getMounted((Mech) unit, mountName);
        }

        return null;
    }

    public static Mounted getMounted(Tank unit, String mountName) {

        for (Mounted mount : unit.getEquipment()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && mount.getType().getInternalName().equals(mountName)) {
                return mount;
            }
        }

        return null;
    }

    public static Mounted getMounted(Mech unit, String mountName) {

        Mech mech = unit;
        int externalEngineHS = UnitUtil.getBaseChassisHeatSinks(mech);

        Mounted eq = null;
        for (Mounted mount : mech.getEquipment()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && mount.getType().getInternalName().equals(mountName)) {
                if (UnitUtil.isHeatSink(mount) && (externalEngineHS-- > 0)) {
                    continue;
                }
                eq = mount;
                break;
            }
        }

        return eq;
    }

    /**
     * remove all CriticalSlots on the passed unit that are internal structur or
     * armor
     *
     * @param unit
     *            the Entity
     * @param internalStructure
     *            true to remove IS, false to remove armor
     */
    public static void removeISorArmorCrits(Entity unit, boolean internalStructure) {
        for (int location = Mech.LOC_HEAD; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if ((mount != null) && (mount.getType() instanceof MiscType) && Arrays.asList(internalStructure ? EquipmentType.structureNames : EquipmentType.armorNames).contains(mount.getType().getInternalName())) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

    /**
     * remove all Mounteds on the passed unit that are internal structur or
     * armor
     *
     * @param unit
     *            the Entity
     * @param internalStructure
     *            true to remove IS, false to remove armor
     */
    public static void removeISorArmorMounts(Entity unit, boolean internalStructure) {
        removeISorArmorCrits(unit, internalStructure);
        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && Arrays.asList(internalStructure ? EquipmentType.structureNames : EquipmentType.armorNames).contains(mount.getType().getInternalName())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }
        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if ((mount.getType() instanceof MiscType) && Arrays.asList(internalStructure ? EquipmentType.structureNames : EquipmentType.armorNames).contains(mount.getType().getInternalName())) {
                unit.getMisc().remove(pos);
            } else {
                pos++;
            }
        }
        if (internalStructure) {
            unit.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        } else {
            unit.setArmorType(EquipmentType.T_ARMOR_STANDARD);
            unit.setArmorTechLevel(unit.getTechLevel());
        }
    }

    public static void checkArmor(Entity unit) {

        if (!(unit instanceof Mech)) {
            return;
        }

        boolean foundError = false;

        Mech mech = (Mech) unit;

        // Check all the mechs locations to see if any armor is greater then can
        // be in there.
        for (int location = 0; location < mech.locations(); location++) {
            // Head armor has a max of 9
            if (location == Mech.LOC_HEAD) {
                int armor = mech.getArmor(location);

                if (armor > 9) {
                    foundError = true;
                    mech.setArmor(9, location);
                }
            } else {
                int armor = mech.getArmor(location);
                if (mech.hasRearArmor(location)) {
                    armor += mech.getArmor(location, true);
                }
                int totalArmor = mech.getInternal(location) * 2;
                // Armor on the location is greater then what can be there.
                if (armor > totalArmor) {
                    foundError = true;
                    int armorOverage = armor - totalArmor;

                    // check for locations with rear armor first and remove the
                    // extra armor from the rear first.
                    if (mech.hasRearArmor(location)) {
                        int rearArmor = mech.getArmor(location, true);
                        if (rearArmor >= armorOverage) {
                            mech.initializeRearArmor(rearArmor - armorOverage, location);
                            armorOverage = 0;
                        } else {
                            armorOverage -= rearArmor;
                            mech.initializeRearArmor(0, location);
                        }
                    }

                    // Any armor overage left remove it from the front. Min 0
                    // armor in the location.
                    armor = mech.getArmor(location);
                    armor = Math.max(0, armor - armorOverage);
                    mech.initializeArmor(armor, location);
                }
            }
        }

        if (foundError) {
            JOptionPane.showMessageDialog(null, "Armor Overage found on this unit.\n\rMegaMekLab has automatically correct the problem.\n\rIt is suggested you view the armor tab to see the correction.", "Armor Overage", JOptionPane.WARNING_MESSAGE);
        }
    }
}
