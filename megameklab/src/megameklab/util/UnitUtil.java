/*
 * Copyright (c) 2008-2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.util;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.verifier.*;
import megamek.common.verifier.TestEntity.Ceil;
import megamek.common.weapons.*;
import megamek.common.weapons.autocannons.HVACWeapon;
import megamek.common.weapons.autocannons.UACWeapon;
import megamek.common.weapons.bayweapons.BayWeapon;
import megamek.common.weapons.defensivepods.BPodWeapon;
import megamek.common.weapons.defensivepods.MPodWeapon;
import megamek.common.weapons.flamers.VehicleFlamerWeapon;
import megamek.common.weapons.gaussrifles.GaussWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lasers.CLChemicalLaserWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.lrms.LRTWeapon;
import megamek.common.weapons.lrms.StreakLRMWeapon;
import megamek.common.weapons.mgs.MGWeapon;
import megamek.common.weapons.missiles.MRMWeapon;
import megamek.common.weapons.missiles.RLWeapon;
import megamek.common.weapons.missiles.ThunderBoltWeapon;
import megamek.common.weapons.ppc.CLPlasmaCannon;
import megamek.common.weapons.ppc.ISPlasmaRifle;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;
import megamek.common.weapons.srms.StreakSRMWeapon;
import megameklab.ui.PopupMessages;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class UnitUtil {

    private static Font rsFont = null;
    private static Font rsBoldFont = null;

    /**
     * tells is EquipementType is an equipment that uses crits/mounted and is
     * spread across multiple locations
     *
     * @param eq The equipment to test
     * @return
     */
    public static boolean isFixedLocationSpreadEquipment(EquipmentType eq) {
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                        || eq.hasFlag(MiscType.F_BA_MANIPULATOR)
                        || eq.hasFlag(MiscType.F_PARTIAL_WING)
                        || eq.hasFlag(MiscType.F_NULLSIG)
                        || eq.hasFlag(MiscType.F_VOIDSIG)
                        || eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)
                        || eq.hasFlag(MiscType.F_TRACKS)
                        || eq.hasFlag(MiscType.F_TALON)
                        || (eq.hasFlag(MiscType.F_STEALTH)
                            && (eq.hasFlag(MiscType.F_MECH_EQUIPMENT)
                                || eq.hasFlag(MiscType.F_TANK_EQUIPMENT)))
                        || eq.hasFlag(MiscType.F_CHAMELEON_SHIELD)
                        || eq.hasFlag(MiscType.F_BLUE_SHIELD)
                        || eq.hasFlag(MiscType.F_MAST_MOUNT)
                        || eq.hasFlag(MiscType.F_SCM)
                        || (eq.hasFlag(MiscType.F_RAM_PLATE)
                        || (eq.hasFlag(MiscType.F_JUMP_JET) && eq.hasFlag(MiscType.F_PROTOMECH_EQUIPMENT))
                        || (eq.hasFlag(MiscType.F_UMU) && eq.hasFlag(MiscType.F_PROTOMECH_EQUIPMENT))
                        || (eq.hasFlag(MiscType.F_MAGNETIC_CLAMP) && eq.hasFlag(MiscType.F_PROTOMECH_EQUIPMENT))
                        || (eq.hasFlag(MiscType.F_MASC) && eq.hasFlag(MiscType.F_PROTOMECH_EQUIPMENT))));
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq The equipment to test
     * @return
     */
    public static boolean isArmor(EquipmentType eq) {
        return eq instanceof ArmorType;
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq The equipment to test
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
     * @param eq The equipment to test
     * @return
     */
    public static boolean isTSM(EquipmentType eq) {
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_TSM) || eq.hasFlag((MiscType.F_INDUSTRIAL_TSM)));
    }

    /**
     * @param eq The equipment to test The equipmentType to check
     * @return true if this is a Remote Sensor Dispenser (BA or vehicular)
     */
    public static boolean isRemoteSensorDispenser(EquipmentType eq) {
        return (eq instanceof MiscType) && eq.hasFlag(MiscType.F_SENSOR_DISPENSER);
    }

    /**
     * @param eq The equipment to test The equipmentType to check
     * @return true if this is a Mine Dispenser (BA or vehicular)
     */
    public static boolean isMineDispenser(EquipmentType eq) {
        return (eq instanceof MiscType) && eq.hasFlag(MiscType.F_VEHICLE_MINE_DISPENSER);
    }

    /**
     * tells if EquipmentType is MASC
     *
     * @param eq The equipment to test
     * @return
     */
    public static boolean isMASC(EquipmentType eq) {
        return (eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_MASC) && !eq.hasSubType(MiscType.S_SUPERCHARGER));
    }

    /**
     * Returns the number of crits used by EquipmentType for each placement. For
     * most equipment this is the same as the total slots, but some spreadable
     * equipment is allocated a single slot at a time, or split between multiple
     * locations.
     *
     * @param mount The equipment mount
     * @return      The number of slots per allocation
     */
    public static int getCritsUsed(Mounted mount) {
        double toReturn = mount.getCriticals();

        //if it's 0, we can return now (e.g. standard armor or IS, we don't
        //want that to count as 1 later on
        if (toReturn == 0) {
            return 0;
        }

        final EquipmentType eq = mount.getType();
        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_PARTIAL_WING)) {
            toReturn = eq.isClan() ? 3 : 4;
        } else  if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                        || eq.hasFlag(MiscType.F_TALON)
                        // Stealth armor is allocated 2 slots/location in mechs, but by individual slot for BA
                        || (eq.hasFlag(MiscType.F_STEALTH) && !(mount.getEntity() instanceof BattleArmor)))) {
            toReturn = 2;
        } else  if (UnitUtil.isFixedLocationSpreadEquipment(eq) || UnitUtil.isTSM(eq)
                || UnitUtil.isArmorOrStructure(eq)) {
            toReturn = 1;
        }
        if ((mount.getEntity() instanceof Mech) && mount.getEntity().isSuperHeavy()) {
            toReturn = Math.ceil(toReturn / 2.0);
        }
        return (int) toReturn;
    }

    /**
     * Removes a piece of equipment from the Entity
     *
     * @param unit The entity  The Entity
     * @param mount The equipment
     */
    public static void removeMounted(Entity unit, Mounted<?> mount) {
        UnitUtil.removeCriticals(unit, mount);

        // Some special checks for BA
        if (unit instanceof BattleArmor) {
            // If we're removing a DWP and it has an attached weapon, we need
            //  to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && (mount.getLinked() != null)) {
                Mounted<?> link = mount.getLinked();
                link.setDWPMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }
            // If we are removing a weapon that is mounted in an DWP, we need
            //  to clear the mounted status of the DWP
            if ((mount.getLinkedBy() != null)
                    && mount.getLinkedBy().getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)) {
                Mounted<?> dwp = mount.getLinkedBy();
                dwp.setLinked(null);
                dwp.setLinkedBy(null);
            }
            // If we're removing an APM and it has an attached weapon, we need
            //  to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_AP_MOUNT) && (mount.getLinked() != null)) {
                Mounted<?> link = mount.getLinked();
                link.setAPMMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }
            // If we are removing a weapon that is mounted in an APM, we need
            //  to clear the mounted status of the AP Mount
            if ((mount.getLinkedBy() != null)
                    && mount.getLinkedBy().getType().hasFlag(MiscType.F_AP_MOUNT)) {
                Mounted<?> apm = mount.getLinkedBy();
                apm.setLinked(null);
                apm.setLinkedBy(null);
            }
        }
        // We will need to reset the equipment numbers of the bay ammo and weapons
        Map<WeaponMounted,List<WeaponMounted>> bayWeapons = new HashMap<>();
        Map<WeaponMounted,List<AmmoMounted>> bayAmmo = new HashMap<>();
        for (WeaponMounted bay : unit.getWeaponBayList()) {
            bayWeapons.put(bay, bay.getBayWeapons());
            bayAmmo.put(bay, bay.getBayAmmo());
        }
        // Some special checks for Aeros
        if (unit instanceof Aero) {
            if (mount instanceof WeaponMounted) {
                // Aeros have additional weapon lists that need to be cleared
                unit.getTotalWeaponList().remove(mount);
                unit.getWeaponBayList().remove(mount);
                unit.getWeaponGroupList().remove(mount);
            }
        }
        unit.getEquipment().remove(mount);
        if (mount instanceof MiscMounted) {
            unit.getMisc().remove(mount);
        } else if (mount instanceof AmmoMounted) {
            unit.getAmmo().remove(mount);
        } else if (mount instanceof WeaponMounted) {
            unit.getWeaponList().remove(mount);
            unit.getTotalWeaponList().remove(mount);
        }
        if (bayWeapons.containsKey(mount)) {
            bayWeapons.get(mount).forEach(w -> {
                removeCriticals(unit, w);
                changeMountStatus(unit, w, Entity.LOC_NONE, Entity.LOC_NONE, false);
            });
            bayAmmo.get(mount).forEach(a -> {
                removeCriticals(unit, a);
                Mounted moveTo = UnitUtil.findUnallocatedAmmo(unit, a.getType());
                if (null != moveTo) {
                    moveTo.setShotsLeft(moveTo.getBaseShotsLeft() + a.getBaseShotsLeft());
                    UnitUtil.removeMounted(unit, a);
                }
                changeMountStatus(unit, a, Entity.LOC_NONE, Entity.LOC_NONE, false);
            });
            bayWeapons.remove(mount);
            bayAmmo.remove(mount);
        }
        for (WeaponMounted bay : bayWeapons.keySet()) {
            bay.clearBayWeapons();
            for (WeaponMounted w : bayWeapons.get(bay)) {
                if (mount != w) {
                    bay.addWeaponToBay(w);
                }
            }
        }
        for (WeaponMounted bay : bayAmmo.keySet()) {
            bay.clearBayAmmo();
            for (AmmoMounted a : bayAmmo.get(bay)) {
                if (mount != a) {
                    bay.addAmmoToBay(a);
                }
            }
        }
        // Remove ammo added for a one-shot launcher
        if ((mount.getType() instanceof WeaponType) && mount.isOneShot()) {
            List<AmmoMounted> osAmmo = new ArrayList<>();
            for (AmmoMounted ammo = (AmmoMounted) mount.getLinked(); ammo != null; ammo = (AmmoMounted) ammo.getLinked()) {
                osAmmo.add(ammo);
            }
            osAmmo.forEach(m -> {
                unit.getEquipment().remove(m);
                unit.getAmmo().remove(m);
            });
        }
        // It's possible that the equipment we are removing was linked to
        // something else, and so the linkedBy state may be set.  We should
        // remove it. Using getLinked could be unreliable, so we'll brute force
        // it
        // An example of this would be removing a linked Artemis IV FCS
        for (Mounted<?> m : unit.getEquipment()) {
            if (mount.equals(m.getLinkedBy())) {
                m.setLinkedBy(null);
            }
        }
        if ((mount.getType() instanceof MiscType)
            && (mount.getType().hasFlag(MiscType.F_HEAD_TURRET)
                || mount.getType().hasFlag(MiscType.F_SHOULDER_TURRET)
                || mount.getType().hasFlag(MiscType.F_QUAD_TURRET))) {
            for (Mounted<?> m : unit.getEquipment()) {
                if (m.getLocation() == mount.getLocation()) {
                    m.setMechTurretMounted(false);
                }
            }
        }
        if ((mount.getType() instanceof MiscType)
                && mount.getType().hasFlag(MiscType.F_SPONSON_TURRET)) {
            for (Mounted<?> m : unit.getEquipment()) {
                m.setSponsonTurretMounted(false);
            }
        }
        if ((mount.getType() instanceof MiscType)
                && mount.getType().hasFlag(MiscType.F_PINTLE_TURRET)) {
            for (Mounted<?> m : unit.getEquipment()) {
                if (m.getLocation() == mount.getLocation()) {
                    m.setPintleTurretMounted(false);
                }
            }
        }
        unit.recalculateTechAdvancement();
    }

    /**
     * Sets the corresponding critical slots to null for the Mounted object.
     *
     * @param unit The entity
     * @param eq The equipment to test
     */
    public static void removeCriticals(Entity unit, Mounted eq) {
        if (eq.getLocation() == Entity.LOC_NONE) {
            return;
        }
        for (int loc = 0; loc < unit.locations(); loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = unit.getCritical(loc, slot);
                if ((cs != null)
                        && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    if (cs.getMount().equals(eq)) {
                        // If there are two pieces of equipment in this slot,
                        // remove first one, and replace it with the second
                        if (cs.getMount2() != null) {
                            cs.setMount(cs.getMount2());
                            cs.setMount2(null);
                        } else { // If it's the only Mounted, clear the slot
                            cs = null;
                            unit.setCritical(loc, slot, cs);
                        }
                    } else if ((cs.getMount2() != null)
                            && cs.getMount2().equals(eq)) {
                        cs.setMount2(null);
                    }
                }
            }
        }
    }

    public static void addMounted(Entity unit, Mounted mounted, int loc, boolean rearMounted)
            throws LocationFullException {
        unit.addEquipment(mounted, loc, rearMounted);
        mounted.setOmniPodMounted(canPodMount(unit, mounted));
        if (unit instanceof Mech) {
            MekUtil.updateClanCasePlacement((Mech) unit);
        }
    }

    /**
     * Updates TC Crits and Mounts based on weapons on a unit or if the TC has
     * been removed.
     *
     * @param unit The entity
     */
    public static Mounted updateTC(Entity unit, EquipmentType tc) {
        UnitUtil.removeTC(unit);
        return UnitUtil.createTCMounts(unit, tc);
    }

    /**
     * Creates TC Mount.
     *
     * @param unit The entity
     */
    public static @Nullable Mounted createTCMounts(Entity unit, EquipmentType tc) {
        try {
            return unit.addEquipment(tc, Entity.LOC_NONE);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Checks to see if unit can use the techlevel
     *
     * @param unit The entity
     * @param tech
     * @return Boolean if the tech level is legal for the passed unit
     */
    public static boolean isLegal(Entity unit, ITechnology tech) {
        if (unit.isMixedTech()) {
            if (!tech.isAvailableIn(unit.getTechLevelYear(), CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                return false;
            }
        } else {
            if (tech.getTechBase() != ITechnology.TECH_BASE_ALL
                    && unit.isClan() != tech.isClan()) {
                return false;
            }

            if (!tech.isAvailableIn(unit.getTechLevelYear(), unit.isClan(),
                    CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                return false;
            }
        }
        return TechConstants.convertFromNormalToSimple(tech.getTechLevel(unit.getTechLevelYear(),
                unit.isClan())) <= TechConstants.convertFromNormalToSimple(unit.getTechLevel());
    }

    /**
     * checks if Mounted is a heat sink
     *
     * @param eq The equipment to test
     * @return
     */
    public static boolean isHeatSink(Mounted eq) {
        return ((eq.getType() != null) && isHeatSink(eq.getType()));
    }

    /**
     * Checks if EquipmentType is a heat sink
     *
     * @param eq The equipment to test
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
     * Checks if EquipmentType is a Mech Physical weapon.
     *
     * @param eq The equipment to test The equipment to check
     * @return   Whether the equipment is a physical weapon
     */
    public static boolean isPhysicalWeapon(EquipmentType eq) {
        if (!(eq instanceof MiscType)) {
            return false;
        }
        if (eq.hasFlag(MiscType.F_CLUB)) {
            // We don't want makeshift clubs picked up on the battlefield showing up as construction options
            return !eq.hasSubType(MiscType.S_CLUB | MiscType.S_TREE_CLUB);
        }
        return eq.hasFlag(MiscType.F_HAND_WEAPON)
                || eq.hasFlag(MiscType.F_TALON)
                || eq.hasFlag(MiscType.F_RAM_PLATE);
    }

    public static String getHeatSinkType(String type, boolean clan) {
        String heatSinkType;

        if (type.startsWith("(Clan)")) {
            clan = true;
            type = type.substring(7).trim();
        } else if (type.startsWith("(IS)")) {
            clan = false;
            type = type.substring(4).trim();
        }

        if (clan) {
            if (type.equals("Single")) {
                heatSinkType = EquipmentTypeLookup.SINGLE_HS;
            } else if (type.equals("Double")) {
                heatSinkType = EquipmentTypeLookup.CLAN_DOUBLE_HS;
            } else {
                heatSinkType = EquipmentTypeLookup.LASER_HS;
            }
        } else {
            if (type.equals("Single")) {
                heatSinkType = EquipmentTypeLookup.SINGLE_HS;
            } else if (type.equals("Double")) {
                heatSinkType = EquipmentTypeLookup.IS_DOUBLE_HS;
            } else {
                heatSinkType = EquipmentTypeLookup.COMPACT_HS_2;
            }
        }

        return heatSinkType;
    }

    public static boolean isJumpJet(Mounted m) {
        return (m.getType() instanceof MiscType) &&
                (m.getType().hasFlag(MiscType.F_JUMP_JET)
                || m.getType().hasFlag(MiscType.F_JUMP_BOOSTER));
    }

    /**
     *
     * @param type The value returned by {@link Mech#getJumpType()}
     * @return     The {@link EquipmentType} lookup key for the jump jet
     */
    public static String getJumpJetType(int type) {
        if (type == Mech.JUMP_IMPROVED) {
            return EquipmentTypeLookup.IMPROVED_JUMP_JET;
        } else if (type == Mech.JUMP_PROTOTYPE) {
            return EquipmentTypeLookup.PROTOTYPE_JUMP_JET;
        } else if (type == Mech.JUMP_BOOSTER) {
            return EquipmentTypeLookup.MECH_JUMP_BOOSTER;
        } else if (type == Mech.JUMP_PROTOTYPE_IMPROVED) {
            return EquipmentTypeLookup.PROTOTYPE_IMPROVED_JJ;
        }
        return EquipmentTypeLookup.JUMP_JET;
    }


    /**
     * Checks whether equipment can be linked to a weapon to enhance it (e.g. Artemis, PPC Capacitor, etc).
     * @param type The equipment to check
     * @return     true if the equipment is a MiscType that can be linked to a weapon.
     */
    public static boolean isWeaponEnhancement(EquipmentType type) {
        return (type instanceof MiscType)
                && (type.hasFlag(MiscType.F_ARTEMIS)
                        || type.hasFlag(MiscType.F_ARTEMIS_V)
                        || type.hasFlag(MiscType.F_ARTEMIS_PROTO)
                        || type.hasFlag(MiscType.F_APOLLO)
                        || type.hasFlag(MiscType.F_PPC_CAPACITOR)
                        || type.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE));
    }

    /**
     * Changes the location for a Mounted instance.  Note: for BattleArmor, this
     * effects which suit the equipment is placed on (as that is what
     * Mounted.location means for BA), but not where on the suit
     * it's located (ie, BAMountLocation isn't affected).  BattleArmor should
     * change this outside of this method.
     *
     * @param unit The entity               The unit being modified
     * @param eq The equipment to test                 The equipment mount to move
     * @param location           The location to move the mount to
     * @param secondaryLocation  The secondary location for split equipment, otherwise {@link Entity#LOC_NONE Entity.LOC_NONE}
     * @param rear               Whether to mount with a rear facing
     */
    public static void changeMountStatus(Entity unit, Mounted eq, int location,
                                         int secondaryLocation, boolean rear) {
        if ((location != eq.getLocation() && !eq.isOneShot())) {
            if (eq.getLinked() != null) {
                eq.getLinked().setLinkedBy(null);
                eq.setLinked(null);
            }
            if (eq.getLinkedBy() != null) {
                eq.getLinkedBy().setLinked(null);
                eq.setLinkedBy(null);
            }
        }
        eq.setLocation(location, rear);
        eq.setSecondLocation(secondaryLocation, rear);
        eq.setSplit(secondaryLocation > -1);
        // If we're adding it to a location on the unit, check equipment linkages
        if (location > Entity.LOC_NONE) {
            try {
                MechFileParser.postLoadInit(unit);
            } catch (Exception ignored) {
                // Exception thrown for not having equipment to link to yet, which is acceptable here
            }
        }
        if (unit instanceof Mech) {
            MekUtil.updateClanCasePlacement((Mech) unit);
        }
    }

    public static void resizeMount(Mounted mount, double newSize) {
        mount.setSize(newSize);
        if (mount.getLocation() == Entity.LOC_NONE) {
            return;
        }
        final Entity entity = mount.getEntity();
        // Mechs may need to shift the crits around to make room if the equipment grows
        if (entity instanceof Mech) {
            final int loc = mount.getLocation();
            int start = -1;
            for (int slot = 0; slot < entity.getNumberOfCriticals(loc); slot++) {
                CriticalSlot crit = entity.getCritical(loc, slot);
                if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                        && crit.getMount().equals(mount)) {
                    start = slot;
                    break;
                }
            }
            removeCriticals(entity, mount);
            compactCriticals(entity, loc);
            if ((start < 0) || (entity.getEmptyCriticals(loc) < mount.getCriticals())) {
                changeMountStatus(entity, mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            } else {
                // If the number of criticals increases, we may need to shift existing criticals
                // to make room. Since we checked for sufficient space and compacted the existing
                // criticals we can be assured of not overrunning the array.
                List<CriticalSlot> toAdd = new ArrayList<>();
                for (int i = 0; i < mount.getCriticals(); i++) {
                    toAdd.add(new CriticalSlot(mount));
                }
                int slot = start;
                while (!toAdd.isEmpty()) {
                    CriticalSlot cs = entity.getCritical(loc, slot);
                    if (cs != null) {
                        toAdd.add(cs);
                    }
                    entity.setCritical(loc, slot, toAdd.get(0));
                    toAdd.remove(0);
                    slot++;
                }
            }
        }
    }

    /**
     * Find unallocated ammo of the same type. Used by large aerospace units when removing ammo
     * from a location to find the group to add it to.
     *
     * @param unit The entity The Entity
     * @param at   The type of armor to match
     * @return     An unallocated non-oneshot ammo mount of the same type, or null if there is not one.
     */
    public static Mounted findUnallocatedAmmo(Entity unit, EquipmentType at) {
        for (Mounted m : unit.getAmmo()) {
            if ((m.getLocation() == Entity.LOC_NONE)
                    && at.equals(m.getType())
                    && ((m.getLinkedBy() == null)
                            || !m.getLinkedBy().getType().hasFlag(WeaponType.F_ONESHOT))) {
                return m;
            }
        }
        return null;
    }

    /**
     * Checks whether the equipment is eligible for pod mounting in an omni unit, either because the
     * equipment itself can never be pod-mounted (such as armor, structure, or myomer enhancements),
     * or the number of fixed heat sinks have not been assigned locations.
     *
     * @param unit The entity
     * @param eq The equipment to test
     * @return
     */
    public static boolean canPodMount(Entity unit, Mounted eq) {
        if (!unit.isOmni() || eq.getType().isOmniFixedOnly()) {
            return false;
        }

        if (eq.getType() instanceof MiscType && unit instanceof Mech
                && (eq.getType().hasFlag(MiscType.F_HEAT_SINK)
                        || eq.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                        || eq.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE))
                && unit.hasEngine()) {
            int needed = Math.max(0, unit.getEngine().getWeightFreeEngineHeatSinks() -
                    UnitUtil.getCriticalFreeHeatSinks(unit, ((Mech)unit).hasCompactHeatSinks()));
            long fixed = unit.getMisc().stream().filter(m ->
            (m.getType().hasFlag(MiscType.F_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE))
            && m.getLocation() != Entity.LOC_NONE && !m.isOmniPodMounted()).count();
            //Do not count this heat among the fixed, since we are checking whether we can change it to pod-mounted
            if (eq.getLocation() != Entity.LOC_NONE && !eq.isOmniPodMounted()) {
                fixed--;
            }
            return fixed >= needed;
        }
        return true;
    }

    /**
     * Removes all pod-mounted equipment from an omni unit
     * @param unit The entity
     */
    public static void resetBaseChassis(Entity unit) {
        if (!unit.isOmni()) {
            return;
        }
        List<Mounted> pods = unit.getEquipment().stream()
                .filter(Mounted::isOmniPodMounted)
                .collect(Collectors.toList());
        for (Mounted m : pods) {
            UnitUtil.removeMounted(unit, m);
            if (m.getType() instanceof MiscType
                    && m.getType().hasFlag(MiscType.F_JUMP_JET)) {
                unit.setOriginalJumpMP(unit.getOriginalJumpMP() - 1);
            }
        }
        List<Transporter> transporters = unit.getTransports().stream()
                .filter(unit::isPodMountedTransport).collect(Collectors.toList());
        transporters.forEach(unit::removeTransporter);
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

    public static int[] getHighestContinuousNumberOfCritsArray(Mech unit) {
        int[] critSpaces = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            critSpaces[loc] = UnitUtil.getHighestContinuousNumberOfCrits(unit, loc);
        }
        return critSpaces;
    }

    public static int getHighestContinuousNumberOfCrits(Entity unit, int location) {
        int highestNumberOfCrits = 0;
        int currentCritCount = 0;

        // Handle locations without crits
        if ((location == Entity.LOC_DESTROYED)
                || (location == Entity.LOC_NONE)) {
            return 0;
        }

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
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()
                    && (((AmmoType) mount.getType()).getAmmoType() != AmmoType.T_INFANTRY)) {
                int slots = 1;
                if (unit.usesWeaponBays()) {
                    slots = (int) Math.ceil(mount.getUsableShotsLeft() / (double) ((AmmoType) mount.getType()).getShots());
                }
                tonnage += slots * mount.getTonnage();
            }
        }

        return tonnage;
    }

    public static int getMaximumArmorPoints(Entity unit) {
        int points = 0;
        if (unit.hasETypeFlag(Entity.ETYPE_MECH)) {
            int headPoints = 3;
            if (unit.getWeightClass() == EntityWeightClass.WEIGHT_SUPER_HEAVY) {
                headPoints = 4;
            }
            points = (unit.getTotalInternal() * 2) + headPoints;
        } else if (unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            points = TestProtomech.maxArmorFactor((Protomech) unit);
        } else if (unit.isSupportVehicle()) {
            points = TestSupportVehicle.maxArmorFactor(unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_TANK)) {
            points = (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else if (unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            points = (unit.getWeightClass() * 4) + 2;
        } else if (unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            points = TestAdvancedAerospace.maxArmorPoints((Jumpship)unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            points = TestSmallCraft.maxArmorPoints((SmallCraft)unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_CONV_FIGHTER)) {
            points = (int) Math.floor(unit.getWeight());
        } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
            points = (int) Math.floor(unit.getWeight() * 8);
        }
        return points;
    }

    public static int getMaximumArmorPoints(Entity unit, int loc) {
        if ((unit instanceof Mech) && (loc == Mech.LOC_HEAD)) {
            if (unit.isSuperHeavy()) {
                return 12;
            } else {
                return 9;
            }
        } else if (unit instanceof Mech) {
            return unit.getInternal(loc) * 2;
        } else if (unit.isSupportVehicle()) {
            return TestSupportVehicle.maxArmorFactor(unit);
        } else if (unit instanceof Tank) {
            if ((unit instanceof VTOL) && (loc == VTOL.LOC_ROTOR)) {
                return 2;
            }
            return (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else if (unit instanceof Protomech) {
            return TestProtomech.maxArmorFactor((Protomech) unit, loc);
        } else {
            return 0;
        }
    }

    public static double getMaximumArmorTonnage(Entity unit) {
        if (unit instanceof Jumpship) {
            return TestAdvancedAerospace.maxArmorWeight((Jumpship) unit);
        }

        double armorPerTon = ArmorType.forEntity(unit).getPointsPerTon(unit);
        double armorWeight = 0;

        if (unit.getArmorType(1) == EquipmentType.T_ARMOR_HARDENED) {
            armorPerTon = 8.0;
        }
        if (unit instanceof Mech) {
            double points = (unit.getTotalInternal() * 2);
            // Add in extra armor points for head
            if (unit.isSuperHeavy()) {
                points += 4;
            } else {
                points += 3;
            }
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof Protomech) {
            double points = TestProtomech.maxArmorFactor((Protomech) unit);
            return points * ArmorType.forEntity(unit).getWeightPerPoint();
        } else if (unit.isSupportVehicle()) {
            // Max armor is determined by number of points.
            double weight = TestSupportVehicle.maxArmorFactor(unit)
                    * TestSupportVehicle.armorWeightPerPoint(unit);
            if (unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
                return TestEntity.round(weight, Ceil.KILO);
            } else {
                return TestEntity.ceil(weight, Ceil.HALFTON);
            }
        } else if (unit instanceof Tank) {
            double points = Math.floor((unit.getWeight() * 3.5) + 40);
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof BattleArmor) {
            armorWeight = (unit.getWeightClass() * 4) + 2;
        } else if (unit instanceof SmallCraft) {
            return TestSmallCraft.maxArmorWeight((SmallCraft) unit);
        }
        if (unit instanceof Aero) {
            double points = TestAero.maxArmorPoints(unit, unit.getWeight());
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        }
        return armorWeight;
    }

    /**
     * Computes the total number of armor points available to the unit for a given tonnage of armor.
     * This does not round down the calculation or take into account any maximum number of armor
     * points or tonnage allowed to the unit.
     *
     * NOTE: only use for non-patchwork armor
     *
     * @param   unit
     * @param   armorTons
     * @return  the number of armor points available for the armor tonnage
     */
    public static double getRawArmorPoints(Entity unit, double armorTons) {
        if (unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            return Math.round(armorTons / ArmorType.forEntity(unit).getWeightPerPoint());
        } else if (unit.isSupportVehicle()) {
            return Math.floor(armorTons / TestSupportVehicle.armorWeightPerPoint(unit));
        } else if ((unit instanceof Jumpship)
                && unit.getArmorType(unit.firstArmorIndex()) == EquipmentType.T_ARMOR_PRIMITIVE_AERO) {
            // Because primitive JumpShip armor has an extra step of rounding we have to give it special treatment.
            // Standard armor value is computed first, rounded down, then the primitive armor mod is applied.
            return Math.floor(Math.floor(armorTons * TestAdvancedAerospace.armorPointsPerTon((Jumpship) unit,
                    EquipmentType.T_ARMOR_AEROSPACE, false)) * 0.66);
        }
        return armorTons * UnitUtil.getArmorPointsPerTon(unit);
    }

    /**
     * Computes the total number of additional points provided for aerospace vessels based on
     * their SI. This is usually a whole number but may be a fractional amount for primitive
     * JumpShips.
     *
     * @param entity The unit to compute bonus armor for.
     * @return       The number of extra armor points received for SI. This is the total number, which
     *               is usually divided evenly among armor facings.
     */
    public static double getSIBonusArmorPoints(Entity entity) {
        double points = 0.0;
        if (entity.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            points = ((SmallCraft)entity).getSI() * (entity.locations() - 1);
        } else if (entity.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            points = Math.round(((Jumpship) entity).getSI() / 10.0) * 6;
        }
        if (entity.isPrimitive()) {
            return points * ArmorType.of(EquipmentType.T_ARMOR_PRIMITIVE_AERO, false).getArmorPointsMultiplier();
        } else {
            return points;
        }
    }

    /**
     * NOTE: only use for non-patchwork armor
     *
     * @param unit The entity
     * @param armorTons
     * @return
     */
    public static int getArmorPoints(Entity unit, double armorTons) {
        int raw = (int) Math.floor(UnitUtil.getRawArmorPoints(unit,  armorTons)
                + UnitUtil.getSIBonusArmorPoints(unit));
        return Math.min(raw, UnitUtil.getMaximumArmorPoints(unit));
    }

    /**
     * Calculate the number of armor points per ton of armor for the given unit.
     *
     * @param en        The unit
     * @return          The number of armor points per ton
     */
    public static double getArmorPointsPerTon(Entity en) {
        ArmorType armor = ArmorType.forEntity(en);
        if (armor.hasFlag(MiscType.F_SUPPORT_VEE_BAR_ARMOR)) {
            return 1.0 / armor.getSVWeightPerPoint(en.getArmorTechRating());
        } else {
            return armor.getPointsPerTon(en);
        }
    }

    public static void compactCriticals(Entity unit) {
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit instanceof Mech) {
                MekUtil.compactCriticals((Mech) unit, loc);
            } else {
                compactCriticals(unit, loc);
            }
        }
    }

    /**
     * Determine the maximum number of armor points that can be mounted in a location.
     *
     * @param entity
     * @param location
     * @return  The maximum number of armor points for the location, or null if there is no maximum.
     */
    public static @Nullable Integer getMaxArmor(Entity entity, int location) {
        if ((location < 0) || (location >= entity.locations())) {
            return 0;
        }
        if (entity.hasETypeFlag(Entity.ETYPE_MECH)) {
            if (location == Mech.LOC_HEAD) {
                return (entity.getWeightClass() == EntityWeightClass.WEIGHT_SUPER_HEAVY)? 12 : 9;
            } else {
                return entity.getOInternal(location) * 2;
            }
        } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            return TestProtomech.maxArmorFactor((Protomech) entity, location);
        } else if ((entity instanceof VTOL) && (location == VTOL.LOC_ROTOR)) {
            return 2;
        }
        return null;
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

    public static boolean isAMS(WeaponType weapon) {
        return weapon.hasFlag(WeaponType.F_AMS);
    }

    public static boolean hasSwitchableAmmo(WeaponType weapon) {
        return (weapon instanceof AmmoWeapon) && !(weapon instanceof StreakLRMWeapon)
                && !(weapon instanceof StreakSRMWeapon) && !(weapon instanceof GaussWeapon)
                && !(weapon instanceof UACWeapon) && !(weapon instanceof HVACWeapon)
                && !(weapon instanceof MGWeapon) && !(weapon instanceof ThunderBoltWeapon)
                && !(weapon instanceof CLChemicalLaserWeapon) && !(weapon instanceof MPodWeapon)
                && !(weapon instanceof BPodWeapon) && !(weapon instanceof ISPlasmaRifle)
                && !(weapon instanceof CLPlasmaCannon) && !(weapon instanceof VehicleFlamerWeapon)
                && !UnitUtil.isAMS(weapon);
    }

    public static void loadFonts() {
        Font font = Font.decode(CConfig.getParam(CConfig.RS_FONT, "Eurostile"));
        // If the font is not installed, use system default sans
        if (null == font) {
            font = Font.decode(Font.SANS_SERIF);
        }
        // If that still doesn't work, get the default dialog font
        if (null == font) {
            font = Font.decode(null);
        }
        rsFont = font.deriveFont(Font.PLAIN, 8);
        rsBoldFont = font.deriveFont(Font.BOLD, 8);
    }

    public static Font deriveFont(float pointSize) {
        return UnitUtil.deriveFont(false, pointSize);
    }

    public static Font deriveFont(boolean boldFont, float pointSize) {
        UnitUtil.loadFonts();

        if (boldFont) {
            return rsBoldFont.deriveFont(pointSize);
        }

        return rsFont.deriveFont(pointSize);
    }

    public static void removeOneShotAmmo(Entity unit) {
        ArrayList<Mounted> ammoList = new ArrayList<>();

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

    public static void removeClanCase(Entity unit) {
        ArrayList<Mounted> caseList = new ArrayList<>();

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
                    && ((mount.getLocation() == location) || (mount.getSecondLocation() == location))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks to see if something is a Jump Jet
     *
     * @param eq The equipment to test
     * @return
     */
    public static boolean isJumpJet(EquipmentType eq) {
        if ((eq instanceof MiscType)
                && (eq.hasFlag(MiscType.F_JUMP_JET)
                        || eq.hasFlag(MiscType.F_UMU)
                        || eq.hasFlag(MiscType.F_BA_VTOL))) {
            return true;
        }

        return false;
    }

    /**
     * @return the name of the given equipment with (IS) or (Clan) added for mixed tech units when
     * appropriate.
     */
    public static String getCritName(Entity unit, EquipmentType eq) {
        String name = eq.getName();
        // Only shorten non-ammo; getShortName leaves off "Ammo" and "[Half]" that we want
        if (name.length() > 22 && !(eq instanceof AmmoType) ) {
            name = eq.getShortName();
        }
        if (unit.isMixedTech()
                && (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_ALLOWED_ALL)
                && (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_TECH_UNKNOWN)) {
            if (unit.isClan() && !TechConstants.isClan(eq.getTechLevel(unit.getTechLevelYear()))) {
                name += " (IS)";
            } else if (!unit.isClan() && TechConstants.isClan(eq.getTechLevel(unit.getTechLevelYear()))) {
                name += " (Clan)";
            }
        }
        return name;
    }

    /**
     * Return the number of critical-space free heatsinks that the given entity
     * can have.
     *
     * @param unit The entity
     *            The unit mounting the heatsinks
     * @param compact
     *            Whether the heatsinks are compact or not
     * @return T he number of critical-free heat sinks.
     */
    public static int getCriticalFreeHeatSinks(Entity unit, boolean compact) {
        int engineHSCapacity = unit.getEngine().integralHeatSinkCapacity(compact);

        if (unit.isOmni()) {
            engineHSCapacity = Math.min(engineHSCapacity, unit.getEngine().getBaseChassisHeatSinks(compact));
        }

        return engineHSCapacity;
    }

    public static boolean isPreviousCritEmpty(Entity unit, CriticalSlot cs, int slot, int location) {
        if (slot == 0) {
            return false;
        } else if (unit instanceof Mech) {
            return (slot <= 0) || (unit.getCritical(location, slot - 1) == null);
        } else {
            return true;
        }
    }

    public static boolean isLastCrit(Entity unit, CriticalSlot cs, int slot, int location) {
        if (unit instanceof Mech) {
            return MekUtil.isLastMechCrit((Mech) unit, cs, slot, location);
        }
        return true;
    }

    /**
     * Finds all the critical slots in the location containing the mount and sets or clears the
     * armored component flag in accordance with the flag on the mount.
     *
     * @param unit The entity     The unit the equipment is mounted on
     * @param mount    The mount
     * @param location The location to check
     */
    public static void updateCritsArmoredStatus(Entity unit, Mounted mount, int location) {
        for (int position = 0; position < unit.getNumberOfCriticals(location); position++) {
            CriticalSlot cs = unit.getCritical(location, position);
            if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                continue;
            }

            if (mount.equals(cs.getMount())) {
                cs.setArmored(mount.isArmored());
            }
        }
    }

    /**
     * Sets the armored component flag on all critical slots occupied by an equipment mount to
     * be the same as the flag on the mount.
     *
     * @param unit The entity  The unit the equipment is on
     * @param mount The equipment mount
     */
    public static void updateCritsArmoredStatus(Entity unit, Mounted mount) {
        /* Several types of equipment have multiple fixed locations. These
         * are always mounted in the primary location and added to critical
         * slots in the other location(s). Examples are partial wing (both side torsos)
         * and mech tracks (all legs). Rather than dealing with each piece of equipment
         * individually and risking missing one, just check everywhere.
         */
        if (isFixedLocationSpreadEquipment(mount.getType())) {
            for (int loc = 0; loc < unit.locations(); loc++) {
                updateCritsArmoredStatus(unit, mount, loc);
            }
        } else {
            updateCritsArmoredStatus(unit, mount, mount.getLocation());

            if ((mount.isSplitable() || mount.getType().isSpreadable())
                    && (mount.getSecondLocation() != Entity.LOC_NONE)) {
                updateCritsArmoredStatus(unit, mount, mount.getSecondLocation());
            }
        }
    }

    public static void updateCritsArmoredStatus(Entity unit, @Nullable CriticalSlot cs,
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

    public static boolean isArmorable(@Nullable CriticalSlot cs) {
        if (cs == null) {
            return false;
        } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
            return true;
        } else {
            Mounted mount = cs.getMount();
            return (mount != null) && isArmorable(mount.getType());
        }
    }

    public static boolean isArmorable(EquipmentType eq) {
        if (eq instanceof AmmoType) {
            // The prohibition against armoring ammo bins presumably only applies to actual
            // ammo bins and not equipment that we've implemented as ammo because it's explody and gets used up.
            return ((AmmoType) eq).getAmmoType() == AmmoType.T_COOLANT_POD;
        }
        return eq.isHittable();
    }

    public static void updateLoadedUnit(Entity unit) {
        // Check for illegal armor tech levels and set to the tech level of the unit.
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit.getArmorType(loc) >= 0) {
                if (unit.getArmorTechLevel(loc) < 0) {
                    unit.setArmorTechLevel(unit.getTechLevel());
                }
            }
        }

        // Removing equipment for construction purposes can shift the equipment indices.
        // We need to be able to update bay weapon and ammo indices. This includes
        // weapon bays and machine gun arrays.
        Map<WeaponMounted, List<WeaponMounted>> bayWeapons = new HashMap<>();
        Map<WeaponMounted, List<AmmoMounted>> bayAmmo = new HashMap<>();
        for (WeaponMounted m : unit.getTotalWeaponList()) {
            bayWeapons.put(m, m.getBayWeapons());
            bayAmmo.put(m, m.getBayAmmo());
        }
        UnitUtil.removeOneShotAmmo(unit);

        if (unit instanceof Mech) {
            MekUtil.updateLoadedMech((Mech) unit);
        } else if (unit instanceof Aero) {
            AeroUtil.updateLoadedAero((Aero) unit);
        }
        // Replace bay weapon and ammo equipment numbers with the current index by looking
        // up the old index in the old list
        for (WeaponMounted bay : unit.getTotalWeaponList()) {
            if (bayWeapons.containsKey(bay)) {
                bay.clearBayWeapons();
                bay.clearBayAmmo();
                for (WeaponMounted w : bayWeapons.get(bay)) {
                    bay.addWeaponToBay(w);
                }
                for (AmmoMounted a : bayAmmo.get(bay)) {
                    bay.addAmmoToBay(a);
                }
            }
        }
    }

    public static boolean isUnitWeapon(EquipmentType eq, Entity unit) {
        if (unit instanceof Tank) {
            return TankUtil.isTankWeapon(eq, unit);
        }

        if (unit instanceof BattleArmor) {
            return BattleArmorUtil.isBattleArmorWeapon(eq, unit);
        }

        if (unit instanceof Infantry) {
            return InfantryUtil.isInfantryEquipment(eq, unit);
        }

        return MekUtil.isMechWeapon(eq, unit);
    }

    public static boolean isEntityEquipment(EquipmentType eq, Entity en) {
        if (en instanceof Mech) {
            return MekUtil.isMechEquipment(eq, (Mech) en);
        } else if (en instanceof Protomech) {
            return ProtoMekUtil.isProtomechEquipment(eq, (Protomech) en);
        } else if (en.isSupportVehicle()) {
            return isSupportVehicleEquipment(eq, en);
        } else if (en instanceof Tank) {
            return TankUtil.isTankEquipment(eq, (Tank) en);
        } else if (en instanceof BattleArmor) {
            return BattleArmorUtil.isBAEquipment(eq, (BattleArmor) en);
        } else if (en instanceof Aero) {
            return AeroUtil.isAeroEquipment(eq, (Aero) en);
        } else {
            return true;
        }
    }

    /**
     * Returns true if the given Equipment is available as equipment to the given Support
     * Vehicle. Includes WeaponTypes, AmmoTypes and MiscTypes.
     *
     * @param eq The equipment to test The tested equipment
     * @param unit The entity The support vehicles. May be an Aero or Tank subtype
     * @return true if the equipment is usable by the entity
     */
    public static boolean isSupportVehicleEquipment(EquipmentType eq, Entity unit) {
        if ((unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)
                && ((eq.getTonnage(unit) >= 5.0)
                || (eq instanceof MiscType) && eq.hasFlag(MiscType.F_HEAVY_EQUIPMENT))) {
            return false;
        }
        if ((eq instanceof MiscType) && !eq.hasFlag(MiscType.F_SUPPORT_TANK_EQUIPMENT)) {
            return false;
        } else if ((eq instanceof WeaponType)
            && (unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)) {
            // Small support vehicles can only mount infantry weapons
            return (eq instanceof InfantryWeapon)
                    && !eq.hasFlag(WeaponType.F_INF_ARCHAIC);
        } else if (eq instanceof AmmoType) {
            return true;
        }
        if (unit.isAero()) {
            return AeroUtil.isAeroEquipment(eq, (Aero) unit);
        } else {
            return TankUtil.isTankEquipment(eq, (Tank) unit);
        }
    }

    /**
     * remove all CriticalSlots on the passed unit that are internal structure or armor
     *
     * @param unit The entity the Entity
     * @param internalStructure true to remove IS, false to remove armor
     */
    public static void removeISorArmorCrits(Entity unit, boolean internalStructure) {
        List<String> mountList = new ArrayList<>();
        if (internalStructure) {
            for (String struc : EquipmentType.structureNames) {
                mountList.add("IS " + struc);
                mountList.add("Clan " + struc);
            }
        } else {
            mountList = ArmorType.allArmorTypes().stream().map(ArmorType::getInternalName).collect(Collectors.toList());
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
     * remove all Mounted on the passed unit that are internal structure or
     * armor
     *
     * @param unit The entity the Entity
     * @param internalStructure true to remove IS, false to remove armor
     */
    public static void removeISorArmorMounts(Entity unit, boolean internalStructure) {
        UnitUtil.removeISorArmorCrits(unit, internalStructure);
        ArrayList<String> mountList = new ArrayList<>();

        mountList.add("Standard");

        List<String> names;
        if (internalStructure) {
            names = Arrays.asList(EquipmentType.structureNames);
        } else {
            names = ArmorType.allArmorNames();
        }
        for (String name : names) {
            mountList.add(String.format("Clan %1s", name));
            mountList.add(String.format("IS %1s", name));
            mountList.add(name);
        }

        for (int pos = 0; pos < unit.getEquipment().size();) {
            Mounted mount = unit.getEquipment().get(pos);
            if (mountList.contains(mount.getType().getInternalName())) {
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

    /**
     * Remove all mounts for the current armor type from a single location on the passed unit
     * and sets the armor type in that location to standard.
     *
     * @param unit The entity The <code>Entity</code>
     * @param loc  The location from which to remove the armor mounts.
     */
    public static void resetArmor(Entity unit, int loc) {
        String name = EquipmentType.getArmorTypeName(unit.getArmorType(loc),
                TechConstants.isClan(unit.getArmorTechLevel(loc)));
        EquipmentType eq = EquipmentType.get(name);
        if (null != eq) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(loc); slot++) {
                final CriticalSlot crit = unit.getCritical(loc, slot);
                if ((null != crit) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)
                        && (null != crit.getMount()) && crit.getMount().getType().equals(eq)) {
                    unit.getMisc().remove(crit.getMount());
                    unit.setCritical(loc, slot, null);
                }
            }
        }
        unit.setArmorType(EquipmentType.T_ARMOR_STANDARD, loc);
        unit.setArmorTechLevel(TechConstants.T_INTRO_BOXSET, loc);
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

                if ((armor > 9) && !mech.isSuperHeavy()) {
                    foundError = true;
                    mech.initializeArmor(9, location);
                } else if (armor > 12) {
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
            JOptionPane.showMessageDialog(null,
                    "Too much armor found on this unit.\n\rMegaMekLab has automatically corrected the problem.\n\rIt is suggested you check the armor allocation.",
                    "Too much armor", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @param unit The entity the supplied entity
     * @return a TestEntity instance for the supplied Entity.
     */
    public static TestEntity getEntityVerifier(Entity unit) {
        EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
                "data/mechfiles/UnitVerifierOptions.xml")); // TODO : Remove inline file path
        TestEntity testEntity = null;

        if (unit.hasETypeFlag(Entity.ETYPE_MECH)) {
            testEntity = new TestMech((Mech) unit, entityVerifier.mechOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            testEntity = new TestProtomech((Protomech) unit, entityVerifier.protomechOption, null);
        } else if (unit.isSupportVehicle()) {
            testEntity = new TestSupportVehicle(unit, entityVerifier.tankOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_TANK)) {
            testEntity = new TestTank((Tank) unit, entityVerifier.tankOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            testEntity = new TestSmallCraft((SmallCraft) unit, entityVerifier.aeroOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            testEntity = new TestAdvancedAerospace((Jumpship) unit, entityVerifier.aeroOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
            testEntity = new TestAero((Aero) unit, entityVerifier.aeroOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            testEntity = new TestBattleArmor((BattleArmor) unit, entityVerifier.baOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_INFANTRY)) {
            testEntity = new TestInfantry((Infantry)unit, entityVerifier.infOption, null);
        }
        return testEntity;
    }


    /**
     * check that the unit is vaild
     *
     * @param unit The entity
     * @return
     */
    public static String validateUnit(Entity unit) {
        StringBuffer sb = new StringBuffer();
        TestEntity testEntity = getEntityVerifier(unit);

        if (testEntity != null) {
            testEntity.correctEntity(sb, unit.getTechLevel());
        }

        return sb.toString();
    }

    public static void removeAllMiscMounteds(Entity unit, BigInteger flag) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(flag)) {
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
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TARGCOMP)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    /**
     * Checks whether the equipment can be added to the location on the build tab
     * @param unit The entity      The Entity being designed
     * @param eq The equipment to test        The equipment
     * @param location  The location to add it
     * @return          Whether the location is valid
     */
    public static boolean isValidLocation(Entity unit, EquipmentType eq, int location) {
        if (unit instanceof BattleArmor) {
            // Can only be mounted in APM or armored glove; can't be added directly to location
            return !(eq instanceof WeaponType && eq.hasFlag(WeaponType.F_INFANTRY));
        }
        return TestEntity.isValidLocation(unit, eq, location, null);
    }

    /**
     * Makes the equipment mounted in one location identical to that in another location. Any equipment
     * previously in the target location that is does not match the source location is removed and
     * assigned to Entity.LOC_NONE.
     *
     * @param entity          The unit being modified
     * @param fromLoc         The source location index
     * @param toLoc           The target location index
     * @throws LocationFullException If the target location is full
     */
    public static void copyLocationEquipment(Entity entity, int fromLoc, int toLoc)
            throws LocationFullException{
        copyLocationEquipment(entity, fromLoc, toLoc, true, true);
    }

    /**
     * Makes the equipment mounted in one location identical to that in another location. Any equipment
     * previously in the target location that does not match the source location is removed and
     * assigned to Entity.LOC_NONE. This does not handle split location equipment.
     *
     * @param entity          The unit being modified
     * @param fromLoc         The source location index
     * @param toLoc           The target location index
     * @param includeForward  Whether to include forward-mounted equipment
     * @param includeRear     Whether to include rear-mounted equipment
     * @throws LocationFullException If the target location is full
     */
    public static void copyLocationEquipment(final Entity entity, final int fromLoc,
                                             final int toLoc, final boolean includeForward,
                                             final boolean includeRear)
            throws LocationFullException {
        // Remove any equipment already in the location, but keep a list of it
        // to reuse as much as possible.
        List<Mounted> removed = entity.getEquipment().stream()
                .filter(m -> m.getLocation() == toLoc)
                .filter(m -> m.isRearMounted() ? includeRear : includeForward)
                .collect(Collectors.toList());

        // Add to this any equipment that is already unequipped (= in Entity.LOC_NONE) and free to be used
        List<Mounted> unequipped = entity.getEquipment().stream()
                .filter(m -> m.getLocation() == Entity.LOC_NONE)
                .collect(Collectors.toList());

        removed.forEach(m -> UnitUtil.removeCriticals(entity, m));

        removed.stream()
                .filter(m -> !(m.getType() instanceof BayWeapon))
                .forEach(m-> changeMountStatus(entity, m, Entity.LOC_NONE, Entity.LOC_NONE, false));

        removed.stream()
                .filter(m -> (m.getType() instanceof BayWeapon))
                .forEach(m -> removeMounted(entity, m));

        removed.addAll(unequipped);

        // Now we go through the equipment in the location to copy and add it to the other location.
        // If there is a match in what we removed, use that. Otherwise, add the equipment to the
        // unit. If the unit uses weapon bays, we need to create them in the new location and fill
        // them. If the unit doesn't use bays, we will iterate through the crit slots to get the
        // equipment in the same order to be nice and tidy.
        if (entity.usesWeaponBays()) {
            List<WeaponMounted> bayList = entity.getWeaponBayList().stream()
                    .filter(bay -> (bay.getLocation() == fromLoc) && (bay.isRearMounted() ? includeRear : includeForward))
                    .collect(Collectors.toList());
            for (WeaponMounted bay : bayList) {
                if ((bay.getLocation() == fromLoc)
                        && (bay.isRearMounted() ? includeRear : includeForward)) {
                    WeaponMounted newBay = (WeaponMounted) Mounted.createMounted(entity, bay.getType());
                    entity.addEquipment(newBay, toLoc, bay.isRearMounted());
                    for (WeaponMounted bayW : bay.getBayWeapons()) {
                        WeaponMounted toAdd = (WeaponMounted) copyEquipment(entity, toLoc, bayW, removed);
                        newBay.addWeaponToBay(toAdd);
                    }
                    for (AmmoMounted ammo : bay.getBayAmmo()) {
                        Mounted<?> toAdd = copyEquipment(entity, toLoc, ammo, removed);
                        newBay.addAmmoToBay(entity.getEquipmentNum(toAdd));
                    }
                }
            }
            // Now we copy any other equipment
            List<MiscMounted> miscList = new ArrayList<>(entity.getMisc());
            for (MiscMounted m : miscList) {
                if ((m.getLocation() == fromLoc)
                        && (m.isRearMounted() ? includeRear : includeForward)) {
                    copyEquipment(entity, toLoc, m, removed);
                }
            }
        } else {
            for (int slot = 0; slot < entity.getNumberOfCriticals(fromLoc); slot++) {
                final CriticalSlot crit = entity.getCritical(fromLoc, slot);
                if ((null != crit) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    copyEquipment(entity, toLoc, crit.getMount(), removed);
                }
            }
        }

        // Link up Artemis, etc.
        try {
            MechFileParser.postLoadInit(entity);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
    }

    /**
     * Used by {@link #copyLocationEquipment(Entity, int, int, boolean, boolean)} to perform the actual
     * copy of equipment from one location to another.
     *
     * @param entity The entity be processed
     * @param toLoc  The location to copy the equipment to
     * @param toCopy The equipment to copy
     * @param reuse  A list of equipment to reuse if there is a copy available. If not, a new item will
     *               be created. Note that this modifies the contents of the list by removing the equipment
     *               mount that was reused
     * @return       The new equipment mount created in the new location
     * @throws LocationFullException
     *               If there are not enough slots in the new location to add the equipment.
     */
    private static Mounted copyEquipment(Entity entity, int toLoc, Mounted toCopy, List<Mounted> reuse)
            throws LocationFullException {
        Mounted toAdd = reuse.stream().filter(m -> m.getType().equals(toCopy.getType()))
                .findFirst().orElse(null);
        if (null != toAdd) {
            reuse.remove(toAdd);
        } else {
            toAdd = Mounted.createMounted(entity, toCopy.getType());
        }
        if (toCopy.getType() instanceof AmmoType) {
            toAdd.setSize(toCopy.getSize());
            toAdd.setShotsLeft(toCopy.getBaseShotsLeft());
        }
        entity.addEquipment(toAdd, toLoc, toCopy.isRearMounted());
        changeMountStatus(entity, toAdd, toLoc, Entity.LOC_NONE, toCopy.isRearMounted());
        removeHiddenAmmo(toAdd);
        return toAdd;
    }

    /**
     * If the given Mounted is a one-shot launcher or infantry weapon, this method removes the hidden
     * ammo linked to it, if any. During construction, we have no use of hidden ammo. Cannot
     * use {@link #removeOneShotAmmo(Entity)} here as it removes all ammo that has
     * no location (which is how hidden ammo is kept when a unit is loaded from file) but during construction
     * normal ammo may not yet have been allocated and also have no location.
     *
     * @param mounted The weapon to remove linked hidden ammo
     */
    public static void removeHiddenAmmo(Mounted mounted) {
        EquipmentType launcherType = mounted.getType();
        if ((launcherType instanceof WeaponType) && (launcherType.hasFlag(WeaponType.F_ONESHOT)
                || (((WeaponType) launcherType).getAmmoType() == AmmoType.T_INFANTRY))) {
            Mounted oneShotAmmo = mounted.getLinked();
            if (oneShotAmmo != null) {
                mounted.getEntity().getEquipment().remove(oneShotAmmo);
                mounted.getEntity().getAmmo().remove(oneShotAmmo);
                mounted.setLinked(null);
            }
        }
    }

    public static void showValidation(Entity entity, JFrame frame) {
        final String validation = UnitUtil.validateUnit(entity);
        if (validation.isBlank()) {
            PopupMessages.showUnitIsValid(frame);
        } else {
            PopupMessages.showUnitInvalidWarning(frame, validation);
        }
    }

    /**
     * Checks whether the unit has an weapon that uses the ammo type and the munition is legal for the
     * type of unit.
     *
     * @param unit The entity The unit
     * @param atype The ammo
     * @param includeOneShot If false, ignores one-shot weapons
     * @return Whether the unit can make use of the ammo
     */
    public static boolean canUseAmmo(Entity unit, AmmoType atype, boolean includeOneShot) {
        if ((unit instanceof BattleArmor) && !atype.hasFlag(AmmoType.F_BATTLEARMOR)) {
            return false;
        } else if (!(unit instanceof BattleArmor) && atype.hasFlag(AmmoType.F_BATTLEARMOR)) {
            return false;
        } else if (unit.hasETypeFlag(Entity.ETYPE_AERO) && !atype.canAeroUse()) {
            return false;
        } else if (atype.hasFlag(AmmoType.F_PROTOMECH) && !(unit instanceof Protomech)) {
            return false;
        }

        for (Mounted m : unit.getTotalWeaponList()) {
            if (m.getType() instanceof AmmoWeapon) {
                WeaponType wtype = (WeaponType) m.getType();
                if ((wtype.getAmmoType() == atype.getAmmoType())
                        && (wtype.getRackSize() == atype.getRackSize())
                        && (includeOneShot || !m.getType().hasFlag(WeaponType.F_ONESHOT))) {
                    return true;
                }
            } else if ((atype instanceof SmallWeaponAmmoType)
                    && ((SmallWeaponAmmoType) atype).isAmmoFor(m.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @deprecated Use {@link UnitUtil#checkEquipmentByTechLevel(Entity, ITechManager)} instead
     */
    @Deprecated
    public static void checkEquipmentByTechLevel(Entity unit) {
        Vector<Mounted> toRemove = new Vector<>();
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
            if (!UnitUtil.isLegal(unit, etype)) {
                toRemove.add(m);
            }
        }
        for (Mounted m : toRemove) {
            UnitUtil.removeMounted(unit, m);
        }
        if (unit instanceof Infantry) {
            Infantry pbi = (Infantry) unit;
            if ((null != pbi.getPrimaryWeapon())
                    && !UnitUtil.isLegal(unit, pbi.getPrimaryWeapon())) {
                InfantryUtil.replaceMainWeapon((Infantry) unit,
                        (InfantryWeapon) EquipmentType.get("Infantry Auto Rifle"), false);
            }
            if ((null != pbi.getSecondaryWeapon())
                    && !UnitUtil.isLegal(unit, pbi.getSecondaryWeapon())) {
                InfantryUtil.replaceMainWeapon((Infantry) unit, null, true);
            }
        }
    }

    /**
     * Checks for any equipment that is added on the equipment tab and removes any that is
     * no longer legal for the current year/tech base/tech level
     * @param unit The entity         The unit to check
     * @param techManager  The manager that handles the checking
     * @return             Whether any changes were made
     */
    public static boolean checkEquipmentByTechLevel(Entity unit, ITechManager techManager) {
        List<Mounted> toRemove = new ArrayList<>();
        ITechnology acTA = Entity.getArmoredComponentTechAdvancement();
        boolean dirty = false;
        for (Mounted m : unit.getEquipment()) {
            if (m.isArmored() && !techManager.isLegal(acTA)) {
                m.setArmored(false);
                updateCritsArmoredStatus(unit, m);
                dirty = true;
            }
            EquipmentType etype = m.getType();
            if (UnitUtil.isArmorOrStructure(etype)
                    || UnitUtil.isHeatSink(etype) || UnitUtil.isJumpJet(etype)) {
                continue;
            }
            if (etype instanceof MiscType
                    && (etype.hasFlag(MiscType.F_TSM)
                    || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || (etype.hasFlag(MiscType.F_MASC)
                        && !etype.hasSubType(MiscType.S_SUPERCHARGER) && !etype.hasSubType(MiscType.S_JETBOOSTER))
                    || etype.hasFlag(MiscType.F_SCM))) {
                continue;
            }
            if (!techManager.isLegal(etype)) {
                toRemove.add(m);
            }
        }
        dirty |= !toRemove.isEmpty();
        for (Mounted m : toRemove) {
            UnitUtil.removeMounted(unit, m);
        }
        if (unit instanceof Infantry) {
            Infantry pbi = (Infantry) unit;
            if ((null != pbi.getPrimaryWeapon())
                    && !techManager.isLegal(pbi.getPrimaryWeapon())) {
                dirty = true;
                InfantryUtil.replaceMainWeapon((Infantry) unit,
                        (InfantryWeapon) EquipmentType
                                .get("Infantry Auto Rifle"), false);
            }
            if ((null != pbi.getSecondaryWeapon())
                    && !techManager.isLegal(pbi.getSecondaryWeapon())) {
                dirty = true;
                InfantryUtil.replaceMainWeapon((Infantry) unit, null, true);
            }
            if (techManager.getTechLevel().ordinal() <= SimpleTechLevel.STANDARD.ordinal() && pbi.hasFieldWeapon()) {
                InfantryUtil.replaceFieldGun(pbi, null, 0);
            }
        }
        return dirty;
    }

    /**
     * Updates the manual BV value of the given entity. When manualBV is 0 or less,
     * the entity is set to not use a manual BV value and the manual BV is set to -1.
     * @param manualBV A manual BV value; values less than 1 remove the manual BV from the entity.
     */
    public static void setManualBV(int manualBV, Entity entity) {
        entity.setManualBV((manualBV > 0) ? manualBV : -1);
        entity.setUseManualBV(manualBV > 0);
    }

    /**
     * For MiscTypes of variable size such as Ladders this sets the size to the minimum
     * size which is equal to the step size (20m for Ladders, 0.5t for Cargo space and the like).
     */
    public static void setVariableSizeMiscTypeMinimumSize(Mounted mounted) {
        if ((mounted.getType() instanceof MiscType) && mounted.getType().isVariableSize()) {
            mounted.setSize(mounted.getType().variableStepSize());
        }
    }

    private UnitUtil() { }

    static boolean isNonMekOrTankWeapon(Entity unit, WeaponType weapon) {
        if (weapon.getTonnage(unit) <= 0) {
            return true;
        }

        if (weapon.isCapital() || weapon.isSubCapital()) {
            return true;
        }

        if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon))
                && (weapon.getRackSize() != 5)
                && (weapon.getRackSize() != 10)
                && (weapon.getRackSize() != 15)
                && (weapon.getRackSize() != 20)) {
            return true;
        }
        if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon))
                && (weapon.getRackSize() != 2)
                && (weapon.getRackSize() != 4)
                && (weapon.getRackSize() != 6)) {
            return true;
        }
        if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
            return true;
        }

        if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
            return true;
        }

        if (weapon.hasFlag(WeaponType.F_ENERGY)
                || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon
                        .getAmmoType() == AmmoType.T_PLASMA))) {

            return weapon.hasFlag(WeaponType.F_ENERGY)
                    && weapon.hasFlag(WeaponType.F_PLASMA)
                    && (weapon.getAmmoType() == AmmoType.T_NA);
        }
        return false;
    }
}