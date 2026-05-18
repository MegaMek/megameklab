/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import megamek.common.annotations.Nullable;
import megamek.common.equipment.Mounted;

/**
 * Crit views implementing this interface can help the user by darkening those locations that cannot receive a
 * mouse-dragged equipment. This is used by transfer handlers.
 */
public interface CriticalSlotsView {

    /**
     * Darkens all other crit blocks (those that are not for the given location).
     */
    void markUnavailableLocations(int location);

    /**
     * Darkens all crit blocks that are unavailable to the given equipment, e.g. all but Torsos for CASE. It's a
     * design decision if this should check only if the location is suitable at all, or also equipment space
     * requirements or even equipment counts (e.g. only one sword per location). This can be handled differently
     * vor different unit types.
     */
    void markUnavailableLocations(@Nullable Mounted<?> equipment);

    /**
     * Resets all crit blocks to not darkened.
     */
    void unMarkAllLocations();
}
