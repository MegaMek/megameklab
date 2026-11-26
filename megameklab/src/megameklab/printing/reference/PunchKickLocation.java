/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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

import java.util.ArrayList;
import java.util.List;

import megamek.common.units.Entity;
import megamek.common.units.QuadMek;
import megamek.common.units.TripodMek;
import megameklab.printing.PrintEntity;

/**
 * Combined punch and kick location table for used with condensed reference tables.
 */
public class PunchKickLocation extends ReferenceTable {

    private final Entity entity;

    public PunchKickLocation(PrintEntity sheet) {
        super(sheet, 0.08, 0.18, 0.32, 0.47, 0.61, 0.76, 0.9);
        entity = sheet.getEntity();
        setHeaders(bundle.getString("dieRoll1d6"),
              bundle.getString("leftSide"),
              bundle.getString("punchFrontRear"),
              bundle.getString("rightSide"),
              bundle.getString("leftSide"),
              bundle.getString("kickFrontRear"),
              bundle.getString("rightSide")
        );
        if (entity instanceof TripodMek) {
            addTripodRows();
        } else if (entity instanceof QuadMek) {
            addQuadRows();
        } else {
            addBipedRows();
        }
    }

    private void addBipedRows() {
        for (int roll = 1; roll <= 6; roll++) {
            addPunchKickRow(roll, "LL", (roll <= 3) ? "RL" : "LL", "RL");
        }
    }

    private void addQuadRows() {
        for (int roll = 1; roll <= 6; roll++) {
            if (roll <= 3) {
                addPunchKickRow(roll, "LFL", "RFL/RRL", "RFL");
            } else {
                addPunchKickRow(roll, "LRL", "LFL/LRL", "RRL");
            }
        }
    }

    private void addTripodRows() {
        addPunchKickRow(1, "LL", "LL", "LL");
        addPunchKickRow(2, "LL", "LL", "CL");
        addPunchKickRow(3, "LL", "CL", "CL");
        addPunchKickRow(4, "CL", "CL", "RL");
        addPunchKickRow(5, "CL", "RL", "RL");
        addPunchKickRow(6, "RL", "RL", "RL");
    }

    private void addPunchKickRow(int roll, String kickLeft, String kickFrontRear, String kickRight) {
        var row = new ArrayList<>(PunchLocation.punchRow(entity, roll));
        row.addAll(List.of(kickLeft, kickFrontRear, kickRight));
        addRow(row);
    }
}
