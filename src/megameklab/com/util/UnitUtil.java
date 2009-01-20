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

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.WeaponType;
import megamek.common.weapons.CLAMS;
import megamek.common.weapons.CLLaserAMS;
import megamek.common.weapons.EnergyWeapon;
import megamek.common.weapons.GaussWeapon;
import megamek.common.weapons.HAGWeapon;
import megamek.common.weapons.HVACWeapon;
import megamek.common.weapons.ISAMS;
import megamek.common.weapons.ISLaserAMS;
import megamek.common.weapons.MGWeapon;
import megamek.common.weapons.ThunderBoltWeapon;
import megamek.common.weapons.UACWeapon;
import megamek.common.weapons.Weapon;

public class UnitUtil {

    public static String TARGETINGCOMPUTER = "Targeting Computer";
    public static String ISTARGETINGCOMPUTER = "ISTargeting Computer";
    public static String CLTARGETINGCOMPUTER = "CLTargeting Computer";
    public static String TSM = "TSM";
    public static String INDUSTRIALTSM = "Industrial TSM";
    public static String ENVIROSEAL = "Environmental Sealing";

    private static Font euroFont = null;
    private static Font euroBoldFont = null;

    /**
     * tells is EquipementType is armor or Structure that uses crits/mounted
     *
     * @param eq
     * @return
     */
    public static boolean isArmorOrStructure(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_ENDO_STEEL) || eq.hasFlag(MiscType.F_FERRO_FIBROUS) || eq.hasFlag(MiscType.F_NULLSIG) || eq.hasFlag(MiscType.F_STEALTH) || eq.hasFlag(MiscType.F_VOIDSIG) || eq.hasFlag(MiscType.F_CHAMELEON_SHIELD) || eq.hasFlag(MiscType.F_REACTIVE) || eq.hasFlag(MiscType.F_REFLECTIVE));
    }

    /**
     * tells if EquipmentType is TSM or TargetComp
     *
     * @param eq
     * @return
     */
    public static boolean isTSM(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_TSM));
    }

    /**
     * Returns the number of crits used by EquipmentType eq, 1 if armor or structure EquipmentType
     *
     * @param unit
     * @param eq
     * @return
     */
    public static int getCritsUsed(Mech unit, EquipmentType eq) {
        if (UnitUtil.isArmorOrStructure(eq) || UnitUtil.isTSM(eq)) {
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

        if (equipment.getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeCrits(unit, UnitUtil.TSM);
        } else if (equipment.getName().equals(UnitUtil.INDUSTRIALTSM)) {
            UnitUtil.removeCrits(unit, UnitUtil.INDUSTRIALTSM);
        } else {
            UnitUtil.removeCriticals(unit, equipment);
        }

        if (equipment.getName().equals(UnitUtil.TSM)) {
            UnitUtil.removeMounts(unit, UnitUtil.TSM);
        } else if (equipment.getName().equals(UnitUtil.TARGETINGCOMPUTER)) {
            UnitUtil.removeMounts(unit, UnitUtil.TARGETINGCOMPUTER);
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
     * Removes mounts of a certain type from the Mek.
     *
     * @param Unit
     */
    public static void removeMounts(Mech unit, String mountName) {

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (mount.getType().getName().equals(mountName)) {
                unit.getEquipment().remove(pos);
                unit.getMisc().remove(mount);
            } else {
                pos++;
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
                    Mounted mount = unit.getEquipment(crit.getIndex());

                    if ((mount != null) && mount.getType().getName().equals(critType)) {
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
        /*
         * if (eq.getType().getName().equals(UnitUtil.TSM)) { UnitUtil.removeTSMCrits(unit); } else
         */
        if (eq.getType().getName().equals(UnitUtil.TARGETINGCOMPUTER)) {
            UnitUtil.removeCrits(unit, UnitUtil.TARGETINGCOMPUTER);
        } else if (eq.isSplitable() || eq.getType().isSpreadable()) {
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
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getIndex() == unit.getEquipmentNum(eq))) {
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
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getIndex() == unit.getEquipmentNum(eq))) {
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
     * Updates TC Crits and Mounts based on weapons on a unit or if the TC has been removed.
     *
     * @param unit
     */
    public static void updateTC(Mech unit) {

        UnitUtil.removeCrits(unit, UnitUtil.TARGETINGCOMPUTER);
        UnitUtil.removeMounts(unit, UnitUtil.TARGETINGCOMPUTER);
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

        // for (; TCCount > 0; TCCount--) {
        try {
            unit.addEquipment(new Mounted(unit, EquipmentType.get(TargetingComputerType)), Entity.LOC_NONE, false);
        } catch (Exception ex) {

        }
        // }
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
        int heatSinks = hsAmount - unit.getEngine().integralHeatSinkCapacity();
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

        unit.addEngineSinks(hsAmount, UnitUtil.getHeatSinkType(hsType, unit.isClan()));

        UnitUtil.addHeatSinkMounts(unit, hsAmount, hsType);
    }

    /**
     * simple method to let us know if eq should be printed on the weapons and equipment section of the Record sheet.
     *
     * @param eq
     * @return
     */
    public static boolean isPrintableEquipment(EquipmentType eq) {

        if (!eq.isHittable()) {
            return false;
        }

        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_MASC)) {
            return false;
        }

        if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_JUMP_JET) || eq.hasFlag(MiscType.F_JUMP_BOOSTER))) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq)) {
            return false;
        }

        return true;

    }

    public static void changeMountStatus(Entity unit, Mounted eq, int location, int secondaryLocation, boolean rear) {
        if (eq.getType() instanceof Weapon) {
            for (Mounted mount : unit.getWeaponList()) {
                if (mount == eq) {
                    mount.setLocation(location, rear);
                    mount.setSecondLocation(secondaryLocation, rear);
                    mount.setSplit(secondaryLocation > -1);
                    break;
                }
            }
        } else if (eq.getType() instanceof AmmoType) {
            for (Mounted mount : unit.getAmmo()) {
                if (mount == eq) {
                    mount.setLocation(location);
                    mount.setSecondLocation(secondaryLocation);
                    mount.setSplit(secondaryLocation > -1);
                    break;
                }
            }
        } else {
            for (Mounted mount : unit.getMisc()) {
                if (mount == eq) {
                    mount.setLocation(location);
                    mount.setSecondLocation(secondaryLocation);
                    mount.setSplit(secondaryLocation > -1);
                    break;
                }
            }
        }

    }

    public static boolean hasTargComp(Entity unit) {

        for (Mounted mount : unit.getEquipment()) {
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TARGCOMP)) {
                return true;
            }
        }

        return false;
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

    public static double getTotalArmorTonnage(Mech unit) {

        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(), unit.getArmorTechLevel());
        if (unit.getArmorType() == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        double points = (unit.getTotalInternal() * 2) + 3;
        double armorWeight = points / armorPerTon;
        armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;

        return armorWeight;
    }

    public static int getArmorPoints(Mech unit, double armorTons) {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(unit.getArmorType(), unit.getArmorTechLevel());
        if (unit.getArmorType() == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        return (int)Math.floor(armorPerTon * armorTons);
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
            compactCriticals(mech, loc);
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

        return true;
    }

    public static void expandUnitMounts(Entity unit) {

        for (int location = 0; location <= Mech.LOC_LLEG; location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getMount() == null) {
                    Mounted mount = unit.getEquipment(cs.getIndex());

                    if (UnitUtil.isArmorOrStructure(mount.getType()) || UnitUtil.isTSM(mount.getType())) {
                        Mounted newMount = new Mounted(unit, mount.getType());
                        newMount.setLocation(location, mount.isRearMounted());
                        cs.setMount(newMount);
                        unit.getEquipment().add(newMount);
                        if (!UnitUtil.isArmorOrStructure(mount.getType())) {
                            unit.getMisc().add(newMount);
                        }
                        cs.setIndex(unit.getEquipmentNum(newMount));
                    } else {
                        cs.setMount(mount);
                    }
                }
            }
        }
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

        loadFonts();

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
            unit.getEquipment().remove(mount);
            unit.getAmmo().remove(mount);
        }
    }

    public static boolean hasAmmo(Entity unit, int location) {

        for (Mounted mount : unit.getEquipment()) {


            if (mount.getType().isExplosive() && (mount.getLocation() == location)) {
                return true;
            }
        }

        return false;
    }
}
