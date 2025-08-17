/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import java.util.ResourceBundle;

import megamek.codeUtilities.StringUtility;
import megamek.common.units.Entity;

/**
 * This class represents the different types of arrangement of the chassis names of those Meks that have a Clan and an
 * IS name such as the Mad Cat a.k.a. Timber Wolf. This is used to determine how to print those names on record sheets
 * (but not elsewhere as we might not want this to be dependent on how the record sheets might be at any moment).
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
     * Parses the given String, returning the ClanIsMekNameArrangement fitting the String like the valueOf() method
     * does, but returns CLAN_IS when it can't be parsed (instead of null).
     *
     * @param arrangementName A string giving one of the ClanIsMekNameArrangement values
     *
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
