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

import megameklab.printing.PrintRecordSheet;

/**
 * Hit modifiers for conventional infantry vs. mek
 */
public class AntiMekAttackTable extends ReferenceTable {

    public AntiMekAttackTable(PrintRecordSheet sheet) {
        super(sheet, 0.15, 0.5, 0.8);
        setHeaders(bundle.getString("activeTroopers"), bundle.getString("leg"), bundle.getString("swarm"));
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
