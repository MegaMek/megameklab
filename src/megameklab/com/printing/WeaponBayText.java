/*
 * MegaMekLab - Copyright (C) 2008-2020
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

package megameklab.com.printing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamek.common.*;
import megamek.common.weapons.AmmoWeapon;

/**
 * Convenience class for storing information about weapon pays for printing.
 * This consists of a list of the weapons in the bay with heat & damage, along
 * with the location of the bay.
 *
 * @author arlith
 *
 */
public class WeaponBayText implements Comparable<WeaponBayText> {

    /**
     * Keeps track of the distinct weapons in this bay, along with a count.
     */
    public Map<WeaponType, Integer> weapons = new HashMap<>();

    /**
     * Track the ammo for each weapontype in the bay.
     */
    public Map<WeaponType, Mounted> weaponAmmo = new HashMap<>();

    /**
     * Track any linked equipment that affects the AV or heat. By the rules, most of them are either
     * all or none for the entire bay (or ship), but for PPC capacitors there is no published rule
     * I can find that the entire bay has to have them, or even all of the same type within the bay.
     * It's easier to treat them all the same, and this will also support illegal builds that only put
     * Artemis on some weapons, or mix Artemis types.
     */
    public Map<WeaponType, Map<EquipmentType, Integer>> augmentations = new HashMap<>();

    public boolean allowNosAftCombine;
    /**
     * The location of the bay, or locations if multiple identical bays are
     * combined.
     */
    public List<Integer> loc = new ArrayList<>();

    public WeaponBayText(int l, boolean combineNoseAftBays) {
        loc.add(l);
        allowNosAftCombine = combineNoseAftBays;
    }

    /**
     * Add a new weapon into this bay.
     *
     * @param weapon The weapon to add to the bay
     */
    public void addBayWeapon(Mounted weapon) {
        WeaponType wtype = (WeaponType) weapon.getType();
        if (weapons.containsKey(wtype)) {
            weapons.put(wtype, weapons.get(wtype) + 1);
        } else {
            weapons.put(wtype, 1);
            if ((wtype instanceof AmmoWeapon) && (weapon.getLinked() != null)) {
                Mounted ammo = weapon.getLinked();
                if (ammo.getType() instanceof AmmoType) {
                    weaponAmmo.put(wtype, ammo);
                }
            }
        }
        if (null != weapon.getLinkedBy()) {
            augmentations.putIfAbsent(wtype, new HashMap<>());
            augmentations.get(wtype).merge(weapon.getLinkedBy().getType(), 1, Integer::sum);
        }
    }

    /**
     * Determines if two WeaponBayTexts are lateraly similar and hence can be
     * combined. That is, if there is a weapon bay on the left side that is
     * identical to one on the right side, then those two can be combined in a
     * location like FRS/FLS. This allows weapon lists to be compacted.
     *
     * @param other The other instance
     * @return      Whether the two bays are identical
     */
    public boolean canCombine(WeaponBayText other) {
        // Check for opposing sides
        boolean opposingSide = false;
        if ((loc.size() == 1) && (other.loc.size() == 1)) {
            opposingSide = checkOpposingSide(loc.get(0), other.loc.get(0));
        }
        return opposingSide && weapons.equals(other.weapons)
                && ammosMatch(other) && augmentations.equals(other.augmentations);
    }

    /**
     * Used to compare ammos across WeaponBayTexts. Since Mounted.equals isn't
     * implemented, we can't directly use Map.equals.
     *
     * @param other The other bay
     * @return      Whether the ammo types and number of shots per type match
     */
    private boolean ammosMatch(WeaponBayText other) {
        boolean rv = (weaponAmmo.size() == other.weaponAmmo.size())
                && weaponAmmo.keySet().equals(other.weaponAmmo.keySet());
        if (rv) {
            for (WeaponType wtype : weaponAmmo.keySet()) {
                rv &= weaponAmmo.get(wtype).getBaseShotsLeft() == other.weaponAmmo.get(wtype).getBaseShotsLeft();
            }
        }
        return rv;
    }

    private boolean checkOpposingSide(int loc1, int loc2) {
        boolean rv = false;
        if (((loc1 == Jumpship.LOC_FLS) && (loc2 == Jumpship.LOC_FRS))
                || ((loc1 == Jumpship.LOC_FRS) && (loc2 == Jumpship.LOC_FLS))) {
            rv = true;
        } else if (((loc1 == Jumpship.LOC_ALS) && (loc2 == Jumpship.LOC_ARS))
                || ((loc1 == Jumpship.LOC_ARS) && (loc2 == Jumpship.LOC_ALS))) {
            rv = true;
        } else if (((loc1 == Warship.LOC_LBS) && (loc2 == Warship.LOC_RBS))
                || ((loc1 == Warship.LOC_RBS) && (loc2 == Warship.LOC_LBS))) {
            rv = true;
        }
        if ((allowNosAftCombine && ((loc1 == Jumpship.LOC_NOSE) && (loc2 == Jumpship.LOC_AFT)))
                || ((loc1 == Jumpship.LOC_AFT) && (loc2 == Jumpship.LOC_NOSE))) {
            rv = true;
        }
        return rv;
    }

    /**
     * Combine two WeaponBayTexts. Since they should both contain the same weapons,
     * the only thing that needs to be updated is the locations. This should only be
     * called if canCombine returns true for both WeaponBayTexts.
     *
     * @param other The other bay to combine with this one
     */
    public void combine(WeaponBayText other) {
        loc.addAll(other.loc);
        loc.sort(Comparator.comparingInt(this::getLocWeight));
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
     * The display order for Warship locations is different from the numerical order
     * of the defines, so we want to get the loc weights for sorting purposes.
     *
     * @param loc The location index
     * @return    The sort order for the location
     */
    private int getLocWeight(int loc) {
        switch (loc) {
            case Jumpship.LOC_NOSE:
                return 0;
            case Jumpship.LOC_FLS:
                return 1;
            case Jumpship.LOC_FRS:
                return 2;
            case Warship.LOC_LBS:
                return 3;
            case Warship.LOC_RBS:
                return 4;
            case Jumpship.LOC_ALS:
                return 5;
            case Jumpship.LOC_ARS:
                return 6;
            case Jumpship.LOC_AFT:
            default:
                return 7;
        }
    }

}
