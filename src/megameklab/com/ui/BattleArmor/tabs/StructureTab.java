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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
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
import megamek.common.LocationFullException;
import megamek.common.MechView;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestBattleArmor.BAManipulator;
import megameklab.com.ui.EntitySource;
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
    private String[] isTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    private String[] chassisTypeArray =
        { "Humanoid", "Quad"};
    private String[] jumpTypeArray =
        { "Jump",MOVE_VTOL,MOVE_UMU};
    
    Dimension labelSize = new Dimension(110, 25);
    Dimension enhanceLabelSz = new Dimension(200,25);
    
    // Basic Info Panel
    private JTextField chassis = new JTextField(5);
    private JTextField model = new JTextField(5);
    private JTextField era = new JTextField(3);
    private JTextField source = new JTextField(3);
    
    private JComboBox<String> techType = new JComboBox<String>(techTypes);
    private JComboBox<String> techLevel = new JComboBox<String>(isTechLevels);
    
    private JSpinner numTroopers;
    
    // Chassis Panel
    private JComboBox<String> chassisType = new JComboBox<String>(chassisTypeArray);
    private JComboBox<String> weightClass;
    private JLabel lblExoskeleton = createLabel("Exoskeleton:", labelSize);
    private JCheckBox chkExoskeleton = new JCheckBox();
    private JLabel lblClanHarjel = createLabel("Harjel:", labelSize);
    private JCheckBox chkClanHarjel = new JCheckBox();
    
    private JSpinner walkMP;
    private JSpinner jumpMP;
    
    private JComboBox<String> jumpType = new JComboBox<String>(jumpTypeArray);
    
    // Armor Panel
    private JComboBox<String> armorType;
    
    private JSpinner armorPoints;
    
    // Manipulator Panel
    private JComboBox<String> leftManipSelect = new JComboBox<String>();
    private JComboBox<String> rightManipSelect = new JComboBox<String>();
    
    // Enhancement Panel
    private JLabel lblJumpBooster = 
            createLabel("Jump Booster:",enhanceLabelSz);
    private JLabel lblMechJumpBooster = 
            createLabel("Mechanical Jump Booster:",enhanceLabelSz);
    private JLabel lblMyomerBooster = 
            createLabel("Myomer Booster:",enhanceLabelSz);
    private JLabel lblPartialWing = 
            createLabel("Partial Wing:",enhanceLabelSz);
    private JCheckBox chkJumpBooster = new JCheckBox();
    private JCheckBox chkMechJumpBooster = new JCheckBox();
    private JCheckBox chkMyomerBooster = new JCheckBox();
    private JCheckBox chkPartialWing = new JCheckBox();

    
    private JTextField txtPrimary = new JTextField("None");
    private JTextField txtSecondary = new JTextField("None");
    
    private MechViewPanel panelMekView;
    
	public StructureTab(EntitySource eSource) {
	    super(eSource);
        setUpPanels();
        refresh();
	}

	public void setUpPanels() {	    
	    JPanel previewPanel = new JPanel();
	    previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
	    panelMekView = new MechViewPanel(450, 480,false);
	    //mekViewScrollPane.setMinimumSize(new java.awt.Dimension(450, 550));
	    //mekViewScrollPane.setMaximumSize(new java.awt.Dimension(450, 550));
	    //mekViewScrollPane.setPreferredSize(new java.awt.Dimension(450, 550));
	    previewPanel.add(panelMekView);
	    
	    JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel basicPanel = new JPanel(new GridBagLayout());
        JPanel chassisPanel = new JPanel(new GridBagLayout());
        JPanel armorPanel = new JPanel(new GridBagLayout());
        JPanel manipPanel = new JPanel(new GridBagLayout());
        JPanel enhancePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(250, 25);
       
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

        chassis.setText(getBattleArmor().getChassis());
        model.setText(getBattleArmor().getModel());

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

        walkMP = new JSpinner(new SpinnerNumberModel(
                getBattleArmor().getMinimumWalkMP(), 
                getBattleArmor().getMinimumWalkMP(), 
                getBattleArmor().getMaximumWalkMP(), 1));
        ((JSpinner.DefaultEditor) walkMP.getEditor()).setSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setMaximumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setPreferredSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor())
                .setMinimumSize(spinnerSize);
        ((JSpinner.DefaultEditor) walkMP.getEditor()).getTextField()
                .setEditable(false);

        jumpMP = new JSpinner(new SpinnerNumberModel(0, 0, getBattleArmor().getMaximumJumpMP(true), 1));
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
            modelWeightClass.addElement(EntityWeightClass.getClassName(i, getBattleArmor()));
        }
        weightClass = new JComboBox<String>(modelWeightClass);
        
        JPanel chassisOptions = new JPanel();
        chassisOptions.add(lblExoskeleton);
        chassisOptions.add(chkExoskeleton);
        chassisOptions.add(lblClanHarjel);
        chassisOptions.add(chkClanHarjel);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        chassisPanel.add(createLabel("Body Type:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(chassisType, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        chassisPanel.add(createLabel("Weight Class:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(weightClass, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        chassisPanel.add(chassisOptions, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        chassisPanel.add(createLabel("Ground MP:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(walkMP, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        chassisPanel.add(createLabel("Jump MP:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(jumpMP, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        chassisPanel.add(createLabel("Move Mode:", labelSize), gbc);
        gbc.gridx = 1;
        chassisPanel.add(jumpType, gbc);
 
        armorType = new JComboBox<String>();
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
        
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        manipPanel.add(createLabel("Left Arm:", labelSize), gbc);
        gbc.gridy = 1;
        manipPanel.add(createLabel("Right Arm:", labelSize), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; 
        gbc.gridy = 0;
        manipPanel.add(leftManipSelect, gbc);
        gbc.gridy = 1;
        manipPanel.add(rightManipSelect, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        enhancePanel.add(lblPartialWing,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        enhancePanel.add(chkPartialWing,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        enhancePanel.add(lblJumpBooster,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        enhancePanel.add(chkJumpBooster,gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        enhancePanel.add(lblMechJumpBooster,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        enhancePanel.add(chkMechJumpBooster,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        enhancePanel.add(lblMyomerBooster,gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        enhancePanel.add(chkMyomerBooster,gbc);
        

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
        setFieldSize(leftManipSelect, comboSize);
        setFieldSize(rightManipSelect, comboSize);


        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        chassisPanel.setBorder(BorderFactory.createTitledBorder("Chassis"));
        armorPanel.setBorder(BorderFactory.createTitledBorder("Armor"));
        //weaponView.setBorder(BorderFactory.createTitledBorder("Weapon Selection"));
        manipPanel.setBorder(BorderFactory.createTitledBorder("Manipulators"));
        enhancePanel.setBorder(BorderFactory.createTitledBorder("Enhancements"));

        leftPanel.add(basicPanel);
        leftPanel.add(chassisPanel);
        leftPanel.add(armorPanel);
        leftPanel.add(manipPanel);
        //leftPanel.add(Box.createVerticalGlue());
        
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(previewPanel);
        rightPanel.add(enhancePanel);
        
        
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,30,0,30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0;
        add(leftPanel, gbc);
        gbc.gridx = 1;
        add(rightPanel,gbc);

    }

   public JLabel createLeftLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.LEFT);

        setFieldSize(label, maxSize);
        return label;
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
        if(getBattleArmor().getWeightClass() == EntityWeightClass.WEIGHT_ULTRA_LIGHT) {
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
            jumpType.removeAllItems();
            if(getBattleArmor().isClan()) {
                for(String j : jumpTypeArray) {
                    jumpType.addItem(j);
                }
                if (getBattleArmor().getMovementMode() == EntityMovementMode.INF_UMU){
                    jumpType.setSelectedItem(MOVE_UMU);
                } else if (getBattleArmor().getMovementMode() == EntityMovementMode.VTOL){
                    jumpType.setSelectedItem(MOVE_VTOL);
                } else {
                    jumpType.setSelectedItem("Jump");
                }
            } else {
                jumpType.addItem(jumpTypeArray[0]);
                jumpType.setSelectedIndex(0);
            }
        }

        weightClass.setSelectedIndex(getBattleArmor().getWeightClass());
        if (getBattleArmor().getWeightClass() != EntityWeightClass.WEIGHT_ULTRA_LIGHT){
            chkExoskeleton.setEnabled(false);
            lblExoskeleton.setEnabled(false);
            chkExoskeleton.setSelected(false);
            getBattleArmor().setIsExoskeleton(false);
        } else {
            chkExoskeleton.setEnabled(true);
            lblExoskeleton.setEnabled(true);
            getBattleArmor().setIsExoskeleton(chkExoskeleton.isSelected());
        }
        
        if (getBattleArmor().isClan()){
            if (getBattleArmor().isClan() && chkExoskeleton.isSelected()){
                lblClanHarjel.setEnabled(true);
                chkClanHarjel.setEnabled(true);
                getBattleArmor().setClanExoWithoutHarjel(
                        chkClanHarjel.isSelected());
            } else {
                // Clan BA have Harjel by default
                chkClanHarjel.setSelected(true);
                // Only industrial exoskeletons can't have Harjel
                lblClanHarjel.setEnabled(false);
                chkClanHarjel.setEnabled(false);
                getBattleArmor().setClanExoWithoutHarjel(false);
            }
        } else {
            lblClanHarjel.setEnabled(false);
            chkClanHarjel.setEnabled(false);                
            chkClanHarjel.setSelected(false);
            getBattleArmor().setClanExoWithoutHarjel(false);
        }
        
        

        walkMP.setValue(getBattleArmor().getOriginalWalkMP());
        ((SpinnerNumberModel) walkMP.getModel()).setMinimum(getBattleArmor().getMinimumWalkMP());
        ((SpinnerNumberModel) walkMP.getModel()).setMaximum(getBattleArmor().getMaximumWalkMP());
        jumpMP.setValue(getBattleArmor().getOriginalJumpMP());
        ((SpinnerNumberModel) jumpMP.getModel()).setMaximum(getBattleArmor().getMaximumJumpMP(true));
        
        armorType.removeAllItems();
        for (TestBattleArmor.BAArmor armor : TestBattleArmor.BAArmor.values()){
            int type = armor.type;
            EquipmentType et;
            if (!isMixed) {
                boolean techMatch = (armor.isClan && getBattleArmor().isClan()) || 
                        (!armor.isClan && !getBattleArmor().isClan());
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
                String armorName = 
                        EquipmentType.getArmorTypeName(type, armor.isClan);
                et = EquipmentType.get(armorName);
                if (et != null && TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                                et.getTechLevel(getBattleArmor().getYear()), 
                                isMixed)) {
                    armorType.addItem(armorName);
                }
            }
        }           
        
        String armorName = "";
        if (isMixed){
            boolean isClanArmor = TechConstants.isClan(
                    getBattleArmor().getArmorTechLevel(BattleArmor.LOC_SQUAD));
            if (isClanArmor){
                armorName = "Clan ";
            } else {
                armorName = "IS ";
            }
        }
        armorName += EquipmentType.getArmorTypeName(
                getBattleArmor().getArmorType(BattleArmor.LOC_SQUAD));
        armorType.setSelectedItem(armorName);
        
        // We have to use a LOC_TROOPER as getOArmor has a special case for LOC_SQUAD...
        armorPoints.setValue(getBattleArmor().getOArmor(BattleArmor.LOC_TROOPER_1));
        ((SpinnerNumberModel) armorPoints.getModel()).setMaximum(getBattleArmor().getMaximumArmorPoints());

        numTroopers.setValue(getBattleArmor().getTroopers());

        // Manipulators
        leftManipSelect.removeAllItems();
        rightManipSelect.removeAllItems();
        leftManipSelect.addItem(BattleArmor.MANIPULATOR_TYPE_STRINGS[BattleArmor.MANIPULATOR_NONE]);
        rightManipSelect.addItem(BattleArmor.MANIPULATOR_TYPE_STRINGS[BattleArmor.MANIPULATOR_NONE]);
        for (BAManipulator manip : BAManipulator.values()){
            EquipmentType et = EquipmentType.get(manip.internalName);
            boolean legalTechLvl = (et != null) &&  
                    (TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                            et.getTechLevel(getBattleArmor().getYear()), 
                            isMixed));
            if (legalTechLvl){
                leftManipSelect.addItem(et.getName());
                rightManipSelect.addItem(et.getName());
            }
        }
        int manipType = BAManipulator.getManipulator(
                getBattleArmor().getLeftManipulatorName()).type;
        leftManipSelect.setSelectedItem(
                BattleArmor.MANIPULATOR_NAME_STRINGS[manipType]);
        manipType = BAManipulator.getManipulator(
                getBattleArmor().getRightManipulatorName()).type;
        rightManipSelect.setSelectedItem(
                BattleArmor.MANIPULATOR_NAME_STRINGS[manipType]);
        
        
        // Enhancements
        EquipmentType eType;
        boolean legalTech;
        // Jump Booster
        if (isClan){
            eType = EquipmentType.get("CLBAJumpBooster");
        } else {
            eType = EquipmentType.get("ISBAJumpBooster");
        }
        legalTech = TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                eType.getTechLevel(getBattleArmor().getYear()), 
                isMixed);
        if (legalTech && getBattleArmor().getMovementMode() == EntityMovementMode.INF_JUMP
                && getBattleArmor().getJumpMP() > 0 
                && !getBattleArmor().hasWorkingMisc(MiscType.F_PARTIAL_WING)){
            chkJumpBooster.setEnabled(true);
            lblJumpBooster.setEnabled(true);
        } else {
            chkJumpBooster.setEnabled(false);
            lblJumpBooster.setEnabled(false);
        }
        if (getBattleArmor().hasWorkingMisc(MiscType.F_JUMP_BOOSTER)){
            chkJumpBooster.setSelected(true);
        }
        
        // Partial Wing
        eType = EquipmentType.get("BAPartialWing");
        legalTech = TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                eType.getTechLevel(getBattleArmor().getYear()), 
                isMixed);
        if (legalTech && getBattleArmor().getMovementMode() == EntityMovementMode.INF_JUMP
                && getBattleArmor().getJumpMP() > 0 
                && !getBattleArmor().hasWorkingMisc(MiscType.F_JUMP_BOOSTER)){
            chkPartialWing.setEnabled(true);
            lblPartialWing.setEnabled(true);
        } else {
            chkPartialWing.setEnabled(false);
            lblPartialWing.setEnabled(false);
        }
        if (getBattleArmor().hasWorkingMisc(MiscType.F_PARTIAL_WING)){
            chkPartialWing.setSelected(true);
        }
        
        // Myomer Booster
        eType = EquipmentType.get("CLBAMyomerBooster");
        legalTech = TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                eType.getTechLevel(getBattleArmor().getYear()), 
                isMixed) && isClan;
        if (legalTech && !getBattleArmor().hasWorkingMisc(MiscType.F_STEALTH)
                && !getBattleArmor().hasWorkingMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER)
                && getBattleArmor().getWeightClass() != EntityWeightClass.WEIGHT_ULTRA_LIGHT){
            chkMyomerBooster.setEnabled(true);
            lblMyomerBooster.setEnabled(true);
        } else {
            chkMyomerBooster.setEnabled(false);
            lblMyomerBooster.setEnabled(false);
        }
        if (getBattleArmor().hasWorkingMisc(MiscType.F_MASC)){
            chkMyomerBooster.setSelected(true);
        }
        
        // Mechanical Jump Booster
        if (isClan){
            eType = EquipmentType.get("CLMechanicalJumpBooster");
        } else {
            eType = EquipmentType.get("ISMechanicalJumpBooster");
        }
        legalTech = TechConstants.isLegal(getBattleArmor().getTechLevel(), 
                eType.getTechLevel(getBattleArmor().getYear()), 
                isMixed);
        if (legalTech && !getBattleArmor().hasWorkingMisc(MiscType.F_MASC)){
            chkMechJumpBooster.setEnabled(true);
            lblMechJumpBooster.setEnabled(true);
        } else {
            chkMechJumpBooster.setEnabled(false);
            lblMechJumpBooster.setEnabled(false);
        }
        if (getBattleArmor().hasWorkingMisc(MiscType.F_MECHANICAL_JUMP_BOOSTER)){
            chkMechJumpBooster.setSelected(true);
        }
        
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
        leftManipSelect.addActionListener(this);
        rightManipSelect.addActionListener(this);
        chkJumpBooster.addActionListener(this);
        chkMechJumpBooster.addActionListener(this);
        chkExoskeleton.addActionListener(this);
        chkClanHarjel.addActionListener(this);
        chkMyomerBooster.addActionListener(this);
        chkPartialWing.addActionListener(this);
        
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
        leftManipSelect.removeActionListener(this);
        rightManipSelect.removeActionListener(this);
        chkJumpBooster.removeActionListener(this);
        chkMechJumpBooster.removeActionListener(this);
        chkMyomerBooster.removeActionListener(this);
        chkPartialWing.removeActionListener(this);
        chkExoskeleton.removeActionListener(this);
        chkClanHarjel.removeActionListener(this);
        
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
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
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
                UnitUtil.checkEquipmentByTechLevel(getBattleArmor());
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
                    if (getBattleArmor().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                        }
                    } else if (getBattleArmor().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getBattleArmor().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_IS_UNOFFICIAL) && (getBattleArmor().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getBattleArmor().getTechLevel() != TechConstants.T_IS_TW_NON_BOX)) {
                            getBattleArmor().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
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
                    if (getBattleArmor().getYear() < 3090) {
                        //before 3090, mixed tech is experimental
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                        }
                    } else if (getBattleArmor().getYear() < 3145) {
                        // between 3090 and 3145, mixed tech is advanced
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getBattleArmor().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                    } else {
                        // from 3145 on, mixed tech is tourney legal
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL) && (getBattleArmor().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getBattleArmor().getTechLevel() != TechConstants.T_CLAN_TW)) {
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
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
                UnitUtil.checkEquipmentByTechLevel(getBattleArmor());
            }
            else if (combo.equals(chassisType)) {
                getBattleArmor().setChassisType(chassisType.getSelectedIndex());
                resetMovementMinMax();
            }
            else if (combo.equals(weightClass)) {
                getBattleArmor().setWeightClass(combo.getSelectedIndex());
                if((getBattleArmor().getWeightClass() == EntityWeightClass.WEIGHT_ULTRA_LIGHT)
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
                UnitUtil.removeISorArmorMounts(getBattleArmor(), false);
                String armorName;
                if (getBattleArmor().isMixedTech()){
                    armorName = (String)armorType.getSelectedItem();
                } else if (getBattleArmor().isClan()){
                    armorName = "Clan " + (String)armorType.getSelectedItem();
                } else {
                    armorName = "IS " + (String)armorType.getSelectedItem();
                }
                EquipmentType aType = EquipmentType.get(armorName);
                int armorCount = aType.getCriticals(getBattleArmor());
                getBattleArmor().setArmorType(armorName);
                getBattleArmor().setArmorTechLevel(
                        aType.getTechLevel(getBattleArmor().getYear()));

                for (; armorCount > 0; armorCount--) {
                    try {
                        getBattleArmor().addEquipment(new Mounted(getBattleArmor(), aType),
                                BattleArmor.LOC_SQUAD, false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (combo.equals(leftManipSelect)){
                // If the BA already had a manipulator here, we'll need to
                //  remove it
                Mounted leftManip = getBattleArmor().getLeftManipulator();
                BAManipulator manipType;
                if (leftManip != null){
                    UnitUtil.removeMounted(getBattleArmor(), leftManip);
                    manipType = BAManipulator.getManipulator(
                            leftManip.getType().getInternalName());
                    // If this manipulator was mounted as a pair, 
                    //   remove the paired manipulator
                    if (manipType.pairMounted){
                        Mounted rightManip = 
                                getBattleArmor().getRightManipulator();
                        if (rightManip != null){
                            UnitUtil.removeMounted(getBattleArmor(), rightManip);
                            rightManipSelect.setSelectedIndex(0);
                        }
                    }
                }
                
                // If we selected something other than "None", mount it
                if (leftManipSelect.getSelectedIndex() != 0){
                    String manipName = 
                            (String)leftManipSelect.getSelectedItem();
                    manipType = BAManipulator.getManipulator(manipName);
                    EquipmentType et = EquipmentType.get(manipType.internalName);
                    leftManip = new Mounted(getBattleArmor(), et);
                    leftManip.setBaMountLoc(BattleArmor.MOUNT_LOC_LARM);
                    try { 
                        // Add the manipulator
                        getBattleArmor().addEquipment(leftManip,
                                BattleArmor.LOC_SQUAD, false);
                        // Check to see if we need to add its mate
                        manipType = BAManipulator.getManipulator(
                                leftManip.getType().getInternalName());
                        // If this manipulator was mounted as a pair, 
                        //   remove the paired manipulator
                        if (manipType.pairMounted){
                            Mounted rightManip = new Mounted(getBattleArmor(), et);
                            rightManip.setBaMountLoc(
                                    BattleArmor.MOUNT_LOC_RARM);
                            getBattleArmor().addEquipment(rightManip,
                                    BattleArmor.LOC_SQUAD, false);
                        }
                    } catch (LocationFullException ex){
                        // This shouldn't happen
                        ex.printStackTrace();
                    }
                }
            } else if (combo.equals(rightManipSelect)){
                // If the BA already had a manipulator here, we'll need to
                //  remove it
                Mounted rightManip = getBattleArmor().getRightManipulator();
                BAManipulator manipType;
                if (rightManip != null){
                    UnitUtil.removeMounted(getBattleArmor(), rightManip);
                    manipType = BAManipulator.getManipulator(
                            rightManip.getType().getInternalName());
                    // If this manipulator was mounted as a pair, 
                    //   remove the paired manipulator
                    if (manipType.pairMounted){
                        Mounted leftManip = 
                                getBattleArmor().getLeftManipulator();
                        if (leftManip != null){
                            UnitUtil.removeMounted(getBattleArmor(), leftManip);
                            leftManipSelect.setSelectedIndex(0);
                        }
                    }
                }
                
                // If we selected something other than "None", mount it
                if (rightManipSelect.getSelectedIndex() != 0){
                    String manipName = 
                            (String)rightManipSelect.getSelectedItem();
                    manipType = BAManipulator.getManipulator(manipName);
                    EquipmentType et = EquipmentType.get(manipType.internalName);
                    rightManip = new Mounted(getBattleArmor(), et);
                    rightManip.setBaMountLoc(BattleArmor.MOUNT_LOC_RARM);
                    try { 
                        // Add the manipulator
                        getBattleArmor().addEquipment(rightManip,
                                BattleArmor.LOC_SQUAD, false);
                        // Check to see if we need to add its mate
                        manipType = BAManipulator.getManipulator(
                                rightManip.getType().getInternalName());
                        // If this manipulator was mounted as a pair, 
                        //   remove the paired manipulator
                        if (manipType.pairMounted){
                            Mounted leftManip = new Mounted(getBattleArmor(), et);
                            leftManip.setBaMountLoc(
                                    BattleArmor.MOUNT_LOC_LARM);
                            getBattleArmor().addEquipment(leftManip,
                                    BattleArmor.LOC_SQUAD, false);
                        }
                    } catch (LocationFullException ex){
                        // This shouldn't happen
                        ex.printStackTrace();
                    }
                }
            }
        } else if (e.getSource() instanceof JCheckBox){
            JCheckBox chkBox = (JCheckBox) e.getSource();
            if (chkBox.isSelected()){
                EquipmentType eType = null;
                int baMountLoc = BattleArmor.MOUNT_LOC_NONE;
                
                if (chkBox.equals(chkJumpBooster)){
                    if (getBattleArmor().isClan()){
                        eType = EquipmentType.get("CLBAJumpBooster");
                    } else {
                        eType = EquipmentType.get("ISBAJumpBooster");
                    }
                    baMountLoc = BattleArmor.MOUNT_LOC_BODY;
                } else if (chkBox.equals(chkMechJumpBooster)){
                    if (getBattleArmor().isClan()){
                        eType = EquipmentType.get("CLMechanicalJumpBooster");
                    } else {
                        eType = EquipmentType.get("ISMechanicalJumpBooster");
                    }
                    baMountLoc = BattleArmor.MOUNT_LOC_BODY;
                } else if (chkBox.equals(chkMyomerBooster)){
                    eType = EquipmentType.get("CLBAMyomerBooster");
                    baMountLoc = BattleArmor.MOUNT_LOC_NONE;
                } else if (chkBox.equals(chkPartialWing)){
                    eType = EquipmentType.get("BAPartialWing");
                    baMountLoc = BattleArmor.MOUNT_LOC_BODY;
                }
                try {
                    if (eType != null){
                        int numTimesToAdd = 1;
                        // Spreadable equipment needs to have a Mounted entry
                        // for each critical
                        if (eType.isSpreadable()) {
                            numTimesToAdd = eType.getCriticals(getBattleArmor());
                        } 
                        for (int i = 0; i < numTimesToAdd; i++) {
                            Mounted newMount = new Mounted(getBattleArmor(), eType);
                            newMount.setBaMountLoc(baMountLoc);
                            getBattleArmor().addEquipment(newMount,
                                    BattleArmor.LOC_SQUAD, false);
                        }
                    }
                } catch (LocationFullException exc){
                    // Shouldn't happen with BA
                    exc.printStackTrace();
                }
            } else {
                ArrayList<Mounted> mounts = new ArrayList<Mounted>();
                for (Mounted m : getBattleArmor().getMisc()){
                    if (chkBox.equals(chkJumpBooster) 
                            && m.getType().hasFlag(MiscType.F_JUMP_BOOSTER)){
                        mounts.add(m);
                    } else if (chkBox.equals(chkMechJumpBooster) 
                            && m.getType().hasFlag(
                                    MiscType.F_MECHANICAL_JUMP_BOOSTER)){
                        mounts.add(m);
                    } else if (chkBox.equals(chkMyomerBooster) 
                            && m.getType().hasFlag(MiscType.F_MASC)){
                        mounts.add(m);
                    } else if (chkBox.equals(chkPartialWing) 
                            && m.getType().hasFlag(MiscType.F_PARTIAL_WING)){
                        mounts.add(m);
                    }
                }
                for (Mounted mount : mounts) {
                    UnitUtil.removeMounted(getBattleArmor(), mount);
                }
            }
        }
        refresh.refreshAll();
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(era)) {
            try {
                getBattleArmor().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getBattleArmor().setYear(3145);
            }
        } else if (e.getSource().equals(source)) {
            getBattleArmor().setSource(source.getText());
        } else if (e.getSource().equals(chassis)) {
            getBattleArmor().setChassis(chassis.getText().trim());
        } else if (e.getSource().equals(model)) {
            getBattleArmor().setModel(model.getText().trim());
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
                for(int i = 0; i < getBattleArmor().locations(); i++) {
                    getBattleArmor().initializeArmor(value, i);
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
        if(getBattleArmor().getOriginalWalkMP() > getBattleArmor().getMaximumWalkMP()) {
            getBattleArmor().setOriginalWalkMP(getBattleArmor().getMaximumWalkMP());
        }
        else if(getBattleArmor().getOriginalWalkMP() < getBattleArmor().getMinimumWalkMP()) {
            getBattleArmor().setOriginalWalkMP(getBattleArmor().getMinimumWalkMP());
        }
        if(getBattleArmor().getOriginalJumpMP() > getBattleArmor().getMaximumJumpMP(true)) {
            getBattleArmor().setOriginalJumpMP(getBattleArmor().getMaximumJumpMP(true));
        }
    }

    public void resetArmorMax() {
        for(int i = 0; i < getBattleArmor().locations(); i++) {
            if(getBattleArmor().getOArmor(i) > getBattleArmor().getMaximumArmorPoints()) {
                getBattleArmor().initializeArmor(getBattleArmor().getMaximumArmorPoints(), i);
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
            getBattleArmor().setMovementMode(nMotion);
        }
    }

    public void resetJumpType() {
        if(!getBattleArmor().isClan())  {
            if(getBattleArmor().getOriginalJumpMP() > 0) {
                getBattleArmor().setMovementMode(EntityMovementMode.INF_JUMP);
            } else {
                getBattleArmor().setMovementMode(EntityMovementMode.INF_LEG);
            }
            resetMovementMinMax();
        }
    }
    
    public void refreshPreview(){
        boolean populateTextFields = true;
        MechView mechView = null;
        try {
            mechView = new MechView(getBattleArmor(), false);
        } catch (Exception e) {
            e.printStackTrace();
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields && (mechView != null)) {
            panelMekView.setMech(getBattleArmor());
        } else {
            panelMekView.reset();
        }
    }
    
}