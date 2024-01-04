/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.infantry;

import megamek.common.*;
import megamek.common.verifier.TestInfantry;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.util.InfantryUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Panel for conventional infantry weapons (primary, secondary, field gun). The only editable
 * controls are for changing the number of secondary weapons and field guns.
 * 
 * @author Neoancient
 */
public class CIWeaponView extends BuildView implements ActionListener {
    private List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }

    private final EnumSet<EntityMovementMode> FIELD_GUN_MODES = EnumSet.of(
            EntityMovementMode.TRACKED, EntityMovementMode.WHEELED, EntityMovementMode.INF_MOTORIZED);

    private final JTextField txtPrimary = new JTextField();
    private final JTextField txtSecondary = new JTextField();
    private final JTextField txtGuns = new JTextField();
    private final JComboBox<Integer> cbNumSecondary = new JComboBox<>();
    private final JComboBox<Integer> cbNumGuns = new JComboBox<>();
    private final JCheckBox chkAntiMek = new JCheckBox();

    private ITechManager techManager;
    private String fgMotiveMsg;
    private String noneMsg;

    public CIWeaponView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        fgMotiveMsg = resourceMap.getString("InfantryWeaponView.txtGuns.badMotive");
        noneMsg = resourceMap.getString("InfantryWeaponView.none");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        add(new JLabel(resourceMap.getString("InfantryWeaponView.txtPrimary.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        txtPrimary.setToolTipText(resourceMap.getString("InfantryWeaponView.txtPrimary.tooltip"));
        setFieldSize(txtPrimary, controlSize);
        txtPrimary.setEditable(false);
        add(txtPrimary, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("InfantryWeaponView.txtSecondary.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        setFieldSize(txtSecondary, controlSize);
        txtSecondary.setToolTipText(resourceMap.getString("InfantryWeaponView.txtSecondary.tooltip"));
        txtSecondary.setEditable(false);
        add(txtSecondary, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("InfantryWeaponView.cbNumSecondary.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        setFieldSize(cbNumSecondary, controlSize);
        cbNumSecondary.setToolTipText(resourceMap.getString("InfantryWeaponView.cbNumSecondary.tooltip"));
        add(cbNumSecondary, gbc);
        cbNumSecondary.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("InfantryWeaponView.txtGuns.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        setFieldSize(txtGuns, controlSize);
        txtGuns.setToolTipText(resourceMap.getString("InfantryWeaponView.txtGuns.tooltip"));
        txtGuns.setEditable(false);
        add(txtGuns, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("InfantryWeaponView.cbNumGuns.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        setFieldSize(cbNumGuns, controlSize);
        cbNumGuns.setToolTipText(resourceMap.getString("InfantryWeaponView.cbNumGuns.tooltip"));
        add(cbNumGuns, gbc);
        cbNumGuns.addActionListener(this);

        chkAntiMek.setText(resourceMap.getString("InfantryWeaponView.chkAntiMek.text"));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        chkAntiMek.setHorizontalTextPosition(SwingConstants.LEFT);
        chkAntiMek.setToolTipText(resourceMap.getString("InfantryWeaponView.chkAntiMek.tooltip"));
        add(chkAntiMek, gbc);
        chkAntiMek.addActionListener(this);
    }

    public void setFromEntity(Infantry inf) {
        if (inf.getPrimaryWeapon() != null) {
            txtPrimary.setText(InfantryUtil.trimInfantryWeaponNames(inf.getPrimaryWeapon().getName()));
        } else {
            txtPrimary.setText(noneMsg);
        }
        if (inf.getSecondaryWeapon() != null) {
            txtSecondary.setText(InfantryUtil.trimInfantryWeaponNames(inf.getSecondaryWeapon().getName()));
        } else {
            txtSecondary.setText(noneMsg);
        }
        
        cbNumSecondary.removeActionListener(this);
        cbNumSecondary.removeAllItems();
        cbNumSecondary.addItem(0);
        if (inf.hasSpecialization(Infantry.TAG_TROOPS)) {
            cbNumSecondary.addItem(2);
        } else {
            for (int i = 1; i <= TestInfantry.maxSecondaryWeapons(inf); i++) {
                cbNumSecondary.addItem(i);
            }
        }
        cbNumSecondary.setSelectedItem(inf.getSecondaryWeaponsPerSquad());
        cbNumSecondary.addActionListener(this);
        if (cbNumSecondary.getSelectedIndex() < 0) {
            cbNumSecondary.setSelectedIndex(0);
        }
        
        List<EquipmentType> fieldGuns = inf.getWeaponList().stream()
                .filter(m -> m.getLocation() == Infantry.LOC_FIELD_GUNS)
                .map(Mounted::getType)
                .filter(et -> et instanceof WeaponType)
                .collect(Collectors.toList());
        if (fieldGuns.isEmpty()) {
            cbNumGuns.setEnabled(false);
            if (!FIELD_GUN_MODES.contains(inf.getMovementMode())) {
                txtGuns.setText(fgMotiveMsg);
            } else {
                txtGuns.setText(noneMsg);
            }
        } else {
            cbNumGuns.setEnabled(true);
            int maxNum = 1;
            if (!(fieldGuns.get(0) instanceof ArtilleryWeapon
                    || fieldGuns.get(0) instanceof ArtilleryCannonWeapon)) {
                int crewReq = Math.max(2, (int) Math.ceil(fieldGuns.get(0).getTonnage(inf)));
                maxNum = inf.getShootingStrength() / crewReq;                
            }
            cbNumGuns.removeActionListener(this);
            cbNumGuns.removeAllItems();
            for (int i = 0; i <= maxNum; i++) {
                cbNumGuns.addItem(i);
            }
            cbNumGuns.setSelectedIndex(Math.min(fieldGuns.size(), maxNum));
            cbNumGuns.addActionListener(this);
            txtGuns.setText(fieldGuns.get(0).getName());
        }

        if (techManager.getTechLevel().ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtGuns.setEnabled(true);
            cbNumGuns.setEnabled(true);
        } else {
            txtGuns.setEnabled(false);
            cbNumGuns.setEnabled(false);
        }

        if (!inf.isMechanized() && techManager.isLegal(Infantry.getAntiMekTA())) {
            chkAntiMek.setEnabled(true);
            chkAntiMek.removeActionListener(this);
            chkAntiMek.setSelected(inf.isAntiMekTrained());
            chkAntiMek.addActionListener(this);
        } else {
            chkAntiMek.setEnabled(false);
            chkAntiMek.setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbNumSecondary) {
            listeners.forEach(l -> l.numSecondaryChanged((Integer) cbNumSecondary.getSelectedItem()));
        } else if (e.getSource() == cbNumGuns) {
            listeners.forEach(l -> l.numFieldGunsChanged((Integer) cbNumGuns.getSelectedItem()));
        } else if (e.getSource() == chkAntiMek) {
            listeners.forEach(l -> l.antiMekChanged(chkAntiMek.isSelected()));
        }
    }
}
