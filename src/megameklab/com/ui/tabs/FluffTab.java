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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import megamek.common.Entity;
import megamek.common.EntityFluff;
import megamek.common.EntityFluff.System;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;

/**
 * Panel for editing unit fluff
 * 
 * @author Neoancient
 *
 */
public class FluffTab extends ITab implements FocusListener {

    private static final long serialVersionUID = 5203904989540243537L;

    private final JTextArea txtCapabilities = new JTextArea(4, 40);
    private final JTextArea txtOverview = new JTextArea(4, 40);
    private final JTextArea txtDeployment = new JTextArea(4, 40);
    private final JTextArea txtHistory = new JTextArea(4, 40);
    
    private final JTextField txtManufacturer = new JTextField(12);
    private final JTextField txtPrimaryFactory = new JTextField(12);
    private final JTextField txtUse = new JTextField(12);
    private final JTextField txtLength = new JTextField(8);
    private final JTextField txtWidth = new JTextField(8);
    private final JTextField txtHeight = new JTextField(8);
    private final Map<System, JTextField> txtCompManufacturers = new EnumMap<>(System.class);
    private final Map<System, JTextField> txtCompModels = new EnumMap<>(System.class);
    
    private final JTextArea txtNotes = new JTextArea(4, 40);
    
    private static final String TAG_MANUFACTURER = "manufacturer"; //$NON-NLS-1
    private static final String TAG_MODEL = "model"; //$NON-NLS-1
    
    private RefreshListener refresh;
    
    public FluffTab(EntitySource esource) {
        super(esource);
        initUi();
    }
    
    // For convenience
    private EntityFluff getFluff() {
        return eSource.getEntity().getFluff();
    }
    
    public void setRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void initUi() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs", new EncodeControl()); //$NON-NLS-1$
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel panLeft = new JPanel();
        JPanel panRight = new JPanel();
        add(panLeft);
        if (!eSource.getEntity().hasETypeFlag(Entity.ETYPE_INFANTRY)
                || eSource.getEntity().hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            add(panRight);
        }
        
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
        txtCapabilities.addFocusListener(this);
        gbc.gridy++;
        
        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtOverview")), gbc);
        gbc.gridy++;
        txtOverview.setLineWrap(true);
        txtOverview.setWrapStyleWord(true);
        txtOverview.setText(getFluff().getOverview());
        txtOverview.setBorder(border);
        panLeft.add(txtOverview, gbc);
        txtOverview.addFocusListener(this);
        gbc.gridy++;

        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtDeployment")), gbc);
        gbc.gridy++;
        txtDeployment.setLineWrap(true);
        txtDeployment.setWrapStyleWord(true);
        txtDeployment.setBorder(border);
        txtDeployment.setText(getFluff().getDeployment());
        panLeft.add(txtDeployment, gbc);
        txtDeployment.addFocusListener(this);
        gbc.gridy++;

        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtHistory")), gbc);
        gbc.gridy++;
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);
        txtHistory.setBorder(border);
        txtHistory.setText(getFluff().getHistory());
        panLeft.add(txtHistory, gbc);
        txtHistory.addFocusListener(this);
        gbc.gridy++;
        
        panLeft.add(new JLabel(resourceMap.getString("FluffTab.txtNotes")), gbc);
        gbc.gridy++;
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setBorder(border);
        txtNotes.setText(getFluff().getNotes());
        gbc.weighty = 1.0;
        panLeft.add(txtNotes, gbc);
        txtNotes.addFocusListener(this);
        
        panRight.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.txtManufacturer")), gbc);
        txtManufacturer.setText(getFluff().getManufacturer());
        gbc.gridx = 1;
        panRight.add(txtManufacturer, gbc);
        txtManufacturer.addFocusListener(this);
        gbc.gridy++;

        gbc.gridx = 0;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.txtPrimaryFactory")), gbc);
        txtPrimaryFactory.setText(getFluff().getPrimaryFactory());
        gbc.gridx = 1;
        panRight.add(txtPrimaryFactory, gbc);
        txtPrimaryFactory.addFocusListener(this);
        gbc.gridy++;
        
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                || eSource.getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            gbc.gridx = 0;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.txtUse")), gbc);
            gbc.gridx = 1;
            txtUse.setText(getFluff().getUse());
            panRight.add(txtUse, gbc);
            txtUse.addFocusListener(this);
            gbc.gridy++;
            
            gbc.gridx = 0;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.txtLength")), gbc);
            gbc.gridx = 1;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.txtWidth")), gbc);
            gbc.gridx = 2;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.txtHeight")), gbc);
            gbc.gridy++;
            
            gbc.gridx = 0;
            txtLength.setText(getFluff().getLength());
            panRight.add(txtLength, gbc);
            txtLength.addFocusListener(this);

            gbc.gridx = 1;
            txtWidth.setText(getFluff().getWidth());
            panRight.add(txtWidth, gbc);
            txtWidth.addFocusListener(this);

            gbc.gridx = 2;
            txtHeight.setText(getFluff().getHeight());
            panRight.add(txtHeight, gbc);
            txtHeight.addFocusListener(this);
            gbc.gridy++;
        }
        gbc.gridx = 0;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.System")), gbc);
        gbc.gridx = 1;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.Manufacturer")), gbc);
        gbc.gridx = 2;
        panRight.add(new JLabel(resourceMap.getString("FluffTab.Model")), gbc);
        gbc.gridy++;
        for (EntityFluff.System system : EntityFluff.System.values()) {
            if ((system == EntityFluff.System.JUMPJET)
                    && eSource.getEntity().hasETypeFlag(Entity.ETYPE_AERO)) {
                continue;
            }
            gbc.gridx = 0;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.System." + system.toString())), gbc);
            gbc.gridx = 1;
            JTextField txt = new JTextField(12);
            txt.setText(getFluff().getSystemManufacturer(system));
            panRight.add(txt, gbc);
            txtCompManufacturers.put(system, txt);
            txt.setName(system.name() + ":" + TAG_MANUFACTURER);
            txt.addFocusListener(this);
            gbc.gridx = 2;
            txt = new JTextField(12);
            txt.setText(getFluff().getSystemModel(system));
            panRight.add(txt, gbc);
            txtCompModels.put(system, txt);
            txt.setName(system.name() + ":" + TAG_MODEL);
            txt.addFocusListener(this);
            gbc.gridy++;
        }
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        panRight.add(new JPanel(), gbc);
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        // do nothing
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtCapabilities) {
            getFluff().setCapabilities(txtCapabilities.getText());
        } else if (e.getSource() == txtOverview) {
            getFluff().setOverview(txtOverview.getText());
        } else if (e.getSource() == txtDeployment) {
            getFluff().setDeployment(txtDeployment.getText());
        } else if (e.getSource() == txtHistory) {
            getFluff().setHistory(txtHistory.getText());
        } else if (e.getSource() == txtManufacturer) {
            getFluff().setManufacturer(txtManufacturer.getText());
        } else if (e.getSource() == txtPrimaryFactory) {
            getFluff().setPrimaryFactory(txtPrimaryFactory.getText());
        } else if (e.getSource() == txtUse) {
            getFluff().setUse(txtUse.getText());
        } else if (e.getSource() == txtLength) {
            getFluff().setLength(txtLength.getText());
        } else if (e.getSource() == txtWidth) {
            getFluff().setWidth(txtWidth.getText());
        } else if (e.getSource() == txtHeight) {
            getFluff().setHeight(txtHeight.getText());
        } else if (e.getSource() == txtNotes) {
            getFluff().setNotes(txtNotes.getText());
        } else if (e.getSource() instanceof JTextField) {
            String[] fields = ((JTextField) e.getSource()).getName().split(":");
            EntityFluff.System system = EntityFluff.System.parse(fields[0]);
            if (null != system) {
                if (TAG_MANUFACTURER.equals(fields[1])) {
                    getFluff().setSystemManufacturer(system, ((JTextField) e.getSource()).getText());
                } else if (TAG_MODEL.equals(fields[1])) {
                    getFluff().setSystemModel(system, ((JTextField) e.getSource()).getText());
                }
            }
        }
        if (null != refresh) {
            refresh.refreshPreview();
        }
    }
}
