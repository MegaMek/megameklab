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
package megameklab.com.printing;

/**
 * Interface for classes that process entries in the weapons and inventory table
 */
public interface InventoryEntry {
    String DASH = "\u2014";

    /**
     * @return The number of rows this entry takes on the table. This does not consider text that is
     *         wrapped due to insufficient field width.
     */
    int nRows();

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The number of identical equipment pieces represented by this entry
     */
    String getQuantityField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The name of the equipment
     */
    String getNameField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The location/firing arc
     */
    String getLocationField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The amount of heat generated
     */
    String getHeatField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The damage caused by the weapon, as well as its type code
     */
    String getDamageField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The upper limit of the minimum range
     */
    String getMinField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The upper limit of the short range (ground units) or SRV (aero units)
     */
    String getShortField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The upper limit of the medium range (ground units) or MRV (aero units)
     */
    String getMediumField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The upper limit of the long range (ground units) or LRV (aero units)
     */
    String getLongField(int row);

    /**
     * @param row The row index within the entry. Should be < nRows()
     * @return    The ERV (aero units)
     */
    String getExtremeField(int row);

    /**
     * @return Whether lines after the first line should be indented
     */
    boolean indentMultiline();
}
