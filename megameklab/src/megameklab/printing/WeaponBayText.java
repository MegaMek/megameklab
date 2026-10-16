/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.EquipmentFlag;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Jumpship;
import megamek.common.units.Warship;
import megamek.common.weapons.AmmoWeapon;

/**
 * Convenience class for storing information about weapon bays for printing. This consists of a list of the weapons in
 * the bay with heat and damage, along with the location of the bay.
 *
 * @author arlith
 */
public class WeaponBayText implements Comparable<WeaponBayText> {

    /**
     * Keeps track of the distinct weapons in this bay, along with a count.
     */
    final Map<WeaponType, Integer> weapons = new HashMap<>();

    /**
     * Track the ammo for each weaponType in the bay.
     */
    final Map<WeaponType, List<Mounted<?>>> weaponAmmo = new HashMap<>();

    /**
     * Track any linked equipment that affects the AV or heat. By the rules, most of them are either all or none for the
     * entire bay (or ship), but for PPC capacitors there is no published rule I can find that the entire bay has to
     * have them, or even all the same type within the bay. It's easier to treat them all the same, and this will also
     * support illegal builds that only put Artemis on some weapons, or mix Artemis types.
     */
    final Map<WeaponType, Map<EquipmentType, Integer>> augmentations = new HashMap<>();

    final boolean rear;
    /**
     * The location of the bay, or locations if multiple identical bays are combined.
     */
    public List<Integer> loc = new ArrayList<>();

    /**
     * @param l    The location index
     * @param rear Whether the bay is rear mounted (dropship wing/aft sides)
     */
    public WeaponBayText(int l, boolean rear) {
        loc.add(l);
        this.rear = rear;
    }

    /**
     * Add a new weapon into this bay.
     *
     * @param weapon The weapon to add to the bay
     *
     * @return Whether this is the first weapon of its type in the bay
     */
    public boolean addBayWeapon(Mounted<?> weapon) {
        WeaponType weaponType = (WeaponType) weapon.getType();
        int count = weapons.merge(weaponType, 1, Integer::sum);
        if (null != weapon.getLinkedBy()) {
            augmentations.computeIfAbsent(weaponType, ignored -> new HashMap<>())
                  .merge(weapon.getLinkedBy().getType(), 1, Integer::sum);
        }
        return count == 1;
    }

    /**
     * Add a new ammo into this bay.
     *
     * @param ammo The ammo to add to the bay
     */
    public void addBayAmmo(WeaponType weaponType, AmmoMounted ammo) {
        if (weaponType instanceof AmmoWeapon && ammo.getBaseShotsLeft() > 0) {
            weaponAmmo.computeIfAbsent(weaponType, ignored -> new ArrayList<>()).add(ammo);
        }
    }

    /** Immutable contents, excluding location. Ammo is a multiset of individual bins, not a shot total. */
    CombinationKey combinationKey() {
        Map<WeaponType, Map<AmmoDescriptor, Integer>> ammo = new HashMap<>();
        for (Map.Entry<WeaponType, List<Mounted<?>>> entry : weaponAmmo.entrySet()) {
            Map<AmmoDescriptor, Integer> counts = new HashMap<>();
            for (Mounted<?> mounted : entry.getValue()) {
                counts.merge(new AmmoDescriptor(mounted.getType().getShortName(), mounted.getBaseShotsLeft()), 1,
                      Integer::sum);
            }
            ammo.put(entry.getKey(), Map.copyOf(counts));
        }
        Map<WeaponType, Map<EquipmentType, Integer>> augmentations = new HashMap<>();
        for (Map.Entry<WeaponType, Map<EquipmentType, Integer>> entry : this.augmentations.entrySet()) {
            augmentations.put(entry.getKey(), Map.copyOf(entry.getValue()));
        }
        return new CombinationKey(Map.copyOf(weapons), Map.copyOf(ammo), Map.copyOf(augmentations));
    }

    record CombinationKey(Map<WeaponType, Integer> weapons,
                          Map<WeaponType, Map<AmmoDescriptor, Integer>> ammo,
                          Map<WeaponType, Map<EquipmentType, Integer>> augmentations) { }

    record AmmoDescriptor(String name, int shots) { }

    static int opposingLocation(int location) {
        return switch (location) {
            case Jumpship.LOC_FLS -> Jumpship.LOC_FRS;
            case Jumpship.LOC_FRS -> Jumpship.LOC_FLS;
            case Jumpship.LOC_ALS -> Jumpship.LOC_ARS;
            case Jumpship.LOC_ARS -> Jumpship.LOC_ALS;
            case Warship.LOC_LBS -> Warship.LOC_RBS;
            case Warship.LOC_RBS -> Warship.LOC_LBS;
            default -> -1;
        };
    }

    static boolean rearMustMatch(int location) {
        // The front-side indices also represent the left/right wings on a DropShip.
        return location == Jumpship.LOC_FLS || location == Jumpship.LOC_FRS;
    }

    /**
     * Combine two WeaponBayTexts. Since they should both contain the same weapons, the only thing that needs to be
     * updated is the locations. The caller must first match their contents and opposing locations.
     *
     * @param other The other bay to combine with this one
     */
    public void combine(WeaponBayText other) {
        loc.addAll(other.loc);
        loc.sort(Comparator.comparingInt(this::getLocWeight));
    }

    /**
     * @param flag A MiscType flag
     *
     * @return The number of weapons in the entire bay linked by equipment with the given flag
     */
    public int countAugmentations(EquipmentFlag flag) {
        int count = 0;
        for (WeaponType weaponType : augmentations.keySet()) {
            count += countAugmentations(weaponType, flag);
        }
        return count;
    }

    /**
     * @param weaponType A type of weapon in the bay
     * @param flag       A MiscType flag
     *
     * @return The number of weapons of the given type in the bay linked by equipment with the given flag
     */
    public int countAugmentations(WeaponType weaponType, EquipmentFlag flag) {
        int count = 0;
        if (augmentations.containsKey(weaponType)) {
            for (EquipmentType equipmentType : augmentations.get(weaponType).keySet()) {
                if (equipmentType.hasFlag(flag)) {
                    count += augmentations.get(weaponType).get(equipmentType);
                }
            }
        }
        return count;
    }

    /**
     * @param flag A MiscType flag
     *
     * @return Whether all weapons in the bay are linked by equipment with the given flag
     */
    public boolean allHaveAugmentation(EquipmentFlag flag) {
        return countAugmentations(flag) == weapons.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Compare two WeaponBayTexts based upon location
     */
    @Override
    public int compareTo(WeaponBayText o) {
        int v1 = getLocWeight(loc.getFirst());
        int v2 = getLocWeight(o.loc.getFirst());
        return v1 - v2;
    }

    /**
     * The display order for Warship locations is different from the numerical order of the defines, so we want to get
     * the loc weights for sorting purposes.
     *
     * @param loc The location index
     *
     * @return The sort order for the location
     */
    private int getLocWeight(int loc) {
        return switch (loc) {
            case Jumpship.LOC_NOSE -> 0;
            case Jumpship.LOC_FLS -> rear ? 2 : 1;
            case Jumpship.LOC_FRS -> rear ? 4 : 3;
            case Warship.LOC_LBS -> 5;
            case Warship.LOC_RBS -> 6;
            case Jumpship.LOC_ALS -> 7;
            case Jumpship.LOC_ARS -> 8;
            default -> 9;
        };
    }

}
