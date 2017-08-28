/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.GBC;
import megamek.common.Engine;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.SimpleTechLevel;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.TroopSpace;
import megamek.common.WeaponType;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Vehicle.views.ArmorView;
import megameklab.com.ui.Vehicle.views.SummaryView;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MovementView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener,
        ChangeListener, ItemListener,
        BasicInfoView.BasicInfoListener, MovementView.MovementListener,
        MVFArmorView.ArmorListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    private static final String ENGINESTANDARD = "Standard";
    private static final String ENGINEXL = "XL";
    private static final String ENGINELIGHT = "Light";
    private static final String ENGINECOMPACT = "Compact";
    private static final String ENGINEFISSION = "Fission";
    private static final String ENGINEFUELCELL = "Fuel Cell";
    private static final String ENGINEXXL = "XXL";
    private static final String ENGINEICE = "I.C.E";

    String[] isEngineTypes = { ENGINESTANDARD, ENGINEICE, ENGINEXL,
            ENGINELIGHT, ENGINECOMPACT, ENGINEFISSION, ENGINEFUELCELL,
            ENGINEXXL };
    String[] clanEngineTypes = { ENGINESTANDARD, ENGINEICE, ENGINEXL,
            ENGINEFUELCELL, ENGINEXXL };

    JComboBox<String> engineType = new JComboBox<String>(isEngineTypes);

    JSpinner weight;
    String[] tankMotiveTypes = { "Hover", "Wheeled", "Tracked", "WiGE", "VTOL",
    		"Naval (Displacement)", "Hydrofoil", "Submarine"};
    JComboBox<String> tankMotiveType = new JComboBox<String>(tankMotiveTypes);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox superHeavyCB = new JCheckBox("Super-Heavy");
    String[] turretTypes = { "None", "Single", "Dual" };
    JComboBox<String> turretCombo = new JComboBox<String>(turretTypes);
    JSpinner baseChassisTurretWeight;
    JSpinner baseChassisTurret2Weight;
    Dimension maxSize = new Dimension();
    JLabel baseChassisHeatSinksLabel = new JLabel("Base Heat Sinks:",
            SwingConstants.TRAILING);
    JPanel masterPanel;
    BasicInfoView panBasicInfo; 
    JPanel panChassis;
    MVFArmorView panArmor;
    MovementView panMovement;
    SummaryView panSummary;
    
    JSpinner fixedTroopStorage = null;
    JSpinner podTroopStorage = null;
    JLabel podTroopStorageLabel;
    JButton resetChassisButton = new JButton("Reset Chassis (Omni)");
    int maxTonnage = 50;
    int minTonnage = 1;
    private ArmorView armor;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        armor = new ArmorView(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getTank().getConstructionTechAdvancement());
        panChassis = new JPanel(new GridBagLayout());
        panArmor = new MVFArmorView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panSummary = new SummaryView(eSource);

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(55, 25);

        weight = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1));

        baseChassisTurretWeight = new JSpinner(new SpinnerNumberModel(0, 0.0,
                getTank().getWeight(), 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) baseChassisTurretWeight.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurretWeight.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurretWeight.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurretWeight.getEditor())
                .setMinimumSize(spinnerSize);

        baseChassisTurret2Weight = new JSpinner(new SpinnerNumberModel(0, 0.0,
                getTank().getWeight(), 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) baseChassisTurret2Weight.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurret2Weight.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurret2Weight.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisTurret2Weight.getEditor())
                .setMinimumSize(spinnerSize);

        fixedTroopStorage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));
        podTroopStorage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));

        panBasicInfo.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());

        Dimension labelSize = new Dimension(110, 25);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panChassis.add(createLabel("Tonnage:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panChassis.add(weight, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panChassis.add(omniCB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panChassis.add(superHeavyCB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panChassis.add(createLabel("Motive Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panChassis.add(tankMotiveType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Engine Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panChassis.add(engineType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Turret:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panChassis.add(turretCombo, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panChassis.add(createLabel("Base Chassis Turret Weight:", labelSize), gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panChassis.add(baseChassisTurretWeight, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panChassis.add(createLabel("Base Chassis Second Turret Weight:", labelSize), gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panChassis.add(baseChassisTurret2Weight, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panChassis.add(createLabel("Fixed Troop Storage:", labelSize), gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panChassis.add(fixedTroopStorage, gbc);
        podTroopStorageLabel = createLabel("Pod Troop Storage:", labelSize);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panChassis.add(podTroopStorageLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panChassis.add(podTroopStorage, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        panChassis.add(resetChassisButton, gbc);

        Dimension comboSize = new Dimension(180, 25);

        setFieldSize(turretCombo, comboSize);
        setFieldSize(engineType, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(panMovement);
        leftPanel.add(Box.createGlue());
        
        rightPanel.add(panArmor);
        rightPanel.add(panSummary);
        rightPanel.add(Box.createVerticalGlue());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(rightPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(armor, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armor.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }

    public void refresh() {
        removeAllListeners();
        
        panBasicInfo.setFromEntity(getTank());
        panMovement.setFromEntity(getTank());
        panArmor.setFromEntity(getTank());
        
        omniCB.setSelected(getTank().isOmni());
        superHeavyCB.setSelected((getTank()).isSuperHeavy());
        resetChassisButton.setEnabled(getTank().isOmni());
        podTroopStorageLabel.setEnabled(getTank().isOmni());
        podTroopStorage.setEnabled(getTank().isOmni());

        if (!getTank().hasNoDualTurret()) {
            turretCombo.setSelectedIndex(2);
        } else if (!getTank().hasNoTurret()) {
            turretCombo.setSelectedIndex(1);
        } else {
            turretCombo.setSelectedIndex(0);
        }

        switch (getTank().getMovementMode()) {
            case HOVER:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(0);
                break;
            case WHEELED:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(1);
                break;
            case TRACKED:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(2);
                break;
            case WIGE:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(3);
                break;
            case VTOL:
                tankMotiveType.setSelectedIndex(4);
                if ((getTank().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL) ||  (getTank().getTechLevel() == TechConstants.T_IS_UNOFFICIAL) || (getTank().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL) || (getTank().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL)) {
                    turretCombo.setEnabled(true);
                    turretCombo.removeAllItems();
                    turretCombo.addItem("None");
                    turretCombo.addItem("Chin");
                    turretCombo.setSelectedIndex(getTank().hasNoTurret()?0:1);
                } else {
                    turretCombo.setEnabled(false);
                }
                updateTurrets(turretCombo);
                break;
            case NAVAL:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(5);
                break;
            case HYDROFOIL:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(6);
                break;
            case SUBMARINE:
                turretCombo.setEnabled(true);
                tankMotiveType.setSelectedIndex(7);
                break;
            default:
                break;
        }

        setAvailableWeights((int) getTank().getWeight());

        updateEngineTypes(getTank().isClan());
        updateTroopSpaceAllowed();

        engineType.setSelectedIndex(convertEngineType(getTank().getEngine()
                .getEngineType()));
        armor.refresh();
        panSummary.refresh();

        updateBCTurretWeightSpinners();
        
        addAllListeners();
    }
    
    private void updateBCTurretWeightSpinners() {
        if (getTank().isOmni() && hasDualTurret()) {
            baseChassisTurretWeight.setEnabled(true);
            if (getTank().getBaseChassisTurretWeight() >= 0) {
                baseChassisTurretWeight.setValue((double)getTank()
                        .getBaseChassisTurretWeight());
            } else {
                baseChassisTurretWeight.setValue(0.0);
            }
            baseChassisTurret2Weight.setEnabled(true);
            if (getTank().getBaseChassisTurret2Weight() >= 0) {
                baseChassisTurret2Weight.setValue((double)getTank()
                        .getBaseChassisTurret2Weight());
            } else {
                baseChassisTurret2Weight.setValue(0.0);
            }
        } else if (getTank().isOmni() && hasTurret()) {
            baseChassisTurretWeight.setEnabled(true);
            if (getTank().getBaseChassisTurretWeight() >= 0) {
                baseChassisTurretWeight.setValue((double)getTank()
                        .getBaseChassisTurretWeight());
            } else {
                baseChassisTurretWeight.setValue(0.0);
            }
            baseChassisTurret2Weight.setEnabled(false);
            baseChassisTurret2Weight.setValue(0.0);
        } else {
            baseChassisTurretWeight.setEnabled(false);
            baseChassisTurret2Weight.setEnabled(false);
            baseChassisTurretWeight.setValue(0.0);
            baseChassisTurret2Weight.setValue(0.0);
        }
    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void actionPerformed(ActionEvent e) {

        if (refresh == null) {
            return;
        }
        removeAllListeners();
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();

            if (check.equals(omniCB)) {
                getTank().setOmni(omniCB.isSelected());
                if (getTank().isOmni()) {
                } else {
                    getTank().getEngine().setBaseChassisHeatSinks(-1);
                    fixedTroopStorage.setValue(getTank().getTroopCarryingSpace());
                    podTroopStorage.setValue(0.0);
                    updateTroopSpaceAllotted();
                }
                resetChassisButton.setEnabled(getTank().isOmni());
                podTroopStorageLabel.setEnabled(getTank().isOmni());
                podTroopStorage.setEnabled(getTank().isOmni());
            }
        }
        addAllListeners();
        refresh.refreshAll();
    }

    public void removeAllListeners() {
        engineType.removeItemListener(this);
        weight.removeChangeListener(this);
        omniCB.removeActionListener(this);
        superHeavyCB.removeActionListener(this);
        turretCombo.removeItemListener(this);
        tankMotiveType.removeItemListener(this);
        fixedTroopStorage.removeChangeListener(this);
        podTroopStorage.removeChangeListener(this);
        resetChassisButton.removeActionListener(this);
        baseChassisTurretWeight.removeChangeListener(this);
        baseChassisTurret2Weight.removeChangeListener(this);
        panBasicInfo.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
    }

    public void addAllListeners() {
        engineType.addItemListener(this);
        weight.addChangeListener(this);
        omniCB.addActionListener(this);
        superHeavyCB.addActionListener(this);
        turretCombo.addItemListener(this);
        tankMotiveType.addItemListener(this);
        fixedTroopStorage.addChangeListener(this);
        podTroopStorage.addChangeListener(this);
        resetChassisButton.addActionListener(this);
        baseChassisTurretWeight.addChangeListener(this);
        baseChassisTurret2Weight.addChangeListener(this);
        panBasicInfo.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public boolean hasTurret() {
        return turretCombo.getSelectedIndex() > 0;
    }

    public boolean hasDualTurret() {
        return turretCombo.getSelectedIndex() > 1;
    }

    private void updateTroopSpaceAllowed() {
    	double troops = getTank().getTroopCarryingSpace();
    	double pod = getTank().getPodMountedTroopCarryingSpace();

        if (troops > (int) getTank().getWeight()) {
            getTank().removeAllTransporters();
            ((SpinnerNumberModel) fixedTroopStorage.getModel()).setValue(0.0);
            ((SpinnerNumberModel) podTroopStorage.getModel()).setValue(0.0);
        } else {
            fixedTroopStorage.setValue(troops - pod);
            podTroopStorage.setValue(pod);
        }

    }

    private void setAvailableWeights(int currentTonnage) {

        if (!isSuperHeavy()) {
            switch (tankMotiveType.getSelectedIndex()) {
                case 0:
                    maxTonnage = 50;
                    getTank().setMovementMode(EntityMovementMode.HOVER);
                    break;
                case 1:
                    maxTonnage = 80;
                    getTank().setMovementMode(EntityMovementMode.WHEELED);
                    break;
                case 2:
                    maxTonnage = 100;
                    getTank().setMovementMode(EntityMovementMode.TRACKED);
                    break;
                case 3:
                    maxTonnage = 80;
                    getTank().setMovementMode(EntityMovementMode.WIGE);
                    break;
                case 4:
                    maxTonnage = 30;
                    getTank().setMovementMode(EntityMovementMode.VTOL);
                    break;
                case 5:
                    maxTonnage = 300;
                    getTank().setMovementMode(EntityMovementMode.NAVAL);
                    break;
                case 6:
                    maxTonnage = 100;
                    getTank().setMovementMode(EntityMovementMode.HYDROFOIL);
                    break;
                case 7:
                    maxTonnage = 300;
                    getTank().setMovementMode(EntityMovementMode.SUBMARINE);
                    break;
            }
            minTonnage = 1;
        } else {
            switch (tankMotiveType.getSelectedIndex()) {
                case 0:
                    minTonnage = 51;
                    maxTonnage = 100;
                    getTank().setMovementMode(EntityMovementMode.HOVER);
                    break;
                case 1:
                    minTonnage = 81;
                    maxTonnage = 160;
                    getTank().setMovementMode(EntityMovementMode.WHEELED);
                    break;
                case 2:
                    minTonnage = 101;
                    maxTonnage = 200;
                    getTank().setMovementMode(EntityMovementMode.TRACKED);
                    break;
                case 3:
                    minTonnage = 81;
                    maxTonnage = 160;
                    getTank().setMovementMode(EntityMovementMode.WIGE);
                    break;
                case 4:
                    minTonnage = 31;
                    maxTonnage = 60;
                    getTank().setMovementMode(EntityMovementMode.VTOL);
                    break;
                case 5:
                    minTonnage = 301;
                    maxTonnage = 555;
                    getTank().setMovementMode(EntityMovementMode.NAVAL);
                    break;
                case 7:
                    minTonnage = 301;
                    maxTonnage = 555;
                    getTank().setMovementMode(EntityMovementMode.SUBMARINE);
                    break;
            }
        }

        currentTonnage = Math.max(minTonnage,
                Math.min(currentTonnage, maxTonnage));
        getTank().setWeight(currentTonnage);
        ((SpinnerNumberModel) fixedTroopStorage.getModel())
            .setMaximum((double) currentTonnage);
        ((SpinnerNumberModel) podTroopStorage.getModel())
            .setMaximum((double) currentTonnage);
        ((SpinnerNumberModel) weight.getModel()).setMaximum(maxTonnage);
        ((SpinnerNumberModel) weight.getModel()).setMinimum(minTonnage);
        weight.getModel().setValue((int)getTank().getWeight());
    }

    private int convertEngineType(String engineType) {
        if (engineType.equals(ENGINESTANDARD)) {
            return Engine.NORMAL_ENGINE;
        }
        if (engineType.equals(ENGINEXL)) {
            return Engine.XL_ENGINE;
        }
        if (engineType.equals(ENGINEXXL)) {
            return Engine.XXL_ENGINE;
        }
        if (engineType.equals(ENGINEICE)) {
            return Engine.COMBUSTION_ENGINE;
        }
        if (engineType.equals(ENGINECOMPACT)) {
            return Engine.COMPACT_ENGINE;
        }
        if (engineType.equals(ENGINEFISSION)) {
            return Engine.FISSION;
        }
        if (engineType.equals(ENGINEFUELCELL)) {
            return Engine.FUEL_CELL;
        }
        if (engineType.equals(ENGINELIGHT)) {
            return Engine.LIGHT_ENGINE;
        }

        return Engine.NORMAL_ENGINE;
    }

    private void updateEngineTypes(boolean isClan) {

        int engineCount = 1;
        String[] engineList;

        engineType.removeAllItems();

        if (isClan) {
            engineList = clanEngineTypes;
            switch (getTank().getTechLevel()) {
                case TechConstants.T_CLAN_TW:
                    engineCount = 3;
                    break;
                case TechConstants.T_CLAN_ADVANCED:
                    engineCount = 4;
                    break;
                case TechConstants.T_CLAN_EXPERIMENTAL:
                case TechConstants.T_CLAN_UNOFFICIAL:
                    engineCount = 5;
                    break;
            }
        } else {

            engineList = isEngineTypes;
            switch (getTank().getTechLevel()) {
                case TechConstants.T_INTRO_BOXSET:
                    engineCount = 2;
                    break;
                case TechConstants.T_IS_TW_NON_BOX:
                    engineCount = 5;
                    break;
                case TechConstants.T_IS_ADVANCED:
                    engineCount = 7;
                    break;
                case TechConstants.T_IS_EXPERIMENTAL:
                case TechConstants.T_IS_UNOFFICIAL:
                    engineCount = 8;
                    break;
            }
        }

        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

    }

    private int convertEngineType(int engineType) {

        if (getTank().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            switch (engineType) {
                case Engine.NORMAL_ENGINE:
                    return 0;
                case Engine.COMBUSTION_ENGINE:
                    return 1;
                case Engine.XL_ENGINE:
                    return 2;
                case Engine.FUEL_CELL:
                    return 3;
                case Engine.XXL_ENGINE:
                    return 4;
            }
        } else {
            switch (engineType) {
                case Engine.NORMAL_ENGINE:
                    return 0;
                case Engine.COMBUSTION_ENGINE:
                    return 1;
                case Engine.XL_ENGINE:
                    return 2;
                case Engine.LIGHT_ENGINE:
                    return 3;
                case Engine.COMPACT_ENGINE:
                    return 4;
                case Engine.FISSION:
                    return 5;
                case Engine.FUEL_CELL:
                    return 6;
                case Engine.XXL_ENGINE:
                    return 7;
            }
        }

        return 0;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        removeAllListeners();
        if (e.getSource().equals(weight)) {
            updateWeightEngineMovement();
        }
        if (e.getSource().equals(fixedTroopStorage) || e.getSource().equals(podTroopStorage)) {
            updateTroopSpaceAllotted();
        } else if (e.getSource().equals(baseChassisTurretWeight)) {
            getTank().setBaseChassisTurretWeight(
                    ((Double) baseChassisTurretWeight.getValue()).floatValue());
        } else if (e.getSource().equals(baseChassisTurret2Weight)) {
            getTank()
                    .setBaseChassisTurret2Weight(
                            ((Double) baseChassisTurret2Weight.getValue())
                                    .floatValue());
        }
        addAllListeners();
        refresh.refreshAll();
    }

    private void updateTroopSpaceAllotted() {
        getTank().removeAllTransporters();
        double fixedStorage = (Double)fixedTroopStorage.getValue();
        double podStorage = + (Double)podTroopStorage.getValue();
        if (fixedStorage + podStorage > 0) {
            double troopTons = Math
                    .round((fixedStorage) * 2) / 2.0;
            getTank().addTransporter(new TroopSpace(troopTons), false);
            if (podStorage > 0) {
                troopTons = Math.round(podStorage * 2) / 2.0;
                getTank().addTransporter(new TroopSpace(troopTons), true);
            }
        }
    }

    public boolean isSuperHeavy() {
        return superHeavyCB.isSelected();
    }

    public boolean isVTOL() {
        return tankMotiveType.getSelectedIndex() == 4;
    }

    private void updateTurrets(JComboBox<String> combo) {
        if ((combo.getSelectedIndex() == 0) || (combo.getSelectedIndex() == 1)) {
            int turretLoc = getTank().getLocTurret();
            List<Mounted> toRemove = new ArrayList<Mounted>();
            List<Mounted> toRemoveMisc = new ArrayList<Mounted>();
            List<Mounted> toRemoveWeapons = new ArrayList<Mounted>();
            if (!getTank().hasNoDualTurret()) {
                for (int slot = 0; slot < getTank().getNumberOfCriticals(
                        getTank().getLocTurret2()); slot++) {
                    getTank().setCritical(getTank().getLocTurret2(), slot, null);
                }
                for (Mounted mount : getTank().getEquipment()) {
                    if (mount.getLocation() == getTank().getLocTurret2()) {
                        toRemove.add(mount);
                        if (mount.getType() instanceof WeaponType) {
                            toRemoveWeapons.add(mount);
                        } else if (mount.getType() instanceof MiscType) {
                            toRemoveMisc.add(mount);
                        }
                    }
                }
                getTank().setHasNoDualTurret(true);
                getTank().setBaseChassisTurret2Weight(-1);
            }
            if (getTank().hasNoTurret() && (combo.getSelectedIndex() == 1)) {
                // add turret
                getTank().setHasNoTurret(false);
                getTank().autoSetInternal();
                getTank().initializeArmor(0, turretLoc);
                getTank().setArmorTechLevel(
                        getTank().getArmorTechLevel(Tank.LOC_FRONT),
                        turretLoc);
                getTank().setArmorType(getTank().getArmorType(Tank.LOC_FRONT),
                        turretLoc);
            } else if (!getTank().hasNoTurret()
                    && (combo.getSelectedIndex() == 0)) {
                for (int slot = 0; slot < getTank().getNumberOfCriticals(
                        turretLoc); slot++) {
                    getTank().setCritical(turretLoc, slot, null);
                }
                for (Mounted mount : getTank().getEquipment()) {
                    if (mount.getLocation() == turretLoc) {
                        toRemove.add(mount);
                        if (mount.getType() instanceof WeaponType) {
                            toRemoveWeapons.add(mount);
                        } else if (mount.getType() instanceof MiscType) {
                            toRemoveMisc.add(mount);
                        }
                    }
                }
                getTank().setHasNoTurret(true);
                getTank().setBaseChassisTurretWeight(-1);
            }
            getTank().getEquipment().removeAll(toRemove);
            getTank().getMisc().removeAll(toRemoveMisc);
            getTank().getWeaponList().removeAll(toRemoveWeapons);
        } else if (combo.getSelectedIndex() == 2) {
            // add second turret
            getTank().setHasNoDualTurret(false);
            // if no first turret, add that
            if (getTank().hasNoTurret()) {
                int turretLoc = getTank().getLocTurret2();
                getTank().setHasNoTurret(false);
                getTank().initializeArmor(0, turretLoc);
                getTank().setArmorTechLevel(
                        getTank().getArmorTechLevel(Tank.LOC_FRONT),
                        turretLoc);
                getTank().setArmorType(getTank().getArmorType(Tank.LOC_FRONT),
                        turretLoc);
            }
            getTank().autoSetInternal();
            getTank().initializeArmor(0, getTank().getLocTurret2());
            getTank().setArmorTechLevel(
                    getTank().getArmorTechLevel(Tank.LOC_FRONT),
                    getTank().getLocTurret2());
            getTank().setArmorType(getTank().getArmorType(getTank().getLocTurret()),
                    getTank().getLocTurret2());
        }
        armor.refresh();
    }

    private void updateWeightEngineMovement() {

        // we first need to set the weight, because the tank's
        // suspension factor
        // depends on the weight, and is needed for the engine
        // rating
    	double oldWeight = getTank().getWeight();
        getTank().setWeight(Double.parseDouble(weight.getModel().getValue().toString()));
        int rating = Math
                .max(10, (panMovement.getWalk() * Integer.parseInt(weight
                                .getModel().getValue().toString()))
                                - (getTank()).getSuspensionFactor());
        if (rating > 500) {
            // if we changed weight, reset the weight if the rating is too high
            if (oldWeight < getTank().getWeight()) {
                getTank().setWeight(oldWeight);
                weight.getModel().setValue((int)oldWeight);
            }
            JOptionPane
                    .showMessageDialog(
                            this,
                            "That speed/weight combination would create an engine with a rating over 500.",
                            "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
        } else {
            getTank().setWeight(Double.parseDouble(weight.getModel().getValue().toString()));
            ((SpinnerNumberModel) fixedTroopStorage.getModel()).setMaximum(Double
                    .parseDouble(weight.getModel().getValue().toString()));
            ((SpinnerNumberModel) podTroopStorage.getModel()).setMaximum(Double
                    .parseDouble(weight.getModel().getValue().toString()));
            getTank().autoSetInternal();
            int type = convertEngineType(engineType.getSelectedItem()
                    .toString());
            if (getTank().isClan()) {
                getTank().setEngine(
                        new Engine(rating, type, Engine.CLAN_ENGINE
                                | Engine.TANK_ENGINE));
            } else {
                getTank().setEngine(
                        new Engine(rating, type, Engine.TANK_ENGINE));
            }
            getTank().setOriginalWalkMP(panMovement.getWalk());
        }
    }

    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {
        if (EquipmentType.T_ARMOR_PATCHWORK == at) {
            boolean isMixed = panBasicInfo.isMixedTech();
            List<EquipmentType> armors = panArmor.getAllArmors();
            List<TechComboBox<EquipmentType>> combos = new ArrayList<>();
            JPanel panel = new JPanel(new GridBagLayout());
            // Start with 1 to skip body
            for (int loc = 1; loc < getTank().locations(); loc++) {
                TechComboBox<EquipmentType> cbLoc = new TechComboBox<>(eq -> eq.getName());
                cbLoc.showTechBase(isMixed);
                armors.forEach(a -> cbLoc.addItem(a));
                EquipmentType locArmor = EquipmentType.get(EquipmentType
                        .getArmorTypeName(getTank().getArmorType(loc),
                                TechConstants.isClan(getTank().getArmorTechLevel(loc))));
                cbLoc.setSelectedItem(locArmor);
                combos.add(cbLoc);
                JLabel label = new JLabel(getTank().getLocationName(loc));
                panel.add(label, GBC.std());
                panel.add(cbLoc, GBC.eol());
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            UnitUtil.removeISorArmorMounts(getTank(), false);
            for (int loc = 0; loc < getTank().locations(); loc++) {
                EquipmentType armor = (EquipmentType)combos.get(loc).getSelectedItem();
                getTank().setArmorTechLevel(armor.getTechLevel(panBasicInfo.getTechYear()), loc);
                getTank().setArmorType(EquipmentType.getArmorType(armor), loc);
            }
            panArmor.setFromEntity(getTank());
        } else {
            getTank().setArmorTechLevel(aTechLevel);
            getTank().setArmorType(at);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            removeAllListeners();
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            try {
                // if a Tank is primitive and thus needs a larger engine
                if (combo.equals(engineType)) {
                    updateWeightEngineMovement();
                } else if (combo.equals(tankMotiveType)) {
                    int currentTonnage = (Integer) weight.getModel().getValue();
                    setAvailableWeights(currentTonnage);
                    panArmor.setFromEntity(getTank());
                    updateWeightEngineMovement();
                } else if (combo.equals(turretCombo)) {
                    updateTurrets(combo);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            addAllListeners();
            refresh.refreshAll();
        }
    }

    public void refreshSummary() {
        panSummary.refresh();
    }

    @Override
    public void chassisChanged(String chassis) {
        getTank().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getTank().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getTank().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getTank().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getTank().isClan()) || (mixed != getTank().isMixedTech())) {
            getTank().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }
    
    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getTank().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.isClan()));
        if (!getTank().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
        }
        /*
        createArmorMountsAndSetArmorType(getTank().getArmorType(0), getTank().getArmorTechLevel(0));
        // If we have a large engine, a drop in tech level may make it unavailable and we will need
        // to reduce speed to a legal value.
        if (getTank().getEngine().hasFlag(Engine.LARGE_ENGINE)
                && panChassis.getAvailableEngines().isEmpty()) {
            int walk;
            if (getTank().isPrimitive()) {
                walk = 400 / (int)(getTank().getWeight() * 1.2);
            } else {
                walk = 400 / (int)getTank().getWeight();
            }
            recalculateEngineRating(walk, getTank().getWeight());
            getTank().setOriginalWalkMP(walk);
            panMovement.setFromEntity(getTank());
            JOptionPane.showMessageDialog(
                    this, String.format("Large engine not available at this tech level. Reducing MP to %d.", walk),
                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
        }
        if (UnitUtil.checkEquipmentByTechLevel(getTank(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        */
        panArmor.refresh();
        panMovement.refresh();
        armor.resetArmorPoints();
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getTank().setManualBV(manualBV);
    }

    @Override
    public void walkChanged(int walkMP) {
        updateWeightEngineMovement();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
        panMovement.setFromEntity(getTank());
//        panChassis.refresh();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        if (null != jumpJet) {
            UnitUtil.removeAllMiscMounteds(getTank(), MiscType.F_JUMP_JET);
            getTank().setOriginalJumpMP(0);
            for (int i = 0; i < jumpMP; i++) {
                try {
                    getTank().addEquipment(jumpJet, Tank.LOC_BODY);
                } catch (LocationFullException e) {
                    e.printStackTrace();
                }
            }
            panSummary.refresh();
            refresh.refreshBuild();
            refresh.refreshStatus();
            refresh.refreshPreview();
            panMovement.setFromEntity(getTank());
        }
    }

    @Override
    public void jumpTypeChanged(final EquipmentType jumpJet) {
        // Vehicles ignore
    }

    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        if (!getTank().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getTank(), false);
        }
        createArmorMountsAndSetArmorType(at, aTechLevel);
        if (!getTank().hasPatchworkArmor()) {
            armor.resetArmorPoints();
        }
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTonnageChanged(double tonnage) {
        getTank().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        armor.resetArmorPoints();

        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getTank());
        getTank().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getTank())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());
        double totalTonnage = getTank().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(getTank().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getTank()));
        getTank().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getTank());
        panArmor.addListener(this);
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

}
