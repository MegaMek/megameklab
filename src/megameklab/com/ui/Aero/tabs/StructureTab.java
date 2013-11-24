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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import megamek.common.Aero;
import megamek.common.CriticalSlot;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.verifier.TestAero;
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
    private static final String ENGINELIGHT = "Light";
    private static final String ENGINECOMPACT = "Compact";


    String[] isEngineTypes = { ENGINESTANDARD, ENGINEXL, ENGINELIGHT,
            ENGINECOMPACT};
    String[] isIndustrialEngineTypes = { ENGINESTANDARD};
    String[] clanEngineTypes = { ENGINESTANDARD, ENGINEXL};
    String[] clanIndustrialEngineTypes = { ENGINESTANDARD};
    private int clanEngineFlag = 0;
    
    String[] clanHeatSinkTypes = { "Single", "Double"};
    String[] isHeatSinkTypes = { "Single", "Double"};
    
    String[] techTypes = { "Inner Sphere", "Clan", "Mixed Inner Sphere",
    "Mixed Clan" };
    
    String[] isTechLevels = { "Introductory", "Standard", "Advanced",
            "Experimental", "Unoffical" };
    String[] clanTechLevels = { "Standard", "Advanced", "Experimental",
            "Unoffical" };

    private JPanel masterPanel;
    private JPanel panInfo;
    private JPanel panChassis;
    private JPanel panArmor;
    private JPanel panMovement;
    private JPanel panHeat;
    private SummaryView panSummary;
    private ArmorView armor;

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
    private JComboBox<String> structureCombo = 
            new JComboBox<String>(EquipmentType.structureNames);
    private JComboBox<String> engineType = new JComboBox<String>(isEngineTypes);   
    private JComboBox<String> cockpitType = 
            new JComboBox<String>(Aero.COCKPIT_SHORT_STRING);
    private JCheckBox omniCB = new JCheckBox("Omni");
    
    // Movement Panel 
    private JSpinner fuel;
    private JSpinner safeThrust;
    private JTextField maxThrust;
    
    
    // Heat Sinks Panel
    private JComboBox<String> heatSinkType = 
            new JComboBox<String>(isHeatSinkTypes);
    private JSpinner heatSinkNumber;
    private JSpinner baseChassisHeatSinks;

    // Armor Panel
    private JComboBox<String> armorCombo = new JComboBox<String>();
    private JSpinner armorTonnage;
    private JButton maximizeArmorButton = new JButton("Maximize Armor");
    

    public StructureTab(Aero unit) {
        this.unit = unit;
        armor = new ArmorView(getAero());
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
        panSummary = new SummaryView(getAero());

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
        ((JSpinner.DefaultEditor) safeThrust.getEditor()).getTextField()
                .setEditable(false);
        maxThrust = new JTextField();
        maxThrust.setEditable(false);
        setFieldSize(maxThrust, spinnerSize);
        maxThrust.setHorizontalAlignment(SwingConstants.RIGHT);

        weightClass = new JSpinner(new SpinnerNumberModel(20, 10, 100, 5));
        ((JSpinner.DefaultEditor) weightClass.getEditor()).getTextField()
                .setEditable(false);
        
        fuel = new JSpinner(new SpinnerNumberModel(1, 1, 
                ((Integer)weightClass.getValue()).intValue(), 1));
        ((JSpinner.DefaultEditor) fuel.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) fuel.getEditor())
                .setMinimumSize(spinnerSize);

        heatSinkNumber = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spinnerSize = new Dimension(40, 25);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) heatSinkNumber.getEditor()).getTextField()
                .setEditable(false);

        baseChassisHeatSinks = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) baseChassisHeatSinks.getEditor())
                .getTextField().setEditable(false);

        armorTonnage = new JSpinner(new SpinnerNumberModel(
                unit.getArmorWeight(), 0.0, 30.5, 0.5));
        spinnerSize = new Dimension(45, 25);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorTonnage.getEditor()).getTextField()
                .setEditable(false);

        // lblFreeSinks.setFont(new Font(lblFreeSinks.getName(), Font.PLAIN,
        // 10));

        chassis.setText(unit.getChassis());
        model.setText(unit.getModel());

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
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
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
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panMovement.add(createLabel("Fuel:", labelSize), gbc);  
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(fuel, gbc);        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panMovement.add(createLabel("Safe Thrust:", labelSize), gbc);        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panMovement.add(safeThrust, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panMovement.add(createLabel("Max Thrust:", labelSize), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panMovement.add(maxThrust, gbc);
        

        gbc.gridx = 0;
        gbc.gridy = 0;
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
        setFieldSize(cockpitType, comboSize);
        setFieldSize(structureCombo, comboSize);        
        setFieldSize(model, comboSize);
        setFieldSize(chassis, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panInfo);
        leftPanel.add(panChassis);
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
        if (getAero().isPrimitive()) {
            getAero().setOmni(false);
            omniCB.setEnabled(false);
        } else {
            omniCB.setEnabled(true);
        }
        omniCB.setSelected(getAero().isOmni());
        
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

        setStructureCombo();
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

        fuel.setValue(getAero().getFuelTonnage());
        safeThrust.setValue(getAero().getOriginalWalkMP());                
        maxThrust.setText(getAero().getRunMPasString());


        ((SpinnerNumberModel) armorTonnage.getModel()).setMaximum(UnitUtil
                .getMaximumArmorTonnage(unit));
        ((SpinnerNumberModel) armorTonnage.getModel()).setValue(Math.min(UnitUtil
                .getMaximumArmorTonnage(unit), unit.getLabArmorTonnage()));
        
        armorTonnage.setEnabled(true);
        maximizeArmorButton.setEnabled(true);
        
        armor.updateUnit(unit);
        armor.refresh();
        panSummary.updateUnit(unit);
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
                    armor.resetArmorPoints();
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
                UnitUtil.removeISorArmorMounts(unit, false);
                createArmorMountsAndSetArmorType();
                armor.resetArmorPoints();
            } else if (combo.equals(structureCombo)) {
                UnitUtil.removeISorArmorMounts(unit, true);
                createISMounts();
                populateChoices(true);
            } else if (combo.equals(heatSinkType)) {
                getAero().setHeatSinks((Integer) heatSinkNumber.getValue());
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
                armor.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(unit);
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
                    if (unit.getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            unit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (unit.getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            unit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        }
                    }
                    unit.setMixedTech(true);

                } else if ((techType.getSelectedIndex() == 3)
                        && (!unit.isMixedTech() || !unit.isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (unit.getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            unit.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (unit.getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_CLAN_TW)) {
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                        }
                    }
                    getAero().setMixedTech(true);
                }
                if (!unit.hasPatchworkArmor()) {
                    UnitUtil.removeISorArmorMounts(unit, false);
                }
                createArmorMountsAndSetArmorType();
                populateChoices(true);
                armor.resetArmorPoints();
                UnitUtil.checkEquipmentByTechLevel(unit);
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
        armorCombo.removeItemListener(this);       
        engineType.removeItemListener(this);
        weightClass.removeChangeListener(this);
        cockpitType.removeItemListener(this);
        heatSinkNumber.removeChangeListener(this);
        heatSinkType.removeItemListener(this);
        safeThrust.removeChangeListener(this);
        techLevel.removeItemListener(this);
        techType.removeItemListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        manualBV.removeKeyListener(this);
        omniCB.removeActionListener(this);
        structureCombo.removeItemListener(this);
        baseChassisHeatSinks.removeChangeListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        armorTonnage.removeChangeListener(this);
    }

    public void addAllListeners() {
        maximizeArmorButton.addActionListener(this);
        armorCombo.addItemListener(this);
        engineType.addItemListener(this);
        weightClass.addChangeListener(this);
        cockpitType.addItemListener(this);
        heatSinkNumber.addChangeListener(this);
        heatSinkType.addItemListener(this);
        safeThrust.addChangeListener(this);
        techLevel.addItemListener(this);
        techType.addItemListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        manualBV.addKeyListener(this);
        omniCB.addActionListener(this);
        structureCombo.addItemListener(this);
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
            armor.resetArmorPoints();
            UnitUtil.checkEquipmentByTechLevel(unit);
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
            unit.setChassis(chassis.getText().trim());
        } else if (e.getSource().equals(model)) {
            unit.setModel(model.getText().trim());
        }
        addAllListeners();
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    private void createISMounts() {
        int isCount = 0;
        int structType = structureCombo.getSelectedIndex();
        String structName = EquipmentType.getStructureTypeName(structType, getAero().isClan());

        if (getAero().isMixedTech()) {
            structName = structureCombo.getSelectedItem().toString();
            boolean clanStruct = getAero().isClan() ? structName
                    .indexOf(" (IS)") == -1
                    : structName.indexOf(" (Clan)") > -1;

            structName = structureCombo.getSelectedItem().toString()
                    .replace(" (IS)", "").replace(" (Clan)", "");

            if (clanStruct) {
                structName = String.format("Clan %1$s", structName);
            } else {
                structName = String.format("IS %1$s", structName);
            }
        }

        getAero().setStructureType(structName);
        isCount = EquipmentType.get(structName).getCriticals(getAero());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getAero().addEquipment(
                        new Mounted(getAero(), EquipmentType.get(structName)),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
                    case Engine.FUEL_CELL:
                        return 7;
                    case Engine.XXL_ENGINE:
                        return 9;
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
            else if (!getAero().isClan()
                    && getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
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
        } else if (getAero().getEngine().hasFlag(Engine.CLAN_ENGINE)) {
            if (getAero().isPrimitive()) {
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
            if (getAero().isPrimitive()) {
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
        for (int index = 0; index < (EquipmentType.armorNames.length); index++) {
            EquipmentType et;
            if (!isMixed) {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, isClan));
                if (((index == EquipmentType.T_ARMOR_PATCHWORK) && isExperimental) || ((et != null) && et.hasFlag(MiscType.F_MECH_EQUIPMENT) && (TechConstants.isLegal(getAero().getTechLevel(), et.getTechLevel(getAero().getYear()), isMixed)))) {
                    armorCombo.addItem(EquipmentType.armorNames[index]);
                }
            } else {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, true));
                if (et != null) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index, true));
                }
                et = EquipmentType.get(EquipmentType.getArmorTypeName(index, false));
                if (et != null) {
                    armorCombo.addItem(EquipmentType.getArmorTypeName(index, false));
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

                switch (getAero().getTechLevel()) {
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
                switch (getAero().getTechLevel()) {
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

        ((SpinnerNumberModel)fuel.getModel()).setMaximum(
                (Integer)weightClass.getValue());
 
        /* UNIT UPDATING */
        if (updateUnit) {
            setArmorCombo(getAero().getArmorType(0));
            if (armorCombo.getSelectedIndex() == -1) {
                armorCombo.setSelectedIndex(0);
                UnitUtil.removeISorArmorMounts(unit, false);
                createArmorMountsAndSetArmorType();
                armor.resetArmorPoints();
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
            setStructureCombo();
            if (structureCombo.getSelectedIndex() == -1) {
                structureCombo.setSelectedIndex(0);
                UnitUtil.removeISorArmorMounts(getAero(), true);
                createISMounts();
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

    private void setStructureCombo() {

        if (getAero().isMixedTech()) {
            String structName;
            if (getAero().isClan()) {
                structName = EquipmentType.structureNames[getAero()
                        .getStructureType()];
                if ((getAero().getStructureType() != EquipmentType.T_STRUCTURE_STANDARD) &&
                        (getAero().getStructureType() != EquipmentType.T_STRUCTURE_INDUSTRIAL)) {
                    structName = structName + " (IS)";
                }
                if (getAero()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL];
                } else if (getAero()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE];
                }
            } else {
                structName = EquipmentType.structureNames[getAero()
                        .getStructureType()];
                if (getAero()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_STEEL]
                            + " (Clan)";
                } else if (getAero()
                        .hasWorkingMisc(
                                "Clan "
                                        + EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE])) {
                    structName = EquipmentType.structureNames[EquipmentType.T_STRUCTURE_ENDO_COMPOSITE]
                            + " (Clan)";
                }
            }
            structureCombo.setSelectedIndex(-1);
            for (int pos = 0; pos < structureCombo.getItemCount(); pos++) {
                if (structureCombo.getItemAt(pos).equals(structName)) {
                    structureCombo.setSelectedIndex(pos);
                    break;
                }
            }
        } else {
            int selIndex = getAero().getStructureType();
            if (structureCombo.getItemCount() <= selIndex) {
                selIndex = -1;
            }
            structureCombo.setSelectedIndex(selIndex);
        }
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            removeAllListeners();
            if (spinner.equals(weightClass)) {
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
                    getAero().setWeight((Integer) weightClass.getValue());
                    getAero().autoSetInternal();
                    engineType.setSelectedIndex(engineType.getSelectedIndex());
                }
                populateChoices(true);
            } else if (spinner.equals(safeThrust)) {
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
                    System.out.println("Setting new engine rating.");
                    getAero().setEngine(
                            new Engine(rating, convertEngineType(engineType
                                    .getSelectedItem().toString()),
                                    clanEngineFlag));
                }
            } else if (spinner.equals(fuel)) {
                getAero().setFuelTonnage((Integer)fuel.getValue());
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

    private void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(unit);
        armorTonnage.setValue(maxArmor);
        unit.setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
    }

    private void createArmorMountsAndSetArmorType() {
        unit.setArmorTechLevel(EquipmentType.get(
                getArmorType(armorCombo)).getTechLevel(getAero().getYear()));
        unit.setArmorType(getArmorType(armorCombo));
        int armorCount = 0;

        armorCount = EquipmentType.get(getArmorType(armorCombo))
                .getCriticals(unit);

        if (armorCount < 1) {
            return;
        }

        for (; armorCount > 0; armorCount--) {
            try {
                getAero().addEquipment(
                        new Mounted(unit,
                                EquipmentType.get(getArmorType(armorCombo))),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
            }
        }
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

    private void setArmorTonnage() {
        unit.setArmorTonnage(((Double) armorTonnage.getValue()));
        armor.resetArmorPoints();
    }


    public void setAsCustomization() {
        chassis.setEditable(false);
        chassis.setEnabled(false);
        weightClass.setEnabled(false);
        era.setEditable(false);
        era.setEnabled(false);
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
     * Get a list of the internal structure types legal for this unit
     * @return a <code>List<String></code> of the legal IS types, for use
     * in the IS combobox
     */
    private List<String> getStructureTypes() {
        List<String> structures = new ArrayList<String>();
        for (int i = 0; i < EquipmentType.structureNames.length; i++) {
            EquipmentType et = EquipmentType.get(EquipmentType.getStructureTypeName(i, unit.isClan()));
            if ((et != null) && TechConstants.isLegal(unit.getTechLevel(), et.getTechLevel(unit.getYear()), unit.isMixedTech())) {
                structures.add(et.getName());
            }
            if (unit.isMixedTech()) {
                et = EquipmentType.get(EquipmentType.getStructureTypeName(i, !unit.isClan()));
                if ((et != null) && TechConstants.isLegal(unit.getTechLevel(), et.getTechLevel(unit.getYear()), unit.isMixedTech())) {
                    structures.add(et.getName()+(unit.isClan()?" (IS)":" (Clan)"));
                }
            }
        }
        return structures;
    }
}
