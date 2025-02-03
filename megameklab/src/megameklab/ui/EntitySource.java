/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab
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
package megameklab.ui;

import megamek.common.Entity;
import megamek.common.ITechManager;

/**
 * In order to track when changes are made to the unit, we will have one
 * ultimate holder of the Entity instance, and pass that around. That way, when
 * the Entity is accessed, we can set a dirty flag so we know changes were made.
 *
 * @author nwalczak
 *
 */
public interface EntitySource {

    Entity getEntity();

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entitytype An ETYPE flag indicating the type of Entity to create.
     *                   Rather than the entire
     *                   bitmask, only the flag that distinguishes the class from
     *                   its parent is used.
     */
    default void createNewUnit(long entitytype) {
        createNewUnit(entitytype, false, false, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entitytype  An ETYPE flag indicating the type of Entity to create.
     *                    Rather than the entire
     *                    bitmask, only the flag that distinguishes the class from
     *                    its parent is used.
     * @param isPrimitive Whether the new Entity should be primitive; not used by
     *                    all unit types
     */
    default void createNewUnit(long entitytype, boolean isPrimitive) {
        createNewUnit(entitytype, isPrimitive, false, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entitytype   An ETYPE flag indicating the type of Entity to create.
     *                     Rather than the entire
     *                     bitmask, only the flag that distinguishes the class from
     *                     its parent is used.
     * @param isPrimitive  Whether the new Entity should be primitive; not used by
     *                     all unit types
     * @param isIndustrial Whether the new Entity should be an industrial mek; not
     *                     used by other unit types
     */
    default void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial) {
        createNewUnit(entitytype, isPrimitive, isIndustrial, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entitytype An ETYPE flag indicating the type of Entity to create.
     *                   Rather than the entire
     *                   bitmask, only the flag that distinguishes the class from
     *                   its parent is used.
     * @param oldUnit    If not null, the basic information (name, year, source,
     *                   tech level, manual bv)
     *                   will be copied from the old unit.
     */
    default void createNewUnit(long entitytype, Entity oldUnit) {
        createNewUnit(entitytype, oldUnit.isPrimitive(), false, oldUnit);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entitytype   An ETYPE flag indicating the type of Entity to create.
     *                     Rather than the entire
     *                     bitmask, only the flag that distinguishes the class from
     *                     its parent is used.
     * @param isPrimitive  Whether the new Entity should be primitive; not used by
     *                     all unit types
     * @param isIndustrial Whether the new Entity should be an industrial mek; not
     *                     used by other unit types
     * @param oldUnit      If not null, the basic information (name, year, source,
     *                     tech level, manual bv)
     *                     will be copied from the old unit.
     */
    void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit);

    /**
     * @return the current TechManager, which provides the means to determine
     *         legality of any piece
     *         of tech.
     */
    ITechManager getTechManager();

    /**
     * Mark the entity provided by this EntitySource a having a read-only chassis, so that the only changes that can be made are to omni pod space.
     * No enforcement is provided, you should call {@link EntitySource#canModifyBaseChassis} to determine if non-modifications should be allowed.
     * @param locked {@code true} if the entity's base chassis should be treated as read-only
     */
    default void setBaseChassisModifiable(boolean locked) {}

    /**
     * Determine if you should allow modifications to an omni-unit's base chassis.
     * This is not enforced, be careful.
     * @return {@code true} if you can modify the entity's base chassis.
     */
    default boolean canModifyBaseChassis() {
        return true;
    }
}
