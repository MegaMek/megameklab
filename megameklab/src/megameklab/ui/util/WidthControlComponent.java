/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.util;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is an empty JPanel with a preferred width that corresponds to a JTextField with a fixed column number. It should
 * therefore keep a certain width but adapt to different UI scaling and fonts. It can be used to give the right column
 * on input views like CIPlatoonView or others the same width.
 *
 * @see #TEXT_FIELD_COLUMNS
 */
public class WidthControlComponent extends JPanel {

    public static final int TEXT_FIELD_COLUMNS = 18;
    private static final JTextField WIDTH_PROTOTYPE = new JTextField("None", TEXT_FIELD_COLUMNS);

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getPreferredWidth(), 0);
    }

    public int getPreferredWidth() {
        return WIDTH_PROTOTYPE.getPreferredSize().width;
    }
}
