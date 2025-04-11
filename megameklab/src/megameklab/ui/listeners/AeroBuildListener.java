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
 */
package megameklab.ui.listeners;

import megamek.common.Engine;
import megamek.common.bays.BayData;

/**
 * Listener for views used by aerospace units.
 *
 * @author Neoancient
 */
public interface AeroBuildListener extends BuildListener {

    void tonnageChanged(double tonnage);

    void omniChanged(boolean omni);

    void vstolChanged(boolean vstol);

    void fighterTypeChanged(int type);

    void engineChanged(Engine engine);

    void cockpitChanged(int cockpitType);

    void resetChassis();

    /**
     * Notify of a change in the size of any infantry compartment
     *
     * @param fixed The weight in tons of the infantry compartment
     * @param pod   The weight in tons of any pod-mounted infantry compartment
     */
    void troopSpaceChanged(double fixed, double pod);

    /**
     * Notify of a change in the size of a cargo bay
     *
     * @param bayType The type of bay
     * @param fixed   The size of a fixed bay
     * @param pod     The size of a pod-mounted bay
     */
    void cargoSpaceChanged(BayData bayType, double fixed, double pod);

}
