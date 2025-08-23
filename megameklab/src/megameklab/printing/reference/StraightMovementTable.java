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
package megameklab.printing.reference;

import megamek.common.units.ConvFighter;
import megamek.common.units.Dropship;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.SmallCraft;
import megameklab.printing.PrintAero;

/**
 * For aerodyne units
 */
public class StraightMovementTable extends ReferenceTable {

    public StraightMovementTable(PrintAero sheet) {
        super(sheet, 0.1, 0.45, 0.8);
        setHeaders(bundle.getString("velocity"), bundle.getString("lowAltitude"),
              bundle.getString("ground"));
        if ((sheet.getEntity() instanceof Dropship)
              || sheet.getEntity().getMovementMode().equals(EntityMovementMode.AIRSHIP)) {
            addDropshipRows();
        } else if (sheet.getEntity() instanceof SmallCraft) {
            addSmallCraftRows();
        } else if (sheet.getEntity().isSupportVehicle()) {
            addFixedWingRows();
        } else if (sheet.getEntity() instanceof ConvFighter) {
            addCFRows();
        } else {
            addASFRows();
        }
    }

    private void addDropshipRows() {
        addRow("1", "1", "8");
        addRow("2", "1", "16");
        addRow("3", "1", "24");
        addRow("4", "2", "32");
        addRow("5", "2", "40");
        addRow("6", "2", "48");
        addRow("7", "3", "56");
        addRow("8", "3", "64");
        addRow("9", "3", "72");
        addRow("10", "4", "80");
        addRow("11", "4", "88");
        addRow("12", "4", "96");
        addRow("13-15", "5", "-");
        addRow("16+", "6", "-");
    }

    private void addSmallCraftRows() {
        addRow("1", "1", "8");
        addRow("2", "1", "14");
        addRow("3", "1", "20");
        addRow("4", "1", "26");
        addRow("5", "1", "32");
        addRow("6", "1", "38");
        addRow("7", "2", "44");
        addRow("8", "2", "50");
        addRow("9", "2", "56");
        addRow("10", "3", "62");
        addRow("11", "3", "68");
        addRow("12", "3", "74");
        addRow("13-15", "4", "-");
        addRow("16+", "5", "-");
    }

    private void addCFRows() {
        addRow("1", "1", "8");
        addRow("2", "1", "12");
        addRow("3", "1", "16");
        addRow("4", "1", "20");
        addRow("5", "1", "24");
        addRow("6", "1", "28");
        addRow("7", "1", "32");
        addRow("8", "1", "36");
        addRow("9", "1", "40");
        addRow("10", "2", "44");
        addRow("11", "2", "48");
        addRow("12", "2", "52");
        addRow("13-15", "3", "-");
        addRow("16+", "4", "-");
    }

    private void addFixedWingRows() {
        addRow("1", "2", "8");
        addRow("2", "2", "14");
        addRow("3", "2", "20");
        addRow("4", "2", "26");
        addRow("5", "2", "32");
        addRow("6", "2", "38");
        addRow("7", "2", "44");
        addRow("8", "2", "50");
        addRow("9", "2", "56");
        addRow("10", "4", "62");
        addRow("11", "4", "68");
        addRow("12", "4", "74");
        addRow("13-15", "6", "-");
        addRow("16+", "8", "-");
    }

    private void addASFRows() {
        addRow("1", "1", "8");
        addRow("2", "1", "12");
        addRow("3", "1", "16");
        addRow("4", "1", "20");
        addRow("5", "1", "24");
        addRow("6", "1", "28");
        addRow("7", "2", "32");
        addRow("8", "2", "36");
        addRow("9", "2", "40");
        addRow("10", "3", "44");
        addRow("11", "3", "48");
        addRow("12", "3", "52");
        addRow("13-15", "4", "-");
        addRow("16+", "5", "-");
    }
}
