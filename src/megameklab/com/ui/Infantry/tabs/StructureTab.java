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

package megameklab.com.ui.Infantry.tabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.Optional;
import java.util.StringJoiner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Infantry;
import megamek.common.SimpleTechLevel;
import megamek.common.WeaponType;
import megamek.common.options.IOption;
import megamek.common.options.PilotOptions;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Infantry.views.ArmorView;
import megameklab.com.ui.Infantry.views.AugmentationView;
import megameklab.com.ui.Infantry.views.FieldGunView;
import megameklab.com.ui.Infantry.views.SpecializationView;
import megameklab.com.ui.Infantry.views.WeaponView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.InfantryWeaponView;
import megameklab.com.ui.view.PlatoonTypeView;
import megameklab.com.ui.view.listeners.InfantryBuildListener;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements InfantryBuildListener {

    /**
     *
     */
    private static final long serialVersionUID = -7985608549543235815L;

    private RefreshListener refresh;

    public static final int T_INFANTRY_WEAPONS = 0;
    public static final int T_FIELD_GUNS       = 1;
    public static final int T_ARMOR_KIT        = 2;
    public static final int T_SPECIALIZATION   = 3;
    public static final int T_AUGMENTATION     = 4;

    private BasicInfoView panBasicInfo;
    private PlatoonTypeView panPlatoonType;
    private InfantryWeaponView panWeapons;
    
    private String[] tabNames = {"Weapons", "Field Guns", "Armor Kit", "Specializations", "Augmentation"};

    private JTextField txtArmor = new JTextField("None");
    private JTextPane txtSpecializations = new JTextPane();
    private JTextPane txtAugmentations = new JTextPane();

    private JTabbedPane equipmentPane;
    
    private WeaponView weaponView;
    private FieldGunView fieldGunView;
    private ArmorView armorView;
    private SpecializationView specializationView;
    private AugmentationView augmentationView;

    public StructureTab(EntitySource eSource) {
        super(eSource);
        panBasicInfo = new BasicInfoView(getInfantry().getConstructionTechAdvancement());
        panPlatoonType = new PlatoonTypeView(panBasicInfo);
        panWeapons = new InfantryWeaponView(panBasicInfo);
        weaponView = new WeaponView(eSource, panBasicInfo);
        fieldGunView = new FieldGunView(eSource, panBasicInfo);
        armorView = new ArmorView(eSource, panBasicInfo);
        specializationView = new SpecializationView(eSource);
        augmentationView = new AugmentationView(eSource);
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
        
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
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
        equipmentPane.addTab(tabNames[T_AUGMENTATION], augmentationView);

        leftPanel.add(panBasicInfo);
        leftPanel.add(panPlatoonType);
        leftPanel.add(panWeapons);
        leftPanel.add(advancedPanel);
        leftPanel.add(Box.createVerticalGlue());
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(new JScrollPane(leftPanel), gbc);
        gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(equipmentPane, gbc);

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
        
        panBasicInfo.setFromEntity(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());

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
                e.hasMoreElements();) {
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
        specializationView.addRefreshedListener(refresh);
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
        if (getInfantry().hasSpecialization(Infantry.TAG_TROOPS)
                && ((getInfantry().getSecondaryN() < 2)
                        || (getInfantry().getSecondaryWeapon() == null)
                        || !getInfantry().getSecondaryWeapon().hasFlag(WeaponType.F_TAG))) {
            UnitUtil.replaceMainWeapon(getInfantry(), (InfantryWeapon)EquipmentType.get("InfantryTAG"), true);
            getInfantry().setSecondaryN(2);
        }
    }
    
    private void enableTabs() {
        SimpleTechLevel level = panBasicInfo.getTechLevel();
        if (level.ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtArmor.setEnabled(true);
            txtSpecializations.setEnabled(true);
            equipmentPane.setEnabledAt(T_FIELD_GUNS, 
                    getInfantry().getMovementMode() == EntityMovementMode.INF_MOTORIZED
                    || getInfantry().getMovementMode() == EntityMovementMode.TRACKED
                    || getInfantry().getMovementMode() == EntityMovementMode.WHEELED);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, true);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, true);
            //Experimental level
            txtAugmentations.setEnabled(level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
            equipmentPane.setEnabledAt(T_AUGMENTATION, level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
        } else {
            txtArmor.setEnabled(false);
            txtSpecializations.setEnabled(false);
            txtAugmentations.setEnabled(false);
            equipmentPane.setEnabledAt(T_FIELD_GUNS, false);
            equipmentPane.setEnabledAt(T_ARMOR_KIT, false);
            equipmentPane.setEnabledAt(T_SPECIALIZATION, false);
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
        refresh.refreshPreview();
    }

    @Override
    public void modelChanged(String model) {
        getInfantry().setModel(model);
        refresh.refreshPreview();
    }

    @Override
    public void yearChanged(int year) {
        getInfantry().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        if (!panBasicInfo.isLegal(Infantry.getMotiveTechAdvancement(getInfantry().getMovementMode()))) {
            motiveTypeChanged(EntityMovementMode.INF_LEG, false);
        }
        getInfantry().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.useClanTechBase()));
        UnitUtil.checkEquipmentByTechLevel(getInfantry(), panBasicInfo);
        UnitUtil.resetInfantryArmor(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        updateSpecializations();
        enableTabs();
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
        getMech().setManualBV(manualBV);
    }

    @Override
    public void motiveTypeChanged(EntityMovementMode movementMode, boolean alt) {
        if (alt && (movementMode == EntityMovementMode.INF_UMU)) {
            getInfantry().setMotorizedScuba();
        } else {
            getInfantry().setMovementMode(movementMode);
        }
        getInfantry().setMicrolite(alt && (movementMode == EntityMovementMode.VTOL));

        if (getInfantry().getMovementMode() != EntityMovementMode.INF_MOTORIZED
                && getInfantry().getMovementMode() != EntityMovementMode.TRACKED
                && getInfantry().getMovementMode() != EntityMovementMode.WHEELED) {
            UnitUtil.replaceFieldGun(getInfantry(), null, 0);
        }
        enableTabs();
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
    }

    @Override
    public void platoonSizeChanged(int numSquads, int squadSize) {
        getInfantry().setSquadN(numSquads);
        getInfantry().setSquadSize(squadSize);
        getInfantry().autoSetInternal();
        panPlatoonType.setFromEntity(getInfantry());
    }
    
    @Override
    public void specializationsChanged() {
        updateSpecializations();
        panPlatoonType.setFromEntity(getInfantry());
        panWeapons.setFromEntity(getInfantry());
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void numSecondaryChanged(final int count) {
        if (getInfantry().getSecondaryWeapon() == null) {
            getInfantry().setSecondaryN(0);
        } else if (count == 0) {
            UnitUtil.replaceMainWeapon(getInfantry(), null, true);
            getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.TAG_TROOPS);
        } else {
            getInfantry().setSecondaryN(count);
        }
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void numFieldGunsChanged(final int count) {
        Optional<EquipmentType> fieldGun = getInfantry().getWeaponList()
                .stream().filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
                .map(m -> m.getType()).filter(eq -> eq instanceof WeaponType)
                .findAny();
        UnitUtil.replaceFieldGun(getInfantry(), (WeaponType)fieldGun.orElse(null),
                count);
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void antiMekChanged(final boolean antiMek) {
        getInfantry().setAntiMekSkill(antiMek);
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

}