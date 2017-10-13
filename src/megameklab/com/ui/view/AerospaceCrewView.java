/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import megamek.common.BattleArmor;
import megamek.common.EntityWeightClass;
import megamek.common.ITechManager;
import megamek.common.SmallCraft;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestSmallCraft;

/**
 * Structure tab panel for setting crew levels and quarters, as well as life boats and escape pods.
 * 
 * @author Neoancient
 *
 */

public class AerospaceCrewView extends BuildView {
    
    private final JSpinner spnCrew = new JSpinner(new SpinnerNumberModel(3, 1, null, 1));
    private final JSpinner spnOfficers = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    private final JLabel lblEnlisted = new JLabel();
    private final JLabel lblGunners = new JLabel();
    private final JSpinner spnMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnBAMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnPassengers = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersOfficer = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersStandard = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersFirstClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersSecondClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersSteerage = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnLifeBoats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnEscapePods = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JButton btnAssignQuarters = new JButton();
    
    private final ITechManager techManager;
    
    public AerospaceCrewView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    public void initUI() {
        setLayout(new GridBagLayout());
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnCrew.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnCrew, spinnerSize);
        spnCrew.setToolTipText(resourceMap.getString("AerospaceCrewView.spnCrew.tooltip"));
        add(spnCrew, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnOfficers.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnOfficers, spinnerSize);
        spnOfficers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnOfficers.tooltip"));
        add(spnOfficers, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.lblEnlisted.text"), labelSize), gbc);
        gbc.gridx = 1;
        add(lblEnlisted, gbc);
        lblEnlisted.setToolTipText(resourceMap.getString("AerospaceCrewView.lblEnlisted.tooltip"));
        lblEnlisted.setHorizontalAlignment(JLabel.RIGHT);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.lblGunners.text"), labelSize), gbc);
        gbc.gridx = 1;
        add(lblGunners, gbc);
        lblGunners.setToolTipText(resourceMap.getString("AerospaceCrewView.lblGunners.tooltip"));
        lblGunners.setHorizontalAlignment(JLabel.RIGHT);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnPassengers.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnPassengers, spinnerSize);
        spnPassengers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnPassengers.tooltip"));
        add(spnPassengers, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnMarines.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnMarines, spinnerSize);
        spnMarines.setToolTipText(resourceMap.getString("AerospaceCrewView.spnMarines.tooltip"));
        add(spnMarines, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnBAMarines.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnBAMarines, spinnerSize);
        spnBAMarines.setToolTipText(resourceMap.getString("AerospaceCrewView.spnBAMarines.tooltip"));
        add(spnBAMarines, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        btnAssignQuarters.setText(resourceMap.getString("AerospaceCrewView.btnAssignQuarters.text"));
        btnAssignQuarters.setToolTipText(resourceMap.getString("AerospaceCrewView.btnAssignQuarters.tooltip"));
        add(btnAssignQuarters, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersOfficer.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersOfficer, spinnerSize);
        spnQuartersOfficer.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersOfficer.tooltip"));
        add(spnQuartersOfficer, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersStandard.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersStandard, spinnerSize);
        spnQuartersStandard.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersStandard.tooltip"));
        add(spnQuartersStandard, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersFirstClass.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersFirstClass, spinnerSize);
        spnQuartersFirstClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersFirstClass.tooltip"));
        add(spnQuartersFirstClass, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersSecondClass.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersSecondClass, spinnerSize);
        spnQuartersSecondClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersSecondClass.tooltip"));
        add(spnQuartersSecondClass, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersSteerage.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersSteerage, spinnerSize);
        spnQuartersSteerage.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersSteerage.tooltip"));
        add(spnQuartersSteerage, gbc);
        
        gbc.gridx = 2;
        gbc.gridy += 2;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnLifeBoats.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnLifeBoats, spinnerSize);
        spnLifeBoats.setToolTipText(resourceMap.getString("AerospaceCrewView.spnLifeBoats.tooltip"));
        add(spnLifeBoats, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnEscapePods.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnEscapePods, spinnerSize);
        spnEscapePods.setToolTipText(resourceMap.getString("AerospaceCrewView.spnEscapePods.tooltip"));
        add(spnEscapePods, gbc);
    }
    
    public void setFromEntity(SmallCraft sc) {
        int gunners = TestSmallCraft.requiredGunners(sc);
        int minBase = TestSmallCraft.minimumBaseCrew(sc);
        int nonBay = sc.getNCrew() - sc.getBayPersonnel();
        int minOfficers = (int)Math.ceil(nonBay / 5.0);
        ((SpinnerNumberModel)spnCrew.getModel()).setMinimum(minBase + gunners);
        ((SpinnerNumberModel)spnOfficers.getModel()).setMinimum(minOfficers);
        
        spnCrew.setValue(nonBay);
        spnOfficers.setValue(Math.max(minOfficers, sc.getNOfficers()));
        lblEnlisted.setText(String.valueOf(Math.max(0, nonBay - gunners - ((Integer) spnOfficers.getValue()))));
        lblGunners.setText(String.valueOf(gunners));
        spnPassengers.setValue(sc.getNPassenger());
        spnMarines.setValue(sc.getNMarines());
        
        if (techManager.isLegal(BattleArmor.getConstructionTechAdvancement(EntityWeightClass.WEIGHT_MEDIUM))) {
            spnBAMarines.setValue(sc.getNBattleArmor());
            spnBAMarines.setVisible(true);
        } else {
            spnBAMarines.setValue(0);
            spnBAMarines.setVisible(false);
        }
        
        spnLifeBoats.setValue(sc.getLifeBoats());
        spnEscapePods.setValue(sc.getEscapePods());
    }
}
