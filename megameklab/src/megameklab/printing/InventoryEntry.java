/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.util.AeroAVModCalculator;

/**
 * Interface for classes that process entries in the weapons and inventory table
 */
public interface InventoryEntry {
    String DASH = "\u2014";
    String MINUS = "\u2212";
    String DAGGER = "\u2020";
    String DOUBLE_DAGGER = "\u2021";

    /**
     * @return The number of rows this entry takes on the table. This does not consider text that is wrapped due to
     *       insufficient field width.
     */
    int nRows();

    String getUniqueId();

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The number of identical equipment pieces represented by this entry
     */
    String getQuantityField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The name of the equipment
     */
    String getNameField(int row);

    /**
     * @return If is damaged
     */
    boolean isDamaged();

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The location/firing arc
     */
    String getLocationField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The amount of heat generated
     */
    String getHeatField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The damage caused by the weapon, as well as its type code
     */
    String getDamageField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The upper limit of the minimum range
     */
    String getMinField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The upper limit of the short range (ground units) or SRV (aero units)
     */
    String getShortField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The upper limit of the medium range (ground units) or MRV (aero units)
     */
    String getMediumField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The upper limit of the long range (ground units) or LRV (aero units)
     */
    String getLongField(int row);

    /**
     * @param row The row index within the entry. Should be &lt; nRows()
     *
     * @return The ERV (aero units)
     */
    String getExtremeField(int row);

    /**
     * @param row The row index within the entry
     *
     * @return The to-hit modifier of the item
     */
    default String getModField(int row) {
        return getModField(row, false);
    };

    /**
     * @param row The row index within the entry
     *
     * @return The to-hit modifier of the item
     */
    String getModField(int row, boolean baseOnly);

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
     *
     * @return The AV modification, if any
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
     *
     * @return The AV modification, if any
     */
    default int aeroAVMod(WeaponType weapon, EquipmentType linkedBy, boolean bay) {
        return AeroAVModCalculator.calculateBonus(weapon, linkedBy, bay);
    }
}
