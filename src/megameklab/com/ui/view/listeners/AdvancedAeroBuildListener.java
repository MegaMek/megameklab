/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.ui.view.listeners;

import java.util.List;

/**
 *
 * Listener for views used by jumpships, warships, and space stations.
 * 
 * @author Neoancient
 *
 */
public interface AdvancedAeroBuildListener extends AeroVesselBuildListener {

    void tonnageChanged(double tonnage);
    void lfBatteryChanged(boolean battery);
    void modularChanged(boolean modular);
    void sailChanged(boolean sail);
    void baseTypeChanged(int type);
    void rangeChanged(int range);
    void siChanged(int si);
    void gravDecksChanged(List<Integer> deckSizes);
    
}
