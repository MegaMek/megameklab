/*
 * MekWars - Copyright (C) 2008 
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

package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import megamek.common.Mech;

public class Header extends JPanel implements KeyListener{

    private Mech unit;
    private MainUI main;
    /**
     * 
     */
    private static final long serialVersionUID = -5806920722514383555L;

    
    public Header(Mech unit, MainUI main){
        this.unit = unit;
        this.main = main;
    }
    
    public void refresh(){
        
    }
    
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}