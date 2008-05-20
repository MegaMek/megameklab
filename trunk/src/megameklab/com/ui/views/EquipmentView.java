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

package megameklab.com.ui.views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import megamek.common.Mech;
import megameklab.com.ui.util.RefreshListener;

public class EquipmentView extends JPanel implements KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private Mech unit;
    private RefreshListener refresh;

    public EquipmentView (Mech unit){
        this.unit = unit;
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }


    public void refresh() {
    }
    
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}