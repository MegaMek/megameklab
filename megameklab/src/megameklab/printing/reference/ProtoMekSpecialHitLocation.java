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
 * Table for proto damage due to area effect, falling, or collisions.
 */
public class ProtoMekSpecialHitLocation extends ReferenceTable {

    public ProtoMekSpecialHitLocation(PrintRecordSheet sheet) {
        super(sheet, 0.2, 0.7);
        addRows();
    }

    private void addRows() {
        setHeaders(bundle.getString("2d6Result"), bundle.getString("hitLocation"));
        addRow("2", bundle.getString("mainGun"));
        addRow("3", bundle.getString("legs"));
        addRow("4", bundle.getString("legs"));
        addRow("5", bundle.getString("rightArm"));
        addRow("6", bundle.getString("torso"));
        addRow("7", bundle.getString("torso"));
        addRow("8", bundle.getString("torso"));
        addRow("9", bundle.getString("leftArm"));
        addRow("10", bundle.getString("legs"));
        addRow("11", bundle.getString("legs"));
        addRow("12", bundle.getString("head"));
    }
}
