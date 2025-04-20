/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 */
package megameklab.ui.infantry;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.Optional;
import java.util.StringJoiner;
import javax.swing.*;

import megamek.common.*;
import megamek.common.options.IOption;
import megamek.common.options.PilotOptions;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.generalUnit.IconView;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.InfantryUtil;
import megameklab.util.UnitUtil;

public class CIStructureTab extends ITab implements InfantryBuildListener {
    private RefreshListener refresh;

    public static final int T_INFANTRY_WEAPONS = 0;
    public static final int T_FIELD_GUNS = 1;
    public static final int T_ARMOR_KIT = 2;
    public static final int T_SPECIALIZATION = 3;
    public static final int T_MOUNT = 4;
    public static final int T_AUGMENTATION = 5;
    private static final EquipmentType antiMekGear = EquipmentType.get(EquipmentTypeLookup.ANTI_MEK_GEAR);

    private final BasicInfoView panBasicInfo;
    private final CIPlatoonTypeView panPlatoonType;
    private final CIWeaponView panWeapons;
    private final IconView iconView;

    private final String[] tabNames = { "Weapons", "Field Guns", "Armor Kit", "Specializations", "Mount",
                                        "Augmentation" };

    private final JTextField txtArmor = new JTextField("None");
    private final JTextPane txtSpecializations = new JTextPane();
    private final JTextPane txtAugmentations = new JTextPane();

    private JTabbedPane equipmentPane;

    private final CIEquipmentView weaponView;
    private final CIFieldGunView fieldGunView;
    private final CIArmorView armorView;
    private final CISpecializationView specializationView;
    private final CIMountView mountView;
    private final CIAugmentationView augmentationView;

    public CIStructureTab(EntitySource eSource) {
        super(eSource);
        panBasicInfo = new BasicInfoView(getInfantry().getConstructionTechAdvancement());
        panPlatoonType = new CIPlatoonTypeView(panBasicInfo);
        panWeapons = new CIWeaponView(panBasicInfo);
        weaponView = new CIEquipmentView(eSource, panBasicInfo);
        fieldGunView = new CIFieldGunView(eSource, panBasicInfo);
        armorView = new CIArmorView(eSource, panBasicInfo);
        specializationView = new CISpecializationView(eSource);
        mountView = new CIMountView(eSource);
        augmentationView = new CIAugmentationView(eSource);
        iconView = new IconView();
        setUpPanels();
        refresh();
    }

    public void setUpPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel advancedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(200, 25);
        Dimension labelSize = new Dimension(110, 25);

        txtArmor.setEditable(false);
        txtSpecializations.setEditable(false);
        txtSpecializations.setContentType("text/html");
        txtAugmentations.setEditable(false);
        txtAugmentations.setContentType("text/html");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        advancedPanel.add(createLabel("Armor:", labelSize), gbc);
        gbc.gridx = 1;
        advancedPanel.add(txtArmor, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        advancedPanel.add(createLabel("Specializations:", labelSize), gbc);
        gbc.gridx = 1;
        advancedPanel.add(txtSpecializations, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        advancedPanel.add(createLabel("Augmentations:", labelSize), gbc);
        gbc.gridx = 1;
        advancedPanel.add(txtAugmentations, gbc);

        setFieldSize(txtArmor, comboSize);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panPlatoonType.setBorder(BorderFactory.createTitledBorder("Movement and Size"));
        panWeapons.setBorder(BorderFactory.createTitledBorder("Current Weapons"));
        advancedPanel.setBorder(BorderFactory.createTitledBorder("Advanced"));

        equipmentPane = new JTabbedPane();
        equipmentPane.addTab(tabNames[T_INFANTRY_WEAPONS], weaponView);
        equipmentPane.addTab(tabNames[T_FIELD_GUNS], fieldGunView);
        equipmentPane.addTab(tabNames[T_ARMOR_KIT], armorView);
        equipmentPane.addTab(tabNames[T_SPECIALIZATION], specializationView);
        equipmentPane.addTab(tabNames[T_MOUNT], mountView);
        equipmentPane.addTab(tabNames[T_AUGMENTATION], augmentationView);

        leftPanel.add(panBasicInfo);
        leftPanel.add(iconView);
        leftPanel.add(panPlatoonType);
        leftPanel.add(panWeapons);
        leftPanel.add(advancedPanel);
        leftPanel.add(Box.createVerticalGlue());
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(leftPanel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(equipmentPane, gbc);
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    public void setTechFaction(int techFaction) {
        panBasicInfo.setTechFaction(techFaction);
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

        panBasicInfo.setFromEntity(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        iconView.setFromEntity(getEntity());

        removeAllListeners();

        EquipmentType armor = getInfantry().getArmorKit();
        if (null != armor) {
            txtArmor.setText(armor.getName());
        } else {
            String desc = getInfantry().getArmorDesc();
            if (desc.equals("1.0")) {
                txtArmor.setText("None");
            } else {
                txtArmor.setText(desc);
            }
        }
        updateSpecializations();
        StringJoiner sj = new StringJoiner("<br/>");
        for (Enumeration<IOption> e = getInfantry().getCrew().getOptions(PilotOptions.MD_ADVANTAGES);
              e.hasMoreElements(); ) {
            final IOption opt = e.nextElement();
            if (getInfantry().getCrew().getOptions().booleanOption(opt.getName())) {
                sj.add(opt.getDisplayableName());
            }
        }
        if (sj.length() > 0) {
            txtAugmentations.setText(sj.toString());
        } else {
            txtAugmentations.setText("None");
        }

        weaponView.refresh();
        fieldGunView.refresh();
        armorView.refresh();
        specializationView.refresh();
        mountView.refresh();
        augmentationView.refresh();

        enableTabs();

        addAllListeners();
    }

    public void addAllListeners() {
        panBasicInfo.addListener(this);
        panPlatoonType.addListener(this);
        panWeapons.addListener(this);
        specializationView.addListener(this);
    }

    public void removeAllListeners() {
        panWeapons.removeListener(this);
        panBasicInfo.removeListener(this);
        panPlatoonType.removeListener(this);
        specializationView.removeListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        weaponView.addRefreshedListener(refresh);
        fieldGunView.addRefreshedListener(refresh);
        armorView.addRefreshedListener(refresh);
        mountView.addRefreshedListener(refresh);
        augmentationView.addRefreshedListener(refresh);
    }

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
    }

    private void updateSpecializations() {
        if (getInfantry().getSpecializations() == 0) {
            txtSpecializations.setText("None");
        } else {
            StringJoiner sj = new StringJoiner("<br/>");
            for (int i = 0; i < Infantry.NUM_SPECIALIZATIONS; i++) {
                if (getInfantry().hasSpecialization(1 << i)) {
                    sj.add(Infantry.getSpecializationName(1 << i));
                }
            }
            txtSpecializations.setText(sj.toString());
        }
        if (getInfantry().hasSpecialization(Infantry.TAG_TROOPS) &&
                  ((getInfantry().getSecondaryWeaponsPerSquad() < 2) ||
                         (getInfantry().getSecondaryWeapon() == null) ||
                         !getInfantry().getSecondaryWeapon().hasFlag(WeaponType.F_TAG))) {
            InfantryUtil.replaceMainWeapon(getInfantry(),
                  (InfantryWeapon) EquipmentType.get(EquipmentTypeLookup.INFANTRY_TAG),
                  true);
            getInfantry().setSecondaryWeaponsPerSquad(2);
        }
    }

    private void enableTabs() {
        SimpleTechLevel level = panBasicInfo.getTechLevel();
        if (level.ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtArmor.setEnabled(true);
            txtSpecializations.setEnabled(true);
            equipmentPane.setEnabledAt(T_FIELD_GUNS,
                  getInfantry().getMovementMode() == EntityMovementMode.INF_MOTORIZED ||
                        getInfantry().getMovementMode() == EntityMovementMode.TRACKED ||
                        getInfantry().getMovementMode() == EntityMovementMode.WHEELED);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, true);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, true);
            equipmentPane.setEnabledAt(T_MOUNT, getInfantry().getMount() != null);
            // Experimental level
            txtAugmentations.setEnabled(level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
            equipmentPane.setEnabledAt(T_AUGMENTATION, level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
        } else {
            txtArmor.setEnabled(false);
            txtSpecializations.setEnabled(false);
            txtAugmentations.setEnabled(false);
            equipmentPane.setEnabledAt(T_FIELD_GUNS, false);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, false);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, false);
            equipmentPane.setEnabledAt(T_MOUNT, false);
            equipmentPane.setEnabledAt(T_AUGMENTATION, false);
        }
        if (!equipmentPane.isEnabledAt(equipmentPane.getSelectedIndex())) {
            equipmentPane.setSelectedIndex(T_INFANTRY_WEAPONS);
        }
    }

    public void refreshEquipmentTable() {
        weaponView.refresh();
    }

    @Override
    public void refreshSummary() {
        // TODO Auto-generated method stub

    }

    @Override
    public void chassisChanged(String chassis) {
        getInfantry().setChassis(chassis);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void modelChanged(String model) {
        getInfantry().setModel(model);
        refresh.refreshHeader();
        refresh.refreshPreview();
        iconView.refresh();
    }

    @Override
    public void yearChanged(int year) {
        getInfantry().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        if (!panBasicInfo.isLegal(getInfantry().getMotiveTechAdvancement())) {
            motiveTypeChanged(EntityMovementMode.INF_LEG, false);
        }
        getInfantry().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        UnitUtil.checkEquipmentByTechLevel(getInfantry(), panBasicInfo);
        InfantryUtil.resetInfantryArmor(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        updateSpecializations();
        enableTabs();
        weaponView.refresh();
        fieldGunView.refresh();
        armorView.refresh();
        specializationView.refresh();
        augmentationView.refresh();
        refresh.refreshPreview();
    }

    @Override
    public void sourceChanged(String source) {
        getInfantry().setSource(source);
        refresh.refreshPreview();
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getInfantry().isClan()) || (mixed != getInfantry().isMixedTech())) {
            getInfantry().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void motiveTypeChanged(EntityMovementMode movementMode, boolean alt) {
        if (alt && (movementMode == EntityMovementMode.INF_UMU)) {
            getInfantry().setMotorizedScuba();
        } else if (movementMode.isNone()) {
            // Pick a default
            getInfantry().setMount(InfantryMount.HORSE);
        } else {
            getInfantry().setMovementMode(movementMode);
            getInfantry().setMount(null);
        }
        getInfantry().setMicrolite(alt && (movementMode == EntityMovementMode.VTOL));

        if (getInfantry().getMovementMode() != EntityMovementMode.INF_MOTORIZED &&
                  getInfantry().getMovementMode() != EntityMovementMode.TRACKED &&
                  getInfantry().getMovementMode() != EntityMovementMode.WHEELED) {
            InfantryUtil.replaceFieldGun(getInfantry(), null, 0);
        }
        enableTabs();
        TestInfantry.adaptAntiMekAttacks(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        specializationView.refresh();
        mountView.refresh();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }

    @Override
    public void platoonSizeChanged(int numSquads, int squadSize) {
        getInfantry().setSquadCount(numSquads);
        getInfantry().setSquadSize(squadSize);
        getInfantry().autoSetInternal();
        panPlatoonType.setFromEntity(getInfantry());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void specializationsChanged() {
        updateSpecializations();
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        weaponView.refresh();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void numSecondaryChanged(final int count) {
        if (getInfantry().getSecondaryWeapon() == null) {
            getInfantry().setSecondaryWeaponsPerSquad(0);
        } else {
            if (count == 0) {
                InfantryUtil.replaceMainWeapon(getInfantry(), null, true);
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.TAG_TROOPS);
            }
            getInfantry().setSecondaryWeaponsPerSquad(count);
        }
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void numFieldGunsChanged(final int count) {
        Optional<WeaponType> fieldGun = getInfantry().getWeaponList()
                                              .stream()
                                              .filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
                                              .map(Mounted::getType)
                                              .findAny();
        InfantryUtil.replaceFieldGun(getInfantry(), fieldGun.orElse(null), count);
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void antiMekChanged(final boolean antiMek) {
        if (antiMek) {
            try {
                getInfantry().addEquipment(antiMekGear, Infantry.LOC_INFANTRY);
            } catch (LocationFullException ignored) {
                // Not on infantry
            }
        } else {
            UnitUtil.removeAllMounteds(getInfantry(), antiMekGear);
        }
        TestInfantry.adaptAntiMekAttacks(getInfantry());
        refresh.refreshStatus();
    }

    @Override
    public void walkChanged(int walkMP) {
        // not used by conventional infantry
    }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) {
        // not used by conventional infantry
    }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) {
        // not used by conventional infantry
    }

    @Override
    public void mulIdChanged(int mulId) {
        getInfantry().setMulId(mulId);
        refresh.refreshSummary();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshSummary();
        refresh.refreshPreview();
    }
}
