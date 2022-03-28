/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.common.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Base class that has some common layout methods for main ui component views.
 * 
 * @author Neoancient
 */
public abstract class BuildView extends JPanel {
    protected final Dimension labelSize = new Dimension(110, 25);
    protected final Dimension labelSizeLg = new Dimension(180, 25);
    protected final Dimension controlSize = new Dimension(180, 25);
    protected final Dimension spinnerSize = new Dimension(55, 25);
    protected final Dimension spinnerSizeLg = new Dimension(70, 25);
    protected final Dimension editorSize = new Dimension(40, 25);
    protected final Dimension editorSizeLg = new Dimension(55, 25);

    public JLabel createLabel(final ResourceBundle resources, final String name, final String text,
                              final Dimension maxSize) {
        return createLabel(resources, name, text, null, maxSize);
    }

    public JLabel createLabel(final ResourceBundle resources, final String name, final String text,
                              final @Nullable String toolTipText, final Dimension maxSize) {
        return createLabel(name, resources.getString(text),
                (toolTipText == null) ? null : resources.getString(toolTipText), maxSize);
    }

    public JLabel createLabel(final String name, final String text, final Dimension maxSize) {
        return createLabel(name, text, null, maxSize);
    }

    public JLabel createLabel(final String name, final String text,
                              final @Nullable String toolTipText, final Dimension maxSize) {
        final JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setToolTipText(toolTipText);
        if (!name.isBlank()) {
            label.setName(name);
        }
        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(final JComponent box, final Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }
}
