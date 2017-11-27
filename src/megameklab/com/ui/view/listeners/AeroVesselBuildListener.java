/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

/**
 * Listener for views used by small craft and large craft.
 * 
 * @author Neoancient
 *
 */
public interface AeroVesselBuildListener extends BuildListener {

    void officersChanged(int nOfficers);
    void baseCrewChanged(int nCrew);
    void gunnersChanged(int nGunners);
    void passengersChanged(int nPassengers);
    void marinesChanged(int nMarines);
    void baMarinesChanged(int nBAMarines);
    void quartersChanged(int officer, int standard, int secondclass, int steerage);
    void autoAssignQuarters();
    void escapeChanged(int lifeBoats, int escapePods);
}
