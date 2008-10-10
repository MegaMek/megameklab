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

package megameklab.com.ui.util;

import java.util.concurrent.ConcurrentLinkedQueue;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;

public class UnitUtil {

    public static String TARGETINGCOMPUTER = "Targeting Computer";
    public static String ISTARGETINGCOMPUTER = "ISTargeting Computer";
    public static String CLTARGETINGCOMPUTER = "CLTargeting Computer";
    public static String TSM = "TSM";

    /**
     * tells is EquipementType is armor or Structure that uses crits/mounted
     * 
     * @param eq
     * @return
     */
    public static boolean isArmorOrStructure(EquipmentType eq) {
        return eq instanceof MiscType && (eq.hasFlag(MiscType.F_ENDO_STEEL) || eq.hasFlag(MiscType.F_FERRO_FIBROUS));
    }

    /**
     * tells if EquipmentType is TSM or TargetComp
     * 
     * @param eq
     * @return
     */
    public static boolean isTSMorTC(EquipmentType eq) {
        return eq instanceof MiscType && (eq.hasFlag(MiscType.F_TSM) || eq.hasFlag(MiscType.F_TARGCOMP));
    }

    /**
     * Returns the number of crits used by EquipmentType eq, 1 if armor or structure EquipmentType
     * 
     * @param unit
     * @param eq
     * @return
     */
    public static int getCritsUsed(Mech unit, EquipmentType eq) {
        if (UnitUtil.isArmorOrStructure(eq) || UnitUtil.isTSMorTC(eq)) {
            return 1;
        }

        return eq.getCriticals(unit);
    }

    /**
     * Removes a Mounted object from the units various equipment lists
     * 
     * @param unit
     * @param eq
     */
    public static void removeMounted(Mech unit, EquipmentType eq) {
        Mounted equipment = null;
        for (Mounted mount : unit.getEquipment()) {
            if (mount.getType().getInternalName().equals(eq.getInternalName())) {
                equipment = mount;
                break;
            }
        }

        if (equipment == null) {
            return;
        }

        UnitUtil.removeCriticals(unit, equipment);

        if (equipment.getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeTSMMounts(unit);
        } else if (equipment.getName().equals(UnitUtil.TARGETINGCOMPUTER)) {
            UnitUtil.removeTCMounts(unit);
        } else {
            unit.getEquipment().remove(equipment);

            if (equipment.getType() instanceof MiscType) {
                unit.getMisc().remove(equipment);
            } else if (equipment.getType() instanceof AmmoType) {
                unit.getAmmo().remove(equipment);
            } else {
                unit.getWeaponList().remove(equipment);
            }
        }

    }

    /**
     * Removes TSM Mounts from the Mek.
     * 
     * @param Unit
     */
    public static void removeTSMMounts(Mech unit) {

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (mount.getType().getName().equals(UnitUtil.TSM)) {
                unit.getEquipment().remove(pos);
                unit.getMisc().remove(mount);
            } else {
                pos++;
            }
        }
    }

    /**
     * Removes Targetting Computer Mounts from the Mech
     * 
     * @param unit
     */
    public static void removeTCMounts(Mech unit) {

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (mount.getType().getName().equals(UnitUtil.TARGETINGCOMPUTER)) {
                unit.getEquipment().remove(pos);
                unit.getMisc().remove(mount);
            } else {
                pos++;
            }
        }
    }

    /**
     * Removes all TSM crits from
     * 
     * @param unit
     * @param unit
     */
    public static void removeTSMCrits(Mech unit) {

        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if (mount != null && mount.getType().getName().equals(UnitUtil.TSM)) {
                        crit = null;
                        unit.setCritical(location, slot, crit);
                    }
                }
            }
        }
    }

    /**
     * Removes all Targeting computer crits form the Mech
     * 
     * @param unit
     */
    public static void removeTCCrits(Mech unit) {

        for (int location = Mech.LOC_HEAD; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if (mount != null && (mount.getType().getName().equals(UnitUtil.TARGETINGCOMPUTER))) {
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

        if (eq.getType().getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeTSMCrits(unit);
        } else if (eq.getType().getName().equals(UnitUtil.TARGETINGCOMPUTER)) {
            UnitUtil.removeTCCrits(unit);
        } else if (eq.isSplitable() || eq.getType().isSpreadable()) {
            UnitUtil.removeSplitCriticals(unit, eq);
        } else {
            int location = eq.getLocation();
            if (location != Mech.LOC_NONE) {
                int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
                for (int slot = 0; slot < unit.getNumberOfCriticals(location) && critsUsed > 0; slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
                    if (cs != null && cs.getType() == CriticalSlot.TYPE_EQUIPMENT && cs.getIndex() == unit.getEquipmentNum(eq)) {
                        cs = null;
                        unit.setCritical(location, slot, cs);
                        --critsUsed;
                    }
                }
            }
        }
    }

    /**
     * Removes crits for weapons that have split locations
     * 
     * @param unit
     * @param eq
     */
    public static void removeSplitCriticals(Mech unit, Mounted eq) {

        int location = eq.getLocation();
        if (location != Mech.LOC_NONE) {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            for (int slot = 0; slot < unit.getNumberOfCriticals(location) && critsUsed > 0; slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if (cs != null && cs.getType() == CriticalSlot.TYPE_EQUIPMENT && cs.getIndex() == unit.getEquipmentNum(eq)) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                    --critsUsed;
                }
            }
        }

        location = eq.getSecondLocation();
        if (location != Mech.LOC_NONE) {
            int critsUsed = UnitUtil.getCritsUsed(unit, eq.getType());
            for (int slot = 0; slot < unit.getNumberOfCriticals(location) && critsUsed > 0; slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if (cs != null && cs.getType() == CriticalSlot.TYPE_EQUIPMENT && cs.getIndex() == unit.getEquipmentNum(eq)) {
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

                if (cs != null && cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                    cs = null;
                    unit.setCritical(location, slot, cs);
                }
            }
        }

        for (Mounted mount : unit.getEquipment()) {
            mount.setLocation(Mech.LOC_NONE, false);
        }

    }

    /**
     * Updates TC Crits and Mounts based on weapons on a unit or if the TC has been removed.
     * 
     * @param unit
     */
    public static void updateTC(Mech unit) {
        UnitUtil.removeTCCrits(unit);
        UnitUtil.removeTCMounts(unit);
        createTCMounts(unit);
    }

    /**
     * Creates TC Mounts and Criticals for a Unit.
     * 
     * @param unit
     */
    public static void createTCMounts(Mech unit) {
        int TCCount = 0;
        String TargetingComputerType = "";

        if (unit.isClan()) {
            TargetingComputerType = UnitUtil.CLTARGETINGCOMPUTER;
        } else {
            TargetingComputerType = UnitUtil.ISTARGETINGCOMPUTER;
        }

        TCCount = EquipmentType.get(TargetingComputerType).getCriticals(unit);

        if (TCCount < 1) {
            return;
        }

        for (; TCCount > 0; TCCount--) {
            try {
                unit.addEquipment(new Mounted(unit, EquipmentType.get(TargetingComputerType)), Entity.LOC_NONE, false);
            } catch (Exception ex) {

            }
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
                if (techLevel == TechConstants.T_INTRO_BOXSET || techLevel == TechConstants.T_IS_TW_NON_BOX || techLevel == TechConstants.T_IS_ADVANCED || techLevel == TechConstants.T_IS_EXPERIMENTAL || techLevel == TechConstants.T_IS_UNOFFICIAL) {
                    return false;
                }
            } else {
                if (techLevel == TechConstants.T_CLAN_TW || techLevel == TechConstants.T_CLAN_ADVANCED || techLevel == TechConstants.T_CLAN_EXPERIMENTAL || techLevel == TechConstants.T_CLAN_UNOFFICIAL) {
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

        if (!unit.hasDoubleHeatSinks() || unit.hasLaserHeatSinks())
            return false;

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) || mounted.getType().hasFlag(MiscType.F_HEAT_SINK)) {

                if (mounted.getType().getInternalName().indexOf("Compact") > -1)
                    return true;

                return false;
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
        if (eq.getType() instanceof MiscType && (eq.getType().hasFlag(MiscType.F_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_LASER_HEAT_SINK) || eq.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK))) {
            return true;
        }

        return false;
    }

    /**
     * Checks if EquipmentType is a heat sink
     * @param eq
     * @return
     */
    public static boolean isHeatSink(EquipmentType eq) {
        if (eq instanceof MiscType && (eq.hasFlag(MiscType.F_HEAT_SINK) || eq.hasFlag(MiscType.F_LASER_HEAT_SINK) || eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK))) {
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
        int heatSinks = hsAmount - unit.getEngine().integralHeatSinkCapacity();
        EquipmentType sinkType;

        if (hsType == 1 || hsType == 3) {
            sinkType = EquipmentType.get(unit.isClan() ? "CLDoubleHeatSink" : "ISDoubleHeatSink");
        } else {
            sinkType = EquipmentType.get("Heat Sink");
        }

        for (; heatSinks > 0; heatSinks--) {

            try {
                unit.addEquipment(new Mounted(unit, sinkType), Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
        if (hsType == 1 || hsType == 3)
            unit.addEngineSinks(hsAmount, true);
        else
            unit.addEngineSinks(hsAmount, false);
        UnitUtil.addHeatSinkMounts(unit, hsAmount, hsType);
    }

    
    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     * @param eq
     * @return
     */
    public static boolean isPrintableEquipment(EquipmentType eq){
        if ( eq instanceof MiscType && (eq.hasFlag(MiscType.F_ENDO_STEEL) || eq.hasFlag(MiscType.F_FERRO_FIBROUS) || eq.hasFlag(MiscType.F_MASC) || eq.hasFlag(MiscType.F_TSM)) ){
            return false;
        }
        
        if ( UnitUtil.isHeatSink(eq) ) {
            return false;
        }
        
        return true;

    }
}