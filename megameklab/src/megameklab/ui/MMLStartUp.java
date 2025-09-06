/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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

import java.util.ResourceBundle;

/**
 * This class represents the different types of entry into MML that can be configured in the settings. Options are e.g.
 * starting with the splash screen main UI (as it has been), trying to load the most recent unit or starting with a new
 * unit directly.
 *
 * @author Simon (Juliez)
 */
public enum MMLStartUp {

    SPLASH_SCREEN,
    RECENT_UNIT,
    RESTORE_TABS,
    NEW_MEK,
    NEW_TANK,
    NEW_BATTLEARMOR,
    NEW_CONVENTIONAL_INFANTRY,
    NEW_FIGHTER,
    NEW_DROPSHIP,
    NEW_JUMPSHIP,
    NEW_SUPPORT_VEE,
    NEW_PROTOMEK;

    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");

    /** @return A display name for this MMLStartUp taken from the resources (possibly localised). */
    public String getDisplayName() {
        return resources.getString("MMLStartUp." + name());
    }

    /**
     * Parses the given String, returning the MMLStartUp fitting the String like the valueOf() method does, but returns
     * SPLASH_SCREEN when it can't be parsed (instead of null).
     *
     * @param startUpName A string giving one of the MMLStartUp values
     *
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
