/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import megamek.client.Client;
import megamek.codeUtilities.StringUtility;
import megamek.common.CriticalSlot;
import megamek.common.Player;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.enums.TechBase;
import megamek.common.equipment.*;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.exceptions.LocationFullException;
import megamek.common.game.Game;
import megamek.common.interfaces.ITechManager;
import megamek.common.interfaces.ITechnology;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.MekFileParser;
import megamek.common.options.OptionsConstants;
import megamek.common.units.*;
import megamek.common.util.BuildingBlock;
import megamek.common.verifier.*;
import megamek.common.weapons.AmmoWeapon;
import megamek.common.weapons.autoCannons.HVACWeapon;
import megamek.common.weapons.autoCannons.RACWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megamek.common.weapons.defensivePods.BPodWeapon;
import megamek.common.weapons.defensivePods.MPodWeapon;
import megamek.common.weapons.flamers.VehicleFlamerWeapon;
import megamek.common.weapons.gaussRifles.GaussWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.lasers.clan.CLChemicalLaserWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.lrms.LRTWeapon;
import megamek.common.weapons.lrms.StreakLRMWeapon;
import megamek.common.weapons.mgs.MGWeapon;
import megamek.common.weapons.missiles.MRMWeapon;
import megamek.common.weapons.missiles.rocketLauncher.RLWeapon;
import megamek.common.weapons.missiles.thuunderbolt.ThunderboltWeapon;
import megamek.common.weapons.ppc.clan.CLPlasmaCannon;
import megamek.common.weapons.ppc.innerSphere.ISPlasmaRifle;
import megamek.common.weapons.srms.SRMWeapon;
import megamek.common.weapons.srms.SRTWeapon;
import megamek.common.weapons.srms.StreakSRMWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.PopupMessages;

public class UnitUtil {
    private static final MMLogger LOGGER = MMLogger.create(UnitUtil.class);

    private static Font rsFont = null;
    private static Font rsBoldFont = null;
    private static final Client dummyClient = new Client("", "", 0);
    private static final Player dummyPlayer = new Player(1, "");

    static {
        final Game game = dummyClient.getGame();
        game.getOptions().getOption(OptionsConstants.ADVANCED_STRATOPS_QUIRKS).setValue(true);
        game.getOptions().getOption(OptionsConstants.RPG_PILOT_ADVANTAGES).setValue(true);
        game.getOptions().getOption(OptionsConstants.RPG_MANEI_DOMINI).setValue(true);
        game.addPlayer(1, dummyPlayer);
        dummyClient.setLocalPlayerNumber(1);
    }

    /**
     * Returns a Client object that is used for internal calculations of the units.
     *
     * @return A Client object
     */
    public static Client getDummyClient() {
        return dummyClient;
    }

    /**
     * Tells us if the passed in {@link EquipmentType} is equipment that uses critical slots/mounted and is spread
     * across multiple locations
     * <p>
     * This batch of checks should be moved to the {@link MiscType} class and simplified vs a massive list here. Can be
     * a flag on the MiscType to denote "Fixed Location" and "Spread Equipment." MiscType#spreadable is already present
     * on the MiscType so adding the field just like MiscType#omniFixedOnly.
     *
     * @param eq The equipment to test
     *
     * @return True if EquipmentType is of MiscType, is Fixed, and is spread across multiple locations.
     */
    public static boolean isFixedLocationSpreadEquipment(EquipmentType eq) {
        return (eq instanceof MiscType) &&
              (eq.hasFlag(MiscType.F_JUMP_BOOSTER) ||
                    eq.hasFlag(MiscType.F_BA_MANIPULATOR) ||
                    eq.hasFlag(MiscType.F_PARTIAL_WING) ||
                    eq.hasFlag(MiscType.F_NULL_SIG) ||
                    eq.hasFlag(MiscType.F_VOID_SIG) ||
                    eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING) ||
                    eq.hasFlag(MiscType.F_TRACKS) ||
                    eq.hasFlag(MiscType.F_TALON) ||
                    (eq.hasFlag(MiscType.F_STEALTH) &&
                          (eq.hasFlag(MiscType.F_MEK_EQUIPMENT) || eq.hasFlag(MiscType.F_TANK_EQUIPMENT))) ||
                    eq.hasFlag(MiscType.F_CHAMELEON_SHIELD) ||
                    eq.hasFlag(MiscType.F_BLUE_SHIELD) ||
                    eq.hasFlag(MiscType.F_MAST_MOUNT) ||
                    eq.hasFlag(MiscType.F_SCM) ||
                    eq.hasFlag(MiscType.F_CHAIN_DRAPE) ||
                    (eq.hasFlag(MiscType.F_RAM_PLATE) ||
                          (eq.hasFlag(MiscType.F_JUMP_JET) && eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT)) ||
                          (eq.hasFlag(MiscType.F_UMU) && eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT)) ||
                          (eq.hasFlag(MiscType.F_MAGNETIC_CLAMP) &&
                                eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT)) ||
                          (eq.hasFlag(MiscType.F_MASC) && eq.hasFlag(MiscType.F_PROTOMEK_EQUIPMENT))));
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq The equipment to test
     */
    public static boolean isArmor(EquipmentType eq) {
        return eq instanceof ArmorType;
    }

    /**
     * tells if the EquipmentType is a type of armor
     *
     * @param eq The equipment to test
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
     */
    public static boolean isTSM(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_TSM) || eq.hasFlag((MiscType.F_INDUSTRIAL_TSM)));
    }

    /**
     * @param eq The equipment to test The equipmentType to check
     *
     * @return true if this is a Remote Sensor Dispenser (BA or vehicular)
     */
    public static boolean isRemoteSensorDispenser(EquipmentType eq) {
        return (eq instanceof MiscType) && eq.hasFlag(MiscType.F_SENSOR_DISPENSER);
    }

    /**
     * @param eq The equipment to test The equipmentType to check
     *
     * @return true if this is a Mine Dispenser (BA or vehicular)
     */
    public static boolean isMineDispenser(EquipmentType eq) {
        return (eq instanceof MiscType) && eq.hasFlag(MiscType.F_VEHICLE_MINE_DISPENSER);
    }

    /**
     * tells if EquipmentType is MASC
     *
     * @param eq The equipment to test
     */
    public static boolean isMASC(EquipmentType eq) {
        return (eq instanceof MiscType) && (eq.hasFlag(MiscType.F_MASC) && !eq.hasFlag(MiscTypeFlag.S_SUPERCHARGER));
    }

    /**
     * Returns the number of critical slots used by EquipmentType for each placement. For most equipment this is the
     * same as the total slots, but some spreadable equipment is allocated a single slot at a time, or split between
     * multiple locations.
     *
     * @param mount The equipment mount
     *
     * @return The number of slots per allocation
     */
    public static int getCritsUsed(Mounted<?> mount) {
        double toReturn = mount.getNumCriticalSlots();

        // if it's 0, we can return now (e.g. standard armor or IS, we don't
        // want that to count as 1 later on
        if (toReturn == 0) {
            return 0;
        }

        final EquipmentType eq = mount.getType();
        if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_PARTIAL_WING)) {
            toReturn = eq.isClan() ? 3 : 4;
        } else if ((eq instanceof MiscType) && eq.hasFlag(MiscType.F_CHAIN_DRAPE)) {
            toReturn = 3;
        } else if ((eq instanceof MiscType) &&
              (eq.hasFlag(MiscType.F_JUMP_BOOSTER) ||
                    eq.hasFlag(MiscType.F_TALON) ||
                    (eq.hasFlag(MiscType.F_STEALTH) && !(mount.getEntity() instanceof BattleArmor)))) {
            // Stealth armor is allocated 2 slots/location in Meks, but by individual slot for BA
            toReturn = 2;
        } else if (UnitUtil.isFixedLocationSpreadEquipment(eq) ||
              UnitUtil.isTSM(eq) ||
              UnitUtil.isArmorOrStructure(eq)) {
            toReturn = 1;
        }
        if ((mount.getEntity() instanceof Mek) && mount.getEntity().isSuperHeavy()) {
            toReturn = Math.ceil(toReturn / 2.0);
        }
        return (int) toReturn;
    }

    /**
     * Removes a piece of equipment from the Entity
     *
     * @param unit  The entity
     * @param mount The equipment
     */
    public static void removeMounted(Entity unit, Mounted<?> mount) {
        UnitUtil.removeCriticalSlots(unit, mount);

        // Some special checks for BA
        if (unit instanceof BattleArmor) {
            // If we're removing a DWP, and it has an attached weapon, we need to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK) && (mount.getLinked() != null)) {
                Mounted<?> link = mount.getLinked();
                link.setDWPMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }

            // If we are removing a weapon that is mounted in an DWP, we need to clear the mounted status of the DWP
            if ((mount.getLinkedBy() != null) &&
                  mount.getLinkedBy().getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)) {
                Mounted<?> dwp = mount.getLinkedBy();
                dwp.setLinked(null);
                dwp.setLinkedBy(null);
            }

            // If we're removing an APM, and it has an attached weapon, we need to detach the weapon
            if (mount.getType().hasFlag(MiscType.F_AP_MOUNT) && (mount.getLinked() != null)) {
                Mounted<?> link = mount.getLinked();
                link.setAPMMounted(false);
                link.setLinked(null);
                link.setLinkedBy(null);
            }

            // If we're removing a weapon that is mounted in an APM, we need to clear the mounted status of the AP
            // Mount.
            if ((mount.getLinkedBy() != null) && mount.getLinkedBy().getType().hasFlag(MiscType.F_AP_MOUNT)) {
                Mounted<?> apm = mount.getLinkedBy();
                apm.setLinked(null);
                apm.setLinkedBy(null);
            }
        }

        // We will need to reset the equipment numbers of the bay ammo and weapons
        Map<WeaponMounted, List<WeaponMounted>> bayWeapons = new HashMap<>();
        Map<WeaponMounted, List<AmmoMounted>> bayAmmo = new HashMap<>();
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

        if (mount instanceof WeaponMounted && bayWeapons.containsKey(mount)) {
            bayWeapons.get(mount).forEach(w -> {
                removeCriticalSlots(unit, w);
                changeMountStatus(unit, w, Entity.LOC_NONE, Entity.LOC_NONE, false);
            });
            bayAmmo.get(mount).forEach(a -> {
                removeCriticalSlots(unit, a);
                Mounted<?> moveTo = UnitUtil.findUnallocatedAmmo(unit, a.getType());

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
            for (AmmoMounted ammo = (AmmoMounted) mount.getLinked();
                  ammo != null;
                  ammo = (AmmoMounted) ammo.getLinked()) {
                osAmmo.add(ammo);
            }
            osAmmo.forEach(m -> {
                unit.getEquipment().remove(m);
                unit.getAmmo().remove(m);
            });
        }

        // It's possible that the equipment we are removing was linked to something else, and so the linkedBy state may
        // be set. We should remove it. Using getLinked could be unreliable, so we'll brute force it. An example of this
        // would be removing a linked Artemis IV FCS
        for (Mounted<?> m : unit.getEquipment()) {
            if (mount.equals(m.getLinkedBy())) {
                m.setLinkedBy(null);
            }
        }

        if ((mount.getType() instanceof MiscType) &&
              (mount.getType().hasFlag(MiscType.F_HEAD_TURRET) ||
                    mount.getType().hasFlag(MiscType.F_SHOULDER_TURRET) ||
                    mount.getType().hasFlag(MiscType.F_QUAD_TURRET))) {
            for (Mounted<?> m : unit.getEquipment()) {
                if (m.getLocation() == mount.getLocation()) {
                    m.setMekTurretMounted(false);
                }
            }
        }

        if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_SPONSON_TURRET)) {
            for (Mounted<?> m : unit.getEquipment()) {
                m.setSponsonTurretMounted(false);
            }
        }

        if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_PINTLE_TURRET)) {
            for (Mounted<?> m : unit.getEquipment()) {
                if (m.getLocation() == mount.getLocation()) {
                    m.setPintleTurretMounted(false);
                }
            }
        }
        unit.recalculateTechAdvancement();
    }

    /**
     * Removes all criticalSlots of the given unit.
     */
    synchronized public static void removeAllCriticalSlots(Entity unit) {
        removeAllCriticalSlotsFrom(unit, IntStream.range(0, unit.locations()).boxed().toList());

        // cleanup of remnants if any (should not be needed, but we never know)
        unit.getEquipment()
              .stream()
              .filter(m -> (m != null) &&
                    (m.getLocation() != Entity.LOC_NONE) &&
                    (!UnitUtil.isFixedLocationSpreadEquipment(m.getType())))
              .forEach(m -> {
                  UnitUtil.removeCriticalSlots(unit, m);
                  UnitUtil.changeMountStatus(unit, m, Entity.LOC_NONE, Entity.LOC_NONE, false);
              });
    }

    /**
     * Removes all criticalSlots from the given locations for the given unit.
     */
    synchronized public static void removeAllCriticalSlotsFrom(Entity unit, List<Integer> locations) {
        // Special handling for BattleArmor
        if (unit instanceof BattleArmor ba) {
            ba.getEquipment()
                  .stream()
                  .filter(m -> (m != null) && (m.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE))
                  .filter(m -> locations.contains(m.getBaMountLoc()))
                  .forEach(m -> {
                      m.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                      UnitUtil.changeMountStatus(unit, m, BattleArmor.LOC_SQUAD, BattleArmor.LOC_SQUAD, false);
                  });
            return;
        }
        // first we remove all criticalSlots
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (!locations.contains(loc)) {
                continue;
            }
            for (int i = 0; i < unit.getNumberOfCriticalSlots(loc); i++) {
                CriticalSlot cs = unit.getCritical(loc, i);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted<?> m1 = cs.getMount();
                    Mounted<?> m2 = cs.getMount2();
                    if ((m2 != null) && (!UnitUtil.isFixedLocationSpreadEquipment(m2.getType()))) {
                        UnitUtil.removeCriticalSlots(unit, m2);
                        UnitUtil.changeMountStatus(unit, m2, Entity.LOC_NONE, Entity.LOC_NONE, false);
                    }
                    if ((m1 != null) && (!UnitUtil.isFixedLocationSpreadEquipment(m1.getType()))) {
                        UnitUtil.removeCriticalSlots(unit, m1);
                        UnitUtil.changeMountStatus(unit, m1, Entity.LOC_NONE, Entity.LOC_NONE, false);
                    }
                }
            }
        }
        // cleanup of remnants if any (should not be needed, but we never know)
        unit.getEquipment()
              .stream()
              .filter(m -> (m != null) && locations.contains(m.getLocation()))
              .filter(m -> (m.getLocation() != Entity.LOC_NONE) &&
                    (!UnitUtil.isFixedLocationSpreadEquipment(m.getType())))
              .forEach(m -> {
                  UnitUtil.removeCriticalSlots(unit, m);
                  UnitUtil.changeMountStatus(unit, m, Entity.LOC_NONE, Entity.LOC_NONE, false);
              });
    }

    /**
     * Sets the corresponding critical slots to null for the Mounted object.
     *
     * @param unit The entity
     * @param eq   The equipment to test
     */
    public static void removeCriticalSlots(Entity unit, Mounted<?> eq) {
        if (eq.getLocation() == Entity.LOC_NONE) {
            return;
        }

        for (int loc = 0; loc < unit.locations(); loc++) {
            for (int slot = 0; slot < unit.getNumberOfCriticalSlots(loc); slot++) {
                CriticalSlot criticalSlot = unit.getCritical(loc, slot);
                if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    if ((criticalSlot.getMount() != null) && (criticalSlot.getMount().equals(eq))) {
                        // If there are two pieces of equipment in this slot, remove first one, and replace it with
                        // the second
                        if (criticalSlot.getMount2() != null) {
                            criticalSlot.setMount(criticalSlot.getMount2());
                            criticalSlot.setMount2(null);
                        } else {
                            // If it's the only Mounted, clear the slot
                            unit.setCritical(loc, slot, null);
                        }
                    } else if ((criticalSlot.getMount2() != null) && (criticalSlot.getMount2().equals(eq))) {
                        criticalSlot.setMount2(null);
                    }
                }
            }
        }
    }

    /**
     * Sets the corresponding critical slots to null for the Mounted object in the given location.
     *
     * @param unit The entity
     * @param eq   The equipment to test
     * @param loc  The location to remove critical slots from.
     */
    public static void removeCriticalSlots(Entity unit, Mounted<?> eq, int loc) {
        if (eq.getLocation() == Entity.LOC_NONE) {
            return;
        }

        for (int slot = 0; slot < unit.getNumberOfCriticalSlots(loc); slot++) {
            CriticalSlot criticalSlot = unit.getCritical(loc, slot);
            if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                if ((criticalSlot.getMount() != null) && (criticalSlot.getMount().equals(eq))) {
                    // If there are two pieces of equipment in this slot,
                    // remove first one, and replace it with the second
                    if (criticalSlot.getMount2() != null) {
                        criticalSlot.setMount(criticalSlot.getMount2());
                        criticalSlot.setMount2(null);
                    } else {
                        // If it's the only Mounted, clear the slot
                        unit.setCritical(loc, slot, null);
                    }
                } else if ((criticalSlot.getMount2() != null) && (criticalSlot.getMount2().equals(eq))) {
                    criticalSlot.setMount2(null);
                }
            }
        }
    }

    public static void addMounted(Entity unit, Mounted<?> mounted, int loc, boolean rearMounted)
          throws LocationFullException {
        unit.addEquipment(mounted, loc, rearMounted);
        mounted.setOmniPodMounted(canPodMount(unit, mounted));

        if (unit instanceof Mek mekUnit) {
            MekUtil.updateClanCasePlacement(mekUnit);
        }
    }

    /**
     * Updates TC Critical Slots and Mounts based on weapons on a unit or if the TC has been removed.
     *
     * @param unit The entity
     */
    public static Mounted<?> updateTC(Entity unit, EquipmentType tc) {
        UnitUtil.removeTC(unit);
        return UnitUtil.createTCMounts(unit, tc);
    }

    /**
     * Creates TC Mount.
     *
     * @param unit The entity
     */
    public static @Nullable Mounted<?> createTCMounts(Entity unit, EquipmentType tc) {
        try {
            return unit.addEquipment(tc, Entity.LOC_NONE);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Checks to see if unit can use the tech level
     *
     * @param unit The entity
     *
     * @return Boolean if the tech level is legal for the passed unit
     */
    public static boolean isLegal(Entity unit, ITechnology tech) {
        if (unit.isMixedTech()) {
            if (!tech.isAvailableIn(unit.getTechLevelYear(), CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                return false;
            }
        } else {
            if (tech.getTechBase() != TechBase.ALL && unit.isClan() != tech.isClan()) {
                return false;
            }

            if (!tech.isAvailableIn(unit.getTechLevelYear(),
                  unit.isClan(),
                  CConfig.getBooleanParam(CConfig.TECH_EXTINCT))) {
                return false;
            }
        }
        return TechConstants.convertFromNormalToSimple(tech.getTechLevel(unit.getTechLevelYear(), unit.isClan())) <=
              TechConstants.convertFromNormalToSimple(unit.getTechLevel());
    }

    /**
     * checks if Mounted is a heat sink
     *
     * @param eq The equipment to test
     */
    public static boolean isHeatSink(Mounted<?> eq) {
        return ((eq.getType() != null) && isHeatSink(eq.getType()));
    }

    /**
     * Checks if EquipmentType is a heat sink
     *
     * @param eq The equipment to test
     */
    public static boolean isHeatSink(EquipmentType eq) {
        return isHeatSink(eq, false);
    }

    public static boolean isHeatSink(EquipmentType eq, boolean ignorePrototype) {
        return (eq instanceof MiscType) &&
              (eq.hasFlag(MiscType.F_HEAT_SINK) ||
                    eq.hasFlag(MiscType.F_LASER_HEAT_SINK) ||
                    eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK) ||
                    (eq.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE) && !ignorePrototype));
    }

    /**
     * Checks if EquipmentType is a Mek Physical weapon.
     *
     * @param eq The equipment to test The equipment to check
     *
     * @return Whether the equipment is a physical weapon
     */
    public static boolean isPhysicalWeapon(EquipmentType eq) {
        if (!(eq instanceof MiscType)) {
            return false;
        }
        if (eq.hasFlag(MiscType.F_CLUB)) {
            // We don't want makeshift clubs picked up on the battlefield showing up as
            // construction options
            return !eq.hasAnyFlag(MiscTypeFlag.S_CLUB, MiscTypeFlag.S_TREE_CLUB);
        }
        return eq.hasFlag(MiscType.F_HAND_WEAPON) || eq.hasFlag(MiscType.F_TALON) || eq.hasFlag(MiscType.F_RAM_PLATE);
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

    public static boolean isJumpJet(Mounted<?> m) {
        return (m.getType() instanceof MiscType) &&
              (m.getType().hasFlag(MiscType.F_JUMP_JET) || m.getType().hasFlag(MiscType.F_JUMP_BOOSTER));
    }

    /**
     * @param type The value returned by {@link Mek#getJumpType()}
     *
     * @return The {@link EquipmentType} lookup key for the jump jet
     */
    public static String getJumpJetType(int type) {
        if (type == Mek.JUMP_IMPROVED) {
            return EquipmentTypeLookup.IMPROVED_JUMP_JET;
        } else if (type == Mek.JUMP_PROTOTYPE) {
            return EquipmentTypeLookup.PROTOTYPE_JUMP_JET;
        } else if (type == Mek.JUMP_PROTOTYPE_IMPROVED) {
            return EquipmentTypeLookup.PROTOTYPE_IMPROVED_JJ;
        }
        return EquipmentTypeLookup.JUMP_JET;
    }

    /**
     * Checks whether equipment can be linked to a weapon to enhance it (e.g. Artemis, PPC Capacitor, etc.).
     *
     * @param type The equipment to check
     *
     * @return true if the equipment is a MiscType that can be linked to a weapon.
     */
    public static boolean isWeaponEnhancement(EquipmentType type) {
        return (type instanceof MiscType) &&
              (type.hasFlag(MiscType.F_ARTEMIS) ||
                    type.hasFlag(MiscType.F_ARTEMIS_V) ||
                    type.hasFlag(MiscType.F_ARTEMIS_PROTO) ||
                    type.hasFlag(MiscType.F_APOLLO) ||
                    type.hasFlag(MiscType.F_PPC_CAPACITOR) ||
                    type.hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE));
    }

    /**
     * Changes the location for a Mounted instance. Note: for BattleArmor, this effects which suit the equipment is
     * placed on (as that is what Mounted. Location means for BA), but not where on the suit it's located (ie,
     * BAMountLocation isn't affected). BattleArmor should change this outside of this method.
     *
     * @param unit              The entity The unit being modified
     * @param eq                The equipment to test The equipment mount to move
     * @param location          The location to move the mount to
     * @param secondaryLocation The secondary location for split equipment, otherwise
     *                          {@link Entity#LOC_NONE Entity.LOC_NONE}
     * @param rear              Whether to mount with a rear facing
     */
    public static void changeMountStatus(Entity unit, Mounted<?> eq, int location, int secondaryLocation,
          boolean rear) {
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
                MekFileParser.postLoadInit(unit);
            } catch (Exception ignored) {
                // Exception thrown for not having equipment to link to yet, which is acceptable
                // here
            }
        }
        if (unit instanceof Mek) {
            MekUtil.updateClanCasePlacement((Mek) unit);
        }
    }

    public static void resizeMount(Mounted<?> mount, double newSize) {
        mount.setSize(newSize);
        if (mount.getLocation() == Entity.LOC_NONE) {
            return;
        }
        final Entity entity = mount.getEntity();
        // Meks may need to shift the critical slots around to make room if the equipment grows
        if (entity instanceof Mek) {
            final int loc = mount.getLocation();
            int start = -1;

            for (int slot = 0; slot < entity.getNumberOfCriticalSlots(loc); slot++) {
                CriticalSlot criticalSlot = entity.getCritical(loc, slot);
                if ((criticalSlot != null) &&
                      (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT) &&
                      criticalSlot.getMount().equals(mount)) {
                    start = slot;
                    break;
                }
            }

            removeCriticalSlots(entity, mount);
            compactCriticalSlots(entity, loc);
            if ((start < 0) || (entity.getEmptyCriticalSlots(loc) < mount.getNumCriticalSlots())) {
                changeMountStatus(entity, mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            } else {
                // If the number of critical slots increases, we may need to shift existing critical slots to make
                // room. Since we checked for sufficient space and compacted the existing critical slots we can be
                // assured of not overrunning the array.
                List<CriticalSlot> toAdd = new ArrayList<>();
                for (int i = 0; i < mount.getNumCriticalSlots(); i++) {
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
     * Find unallocated ammo of the same type. Used by large aerospace units when removing ammo from a location to find
     * the group to add it to.
     *
     * @param unit The entity
     * @param at   The type of armor to match
     *
     * @return An unallocated non-one-shot ammo mount of the same type, or null if there is not one.
     */
    public static Mounted<?> findUnallocatedAmmo(Entity unit, EquipmentType at) {
        for (Mounted<?> m : unit.getAmmo()) {
            if ((m.getLocation() == Entity.LOC_NONE) &&
                  at.equals(m.getType()) &&
                  ((m.getLinkedBy() == null) || !m.getLinkedBy().getType().hasFlag(WeaponType.F_ONE_SHOT))) {
                return m;
            }
        }
        return null;
    }

    /**
     * Checks whether the equipment is eligible for pod mounting in an omni unit, either because the equipment itself
     * can never be pod-mounted (such as armor, structure, or myomer enhancements), or the number of fixed heat sinks
     * have not been assigned locations.
     *
     * @param unit The entity
     * @param eq   The equipment to test
     */
    public static boolean canPodMount(Entity unit, Mounted<?> eq) {
        if (!unit.isOmni() || eq.getType().isOmniFixedOnly()) {
            return false;
        }

        if (eq.getType() instanceof MiscType &&
              unit instanceof Mek &&
              (eq.getType().hasFlag(MiscType.F_HEAT_SINK) ||
                    eq.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) ||
                    eq.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) &&
              unit.hasEngine()) {
            int needed = Math.max(0,
                  unit.getEngine().getWeightFreeEngineHeatSinks() -
                        UnitUtil.getCriticalFreeHeatSinks(unit, ((Mek) unit).hasCompactHeatSinks()));
            long fixed = unit.getMisc()
                  .stream()
                  .filter(m -> (m.getType().hasFlag(MiscType.F_HEAT_SINK) ||
                        m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) ||
                        m.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) &&
                        m.getLocation() != Entity.LOC_NONE &&
                        !m.isOmniPodMounted())
                  .count();
            // Do not count this heat among the fixed, since we are checking whether we can
            // change it to pod-mounted
            if (eq.getLocation() != Entity.LOC_NONE && !eq.isOmniPodMounted()) {
                fixed--;
            }
            return fixed >= needed;
        }
        return true;
    }

    /**
     * Removes all pod-mounted equipment from an omni unit
     *
     * @param unit The entity
     */
    public static void resetBaseChassis(Entity unit) {
        if (!unit.isOmni()) {
            return;
        }
        List<Mounted<?>> pods = unit.getEquipment().stream().filter(Mounted::isOmniPodMounted).toList();
        for (Mounted<?> m : pods) {
            UnitUtil.removeMounted(unit, m);
            if (m.getType() instanceof MiscType && m.getType().hasFlag(MiscType.F_JUMP_JET)) {
                unit.setOriginalJumpMP(unit.getOriginalJumpMP() - 1);
            }
        }
        List<Transporter> transporters = unit.getTransports().stream().filter(unit::isPodMountedTransport).toList();
        transporters.forEach(unit::removeTransporter);
    }

    public static boolean hasTargComp(Entity unit) {
        for (Mounted<?> mount : unit.getEquipment()) {
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TARGETING_COMPUTER)) {
                return true;
            }
        }

        return false;
    }

    public static int[] getHighestContinuousNumberOfCritsArray(Mek unit) {
        int[] criticalSpaces = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };

        for (int loc = 0; loc <= Mek.LOC_LEFT_LEG; loc++) {
            criticalSpaces[loc] = UnitUtil.getHighestContinuousNumberOfCrits(unit, loc);
        }

        return criticalSpaces;
    }

    public static int getHighestContinuousNumberOfCrits(Entity unit, int location) {
        int highestNumberOfCriticalSlots = 0;
        int currentCriticalSlotCount = 0;

        // Handle locations without critical clots
        if ((location == Entity.LOC_DESTROYED) || (location == Entity.LOC_NONE)) {
            return 0;
        }

        for (int slot = 0; slot < unit.getNumberOfCriticalSlots(location); slot++) {
            if (unit.getCritical(location, slot) == null) {
                currentCriticalSlotCount++;
            } else {
                currentCriticalSlotCount = 0;
            }
            highestNumberOfCriticalSlots = Math.max(currentCriticalSlotCount, highestNumberOfCriticalSlots);
        }

        return highestNumberOfCriticalSlots;
    }

    public static double getUnallocatedAmmoTonnage(Entity unit) {
        double tonnage = 0;

        for (Mounted<?> mount : unit.getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) &&
                  !mount.isOneShotAmmo() &&
                  (((AmmoType) mount.getType()).getAmmoType() != AmmoType.AmmoTypeEnum.INFANTRY)) {
                int slots = 1;
                if (unit.usesWeaponBays()) {
                    slots = (int) Math.ceil(mount.getUsableShotsLeft() /
                          (double) ((AmmoType) mount.getType()).getShots());
                }
                tonnage += slots * mount.getTonnage();
            }
        }

        return tonnage;
    }

    public static int getMaximumArmorPoints(Entity unit) {
        int points = 0;
        if (unit.hasETypeFlag(Entity.ETYPE_MEK)) {
            int headPoints = 3;
            if (unit.getWeightClass() == EntityWeightClass.WEIGHT_SUPER_HEAVY) {
                headPoints = 4;
            }
            points = (unit.getTotalInternal() * 2) + headPoints;
        } else if (unit.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
            points = TestProtoMek.maxArmorFactor((ProtoMek) unit);
        } else if (unit.isSupportVehicle()) {
            points = TestSupportVehicle.maxArmorFactor(unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_TANK)) {
            points = (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else if (unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            points = (unit.getWeightClass() * 4) + 2;
        } else if (unit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            points = TestAdvancedAerospace.maxArmorPoints((Jumpship) unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            points = TestSmallCraft.maxArmorPoints((SmallCraft) unit);
        } else if (unit.hasETypeFlag(Entity.ETYPE_CONV_FIGHTER)) {
            points = (int) Math.floor(unit.getWeight());
        } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
            points = (int) Math.floor(unit.getWeight() * 8);
        }
        return points;
    }

    public static int getMaximumArmorPoints(Entity unit, int loc) {
        if ((unit instanceof Mek) && (loc == Mek.LOC_HEAD)) {
            if (unit.isSuperHeavy()) {
                return 12;
            } else {
                return 9;
            }
        } else if (unit instanceof Mek) {
            return unit.getInternal(loc) * 2;
        } else if (unit.isSupportVehicle()) {
            return TestSupportVehicle.maxArmorFactor(unit);
        } else if (unit instanceof Tank) {
            if ((unit instanceof VTOL) && (loc == VTOL.LOC_ROTOR)) {
                return 2;
            }
            return (int) Math.floor((unit.getWeight() * 3.5) + 40);
        } else if (unit instanceof ProtoMek) {
            return TestProtoMek.maxArmorFactor((ProtoMek) unit, loc);
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
        if (unit instanceof Mek) {
            double points = (unit.getTotalInternal() * 2);
            // Add in extra armor points for head
            if (unit.isSuperHeavy()) {
                points += 4;
            } else {
                points += 3;
            }
            armorWeight = points / armorPerTon;
            armorWeight = Math.ceil(armorWeight * 2.0) / 2.0;
        } else if (unit instanceof ProtoMek) {
            double points = TestProtoMek.maxArmorFactor((ProtoMek) unit);
            return points * ArmorType.forEntity(unit).getWeightPerPoint();
        } else if (unit.isSupportVehicle()) {
            // Max armor is determined by number of points.
            double weight = TestSupportVehicle.maxArmorFactor(unit) * TestSupportVehicle.armorWeightPerPoint(unit);
            if (unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
                return TestEntity.round(weight, Ceil.KILO);
            } else {
                return TestEntity.ceil(weight, Ceil.HALF_TON);
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
     * Calculate the number of armor points per ton of armor for the given unit.
     *
     * @param en The unit
     *
     * @return The number of armor points per ton
     */
    public static double getArmorPointsPerTon(Entity en) {
        ArmorType armor = ArmorType.forEntity(en);
        if (armor.hasFlag(MiscType.F_SUPPORT_VEE_BAR_ARMOR)) {
            return 1.0 / armor.getSVWeightPerPoint(en.getArmorTechRating());
        } else {
            return armor.getPointsPerTon(en);
        }
    }

    public static void compactCriticalSlots(Entity unit) {
        for (int loc = 0; loc < unit.locations(); loc++) {
            if (unit instanceof Mek) {
                MekUtil.compactCriticalSlots((Mek) unit, loc);
            } else {
                compactCriticalSlots(unit, loc);
            }
        }
    }

    /**
     * Determine the maximum number of armor points that can be mounted in a location.
     *
     * @return The maximum number of armor points for the location, or null if there is no maximum.
     */
    public static @Nullable Integer getMaxArmor(Entity entity, int location) {
        if ((location < 0) || (location >= entity.locations())) {
            return 0;
        }
        if (entity.hasETypeFlag(Entity.ETYPE_MEK)) {
            if (location == Mek.LOC_HEAD) {
                return (entity.getWeightClass() == EntityWeightClass.WEIGHT_SUPER_HEAVY) ? 12 : 9;
            } else {
                return entity.getOInternal(location) * 2;
            }
        } else if (entity.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
            return TestProtoMek.maxArmorFactor((ProtoMek) entity, location);
        } else if ((entity instanceof VTOL) && (location == VTOL.LOC_ROTOR)) {
            return 2;
        }
        return null;
    }

    public static void compactCriticalSlots(Entity unit, int loc) {
        int firstEmpty = -1;
        for (int slot = 0; slot < unit.getNumberOfCriticalSlots(loc); slot++) {
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
        return (weapon instanceof AmmoWeapon) &&
              !(weapon instanceof StreakLRMWeapon) &&
              !(weapon instanceof StreakSRMWeapon) &&
              !(weapon instanceof GaussWeapon) &&
              !(weapon instanceof UACWeapon) &&
              !(weapon instanceof HVACWeapon) &&
              !(weapon instanceof MGWeapon) &&
              !(weapon instanceof ThunderboltWeapon) &&
              !(weapon instanceof CLChemicalLaserWeapon) &&
              !(weapon instanceof MPodWeapon) &&
              !(weapon instanceof BPodWeapon) &&
              !(weapon instanceof ISPlasmaRifle) &&
              !(weapon instanceof CLPlasmaCannon) &&
              !(weapon instanceof VehicleFlamerWeapon) &&
              !UnitUtil.isAMS(weapon);
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
        ArrayList<Mounted<?>> ammoList = new ArrayList<>();

        for (Mounted<?> mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                ammoList.add(mount);
            }
        }

        for (Mounted<?> mount : ammoList) {
            unit.getEquipment().remove(mount);

            if (mount instanceof AmmoMounted) {
                unit.getAmmo().remove(mount);
            }
        }
    }

    public static boolean hasAmmo(Entity unit, int location) {
        for (Mounted<?> mount : unit.getEquipment()) {
            if (mount.getType().isExplosive(mount, true) &&
                  ((mount.getLocation() == location) || (mount.getSecondLocation() == location))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks to see if something is a Jump Jet
     *
     * @param eq The equipment to test
     */
    public static boolean isJumpJet(EquipmentType eq) {
        return (eq instanceof MiscType) &&
              (eq.hasFlag(MiscType.F_JUMP_JET) ||
                    eq.hasFlag(MiscType.F_UMU) ||
                    eq.hasFlag(MiscType.F_BA_VTOL));
    }

    /**
     * @return the name of the given equipment with (IS) or (Clan) added for mixed tech units when appropriate.
     */
    public static String getCritName(Entity unit, EquipmentType eq) {
        String name = eq.getName();
        // Only shorten non-ammo; getShortName leaves off "Ammo" and "[Half]" that we
        // want
        if (name.length() > 22 && !(eq instanceof AmmoType)) {
            name = eq.getShortName();
        }
        if (unit.isMixedTech() &&
              (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_ALLOWED_ALL) &&
              (eq.getTechLevel(unit.getTechLevelYear()) != TechConstants.T_TECH_UNKNOWN)) {
            if (unit.isClan() && !TechConstants.isClan(eq.getTechLevel(unit.getTechLevelYear()))) {
                name += " (IS)";
            } else if (!unit.isClan() && TechConstants.isClan(eq.getTechLevel(unit.getTechLevelYear()))) {
                name += " (Clan)";
            }
        }
        return name;
    }

    /**
     * Return the number of critical-space free heat sinks that the given entity can have.
     *
     * @param unit    The entity The unit mounting the heat sinks
     * @param compact Whether the heat sinks are compact or not
     *
     * @return The number of critical-free heat sinks.
     */
    public static int getCriticalFreeHeatSinks(Entity unit, boolean compact) {
        int engineHSCapacity = unit.getEngine().integralHeatSinkCapacity(compact);

        if (unit.isOmni()) {
            engineHSCapacity = Math.min(engineHSCapacity, unit.getEngine().getBaseChassisHeatSinks(compact));
        }

        return engineHSCapacity;
    }

    /**
     * Returns the total heat generation of the entity
     */
    public static int getTotalHeatGeneration(Entity entity) {
        return getTotalHeatGeneration(entity, true);
    }

    /**
     * Returns the total heat generation of the entity
     *
     * @param countOneShots If false, one-shot weapons are excluded.
     */
    public static int getTotalHeatGeneration(Entity entity, boolean countOneShots) {
        return entity.getEquipment().stream().mapToInt(m -> {
            var heat = m.getType().getHeat();
            if (m instanceof WeaponMounted wm) {
                if (!countOneShots && wm.getType().hasFlag(WeaponType.F_ONE_SHOT)) {
                    return 0;
                }

                if (wm.getLinkedBy() instanceof MiscMounted linkMount) {
                    if (linkMount.getType().hasFlag(MiscTypeFlag.F_LASER_INSULATOR)) {
                        heat--;
                    } else if (linkMount.getType().hasFlag(MiscTypeFlag.F_PPC_CAPACITOR)
                          && wm.hasChargedOrChargingCapacitor() == 0) {
                        // If the capacitor is charged it will have been counted already, only add 5 heat if it's
                        // not charged. For Lab purposes, we always pretend that the capacitor is charged.
                        heat += 5;
                    }
                }

                // Order matters since RACWeapons are UACWeapons
                if (wm.getType() instanceof RACWeapon) {
                    heat *= 6;
                } else if (wm.getType() instanceof UACWeapon) {
                    heat *= 2;
                }
            }
            return heat;
        }).sum();
    }

    /**
     * Determines if the previous critical slot is empty.
     *
     * @param unit     Unit to check.
     * @param slot     Critical Slot Location
     * @param location Unit Location
     *
     * @return True if previous is empty.
     */
    public static boolean isPreviousCriticalSlotEmpty(Entity unit, int slot, int location) {
        if (slot == 0) {
            return false;
        } else if (unit instanceof Mek) {
            return (slot <= 0) || (unit.getCritical(location, slot - 1) == null);
        } else {
            return true;
        }
    }

    public static boolean isLastCrit(Entity unit, CriticalSlot cs, int slot, int location) {
        if (unit instanceof Mek unitMek) {
            return MekUtil.isLastMekCrit(unitMek, cs, slot, location);
        }

        return true;
    }

    /**
     * Finds all the critical slots in the location containing the mount and sets or clears the armored component flag
     * in accordance with the flag on the mount.
     *
     * @param unit     The entity The unit the equipment is mounted on
     * @param mount    The mount
     * @param location The location to check
     */
    public static void updateCritsArmoredStatus(Entity unit, Mounted<?> mount, int location) {
        for (int position = 0; position < unit.getNumberOfCriticalSlots(location); position++) {
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
     * Sets the armored component flag on all critical slots occupied by an equipment mount to be the same as the flag
     * on the mount.
     *
     * @param unit  The entity The unit the equipment is on
     * @param mount The equipment mount
     */
    public static void updateCritsArmoredStatus(Entity unit, Mounted<?> mount) {
        /*
         * Several types of equipment have multiple fixed locations. These
         * are always mounted in the primary location and added to critical
         * slots in the other location(s). Examples are partial wing (both side torsos)
         * and Mek tracks (all legs). Rather than dealing with each piece of equipment
         * individually and risking missing one, just check everywhere.
         */
        if (isFixedLocationSpreadEquipment(mount.getType())) {
            for (int loc = 0; loc < unit.locations(); loc++) {
                updateCritsArmoredStatus(unit, mount, loc);
            }
        } else {
            updateCritsArmoredStatus(unit, mount, mount.getLocation());

            if ((mount.isSplitable() || mount.getType().isSpreadable()) &&
                  (mount.getSecondLocation() != Entity.LOC_NONE)) {
                updateCritsArmoredStatus(unit, mount, mount.getSecondLocation());
            }
        }
    }

    public static void updateCritsArmoredStatus(Entity unit, @Nullable CriticalSlot cs, int location) {
        if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            return;
        }

        if (cs.getIndex() <= Mek.SYSTEM_GYRO) {
            for (int loc = Mek.LOC_HEAD; loc <= Mek.LOC_LEFT_TORSO; loc++) {
                for (int slot = 0; slot < unit.getNumberOfCriticalSlots(loc); slot++) {
                    CriticalSlot newCrit = unit.getCritical(loc, slot);

                    if ((newCrit != null) &&
                          (newCrit.getType() == CriticalSlot.TYPE_SYSTEM) &&
                          (newCrit.getIndex() == cs.getIndex())) {
                        newCrit.setArmored(cs.isArmored());
                    }
                }
            }
        } else {
            // actuators
            for (int slot = 0; slot < unit.getNumberOfCriticalSlots(location); slot++) {
                CriticalSlot newCriticalSlot = unit.getCritical(location, slot);

                if ((newCriticalSlot != null) &&
                      (newCriticalSlot.getType() == CriticalSlot.TYPE_SYSTEM) &&
                      (newCriticalSlot.getIndex() == cs.getIndex())) {
                    newCriticalSlot.setArmored(cs.isArmored());
                }
            }
        }
    }

    public static boolean isArmorOrStructure(EquipmentType eq) {
        return UnitUtil.isArmor(eq) || UnitUtil.isStructure(eq);
    }

    public static void updateLoadedUnit(Entity unit) {
        // Add Entity to a dummy game
        unit.setGame(getDummyClient().getGame());
        unit.setOwner(getDummyClient().getLocalPlayer());

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

        if (unit instanceof Mek) {
            MekUtil.updateLoadedMek((Mek) unit);
        } else if (unit instanceof Aero) {
            AeroUtil.updateLoadedAero((Aero) unit);
        }
        // Replace bay weapon and ammo equipment numbers with the current index by
        // looking
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

        return MekUtil.isMekWeapon(eq, unit);
    }

    public static boolean isEntityEquipment(EquipmentType eq, Entity en) {
        if (en instanceof Mek) {
            return MekUtil.isMekEquipment(eq, (Mek) en);
        } else if (en instanceof ProtoMek) {
            return ProtoMekUtil.isProtoMekEquipment(eq, (ProtoMek) en);
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
     * Returns true if the given Equipment is available as equipment to the given Support Vehicle. Includes WeaponTypes,
     * AmmoTypes and MiscTypes.
     *
     * @param type The equipment to test
     * @param unit The support vehicle (an Aero or Tank subclass!)
     *
     * @return true if the equipment is usable by the entity
     */
    public static boolean isSupportVehicleEquipment(EquipmentType type, Entity unit) {
        if (TestSupportVehicle.isSmallSupportVehicle(unit) && (type.getTonnage(unit) >= 5.0)) {
            return false;
        } else if ((type instanceof MiscType) && !type.hasFlag(MiscType.F_SUPPORT_TANK_EQUIPMENT)) {
            return false;
        } else if ((type instanceof WeaponType) && TestSupportVehicle.isSmallSupportVehicle(unit)) {
            return TestSupportVehicle.isSmallSupportVehicleWeapon(type);
        } else if (type instanceof AmmoType) {
            return true;
        }
        //TODO: Align with TestSupportVehicle:
        if (unit.isAero()) {
            return AeroUtil.isAeroEquipment(type, (Aero) unit);
        } else {
            return TankUtil.isTankEquipment(type, (Tank) unit);
        }
    }

    /**
     * remove all CriticalSlots on the passed unit that are internal structure or armor
     *
     * @param unit              The entity
     * @param internalStructure true to remove IS, false to remove armor
     */
    public static void removeISorArmorCrits(Entity unit, boolean internalStructure) {
        List<String> mountList = new ArrayList<>();
        if (internalStructure) {
            for (String structureName : EquipmentType.structureNames) {
                mountList.add("IS " + structureName);
                mountList.add("Clan " + structureName);
            }
        } else {
            mountList = ArmorType.allArmorTypes().stream().map(ArmorType::getInternalName).collect(Collectors.toList());
        }

        for (int location = Mek.LOC_HEAD; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticalSlots(location); slot++) {
                CriticalSlot criticalSlot = unit.getCritical(location, slot);
                if ((criticalSlot != null) && (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    Mounted<?> mount = criticalSlot.getMount();

                    if ((mount != null) &&
                          (mount.getType() instanceof MiscType) &&
                          mountList.contains(mount.getType().getInternalName())) {
                        unit.setCritical(location, slot, null);
                    }
                }
            }
        }
    }

    /**
     * remove all Mounted on the passed unit that are internal structure or armor
     *
     * @param unit              The entity
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

        for (int pos = 0; pos < unit.getEquipment().size(); ) {
            Mounted<?> mount = unit.getEquipment().get(pos);
            if (mountList.contains(mount.getType().getInternalName())) {
                unit.getEquipment().remove(pos);
            } else {
                pos++;
            }
        }

        for (int pos = 0; pos < unit.getMisc().size(); ) {
            Mounted<?> mount = unit.getMisc().get(pos);
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

    /**
     * Remove all mounts for the current armor type from a single location on the passed unit and sets the armor type in
     * that location to standard.
     *
     * @param unit The entity The <code>Entity</code>
     * @param loc  The location from which to remove the armor mounts.
     */
    public static void resetArmor(Entity unit, int loc) {
        String name = EquipmentType.getArmorTypeName(unit.getArmorType(loc),
              TechConstants.isClan(unit.getArmorTechLevel(loc)));

        EquipmentType eq = EquipmentType.get(name);
        if (null != eq) {
            for (int slot = 0; slot < unit.getNumberOfCriticalSlots(loc); slot++) {
                final CriticalSlot criticalSlot = unit.getCritical(loc, slot);
                if ((null != criticalSlot) &&
                      (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT) &&
                      (null != criticalSlot.getMount()) &&
                      criticalSlot.getMount().getType().equals(eq)) {
                    Mounted<?> mount = criticalSlot.getMount();

                    if (mount instanceof MiscMounted) {
                        unit.getMisc().remove(mount);
                    }

                    unit.setCritical(loc, slot, null);
                }
            }
        }

        unit.setArmorType(EquipmentType.T_ARMOR_STANDARD, loc);
        unit.setArmorTechLevel(TechConstants.T_INTRO_BOX_SET, loc);
    }

    public static void checkArmor(Entity unit) {
        if (!(unit instanceof Mek mek)) {
            return;
        }

        boolean foundError = false;

        // Check all the meks locations to see if any armor is greater than can be in there.
        for (int location = 0; location < mek.locations(); location++) {
            // Head armor has a max of 9
            int armor = mek.getArmor(location);

            if (location == Mek.LOC_HEAD) {
                if ((armor > 9) && !mek.isSuperHeavy()) {
                    foundError = true;
                    mek.initializeArmor(9, location);
                } else if (armor > 12) {
                    foundError = true;
                    mek.initializeArmor(9, location);
                }
            } else {
                if (mek.hasRearArmor(location)) {
                    armor += mek.getArmor(location, true);
                }
                int totalArmor = mek.getInternal(location) * 2;
                // Armor on the location is greater than what can be there.
                if (armor > totalArmor) {
                    foundError = true;
                    int armorOverage = armor - totalArmor;

                    // check for locations with rear armor first and remove the
                    // extra armor from the rear first.
                    if (mek.hasRearArmor(location)) {
                        int rearArmor = mek.getArmor(location, true);
                        if (rearArmor >= armorOverage) {
                            mek.initializeRearArmor(rearArmor - armorOverage, location);
                            armorOverage = 0;
                        } else {
                            armorOverage -= rearArmor;
                            mek.initializeRearArmor(0, location);
                        }
                    }

                    // Any armor overage left remove it from the front. Min 0
                    // armor in the location.
                    armor = mek.getArmor(location);
                    armor = Math.max(0, armor - armorOverage);
                    mek.initializeArmor(armor, location);
                }
            }
        }

        if (foundError) {
            JOptionPane.showMessageDialog(null,
                  "Too much armor found on this unit.\n\rMegaMekLab has automatically corrected the problem.\n\rIt is suggested you check the armor allocation.",
                  "Too much armor",
                  JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @param unit The entity the supplied entity
     *
     * @return a TestEntity instance for the supplied Entity.
     */
    public static TestEntity getEntityVerifier(Entity unit) {
        // TODO : Remove inline file path
        EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mekfiles/UnitVerifierOptions.xml"));
        TestEntity testEntity = null;

        if (unit.hasETypeFlag(Entity.ETYPE_MEK)) {
            testEntity = new TestMek((Mek) unit, entityVerifier.mekOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
            testEntity = new TestProtoMek((ProtoMek) unit, entityVerifier.protomekOption, null);
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
            testEntity = new TestInfantry((Infantry) unit, entityVerifier.infOption, null);
        } else if (unit.hasETypeFlag(Entity.ETYPE_HANDHELD_WEAPON)) {
            testEntity = new TestHandheldWeapon((HandheldWeapon) unit, entityVerifier.infOption, null);
        }
        return testEntity;
    }

    /**
     * check that the unit is valid
     *
     * @param unit The entity
     */
    public static String validateUnit(Entity unit) {
        StringBuffer sb = new StringBuffer();
        TestEntity testEntity = getEntityVerifier(unit);

        if (testEntity != null) {
            testEntity.correctEntity(sb, unit.getTechLevel());
        }

        return sb.toString();
    }

    public static void removeAllMiscMounted(Entity unit, EquipmentFlag flag) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted<?> mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(flag)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static void removeAllMounted(Entity unit, EquipmentType et) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted<?> mount = unit.getEquipment().get(pos);
            if (mount.getType().equals(et)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    public static void removeTC(Entity unit) {
        for (int pos = unit.getEquipment().size() - 1; pos >= 0; pos--) {
            Mounted<?> mount = unit.getEquipment().get(pos);
            if ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_TARGETING_COMPUTER)) {
                UnitUtil.removeMounted(unit, mount);
            }
        }
    }

    /**
     * Checks whether the equipment can be added to the location on the build tab
     *
     * @param unit     The entity being designed
     * @param eq       The equipment to test The equipment
     * @param location The location to add it
     *
     * @return Whether the location is valid
     */
    public static boolean isValidLocation(Entity unit, EquipmentType eq, int location) {
        if (unit instanceof BattleArmor) {
            // Can only be mounted in APM or armored glove; can't be added directly to
            // location
            return !(eq instanceof WeaponType && eq.hasFlag(WeaponType.F_INFANTRY));
        }
        return TestEntity.isValidLocation(unit, eq, location, null);
    }

    /**
     * Makes the equipment mounted in one location identical to that in another location. Any equipment previously in
     * the target location that is does not match the source location is removed and assigned to Entity.LOC_NONE.
     *
     * @param entity  The unit being modified
     * @param fromLoc The source location index
     * @param toLoc   The target location index
     *
     * @throws LocationFullException If the target location is full
     */
    public static void copyLocationEquipment(Entity entity, int fromLoc, int toLoc) throws LocationFullException {
        copyLocationEquipment(entity, fromLoc, toLoc, true, true);
    }

    /**
     * Makes the equipment mounted in one location identical to that in another location. Any equipment previously in
     * the target location that does not match the source location is removed and assigned to Entity.LOC_NONE. This does
     * not handle split location equipment.
     *
     * @param entity         The unit being modified
     * @param fromLoc        The source location index
     * @param toLoc          The target location index
     * @param includeForward Whether to include forward-mounted equipment
     * @param includeRear    Whether to include rear-mounted equipment
     *
     * @throws LocationFullException If the target location is full
     */
    public static void copyLocationEquipment(final Entity entity, final int fromLoc, final int toLoc,
          final boolean includeForward, final boolean includeRear) throws LocationFullException {
        // Remove any equipment already in the location, but keep a list of it
        // to reuse as much as possible.
        List<Mounted<?>> removed = entity.getEquipment()
              .stream()
              .filter(m -> m.getLocation() == toLoc)
              .filter(m -> m.isRearMounted() ? includeRear : includeForward)
              .collect(Collectors.toList());

        // Add to this any equipment that is already unequipped (= in Entity.LOC_NONE)
        // and free to be used
        List<Mounted<?>> unequipped = entity.getEquipment()
              .stream()
              .filter(m -> m.getLocation() == Entity.LOC_NONE)
              .toList();

        removed.forEach(m -> UnitUtil.removeCriticalSlots(entity, m));

        removed.stream()
              .filter(m -> !(m.getType() instanceof BayWeapon))
              .forEach(m -> changeMountStatus(entity, m, Entity.LOC_NONE, Entity.LOC_NONE, false));

        removed.stream().filter(m -> (m.getType() instanceof BayWeapon)).forEach(m -> removeMounted(entity, m));

        removed.addAll(unequipped);

        /*
         * Now we go through the equipment in the location to copy and add it to the other location. If there is a
         * match in what we removed, use that. Otherwise, add the equipment to the unit. If the unit uses weapon
         * bays, we need to create them in the new location and fill them. If the unit doesn't use bays, we will
         * iterate through the critical slots to get the equipment in the same order to be nice and tidy.
         */
        if (entity.usesWeaponBays()) {
            List<WeaponMounted> bayList = entity.getWeaponBayList()
                  .stream()
                  .filter(bay -> (bay.getLocation() == fromLoc) &&
                        (bay.isRearMounted() ?
                              includeRear :
                              includeForward))
                  .toList();
            for (WeaponMounted bay : bayList) {
                if ((bay.getLocation() == fromLoc) && (bay.isRearMounted() ? includeRear : includeForward)) {
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
                if ((m.getLocation() == fromLoc) && (m.isRearMounted() ? includeRear : includeForward)) {
                    copyEquipment(entity, toLoc, m, removed);
                }
            }
        } else {
            for (int slot = 0; slot < entity.getNumberOfCriticalSlots(fromLoc); slot++) {
                final CriticalSlot criticalSlot = entity.getCritical(fromLoc, slot);
                if ((null != criticalSlot) && (criticalSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                    copyEquipment(entity, toLoc, criticalSlot.getMount(), removed);
                }
            }
        }

        // Link up Artemis, etc.
        try {
            MekFileParser.postLoadInit(entity);
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }
    }

    /**
     * Used by {@link #copyLocationEquipment(Entity, int, int, boolean, boolean)} to perform the actual copy of
     * equipment from one location to another.
     *
     * @param entity The entity be processed
     * @param toLoc  The location to copy the equipment to
     * @param toCopy The equipment to copy
     * @param reuse  A list of equipment to reuse if there is a copy available. If not, a new item will be created. Note
     *               that this modifies the contents of the list by removing the equipment mount that was reused
     *
     * @return The new equipment mount created in the new location
     *
     * @throws LocationFullException If there are not enough slots in the new location to add the equipment.
     */
    private static Mounted<?> copyEquipment(Entity entity, int toLoc, Mounted<?> toCopy, List<Mounted<?>> reuse)
          throws LocationFullException {
        Mounted<?> toAdd = reuse.stream().filter(m -> m.getType().equals(toCopy.getType())).findFirst().orElse(null);
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
     * If the given Mounted is a one-shot launcher or infantry weapon, this method removes the hidden ammo linked to it,
     * if any. During construction, we have no use of hidden ammo. Cannot use {@link #removeOneShotAmmo(Entity)} here as
     * it removes all ammo that has no location (which is how hidden ammo is kept when a unit is loaded from file) but
     * during construction normal ammo may not yet have been allocated and also have no location.
     *
     * @param mounted The weapon to remove linked hidden ammo
     */
    public static void removeHiddenAmmo(Mounted<?> mounted) {
        EquipmentType launcherType = mounted.getType();
        if ((launcherType instanceof WeaponType) &&
              (launcherType.hasFlag(WeaponType.F_ONE_SHOT) ||
                    (((WeaponType) launcherType).getAmmoType() == AmmoType.AmmoTypeEnum.INFANTRY))) {
            Mounted<?> oneShotAmmo = mounted.getLinked();
            if (oneShotAmmo != null) {
                mounted.getEntity().getEquipment().remove(oneShotAmmo);

                if (oneShotAmmo instanceof AmmoMounted) {
                    mounted.getEntity().getAmmo().remove(oneShotAmmo);
                }

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
     * Checks whether the unit has a weapon that uses the ammo type and the munition is legal for the type of unit.
     *
     * @param unit           The entity The unit
     * @param ammoType       The ammo
     * @param includeOneShot If false, ignores one-shot weapons
     *
     * @return Whether the unit can make use of the ammo
     */
    public static boolean canUseAmmo(Entity unit, AmmoType ammoType, boolean includeOneShot) {
        if ((unit instanceof BattleArmor) && !ammoType.hasFlag(AmmoType.F_BATTLEARMOR)) {
            return false;
        } else if (!(unit instanceof BattleArmor) && ammoType.hasFlag(AmmoType.F_BATTLEARMOR)) {
            return false;
        } else if (unit.hasETypeFlag(Entity.ETYPE_AERO) && !ammoType.canAeroUse()) {
            return false;
        } else if (ammoType.hasFlag(AmmoType.F_PROTOMEK) && !(unit instanceof ProtoMek)) {
            return false;
        }

        for (Mounted<?> m : unit.getTotalWeaponList()) {
            if (m.getType() instanceof AmmoWeapon ammoWeaponType) {
                if ((ammoWeaponType.getAmmoType() == ammoType.getAmmoType()) &&
                      (ammoWeaponType.getRackSize() == ammoType.getRackSize()) &&
                      (includeOneShot || !m.getType().hasFlag(WeaponType.F_ONE_SHOT))) {
                    return true;
                }
            } else if ((ammoType instanceof SmallWeaponAmmoType smallWeaponAmmoType) &&
                  smallWeaponAmmoType.isAmmoFor(m.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for any equipment added on the equipment tab and removes any that is no longer legal for the current
     * year/tech base/tech level
     *
     * @param unit        The entity The unit to check
     * @param techManager The manager that handles the checking
     *
     * @return Whether any changes were made
     */
    public static boolean checkEquipmentByTechLevel(Entity unit, ITechManager techManager) {
        List<Mounted<?>> toRemove = new ArrayList<>();
        ITechnology acTA = Entity.getArmoredComponentTechAdvancement();
        boolean dirty = false;
        for (Mounted<?> m : unit.getEquipment()) {
            if (m.isArmored() && !techManager.isLegal(acTA)) {
                m.setArmored(false);
                updateCritsArmoredStatus(unit, m);
                dirty = true;
            }

            EquipmentType equipmentType = m.getType();
            if (UnitUtil.isArmorOrStructure(equipmentType) ||
                  UnitUtil.isHeatSink(equipmentType) ||
                  UnitUtil.isJumpJet(equipmentType)) {
                continue;
            }

            if (equipmentType instanceof MiscType &&
                  (equipmentType.hasFlag(MiscType.F_TSM) ||
                        equipmentType.hasFlag(MiscType.F_INDUSTRIAL_TSM) ||
                        (equipmentType.hasFlag(MiscType.F_MASC) &&
                              !equipmentType.hasFlag(MiscTypeFlag.S_SUPERCHARGER) &&
                              !equipmentType.hasFlag(MiscTypeFlag.S_JET_BOOSTER)) ||
                        equipmentType.hasFlag(MiscType.F_SCM))) {
                continue;
            }

            if (!techManager.isLegal(equipmentType)) {
                toRemove.add(m);
            }
        }
        dirty |= !toRemove.isEmpty();
        for (Mounted<?> m : toRemove) {
            UnitUtil.removeMounted(unit, m);
        }

        if (unit instanceof Infantry pbi) {
            if ((null != pbi.getPrimaryWeapon()) && !techManager.isLegal(pbi.getPrimaryWeapon())) {
                dirty = true;
                InfantryUtil.replaceMainWeapon(pbi, (InfantryWeapon) EquipmentType.get("Infantry Auto Rifle"), false);
            }
            if ((null != pbi.getSecondaryWeapon()) && !techManager.isLegal(pbi.getSecondaryWeapon())) {
                dirty = true;
                InfantryUtil.replaceMainWeapon(pbi, null, true);
            }
            if (techManager.getTechLevel().ordinal() <= SimpleTechLevel.STANDARD.ordinal() && pbi.hasFieldWeapon()) {
                InfantryUtil.replaceFieldGun(pbi, null, 0);
            }
        }
        return dirty;
    }

    /**
     * Updates the manual BV value of the given entity. When manualBV is 0 or less, the entity is set to not use a
     * manual BV value and the manual BV is set to -1.
     *
     * @param manualBV A manual BV value; values less than 1 remove the manual BV from the entity.
     */
    public static void setManualBV(int manualBV, Entity entity) {
        entity.setManualBV((manualBV > 0) ? manualBV : -1);
        entity.setUseManualBV(manualBV > 0);
    }

    /**
     * For MiscTypes of variable size such as Ladders this sets the size to the minimum size which is equal to the step
     * size (20m for Ladders, 0.5t for Cargo space and the like).
     */
    public static void setVariableSizeMiscTypeMinimumSize(Mounted<?> mounted) {
        if ((mounted.getType() instanceof MiscType) && mounted.getType().isVariableSize()) {
            mounted.setSize(mounted.getType().variableStepSize());
        }
    }

    private UnitUtil() {
    }

    static boolean isNonMekOrTankWeapon(Entity unit, WeaponType weapon) {
        if (weapon.getTonnage(unit) <= 0) {
            return true;
        }

        if (weapon.isCapital() || weapon.isSubCapital()) {
            return true;
        }

        if (((weapon instanceof LRMWeapon) || (weapon instanceof LRTWeapon)) &&
              (weapon.getRackSize() != 5) &&
              (weapon.getRackSize() != 10) &&
              (weapon.getRackSize() != 15) &&
              (weapon.getRackSize() != 20)) {
            return true;
        }
        if (((weapon instanceof SRMWeapon) || (weapon instanceof SRTWeapon)) &&
              (weapon.getRackSize() != 2) &&
              (weapon.getRackSize() != 4) &&
              (weapon.getRackSize() != 6)) {
            return true;
        }
        if ((weapon instanceof MRMWeapon) && (weapon.getRackSize() < 10)) {
            return true;
        }

        if ((weapon instanceof RLWeapon) && (weapon.getRackSize() < 10)) {
            return true;
        }

        if (weapon.hasFlag(WeaponType.F_ENERGY) ||
              (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.AmmoTypeEnum.PLASMA))) {

            return weapon.hasFlag(WeaponType.F_ENERGY) &&
                  weapon.hasFlag(WeaponType.F_PLASMA) &&
                  (weapon.getAmmoType() == AmmoType.AmmoTypeEnum.NA);
        }
        return false;
    }

    public static long getEditorTypeForEntity(Entity newUnit) {
        if ((newUnit == null) || (newUnit instanceof Mek)) {
            return Entity.ETYPE_MEK;
        } else if (newUnit.isSupportVehicle()) {
            return Entity.ETYPE_SUPPORT_TANK;
        } else if (newUnit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            return Entity.ETYPE_DROPSHIP;
        } else if (newUnit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            return Entity.ETYPE_JUMPSHIP;
        } else if ((newUnit instanceof Aero) && !(newUnit instanceof FixedWingSupport)) {
            return Entity.ETYPE_AERO;
        } else if (newUnit instanceof BattleArmor) {
            return Entity.ETYPE_BATTLEARMOR;
        } else if (newUnit instanceof Infantry) {
            return Entity.ETYPE_INFANTRY;
        } else if (newUnit instanceof ProtoMek) {
            return Entity.ETYPE_PROTOMEK;
        } else if ((newUnit instanceof Tank) && !(newUnit instanceof GunEmplacement)) {
            return Entity.ETYPE_TANK;
        } else if (newUnit instanceof HandheldWeapon) {
            return Entity.ETYPE_HANDHELD_WEAPON;
        } else if (newUnit instanceof GunEmplacement) {
            return Entity.ETYPE_GUN_EMPLACEMENT;
        } else {
            throw new IllegalArgumentException("Cannot open this entity in an editor");
        }
    }

    /**
     * Encodes the unit to a string.
     *
     * @param entity The unit to encode
     *
     * @return The encoded unit as a string, or null if the unit is null or an error
     */
    public static String saveUnitToString(Entity entity, boolean includeGeneratorHeader) {
        if (entity == null) {
            return null;
        }
        try {
            String unitAsString;
            if (entity instanceof Mek) {
                unitAsString = ((Mek) entity).getMtf();
            } else {
                BuildingBlock blk = BLKFile.getBlock(entity);
                StringBuilder sb = new StringBuilder();
                String[] lines = blk.getAllDataAsString();
                for (String line : lines) {
                    sb.append(line).append(java.lang.System.lineSeparator());
                }
                unitAsString = sb.toString();
            }
            if (!includeGeneratorHeader) {
                return unitAsString.substring(unitAsString.indexOf("\n") + 1);
            }
            return unitAsString;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Clone an entity. This method creates a deep copy of the entity, including all its properties and references.
     *
     * @param entity The entity to copy
     *
     * @return The copied entity
     */
    static public Entity cloneUnit(Entity entity) {
        try {
            Entity newEntity;
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                    // Serialize the entities
                    objectOutputStream.writeObject(entity);
                    objectOutputStream.flush();
                    byte[] serializedData = byteArrayOutputStream.toByteArray();

                    // Deserialize to create new instances
                    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedData)) {
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                            newEntity = (Entity) objectInputStream.readObject();
                        }
                    }
                }
            }
            newEntity.setGame(entity.getGame());
            newEntity.setOwner(entity.getOwner());
            return newEntity;
        } catch (Exception e) {
            LOGGER.error(e, "Failed to break references for entity {}", entity);
            return null;
        }
    }

    /**
     * Reset the damage to the unit to its original state.
     *
     * @param entity The entity to reset
     */
    static public void resetUnit(Entity entity) {
        for (Mounted<?> mounted : entity.getEquipment()) {
            if (mounted instanceof MiscMounted misc) {
                misc.setDamageTaken(0);
            }
            mounted.setHit(false);
            mounted.setDestroyed(false);
            mounted.setMissing(false);
            mounted.setJammed(false);
            mounted.setBreached(false);
            mounted.setFired(false);
            mounted.setDumping(false);
        }
        for (int loc = 0; loc < entity.locations(); loc++) {
            for (int slot = 0; slot < entity.getNumberOfCriticalSlots(loc); slot++) {
                CriticalSlot cs = entity.getCritical(loc, slot);
                if (cs != null) {
                    cs.setDestroyed(false);
                    cs.setMissing(false);
                    cs.setBreached(false);
                    cs.setHit(false);
                }
            }
            entity.setInternal(entity.getOInternal(loc), loc);
            entity.setArmor(entity.getOArmor(loc), loc);
            if (entity.hasRearArmor(loc)) {
                entity.setArmor(entity.getOArmor(loc, true), loc, true);
            }
        }
        if (entity instanceof Aero aero) {
            aero.setSI(aero.getOSI());
        }
        for (int i = 0; i < entity.getCrew().getSlotCount(); i++) {
            Crew crew = entity.getCrew();
            crew.setHits(0, i);
            crew.setDead(false, i);
        }
    }

    static public boolean isDamaged(Entity entity, boolean includeCrew) {
        for (Mounted<?> mounted : entity.getEquipment()) {
            if (mounted.isHit() || mounted.isDestroyed() || mounted.isMissing()) {
                return true;
            }
        }
        for (int loc = 0; loc < entity.locations(); loc++) {
            for (int slot = 0; slot < entity.getNumberOfCriticalSlots(loc); slot++) {
                CriticalSlot cs = entity.getCritical(loc, slot);
                if (cs != null && (cs.isDamaged() || cs.isMissing())) {
                    return true;
                }
            }
        }
        if (entity instanceof Aero aero) {
            if ((aero.getOSI() > 0) && (aero.getSI() < aero.getOSI())) {
                return true;
            }
        }
        for (int loc = 0; loc < entity.locations(); loc++) {
            if ((entity.getOInternal(loc) > 0) && (entity.getInternal(loc) < entity.getOInternal(loc))) {
                return true;
            }
            if ((entity.getOArmor(loc) > 0) && (entity.getArmor(loc) < entity.getOArmor(loc))) {
                return true;
            }
            if (entity.hasRearArmor(loc) && (entity.getOArmor(loc, true) > 0) && (entity.getArmor(loc, true)
                  < entity.getOArmor(loc, true))) {
                return true;
            }
        }
        if (includeCrew) {
            for (int i = 0; i < entity.getCrew().getSlotCount(); i++) {
                Crew crew = entity.getCrew();
                if ((crew.getHits(i) > 0) || crew.isDead(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getPrintName(Entity e) {
        return CConfig.getMekNameArrangement().printChassis(e)
              + (StringUtility.isNullOrBlank(e.getModel()) ? "" : " " + e.getModel());
    }
}
