/*
 * MegaMekLab - Copyright (C) 2010
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import megamek.common.Entity;

public class ImagePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1885941921775619243L;
    private Image background;

    public ImagePanel(Entity unit, String path) {
        background = ImageHelper.getFluffImage(unit, path);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            // g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

    }
}