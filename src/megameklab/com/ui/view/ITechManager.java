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
import megameklab.com.util.CConfig;

/**
 * Implementing classes dictate tech base and rule level and can determine whether any given technology
 * is legal within the constraints.
 * 
 * @author Neoancient
 *
 */
public interface ITechManager {
    
    int getIntroYear();
    int getTechYear();
    boolean isClan();
    boolean isMixedTech();
    SimpleTechLevel getTechLevel();
    
    default boolean isLegal(ITechnology tech) {
        // Unofficial tech has the option to ignore year availability
        if ((getTechLevel() == SimpleTechLevel.UNOFFICIAL)
                && CConfig.getBooleanParam(CConfig.CONFIG_TECH_UNOFFICAL_NO_YEAR)) {
            return isMixedTech() || (tech.getTechBase() == ITechnology.TECH_BASE_ALL)
                    || (isClan() == tech.isClan());
        }
        boolean useTP = CConfig.getBooleanParam(CConfig.CONFIG_TECH_PROGRESSION);
        boolean showExtinct = CConfig.getBooleanParam(CConfig.CONFIG_TECH_EXTINCT);
        if (isMixedTech()) {
            if (!tech.isAvailableIn(getTechYear())
                    && (!showExtinct || (tech.getIntroductionDate() < getIntroYear()))) {
                return false;
            } else if (useTP) {
                // If using tech progression with mixed tech, we pass if either IS or Clan meets the required level
                return tech.getSimpleLevel(getTechYear(), true).compareTo(getTechLevel()) <= 0
                        || tech.getSimpleLevel(getTechYear(), false).compareTo(getTechLevel()) <= 0;
            }
        } else {
            if (tech.getTechBase() != ITechnology.TECH_BASE_ALL
                    && isClan() != tech.isClan()) {
                return false;
            } else if (!tech.isAvailableIn(getIntroYear(), isClan())
                    && (!showExtinct || (tech.getIntroductionDate(isClan()) < getIntroYear()))) {
                return false;
            } else if (useTP) {
                return tech.getSimpleLevel(getTechYear(), isClan()).compareTo(getTechLevel()) <= 0;
            }
        }
        // It's available in the year and we're not using tech progression, so just check the tech level.
        return tech.getStaticTechLevel().compareTo(getTechLevel()) <= 0;
    }

}
