/*
 * Copyright (C) 2015-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import megamek.common.SimpleTechLevel;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;

/**
 * The EntitySource provides access to the unit, i.e. the current Entity instance, that is being edited. It is important
 * not to store the specific Entity instance as certain changes (e.g. changing between ASF and CF or between normal BM
 * and superheavy) require creating a new Entity subclass object and discarding the previous.
 *
 * @author nwalczak
 */
public interface EntitySource {

    Entity getEntity();

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entityType An ETYPE flag indicating the type of Entity to create. Rather than the entire bitmask, only the
     *                   flag that distinguishes the class from its parent is used.
     */
    default void createNewUnit(long entityType) {
        createNewUnit(entityType, false, false, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entityType  An ETYPE flag indicating the type of Entity to create. Rather than the entire bitmask, only
     *                    the flag that distinguishes the class from its parent is used.
     * @param isPrimitive Whether the new Entity should be primitive; not used by all unit types
     */
    default void createNewUnit(long entityType, boolean isPrimitive) {
        createNewUnit(entityType, isPrimitive, false, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entityType   An ETYPE flag indicating the type of Entity to create. Rather than the entire bitmask, only
     *                     the flag that distinguishes the class from its parent is used.
     * @param isPrimitive  Whether the new Entity should be primitive; not used by all unit types
     * @param isIndustrial Whether the new Entity should be an industrial mek; not used by other unit types
     */
    default void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial) {
        createNewUnit(entityType, isPrimitive, isIndustrial, null);
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entityType An ETYPE flag indicating the type of Entity to create. Rather than the entire bitmask, only the
     *                   flag that distinguishes the class from its parent is used.
     * @param oldUnit    If not null, the basic information (name, year, source, tech level, manual bv) will be copied
     *                   from the old unit.
     */
    default void createNewUnit(long entityType, Entity oldUnit) {
        createNewUnit(entityType, oldUnit.isPrimitive(), false, oldUnit);
    }

    default void copyUnitBasics(Entity newUnit, Entity oldUnit) {
        int constructionIntroYear = newUnit.getConstructionTechAdvancement().getIntroductionDate();
        newUnit.setChassis(oldUnit.getChassis());
        newUnit.setModel(oldUnit.getModel());
        newUnit.setYear(Math.max(oldUnit.getYear(), constructionIntroYear));
        newUnit.setOriginalBuildYear(Math.max(oldUnit.getOriginalBuildYear(), constructionIntroYear));
        newUnit.setSource(oldUnit.getSource());
        newUnit.setPublished(oldUnit.getPublished());
        newUnit.setManualBV(oldUnit.getManualBV());
        SimpleTechLevel lvl = SimpleTechLevel.max(newUnit.getStaticTechLevel(),
              SimpleTechLevel.convertCompoundToSimple(oldUnit.getTechLevel()));
        newUnit.setTechLevel(lvl.getCompoundTechLevel(oldUnit.isClan()));
        newUnit.setMixedTech(oldUnit.isMixedTech());
    }

    /**
     * Replace the current Entity with a new one of the indicated type
     *
     * @param entityType   An ETYPE flag indicating the type of Entity to create. Rather than the entire bitmask, only
     *                     the flag that distinguishes the class from its parent is used.
     * @param isPrimitive  Whether the new Entity should be primitive; not used by all unit types
     * @param isIndustrial Whether the new Entity should be an industrial mek; not used by other unit types
     * @param oldUnit      If not null, the basic information (name, year, source, tech level, manual bv) will be copied
     *                     from the old unit.
     */
    void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit);

    /**
     * @return the current TechManager, which provides the means to determine legality of any piece of tech.
     */
    ITechManager getTechManager();
}
