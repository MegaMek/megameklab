/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit.summary;

import javax.swing.*;
import java.awt.*;

/**
 * This label is used by the unit summary in the structure tab to display the weight and crits. It is right-aligned
 * and uses the standard border given in {@link SummaryItem}.
 */
public class SummaryWeightLabel extends JLabel {

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