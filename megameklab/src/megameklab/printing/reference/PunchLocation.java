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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing.reference;

import megamek.common.units.QuadMek;
import megameklab.printing.PrintMek;

/**
 * Hit location table for punch attacks
 */
public class PunchLocation extends ReferenceTable {

    public PunchLocation(PrintMek sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll1d6"), bundle.getString("leftSide"),
              bundle.getString("frontRear"), bundle.getString("rightSide"));
        if (sheet.getEntity() instanceof QuadMek) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        addRow("1", "LT", "LA", "RT");
        addRow("2", "LT", "LT", "RT");
        addRow("3", "CT", "CT", "CT");
        addRow("4", "LA", "RT", "RA");
        addRow("5", "LA", "RA", "RA");
        addRow("6", "HD", "HD", "HD");
    }

    private void addQuadRows() {
        addRow("1", "LT", "LFL/LRL", "RT");
        addRow("2", "LT", "LT", "RT");
        addRow("3", "CT", "CT", "CT");
        addRow("4", "LFL", "RT", "RFL");
        addRow("5", "LRL", "RFL/RRL", "RRL");
        addRow("6", "HD", "HD", "HD");
    }
}
