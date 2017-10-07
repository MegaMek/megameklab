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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.util.TechComboBox;
import megameklab.com.ui.view.listeners.BuildListener;
import megameklab.com.util.UnitUtil;

/**
 * Panel for assigning armor type and tonnage for mechs, (combat) vehicles, and fighters.
 * 
 * @author Neoancient
 *
 */
public class MVFArmorView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1246552271894765543L;

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BuildListener l) {
        listeners.add(l);
    }
    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }
    
    private final static String CMD_MAXIMIZE  = "MAXIMIZE"; //$NON-NLS-1$
    private final static String CMD_REMAINING = "REMAINING"; //$NON-NLS-1$
    
    private final TechComboBox<EquipmentType> cbArmorType = new TechComboBox<>(eq -> eq.getName());
    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(0, 0, 0, 0.5);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    
    private final ITechManager techManager;
    
    private long etype;
    private boolean industrial;
    private boolean hardenedIllegal;
    
    private final static int[] INDUSTRIAL_TYPES = {
            EquipmentType.T_ARMOR_INDUSTRIAL, EquipmentType.T_ARMOR_HEAVY_INDUSTRIAL,
            EquipmentType.T_ARMOR_COMMERCIAL
    };
    
    public MVFArmorView(ITechManager techManager) {
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
        cbArmorType.setNullValue(EquipmentType.armorNames[EquipmentType.T_ARMOR_PATCHWORK]);
        cbArmorType.setToolTipText(resourceMap.getString("ArmorView.cbArmorType.tooltip")); //$NON-NLS-1$
        add(cbArmorType, gbc);
        cbArmorType.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ArmorView.spnTonnage.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        setFieldSize(spnTonnage.getEditor(), editorSize);
        spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
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
        industrial = (en instanceof Mech) && ((Mech)en).isIndustrial();
        hardenedIllegal = ((etype & Entity.ETYPE_LAND_AIR_MECH) != 0)
                || (en.getMovementMode() == EntityMovementMode.VTOL)
                || (en.getMovementMode() == EntityMovementMode.WIGE)
                || (en.getMovementMode() == EntityMovementMode.HOVER);
        refresh();
        cbArmorType.removeActionListener(this);
        spnTonnage.removeChangeListener(this);
        if (en.hasPatchworkArmor()) {
            for (int i = 0; i < cbArmorType.getModel().getSize(); i++) {
                if (cbArmorType.getItemAt(i) == null) {
                    cbArmorType.setSelectedIndex(i);
                    break;
                }
            }
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            spnTonnage.setEnabled(false);
            btnMaximize.setEnabled(false);
            btnUseRemaining.setEnabled(false);
        } else {
            String name = EquipmentType.getArmorTypeName(en.getArmorType(0),
                    TechConstants.isClan(en.getArmorTechLevel(0)));
            EquipmentType eq = EquipmentType.get(name);
            cbArmorType.setSelectedItem(eq);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            tonnageModel.setMaximum(UnitUtil.getMaximumArmorTonnage(en));
            spnTonnage.setEnabled(true);
            btnMaximize.setEnabled(true);
            btnUseRemaining.setEnabled(true);
        }
        
        cbArmorType.addActionListener(this);
        spnTonnage.addChangeListener(this);
    }
    
    public void refresh() {
        EquipmentType prev = (EquipmentType)cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();
        
        // IndustrialMechs can only use industrial armor below experimental rules level
        if (industrial && (techManager.getTechLevel().ordinal() < SimpleTechLevel.EXPERIMENTAL.ordinal())) {
            for (int at : INDUSTRIAL_TYPES) {
                String name = EquipmentType.getArmorTypeName(at, false);
                EquipmentType eq = EquipmentType.get(name);
                if ((null != eq) && techManager.isLegal(eq)) {
                    cbArmorType.addItem(eq);
                }
            }
        } else {
            BigInteger flag = MiscType.F_MECH_EQUIPMENT;
            if ((etype & Entity.ETYPE_AERO) != 0) {
                flag = MiscType.F_AERO_EQUIPMENT;
            } else if ((etype & Entity.ETYPE_TANK) != 0) {
                flag = MiscType.F_TANK_EQUIPMENT;
            }
            boolean isLAM = (etype & Entity.ETYPE_LAND_AIR_MECH) != 0;
            
            for (int at = 0; at < EquipmentType.armorNames.length; at++) {
                if (at == EquipmentType.T_ARMOR_PATCHWORK) {
                    continue;
                }
                if ((at == EquipmentType.T_ARMOR_HARDENED) && hardenedIllegal) {
                    continue;
                }
                String name = EquipmentType.getArmorTypeName(at, techManager.useClanTechBase());
                EquipmentType eq = EquipmentType.get(name);
                if ((null == eq) || (isLAM && ((eq.getCriticals(null) != 0)))) {
                    continue;
                }
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
        }
        if (techManager.isLegal(Entity.getPatchworkArmorAdvancement())) {
            cbArmorType.addItem(null);
        }
        if (null == prev) {
            cbArmorType.setSelectedIndex(cbArmorType.getModel().getSize() - 1);
        } else {
            cbArmorType.setSelectedItem(prev);
        }
        cbArmorType.addActionListener(this);
        if ((null != prev) && (cbArmorType.getSelectedIndex() < 0)) {
            cbArmorType.setSelectedIndex(0);
        }
        cbArmorType.showTechBase(techManager.useMixedTech());
    }
    
    public EquipmentType getArmor() {
        return (EquipmentType)cbArmorType.getSelectedItem();
    }
    
    public int getArmorType() {
        if (cbArmorType.getSelectedItem() == null) {
            return EquipmentType.T_ARMOR_PATCHWORK;
        } else {
            return EquipmentType.getArmorType((EquipmentType)cbArmorType.getSelectedItem());
        }
    }
    
    /**
     * Used to populate the patchwork armor dialog.
     * 
     * @return A list of the contents of the armor type combo box.
     */
    public List<EquipmentType> getAllArmors() {
        List<EquipmentType> list = new ArrayList<>();
        for (int i = 0; i < cbArmorType.getModel().getSize(); i++) {
            if (null != cbArmorType.getItemAt(i)) {
                list.add(cbArmorType.getItemAt(i));
            }
        }
        return list;
    }

    public int getArmorTechConstant() {
        if (cbArmorType.getSelectedItem() == null) {
            return Entity.getPatchworkArmorAdvancement().getTechLevel(techManager.getGameYear(),
                    techManager.useClanTechBase());
        } else {
            EquipmentType armor = (EquipmentType)cbArmorType.getSelectedItem();
            return (armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.armorTonnageChanged(tonnageModel.getNumber().doubleValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbArmorType) {
            listeners.forEach(l -> l.armorTypeChanged(getArmorType(), getArmorTechConstant()));
        } else if (CMD_MAXIMIZE.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.maximizeArmor());
        } else if (CMD_REMAINING.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.useRemainingTonnageArmor());
        }
    }
    
}
