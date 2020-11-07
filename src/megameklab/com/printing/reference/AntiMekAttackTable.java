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
 * Hit modifiers for conventional infantry vs. mek
 */
public class AntiMekAttackTable extends ReferenceTable {

    public AntiMekAttackTable(PrintRecordSheet sheet) {
        super(sheet, 0.15, 0.5, 0.8);
        setHeaders(bundle.getString("activeTroopers"), bundle.getString("leg"),
                bundle.getString("swarm"));
        addRows();
    }

    private void addRows() {
        addRow("22+", "0", "+2");
        addRow("16-21", "+2", "+5");
        addRow("10-15", "+5", bundle.getString("notPossible"));
        addRow("5-9", "+7", bundle.getString("notPossible"));
        addRow("1-4", bundle.getString("notPossible"), bundle.getString("notPossible"));
    }
}
