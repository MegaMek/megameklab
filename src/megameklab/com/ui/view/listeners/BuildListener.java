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

import megamek.common.EquipmentType;
import megamek.common.FuelType;
import megamek.common.SimpleTechLevel;

/**
 * Combined listener interface for the various subviews of the structure tab. Includes callbacks
 * used by multiple unit types. Listeners for specific unit types extend this one.
 * 
 * @author Neoancient
 *
 */
public interface BuildListener {

    void refreshSummary();
    void chassisChanged(String chassis);
    void modelChanged(String model);
    void yearChanged(int year);
    void updateTechLevel();
    void sourceChanged(String source);
    void techBaseChanged(boolean clan, boolean mixed);
    void techLevelChanged(SimpleTechLevel techLevel);
    void manualBVChanged(int manualBV);

    void walkChanged(int walkMP);
    void jumpChanged(int jumpMP, EquipmentType jumpJet);
    void jumpTypeChanged(EquipmentType jumpJet);
    
    /*
     * Methods used by multiple unit types but not all are given default implementations that
     * ignore them.
     */

    default void heatSinksChanged(int index, int count) {};
    default void heatSinksChanged(EquipmentType hsType, int count) {};
    default void heatSinkBaseCountChanged(int count) {};

    // For aerospace units and support vehicles
    default void fuelTonnageChanged(double tonnage) {};

    /**
     * Notify of a change in the amount of fuel. For aerospace this is number of fuel points.
     * For ground support vehicles this is the range in km.
     *
     * @param capacity The number of fuel points or range as appropriate to the unit type
     */
    default void fuelCapacityChanged(int capacity) {};

    /**
     * Notify of a change in ICE engine fuel type. This is only used for vehicles.
     *
     * @param fuelType The engine fuel type
     */
    default void fuelTypeChanged(FuelType fuelType) {}
}
