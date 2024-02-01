/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import megamek.codeUtilities.StringUtility;
import megamek.common.Entity;

import java.util.ResourceBundle;

/**
 * This class represents the different types of arrangement of the chassis names of those Meks that have a Clan
 * and an IS name such as the Mad Cat a.k.a. Timber Wolf. This is used to determine how to print those names
 * on record sheets (but not elsewhere as we might not want this to be dependent on how the record sheets
 * might be at any moment).
 *
 * @author Simon (Juliez)
 */
public enum MekChassisArrangement {

    CLAN_IS,
    IS_CLAN,
    CLAN_ONLY,
    IS_ONLY;

    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");

    /** @return A display name for this ClanIsMekNameArrangement taken from the resources (possibly localised). */
    public String getDisplayName() {
        return resources.getString("ClanISMekNameOrdering." + name());
    }

    /**
     * Parses the given String, returning the ClanIsMekNameArrangement fitting the String like the valueOf() method does,
     * but returns CLAN_IS when it can't be parsed (instead of null).
     *
     * @param arrangementName A string giving one of the ClanIsMekNameArrangement values
     * @return the MekChassisArrangement parsed from the string or CLAN_IS. Never returns null.
     */
    public static MekChassisArrangement parse(String arrangementName) {
        try {
            return valueOf(arrangementName);
        } catch (IllegalArgumentException e) {
            return CLAN_IS;
        }
    }

    public String printChassis(Entity entity) {
        if (StringUtility.isNullOrBlank(entity.getClanChassisName())) {
            return entity.getChassis();
        } else if (this == IS_ONLY) {
            return entity.getChassis();
        } else if (this == CLAN_ONLY) {
            return entity.getClanChassisName();
        } else if (this == CLAN_IS) {
            return entity.getClanChassisName() + " (" + entity.getChassis() + ")";
        } else {
            return entity.getChassis() + " (" + entity.getClanChassisName() + ")";
        }
    }
}