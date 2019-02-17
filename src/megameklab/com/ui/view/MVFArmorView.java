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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Mech;
import megamek.common.TechConstants;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestEntity;
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
    private final JCheckBox chkPatchwork = new JCheckBox();
    private final JButton btnMaximize = new JButton();
    private final JButton btnUseRemaining = new JButton();
    
    private final ITechManager techManager;
    
    private long etype;
    private boolean industrial;
    private EntityMovementMode movementMode;
    
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
        setFieldSize(spnTonnage, spinnerSizeLg);
        spnTonnage.setToolTipText(resourceMap.getString("ArmorView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        chkPatchwork.setText(resourceMap.getString("ArmorView.chkPatchwork.text")); //$NON-NLS-1$
        setFieldSize(chkPatchwork, controlSize);
        chkPatchwork.setToolTipText(resourceMap.getString("ArmorView.chkPatchwork.tooltip")); //$NON-NLS-1$
        add(chkPatchwork, gbc);
        chkPatchwork.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        btnMaximize.setText(resourceMap.getString("ArmorView.btnMaximize.text")); //$NON-NLS-1$
        btnMaximize.setActionCommand(CMD_MAXIMIZE);
        setFieldSize(btnMaximize, controlSize);
        btnMaximize.setToolTipText(resourceMap.getString("ArmorView.btnMaximize.tooltip")); //$NON-NLS-1$
        add(btnMaximize, gbc);
        btnMaximize.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        btnUseRemaining.setText(resourceMap.getString("ArmorView.btnRemaining.text")); //$NON-NLS-1$
        btnUseRemaining.setActionCommand(CMD_REMAINING);
        setFieldSize(btnUseRemaining, controlSize);
        btnUseRemaining.setToolTipText(resourceMap.getString("ArmorView.btnRemaining.tooltip")); //$NON-NLS-1$
        add(btnUseRemaining, gbc);
        btnUseRemaining.addActionListener(this);
    }
    
    /**
     * Sets the values of all fields from the current Entity.
     * @param en The Entity currently being edited
     */
    public void setFromEntity(Entity en) {
        setFromEntity(en, false);
    }
    
    /**
     * Sets the values of all fields from the current Entity, with the option of ignoring
     * whether the {@code Entity} has patchwork armor. This is because the Entity does not
     * report patchwork armor unless it actually has multiple armor types, and we don't want
     * to clear the patchwork checkbox unless we're loading a new unit.
     * 
     * @param en The Entity being edited
     * @param overridePatchwork Whether to ignore whether the Entity has patchwork armor.
     */
    public void setFromEntity(Entity en, boolean ignoreEntityPatchwork) {
        etype = en.getEntityType();
        industrial = (en instanceof Mech) && ((Mech)en).isIndustrial();
        movementMode = en.getMovementMode();
        refresh();
        cbArmorType.removeActionListener(this);
        spnTonnage.removeChangeListener(this);
        chkPatchwork.removeActionListener(this);
        if ((!ignoreEntityPatchwork && en.hasPatchworkArmor())
                || (ignoreEntityPatchwork && isPatchwork())) {
            cbArmorType.setEnabled(false);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            spnTonnage.setEnabled(false);
            chkPatchwork.setVisible(true);
            chkPatchwork.setSelected(true);
            btnMaximize.setEnabled(false);
            btnUseRemaining.setEnabled(false);
        } else {
            String name = EquipmentType.getArmorTypeName(en.getArmorType(0),
                    TechConstants.isClan(en.getArmorTechLevel(0)));
            EquipmentType eq = EquipmentType.get(name);
            cbArmorType.setEnabled(true);
            cbArmorType.setSelectedItem(eq);
            tonnageModel.setValue(Math.min(UnitUtil.getMaximumArmorTonnage(en),
                    en.getLabArmorTonnage()));
            tonnageModel.setMaximum(UnitUtil.getMaximumArmorTonnage(en));
            spnTonnage.setEnabled(true);
            chkPatchwork.setSelected(false);
            btnMaximize.setEnabled(true);
            btnUseRemaining.setEnabled(true);
        }
        
        cbArmorType.addActionListener(this);
        spnTonnage.addChangeListener(this);
        chkPatchwork.addActionListener(this);
    }
    
    public void refresh() {
        EquipmentType prev = (EquipmentType)cbArmorType.getSelectedItem();
        cbArmorType.removeActionListener(this);
        cbArmorType.removeAllItems();
        List<EquipmentType> allArmors = TestEntity.legalArmorsFor(etype, industrial, movementMode, techManager);
        allArmors.forEach(eq -> cbArmorType.addItem(eq));
        if (((etype & (Entity.ETYPE_SMALL_CRAFT | Entity.ETYPE_JUMPSHIP)) == 0)
                && techManager.isLegal(Entity.getPatchworkArmorAdvancement())) {
            chkPatchwork.setVisible(true);
        } else {
            chkPatchwork.setVisible(false);
            chkPatchwork.setSelected(false);
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
    
    /**
     * @return The selected armor equipment
     */
    public EquipmentType getArmor() {
        return (EquipmentType)cbArmorType.getSelectedItem();
    }
    
    /**
     * Enables or disables the patchwork checkbox and refreshes
     * 
     * @param patchwork Whether the patchwork checkbox should be enabled
     */
    public void setPatchwork(boolean patchwork) {
        chkPatchwork.setSelected(patchwork);
        refresh();
    }
    
    /**
     * @return Whether the patchwork checkbox is selected
     */
    public boolean isPatchwork() {
        return chkPatchwork.isSelected();
    }
    
    /**
     * @return The armor type constant for the selected armor
     */
    public int getArmorType() {
        if (isPatchwork()) {
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
        EquipmentType armor = (EquipmentType) cbArmorType.getSelectedItem();
        if (null == armor) {
            return TechConstants.T_TECH_UNKNOWN;
        }
        return (armor.getTechLevel(techManager.getGameYear(), armor.isClan()));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.armorTonnageChanged(tonnageModel.getNumber().doubleValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == cbArmorType)
                || ((e.getSource() == chkPatchwork) && !isPatchwork())) {
            listeners.forEach(l -> l.armorTypeChanged(getArmorType(), getArmorTechConstant()));
        } else if (e.getSource() == chkPatchwork) {
            listeners.forEach(l -> l.armorTypeChanged(EquipmentType.T_ARMOR_PATCHWORK,
                    Entity.getPatchworkArmorAdvancement().getTechLevel(techManager.getGameYear(),
                            techManager.useClanTechBase())));
        } else if (CMD_MAXIMIZE.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.maximizeArmor());
        } else if (CMD_REMAINING.equals(e.getActionCommand())) {
            listeners.forEach(l -> l.useRemainingTonnageArmor());
        }
    }
    
}
