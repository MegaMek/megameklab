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
package megameklab.com.printing.reference;

import megamek.common.QuadMech;
import megameklab.com.printing.PrintMech;

/**
 * Hit location table for kick attacks
 */
public class KickLocation extends ReferenceTable {

    public KickLocation(PrintMech sheet) {
        super(sheet, "KICK LOCATION TABLE", 0.1, 0.35, 0.6, 0.85);
        setHeaders("Die Roll", "LS", "F/R", "RS");
        if (sheet.getEntity() instanceof QuadMech) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addQuadRows() {
        addRow("1-3", "LL", "RL", "RL");
        addRow("4-6", "LL", "LL", "RL");
    }

    private void addBipedRows() {
        addRow("1-3", "LFL", "RFL/RRL", "RFL");
        addRow("4-6", "LRL", "LFL/LRL", "RRL");
    }
}
