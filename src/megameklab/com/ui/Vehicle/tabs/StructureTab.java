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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
import javax.swing.JTextField;
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
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.TroopSpace;
import megamek.common.VTOL;
import megamek.common.WeaponType;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestTank;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Vehicle.views.SummaryView;
import megameklab.com.ui.Vehicle.views.ArmorView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener,
        ChangeListener, ItemListener {

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

    JSpinner cruiseMP;
    JSpinner jumpMP;
    JTextField flankMP;
    JSpinner weight;
    JSpinner armorTonnage;
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JButton unusedTonnageArmorButton = new JButton("Use Remaining Tonnage");
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);
    String[] techTypes = { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox<String> techType = new JComboBox<String>(techTypes);
    String[] isTechLevels = { "Intro", "Standard", "Advanced", "Experimental",
            "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental",
            "Unoffical" };
    JComboBox<String> techLevel;
    String[] tankMotiveTypes = { "Hover", "Wheeled", "Tracked", "WiGE", "VTOL" };
    JComboBox<String> tankMotiveType = new JComboBox<String>(tankMotiveTypes);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
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
    JPanel panInfo;
    JPanel panChassis;
    JPanel panArmor;
    JPanel panMovement;
    SummaryView panSummary;
    
    JSpinner troopStorage = null;
    int maxTonnage = 50;
    int minTonnage = 1;
    JTextField manualBV = new JTextField(3);
    private ArmorView armor;
    private JComboBox<String> armorCombo = new JComboBox<String>();

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
        panInfo = new JPanel(new GridBagLayout());
        panChassis = new JPanel(new GridBagLayout());
        panArmor = new JPanel(new GridBagLayout());
        panMovement = new JPanel(new GridBagLayout());
        panSummary = new SummaryView(eSource);

        if (getTank().isClan()){
            techLevel = new JComboBox<String>(clanTechLevels);
        } else {
            techLevel = new JComboBox<String>(isTechLevels);
        }

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(55, 25);

        cruiseMP = new JSpinner(new SpinnerNumberModel(3, 0, 25, 1));
        ((JSpinner.DefaultEditor) cruiseMP.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) cruiseMP.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) cruiseMP.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) cruiseMP.getEditor())
                .setMinimumSize(spinnerSize);

        flankMP = new JTextField();
        flankMP.setEditable(false);
        setFieldSize(flankMP, spinnerSize);
        flankMP.setHorizontalAlignment(SwingConstants.RIGHT);



        jumpMP = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setMinimumSize(spinnerSize);

        disableJump();

        weight = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1));

        armorTonnage = new JSpinner(new SpinnerNumberModel(
                getTank().getArmorWeight(), 0.0, ((getTank().getWeight()*3.5)+40)/8, 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMinimumSize(spinnerSize);

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

        updateArmor();

        troopStorage = new JSpinner(new SpinnerNumberModel(0, 0, 0, 0.5));

        chassis.setText(getTank().getChassis());
        model.setText(getTank().getModel());

        Dimension labelSize = new Dimension(110, 25);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        panInfo.add(createLabel("Chassis:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panInfo.add(chassis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panInfo.add(createLabel("Model:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panInfo.add(model, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panInfo.add(createLabel("Year:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panInfo.add(era, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panInfo.add(createLabel("Source/Era:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panInfo.add(source, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panInfo.add(createLabel("Tech:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panInfo.add(techType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panInfo.add(createLabel("Tech Level:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panInfo.add(techLevel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panInfo.add(createLabel("Manual BV:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panInfo.add(manualBV, gbc);

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
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Troop Storage:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        panChassis.add(troopStorage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panArmor.add(createLabel("Armor Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panArmor.add(armorCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panArmor.add(createLabel("Armor Tonnage:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panArmor.add(armorTonnage, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panArmor.add(maximizeArmorButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panArmor.add(unusedTonnageArmorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panMovement.add(createLabel("Cruise MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(cruiseMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panMovement.add(createLabel("Flank MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panMovement.add(flankMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panMovement.add(createLabel("Jumping MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panMovement.add(jumpMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;

        Dimension comboSize = new Dimension(180, 25);

        setFieldSize(era, comboSize);
        setFieldSize(manualBV, comboSize);
        setFieldSize(source, comboSize);
        setFieldSize(techType, comboSize);
        setFieldSize(armorCombo, comboSize);
        setFieldSize(techLevel, comboSize);
        setFieldSize(turretCombo, comboSize);
        setFieldSize(engineType, comboSize);
        setFieldSize(model, comboSize);
        setFieldSize(chassis, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
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

        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armor.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));
    }

    public void refresh() {
        removeAllListeners();
        omniCB.setSelected(getTank().isOmni());
        superHeavyCB.setSelected((getTank()).isSuperHeavy());

        if (!getTank().hasNoDualTurret()) {
            turretCombo.setSelectedIndex(2);
        } else if (!getTank().hasNoTurret()) {
            turretCombo.setSelectedIndex(1);
        } else {
            turretCombo.setSelectedIndex(0);
        }

        era.setText(Integer.toString(getTank().getYear()));
        source.setText(getTank().getSource());
        manualBV.setText(Integer.toString(Math.max(0, getTank().getManualBV())));

        if (getTank().isMixedTech()) {
            if (getTank().isClan()) {

                techType.setSelectedIndex(3);
                if (getTank().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getTank().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getTank().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getTank().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getTank().isClan()) {

            techType.setSelectedIndex(1);
            if (getTank().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getTank().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getTank().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getTank().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getTank().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getTank().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getTank().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

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
            default:
                break;
        }

        setAvailableWeights((int) getTank().getWeight());

        cruiseMP.getModel().setValue(getTank().getWalkMP());
        flankMP.setText(getTank().getRunMPasString());

        updateEngineTypes(getTank().isClan());
        updateTroopSpaceAllowed();

        engineType.setSelectedIndex(convertEngineType(getTank().getEngine()
                .getEngineType()));
        if (getTank().hasPatchworkArmor()) {
            setArmorCombo(EquipmentType.T_ARMOR_PATCHWORK);
        } else {
            setArmorCombo(getTank().getArmorType(1));
        }
        double maxArmorTonnage = UnitUtil.getMaximumArmorTonnage(getTank());
        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(maxArmorTonnage);
        ((SpinnerNumberModel) armorTonnage.getModel()).setValue(Math.min(
                UnitUtil.getMaximumArmorTonnage(getTank()),
                getTank().getLabArmorTonnage()));
        if (getTank().hasPatchworkArmor()) {
            TestTank testTank = new TestTank(
                    getTank(),
                    EntityVerifier.getInstance(new File(
                            "data/mechfiles/UnitVerifierOptions.xml")).tankOption,
                    null);
            armorTonnage.setEnabled(false);
            armorTonnage.getModel()
                    .setValue(testTank.getWeightAllocatedArmor());
            getTank().setArmorTonnage(testTank.getWeightAllocatedArmor());
            maximizeArmorButton.setEnabled(false);
            unusedTonnageArmorButton.setEnabled(false);
        } else {
            if (Double.parseDouble(armorTonnage.getValue().toString()) > maxArmorTonnage) {
                armorTonnage.setValue(maxArmorTonnage);
            }
            setArmorTonnage();
            armorTonnage.setEnabled(true);
            maximizeArmorButton.setEnabled(true);
            unusedTonnageArmorButton.setEnabled(true);
        }
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
                }
            }
        }
        else if (e.getSource() instanceof JButton) {
            if (e.getSource().equals(maximizeArmorButton)) {
                maximizeArmor();
            } else if (e.getSource().equals(unusedTonnageArmorButton)) {
                useRemainingTonnageArmor();
            }
        }
        addAllListeners();
        refresh.refreshAll();
    }

    public void removeAllListeners() {
        engineType.removeItemListener(this);
        weight.removeChangeListener(this);
        cruiseMP.removeChangeListener(this);
        techLevel.removeItemListener(this);
        techType.removeItemListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        superHeavyCB.removeActionListener(this);
        turretCombo.removeItemListener(this);
        tankMotiveType.removeItemListener(this);
        troopStorage.removeChangeListener(this);
        maximizeArmorButton.removeActionListener(this);
        unusedTonnageArmorButton.removeActionListener(this);
        armorTonnage.removeChangeListener(this);
        baseChassisTurretWeight.removeChangeListener(this);
        baseChassisTurret2Weight.removeChangeListener(this);
        armorCombo.removeItemListener(this);
        jumpMP.removeChangeListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
    }

    public void addAllListeners() {
        engineType.addItemListener(this);
        weight.addChangeListener(this);
        cruiseMP.addChangeListener(this);
        techLevel.addItemListener(this);
        techType.addItemListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        superHeavyCB.addActionListener(this);
        turretCombo.addItemListener(this);
        tankMotiveType.addItemListener(this);
        troopStorage.addChangeListener(this);
        maximizeArmorButton.addActionListener(this);
        unusedTonnageArmorButton.addActionListener(this);
        armorTonnage.addChangeListener(this);
        baseChassisTurretWeight.addChangeListener(this);
        baseChassisTurret2Weight.addChangeListener(this);
        armorCombo.addItemListener(this);
        jumpMP.addChangeListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                int year = Integer.parseInt(era.getText());
                if (year < 1950) {
                    return;
                }
                getTank().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getTank().setYear(3145);
            }
            removeAllListeners();
            updateArmor();
            addAllListeners();
            refresh.refreshEquipment();
        } else if (e.getSource().equals(source)) {
            getTank().setSource(source.getText());
        } else if (e.getSource().equals(manualBV)) {
            int bv = Integer.parseInt(manualBV.getText());
            if (bv <= 0) {
                getTank().setUseManualBV(false);
                getTank().setManualBV(0);
            } else {
                getTank().setUseManualBV(true);
                getTank().setManualBV(bv);
            }
        } else if (e.getSource().equals(chassis)) {
            getTank().setChassis(chassis.getText().trim());
            refresh.refreshPreview();
            refresh.refreshHeader();
        } else if (e.getSource().equals(model)) {
            getTank().setModel(model.getText().trim());
            refresh.refreshPreview();
            refresh.refreshHeader();
        }
    }

    public void keyTyped(KeyEvent e) {
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

        if (troops > (int) getTank().getWeight()) {
            getTank().removeAllTransporters();
            ((SpinnerNumberModel) troopStorage.getModel()).setValue(0);
        } else {
            ((SpinnerNumberModel) troopStorage.getModel())
                    .setValue((double) troops);
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
                    disableJump();
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
                    disableJump();
                    break;
            }
        }

        currentTonnage = Math.max(minTonnage,
                Math.min(currentTonnage, maxTonnage));
        getTank().setWeight(currentTonnage);
        ((SpinnerNumberModel) troopStorage.getModel())
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
        if (e.getSource().equals(cruiseMP) || e.getSource().equals(weight)) {
            updateWeightEngineMovement();
        }
        if (e.getSource().equals(troopStorage)) {
            getTank().removeAllTransporters();
            if (((SpinnerNumberModel) troopStorage.getModel()).getNumber()
                    .doubleValue() > 0) {
                double troopTons = Math
                        .round(((Double) troopStorage.getValue()) * 2) / 2.0;
                getTank().addTransporter(new TroopSpace(troopTons));
            }
        } else if (e.getSource().equals(armorTonnage)) {
            setArmorTonnage();
        } else if (e.getSource().equals(jumpMP)) {
            setJumpMP();
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

    private void setArmorCombo(int type) {
        armorCombo.setSelectedIndex(-1);

        for (int index = 0; index < armorCombo.getItemCount(); index++) {
            if (getTank().isMixedTech()) {
                if (EquipmentType.getArmorTypeName(type,TechConstants.isClan(getTank().getArmorTechLevel(0))).equals(armorCombo.getItemAt(index))) {
                    armorCombo.setSelectedIndex(index);
                }
            } else {
                if (EquipmentType.getArmorTypeName(type).equals(armorCombo.getItemAt(index))) {
                    armorCombo.setSelectedIndex(index);
                }
            }
        }
    }


    private String getArmorType(JComboBox<String> combo) {
        String armorType = combo.getSelectedItem().toString();
        if (armorType.equals(EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK))) {
            return armorType;
        }
        if (!getTank().isMixedTech()) {
            String prefix = getTank().isClan() ? "Clan " : "IS ";
            for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
                if (armorType.equals(EquipmentType.armorNames[pos])) {
                    return prefix+armorType;
                }
            }
        } else {
            for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
                if (armorType.equals(EquipmentType.getArmorTypeName(pos, true))) {
                    return armorType;
                }
                if (armorType.equals(EquipmentType.getArmorTypeName(pos, false))) {
                    return armorType;
                }
            }
        }

        return EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_STANDARD, getTank().isClan());
    }

    private void updateWeightEngineMovement() {

        // we first need to set the weight, because the tank's
        // suspension factor
        // depends on the weight, and is needed for the engine
        // rating
    	double oldWeight = getTank().getWeight();
        getTank().setWeight(Double.parseDouble(weight.getModel().getValue().toString()));
        int rating = Math
                .max(10,
                        (Integer.parseInt(cruiseMP.getModel().getValue()
                                .toString()) * Integer.parseInt(weight
                                .getModel().getValue().toString()))
                                - (getTank()).getSuspensionFactor());
        if (rating > 500) {
            // if we changed weight, reset the weight if the rating is too high
            if (oldWeight < getTank().getWeight()) {
                getTank().setWeight(oldWeight);
                weight.getModel().setValue((int)oldWeight);
            } else {
                // otherwise, set the cruiseMP spinner to the old value
                cruiseMP.getModel().setValue(getTank().getOriginalWalkMP());
            }
            JOptionPane
                    .showMessageDialog(
                            this,
                            "That speed/weight combination would create an engine with a rating over 500.",
                            "Bad Engine Rating", JOptionPane.ERROR_MESSAGE);
        } else {
            getTank().setWeight(Double.parseDouble(weight.getModel().getValue().toString()));
            ((SpinnerNumberModel) troopStorage.getModel()).setMaximum(Double
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
            getTank().setOriginalWalkMP(
                    (Integer) cruiseMP.getModel().getValue());
        }
    }

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getTank());
        armorTonnage.setValue(maxArmor);
        getTank().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
    }
    
    private void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getTank())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getTank());
        double totalTonnage = getTank().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getTank()));
        armorTonnage.setValue(maxArmor);
        getTank().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();        
    }    

    private void setArmorTonnage() {
        double armorTons = Math.round(((Double) armorTonnage.getValue()) * 2) / 2.0;
        getTank().setArmorTonnage(armorTons);
        armor.resetArmorPoints();
    }

    private void setArmorType() {
        UnitUtil.removeISorArmorMounts(getTank(), false);
        if (getArmorType(armorCombo) == EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK)) {
            JComboBox<String> frontArmor = new JComboBox<String>();
            frontArmor.setName("FR");
            JComboBox<String> leftArmor = new JComboBox<String>();
            leftArmor.setName((getTank() instanceof SuperHeavyTank?"FRLS":"LS"));
            JComboBox<String> rightArmor = new JComboBox<String>();
            rightArmor.setName((getTank() instanceof SuperHeavyTank?"FRRS":"RS"));
            JComboBox<String> rearArmor = new JComboBox<String>();
            rearArmor.setName("RR");
            JComboBox<String> turretArmor = new JComboBox<String>();
            turretArmor.setName("TU");
            JComboBox<String> turret2Armor = new JComboBox<String>();
            turret2Armor.setName("TU2");
            JComboBox<String> rearLeftArmor = new JComboBox<String>();
            rearLeftArmor.setName("RRLS");
            JComboBox<String> rearRightArmor = new JComboBox<String>();
            rearRightArmor.setName("RRRS");
            JComboBox<String> rotorArmor = new JComboBox<String>();
            rotorArmor.setName("RO");
            boolean isMixed = getTank().isMixedTech();
            boolean isClan = getTank().isClan();
            for (int index = 0; index < (EquipmentType.armorNames.length); index++) {
                EquipmentType et;
                if (!isMixed) {
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(index, isClan));
                    if ((et != null) && et.hasFlag(MiscType.F_TANK_EQUIPMENT) && (TechConstants.isLegal(getTank().getTechLevel(), et.getTechLevel(getTank().getYear()), isMixed))) {
                        if (et.hasFlag(MiscType.F_HARDENED_ARMOR)
                                && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                                || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                                || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                            continue;
                        }
                        frontArmor.addItem(EquipmentType.armorNames[index]);
                        leftArmor.addItem(EquipmentType.armorNames[index]);
                        rightArmor.addItem(EquipmentType.armorNames[index]);
                        rearArmor.addItem(EquipmentType.armorNames[index]);
                        turretArmor.addItem(EquipmentType.armorNames[index]);
                        turret2Armor.addItem(EquipmentType.armorNames[index]);
                        rearLeftArmor.addItem(EquipmentType.armorNames[index]);
                        rearRightArmor.addItem(EquipmentType.armorNames[index]);
                        rotorArmor.addItem(EquipmentType.armorNames[index]);
                    }
                } else {
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(index, true));
                    if (et != null) {
                        if (et.hasFlag(MiscType.F_HARDENED_ARMOR)
                                && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                                || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                                || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                            continue;
                        }
                        frontArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        leftArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        rightArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        rearArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        turretArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        turret2Armor.addItem(EquipmentType.getArmorTypeName(index, true));
                        rearLeftArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        rearRightArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                        rotorArmor.addItem(EquipmentType.getArmorTypeName(index, true));
                    }
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(index, false));
                    if (et != null) {
                        if (et.hasFlag(MiscType.F_HARDENED_ARMOR)
                                && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                                || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                                || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                            continue;
                        }
                        frontArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        leftArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        rightArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        rearArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        turretArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        turret2Armor.addItem(EquipmentType.getArmorTypeName(index, false));
                        rearLeftArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        rearRightArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                        rotorArmor.addItem(EquipmentType.getArmorTypeName(index, false));
                    }
                }
            }
            setArmorType(frontArmor, getTank().getArmorType(Tank.LOC_FRONT), false);
            setArmorType(leftArmor, getTank().getArmorType(getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT), false);
            setArmorType(rightArmor, getTank().getArmorType(getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT), false);
            setArmorType(rearArmor, getTank().getArmorType(getTank() instanceof SuperHeavyTank?Tank.LOC_REAR:Tank.LOC_REAR), false);
            if (!getTank().hasNoTurret()) {
                setArmorType(turretArmor, getTank().getLocTurret(), false);
            }
            if (!getTank().hasNoDualTurret()) {
                setArmorType(turret2Armor, getTank().getArmorType(getTank().getLocTurret2()), false);
            }
            if (getTank() instanceof SuperHeavyTank) {
                setArmorType(rearLeftArmor, getTank().getArmorType(SuperHeavyTank.LOC_REARLEFT), false);
                setArmorType(rearRightArmor, getTank().getArmorType(SuperHeavyTank.LOC_REARRIGHT), false);
            }
            if (getTank() instanceof VTOL) {
                setArmorType(rotorArmor, getTank().getArmorType(VTOL.LOC_ROTOR), false);
            }

            JLabel frontLabel = new JLabel("Front:");
            JLabel leftLabel = new JLabel(getTank() instanceof SuperHeavyTank ? "Front Left:"
                    : "Left:");
            JLabel righttLabel = new JLabel(getTank() instanceof SuperHeavyTank ? "Front Right:"
                    : "Right:");
            JLabel rearLabel = new JLabel("Rear:");
            JLabel turretLabel = new JLabel("Turret:");
            JLabel turret2Label = new JLabel("Front Turret:");
            JLabel rearLeftLabel = new JLabel("Rear Left:");
            JLabel rearRightLabel = new JLabel("Rear Right:");
            JLabel rotorLabel = new JLabel("Rotor:");
            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(frontLabel, GBC.std());
            panel.add(frontArmor, GBC.eol());
            panel.add(leftLabel, GBC.std());
            panel.add(leftArmor, GBC.eol());
            panel.add(righttLabel, GBC.std());
            panel.add(rightArmor, GBC.eol());
            panel.add(rearLabel, GBC.std());
            panel.add(rearArmor, GBC.eol());
            if (!getTank().hasNoTurret()) {
                panel.add(turretLabel, GBC.std());
                panel.add(turretArmor, GBC.eol());
            }
            if (!getTank().hasNoDualTurret()) {
                panel.add(turret2Label, GBC.std());
                panel.add(turret2Armor, GBC.eol());
            }
            if (getTank() instanceof SuperHeavyTank) {
                panel.add(rearLeftLabel, GBC.std());
                panel.add(rearLeftArmor, GBC.eol());
                panel.add(rearRightLabel, GBC.std());
                panel.add(rearRightArmor, GBC.eol());
            }
            if (getTank() instanceof VTOL) {
                panel.add(rotorLabel, GBC.std());
                panel.add(rotorArmor, GBC.eol());
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            getTank().setArmorTechLevel(EquipmentType.get(getArmorType(frontArmor)).getTechLevel(getTank().getYear()), Tank.LOC_FRONT);
            getTank().setArmorType(getArmorType(frontArmor), Tank.LOC_FRONT);
            getTank().setArmorTechLevel(EquipmentType.get(getArmorType(leftArmor)).getTechLevel(getTank().getYear()), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT);
            getTank().setArmorType(getArmorType(leftArmor), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT);
            getTank().setArmorTechLevel(EquipmentType.get(getArmorType(rightArmor)).getTechLevel(getTank().getYear()), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT);
            getTank().setArmorType(getArmorType(rightArmor), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT);
            getTank().setArmorTechLevel(EquipmentType.get(getArmorType(rearArmor)).getTechLevel(getTank().getYear()), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_REAR:Tank.LOC_REAR);
            getTank().setArmorType(getArmorType(rearArmor), getTank() instanceof SuperHeavyTank?SuperHeavyTank.LOC_REAR:Tank.LOC_REAR);
            if (!getTank().hasNoTurret()) {
                getTank().setArmorTechLevel(EquipmentType.get(getArmorType(turretArmor)).getTechLevel(getTank().getYear()), getTank().getLocTurret());
                getTank().setArmorType(getArmorType(turretArmor), getTank().getLocTurret());
            }
            if (!getTank().hasNoDualTurret()) {
                getTank().setArmorTechLevel(EquipmentType.get(getArmorType(turret2Armor)).getTechLevel(getTank().getYear()), getTank().getLocTurret2());
                getTank().setArmorType(getArmorType(turret2Armor), getTank().getLocTurret2());
            }
            if (getTank() instanceof SuperHeavyTank) {
                getTank().setArmorTechLevel(EquipmentType.get(getArmorType(rearLeftArmor)).getTechLevel(getTank().getYear()), SuperHeavyTank.LOC_REARLEFT);
                getTank().setArmorType(getArmorType(rearLeftArmor), SuperHeavyTank.LOC_REARLEFT);
                getTank().setArmorTechLevel(EquipmentType.get(getArmorType(rearRightArmor)).getTechLevel(getTank().getYear()), SuperHeavyTank.LOC_REARRIGHT);
                getTank().setArmorType(getArmorType(rearRightArmor), SuperHeavyTank.LOC_REARRIGHT);

            }
            if (getTank() instanceof VTOL) {
                getTank().setArmorTechLevel(EquipmentType.get(getArmorType(rotorArmor)).getTechLevel(getTank().getYear()), VTOL.LOC_ROTOR);
                getTank().setArmorType(getArmorType(rotorArmor), VTOL.LOC_ROTOR);
            }

            if (!getTank().hasPatchworkArmor()) {
                setArmorType(armorCombo, EquipmentType.T_ARMOR_STANDARD, false);
            }
        } else {
            getTank().setArmorTechLevel(EquipmentType.get(getArmorType(armorCombo))
                    .getTechLevel(getTank().getYear()));
            getTank().setArmorType(getArmorType(armorCombo));
        }
    }

    @SuppressWarnings("unused")
    private void TEMP() {
        int oldIndex = armorCombo.getSelectedIndex();
        setArmorCombo(oldIndex);
    }

    private void setArmorType(JComboBox<String> combo, int type, boolean removeListeners) {
        if (removeListeners) {
            removeAllListeners();
        }
        for (int index = 0; index < combo.getItemCount(); index++) {
            if (EquipmentType.getArmorTypeName(type).equals(combo.getItemAt(index))) {
                armorCombo.setSelectedIndex(index);
            }
        }
        if (removeListeners) {
            addAllListeners();
        }
    }

    public void setArmorType(int type) {
        setArmorType(armorCombo, type, true);
    }

    private void updateArmor() {
        boolean isClan = getTank().isClan();
        boolean isMixed = getTank().isMixedTech();
        boolean isExperimental = (getTank().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getTank().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getTank().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getTank().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);

        /* ARMOR */
        armorCombo.removeAllItems();
        for (int index = 0; index < (EquipmentType.armorNames.length); index++) {
            EquipmentType et;
            if (!isMixed) {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, isClan));

                if (((index == EquipmentType.T_ARMOR_PATCHWORK) && isExperimental) || ((et != null) && et.hasFlag(MiscType.F_TANK_EQUIPMENT) && (TechConstants.isLegal(getTank().getTechLevel(), et.getTechLevel(getTank().getYear()), isMixed)))) {
                    if ((et != null) && et.hasFlag(MiscType.F_HARDENED_ARMOR)
                            && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                            || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                            || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                        continue;
                    }
                    armorCombo.addItem(EquipmentType.armorNames[index]);
                }
            } else {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, true));
                if (et != null && et.hasFlag(MiscType.F_TANK_EQUIPMENT) && TechConstants
                        .isLegal(getTank().getTechLevel(), et
                                .getTechLevel(getTank().getYear()),
                                isMixed)) {
                    if (et.hasFlag(MiscType.F_HARDENED_ARMOR)
                            && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                            || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                            || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                        continue;
                    }
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index, true));
                }
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, false));
                if (et != null && et.hasFlag(MiscType.F_TANK_EQUIPMENT) && TechConstants
                        .isLegal(getTank().getTechLevel(), et
                                .getTechLevel(getTank().getYear()),
                                isMixed)) {
                    if (et.hasFlag(MiscType.F_HARDENED_ARMOR)
                            && ((getTank().getMovementMode() == EntityMovementMode.HOVER)
                            || (getTank().getMovementMode() == EntityMovementMode.WIGE)
                            || (getTank().getMovementMode() == EntityMovementMode.VTOL))) {
                        continue;
                    }
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index, false));
                }
                if (index == EquipmentType.T_ARMOR_PATCHWORK) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index));
                }
            }
        }
    }

    private void setJumpMP() {
        int jump = Integer.parseInt(jumpMP.getModel().getValue().toString());
        UnitUtil.removeAllMiscMounteds(getTank(), MiscType.F_JUMP_JET);
        getTank().setOriginalJumpMP(0);
        for (int i = 0; i < jump; i++) {
            try {
                getTank().addEquipment(EquipmentType.get("JumpJet"), Tank.LOC_BODY);
            } catch (LocationFullException e) {
                e.printStackTrace();
            }
        }
    }

    private void disableJump() {
        jumpMP.setEnabled(false);
        jumpMP.getModel().setValue(0);
        setJumpMP();
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
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (getTank().isClan()) {
                        switch (unitTechLevel) {
                            case 0:
                                getTank().setTechLevel(TechConstants.T_CLAN_TW);
                                getTank().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                disableJump();
                                addAllListeners();
                                techType.setSelectedIndex(1);
                                removeAllListeners();
                                break;
                            case 1:
                                getTank().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                                getTank().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                                disableJump();
                                break;
                            case 2:
                                getTank().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                getTank().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                jumpMP.setEnabled(true);
                                break;
                            case 3:
                                getTank().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                getTank().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                jumpMP.setEnabled(true);
                                break;
                            default:
                                getTank().setTechLevel(TechConstants.T_CLAN_TW);
                                getTank().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                disableJump();
                                break;
                        }

                    } else {
                        switch (unitTechLevel) {
                            case 0:
                                getTank().setTechLevel(TechConstants.T_INTRO_BOXSET);
                                getTank().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                                disableJump();
                                addAllListeners();
                                techType.setSelectedIndex(0);
                                removeAllListeners();
                                break;
                            case 1:
                                getTank().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                getTank().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                disableJump();
                                addAllListeners();
                                techType.setSelectedIndex(0);
                                removeAllListeners();
                                break;
                            case 2:
                                getTank().setTechLevel(TechConstants.T_IS_ADVANCED);
                                getTank().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                                disableJump();
                                break;
                            case 3:
                                getTank().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                getTank().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                jumpMP.setEnabled(true);
                                break;
                            default:
                                getTank().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                getTank().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                jumpMP.setEnabled(true);
                                break;
                        }

                    }

                    int engineNumber = engineType.getSelectedIndex();
                    updateEngineTypes(getTank().isClan());
                    // Reset the engine
                    if (engineNumber >= engineType.getItemCount()) {
                        engineType.setSelectedIndex(0);
                    } else {
                        engineType.setSelectedIndex(engineNumber);
                    }
                    updateArmor();
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1)
                            && (!getTank().isClan() || getTank().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        if (!(getTank()).isSuperHeavy()) {
                            getTank().setTechLevel(TechConstants.T_CLAN_TW);
                        } else {
                            if (getTank().getTechLevel() == TechConstants.T_IS_ADVANCED) {
                                getTank().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            } else if (getTank().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL) {
                                getTank().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            } else if (getTank().getTechLevel() == TechConstants.T_IS_UNOFFICIAL) {
                                getTank().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            }
                        }

                        getTank().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getTank().setMixedTech(false);
                    } else if ((techType.getSelectedIndex() == 0)
                            && (getTank().isClan() || getTank().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }
                        if (!(getTank()).isSuperHeavy()) {
                            getTank().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        } else {
                            if (getTank().getTechLevel() == TechConstants.T_CLAN_ADVANCED) {
                                getTank().setTechLevel(TechConstants.T_IS_ADVANCED);
                            } else if (getTank().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL) {
                                getTank().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            } else if (getTank().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL) {
                                getTank().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            }
                        }
                        getTank().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getTank().setMixedTech(false);

                    } else if ((techType.getSelectedIndex() == 2)
                            && (!getTank().isMixedTech() || getTank().isClan())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        if (getTank().getYear() < 3090) {
                            //before 3090, mixed tech is experimental
                            if ((getTank().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                                getTank().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            }
                        } else if (getTank().getYear() < 3145) {
                            // between 3090 and 3145, mixed tech is advanced
                            if ((getTank().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getTank().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                                getTank().setTechLevel(TechConstants.T_IS_ADVANCED);
                            }
                        } else {
                            // from 3145 on, mixed tech is tourney legal
                            if ((getTank().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getTank().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getTank().getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                                getTank().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            }
                        }
                        getTank().setMixedTech(true);

                    } else if ((techType.getSelectedIndex() == 3)
                            && (!getTank().isMixedTech() || !getTank().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }
                        if (getTank().getYear() < 3090) {
                            //before 3090, mixed tech is experimental
                            if ((getTank().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                                getTank().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            }
                        } else if (getTank().getYear() < 3145) {
                            // between 3090 and 3145, mixed tech is advanced
                            if ((getTank().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getTank().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                                getTank().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            }
                        } else {
                            // from 3145 on, mixed tech is tourney legal
                            if ((getTank().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getTank().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getTank().getTechLevel() != TechConstants.T_CLAN_TW)) {
                                getTank().setTechLevel(TechConstants.T_CLAN_TW);
                            }
                        }
                        getTank().setMixedTech(true);
                    }
                    int engineNumber = engineType.getSelectedIndex();
                    updateEngineTypes(getTank().isClan());

                    // Reset the engine
                    if (engineNumber >= engineType.getItemCount()) {
                        engineType.setSelectedIndex(0);
                    } else {
                        engineType.setSelectedIndex(engineNumber);
                    }
                    updateArmor();
                } else if (combo.equals(tankMotiveType)) {
                    int currentTonnage = (Integer) weight.getModel().getValue();
                    setAvailableWeights(currentTonnage);
                    updateArmor();
                    updateWeightEngineMovement();
                } else if (combo.equals(turretCombo)) {
                    updateTurrets(combo);
                } else if (combo.equals(armorCombo)) {
                    if (!getTank().hasPatchworkArmor()) {
                        armor.resetArmorPoints();
                    }
                    setArmorType();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            addAllListeners();
            refresh.refreshAll();
        }
    }

}
