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

import java.awt.Color;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.views.CriticalView;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.Mech;

public class StructureTab extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6756011847500605874L;

    Entity unit;
    public StructureTab(Entity unit) {
        this.unit = unit;
        refresh();
        
    }
    
    
    public JPanel enginePanel() {
        JPanel masterPanel = new JPanel();
        String[] engineTypes = {"I.C.E.","Standard","XL","Light","XXL","Compact"};
        JComboBox engineType = new JComboBox(engineTypes);
        
        TreeSet<String> engineRatings = new TreeSet<String>();
        
        for (int pos = 0; pos < Engine.ENGINE_RATINGS.length; pos++ )
            engineRatings.add(Float.toString(Engine.ENGINE_RATINGS[pos]));
        
        JComboBox engineRating = new JComboBox(engineRatings.toArray());
        
        masterPanel.add(new JLabel("Engine Type:",JLabel.TRAILING));
        masterPanel.add(engineType);
        masterPanel.add(new JLabel("Engine Rating:",JLabel.TRAILING));
        masterPanel.add(engineRating);
        masterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        engineType.setSelectedIndex(unit.getEngine().getEngineType());
        
        float weight = Engine.ENGINE_RATINGS[(int)Math.ceil(unit.getEngine().getRating()/5)];
        for (int pos = 0; pos < engineRating.getItemCount(); pos++ ) {
            
            if ( Float.parseFloat(engineRating.getItemAt(pos).toString()) == weight) {
                engineRating.setSelectedIndex(pos);
                break;
            }
        }
        
        return masterPanel;
    }
    

    public void refresh() {
        
        this.removeAll();
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(enginePanel());
        this.add(new CriticalView((Mech)unit));
    }
}