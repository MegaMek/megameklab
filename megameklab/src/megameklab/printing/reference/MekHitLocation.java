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

import megamek.common.QuadMek;
import megamek.common.TripodMek;
import megameklab.printing.PrintMek;

/**
 * Hit locations table for Meks
 */
public class MekHitLocation extends ReferenceTable {

    public MekHitLocation(PrintMek sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll2d6"), bundle.getString("leftSide"),
                bundle.getString("frontRear"), bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof TripodMek) {
            addRows(TRIPOD_LOCATIONS);
            addNote(bundle.getString("tripodLegNote"));
        } else if (sheet.getEntity() instanceof QuadMek) {
            addRows(QUAD_LOCATIONS);
        } else {
            addRows(BIPED_LOCATIONS);
        }
    }

    static final String[][] BIPED_LOCATIONS = {
            { "2*", "LT(C)", "CT(C)", "RT(C)" },
            { "3", "LL", "RA", "RL" },
            { "4", "LA", "RA", "RA" },
            { "5", "LA", "RL", "RA" },
            { "6", "LL", "RT", "RL" },
            { "7", "LT", "CT", "RT" },
            { "8", "CT", "LT", "CT" },
            { "9", "RT", "LL", "LT" },
            { "10", "RA", "LA", "LA" },
            { "11", "RL", "LA", "LL" },
            { "12", "HD", "HD", "HD" }
    };

    static final String[][] QUAD_LOCATIONS = {
            { "2*", "LT(C)", "CT(C)", "RT(C)" },
            { "3", "LRL", "RFL", "RRL" },
            { "4", "LFL", "RFL", "RFL" },
            { "5", "LFL", "RRL", "RFL" },
            { "6", "LRL", "RT", "RRL" },
            { "7", "LT", "CT", "RT" },
            { "8", "CT", "LT", "CT" },
            { "9", "RT", "LRL", "LT" },
            { "10", "RFL", "LFL", "LFL" },
            { "11", "RRL", "LFL", "LRL" },
            { "12", "HD", "HD", "HD" }
    };

    static final String[][] TRIPOD_LOCATIONS = {
            { "2*", "LT(C)", "CT(C)", "RT(C)" },
            { "3", "Leg (+1)\u2020", "RA", "Leg (-1)\u2020" },
            { "4", "LA", "RA", "RA" },
            { "5", "LA", "Leg\u2020", "RA" },
            { "6", "Leg (+1)\u2020", "RT", "Leg (-1)\u2020" },
            { "7", "LT", "CT", "RT" },
            { "8", "CT", "LT", "CT" },
            { "9", "RT", "Leg\u2020", "LT" },
            { "10", "RA", "LA", "LA" },
            { "11", "Leg (+1)\u2020", "LA", "Leg (-1)\u2020" },
            { "12", "HD", "HD", "HD" }
    };

    private void addRows(String[][] locations) {
        for (String[] row : locations) {
            addRow(row);
        }
        addNote(bundle.getString("tacNote"));
    }
}
