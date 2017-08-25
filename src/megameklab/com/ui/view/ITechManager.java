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
    int getFaction();
    boolean isClan();
    boolean isMixedTech();
    SimpleTechLevel getTechLevel();
    
    default boolean isLegal(ITechnology tech) {
        // Unofficial tech has the option to ignore year availability
        if ((getTechLevel() == SimpleTechLevel.UNOFFICIAL)
                && CConfig.getBooleanParam(CConfig.TECH_UNOFFICAL_NO_YEAR)) {
            return isMixedTech() || (tech.getTechBase() == ITechnology.TECH_BASE_ALL)
                    || (isClan() == tech.isClan());
        }
        boolean useTP = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        boolean showExtinct = CConfig.getBooleanParam(CConfig.TECH_EXTINCT);

        int faction = getFaction();
        boolean availableIS = tech.isAvailableIn(getIntroYear(), false, faction);
        boolean availableClan = tech.isAvailableIn(getIntroYear(), true, faction);
        boolean extinctIS = tech.isExtinct(getIntroYear(), false, faction);
        boolean extinctClan = tech.isExtinct(getIntroYear(), true, faction);
        // A little bit of hard-coded universe detail
        if ((faction == ITechnology.F_CS)
                && extinctIS && (tech.getReintroductionDate(false) != ITechnology.DATE_NONE)) {
            availableIS = true;
            extinctIS = false;
            faction = ITechnology.F_TH;
        } else if (isClan() && !availableClan && tech.isAvailableIn(2787, false, ITechnology.F_TH)) {
            if (!extinctClan) {
                availableClan = true;
                faction = ITechnology.F_TH;
            }
        }
        if (isMixedTech()) {
            if (!availableIS && !availableClan
                    && !(showExtinct && (extinctIS || extinctClan))) {
                return false;
            } else if (useTP) {
                // If using tech progression with mixed tech, we pass if either IS or Clan meets the required level
                return tech.getSimpleLevel(getTechYear(), true, faction).compareTo(getTechLevel()) <= 0
                        || tech.getSimpleLevel(getTechYear(), false, faction).compareTo(getTechLevel()) <= 0;
            }
        } else {
            if (tech.getTechBase() != ITechnology.TECH_BASE_ALL
                    && isClan() != tech.isClan()) {
                return false;
            } else if (isClan() && !availableClan && !(showExtinct && extinctClan)) {
                return false;
            } else if (!isClan() && !availableIS && !(showExtinct && extinctIS)) {
                return false;
            } else if (useTP) {
                return tech.getSimpleLevel(getTechYear(), isClan(), faction).compareTo(getTechLevel()) <= 0;
            }
        }
        // It's available in the year and we're not using tech progression, so just check the tech level.
        return tech.getStaticTechLevel().compareTo(getTechLevel()) <= 0;
    }
}
