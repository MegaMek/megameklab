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

    // For units that allocate armor by tonnage
    default void armorTypeChanged(int at, int armorTechLevel) {};
    default void armorTonnageChanged(double tonnage) {};
    default void maximizeArmor() {};
    default void useRemainingTonnageArmor() {};

    // For units that allocate armor by point
    default void armorValueChanged(int points) {};
    default void armorTypeChanged(EquipmentType armor) {};

    // For units that allocate armor to location
    default void armorPointsChanged(int location, int front, int rear) {};
    default void patchworkChanged(int location, EquipmentType armor) {};
    default void autoAllocateArmor() {};

    // For aerospace units and support vehicles
    default void fuelTonnageChanged(double tonnage) {};
}
