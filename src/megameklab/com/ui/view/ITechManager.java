/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import megamek.common.ITechnology;
import megamek.common.SimpleTechLevel;

/**
 * Implementing classes dictate tech base and rule level and can determine whether any given technology
 * is legal within the constraints.
 * 
 * @author Neoancient
 *
 */
public interface ITechManager {
    
    int getTechYear();
    boolean isClan();
    boolean isMixedTech();
    SimpleTechLevel getTechLevel();
    
    default boolean isLegal(ITechnology tech) {
        if (isMixedTech()) {
            if (!tech.isAvailableIn(getTechYear())) {
                return false;
            }
            /* TODO: era-based option
             * return tech.getSimpleLevel(getYear(), true).compareTo(getTechLevel()) <= 0
             *       || tech.getSimpleLevel(getYear(), false).compareTo(getTechLevel()) <= 0;
                    */
        } else {
            if (tech.getTechBase() != ITechnology.TECH_BASE_ALL
                    && isClan() != tech.isClan()) {
                return false;
            }
            if (!tech.isAvailableIn(getTechYear(), isClan())) {
                return false;
            }
            /* TODO: era-based option
             * return tech.getSimpleLevel(getYear(), isClan()).compareTo(getTechLevel()) <= 0;
                    */
        }
        return tech.getStaticTechLevel().compareTo(getTechLevel()) <= 0;
    }

}
