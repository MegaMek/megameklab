/*
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.com.ui.mek;

import megamek.common.*;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import java.util.*;

import static java.util.stream.Collectors.toSet;
import static megameklab.com.util.UnitUtil.*;

/**
 * Utility methods that are likely only applicable to Meks
 */
public final class BMUtils {

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
        List<CriticalSlot> presentGear = extricateCritSlots(mek, location);
        refillCritSlots(mek, location, presentGear);
    }

    /**
     * Clears all links of the given equipment to other equipment and unallocates
     * it (assigns to LOC_NONE). Note: Does not clear the equipment's crit slots from its
     * former location. For this, use {@link UnitUtil#removeCriticals(Entity, Mounted)}
     */
    public static void clearMountedLocationAndLinked(Mounted equipment) {
        if ((Entity.LOC_NONE != equipment.getLocation() && !equipment.isOneShot())) {
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
        for (Mounted mount : mek.getEquipment()) {
            if (!BMUtils.isFMU(mount) || (mount.getLocation() != Entity.LOC_NONE)) {
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
            if ((critSlot != null) && BMUtils.isFMU(critSlot.getMount())) {
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
        for (Mounted mount : mek.getEquipment()) {
            int externalEngineHS = UnitUtil.getCriticalFreeHeatSinks(mek, mek.hasCompactHeatSinks());
            for (int location = Mech.LOC_HEAD; location < mek.locations(); location++) {

                if (!UnitUtil.isValidLocation(mek, mount.getType(), location)) {
                    continue;
                }

                int continuousNumberOfCrits = UnitUtil.getHighestContinuousNumberOfCrits(mek, location);
                int critsUsed = UnitUtil.getCritsUsed(mount);
                if (continuousNumberOfCrits < critsUsed) {
                    continue;
                }
                if ((mount.getLocation() == Entity.LOC_NONE)) {
                    if (UnitUtil.isHeatSink(mount) && (externalEngineHS-- > 0)) {
                        continue;
                    }
                } else {
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
        presentGear.sort(new BMUtils.MekLocationSorter(mek));
        presentGear = reOrderLinkedEquipment(presentGear);
        refillCritSlots(mek, location, presentGear);
    }

    /**
     * Removes all crit slots from the given location except for system crit slots
     * (e.g. engine) and returns them as a list.
     */
    private static List<CriticalSlot> extricateCritSlots(Mech mek, int location) {
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
    private static void refillCritSlots(Mech mek, int location, List<CriticalSlot> critList) {
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
     * A location crit slot sorter using the official sort order (mostly)
     */
    public static class MekLocationSorter implements Comparator<CriticalSlot> {
        
        private final Mech mek;
        
        public MekLocationSorter(Mech mek) {
            this.mek = mek;
        }

        @Override
        public int compare(CriticalSlot critA, CriticalSlot critB) {
            int coarseOrderA = getCoarseOrder(mek, critA);
            int coarseOrderB = getCoarseOrder(mek, critB);
            if (coarseOrderA == 4 && coarseOrderB == 4) {
                // compare average damage; using Aero damage here
                double dmgA = 0;
                double dmgB = 0;
                if (critA.getMount().getType() instanceof WeaponType) {
                    dmgA = ((WeaponType) critA.getMount().getType()).getShortAV();
                }
                if (critB.getMount().getType() instanceof WeaponType) {
                    dmgB = ((WeaponType) critB.getMount().getType()).getShortAV();
                }
                if (dmgA != dmgB) {
                    return (dmgA > dmgB) ? -1 : 1;
                } else {
                    // equal damage, compare crits
                    int critsA = critA.getMount().getCriticals();
                    int critsB = critB.getMount().getCriticals();
                    if (critsA != critsB) {
                        return (critsA > critsB) ? -1 : 1;
                    } else {
                        // equal crits, compare weight
                        double weightA = critA.getMount().getType().getTonnage(mek);
                        double weightB = critB.getMount().getType().getTonnage(mek);
                        return Double.compare(weightB, weightA);
                    }
                }
            } else if (coarseOrderA == 5 && coarseOrderB == 5) {
                AmmoType ammoA = (AmmoType) critA.getMount().getType();
                AmmoType ammoB = (AmmoType) critB.getMount().getType();
                int dmgA = ammoA.getRackSize() * ammoA.getDamagePerShot();
                int dmgB = ammoB.getRackSize() * ammoB.getDamagePerShot();
                return Integer.compare(dmgB, dmgA);
            } else {
                return Integer.compare(coarseOrderA, coarseOrderB);
            }
        }
    }

    /**
     * Returns a number indicating the order in which equipment should be sorted
     * within a location according to the official crit slot sorting. Weapons and
     * ammo require further internal sorting and linked equipment such as Artemis and PPC
     * Capacitors also require extra treatment to be placed behind their weapon.
     */
    public static int getCoarseOrder(Mech mek, CriticalSlot critSlot) {
        if (isPartialWingCrit(critSlot)) {
            return 1;
        } else if (UnitUtil.isHeatSink(critSlot.getMount())) {
            return 2;
        } else if (UnitUtil.isJumpJet(critSlot.getMount())) {
            return 3;
        } else if (UnitUtil.isMechWeapon(critSlot.getMount().getType(), mek)) {
            return 4;
        } else if (critSlot.getMount().getType() instanceof AmmoType) {
            return 5;
        } else if (critSlot.getMount().getType().isHittable()) {
            return 6;
        } else if (isCASECrit(critSlot)) {
            return 7;
        } else if (EquipmentType.isStructureType(critSlot.getMount().getType())) {
            return 8;
        } else if (EquipmentType.isArmorType(critSlot.getMount().getType())) {
            return 9;
        } else {
            return 10;
        }
    }

    public static boolean isPartialWingCrit(CriticalSlot critSlot) {
        return (critSlot.getMount().getType() instanceof MiscType)
                && critSlot.getMount().getType().hasFlag(MiscType.F_PARTIAL_WING);
    }

    public static boolean isCASECrit(CriticalSlot critSlot) {
        return (critSlot.getMount().getType() instanceof MiscType)
                && (critSlot.getMount().getType().hasFlag(MiscType.F_CASE)
                || critSlot.getMount().getType().hasFlag(MiscType.F_CASEP)
                || critSlot.getMount().getType().hasFlag(MiscType.F_CASEII));
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
        int highestNumberOfCrits = 0;
        int currentCritCount = 0;
        for (int slot = 0; slot < mek.getNumberOfCriticals(location); slot++) {
            CriticalSlot critSlot = mek.getCritical(location, slot);
            if ((critSlot == null) || (ignoreFMU && BMUtils.isFMU(critSlot.getMount()))) {
                currentCritCount++;
            } else {
                currentCritCount = 0;
            }
            highestNumberOfCrits = Math.max(currentCritCount, highestNumberOfCrits);
        }
        return highestNumberOfCrits;
    }
}
