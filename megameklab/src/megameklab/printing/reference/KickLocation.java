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
import megamek.common.TripodMech;
import megameklab.printing.PrintMech;

/**
 * Hit location table for kick attacks
 */
public class KickLocation extends ReferenceTable {

    public KickLocation(PrintMech sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll1d6"), bundle.getString("leftSide"),
                bundle.getString("frontRear"), bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof QuadMech) {
            addQuadRows();
        } else if (sheet.getEntity() instanceof TripodMech) {
            addTripodRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("1-3", "LL", "RL", "RL");
        addRow("4-6", "LL", "LL", "RL");
    }

    private void addQuadRows() {
        addRow("1-3", "LFL", "RFL/RRL", "RFL");
        addRow("4-6", "LRL", "LFL/LRL", "RRL");
    }

    private void addTripodRows() {
        addRow("1", "LL", "LL", "LL");
        addRow("2", "LL", "LL", "CL");
        addRow("3", "LL", "CL", "CL");
        addRow("4", "CL", "CL", "RL");
        addRow("5", "CL", "RL", "RL");
        addRow("6", "RL", "RL", "RL");
    }
}
