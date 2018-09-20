/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
package megameklab.com.ui.tabs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import megamek.common.EntityFluff;
import megamek.common.EntityFluff.System;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.ITab;

/**
 * Panel for editing unit fluff
 * 
 * @author Neoancient
 *
 */
public class FluffTab extends ITab {

    private static final long serialVersionUID = 5203904989540243537L;

    private final JTextArea txtCapabilities = new JTextArea(4, 40);
    private final JTextArea txtOverview = new JTextArea(4, 40);
    private final JTextArea txtDeployment = new JTextArea(4, 40);
    private final JTextArea txtHistory = new JTextArea(4, 40);
    
    private final JTextField txtManufacturer = new JTextField(12);
    private final JTextField txtPrimaryFactory = new JTextField(12);
    private final Map<System, JTextField> txtCompManufacturers = new EnumMap<>(System.class);
    private final Map<System, JTextField> txtCompModels = new EnumMap<>(System.class);
    
    private final JTextArea txtNotes = new JTextArea(4, 40);
    
    public FluffTab(EntitySource esource) {
        super(esource);
        initUi();
    }
    
    // For convenience
    private EntityFluff getFluff() {
        return eSource.getEntity().getFluff();
    }
    
    private void initUi() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs", new EncodeControl()); //$NON-NLS-1$
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel panLeft = new JPanel();
        JPanel panRight = new JPanel();
        add(panLeft);
        add(panRight);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        panLeft.setLayout(new GridBagLayout());
        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtCapabilities")), gbc);
        gbc.gridy++;
        txtCapabilities.setLineWrap(true);
        txtCapabilities.setWrapStyleWord(true);
        txtCapabilities.setBorder(border);
        txtCapabilities.setText(getFluff().getCapabilities());
        panLeft.add(txtCapabilities, gbc);
        gbc.gridy++;
        
        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtOverview")), gbc);
        gbc.gridy++;
        txtOverview.setLineWrap(true);
        txtOverview.setWrapStyleWord(true);
        txtOverview.setText(getFluff().getOverview());
        txtOverview.setBorder(border);
        panLeft.add(txtOverview, gbc);
        gbc.gridy++;

        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtDeployment")), gbc);
        gbc.gridy++;
        txtDeployment.setLineWrap(true);
        txtDeployment.setWrapStyleWord(true);
        txtDeployment.setBorder(border);
        txtDeployment.setText(getFluff().getDeployment());
        panLeft.add(txtDeployment, gbc);
        gbc.gridy++;

        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtHistory")), gbc);
        gbc.gridy++;
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);
        txtHistory.setBorder(border);
        txtHistory.setText(getFluff().getHistory());
        panLeft.add(txtHistory, gbc);
        gbc.gridy++;
        
        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtNotes")), gbc);
        gbc.gridy++;
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setBorder(border);
        txtNotes.setText(getFluff().getNotes());
        gbc.weighty = 1.0;
        panLeft.add(txtNotes, gbc);
        
        panRight.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.txtManufacturer")), gbc);
        txtManufacturer.setText(getFluff().getManufacturer());
        gbc.gridx = 1;
        panRight.add(txtManufacturer, gbc);
        gbc.gridy++;

        gbc.gridx = 0;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.txtPrimaryFactory")), gbc);
        txtPrimaryFactory.setText(getFluff().getPrimaryFactory());
        gbc.gridx = 1;
        panRight.add(txtPrimaryFactory, gbc);
        gbc.gridy++;
        
        gbc.gridx = 0;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.System")), gbc);
        gbc.gridx = 1;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.Manufacturer")), gbc);
        gbc.gridx = 2;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.Model")), gbc);
        gbc.gridy++;
        for (EntityFluff.System comp : EntityFluff.System.values()) {
            gbc.gridx = 0;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.System." + comp.toString())), gbc);
            gbc.gridx = 1;
            JTextField txt = new JTextField(12);
            txt.setText(getFluff().getSystemManufacturer(comp));
            panRight.add(txt, gbc);
            txtCompManufacturers.put(comp, txt);
            gbc.gridx = 2;
            txt = new JTextField(12);
            txt.setText(getFluff().getSystemModel(comp));
            panRight.add(txt, gbc);
            txtCompModels.put(comp, txt);
            gbc.gridy++;
        }
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        panRight.add(new JPanel(), gbc);
        
    }
}
