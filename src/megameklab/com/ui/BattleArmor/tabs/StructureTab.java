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

package megameklab.com.ui.BattleArmor.tabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.swing.MechViewPanel;
import megamek.common.BattleArmor;
import megamek.common.EntityMovementMode;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.MechView;
import megamek.common.TechConstants;
import megamek.common.verifier.TestBattleArmor;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, KeyListener, ChangeListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -7985608549543235815L;

    private RefreshListener refresh;

    private static final String MOVE_UMU = "UMU";
    private static final String MOVE_VTOL = "VTOL";

    private String[] techTypes =
        { "Inner Sphere", "Clan", "Mixed Inner Sphere", "Mixed Clan" };
    private JComboBox<String> techType = new JComboBox(techTypes);
    private String[] isTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private JComboBox<String> techLevel = new JComboBox(isTechLevels);
    private String[] chassisTypeArray =
        { "Humanoid", "Quad"};
    private JComboBox<String> chassisType = new JComboBox(chassisTypeArray);
    private JComboBox<String> weightClass;
    private String[] jumpTypeArray =
        { "Jump",MOVE_VTOL,MOVE_UMU};
    private JComboBox<String> jumpType = new JComboBox(jumpTypeArray);
    private JComboBox<String> armorType;

    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);

    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);

    private JTextField txtPrimary = new JTextField("None");
    private JTextField txtSecondary = new JTextField("None");

    private JSpinner walkMP;
    private JSpinner jumpMP;
    private JSpinner numTroopers; 
    private JSpinner armorPoints;
    
    private MechViewPanel panelMekView;

	public StructureTab(BattleArmor unit) {
        this.unit = unit;
        setUpPanels();
        refresh();
	}

	public void setUpPanels() {
	    JPanel previewPanel = new JPanel();
	    previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
	    panelMekView = new MechViewPanel(450, 550,false);
	    //mekViewScrollPane.setMinimumSize(new java.awt.Dimension(450, 550));
	    //mekViewScrollPane.setMaximumSize(new java.awt.Dimension(450, 550));
	    //mekViewScrollPane.setPreferredSize(new java.awt.Dimension(450, 550));
	    previewPanel.add(panelMekView);
	    
	    JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel basicPanel = new JPanel(new GridBagLayout());
        JPanel chassisPanel = new JPanel(new GridBagLayout());
        JPanel armorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(200, 25);
        Dimension labelSize = new Dimension(110, 25);
        Dimension spinnerSize = new Dimension(55, 25);

        // General IS BA squads are sized 4, Clan 5, and some IS squads 6 (WOB)
        //  I found no rules for min or max sizes, so I'm going to allow 
        //  selection of 4, 5 and 6
        numTroopers = new JSpinner(new SpinnerNumberModel(4, 4, 6, 1));
        ((JSpinner.DefaultEditor) numTroopers.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) numTroopers.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) numTroopers.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) numTroopers.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) numTroopers.getEditor()).getTextField()
                .setEditable(false);
        
        txtPrimary.setEditable(false);
        txtSecondary.setEditable(false);

        chassis.setText(unit.getChassis());
        model.setText(unit.getModel());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,0,1,2);
        basicPanel.add(createLabel("Chassis:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(chassis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        basicPanel.add(createLabel("Model:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(model, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        basicPanel.add(createLabel("Year:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(era, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        basicPanel.add(createLabel("Source/Era:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(source, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        basicPanel.add(createLabel("Tech:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(techType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        basicPanel.add(createLabel("Tech Level:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(techLevel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        basicPanel.add(createLabel("Squad Size:", labelSize), gbc);
        gbc.gridx = 1;
        basicPanel.add(numTroopers, gbc);

        walkMP = new JSpinner(new SpinnerNumberModel(1, getBattleArmor().getMinimumWalkMP(), getBattleArmor().getMaximumWalkMP(), 1));
        ((JSpinner.DefaultEditor) walkMP.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setEditable(false);

        jumpMP = new JSpinner(new SpinnerNumberModel(0, 0, getBattleArmor().getMaximumJumpMP(), 1));
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) jumpMP.getEditor()).getTextField()
                .setEditable(false);

        DefaultComboBoxModel<String> modelWeightClass = new DefaultComboBoxModel<String>();
        for(int i = EntityWeightClass.WEIGHT_ULTRA_LIGHT; i <= EntityWeightClass.WEIGHT_ASSAULT; i++) {
            modelWeightClass.addElement(EntityWeightClass.getClassName(i, unit));
        }
        weightClass = new JComboBox(modelWeightClass);
        gbc.gridx = 0;
        gbc.gridy = 0;
        chassisPanel.add(createLabel("Body Type:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(chassisType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        chassisPanel.add(createLabel("Weight Class", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(weightClass, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        chassisPanel.add(createLabel("Ground MP:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(walkMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        chassisPanel.add(createLabel("Jump MP:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(jumpMP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        chassisPanel.add(createLabel("Move Mode:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(jumpType, gbc);
 
        armorType = new JComboBox();
        armorPoints = new JSpinner(new SpinnerNumberModel(0, 0, getBattleArmor().getMaximumArmorPoints(), 1));
        ((JSpinner.DefaultEditor) armorPoints.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorPoints.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorPoints.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorPoints.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) armorPoints.getEditor()).getTextField()
                .setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        armorPanel.add(createLabel("Armor Type:", labelSize), gbc);
        gbc.gridx = 1;
        armorPanel.add(armorType, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        armorPanel.add(createLabel("Amount:", labelSize), gbc);
        gbc.gridx = 1;
        armorPanel.add(armorPoints, gbc);

        setFieldSize(chassisType, comboSize);
        setFieldSize(armorType, comboSize);
        setFieldSize(weightClass, comboSize);
        setFieldSize(jumpType, comboSize);
        setFieldSize(era, comboSize);
        setFieldSize(source, comboSize);
        setFieldSize(chassis, comboSize);
        setFieldSize(model, comboSize);
        setFieldSize(techType, comboSize);
        setFieldSize(techLevel, comboSize);
        setFieldSize(txtPrimary, comboSize);
        setFieldSize(txtSecondary, comboSize);


        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        chassisPanel.setBorder(BorderFactory.createTitledBorder("Chassis"));
        armorPanel.setBorder(BorderFactory.createTitledBorder("Armor"));
        //weaponView.setBorder(BorderFactory.createTitledBorder("Weapon Selection"));

        leftPanel.add(basicPanel);
        leftPanel.add(chassisPanel);
        leftPanel.add(armorPanel);
        leftPanel.add(Box.createVerticalGlue());
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,30,0,30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0;
        add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        //gbc.weightx = 1.0;
        //gbc.weighty = 1.0;
        add(previewPanel, gbc);
        //add(weaponView, gbc);

        getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
        refresh();
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

	public void refresh() {
	    removeAllActionListeners();
	    
        boolean isClan = getBattleArmor().isClan();
        boolean isMixed = getBattleArmor().isMixedTech();
        boolean isExperimental = (getBattleArmor().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);
	    
        era.setText(Integer.toString(getInfantry().getYear()));
        source.setText(getBattleArmor().getSource());

        if (getInfantry().isClan()) {
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

        if (isMixed) {
            if (isClan) {

                techType.setSelectedIndex(3);
                if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);
                if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            }
        } else if (isClan) {

            techType.setSelectedIndex(1);
            if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else { // Innersphere
            techType.setSelectedIndex(0);

            if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        }

        chassisType.removeAllItems();
        if(unit.getWeightClass() == EntityWeightClass.WEIGHT_ULTRA_LIGHT) {
            // PA(L) can only be Biped
            chassisType.addItem(chassisTypeArray[0]);
            chassisType.setSelectedIndex(0);
        } else {
            for(String c : chassisTypeArray) {
                chassisType.addItem(c);
            }
            chassisType.setSelectedIndex(getBattleArmor().getChassisType());
        }

        if (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD){
            // Quad's can't use any form of jump MP
            jumpType.setEnabled(false);
            jumpType.setSelectedIndex(0);
            jumpMP.setEnabled(false);
            jumpMP.getModel().setValue(0);
        } else {
            jumpType.setEnabled(true);
            jumpMP.setEnabled(true);
            int selIdx = jumpType.getSelectedIndex();
            jumpType.removeAllItems();
            if(unit.isClan()) {
                for(String j : jumpTypeArray) {
                    jumpType.addItem(j);
                }
                jumpType.setSelectedIndex(selIdx);
            } else {
                jumpType.addItem(jumpTypeArray[0]);
                jumpType.setSelectedIndex(0);
            }
        }

        weightClass.setSelectedIndex(unit.getWeightClass());

        walkMP.setValue(getBattleArmor().getOriginalWalkMP());
        ((SpinnerNumberModel) walkMP.getModel()).setMinimum(getBattleArmor().getMinimumWalkMP());
        ((SpinnerNumberModel) walkMP.getModel()).setMaximum(getBattleArmor().getMaximumWalkMP());
        jumpMP.setValue(getBattleArmor().getOriginalJumpMP());
        ((SpinnerNumberModel) jumpMP.getModel()).setMaximum(getBattleArmor().getMaximumJumpMP());
        
        armorType.removeAllItems();
        for (TestBattleArmor.BAArmor armor : TestBattleArmor.BAArmor.values()){
            int type = armor.type;
            EquipmentType et;
            if (!isMixed) {
                boolean techMatch = (armor.isClan && unit.isClan()) || 
                        (!armor.isClan && !unit.isClan());
                et = EquipmentType.get(
                        EquipmentType.getArmorTypeName(type, armor.isClan));
                boolean legalTechLvl = (et != null) &&  
                        (TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                                et.getTechLevel(getBattleArmor().getYear()), 
                                isMixed));
                if (techMatch && (isExperimental || legalTechLvl)) {
                    armorType.addItem(EquipmentType.armorNames[type]);
                }
            } else {
                et = EquipmentType.get(EquipmentType.getArmorTypeName(type, true));
                if (et != null) {
                    armorType.addItem(EquipmentType.getArmorTypeName(type, true));
                }
                et = EquipmentType.get(EquipmentType.getArmorTypeName(type, false));
                if (et != null) {
                    armorType.addItem(EquipmentType.getArmorTypeName(type, false));
                }
            }
        }           
        
        armorType.setSelectedItem(EquipmentType.getArmorTypeName(unit.getArmorType(BattleArmor.LOC_SQUAD)));
        // We have to use a LOC_TROOPER as getOArmor has a special case for LOC_SQUAD...
        armorPoints.setValue(getBattleArmor().getOArmor(BattleArmor.LOC_TROOPER_1));
        ((SpinnerNumberModel) armorPoints.getModel()).setMaximum(getBattleArmor().getMaximumArmorPoints());

       // weaponView.updateUnit(unit);
        //weaponView.refresh();
        refreshPreview();
        
        addAllActionListeners();
	}

	public void addAllActionListeners() {
        chassisType.addActionListener(this);
        weightClass.addActionListener(this);
        armorType.addActionListener(this);
        jumpType.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        chassis.addKeyListener(this);
        model.addKeyListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
        walkMP.addChangeListener(this);
        jumpMP.addChangeListener(this);
        numTroopers.addChangeListener(this);
        armorPoints.addChangeListener(this);
    }

    public void removeAllActionListeners() {
        chassisType.removeActionListener(this);
        weightClass.removeActionListener(this);
        armorType.removeActionListener(this);
        jumpType.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        chassis.removeKeyListener(this);
        model.removeKeyListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
        walkMP.removeChangeListener(this);
        jumpMP.removeChangeListener(this);
        numTroopers.removeChangeListener(this);
        armorPoints.removeChangeListener(this);
    }

	public void addRefreshedListener(RefreshListener l) {
	    refresh = l;
	    //weaponView.addRefreshedListener(refresh);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        removeAllActionListeners();
        if (e.getSource() instanceof JComboBox) {
            JComboBox<String> combo = (JComboBox) e.getSource();
            if (combo.equals(techLevel)) {
                int unitTechLevel = techLevel.getSelectedIndex();
                if (getBattleArmor().isClan()) {
                    switch (unitTechLevel) {
                        case 0:
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            techType.setSelectedIndex(1);
                            break;
                        case 1:
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                            break;
                        case 2:
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            break;
                        case 3:
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                            break;
                        default:
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                            break;
                    }

                } else {
                    switch (unitTechLevel) {
                        case 0:
                            getBattleArmor().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            techType.setSelectedIndex(0);
                            break;
                        case 1:
                            getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                            break;
                        case 2:
                            getBattleArmor().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            break;
                        default:
                            getBattleArmor().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                            break;
                    }

                }
                UnitUtil.checkEquipmentByTechLevel(unit);
            }
            else if (combo.equals(techType)) {
                if ((techType.getSelectedIndex() == 1) && (!getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if(!getBattleArmor().isClan()) {
                        int level = TechConstants.getOppositeTechLevel(getBattleArmor().getTechLevel());
                        getBattleArmor().setTechLevel(level);
                        getBattleArmor().setArmorTechLevel(level);
                        getBattleArmor().setTroopers(5);
                        getBattleArmor().autoSetInternal();
                        numTroopers.setValue(5);
                    }
                    getBattleArmor().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 0) && (getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                    techLevel.removeAllItems();

                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }

                    if(getBattleArmor().isClan()) {
                        int level = TechConstants.getOppositeTechLevel(getInfantry().getTechLevel());
                        getBattleArmor().setTechLevel(level);
                        getBattleArmor().setArmorTechLevel(level);
                        getBattleArmor().setTroopers(4);
                        getBattleArmor().autoSetInternal();
                        numTroopers.setValue(4);
                    }
                    getBattleArmor().setMixedTech(false);
                } else if ((techType.getSelectedIndex() == 2) && (!getBattleArmor().isMixedTech() || getBattleArmor().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : isTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (unit.getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            unit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            unit.setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (unit.getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            unit.setTechLevel(TechConstants.T_IS_ADVANCED);
                            unit.setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((unit.getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            unit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            unit.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                        }
                    }
                    getBattleArmor().setTroopers(4);
                    getBattleArmor().autoSetInternal();
                    numTroopers.setValue(4);
                    getBattleArmor().setMixedTech(true);
                } else if ((techType.getSelectedIndex() == 3) && (!getBattleArmor().isMixedTech() || !getBattleArmor().isClan())) {
                    techLevel.removeAllItems();
                    for (String item : clanTechLevels) {
                        techLevel.addItem(item);
                    }
                    if (unit.getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            unit.setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (unit.getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            unit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((unit.getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (unit.getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (unit.getTechLevel() != TechConstants.T_CLAN_TW)) {
                            unit.setTechLevel(TechConstants.T_CLAN_TW);
                            unit.setArmorTechLevel(TechConstants.T_CLAN_TW);
                        }
                    }
                    getBattleArmor().setTroopers(5);
                    getBattleArmor().autoSetInternal();
                    numTroopers.setValue(5);
                    getBattleArmor().setMixedTech(true);
                } else {
                    addAllActionListeners();
                    return;
                }
                resetJumpType();
                UnitUtil.checkEquipmentByTechLevel(unit);
            }
            else if (combo.equals(chassisType)) {
                getBattleArmor().setChassisType(chassisType.getSelectedIndex());
                resetMovementMinMax();
            }
            else if (combo.equals(weightClass)) {
                getBattleArmor().setWeightClass(combo.getSelectedIndex());
                if((unit.getWeightClass() == EntityWeightClass.WEIGHT_ULTRA_LIGHT)
                        && (getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD)) {
                    getBattleArmor().setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);
                }
                resetMovementMinMax();
                resetArmorMax();
            }
            else if (combo.equals(jumpType)) {
                resetMovementMode();
            }
            else if (combo.equals(armorType)) {
                getBattleArmor().setArmorType((String)combo.getSelectedItem());
            }
        }
        refresh.refreshAll();
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(era)) {
            try {
                unit.setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                unit.setYear(3145);
            }
        } else if (e.getSource().equals(source)) {
            unit.setSource(source.getText());
        } else if (e.getSource().equals(chassis)) {
            unit.setChassis(chassis.getText().trim());
        } else if (e.getSource().equals(model)) {
            unit.setModel(model.getText().trim());
        }
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    public void setAsCustomization() {
        chassis.setEditable(false);
        chassis.setEnabled(false);
        era.setEditable(false);
        era.setEnabled(false);
    }

    public void stateChanged(ChangeEvent e) {
        removeAllActionListeners();

        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            if (spinner.equals(walkMP)) {
                getBattleArmor().setOriginalWalkMP((Integer) walkMP.getValue());
            }
            else if (spinner.equals(jumpMP)) {
                getBattleArmor().setOriginalJumpMP((Integer) jumpMP.getValue());
                resetMovementMode();
            }
            else if (spinner.equals(armorPoints)) {
                int value = (Integer) armorPoints.getValue();
                for(int i = 0; i < unit.locations(); i++) {
                    unit.initializeArmor(value, i);
                }
            } else if (spinner.equals(numTroopers)){
                int value = (Integer) numTroopers.getValue();
                if (value != getBattleArmor().getTroopers()){
                    getBattleArmor().setTroopers(value);
                    getBattleArmor().autoSetInternal();
                }
            }
        }

        addAllActionListeners();

        refresh.refreshAll();
    }

    public void resetMovementMinMax() {
        if(unit.getOriginalWalkMP() > getBattleArmor().getMaximumWalkMP()) {
            unit.setOriginalWalkMP(getBattleArmor().getMaximumWalkMP());
        }
        else if(unit.getOriginalWalkMP() < getBattleArmor().getMinimumWalkMP()) {
            unit.setOriginalWalkMP(getBattleArmor().getMinimumWalkMP());
        }
        if(unit.getOriginalJumpMP() > getBattleArmor().getMaximumJumpMP()) {
            unit.setOriginalJumpMP(getBattleArmor().getMaximumJumpMP());
        }
    }

    public void resetArmorMax() {
        for(int i = 0; i < unit.locations(); i++) {
            if(unit.getOArmor(i) > getBattleArmor().getMaximumArmorPoints()) {
                unit.initializeArmor(getBattleArmor().getMaximumArmorPoints(), i);
            }
        }
    }

    public void resetMovementMode() {
        EntityMovementMode nMotion = EntityMovementMode.INF_LEG;
        if(getBattleArmor().getOriginalJumpMP() > 0) {
            if(jumpType.getSelectedItem().equals(MOVE_VTOL)) {
                nMotion = EntityMovementMode.VTOL;
            }
            else if(jumpType.getSelectedItem().equals(MOVE_UMU)) {
                nMotion = EntityMovementMode.INF_UMU;
            } else {
                nMotion = EntityMovementMode.INF_JUMP;
            }
            unit.setMovementMode(nMotion);
        }
    }

    public void resetJumpType() {
        if(!unit.isClan())  {
            if(unit.getOriginalJumpMP() > 0) {
                unit.setMovementMode(EntityMovementMode.INF_JUMP);
            } else {
                unit.setMovementMode(EntityMovementMode.INF_LEG);
            }
            resetMovementMinMax();
        }
    }
    
    public void refreshPreview(){
        boolean populateTextFields = true;
        MechView mechView = null;
        try {
            mechView = new MechView(unit, false);
        } catch (Exception e) {
            e.printStackTrace();
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields && (mechView != null)) {
            panelMekView.setMech(unit);
        } else {
            panelMekView.reset();
        }
    }
    
}