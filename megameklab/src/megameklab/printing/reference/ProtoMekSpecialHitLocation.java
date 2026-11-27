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

import megameklab.printing.PrintRecordSheet;

/**
 * Table for proto damage due to area effect, falling, or collisions.
 */
public class ProtoMekSpecialHitLocation extends ReferenceTable {

    public ProtoMekSpecialHitLocation(PrintRecordSheet sheet) {
        super(sheet, 0.2, 0.7);
        addRows();
    }

    private void addRows() {
        setHeaders(bundle.getString("2d6Result"), bundle.getString("hitLocation"));
        for (int roll = 2; roll <= 12; roll++) {
            addRow(String.valueOf(roll), bundle.getString(locationKey(roll)));
        }
    }

    static String locationKey(int roll) {
        return switch (roll) {
            case 2 -> "mainGun";
            case 3, 4, 10, 11 -> "legs";
            case 5 -> "rightArm";
            case 6, 7, 8 -> "torso";
            case 9 -> "leftArm";
            case 12 -> "head";
            default -> throw new IllegalArgumentException("Invalid 2D6 roll: " + roll);
        };
    }
}
