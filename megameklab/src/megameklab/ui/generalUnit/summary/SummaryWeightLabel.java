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
 */
package megameklab.ui.generalUnit.summary;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This label is used by the unit summary in the structure tab to display the weight and crits. It is right-aligned and
 * uses the standard border given in {@link SummaryItem}.
 */
public class SummaryWeightLabel extends JLabel {

    /** This internal label is not shown but used to control the size of the actual label. */
    private final JLabel internalSizingLabel = new JLabel("15000 t");

    public SummaryWeightLabel(String text) {
        super(text, SwingConstants.RIGHT);
        initialize();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if (internalSizingLabel != null) {
            internalSizingLabel.setFont(font);
        }
    }

    public SummaryWeightLabel() {
        this("");
    }

    private void initialize() {
        setBorder(SummaryItem.labelBorder);
        if (internalSizingLabel != null) {
            internalSizingLabel.setBorder(SummaryItem.labelBorder);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return internalSizingLabel.getPreferredSize();
    }
}
