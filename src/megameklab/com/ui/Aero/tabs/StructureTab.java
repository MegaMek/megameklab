/*
 * MegaMekLab - Copyright (C) 2008
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

package megameklab.com.ui.Aero.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import megamek.common.Aero;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Aero.views.ArmorView;
import megameklab.com.ui.Aero.views.SummaryView;
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
    private static final String ENGINEXXL = "XXL";
    private static final String ENGINELIGHT = "Light";
    private static final String ENGINECOMPACT = "Compact";


    String[] isEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINEXXL, ENGINELIGHT,
            ENGINECOMPACT};
    String[] isIndustrialEngineTypes = { ENGINESTANDARD};
    String[] clanEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINEXXL};
    String[] clanIndustrialEngineTypes = { ENGINESTANDARD};
    private int clanEngineFlag = 0;

    String[] heatSinkTypes = { "Single", "Double"};

    String[] techTypes = { "Inner Sphere", "Clan", "Mixed Inner Sphere",
    "Mixed Clan" };

    String[] isTechLevels = { "Introductory", "Standard", "Advanced",
            "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental",
            "Unoffical" };

    String[]  craftTypes = {"Aerospace Fighter"};

    private JPanel masterPanel;
    private JPanel panInfo;
    private JPanel panChassis;
    private JPanel panArmor;
    private JPanel panMovement;
    private JPanel panFuel;
    private JPanel panHeat;
    private SummaryView panSummary;
    private ArmorView armorView;

    RefreshListener refresh = null;

    // Basic Info Panel
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);
    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);
    private JComboBox<String> techType = new JComboBox<String>(techTypes);
    private JComboBox<String> techLevel = new JComboBox<String>(isTechLevels);

    private JTextField manualBV = new JTextField(3);

    // Chassis Panel
    private JSpinner weightClass;
    private JSpinner structuralIntegrity;
    private JCheckBox hasVSTOL = new JCheckBox("VSTOL");
    private JComboBox<String> unitType  = new JComboBox<String>(craftTypes);
    private JComboBox<String> engineType = new JComboBox<String>(isEngineTypes);
    private JComboBox<String> cockpitType =
            new JComboBox<String>(Aero.COCKPIT_SHORT_STRING);
    private JCheckBox omniCB = new JCheckBox("Omni");

    // Movement Panel
    private JSpinner safeThrust;
    private JTextField maxThrust;

    // Fuel Panel
    private JSpinner fuel;
    private JLabel fuelPoints;
    private JLabel turnsAtSafe;
    private JLabel turnsAtMax;
    //private JLabel burnDays1G;
    //private JLabel burnDaysMax;


    // Heat Sinks Panel
    private JComboBox<String> heatSinkType =
            new JComboBox<String>(heatSinkTypes);
    private JSpinner heatSinkNumber;
    private JSpinner baseChassisHeatSinks;

    // Armor Panel
    private JComboBox<String> armorCombo = new JComboBox<String>();
    private JSpinner armorTonnage;
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JButton unusedTonnageArmorButton = new JButton("Use Remaining Tonnage");


    public StructureTab(EntitySource eSource) {
        super(eSource);
        armorView = new ArmorView(eSource);
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        populateChoices(false);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new JPanel(new GridBagLayout());
        panChassis = new JPanel(new GridBagLayout());
        panArmor = new JPanel(new GridBagLayout());
        panMovement = new JPanel(new GridBagLayout());
        panFuel = new JPanel(new GridBagLayout());
        panHeat = new JPanel(new GridBagLayout());
        panSummary = new SummaryView(eSource);

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(55, 25);

        safeThrust = new JSpinner(new SpinnerNumberModel(1, 1, 25, 1));
        ((JSpinner.DefaultEditor) safeThrust.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) safeThrust.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) safeThrust.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) safeThrust.getEditor())
                .setMinimumSize(spinnerSize);

        maxThrust = new JTextField();
        maxThrust.setEditable(false);
        setFieldSize(maxThrust, spinnerSize);
        maxThrust.setHorizontalAlignment(SwingConstants.RIGHT);

        weightClass = new JSpinner(new SpinnerNumberModel(20, 10, 100, 5));

        structuralIntegrity = new JSpinner(new SpinnerNumberModel(3, 1, 100, 1));
        ((JSpinner.DefaultEditor) structuralIntegrity.getEditor())
                .getTextField().setEditable(false);
        structuralIntegrity.setEnabled(false);

        hasVSTOL.setToolTipText("Very short take-off and landing");
        hasVSTOL.setSelected(true);
        hasVSTOL.setEnabled(false);

        fuel = new JSpinner(new SpinnerNumberModel(1.0, 0.0,
                ((Integer)weightClass.getValue()).doubleValue(), 0.5));
        ((JSpinner.DefaultEditor) fuel.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setMinimumSize(spinnerSize);

        fuelPoints = new JLabel("0");
        turnsAtSafe = new JLabel("0", JLabel.CENTER);
        turnsAtMax = new JLabel("0", JLabel.CENTER);
        //burnDays1G = new JLabel("0", JLabel.CENTER);
        //burnDaysMax = new JLabel("0", JLabel.CENTER);

        heatSinkNumber = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        spinnerSize = new Dimension(40, 25);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setMinimumSize(spinnerSize);

        baseChassisHeatSinks = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMinimumSize(spinnerSize);

        armorTonnage = new JSpinner(new SpinnerNumberModel(
                getAero().getArmorWeight(), 0.0, UnitUtil.getMaximumArmorTonnage(getAero()), 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMinimumSize(spinnerSize);


        chassis.setText(getAero().getChassis());
        model.setText(getAero().getModel());

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
        panChassis.add(weightClass, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panChassis.add(omniCB, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panChassis.add(createLabel("SI:", labelSize),gbc);
        gbc.gridx = 1;
        panChassis.add(structuralIntegrity, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panChassis.add(hasVSTOL,gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panChassis.add(createLabel("Unit Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panChassis.add(unitType, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Engine Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panChassis.add(engineType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Cockpit Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panChassis.add(cockpitType, gbc);


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
        panMovement.add(createLabel("Safe Thrust:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(safeThrust, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panMovement.add(createLabel("Max Thrust:", labelSize), gbc);
        gbc.gridx = 1;
        panMovement.add(maxThrust, gbc);


        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panFuel.add(createLabel("Fuel Tons:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        panFuel.add(fuel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panFuel.add(createLabel("Fuel Points:", labelSize), gbc);
        gbc.gridx = 3;
        fuelPoints.setSize(labelSize);
        gbc.insets = new Insets(0,10,0,20);
        panFuel.add(fuelPoints, gbc);
        gbc.insets = new Insets(0,0,0,0);


        JPanel fuelInfoPanel = new JPanel(new GridLayout(0,2));

        JLabel lblTurnsAtSafe =
                new JLabel("Turns at Safe", JLabel.CENTER);
        JLabel lblTurnsAtMax =
                new JLabel("Turns at Max", JLabel.CENTER);
        fuelInfoPanel.add(lblTurnsAtSafe);
        fuelInfoPanel.add(lblTurnsAtMax);

        fuelInfoPanel.add(turnsAtSafe);
        fuelInfoPanel.add(turnsAtMax);

        /*
        JLabel lbl1GBurnDays =
                new JLabel("1G Burn Days", JLabel.CENTER);
        JLabel lblMaxBurnDays =
                new JLabel("Max Burn Days", JLabel.CENTER);
        fuelInfoPanel.add(lbl1GBurnDays);
        fuelInfoPanel.add(lblMaxBurnDays);

        fuelInfoPanel.add(burnDays1G);
        fuelInfoPanel.add(burnDaysMax);
        */


        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        panFuel.add(fuelInfoPanel, gbc);



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panHeat.add(createLabel("Sink Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panHeat.add(heatSinkType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panHeat.add(createLabel("Number:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panHeat.add(heatSinkNumber, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panHeat.add(createLabel("Base (Omni):", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panHeat.add(baseChassisHeatSinks, gbc);

        Dimension comboSize = new Dimension(180, 25);

        setFieldSize(era, comboSize);
        setFieldSize(manualBV, comboSize);
        setFieldSize(source, comboSize);
        setFieldSize(techType, comboSize);
        setFieldSize(armorCombo, comboSize);
        setFieldSize(techLevel, comboSize);
        setFieldSize(heatSinkType, comboSize);
        setFieldSize(engineType, comboSize);
        setFieldSize(unitType, comboSize);
        setFieldSize(cockpitType, comboSize);
        setFieldSize(model, comboSize);
        setFieldSize(chassis, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
        leftPanel.add(panChassis);
        leftPanel.add(panHeat);
        //leftPanel.add(Box.createGlue());
        //leftPanel.add(Box.createVerticalGlue());

        midPanel.add(panMovement);
        midPanel.add(panFuel);
        midPanel.add(panSummary);
        midPanel.add(Box.createHorizontalStrut(300));

        rightPanel.add(panArmor);
        rightPanel.add(armorView);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        masterPanel.add(midPanel, gbc);
        gbc.gridx = 2;
        masterPanel.add(rightPanel, gbc);

        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panFuel.setBorder(BorderFactory.createTitledBorder("Fuel"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armorView.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));

    }

    public void refresh() {
        removeAllListeners();
        if (getAero().isPrimitive()) {
            getAero().setOmni(false);
            omniCB.setEnabled(false);
        } else {
            omniCB.setEnabled(true);
        }
        omniCB.setSelected(getAero().isOmni());

        fuelPoints.setText(getAero().getFuel()+"");
        turnsAtSafe.setText(String.format(
                "%1$.2f", TestAero.calculateMaxTurnsAtSafe(getAero())));
        turnsAtMax.setText(String.format(
                "%1$.2f", TestAero.calculateMaxTurnsAtMax(getAero())));

        era.setText(Integer.toString(getAero().getYear()));
        source.setText(getAero().getSource());
        manualBV.setText(Integer.toString(Math.max(0, getAero().getManualBV())));
        weightClass.setValue((int) (getAero().getWeight()));

        int totalSinks = getAero().getHeatSinks();
        int freeSinks = getAero().getEngine().getWeightFreeEngineHeatSinks();
        ((SpinnerNumberModel) heatSinkNumber.getModel()).setMinimum(freeSinks);
        heatSinkNumber.setValue(totalSinks);

        ((SpinnerNumberModel) baseChassisHeatSinks.getModel())
                .setMaximum(getAero().getEngine().integralHeatSinkCapacity(
                       false));

        baseChassisHeatSinks.setValue(Math.max(0, getAero().getEngine()
                .getBaseChassisHeatSinks(false)));

        if (getAero().isOmni()) {
            baseChassisHeatSinks.setEnabled(true);
            getAero().getEngine().setBaseChassisHeatSinks(
                    Math.max(0, (Integer) baseChassisHeatSinks.getValue()));
        } else {
            baseChassisHeatSinks.setEnabled(false);
            getAero().getEngine().setBaseChassisHeatSinks(-1);
        }

        if (getAero().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
        }
        engineType.setSelectedIndex(convertEngineType(getAero().getEngine()
                .getEngineType()));

        if (getAero().hasPatchworkArmor()) {
            setArmorCombo(EquipmentType.T_ARMOR_PATCHWORK);
        } else {
            setArmorCombo(getAero().getArmorType(0));
        }

        cockpitType.setSelectedItem(Aero.COCKPIT_SHORT_STRING[getAero()
                .getCockpitType()]);

        if (getAero().isMixedTech()) {
            if (getAero().isClan()) {

                techType.setSelectedIndex(3);
                if (getAero().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getAero().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getAero().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getAero().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getAero().isClan()) {

            techType.setSelectedIndex(1);
            if (getAero().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getAero().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getAero().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getAero().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getAero().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getAero().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getAero().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        setHeatSinkCombo();

        fuel.setValue((double)getAero().getFuelTonnage());
        safeThrust.setValue(getAero().getOriginalWalkMP());
        maxThrust.setText(getAero().getRunMPasString());

        setAeroStructuralIntegrity();

        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil
                .getMaximumArmorTonnage(getAero()));
        ((SpinnerNumberModel) armorTonnage.getModel()).setValue(Math.min(UnitUtil
                .getMaximumArmorTonnage(getAero()), getAero().getLabArmorTonnage()));

        armorTonnage.setEnabled(true);
        maximizeArmorButton.setEnabled(true);
        unusedTonnageArmorButton.setEnabled(true);

        armorView.refresh();
        panSummary.refresh();
        addAllListeners();

    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.RIGHT);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            removeAllListeners();
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            // we need to do cockpit also here, because cockpitType
            // determines
            // if a mech is primitive and thus needs a larger engine
            if (combo.equals(engineType) || combo.equals(cockpitType)) {
                if (combo.equals(cockpitType)) {
                    getAero().setCockpitType(combo.getSelectedIndex());
                    if (getAero().getCockpitType() == Aero.COCKPIT_PRIMITIVE){
                        getAero().setArmorType(EquipmentType.T_ARMOR_PRIMITIVE);
                    }
                    armorView.resetArmorPoints();
                }
                int rating = TestAero.calculateEngineRating(
                        getAero(),
                        (Integer) weightClass.getValue(),
                        (Integer) safeThrust.getValue());
                if (rating > TestAero.MAX_ENGINE_RATING) {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "That speed would create an engine " +
                                    "with a rating over the max rating, " +
                                    TestAero.MAX_ENGINE_RATING + ".",
                                    "Bad Engine Rating",
                                    JOptionPane.ERROR_MESSAGE);
                } else {
                    setNewEngine(rating, convertEngineType(engineType
                            .getSelectedItem().toString()));
                }
            } else if (combo.equals(armorCombo)) {
                UnitUtil.removeISorArmorMounts(getAero(), false);
                createArmorMountsAndSetArmorType();
                armorView.resetArmorPoints();
            } else if (combo.equals(heatSinkType)) {
                getAero().setHeatType((Integer) heatSinkType.getSelectedIndex());
            } else if (combo.equals(techLevel)) {
                int unitTechLevel = techLevel.getSelectedIndex();

                if (getAero().isClan()) {
                    switch (unitTechLevel) {
                        case 0:
                            getAero().setTechLevel(TechConstants.T_CLAN_TW);
                            techType.setSelectedIndex(1);
                            break;
                        case 1:
                            getAero().setTechLevel(
                                    TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            getAero().setTechLevel(
                                    TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            getAero().setTechLevel(
                                    TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            getAero().setTechLevel(TechConstants.T_CLAN_TW);
                            break;
                    }

                } else {
                    switch (unitTechLevel) {
                        case 0:
                            getAero()
                                    .setTechLevel(TechConstants.T_INTRO_BOXSET);
                            techType.setSelectedIndex(0);
                            break;
                        case 1:
                            getAero().setTechLevel(
                                    TechConstants.T_IS_TW_NON_BOX);
                            techType.setSelectedIndex(0);
                            break;
                        case 2:
                            getAero().setTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                            getAero().setTechLevel(
                                    TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            getAero().setTechLevel(
                                    TechConstants.T_IS_UNOFFICIAL);
                            break;
                    }
                }
                populateChoices(true);
                armorView.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(getAero());
            } else if (combo.equals(techType)) {
                if ((techType.getSelectedIndex() == 1)
                        && (!getAero().isClan() || getAero().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    getAero().setMixedTech(false);
                    if (!getAero().isClan()) {
                        int level = TechConstants
                                .getOppositeTechLevel(getAero().getTechLevel());
                        getAero().setTechLevel(level);
                        getAero().setStructureTechLevel(level);
                    }
                } else if ((techType.getSelectedIndex() == 0)
                        && (getAero().isClan() || getAero().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    getAero().setMixedTech(false);
                    if (getAero().isClan()) {
                        int level = TechConstants
                                .getOppositeTechLevel(getAero().getTechLevel());
                        getAero().setTechLevel(level);
                        getAero().setStructureTechLevel(level);
                    }
                } else if ((techType.getSelectedIndex() == 2)
                        && (!getAero().isMixedTech() || getAero().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getAero().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getAero().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getAero().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (getAero().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getAero().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getAero().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            getAero().setTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getAero().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getAero().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getAero().getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            getAero().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        }
                    }
                    getAero().setMixedTech(true);
                } else if ((techType.getSelectedIndex() == 3)
                        && (!getAero().isMixedTech() || !getAero().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getAero().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getAero().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            getAero().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (getAero().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getAero().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getAero().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            getAero().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getAero().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getAero().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getAero().getTechLevel() != TechConstants.T_CLAN_TW)) {
                            getAero().setTechLevel(TechConstants.T_CLAN_TW);
                        }
                    }
                    getAero().setMixedTech(true);
                }
                if (!getAero().hasPatchworkArmor()) {
                    UnitUtil.removeISorArmorMounts(getAero(), false);
                }
                createArmorMountsAndSetArmorType();
                populateChoices(true);
                armorView.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(getAero());
            }
            addAllListeners();
            refresh.refreshAll();
        }
    }

    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();
            if (check.equals(omniCB)) {
                getAero().setOmni(omniCB.isSelected());
                if (getAero().isOmni()) {
                    baseChassisHeatSinks.setEnabled(true);
                    getAero().getEngine().setBaseChassisHeatSinks(
                            10 + (Integer) baseChassisHeatSinks.getValue());
                } else {
                    baseChassisHeatSinks.setEnabled(false);
                    getAero().getEngine().setBaseChassisHeatSinks(-1);
                }

            }
        } else if (e.getSource() instanceof JButton) {
            if (e.getSource().equals(maximizeArmorButton)) {
                maximizeArmor();
            } else if (e.getSource().equals(unusedTonnageArmorButton)) {
                useRemainingTonnageArmor();
            }
        }
        addAllListeners();
        refresh.refreshAll();
    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getAero().locations(); loc++) {
            for (int slot = 0; slot < getAero().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getAero().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getAero().setCritical(loc, slot, null);
                }
            }
        }
    }

    public void removeAllListeners() {
        maximizeArmorButton.removeActionListener(this);
        unusedTonnageArmorButton.removeActionListener(this);
        armorCombo.removeItemListener(this);
        engineType.removeItemListener(this);
        unitType.removeItemListener(this);
        weightClass.removeChangeListener(this);
        cockpitType.removeItemListener(this);
        heatSinkNumber.removeChangeListener(this);
        heatSinkType.removeItemListener(this);
        safeThrust.removeChangeListener(this);
        fuel.removeChangeListener(this);
        techLevel.removeItemListener(this);
        techType.removeItemListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        baseChassisHeatSinks.removeChangeListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        armorTonnage.removeChangeListener(this);
    }

    public void addAllListeners() {
        maximizeArmorButton.addActionListener(this);
        unusedTonnageArmorButton.addActionListener(this);
        armorCombo.addItemListener(this);
        engineType.addItemListener(this);
        unitType.addItemListener(this);
        weightClass.addChangeListener(this);
        cockpitType.addItemListener(this);
        heatSinkNumber.addChangeListener(this);
        heatSinkType.addItemListener(this);
        safeThrust.addChangeListener(this);
        fuel.addChangeListener(this);
        techLevel.addItemListener(this);
        techType.addItemListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        baseChassisHeatSinks.addChangeListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
        armorTonnage.addChangeListener(this);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        removeAllListeners();
        if (e.getSource().equals(era)) {
            try {
                int year = Integer.parseInt(era.getText());
                if (year < 1950) {
                    addAllListeners();
                    return;
                }
                getAero().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getAero().setYear(3145);
            }
            refresh.refreshEquipment();
            populateChoices(true);
            armorView.resetArmorPoints();
            UnitUtil.checkEquipmentByTechLevel(getAero());
        } else if (e.getSource().equals(source)) {
            getAero().setSource(source.getText());
        } else if (e.getSource().equals(manualBV)) {
            if (!manualBV.getText().equals("-")) {
                int bv = Integer.parseInt(manualBV.getText());
                if (bv == 0) {
                    getAero().setUseManualBV(false);
                    getAero().setManualBV(0);
                } else {
                    getAero().setUseManualBV(true);
                    getAero().setManualBV(bv);
                }
            }
        } else if (e.getSource().equals(chassis)) {
            getAero().setChassis(chassis.getText().trim());
        } else if (e.getSource().equals(model)) {
            getAero().setModel(model.getText().trim());
        }
        addAllListeners();
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armorView.addRefreshedListener(l);
    }


    private int convertEngineType(int engineType) {

        if (getAero().isMixedTech()) {
            // Clan Chassis with Clan Engine
            if (getAero().isClan()
                    && getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.XXL_ENGINE:
                        return 4;
                }
            }// Clan Chassis with IS Engine
            else if (getAero().isClan()
                    && !getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.LIGHT_ENGINE:
                        return 6;
                    case Engine.COMPACT_ENGINE:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 5;
                }
            }// IS Chassis with Clan Engine
            else if (!getAero().isClan()
                    && getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.XXL_ENGINE:
                        return 5;
                }
            }// IS Chassis with IS Engine
            else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.LIGHT_ENGINE:
                        return 6;
                    case Engine.COMPACT_ENGINE:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 4;
                }

            }
        } else if (getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            if (getAero().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.XXL_ENGINE:
                        return 2;
                }
            }
        } else {
            if (getAero().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.LIGHT_ENGINE:
                        return 3;
                    case Engine.COMPACT_ENGINE:
                        return 4;
                    case Engine.XXL_ENGINE:
                        return 2;
                }
            }
        }
        return 0;
    }

    private int convertEngineType(String engineType) {

        if (getAero().isMixedTech()) {
            if (engineType.startsWith("(")) {
                if (engineType.startsWith("(Clan")) {
                    clanEngineFlag = Engine.CLAN_ENGINE;
                } else {
                    clanEngineFlag = 0;
                }

                engineType = engineType.substring(
                        engineType.lastIndexOf(")") + 1).trim();
            } else {
                if (getAero().isClan()) {
                    clanEngineFlag = Engine.CLAN_ENGINE;
                } else {
                    clanEngineFlag = 0;
                }
            }
        }

        if (engineType.equals(ENGINESTANDARD)) {
            return Engine.NORMAL_ENGINE;
        }
        if (engineType.equals(ENGINEXL)) {
            return Engine.XL_ENGINE;
        }
        if (engineType.equals(ENGINECOMPACT)) {
            return Engine.COMPACT_ENGINE;
        }
        if (engineType.equals(ENGINELIGHT)) {
            return Engine.LIGHT_ENGINE;
        }
        if (engineType.equals(ENGINEXXL)) {
            return Engine.XXL_ENGINE;
        }

        return Engine.NORMAL_ENGINE;
    }

    /**
     * resets all the various combo boxes with appropriate options based on the
     * tech base and tech level of the unit. This should NEVER be run when
     * listeners are turned on. If the updateUnit boolean is set to true, then
     * this method will check that the values of the current unit are available
     * as choices after populating the choices and if not it will reset them to
     * default values on the unit itself.
     *
     * @param updateUnit
     */
    private void populateChoices(boolean updateUnit) {

        boolean isClan = getAero().isClan();
        boolean isMixed = getAero().isMixedTech();
        boolean isExperimental = (getAero().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getAero().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getAero().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getAero().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);


        /* ARMOR */
        armorCombo.removeAllItems();
        for (TestAero.AeroArmor armor : TestAero.AeroArmor.values()) {
            int type = armor.type;
            EquipmentType et;
            if (!isMixed) {
                boolean techMatch = (armor.isClan && getAero().isClan()) ||
                        (!armor.isClan && !getAero().isClan());
                et = EquipmentType.get(
                        EquipmentType.getArmorTypeName(type, armor.isClan));
                boolean isPatchwork = type == EquipmentType.T_ARMOR_PATCHWORK;
                boolean legalTechLvl = (et != null) &&
                        (TechConstants.isLegal(getAero().getTechLevel(),
                                et.getTechLevel(getAero().getYear()),
                                isMixed));
                if (techMatch && ((isPatchwork && isExperimental) ||
                        legalTechLvl)) {

                    armorCombo.addItem(EquipmentType.armorNames[type]);
                }
            } else {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(type, true));
                if (et != null && TechConstants.isLegal(getAero().getTechLevel(), et
                                .getTechLevel(getAero().getYear()),
                                true)) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(type, true));
                }
                et = EquipmentType.get(EquipmentType.getArmorTypeName(type, false));
                if (et != null && TechConstants.isLegal(getAero().getTechLevel(), et
                                .getTechLevel(getAero().getYear()),
                                true)) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(type, false));
                }
                if (type == EquipmentType.T_ARMOR_PATCHWORK) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(type));
                }
            }
        }

        /* ENGINE */
        int engineCount = 1;
        String[] engineList = new String[0];

        engineType.removeAllItems();

        if (isMixed) {
            if (isClan) {
                engineCount = clanEngineTypes.length + isEngineTypes.length;
                engineList = new String[engineCount];
                int clanPos = 0;
                int enginePos = 0;
                for (String isEngine : isEngineTypes) {
                    if (clanPos < clanEngineTypes.length &&
                            clanEngineTypes[clanPos].equals(isEngine)) {
                        engineList[enginePos] = clanEngineTypes[clanPos];
                        clanPos++;
                        enginePos++;
                    }
                    engineList[enginePos] = String
                            .format("(IS) %1$s", isEngine);
                    enginePos++;
                }
            } else {
                engineCount = clanEngineTypes.length + isEngineTypes.length;
                engineList = new String[engineCount];
                int clanPos = 0;
                int enginePos = 0;
                for (String isEngine : isEngineTypes) {
                    engineList[enginePos] = isEngine;
                    enginePos++;
                    if (clanPos < clanEngineTypes.length &&
                            clanEngineTypes[clanPos].equals(isEngine)) {
                        engineList[enginePos] = String.format("(Clan) %1$s",
                                clanEngineTypes[clanPos]);
                        clanPos++;
                        enginePos++;
                    }
                }
            }
        } else {
            if (isClan) {
                clanEngineFlag = Engine.CLAN_ENGINE;
                if (getAero().isPrimitive()) {
                    engineList = clanIndustrialEngineTypes;
                    engineCount = clanIndustrialEngineTypes.length;
                } else {
                    engineList = clanEngineTypes;
                    switch (getAero().getTechLevel()) {
                        case TechConstants.T_INTRO_BOXSET:
                            engineCount = 1;
                            break;
                        case TechConstants.T_CLAN_TW:
                        case TechConstants.T_CLAN_ADVANCED:
                        case TechConstants.T_CLAN_EXPERIMENTAL:
                        case TechConstants.T_CLAN_UNOFFICIAL:
                            engineCount = 3;
                            break;
                        case TechConstants.T_IS_TW_NON_BOX:
                            engineCount = 2;
                            break;
                        case TechConstants.T_IS_ADVANCED:
                        case TechConstants.T_IS_EXPERIMENTAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 3;
                            break;
                    }
                }
            } else {
                clanEngineFlag = 0;
                if (getAero().isPrimitive()) {
                    engineList = isIndustrialEngineTypes;
                    engineCount = isIndustrialEngineTypes.length;
                } else {
                    engineList = isEngineTypes;
                    switch (getAero().getTechLevel()) {
                    case TechConstants.T_INTRO_BOXSET:
                        engineCount = 1;
                        break;
                    case TechConstants.T_CLAN_TW:
                    case TechConstants.T_CLAN_ADVANCED:
                    case TechConstants.T_CLAN_EXPERIMENTAL:
                    case TechConstants.T_CLAN_UNOFFICIAL:
                        engineCount = 3;
                        break;
                    case TechConstants.T_IS_TW_NON_BOX:
                        engineCount = 2;
                        break;
                    case TechConstants.T_IS_ADVANCED:
                    case TechConstants.T_IS_EXPERIMENTAL:
                    case TechConstants.T_IS_UNOFFICIAL:
                        engineCount = 5;
                        break;
                    }
                }
            }
        }
        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

        /* COCKPIT */
        cockpitType.removeAllItems();


        switch (getAero().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                break;
            case TechConstants.T_CLAN_TW:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                break;
            case TechConstants.T_IS_TW_NON_BOX:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                break;
            case TechConstants.T_CLAN_ADVANCED:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_IS_ADVANCED:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_PRIMITIVE]);
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
                cockpitType
                .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_PRIMITIVE]);
                break;
            case TechConstants.T_CLAN_UNOFFICIAL:
                cockpitType
                .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_PRIMITIVE]);
                break;
            case TechConstants.T_IS_UNOFFICIAL:
                cockpitType
                .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Aero.COCKPIT_SHORT_STRING[Aero.COCKPIT_PRIMITIVE]);
                break;
        }


        /* HEAT SINKS */
        heatSinkType.removeAllItems();
        for (int index = 0; index < heatSinkTypes.length; index++) {
            heatSinkType.addItem(heatSinkTypes[index]);
        }

        ((SpinnerNumberModel)fuel.getModel()).setMaximum(
                ((Integer)weightClass.getValue()).doubleValue());

        /* UNIT UPDATING */
        if (updateUnit) {
            setArmorCombo(getAero().getArmorType(0));
            if (armorCombo.getSelectedIndex() == -1) {
                armorCombo.setSelectedIndex(0);
                UnitUtil.removeISorArmorMounts(getAero(), false);
                createArmorMountsAndSetArmorType();
                armorView.resetArmorPoints();
            }
            int selEngine = convertEngineType(getAero().getEngine()
                    .getEngineType());
            if (engineType.getItemCount() <= selEngine) {
                selEngine = -1;
            }
            engineType.setSelectedIndex(selEngine);
            if (engineType.getSelectedIndex() == -1) {
                engineType.setSelectedIndex(0);
                setNewEngine(getAero().getEngine().getRating(),
                        convertEngineType(engineType.getSelectedItem()
                                .toString()));
            }
            cockpitType.setSelectedIndex(-1);
            String selItem = Aero.COCKPIT_SHORT_STRING[getAero()
                    .getCockpitType()];
            for (int i = 0; i < cockpitType.getItemCount(); i++) {
                if (cockpitType.getItemAt(i).equals(selItem)) {
                    cockpitType.setSelectedIndex(i);
                    break;
                }
            }
            if (cockpitType.getSelectedIndex() == -1) {
                cockpitType.setSelectedIndex(0);
            }
            setHeatSinkCombo();
            if (heatSinkType.getSelectedIndex() == -1) {
                heatSinkType.setSelectedIndex(0);
                getAero().setHeatType(heatSinkType.getSelectedIndex());
            }
        }

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            removeAllListeners();
            if (spinner.equals(weightClass)) {
                setAeroStructuralIntegrity();
                if (refreshEngine(false)) {
                    getAero().setWeight((Integer) weightClass.getValue());
                    getAero().autoSetInternal();
                    refreshEngine(true);
                    engineType.setSelectedIndex(engineType.getSelectedIndex());
                }
                populateChoices(true);
            } else if (spinner.equals(safeThrust)) {
                setAeroStructuralIntegrity();
                refreshEngine(true);
            } else if (spinner.equals(fuel)) {
                double fuelTons = Math.round(((Double) fuel.getValue()) * 2) / 2.0;
                getAero().setFuelTonnage(fuelTons);
            } else if (spinner.equals(armorTonnage)) {
                setArmorTonnage();
            } else if (spinner.equals(heatSinkNumber)) {
                getAero().setHeatSinks((Integer) heatSinkNumber.getValue());
            } else if (spinner.equals(baseChassisHeatSinks)) {
                getAero().getEngine().setBaseChassisHeatSinks(
                        Math.max(0, (Integer) baseChassisHeatSinks.getValue()));
            }
            addAllListeners();

            refresh.refreshAll();
        }
    }

    /**
     * Calculate the engine rating for the currently selected weight and safe
     * thrust value and return whether that engine is valid.  If the setEngine
     * flag is turned on, it will also set the engine for the unit.
     * 
     * @param setEngine  Determines whether the new engine is set or not.
     * @return
     */
    private boolean refreshEngine(boolean setEngine) {
        boolean retVal = true;
        int rating = TestAero.calculateEngineRating(
                getAero(),
                (Integer) weightClass.getValue(),
                (Integer) safeThrust.getValue());
        if (rating > TestAero.MAX_ENGINE_RATING) {
            JOptionPane
                    .showMessageDialog(
                            this,
                            "That speed would create an engine " +
                            "with a rating over the max rating, " +
                            TestAero.MAX_ENGINE_RATING + ".",
                            "Bad Engine Rating",
                            JOptionPane.ERROR_MESSAGE);
            retVal = false;
        } else if (setEngine){
            System.out.println("Setting new engine rating.");
            getAero().setEngine(
                    new Engine(rating, convertEngineType(engineType
                            .getSelectedItem().toString()),
                            clanEngineFlag));
        }
        return retVal;
    }

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getAero());
        armorTonnage.setValue(maxArmor);
        getAero().setArmorTonnage(maxArmor);
        armorView.resetArmorPoints();
    }
    
    private void useRemainingTonnageArmor() {
    	double currentTonnage = UnitUtil.getEntityVerifier(getAero())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getAero());
        double totalTonnage = getAero().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getAero()));
        armorTonnage.setValue(maxArmor);
        getAero().setArmorTonnage(maxArmor);
        armorView.resetArmorPoints();        
    } 

    private void createArmorMountsAndSetArmorType() {
        getAero().setArmorTechLevel(EquipmentType.get(
                getArmorType(armorCombo)).getTechLevel(getAero().getYear()));
        getAero().setArmorType(getArmorType(armorCombo));
    }

    private void setArmorCombo(int type) {
        armorCombo.setSelectedIndex(-1);

        for (int index = 0; index < armorCombo.getItemCount(); index++) {
            if (getAero().isMixedTech()) {
                if (EquipmentType.getArmorTypeName(type,TechConstants.isClan(getAero().getArmorTechLevel(0))).equals(armorCombo.getItemAt(index))) {
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
        if (!getAero().isMixedTech()) {
            String prefix = getAero().isClan()?"Clan ":"IS ";
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

        return EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_STANDARD, getAero().isClan());
    }
    
    public void setAsCustomization() {
        chassis.setEditable(false);
        chassis.setEnabled(false);
        weightClass.setEnabled(false);
        era.setEditable(false);
        era.setEnabled(false);
    }

    private void setArmorTonnage() {
        double armorTons = Math.round(((Double) armorTonnage.getValue()) * 2) / 2.0;
        getAero().setArmorTonnage(armorTons);
        armorView.resetArmorPoints();
    }

    private void setNewEngine(int rating, int type) {
        getAero().setEngine(new Engine(rating, type, clanEngineFlag));
    }

    private void setHeatSinkCombo() {
        int selIndex = -1;

        if (getAero().getHeatType() == Aero.HEAT_DOUBLE) {
            selIndex = 1;
        } else {
            selIndex = 0;
        }

        if (heatSinkType.getItemCount() <= selIndex) {
            selIndex = -1;
        }
        heatSinkType.setSelectedIndex(selIndex);
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

    /**
     * Sets the structural integrity for Aerospace and Conventional fighters.
     * For these units, the SI is equal to the safe thrust rating or 10% of the
     * units tonnage, whichever is greater.  The SI for fighters does not take
     * up any tonnage.
     */
    public void setAeroStructuralIntegrity(){
        int si = (int)Math.max(
                (Integer)weightClass.getValue() * 0.1,
                (Integer)safeThrust.getValue());
        getAero().setSI(si);
        structuralIntegrity.setValue(si);
    }

}
