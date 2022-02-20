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

import megamek.common.QuadMech;
import megameklab.printing.PrintMech;

/**
 * Hit location table for punch attacks
 */
public class PunchLocation extends ReferenceTable {

    public PunchLocation(PrintMech sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll1d6"), bundle.getString("leftSide"),
                bundle.getString("frontRear"), bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof QuadMech) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("1", "LT", "LA", "RT");
        addRow("2", "LT", "LT", "RT");
        addRow("3", "CT", "CT", "CT");
        addRow("4", "LA", "RT", "RA");
        addRow("5", "LA", "RA", "RA");
        addRow("6", "HD", "HD", "HD");
    }

    private void addQuadRows() {
        addRow("1", "LT", "LFL/LRL", "RT");
        addRow("2", "LT", "LT", "RT");
        addRow("3", "CT", "CT", "CT");
        addRow("4", "LFL", "RT", "RFL");
        addRow("5", "LRL", "RFL/RRL", "RRL");
        addRow("6", "HD", "HD", "HD");
    }
}
