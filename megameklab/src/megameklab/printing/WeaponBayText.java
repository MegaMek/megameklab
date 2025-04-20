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
 */
package megameklab.printing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamek.common.AmmoType;
import megamek.common.EquipmentFlag;
import megamek.common.EquipmentType;
import megamek.common.Jumpship;
import megamek.common.Mounted;
import megamek.common.Warship;
import megamek.common.WeaponType;
import megamek.common.weapons.AmmoWeapon;

/**
 * Convenience class for storing information about a weapon pays for printing. This consists of a list of the weapons in
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
    final Map<WeaponType, Mounted<?>> weaponAmmo = new HashMap<>();

    /**
     * Track any linked equipment that affects the AV or heat. By the rules, most of them are either all or none for the
     * entire bay (or ship), but for PPC capacitors there is no published rule I can find that the entire bay has to
     * have them, or even all the same type within the bay. It's easier to treat them all the same, and this will also
     * support illegal builds that only put Artemis on some weapons or mix Artemis types.
     */
    final Map<WeaponType, Map<EquipmentType, Integer>> augmentations = new HashMap<>();

    final boolean rear;
    /**
     * The location of the bay, or locations if multiple identical bays are combined.
     */
    public List<Integer> loc = new ArrayList<>();

    /**
     * @param l    The location index
     * @param rear Whether the bay is rear mounted (drop-ship wing/aft sides)
     */
    public WeaponBayText(int l, boolean rear) {
        loc.add(l);
        this.rear = rear;
    }

    /**
     * Add a new weapon into this bay.
     *
     * @param weapon The weapon to add to the bay
     */
    public void addBayWeapon(Mounted<?> weapon) {
        WeaponType weaponType = (WeaponType) weapon.getType();
        if (weapons.containsKey(weaponType)) {
            weapons.put(weaponType, weapons.get(weaponType) + 1);
        } else {
            weapons.put(weaponType, 1);
            if ((weaponType instanceof AmmoWeapon) && (weapon.getLinked() != null)) {
                Mounted<?> ammo = weapon.getLinked();
                if (ammo.getType() instanceof AmmoType) {
                    weaponAmmo.put(weaponType, ammo);
                }
            }
        }
        if (null != weapon.getLinkedBy()) {
            augmentations.putIfAbsent(weaponType, new HashMap<>());
            augmentations.get(weaponType).merge(weapon.getLinkedBy().getType(), 1, Integer::sum);
        }
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
        return loc.size() == 1 &&
                     checkOpposingSide(loc.get(0), other.loc.get(0), rear, other.rear) &&
                     weapons.equals(other.weapons) &&
                     ammosMatch(other) &&
                     augmentations.equals(other.augmentations);
    }

    /**
     * Used to compare ammo's across WeaponBayTexts. Since <code>Mounted.equals</code> isn't implemented, we can't
     * directly use Map.equals.
     *
     * @param other The other bay
     *
     * @return Whether the ammo types and number of shots per type match
     */
    private boolean ammosMatch(WeaponBayText other) {
        boolean rv = (weaponAmmo.size() == other.weaponAmmo.size()) &&
                           weaponAmmo.keySet().equals(other.weaponAmmo.keySet());
        if (rv) {
            for (WeaponType weaponType : weaponAmmo.keySet()) {
                rv &= weaponAmmo.get(weaponType).getBaseShotsLeft() ==
                            other.weaponAmmo.get(weaponType).getBaseShotsLeft();
            }
        }
        return rv;
    }

    private boolean checkOpposingSide(int loc1, int loc2, boolean rear1, boolean rear2) {
        return switch (loc1) {
            // Jumpship.LOC_FLS and Jumpship.LOC_FRS are the same indices as
            // Dropship.LOC_LWING and Dropship.LOC_RWING
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
     * The display order for Warship locations is different from the numerical order of the definitions, so we want to
     * get the loc weights for sorting purposes.
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
