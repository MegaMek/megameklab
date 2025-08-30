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

/**
 * Provides fields for the AR10 missile data table.
 */
public class AR10InventoryEntry implements InventoryEntry {
    private static final String[] names = { "Killer Whale", "White Shark", "Barracuda" };
    private static final String[] weight = { "50", "40", "30" };
    private static final String[] heat = { "20", "15", "10" };
    private static final String[] av = { "4", "3", "2" };

    @Override
    public int nRows() {
        return 3;
    }

    @Override
    public String getUniqueId() {
        return "";
    }

    @Override
    public String getQuantityField(int row) {
        return "";
    }

    @Override
    public boolean isDamaged() {
        return false;
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
    public String getModField(int row, boolean baseOnly) {
        // Todo: Find out if AR10s have to-hit mods
        return "";
    }

    @Override
    public boolean indentMultiline() {
        return false;
    }

    @Override
    public boolean hasQuirks() {
        return false;
    }

    @Override
    public String getQuirksField() {
        return "";
    }
}
