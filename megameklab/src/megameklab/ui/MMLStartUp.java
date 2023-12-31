/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
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

import java.util.ResourceBundle;

/**
 * This class represents the different types of entry into MML that can be configured in the settings. Options
 * are e.g. starting with the splash screen main UI (as it has been), trying to load the most recent unit or
 * starting with a new unit directly.
 *
 * @author Simon (Juliez)
 */
public enum MMLStartUp {

    SPLASH_SCREEN,
    RECENT_UNIT,
    NEW_MEK,
    NEW_TANK,
    NEW_BATTLEARMOR,
    NEW_CONVINFANTRY,
    NEW_FIGHTER,
    NEW_DROPSHIP,
    NEW_JUMPSHIP,
    NEW_SUPPORTVEE,
    NEW_PROTOMEK;

    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");

    /** @return A display name for this MMLStartUp taken from the resources (possibly localised). */
    public String getDisplayName() {
        return resources.getString("MMLStartUp." + name());
    }

    /**
     * Parses the given String, returning the MMLStartUp fitting the String like the valueOf() method does,
     * but returns SPLASH_SCREEN when it can't be parsed (instead of null).
     *
     * @param startUpName A string giving one of the MMLStartUp values
     * @return the MMLStartUp parsed from the string or SPLASH_SCREEN. Never returns null.
     */
    public static MMLStartUp parse(String startUpName) {
        try {
            return valueOf(startUpName);
        } catch (IllegalArgumentException e) {
            return SPLASH_SCREEN;
        }
    }
}