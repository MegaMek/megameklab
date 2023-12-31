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
package megameklab.util;

/**
 * Scale to use when printing record sheets
 */
public enum RSScale {
    HEXES("hexes", ""),
    INCHES("inches", "\""),
    CENTIMETERS("centimeters", " cm");

    public final String fullName;
    public final String abbreviation;

    RSScale(String fullName, String abbreviation) {
        this.fullName = fullName;
        this.abbreviation = abbreviation;
    }

    /**
     * Used for display name when space is limited. If the full name is longer than six characters
     * the abbreviation is used.
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
