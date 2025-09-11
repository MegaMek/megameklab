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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import megamek.client.ui.util.DisplayTextField;

/**
 * This is a JLabel that is right-aligned and uses a preferred height equal to that of a JTextField; it can be used in
 * BuildViews as a standard label class for input fields. It will align properly with JTextFields or even multi-line
 * TextPanes even if the alignment of the component in a GridBagLayout is set to NORTH.
 */
public class StandardBuildLabel extends JLabel {

    private final DisplayTextField heightPrototype = new DisplayTextField("unimportant", 1);

    public StandardBuildLabel(String text) {
        super(text, SwingConstants.RIGHT);
    }

    public StandardBuildLabel() {
        this("");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, heightPrototype.getPreferredSize().height);
    }
}
