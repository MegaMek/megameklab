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
 * Hit locations table for Meks
 */
public class MekHitLocation extends ReferenceTable {

    public MekHitLocation(PrintMech sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll2d6"), bundle.getString("leftSide"),
                bundle.getString("frontRear"), bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof TripodMech) {
            addTripodRows();
        } else if (sheet.getEntity() instanceof QuadMech) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("2*", "LT(C)", "CT(C)", "RT(C)");
        addRow("3", "LL", "RA", "RL");
        addRow("4", "LA", "RA", "RA");
        addRow("5", "LA", "RL", "RA");
        addRow("6", "LL", "RT", "RL");
        addRow("7", "LT", "CT", "RT");
        addRow("8", "CT", "LT", "CT");
        addRow("9", "RT", "LL", "LT");
        addRow("10", "RA", "LA", "LA");
        addRow("11", "RL", "LA", "LL");
        addRow("12", "HD", "HD", "HD");
        addNote(bundle.getString("tacNote"));
    }

    private void addQuadRows() {
        addRow("2*", "LT(C)", "CT(C)", "RT(C)");
        addRow("3", "LRL", "RFL", "RRL");
        addRow("4", "LFL", "RFL", "RFL");
        addRow("5", "LFL", "RRL", "RFL");
        addRow("6", "LRL", "RT", "RRL");
        addRow("7", "LT", "CT", "RT");
        addRow("8", "CT", "LT", "CT");
        addRow("9", "RT", "LRL", "LT");
        addRow("10", "RFL", "LFL", "LFL");
        addRow("11", "RRL", "LFL", "LRL");
        addRow("12", "HD", "HD", "HD");
        addNote(bundle.getString("tacNote"));
    }

    private void addTripodRows() {
        addRow("2*", "LT(C)", "CT(C)", "RT(C)");
        addRow("3", "Leg (+1)†", "RA", "Leg (-1)†");
        addRow("4", "LA", "RA", "RA");
        addRow("5", "LA", "Leg†", "RA");
        addRow("6", "Leg (+1)†", "RT", "Leg (-1)†");
        addRow("7", "LT", "CT", "RT");
        addRow("8", "CT", "LT", "CT");
        addRow("9", "RT", "Leg†", "LT");
        addRow("10", "RA", "LA", "LA");
        addRow("11", "Leg (+1)†", "LA", "Leg (-1)†");
        addRow("12", "HD", "HD", "HD");
        addNote(bundle.getString("tacNote"));
        addNote(bundle.getString("tripodLegNote"));
    }
}
