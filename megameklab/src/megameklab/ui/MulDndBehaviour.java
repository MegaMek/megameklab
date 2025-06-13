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
package megameklab.ui;

import java.util.ResourceBundle;

public enum MulDndBehaviour {
    PRINT,
    EXPORT,
    LOAD_FORCE;

    private final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");

    /** @return A display name for this MMLStartUp taken from the resources (possibly localised). */
    public String getDisplayName() {
        return resources.getString("MulDndBehaviour." + name());
    }
}
