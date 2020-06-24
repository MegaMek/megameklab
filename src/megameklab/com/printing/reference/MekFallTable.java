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

import megameklab.com.printing.PrintMech;

/**
 * Table showing the direction of a fall and side taking damage
 */
public class MekFallTable extends ReferenceTable {

    public MekFallTable(PrintMech sheet) {
        super(sheet, "FACING AFTER FALL", 0.1, 0.45, 0.8);
        setHeaders("Die Roll\n(1D6)", "New Facing", "Hit Location");
        addRow("1", "Same Direction", "Front");
        addRow("2", "1 Hexside Right", "Right Side");
        addRow("3", "2 Hexsides Right", "Right Side");
        addRow("4", "Opposite Direction", "Rear");
        addRow("5", "2 Hexsides Left", "Left Side");
        addRow("6", "1 Hexside Left", "Left Side");
    }
}
