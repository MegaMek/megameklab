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
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.MechView;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestBattleArmor.BAManipulator;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.BAChassisView;
import megameklab.com.ui.view.BAEnhancementView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.ITechManager;
import megameklab.com.ui.view.MovementView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener, ChangeListener,
        BasicInfoView.BasicInfoListener,
        BAChassisView.ChassisListener,
        MovementView.MovementListener,
        BAEnhancementView.EnhancementListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -7985608549543235815L;

    private RefreshListener refresh;

    Dimension labelSize = new Dimension(110, 25);
    Dimension enhanceLabelSz = new Dimension(200,25);

    private BasicInfoView panBasicInfo;
    private BAChassisView panChassis;
    private MovementView panMovement;
    private BAEnhancementView panEnhancements;
    
    // Armor Panel
    private JComboBox<String> armorType;
    
    private JSpinner armorPoints;
    
    // Manipulator Panel
    private JComboBox<String> leftManipSelect = new JComboBox<String>();
    private JComboBox<String> rightManipSelect = new JComboBox<String>();
    
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

        panBasicInfo = new BasicInfoView(getBattleArmor().getConstructionTechAdvancement());
        panChassis = new BAChassisView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        JPanel armorPanel = new JPanel(new GridBagLayout());
        JPanel manipPanel = new JPanel(new GridBagLayout());
        panEnhancements = new BAEnhancementView(panBasicInfo);
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(250, 25);
       
        Dimension spinnerSize = new Dimension(55, 25);

        txtPrimary.setEditable(false);
        txtSecondary.setEditable(false);

        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

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
        

        setFieldSize(armorType, comboSize);
        setFieldSize(txtPrimary, comboSize);
        setFieldSize(txtSecondary, comboSize);
        setFieldSize(leftManipSelect, comboSize);
        setFieldSize(rightManipSelect, comboSize);


        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        armorPanel.setBorder(BorderFactory.createTitledBorder("Armor"));
        //weaponView.setBorder(BorderFactory.createTitledBorder("Weapon Selection"));
        manipPanel.setBorder(BorderFactory.createTitledBorder("Manipulators"));
        panEnhancements.setBorder(BorderFactory.createTitledBorder("Enhancements"));

        leftPanel.add(panBasicInfo);
        leftPanel.add(panChassis);
        leftPanel.add(panMovement);
        leftPanel.add(armorPanel);
        leftPanel.add(manipPanel);
        //leftPanel.add(Box.createVerticalGlue());
        
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(previewPanel);
        rightPanel.add(panEnhancements);
        
        
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
	    panBasicInfo.setFromEntity(getBattleArmor());
	    panChassis.setFromEntity(getBattleArmor());
	    panMovement.setFromEntity(getBattleArmor());
	    panEnhancements.setFromEntity(getBattleArmor());
	    
	    removeAllListeners();
	    
        boolean isMixed = getBattleArmor().isMixedTech();
        boolean isExperimental = (getBattleArmor().getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_IS_UNOFFICIAL)
                || (getBattleArmor().getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL);
	    

        
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
        refreshPreview();

        addAllListeners();
	}
	
	public ITechManager getTechManager() {
	    return panBasicInfo;
	}

	public void addAllListeners() {
        armorType.addActionListener(this);
        leftManipSelect.addActionListener(this);
        rightManipSelect.addActionListener(this);
        
        armorPoints.addChangeListener(this);
        
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panEnhancements.addListener(this);
    }

    public void removeAllListeners() {
        armorType.removeActionListener(this);
        leftManipSelect.removeActionListener(this);
        rightManipSelect.removeActionListener(this);
        
        armorPoints.removeChangeListener(this);
        
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panEnhancements.removeListener(this);
    }

	public void addRefreshedListener(RefreshListener l) {
	    refresh = l;
	    //weaponView.addRefreshedListener(refresh);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            if (combo.equals(armorType)) {
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
        }
        refresh.refreshAll();
    }

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
    }

    public void stateChanged(ChangeEvent e) {
        removeAllListeners();

        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            if (spinner.equals(armorPoints)) {
                int value = (Integer) armorPoints.getValue();
                for(int i = 0; i < getBattleArmor().locations(); i++) {
                    getBattleArmor().initializeArmor(value, i);
                }
            }
        }

        addAllListeners();

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
    
    @Override
    public void refreshSummary() {
        // no summary
    }
    
    @Override
    public void chassisChanged(String chassis) {
        getBattleArmor().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getBattleArmor().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getBattleArmor().setYear(year);
        updateTechLevel();
    }

    @Override
    public void sourceChanged(String source) {
        getBattleArmor().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getBattleArmor().isClan()) || (mixed != getBattleArmor().isMixedTech())) {
            getBattleArmor().setMixedTech(mixed);
            updateTechLevel();
            if (clan) {
                squadSizeChanged(5);
            } else if (getBattleArmor().getTroopers() == 5) {
                squadSizeChanged(4);
            }
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }
    
    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getBattleArmor().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.isClan()));
        if (UnitUtil.checkEquipmentByTechLevel(getBattleArmor(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panMovement.refresh();
        panEnhancements.setFromEntity(getBattleArmor());
//        panArmor.refresh();
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getBattleArmor().setManualBV(manualBV);
    }

    @Override
    public void walkChanged(int walkMP) {
        getBattleArmor().setOriginalWalkMP(walkMP);
        panMovement.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        getBattleArmor().setOriginalJumpMP(jumpMP);
        if (0 == jumpMP) {
            jumpTypeChanged(null);
        } else {
            jumpTypeChanged(jumpJet);
        }
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        if ((null == jumpJet) || (getBattleArmor().getOriginalJumpMP() == 0)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_LEG);
        } else if (jumpJet.hasFlag(MiscType.F_JUMP_JET)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_JUMP);
        } else if (jumpJet.hasFlag(MiscType.F_UMU)) {
            getBattleArmor().setMovementMode(EntityMovementMode.INF_UMU);
        } else {
            getBattleArmor().setMovementMode(EntityMovementMode.VTOL);
        }
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void chassisTypeChanged(int chassisType) {
        getBattleArmor().setChassisType(chassisType);
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.refresh();
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void weightClassChanged(int weightClass) {
        getBattleArmor().setWeightClass(weightClass);
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.refresh();
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void exoskeletonChanged(boolean exoskeleton) {
        getBattleArmor().setIsExoskeleton(exoskeleton);
        if (exoskeleton && !panBasicInfo.isClan()) {
            getBattleArmor().setClanExoWithoutHarjel(panChassis.hasHarjel());
        }
        refresh.refreshStatus();
        refreshPreview();
    }

    @Override
    public void harjelChanged(boolean harjel) {
        getBattleArmor().setClanExoWithoutHarjel(panChassis.isExoskeleton() && !harjel);
        refresh.refreshStatus();
        refreshPreview();
    }

    @Override
    public void squadSizeChanged(int squadSize) {
        if (squadSize != getBattleArmor().getTroopers()){
            getBattleArmor().setTroopers(squadSize);
            getBattleArmor().autoSetInternal();
            refresh.refreshStatus();
            refresh.refreshPreview();
            refresh.refreshBuild();
        }
    }

    @Override
    public void enhancementChanged(EquipmentType eq, boolean selected) {
        if (selected) {
            try {
                int loc = BattleArmor.MOUNT_LOC_BODY;
                if (eq.hasFlag(MiscType.F_MASC)) {
                    loc = BattleArmor.MOUNT_LOC_NONE;
                }
                int numTimesToAdd = 1;
                // Spreadable equipment needs to have a Mounted entry
                // for each critical
                if (eq.isSpreadable()) {
                    numTimesToAdd = eq.getCriticals(getBattleArmor());
                } 
                for (int i = 0; i < numTimesToAdd; i++) {
                    Mounted newMount = new Mounted(getBattleArmor(), eq);
                    newMount.setBaMountLoc(loc);
                    getBattleArmor().addEquipment(newMount,
                            BattleArmor.LOC_SQUAD, false);
                }
            } catch (LocationFullException exc) {
                // Shouldn't happen with BA
                exc.printStackTrace();
            }
        } else {
            List<Mounted> mounts = getBattleArmor().getMisc().stream()
                    .filter(m -> m.getType().equals(eq))
                    .collect(Collectors.toList());
            for (Mounted mount : mounts) {
                UnitUtil.removeMounted(getBattleArmor(), mount);
            }
        }
        panMovement.setFromEntity(getBattleArmor());
        refresh.refreshSummary();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
}