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

package ui.tabs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ui.util.RefreshListener;
import ui.util.SpringLayoutHelper;
import ui.views.ArmorView;

import megamek.common.EquipmentType;
import megamek.common.Mech;

public class ArmorTab extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -7235362583437251408L;

    private Mech unit;
    private ArmorView armor;
    private RefreshListener refresh = null;
    private JComboBox armorCombo = new JComboBox(EquipmentType.armorNames);
    private JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);

    private JPanel buttonPanel = new JPanel();

    public ArmorTab(Mech unit) {

        this.unit = unit;
        armor = new ArmorView(this.unit);
        this.add(ButtonPanel());
        this.add(armor);
        refresh();

        addAllListeners();

    }

    public void refresh() {
        armor.refresh();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public void actionPerformed(ActionEvent arg0) {
        removeAllListeners();
        if (arg0.getSource() instanceof JComboBox) {
            unit.setArmorType(armorCombo.getSelectedIndex());
            unit.setStructureType(structureCombo.getSelectedIndex());
            refresh.refreshStatus();
            refresh();
        }
        addAllListeners();
    }

    public JPanel ButtonPanel() {
        JPanel masterPanel = new JPanel(new SpringLayout());

        masterPanel.add(new JLabel("Armor", JLabel.TRAILING));
        masterPanel.add(armorCombo);
        masterPanel.add(new JLabel("Structure", JLabel.TRAILING));
        masterPanel.add(structureCombo);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
        
        buttonPanel.add(masterPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return buttonPanel;
    }

    private void addAllListeners() {
        armorCombo.addActionListener(this);
        structureCombo.addActionListener(this);
    }

    private void removeAllListeners() {
        armorCombo.removeActionListener(this);
        structureCombo.removeActionListener(this);
    }

}