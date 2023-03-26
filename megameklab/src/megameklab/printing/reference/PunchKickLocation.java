/*
 * MegaMekLab - Copyright (C) 2023 - The MegaMek Team
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
import megameklab.printing.PrintEntity;

/**
 * Combined punch and kick location table for used with condensed reference tables.
 */
public class PunchKickLocation extends ReferenceTable {

    public PunchKickLocation(PrintEntity sheet) {
        super(sheet, 0.08, 0.18, 0.32, 0.47, 0.61, 0.76, 0.9);
        setHeaders(bundle.getString("dieRoll1d6"),
                bundle.getString("leftSide"),
                bundle.getString("punchFrontRear"),
                bundle.getString("rightSide"),
                bundle.getString("leftSide"),
                bundle.getString("kickFrontRear"),
                bundle.getString("rightSide")
        );
        if (sheet.getEntity() instanceof TripodMech) {
            addTripodRows();
        } else if (sheet.getEntity() instanceof QuadMech) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("1", "LT", "LA", "RT", "LL", "RL", "RL");
        addRow("2", "LT", "LT", "RT", "LL", "RL", "RL");
        addRow("3", "CT", "CT", "CT", "LL", "RL", "RL");
        addRow("4", "LA", "RT", "RA", "LL", "LL", "RL");
        addRow("5", "LA", "RA", "RA", "LL", "LL", "RL");
        addRow("6", "HD", "HD", "HD", "LL", "LL", "RL");
    }

    private void addQuadRows() {
        addRow("1", "LT", "LFL/LRL", "RT", "LFL", "RFL/RRL", "RFL");
        addRow("2", "LT", "LT", "RT", "LFL", "RFL/RRL", "RFL");
        addRow("3", "CT", "CT", "CT", "LFL", "RFL/RRL", "RFL");
        addRow("4", "LFL", "RT", "RFL", "LRL", "LFL/LRL", "RRL");
        addRow("5", "LRL", "RFL/RRL", "RRL", "LRL", "LFL/LRL", "RRL");
        addRow("6", "HD", "HD", "HD", "LRL", "LFL/LRL", "RRL");
    }

    private void addTripodRows() {
        addRow("1", "LT", "LA", "RT", "LL", "LL", "LL");
        addRow("2", "LT", "LT", "RT", "LL", "LL", "CL");
        addRow("3", "CT", "CT", "CT", "LL", "CL", "CL");
        addRow("4", "LA", "RT", "RA", "CL", "CL", "RL");
        addRow("5", "LA", "RA", "RA", "CL", "RL", "RL");
        addRow("6", "HD", "HD", "HD", "RL", "RL", "RL");
    }

}
