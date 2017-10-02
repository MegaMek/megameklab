/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.com.ui.view.listeners;

import megamek.common.EntityMovementMode;

/**
 * Listener for views used by conventional infantry.
 * 
 * @author Neoancient
 *
 */
public interface InfantryBuildListener extends BuildListener {
    void numSecondaryChanged(int count);
    void numFieldGunsChanged(int count);
    void antiMekChanged(boolean antiMek);

    /**
     * @param motiveType The selected motive type
     * @param alt        If motiveType is VTOL or INF_UMU, alt is true for microlite and motorized scuba
     *                   respectively, false for microcopter and foot scuba. It has no meaning for other
     *                   motive types.
     */
    void motiveTypeChanged(EntityMovementMode motiveType, boolean alt);
    void platoonSizeChanged(int numSquads, int squadSize);
    
    void specializationsChanged();
}
