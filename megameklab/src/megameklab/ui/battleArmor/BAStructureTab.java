/*
 * MegaMekLab
 * Copyright (C) 2008-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import megamek.codeUtilities.MathUtility;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.ArmorType;
import megamek.common.equipment.MiscMounted;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestBattleArmor.BAManipulator;
import megamek.common.verifier.TestEntity;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.*;
import megameklab.ui.listeners.ArmorAllocationListener;
import megameklab.ui.listeners.BABuildListener;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAStructureTab extends ITab implements ActionListener, ChangeListener, BABuildListener, ArmorAllocationListener {
    private RefreshListener refresh;

    Dimension labelSize = new Dimension(110, 25);

    private BasicInfoView panBasicInfo;
    private BAChassisView panChassis;
    private MovementView panMovement;
    private BAProtoArmorView panArmor;
    private BAEnhancementView panEnhancements;
    private IconView iconView;

    // Manipulator Panel
    private final CustomComboBox<String> leftManipSelect = new CustomComboBox<>(this::manipulatorDisplayName);
    private final CustomComboBox<String> rightManipSelect = new CustomComboBox<>(this::manipulatorDisplayName);

    private final SpinnerNumberModel spnLeftManipulatorSizeModel = new SpinnerNumberModel(0.5, 0.5, Double.MAX_VALUE, 0.5);
    private final SpinnerNumberModel spnRightManipulatorSizeModel = new SpinnerNumberModel(0.5, 0.5, Double.MAX_VALUE, 0.5);
    private final JSpinner spnLeftManipulatorSize = new JSpinner(spnLeftManipulatorSizeModel);
    private final JSpinner spnRightManipulatorSize = new JSpinner(spnRightManipulatorSizeModel);
    private final JLabel lblSize = createLabel("Size:", labelSize);

    private PreviewTab previewTab;

    public BAStructureTab(EntitySource eSource) {
        super(eSource);
        setUpPanels();
        refresh();
    }

    public void setUpPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        previewTab = new PreviewTab(eSource);
        panBasicInfo = new BasicInfoView(getBattleArmor().getConstructionTechAdvancement());
        panChassis = new BAChassisView(panBasicInfo);
        panMovement = new MovementView(panBasicInfo);
        panArmor = new BAProtoArmorView(panBasicInfo);
        iconView = new IconView();
        JPanel manipPanel = new JPanel(new GridBagLayout());
        panEnhancements = new BAEnhancementView(panBasicInfo);
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(250, 25);
        Dimension spinnerSize = new Dimension(100, 25);

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
        gbc.gridx = 2;
        manipPanel.add(lblSize, gbc);
        gbc.gridx = 3;
        manipPanel.add(spnLeftManipulatorSize, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        manipPanel.add(rightManipSelect, gbc);
        gbc.gridx = 3;
        manipPanel.add(spnRightManipulatorSize, gbc);

        setFieldSize(leftManipSelect, comboSize);
        setFieldSize(rightManipSelect, comboSize);
        setFieldSize(spnLeftManipulatorSize, spinnerSize);
        setFieldSize(spnRightManipulatorSize, spinnerSize);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassis.setBorder(BorderFactory.createTitledBorder("Chassis"));
        panMovement.setBorder(BorderFactory.createTitledBorder("Movement"));
        panArmor.setBorder(BorderFactory.createTitledBorder("Armor"));
        manipPanel.setBorder(BorderFactory.createTitledBorder("Manipulators"));
        panEnhancements.setBorder(BorderFactory.createTitledBorder("Enhancements"));

        leftPanel.add(panBasicInfo);
        leftPanel.add(iconView);
        leftPanel.add(panChassis);
        leftPanel.add(panMovement);
        leftPanel.add(panArmor);

        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(previewTab);
        rightPanel.add(panEnhancements);
        rightPanel.add(manipPanel);

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

    public JLabel createLabel(String text, Dimension maxSize) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        setFieldSize(label, maxSize);
        return label;
    }

    @Override
    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void refresh() {
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.setFromEntity(getBattleArmor());
        panMovement.setFromEntity(getBattleArmor());
        panArmor.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        iconView.setFromEntity(getEntity());

        removeAllListeners();

        // Manipulators
        leftManipSelect.removeAllItems();
        rightManipSelect.removeAllItems();
        leftManipSelect.addItem(BattleArmor.MANIPULATOR_TYPE_STRINGS[BattleArmor.MANIPULATOR_NONE]);
        rightManipSelect.addItem(BattleArmor.MANIPULATOR_TYPE_STRINGS[BattleArmor.MANIPULATOR_NONE]);
        for (BAManipulator manip : BAManipulator.values()) {
            EquipmentType et = EquipmentType.get(manip.internalName);
            if ((null != et) && getTechManager().isLegal(et)) {
                leftManipSelect.addItem(et.getName());
                rightManipSelect.addItem(et.getName());
            }
        }
        BAManipulator manipulator = BAManipulator.getManipulator(
                getBattleArmor().getLeftManipulatorName());
        leftManipSelect.setSelectedItem(
                BattleArmor.MANIPULATOR_NAME_STRINGS[manipulator.type]);
        manipulator = BAManipulator.getManipulator(
                getBattleArmor().getRightManipulatorName());
        rightManipSelect.setSelectedItem(
                BattleArmor.MANIPULATOR_NAME_STRINGS[manipulator.type]);
        refreshManipulatorSizes(BattleArmor.MOUNT_LOC_LARM, spnLeftManipulatorSize, spnLeftManipulatorSizeModel);
        // For variable-sized pair-mounted manipulators, we'll only use one spinner
        spnRightManipulatorSize.setEnabled(!manipulator.pairMounted);
        refreshManipulatorSizes(BattleArmor.MOUNT_LOC_RARM, spnRightManipulatorSize, spnRightManipulatorSizeModel);
        lblSize.setVisible(spnLeftManipulatorSize.isVisible() || spnRightManipulatorSize.isVisible());

        refreshPreview();

        addAllListeners();
    }

    /**
     * Sets values for the size control if the manipulator has a variable size; otherwise hides it.
     * @param mountLoc The mount location
     * @param spinner  The spinner to show/hide
     * @param model    The spinner's number model
     */
    private void refreshManipulatorSizes(int mountLoc, JSpinner spinner, SpinnerNumberModel model) {
        Optional<MiscMounted> mounted = getBattleArmor().getMisc().stream()
                .filter(m -> m.getType().hasFlag(MiscType.F_BA_MANIPULATOR) && (m.getBaMountLoc() == mountLoc))
                .findFirst();
        if (mounted.isPresent() && mounted.get().getType().isVariableSize()) {
            model.setValue(mounted.get().getSize());
            model.setStepSize(mounted.get().getType().variableStepSize());
            model.setMinimum(mounted.get().getType().variableStepSize());
            spinner.setVisible(true);
        } else {
            spinner.setVisible(false);
        }
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    /*
     * Used by MekHQ to set the tech faction for custom refits.
     */
    public void setTechFaction(int techFaction) {
        panBasicInfo.setTechFaction(techFaction);
    }

    public void addAllListeners() {
        leftManipSelect.addActionListener(this);
        rightManipSelect.addActionListener(this);
        spnLeftManipulatorSize.addChangeListener(this);
        spnRightManipulatorSize.addChangeListener(this);

        panBasicInfo.addListener(this);
        panChassis.addListener(this);
        panMovement.addListener(this);
        panArmor.addListener(this);
        panEnhancements.addListener(this);
    }

    public void removeAllListeners() {
        leftManipSelect.removeActionListener(this);
        rightManipSelect.removeActionListener(this);
        spnLeftManipulatorSize.removeChangeListener(this);
        spnRightManipulatorSize.removeChangeListener(this);

        panBasicInfo.removeListener(this);
        panChassis.removeListener(this);
        panMovement.removeListener(this);
        panArmor.removeListener(this);
        panEnhancements.removeListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource() instanceof JComboBox) {
            if (e.getSource().equals(leftManipSelect) || e.getSource().equals(rightManipSelect)) {
                String name = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
                setManipulator(BAManipulator.getManipulator(name),
                        e.getSource().equals(leftManipSelect) ? BattleArmor.MOUNT_LOC_LARM : BattleArmor.MOUNT_LOC_RARM,
                        true);
            }
        }
        refresh.refreshAll();
    }

    private void setManipulator(BAManipulator manipulator, int mountLoc, boolean checkPaired) {
        MiscMounted current = getManipulator(mountLoc);
        if (current != null) {
            UnitUtil.removeMounted(getBattleArmor(), current);
        }
        if (manipulator != BAManipulator.NONE) {
            MiscMounted newMount = new MiscMounted(getBattleArmor(), (MiscType) EquipmentType.get(manipulator.internalName));
            newMount.setBaMountLoc(mountLoc);
            try {
                getBattleArmor().addEquipment(newMount, BattleArmor.LOC_SQUAD, false);
            } catch (LocationFullException ex) {
                LogManager.getLogger().error("Could not mount " + manipulator, ex);
            }
        }
        if (checkPaired) {
            int otherArm = mountLoc == (BattleArmor.MOUNT_LOC_LARM) ?
                    BattleArmor.MOUNT_LOC_RARM : BattleArmor.MOUNT_LOC_LARM;
            if (manipulator.pairMounted) {
                setManipulator(manipulator, otherArm, false);
            } else if ((current != null) && isPairedManipulator(current.getType())) {
                MiscMounted toRemove = getManipulator(otherArm);
                if (toRemove != null) {
                    UnitUtil.removeMounted(getBattleArmor(), toRemove);
                }
            }
        }
    }

    private @Nullable MiscMounted getManipulator(int mountLoc) {
        return getBattleArmor().getMisc().stream()
                .filter(m -> (m.getBaMountLoc() == mountLoc) && m.getType().hasFlag(MiscType.F_BA_MANIPULATOR))
                .findFirst().orElse(null);
    }

    private boolean isPairedManipulator(EquipmentType eq) {
        BAManipulator manipulator = BAManipulator.getManipulator(eq.getInternalName());
        if (manipulator != null) {
            return manipulator.pairMounted;
        } else {
            return false;
        }
    }

    @Override
    public void stateChanged(ChangeEvent evt) {
        if (evt.getSource() == spnLeftManipulatorSize) {
            setManipulatorSize(BattleArmor.MOUNT_LOC_LARM, spnLeftManipulatorSizeModel.getNumber().doubleValue());
            if (BAManipulator.getManipulator(getBattleArmor().getLeftManipulatorName()).pairMounted) {
                spnRightManipulatorSizeModel.setValue(spnLeftManipulatorSizeModel.getValue());
            }
        } else if (evt.getSource() == spnRightManipulatorSize) {
            setManipulatorSize(BattleArmor.MOUNT_LOC_RARM, spnRightManipulatorSizeModel.getNumber().doubleValue());
        }
        refresh.refreshAll();
    }

    private void setManipulatorSize(int mountLoc, double size) {
        Optional<MiscMounted> mounted = getBattleArmor().getMisc().stream()
                .filter(m -> m.getType().hasFlag(MiscType.F_BA_MANIPULATOR) && (m.getBaMountLoc() == mountLoc))
                .findFirst();
        mounted.ifPresent(value -> value.setSize(size));
    }

    /**
     * Extracts the actual name of the manipulator from the full equipment name
     */
    private String manipulatorDisplayName(String name) {
        int start = name.indexOf("[");
        if (start > 0) {
            return name.substring(start + 1).replace("]", "");
        }
        return name;
    }

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
    }

    public void refreshPreview() {
        previewTab.refresh();
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
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getBattleArmor().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
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
        updateTechLevel();
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        removeAllListeners();
        getBattleArmor().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        if (UnitUtil.checkEquipmentByTechLevel(getBattleArmor(), panBasicInfo)) {
            refresh.refreshEquipment();
        } else {
            refresh.refreshEquipmentTable();
        }
        panChassis.refresh();
        panMovement.refresh();
        panEnhancements.setFromEntity(getBattleArmor());
        panArmor.refresh();
        ArmorType armor = panArmor.getArmor();
        // If the current armor is no longer available, switch to the current selection
        if (EquipmentType.getArmorType(armor) != getBattleArmor().getArmorType(BattleArmor.LOC_SQUAD)
                || (armor.getTechLevel(getBattleArmor().getYear())
                != getBattleArmor().getArmorTechLevel(BattleArmor.LOC_SQUAD))) {
            armorTypeChanged(armor);
        }
        addAllListeners();
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
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
        if (getEntity().getMisc().stream().noneMatch(m -> m.getType().equals(jumpJet))) {
            UnitUtil.removeAllMiscMounteds(getBattleArmor(), MiscType.F_JUMP_JET);
            UnitUtil.removeAllMiscMounteds(getBattleArmor(), MiscType.F_BA_VTOL);
            UnitUtil.removeAllMiscMounteds(getBattleArmor(), MiscType.F_UMU);
            if (getBattleArmor().getOriginalJumpMP() > 0 && jumpJet != null) {
                try {
                    getBattleArmor().addEquipment(jumpJet, BattleArmor.LOC_NONE);
                } catch (LocationFullException ignored) {
                    // not when we're adding to LOC_NONE
                }
            }
        }
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
        panChassis.setFromEntity(getBattleArmor());
        panMovement.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
        refresh.refreshBuild();
    }

    @Override
    public void weightClassChanged(int weightClass) {
        getBattleArmor().setWeightClass(weightClass);
        if (weightClass > EntityWeightClass.WEIGHT_ULTRA_LIGHT) {
            getBattleArmor().setIsExoskeleton(false);
            getBattleArmor().setClanExoWithoutHarjel(false);
        }
        panBasicInfo.setFromEntity(getBattleArmor());
        panChassis.refresh();
        panMovement.setFromEntity(getBattleArmor());
        panArmor.setFromEntity(getBattleArmor());
        panEnhancements.setFromEntity(getBattleArmor());
        refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void exoskeletonChanged(boolean exoskeleton) {
        getBattleArmor().setIsExoskeleton(exoskeleton);
        if (exoskeleton && !panBasicInfo.useClanTechBase()) {
            getBattleArmor().setClanExoWithoutHarjel(panChassis.hasHarjel());
        }
        panBasicInfo.setFromEntity(getBattleArmor());
        panArmor.refresh();
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
        if (squadSize != getBattleArmor().getTroopers()) {
            // We need to resize several arrays. This clears out the critical slots, so
            // we're going to preserve them before refreshing and restore the data afterward.
            CriticalSlot[][] slots = new CriticalSlot[getBattleArmor().locations()][];
            for (int loc = 0; loc < getBattleArmor().locations(); loc++) {
                slots[loc] = new CriticalSlot[getBattleArmor().getNumberOfCriticals(loc)];
                for (int i = 0; i < getBattleArmor().getNumberOfCriticals(loc); i++) {
                    slots[loc][i] = getBattleArmor().getCritical(loc, i);
                }
            }
            int armor = getBattleArmor().getArmor(BattleArmor.LOC_TROOPER_1);
            getBattleArmor().setTroopers(squadSize);
            getBattleArmor().refreshLocations();
            for (int loc = 0; loc < Math.min(getBattleArmor().locations(), slots.length); loc++) {
                for (int i = 0; i < slots[loc].length; i++) {
                    getBattleArmor().setCritical(loc, i, slots[loc][i]);
                }
            }
            getBattleArmor().autoSetInternal();
            for (int i = 1; i < getBattleArmor().locations(); i++) {
                getBattleArmor().initializeArmor(armor, i);
            }
            refresh.refreshStatus();
            refresh.refreshPreview();
            refresh.refreshBuild();
        }
    }

    @Override
    public void turretChanged(int type, int size) {
        getBattleArmor().setModularTurret(type == BAChassisView.TURRET_MODULAR);
        if (type == BAChassisView.TURRET_NONE) {
            size = 0;
        } else {
            size = Math.max(1, size);
        }
        getBattleArmor().setTurretSize(size);

        if (size == 0) {
            for (Mounted mount : getBattleArmor().getEquipment()) {
                if (mount.getBaMountLoc() == BattleArmor.MOUNT_LOC_TURRET) {
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_BODY);
                }
            }
        }
        panChassis.setFromEntity(getBattleArmor());
        refresh.refreshStatus();
        refresh.refreshPreview();
        refresh.refreshBuild();
    }

    @Override
    public void enhancementChanged(EquipmentType eq, boolean selected) {
        if (selected) {
            try {
                int loc = eq.hasFlag(MiscType.F_MASC) ? BattleArmor.MOUNT_LOC_NONE : BattleArmor.MOUNT_LOC_BODY;
                int numTimesToAdd = 1;
                // Spreadable equipment needs to have a Mounted entry
                // for each critical
                if (eq.isSpreadable()) {
                    numTimesToAdd = eq.getCriticals(getBattleArmor());
                }
                for (int i = 0; i < numTimesToAdd; i++) {
                    Mounted newMount = Mounted.createMounted(getBattleArmor(), eq);
                    newMount.setBaMountLoc(loc);
                    getBattleArmor().addEquipment(newMount, BattleArmor.LOC_SQUAD, false);
                }
            } catch (Exception ex) {
                // Shouldn't happen with BA
                LogManager.getLogger().error("", ex);
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
        refresh.refreshStatus();
    }

    @Override
    public void armorFactorChanged(int points) {
        for (int i = 0; i < getBattleArmor().locations(); i++) {
            getBattleArmor().initializeArmor(points, i);
        }
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void armorTypeChanged(ArmorType armor) {
        UnitUtil.removeISorArmorMounts(getBattleArmor(), false);
        int armorCount = armor.getCriticals(getBattleArmor());
        getBattleArmor().setArmorTechLevel(armor.getTechLevel(getBattleArmor().getYear()));
        getBattleArmor().setArmorType(armor.getInternalName());

        for (; armorCount > 0; armorCount--) {
            try {
                getBattleArmor().addEquipment(Mounted.createMounted(getBattleArmor(), armor),
                        BattleArmor.LOC_SQUAD, false);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }
        }
        refresh.refreshBuild();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void maximizeArmor() {
        armorFactorChanged(getBattleArmor().getMaximumArmorPoints());
        panArmor.removeListener(this);
        panArmor.setFromEntity(getBattleArmor());
        panArmor.addListener(this);
    }

    @Override
    public void useRemainingTonnageArmor() {
        final TestBattleArmor testBA = (TestBattleArmor) UnitUtil.getEntityVerifier(getBattleArmor());
        double currentTonnage = testBA.calculateWeight(BattleArmor.LOC_SQUAD);
        currentTonnage += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());
        double totalTonnage = getBattleArmor().getTrooperWeight();
        double remainingTonnage = TestEntity.floor(
                totalTonnage - currentTonnage, TestEntity.Ceil.KILO);
        int points = (int) UnitUtil.getRawArmorPoints(getBattleArmor(), remainingTonnage);
        int maxArmor = MathUtility.clamp(getBattleArmor().getMaximumArmorPoints(), 0,
                points + getBattleArmor().getOArmor(BattleArmor.LOC_TROOPER_1));
        armorFactorChanged(maxArmor);
        panArmor.removeListener(this);
        panArmor.setFromEntity(getBattleArmor());
        panArmor.addListener(this);
    }

    @Override
    public void mulIdChanged(int mulId) {
        getBattleArmor().setMulId(mulId);
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
    }
}
