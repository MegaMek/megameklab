/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.listeners;

import megamek.common.enums.TechRating;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.EquipmentType;
import megamek.common.interfaces.ITechnology;

/**
 * Listener for changes to armor. No method is required by all implementing classes so all are given a default noop
 * implementation.
 */

public interface ArmorAllocationListener {
    /**
     * Notifies of a change in the armor type
     *
     * @param at             The armor type constant from {@link EquipmentType}
     * @param armorTechLevel The tech level constant from {@link megamek.common.TechConstants TechConstants}, used to
     *                       distinguish between IS and Clan versions
     */
    default void armorTypeChanged(int at, int armorTechLevel) {

    }

    /**
     * Notifies of a change in the armor type.
     *
     * @param armor The equipment instance of the armor to use.
     */
    default void armorTypeChanged(ArmorType armor) {

    }

    /**
     * Notifies of a change in the tech rating of support vehicle armor.
     *
     * @param techRating The new tech rating, using the rating constants from
     *                   {@link ITechnology ITechnology}.
     */
    default void armorTechRatingChanged(TechRating techRating) {

    }

    /**
     * Notifies of a change in the BAR of support vehicle armor.
     *
     * @param bar The new BAR
     */
    default void armorBARRatingChanged(int bar) {

    }

    /**
     * Notifies of a change in armor tonnage. This is not used by units that assign armor by point.
     *
     * @param tonnage The total weight of armor on the unit.
     */
    default void armorTonnageChanged(double tonnage) {

    }

    /**
     * Signals that the armor should be set to the maximum value for the unit.
     */
    default void maximizeArmor() {

    }

    /**
     * Signals that any remaining weight that has not been allocated should be used for armor, up to the armor limit for
     * the unit.
     */
    default void useRemainingTonnageArmor() {

    }

    /**
     * Notifies of a change in the total number of armor points on the unit. This is used for unit types that assign
     * armor by the point rather than by weight.
     *
     * @param points The total number of armor points on the unit.
     */
    default void armorFactorChanged(int points) {

    }

    /**
     * Notifies of a change to the number of armor points assigned to a particular location
     *
     * @param location The location to assign the points
     * @param front    The number of points to assign to the front (or total to the location if there is not separate
     *                 rear armor)
     * @param rear     The number of points to assign to the rear armor of the location
     */
    default void armorPointsChanged(int location, int front, int rear) {

    }

    /**
     * Notifies of a change in the type of armor assigned to a specific location of a unit with patchwork armor.
     *
     * @param location The location constant
     * @param armor    The type of armor used in the location.
     */
    default void patchworkChanged(int location, ArmorType armor) {

    }

    /**
     * Signals that the armor should be automatically distributed to the various locations.
     */
    default void autoAllocateArmor() {

    }
}
