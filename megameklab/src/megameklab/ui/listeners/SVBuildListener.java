/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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

import megamek.common.enums.TechRating;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.supportVehicle.SVChassisView;

/**
 * Listener for views used by support vehicle construction.
 */
public interface SVBuildListener extends BuildListener {
    // Turret configuration constants
    int TURRET_NONE = 0;
    int TURRET_SINGLE = 1;
    int TURRET_DUAL = 2;
    int TURRET_CHIN = 3;

    // Fire control constants
    int FIRE_CONTROL_NONE = 0;
    int FIRE_CONTROL_BASIC = 1;
    int FIRE_CONTROL_ADVANCED = 2;

    /**
     * Notify of a change in the unit tonnage
     *
     * @param tonnage The new tonnage
     */
    void tonnageChanged(double tonnage);

    /**
     * Notify of a change in the support vehicle type
     *
     * @param type The new vehicle type
     */
    void typeChanged(TestSupportVehicle.SVType type);

    /**
     * Notify of a change in the structural tech rating
     *
     * @param techRating The new tech rating. This should be one of the constants {@link TechRating#A } through
     *                   {@link TechRating#F}
     */
    void structuralTechRatingChanged(TechRating techRating);

    /**
     * Notify of a change in the type of engine
     *
     * @param engine The new engine
     */
    void engineChanged(Engine engine);

    /**
     * Notify of a change in the engine tech rating
     *
     * @param techRating The new tech rating. This should be one of the constants {@link TechRating#A} through
     *                   {@link TechRating#A}
     */
    void engineTechRatingChanged(TechRating techRating);

    /**
     * Notify of the addition or removal of a chassis modification
     *
     * @param eq        The modification to add or remove
     * @param installed Whether the chassis mod is to be installed or removed
     */
    void setChassisMod(EquipmentType eq, boolean installed);

    /**
     * Notify that the turret configuration has changed
     *
     * @param config The turret configuration constant from {@link SVChassisView SVChassisView}
     */
    void turretChanged(int config);

    /**
     * Notify that sponson turrets have been added or removed
     *
     * @param installed Whether the vehicle has a pair of sponson turrets.
     */
    void sponsonTurretChanged(boolean installed);

    /**
     * Notify that a pintle turret has been added or removed
     *
     * @param installed Whether there is a pintle turret on the left side
     * @param loc       The location to add or remove the pintle mount
     */
    void pintleTurretChanged(boolean installed, int loc);

    /**
     * Notify of a change in the base chassis turret weight for omni vehicles
     *
     * @param turret1 The weight of the first turret or chin turret
     * @param turret2 The weight of the second turret, if any
     */
    void turretBaseWtChanged(double turret1, double turret2);

    /**
     * Notify of a change the type of fire control
     *
     * @param index One of the FIRE_CONTROL_* constants
     */
    void fireConChanged(int index);

    /**
     * Notify of a change in the base chassis fire control weight for omni vehicles
     *
     * @param weight The new weight
     */
    void fireConWtChanged(double weight);

    /**
     * Notify of a change in the number of crew seats
     *
     * @param standard    The number of fixed standard combat crew seats
     * @param standardPod The number of pod-mounted standard combat crew seats
     * @param pillion     The number of fixed pillion seats
     * @param pillionPod  The number of pod-mounted pillion seats
     * @param ejection    The number of fixed ejection seats
     * @param ejectionPod The number of pod-mounted ejection seats
     */
    void setSeating(int standard, int standardPod, int pillion, int pillionPod, int ejection, int ejectionPod);

    /**
     * Notify of a change in the size of crew quarters
     *
     * @param firstClass     The crew capacity of fixed first class/officer quarters
     * @param firstClassPod  The crew capacity of pod-mounted first class/officer quarters
     * @param secondClass    The crew capacity of fixed second class quarters
     * @param secondClassPod The crew capacity of pod-mounted second class quarters
     * @param crew           The capacity of fixed standard crew quarters
     * @param crewPod        The capacity of pod-mounted standard crew quarters
     * @param steerage       The capacity of fixed steerage class quarters
     * @param steeragePod    The capacity of pod-mounted steerage class quarters
     */
    void setQuarters(int firstClass, int firstClassPod, int secondClass, int secondClassPod, int crew, int crewPod,
          int steerage, int steeragePod);

    /**
     * Removes all pod-mounted equipment from an omni.
     */
    void resetChassis();

    /**
     * Notify of a change in the base chassis sponson/pintle weight for OmniVehicles.
     *
     * @param turretWeight The weight of the sponson or pintle turrets.
     */
    void sponsonPintleBaseWtChanged(double turretWeight);
}
