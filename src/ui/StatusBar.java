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

import javax.swing.JPanel;

import megamek.common.Mech;

public class StatusBar extends JPanel {
    
    private MainUI main;
    private Mech unit;
    
    public StatusBar(Mech unit, MainUI main){
        this.unit = unit;
        this.main = main;
    }
    
    public void refresh(){
        
    }
}