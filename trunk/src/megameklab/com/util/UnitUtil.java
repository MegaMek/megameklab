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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLEditorKit;

import megamek.client.ui.swing.MechView;
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
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMech;
import megamek.common.weapons.BPodWeapon;
import megamek.common.weapons.EnergyWeapon;
import megamek.common.weapons.GaussWeapon;
import megamek.common.weapons.HAGWeapon;
import megamek.common.weapons.HVACWeapon;
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

        boolean isMisc = eq instanceof MiscType;

        if (isMisc && eq.hasFlag(MiscType.F_PARTIAL_WING) && (eq.getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)) {
            return 3;
        }
        if (isMisc && eq.hasFlag(MiscType.F_PARTIAL_WING) && (eq.getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)) {
            return 4;
        }

        if (isMisc && (eq.hasFlag(MiscType.F_JUMP_BOOSTER) || eq.hasFlag(MiscType.F_TALON))) {
            return 2;
        }

        if (UnitUtil.isSpreadEquipment(eq) || UnitUtil.isTSM(eq) || UnitUtil.isArmorOrStructure(eq)) {
            return 1;
        }

        return eq.getCriticals(unit);
    }

    public static void removeMounted(Entity unit, Mounted mount) {
        UnitUtil.removeCriticals(unit, mount);
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
        for (int loc = 0; loc <= unit.locations(); loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = unit.getCritical(loc, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getMount().equals(eq))) {
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
        if ((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP)) {
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
            if (mount.getType().hasFlag(MiscType.F_TARGCOMP) && (mount.getType().getTechLevel() == TechConstants.T_CLAN_TW)) {
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
                if (mounted.getType().getInternalName().equals("CLDoubleHeatSink")) {
                    return true;
                }
                return false;
            }
        }

        return false;
    }

    /**
     * Checks for IS Double Heat Sinks
     *
     * @param unit
     * @return
     */
    public static boolean hasIsDoubleHeatSinks(Mech unit) {

        if (!unit.hasDoubleHeatSinks()) {
            return false;
        }

        for (Mounted mounted : unit.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return true;
            }
            if (mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                if (mounted.getType().getInternalName().equals("ISDoubleHeatSink")) {
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
     * Checks if EquipmentType is a Mech Physical weapon
     *
     * @param eq
     * @return
     */
    public static boolean isPhysicalWeapon(EquipmentType eq) {

        if ((eq instanceof MiscType) && ((eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON) || eq.hasFlag(MiscType.F_TALON)))) {
            if (eq.hasFlag(MiscType.F_CLUB) && ((eq.hasSubType(MiscType.S_CLUB) || eq.hasSubType(MiscType.S_TREE_CLUB)))) {
                return false;
            }
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
    public static void addHeatSinkMounts(Mech unit, int hsAmount, String hsType) {
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
        unit.resetSinks();
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

    /**
     * updates the heat sinks.
     *
     * @param unit
     * @param hsAmount
     * @param hsType
     */
    public static void updateHeatSinks(Mech unit, int hsAmount, String hsType) {

        UnitUtil.removeHeatSinks(unit);

        unit.addEngineSinks(UnitUtil.getHeatSinkType(hsType, unit.isClan()), Math.min(hsAmount, UnitUtil.getBaseChassisHeatSinks(unit)));

        UnitUtil.addHeatSinkMounts(unit, hsAmount, hsType);
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

        if (UnitUtil.isSpreadEquipment(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq) && isMech) {
            return false;
        }
        if (!eq.isHittable()) {
            return false;
        }

        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_ARTEMIS) || eq.hasFlag(MiscType.F_ARTEMIS_V) || eq.hasFlag(MiscType.F_APOLLO) || eq.hasFlag(MiscType.F_MASC) || eq.hasFlag(MiscType.F_HARJEL) || eq.hasFlag(MiscType.F_MASS) || eq.hasFlag(MiscType.F_CHASSIS_MODIFICATION) || eq.hasFlag(MiscType.F_MASH_EXTRA) || eq.hasFlag(MiscType.F_DRONE_EXTRA))) {
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

        if (UnitUtil.isSpreadEquipment(eq)) {
            return false;
        }

        if (UnitUtil.isJumpJet(eq)) {
            return false;
        }

        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_AP_MOUNT) || eq.hasFlag(MiscType.F_FIRE_RESISTANT) || eq.hasFlag(MiscType.F_STEALTH) || eq.hasFlag(MiscType.F_ARTEMIS) || eq.hasFlag(MiscType.F_ARTEMIS_V) || eq.hasFlag(MiscType.F_APOLLO) || eq.hasFlag(MiscType.F_HARJEL) || eq.hasFlag(MiscType.F_MASS))) {
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

    public static Mounted getTargComp(Entity unit) {
        for (Mounted misc : unit.getMisc()) {
            if (misc.getType().hasFlag(MiscType.F_TARGCOMP)) {
                return misc;
            }
        }
        return null;
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
            // don't add ammo with just one shot, that's OS ammo
            if ((mount.getLocation() == Entity.LOC_NONE) && (mount.getShotsLeft() > 1)) {
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

    public static double getMaximumArmorTonnage(Entity unit) {

        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(1), unit.getArmorTechLevel(1));
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
        return armorWeight;
    }

    /**
     * NOTE: only use for non-patchwork armor
     * @param unit
     * @param armorTons
     * @return
     */
    public static int getArmorPoints(Entity unit, double armorTons) {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(1), unit.getArmorTechLevel(1));
        if (unit.getArmorType(1) == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        return Math.min((int) Math.floor(armorPerTon * armorTons), UnitUtil.getMaximumArmorPoints(unit));
    }

    public static void reIndexCrits(Entity unit) {

        for (int location = 0; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);

                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    cs.setIndex(unit.getEquipmentNum(cs.getMount()));
                }
            }
        }
    }

    public static void compactCriticals(Entity unit) {
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit instanceof Mech) {
                UnitUtil.compactCriticals((Mech)unit, loc);
            } else {
                UnitUtil.compactCriticals(unit, loc);
            }
        }
    }

    private static void compactCriticals(Entity unit, int loc) {
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

                if (UnitUtil.isTSM(mount.getType()) || UnitUtil.isArmorOrStructure(mount.getType())) {
                    Mounted newMount = new Mounted(unit, mount.getType());
                    newMount.setLocation(location, mount.isRearMounted());
                    newMount.setArmored(mount.isArmored());
                    cs.setMount(newMount);
                    cs.setArmored(mount.isArmored());
                    unit.getEquipment().add(newMount);
                    unit.getMisc().add(newMount);
                    cs.setIndex(unit.getEquipmentNum(newMount));
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
            if ((equip.hasFlag(MiscType.F_INDUSTRIAL_TSM) || equip.hasFlag(MiscType.F_TSM))) {
                // all crits user placeable
                for (int i = 0; i < equip.getCriticals(unit); i++) {
                    locations.add(Entity.LOC_NONE);
                }
            } else if (equip.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)) {
                // 1 crit in each location
                for (int i = 0; i < unit.locations(); i++) {
                    locations.add(i);
                }
            } else if ((equip.hasFlag(MiscType.F_TRACKS) || equip.hasFlag(MiscType.F_TALON) || equip.hasFlag(MiscType.F_JUMP_BOOSTER))) {
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
            } else if ((equip.hasFlag(MiscType.F_VOIDSIG) || equip.hasFlag(MiscType.F_NULLSIG) || equip.hasFlag(MiscType.F_BLUE_SHIELD))) {
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
                        CriticalSlot cs = new CriticalSlot(CriticalSlot.TYPE_EQUIPMENT, unit.getEquipmentNum(mount), mount.getType().isHittable(), mount.isArmored(), mount);
                        cs.setIndex(unit.getEquipmentNum(mount));
                        unit.addCritical(locations.get(0), cs);
                    }
                } catch (LocationFullException lfe) {
                    JOptionPane.showMessageDialog(null, lfe.getMessage(), mount.getName() + " does not fit into " + unit.getLocationName(locations.get(0)), JOptionPane.INFORMATION_MESSAGE);
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

    public static boolean isClanEquipment(EquipmentType eq, Entity en) {
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
        if (unit.isMixedTech() && (eq.getTechLevel() != TechConstants.T_ALLOWED_ALL) && (eq.getTechLevel() != TechConstants.T_TECH_UNKNOWN)) {

            if (unit.isClan() && !UnitUtil.isClanEquipment(eq, unit)) {
                return eq.getName() + " (IS)";
            }

            if (!unit.isClan() && UnitUtil.isClanEquipment(eq, unit)) {
                return eq.getName() + " (Clan)";
            }
        }

        if ((eq instanceof WeaponType) && (eq.hasFlag(WeaponType.F_C3M) || eq.hasFlag(WeaponType.F_C3MBS))) {
            return eq.getName().substring(0, eq.getName().indexOf("with TAG") - 1);
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
        if (eq.getType() instanceof MiscType) {
            sb.append(((MiscType) eq.getType()).getTonnage(unit, eq.getLocation()));
        } else {
            sb.append(eq.getType().getTonnage(unit));
        }

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
        if (eq.isMechTurretMounted()) {
            sb.append("<br>Turret mounted");
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
            if (misc.hasFlag(MiscType.F_SPIKES) || misc.hasFlag(MiscType.F_REACTIVE) || misc.hasFlag(MiscType.F_MODULAR_ARMOR) || misc.isShield()) {
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

        if (UnitUtil.isHeatSink(eq) || UnitUtil.isArmorOrStructure(eq) || UnitUtil.isJumpJet(eq) || UnitUtil.isMechEquipment(eq, (Mech) unit)) {
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

    public static boolean isMechEquipment(EquipmentType eq, Mech unit) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (eq.equals(EquipmentType.get("CLTAG")) || eq.equals(EquipmentType.get("ISC3MasterBoostedSystemUnit")) || eq.equals(EquipmentType.get("ISC3MasterUnit")) || eq.equals(EquipmentType.get("ISTAG")) || eq.equals(EquipmentType.get("IS Coolant Pod")) || eq.equals(EquipmentType.get("Clan Coolant Pod")) || eq.equals(EquipmentType.get("CLLightTAG"))) {
            return true;
        }

        if ((eq instanceof MiscType)) {
            if (eq.hasFlag(MiscType.F_QUAD_TURRET) && !(unit instanceof QuadMech)) {
                return false;
            }

            if (!(unit instanceof BipedMech) && (eq.hasFlag(MiscType.F_SHOULDER_TURRET) || ((eq.hasFlag(MiscType.F_HEAD_TURRET))))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_MECH_EQUIPMENT) && !eq.hasFlag(MiscType.F_CLUB) && !eq.hasFlag(MiscType.F_HAND_WEAPON)) {
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

    public static boolean isBattleArmorWeapon(EquipmentType eq, Entity unit) {

        if (eq instanceof WeaponType) {

            WeaponType weapon = (WeaponType) eq;

            if (!weapon.hasFlag(WeaponType.F_BA_WEAPON)) {
                return false;
            }

            if (weapon.getTonnage(unit) <= 0) {
                return false;
            }

            if (weapon.isCapital() || weapon.isSubCapital()) {
                return false;
            }

            if ((eq instanceof SwarmAttack) || (eq instanceof StopSwarmAttack) || (eq instanceof LegAttack)) {
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

    public static boolean isBattleArmorEquipment(EquipmentType eq) {

        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if (eq.equals(EquipmentType.get("CLTAG")) || eq.equals(EquipmentType.get("ISC3MasterUnit")) || eq.equals(EquipmentType.get("ISTAG")) || eq.equals(EquipmentType.get("IS Coolant Pod")) || eq.equals(EquipmentType.get("Clan Coolant Pod")) || eq.equals(EquipmentType.get("CLLightTAG"))) {
            return true;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_BA_EQUIPMENT)) {
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
        ArrayList<String> mountList = new ArrayList<String>();
        if (internalStructure) {
            mountList.addAll(Arrays.asList(EquipmentType.structureNames));
            mountList.add("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL]);
            mountList.add("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE]);
        } else {
            mountList.addAll(Arrays.asList(EquipmentType.armorNames));
        }

        for (int location = Mech.LOC_HEAD; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot crit = unit.getCritical(location, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if ((mount != null) && (mount.getType() instanceof MiscType) && mountList.contains(mount.getType().getInternalName())) {
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
        UnitUtil.removeISorArmorCrits(unit, internalStructure);
        ArrayList<String> mountList = new ArrayList<String>();

        if (internalStructure) {
            mountList.addAll(Arrays.asList(EquipmentType.structureNames));
            mountList.add("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL]);
            mountList.add("Clan " + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE]);
        } else {
            mountList.addAll(Arrays.asList(EquipmentType.armorNames));
        }

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && mountList.contains(mount.getType().getInternalName())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }

        for (int pos = 0; pos < unit.getMisc().size();) {
            Mounted mount = unit.getMisc().get(pos);
            if ((mount.getType() instanceof MiscType) && mountList.contains(mount.getType().getInternalName())) {
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
                    mech.setArmor(9, location);
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

    public static boolean hasManipulator(BattleArmor ba) {

        for (Mounted mount : ba.getMisc()) {
            MiscType eq = (MiscType) mount.getType();

            if (eq.hasFlag(MiscType.F_BA_EQUIPMENT) && (eq.hasFlag(MiscType.F_ARMORED_GLOVE) || eq.hasFlag(MiscType.F_BASIC_MANIPULATOR) || eq.hasFlag(MiscType.F_BATTLE_CLAW))) {
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

        if (eq.hasFlag(MiscType.F_BA_EQUIPMENT) && (eq.hasFlag(MiscType.F_ARMORED_GLOVE) || eq.hasFlag(MiscType.F_BASIC_MANIPULATOR) || eq.hasFlag(MiscType.F_BATTLE_CLAW))) {
            return true;
        }

        return false;
    }

    public static int getNumberOfEquipmentLikeThis(Entity unit, EquipmentType baseEQ) {
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

        EntityVerifier entityVerifier = new EntityVerifier(new File("data/mechfiles/UnitVerifierOptions.xml"));
        StringBuffer sb = new StringBuffer();
        TestEntity testEntity = null;

        if (unit instanceof Mech) {
            testEntity = new TestMech((Mech) unit, entityVerifier.mechOption, null);

            testEntity.correctEntity(sb, true);
        }

        return sb.toString();

    }

    public static void removeAllMounteds(Entity unit, BigInteger flag) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && ((MiscType) mount.getType()).hasFlag(flag)) {
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
            if ((mount.getType() instanceof MiscType) && ((MiscType) mount.getType()).hasFlag(MiscType.F_TARGCOMP)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static boolean isValidLocation(Entity unit, EquipmentType eq, int location) {
        if ((eq instanceof MiscType)) {
            if (((eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON)))) {
                if (unit instanceof QuadMech) {
                    return false;
                }

                if ((location != Mech.LOC_RARM) && (location != Mech.LOC_LARM)) {
                    return false;
                }
            }

            if (eq.hasFlag(MiscType.F_ACTUATOR_ENHANCEMENT_SYSTEM)) {
                if ((location != Mech.LOC_RARM) && (location != Mech.LOC_LARM) && (location != Mech.LOC_LLEG) && (location != Mech.LOC_RLEG)) {
                    return false;
                }
            }

            if (eq.hasFlag(MiscType.F_HEAD_TURRET) && (!(unit instanceof BipedMech) || (location != Mech.LOC_CT) || ((unit instanceof BipedMech) && (((Mech) unit).getCockpitType() != Mech.COCKPIT_TORSO_MOUNTED)))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_QUAD_TURRET) && (!(unit instanceof QuadMech) || ((location != Mech.LOC_RT) && (location != Mech.LOC_LT)))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_SHOULDER_TURRET) && (!(unit instanceof BipedMech) || ((location != Mech.LOC_RT) && (location != Mech.LOC_LT)))) {
                return false;
            }

        } else if (eq instanceof WeaponType) {
            if (eq.hasFlag(WeaponType.F_VGL)) {
                if ((unit instanceof Mech) && ((location != Mech.LOC_CT) && (location != Mech.LOC_RT) && (location != Mech.LOC_LT))) {
                    return false;
                }
            }
        }

        return true;
    }

    static public Integer getMaxWeight(BattleArmor ba) {
        Integer weight = 400;

        switch (ba.getWeightClass()) {
            case 0:
                weight = 400;
                break;
            case 1:
                weight = 750;
                break;
            case 2:
            case 3:
            case 4:
                weight = ba.getWeightClass() * 500;
                break;

        }
        return weight;
    }

    public static void showValidation(Entity entity, JFrame frame) {
        String sb = UnitUtil.validateUnit(entity);

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(frame, sb, "Unit Validation", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Validation Passed", "Unit Validation", JOptionPane.INFORMATION_MESSAGE);
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
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2, CConfig.getIntParam("WINDOWHEIGHT"));

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
        Dimension size = new Dimension(CConfig.getIntParam("WINDOWWIDTH") / 2, CConfig.getIntParam("WINDOWHEIGHT"));

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
        Dimension size = new Dimension((int)(CConfig.getIntParam("WINDOWWIDTH") / 1.5), CConfig.getIntParam("WINDOWHEIGHT"));

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
            sb.append(" B"+mech.getBARRating(loc));
        }
        return sb.toString();
    }
}
