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

    int nRows();

    String getQuantityField(int row);

    String getNameField(int row);

    String getLocationField(int row);

    String getHeatField(int row);

    String getDamageField(int row);

    String getMinField(int row);

    String getShortField(int row);

    String getMediumField(int row);

    String getLongField(int row);

    String getExtremeField(int row);

    boolean indentMultiline();
}
