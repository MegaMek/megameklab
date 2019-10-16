/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.view.listeners;

import megamek.common.EquipmentType;

/**
 * Listener for changes to armor. No method is required by all implementing classes so all are given a default
 * noop implementation.
 */

public interface ArmorAllocationListener {
    /**
     * Notifies of a change in the armor type
     *
     * @param at             The armor type constant from {@link EquipmentType}
     * @param armorTechLevel The tech level constant from {@link megamek.common.TechConstants TechConstants},
     *                       used to distinguish between IS and Clan versions
     */
    default void armorTypeChanged(int at, int armorTechLevel) {}

    /**
     * Notifies of a change in the armor type.
     *
     * @param armor The equipment instance of the armor to use.
     */
    default void armorTypeChanged(EquipmentType armor) {}

    /**
     * Notifies of a change in the tech rating of support vehicle armor.
     *
     * @param techRating The new tech rating, using the rating constants from {@link megamek.common.ITechnology ITechnology}.
     */
    default void armorTechRatingChanged(int techRating) {}

    /**
     * Notifies of a change in the BAR of support vehicle armor.
     *
     * @param bar The new BAR
     */
    default void armorBARRatingChanged(int bar) {}

    /**
     * Notifies of a change in armor tonnage. This is not used by units that assign armor by point.
     *
     * @param tonnage The total weight of armor on the unit.
     */
    default void armorTonnageChanged(double tonnage){}

    /**
     * Signals that the armor should be set to the maximum value for the unit.
     */
    default void maximizeArmor(){}

    /**
     * Signals that any remaining weight that has not been allocated should be used for armor, up to the armor
     * limit for the unit.
     */
    default void useRemainingTonnageArmor(){}

    /**
     * Notifies of a change in the total number of armor points on the unit. This is used for unit types that
     * assign armor by the point rather than by weight.
     *
     * @param points The total number of armor points on the unit.
     */
    default void armorFactorChanged(int points){}

    /**
     * Notifies of a change to the number of armor points assigned to a particular location
     *
     * @param location The location to assign the points
     * @param front    The number of points to assign to the front (or total to the location if there is not separate rear armor)
     * @param rear     The number of points to assign to the rear armor of the location
     */
    default void armorPointsChanged(int location, int front, int rear) {}

    /**
     * Notifies of a change in the type of armor assigned to a specific location of a unit with patchwork armor.
     *
     * @param location The location constant
     * @param armor    The type of armor used in the location.
     */
    default void patchworkChanged(int location, EquipmentType armor) {}

    /**
     * Signals that the armor should be automatically distributed to the various locations.
     */
    default void autoAllocateArmor() {}
}
