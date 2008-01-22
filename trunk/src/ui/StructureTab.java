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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ui.util.SpringLayoutHelper;
import ui.views.CriticalView;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.Mech;

public class StructureTab extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6756011847500605874L;

    Mech unit;
    String[] engineTypes = { "I.C.E.", "Standard", "XL", "Light", "XXL", "Compact" };
    JComboBox engineType = new JComboBox(engineTypes);
    JComboBox engineRating;
    JComboBox gyroType = new JComboBox(Mech.GYRO_SHORT_STRING);
    JComboBox weightClass;
    JComboBox cockpitType = new JComboBox(Mech.COCKPIT_SHORT_STRING);
    String[] heatSinkTypes = { "Single", "Double", "Compact", "Laser" };
    JComboBox heatSinkType = new JComboBox(heatSinkTypes);
    JComboBox heatSinkNumber;
    
    public StructureTab(Entity unit) {
        this.unit = (Mech)unit;
        refresh();

    }

    public JPanel enginePanel() {
        JPanel masterPanel = new JPanel();
        
        masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.Y_AXIS));
        masterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel enginePanel = new JPanel();
        JPanel gyroPanel = new JPanel();
        JPanel weightPanel = new JPanel();
        JPanel heatSinkPanel = new JPanel();
        
        Vector<String> engineRatings = new Vector<String>();
        String lastRating = "";

        for (int pos = 1; pos < Engine.ENGINE_RATINGS.length; pos++) {
            String newRating = Float.toString(Engine.ENGINE_RATINGS[pos]);

            if (!lastRating.equals(newRating)) {
                engineRatings.add(newRating);
                lastRating = newRating;
            }
        }

        engineRating = new JComboBox(engineRatings.toArray());

        enginePanel.add(new JLabel("Engine Type:", JLabel.TRAILING));
        enginePanel.add(engineType);
        enginePanel.add(new JLabel("Engine Rating:", JLabel.TRAILING));
        enginePanel.add(engineRating);
        
        gyroPanel.add(new JLabel("Gyro:",JLabel.TRAILING));
        gyroPanel.add(gyroType);
        
        gyroPanel.add(new JLabel("Cockpit:",JLabel.TRAILING));
        gyroPanel.add(cockpitType);
        
        Vector<String>weightClasses = new Vector<String>(1,1);
        
        for ( int weight = 10; weight < 101; weight += 5)
            weightClasses.add(Integer.toString(weight));

        weightClass = new JComboBox(weightClasses.toArray());
        
        weightPanel.add(new JLabel("Weight:",JLabel.TRAILING));
        weightPanel.add(weightClass);

        Vector<String>heatSinks = new Vector<String>(1,1);
        for ( int count = 0; count < 30; count++ )
            heatSinks.add(Integer.toString(count));
        
        heatSinkNumber = new JComboBox(heatSinks.toArray());
        
        heatSinkPanel.add(new JLabel("Heat Sinks:",JLabel.TRAILING));
        heatSinkPanel.add(heatSinkType);
        heatSinkPanel.add(new JLabel("Number:",JLabel.TRAILING));
        heatSinkPanel.add(heatSinkNumber);
        
        gyroType.setSelectedIndex(unit.getGyroType());
        engineType.setSelectedIndex(unit.getEngine().getEngineType());
        weightClass.setSelectedIndex((int)(unit.getWeight()/5)-2);
        cockpitType.setSelectedIndex(unit.getCockpitType());
        heatSinkNumber.setSelectedIndex(unit.getNumberOfSinks());
        heatSinkType.setSelectedIndex(0);
        
        float weight = Engine.ENGINE_RATINGS[(int) Math.ceil(unit.getEngine().getRating() / 5)];
        for (int pos = 0; pos < engineRating.getItemCount(); pos++) {

            if (Float.parseFloat(engineRating.getItemAt(pos).toString()) == weight) {
                engineRating.setSelectedIndex(pos);
                break;
            }
        }

        masterPanel.add(enginePanel);
        masterPanel.add(gyroPanel);
        masterPanel.add(heatSinkPanel);
        masterPanel.add(weightPanel);
        
        
        return masterPanel;
    }

    public void refresh() {

        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(enginePanel());
        this.add(new CriticalView((Mech) unit, false));
    }
}