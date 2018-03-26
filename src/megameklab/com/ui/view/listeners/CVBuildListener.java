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

import megamek.common.Engine;
import megamek.common.EntityMovementMode;
import megamek.common.verifier.BayData;

/**
 * Listener for views used by combat vehicles.
 * 
 * @author Neoancient
 *
 */
public interface CVBuildListener extends BuildListener {

    void tonnageChanged(double tonnage);
    void omniChanged(boolean omni);
    void superheavyChanged(boolean superheavy);
    void motiveChanged(EntityMovementMode motive);
    void engineChanged(Engine engine);
    void turretChanged(int turretConfig);
    void turretBaseWtChanged(double turret1, double turret2);
    void troopSpaceChanged(double fixed, double pod);
    void cargoSpaceChanged(BayData bayType, double fixed, double pod);
    void resetChassis();

}
