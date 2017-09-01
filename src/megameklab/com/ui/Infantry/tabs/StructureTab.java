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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megamek.common.SimpleTechLevel;
import megamek.common.WeaponType;
import megamek.common.options.IOption;
import megamek.common.options.OptionsConstants;
import megamek.common.options.PilotOptions;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Infantry.views.ArmorView;
import megameklab.com.ui.Infantry.views.AugmentationView;
import megameklab.com.ui.Infantry.views.FieldGunView;
import megameklab.com.ui.Infantry.views.SpecializationView;
import megameklab.com.ui.Infantry.views.WeaponView;
import megameklab.com.ui.view.BasicInfoView;
import megameklab.com.ui.view.ITechManager;
import megameklab.com.ui.view.PlatoonTypeView;
import megameklab.com.util.ITab;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class StructureTab extends ITab implements ActionListener,
        BasicInfoView.BasicInfoListener,
        PlatoonTypeView.PlatoonListener {

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
    
    private String[] secondaryNArray = { "0", "1", "2" };
    private JComboBox<String> secondaryN = new JComboBox<String>(secondaryNArray);
    private JCheckBox antiMekTraining = new JCheckBox("Anti-mek Training");
    private JComboBox<String> cbFieldGunN = new JComboBox<String>();
    private String[] tabNames = {"Weapons", "Field Guns", "Armor Kit", "Specializations", "Augmentation"};

    private JTextField txtPrimary = new JTextField("None");
    private JTextField txtSecondary = new JTextField("None");
    
    private JTextField txtFieldGun = new JTextField("None");
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
        weaponView = new WeaponView(eSource);
        fieldGunView = new FieldGunView(eSource);
        armorView = new ArmorView(eSource);
        specializationView = new SpecializationView(eSource);
        augmentationView = new AugmentationView(eSource);
        setUpPanels();
        refresh();
    }

    public void setUpPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel weaponPanel = new JPanel(new GridBagLayout());
        JPanel advancedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension comboSize = new Dimension(200, 25);
        Dimension labelSize = new Dimension(110, 25);

        txtPrimary.setEditable(false);
        txtSecondary.setEditable(false);
        txtFieldGun.setEditable(false);
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
        weaponPanel.add(createLabel("Primary:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(txtPrimary, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        weaponPanel.add(createLabel("Secondary:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(txtSecondary, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        weaponPanel.add(createLabel("Secondary #:", labelSize), gbc);
        gbc.gridx = 1;
        weaponPanel.add(secondaryN, gbc);
        antiMekTraining.setHorizontalTextPosition(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        weaponPanel.add(antiMekTraining, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        advancedPanel.add(createLabel("Field Gun:", labelSize), gbc);
        gbc.gridx = 1;
        advancedPanel.add(txtFieldGun, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        advancedPanel.add(createLabel("Field Gun #:", labelSize), gbc);
        gbc.gridx = 1;
        advancedPanel.add(cbFieldGunN, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
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

        setFieldSize(secondaryN, comboSize);
        setFieldSize(txtPrimary, comboSize);
        setFieldSize(txtSecondary, comboSize);
        setFieldSize(txtFieldGun, comboSize);
        setFieldSize(txtArmor, comboSize);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panPlatoonType.setBorder(BorderFactory.createTitledBorder("Movement and Size"));
        weaponPanel.setBorder(BorderFactory.createTitledBorder("Current Weapons"));
        advancedPanel.setBorder(BorderFactory.createTitledBorder("Advanced"));
        
        equipmentPane = new JTabbedPane();
        equipmentPane.addTab(tabNames[T_INFANTRY_WEAPONS], weaponView);
        equipmentPane.addTab(tabNames[T_FIELD_GUNS], fieldGunView);
        equipmentPane.addTab(tabNames[T_ARMOR_KIT], armorView);
        equipmentPane.addTab(tabNames[T_SPECIALIZATION], specializationView);
        equipmentPane.addTab(tabNames[T_AUGMENTATION], augmentationView);

        leftPanel.add(panBasicInfo);
        leftPanel.add(panPlatoonType);
        leftPanel.add(weaponPanel);
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
        removeAllListeners();
        
        panBasicInfo.setFromEntity(getInfantry());
        panPlatoonType.setFromEntity(getInfantry());

        int maxSecondaryN = 2;

        if (getInfantry().hasSpecialization(Infantry.MOUNTAIN_TROOPS | Infantry.PARATROOPS)) {
            maxSecondaryN = 1;
        }
        if (getInfantry().hasSpecialization(Infantry.COMBAT_ENGINEERS)) {
            maxSecondaryN = 0;
        }
        if (getInfantry().getSecondaryWeapon() != null) {
            int crewReq = getInfantry().getSecondaryWeapon().getCrew();
            /* IOps shows the reduced crew requirements, but not the increased max secondary which
             * should be there per http://bg.battletech.com/forums/index.php?topic=50926.msg1279434#msg1279434
             */
            if (getInfantry().getCrew().getOptions().booleanOption(OptionsConstants.MD_DERMAL_ARMOR)) {
                crewReq--;
                maxSecondaryN++;
            }
            if (getInfantry().getCrew().getOptions().booleanOption(OptionsConstants.MD_TSM_IMPLANT)) {
                crewReq--;
                maxSecondaryN++;
            }
            maxSecondaryN = Math.min(maxSecondaryN, getInfantry().getSquadSize() / (Math.max(1, crewReq)));
        }
        secondaryN.removeAllItems();
        if (getInfantry().getSecondaryWeapon() != null
                && getInfantry().getSecondaryWeapon().hasFlag(WeaponType.F_TAG)) {
            getInfantry().setSecondaryN(2);
            secondaryN.addItem("0");
            secondaryN.addItem("2");
            secondaryN.setSelectedIndex(1);
        } else {
            for (int i = 0; i <= maxSecondaryN; i++) {
                secondaryN.addItem(Integer.toString(i));
            }
            secondaryN.setSelectedIndex(Math.min(maxSecondaryN, getInfantry().getSecondaryN()));
        }
        getInfantry().setAntiMekSkill(antiMekTraining.isSelected());

        if (getInfantry().getMovementMode() != EntityMovementMode.INF_MOTORIZED
                && getInfantry().getMovementMode() != EntityMovementMode.TRACKED
                && getInfantry().getMovementMode() != EntityMovementMode.WHEELED) {
            UnitUtil.replaceFieldGun(getInfantry(), null, 0);
        }

        if (null != getInfantry().getPrimaryWeapon()) {
            txtPrimary.setText(UnitUtil.trimInfantryWeaponNames(getInfantry().getPrimaryWeapon().getName()));
        } else {
            txtPrimary.setText("None");
        }
        if (null != getInfantry().getSecondaryWeapon()) {
            txtSecondary.setText(UnitUtil.trimInfantryWeaponNames(getInfantry().getSecondaryWeapon().getName()));
        } else {
            txtSecondary.setText("None");
        }
        
        List<EquipmentType> fieldGuns = getInfantry().getWeaponList().stream()
                .filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
                .map(m -> m.getType()).filter(et -> et instanceof WeaponType)
                .collect(Collectors.toList());
        if (fieldGuns.isEmpty()) {
            cbFieldGunN.setEnabled(false);
            if ((getInfantry().getMovementMode() == EntityMovementMode.INF_MOTORIZED)
                    || (getInfantry().getMovementMode() == EntityMovementMode.TRACKED)
                    || (getInfantry().getMovementMode() == EntityMovementMode.WHEELED)) {
                txtFieldGun.setText("None");
            } else {
                txtFieldGun.setText("Invalid Motive Type");
            }
        } else {
            cbFieldGunN.setEnabled(true);
            int maxNum = 1;
            if (!(fieldGuns.get(0) instanceof ArtilleryWeapon
                    || fieldGuns.get(0) instanceof ArtilleryCannonWeapon)) {
                int crewReq = Math.max(2, (int)Math.ceil(fieldGuns.get(0).getTonnage(getInfantry())));
                maxNum = getInfantry().getShootingStrength() / crewReq;                
            }
            cbFieldGunN.removeAllItems();
            for (int i = 0; i <= maxNum; i++) {
                cbFieldGunN.addItem(Integer.toString(i));
            }
            cbFieldGunN.setSelectedIndex(Math.min(fieldGuns.size(), maxNum));
            txtFieldGun.setText(fieldGuns.get(0).getName());
        }
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
        
        SimpleTechLevel level = panBasicInfo.getTechLevel();
        if (level.ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtArmor.setEnabled(true);
            txtFieldGun.setEnabled(true);
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
            txtFieldGun.setEnabled(false);
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
        addAllListeners();
    }

    public void addAllListeners() {
        secondaryN.addActionListener(this);
        cbFieldGunN.addActionListener(this);
        antiMekTraining.addActionListener(this);
        panBasicInfo.addListener(this);
        panPlatoonType.addListener(this);
    }

    public void removeAllListeners() {
        secondaryN.removeActionListener(this);
        cbFieldGunN.removeActionListener(this);
        antiMekTraining.removeActionListener(this);
        panBasicInfo.removeListener(this);
        panPlatoonType.removeListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        weaponView.addRefreshedListener(refresh);
        fieldGunView.addRefreshedListener(refresh);
        armorView.addRefreshedListener(refresh);
        specializationView.addRefreshedListener(refresh);
        augmentationView.addRefreshedListener(refresh);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            if (combo.equals(secondaryN)) {
                getInfantry().setSecondaryN(secondaryN.getSelectedIndex());
                checkMainWeapon();
            } else if (combo.equals(cbFieldGunN)) {
                Optional<EquipmentType> fieldGun = getInfantry().getWeaponList()
                        .stream().filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
                        .map(m -> m.getType()).filter(eq -> eq instanceof WeaponType)
                        .findAny();
                UnitUtil.replaceFieldGun(getInfantry(), (WeaponType)fieldGun.get(),
                        cbFieldGunN.getSelectedIndex());
            }
        } else if (e.getSource().equals(antiMekTraining)) {
            getInfantry().setAntiMekSkill(antiMekTraining.isSelected());
        }
        refresh.refreshAll();
    }

    private void checkMainWeapon() {
        Mounted existingInfantryMount = null;
        for (Mounted m : getInfantry().getWeaponList()) {
            if ((m.getType() instanceof InfantryWeapon) && (m.getLocation() == Infantry.LOC_INFANTRY)) {
                existingInfantryMount = m;
                break;
            }
        }
        if (null != existingInfantryMount) {
            UnitUtil.removeMounted(getInfantry(), existingInfantryMount);
        }

        // if there is more than one secondary weapon per squad, then add that
        // to the unit
        // otherwise add the primary weapon
        if ((getInfantry().getSecondaryN() < 2) || (null == getInfantry().getSecondaryWeapon())) {
            try {
                getInfantry().addEquipment(getInfantry().getPrimaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

            }
        } else {
            try {
                getInfantry().addEquipment(getInfantry().getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {

            }
        }
        // also check for zero secondary n
        if (getInfantry().getSecondaryN() <= 0) {
            UnitUtil.replaceMainWeapon(getInfantry(), null, true);
        }
    }

    public void setAsCustomization() {
        panBasicInfo.setAsCustomization();
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
    }

    @Override
    public void modelChanged(String model) {
        getInfantry().setModel(model);
    }

    @Override
    public void yearChanged(int year) {
        getInfantry().setYear(year);
    }

    @Override
    public void updateTechLevel() {
        removeAllListeners();
        if (!panBasicInfo.isLegal(Infantry.getMotiveTechAdvancement(getInfantry().getMovementMode()))) {
            motiveTypeChanged(EntityMovementMode.INF_LEG, false);
        }
        getInfantry().setTechLevel(panBasicInfo.getTechLevel().getCompoundTechLevel(panBasicInfo.isClan()));
        UnitUtil.checkEquipmentByTechLevel(getInfantry(), panBasicInfo);
        UnitUtil.resetInfantryArmor(getInfantry());
        refresh();
        addAllListeners();
    }

    @Override
    public void sourceChanged(String source) {
        getInfantry().setSource(source);
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
        if (getInfantry().isMechanized()) {
            antiMekTraining.setSelected(false);
            antiMekTraining.setEnabled(false);
        } else {
            antiMekTraining.setEnabled(true);
        }
        refresh();
    }

    @Override
    public void platoonSizeChanged(int numSquads, int squadSize) {
        getInfantry().setSquadN(numSquads);
        getInfantry().setSquadSize(squadSize);
        getInfantry().autoSetInternal();
        panPlatoonType.setFromEntity(getInfantry());
    }

}