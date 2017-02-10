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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

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
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LandAirMech;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.TechConstants;
import megamek.common.TripodMech;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMech;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Mek.views.ArmorView;
import megameklab.com.ui.Mek.views.SummaryView;
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

    String[] isEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINELIGHT,
            ENGINECOMPACT, ENGINEFISSION, ENGINEFUELCELL, ENGINEXXL, ENGINEICE };
    String[] isIndustrialEngineTypes = { ENGINESTANDARD, ENGINEICE,
            ENGINEFUELCELL, ENGINEFISSION };
    String[] clanEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINEFUELCELL,
            ENGINEXXL, ENGINEICE };
    String[] clanIndustrialEngineTypes = { ENGINESTANDARD, ENGINEICE,
            ENGINEFUELCELL, ENGINEFISSION };
    String[] isSuperHeavyEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINELIGHT,
            ENGINECOMPACT, ENGINEXXL };
    String[] clanSuperHeavyEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINEXXL }; // For mixed tech
    private int clanEngineFlag = 0;
    private int superHeavyEngineFlag = 0;

    JPanel panInfo;
    JPanel panChassis;
    JPanel panArmor;
    JPanel panMovement;
    JPanel panHeat;
    SummaryView panSummary;

    private ArmorView armor;

    JComboBox<String> engineType = new JComboBox<String>(isEngineTypes);
    String[] enhancements = { "None", "MASC", "TSM" };
    JComboBox<String> enhancement = new JComboBox<String>(enhancements);
    JSpinner walkMPBase;
    JTextField runMPBase;
    JSpinner jumpMPBase;
    JTextField walkMPFinal;
    JTextField runMPFinal;
    JTextField jumpMPFinal;
    SpinnerNumberModel jumpModel;
    JComboBox<String> gyroType = new JComboBox<String>(Mech.GYRO_SHORT_STRING);
    JSpinner weightClass;
    JComboBox<String> cockpitType = new JComboBox<String>(
            Mech.COCKPIT_SHORT_STRING);
    String[] clanHeatSinkTypes = { "Single", "Double", "Laser" };
    String[] isHeatSinkTypes = { "Single", "Double", "Compact" };
    JComboBox<String> heatSinkType = new JComboBox<String>(isHeatSinkTypes);
    JSpinner heatSinkNumber;
    JSpinner baseChassisHeatSinks;
    String[] techTypes = { "Inner Sphere", "Clan", "Mixed Inner Sphere",
            "Mixed Clan" };
    JComboBox<String> techType = new JComboBox<String>(techTypes);
    String[] isTechLevels = { "Introductory", "Standard", "Advanced",
            "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental",
            "Unoffical" };
    String[] motiveTypes = { "Biped", "Quad", "Tripod" };
    JComboBox<String> motiveType = new JComboBox<String>(motiveTypes);
    JComboBox<String> techLevel = new JComboBox<String>(isTechLevels);
    String[] jjTypes = { "Standard", "Improved", "Prototype",
            "Mechanical Boosters", "Improved Prototype" };
    JComboBox<String> jjType = new JComboBox<String>(jjTypes);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    JCheckBox omniCB = new JCheckBox("Omni");
    JCheckBox lamCB = new JCheckBox("LAM");
    JCheckBox fullHeadEjectCB = new JCheckBox("Full Head Ejection");
    JComboBox<String> structureCombo = new JComboBox<String>(
            EquipmentType.structureNames);
    JPanel masterPanel;
    JTextField manualBV = new JTextField(3);
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);
    private JLabel lblFreeSinks = new JLabel("");

    private JComboBox<String> armorCombo = new JComboBox<String>();
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    private JButton unusedTonnageArmorButton = new JButton("Use Remaining Tonnage");
    private JSpinner armorTonnage;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        armor = new ArmorView(eSource);
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
        panHeat = new JPanel(new GridBagLayout());
        panSummary = new SummaryView(eSource);

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(55, 25);

        walkMPBase = new JSpinner(new SpinnerNumberModel(1, 1, 25, 1));
        ((JSpinner.DefaultEditor) walkMPBase.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMPBase.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMPBase.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMPBase.getEditor())
                .setMinimumSize(spinnerSize);
        runMPBase = new JTextField();
        runMPBase.setEditable(false);
        setFieldSize(runMPBase, new Dimension(60, 25));
        runMPBase.setHorizontalAlignment(SwingConstants.RIGHT);

        jumpModel = new SpinnerNumberModel(0, 0, 25, 1);
        jumpMPBase = new JSpinner(jumpModel);
        JSpinner.DefaultEditor jumpEditor = ((JSpinner.DefaultEditor) jumpMPBase
                .getEditor());
        jumpEditor.setSize(spinnerSize);
        jumpEditor.setMaximumSize(spinnerSize);
        jumpEditor.setPreferredSize(spinnerSize);
        jumpEditor.setMinimumSize(spinnerSize);

        walkMPFinal = new JTextField();
        walkMPFinal.setEditable(false);
        setFieldSize(walkMPFinal, new Dimension(60, 25));
        walkMPFinal.setHorizontalAlignment(SwingConstants.RIGHT);

        runMPFinal = new JTextField();
        runMPFinal.setEditable(false);
        setFieldSize(runMPFinal, new Dimension(60, 25));
        runMPFinal.setHorizontalAlignment(SwingConstants.RIGHT);

        jumpMPFinal = new JTextField();
        jumpMPFinal.setEditable(false);
        setFieldSize(jumpMPFinal, new Dimension(60, 25));
        jumpMPFinal.setHorizontalAlignment(SwingConstants.RIGHT);

        weightClass = new JSpinner(new SpinnerNumberModel(20, 10, 100, 5));

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

        baseChassisHeatSinks = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMinimumSize(spinnerSize);

        armorTonnage = new JSpinner(new SpinnerNumberModel(
                getMech().getArmorWeight(), 0.0,
                UnitUtil.getMaximumArmorTonnage(getMech()), 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMinimumSize(spinnerSize);

        // lblFreeSinks.setFont(new Font(lblFreeSinks.getName(), Font.PLAIN,
        // 10));

        chassis.setText(getMech().getChassis());
        model.setText(getMech().getModel());

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
        panChassis.add(omniCB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panChassis.add(lamCB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panChassis.add(createLabel("Motive Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panChassis.add(motiveType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Structure Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panChassis.add(structureCombo, gbc);

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
        panChassis.add(createLabel("Gyro Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panChassis.add(gyroType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Cockpit Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panChassis.add(cockpitType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panChassis.add(createLabel("Enhancements:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        panChassis.add(enhancement, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        panChassis.add(fullHeadEjectCB, gbc);

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
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panMovement.add(new JLabel("Base"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panMovement.add(new JLabel("Final"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panMovement.add(createLabel("Walking MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(walkMPBase, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(walkMPFinal, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panMovement.add(createLabel("Running MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panMovement.add(runMPBase, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(runMPFinal, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panMovement.add(createLabel("Jumping MP:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panMovement.add(jumpMPBase, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(jumpMPFinal, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panMovement.add(createLabel("Jump Jet Type:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panMovement.add(jjType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
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
        gbc.gridx = 2;
        gbc.gridy = 1;
        panHeat.add(lblFreeSinks, gbc);
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
        setFieldSize(enhancement, comboSize);
        setFieldSize(gyroType, comboSize);
        setFieldSize(cockpitType, comboSize);
        setFieldSize(structureCombo, comboSize);
        setFieldSize(jjType, comboSize);
        setFieldSize(model, comboSize);
        setFieldSize(chassis, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(panChassis);
        leftPanel.add(Box.createVerticalStrut(11));
        leftPanel.add(panHeat);
        leftPanel.add(Box.createGlue());
        
        rightPanel.add(panArmor);
        rightPanel.add(panMovement);       
        rightPanel.add(panSummary);
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

        panInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
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
        if (getMech() instanceof TripodMech) {
            motiveType.setSelectedIndex(2);
        }
        if (getMech() instanceof QuadMech) {
            motiveType.setSelectedIndex(1);
        } else {
            motiveType.setSelectedItem(0);
        }
        lamCB.setSelected(getMech() instanceof LandAirMech);
        fullHeadEjectCB.setSelected(getMech().hasFullHeadEject());
        era.setText(Integer.toString(getMech().getYear()));
        source.setText(getMech().getSource());
        manualBV.setText(Integer.toString(Math.max(0, getMech().getManualBV())));
        weightClass.setValue((int) (getMech().getWeight()));

        int totalSinks = getMech().heatSinks(false);
        int freeSinks = getMech().getEngine().getWeightFreeEngineHeatSinks();
        ((SpinnerNumberModel) heatSinkNumber.getModel()).setMinimum(freeSinks);
        heatSinkNumber.setValue(totalSinks);

        ((SpinnerNumberModel) baseChassisHeatSinks.getModel())
                .setMaximum(getMech().getEngine().integralHeatSinkCapacity(
                        getMech().hasCompactHeatSinks()));

        baseChassisHeatSinks.setValue(Math.max(0, getMech().getEngine()
                .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks())));

        if (getMech().isOmni()) {
            baseChassisHeatSinks.setEnabled(true);
            getMech().getEngine().setBaseChassisHeatSinks(
                    Math.max(0, (Integer) baseChassisHeatSinks.getValue()));
        } else {
            baseChassisHeatSinks.setEnabled(false);
            getMech().getEngine().setBaseChassisHeatSinks(-1);
        }

        lblFreeSinks.setText("Engine Free: "
                + UnitUtil.getCriticalFreeHeatSinks(getMech(), getMech()
                        .hasCompactHeatSinks()));

        if (getMech().isClan()) {
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
        engineType.setSelectedIndex(convertEngineType(getMech().getEngine()
                .getEngineType()));

        setEnhancementCombo();
        setStructureCombo();
        if (getMech().hasPatchworkArmor()) {
            setArmorCombo(EquipmentType.T_ARMOR_PATCHWORK);
        } else {
            setArmorCombo(getMech().getArmorType(0));
        }

        cockpitType.setSelectedItem(Mech.COCKPIT_SHORT_STRING[getMech()
                .getCockpitType()]);

        String gyroName = Mech.GYRO_SHORT_STRING[getMech().getGyroType()];
        gyroType.setSelectedItem(gyroName);

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

        setHeatSinkCombo();

        walkMPBase.setValue(Math.max(1, getMech().getOriginalWalkMP()));
        walkMPFinal.setText(String.valueOf(getMech().getWalkMP()));
        setJumpJetCombo();
        refreshJumpMP();
        //getOriginalRunMPWithoutMASC() still subtracts for hardened armor, so we just do the calculation here
        runMPBase.setText(String.valueOf((int)Math.ceil((int)walkMPBase.getValue() * 1.5)));
        runMPFinal.setText(getMech().getRunMPasString());
        
        StringJoiner walkTooltip = new StringJoiner(", ");
        StringJoiner runTooltip = new StringJoiner(", ");
        StringJoiner jumpTooltip = new StringJoiner(", ");
        
        if (getMech().hasModularArmor()) {
        	walkTooltip.add("-1 (Modular armor)");
        	jumpTooltip.add("-1 (Modular armor)");
        }
        if (getMech().hasMPReducingHardenedArmor()) {
        	runTooltip.add("-1 (Hardened armor)");
        }
        if (getMech().hasArmedMASC()) {
        	runTooltip.add("MASC");
        }
        if (getMech().hasArmedMASCAndSuperCharger()) {
        	runTooltip.add("Supercharger");
        }
        Optional<Mounted> partialWing = getMech().getMisc().stream()
        		.filter(m -> m.getType().hasFlag(MiscType.F_PARTIAL_WING)).findAny();
        if (partialWing.isPresent()) {
        	jumpTooltip.add(String.format("+%d (Partial wing)",
        			getMech().getPartialWingJumpBonus(partialWing.get())));
        }
        int medShields = getMech().getNumberOfShields(MiscType.S_SHIELD_MEDIUM);
        int lgShields = getMech().getNumberOfShields(MiscType.S_SHIELD_LARGE);
        if (lgShields + medShields > 0) {
        	walkTooltip.add(String.format("-%d (Shield)", lgShields + medShields));
        }
        if (lgShields > 0) {
        	jumpTooltip.add("No Jump (Large Shield)");
        }
        walkMPFinal.setToolTipText(walkTooltip.length() > 0? walkTooltip.toString() : null);
        runMPFinal.setToolTipText(runTooltip.length() > 0? runTooltip.toString() : null);
        jumpMPFinal.setToolTipText(jumpTooltip.length() > 0 && getMech().getOriginalJumpMP(true) > 0?
        		jumpTooltip.toString() : null);

        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil
                .getMaximumArmorTonnage(getMech()));
        ((SpinnerNumberModel) armorTonnage.getModel()).setValue(Math.min(
                UnitUtil.getMaximumArmorTonnage(getMech()),
                getMech().getLabArmorTonnage()));
        if (getMech().hasPatchworkArmor()) {
            TestMech testMech = new TestMech(
                    getMech(),
                    EntityVerifier.getInstance(new File(
                            "data/mechfiles/UnitVerifierOptions.xml")).mechOption,
                    null);
            armorTonnage.setEnabled(false);
            armorTonnage.getModel()
                    .setValue(testMech.getWeightAllocatedArmor());
            getMech().setArmorTonnage(testMech.getWeightAllocatedArmor());
            maximizeArmorButton.setEnabled(false);
            unusedTonnageArmorButton.setEnabled(false);
        } else {
            armorTonnage.setEnabled(true);
            maximizeArmorButton.setEnabled(true);
            unusedTonnageArmorButton.setEnabled(true);
        }
        armor.refresh();
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
                    refreshCockpitType();
                }
                resetEngine();
            } else if (combo.equals(armorCombo)) {
                if (!getMech().hasPatchworkArmor()) {
                    UnitUtil.removeISorArmorMounts(getMech(), false);
                }
                createArmorMountsAndSetArmorType();
                if (!getMech().hasPatchworkArmor()) {
                    armor.resetArmorPoints();
                }
            } else if (combo.equals(structureCombo)) {
                UnitUtil.removeISorArmorMounts(getMech(), true);
                createISMounts();
                populateChoices(true);
            } else if (combo.equals(gyroType)) {
                if (getMech().getEngine().hasFlag(Engine.LARGE_ENGINE)
                        && (combo.getSelectedIndex() == Mech.GYRO_XL)) {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "A XL gyro does not fit with a large engine installed",
                                    "Bad Gyro", JOptionPane.ERROR_MESSAGE);
                } else {
                    int gyroType = Arrays.asList(Mech.GYRO_SHORT_STRING)
                            .indexOf(((String)combo.getSelectedItem()).replace("(IS) ", ""));
                    getMech().setGyroType(gyroType);
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
                        case Mech.GYRO_NONE:
                            UnitUtil.compactCriticals(getMech(), Mech.LOC_CT);
                            break;
                        default:
                            getMech().addGyro();
                    }
                }
            } else if (combo.equals(heatSinkType)) {
                UnitUtil.updateHeatSinks(getMech(), (Integer) heatSinkNumber
                        .getValue(), heatSinkType.getSelectedItem().toString());
            } else if (combo.equals(jjType)) {
                refreshJumpMP();
            } else if (combo.equals(enhancement)) {
                UnitUtil.updateEnhancements(getMech(), hasMASC(), hasTSM());
            } else if (combo.equals(techLevel)) {
                int unitTechLevel = techLevel.getSelectedIndex();

                if (getMech().isClan()) {
                    switch (unitTechLevel) {
                        case 0:
                            getMech().setTechLevel(TechConstants.T_CLAN_TW);
                            techType.setSelectedIndex(1);
                            break;
                        case 1:
                            getMech().setTechLevel(
                                    TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            getMech().setTechLevel(
                                    TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            getMech().setTechLevel(
                                    TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            getMech().setTechLevel(TechConstants.T_CLAN_TW);
                            break;
                    }

                } else {
                    switch (unitTechLevel) {
                        case 0:
                            getMech()
                                    .setTechLevel(TechConstants.T_INTRO_BOXSET);
                            techType.setSelectedIndex(0);
                            break;
                        case 1:
                            getMech().setTechLevel(
                                    TechConstants.T_IS_TW_NON_BOX);
                            techType.setSelectedIndex(0);
                            break;
                        case 2:
                            getMech().setTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 3:
                            getMech().setTechLevel(
                                    TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            getMech().setTechLevel(
                                    TechConstants.T_IS_UNOFFICIAL);
                            break;
                    }
                }
                populateChoices(true);
                refreshCockpitType();
                armor.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(getMech());
            } else if (combo.equals(techType)) {
                if ((techType.getSelectedIndex() == 1)
                        && (!getMech().isClan() || getMech().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    getMech().setMixedTech(false);
                    if (!getMech().isClan()) {
                        int level = TechConstants
                                .getOppositeTechLevel(getMech().getTechLevel());
                        getMech().setTechLevel(level);
                        getMech().setStructureTechLevel(level);
                    }
                } else if ((techType.getSelectedIndex() == 0)
                        && (getMech().isClan() || getMech().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    getMech().setMixedTech(false);
                    if (getMech().isClan()) {
                        int level = TechConstants
                                .getOppositeTechLevel(getMech().getTechLevel());
                        getMech().setTechLevel(level);
                        getMech().setStructureTechLevel(level);
                    }
                } else if ((techType.getSelectedIndex() == 2)
                        && (!getMech().isMixedTech() || getMech().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getMech().getYear() < 3090) {
                        // before 3090, mixed tech is experimental
                        if ((getMech().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getMech().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (getMech().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getMech().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)
                                && (getMech().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            getMech().setTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getMech().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)
                                && (getMech().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)
                                && (getMech().getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            getMech().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        }
                    }
                    getMech().setMixedTech(true);

                } else if ((techType.getSelectedIndex() == 3)
                        && (!getMech().isMixedTech() || !getMech().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (getMech().getYear() < 3090) {
                        // before 3090, mixed tech is experimental
                        if ((getMech().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            getMech().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (getMech().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getMech().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)
                                && (getMech().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            getMech().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getMech().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)
                                && (getMech().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)
                                && (getMech().getTechLevel() != TechConstants.T_CLAN_TW)) {
                            getMech().setTechLevel(TechConstants.T_CLAN_TW);
                        }
                    }
                    getMech().setMixedTech(true);
                }
                if (!getMech().hasPatchworkArmor()) {
                    UnitUtil.removeISorArmorMounts(getMech(), false);
                }
                createArmorMountsAndSetArmorType();
                populateChoices(true);
                armor.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(getMech());
            }
            addAllListeners();
            refresh.refreshAll();
        }
    }

    public void refreshJumpMP() {
        // check to make sure we are not over the number of jets
        // we are allowed
        jumpMPBase.setValue(getMech().getOriginalJumpMP(true));
        jumpMPFinal.setText(String.valueOf(getMech().getJumpMP()));
        if ((getJumpJetType() == Mech.JUMP_IMPROVED)
                || (getJumpJetType() == Mech.JUMP_PROTOTYPE_IMPROVED)) {
            jumpModel.setMaximum((int)Math.ceil(getMech().getOriginalWalkMP() * 1.5));
        } else if (getJumpJetType() == Mech.JUMP_BOOSTER) {
            jumpModel.setMaximum(20);
        } else {
            jumpModel.setMaximum(getMech().getOriginalWalkMP());
        }
        if ((Integer) jumpModel.getValue() > (Integer) jumpModel.getMaximum()) {
            jumpMPBase.setValue(jumpModel.getMaximum());
        }
        UnitUtil.updateJumpJets(getMech(), (Integer) jumpModel.getValue(),
                getJumpJetType());
    }

    public void refreshCockpitType() {
        getMech().setCockpitType(
                Mech.getCockpitTypeForString(cockpitType
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
            case Mech.COCKPIT_INTERFACE:
                getMech().addInterfaceCockpit();
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
        armor.resetArmorPoints();
        populateChoices(true);
    }

    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();
            if (check.equals(omniCB)) {
                getMech().setOmni(omniCB.isSelected());
                if (getMech().isOmni()) {
                    baseChassisHeatSinks.setEnabled(true);
                    getMech().getEngine().setBaseChassisHeatSinks(
                            10 + (Integer) baseChassisHeatSinks.getValue());
                    UnitUtil.removeOmniArmActuators(getMech());
                } else {
                    baseChassisHeatSinks.setEnabled(false);
                    getMech().getEngine().setBaseChassisHeatSinks(-1);
                }
                UnitUtil.updateAutoSinks(getMech(),
                        (String) heatSinkType.getSelectedItem());

            } else if (check.equals(fullHeadEjectCB)) {
                getMech().setFullHeadEject(fullHeadEjectCB.isSelected());
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
        unusedTonnageArmorButton.removeActionListener(this);
        armorCombo.removeItemListener(this);
        gyroType.removeItemListener(this);
        engineType.removeItemListener(this);
        weightClass.removeChangeListener(this);
        cockpitType.removeItemListener(this);
        heatSinkNumber.removeChangeListener(this);
        heatSinkType.removeItemListener(this);
        walkMPBase.removeChangeListener(this);
        techLevel.removeItemListener(this);
        techType.removeItemListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        motiveType.removeItemListener(this);
        lamCB.removeActionListener(this);
        fullHeadEjectCB.removeActionListener(this);
        structureCombo.removeItemListener(this);
        baseChassisHeatSinks.removeChangeListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        jumpMPBase.removeChangeListener(this);
        jjType.removeItemListener(this);
        enhancement.removeItemListener(this);
        armorTonnage.removeChangeListener(this);
    }

    public void addAllListeners() {
        maximizeArmorButton.addActionListener(this);
        unusedTonnageArmorButton.addActionListener(this);
        armorCombo.addItemListener(this);
        gyroType.addItemListener(this);
        engineType.addItemListener(this);
        weightClass.addChangeListener(this);
        cockpitType.addItemListener(this);
        heatSinkNumber.addChangeListener(this);
        heatSinkType.addItemListener(this);
        walkMPBase.addChangeListener(this);
        techLevel.addItemListener(this);
        techType.addItemListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        motiveType.addItemListener(this);
        lamCB.addActionListener(this);
        fullHeadEjectCB.addActionListener(this);
        structureCombo.addItemListener(this);
        baseChassisHeatSinks.addChangeListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
        jumpMPBase.addChangeListener(this);
        jjType.addItemListener(this);
        enhancement.addItemListener(this);
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
                getMech().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getMech().setYear(3145);
            }            
            populateChoices(true);
            armor.resetArmorPoints();
            UnitUtil.checkEquipmentByTechLevel(getMech());
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
            getMech().setChassis(chassis.getText().trim());
        } else if (e.getSource().equals(model)) {
            getMech().setModel(model.getText().trim());
        }
        addAllListeners();
        refresh.refreshEquipment();
        refresh.refreshPreview();
        refresh.refreshHeader();
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

    public boolean isTripod() {
        return motiveType.getSelectedIndex() == 2;
    }

    public boolean isLAM() {
        return lamCB.isSelected();
    }

    private void createISMounts() {
        int isCount = 0;
        String structName = (String)structureCombo.getSelectedItem();

        if (getMech().isMixedTech()) {
            structName = structureCombo.getSelectedItem().toString();
            boolean clanStruct = getMech().isClan() ? structName
                    .indexOf(" (IS)") == -1
                    : structName.indexOf(" (Clan)") > -1;

            structName = structureCombo.getSelectedItem().toString()
                    .replace(" (IS)", "").replace(" (Clan)", "");

            if (clanStruct) {
                structName = String.format("Clan %1$s", structName);
            } else {
                structName = String.format("IS %1$s", structName);
            }
        } else {
            if (getMech().isClan()) {
                structName = String.format("Clan %1$s", structName);
            } else {
                structName = String.format("IS %1$s", structName);
            }
        }

        getMech().setStructureType(structName);
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
            } else if (getMech().isSuperHeavy()) {
                if (getMech().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
                    switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 1;
                    case Engine.XL_ENGINE:
                        return 3;
                    case Engine.XXL_ENGINE:
                        return 7;
                    }
                } else {
                    switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 2;
                    case Engine.LIGHT_ENGINE:
                        return 4;
                    case Engine.COMPACT_ENGINE:
                        return 5;
                    case Engine.XXL_ENGINE:
                        return 6;
                    }
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
            } else if (getMech().isSuperHeavy()) {
                switch (engineType) {
                    case Engine.NORMAL_ENGINE:
                        return 0;
                    case Engine.XL_ENGINE:
                        return 1;
                    case Engine.LIGHT_ENGINE:
                        return 2;
                    case Engine.COMPACT_ENGINE:
                        return 3;
                    case Engine.XXL_ENGINE:
                        return 4;
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

        boolean isClan = getMech().isClan();
        boolean isMixed = getMech().isMixedTech();
        boolean isExperimental = (getMech().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getMech().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getMech().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getMech().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);
        boolean isAdvanced = (getMech().getTechLevel() == TechConstants.T_IS_ADVANCED)
                || (getMech().getTechLevel() == TechConstants.T_CLAN_ADVANCED)
                || (getMech().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getMech().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getMech().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getMech().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);

        // advanced means we allow ultra-light mechs
        if (!isAdvanced) {
            ((SpinnerNumberModel) weightClass.getModel()).setMinimum(20);
        } else {
            ((SpinnerNumberModel) weightClass.getModel()).setMinimum(10);
        }
        // advanced IS means we allow super-heavy mechs
        if (!isClan && isAdvanced) {
            ((SpinnerNumberModel) weightClass.getModel()).setMaximum(200);
        } else {
            ((SpinnerNumberModel) weightClass.getModel()).setMaximum(100);
        }

        /* ARMOR */
        armorCombo.removeAllItems();
        for (int index = 0; index < (EquipmentType.armorNames.length); index++) {
            EquipmentType et;
            if (!isMixed) {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index,
                        isClan));
                if (((index == EquipmentType.T_ARMOR_PATCHWORK) && isExperimental)
                        || ((et != null)
                                && et.hasFlag(MiscType.F_MECH_EQUIPMENT) && (TechConstants
                                    .isLegal(getMech().getTechLevel(), et
                                            .getTechLevel(getMech().getYear()),
                                            isMixed)))) {
                    armorCombo.addItem(EquipmentType.armorNames[index]);
                }
            } else {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index,
                        true));
                if (et != null && et.hasFlag(MiscType.F_MECH_EQUIPMENT) && TechConstants
                        .isLegal(getMech().getTechLevel(), et
                                .getTechLevel(getMech().getYear()),
                                isMixed)) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index,
                            true));
                }
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index,
                        false));
                if (et != null && et.hasFlag(MiscType.F_MECH_EQUIPMENT) && TechConstants
                        .isLegal(getMech().getTechLevel(), et
                                .getTechLevel(getMech().getYear()),
                                isMixed)) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index,
                            false));
                }
                if (index == EquipmentType.T_ARMOR_PATCHWORK) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index));
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
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = isIndustrialEngineTypes;
                    engineCount = isIndustrialEngineTypes.length;
                    if (getMech().isSuperHeavy()) {
                        engineCount = 1;
                        superHeavyEngineFlag = Engine.SUPERHEAVY_ENGINE;
                    } else {
                        superHeavyEngineFlag = 0;
                    }
                } else {
                    if (getMech().isSuperHeavy()) {
                        superHeavyEngineFlag = Engine.SUPERHEAVY_ENGINE;
                        engineCount = clanSuperHeavyEngineTypes.length + isSuperHeavyEngineTypes.length;
                        engineList = new String[engineCount];
                        int clanPos = 0;
                        int enginePos = 0;
                        for (String isEngine : isSuperHeavyEngineTypes) {
                            engineList[enginePos] = isEngine;
                            enginePos++;
                            if (clanSuperHeavyEngineTypes[clanPos].equals(isEngine)) {
                                engineList[enginePos] = String.format("(Clan) %1$s",
                                        clanSuperHeavyEngineTypes[clanPos]);
                                clanPos++;
                                enginePos++;
                            }
                        }
                    } else {
                        superHeavyEngineFlag = 0;
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
                            engineCount = 5;
                            break;
                        case TechConstants.T_CLAN_UNOFFICIAL:
                        case TechConstants.T_IS_UNOFFICIAL:
                            engineCount = 5;
                            break;
                    }
                }
            } else {
                clanEngineFlag = 0;
                if (getMech().isIndustrial() || getMech().isPrimitive()) {
                    engineList = isIndustrialEngineTypes;
                    engineCount = isIndustrialEngineTypes.length;
                    if (getMech().isSuperHeavy()) {
                        engineCount = 1;
                        superHeavyEngineFlag = Engine.SUPERHEAVY_ENGINE;
                    } else {
                        superHeavyEngineFlag = 0;
                    }
                } else {
                    if (getMech().isSuperHeavy()) {
                        engineList = isSuperHeavyEngineTypes;
                        engineCount = isSuperHeavyEngineTypes.length;
                        superHeavyEngineFlag = Engine.SUPERHEAVY_ENGINE;
                        switch (getMech().getTechLevel()) {
                            case TechConstants.T_IS_ADVANCED:
                                engineCount = isSuperHeavyEngineTypes.length - 1;
                                break;
                            case TechConstants.T_IS_EXPERIMENTAL:
                            case TechConstants.T_IS_UNOFFICIAL:
                                engineCount = isSuperHeavyEngineTypes.length;
                                break;
                        }
                    } else {
                        superHeavyEngineFlag = 0;
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
                                engineCount = 8;
                                break;
                            case TechConstants.T_CLAN_UNOFFICIAL:
                            case TechConstants.T_IS_UNOFFICIAL:
                                engineCount = 8;
                                break;
                        }
                    }

                }
            }
        }
        for (int index = 0; index < engineCount; index++) {
            engineType.addItem(engineList[index]);
        }

        /* COCKPIT */
        cockpitType.removeAllItems();

        if (getMech().isSuperHeavy()) {
            if (getMech() instanceof TripodMech) {
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SUPERHEAVY_TRIPOD]);
            } else {
                cockpitType
                        .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SUPERHEAVY]);
                if (isExperimental
                        || (getMech().getTechLevel() == TechConstants.T_CLAN_ADVANCED)
                        || (getMech().getTechLevel() == TechConstants.T_IS_ADVANCED)) {
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_COMMAND_CONSOLE]);
                }
            }
        } else if (getMech() instanceof TripodMech) {
            cockpitType.addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_TRIPOD]);
        } else {
            switch (getMech().getTechLevel()) {
                case TechConstants.T_INTRO_BOXSET:
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                    break;
                case TechConstants.T_CLAN_TW:
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                    break;
                case TechConstants.T_IS_TW_NON_BOX:
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_STANDARD]);
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INDUSTRIAL]);
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_SMALL]);
                    break;
                case TechConstants.T_CLAN_ADVANCED:
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
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INTERFACE]);
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
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_INTERFACE]);
                    cockpitType
                            .addItem(Mech.COCKPIT_SHORT_STRING[Mech.COCKPIT_DUAL]);
                    break;
            }
        }

        /* INTERNAL STRUCTURE */
        structureCombo.removeAllItems();
        for (String structure : getStructureTypes()) {
            structureCombo.addItem(structure);
        }

        /* HEAT SINKS */
        int heatSinkCount = 0;
        String[] heatSinkList;
        heatSinkType.removeAllItems();

        if (isMixed) {
            heatSinkCount = (clanHeatSinkTypes.length + isHeatSinkTypes.length) - 1;
            heatSinkList = new String[heatSinkCount];
            int clanPos = 1;
            int heatSinkPos = 0;
            if (isClan) {
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
            if (isClan) {

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

        /* JUMP JETS */
        int jjCount = 0;
        jjType.removeAllItems();

        switch (getMech().getTechLevel()) {
            case TechConstants.T_INTRO_BOXSET:
                jjCount = 1;
                break;
            case TechConstants.T_IS_EXPERIMENTAL:
            case TechConstants.T_IS_UNOFFICIAL:
                jjCount = jjTypes.length;
                break;
            default:
                jjCount = 2;
        }

        for (int index = 0; index < jjCount; index++) {
            jjType.addItem(jjTypes[index]);
        }

        /* GYRO */
        String[] gyroList = new String[0];
        gyroType.removeAllItems();
        if (getMech().isSuperHeavy()) {
            gyroList = new String[1];
            gyroList[0] = Mech.GYRO_SHORT_STRING[Mech.GYRO_SUPERHEAVY];
        } else if (isMixed) {
            if (isClan) {
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
            if (isClan) {
                gyroList = new String[1];
                gyroList[0] = Mech.GYRO_SHORT_STRING[0];
            } else {
                gyroList = Mech.GYRO_SHORT_STRING.clone();
            }
        }
        if (getMech().getTechLevel() <= TechConstants.T_INTRO_BOXSET) {
            gyroList = new String[1];
            gyroList[0] = Mech.GYRO_SHORT_STRING[0];
        }
        for (String gyro : gyroList) {
            if (gyro.equals(Mech.GYRO_SHORT_STRING[Mech.GYRO_NONE])
                    && !(getMech().getCockpitType() == Mech.COCKPIT_INTERFACE)) {
                continue;
            }
            if (gyro.equals(Mech.GYRO_SHORT_STRING[Mech.GYRO_SUPERHEAVY])
                    && !getMech().isSuperHeavy()) {
                continue;
            }
            gyroType.addItem(gyro);
        }

        /* ENHANCEMENTS */
        enhancement.removeAllItems();
        enhancement.addItem("None");
        if (getMech().getTechLevel() > TechConstants.T_INTRO_BOXSET) {
            enhancement.addItem("MASC");
            if (!TechConstants.isClan(getMech().getTechLevel())) {
                if (getMech().isIndustrial()) {
                    enhancement.addItem("Industrial TSM");
                } else {
                    enhancement.addItem("TSM");
                }
            }
        }

        /* UNIT UPDATING */
        if (updateUnit) {
            // if we're ultra-light, and not at least advanced any more, revert
            // to 20 tons
            if (!isAdvanced && (getMech().getWeight() < 20)) {
                getMech().setWeight(20);
            }
            // if we're clan or not at least advanced, and super heavy, revert
            // to 100 tons
            if ((isClan || !isAdvanced) && (getMech().getWeight() > 100)) {
                getMech().setWeight(100);
                getMech().clearCockpitCrits();
                getMech().addCockpit();
                UnitUtil.resetCriticalsAndMounts(getMech());
            }
            setArmorCombo(getMech().getArmorType(0));
            if (armorCombo.getSelectedIndex() == -1) {
                armorCombo.setSelectedIndex(0);
                UnitUtil.removeISorArmorMounts(getMech(), false);
                createArmorMountsAndSetArmorType();
                armor.resetArmorPoints();
            }
            int selEngine = convertEngineType(getMech().getEngine()
                    .getEngineType());
            if (engineType.getItemCount() <= selEngine) {
                selEngine = -1;
            }
            engineType.setSelectedIndex(selEngine);
            if (engineType.getSelectedIndex() == -1) {
                engineType.setSelectedIndex(0);
                resetEngine();
            }
            setStructureCombo();
            if (structureCombo.getSelectedIndex() == -1) {
                structureCombo.setSelectedIndex(0);
                UnitUtil.removeISorArmorMounts(getMech(), true);
                createISMounts();
            }
            cockpitType.setSelectedIndex(-1);
            String selItem = Mech.COCKPIT_SHORT_STRING[getMech()
                    .getCockpitType()];
            for (int i = 0; i < cockpitType.getItemCount(); i++) {
                if (cockpitType.getItemAt(i).equals(selItem)) {
                    cockpitType.setSelectedIndex(i);
                    break;
                }
            }
            if (cockpitType.getSelectedIndex() == -1) {
                cockpitType.setSelectedIndex(0);
                getMech().clearCockpitCrits();
                getMech().addCockpit();
            }
            setHeatSinkCombo();
            if (heatSinkType.getSelectedIndex() == -1) {
                heatSinkType.setSelectedIndex(0);
                UnitUtil.updateHeatSinks(getMech(),
                        (Integer) heatSinkNumber.getValue(), "Single");
            }
            setJumpJetCombo();
            if (jjType.getSelectedIndex() == -1) {
                jjType.setSelectedIndex(0);
                int jump = Math.min((Integer) jumpModel.getValue(), getMech()
                        .getWalkMP(true, false, true));
                UnitUtil.updateJumpJets(getMech(), jump, Mech.JUMP_STANDARD);
            }
            String gyroName = Mech.GYRO_SHORT_STRING[getMech().getGyroType()];
            gyroType.setSelectedItem(gyroName);
            if (gyroType.getSelectedIndex() == -1) {
                getMech().clearGyroCrits();
                getMech().addGyro();
            }

            setEnhancementCombo();
            if (enhancement.getSelectedIndex() == -1) {
                enhancement.setSelectedIndex(0);
                UnitUtil.updateEnhancements(getMech(), false, false);
            }
        }

    }

    private void setStructureCombo() {

        if (getMech().isMixedTech()) {
            String structName;
            if (getMech().isClan()) {
                structName = EquipmentType.structureNames[getMech()
                        .getStructureType()];
                if ((getMech().getStructureType() != EquipmentType.T_STRUCTURE_STANDARD)
                        && (getMech().getStructureType() != EquipmentType.T_STRUCTURE_INDUSTRIAL)) {
                    structName = structName + " (IS)";
                }
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
            structureCombo.setSelectedIndex(-1);
            structureCombo.setSelectedItem(structName);
        } else {
            int structType = getMech().getStructureType();
            String structTypeName =
                    EquipmentType.getStructureTypeName(structType);
            structureCombo.setSelectedItem(structTypeName);
        }
    }

    private void setJumpJetCombo() {
        int selIndex = Math.max(0, getMech().getJumpType() - 1);
        // Hack because of hidden disposable jump packs
        if (selIndex == 5) {
            selIndex--;
        }
        if (jjType.getItemCount() <= selIndex) {
            selIndex = -1;
        }
        jjType.setSelectedIndex(selIndex);
    }

    private void setEnhancementCombo() {
        String selEnhance = "None";
        if ((getMech().hasMASC() && !getMech().hasWorkingMisc(MiscType.F_MASC,
                MiscType.S_SUPERCHARGER))
                || getMech().hasMASCAndSuperCharger()) {
            selEnhance = "MASC";
        } else if (getMech().hasIndustrialTSM()) {
            selEnhance = "Industrial TSM";
        } else if (getMech().hasTSM()) {
            selEnhance = "TSM";
        }
        enhancement.setSelectedIndex(-1);
        for (int i = 0; i < enhancement.getItemCount(); i++) {
            if (enhancement.getItemAt(i).equals(selEnhance)) {
                enhancement.setSelectedIndex(i);
                break;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            removeAllListeners();
            if (spinner.equals(weightClass)) {
                boolean changedSuperHeavyStatus = false;
                if ((getMech().isSuperHeavy() 
                        && ((Integer) weightClass.getValue() <= 100))
                        || (!getMech().isSuperHeavy() 
                                && ((Integer) weightClass.getValue() > 100))) {
                    // if we switch from being superheavy to not being superheavy,
                    // remove crits
                    for (Mounted mount : getMech().getEquipment()) {
                        if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                            UnitUtil.removeCriticals(getMech(), mount);
                            UnitUtil.changeMountStatus(getMech(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
                        }
                    }
                    changedSuperHeavyStatus = true;
                }
                getMech().setWeight((Integer) weightClass.getValue());
                getMech().autoSetInternal();
                populateChoices(true);
                if (changedSuperHeavyStatus) {
                    // Interal structure crits may change
                    UnitUtil.removeISorArmorMounts(getMech(), true);
                    createISMounts();
                    getMech().clearGyroCrits();
                    getMech().addGyro();
                }
                resetEngine();
            } else if (spinner.equals(walkMPBase)) {
                resetEngine();
            } else if (spinner.equals(jumpMPBase)) {
                UnitUtil.updateJumpJets(getMech(), (Integer) jumpModel.getValue(),
                        getJumpJetType());
            } else if (spinner.equals(armorTonnage)) {
                setArmorTonnage();
            } else if (spinner.equals(heatSinkNumber)) {
                UnitUtil.updateHeatSinks(getMech(), (Integer) heatSinkNumber
                        .getValue(), heatSinkType.getSelectedItem().toString());
            } else if (spinner.equals(baseChassisHeatSinks)) {
                getMech().getEngine().setBaseChassisHeatSinks(
                        Math.max(0, (Integer) baseChassisHeatSinks.getValue()));
                UnitUtil.updateAutoSinks(getMech(),
                        (String) heatSinkType.getSelectedItem());
            }
            addAllListeners();

            refresh.refreshAll();
        }
    }

    private boolean resetEngine() {
        boolean retVal = false;
        //do {
            Mech mech = getMech();
            int rating = ((Integer) walkMPBase.getValue())
                    * ((Integer) weightClass.getValue());
            if (mech.isPrimitive()) {
                double dRating = ((Integer) walkMPBase.getValue())
                        * ((Integer) weightClass.getValue());
                dRating *= 1.2;
                if ((dRating % 5) != 0) {
                    dRating = (dRating - (dRating % 5)) + 5;
                }
                rating = (int) dRating;
            }
            if ((rating > 400) && (mech.getGyroType() == Mech.GYRO_XL)) {
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
                mech.clearEngineCrits();
                System.out.println("Setting new engine rating.");
                // Create new engine
                Engine newEngine = new Engine(rating, convertEngineType(engineType
                        .getSelectedItem().toString()), clanEngineFlag
                        | superHeavyEngineFlag);
                // Make sure we keep same number of base heat sinks for omnis
                newEngine.setBaseChassisHeatSinks(mech.getEngine()
                        .getBaseChassisHeatSinks(mech.hasCompactHeatSinks()));
                // Add new engine
                mech.setEngine(newEngine);
                System.out.println("Adding engine crits.");
                mech.addEngineCrits();
                int autoSinks = mech.getEngine()
                        .getWeightFreeEngineHeatSinks();
                System.out.println("Updating # engine heat sinks to "
                        + autoSinks);
                UnitUtil.updateAutoSinks(mech,
                        (String) heatSinkType.getSelectedItem());
                retVal = true;
            }
            // We need a minimum of 1 here...
            // most useful when we lower tonnage from the default 25 w/out changing settings
            // as that can return a bad engine
            /*if (getMech().getOriginalWalkMP() < 1) {
                walkMP.setValue(((Integer) walkMP.getValue()) + 1);
            }
        } while (getMech().getOriginalWalkMP() < 1);*/
        return retVal;
    }

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getMech());
        armorTonnage.setValue(maxArmor);
        getMech().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
    }
    
    private void useRemainingTonnageArmor() {
    	double currentTonnage = UnitUtil.getEntityVerifier(getMech())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getMech());
        double totalTonnage = getMech().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getMech()));
        armorTonnage.setValue(maxArmor);
        getMech().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();        
    }

    private void createArmorMountsAndSetArmorType() {

        if (getArmorType(armorCombo) == EquipmentType
                .getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK)) {
            JComboBox<String> headArmor = new JComboBox<String>();
            headArmor.setName("head");
            JComboBox<String> laArmor = new JComboBox<String>();
            laArmor.setName("la");
            JComboBox<String> ltArmor = new JComboBox<String>();
            ltArmor.setName("lt");
            JComboBox<String> ctArmor = new JComboBox<String>();
            ctArmor.setName("ct");
            JComboBox<String> rtArmor = new JComboBox<String>();
            rtArmor.setName("rt");
            JComboBox<String> raArmor = new JComboBox<String>();
            raArmor.setName("ra");
            JComboBox<String> llArmor = new JComboBox<String>();
            llArmor.setName("ll");
            JComboBox<String> rlArmor = new JComboBox<String>();
            rlArmor.setName("rl");
            boolean isMixed = getMech().isMixedTech();
            boolean isClan = getMech().isClan();
            for (int index = 0; index < (EquipmentType.armorNames.length); index++) {
                EquipmentType et;
                if (!isMixed) {
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(
                            index, isClan));
                    if ((et != null)
                            && et.hasFlag(MiscType.F_MECH_EQUIPMENT)
                            && (TechConstants.isLegal(getMech().getTechLevel(),
                                    et.getTechLevel(getMech().getYear()),
                                    isMixed))) {
                        headArmor.addItem(EquipmentType.armorNames[index]);
                        laArmor.addItem(EquipmentType.armorNames[index]);
                        ltArmor.addItem(EquipmentType.armorNames[index]);
                        ctArmor.addItem(EquipmentType.armorNames[index]);
                        rtArmor.addItem(EquipmentType.armorNames[index]);
                        raArmor.addItem(EquipmentType.armorNames[index]);
                        llArmor.addItem(EquipmentType.armorNames[index]);
                        rlArmor.addItem(EquipmentType.armorNames[index]);
                    }
                } else {
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(
                            index, true));
                    if (et != null) {
                        headArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        laArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        ltArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        ctArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        rtArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        raArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        llArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                        rlArmor.addItem(EquipmentType.getArmorTypeName(index,
                                true));
                    }
                    et = EquipmentType.get(EquipmentType.getArmorTypeName(
                            index, false));
                    if (et != null) {
                        headArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        laArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        ltArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        ctArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        rtArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        raArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        llArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                        rlArmor.addItem(EquipmentType.getArmorTypeName(index,
                                false));
                    }
                }
            }
            setArmorType(headArmor, getMech().getArmorType(Mech.LOC_HEAD), false);
            setArmorType(laArmor, getMech().getArmorType(Mech.LOC_LARM), false);
            setArmorType(ltArmor, getMech().getArmorType(Mech.LOC_LT), false);
            setArmorType(ctArmor, getMech().getArmorType(Mech.LOC_CT), false);
            setArmorType(rtArmor, getMech().getArmorType(Mech.LOC_RT), false);
            setArmorType(raArmor, getMech().getArmorType(Mech.LOC_RARM), false);
            setArmorType(llArmor, getMech().getArmorType(Mech.LOC_LLEG), false);
            setArmorType(rlArmor, getMech().getArmorType(Mech.LOC_RLEG), false);
            JLabel headLabel = new JLabel("Head:");
            JLabel laLabel = new JLabel(getMech() instanceof QuadMech ?
                "Front Left Leg:" : "Left Arm:");
            JLabel ltLabel = new JLabel("Left Torso:");
            JLabel ctLabel = new JLabel("Center Torso:");
            JLabel rtLabel = new JLabel("Right Torso:");
            JLabel raLabel = new JLabel(getMech() instanceof QuadMech ?
                "Front Right Leg:" : "Right Arm:");
            JLabel llLabel = new JLabel(getMech() instanceof QuadMech ?
                "Rear Left Leg:" : "Left Leg:");
            JLabel rlLabel = new JLabel(getMech() instanceof QuadMech ?
                "Rear Right Leg:" : "Right Leg:");
            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(headLabel, GBC.std());
            panel.add(headArmor, GBC.eol());
            panel.add(laLabel, GBC.std());
            panel.add(laArmor, GBC.eol());
            panel.add(ltLabel, GBC.std());
            panel.add(ltArmor, GBC.eol());
            panel.add(ctLabel, GBC.std());
            panel.add(ctArmor, GBC.eol());
            panel.add(rtLabel, GBC.std());
            panel.add(rtArmor, GBC.eol());
            panel.add(raLabel, GBC.std());
            panel.add(raArmor, GBC.eol());
            panel.add(llLabel, GBC.std());
            panel.add(llArmor, GBC.eol());
            panel.add(rlLabel, GBC.std());
            panel.add(rlArmor, GBC.eol());
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(headArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_HEAD);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(laArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_LARM);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(ltArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_LT);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(ctArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_CT);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(rtArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_RT);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(raArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_RARM);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(llArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_LLEG);
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(rlArmor))
                    .getTechLevel(getMech().getYear()), Mech.LOC_RLEG);
            getMech().setArmorType(getArmorType(headArmor), Mech.LOC_HEAD);
            getMech().setArmorType(getArmorType(laArmor), Mech.LOC_LARM);
            getMech().setArmorType(getArmorType(ltArmor), Mech.LOC_LT);
            getMech().setArmorType(getArmorType(ctArmor), Mech.LOC_CT);
            getMech().setArmorType(getArmorType(rtArmor), Mech.LOC_RT);
            getMech().setArmorType(getArmorType(raArmor), Mech.LOC_RARM);
            getMech().setArmorType(getArmorType(llArmor), Mech.LOC_LLEG);
            getMech().setArmorType(getArmorType(rlArmor), Mech.LOC_RLEG);
            for (int i = 0; i < getMech().locations(); i++) {
                int armorCount = 0;
                switch (getMech().getArmorType(i)) {
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
                        if (TechConstants.isClan(getMech().getArmorTechLevel(i))) {
                            armorCount = 1;
                        } else {
                            armorCount = 2;
                        }
                        break;
                    default:
                        break;
                }
                if (armorCount < 1) {
                    continue;
                }

                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(
                                new Mounted(getMech(),
                                        EquipmentType.get(EquipmentType
                                                .getArmorTypeName(
                                                        getMech().getArmorType(i),
                                                        getMech().isClan()))), i,
                                false);
                    } catch (LocationFullException ex) {
                        JOptionPane
                                .showMessageDialog(
                                        null,
                                        EquipmentType.getArmorTypeName(getMech()
                                                .getArmorType(i))
                                                + " does not fit in location "
                                                + getMech().getLocationName(i)
                                                + ". Resetting to Standard Armor in this location.",
                                        "Error",
                                        JOptionPane.INFORMATION_MESSAGE);
                        getMech().setArmorType(EquipmentType.T_ARMOR_STANDARD, i);
                    }
                }
            }
            if (!getMech().hasPatchworkArmor()) {
                setArmorType(armorCombo, EquipmentType.T_ARMOR_STANDARD, false);
            }
        } else {
            getMech().setArmorTechLevel(EquipmentType.get(getArmorType(armorCombo))
                    .getTechLevel(getMech().getYear()));
            getMech().setArmorType(getArmorType(armorCombo));
            int armorCount = 0;

            armorCount = EquipmentType.get(getArmorType(armorCombo))
                    .getCriticals(getMech());

            if (armorCount < 1) {
                return;
            }
            // auto-place stealth crits
            if (getMech().getArmorType(0) == EquipmentType.T_ARMOR_STEALTH) {
                Mounted mount = UnitUtil.createSpreadMounts(
                        getMech(),
                        EquipmentType.get(EquipmentType.getArmorTypeName(
                                getMech().getArmorType(0), false)));
                if (mount == null) {
                    JOptionPane.showMessageDialog(null,
                            "Stealth Armor does not fit in location.",
                            "Resetting to Standard Armor",
                            JOptionPane.INFORMATION_MESSAGE);
                    setArmorCombo(EquipmentType.T_ARMOR_STANDARD);
                    getMech().setArmorTechLevel(EquipmentType.get(
                            getArmorType(armorCombo)).getTechLevel(
                            getMech().getYear()));
                    getMech().setArmorType(getArmorType(armorCombo));
                }
            } else {
                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(new Mounted(getMech(),
                                EquipmentType.get(getArmorType(armorCombo))),
                                Entity.LOC_NONE, false);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void setArmorCombo(int type) {
        armorCombo.setSelectedIndex(-1);

        for (int index = 0; index < armorCombo.getItemCount(); index++) {
            if (getMech().isMixedTech()) {
                if (EquipmentType.getArmorTypeName(type,
                        TechConstants.isClan(getMech().getArmorTechLevel(0)))
                        .equals(armorCombo.getItemAt(index))) {
                    armorCombo.setSelectedIndex(index);
                }
            } else {
                if (EquipmentType.getArmorTypeName(type).equals(
                        armorCombo.getItemAt(index))) {
                    armorCombo.setSelectedIndex(index);
                }
            }
        }
    }

    private int getJumpJetType() {
        int retVal = jjType.getSelectedIndex() + 1;
        // Hack to hide Disposable Jump Packs
        if (retVal == jjTypes.length) {
            retVal++;
        }
        return retVal;
    }

    private String getArmorType(JComboBox<String> combo) {
        String armorType = combo.getSelectedItem().toString();
        if (armorType.equals(EquipmentType
                .getArmorTypeName(EquipmentType.T_ARMOR_PATCHWORK))) {
            return armorType;
        }
        if (!getMech().isMixedTech()) {
            String prefix = getMech().isClan() ? "Clan " : "IS ";
            for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
                if (armorType.equals(EquipmentType.armorNames[pos])) {
                    return prefix + armorType;
                }
            }
        } else {
            for (int pos = 0; pos < EquipmentType.armorNames.length; pos++) {
                if (armorType.equals(EquipmentType.getArmorTypeName(pos, true))) {
                    return armorType;
                }
                if (armorType
                        .equals(EquipmentType.getArmorTypeName(pos, false))) {
                    return armorType;
                }
            }
        }

        return EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_STANDARD,
                getMech().isClan());
    }

    private void setArmorTonnage() {
        double armorTons = Math.round(((Double) armorTonnage.getValue()) * 2) / 2.0;
        getMech().setArmorTonnage(armorTons);
        armor.resetArmorPoints();
    }

    private boolean hasTSM() {
        return ((String) enhancement.getSelectedItem()).contains("TSM");
    }

    private boolean hasMASC() {
        return ((String) enhancement.getSelectedItem()).contains("MASC");
    }

    public void setAsCustomization() {
        chassis.setEditable(false);
        chassis.setEnabled(false);
        weightClass.setEnabled(false);
        era.setEditable(false);
        era.setEnabled(false);
        motiveType.setEnabled(false);
    }

    private void setHeatSinkCombo() {
        int selIndex = -1;
        if (getMech().isMixedTech()) {
            if (getMech().isClan()) {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    selIndex = 3;
                } else if (getMech().hasCompactHeatSinks()) {
                    selIndex = 4;
                } else if (getMech().hasDoubleHeatSinks()) {

                    if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                        selIndex = 1;
                    } else {
                        selIndex = 2;
                    }

                } else {
                    selIndex = 0;
                }
            } else {
                if (UnitUtil.hasLaserHeatSinks(getMech())) {
                    selIndex = 3;
                } else if (getMech().hasCompactHeatSinks()) {
                    selIndex = 4;
                } else if (getMech().hasDoubleHeatSinks()) {
                    if (UnitUtil.hasClanDoubleHeatSinks(getMech())) {
                        selIndex = 1;
                    } else {
                        selIndex = 2;
                    }
                } else {
                    selIndex = 0;
                }
            }
        } else {
            if (UnitUtil.hasLaserHeatSinks(getMech())) {
                selIndex = 2;
            } else if (getMech().hasDoubleHeatSinks()) {
                if (getMech().hasCompactHeatSinks()) {
                    selIndex = 2;
                } else {
                    selIndex = 1;
                }
            } else if (getMech().hasCompactHeatSinks()) {
                selIndex = 2;
            } else {
                selIndex = 0;
            }
        }
        if (heatSinkType.getItemCount() <= selIndex) {
            selIndex = -1;
        }
        heatSinkType.setSelectedIndex(selIndex);
    }

    private void setArmorType(JComboBox<String> combo, int type,
            boolean removeListeners) {
        if (removeListeners) {
            removeAllListeners();
        }
        for (int index = 0; index < combo.getItemCount(); index++) {
            if (EquipmentType.getArmorTypeName(type).equals(
                    combo.getItemAt(index))) {
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
     * Get a list of the internal structure types legal for this unit
     *
     * @return a <code>List<String></code> of the legal IS types, for use in the
     *         IS combobox
     */
    private List<String> getStructureTypes() {
        List<String> structures = new ArrayList<String>();
        for (int i = 0; i < EquipmentType.structureNames.length; i++) {
            // superheavies are restricted to standard, industrial, endo and
            // endo-composite
            if (getMech().isSuperHeavy()) {
                if ((i != EquipmentType.T_STRUCTURE_STANDARD)
                        && (i != EquipmentType.T_STRUCTURE_INDUSTRIAL)
                        && (i != EquipmentType.T_STRUCTURE_ENDO_STEEL)
                        && (i != EquipmentType.T_STRUCTURE_ENDO_COMPOSITE)) {
                    continue;
                }
            }
            EquipmentType et = EquipmentType.get(EquipmentType
                    .getStructureTypeName(i, getMech().isClan()));
            if ((et != null)
                    && TechConstants
                            .isLegal(getMech().getTechLevel(),
                                    et.getTechLevel(getMech().getYear()),
                                    getMech().isMixedTech())) {
                structures.add(et.getName());
            }
            if (getMech().isMixedTech()) {
                et = EquipmentType.get(EquipmentType.getStructureTypeName(i,
                        !getMech().isClan()));
                if ((et != null)
                        && TechConstants.isLegal(getMech().getTechLevel(),
                                et.getTechLevel(getMech().getYear()),
                                getMech().isMixedTech())) {
                    structures.add(et.getName()
                            + (getMech().isClan() ? " (IS)" : " (Clan)"));
                }
            }
        }
        return structures;
    }
}