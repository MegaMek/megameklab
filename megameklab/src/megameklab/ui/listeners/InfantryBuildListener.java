/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.listeners;

import megamek.common.units.EntityMovementMode;

/**
 * Listener for views used by conventional infantry.
 *
 * @author Neoancient
 */
public interface InfantryBuildListener extends BuildListener {
    void numSecondaryChanged(int count);

    void numFieldGunsChanged(int count);

    void antiMekChanged(boolean antiMek);

    /**
     * @param motiveType The selected motive type
     * @param alt        If motiveType is VTOL or INF_UMU, alt is true for microlite and motorized scuba respectively,
     *                   false for MicroCopter and foot scuba. It has no meaning for other motive types.
     */
    void motiveTypeChanged(EntityMovementMode motiveType, boolean alt);

    void platoonSizeChanged(int numSquads, int squadSize);

    void specializationsChanged();
}
