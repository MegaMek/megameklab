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

import megamek.common.SimpleTechLevel;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.enums.FuelType;
import megamek.common.units.UnitRole;

/**
 * Combined listener interface for the various subviews of the structure tab. Includes callbacks used by multiple unit
 * types. Listeners for specific unit types extend this one.
 *
 * @author Neoancient
 */
public interface BuildListener {

    void refreshSummary();

    void chassisChanged(String chassis);

    default void clanNameChanged(String clanName) {
    }

    void modelChanged(String model);

    void yearChanged(int year);

    void updateTechLevel();

    void sourceChanged(String source);

    void mulIdChanged(int mulId);

    void techBaseChanged(boolean clan, boolean mixed);

    void techLevelChanged(SimpleTechLevel techLevel);

    void roleChanged(UnitRole role);

    /**
     * Notifies of a change of the manually entered BV. When manualBV is 0 or less, the unit should be set to not use a
     * manual BV value and the manual BV set to -1.
     *
     * @param manualBV The entered manual BV; may be invalid (0 or less)
     */
    void manualBVChanged(int manualBV);

    void walkChanged(int walkMP);

    void jumpChanged(int jumpMP, EquipmentType jumpJet);

    void jumpTypeChanged(EquipmentType jumpJet);

    /*
     * Methods used by multiple unit types but not all are given default
     * implementations that
     * ignore them.
     */

    /**
     * Notifies of a change in heat sink type or count for aerospace units
     *
     * @param index Either {@link megameklab.ui.generalUnit.HeatSinkView#TYPE_SINGLE} or
     *              {@link megameklab.ui.generalUnit.HeatSinkView#TYPE_DOUBLE_AERO}
     * @param count The number of heat sinks
     */
    default void heatSinksChanged(int index, int count) {
    }

    /**
     * Notifies of a change in heat sink type or count for meks
     *
     * @param hsType The type of heat sink
     * @param count  The total number of heat sinks
     */
    default void heatSinksChanged(EquipmentType hsType, int count) {
    }

    /**
     * Notifies of a change in the distribution between single and double heat sinks on a unit with prototype double
     * heat sinks.
     *
     * @param prototype The number of prototype double heat sinks
     */
    default void redistributePrototypeHS(int prototype) {
    }

    /**
     * Notifies of a change in the number of heat sinks that are part of the base chassis of an omni unit
     *
     * @param count The number of fixed heat sinks
     */
    default void heatSinkBaseCountChanged(int count) {
    }

    /**
     * Notifies of a change in the presence of a RISC Heat Sink Override Kit
     *
     * @param hasKit True if the unit now has the kit
     */
    default void riscHeatSinkOverrideKitChanged(boolean hasKit) {
    }

    // For aerospace units and support vehicles
    default void fuelTonnageChanged(double tonnage) {
    }

    /**
     * Notify of a change in the amount of fuel. For aerospace this is number of fuel points. For ground support
     * vehicles this is the range in km.
     *
     * @param capacity The number of fuel points or range as appropriate to the unit type
     */
    default void fuelCapacityChanged(int capacity) {
    }

    /**
     * Notify of a change in ICE engine fuel type. This is only used for vehicles.
     *
     * @param fuelType The engine fuel type
     */
    default void fuelTypeChanged(FuelType fuelType) {
    }
}
