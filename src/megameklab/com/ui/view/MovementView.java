/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMech;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.BuildListener;

/**
 * Controls for setting a unit's speed
 * 
 * @author Neoancient
 *
 */
public class MovementView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 9047797409742756926L;

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BuildListener l) {
        listeners.add(l);
    }
    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }
    
    private final static int LABEL_INDEX_MEK  = 0;
    private final static int LABEL_INDEX_TANK = 1;
    private final static int LABEL_INDEX_AERO = 2;
    private final static int LABEL_INDEX_BA   = 3;

    private final SpinnerNumberModel spnWalkModel = new SpinnerNumberModel(1, 1, null, 1);
    private final SpinnerNumberModel spnJumpModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnWalk = new JSpinner(spnWalkModel);
    private final JSpinner spnJump = new JSpinner(spnJumpModel);
    private final TechComboBox<EquipmentType> cbJumpType =
                new TechComboBox<>(eq -> eq.getName().replaceAll("\\s+\\[.*?\\]",  ""));
    
    private final JLabel lblWalk = createLabel("", labelSize);
    private final JLabel lblRun = createLabel("", labelSize);
    private final JLabel lblJump = createLabel("", labelSize);
    private final JLabel lblJumpType = createLabel("", labelSize);
    
    private final JTextField txtRunBase = new JTextField();
    private final JTextField txtWalkFinal = new JTextField();
    private final JTextField txtRunFinal = new JTextField();
    private final JTextField txtJumpFinal = new JTextField();
    
    private final ITechManager techManager;
    private long etype;
    private boolean industrial;
    private String[] walkNames;
    private String[] runNames;
    private String[] jumpNames;
    
    public MovementView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        walkNames = resourceMap.getString("MovementView.lblWalk.values").split(","); //$NON-NLS-1$
        runNames = resourceMap.getString("MovementView.lblRun.values").split(","); //$NON-NLS-1$
        jumpNames = resourceMap.getString("MovementView.lblJump.values").split(","); //$NON-NLS-1$
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("MovementView.lblBase.text")), gbc); //$NON-NLS-1$

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("MovementView.lblFinal.text")), gbc); //$NON-NLS-1$

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblWalk, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        spnWalk.setToolTipText(resourceMap.getString("MovementView.spnWalk.tooltip")); //$NON-NLS-1$
        add(spnWalk, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        txtWalkFinal.setToolTipText(resourceMap.getString("MovementView.txtWalkFinal.tooltip")); //$NON-NLS-1$
        add(txtWalkFinal, gbc);
        spnWalk.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblRun, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtRunBase.setToolTipText(resourceMap.getString("MovementView.txtRunBase.tooltip")); //$NON-NLS-1$
        add(txtRunBase, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        txtRunFinal.setToolTipText(resourceMap.getString("MovementView.txtRunFinal.tooltip")); //$NON-NLS-1$
        add(txtRunFinal, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblJump, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        spnJump.setToolTipText(resourceMap.getString("MovementView.spnJump.tooltip")); //$NON-NLS-1$
        add(spnJump, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        txtJumpFinal.setToolTipText(resourceMap.getString("MovementView.txtJumpFinal.tooltip")); //$NON-NLS-1$
        add(txtJumpFinal, gbc);
        spnJump.addChangeListener(this);
        
        lblJumpType.setText(resourceMap.getString("MovementView.cbJumpType.text")); // $NON-NLS-1$
        cbJumpType.setNullValue(resourceMap.getString("MovementView.cbJumpType.null")); //$NON-NLS-1$
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblJumpType, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        cbJumpType.setToolTipText(resourceMap.getString("MovementView.cbJumpType.tooltip")); //$NON-NLS-1$
        add(cbJumpType, gbc);
        cbJumpType.addActionListener(this);

        setFieldSize(spnWalk.getEditor(), editorSize);
        setFieldSize(txtWalkFinal, editorSize);
        txtWalkFinal.setEditable(false);
        txtWalkFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        setFieldSize(txtRunBase, editorSize);
        setFieldSize(txtRunFinal, editorSize);
        txtRunBase.setEditable(false);
        txtRunFinal.setEditable(false);
        txtRunFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        setFieldSize(spnJump.getEditor(), editorSize);
        setFieldSize(txtJumpFinal, editorSize);
        txtJumpFinal.setEditable(false);
        txtJumpFinal.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    public void setFromEntity(Entity en) {
        etype = en.getEntityType();
        industrial = (en instanceof Mech) && ((Mech)en).isIndustrial();
        refresh();

        boolean improvedJJ = false;
        Optional<EquipmentType> jj = en.getMisc().stream().map(Mounted::getType)
                .filter(eq -> eq.hasFlag(MiscType.F_JUMP_JET) || eq.hasFlag(MiscType.F_UMU)
                        || eq.hasFlag(MiscType.F_JUMP_BOOSTER)).findAny();
        if (jj.isPresent()) {
            cbJumpType.removeActionListener(this);
            cbJumpType.setSelectedItem(jj.get());
            cbJumpType.addActionListener(this);
        }
        // LAMs have a minimum jump MP of 3, which implies a minimum walk
        int minWalk = 1;
        Integer maxWalk = null;
        int minJump = 0;
        Integer maxJump = en.getOriginalWalkMP();
        if (cbJumpType.getModel().getSize() == 0) { // No legal jump jet tech for this unit type
            maxJump = 0;
        } else if (en.hasETypeFlag(Entity.ETYPE_MECH)) {
            maxJump = TestMech.maxJumpMP((Mech)en);
        }
        if (en.hasETypeFlag(Entity.ETYPE_TANK)) {
            int minRating = 10 + Tank.getSuspensionFactor(en.getMovementMode(), en.getWeight());
            minWalk = Math.max(1, (int)(minRating / en.getWeight()));
        } else if (en.hasETypeFlag(Entity.ETYPE_LAND_AIR_MECH)) {
            minJump = 3;
            minWalk = improvedJJ? 2 : 3;
        } else if (en.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            cbJumpType.removeActionListener(this);
            maxWalk = TestBattleArmor.maxWalkMP((BattleArmor)en);
            if (((BattleArmor)en).getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD) {
                minWalk = 2;
            }
            cbJumpType.setSelectedItem(TestBattleArmor.BAMotiveSystems.getEquipment(en.getMovementMode()));
            if (en.getMovementMode() == EntityMovementMode.VTOL) {
                maxJump = TestBattleArmor.maxVtolMP((BattleArmor)en);
            } else if (en.getMovementMode() == EntityMovementMode.INF_UMU) {
                maxJump = TestBattleArmor.maxUmuMP((BattleArmor)en);
            } else {
                maxJump = TestBattleArmor.maxJumpMP((BattleArmor)en);
            }
            cbJumpType.addActionListener(this);
        } else if (en.hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            minWalk = 0; // Station-keeping drive. Legal for warships, though unusual.
        }

        spnWalkModel.setMinimum(minWalk);
        spnWalkModel.setMaximum(maxWalk);
        spnWalk.setValue(Math.max(minWalk, en.getOriginalWalkMP()));
        txtWalkFinal.setText(String.valueOf(en.getWalkMP()));

        //getOriginalRunMPWithoutMASC() still subtracts for hardened armor, so we just do the calculation here
        txtRunBase.setText(String.valueOf((int)Math.ceil(en.getOriginalWalkMP() * 1.5)));
        txtRunFinal.setText(en.getRunMPasString());
        
        spnJump.removeChangeListener(this);
        int jump0 = en.getOriginalJumpMP();
        int jump = en.getJumpMP();
        if (0 == jump0) {
            jump0 = en.getAllUMUCount();
            if (jump0 > 0) {
                jump = jump0;
            }
        }
        spnJumpModel.setMinimum(minJump);
        spnJumpModel.setMaximum(maxJump);
        spnJump.setValue(Math.max(minJump, jump0));
        txtJumpFinal.setText(String.valueOf(jump));
        spnJump.addChangeListener(this);
        
        setMovementModToolTips(en);
        
        int labelIndex = LABEL_INDEX_MEK;
        boolean showJump = true;
        boolean showJumpType = true;
        boolean showRun = true;
        if (en.hasETypeFlag(Entity.ETYPE_AERO)) {
            labelIndex = LABEL_INDEX_AERO;
            showJump = showJumpType = false;
        } else if (en.hasETypeFlag(Entity.ETYPE_VTOL) || en.hasETypeFlag(Entity.ETYPE_SUPPORT_TANK)) {
            labelIndex = LABEL_INDEX_TANK;
            showJump = showJumpType = false;
        } else if (en.hasETypeFlag(Entity.ETYPE_TANK)) {
            labelIndex = LABEL_INDEX_TANK;
            showJumpType = (en.getMovementMode() == EntityMovementMode.TRACKED)
                    || (en.getMovementMode() == EntityMovementMode.WHEELED)
                    || (en.getMovementMode() == EntityMovementMode.HOVER)
                    || (en.getMovementMode() == EntityMovementMode.WIGE);
        } else if (en.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            labelIndex = LABEL_INDEX_BA;
            showRun = false;
        }
        lblWalk.setText(walkNames[labelIndex]);
        lblRun.setText(runNames[labelIndex]);
        lblRun.setVisible(showRun);
        txtRunBase.setVisible(showRun);
        txtRunFinal.setVisible(showRun);
        lblJump.setText(jumpNames[labelIndex]);
        lblJump.setVisible(showJump);
        spnJump.setVisible(showJump);
        txtJumpFinal.setVisible(showJump);
        lblJumpType.setVisible(showJumpType);
        cbJumpType.setVisible(showJumpType);
        spnJump.setEnabled((null == maxJump) || (maxJump > 0));
        cbJumpType.setEnabled((null == maxJump) || (maxJump > 0));
        
        if ((maxJump != null) && (jump0 > maxJump)) {
            spnJump.setValue(spnJumpModel.getMaximum());
        } else if (jump0 < minJump) {
            spnJump.setValue(spnJumpModel.getMinimum());
        }
    }
    
    public void refresh() {
        if (cbJumpType.isVisible()) {
            EquipmentType prev = (EquipmentType)cbJumpType.getSelectedItem();
            cbJumpType.removeActionListener(this);
            cbJumpType.removeAllItems();
            for (EquipmentType eq : TestEntity.validJumpJets(etype, industrial)) {
                if (techManager.isLegal(eq)) {
                    cbJumpType.addItem(eq);
                }
            }
            cbJumpType.setSelectedItem(prev);
            cbJumpType.addActionListener(this);
            if (cbJumpType.getModel().getSize() > 0) {
                spnJump.setEnabled(true);
                cbJumpType.setEnabled(true);
                if ((cbJumpType.getSelectedIndex() < 0)) {
                    cbJumpType.setSelectedIndex(0);
                }
            } else {
                spnJump.setEnabled(false);
                cbJumpType.setEnabled(false);
            }
        }
    }
    
    private void setMovementModToolTips(Entity en) {
        StringJoiner walkTooltip = new StringJoiner(", ");
        StringJoiner runTooltip = new StringJoiner(", ");
        StringJoiner jumpTooltip = new StringJoiner(", ");
        
        if (en.hasModularArmor()) {
            walkTooltip.add("-1 (Modular armor)");
            jumpTooltip.add("-1 (Modular armor)");
        }
        if (en instanceof Mech) {
            if (((Mech)en).hasMPReducingHardenedArmor()) {
                runTooltip.add("-1 (Hardened armor)");
            }
            if (((Mech)en).hasArmedMASC()) {
                runTooltip.add("MASC/Supercharger");
            }
            int medShields = ((Mech)en).getNumberOfShields(MiscType.S_SHIELD_MEDIUM);
            int lgShields = ((Mech)en).getNumberOfShields(MiscType.S_SHIELD_LARGE);
            if (lgShields + medShields > 0) {
                walkTooltip.add(String.format("-%d (Shield)", lgShields + medShields));
            }
            if (lgShields > 0) {
                jumpTooltip.add("No Jump (Large Shield)");
            }
        } else if (en.hasWorkingMisc(MiscType.F_MASC)) {
            runTooltip.add("Supercharger");
        } else if (en.hasWorkingMisc(MiscType.F_JET_BOOSTER)) {
            runTooltip.add("Jet Booster");
        }
        Optional<Mounted> partialWing = en.getMisc().stream()
                .filter(m -> m.getType().hasFlag(MiscType.F_PARTIAL_WING)).findAny();
        if (partialWing.isPresent()) {
            int bonus = 2;
            if (en instanceof Mech) {
                bonus = ((Mech)en).getPartialWingJumpBonus(partialWing.get());
            }
            jumpTooltip.add(String.format("+%d (Partial wing)", bonus));
        }
        txtWalkFinal.setToolTipText(walkTooltip.length() > 0? walkTooltip.toString() : null);
        txtRunFinal.setToolTipText(runTooltip.length() > 0? runTooltip.toString() : null);
        txtJumpFinal.setToolTipText(jumpTooltip.length() > 0 && en.getOriginalJumpMP(true) > 0?
                jumpTooltip.toString() : null);
    }
    
    public int getWalk() {
        return spnWalkModel.getNumber().intValue();
    }
    
    public int getJump() {
        return spnJumpModel.getNumber().intValue();
    }
    
    public EquipmentType getJumpJet() {
        return (EquipmentType)cbJumpType.getSelectedItem();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnWalk) {
            listeners.forEach(l -> l.walkChanged(getWalk()));
        } else if (e.getSource() == spnJump) {
            listeners.forEach(l -> l.jumpChanged(getJump(), getJumpJet()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbJumpType) {
            listeners.forEach(l -> l.jumpTypeChanged(getJumpJet()));
        }
    }
}
