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
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.MiscType;
import megamek.common.Protomech;
import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.ArmorAllocationListener;
import megameklab.com.util.UnitUtil;

/**
 * Structure table armor panel for units that allocate armor by point instead of ton.
 * 
 * @author Neoancient
 *
 */
public class BAProtoArmorView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 14527455823813010L;

    private final List<ArmorAllocationListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ArmorAllocationListener l) {
        listeners.add(l);
    }
    public void removeListener(ArmorAllocationListener l) {
        listeners.remove(l);
    }
    
    private final static String CMD_MAXIMIZE  = "MAXIMIZE"; //$NON-NLS-1$
    private final static String CMD_REMAINING = "REMAINING"; //$NON-NLS-1$
    
    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(eq -> eq.getName());
    private final SpinnerNumberModel spnArmorPointsModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnArmorPoints = new JSpinner(spnArmorPointsModel);
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    
    private final ITechManager techManager;
    private long etype;
    
    public BAProtoArmorView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ArmorView.cbArmorType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        setFieldSize(cbArmorType, controlSize);
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip")); //$NON-NLS-1$
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ArmorView.spnArmorPoints.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        setFieldSize(spnArmorPoints.getEditor(), editorSize);
        spnArmorPoints.setToolTipText(resourceMap.getString("ArmorView.spnArmorPoints.tooltip")); //$NON-NLS-1$
        add(spnArmorPoints, gbc);
        spnArmorPoints.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text")); //$NON-NLS-1$
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        setFieldSize(btnMaximize, controlSize);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip")); //$NON-NLS-1$
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text")); //$NON-NLS-1$
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        setFieldSize(btnUseRemaining, controlSize);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip")); //$NON-NLS-1$
        add(btnUseRemaining, gbc);
        btnUseRemaining.addActionListener(this);
    }
    
    public void setFromEntity(Entity en) {
        etype = en.getEntityType();
        refresh();
        cbArmorType.removeActionListener(this);
        spnArmorPoints.removeChangeListener(this);
        String name = EquipmentType.getArmorTypeName(en.getArmorType(0),
                TechConstants.isClan(en.getArmorTechLevel(0)));
        EquipmentType eq = EquipmentType.get(name);
        cbArmorType.setSelectedItem(eq);
        if (en.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
            spnArmorPointsModel.setValue(Math.min(((BattleArmor)en).getMaximumArmorPoints(),
                    en.getOArmor(BattleArmor.LOC_TROOPER_1)));
            spnArmorPointsModel.setMaximum(((BattleArmor)en).getMaximumArmorPoints());
        } else if (en.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
            final int max = TestProtomech.maxArmorFactor((Protomech) en);
            spnArmorPointsModel.setValue(Math.min(max,
                    (int) UnitUtil.getRawArmorPoints(en, en.getLabArmorTonnage())));
            spnArmorPointsModel.setMaximum(max);
        } else {
            spnArmorPointsModel.setValue(en.getTotalOArmor());
        }
        
        cbArmorType.addActionListener(this);
        spnArmorPoints.addChangeListener(this);
    }
    
    public @Nullable EquipmentType getArmor() {
        return (EquipmentType) cbArmorType.getSelectedItem();
    }
    
    public int getArmorPoints() {
        return spnArmorPointsModel.getNumber().intValue();
    }
    
    public void refresh() {
        EquipmentType prev = (EquipmentType)cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();
        
        BigInteger flag = BigInteger.valueOf(0);
        if ((etype & Entity.ETYPE_BATTLEARMOR) != 0) {
            flag = MiscType.F_BA_EQUIPMENT;
        } else if ((etype & Entity.ETYPE_PROTOMECH) != 0) {
            flag = MiscType.F_PROTOMECH_EQUIPMENT;
        }
        for (int at = 0; at < EquipmentType.armorNames.length; at++) {
            String name = EquipmentType.getArmorTypeName(at, techManager.useClanTechBase());
            EquipmentType eq = EquipmentType.get(name);
            if ((null != eq) && eq.hasFlag(flag) && techManager.isLegal(eq)) {
                cbArmorType.addItem(eq);
            }
            if (techManager.useMixedTech()) {
                name = EquipmentType.getArmorTypeName(at, !techManager.useClanTechBase());
                EquipmentType eq2 = EquipmentType.get(name);
                if ((null != eq2) && (eq != eq2) && eq2.hasFlag(flag)
                        && techManager.isLegal(eq2)) {
                    cbArmorType.addItem(eq2);
                }
            }
        }
        cbArmorType.setSelectedItem(prev);
        cbArmorType.addActionListener(this);
        if ((cbArmorType.getSelectedIndex() < 0)
                && (cbArmorType.getModel().getSize() > 0)) {
            cbArmorType.setSelectedIndex(0);
        }
        cbArmorType.showTechBase(techManager.useMixedTech());
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnArmorPoints) {
            listeners.forEach(l -> l.armorFactorChanged(spnArmorPointsModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbArmorType) {
            listeners.forEach(l -> l.armorTypeChanged((EquipmentType)cbArmorType.getSelectedItem()));
        } else if (CMD_MAXIMIZE.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.maximizeArmor());
        } else if (CMD_REMAINING.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.useRemainingTonnageArmor());
        }
    }

}
