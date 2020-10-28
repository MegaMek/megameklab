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

import megameklab.com.printing.PrintAero;
import org.apache.batik.util.SVGConstants;

/**
 * Thrust point cost to change facing on a space or high-altitude map
 */
public class ChangingFacingCostTable extends ReferenceTable {

    public ChangingFacingCostTable(PrintAero sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("velocity"), bundle.getString("thrustPoints"),
                bundle.getString("velocity"), bundle.getString("thrustPoints"));
        addRows();
    }

    private void addRows() {
        addRow("0-2", "1", "10", "5");
        addRow("3-5", "2", "11", "6");
        addRow("6-7", "3", "12", bundle.getString("plus1perPoint"));
        addRow("8-9", "4", "", "");
    }
}
