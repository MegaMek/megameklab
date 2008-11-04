/*
 * MegaMekLab - Copyright (C) 2008 
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

import javax.swing.JPanel;

import megamek.common.Mech;

public class IView extends JPanel{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6741722012756653309L;
    public Mech unit;
    public IView(Mech unit) {
        this.unit  = unit;
    }
    
    public void updateMech(Mech unit) {
        this.unit = unit;
    }

}