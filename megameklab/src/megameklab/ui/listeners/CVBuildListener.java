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

import megamek.common.equipment.Engine;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.verifier.BayData;
import megameklab.ui.combatVehicle.CVChassisView;

/**
 * Listener for views used by combat vehicles.
 *
 * @author Neoancient
 */
public interface CVBuildListener extends BuildListener {
    /**
     * Notify of a change in the vehicle tonnage
     *
     * @param tonnage The construction weight of the vehicle in tons
     */
    void tonnageChanged(double tonnage);

    /**
     * Notify of a change in omni status
     *
     * @param omni Whether the vehicle is an OmniVehicle
     */
    void omniChanged(boolean omni);

    /**
     * Notify of a change in superheavy status
     *
     * @param superheavy Whether the vehicle is in the superheavy weight range.
     */
    void superheavyChanged(boolean superheavy);

    /**
     * Notify of a change in trailer status
     *
     * @param trailer Whether the vehicle is constructed as a trailer
     */
    void trailerChanged(boolean trailer);

    /**
     * Notify of a change in whether a trailer has control systems.
     *
     * @param controlSystems Whether the trailer has control systems.
     */
    void controlSystemsChanged(boolean controlSystems);

    /**
     * Notify of a change in motive type. May require instantiation of a new {@link Entity Entity}.
     *
     * @param motive The new motive type
     */
    void motiveChanged(EntityMovementMode motive);

    /**
     * Notify of a change in the type of engine
     *
     * @param engine The new engine type
     */
    void engineChanged(Engine engine);

    /**
     * Notify of a change in turret configuration.
     *
     * @param turretConfig One of {@link CVChassisView#TURRET_NONE TURRET_NONE}, *
     *                     {@link CVChassisView#TURRET_SINGLE TURRET_SINGLE},
     *                     {@link CVChassisView#TURRET_DUAL TURRET_DUAL}, or *
     *                     {@link CVChassisView#TURRET_CHIN TURRET_CHIN}
     */
    void turretChanged(int turretConfig);

    /**
     * Notify of a change in the base weight of one or more turrets. This is used for OmniVehicles, which have to set
     * the limit of pod space in the turret(s) as part of the base chassis design.
     *
     * @param turret1 The weight of the turret, or the rear turret in dual-turret vehicles
     * @param turret2 The weight of the front turret in dual-turret vehicles
     */
    void turretBaseWtChanged(double turret1, double turret2);

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

    /**
     * Notify of a command to remove all pod-mounted equipment from an OmniVehicle and reset it to the base chassis.
     */
    void resetChassis();

    /**
     * Notify of a change in the number of extra combat seats.
     *
     * @param seats The number of extra seats
     */
    void extraSeatsChanged(int seats);
}
