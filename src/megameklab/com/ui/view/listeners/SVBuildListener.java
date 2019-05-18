/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.view.listeners;

import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.verifier.TestSupportVehicle;

/**
 * Listener for views used by support vehicle construction.
 */
public interface SVBuildListener extends BuildListener {
    /* Turret configuration constants */
    int TURRET_NONE   = 0;
    int TURRET_SINGLE = 1;
    int TURRET_DUAL   = 2;
    int TURRET_CHIN   = 3;

    /* Fire control constants */
    int FIRECON_NONE     = 0;
    int FIRECON_BASIC    = 1;
    int FIRECON_ADVANCED = 2;

    /**
     * Notify of a change in the unit tonnage
     * @param tonnage The new tonnage
     */
    void tonnageChanged(double tonnage);

    /**
     * Notify of a change in the support vehicle type
     * @param type The new vehicle type
     */
    void typeChanged(TestSupportVehicle.SVType type);

    /**
     * Notify of a change in the structural tech rating
     * @param techRating The new tech rating. This should be one of the constants {@link megamek.common.ITechnology#RATING_A ITechnology.RATING_A}
     *                   through {@link megamek.common.ITechnology#RATING_A ITechnology.RATING_F}
     */
    void structuralTechRatingChanged(int techRating);

    /**
     * Notify of a change in the type of engine
     * @param engine The new engine
     */
    void engineChanged(Engine engine);
    /**
     * Notify of a change in the engine tech rating
     * @param techRating The new tech rating. This should be one of the constants {@link megamek.common.ITechnology#RATING_A ITechnology.RATING_A}
     *                   through {@link megamek.common.ITechnology#RATING_A ITechnology.RATING_F}
     */
    void engineTechRatingChanged(int techRating);

    /**
     * Notify of the addition or removal of a chassis modification
     * @param eq        The modification to add or remove
     * @param installed Whether the chassis mod is to be installed or removed
     */
    void setChassisMod(EquipmentType eq, boolean installed);

    /**
     * Notify that the turret configuration has changed
     *
     * @param config The turret configuration constant from {@link megameklab.com.ui.view.SVChassisView SVChassisView}
     */
    void turretChanged(int config);

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
     * @param index One of the FIRECON_* constants
     *
     */
    void fireConChanged(int index);

    /**
     * Notify of a change in the base chassis fire control weight for omni vehicles
     *
     * @param weight The new weight
     */
    void fireConWtChanged(double weight);
}
