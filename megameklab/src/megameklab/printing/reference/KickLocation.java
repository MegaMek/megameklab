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

import megamek.common.QuadMek;
import megamek.common.TripodMek;
import megameklab.printing.PrintMek;

/**
 * The hit location table for kick attacks
 */
public class KickLocation extends ReferenceTable {

    public KickLocation(PrintMek sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll1d6"),
              bundle.getString("leftSide"),
              bundle.getString("frontRear"),
              bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof QuadMek) {
            addQuadRows();
        } else if (sheet.getEntity() instanceof TripodMek) {
            addTripodRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("1-3", "LL", "RL", "RL");
        addRow("4-6", "LL", "LL", "RL");
    }

    private void addQuadRows() {
        addRow("1-3", "LFL", "RFL/RRL", "RFL");
        addRow("4-6", "LRL", "LFL/LRL", "RRL");
    }

    private void addTripodRows() {
        addRow("1", "LL", "LL", "LL");
        addRow("2", "LL", "LL", "CL");
        addRow("3", "LL", "CL", "CL");
        addRow("4", "CL", "CL", "RL");
        addRow("5", "CL", "RL", "RL");
        addRow("6", "RL", "RL", "RL");
    }
}
