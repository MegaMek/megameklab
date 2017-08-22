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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadVee;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Mek.views.ArmorView;
import megameklab.com.ui.Mek.views.SummaryView;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.HeatSinkView;
import megameklab.com.ui.view.MVFArmorView;
import megameklab.com.ui.view.MekChassisView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ChangeListener, ItemListener,
    BasicInfoView.BasicInfoListener, MekChassisView.MekChassisListener,
    HeatSinkView.HeatSinkListener, MVFArmorView.ArmorListener {
    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    BasicInfoView panBasicInfo;
    MekChassisView panChassis;
    MVFArmorView panArmor;
    JPanel panMovement;
    HeatSinkView panHeat;
    SummaryView panSummary;

    private ArmorView armor;

    JSpinner walkMPBase;
    JTextField runMPBase;
    JSpinner jumpMPBase;
    JTextField walkMPFinal;
    JTextField runMPFinal;
    JTextField jumpMPFinal;
    SpinnerNumberModel jumpModel;
    String[] jjTypes = { "Standard", "Improved", "Prototype",
            "Mechanical Boosters", "Improved Prototype" };
    JComboBox<String> jjType = new JComboBox<String>(jjTypes);
    RefreshListener refresh = null;
    JPanel masterPanel;

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
        panBasicInfo = new BasicInfoView(getMech().getConstructionTechAdvancement());
        panChassis = new MekChassisView(panBasicInfo);
        panArmor = new MVFArmorView(panBasicInfo);
        panMovement = new JPanel(new GridBagLayout());
        panHeat = new HeatSinkView(panBasicInfo);
        panSummary = new SummaryView(eSource);

        GridBagConstraints gbc;

        Dimension spinnerSize = new Dimension(55, 25);
        Dimension labelSize = new Dimension(180, 25);

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

        // lblFreeSinks.setFont(new Font(lblFreeSinks.getName(), Font.PLAIN,
        // 10));

        panBasicInfo.setFromEntity(getMech());
        panChassis.setFromEntity(getMech());
        panArmor.setFromEntity(getMech());
        panHeat.setFromMech(getMech());

        gbc = new GridBagConstraints();
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

        Dimension comboSize = new Dimension(180, 25);

        setFieldSize(jjType, comboSize);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
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

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panHeat.setBorder(BorderFactory.createTitledBorder("Heat Sinks"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        panSummary.setBorder(BorderFactory.createTitledBorder("Summary"));
        armor.setBorder(BorderFactory.createTitledBorder("Armor Allocation"));

    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getMech());
        panChassis.setFromEntity(getMech());
        panArmor.setFromEntity(getMech());
        panHeat.setFromMech(getMech());

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

            if (combo.equals(jjType)) {
                refreshJumpMP();
            }
            addAllListeners();
            refresh.refreshAll();
        }
    }
    
    private void resetSystemCrits() {
        getMech().clearCockpitCrits();
        getMech().clearGyroCrits();
        getMech().clearEngineCrits();
        
        int[] ctEngine = getMech().getEngine().getCenterTorsoCriticalSlots(getMech().getGyroType());
        int lastEngine = ctEngine[ctEngine.length - 1];
        for (int slot = 0; slot <= lastEngine; slot++) {
            clearCrit(Mech.LOC_CT, slot);
        }
        for (int slot : getMech().getEngine().getSideTorsoCriticalSlots()) {
            clearCrit(Mech.LOC_RT, slot);
            clearCrit(Mech.LOC_LT, slot);
        }
        getMech().addEngineCrits();
        switch (getMech().getGyroType()) {
        case Mech.GYRO_COMPACT:
            getMech().addCompactGyro();
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
        
        switch (getMech().getCockpitType()) {
            case Mech.COCKPIT_COMMAND_CONSOLE:
                clearCritsForCockpit(false, true);
                getMech().addCommandConsole();
                break;
            case Mech.COCKPIT_DUAL:
                clearCritsForCockpit(false, true);
                getMech().addDualCockpit();
                break;
            case Mech.COCKPIT_SMALL:
                clearCritsForCockpit(true, false);
                getMech().addSmallCockpit();
                break;
            case Mech.COCKPIT_INTERFACE:
                clearCritsForCockpit(false, true);
                getMech().addInterfaceCockpit();
                break;
            case Mech.COCKPIT_TORSO_MOUNTED:
                if (lastEngine + 2 < getMech().getNumberOfCriticals(Mech.LOC_CT)) {
                    clearCrit(Mech.LOC_CT, lastEngine + 1);
                    clearCrit(Mech.LOC_CT, lastEngine + 2);
                }
                clearCrit(Mech.LOC_HEAD, 0);
                clearCrit(Mech.LOC_HEAD, 1);
                if (getMech().getEmptyCriticals(Mech.LOC_LT) < 1) {
                    for (int i = 0; i < getMech().getNumberOfCriticals(Mech.LOC_LT); i++) {
                        if (getMech().getCritical(Mech.LOC_LT, i) != null
                                && getMech().getCritical(Mech.LOC_LT, i).getType() == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mech.LOC_LT, i);
                            break;
                        }
                    }
                }
                if (getMech().getEmptyCriticals(Mech.LOC_RT) < 1) {
                    for (int i = 0; i < getMech().getNumberOfCriticals(Mech.LOC_RT); i++) {
                        if (getMech().getCritical(Mech.LOC_RT, i) != null
                                && getMech().getCritical(Mech.LOC_RT, i).getType() == CriticalSlot.TYPE_EQUIPMENT) {
                            clearCrit(Mech.LOC_RT, i);
                            break;
                        }
                    }
                }
                getMech().addTorsoMountedCockpit();
                break;
            case Mech.COCKPIT_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMech().addIndustrialCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_INDUSTRIAL);
                break;
            case Mech.COCKPIT_PRIMITIVE:
                clearCritsForCockpit(false, false);
                getMech().addPrimitiveCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_PRIMITIVE);
                break;
            case Mech.COCKPIT_PRIMITIVE_INDUSTRIAL:
                clearCritsForCockpit(false, false);
                getMech().addIndustrialPrimitiveCockpit();
                getMech().setArmorType(
                        EquipmentType.T_ARMOR_COMMERCIAL);
                break;
            default:
                clearCritsForCockpit(false, false);
                getMech().addCockpit();
        }
        refresh.refreshBuild();
    }
    
    /**
     * Removes equipment placed in head locations that are needed for a cockpit. For most cockpit
     * types, this is all but the fourth slot.
     * 
     * @param small If true, only clears the first four slots.
     * @param dual  If true, removes all equipment mounted in the head.
     */
    private void clearCritsForCockpit(boolean small, boolean dual) {
        for (int slot = 0; slot < (small?4:6); slot++) {
            if ((slot == 3) && !dual) {
                continue;
            }
            clearCrit(Mech.LOC_HEAD, slot);
        }
    }

    /**
     * Removes equipment placed in the given critical slot to clear the space for a system critical
     */
    private void clearCrit(int loc, int slotNum) {
        final CriticalSlot crit = getMech().getCritical(loc, slotNum);
        Mounted mounted = null;
        if (crit != null && crit.getType() == CriticalSlot.TYPE_EQUIPMENT) {
            mounted = crit.getMount();
        }
        if (mounted == null) {
            return;
        }
        UnitUtil.removeCriticals(getMech(), mounted);
        if (crit.getMount2() != null) {
            UnitUtil.removeCriticals(getMech(), crit.getMount2());
        }

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getMech());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            UnitUtil.changeMountStatus(getMech(), mounted, Entity.LOC_NONE, Entity.LOC_NONE,
                    false);
            if (crit.getMount2() != null) {
                UnitUtil.changeMountStatus(getMech(), crit.getMount2(), Entity.LOC_NONE, Entity.LOC_NONE,
                        false);
            }
        }

    }

    public void refreshJumpMP() {
        // check to make sure we are not over the number of jets
        // we are allowed
        jumpMPBase.setValue(getMech().getOriginalJumpMP(true));
        jumpMPFinal.setText(String.valueOf(getMech().getJumpMP()));
        if (getMech().isSuperHeavy()) {
            jumpModel.setMaximum(0);
        } else if ((getJumpJetType() == Mech.JUMP_IMPROVED)
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
        walkMPBase.removeChangeListener(this);
        jumpMPBase.removeChangeListener(this);
        jjType.removeItemListener(this);
        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panHeat.removeListener(this);
        panArmor.removeListener(this);
    }

    public void addAllListeners() {
        walkMPBase.addChangeListener(this);
        jumpMPBase.addChangeListener(this);
        jjType.addItemListener(this);
        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panHeat.addListener(this);
        panArmor.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        armor.addRefreshedListener(l);
    }

    public boolean isQuad() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_STANDARD)
                && (panChassis.getMotiveTypeIndex() == MekChassisView.MOTIVE_TYPE_QUAD);
    }

    public boolean isTripod() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_STANDARD)
                && (panChassis.getMotiveTypeIndex() == MekChassisView.MOTIVE_TYPE_TRIPOD);
    }

    public boolean isLAM() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_LAM);
    }

    public boolean isQuadVee() {
        return (panChassis.getBaseTypeIndex() == MekChassisView.BASE_TYPE_QUADVEE);
    }

    private void createISMounts(EquipmentType structure) {
        int isCount = 0;
        getMech().setStructureType(EquipmentType.getStructureType(structure));
        getMech().setStructureTechLevel(structure.getStaticTechLevel().getCompoundTechLevel(structure.isClan()));
        
        isCount = structure.getCriticals(getMech());
        if (isCount < 1) {
            return;
        }
        for (; isCount > 0; isCount--) {
            try {
                getMech().addEquipment(
                        new Mounted(getMech(), structure),
                        Entity.LOC_NONE, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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

        boolean isExperimental = (panBasicInfo.getTechLevel() == SimpleTechLevel.EXPERIMENTAL)
                || (panBasicInfo.getTechLevel() == SimpleTechLevel.UNOFFICIAL);
        
        panChassis.refresh();
        panHeat.refresh();
        panArmor.refresh();

        /* JUMP JETS */
        int jjCount = 2;
        if (panBasicInfo.getTechLevel() == SimpleTechLevel.INTRO) {
            jjCount = 1;
        } else if (isExperimental && !panBasicInfo.isClan()) {
            jjCount = jjTypes.length;
        }
        jjType.removeAllItems();

        for (int index = 0; index < jjCount; index++) {
            jjType.addItem(jjTypes[index]);
        }

        /* UNIT UPDATING */
        if (updateUnit) {
            setJumpJetCombo();
            if (jjType.getSelectedIndex() == -1) {
                jjType.setSelectedIndex(0);
                int jump = Math.min((Integer) jumpModel.getValue(), getMech()
                        .getWalkMP(true, false, true));
                UnitUtil.updateJumpJets(getMech(), jump, Mech.JUMP_STANDARD);
            }
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            removeAllListeners();
            if (spinner.equals(walkMPBase)) {
                recalculateEngineRating();
            } else if (spinner.equals(jumpMPBase)) {
                UnitUtil.updateJumpJets(getMech(), (Integer) jumpModel.getValue(),
                        getJumpJetType());
            }
            addAllListeners();

            refresh.refreshAll();
        }
    }
    
    private void recalculateEngineRating() {
        int rating = ((Integer) walkMPBase.getValue())
                * ((int) panChassis.getTonnage());
        if (getMech().isPrimitive()) {
            rating = (int)Math.ceil((rating * 1.2) / 5.0) * 5; 
        }
        if (getMech().getEngine().getRating() != rating) {
            panChassis.setEngineRating(rating);
            Engine engine = panChassis.getEngine();
            engine.setBaseChassisHeatSinks(getMech().getEngine()
                    .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks()));
            getMech().setEngine(engine);
            UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
        }
    }
    
    private boolean hasCTSpace(Engine engine, int gyroType, int cockpitType) {
        if (getMech().isSuperHeavy()) {
            return true;
        }
        int crits = 10;
        if (engine.getEngineType() == Engine.COMPACT_ENGINE) {
            crits -= 3;
        } else if (engine.hasFlag(Engine.LARGE_ENGINE)) {
            crits += 2;
        }
        if (gyroType == Mech.GYRO_COMPACT) {
            crits -= 2;
        } else if (gyroType == Mech.GYRO_XL) {
            crits += 2;
        }
        if ((cockpitType == Mech.COCKPIT_TORSO_MOUNTED)
                || (cockpitType == Mech.COCKPIT_VRRP)) {
            crits += 2;
        }
        return crits <= 12;
    }
    
    private void createArmorMountsAndSetArmorType(int at, int aTechLevel) {

        if (at == EquipmentType.T_ARMOR_PATCHWORK) {
            boolean isMixed = panBasicInfo.isMixedTech();
            List<EquipmentType> armors = panArmor.getAllArmors();
            List<TechComboBox<EquipmentType>> combos = new ArrayList<>();
            JPanel panel = new JPanel(new GridBagLayout());
            for (int loc = 0; loc < getMech().locations(); loc++) {
                TechComboBox<EquipmentType> cbLoc = new TechComboBox<>(eq -> eq.getName());
                cbLoc.showTechBase(isMixed);
                armors.forEach(a -> cbLoc.addItem(a));
                EquipmentType locArmor = EquipmentType.get(EquipmentType
                        .getArmorTypeName(getMech().getArmorType(loc),
                                TechConstants.isClan(getMech().getArmorTechLevel(loc))));
                cbLoc.setSelectedItem(locArmor);
                combos.add(cbLoc);
                JLabel label = new JLabel(getMech().getLocationName(loc));
                panel.add(label, GBC.std());
                panel.add(cbLoc, GBC.eol());
            }
            JOptionPane.showMessageDialog(this, panel,
                    "Please choose the armor types",
                    JOptionPane.QUESTION_MESSAGE);
            UnitUtil.removeISorArmorMounts(getMech(), false);
            for (int loc = 0; loc < getMech().locations(); loc++) {
                EquipmentType armor = (EquipmentType)combos.get(loc).getSelectedItem();
                getMech().setArmorTechLevel(armor.getTechLevel(panBasicInfo.getTechYear()), loc);
                getMech().setArmorType(EquipmentType.getArmorType(armor), loc);
                int crits = 0;
                switch (EquipmentType.getArmorType(armor)) {
                    case EquipmentType.T_ARMOR_STEALTH:
                    case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                        crits = 2;
                        break;
                    case EquipmentType.T_ARMOR_HEAVY_FERRO:
                        crits = 3;
                        break;
                    case EquipmentType.T_ARMOR_FERRO_FIBROUS:
                    case EquipmentType.T_ARMOR_REFLECTIVE:
                    case EquipmentType.T_ARMOR_REACTIVE:
                        if (armor.isClan()) {
                            crits = 1;
                        } else {
                            crits = 2;
                        }
                        break;
                }
                if (getMech().getEmptyCriticals(loc) < crits) {
                    JOptionPane .showMessageDialog(
                            null, armor.getName()
                            + " does not fit in location "
                            + getMech().getLocationName(loc)
                            + ". Resetting to Standard Armor in this location.",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET, loc);
                    getMech().setArmorType(EquipmentType.T_ARMOR_STANDARD, loc);
                } else {
                    for (; crits > 0; crits--) {
                        try {
                            getMech().addEquipment( new Mounted(getMech(), armor), loc, false);
                        } catch (LocationFullException ex) {
                        }
                    }
                }
            }
            panArmor.setFromEntity(getMech());
        } else {
            getMech().setArmorTechLevel(aTechLevel);
            getMech().setArmorType(at);
            final EquipmentType armor = EquipmentType.get(EquipmentType.getArmorTypeName(at,
                    TechConstants.isClan(aTechLevel)));
            int armorCount = armor.getCriticals(getMech());

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
                    getMech().setArmorType(EquipmentType.T_ARMOR_STANDARD);
                    getMech().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                    panArmor.setFromEntity(getMech());
                }
            } else {
                for (; armorCount > 0; armorCount--) {
                    try {
                        getMech().addEquipment(new Mounted(getMech(),
                                armor), Entity.LOC_NONE, false);
                    } catch (Exception ex) {
                    }
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

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
        panChassis.setAsCustomization();
    }

    @Override
    public void refreshSummary() {
        panSummary.refresh();
    }
    
    @Override
    public void chassisChanged(String chassis) {
        getMech().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getMech().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getMech().setYear(year);
    }

    @Override
    public void sourceChanged(String source) {
        getMech().setSource(source);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getMech().isClan()) || (mixed != getMech().isMixedTech())) {
            getMech().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        if (techLevel != getMech().getStaticTechLevel()) {
            updateTechLevel();
        }
    }
    
    private void updateTechLevel() {
        removeAllListeners();
        switch(panBasicInfo.getTechLevel()) {
            case INTRO:
                getMech().setTechLevel(panBasicInfo.isClan()? TechConstants.T_CLAN_TW : TechConstants.T_INTRO_BOXSET);
                break;
            case STANDARD:
                getMech().setTechLevel(panBasicInfo.isClan()? TechConstants.T_CLAN_TW : TechConstants.T_IS_TW_NON_BOX);
                break;
            case ADVANCED:
                getMech().setTechLevel(panBasicInfo.isClan()? TechConstants.T_CLAN_ADVANCED : TechConstants.T_IS_ADVANCED);
                break;
            case EXPERIMENTAL:
                getMech().setTechLevel(panBasicInfo.isClan()? TechConstants.T_CLAN_EXPERIMENTAL : TechConstants.T_IS_EXPERIMENTAL);
                break;
            case UNOFFICIAL:
            default:
                getMech().setTechLevel(panBasicInfo.isClan()? TechConstants.T_CLAN_UNOFFICIAL: TechConstants.T_IS_UNOFFICIAL);
                break;
        }

        if (!getMech().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getMech(), false);
        }
        createArmorMountsAndSetArmorType(getMech().getArmorType(0), getMech().getArmorTechLevel(0));
        populateChoices(true);
        armor.resetArmorPoints();
        UnitUtil.checkEquipmentByTechLevel(getMech());
        addAllListeners();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        getMech().setManualBV(manualBV);
    }

    @Override
    public void tonnageChanged(double tonnage) {
        boolean changedSuperHeavyStatus = getMech().isSuperHeavy() != tonnage <= 100;
        if (changedSuperHeavyStatus) {
            // if we switch from being superheavy to not being superheavy,
            // remove crits
            for (Mounted mount : getMech().getEquipment()) {
                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                    UnitUtil.removeCriticals(getMech(), mount);
                    UnitUtil.changeMountStatus(getMech(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
                }
            }
        }
        getMech().setWeight(tonnage);
        getMech().autoSetInternal();
        if (getMech().isSuperHeavy()) {
            getMech().setOriginalJumpMP(0);
        }
        populateChoices(true);
        if (changedSuperHeavyStatus) {
            // Internal structure crits may change
            UnitUtil.removeISorArmorMounts(getMech(), true);
            createISMounts(panChassis.getStructure());
            resetSystemCrits();
            refresh();
        }
        recalculateEngineRating();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void omniChanged(boolean omni) {
        getMech().setOmni(omni);
        getMech().getEngine().setBaseChassisHeatSinks(
                omni? Math.max(0, panHeat.getBaseCount()) : -1);
        panHeat.setFromMech(getMech());
        UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
        refresh.refreshPreview();
    }

    @Override
    public void typeChanged(int baseType, int motiveType, long etype) {
        boolean industrial = false;
        switch (baseType) {
            case MekChassisView.BASE_TYPE_INDUSTRIAL:
                industrial = true;
                //fall through
            case MekChassisView.BASE_TYPE_STANDARD:
                boolean primitive = getMech().isPrimitive();
                if (motiveType == MekChassisView.MOTIVE_TYPE_BIPED) {
                    if  (((getMech().getEntityType() & Entity.ETYPE_BIPED_MECH) == 0)
                            || ((getMech().getEntityType() & Entity.ETYPE_LAND_AIR_MECH) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_BIPED_MECH, primitive, industrial);
                    }
                } else if (motiveType == MekChassisView.MOTIVE_TYPE_QUAD) {
                    if  (((getMech().getEntityType() & Entity.ETYPE_QUAD_MECH) == 0)
                            || ((getMech().getEntityType() & Entity.ETYPE_QUADVEE) != 0)) {
                        eSource.createNewUnit(Entity.ETYPE_QUAD_MECH, primitive, industrial);
                    }
                } else if ((getMech().getEntityType() & Entity.ETYPE_TRIPOD_MECH) == 0) {
                    eSource.createNewUnit(Entity.ETYPE_TRIPOD_MECH, primitive, industrial);
                }
                break;
            case MekChassisView.BASE_TYPE_LAM:
                if (getMech() instanceof LandAirMech) {
                    ((LandAirMech)getMech()).setLAMType(motiveType);
                } else {
                    eSource.createNewUnit(Entity.ETYPE_LAND_AIR_MECH);
                }
                break;
            case MekChassisView.BASE_TYPE_QUADVEE:
                if (getMech() instanceof QuadVee) {
                    if (motiveType != ((QuadVee)getMech()).getMotiveType()) {
                        Optional<Mounted> mount = getMech().getMisc().stream()
                                .filter(m -> m.getType().hasFlag(MiscType.F_TRACKS))
                                .findAny();
                        if (mount.isPresent()) {
                            UnitUtil.removeMounted(getMech(), mount.get());
                        }
    
                        if (motiveType == QuadVee.MOTIVE_WHEEL) {
                            ((QuadVee)getMech()).setMotiveType(QuadVee.MOTIVE_WHEEL);
                            UnitUtil.createSpreadMounts(getMech(), EquipmentType.get("Wheels"));
                        } else {
                            ((QuadVee)getMech()).setMotiveType(QuadVee.MOTIVE_TRACK);
                            UnitUtil.createSpreadMounts(getMech(), EquipmentType.get("Tracks"));
                        }
                    }
                } else {
                    eSource.createNewUnit(Entity.ETYPE_QUADVEE);
                }
                break;
        }
        if (getMech().isIndustrial() != industrial) {
            getMech().setStructureType(industrial?
                    EquipmentType.T_STRUCTURE_INDUSTRIAL : EquipmentType.T_STRUCTURE_STANDARD);
        }

        refresh();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void structureChanged(EquipmentType structure) {
        UnitUtil.removeISorArmorMounts(getMech(), true);
        createISMounts(structure);
        refreshSummary();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void engineChanged(Engine engine) {
        if (!hasCTSpace(engine, panChassis.getGyroType(), panChassis.getCockpitType())) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this engine.",
                    "Bad Engine", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setEngine(getMech().getEngine());
            panChassis.addListener(this);
        } else {
            // Make sure we keep same number of base heat sinks for omnis
            engine.setBaseChassisHeatSinks(getMech().getEngine()
                    .getBaseChassisHeatSinks(getMech().hasCompactHeatSinks()));
            getMech().setEngine(engine);
            resetSystemCrits();
            UnitUtil.updateAutoSinks(getMech(), getMech().hasCompactHeatSinks());
            refreshSummary();
            refresh.refreshPreview();
            refresh.refreshStatus();
        }
    }

    @Override
    public void gyroChanged(int gyroType) {
        if (!hasCTSpace(panChassis.getEngine(), gyroType, panChassis.getCockpitType())) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this gyro.",
                    "Bad Gyro", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setGyroType(getMech().getGyroType());
            panChassis.addListener(this);
        } else {
            getMech().setGyroType(gyroType);
            resetSystemCrits();
        }
        refreshSummary();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void cockpitChanged(int cockpitType) {
        if (!hasCTSpace(panChassis.getEngine(), panChassis.getGyroType(), cockpitType)) {
            JOptionPane.showMessageDialog(
                    this, "There is not enough space in the center torso for this cockpit.",
                    "Bad Gyro", JOptionPane.ERROR_MESSAGE);
            panChassis.removeListener(this);
            panChassis.setCockpitType(getMech().getCockpitType());
            panChassis.addListener(this);
        } else {
            getMech().setCockpitType(cockpitType);
            resetSystemCrits();
            refreshSummary();
            refresh.refreshPreview();
            refresh.refreshStatus();
        }
    }

    @Override
    public void enhancementChanged(EquipmentType enhancement) {
        UnitUtil.removeEnhancements(getMech());
        if (null != enhancement) {
            if (enhancement.hasFlag(MiscType.F_MASC)) {
                Mounted mount = new Mounted(getMech(), enhancement);
                try {
                    getMech().addEquipment(mount, Entity.LOC_NONE, false);
                } catch (LocationFullException lfe) {
                    // this can't happen, we add to Entity.LOC_NONE
                }
            } else {
                UnitUtil.createSpreadMounts(getMech(), enhancement);
            }
        }
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void fullHeadEjectChanged(boolean eject) {
        getMech().setFullHeadEject(eject);
    }

    @Override
    public void resetChassis() {
        UnitUtil.resetBaseChassis(getMech());
        refresh.refreshAll();
    }

    @Override
    public void heatSinksChanged(int index, int count) {
        // Method for ASFs
    }

    @Override
    public void heatSinksChanged(EquipmentType hsType, int count) {
        // if we have the same type of heat sink, then we should not remove the
        // existing heat sinks
        int currentSinks = UnitUtil.countActualHeatSinks(getMech());
        if (getMech().hasWorkingMisc(hsType.getInternalName())) {
            if (count < currentSinks) {
                UnitUtil.removeHeatSinks(getMech(), currentSinks - count);
            } else if (count > currentSinks) {
                UnitUtil.addHeatSinkMounts(getMech(), count - currentSinks,
                        hsType);
            }
        } else {
            UnitUtil.removeHeatSinks(getMech(), count);
            UnitUtil.addHeatSinkMounts(getMech(), count, hsType);
        }
        getMech().resetSinks();
        panSummary.refresh();
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void heatSinkBaseCountChanged(int count) {
        getMech().getEngine().setBaseChassisHeatSinks(
                Math.max(0, count));
        UnitUtil.updateAutoSinks(getMech(), panHeat.getHeatSinkType().hasFlag(MiscType.F_COMPACT_HEAT_SINK));
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void armorTypeChanged(int at, int aTechLevel) {
        if (!getMech().hasPatchworkArmor()) {
            UnitUtil.removeISorArmorMounts(getMech(), false);
        }
        createArmorMountsAndSetArmorType(at, aTechLevel);
        if (!getMech().hasPatchworkArmor()) {
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
        getMech().setArmorTonnage(Math.round(tonnage * 2) / 2.0);
        armor.resetArmorPoints();
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        double maxArmor = UnitUtil.getMaximumArmorTonnage(getMech());
        getMech().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMech());
        panArmor.addListener(this);
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }
    
    @Override
    public void useRemainingTonnageArmor() {
        double currentTonnage = UnitUtil.getEntityVerifier(getMech())
                .calculateWeight();
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getMech());
        double totalTonnage = getMech().getWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.HALFTON);
        
        double maxArmor = Math.min(getMech().getArmorWeight() + remainingTonnage,
                UnitUtil.getMaximumArmorTonnage(getMech()));
        getMech().setArmorTonnage(maxArmor);
        armor.resetArmorPoints();
        panArmor.removeListener(this);
        panArmor.setFromEntity(getMech());
        panArmor.addListener(this);
        
        armor.refresh();
        panSummary.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

}