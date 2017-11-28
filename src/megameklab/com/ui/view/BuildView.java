/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

package megameklab.com.ui.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Base class that has some common layout methods for main ui component views.
 * 
 * @author Neoancient
 *
 */
public abstract class BuildView extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8823653930022761924L;
    
    final protected Dimension labelSize = new Dimension(110, 25);
    final protected Dimension controlSize = new Dimension(180, 25);
    final protected Dimension spinnerSize = new Dimension(55, 25);
    final protected Dimension spinnerSizeLg = new Dimension(70, 25);
    final protected Dimension editorSize = new Dimension(40, 25);
    final protected Dimension editorSizeLg = new Dimension(55, 25);

    public JLabel createLabel(String text, Dimension maxSize) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

}
