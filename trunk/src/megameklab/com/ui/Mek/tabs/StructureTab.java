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

package megameklab.com.ui.Mek.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.GBC;
import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.TechConstants;
import megameklab.com.ui.Mek.views.ArmorView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener,
        ChangeListener {

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

    String[] isEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINELIGHT,
            ENGINECOMPACT, ENGINEFISSION, ENGINEFUELCELL, ENGINEXXL };
    String[] isIndustrialEngineTypes = { ENGINESTANDARD, ENGINEICE,
            ENGINEFUELCELL, ENGINEFISSION };
    String[] clanEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINEFUELCELL,
            ENGINEXXL };
    String[] clanIndustrialEngineTypes = { ENGINESTANDARD, ENGINEICE,
            ENGINEFUELCELL, ENGINEFISSION };
    private int clanEngineFlag = 0;

    JPanel panInfo;
    JPanel panChassis;
    JPanel panArmor;
    JPanel panMovement;
    JPanel panHeat;

    private ArmorView armor;

    JComboBox engineType = new JComboBox(isEngineTypes);
    String[] enhancements = { "None", "MASC" , "TSM"};
    JComboBox enhancement = new JComboBox(enhancements);
    JSpinner walkMP;
    JTextField runMP;
    JSpinner jumpMP;
    JComboBox gyroType = new JComboBox(Mech.GYRO_SHORT_STRING);
    JSpinner weightClass;
    JComboBox cockpitType = new JComboBox(Mech.COCKPIT_SHORT_STRING);
    String[] clanHeatSinkTypes = { "Single", "Double", "Laser" };
    String[] isHeatSinkTypes = { "Single", "Double", "Compact" };
    JComboBox heatSinkType = new JComboBox(isHeatSinkTypes);
    JComboBox heatSinkNumber;
    JComboBox baseChassisHeatSinks;
    String[] techTypes = { "Inner Sphere", "Clan", "Mixed Inner Sphere",
            "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels = { "Introductory", "Standard", "Advanced",
            "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental",
            "Unoffical" };
    String[] motiveTypes = { "Biped", "Quad" };
    JComboBox motiveType = new JComboBox(motiveTypes);
    JComboBox techLevel = new JComboBox(isTechLevels);
    String[] jjTypes = { "Standard", "Improved", "Improved Prototype", "Mechanical Boosters" };
    JComboBox jjType = new JComboBox(jjTypes);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox lamCB = new JCheckBox("LAM");
    JCheckBox fullHeadEjectCB = new JCheckBox("Full Head Ejection");
    JComboBox structureCombo = new JComboBox(EquipmentType.structureNames);
    JPanel masterPanel;
    JTextField manualBV = new JTextField(3);
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);

    private String[] armorNames = new String[] {
            EquipmentType.armorNames[EquipmentType.T_ARMOR_STANDARD],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_LIGHT_FERRO],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_FERRO],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_STEALTH],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_COMMERCIAL],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_INDUSTRIAL],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_FIBROUS_PROTO],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_FERRO_LAMELLOR],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_HARDENED],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_REACTIVE],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_REFLECTIVE],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_PRIMITIVE],
            EquipmentType.armorNames[EquipmentType.T_ARMOR_PATCHWORK] };
    private JComboBox armorCombo = new JComboBox(armorNames);
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JSpinner armorTonnage;
    private JCheckBox clanArmor = new JCheckBox("Clan Armor");

    public StructureTab(Mech unit) {
        this.unit = unit;
        armor = new ArmorView(getMech());
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        setArmorType(unit.getArmorType(0));
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panInfo = new JPanel(new GridBagLayout());
        panChassis = new JPanel(new GridBagLayout());
        panArmor = new JPanel(new GridBagLayout());
        panMovement = new JPanel(new GridBagLayout());
        panHeat = new JPanel(new SpringLayout());

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(35, 25);

        walkMP = new JSpinner(new SpinnerNumberModel(1, 1, 25, 1));
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField().setSize(
                spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setEditable(false);
        runMP = new JTextField();
        runMP.setEditable(false);
        // runMP.setEnabled(false);
        setFieldSize(runMP, new Dimension(37, 25));
        runMP.setHorizontalAlignment(SwingConstants.RIGHT);

        jumpMP = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField().setSize(
                spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField()
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField()
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField()
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField()
                .setEditable(false);

        weightClass = new JSpinner(new SpinnerNumberModel(20, 10, 100, 5));
        ((JSpinner.DefaultEditor) weightClass.getEditor()).getTextField()
                .setEditable(false);

        heatSinkNumber = new JComboBox();
        baseChassisHeatSinks = new JComboBox();
        
        armorTonnage = new JSpinner(new SpinnerNumberModel(unit.getArmorWeight(), 0.0, 30.5, 0.5));     
        ((JSpinner.DefaultEditor)armorTonnage.getEditor()).getTextField().setSize(spinnerSize);
        ((JSpinner.DefaultEditor)armorTonnage.getEditor()).getTextField().setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor)armorTonnage.getEditor()).getTextField().setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor)armorTonnage.getEditor()).getTextField().setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor)armorTonnage.getEditor()).getTextField().setEditable(false);
     

        chassis.setText(unit.getChassis());
        model.setText(unit.getModel());

        Dimension labelSize = new Dimension(110, 25);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gridBagConstraints.ipadx = 5;
        // gridBagConstraints.ipady = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panInfo.add(createLabel("Chassis:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panInfo.add(chassis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        // gridBagConstraints.ipadx = 5;
        // gridBagConstraints.ipady = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panInfo.add(createLabel("Model:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panInfo.add(model, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        // gridBagConstraints.ipadx = 5;
        // gridBagConstraints.ipady = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panInfo.add(createLabel("Year:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        panInfo.add(era, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        panInfo.add(createLabel("Source/Era:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        panInfo.add(source, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        panInfo.add(createLabel("Tech:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        panInfo.add(techType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        panInfo.add(createLabel("Tech Level:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        panInfo.add(techLevel, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panChassis.add(createLabel("Tonnage:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panChassis.add(weightClass, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panChassis.add(omniCB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panChassis.add(lamCB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panChassis.add(createLabel("Motive Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(motiveType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panChassis.add(createLabel("Structure Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(structureCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panChassis.add(createLabel("Engine Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(engineType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panChassis.add(createLabel("Gyro Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(gyroType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        panChassis.add(createLabel("Cockpit Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(cockpitType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        panChassis.add(createLabel("Enhancements:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(enhancement, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panChassis.add(fullHeadEjectCB, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gridBagConstraints.ipadx = 5;
        // gridBagConstraints.ipady = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panArmor.add(createLabel("Armor Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panArmor.add(armorCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panArmor.add(createLabel("Armor Tonnage:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panArmor.add(armorTonnage, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panArmor.add(clanArmor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panArmor.add(maximizeArmorButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panMovement.add(createLabel("Walking MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        panMovement.add(walkMP, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panMovement.add(createLabel("Running MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        panMovement.add(runMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panMovement.add(createLabel("Jumping MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        panMovement.add(jumpMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        panMovement.add(createLabel("Jump Jet Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panMovement.add(jjType, gbc);

        panHeat.add(createLabel("Base (Omni):", labelSize));
        panHeat.add(baseChassisHeatSinks);

        panHeat.add(createLabel("Sink Type:", labelSize));
        panHeat.add(heatSinkType);

        panHeat.add(createLabel("Number:", labelSize));
        panHeat.add(heatSinkNumber);

        // masterPanel.add(createLabel("Manual BV:", maxSize));
        // masterPanel.add(manualBV);

        Dimension comboSize = new Dimension(180, 25);

        setFieldSize(era, comboSize);
        setFieldSize(manualBV, comboSize);
        setFieldSize(source, comboSize);
        setFieldSize(techType, comboSize);
        setFieldSize(armorCombo, comboSize);
        setFieldSize(techLevel, comboSize);
        setFieldSize(heatSinkNumber, comboSize);
        setFieldSize(heatSinkType, comboSize);
        setFieldSize(baseChassisHeatSinks, comboSize);
        setFieldSize(engineType, comboSize);
        setFieldSize(enhancement, comboSize);
        setFieldSize(gyroType, comboSize);
        setFieldSize(cockpitType, comboSize);
        setFieldSize(structureCombo, comboSize);
        setFieldSize(jjType, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
        leftPanel.add(panChassis);
        leftPanel.add(Box.createGlue());
        rightPanel.add(panArmor);
        rightPanel.add(panMovement);
        rightPanel.add(panHeat);
        leftPanel.add(Box.createVerticalGlue());

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

        SpringLayoutHelper.setupSpringGrid(panHeat, 2);

        // masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(),
        // Color.blue.darker()));
        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        armor.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));

    }

    public void refresh() {
        removeAllListeners();
        if (getMech().isPrimitive()) {
            getMech().setOmni(false);
            omniCB.setEnabled(false);
        } else {
            omniCB.setEnabled(true);
        }
        omniCB.setSelected(getMech().isOmni());
        if (unit instanceof QuadMech) {
            motiveType.setSelectedItem("Quad");
        } else {
            motiveType.setSelectedItem("Biped");
        }
        lamCB.setSelected(unit instanceof LandAirMech);
        fullHeadEjectCB.setSelected(getMech().hasFullHeadEject());
        era.setText(Integer.toString(getMech().getYear()));
        source.setText(getMech().getSource());
        manualBV.setText(Integer.toString(Math.max(0, getMech().getManualBV())));
        weightClass.setValue((int) (getMech().getWeight()));
        // weightClass.setSelectedIndex((int) (getMech().getWeight() / 5) - 2);
        heatSinkNumber.removeAllItems();
        baseChassisHeatSinks.removeAllItems();

        for (int count = getMech().getEngine().getWeightFreeEngineHeatSinks(); count < 50; count++) {
            heatSinkNumber.addItem(Integer.toString(count));
        }

        for (int count = 0; count <= getMech().getEngine()
                .integralHeatSinkCapacity(getMech().hasCompactHeatSinks()); count++) {
            baseChassisHeatSinks.addItem(Integer.toString(count));
        }

        int totalSinks = getMech().heatSinks(false);
        int freeSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
        heatSinkNumber.setSelectedIndex(totalSinks - freeSinks);
        baseChassisHeatSinks.setSelectedIndex(Math.max(0, getMech().getEngine()
                .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks())));

        if (getMech().isOmni()) {
            baseChassisHeatSinks.setEnabled(true);
            getMech().getEngine().setBaseChassisHeatSinks(
                    Math.max(0, baseChassisHeatSinks.getSelectedIndex()));
        } else {
            baseChassisHeatSinks.setEnabled(false);
            getMech().getEngine().setBaseChassisHeatSinks(-1);
        }

        if (getMech().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
            createEngineList(true);
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
            createEngineList(false);

        }

        engineType.setSelectedIndex(convertEngineType(getMech().getEngine()
                .getEngineType()));

        createSystemList();
        createHeatSinkList();
        createJumpJetList();
        createEnhancementList();
        createGyroList();
        createEnhancementList();
        if(getMech().hasMASC()) {
            enhancement.setSelectedItem("MASC");
        }
        else if(getMech().hasIndustrialTSM()) {
            enhancement.setSelectedItem("Industrial TSM");
        } 
        else if(getMech().hasTSM()) {
            enhancement.setSelectedItem("TSM");
        } else {
            enhancement.setSelectedIndex(0);
        }
        
        cockpitType.setSelectedItem(Mech.COCKPIT_SHORT_STRING[getMech()
                .getCockpitType()]);
        setStructureCombo();
        gyroType.setSelectedIndex(getMech().getGyroType());

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {

                techType.setSelectedIndex(3);
                if (getMech().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getMech().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getMech().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getMech().isClan()) {

            techType.setSelectedIndex(1);
            if (getMech().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getMech().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getMech().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getMech().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    heatSinkType.setSelectedIndex(3);
                } else if (getMech().hasCompactHeatSinks()) {
                    heatSinkType.setSelectedIndex(4);
                } else if (getMech().hasDoubleHeatSinks()) {

                    if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                        heatSinkType.setSelectedIndex(1);
                    } else {
                        heatSinkType.setSelectedIndex(2);
                    }

                } else {
                    heatSinkType.setSelectedIndex(0);
                }
            } else {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    heatSinkType.setSelectedIndex(3);
                } else if (getMech().hasCompactHeatSinks()) {
                    heatSinkType.setSelectedIndex(4);
                } else if (getMech().hasDoubleHeatSinks()) {
                    if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                        heatSinkType.setSelectedIndex(1);
                    } else {
                        heatSinkType.setSelectedIndex(2);
                    }
                } else {
                    heatSinkType.setSelectedIndex(0);
                }
            }
        } else {
            if (UnitUtil.hasLaserHeatSinks(getMech())) {
                heatSinkType.setSelectedIndex(2);
            } else if (getMech().hasDoubleHeatSinks()) {
                if (getMech().hasCompactHeatSinks()) {
                    heatSinkType.setSelectedIndex(2);
                } else {
                    heatSinkType.setSelectedIndex(1);
                }
            } else if (getMech().hasCompactHeatSinks()) {
                heatSinkType.setSelectedIndex(2);
            } else {
                heatSinkType.setSelectedIndex(0);
            }
        }
        walkMP.setValue(getMech().getWalkMP(true, false, true));
        jumpMP.setValue(getMech().getJumpMP());
        if (getJumpJetType() == Mech.JUMP_IMPROVED || getJumpJetType() == Mech.JUMP_PROTOTYPE) {
            ((SpinnerNumberModel) jumpMP.getModel()).setMaximum(getMech()
                    .getRunMP());
        } 
        else if(getJumpJetType() == Mech.JUMP_BOOSTER) {
            ((SpinnerNumberModel) jumpMP.getModel()).setMaximum(20);
        }
        else {
            ((SpinnerNumberModel) jumpMP.getModel()).setMaximum(getMech()
                    .getWalkMP(true, false, true));
        }
        runMP.setText(getMech().getRunMPasString());
        
        if (getMech().getJumpType() > Mech.JUMP_STANDARD) {
            jjType.setSelectedIndex(getMech().getJumpType()-1);
        } else if (jjType.getSelectedIndex() < 0){
            jjType.setSelectedIndex(0);
        }

        clanArmor.setEnabled(unit.isMixedTech());
        clanArmor.setSelected(unit.isClanArmor(0));
        //setTotalArmorTonnage();
        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil
                .getMaximumArmorTonnage(unit));
        // if (!unit.hasPatchworkArmor()) {
        // setArmorType(unit.getArmorType(0));
        // }
        armor.updateUnit(unit);
        armor.refresh();

        addAllListeners();

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
        /*
         * if ((e.getSource() instanceof JButton) &&
         * e.getSource().equals(browseButton)) { FileDialog fDialog = new
         * FileDialog(new JFrame(), "Image Path", FileDialog.LOAD);
         *
         * if (getMech().getFluff().getMMLImagePath().trim().length() > 0) {
         * String fullPath = new
         * File(getMech().getFluff().getMMLImagePath()).getAbsolutePath();
         * String imageName =
         * fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
         * fullPath = fullPath.substring(0,
         * fullPath.lastIndexOf(File.separatorChar) + 1);
         * fDialog.setDirectory(fullPath); fDialog.setFile(imageName); } else {
         * fDialog.setDirectory(new
         * File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar +
         * ImageHelper.imageMech + File.separatorChar);
         * fDialog.setFile(getMech().getChassis() + " " + getMech().getModel() +
         * ".png"); }
         *
         * fDialog.setLocationRelativeTo(this);
         *
         * fDialog.setVisible(true);
         *
         * if (fDialog.getFile() != null) { String relativeFilePath = new
         * File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
         * relativeFilePath = "." + File.separatorChar +
         * relativeFilePath.substring(new
         * File(System.getProperty("user.dir").toString
         * ()).getAbsolutePath().length() + 1);
         * getMech().getFluff().setMMLImagePath(relativeFilePath); refresh(); }
         * return; }
         */
        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllListeners();

            try {
                // we need to do cockpit also here, because cockpitType
                // determines
                // if a mech is primitive and thus needs a larger engine
                if (combo.equals(engineType) || combo.equals(cockpitType)) {
                    if (combo.equals(cockpitType)) {
                        getMech().setCockpitType(
                                Mech.getCockpitTypeForString(combo
                                        .getSelectedItem().toString()));
                        getMech().clearCockpitCrits();
                        switch (getMech().getCockpitType()) {
                            case Mech.COCKPIT_COMMAND_CONSOLE:
                                getMech().addCommandConsole();
                                break;
                            case Mech.COCKPIT_DUAL:
                                getMech().addDualCockpit();
                                break;
                            case Mech.COCKPIT_SMALL:
                                getMech().addSmallCockpit();
                                break;
                            case Mech.COCKPIT_TORSO_MOUNTED:
                                removeSystemCrits(Mech.SYSTEM_ENGINE);
                                getMech().addEngineCrits();
                                getMech().addTorsoMountedCockpit();
                                break;
                            case Mech.COCKPIT_INDUSTRIAL:
                                getMech().addIndustrialCockpit();
                                getMech().setArmorType(
                                        EquipmentType.T_ARMOR_INDUSTRIAL);
                                break;
                            case Mech.COCKPIT_PRIMITIVE:
                                getMech().addPrimitiveCockpit();
                                getMech().setArmorType(
                                        EquipmentType.T_ARMOR_PRIMITIVE);
                                break;
                            case Mech.COCKPIT_PRIMITIVE_INDUSTRIAL:
                                getMech().addIndustrialPrimitiveCockpit();
                                getMech().setArmorType(
                                        EquipmentType.T_ARMOR_COMMERCIAL);
                                break;
                            default:
                                getMech().addCockpit();
                        }
                    }
                    int rating = ((Integer) walkMP.getValue())
                            * ((Integer) weightClass.getValue());
                    if (getMech().isPrimitive()) {
                        double dRating = ((Integer) walkMP.getValue())
                                * ((Integer) weightClass.getValue());
                        dRating *= 1.2;
                        if ((dRating % 5) != 0) {
                            dRating = (dRating - (dRating % 5)) + 5;
                        }
                        rating = (int) dRating;
                    }
                    if ((rating > 400)
                            && (getMech().getGyroType() == Mech.GYRO_XL)) {
                        JOptionPane
                                .showMessageDialog(
                                        this,
                                        "That speed would require a large engine, which doesn't fit",
                                        "Bad Engine", JOptionPane.ERROR_MESSAGE);
                    }
                    if (rating > 500) {
                        JOptionPane
                                .showMessageDialog(
                                        this,
                                        "That speed would create an engine with a rating over 500.",
                                        "Bad Engine Rating",
                                        JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("Clearning engine crits.");
                        getMech().clearEngineCrits();
                        System.out.println("Setting new engine rating.");
                        getMech().setEngine(
                                new Engine(rating, convertEngineType(engineType
                                        .getSelectedItem().toString()),
                                        clanEngineFlag));
                        getMech().addEngineCrits();
                        System.out.println("Adding engine crits.");
                        int autoSinks = getMech().getEngine()
                                .getWeightFreeEngineHeatSinks();
                        System.out.println("Updating # engine heat sinks to "
                                + autoSinks);
                        UnitUtil.updateHeatSinks(getMech(),
                                heatSinkNumber.getSelectedIndex() + autoSinks,
                                heatSinkType.getSelectedItem().toString());
                    }
                } else if (combo.equals(armorCombo)) {
                    UnitUtil.removeISorArmorMounts(unit, false);
                    createArmorMountsAndSetArmorType();
                    armor.resetArmorPoints();
                } else if (combo.equals(structureCombo)) {
                    UnitUtil.removeISorArmorMounts(getMech(), true);
                    createISMounts();
                } else if (combo.equals(gyroType)) {
                    if (getMech().getEngine().hasFlag(Engine.LARGE_ENGINE)
                            && (combo.getSelectedIndex() == Mech.GYRO_XL)) {
                        JOptionPane
                                .showMessageDialog(
                                        this,
                                        "A XL gyro does not fit with a large engine installed",
                                        "Bad Gyro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        getMech().setGyroType(combo.getSelectedIndex());
                        getMech().clearGyroCrits();

                        switch (getMech().getGyroType()) {
                            case Mech.GYRO_COMPACT:
                                getMech().addCompactGyro();
                                getMech().clearEngineCrits();
                                getMech().addEngineCrits();
                                break;
                            case Mech.GYRO_HEAVY_DUTY:
                                getMech().addHeavyDutyGyro();
                                break;
                            case Mech.GYRO_XL:
                                getMech().addXLGyro();
                                break;
                            default:
                                getMech().addGyro();
                        }
                    }
                } else if (combo.equals(heatSinkType)
                        || combo.equals(heatSinkNumber)) {
                    int autoSinks = getMech().getEngine()
                            .getWeightFreeEngineHeatSinks();
                    System.out
                            .println("Heat sink type or number changed.  Updating # engine sinks to "
                                    + autoSinks);
                    UnitUtil.updateHeatSinks(getMech(),
                            heatSinkNumber.getSelectedIndex() + autoSinks,
                            heatSinkType.getSelectedItem().toString());
                } else if (combo.equals(baseChassisHeatSinks)) {
                    getMech().getEngine()
                            .setBaseChassisHeatSinks(
                                    Math.max(0, baseChassisHeatSinks
                                            .getSelectedIndex()));
                    int autoSinks = getMech().getEngine()
                            .getWeightFreeEngineHeatSinks();
                    System.out
                            .println("Base chassis sinks changed.  Updating # engine sinks to "
                                    + autoSinks);
                    UnitUtil.updateHeatSinks(getMech(),
                            heatSinkNumber.getSelectedIndex() + autoSinks,
                            heatSinkType.getSelectedItem().toString());
                } else if (combo.equals(jjType)) {
                    if (getJumpJetType() == Mech.JUMP_STANDARD) {
                        // check to make sure we are not over the number of jets
                        // we are allowed
                        if (((Integer) jumpMP.getValue()) > getMech()
                                .getWalkMP(true, false, true)) {
                            jumpMP.setValue(getMech().getWalkMP(true, false,
                                    true));
                        }
                    }
                    else if(getJumpJetType() == Mech.JUMP_IMPROVED || getJumpJetType() == Mech.JUMP_PROTOTYPE) {
                        if(((Integer) jumpMP.getValue()) > getMech()
                                .getRunMP()) {
                            jumpMP.setValue(getMech().getRunMP());
                        }
                    }
                    UnitUtil.updateJumpJets(getMech(),
                            (Integer) jumpMP.getValue(), getJumpJetType());
                } else if(combo.equals(enhancement)) {
                    UnitUtil.updateEnhancments(getMech(), hasMASC(), hasTSM());
                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (getMech().isClan()) {
                        switch (unitTechLevel) {
                            case 0:
                                getMech().setTechLevel(TechConstants.T_CLAN_TW);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_CLAN_TW);
                                addAllListeners();
                                techType.setSelectedIndex(1);
                                removeAllListeners();
                                break;
                            case 1:
                                getMech().setTechLevel(
                                        TechConstants.T_CLAN_ADVANCED);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_CLAN_ADVANCED);
                                break;
                            case 2:
                                getMech().setTechLevel(
                                        TechConstants.T_CLAN_EXPERIMENTAL);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_CLAN_EXPERIMENTAL);
                                break;
                            case 3:
                                getMech().setTechLevel(
                                        TechConstants.T_CLAN_UNOFFICIAL);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_CLAN_UNOFFICIAL);
                                break;
                            default:
                                getMech().setTechLevel(TechConstants.T_CLAN_TW);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_CLAN_TW);
                                break;
                        }

                    } else {
                        switch (unitTechLevel) {
                            case 0:
                                getMech().setTechLevel(
                                        TechConstants.T_INTRO_BOXSET);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_INTRO_BOXSET);
                                addAllListeners();
                                techType.setSelectedIndex(0);
                                removeAllListeners();
                                break;
                            case 1:
                                getMech().setTechLevel(
                                        TechConstants.T_IS_TW_NON_BOX);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_IS_TW_NON_BOX);
                                addAllListeners();
                                techType.setSelectedIndex(0);
                                removeAllListeners();
                                break;
                            case 2:
                                getMech().setTechLevel(
                                        TechConstants.T_IS_ADVANCED);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_IS_ADVANCED);
                                break;
                            case 3:
                                getMech().setTechLevel(
                                        TechConstants.T_IS_EXPERIMENTAL);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_IS_EXPERIMENTAL);
                                break;
                            default:
                                getMech().setTechLevel(
                                        TechConstants.T_IS_UNOFFICIAL);
                                getMech().setArmorTechLevel(
                                        TechConstants.T_IS_UNOFFICIAL);
                                break;
                        }

                    }

                    createSystemList();
                    createGyroList();
                    createEngineList(getMech().isClan());
                    createHeatSinkList();
                    createJumpJetList();
                    createEnhancementList();
                    armor.resetArmorPoints();
                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1)
                            && (!getMech().isClan() || getMech().isMixedTech())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getMech().setMixedTech(false);
                        createEngineList(true);
                        createHeatSinkList();
                        createJumpJetList();
                        createEnhancementList();
                        heatSinkType.setSelectedItem("Double");
                    } else if ((techType.getSelectedIndex() == 0)
                            && (getMech().isClan() || getMech().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getMech().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getMech().setArmorTechLevel(
                                TechConstants.T_INTRO_BOXSET);
                        getMech().setMixedTech(false);
                        UnitUtil.removeAllMounteds(getMech(), MiscType.F_TALON);
                        createEngineList(false);
                        createHeatSinkList();
                        createJumpJetList();
                        createEnhancementList();

                    } else if ((techType.getSelectedIndex() == 2)
                            && (!getMech().isMixedTech() || getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to
                        // experimental if
                        // we're not already unofficial
                        if ((getMech().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getMech().setTechLevel(
                                    TechConstants.T_IS_EXPERIMENTAL);
                            getMech().setArmorTechLevel(
                                    TechConstants.T_IS_EXPERIMENTAL);
                        }
                        techLevel.setSelectedIndex(techLevel.getModel()
                                .getSize() - 2);
                        getMech().setMixedTech(true);
                        createEngineList(false);
                        createHeatSinkList();
                        createJumpJetList();
                        createEnhancementList();

                    } else if ((techType.getSelectedIndex() == 3)
                            && (!getMech().isMixedTech() || !getMech().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to advanced if
                        // we're not already experimental or unofficial
                        if (getMech().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) {
                            getMech().setTechLevel(
                                    TechConstants.T_CLAN_EXPERIMENTAL);
                            getMech().setArmorTechLevel(
                                    TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                        techLevel.setSelectedIndex(techLevel.getModel()
                                .getSize() - 2);
                        getMech().setMixedTech(true);
                        createEngineList(true);
                        createHeatSinkList();
                        createJumpJetList();
                        createEnhancementList();
                        heatSinkType.setSelectedItem("Double");
                    } else {
                        createEngineList(getMech().isClan());
                        createHeatSinkList();
                        createJumpJetList();
                        createEnhancementList();
                        heatSinkType
                                .setSelectedItem(getMech().isClan() ? "Double"
                                        : "Single");
                        addAllListeners();
                        return;
                    }
                    addAllListeners();                
                    enhancement.setSelectedIndex(0);
                    structureCombo.setSelectedIndex(0);
                    gyroType.setSelectedIndex(0);
                    engineType.setSelectedIndex(0);
                    armorCombo.setSelectedIndex(0);
                    cockpitType.setSelectedIndex(0);
                    armor.resetArmorPoints();
                    removeAllListeners();
                }
                armor.resetArmorPoints();
                addAllListeners();
                refresh.refreshAll();
            } catch (Exception ex) {
                ex.printStackTrace();
                addAllListeners();
            }
        } else if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();

            if (check.equals(omniCB)) {
                getMech().setOmni(omniCB.isSelected());
                if (getMech().isOmni()) {
                    baseChassisHeatSinks.setEnabled(true);
                    getMech().getEngine().setBaseChassisHeatSinks(
                            10 + baseChassisHeatSinks.getSelectedIndex());
                } else {
                    baseChassisHeatSinks.setEnabled(false);
                    getMech().getEngine().setBaseChassisHeatSinks(-1);
                }
                int autoSinks = getMech().getEngine()
                        .getWeightFreeEngineHeatSinks();
                UnitUtil.updateHeatSinks(getMech(),
                        heatSinkNumber.getSelectedIndex() + autoSinks,
                        heatSinkType.getSelectedItem().toString());
            } else if (check.equals(fullHeadEjectCB)) {
                getMech().setFullHeadEject(fullHeadEjectCB.isSelected());
            } else if (check.equals(clanArmor)) {
                removeAllListeners();
                UnitUtil.removeISorArmorMounts(unit, false);
                if (clanArmor.isSelected()) {
                    if (unit.isClan()) {
                        unit.setArmorTechLevel(unit.getTechLevel());
                    } else {
                        unit.setArmorTechLevel(TechConstants
                                .getOppositeTechLevel(unit.getTechLevel()));
                    }
                } else if (unit.isClan()) {
                    unit.setArmorTechLevel(TechConstants
                            .getOppositeTechLevel(unit.getTechLevel()));
                } else {
                    unit.setArmorTechLevel(unit.getTechLevel());
                }
                createArmorMountsAndSetArmorType();
                addAllListeners();
            }
            refresh.refreshAll();
        } else if(e.getSource() instanceof JButton) {
        	removeAllListeners();
        	if (e.getSource().equals(maximizeArmorButton)) {
        		maximizeArmor();
        	} 
            addAllListeners();
            refresh.refreshAll();

        }
    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getMech().locations(); loc++) {
            for (int slot = 0; slot < getMech().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getMech().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getMech().setCritical(loc, slot, null);
                }
            }
        }
    }

    public void removeAllListeners() {
    	maximizeArmorButton.removeActionListener(this);
        clanArmor.removeActionListener(this);
        armorCombo.removeActionListener(this);
        gyroType.removeActionListener(this);
        engineType.removeActionListener(this);
        weightClass.removeChangeListener(this);
        cockpitType.removeActionListener(this);
        heatSinkNumber.removeActionListener(this);
        heatSinkType.removeActionListener(this);
        walkMP.removeChangeListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        motiveType.removeActionListener(this);
        lamCB.removeActionListener(this);
        fullHeadEjectCB.removeActionListener(this);
        structureCombo.removeActionListener(this);
        baseChassisHeatSinks.removeActionListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        jumpMP.removeChangeListener(this);
        jjType.removeActionListener(this);
        enhancement.removeActionListener(this);
        armorTonnage.removeChangeListener(this);
    }

    public void addAllListeners() {
    	maximizeArmorButton.addActionListener(this);
        clanArmor.addActionListener(this);
        armorCombo.addActionListener(this);
        gyroType.addActionListener(this);
        engineType.addActionListener(this);
        weightClass.addChangeListener(this);
        cockpitType.addActionListener(this);
        heatSinkNumber.addActionListener(this);
        heatSinkType.addActionListener(this);
        walkMP.addChangeListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        motiveType.addActionListener(this);
        lamCB.addActionListener(this);
        fullHeadEjectCB.addActionListener(this);
        structureCombo.addActionListener(this);
        baseChassisHeatSinks.addActionListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
        jumpMP.addChangeListener(this);
        jjType.addActionListener(this);
        enhancement.addActionListener(this);
        armorTonnage.addChangeListener(this);

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                getMech().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getMech().setYear(2075);
            }
        } else if (e.getSource().equals(source)) {
            getMech().setSource(source.getText());
        } else if (e.getSource().equals(manualBV)) {
            if (!manualBV.getText().equals("-")) {
                int bv = Integer.parseInt(manualBV.getText());
                if (bv == 0) {
                    getMech().setUseManualBV(false);
                    getMech().setManualBV(0);
                } else {
                    getMech().setUseManualBV(true);
                    getMech().setManualBV(bv);
                }
            }
        } else if (e.getSource().equals(chassis)) {
            unit.setChassis(chassis.getText().trim());
            refresh.refreshHeader();
        } else if (e.getSource().equals(model)) {
            unit.setChassis(model.getText().trim());
            refresh.refreshHeader();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public boolean isQuad() {
        return motiveType.getSelectedIndex() == 1;
    }

    public boolean isLAM() {
        return lamCB.isSelected();
    }

    private void createISMounts() {
        int isCount = 0;
        int structType = structureCombo.getSelectedIndex();
        String structName = EquipmentType.getStructureTypeName(structType);

        if (getMech().isMixedTech()) {
            structName = structureCombo.getSelectedItem().toString();
            boolean clanStruct = getMech().isClan() ? structName
                    .indexOf(" (IS)") == -1
                    : structName.indexOf(" (Clan)") > -1;

            structName = structureCombo.getSelectedItem().toString()
                    .replace(" (IS)", "").replace(" (Clan)", "");

            for (structType = 0; structType < EquipmentType.structureNames.length; structType++) {
                if (EquipmentType.getStructureTypeName(structType).equals(
                        structName)) {
                    break;
                }
            }

            if (clanStruct) {
                structName = String.format("Clan %1$s", structName);
            }
        }

        getMech().setStructureType(structType);
        isCount = EquipmentType.get(structName).getCriticals(getMech());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMech().addEquipment(
                        new Mounted(getMech(), EquipmentType.get(structName)),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private int convertEngineType(int engineType) {

        if (getMech().isMixedTech()) {
            // Clan Chassis with Clan Engine
            if (getMech().isClan()
                    && getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.FUEL_CELL:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 9;
                }
            }// Clan Chassis with IS Engine
            else if (getMech().isClan()
                    && !getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.LIGHT_ENGINE:
                        return 4;
                    case Engine.COMPACT_ENGINE:
                        return 5;
                    case Engine.FISSION:
                        return 6;
                    case Engine.FUEL_CELL:
                        return 8;
                    case Engine.XXL_ENGINE:
                        return 10;
                }
            }// IS Chassis with Clan Engine
            else if (!getMech().isClan()
                    && getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.FUEL_CELL:
                        return 8;
                    case Engine.XXL_ENGINE:
                        return 10;
                }
            }// IS Chassis with IS Engine
            else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.LIGHT_ENGINE:
                        return 4;
                    case Engine.COMPACT_ENGINE:
                        return 5;
                    case Engine.FISSION:
                        return 6;
                    case Engine.FUEL_CELL:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 9;
                }

            }
        } else if (getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            if (getMech().isIndustrial() || getMech().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.COMBUSTION_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.FISSION:
                        return 3;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.XXL_ENGINE:
                        return 3;
                }
            }
        } else {
            if (getMech().isIndustrial() || getMech().isPrimitive()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.COMBUSTION_ENGINE:
                        return 1;
                    case Engine.FUEL_CELL:
                        return 2;
                    case Engine.FISSION:
                        return 3;
                }
            } else {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.LIGHT_ENGINE:
                        return 2;
                    case Engine.COMPACT_ENGINE:
                        return 3;
                    case Engine.FISSION:
                        return 4;
                    case Engine.FUEL_CELL:
                        return 5;
                    case Engine.XXL_ENGINE:
                        return 6;
                }
            }
        }

        return 0;
    }

    private int convertEngineType(String engineType) {

        if (getMech().isMixedTech()) {
            if (engineType.startsWith("(")) {
                if (engineType.startsWith("(Clan")) {
                    clanEngineFlag = Engine.CLAN_ENGINE;
                } else {
                    clanEngineFlag = 0;
                }

                engineType = engineType.substring(
                        engineType.lastIndexOf(")") + 1).trim();
            } else {
                if (getMech().isClan()) {
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

    private void createSystemList() {
        int selectedIndex = armorCombo.getSelectedIndex();
        armorCombo.removeAllItems();
        int armorCount = armorNames.length;

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                armorCount = 1;
                break;
            case TechConstants.T_CLAN_TW:
            case TechConstants.T_IS_TW_NON_BOX:
            case TechConstants.T_CLAN_ADVANCED:
            case TechConstants.T_IS_ADVANCED:
                armorCount = 8;
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_CLAN_UNOFFICIAL:
            case TechConstants.T_IS_UNOFFICIAL:
                break;
        }

        for (int index = 0; index < armorCount; index++) {
            armorCombo.addItem(armorNames[index]);
        }

        if (armorCount <= selectedIndex) {
            armorCombo.setSelectedIndex(0);
        } else {
            armorCombo.setSelectedIndex(selectedIndex);
        }

        structureCombo.removeAllItems();
        cockpitType.removeAllItems();
        int structCount = EquipmentType.structureNames.length;

        boolean isClan = getMech().isClan();
        boolean isMixed = getMech().isMixedTech();

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                structCount = 1;
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                break;
            case TechConstants.T_CLAN_TW:
                structCount = 3;
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                break;
            case TechConstants.T_IS_TW_NON_BOX:
                structCount = 3;
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                break;
            case TechConstants.T_CLAN_ADVANCED:
                structCount = 3;
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_IS_ADVANCED:
                structCount = 3;
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                break;
            case TechConstants.T_CLAN_EXPERIMENTAL:
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                break;
            case TechConstants.T_CLAN_UNOFFICIAL:
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_DUAL]);

                break;
            case TechConstants.T_IS_UNOFFICIAL:
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_PRIMITIVE_INDUSTRIAL]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TORSO_MOUNTED]);
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_DUAL]);
                break;
        }

        for (int index = 0; index < structCount; index++) {
            if (isMixed
                    && !EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_STANDARD])
                    && !EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_INDUSTRIAL])) {
                if (isClan) {
                    structureCombo.addItem(String.format("%1$s (IS)",
                            EquipmentType.structureNames[index]));
                    if (EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                        structureCombo
                                .addItem(EquipmentType.structureNames[index]);
                    } else if (EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                        structureCombo
                                .addItem(EquipmentType.structureNames[index]);
                    }
                } else {
                    if (EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                        structureCombo.addItem(String.format("%1$s (Clan)",
                                EquipmentType.structureNames[index]));
                    } else if (EquipmentType.structureNames[index]
                            .equals(EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                        structureCombo.addItem(String.format("%1$s (Clan)",
                                EquipmentType.structureNames[index]));
                    }
                    structureCombo.addItem(EquipmentType.structureNames[index]);
                }
            } else {
                structureCombo.addItem(EquipmentType.structureNames[index]);
            }
        }

    }

    private void createHeatSinkList() {

        int heatSinkCount = 0;
        String[] heatSinkList;
        heatSinkType.removeAllItems();

        if (getMech().isMixedTech()) {
            heatSinkCount = (clanHeatSinkTypes.length + isHeatSinkTypes.length) - 1;
            heatSinkList = new String[heatSinkCount];
            int clanPos = 1;
            int heatSinkPos = 0;
            if (getMech().isClan()) {
                clanPos = 0;
                for (String isHeatSink : isHeatSinkTypes) {
                    heatSinkList[heatSinkPos] = clanHeatSinkTypes[clanPos];
                    heatSinkPos++;
                    clanPos++;
                    if (isHeatSink.equals("Single")) {
                        continue;
                    }
                    heatSinkList[heatSinkPos] = String.format("(IS) %1$s",
                            isHeatSink);
                    heatSinkPos++;
                }
            } else {
                for (String isHeatSink : isHeatSinkTypes) {
                    heatSinkList[heatSinkPos] = isHeatSink;
                    heatSinkPos++;
                    if (clanPos < clanHeatSinkTypes.length) {
                        heatSinkList[heatSinkPos] = String.format(
                                "(Clan) %1$s", clanHeatSinkTypes[clanPos]);
                    }
                    clanPos++;
                    heatSinkPos++;
                }
            }
        } else {
            if (getMech().isClan()) {

                heatSinkCount = clanHeatSinkTypes.length;
                heatSinkList = clanHeatSinkTypes;

                switch (getMech().getTechLevel()) {
                    case TechConstants.T_CLAN_TW:
                        heatSinkCount = 2;
                        break;
                    case TechConstants.T_CLAN_ADVANCED:
                    case TechConstants.T_CLAN_EXPERIMENTAL:
                    case TechConstants.T_CLAN_UNOFFICIAL:
                        heatSinkCount = 3;
                        break;
                }
            } else {
                heatSinkList = isHeatSinkTypes;
                switch (getMech().getTechLevel()) {
                    case TechConstants.T_INTRO_BOXSET:
                        heatSinkCount = 1;
                        break;
                    case TechConstants.T_IS_TW_NON_BOX:
                    case TechConstants.T_IS_ADVANCED:
                        heatSinkCount = 2;
                        break;
                    case TechConstants.T_IS_EXPERIMENTAL:
                    case TechConstants.T_IS_UNOFFICIAL:
                        heatSinkCount = 3;
                        break;
                }
            }
        }

        for (int index = 0; index < heatSinkCount; index++) {
            heatSinkType.addItem(heatSinkList[index]);
        }
    }

    private void createJumpJetList() {

        int jjCount = 0;
        // need to preserve choice here, because it cannot be when reset
        int selIndex = jjType.getSelectedIndex();
        jjType.removeAllItems();

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                jjCount = 1;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
                jjCount = jjTypes.length;
                break;
            default:
                jjCount = 2;
        }

        for (int index = 0; index < jjCount; index++) {
            jjType.addItem(jjTypes[index]);
        }

        if (jjType.getItemCount() > selIndex) {
            jjType.setSelectedIndex(selIndex);
        } else {
            jjType.setSelectedIndex(0);
            if (((Integer) jumpMP.getValue()) > getMech().getWalkMP(true,
                    false, true)) {
                jumpMP.setValue(getMech().getWalkMP(true, false, true));
            }
            UnitUtil.updateJumpJets(getMech(), (Integer) jumpMP.getValue(),
                    Mech.JUMP_STANDARD);
        }
    }
    
    private void createEnhancementList() {
        // need to preserve choice here, because it cannot be when reset
        String selItem = (String)enhancement.getSelectedItem();
        enhancement.removeAllItems();

        enhancement.addItem("None");
        if(getMech().getTechLevel() > TechConstants.T_INTRO_BOXSET) {
            enhancement.addItem("MASC");
            if(!TechConstants.isClan(getMech().getTechLevel())) {
                if(getMech().isIndustrial()) {
                    enhancement.addItem("Industrial TSM");
                } else {
                    enhancement.addItem("TSM");
                }
            }
        }

        int selIndex = -1;
        for(int i = 0; i < enhancement.getItemCount(); i++) {
            if(enhancement.getItemAt(i).equals(selItem)) {
                selIndex = i;
                break;
            }
        }
        if (selIndex != -1) {
            enhancement.setSelectedIndex(selIndex);
        } else {
            enhancement.setSelectedIndex(0);
            UnitUtil.updateEnhancments(getMech(), false, false);
        }
    }

    private void createEngineList(boolean isClan) {

        int engineCount = 1;
        String[] engineList = new String[0];

        engineType.removeAllItems();

        if (getMech().isMixedTech()) {
            if (isClan) {
                engineCount = clanEngineTypes.length + isEngineTypes.length;
                engineList = new String[engineCount];
                int clanPos = 0;
                int enginePos = 0;
                for (String isEngine : isEngineTypes) {
                    if (clanEngineTypes[clanPos].equals(isEngine)) {
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
                    if (clanEngineTypes[clanPos].equals(isEngine)) {
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
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = clanIndustrialEngineTypes;
                    engineCount = clanIndustrialEngineTypes.length;
                } else {
                    engineList = clanEngineTypes;
                    switch (getMech().getTechLevel()) {
                        case TechConstants.T_INTRO_BOXSET:
                            engineCount = 1;
                            break;
                        case TechConstants.T_CLAN_TW:
                        case TechConstants.T_IS_TW_NON_BOX:
                            engineCount = 2;
                            break;
                        case TechConstants.T_CLAN_ADVANCED:
                        case TechConstants.T_IS_ADVANCED:
                            engineCount = 3;
                            break;
                        case TechConstants.T_CLAN_EXPERIMENTAL:
                        case TechConstants.T_IS_EXPERIMENTAL:
                            engineCount = 4;
                            break;
                        case TechConstants.T_CLAN_UNOFFICIAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 4;
                            break;
                    }
                }
            } else {
                clanEngineFlag = 0;
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = isIndustrialEngineTypes;
                    engineCount = isIndustrialEngineTypes.length;
                } else {
                    engineList = isEngineTypes;
                    switch (getMech().getTechLevel()) {
                        case TechConstants.T_INTRO_BOXSET:
                            engineCount = 1;
                            break;
                        case TechConstants.T_CLAN_TW:
                        case TechConstants.T_IS_TW_NON_BOX:
                            engineCount = 4;
                            break;
                        case TechConstants.T_CLAN_ADVANCED:
                        case TechConstants.T_IS_ADVANCED:
                            engineCount = 6;
                            break;
                        case TechConstants.T_CLAN_EXPERIMENTAL:
                        case TechConstants.T_IS_EXPERIMENTAL:
                            engineCount = 7;
                            break;
                        case TechConstants.T_CLAN_UNOFFICIAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 7;
                            break;
                    }
                }
            }
        }
        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

    }

    private void createGyroList() {

        String[] gyroList = new String[0];

        gyroType.removeAllItems();

        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {
                int gyroPos = 0;
                gyroList = new String[Mech.GYRO_SHORT_STRING.length];
                for (String gyro : Mech.GYRO_SHORT_STRING) {
                    if (gyroPos == 0) {
                        gyroList[gyroPos] = gyro;
                    } else {
                        gyroList[gyroPos] = String.format("(IS) %1$s", gyro);
                    }
                    gyroPos++;
                }
            } else {
                gyroList = Mech.GYRO_SHORT_STRING.clone();
            }
        } else {
            if (getMech().isClan()) {
                gyroList = new String[1];
                gyroList[0] = Mech.GYRO_SHORT_STRING[0];
            } else {
                gyroList = Mech.GYRO_SHORT_STRING.clone();
            }
        }
        for (String gyro : gyroList) {
            gyroType.addItem(gyro);
        }

    }

    private void setStructureCombo() {

        if (getMech().isMixedTech()) {
            String structName;
            if (getMech().isClan()) {
                structName = EquipmentType.structureNames[getMech()
                        .getStructureType()] + " (IS)";
                if (getMech()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL];
                } else if (getMech()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE];
                }
            } else {
                structName = EquipmentType.structureNames[getMech()
                        .getStructureType()];
                if (getMech()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL]
                            + " (Clan)";
                } else if (getMech()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE]
                            + " (Clan)";
                }
            }
            structureCombo.setSelectedIndex(0);
            for (int pos = 0; pos < structureCombo.getItemCount(); pos++) {
                if (structureCombo.getItemAt(pos).equals(structName)) {
                    structureCombo.setSelectedIndex(pos);
                    break;
                }
            }
        } else {
            structureCombo.setSelectedIndex(getMech().getStructureType());
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            removeAllListeners();
            if (spinner.equals(weightClass)) {
                int rating = ((Integer) walkMP.getValue())
                        * ((Integer) weightClass.getValue());
                if (getMech().isPrimitive()) {
                    double dRating = ((Integer) walkMP.getValue())
                            * ((Integer) weightClass.getValue());
                    dRating *= 1.2;
                    if ((dRating % 5) != 0) {
                        dRating = (dRating - (dRating % 5)) + 5;
                    }
                    rating = (int) dRating;
                }
                if (rating > 500) {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "That speed would create an engine with a rating over 500.",
                                    "Bad Engine Rating",
                                    JOptionPane.ERROR_MESSAGE);
                } else {
                    getMech().setWeight((Integer) weightClass.getValue());
                    getMech().autoSetInternal();
                    addAllListeners();
                    engineType.setSelectedIndex(engineType.getSelectedIndex());
                    removeAllListeners();
                }
            } else if (spinner.equals(walkMP)) {
                int rating = ((Integer) walkMP.getValue())
                        * ((Integer) weightClass.getValue());
                if (getMech().isPrimitive()) {
                    double dRating = ((Integer) walkMP.getValue())
                            * ((Integer) weightClass.getValue());
                    dRating *= 1.2;
                    if ((dRating % 5) != 0) {
                        dRating = (dRating - (dRating % 5)) + 5;
                    }
                    rating = (int) dRating;
                }
                if ((rating > 400) && (getMech().getGyroType() == Mech.GYRO_XL)) {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "That speed would require a large engine, which doesn't fit",
                                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
                }
                if (rating > 500) {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "That speed would create an engine with a rating over 500.",
                                    "Bad Engine Rating",
                                    JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Clearning engine crits.");
                    getMech().clearEngineCrits();
                    System.out.println("Setting new engine rating.");
                    getMech().setEngine(
                            new Engine(rating, convertEngineType(engineType
                                    .getSelectedItem().toString()),
                                    clanEngineFlag));
                    getMech().addEngineCrits();
                    System.out.println("Adding engine crits.");
                    int autoSinks = getMech().getEngine()
                            .getWeightFreeEngineHeatSinks();
                    System.out.println("Updating # engine heat sinks to "
                            + autoSinks);
                    UnitUtil.updateHeatSinks(getMech(),
                            heatSinkNumber.getSelectedIndex() + autoSinks,
                            heatSinkType.getSelectedItem().toString());
                }
			}
			else if(spinner.equals(jumpMP)) {
				UnitUtil.updateJumpJets(getMech(), (Integer)jumpMP.getValue(), getJumpJetType());
			}
			else if(spinner.equals(armorTonnage)) {
				setArmorTonnage();
			}
	        addAllListeners();
	        refresh.refreshAll();
		}
	}

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(unit);
        armorTonnage.setValue(maxArmor);
        unit.setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
    }

    private void createArmorMountsAndSetArmorType() {
        if (getArmorType(armorCombo) == EquipmentType.T_ARMOR_PATCHWORK) {
            JComboBox headArmor = new JComboBox();
            headArmor.setName("head");
            JComboBox laArmor = new JComboBox();
            laArmor.setName("la");
            JComboBox ltArmor = new JComboBox();
            ltArmor.setName("lt");
            JComboBox ctArmor = new JComboBox();
            ctArmor.setName("ct");
            JComboBox rtArmor = new JComboBox();
            rtArmor.setName("rt");
            JComboBox raArmor = new JComboBox();
            raArmor.setName("ra");
            JComboBox llArmor = new JComboBox();
            llArmor.setName("ll");
            JComboBox rlArmor = new JComboBox();
            rlArmor.setName("rl");
            for (int index = 0; index < (armorNames.length - 1); index++) {
                headArmor.addItem(armorNames[index]);
                laArmor.addItem(armorNames[index]);
                ltArmor.addItem(armorNames[index]);
                ctArmor.addItem(armorNames[index]);
                rtArmor.addItem(armorNames[index]);
                raArmor.addItem(armorNames[index]);
                llArmor.addItem(armorNames[index]);
                rlArmor.addItem(armorNames[index]);
            }
            setArmorType(headArmor, unit.getArmorType(Mech.LOC_HEAD), false);
            setArmorType(laArmor, unit.getArmorType(Mech.LOC_LARM), false);
            setArmorType(ltArmor, unit.getArmorType(Mech.LOC_LT), false);
            setArmorType(ctArmor, unit.getArmorType(Mech.LOC_CT), false);
            setArmorType(rtArmor, unit.getArmorType(Mech.LOC_RT), false);
            setArmorType(raArmor, unit.getArmorType(Mech.LOC_RARM), false);
            setArmorType(llArmor, unit.getArmorType(Mech.LOC_LLEG), false);
            setArmorType(rlArmor, unit.getArmorType(Mech.LOC_RLEG), false);
            JCheckBox headClan = new JCheckBox("clan", unit.isClan());
            JCheckBox laClan = new JCheckBox("clan", unit.isClan());
            JCheckBox ltClan = new JCheckBox("clan", unit.isClan());
            JCheckBox ctClan = new JCheckBox("clan", unit.isClan());
            JCheckBox rtClan = new JCheckBox("clan", unit.isClan());
            JCheckBox raClan = new JCheckBox("clan", unit.isClan());
            JCheckBox llClan = new JCheckBox("clan", unit.isClan());
            JCheckBox rlClan = new JCheckBox("clan", unit.isClan());
            JLabel headLabel = new JLabel("Head:");
            JLabel laLabel = new JLabel(unit instanceof BipedMech ? "Left Arm:"
                    : "Front Left Leg");
            JLabel ltLabel = new JLabel("Left Torso:");
            JLabel ctLabel = new JLabel("Center Torso:");
            JLabel rtLabel = new JLabel("Right Torso:");
            JLabel raLabel = new JLabel(
                    unit instanceof BipedMech ? "Right Arm:"
                            : "Front Right Leg");
            JLabel llLabel = new JLabel(unit instanceof BipedMech ? "Left Leg:"
                    : "Rear Left Leg");
            JLabel rlLabel = new JLabel(
                    unit instanceof BipedMech ? "Right Leg:" : "Rear Right Leg");
            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(headLabel, GBC.std());
            panel.add(headClan, GBC.std());
            panel.add(headArmor, GBC.eol());
            panel.add(laLabel, GBC.std());
            panel.add(laClan, GBC.std());
            panel.add(laArmor, GBC.eol());
            panel.add(ltLabel, GBC.std());
            panel.add(ltClan, GBC.std());
            panel.add(ltArmor, GBC.eol());
            panel.add(ctLabel, GBC.std());
            panel.add(ctClan, GBC.std());
            panel.add(ctArmor, GBC.eol());
            panel.add(rtLabel, GBC.std());
            panel.add(rtClan, GBC.std());
            panel.add(rtArmor, GBC.eol());
            panel.add(raLabel, GBC.std());
            panel.add(raClan, GBC.std());
            panel.add(raArmor, GBC.eol());
            panel.add(llLabel, GBC.std());
            panel.add(llClan, GBC.std());
            panel.add(llArmor, GBC.eol());
            panel.add(rlLabel, GBC.std());
            panel.add(rlClan, GBC.std());
            panel.add(rlArmor, GBC.eol());
            if (!unit.isMixedTech()) {
                headClan.setVisible(false);
                laClan.setVisible(false);
                ltClan.setVisible(false);
                ctClan.setVisible(false);
                rtClan.setVisible(false);
                raClan.setVisible(false);
                llClan.setVisible(false);
                rlClan.setVisible(false);
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            unit.setArmorType(getArmorType(headArmor), Mech.LOC_HEAD);
            unit.setArmorType(getArmorType(laArmor), Mech.LOC_LARM);
            unit.setArmorType(getArmorType(ltArmor), Mech.LOC_LT);
            unit.setArmorType(getArmorType(ctArmor), Mech.LOC_CT);
            unit.setArmorType(getArmorType(rtArmor), Mech.LOC_RT);
            unit.setArmorType(getArmorType(raArmor), Mech.LOC_RARM);
            unit.setArmorType(getArmorType(llArmor), Mech.LOC_LLEG);
            unit.setArmorType(getArmorType(rlArmor), Mech.LOC_RLEG);
            setArmorTechLevel(Mech.LOC_HEAD, headClan.isSelected());
            setArmorTechLevel(Mech.LOC_LARM, laClan.isSelected());
            setArmorTechLevel(Mech.LOC_LT, ltClan.isSelected());
            setArmorTechLevel(Mech.LOC_CT, ctClan.isSelected());
            setArmorTechLevel(Mech.LOC_RT, rtClan.isSelected());
            setArmorTechLevel(Mech.LOC_RARM, raClan.isSelected());
            setArmorTechLevel(Mech.LOC_LLEG, llClan.isSelected());
            setArmorTechLevel(Mech.LOC_RLEG, rlClan.isSelected());
            for (int i = 0; i < unit.locations(); i++) {
                int armorCount = 0;
                switch (unit.getArmorType(i)) {
                    case EquipmentType.T_ARMOR_STANDARD:
                    case EquipmentType.T_ARMOR_HARDENED:
                    case EquipmentType.T_ARMOR_INDUSTRIAL:
                    case EquipmentType.T_ARMOR_COMMERCIAL:
                    case EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL:
                        armorCount = 0;
                        break;
                    case EquipmentType.T_ARMOR_STEALTH:
                    case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                        armorCount = 2;
                        break;
                    case EquipmentType.T_ARMOR_HEAVY_FERRO:
                        armorCount = 3;
                        break;
                    case EquipmentType.T_ARMOR_FERRO_FIBROUS:
                    case EquipmentType.T_ARMOR_REFLECTIVE:
                    case EquipmentType.T_ARMOR_REACTIVE:
                        if (TechConstants.isClan(unit.getArmorTechLevel(i))) {
                            armorCount = 1;
                        } else {
                            armorCount = 2;
                        }
                        break;
                    default:
                        break;
                }
                if (armorCount < 1) {
                    break;
                }
                // auto-place stealth crits
                /*
                 * if (getArmorType() == EquipmentType.T_ARMOR_STEALTH) { for
                 * (int loc = 0; loc < getMech().locations(); loc++) { if ((loc
                 * != Mech.LOC_HEAD) && (loc != Mech.LOC_CT)) { try {
                 * getMech().addEquipment(new Mounted(unit,
                 * EquipmentType.get(EquipmentType
                 * .getArmorTypeName(unit.getArmorType(loc)))), loc, false);
                 * getMech().addEquipment(new Mounted(unit,
                 * EquipmentType.get(EquipmentType
                 * .getArmorTypeName(unit.getArmorType(loc)))), loc, false); }
                 * catch (LocationFullException lfe) {
                 * JOptionPane.showMessageDialog(null, lfe.getMessage(),
                 * "Stealth Armor does not fit in location. Resetting to Standard Armor"
                 * , JOptionPane.INFORMATION_MESSAGE);
                 * setArmorType(EquipmentType.T_ARMOR_STANDARD); } } } } else {
                 */
                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(
                                new Mounted(unit,
                                        EquipmentType.get(EquipmentType
                                                .getArmorTypeName(unit
                                                        .getArmorType(i)))),
                                Entity.LOC_NONE, false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                // }
            }
            if (!unit.hasPatchworkArmor()) {
                setArmorType(EquipmentType.T_ARMOR_STANDARD);
            }
        } else {
            unit.setArmorType(getArmorType(armorCombo));
            int armorCount = 0;

            armorCount = EquipmentType.get(
                    EquipmentType.getArmorTypeName(unit.getArmorType(0)))
                    .getCriticals(unit);

            if (armorCount < 1) {
                return;
            }
            // auto-place stealth crits
            if (getArmorType(armorCombo) == EquipmentType.T_ARMOR_STEALTH) {
                Mounted mount = UnitUtil.createSpreadMounts(getMech(),
                        EquipmentType.get(EquipmentType.getArmorTypeName(unit
                                .getArmorType(0))));
                if (mount == null) {
                    JOptionPane.showMessageDialog(null,
                            "Stealth Armor does not fit in location.",
                            "Resetting to Standard Armor",
                            JOptionPane.INFORMATION_MESSAGE);
                    setArmorType(armorCombo, EquipmentType.T_ARMOR_STANDARD,
                            false);
                    unit.setArmorType(getArmorType(armorCombo));
                }
            } else {
                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(
                                new Mounted(unit,
                                        EquipmentType.get(EquipmentType
                                                .getArmorTypeName(unit
                                                        .getArmorType(0)))),
                                Entity.LOC_NONE, false);
                    } catch (Exception ex) {
                    }
                }
            }
        }

    }

    /*
    private void setTotalArmorTonnage() {
        double currentTonnage = unit.getArmorWeight();
        armorTonnage.setValue(currentTonnage);
        armorTonnage.setToolTipText("Max Tonnage: "
                + UnitUtil.getMaximumArmorTonnage(unit));
    }
    */

    private void setArmorType(JComboBox combo, int type, boolean removeListeners) {
        if (removeListeners) {
            removeAllListeners();
        }

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (EquipmentType.armorNames[type].equals(armorNames[pos])) {
                combo.setSelectedIndex(pos);
                break;
            }
        }
        if (removeListeners) {
            addAllListeners();
        }

    }

    private int getJumpJetType() {
        return jjType.getSelectedIndex() + 1;
    }

    public void setArmorType(int type) {
        setArmorType(armorCombo, type, true);
    }

    private int getArmorType(JComboBox combo) {
        String armorType = combo.getSelectedItem().toString();

        for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
            if (armorType.equals(EquipmentType.armorNames[pos])) {
                return pos;
            }
        }

        return EquipmentType.T_ARMOR_STANDARD;
    }

    private void setArmorTechLevel(int loc, boolean clan) {
        if (clan) {
            if (unit.isClan()) {
                unit.setArmorTechLevel(unit.getTechLevel(), loc);
            } else {
                unit.setArmorTechLevel(
                        TechConstants.getOppositeTechLevel(unit.getTechLevel()),
                        loc);
            }
        } else if (unit.isClan()) {
            unit.setArmorTechLevel(
                    TechConstants.getOppositeTechLevel(unit.getTechLevel()),
                    loc);
        } else {
            unit.setArmorTechLevel(unit.getTechLevel(), loc);
        }
    }
    
    private void setArmorTonnage() {
    	unit.setArmorTonnage(((Double)armorTonnage.getValue()));
    	armor.resetArmorPoints();
    }
    
    private boolean hasTSM() {
        return ((String)enhancement.getSelectedItem()).contains("TSM");
    }
    
    private boolean hasMASC() {
        return ((String)enhancement.getSelectedItem()).contains("MASC");
    }
}
