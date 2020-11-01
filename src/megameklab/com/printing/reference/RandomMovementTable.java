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
 * Aerospace random movement due to heat or failed control roll by 5+
 */
public class RandomMovementTable extends ReferenceTable {

    private final boolean breakLines;

    /**
     * @param sheet      The record sheet
     * @param breakLines If true, long lines will be spit at the comma. This permits
     *                   the same table to be used in multiple places that have different
     *                   width requirements.
     */
    public RandomMovementTable(PrintAero sheet, boolean breakLines) {
        super(sheet, 0.1, 0.35);
        this.breakLines = breakLines;
        setHeaders(bundle.getString("d6result"), bundle.getString("effect"));
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        addRows();
    }

    private String handleLine(String str) {
        if (breakLines) {
            return str.replace(", ", "\n");
        } else {
            return str;
        }
    }

    private void addRows() {
        addRow("1", handleLine(bundle.getString("fwd1l2")));
        addRow("2", handleLine(bundle.getString("fwd1l1")));
        addRow("3-4", handleLine(bundle.getString("fwd1")));
        addRow("5", handleLine(bundle.getString("fwd1r1")));
        addRow("6", handleLine(bundle.getString("fwd1r2")));
    }
}
