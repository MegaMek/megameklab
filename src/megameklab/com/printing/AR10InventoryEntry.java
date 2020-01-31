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

import java.util.Collections;
import java.util.List;

/**
 * Provides fields for the AR10 missile data table.
 */
public class AR10InventoryEntry implements InventoryEntry {
    private static final String[] names = {"Killer Whale", "White Shark", "Barracuda"};
    private static final String[] weight = {"50", "40", "30"};
    private static final String[] heat = {"20", "15", "10"};
    private static final String[] av = {"4", "3", "2"};

    @Override
    public int nRows() {
        return 3;
    }

    @Override
    public String getQuantityField(int row) {
        return "";
    }

    @Override
    public String getNameField(int row) {
        return names[row];
    }

    @Override
    public String getLocationField(int row) {
        return weight[row];
    }

    @Override
    public String getHeatField(int row) {
        return heat[row];
    }

    @Override
    public String getDamageField(int row) {
        return "";
    }

    @Override
    public String getMinField(int row) {
        return "";
    }

    @Override
    public String getShortField(int row) {
        return av[row];
    }

    @Override
    public String getMediumField(int row) {
        return av[row];
    }

    @Override
    public String getLongField(int row) {
        return av[row];
    }

    @Override
    public String getExtremeField(int row) {
        return av[row];
    }

    @Override
    public boolean indentMultiline() {
        return false;
    }
}
