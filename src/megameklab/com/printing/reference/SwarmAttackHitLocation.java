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

import megameklab.com.printing.PrintRecordSheet;

/**
 * Hit location for infantry swarm attacks
 */
public class SwarmAttackHitLocation extends ReferenceTable {

    public SwarmAttackHitLocation(PrintRecordSheet sheet) {
        super(sheet, 0.1, 0.45, 0.8);
        setHeaders(bundle.getString("2d6Roll"), bundle.getString("biped"), bundle.getString("quad"));
        addRows();
    }

    private void addRows() {
        addRow("2", "H", "H");
        addRow("3", "CT(R)", "RT");
        addRow("4", "RT(R)", "CT(R)");
        addRow("5", "RT", "RT(R)");
        addRow("6", "RA", "RT");
        addRow("7", "CT", "CT");
        addRow("8", "LA", "LT");
        addRow("9", "LT", "LT(R)");
        addRow("10", "LT(R)", "CT(R)");
        addRow("11", "CT(R)", "LT");
        addRow("12", "H", "H");
    }
}
