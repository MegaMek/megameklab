/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
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
import megamek.common.equipment.MiscMounted;
import megamek.common.weapons.c3.ISC3M;
import megamek.common.weapons.c3.ISC3MBS;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.common.weapons.other.*;
import megamek.common.weapons.ppc.CLPlasmaCannon;
import megamek.common.weapons.tag.CLLightTAG;
import megamek.common.weapons.tag.CLTAG;
import megamek.common.weapons.tag.ISTAG;
import megameklab.ui.PopupMessages;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.stream.Collectors.toSet;
import static megameklab.util.UnitUtil.*;

public final class MekUtil {

    /**
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
                return mounted.getType().getInternalName().equals("CLDoubleHeatSink");
            }
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
        Vector<Mounted<?>> toRemove = new Vector<>();
        int base = UnitUtil.getCriticalFreeHeatSinks(unit, unit.hasCompactHeatSinks());
        boolean splitCompact = false;
        if (unit.hasCompactHeatSinks()) {
            // first check to see if there is a single compact heat sink outside of the engine and
            // remove this first if so
            Mounted<?> mount = getSingleCompactHeatSink(unit);
            if ((null != mount) && (number > 0)) {
                UnitUtil.removeMounted(unit, mount);
                number--;
            }
            // if number is now uneven, then note that we will need to split a compact
            if ((number % 2) == 1) {
                splitCompact = true;
                number--;
            }
        }
        Vector<Mounted<?>> unassigned = new Vector<>();
        Vector<Mounted<?>> assigned = new Vector<>();
        Vector<Mounted<?>> free = new Vector<>();
        for (Mounted<?> m : unit.getMisc()) {
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
            // need to do some number magic here. The unassigned and assigned slots should each
            // contain two heat sinks, but if we dip into the free then we are looking at one heat
            // sink.
            int numberDouble = Math.min(number / 2, unassigned.size() + assigned.size());
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
                    UnitUtil.addMounted(unit,
                            Mounted.createMounted(unit, EquipmentType.get("IS1 Compact Heat Sink")), loc, false);
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
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
        addHeatSinkMounts(unit, hsAmount, EquipmentType.get(UnitUtil.getHeatSinkType(hsType, unit.isClan())));
    }

    /**
     * adds all heat sinks to the mech
     *
     * @param unit
     * @param hsAmount
     * @param sinkType
     */
    public static void addHeatSinkMounts(Mech unit, int hsAmount, EquipmentType sinkType) {
        if (sinkType.hasFlag(MiscType.F_COMPACT_HEAT_SINK)) {
            addCompactHeatSinkMounts(unit, hsAmount);
        } else {
            for (; hsAmount > 0; hsAmount--) {
                try {
                    unit.addEquipment(Mounted.createMounted(unit, sinkType), Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
    }

    public static void addCompactHeatSinkMounts(Mech unit, int hsAmount) {
        // first we need to figure out how many single compacts we need to add/ for the engine, if any
        int currentSinks = countActualHeatSinks(unit);
        int engineCompacts = Math.min(hsAmount, UnitUtil.getCriticalFreeHeatSinks(unit, true));
        int engineToAdd = Math.max(0, engineCompacts - currentSinks);
        unit.addEngineSinks("IS1 Compact Heat Sink", engineToAdd);
        int restHS = hsAmount - engineToAdd;
        Mounted singleCompact = getSingleCompactHeatSink(unit);
        if ((restHS % 2) == 1) {
            if (null == singleCompact) {
                try {
                    unit.addEquipment(Mounted.createMounted(unit, EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_1)),
                            Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            } else {
                int loc = singleCompact.getLocation();
                // remove singleCompact mount and replace with a double
                UnitUtil.removeMounted(unit, singleCompact);
                try {
                    UnitUtil.addMounted(unit,Mounted.createMounted(unit, EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_2)),
                            loc, false);
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
            restHS -= 1;
        }
        for (; restHS > 0; restHS -= 2) {
            try {
                unit.addEquipment(Mounted.createMounted(unit, EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_2)),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
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
        int base = UnitUtil.getCriticalFreeHeatSinks(unit, true);
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
                    || mounted.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                    || mounted.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
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
        int currentSinks = countActualHeatSinks(unit);
        if (hasSameHeatSinkType(unit, hsType)) {
            if (hsAmount < currentSinks) {
                removeHeatSinks(unit, currentSinks - hsAmount);
            } else if (hsAmount > currentSinks) {
                addHeatSinkMounts(unit, hsAmount - currentSinks, hsType);
            }
        } else {
            removeHeatSinks(unit, hsAmount);
            addHeatSinkMounts(unit, hsAmount, hsType);
        }
        unit.resetSinks();
    }

    /**
     * This will cycle through the heat sinks and make sure that enough of them
     * are set LOC_NONE based on the basechassisheat sinks
     *
     * @param unit
     */
    public static void updateAutoSinks(Mech unit, boolean compact) {
        if (compact) {
            updateCompactHeatSinks(unit);
            return;
        }
        int base = UnitUtil.getCriticalFreeHeatSinks(unit, compact);
        List<Mounted> unassigned = new ArrayList<>();
        List<Mounted> assigned = new ArrayList<>();
        for (Mounted m : unit.getMisc()) {
            if (UnitUtil.isHeatSink(m)) {
                if (m.getLocation() == Entity.LOC_NONE) {
                    unassigned.add(m);
                } else if (!m.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) {
                    // Prototype double heat sinks can never be integrated into the engine
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
                return;
            }
            UnitUtil.removeCriticals(unit, m);
            m.setLocation(Entity.LOC_NONE);
            needed--;
        }
        // There may be more crit-free heatsinks, but if the 'mech doesn't
        // have that many heatsinks, the additional space is unused.
    }

    /**
     * Adjusts compact heat sinks to fulfill engine capacity. This is more complex than other heat sink
     * types because the engine heat sinks always have one per mount, and those outside the engine
     * are paired in a slot with one single if there are an odd number.
     *
     * @param mech The mech to adjust heat sinks for
     */
    public static void updateCompactHeatSinks(Mech mech) {
        int base = UnitUtil.getCriticalFreeHeatSinks(mech, true);
        List<Mounted> unallocatedSingle = new ArrayList<>();
        List<Mounted> unallocatedPair = new ArrayList<>();
        List<Mounted> allocatedSingle = new ArrayList<>();
        List<Mounted> allocatedPair = new ArrayList<>();
        for (Mounted m : mech.getMisc()) {
            if (UnitUtil.isHeatSink(m)) {
                if (m.getLocation() == Entity.LOC_NONE) {
                    if (m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                        unallocatedPair.add(m);
                    } else {
                        unallocatedSingle.add(m);
                    }
                } else {
                    if (m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                        allocatedPair.add(m);
                    } else {
                        allocatedSingle.add(m);
                    }
                }
            }
        }

        int needed = base - unallocatedSingle.size();
        int toAdd = 0;
        // If there are more single heat sinks than there is space for in the engine remove them so they
        // can be paired up
        if (needed < 0) {
            int count = removeCompactHeatSinks(-needed, mech, unallocatedSingle);
            needed += count;
            toAdd += count;
        }
        // If we have more space in the engine, start by splitting unallocated double heat sinks
        if (needed > 0) {
            int count = removeCompactHeatSinks(needed, mech, unallocatedPair);
            needed -= count;
            toAdd += count;
        }
        // Next we pull a single out of its location, if any
        if (needed > 0) {
            int count = removeCompactHeatSinks(needed, mech, allocatedSingle);
            needed -= count;
            toAdd += count;
        }
        // Finally we remove as many paired heat sinks as we need to fill the engine
        if (needed > 0) {
            toAdd += removeCompactHeatSinks(needed, mech, allocatedPair);
        }
        // Now we add heat sinks back
        try {
            // First we add as many single heat sinks to LOC_NONE as we need to fill the engine
            int engineAdd = Math.min(toAdd, base - unallocatedSingle.size());
            for (int i = 0; i < engineAdd; i++) {
                mech.addEquipment(EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_1), Entity.LOC_NONE);
                toAdd--;
            }
            // If we have an odd number to add and there is a single already allocated, remove it and pair them.
            // Unallocated singles in excess of engine capacity have already been removed.
            if (((toAdd & 1) == 1) && !allocatedSingle.isEmpty()) {
                UnitUtil.removeMounted(mech, allocatedSingle.remove(0));
                mech.addEquipment(EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_2), Entity.LOC_NONE);
                toAdd--;
            }
            // If we still have an odd number, add one single.
            if ((toAdd & 1) == 1) {
                mech.addEquipment(EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_1), Entity.LOC_NONE);
                toAdd--;
            }
            // Add the remainder as unallocated pairs
            for (int i = 0; i < toAdd; i += 2) {
                mech.addEquipment(EquipmentType.get(EquipmentTypeLookup.COMPACT_HS_2), Entity.LOC_NONE);
            }
        } catch (LocationFullException ignored) {
            // We're added to LOC_NONE
        }
    }

    /**
     * Removes all jump jets from the mek
     *
     * @param unit
     */
    public static void removeJumpJets(Mech unit, int number) {
        Vector<MiscMounted> toRemove = new Vector<>();
        for (MiscMounted eq : unit.getMisc()) {
            if (UnitUtil.isJumpJet(eq)) {
                toRemove.add(eq);
                if (toRemove.size() >= number) {
                    break;
                }
            }
        }

        for (MiscMounted eq : toRemove) {
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
        int ctype = unit.getJumpType();
        if (jjType == ctype) {
            int currentJJ = (int) unit.getMisc().stream()
                    .filter(m -> m.getType()
                    .hasFlag(MiscType.F_JUMP_JET))
                    .count();
            if (jjAmount < currentJJ) {
                removeJumpJets(unit, currentJJ - jjAmount);
                return;
            } else if (jjAmount > currentJJ) {
                jjAmount = jjAmount - currentJJ;
            } else {
                return; // No change, get the fuck outta here!
            }
        } else {
            removeJumpJets(unit, unit.getJumpMP());
        }
        // if this is the same jump jet type, then only remove if too many
        // and add if too low
        if (jjType == Mech.JUMP_BOOSTER) {
            removeJumpJets(unit, unit.getJumpMP());
            createSpreadMounts(
                    unit,
                    EquipmentType.get(UnitUtil.getJumpJetType(jjType)));
        } else {
            while (jjAmount > 0) {
                try {
                    unit.addEquipment(
                            Mounted.createMounted(unit, EquipmentType.get(UnitUtil.getJumpJetType(jjType))),
                            Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
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
        ConcurrentLinkedQueue<Mounted> equipmentList = new ConcurrentLinkedQueue<>(unit.getMisc());
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isTSM(eq.getType()) || UnitUtil.isMASC(eq.getType())
                    || ((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_SCM))) {
                UnitUtil.removeCriticals(unit, eq);
            }
        }
        for (Mounted eq : equipmentList) {
            if (UnitUtil.isTSM(eq.getType()) || UnitUtil.isMASC(eq.getType())
                    || ((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_SCM))) {
                unit.getMisc().remove(eq);
                unit.getEquipment().remove(eq);
            }
        }
    }

    /**
     * Compacts the crit slots in all locations of the given Mek, moving Empty slots to the bottom.
     */
    public static void compactCriticals(Mech mek) {
        for (int loc = 0; loc < mek.locations(); loc++) {
            compactCriticals(mek, loc);
        }
    }

    /**
     * Compacts the crit slots in the given location of the given Mek, moving Empty slots to the bottom.
     */
    public static void compactCriticals(Mech mek, int location) {
        List<CriticalSlot> presentGear = MekUtil.extricateCritSlots(mek, location);
        MekUtil.refillCritSlots(mek, location, presentGear);
    }

    /**
     * Expands crits that are a single mount by have multiple spreadable crits
     * Such as TSM, Endo Steel, Reactive armor.
     *
     * @param unit
     */
    public static void expandUnitMounts(Mech unit) {
        for (int location = 0; location < unit.locations(); location++) {
            for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                CriticalSlot cs = unit.getCritical(location, slot);
                if ((cs == null) || (cs.getType() == CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }
                Mounted<?> mount = cs.getMount();

                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())
                        && (UnitUtil.isTSM(mount.getType())
                        || UnitUtil.isArmorOrStructure(mount.getType()))) {
                    Mounted<?> newMount = Mounted.createMounted(unit, mount.getType());
                    newMount.setLocation(location, mount.isRearMounted());
                    newMount.setArmored(mount.isArmored());
                    cs.setMount(newMount);
                    cs.setArmored(mount.isArmored());
                    unit.getEquipment().remove(mount);
                    if (mount instanceof MiscMounted) {
                        unit.getMisc().remove(mount);
                    }
                    unit.getEquipment().add(newMount);
                    if (newMount instanceof MiscMounted) {
                        unit.getMisc().add((MiscMounted) newMount);
                    }
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
        int blocks = equip.getCriticals(unit);

        boolean isMisc = equip instanceof MiscType;

        List<Integer> locations = new ArrayList<>();

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
                if (unit instanceof TripodMech) {
                    blocks++;
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
                // Need to account for the center leg
                if (unit instanceof TripodMech) {
                    locations.add(Mech.LOC_CLEG);
                    blocks++;
                }
            } else if (equip.hasFlag(MiscType.F_SCM)) {
                // 1 in arms, legs, side torsos
                locations.add(Mech.LOC_LLEG);
                locations.add(Mech.LOC_RLEG);
                locations.add(Mech.LOC_LARM);
                locations.add(Mech.LOC_RARM);
                locations.add(Mech.LOC_LT);
                locations.add(Mech.LOC_RT);
                blocks = 6;
            } else if ((equip.hasFlag(MiscType.F_TRACKS) || equip.hasFlag(MiscType.F_TALON)
                    || equip.hasFlag(MiscType.F_JUMP_BOOSTER))) {
                // 1 block in each leg
                locations.add(Mech.LOC_LLEG);
                locations.add(Mech.LOC_RLEG);
                if (unit instanceof QuadMech) {
                    locations.add(Mech.LOC_LARM);
                    locations.add(Mech.LOC_RARM);
                }
                blocks = (unit instanceof BipedMech ? 2 : 4);
                // Need to account for the center leg
                if (unit instanceof TripodMech) {
                    locations.add(Mech.LOC_CLEG);
                    blocks = 3;
                }
            } else if (equip.hasFlag(MiscType.F_PARTIAL_WING)) {
                // one block in each side torso
                locations.add(Mech.LOC_LT);
                locations.add(Mech.LOC_RT);
                blocks = 2;
            } else if (equip.hasFlag(MiscType.F_RAM_PLATE)) {
                // one block in each torso
                locations.add(Mech.LOC_LT);
                locations.add(Mech.LOC_RT);
                locations.add(Mech.LOC_CT);
                blocks = 3;
            } else if ((equip.hasFlag(MiscType.F_VOIDSIG)
                    || equip.hasFlag(MiscType.F_NULLSIG)
                    || equip.hasFlag(MiscType.F_BLUE_SHIELD))) {
                // Need to account for the center leg
                if (unit instanceof TripodMech) {
                    blocks++;
                }
                // 1 crit in each location, except the head
                for (int i = Mech.LOC_CT; i < unit.locations(); i++) {
                    locations.add(i);
                }
            } else if (equip.hasFlag(MiscType.F_CHAMELEON_SHIELD)) {
                // Need to account for the center leg
                if (unit instanceof TripodMech) {
                    blocks++;
                }
                // 1 crit in each location except head and CT
                for (int i = Mech.LOC_RT; i < unit.locations(); i++) {
                    locations.add(i);
                }
            }
        }

        boolean firstBlock = true;
        Mounted mount = Mounted.createMounted(unit, equip);
        for (; blocks > 0; blocks--) {
            // how many crits per block?
            int crits = UnitUtil.getCritsUsed(mount);
            for (int i = 0; i < crits; i++) {
                try {
                    if (firstBlock || (locations.get(0) == Entity.LOC_NONE)) {
                        // create only one mount per equipment, for BV and stuff
                        UnitUtil.addMounted(unit, mount, locations.get(0), false);
                        if (firstBlock) {
                            firstBlock = false;
                        }
                        if (locations.get(0) == Entity.LOC_NONE) {
                            // only user-placable spread stuff gets location none
                            // for those, we need to create a mount for each crit,
                            // otherwise we can't correctly let the user place them
                            // luckily, that only affects TSM, so BV works out correctly
                            mount = Mounted.createMounted(unit, equip);
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
                                            + unit.getLocationName(locations.get(0)),
                                    JOptionPane.INFORMATION_MESSAGE);
                            unit.getMisc().remove(mount);
                            unit.getEquipment().remove(mount);
                            return null;
                        }
                    }
                } catch (LocationFullException lfe) {
                    PopupMessages.showLocationFullError(null, mount.getName());
                    LogManager.getLogger().error(lfe);
                    unit.getMisc().remove(mount);
                    unit.getEquipment().remove(mount);
                    return null;
                }
            }
            locations.remove(0);
        }
        return mount;
    }

    public static boolean isLastMechCrit(Mech unit, CriticalSlot cs, int slot, int location) {
        if (cs == null) {
            return true;
        }
        // extra check for the last crit in a location, it shouldn't get a border
        if ((slot + 1) >= unit.getNumberOfCriticals(location)) {
            return false;
        }

        int lastIndex = 0;
        if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {

            for (int position = 0; position < unit.getNumberOfCriticals(location); position++) {
                if ((cs.getIndex() == Mech.SYSTEM_ENGINE) && (slot >= 3) && (position < 3)) {
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
            return (nextCrit == null) || (nextCrit.getMount() == null) || !nextCrit.getMount().equals(cs.getMount());
        }

        return slot == lastIndex;
    }

    public static void updateLoadedMech(Mech unit) {
        expandUnitMounts(unit);
        UnitUtil.checkArmor(unit);
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
        int engineHeatSinkCount = UnitUtil.getCriticalFreeHeatSinks(unit,
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
                nCrits += UnitUtil.getCritsUsed(mount);
            }
        }
        for (Mounted mount : unit.getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                nCrits += UnitUtil.getCritsUsed(mount);
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()) {
                nCrits += UnitUtil.getCritsUsed(mount);
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

    public static void removeHand(Mech mech, int location) {
        if (mech.hasSystem(Mech.ACTUATOR_HAND, location)) {
            mech.setCritical(location, 3, null);
        }
    }

    public static void removeArm(Mech mech, int location) {
        if (mech.hasSystem(Mech.ACTUATOR_LOWER_ARM, location)) {
            mech.setCritical(location, 2, null);
            // Only remove the next slot of it actually is a hand
            if (mech.hasSystem(Mech.ACTUATOR_HAND, location)) {
                removeHand(mech, location);
            }
        }
    }

    /**
     * Called by {@link MekUtil#updateCompactHeatSinks(Mech)} to remove heat sinks up to a certain number.
     * The actual number removed could be higher if count is odd and we're removing pairs, or
     * lower if there aren't enough in the list.
     *
     * @param count  The number of heat sinks to remove
     * @param mech   The mech to remove heat sinks from
     * @param hsList The list of heat sinks available for removal
     * @return       The actual number removed
     */
    private static int removeCompactHeatSinks(int count, Mech mech, List<Mounted> hsList) {
        int removed = 0;
        for (Iterator<Mounted> iter = hsList.iterator(); iter.hasNext(); ) {
            Mounted m = iter.next();
            UnitUtil.removeMounted(mech, m);
            removed += m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) ? 2 : 1;
            iter.remove();
            if (removed >= count) {
                break;
            }
        }
        return removed;
    }

    private MekUtil() { }

    /**
     * Clears all links of the given equipment to other equipment and unallocates
     * it (assigns to LOC_NONE). Note: Does not clear the equipment's crit slots from its
     * former location. For this, use {@link UnitUtil#removeCriticals(Entity, Mounted)}
     */
    public static void clearMountedLocationAndLinked(Mounted equipment) {
        if ((Entity.LOC_NONE != equipment.getLocation()) && !equipment.isOneShot()) {
            if (equipment.getLinked() != null) {
                equipment.getLinked().setLinkedBy(null);
                equipment.setLinked(null);
            }
            if (equipment.getLinkedBy() != null) {
                equipment.getLinkedBy().setLinked(null);
                equipment.setLinkedBy(null);
            }
        }
        equipment.setLocation(Entity.LOC_NONE, false);
        equipment.setSecondLocation(Entity.LOC_NONE, false);
        equipment.setSplit(false);
    }

    /**
     * Moves all equipment that is freely movable and unhittable (e.g. Endo Steel and
     * Ferro-Fibrous but not CASE) ("FMU") that is currently unallocated (LOC_NONE) to free
     * locations on the Mek as long as there are any.
     */
    public static void fillInFMU(Mech mek) {
        // Work with a copy because the equipment list may be modified.
        for (Mounted mount : new ArrayList<>(mek.getEquipment())) {
            if (!isFMU(mount) || (mount.getLocation() != Entity.LOC_NONE)) {
                continue;
            }
            for (int location = 0; location < mek.locations(); location++) {
                if (!isValidLocation(mek, mount.getType(), location)
                        || (getMaxContiguousNumOfCrits(mek, location, false) < getCritsUsed(mount))) {
                    continue;
                }
                try {
                    addMounted(mek, mount, location, false);
                    changeMountStatus(mek, mount, location, Entity.LOC_NONE, false);
                    break;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
    }

    /**
     * Removes all equipment that is freely movable and unhittable (e.g. Endo Steel and
     * Ferro-Fibrous but not CASE) ("FMU") from the given location, affecting numOfSlots
     * slots beginning at startingSlot.
     */
    public static void removeFMU(Entity mech, int location, int startingSlot, int numOfSlots) {
        for (int slot = startingSlot; slot < startingSlot + numOfSlots; slot++) {
            CriticalSlot critSlot = mech.getCritical(location, slot);
            if ((critSlot != null) && isFMU(critSlot.getMount())) {
                Mounted mounted = critSlot.getMount();
                UnitUtil.removeCriticals(mech, mounted);
                UnitUtil.changeMountStatus(mech, mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
    }

    /**
     * Moves all equipment that is currently unallocated (LOC_NONE) to free
     * locations on the Mek as long as there are any.
     */
    public static void fillInAllEquipment(Mech mek) {
        int externalEngineHS = UnitUtil.getCriticalFreeHeatSinks(mek, mek.hasCompactHeatSinks());
        for (Mounted mount : mek.getEquipment()) {
            if ((mount.getLocation() != Entity.LOC_NONE)
                    || (UnitUtil.isHeatSink(mount) && (externalEngineHS-- > 0))) {
                continue;
            }
            for (int location = Mech.LOC_HEAD; location < mek.locations(); location++) {
                if (!UnitUtil.isValidLocation(mek, mount.getType(), location)) {
                    continue;
                }
                int critsUsed = UnitUtil.getCritsUsed(mount);
                if (critsUsed > UnitUtil.getHighestContinuousNumberOfCrits(mek, location)) {
                    continue;
                }
                try {
                    if (mount.getType().isSpreadable() || (mount.isSplitable() && (critsUsed > 1))) {
                        for (int count = 0; count < critsUsed; count++) {
                            UnitUtil.addMounted(mek, mount, location, false);
                        }
                    } else {
                        UnitUtil.addMounted(mek, mount, location, false);
                    }
                    UnitUtil.changeMountStatus(mek, mount, location, Entity.LOC_NONE, false);
                    break;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                }
            }
        }
    }

    /**
     * Returns true when a slot's equipment is unhittable and freely movable ("FMU"), such
     * as Endo Steel and Ferro-Fibrous Armor but not CASE (which is unhittable but not
     * freely movable as its location is important).
     */
    public static boolean isFMU(Mounted equipment) {
        return (equipment != null)
                && equipment.getType().getCriticals(equipment.getEntity()) > 0
                && !equipment.getType().isHittable()
                && (equipment.getType() instanceof MiscType)
                && !equipment.getType().hasFlag(MiscType.F_CASE)
                && !equipment.getType().hasFlag(MiscType.F_CASEP)
                && !equipment.getType().hasFlag(MiscType.F_CASEII);
    }

    /**
     * Sorts the allocated equipment on all locations (except HD) of the Mek using
     * the officially used sort order.
     */
    public static void sortCrits(Mech mek) {
        for (int location = 0; location < mek.locations(); location++) {
            if (location != Mech.LOC_HEAD) {
                sortCritSlots(mek, location);
            }
        }
    }

    /**
     * Sorts the crits within the given location. This extricates all non-system
     * crits from the location, sorts them and then refills the location.
     */
    public static void sortCritSlots(Mech mek, int location) {
        List<CriticalSlot> presentGear = extricateCritSlots(mek, location);
        presentGear.sort(new MekCritSlotSorter(mek));
        floatAmmo(presentGear);
        presentGear = reOrderLinkedEquipment(presentGear);
        refillCritSlots(mek, location, presentGear);
    }

    /**
     * If the last weapon in the location uses ammo, floats compatible ammo above other ammo
     * Requires the slots to have already been sorted beforehand
     */
    private static void floatAmmo(List<CriticalSlot> slots) {
        WeaponType lastWeapon = null;
        for (CriticalSlot slot : slots) {
            if (slot.getMount().getType() instanceof WeaponType) {
                lastWeapon = (WeaponType) slot.getMount().getType();
            }
        }
        if (lastWeapon == null) {
            return;
        }

        List<CriticalSlot> compatibleAmmo = new ArrayList<>();
        int insertPosition = -1;
        for (int i = 0; i < slots.size(); ++i) {
            if (slots.get(i).getMount().getType() instanceof AmmoType) {
                if (insertPosition < 0) {
                    insertPosition = i;
                }

                AmmoType ammoType = (AmmoType) slots.get(i).getMount().getType();
                if (lastWeapon.rackSize == ammoType.getRackSize() && lastWeapon.getAmmoType() == lastWeapon.getAmmoType()) {
                    compatibleAmmo.add(slots.get(i));
                }
            }
        }

        if (insertPosition < 0) {
            return;
        }

        slots.removeAll(compatibleAmmo);
        slots.addAll(insertPosition, compatibleAmmo);
    }

    /**
     * Removes all crit slots from the given location except for system crit slots
     * (e.g. engine) and returns them as a list.
     */
    public static List<CriticalSlot> extricateCritSlots(Mech mek, int location) {
        List<CriticalSlot> presentGear = new ArrayList<>();
        for (int slot = 0; slot < mek.getNumberOfCriticals(location); slot++) {
            CriticalSlot critSlot = mek.getCritical(location, slot);
            if ((critSlot != null) && (critSlot.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                presentGear.add(critSlot);
                mek.setCritical(location, slot, null);
            }
        }
        return presentGear;
    }

    /**
     * Fills the given list of crit slots into the given location. This does not check
     * or change the respective mounteds' locations, so care must be taken that all
     * mounteds are indeed allocated to this location or that the crit slots have been
     * taken from this location, e.g. with {@link #extricateCritSlots(Mech, int)}.
     */
    public static void refillCritSlots(Mech mek, int location, List<CriticalSlot> critList) {
        //TODO: If they somehow dont fit, unallocate the mounted and remove all its critslots
        critList.forEach(critSlot -> mek.addCritical(location, critSlot));
    }

    /**
     * Returns a reordered version of the given presentGear list of critslots wherein
     * LinkedBy mounteds such as Artemis and PPC Capacitors are placed directly behind
     * their weapon.
     */
    public static List<CriticalSlot> reOrderLinkedEquipment(List<CriticalSlot> presentGear) {
        List<CriticalSlot> resortedList = new ArrayList<>();
        Set<Mounted> presentMounteds = presentGear.stream().map(CriticalSlot::getMount).collect(toSet());
        // Assemble all the linked gear that is not ammo (ammo is sorted differently)
        Set<Mounted> linkedMounteds = presentMounteds.stream()
                .map(Mounted::getLinkedBy)
                .filter(Objects::nonNull)
                .filter(linked -> linked.getType() instanceof MiscType)
                .filter(presentMounteds::contains)
                .collect(toSet());

        // Now rebuild the list by adding the linkedMounteds behind their weapon
        Mounted lastMounted = null;
        for (CriticalSlot critSlot : presentGear) {
            Mounted currentMounted = critSlot.getMount();
            // after one mounted is fully added, see if there's a linkedMounted to add below it
            if ((lastMounted != null)
                    && (currentMounted != lastMounted)
                    && (lastMounted.getLinkedBy() != null)
                    && (linkedMounteds.contains(lastMounted.getLinkedBy()))) {
                for (CriticalSlot linkedSlot : presentGear) {
                    if (linkedSlot.getMount() == lastMounted.getLinkedBy()) {
                        resortedList.add(linkedSlot);
                    }
                }
            }
            // Add the current crit slot but exclude the linkedMounteds as they are added behind their weapon
            if (!linkedMounteds.contains(critSlot.getMount())) {
                resortedList.add(critSlot);
            }
            lastMounted = currentMounted;
        }
        return resortedList;
    }

    /**
     * Returns a number indicating the order in which equipment should be sorted
     * within a location according to the official crit slot sorting. Weapons and
     * ammo require further internal sorting and linked equipment such as Artemis and PPC
     * Capacitors also require extra treatment to be placed behind their weapon.
     */
    public static int getCoarseOrdering(Mech mek, Mounted mounted) {
        if (isPartialWing(mounted)) {
            return 1;
        } else if (UnitUtil.isHeatSink(mounted)) {
            return 2;
        } else if (UnitUtil.isJumpJet(mounted)) {
            return 3;
        } else if (isMechWeapon(mounted.getType(), mek)) {
            return 4;
        } else if (mounted.getType() instanceof AmmoType) {
            return 5;
        } else if (mounted.getType().isHittable()) {
            return 6;
        } else if (isCASE(mounted)) {
            return 7;
        } else if (EquipmentType.isStructureType(mounted.getType())) {
            return 8;
        } else if (EquipmentType.isArmorType(mounted.getType())) {
            return 9;
        } else {
            return 10;
        }
    }

    public static boolean isPartialWing(Mounted mounted) {
        return (mounted.getType() instanceof MiscType)
                && mounted.getType().hasFlag(MiscType.F_PARTIAL_WING);
    }

    public static boolean isCASE(Mounted mounted) {
        return (mounted.getType() instanceof MiscType)
                && (mounted.getType().hasFlag(MiscType.F_CASE)
                || mounted.getType().hasFlag(MiscType.F_CASEP)
                || mounted.getType().hasFlag(MiscType.F_CASEII));
    }

    /**
     * Returns the highest number of contiguous free crit slots available in the given location.
     * When ignoreFMU is true, slots that contain unhittable and freely moveable (FMU) equipment
     * such as Endo Steel are counted as being free.
     */
    public static int getMaxContiguousNumOfCrits(Mech mek, int location, boolean ignoreFMU) {
        if ((location == Entity.LOC_DESTROYED) || (location == Entity.LOC_NONE)) {
            return 0;
        }
        int maxNumOfCrits = 0;
        for (int slot = 0; slot < mek.getNumberOfCriticals(location); slot++) {
            maxNumOfCrits = Math.max(availableContiguousCrits(mek, location, slot, ignoreFMU), maxNumOfCrits);
        }
        return maxNumOfCrits;
    }

    /**
     * Returns the first slot in the location that together with following slots
     * forms a contiguous block of the given length as size where all slots
     * are either empty of contain freely movable crits such as Endo Steel.
     * Returns -1 if there is no such slot.
     */
    public static int findSlotWithContiguousNumOfCrits(Entity mech, int location, int length) {
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            if (canFreeContiguousCrits(mech, location, slot, length)) {
                return slot;
            }
        }
        return -1;
    }

    /**
     * Returns true when numOfSlots contiguous slots starting from startingSlot are either free or
     * can be freed by removing unhittable and movable equipment such as Endo Steel.
     */
    public static boolean canFreeContiguousCrits(Entity mek, int location, int startingSlot, int numOfSlots) {
        return availableContiguousCrits(mek, location, startingSlot, true) >= numOfSlots;
    }

    /**
     * Returns the number of contiguous slots starting from startingSlot that are either free or
     * can be freed by removing unhittable and movable (FMU) equipment such as Endo Steel.
     * When ignoreFMU is true, slots that contain unhittable and freely moveable (FMU) equipment
     * such as Endo Steel are counted as being free.
     */
    public static int availableContiguousCrits(Entity mek, int location, int startingSlot, boolean ignoreFMU) {
        for (int slot = startingSlot; slot < mek.getNumberOfCriticals(location); slot++) {
            CriticalSlot critSlot = mek.getCritical(location, slot);
            if ((critSlot != null) && !(ignoreFMU && isFMU(critSlot.getMount()))) {
                return slot - startingSlot;
            }
        }
        return mek.getNumberOfCriticals(location) - startingSlot;
    }

    /** Add a vehicular grenade launcher, asking the user for the facing. */
    public static boolean addVGL(Mech mek, Mounted vgl, int location, int slotNumber)
            throws LocationFullException {
        String[] facings;
        if (location == Mech.LOC_LT) {
            facings = new String[4];
            facings[0] = "Front";
            facings[1] = "Front-Left";
            facings[2] = "Rear-Left";
            facings[3] = "Rear";
        } else if (location == Mech.LOC_RT) {
            facings = new String[4];
            facings[0] = "Front";
            facings[1] = "Front-Right";
            facings[2] = "Rear-Right";
            facings[3] = "Rear";
        } else if (location == Mech.LOC_CT) {
            facings = new String[2];
            facings[0] = "Front";
            facings[1] = "Rear";
        } else {
            JOptionPane.showMessageDialog(null,
                    "VGL must be placed in torso location!",
                    "Invalid location",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String facing = (String)JOptionPane.showInputDialog(null,
                "Please choose the facing of the VGL",
                "Choose Facing", JOptionPane.QUESTION_MESSAGE,
                null, facings, facings[0]);
        if (facing == null) {
            return false;
        }
        mek.addEquipment(vgl, location, false, slotNumber);
        UnitUtil.changeMountStatus(mek, vgl, location, -1, false);
        if (facing.equals("Front-Left")) {
            vgl.setFacing(5);
        } else if (facing.equals("Front-Right")) {
            vgl.setFacing(1);
        } else if (facing.equals("Rear-Right")) {
            vgl.setFacing(2);
        } else if (facing.equals("Rear-Left")) {
            vgl.setFacing(4);
        } else if (facing.equals("Rear")) {
            vgl.setFacing(3);
            UnitUtil.changeMountStatus(mek, vgl, location, -1, true);
        }
        return true;
    }

    /**
     * For the given Mek, adds Clan CASE in every location that has potentially
     * explosive equipment (this includes PPC Capacitors) and removes it from all
     * other locations.
     * Calls {@link Mech#addClanCase()}. This method does not check if other
     * CASE types are already present on a location.
     *
     * @param mek the mek to update
     */
    public static void updateClanCasePlacement(Mech mek) {
        if (mek.isClan()) {
            removeAllMounteds(mek, EquipmentType.get(EquipmentTypeLookup.CLAN_CASE));
            mek.addClanCase();
        }
    }

    /**
     * Returns true if the given Equipment is available as equipment to the given Mek.
     * Only valid to use for MiscTypes, not WeaponTypes nor AmmoTypes, also physical
     * weapons return false despite being MiscType.
     *
     * @param eq The tested equipment
     * @param unit The Mek unit
     * @return true if the equipment is usable by the Mek
     */
    // TODO: Make this behave like the other isXYZEquipment
    public static boolean isMechEquipment(EquipmentType eq, Mech unit) {
        if (UnitUtil.isArmorOrStructure(eq)) {
            return false;
        }

        if ((eq instanceof CLTAG) || (eq instanceof ISC3MBS)
                || (eq instanceof ISC3M) || (eq instanceof ISTAG)
                || (eq instanceof AmmoType && ((AmmoType) eq).getAmmoType() == AmmoType.T_COOLANT_POD)
                || (eq instanceof CLLightTAG) || (eq instanceof ISAMS)
                || (eq instanceof CLAMS) || (eq instanceof ISLaserAMS)
                || (eq instanceof CLLaserAMS) || (eq instanceof ISAPDS)) {
            return true;
        }

        if ((eq instanceof MiscType)) {
            if (eq.isAnyOf(EquipmentTypeLookup.LAM_FUEL_TANK, EquipmentTypeLookup.LAM_BOMB_BAY)
                    && !(unit instanceof LandAirMech)) {
                return false;
            }
            if ((eq.hasFlag(MiscType.F_QUAD_TURRET) || eq.hasFlag(MiscType.F_RAM_PLATE))
                    && !(unit instanceof QuadMech)) {
                return false;
            }

            if (!((unit instanceof BipedMech) || (unit instanceof TripodMech))
                    && (eq.hasFlag(MiscType.F_SHOULDER_TURRET))) {
                return false;
            }

            if (unit.isSuperHeavy()
                    && (eq.hasFlag(MiscType.F_ACTUATOR_ENHANCEMENT_SYSTEM)
                            || eq.hasFlag(MiscType.F_MASC) // to catch Supercharger
                            || eq.hasFlag(MiscType.F_SCM)
                            || eq.hasFlag(MiscType.F_MODULAR_ARMOR)
                            || eq.hasFlag(MiscType.F_PARTIAL_WING)
                            || eq.hasFlag(MiscType.F_UMU))) {
                return false;
            }

            if ((unit instanceof LandAirMech)
                    && ((eq.hasFlag(MiscType.F_MASC) && eq.getSubType() == MiscType.S_SUPERCHARGER)
                            || eq.hasFlag(MiscType.F_MODULAR_ARMOR)
                            || eq.hasFlag(MiscType.F_JUMP_BOOSTER)
                            || eq.hasFlag(MiscType.F_PARTIAL_WING)
                            || eq.hasFlag(MiscType.F_VOIDSIG)
                            || eq.hasFlag(MiscType.F_NULLSIG)
                            || eq.hasFlag(MiscType.F_BLUE_SHIELD)
                            || eq.hasFlag(MiscType.F_CHAMELEON_SHIELD)
                            || eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)
                            || eq.hasFlag(MiscType.F_DUMPER)
                            || eq.hasFlag(MiscType.F_HEAVY_BRIDGE_LAYER)
                            || eq.hasFlag(MiscType.F_MEDIUM_BRIDGE_LAYER)
                            || eq.hasFlag(MiscType.F_LIGHT_BRIDGE_LAYER)
                            || (eq.hasFlag(MiscType.F_CLUB)
                                    && (eq.getSubType() == MiscType.S_BACKHOE)
                                    || (eq.getSubType() == MiscType.S_COMBINE)))) {
                return false;
            }

            if (eq.hasFlag(MiscType.F_FUEL)) {
                return unit.isIndustrial()
                        && ((unit.getEngine().getEngineType() == Engine.COMBUSTION_ENGINE)
                            || (unit.getEngine().getEngineType() == Engine.FUEL_CELL));
            }
            if (eq.hasFlag(MiscType.F_ENVIRONMENTAL_SEALING) || eq.is("Cargo Container (10 tons)")) {
                return unit.isIndustrial();
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

    public static boolean isMechWeapon(EquipmentType eq, Entity unit) {
        if (eq instanceof InfantryWeapon) {
            return false;
        }

        if (UnitUtil.isHeatSink(eq) || UnitUtil.isArmorOrStructure(eq)
                || UnitUtil.isJumpJet(eq)
                || isMechEquipment(eq, (Mech) unit)) {
            return false;
        }

        if (eq instanceof AmmoType) {
            return false;
        }

        if (eq instanceof WeaponType) {
            WeaponType weapon = (WeaponType) eq;
            if (!weapon.hasFlag(WeaponType.F_MECH_WEAPON) || isNonMekOrTankWeapon(unit, weapon)) {
                return false;
            }
            if ((unit instanceof LandAirMech)
                    && (weapon.getAmmoType() == AmmoType.T_GAUSS_HEAVY
                    || weapon.getAmmoType() == AmmoType.T_IGAUSS_HEAVY)) {
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * A location CriticalSlot sorter using the official sort order (mostly)
     */
    public static class MekCritSlotSorter implements Comparator<CriticalSlot> {

        private final MekMountedSorter mountedSorter;

        public MekCritSlotSorter(Mech mek) {
            mountedSorter = new MekMountedSorter(mek);
        }

        @Override
        public int compare(CriticalSlot critA, CriticalSlot critB) {
            return mountedSorter.compare(critA.getMount(), critB.getMount());
        }
    }


    /**
     * A Mounted sorter using the official sort order (mostly)
     */
    public static class MekMountedSorter implements Comparator<Mounted> {

        private final Mech mek;

        public MekMountedSorter(Mech mek) {
            this.mek = mek;
        }

        @Override
        public int compare(Mounted mountedA, Mounted mountedB) {
            int coarseOrderA = getCoarseOrdering(mek, mountedA);
            int coarseOrderB = getCoarseOrdering(mek, mountedB);
            if ((coarseOrderA == 4) && (coarseOrderB == 4)) {
                // compare average damage; using Aero damage here
                double dmgA = 0;
                double dmgB = 0;
                // Weapons that deal heat but not damage should sort last after weapons that deal damage
                // The only weapon I could find with positive aerospace damage but only heat damage against mechs
                // is the plasma cannon. If others exist, this could be made to be a more comprehensive check.
                if (mountedA.getType() instanceof WeaponType && !(mountedA.getType() instanceof CLPlasmaCannon)) {
                    dmgA = ((WeaponType) mountedA.getType()).getShortAV();
                }
                if (mountedB.getType() instanceof WeaponType && !(mountedB.getType() instanceof CLPlasmaCannon)) {
                    dmgB = ((WeaponType) mountedB.getType()).getShortAV();
                }
                if (dmgA != dmgB) {
                    return (dmgA > dmgB) ? -1 : 1;
                } else {
                    // equal damage, compare crits
                    int critsA = mountedA.getCriticals();
                    int critsB = mountedB.getCriticals();
                    if (critsA != critsB) {
                        return (critsA > critsB) ? -1 : 1;
                    } else {
                        // equal crits, compare weight
                        double weightA = mountedA.getType().getTonnage(mek);
                        double weightB = mountedB.getType().getTonnage(mek);
                        return Double.compare(weightB, weightA);
                    }
                }
            } else if ((coarseOrderA == 5) && (coarseOrderB == 5)) {
                AmmoType ammoA = (AmmoType) mountedA.getType();
                AmmoType ammoB = (AmmoType) mountedB.getType();
                int dmgA = ammoA.getRackSize() * ammoA.getDamagePerShot();
                int dmgB = ammoB.getRackSize() * ammoB.getDamagePerShot();
                return Integer.compare(dmgB, dmgA);
            } else {
                return Integer.compare(coarseOrderA, coarseOrderB);
            }
        }
    }

    /**
     * Removes the system crits of the given type from the location on the given mek.
     *
     * @param mek The Mek
     * @param systemType The system type, e.g. {@link Mech#SYSTEM_LIFE_SUPPORT}
     * @param loc The location
     */
    public static void removeSystemCrits(Mech mek, int systemType, int loc) {
        for (int slot = 0; slot < mek.getNumberOfCriticals(loc); slot++) {
            CriticalSlot cs = mek.getCritical(loc, slot);
            if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_SYSTEM) && (cs.getIndex() == systemType)) {
                mek.setCritical(loc, slot, null);
            }
        }
    }
}