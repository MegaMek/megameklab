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
 * Table for proto damage due to area effect, falling, or collisions.
 */
public class ProtomekSpecialHitLocation extends ReferenceTable {

    public ProtomekSpecialHitLocation(PrintRecordSheet sheet) {
        super(sheet, 0.2, 0.7);
        addRows();
    }

    private void addRows() {
        addRow(bundle.getString("2d6Result"), bundle.getString("hitLocation"));
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
