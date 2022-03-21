/*
 * MegaMekLab
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
package megameklab.ui.battleArmor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.BattleArmor;
import megamek.common.EntityWeightClass;
import megamek.common.ITechManager;
import megamek.common.util.EncodeControl;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.BABuildListener;

/**
 * Structure tab chassis view for BattleArmor
 * 
 * @author Neoancient
 */
public class BAChassisView extends BuildView implements ActionListener, ChangeListener {
    private final List<BABuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BABuildListener l) {
        listeners.add(l);
    }
    public void removeListener(BABuildListener l) {
        listeners.remove(l);
    }
    
    public final static int TURRET_NONE     = 0;
    public final static int TURRET_STANDARD = 1;
    public final static int TURRET_MODULAR  = 2;
    
    private final SpinnerNumberModel spnTurretSizeModel = new SpinnerNumberModel(0, 0, 10, 1);
    private final JComboBox<String> cbChassisType = new JComboBox<>();
    private final CustomComboBox<Integer> cbWeightClass = new CustomComboBox<>(EntityWeightClass::getClassName);
    private final JCheckBox chkExoskeleton = new JCheckBox();
    private final JCheckBox chkHarjel = new JCheckBox();
    private final JSpinner spnSquadSize = new JSpinner(new SpinnerNumberModel(4, 4, 6, 1));
    private final JComboBox<String> cbTurretType = new JComboBox<>();
    private final JSpinner spnTurretSize = new JSpinner(spnTurretSizeModel);
    
    private ITechManager techManager;
    
    public BAChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl());
        cbChassisType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("BAChassisView.cbBodyType.values").split(",")));
        cbTurretType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("BAChassisView.cbTurretType.values").split(",")));
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("BAChassisView.cbChassisType.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(cbChassisType, controlSize);
        cbChassisType.setToolTipText(resourceMap.getString("BAChassisView.cbChassisType.tooltip"));
        add(cbChassisType, gbc);
        cbChassisType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.cbWeightClass.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(cbWeightClass, controlSize);
        cbWeightClass.setToolTipText(resourceMap.getString("BAChassisView.cbWeightClass.tooltip"));
        add(cbWeightClass, gbc);
        cbWeightClass.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.spnSquadSize.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnSquadSize, controlSize);
        spnSquadSize.setToolTipText(resourceMap.getString("BAChassisView.spnSquadSize.tooltip"));
        add(spnSquadSize, gbc);
        spnSquadSize.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.cbTurretType.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(cbTurretType, controlSize);
        cbTurretType.setToolTipText(resourceMap.getString("BAChassisView.cbTurretType.tooltip"));
        add(cbTurretType, gbc);
        cbTurretType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.spnTurretSize.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnTurretSize, controlSize);
        spnTurretSize.setToolTipText(resourceMap.getString("BAChassisView.spnTurretSize.tooltip"));
        add(spnTurretSize, gbc);
        spnTurretSize.addChangeListener(this);

        JPanel chassisOptions = new JPanel();
        chkExoskeleton.setText(resourceMap.getString("BAChassisView.chkExoskeleton.text"));
        chkExoskeleton.addActionListener(this);
        chkExoskeleton.setToolTipText(resourceMap.getString("BAChassisView.chkExoskeleton.tooltip"));
        chassisOptions.add(chkExoskeleton);
        chkHarjel.setText(resourceMap.getString("BAChassisView.chkHarjel.text"));
        chkHarjel.addActionListener(this);
        chkHarjel.setToolTipText(resourceMap.getString("BAChassisView.chkHarjel.tooltip"));
        chassisOptions.add(chkHarjel);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(chassisOptions, gbc);
                
    }
    
    public void setFromEntity(BattleArmor ba) {
        refresh();
        
        cbChassisType.removeActionListener(this);
        cbChassisType.setSelectedIndex(ba.getChassisType());
        cbChassisType.addActionListener(this);
        
        cbWeightClass.removeActionListener(this);
        cbWeightClass.setSelectedItem(ba.getWeightClass());
        cbWeightClass.addActionListener(this);
        
        chkExoskeleton.removeActionListener(this);
        chkExoskeleton.setSelected(ba.isExoskeleton());
        chkExoskeleton.addActionListener(this);
        chkExoskeleton.setEnabled(ba.getWeightClass() == EntityWeightClass.WEIGHT_ULTRA_LIGHT);
        
        spnSquadSize.removeChangeListener(this);
        spnSquadSize.setValue(ba.getTroopers());
        spnSquadSize.addChangeListener(this);
        
        cbTurretType.removeActionListener(this);
        spnTurretSize.removeChangeListener(this);
        spnTurretSizeModel.setValue(ba.getTurretCapacity());
        if (ba.getTurretCapacity() > 0) {
            if (ba.hasModularTurretMount()) {
                cbTurretType.setSelectedIndex(TURRET_MODULAR);
                spnTurretSizeModel.setMaximum(9);
            } else {
                cbTurretType.setSelectedIndex(TURRET_STANDARD);
                spnTurretSizeModel.setMaximum(10);
            }
        } else {
            cbTurretType.setSelectedIndex(TURRET_NONE);
        }
        cbTurretType.addActionListener(this);
        spnTurretSize.addChangeListener(this);
    }
    
    public void refresh() {
        Integer prevWeight = getWeightClass();
        cbWeightClass.removeActionListener(this);
        cbWeightClass.removeAllItems();
        if (getBodyType() == BattleArmor.CHASSIS_TYPE_BIPED) {
            cbWeightClass.addItem(EntityWeightClass.WEIGHT_ULTRA_LIGHT);
        }
        for (int i = EntityWeightClass.WEIGHT_LIGHT; i <= EntityWeightClass.WEIGHT_ASSAULT; i++) {
            cbWeightClass.addItem(i);
        }
        cbWeightClass.setSelectedItem(prevWeight);
        cbWeightClass.addActionListener(this);
        if (cbWeightClass.getSelectedIndex() < 0) {
            cbWeightClass.setSelectedIndex(0);
        }
        if (getBodyType() == BattleArmor.CHASSIS_TYPE_QUAD) {
            if (cbTurretType.getSelectedIndex() == TURRET_NONE) {
                spnTurretSizeModel.setMaximum(0);
            } else if (cbTurretType.getSelectedIndex() == TURRET_MODULAR) {
                spnTurretSizeModel.setMaximum(9);
            } else {
                spnTurretSizeModel.setMaximum(10);
            }
            cbTurretType.setEnabled(true);
            spnTurretSize.setEnabled(true);
        } else {
            cbTurretType.setSelectedIndex(TURRET_NONE);
            spnTurretSizeModel.setMaximum(0);
            cbTurretType.setEnabled(false);
            spnTurretSize.setEnabled(false);
        }
        
        chkHarjel.setEnabled(techManager.useClanTechBase() && isExoskeleton());
    }
    
    public int getBodyType() {
        return cbChassisType.getSelectedIndex();
    }
    
    public Integer getWeightClass() {
        return (Integer)cbWeightClass.getSelectedItem();
    }
    
    public boolean isExoskeleton() {
        return chkExoskeleton.isSelected();
    }
    
    public boolean hasHarjel() {
        return chkHarjel.isEnabled() && chkHarjel.isSelected();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnSquadSize) {
            listeners.forEach(l -> l.squadSizeChanged((Integer)spnSquadSize.getValue()));
        } else if (e.getSource() == spnTurretSize) {
            listeners.forEach(l -> l.turretChanged(cbTurretType.getSelectedIndex(),
                    spnTurretSizeModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbChassisType) {
            listeners.forEach(l -> l.chassisTypeChanged(cbChassisType.getSelectedIndex()));
        } else if (e.getSource() == cbWeightClass) {
            listeners.forEach(l -> l.weightClassChanged(getWeightClass()));
        } else if (e.getSource() == cbTurretType) {
            listeners.forEach(l -> l.turretChanged(cbTurretType.getSelectedIndex(),
                    spnTurretSizeModel.getNumber().intValue()));
        } else if (e.getSource() == chkExoskeleton) {
            listeners.forEach(l -> l.exoskeletonChanged(chkExoskeleton.isSelected()));
        } else if (e.getSource() == chkHarjel) {
            listeners.forEach(l -> l.harjelChanged(chkHarjel.isSelected()));
        }
    }
}
