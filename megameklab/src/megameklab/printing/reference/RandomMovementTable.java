/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 */
package megameklab.printing.reference;

import megameklab.printing.PrintAero;
import org.apache.batik.util.SVGConstants;

/**
 * Aerospace random movement due to heat or failed control roll by 5+
 */
public class RandomMovementTable extends ReferenceTable {

    private final boolean breakLines;

    /**
     * @param sheet      The record sheet
     * @param breakLines If true, long lines will be spit at the comma. This permits the same table to be used in
     *                   multiple places that have different width requirements.
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
