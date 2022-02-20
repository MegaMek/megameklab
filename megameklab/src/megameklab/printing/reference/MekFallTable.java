/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.printing.reference;

import megameklab.printing.PrintMech;

/**
 * Table showing the direction of a fall and side taking damage
 */
public class MekFallTable extends ReferenceTable {

    public MekFallTable(PrintMech sheet) {
        super(sheet, 0.1, 0.45, 0.8);
        setHeaders(bundle.getString("dieRoll1d6"), bundle.getString("newFacing"),
                bundle.getString("hitLocation"));
        addRow("1", bundle.getString("sameDirection"), bundle.getString("front"));
        addRow("2", bundle.getString("1Right"), bundle.getString("rightSide"));
        addRow("3", bundle.getString("2Right"), bundle.getString("rightSide"));
        addRow("4", bundle.getString("opposite"), bundle.getString("rear"));
        addRow("5", bundle.getString("2Left"), bundle.getString("leftSide"));
        addRow("6", bundle.getString("1Left"), bundle.getString("leftSide"));
    }
}
