/*
 * MegaMekLab - Copyright (C) 2017
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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
package megameklab.com.ui.view;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.ITechnology;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.SVBuildListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for assigning armor type and amount for support vehicles. Manages both standard SV armor and advanced
 * armors, available under experimental rules.
 */
public class SVArmorView extends BuildView implements ActionListener, ChangeListener {

    private final List<SVBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(SVBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(SVBuildListener l) {
        listeners.remove(l);
    }

    private final static String CMD_MAXIMIZE  = "MAXIMIZE"; //$NON-NLS-1$
    private final static String CMD_REMAINING = "REMAINING"; //$NON-NLS-1$

    private final CustomComboBox<Integer> cbTechRating = new CustomComboBox<>(ITechnology::getRatingName);
    private final JComboBox<Integer> cbBARRating = new JComboBox<>();
    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(eq -> eq.getName());
    private final JCheckBox chkAdvanced = new JCheckBox();
    private final JCheckBox chkPatchwork = new JCheckBox();
    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(0, 0, 0, 0.5);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();

    private final ITechManager techManager;
    private TestSupportVehicle.SVType svType;

    public SVArmorView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("SVArmorView.cbTechRating.text"), labelSizeLg), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        setFieldSize(cbTechRating, controlSize);
        cbTechRating.setToolTipText(resourceMap.getString("SVArmorView.cbTechRating.tooltip")); //$NON-NLS-1$
        add(cbTechRating, gbc);
        cbTechRating.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("SVArmorView.cbBARRating.text"), labelSizeLg), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        setFieldSize(cbBARRating, controlSize);
        cbBARRating.setToolTipText(resourceMap.getString("SVArmorView.cbBARRating.tooltip")); //$NON-NLS-1$
        add(cbBARRating, gbc);
        cbBARRating.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ArmorView.cbArmorType.text"), labelSizeLg), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        setFieldSize(cbArmorType, controlSize);
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip")); //$NON-NLS-1$
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ArmorView.spnTonnage.text"), labelSizeLg), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        setFieldSize(spnTonnage, controlSize);
        spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        chkAdvanced.setText(resourceMap.getString("SVArmorView.chkAdvanced.text")); //$NON-NLS-1$
        setFieldSize(chkAdvanced, controlSize);
        chkAdvanced.setToolTipText(resourceMap.getString("SVArmorView.chkAdvanced.tooltip")); //$NON-NLS-1$
        add(chkAdvanced, gbc);
        chkAdvanced.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        chkPatchwork.setText(resourceMap.getString("ArmorView.chkPatchwork.text")); //$NON-NLS-1$
        setFieldSize(chkPatchwork, controlSize);
        chkPatchwork.setToolTipText(resourceMap.getString("ArmorView.chkPatchwork.tooltip")); //$NON-NLS-1$
        add(chkPatchwork, gbc);
        chkPatchwork.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text")); //$NON-NLS-1$
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        setFieldSize(btnMaximize, controlSize);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip")); //$NON-NLS-1$
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text")); //$NON-NLS-1$
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        setFieldSize(btnUseRemaining, controlSize);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip")); //$NON-NLS-1$
        add(btnUseRemaining, gbc);
        btnUseRemaining.addActionListener(this);
    }

    public void setFromEntity(Entity entity) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
