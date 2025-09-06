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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @return Whether true if the weapon was added as new, false if it was already in the bay and just incremented
     */
    public boolean addBayWeapon(Mounted<?> weapon) {
        boolean asNew;
        WeaponType weaponType = (WeaponType) weapon.getType();
        if (weapons.containsKey(weaponType)) {
            weapons.put(weaponType, weapons.get(weaponType) + 1);
            asNew = false;
        } else {
            weapons.put(weaponType, 1);
            asNew = true;
        }
        if (null != weapon.getLinkedBy()) {
            augmentations.putIfAbsent(weaponType, new HashMap<>());
            augmentations.get(weaponType).merge(weapon.getLinkedBy().getType(), 1, Integer::sum);
        }
        return asNew;
    }

    /**
     * Add a new ammo into this bay.
     *
     * @param ammo The ammo to add to the bay
     */
    public void addBayAmmo(WeaponType weaponType, AmmoMounted ammo) {
        if (weaponType instanceof AmmoWeapon) {
            if (ammo.getBaseShotsLeft() > 0) {
                if (weaponAmmo.containsKey(weaponType)) {
                    // If the ammo is already in the bay, just add to the count.
                    List<Mounted<?>> ammoList = weaponAmmo.get(weaponType);
                    ammoList.add(ammo);
                } else {
                    // If the ammo isn't in the bay, add it to the list.
                    List<Mounted<?>> ammoList = new ArrayList<>();
                    ammoList.add(ammo);
                    weaponAmmo.put(weaponType, ammoList);
                }
            }
            // If the ammo is empty, we don't want to add it to the bay.
        }
        // If the weapon isn't an ammo weapon, we don't want to add it to the bay.
    }

    /**
     * Determines if two WeaponBayTexts are laterally similar and hence can be combined. That is, if there is a weapon
     * bay on the left side that is identical to one on the right side, then those two can be combined in a location
     * like FRS/FLS. This allows weapon lists to be compacted.
     *
     * @param other The other instance
     *
     * @return Whether the two bays are identical
     */
    public boolean canCombine(WeaponBayText other) {
        // Check for opposing sides
        return loc.size() == 1
              && checkOpposingSide(loc.get(0), other.loc.get(0), rear, other.rear)
              && weapons.equals(other.weapons)
              && ammunitionMatch(other) && augmentations.equals(other.augmentations);
    }

    /**
     * Used to compare ammunition across WeaponBayTexts. Since Mounted.equals isn't implemented, we can't directly use
     * Map.equals.
     *
     * @param other The other bay
     *
     * @return Whether the ammo types and number of shots per type match
     */
    private boolean ammunitionMatch(WeaponBayText other) {
        // If the number is different, the ammo doesn't match
        if ((weaponAmmo.size() != other.weaponAmmo.size())) {
            return false;
        }
        // We then check the keys if they match
        if (!weaponAmmo.keySet().equals(other.weaponAmmo.keySet())) {
            return false;
        }
        // Now we compare the ammo sets of each weapon
        for (WeaponType weaponType : weaponAmmo.keySet()) {
            List<Mounted<?>> ammoListThis = weaponAmmo.get(weaponType);
            List<Mounted<?>> ammoListOther = other.weaponAmmo.get(weaponType);
            if (ammoListThis == null || ammoListOther == null) {
                return false;
            }
            // If the number of ammo types is different, the ammo doesn't match
            if (ammoListThis.size() != ammoListOther.size()) {
                return false;
            }
            boolean[] otherMatched = new boolean[ammoListOther.size()];
            Arrays.fill(otherMatched, false);

            for (Mounted<?> mountedThis : ammoListThis) {
                if (!(mountedThis instanceof AmmoMounted ammoThis)) {
                    return false; // Should not happen
                }
                final String nameThis = ammoThis.getType().getShortName();
                int shotsThis = ammoThis.getBaseShotsLeft();
                boolean foundMatch = false;
                for (int i = 0; i < ammoListOther.size(); i++) {
                    if (otherMatched[i]) {
                        continue; // Skip already matched items
                    }

                    Mounted<?> mountedOther = ammoListOther.get(i);
                    if (!(mountedOther instanceof AmmoMounted ammoOther)) {
                        // This shouldn't happen
                        return false;
                    }

                    String nameOther = ammoOther.getType().getShortName();
                    int shotsOther = ammoOther.getBaseShotsLeft();

                    // Check if names and shots match
                    if (nameThis.equals(nameOther) && shotsThis == shotsOther) {
                        otherMatched[i] = true;
                        foundMatch = true;
                        break; // Found a match, move to the next item
                    }
                }

                // If no match was found in the other list
                if (!foundMatch) {
                    return false;
                }
            }
        }
        // we matched all
        return true;
    }

    private boolean checkOpposingSide(int loc1, int loc2, boolean rear1, boolean rear2) {
        return switch (loc1) {
            // Jumpship.LOC_FLS and Jumpship.LOC_FRS are the same indices as
            // Dropship.LOC_LEFT_WING and Dropship.LOC_RIGHT_WING
            case Jumpship.LOC_FLS -> loc2 == Jumpship.LOC_FRS && rear1 == rear2;
            case Jumpship.LOC_FRS -> loc2 == Jumpship.LOC_FLS && rear1 == rear2;
            case Jumpship.LOC_ALS -> loc2 == Jumpship.LOC_ARS;
            case Jumpship.LOC_ARS -> loc2 == Jumpship.LOC_ALS;
            case Warship.LOC_LBS -> loc2 == Warship.LOC_RBS;
            case Warship.LOC_RBS -> loc2 == Warship.LOC_LBS;
            default -> false;
        };
    }

    /**
     * Combine two WeaponBayTexts. Since they should both contain the same weapons, the only thing that needs to be
     * updated is the locations. This should only be called if canCombine returns true for both WeaponBayTexts.
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
        int v1 = getLocWeight(loc.get(0));
        int v2 = getLocWeight(o.loc.get(0));
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
