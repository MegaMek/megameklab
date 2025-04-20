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
 */
package megameklab.util;

/**
 * Scale to use when printing record sheets
 */
public enum RSScale {
    HEXES("hexes", ""), INCHES("inches", "\""), CENTIMETERS("centimeters", " cm");

    public final String fullName;
    public final String abbreviation;

    RSScale(String fullName, String abbreviation) {
        this.fullName = fullName;
        this.abbreviation = abbreviation;
    }

    /**
     * Used for display name when space is limited. If the full name is longer than six characters, the abbreviation is
     * used.
     *
     * @return A short name for display
     */
    public String shortName() {
        if (fullName.length() > 6) {
            return abbreviation.trim();
        } else {
            return fullName;
        }
    }
}
