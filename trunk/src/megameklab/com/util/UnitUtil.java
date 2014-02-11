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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLEditorKit;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.LandAirMech;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MechView;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMech;
import megamek.common.verifier.TestTank;
import megamek.common.weapons.ACWeapon;
import megamek.common.weapons.AmmoWeapon;
import megamek.common.weapons.BPodWeapon;
import megamek.common.weapons.CLChemicalLaserWeapon;
import megamek.common.weapons.CLLightTAG;
import megamek.common.weapons.CLPlasmaCannon;
import megamek.common.weapons.CLTAG;
import megamek.common.weapons.EnergyWeapon;
import megamek.common.weapons.GaussWeapon;
import megamek.common.weapons.HAGWeapon;
import megamek.common.weapons.HVACWeapon;
import megamek.common.weapons.ISC3M;
import megamek.common.weapons.ISC3MBS;
import megamek.common.weapons.ISPlasmaRifle;
import megamek.common.weapons.ISTAG;
import megamek.common.weapons.LBXACWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.LRTWeapon;
import megamek.common.weapons.LegAttack;
import megamek.common.weapons.MGWeapon;
import megamek.common.weapons.MPodWeapon;
import megamek.common.weapons.MRMWeapon;
import megamek.common.weapons.PPCWeapon;
import megamek.common.weapons.RLWeapon;
import megamek.common.weapons.SRMWeapon;
import megamek.common.weapons.SRTWeapon;
import megamek.common.weapons.StopSwarmAttack;
import megamek.common.weapons.StreakLRMWeapon;
import megamek.common.weapons.StreakSRMWeapon;
import megamek.common.weapons.SwarmAttack;
import megamek.common.weapons.SwarmWeaponAttack;
import megamek.common.weapons.ThunderBoltWeapon;
import megamek.common.weapons.UACWeapon;
import megamek.common.weapons.VehicleFlamerWeapon;
import megamek.common.weapons.battlearmor.CLBALBX;
import megamek.common.weapons.battlearmor.CLBALightTAG;
import megamek.common.weapons.battlearmor.ISBALightTAG;
import megamek.common.weapons.infantry.InfantryRifleAutoRifleWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;

public class UnitUtil {

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
    public static boolean isFixedLocationSpreadEquipment(EquipmentType eq) {
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                        || (eq.hasFlag(MiscType.F_MASC) 
                                && eq.hasFlag(MiscType.F_BA_EQUIPMENT))
                        || eq.hasFlag(MiscType.F_PARTIAL_WING)
                        || eq.hasFlag(MiscType.F_NULLSIG)
                        || eq.hasFlag(MiscType.F_VOIDSIG)
                        || eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)
                        || eq.hasFlag(MiscType.F_TRACKS)
                        || eq.hasFlag(MiscType.F_TALON)
                        || (eq.hasFlag(MiscType.F_STEALTH) && eq
                                .hasFlag(MiscType.F_MECH_EQUIPMENT))
                        || eq.hasFlag(MiscType.F_CHAMELEON_SHIELD) || eq
                            .hasFlag(MiscType.F_BLUE_SHIELD));
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
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_TSM) || eq
                        .hasFlag((MiscType.F_INDUSTRIAL_TSM)));
    }

    /**
     * tells if EquipmentType is MASC
     *
     * @param eq
     * @return
     */
    public static boolean isMASC(EquipmentType eq) {
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_MASC) && !eq
                        .hasSubType(MiscType.S_SUPERCHARGER));
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

        boolean isMisc = eq instanceof MiscType;
        double toReturn = eq.getCriticals(unit);

        if (isMisc
                && eq.hasFlag(MiscType.F_PARTIAL_WING)
                && TechConstants
                        .isClan(eq.getTechLevel(unit.getTechLevelYear()))) {
            toReturn = 3;
        } else if (isMisc
                && eq.hasFlag(MiscType.F_PARTIAL_WING)
                && !TechConstants.isClan(eq.getTechLevel(unit
                        .getTechLevelYear()))) {
            toReturn = 4;
        } else  if (isMisc
                && (eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                        || eq.hasFlag(MiscType.F_TALON) || eq
                            .hasFlag(MiscType.F_STEALTH))) {
            toReturn = 2;
        } else  if (UnitUtil.isFixedLocationSpreadEquipment(eq) || UnitUtil.isTSM(eq)
                || UnitUtil.isArmorOrStructure(eq)) {
            toReturn = 1;
        }
        if ((unit instanceof Mech) && ((Mech) unit).isSuperHeavy()) {
            toReturn = Math.ceil(toReturn/2.0);
        }
        return (int)toReturn;
    }

    public static void removeMounted(Entity unit, Mounted mount) {
        UnitUtil.removeCriticals(unit, mount);
        
        // Some special checks for BA
        if (unit instanceof BattleArmor){
            // If we're removing a DWP and it has an attached weapon, we need
            //  to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && mount.getLinked() != null){
                Mounted link = mount.getLinked();
                link.setDWPMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }
            // If we are removing a weapon that is mounted in an DWP, we need
            //  to clear the mounted status of the DWP
            if (mount.getLinkedBy() != null 
                    && mount.getLinkedBy().getType().hasFlag(
                            MiscType.F_DETACHABLE_WEAPON_PACK)){
                Mounted dwp = mount.getLinkedBy();
                dwp.setLinked(null);
                dwp.setLinkedBy(null);
            }
            // If we're removing an APM and it has an attached weapon, we need
            //  to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_AP_MOUNT)
                    && mount.getLinked() != null){
                Mounted link = mount.getLinked();
                link.setAPMMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }
            // If we are removing a weapon that is mounted in an APM, we need
            //  to clear the mounted status of the AP Mount
            if (mount.getLinkedBy() != null 
                    && mount.getLinkedBy().getType().hasFlag(
                            MiscType.F_AP_MOUNT)){
                Mounted apm = mount.getLinkedBy();
                apm.setLinked(null);
                apm.setLinkedBy(null);
            }
        }
        unit.getEquipment().remove(mount);
        if (mount.getType() instanceof MiscType) {
            unit.getMisc().remove(mount);
        } else if (mount.getType() instanceof AmmoType) {
            unit.getAmmo().remove(mount);
        } else {
            unit.getWeaponList().remove(mount);
        }
    }

    /**
     * Sets the corresponding critical slots to null for the Mounted object.
     *
     * @param unit
     * @param eq
     */
    public static void removeCriticals(Entity unit, Mounted eq) {

        if (eq.getLocation() == Entity.LOC_NONE) {
            return;
        }
        for (int loc = 0; loc < unit.locations(); loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = unit.getCritical(loc, slot);
                if ((cs != null)
                        && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)
                        && (cs.getMount().equals(eq))) {
                    cs = null;
                    unit.setCritical(loc, slot, cs);
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
        if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_TARGCOMP)) {
            return true;
        }

        return false;
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

                if ((cs != null)
                        && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
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
            if (mount.getType().hasFlag(MiscType.F_TARGCOMP)
                    && TechConstants.isClan(mount.getType().getTechLevel(
                            unit.getTechLevelYear()))) {
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
    public static Mounted updateTC(Entity unit, EquipmentType tc) {
        UnitUtil.removeTC(unit);
        return UnitUtil.createTCMounts(unit, tc);
    }

    /**
     * Creates TC Mount.
     *
     * @param unit
     */
    public static Mounted createTCMounts(Entity unit, EquipmentType tc) {
        Mounted mount = null;
        try {
            mount = unit.addEquipment(tc, Entity.LOC_NONE);
        } catch (Exception ex) {
        }
        return mount;
    }

    /**
     * Checks to see if unit can use the techlevel
     *
     * @param unit
     * @param techLevel
     * @return Boolean if the tech level is legal for the passed unit
     */
    public static boolean isLegal(Entity unit, int techLevel) {
        return TechConstants.isLegal(unit.getTechLevel(), techLevel, false,
                unit.isMixedTech());
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

    /***
     * Checks for Clan DHS
     *
     * @param unit
     * @return
     */
    public static boolean hasClanDoubleHeatSinks(Mech unit) {

        if (!unit.hasDoubleHeatSinks()) {
            return false;
        }

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return false;
            }

            if (mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                if (mounted.getType().getInternalName()
                        .equals("CLDoubleHeatSink")) {
                    return true;
                }
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
        return ((eq.getType() != null) && isHeatSink(eq.getType()));
    }

    /**
     * Checks if EquipmentType is a heat sink
     *
     * @param eq
     * @return
     */
    public static boolean isHeatSink(EquipmentType eq) {
        return isHeatSink(eq, false);
    }

    public static boolean isHeatSink(EquipmentType eq, boolean ignoreprototype) {
        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_HEAT_SINK)
                        || eq.hasFlag(MiscType.F_LASER_HEAT_SINK)
                        || eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK) || (eq
                        .hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE) && !ignoreprototype))) {
            return true;
        }

        return false;
    }

    /**
     * Checks if EquipmentType is a Mech Physical weapon
     *
     * @param eq
     * @return
     */
    public static boolean isPhysicalWeapon(EquipmentType eq) {

        if ((eq instanceof MiscType)
                && ((eq.hasFlag(MiscType.F_CLUB)
                        || eq.hasFlag(MiscType.F_HAND_WEAPON) || eq
                            .hasFlag(MiscType.F_TALON)))) {
            if (eq.hasFlag(MiscType.F_CLUB)
                    && ((eq.hasSubType(MiscType.S_CLUB) || eq
                            .hasSubType(MiscType.S_TREE_CLUB)))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Removes the specified number of heat sinks from the mek Heat sinks are
     * removed first fwith LOC_NONE above the free crit limit then they are
     * removed with a location, and lastly they are removed below the free crit
     * limit
     *
     * @param unit
     */
    public static void removeHeatSinks(Mech unit, int number) {
        Vector<Mounted> toRemove = new Vector<Mounted>();
        int base = UnitUtil.getBaseChassisHeatSinks(unit,
                unit.hasCompactHeatSinks());
        boolean splitCompact = false;
        if (unit.hasCompactHeatSinks()) {
            // first check to see if there is a single compact heat sink outside
            // of
            // the engine and remove this first if so
            Mounted mount = UnitUtil.getSingleCompactHeatSink(unit);
            if ((null != mount) && (number > 0)) {
                UnitUtil.removeMounted(unit, mount);
                number--;
            }
            // if number is now uneven, then note that we will need to split a
            // compact
            if ((number % 2) == 1) {
                splitCompact = true;
                number--;
            }
        }
        Vector<Mounted> unassigned = new Vector<Mounted>();
        Vector<Mounted> assigned = new Vector<Mounted>();
        Vector<Mounted> free = new Vector<Mounted>();
        for (Mounted m : unit.getMisc()) {
            if (UnitUtil.isHeatSink(m)) {
                if (m.getLocation() == Entity.LOC_NONE) {
                    if (base > 0) {
                        free.add(m);
                        base--;
                    } else {
                        unassigned.add(m);
                    }
                } else {
                    assigned.add(m);
                }
            }
        }
        toRemove.addAll(unassigned);
        toRemove.addAll(assigned);
        toRemove.addAll(free);
        if (unit.hasCompactHeatSinks()) {
            // need to do some number magic here. The unassigned and assigned
            // slots
            // should each contain two heat sinks, but if we dip into the free
            // then we
            // are looking at one heat sink.
            int numberDouble = Math.min(number / 2, unassigned.size()
                    + assigned.size());
            int numberSingle = Math.max(0, number - (2 * numberDouble));
            number = numberDouble + numberSingle;
        }
        number = Math.min(number, toRemove.size());
        for (int i = 0; i < number; i++) {
            Mounted eq = toRemove.get(i);
            UnitUtil.removeMounted(unit, eq);
        }
        if (splitCompact) {
            Mounted eq = toRemove.get(number);
            int loc = eq.getLocation();
            // remove singleCompact mount and replace with a double
            UnitUtil.removeMounted(unit, eq);
            if (!eq.getType().hasFlag(MiscType.F_HEAT_SINK)) {
                try {
                    unit.addEquipment(
                            new Mounted(unit, EquipmentType
                                    .get("IS1 Compact Heat Sink")), loc, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
    public static void addHeatSinkMounts(Mech unit, int hsAmount, String hsType) {

        EquipmentType sinkType;
        sinkType = EquipmentType.get(UnitUtil.getHeatSinkType(hsType,
                unit.isClan()));
        if (hsType.equals("Compact")) {
            UnitUtil.addCompactHeatSinkMounts(unit, hsAmount);
        } else {
            for (; hsAmount > 0; hsAmount--) {
                try {
                    unit.addEquipment(new Mounted(unit, sinkType),
                            Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void addCompactHeatSinkMounts(Mech unit, int hsAmount) {
        // first we need to figure out how many single compacts we need to add
        // for the engine, if any
        int currentSinks = UnitUtil.countActualHeatSinks(unit);
        int engineCompacts = Math.min(hsAmount,
                UnitUtil.getBaseChassisHeatSinks(unit, true));
        int engineToAdd = Math.max(0, engineCompacts - currentSinks);
        unit.addEngineSinks("IS1 Compact Heat Sink", engineToAdd);
        int restHS = hsAmount - engineToAdd;
        Mounted singleCompact = getSingleCompactHeatSink(unit);
        if ((restHS % 2) == 1) {
            if (null == singleCompact) {
                try {
                    unit.addEquipment(
                            new Mounted(unit, EquipmentType
                                    .get("IS1 Compact Heat Sink")),
                            Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                int loc = singleCompact.getLocation();
                // remove singleCompact mount and replace with a double
                UnitUtil.removeMounted(unit, singleCompact);
                try {
                    unit.addEquipment(
                            new Mounted(unit, EquipmentType.get(UnitUtil
                                    .getHeatSinkType("Compact", unit.isClan()))),
                            loc, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            restHS -= 1;
        }
        for (; restHS > 0; restHS -= 2) {
            try {
                unit.addEquipment(
                        new Mounted(unit, EquipmentType.get(UnitUtil
                                .getHeatSinkType("Compact", unit.isClan()))),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * get the single non-compact heat sink that is a non-engine sink, if it
     * exits
     *
     * @param unit
     */
    public static Mounted getSingleCompactHeatSink(Mech unit) {
        int base = UnitUtil.getBaseChassisHeatSinks(unit, true);
        for (Mounted m : unit.getMisc()) {
            if (m.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                    && m.getType().hasFlag(MiscType.F_HEAT_SINK)) {
                if (base <= 0) {
                    return m;
                } else {
                    base--;
                }
            }
        }
        return null;
    }

    public static String getHeatSinkType(String type, boolean clan) {
        String heatSinkType = "Heat Sink";

        if (type.startsWith("(Clan)")) {
            clan = true;
            type = type.substring(7).trim();
        } else if (type.startsWith("(IS)")) {
            clan = false;
            type = type.substring(4).trim();
        }

        if (clan) {
            if (type.equals("Single")) {
                heatSinkType = "Heat Sink";
            } else if (type.equals("Double")) {
                heatSinkType = "CLDoubleHeatSink";
            } else {
                heatSinkType = "CLLaser Heat Sink";
            }
        } else {
            if (type.equals("Single")) {
                heatSinkType = "Heat Sink";
            } else if (type.equals("Double")) {
                heatSinkType = "ISDoubleHeatSink";
            } else {
                heatSinkType = "IS2 Compact Heat Sinks";
            }
        }

        return heatSinkType;
    }

    public static boolean hasSameHeatSinkType(Mech unit, String type) {
        // this seems like a total hack, but at present we apparently have no
        // good static integer codes for this on entity
        String heatSinkType = UnitUtil.getHeatSinkType(type, unit.isClan());
        for (Mounted mounted : unit.getMisc()) {
            if (type.equals("Compact")
                    && mounted.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)) {
                return true;
            }
            if (mounted.getType().hasFlag(MiscType.F_HEAT_SINK)
                    || mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                return mounted.getType().getInternalName().equals(heatSinkType);
            }
        }
        return false;
    }

    /**
     * updates the heat sinks.
     *
     * @param unit
     * @param hsAmount
     * @param hsType
     */
    public static void updateHeatSinks(Mech unit, int hsAmount, String hsType) {
        // if we have the same type of heat sink, then we should not remove the
        // existing heat sinks
        int currentSinks = UnitUtil.countActualHeatSinks(unit);
        if (UnitUtil.hasSameHeatSinkType(unit, hsType)) {
            if (hsAmount < currentSinks) {
                UnitUtil.removeHeatSinks(unit, currentSinks - hsAmount);
            } else if (hsAmount > currentSinks) {
                UnitUtil.addHeatSinkMounts(unit, hsAmount - currentSinks,
                        hsType);
            }
        } else {
            UnitUtil.removeHeatSinks(unit, hsAmount);
            UnitUtil.addHeatSinkMounts(unit, hsAmount, hsType);
        }
        unit.resetSinks();
    }

    /**
     * This will cycle through the heat sinks and make sure that enough of them
     * are set LOC_NONE based on the basechassisheat sinks
     *
     * @param unit
     */
    public static void updateAutoSinks(Mech unit, String hsType) {
        int base = UnitUtil.getBaseChassisHeatSinks(unit,
                hsType.equals("Compact"));
        Vector<Mounted> unassigned = new Vector<Mounted>();
        Vector<Mounted> assigned = new Vector<Mounted>();
        for (Mounted m : unit.getMisc()) {
            if (UnitUtil.isHeatSink(m)) {
                if (m.getLocation() == Entity.LOC_NONE) {
                    unassigned.add(m);
                } else {
                    assigned.add(m);
                }
            }
        }
        int needed = base - unassigned.size();
        if (needed <= 0) {
            return;
        }
        for (Mounted m : assigned) {
            if (needed <= 0) {
                break;
            }
            UnitUtil.removeCriticals(unit, m);
            m.setLocation(Entity.LOC_NONE);
            needed--;
        }
        // if for some reason we still didn't find enough heat sinks then make
        // some more
        if (needed > 0) {
            UnitUtil.addHeatSinkMounts(unit, needed, hsType);
        }
    }

    public static boolean isJumpJet(Mounted m) {
        return m.getType().hasFlag(MiscType.F_JUMP_JET)
                || m.getType().hasFlag(MiscType.F_JUMP_BOOSTER);
    }

    public static String getJumpJetType(int type, boolean clan) {
        if (type == Mech.JUMP_IMPROVED) {
            if (clan) {
                return "CLImprovedJump Jet";
            } else {
                return "ISImprovedJump Jet";
            }
        } else if (type == Mech.JUMP_PROTOTYPE) {
            return "ISPrototypeJumpJet";
        } else if (type == Mech.JUMP_BOOSTER) {
            return "Jump Booster";
        }
        return "JumpJet";
    }

    /**
     * Removes all jump jets from the mek
     *
     * @param unit
     */
    public static void removeJumpJets(Mech unit, int number) {
        Vector<Mounted> toRemove = new Vector<Mounted>();
        for (Mounted eq : unit.getMisc()) {
            if (UnitUtil.isJumpJet(eq)) {
                toRemove.add(eq);
                if (toRemove.size() >= number) {
                    break;
                }
            }
        }
        for (Mounted eq : toRemove) {
            UnitUtil.removeMounted(unit, eq);
        }
    }

    /**
     * updates the Jump Jets.
     *
     * @param unit
     * @param jjAmount
     * @param jjType
     */
    public static void updateJumpJets(Mech unit, int jjAmount, int jjType) {
        unit.setOriginalJumpMP(jjAmount);
        if (jjType == unit.getJumpType()) {
            int currentJJ = unit.getJumpMP();
            if (jjAmount < currentJJ) {
                UnitUtil.removeJumpJets(unit, currentJJ - jjAmount);
                return;
            } else if (jjAmount > currentJJ) {
                jjAmount = jjAmount - currentJJ;
            }
        } else {
            UnitUtil.removeJumpJets(unit, unit.getJumpMP());
        }
        // if this is the same jump jet type, then only remove if too many
        // and add if too low
        if (jjType == Mech.JUMP_BOOSTER) {
            UnitUtil.removeJumpJets(unit, unit.getJumpMP());
            createSpreadMounts(
                    unit,
                    EquipmentType.get(UnitUtil.getJumpJetType(jjType,
                            unit.isClan())));
        } else {
            while (jjAmount > 0) {
                try {
                    unit.addEquipment(
                            new Mounted(unit, EquipmentType.get(UnitUtil
                                    .getJumpJetType(jjType, unit.isClan()))),
                            Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                jjAmount--;
            }
        }
    }

    /**
     * Removes all enhancements (TSM and MASC) from the mek
     *
     * @param unit
     */
    public static void removeEnhancements(Mech unit) {
        ConcurrentLinkedQueue<Mounted> equipmentList = new ConcurrentLinkedQueue<Mounted>(
                unit.getMisc());
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isTSM(eq.getType()) || UnitUtil.isMASC(eq.getType())) {
                UnitUtil.removeCriticals(unit, eq);
            }
        }
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isTSM(eq.getType()) || UnitUtil.isMASC(eq.getType())) {
                unit.getMisc().remove(eq);
                unit.getEquipment().remove(eq);
            }
        }
    }

    public static void updateEnhancements(Mech unit, boolean hasMASC,
            boolean hasTSM) {
        UnitUtil.removeEnhancements(unit);
        if (hasTSM) {
            if (unit.isIndustrial()) {
                UnitUtil.createSpreadMounts(unit,
                        EquipmentType.get("Industrial TSM"));
            } else {
                UnitUtil.createSpreadMounts(unit, EquipmentType.get("TSM"));
            }
        }
        if (hasMASC) {
            Mounted mount = new Mounted(unit, EquipmentType.get("ISMASC"));
            if (unit.isClan()) {
                mount = new Mounted(unit, EquipmentType.get("CLMASC"));
            }
            try {
                unit.addEquipment(mount, Entity.LOC_NONE, false);
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    public static boolean isPrintableEquipment(EquipmentType eq) {
        return UnitUtil.isPrintableEquipment(eq, false);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq
     * @return
     */
    public static boolean isPrintableEquipment(EquipmentType eq, boolean isMech) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isFixedLocationSpreadEquipment(eq)
                && !(eq instanceof MiscType) && eq.hasFlag(MiscType.F_TALON)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq) && isMech) {
            return false;
        }
        if (!eq.isHittable() && isMech) {
            return false;
        }

        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_CASE)
                        || eq.hasFlag(MiscType.F_ARTEMIS)
                        || eq.hasFlag(MiscType.F_ARTEMIS_V)
                        || eq.hasFlag(MiscType.F_APOLLO)
                        || (eq.hasFlag(MiscType.F_MASC) && !eq
                                .hasSubType(MiscType.S_JETBOOSTER))
                        || eq.hasFlag(MiscType.F_HARJEL)
                        || eq.hasFlag(MiscType.F_MASS)
                        || eq.hasFlag(MiscType.F_CHASSIS_MODIFICATION)
                        || eq.hasFlag(MiscType.F_MASH_EXTRA)
                        || eq.hasFlag(MiscType.F_DRONE_EXTRA) || eq
                            .hasFlag(MiscType.F_SPONSON_TURRET))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        return true;

    }

    /**
     * simple method to let us know if eq should be printed on the weapons and
     * equipment section of the Record sheet.
     *
     * @param eq
     * @return
     */
    public static boolean isPrintableBAEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (UnitUtil.isFixedLocationSpreadEquipment(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }

        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_AP_MOUNT)
                        || eq.hasFlag(MiscType.F_FIRE_RESISTANT)
                        || eq.hasFlag(MiscType.F_STEALTH)
                        || eq.hasFlag(MiscType.F_ARTEMIS)
                        || eq.hasFlag(MiscType.F_ARTEMIS_V)
                        || eq.hasFlag(MiscType.F_APOLLO)
                        || eq.hasFlag(MiscType.F_HARJEL)
                        || eq.hasFlag(MiscType.F_MASS) || eq
                            .hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        if ((eq instanceof LegAttack) || (eq instanceof SwarmAttack)
                || (eq instanceof StopSwarmAttack)
                || (eq instanceof InfantryRifleAutoRifleWeapon)
                || (eq instanceof SwarmWeaponAttack)) {
            return false;
        }

        return true;

    }

    public static void changeMountStatus(Entity unit, Mounted eq, int location,
            int secondaryLocation, boolean rear) {
        eq.setLocation(location, rear);
        eq.setSecondLocation(secondaryLocation, rear);
        eq.setSplit(secondaryLocation > -1);
    }

    public static boolean hasTargComp(Entity unit) {

        for (Mounted mount : unit.getEquipment()) {
            if ((mount.getType() instanceof MiscType)
                    && mount.getType().hasFlag(MiscType.F_TARGCOMP)) {
                return true;
            }
        }

        return false;
    }

    public static Mounted getTargComp(Entity unit) {
        for (Mounted misc : unit.getMisc()) {
            if (misc.getType().hasFlag(MiscType.F_TARGCOMP)) {
                return misc;
            }
        }
        return null;
    }

    public static int[] getHighestContinuousNumberOfCritsArray(Mech unit) {
        int[] critSpaces = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };

        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            critSpaces[loc] = UnitUtil.getHighestContinuousNumberOfCrits(unit,
                    loc);
        }

        return critSpaces;
    }

    
    /**
     * This method will return the number of contiguous criticals in the given
     * location, starting at the given critical slot
     * 
     * @param unit          Unit to check critical slots on
     * @param location      The location on the unit to check slots on
     * @param startingSlot  The critical slot to start at
     * @return
     */
    public static int getContiguousNumberOfCrits(Entity unit, int location, 
            int startingSlot){
        
        int numCritSlots = unit.getNumberOfCriticals(location);
        int contiguousCrits = 0;
        
        for (int slot = startingSlot; slot < numCritSlots; slot++) {
            if (unit.getCritical(location, slot) == null) {
                contiguousCrits++;
            } else {
               break;
            }
        }
        return contiguousCrits;
    }
    
    
    public static int getHighestContinuousNumberOfCrits(Entity unit,
            int location) {
        int highestNumberOfCrits = 0;
        int currentCritCount = 0;

        for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
            if (unit.getCritical(location, slot) == null) {
                currentCritCount++;
            } else {
                currentCritCount = 0;
            }
            highestNumberOfCrits = Math.max(currentCritCount,
                    highestNumberOfCrits);
        }

        return highestNumberOfCrits;
    }

    public static double getUnallocatedAmmoTonnage(Entity unit) {
        double tonnage = 0;

        for (Mounted mount : unit.getAmmo()) {
            int ammoType = ((AmmoType)mount.getType()).getAmmoType();
            // don't add ammo with just one shot, that's OS ammo
            //  Unless it's a single shot ammo type, like Cruise Missiles
            if ((mount.getLocation() == Entity.LOC_NONE)
                    && (mount.getUsableShotsLeft() > 1
                            || ammoType == AmmoType.T_CRUISE_MISSILE)) {
                tonnage += mount.getType().getTonnage(unit);
            }
        }

        return tonnage;
    }

    public static int getMaximumArmorPoints(Entity unit) {
        int points = 0;
        if (unit instanceof Mech) {
            points = (unit.getTotalInternal() * 2) + 3;
        } else if (unit instanceof Tank) {
            points = (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else if (unit instanceof BattleArmor) {
            points = (unit.getWeightClass() * 4) + 2;
        }
        return points;
    }

    public static int getMaximumArmorPoints(Entity unit, int loc) {
        if ((unit instanceof Mech) && (loc == Mech.LOC_HEAD)) {
            return 9;
        } else if (unit instanceof Mech) {
            return unit.getInternal(loc) * 2;
        } else if (unit instanceof Tank) {
            return (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else {
            return 0;
        }
    }

    public static double getMaximumArmorTonnage(Entity unit) {

        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(
                unit.getArmorType(1), unit.getArmorTechLevel(1));
        double armorWeight = 0;

        if (unit.getArmorType(1) == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        if (unit instanceof Mech) {
            double points = (unit.getTotalInternal() * 2) + 3;
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof Tank) {
            double points = Math.floor((unit.getWeight() * 3.5) + 40);
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof BattleArmor) {
            armorWeight = (unit.getWeightClass() * 4) + 2;
        }
        if (unit instanceof Aero){
            double points =
                    TestAero.maxArmorPoints(unit, unit.getWeight());
            armorWeight = points / armorPerTon;
        }
        return armorWeight;
    }

    /**
     * NOTE: only use for non-patchwork armor
     *
     * @param unit
     * @param armorTons
     * @return
     */
    public static int getArmorPoints(Entity unit, double armorTons) {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(
                unit.getArmorType(1), unit.getArmorTechLevel(1));
        if (unit.getArmorType(1) == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        return Math.min((int) Math.floor(armorPerTon * armorTons),
                UnitUtil.getMaximumArmorPoints(unit));
    }

    /**
     * NOTE: only use for non-patchwork armor
     *
     * @param unit
     * @param armorTons
     * @return
     */
    public static int getArmorPoints(Entity unit, int loc, double armorTons) {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(
                unit.getArmorType(loc), unit.getArmorTechLevel(loc));
        if (unit.getArmorType(loc) == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        return Math.min((int) Math.floor(armorPerTon * armorTons),
                UnitUtil.getMaximumArmorPoints(unit, loc));
    }

    public static void compactCriticals(Entity unit) {
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit instanceof Mech) {
                UnitUtil.compactCriticals((Mech) unit, loc);
            } else {
                UnitUtil.compactCriticals(unit, loc);
            }
        }
    }

    public static void compactCriticals(Entity unit, int loc) {
        int firstEmpty = -1;
        for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
            CriticalSlot cs = unit.getCritical(loc, slot);

            if ((cs == null) && (firstEmpty == -1)) {
                firstEmpty = slot;
            }
            if ((firstEmpty != -1) && (cs != null)) {
                // move this to the first empty slot
                unit.setCritical(loc, firstEmpty, cs);
                // mark the old slot empty
                unit.setCritical(loc, slot, null);
                // restart just after the moved slot's new location
                slot = firstEmpty;
                firstEmpty = -1;
            }
        }
    }

    public static void compactCriticals(Mech unit) {
        for (int loc = 0; loc < unit.locations(); loc++) {
            UnitUtil.compactCriticals(unit, loc);
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
        return weapon.hasFlag(WeaponType.F_AMS);
    }

    public static boolean hasSwitchableAmmo(WeaponType weapon) {

        if (weapon instanceof StreakLRMWeapon) {
            return false;
        }
        if (weapon instanceof StreakSRMWeapon) {
            return false;
        }
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

        if (weapon instanceof CLChemicalLaserWeapon) {
            return false;
        }

        if (weapon instanceof MPodWeapon) {
            return false;
        }

        if (weapon instanceof BPodWeapon) {
            return false;
        }

        if (weapon instanceof ISPlasmaRifle) {
            return false;
        }

        if (weapon instanceof CLPlasmaCannon) {
            return false;
        }
        if (weapon instanceof VehicleFlamerWeapon) {
            return false;
        }
        if (!(weapon instanceof AmmoWeapon)) {
            return false;
        }
        if (weapon instanceof CLBALBX) {
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
        for (int location = 0; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }
                Mounted mount = cs.getMount();

                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())
                        && (UnitUtil.isTSM(mount.getType()) || UnitUtil
                                .isArmorOrStructure(mount.getType()))) {
                    Mounted newMount = new Mounted(unit, mount.getType());
                    newMount.setLocation(location, mount.isRearMounted());
                    newMount.setArmored(mount.isArmored());
                    cs.setMount(newMount);
                    cs.setArmored(mount.isArmored());
                    unit.getEquipment().remove(mount);
                    unit.getMisc().remove(mount);
                    unit.getEquipment().add(newMount);
                    unit.getMisc().add(newMount);
                }
            }
        }
    }

    /**
     * create a Mounted and corresponding CriticalSlots for the passed in
     * <code>EquipmentType</code> on the passed in <code>Mech</code>
     *
     * @param unit
     * @param equip
     * @return
     */
    public static Mounted createSpreadMounts(Mech unit, EquipmentType equip) {
        // how many non-spreadable contiguous blocks of crits?
        int blocks = 0;
        boolean isMisc = equip instanceof MiscType;

        blocks = equip.getCriticals(unit);

        List<Integer> locations = new ArrayList<Integer>();

        if (isMisc) {
            if ((equip.hasFlag(MiscType.F_INDUSTRIAL_TSM) || equip
                    .hasFlag(MiscType.F_TSM))) {
                // all crits user placeable
                for (int i = 0; i < equip.getCriticals(unit); i++) {
                    locations.add(Entity.LOC_NONE);
                }
            } else if (equip.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)) {
                // 1 crit in each location
                for (int i = 0; i < unit.locations(); i++) {
                    locations.add(i);
                }
            } else if (equip.hasFlag(MiscType.F_STEALTH)) {
                // 2 in arms, legs, side torsos
                locations.add(Mech.LOC_LLEG);
                locations.add(Mech.LOC_RLEG);
                locations.add(Mech.LOC_LARM);
                locations.add(Mech.LOC_RARM);
                locations.add(Mech.LOC_LT);
                locations.add(Mech.LOC_RT);
                blocks = 6;
            } else if ((equip.hasFlag(MiscType.F_TRACKS)
                    || equip.hasFlag(MiscType.F_TALON) || equip
                        .hasFlag(MiscType.F_JUMP_BOOSTER))) {
                // 1 block in each leg
                locations.add(Mech.LOC_LLEG);
                locations.add(Mech.LOC_RLEG);
                if (unit instanceof QuadMech) {
                    locations.add(Mech.LOC_LARM);
                    locations.add(Mech.LOC_RARM);
                }
                blocks = (unit instanceof BipedMech ? 2 : 4);
            } else if (equip.hasFlag(MiscType.F_PARTIAL_WING)) {
                // one block in each side torso
                locations.add(Mech.LOC_LT);
                locations.add(Mech.LOC_RT);
                blocks = 2;
            } else if ((equip.hasFlag(MiscType.F_VOIDSIG)
                    || equip.hasFlag(MiscType.F_NULLSIG) || equip
                        .hasFlag(MiscType.F_BLUE_SHIELD))) {
                // 1 crit in each location, except the head
                for (int i = Mech.LOC_CT; i < unit.locations(); i++) {
                    locations.add(i);
                }
            } else if (equip.hasFlag(MiscType.F_CHAMELEON_SHIELD)) {
                // 1 crit in each location except head and CT
                for (int i = Mech.LOC_RT; i < unit.locations(); i++) {
                    locations.add(i);
                }
            }
        }

        boolean firstBlock = true;
        Mounted mount = new Mounted(unit, equip);
        for (; blocks > 0; blocks--) {
            // how many crits per block?
            int crits = UnitUtil.getCritsUsed(unit, equip);
            for (int i = 0; i < crits; i++) {
                try {
                    if (firstBlock || (locations.get(0) == Entity.LOC_NONE)) {
                        // create only one mount per equipment, for BV and stuff
                        unit.addEquipment(mount, locations.get(0), false);
                        if (firstBlock) {
                            firstBlock = false;
                        }
                        if (locations.get(0) == Entity.LOC_NONE) {
                            // only user-placable spread stuff gets location
                            // none
                            // for those, we need to create a mount for each
                            // crit,
                            // otherwise we can't correctly let the user place
                            // them
                            // luckily, that only affects TSM, so BV works out
                            // correctly
                            mount = new Mounted(unit, equip);
                        }
                    } else {
                        CriticalSlot cs = new CriticalSlot(mount);
                        if (!unit.addCritical(locations.get(0), cs)) {
                            UnitUtil.removeCriticals(unit, mount);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "No room for equipment",
                                    mount.getName()
                                            + " does not fit into "
                                            + unit.getLocationName(locations
                                                    .get(0)),
                                    JOptionPane.INFORMATION_MESSAGE);
                            unit.getMisc().remove(mount);
                            unit.getEquipment().remove(mount);
                            return null;
                        }
                    }
                } catch (LocationFullException lfe) {
                    lfe.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            lfe.getMessage(),
                            mount.getName() + " does not fit into "
                                    + unit.getLocationName(locations.get(0)),
                            JOptionPane.INFORMATION_MESSAGE);
                    unit.getMisc().remove(mount);
                    unit.getEquipment().remove(mount);
                    return null;
                }
            }
            locations.remove(0);
        }
        return mount;
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

    public static Font getNewFont(Graphics2D g2d, String info, boolean bold,
            int stringWidth, float pointSize) {
        Font font = UnitUtil.deriveFont(bold, pointSize);

        while ((ImageHelper.getStringWidth(g2d, info, font) > stringWidth)
                && (pointSize > 0)) {
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
                    if ((cs == null)
                            || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
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
                    if ((cs == null)
                            || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
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

            if (mount.getType().isExplosive(mount)
                    && ((mount.getLocation() == location) || (mount
                            .getSecondLocation() == location))) {
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
        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                        || eq.hasFlag(MiscType.F_JUMP_JET) || eq
                            .hasFlag(MiscType.F_UMU))) {
            return true;
        }

        return false;
    }

    public static int getBAAmmoCount(Entity ba, WeaponType weapon, int location) {
        int ammoCount = 0;

        for (Mounted mount : ba.getAmmo()) {
            if (mount.getLocation() != location) {
                continue;
            }
            AmmoType ammo = (AmmoType) mount.getType();

            if ((ammo.getRackSize() == weapon.getRackSize())
                    && (ammo.getAmmoType() == weapon.getAmmoType())) {
                ammoCount++;
            }
        }

        return ammoCount;
    }

    public static String getCritName(Entity unit, EquipmentType eq) {
        String name = eq.getName();
        if ((eq instanceof WeaponType)
                && (eq.hasFlag(WeaponType.F_C3M) || eq
                        .hasFlag(WeaponType.F_C3MBS))) {
            return name = name.substring(0,
                    eq.getName().indexOf("with TAG") - 1);
        }
        if (unit.isMixedTech()
                && (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_ALLOWED_ALL)
                && (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_TECH_UNKNOWN)) {

            if (unit.isClan()
                    && !TechConstants.isClan(eq.getTechLevel(unit
                            .getTechLevelYear()))) {
                name = name + " (IS)";
            }

            if (!unit.isClan()
                    && TechConstants.isClan(eq.getTechLevel(unit
                            .getTechLevelYear()))) {
                name = name + " (Clan)";
            }
        }
        return name;
    }

    public static String getToolTipInfo(Entity unit, Mounted eq) {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        unusualSymbols.setGroupingSeparator(',');
        DecimalFormat myFormatter = new DecimalFormat("#,##0", unusualSymbols);
        StringBuilder sb = new StringBuilder("<HTML>");
        sb.append(eq.getName());
        if (eq.getType() instanceof InfantryWeapon) {
            sb.append("<br>Damage/Trooper: ");
            double infDamage = ((InfantryWeapon) eq.getType())
                    .getInfantryDamage();
            sb.append(infDamage);
            sb.append("<br>Range Class: "
                    + ((InfantryWeapon) eq.getType()).getInfantryRange());
        } else {
            sb.append("<br>Crits: ");
            sb.append(eq.getType().getCriticals(unit));
            sb.append("<br>Tonnage: ");
            if (eq.getType() instanceof MiscType) {
                sb.append(((MiscType) eq.getType()).getTonnage(unit,
                        eq.getLocation()));
            } else {
                sb.append(eq.getType().getTonnage(unit));
            }

            if (eq.getType() instanceof WeaponType) {
                sb.append("<br>Heat: ");
                sb.append(((WeaponType) eq.getType()).getHeat());
            }
        }
        sb.append("<Br>Cost: ");

        double cost = eq.getType().getCost(unit, false, eq.getLocation());

        sb.append(myFormatter.format(cost));
        sb.append(" CBills");

        if (eq.isRearMounted()) {
            sb.append("<br>Rear Facing");
        }
        if (eq.isMechTurretMounted()) {
            sb.append("<br>Turret mounted");
        }
        if (eq.isArmored()) {
            sb.append("<br>Armored");
        }
        sb.append("</html>");
        return sb.toString();
    }

    public static int getBaseChassisHeatSinks(Entity unit, boolean compact) {
        int engineHSCapacity = unit.getEngine().integralHeatSinkCapacity(
                compact);

        if (unit.isOmni()) {
            engineHSCapacity = Math.min(engineHSCapacity, unit.getEngine()
                    .getBaseChassisHeatSinks(compact));
        }

        return engineHSCapacity;
    }

    public static boolean isPreviousCritEmpty(Entity unit, CriticalSlot cs,
            int slot, int location) {
        if (slot == 0) {
            return false;
        }
        if (unit instanceof Mech) {
            if ((slot > 0) && (unit.getCritical(location, slot - 1) != null)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLastCrit(Entity unit, CriticalSlot cs, int slot,
            int location) {
        if (unit instanceof Mech) {
            return UnitUtil.isLastMechCrit((Mech) unit, cs, slot, location);
        }
        return true;
    }

    public static boolean isLastMechCrit(Mech unit, CriticalSlot cs, int slot,
            int location) {

        if (cs == null) {
            return true;
        }
        // extra check for the last crit in a location, it shouldn't get a
        // border
        if ((slot + 1) >= unit.getNumberOfCriticals(location)) {
            return false;
        }

        int lastIndex = 0;
        if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {

            for (int position = 0; position < unit
                    .getNumberOfCriticals(location); position++) {
                if ((cs.getIndex() == Mech.SYSTEM_ENGINE) && (slot >= 3)
                        && (position < 3)) {
                    position = 3;
                }
                CriticalSlot crit = unit.getCritical(location, position);

                if ((crit != null)
                        && (crit.getType() == CriticalSlot.TYPE_SYSTEM)
                        && (crit.getIndex() == cs.getIndex())) {
                    lastIndex = position;
                } else if (position > slot) {
                    break;
                }
            }

        } else {
            CriticalSlot nextCrit = unit.getCritical(location, slot + 1);
            if (nextCrit == null) {
                return true;
            } else if ((nextCrit.getMount() == null)
                    || !nextCrit.getMount().equals(cs.getMount())) {
                return true;
            } else {
                return false;
            }

            /*
             * Mounted originalMount = cs.getMount(); Mounted testMount = null;
             *
             * if (originalMount == null) { originalMount =
             * cs.getMount(); }
             *
             * if (originalMount == null) { return true; }
             *
             * int numberOfCrits = slot -
             * (originalMount.getType().getCriticals(unit) - 1);
             *
             * if (numberOfCrits < 0) { return false; } for (int position =
             * slot; position >= numberOfCrits; position--) { CriticalSlot crit
             * = unit.getCritical(location, position);
             *
             * if ((crit == null) || (crit.getType() !=
             * CriticalSlot.TYPE_EQUIPMENT)) { return false; }
             *
             * if ((testMount = crit.getMount()) == null) { testMount =
             * crit.getMount(); }
             *
             * if (testMount == null) { return false; }
             *
             * if (!testMount.equals(originalMount)) { return false; }
             *
             * } return true;
             */
        }

        return slot == lastIndex;
    }

    public static void updateCritsArmoredStatus(Entity unit, Mounted mount) {
        for (int position = 0; position < unit.getNumberOfCriticals(mount
                .getLocation()); position++) {
            CriticalSlot cs = unit.getCritical(mount.getLocation(), position);
            if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                continue;
            }

            if ((cs.getMount() != null) && cs.getMount().equals(mount)) {
                cs.setArmored(mount.isArmored());
            } else if ((cs.getMount() != null)
                    && cs.getMount().equals(mount)) {
                cs.setArmored(mount.isArmored());
            }

        }

        if ((mount.isSplitable() || mount.getType().isSpreadable())
                && (mount.getSecondLocation() != Entity.LOC_NONE)) {
            for (int position = 0; position < unit.getNumberOfCriticals(mount
                    .getSecondLocation()); position++) {
                CriticalSlot cs = unit.getCritical(mount.getLocation(),
                        position);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if ((cs.getMount() != null) && cs.getMount().equals(mount)) {
                    cs.setArmored(mount.isArmored());
                } else if ((cs.getMount() != null)
                        && cs.getMount().equals(mount)) {
                    cs.setArmored(mount.isArmored());
                }

            }
        }
    }

    public static void updateCritsArmoredStatus(Entity unit, CriticalSlot cs,
            int location) {

        if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            return;
        }

        if (cs.getIndex() <= Mech.SYSTEM_GYRO) {
            for (int loc = Mech.LOC_HEAD; loc <= Mech.LOC_LT; loc++) {
                for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                    CriticalSlot newCrit = unit.getCritical(loc, slot);

                    if ((newCrit != null)
                            && (newCrit.getType() == CriticalSlot.TYPE_SYSTEM)
                            && (newCrit.getIndex() == cs.getIndex())) {
                        newCrit.setArmored(cs.isArmored());
                    }
                }
            }
        } else {
            // actuators
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot newCrit = unit.getCritical(location, slot);

                if ((newCrit != null)
                        && (newCrit.getType() == CriticalSlot.TYPE_SYSTEM)
                        && (newCrit.getIndex() == cs.getIndex())) {
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
            if (misc.hasFlag(MiscType.F_SPIKES)
                    || misc.hasFlag(MiscType.F_REACTIVE)
                    || misc.hasFlag(MiscType.F_MODULAR_ARMOR)
                    || misc.isShield()) {
                return false;
            }
        }

        return true;
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

    public static void updateLoadedMech(Entity unit) {

        if (unit instanceof Mech) {
            UnitUtil.updateLoadedMech((Mech) unit);
        }
    }

    public static void updateLoadedMech(Mech unit) {
        UnitUtil.removeOneShotAmmo(unit);
        UnitUtil.removeClanCase(unit);
        UnitUtil.expandUnitMounts(unit);
        UnitUtil.checkArmor(unit);
    }

    public static boolean isUnitWeapon(EquipmentType eq, Entity unit) {
        if (unit instanceof Tank) {
            return UnitUtil.isTankWeapon(eq, unit);
        }

        if (unit instanceof BattleArmor) {
            return UnitUtil.isBattleArmorWeapon(eq, unit);
        }

        if (unit instanceof Infantry) {
            return UnitUtil.isInfantryEquipment(eq, unit);
        }

        return UnitUtil.isMechWeapon(eq, unit);
    }

    public static boolean isUnitEquipment(EquipmentType eq, Entity unit) {
        if (unit instanceof Tank) {
            return UnitUtil.isTankEquipment(eq);
        }

        if (unit instanceof BattleArmor) {
            return UnitUtil.isBattleArmorEquipment(eq);
        }

        return UnitUtil.isMechEquipment(eq, (Mech) unit);
    }

    public static boolean isMechWeapon(EquipmentType eq, Entity unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq) || UnitUtil.isArmorOrStructure(eq)
                || UnitUtil.isJumpJet(eq)
                || UnitUtil.isMechEquipment(eq, (Mech) unit)) {
            return false;
        }

        if (eq instanceof AmmoType) {
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

            if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon))
                    && (weapon.getRackSize() != 5)
                    && (weapon.getRackSize() != 10)
                    && (weapon.getRackSize() != 15)
                    && (weapon.getRackSize() != 20)) {
                return false;
            }
            if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon))
                    && (weapon.getRackSize() != 2)
                    && (weapon.getRackSize() != 4)
                    && (weapon.getRackSize() != 6)) {
                return false;
            }
            if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY)
                    || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                            .getAmmoType() == AmmoType.T_PLASMA))) {

                if (weapon.hasFlag(WeaponType.F_ENERGY)
                        && weapon.hasFlag(WeaponType.F_PLASMA)
                        && (weapon.getAmmoType() == AmmoType.T_NA)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAeroWeapon(EquipmentType eq, Aero unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq) || UnitUtil.isArmorOrStructure(eq)
                || UnitUtil.isJumpJet(eq)
                || UnitUtil.isAeroEquipment(eq, unit)) {
            return false;
        }

        if (eq instanceof AmmoType) {
            return false;
        }

        if (eq instanceof WeaponType) {

            WeaponType weapon = (WeaponType) eq;

            if (!weapon.hasFlag(WeaponType.F_AERO_WEAPON)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon))
                    && (weapon.getRackSize() != 5)
                    && (weapon.getRackSize() != 10)
                    && (weapon.getRackSize() != 15)
                    && (weapon.getRackSize() != 20)) {
                return false;
            }
            if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon))
                    && (weapon.getRackSize() != 2)
                    && (weapon.getRackSize() != 4)
                    && (weapon.getRackSize() != 6)) {
                return false;
            }
            if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY)
                    || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                            .getAmmoType() == AmmoType.T_PLASMA))) {

                if (weapon.hasFlag(WeaponType.F_ENERGY)
                        && weapon.hasFlag(WeaponType.F_PLASMA)
                        && (weapon.getAmmoType() == AmmoType.T_NA)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAeroEquipment(EquipmentType eq, Aero unit) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof CLTAG) || (eq instanceof ISC3MBS)
                || (eq instanceof ISC3M) || (eq instanceof ISTAG)
                || eq.equals(EquipmentType.get("IS Coolant Pod"))
                || eq.equals(EquipmentType.get("Clan Coolant Pod"))
                || (eq instanceof CLLightTAG)) {
            return true;
        }

        if ((eq instanceof MiscType)) {
            if (eq.hasFlag(MiscType.F_QUAD_TURRET)) {
                return false;
            }

            if ((eq.hasFlag(MiscType.F_SHOULDER_TURRET))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_AERO_EQUIPMENT)
                    && !eq.hasFlag(MiscType.F_CLUB)
                    && !eq.hasFlag(MiscType.F_HAND_WEAPON)
                    && !eq.hasFlag(MiscType.F_TALON)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMechEquipment(EquipmentType eq, Mech unit) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof CLTAG) || (eq instanceof ISC3MBS)
                || (eq instanceof ISC3M) || (eq instanceof ISTAG)
                || eq.equals(EquipmentType.get("IS Coolant Pod"))
                || eq.equals(EquipmentType.get("Clan Coolant Pod"))
                || (eq instanceof CLLightTAG)) {
            return true;
        }

        if ((eq instanceof MiscType)) {
            if (eq.hasFlag(MiscType.F_BOMB_BAY) && !(unit instanceof LandAirMech)) {
                return false;
            }
            if (eq.hasFlag(MiscType.F_QUAD_TURRET)
                    && !(unit instanceof QuadMech)) {
                return false;
            }

            if (!(unit instanceof BipedMech)
                    && (eq.hasFlag(MiscType.F_SHOULDER_TURRET))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_MECH_EQUIPMENT)
                    && !eq.hasFlag(MiscType.F_CLUB)
                    && !eq.hasFlag(MiscType.F_HAND_WEAPON)
                    && !eq.hasFlag(MiscType.F_TALON)) {
                return true;
            }

            if (eq.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isTankWeapon(EquipmentType eq, Entity unit) {
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

            if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon))
                    && (weapon.getRackSize() != 5)
                    && (weapon.getRackSize() != 10)
                    && (weapon.getRackSize() != 15)
                    && (weapon.getRackSize() != 20)) {
                return false;
            }
            if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon))
                    && (weapon.getRackSize() != 2)
                    && (weapon.getRackSize() != 4)
                    && (weapon.getRackSize() != 6)) {
                return false;
            }
            if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY)
                    || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                            .getAmmoType() == AmmoType.T_PLASMA))) {

                if (weapon.hasFlag(WeaponType.F_ENERGY)
                        && weapon.hasFlag(WeaponType.F_PLASMA)
                        && (weapon.getAmmoType() == AmmoType.T_NA)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static boolean isBattleArmorAPWeapon(EquipmentType etype){
        InfantryWeapon infWeap = null;
        if ((etype == null) || !(etype instanceof InfantryWeapon)){
            return false;
        } else {
            infWeap = (InfantryWeapon)etype;
        }
        return  infWeap.hasFlag(WeaponType.F_INFANTRY)
                && !infWeap.hasFlag(WeaponType.F_INF_POINT_BLANK)
                && !infWeap.hasFlag(WeaponType.F_INF_ARCHAIC)
                && !infWeap.hasFlag(WeaponType.F_INF_SUPPORT)
                && (infWeap.getCrew() < 2);
    }

    public static boolean isBattleArmorWeapon(EquipmentType eq, Entity unit) {

        if (eq instanceof WeaponType) {

            WeaponType weapon = (WeaponType) eq;

            if (!weapon.hasFlag(WeaponType.F_BA_WEAPON)
                    && !weapon.hasFlag(WeaponType.F_INFANTRY)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0
                    && !weapon.hasFlag(WeaponType.F_INFANTRY)) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if ((eq instanceof SwarmAttack) || (eq instanceof StopSwarmAttack)
                    || (eq instanceof LegAttack)) {
                return false;
            }

            if (weapon.hasFlag(WeaponType.F_ENERGY)
                    || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                            .getAmmoType() == AmmoType.T_PLASMA))) {
            	return true;
            }
            	
            if (weapon.hasFlag(WeaponType.F_ENERGY) && (weapon.hasFlag(WeaponType.F_PLASMA))
            		&& (weapon.hasFlag(WeaponType.F_BA_WEAPON)))  {
            	return true;
            }
                        
            if (weapon.hasFlag(WeaponType.F_ENERGY)
                        && weapon.hasFlag(WeaponType.F_PLASMA)
                        && (weapon.getAmmoType() == AmmoType.T_NA))
                		{
                    return false;
                }
            
            return true;
        }
		
        return false;
    }

    public static boolean isInfantryEquipment(EquipmentType eq, Entity unit) {

        // TODO: adjust for field guns and artillery
        return eq instanceof InfantryWeapon;
    }

    public static boolean isTankEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof CLTAG) || (eq instanceof ISC3MBS)
                || (eq instanceof ISTAG) || (eq instanceof CLLightTAG)) {
            return true;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_TANK_EQUIPMENT)) {
            return true;

        }

        return false;
    }

    public static boolean isBattleArmorEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof CLBALightTAG) || (eq instanceof ISBALightTAG)) {
            return true;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_BA_EQUIPMENT)) {
            return true;

        }

        return false;
    }

    public static boolean canSwarm(BattleArmor ba) {

        for (Mounted eq : ba.getEquipment()) {
            if ((eq.getType() instanceof SwarmAttack)
                    || (eq.getType() instanceof StopSwarmAttack)) {
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
                m = cs.getMount();
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
                m = cs.getMount();
            }

            EquipmentType type = m.getType();
            if ((type instanceof MiscType) && ((MiscType) type).isShield()) {
                return m.getBaseDamageCapacity();
            }
        }

        return 0;
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
    public static void removeISorArmorCrits(Entity unit,
            boolean internalStructure) {
        ArrayList<String> mountList = new ArrayList<String>();
        if (internalStructure) {
            for (String struc : EquipmentType.structureNames) {
                mountList.add("IS " + struc);
                mountList.add("Clan " + struc);
            }
        } else {
            for (String armor : EquipmentType.armorNames) {
                mountList.add("IS " + armor);
                mountList.add("Clan " + armor);
            }
        }

        for (int location = Mech.LOC_HEAD; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null)
                        && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = crit.getMount();

                    if ((mount != null)
                            && (mount.getType() instanceof MiscType)
                            && mountList.contains(mount.getType()
                                    .getInternalName())) {
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
    public static void removeISorArmorMounts(Entity unit,
            boolean internalStructure) {
        UnitUtil.removeISorArmorCrits(unit, internalStructure);
        ArrayList<String> mountList = new ArrayList<String>();

        List<String> names;
        if (internalStructure) {
            names = Arrays.asList(EquipmentType.structureNames);
        } else {
            names = Arrays.asList(EquipmentType.armorNames);
        }
        for (String name : names) {
            mountList.add(String.format("Clan %1s", name));
            mountList.add(String.format("IS %1s", name));
        }

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType)
                    && mountList.contains(mount.getType().getInternalName())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }

        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if ((mount.getType() instanceof MiscType)
                    && mountList.contains(mount.getType().getInternalName())) {
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

        // Check all the mechs locations to see if any armor is greater than can
        // be in there.
        for (int location = 0; location < mech.locations(); location++) {
            // Head armor has a max of 9
            if (location == Mech.LOC_HEAD) {
                int armor = mech.getArmor(location);

                if (armor > 9) {
                    foundError = true;
                    mech.initializeArmor(9, location);
                }
            } else {
                int armor = mech.getArmor(location);
                if (mech.hasRearArmor(location)) {
                    armor += mech.getArmor(location, true);
                }
                int totalArmor = mech.getInternal(location) * 2;
                // Armor on the location is greater than what can be there.
                if (armor > totalArmor) {
                    foundError = true;
                    int armorOverage = armor - totalArmor;

                    // check for locations with rear armor first and remove the
                    // extra armor from the rear first.
                    if (mech.hasRearArmor(location)) {
                        int rearArmor = mech.getArmor(location, true);
                        if (rearArmor >= armorOverage) {
                            mech.initializeRearArmor(rearArmor - armorOverage,
                                    location);
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
            JOptionPane
                    .showMessageDialog(
                            null,
                            "Too much armor found on this unit.\n\rMegaMekLab has automatically corrected the problem.\n\rIt is suggested you check the armor allocation.",
                            "Too much armor", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static boolean hasManipulator(BattleArmor ba) {

        for (Mounted mount : ba.getMisc()) {
            MiscType eq = (MiscType) mount.getType();

            if (eq.hasFlag(MiscType.F_BA_EQUIPMENT)
                    && (eq.hasFlag(MiscType.F_ARMORED_GLOVE)
                            || eq.hasFlag(MiscType.F_BASIC_MANIPULATOR) || eq
                                .hasFlag(MiscType.F_BATTLE_CLAW))) {
                return true;
            }
        }

        return false;
    }

    public static boolean isManipulator(Mounted mount) {

        if (!(mount.getType() instanceof MiscType)) {
            return false;
        }

        MiscType eq = (MiscType) mount.getType();

        if (eq.hasFlag(MiscType.F_BA_EQUIPMENT)
                && (eq.hasFlag(MiscType.F_ARMORED_GLOVE)
                        || eq.hasFlag(MiscType.F_BASIC_MANIPULATOR)
                        || eq.hasFlag(MiscType.F_BATTLE_CLAW) || eq
                            .hasFlag(MiscType.F_CARGOLIFTER))) {
            return true;
        }

        return false;
    }

    public static int getNumberOfEquipmentLikeThis(Entity unit,
            EquipmentType baseEQ) {
        int numberOfEq = 0;

        for (Mounted mount : unit.getEquipment()) {
            if (mount.getType().equals(baseEQ)) {
                numberOfEq++;
            }
        }

        return numberOfEq;
    }

    /**
     * check that the unit is vaild
     *
     * @param unit
     * @return
     */
    public static String validateUnit(Entity unit) {

        EntityVerifier entityVerifier = new EntityVerifier(new File(
                "data/mechfiles/UnitVerifierOptions.xml"));
        StringBuffer sb = new StringBuffer();
        TestEntity testEntity = null;

        if (unit instanceof Mech) {
            testEntity = new TestMech((Mech) unit, entityVerifier.mechOption,
                    null);
        } else if (unit instanceof Tank) {
            testEntity = new TestTank((Tank) unit, entityVerifier.tankOption,
                    null);
        } else if ((unit.getEntityType() == Entity.ETYPE_AERO)
                && (unit.getEntityType() !=
                Entity.ETYPE_DROPSHIP)
                && (unit.getEntityType() !=
                        Entity.ETYPE_SMALL_CRAFT)
                && (unit.getEntityType() !=
                        Entity.ETYPE_FIGHTER_SQUADRON)
                && (unit.getEntityType() !=
                        Entity.ETYPE_JUMPSHIP)
                && (unit.getEntityType() !=
                        Entity.ETYPE_SPACE_STATION)) {
            testEntity =
                    new TestAero((Aero)unit,entityVerifier.aeroOption,null);
        } else if (unit instanceof BattleArmor){
            testEntity = new TestBattleArmor((BattleArmor) unit, 
                    entityVerifier.baOption, null);
        }
        testEntity.correctEntity(sb, true);

        return sb.toString();

    }

    public static void removeAllMiscMounteds(Entity unit, BigInteger flag) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType)
                    && ((MiscType) mount.getType()).hasFlag(flag)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static void removeAllMounteds(Entity unit, EquipmentType et) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted mount = unit.getEquipment().get(pos);
            if (mount.getType().equals(et)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static void removeTC(Entity unit) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType)
                    && ((MiscType) mount.getType())
                            .hasFlag(MiscType.F_TARGCOMP)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static boolean isValidLocation(Entity unit, EquipmentType eq,
            int location) {
        if ((eq instanceof MiscType)) {
            if (((eq.hasFlag(MiscType.F_CLUB) || eq
                    .hasFlag(MiscType.F_HAND_WEAPON)))) {
                if ((unit instanceof QuadMech)
                        && !((QuadMech) unit).isIndustrial()) {
                    return false;
                }

                if ((location != Mech.LOC_RARM) && (location != Mech.LOC_LARM)) {
                    if ((unit instanceof QuadMech)
                            && ((QuadMech) unit).isIndustrial()) {
                        if ((location != Mech.LOC_LT)
                                && (location != Mech.LOC_RT)) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (eq.hasFlag(MiscType.F_ACTUATOR_ENHANCEMENT_SYSTEM)) {
                if ((location != Mech.LOC_RARM) && (location != Mech.LOC_LARM)
                        && (location != Mech.LOC_LLEG)
                        && (location != Mech.LOC_RLEG)) {
                    return false;
                }
            }

            if (eq.hasFlag(MiscType.F_HEAD_TURRET)
                    && ((location != Mech.LOC_CT)
                    || ((unit instanceof Mech) && (((Mech) unit)
                            .getCockpitType() != Mech.COCKPIT_TORSO_MOUNTED)))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_QUAD_TURRET)
                    && (!(unit instanceof QuadMech) || ((location != Mech.LOC_RT) && (location != Mech.LOC_LT)))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_SHOULDER_TURRET)
                    && (!(unit instanceof BipedMech) || ((location != Mech.LOC_RT) && (location != Mech.LOC_LT)))) {
                return false;
            }

        } else if (eq instanceof WeaponType) {
            if (eq.hasFlag(WeaponType.F_VGL)) {
                if ((unit instanceof Mech)
                        && ((location != Mech.LOC_CT)
                                && (location != Mech.LOC_RT) && (location != Mech.LOC_LT))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void showValidation(Entity entity, JFrame frame) {
        String sb = UnitUtil.validateUnit(entity);

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(frame, sb, "Unit Validation",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Validation Passed",
                    "Unit Validation", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void showUnitSpecs(Entity unit, JFrame frame) {
        HTMLEditorKit kit = new HTMLEditorKit();

        MechView mechView = null;
        try {
            mechView = new MechView(unit, true);
        } catch (Exception e) {
            // error unit didn't load right. this is bad news.
        }

        StringBuffer unitSpecs = new StringBuffer("<html><body>");
        unitSpecs.append(mechView.getMechReadoutBasic());
        unitSpecs.append(mechView.getMechReadoutLoadout());
        unitSpecs.append("</body></html>");

        // System.err.println(unitSpecs.toString());
        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(unitSpecs.toString());

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);
        /*
         * if (unit instanceof Mech) { EntityVerifier entityVerifier = new
         * EntityVerifier(new File("data/mechfiles/UnitVerifierOptions.xml"));
         * //$NON-NLS-1$ TestMech test = new TestMech((Mech)unit,
         * entityVerifier.mechOption, null); JEditorPane pane2 = new
         * JEditorPane();
         * pane2.setText(test.printWeightCalculation().toString());
         * jdialog.add(pane2); }
         */
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2,
                CConfig.getIntParam("WINDOWHEIGHT"));

        jdialog.setPreferredSize(size);
        jdialog.setMinimumSize(size);
        scroll.setPreferredSize(size);
        scroll.setMinimumSize(size);
        // text.setPreferredSize(size);

        jdialog.setLocationRelativeTo(frame);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ex) {
        }

    }

    public static void showUnitCostBreakDown(Entity unit, JFrame frame) {
        HTMLEditorKit kit = new HTMLEditorKit();
        unit.calculateBattleValue(true, true);

        unit.getCost(true);

        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(unit.getBVText());

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2,
                CConfig.getIntParam("WINDOWHEIGHT"));

        jdialog.setPreferredSize(size);
        jdialog.setMinimumSize(size);
        scroll.setPreferredSize(size);
        scroll.setMinimumSize(size);

        jdialog.setLocationRelativeTo(frame);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ex) {
        }
    }

    public static void showBVCalculations(String bvText, JFrame frame) {
        HTMLEditorKit kit = new HTMLEditorKit();

        JEditorPane textPane = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane();

        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret());
        textPane.setEditorKit(kit);

        scroll.setViewportView(textPane);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        textPane.setText(bvText);

        scroll.setVisible(true);

        JDialog jdialog = new JDialog();

        jdialog.add(scroll);
        Dimension size = new Dimension(
                (int) (CConfig.getIntParam("WINDOWWIDTH") / 1.5),
                CConfig.getIntParam("WINDOWHEIGHT"));

        jdialog.setPreferredSize(size);
        jdialog.setMinimumSize(size);
        scroll.setPreferredSize(size);
        scroll.setMinimumSize(size);
        // text.setPreferredSize(size);

        jdialog.setLocationRelativeTo(frame);
        jdialog.setVisible(true);

        try {
            textPane.setSelectionStart(0);
            textPane.setSelectionEnd(0);
        } catch (Exception ex) {
        }

    }

    public static boolean hasBAR(Entity unit) {

        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit.hasBARArmor(loc)) {
                return true;
            }
        }

        return false;
    }

    public static int getLowestBARRating(Entity unit) {
        int bar = 10;

        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit.getBARRating(loc) < bar) {
                bar = unit.getBARRating(loc);
            }
        }
        return bar;
    }

    public static String getArmorString(Mech mech, int loc) {
        if (!mech.hasPatchworkArmor()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        switch (mech.getArmorType(loc)) {
            case EquipmentType.T_ARMOR_REFLECTIVE:
                sb.append("LR");
                break;
            case EquipmentType.T_ARMOR_HARDENED:
                sb.append("HD");
                break;
            case EquipmentType.T_ARMOR_LIGHT_FERRO:
                sb.append("LF");
                break;
            case EquipmentType.T_ARMOR_HEAVY_FERRO:
                sb.append("HF");
                break;
            case EquipmentType.T_ARMOR_FERRO_FIBROUS:
            case EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO:
                sb.append("FF");
                break;
            case EquipmentType.T_ARMOR_STEALTH:
                sb.append("SA");
                break;
            case EquipmentType.T_ARMOR_INDUSTRIAL:
                sb.append("IN");
                break;
            case EquipmentType.T_ARMOR_COMMERCIAL:
                sb.append("CO");
                break;
            case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                sb.append("FL");
                break;
            case EquipmentType.T_ARMOR_REACTIVE:
                sb.append("RE");
                break;
            default:
                return "";
        }
        if (mech.hasBARArmor(loc)) {
            sb.append(" B" + mech.getBARRating(loc));
        }
        return sb.toString();
    }

    public static boolean canUseAmmo(Entity unit, AmmoType atype) {
        boolean match = false;
        if ((unit instanceof BattleArmor) 
                && !atype.hasFlag(AmmoType.F_BATTLEARMOR)){
            return false;
        }
        for (Mounted m : unit.getWeaponList()) {
            if (m.getType() instanceof AmmoWeapon) {
                WeaponType wtype = (WeaponType) m.getType();
                if ((wtype.getAmmoType() == atype.getAmmoType())
                        && (wtype.getRackSize() == atype.getRackSize())) {
                    match = true;
                }
            }
        }
        return match;
    }

    public static int countUsedCriticals(Mech unit) {
        int nCrits = 0;
        for (int i = 0; i < unit.locations(); i++) {
            for (int j = 0; j < unit.getNumberOfCriticals(i); j++) {
                CriticalSlot cs = unit.getCritical(i, j);
                if (null != cs) {
                    nCrits++;
                }
            }
        }
        return nCrits + countUnallocatedCriticals(unit);
    }

    public static int countUnallocatedCriticals(Mech unit) {
        int nCrits = 0;
        int engineHeatSinkCount = UnitUtil.getBaseChassisHeatSinks(unit,
                unit.hasCompactHeatSinks());
        for (Mounted mount : unit.getMisc()) {
            if (UnitUtil.isHeatSink(mount)
                    && (mount.getLocation() == Entity.LOC_NONE)) {
                if (engineHeatSinkCount > 0) {
                    engineHeatSinkCount--;
                    continue;
                }
            }
            if ((mount.getLocation() == Entity.LOC_NONE)) {
                nCrits += UnitUtil.getCritsUsed(unit, mount.getType());
            }
        }
        for (Mounted mount : unit.getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                nCrits += UnitUtil.getCritsUsed(unit, mount.getType());
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE)
                    && ((mount.getUsableShotsLeft() > 1) || (((AmmoType) mount
                            .getType()).getAmmoType() == AmmoType.T_COOLANT_POD))) {
                nCrits += UnitUtil.getCritsUsed(unit, mount.getType());
            }
        }
        return nCrits;
    }

    // gives total number of sinks, not just critical slots
    public static int countActualHeatSinks(Mech unit) {
        int sinks = 0;
        for (Mounted mounted : unit.getMisc()) {
            if (!UnitUtil.isHeatSink(mounted)) {
                continue;
            }
            if (mounted.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)) {
                if (mounted.getType().hasFlag(MiscType.F_HEAT_SINK)) {
                    sinks++;
                } else if (mounted.getType().hasFlag(
                        MiscType.F_DOUBLE_HEAT_SINK)) {
                    sinks++;
                    sinks++;
                }
            } else {
                sinks++;
            }
        }
        return sinks;
    }

    public static void checkEquipmentByTechLevel(Entity unit) {
        Vector<Mounted> toRemove = new Vector<Mounted>();
        for (Mounted m : unit.getEquipment()) {
            EquipmentType etype = m.getType();
            if (UnitUtil.isArmorOrStructure(etype)
                    || UnitUtil.isHeatSink(etype) || UnitUtil.isJumpJet(etype)) {
                continue;
            }
            if (etype.hasFlag(MiscType.F_TSM)
                    || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || etype.hasFlag(MiscType.F_MASC)) {
                continue;
            }
            if (!UnitUtil.isLegal(unit,
                    etype.getTechLevel(unit.getTechLevelYear()))) {
                toRemove.add(m);
            }
        }
        for (Mounted m : toRemove) {
            UnitUtil.removeMounted(unit, m);
        }
        if (unit instanceof Infantry) {
            Infantry pbi = (Infantry) unit;
            if ((null != pbi.getPrimaryWeapon())
                    && !UnitUtil.isLegal(unit, pbi.getPrimaryWeapon()
                            .getTechLevel(pbi.getTechLevelYear()))) {
                UnitUtil.replaceMainWeapon((Infantry) unit,
                        (InfantryWeapon) EquipmentType
                                .get("Infantry Auto Rifle"), false);
            }
            if ((null != pbi.getSecondaryWeapon())
                    && !UnitUtil.isLegal(unit, pbi.getSecondaryWeapon()
                            .getTechLevel(pbi.getTechLevelYear()))) {
                UnitUtil.replaceMainWeapon((Infantry) unit, null, true);
            }
        }
    }

    public static void replaceMainWeapon(Infantry unit, InfantryWeapon weapon,
            boolean secondary) {
        Mounted existingInfantryMount = null;
        for (Mounted m : unit.getWeaponList()) {
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
        // to the unit
        // otherwise add the primary weapon
        if ((unit.getSecondaryN() < 2) || (null == unit.getSecondaryWeapon())) {
            try {
                unit.addEquipment(unit.getPrimaryWeapon(),
                        Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

            }
        } else {
            try {
                unit.addEquipment(unit.getSecondaryWeapon(),
                        Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

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
        unit.setDamageDivisor(1.0);
    }

    public static void removeOmniArmActuators(Mech mech) {
        if (mech instanceof BipedMech) {
            boolean leftACGaussPPC = false;
            boolean rightACGaussPPC = false;
            for (Mounted weapon : mech.getWeaponList()) {
                if ((weapon.getLocation() == Mech.LOC_LARM)
                        && ((weapon.getType() instanceof ACWeapon)
                                || (weapon.getType() instanceof GaussWeapon)
                                || (weapon.getType() instanceof LBXACWeapon)
                                || (weapon.getType() instanceof UACWeapon) || (weapon
                                    .getType() instanceof PPCWeapon))) {
                    leftACGaussPPC = true;
                }
                if ((weapon.getLocation() == Mech.LOC_RARM)
                        && ((weapon.getType() instanceof ACWeapon)
                                || (weapon.getType() instanceof GaussWeapon)
                                || (weapon.getType() instanceof LBXACWeapon)
                                || (weapon.getType() instanceof UACWeapon) || (weapon
                                    .getType() instanceof PPCWeapon))) {
                    rightACGaussPPC = true;
                }
            }
            if (leftACGaussPPC) {
                removeArm((BipedMech) mech, Mech.LOC_LARM);
                UnitUtil.compactCriticals(mech, Mech.LOC_LARM);
            }
            if (rightACGaussPPC) {
                removeArm((BipedMech) mech, Mech.LOC_RARM);
                UnitUtil.compactCriticals(mech, Mech.LOC_RARM);
            }
        }

    }

    public static void removeHand(BipedMech mech, int location) {
        if (mech.hasSystem(Mech.ACTUATOR_HAND, location)) {
            mech.setCritical(location, 3, null);
        }
    }

    public static void removeArm(BipedMech mech, int location) {
        if (mech.hasSystem(Mech.ACTUATOR_LOWER_ARM, location)) {
            mech.setCritical(location, 2, null);
            // Only remove the next slot of it actually is a hand
            if (mech.hasSystem(Mech.ACTUATOR_HAND, location)) {
                removeHand(mech, location);
            }
        }

    }
}
