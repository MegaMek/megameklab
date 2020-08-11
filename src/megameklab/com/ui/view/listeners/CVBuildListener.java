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
import megameklab.com.ui.view.CVChassisView;

/**
 * Listener for views used by combat vehicles.
 * 
 * @author Neoancient
 *
 */
public interface CVBuildListener extends BuildListener {
    /**
     * Notify of a change in the vehicle tonnage
     * @param tonnage The construction weight of the vehicle in tons
     */
    void tonnageChanged(double tonnage);

    /**
     * Notify of a change in omni status
     * @param omni Whether the vehicle is an OmniVehicle
     */
    void omniChanged(boolean omni);

    /**
     * Notify of a change in superheavy status
     * @param superheavy Whether the vehicle is in the superheavy weight range.
     */
    void superheavyChanged(boolean superheavy);

    /**
     * Notify of a change in trailer status
     * @param trailer Whether the vehicle is constructed as a trailer
     */
    void trailerChanged(boolean trailer);

    /**
     * Notify of a change in whether a trailer has control systems.
     * @param controlSystems Whether the trailer has control systems.
     */
    void controlSystemsChanged(boolean controlSystems);

    /**
     * Notify of a change in motive type. May require instantiation of a new {@link megamek.common.Entity Entity}.
     * @param motive The new motive type
     */
    void motiveChanged(EntityMovementMode motive);

    /**
     * Notify of a change in the type of engine
     * @param engine The new engine type
     */
    void engineChanged(Engine engine);

    /**
     * Notify of a change in turret configuration.
     * @param turretConfig One of {@link CVChassisView#TURRET_NONE TURRET_NONE},
     *      * {@link CVChassisView#TURRET_SINGLE TURRET_SINGLE}, {@link CVChassisView#TURRET_DUAL TURRET_DUAL}, or
     *      * {@link CVChassisView#TURRET_CHIN TURRET_CHIN}
     */
    void turretChanged(int turretConfig);

    /**
     * Notify of a change in the base weight of one or more turrets. This is used for omnivehicles,
     * which have to set the limit of pod space in the turret(s) as part of the base chassis design.
     * @param turret1 The weight of the turret, or the rear turret in dual-turret vehicles
     * @param turret2 The weight of the front turret in dual-turret vehicles
     */
    void turretBaseWtChanged(double turret1, double turret2);

    /**
     * Notify of a change in the size of any infantry compartment
     * @param fixed The weight in tons of the infantry compartment
     * @param pod   The weight in tons of any pod-mounted infantry compartment
     */
    void troopSpaceChanged(double fixed, double pod);

    /**
     * Notify of a change in the size of a cargo bay
     * @param bayType The type of bay
     * @param fixed   The size of a fixed bay
     * @param pod     The size of a pod-mounted bay
     */
    void cargoSpaceChanged(BayData bayType, double fixed, double pod);

    /**
     * Notify of a command to remove all pod-mounted equipment from an omnivehicle and reset
     * it to the base chassis.
     */
    void resetChassis();

    /**
     * Notify of a change in the number of extra combat seats.
     * @param seats The number of extra seats
     */
    void extraSeatsChanged(int seats);
}
