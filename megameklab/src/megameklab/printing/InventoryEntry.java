/*
 * MegaMekLab - unit design companion of MegaMek
 * Copyright (C) 2020 The MegaMek Team
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
package megameklab.printing;

import megamek.common.EquipmentType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.util.AeroAVModCalculator;

/**
 * Interface for classes that process entries in the weapons and inventory table
 */
public interface InventoryEntry {
    String DASH = "\u2014";
    String DAGGER = "\u2020";
    String DOUBLE_DAGGER = "\u2021";

    /**
     * @return The number of rows this entry takes on the table. This does not consider text that is
     *         wrapped due to insufficient field width.
     */
    int nRows();

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The number of identical equipment pieces represented by this entry
     */
    String getQuantityField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The name of the equipment
     */
    default String getNameField(int row) {
        return getNameField(row, null, 0, 0);
    }

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The name of the equipment
     */
    String getNameField(int row, PrintRecordSheet sheet, double width, float fontSize);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The location/firing arc
     */
    String getLocationField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The amount of heat generated
     */
    String getHeatField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The damage caused by the weapon, as well as its type code
     */
    String getDamageField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The upper limit of the minimum range
     */
    String getMinField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The upper limit of the short range (ground units) or SRV (aero units)
     */
    String getShortField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The upper limit of the medium range (ground units) or MRV (aero units)
     */
    String getMediumField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The upper limit of the long range (ground units) or LRV (aero units)
     */
    String getLongField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     * @return    The ERV (aero units)
     */
    String getExtremeField(int row);

    /**
     * @return Whether lines after the first line should be indented
     */
    boolean indentMultiline();

    /**
     * @return Whether the entry has any quirks that need to be printed
     */
    boolean hasQuirks();

    /**
     * @return The quirk text to print, if any
     */
    String getQuirksField();
    
    /**
     * Computes any modification to the aerospace AV for linked Artemis, Apollo, or PPC capacitor
     *
     * @param weapon The weapon mount
     * @return       The AV modification, if any
     */
    default int aeroAVMod(Mounted<?> weapon) {
        if (weapon.getType() instanceof WeaponType && weapon.getLinkedBy() != null) {
            return aeroAVMod((WeaponType) weapon.getType(),
                    weapon.getLinkedBy().getType(), weapon.getEntity().usesWeaponBays());
        }
        return 0;
    }

    /**
     * Computes any modification to the aerospace AV for linked Artemis, Apollo, or PPC capacitor
     *
     * @param weapon   The type of weapon
     * @param linkedBy The type of equipment the weapon is linked by
     * @param bay      Whether the weapon is part of a weapon bay
     * @return         The AV modification, if any
     */
    default int aeroAVMod(WeaponType weapon, EquipmentType linkedBy, boolean bay) {
        return AeroAVModCalculator.calculateBonus(weapon, linkedBy, bay);
    }
}
