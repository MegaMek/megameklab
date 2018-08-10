/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Aero;
import megamek.common.Entity;
import megamek.common.ITechManager;
import megamek.common.Jumpship;
import megamek.common.SpaceStation;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megameklab.com.ui.view.listeners.AdvancedAeroBuildListener;

/**
 * Structure tab chassis panel for jumpships, warships, and space stations.
 * 
 * @author Neoancient
 *
 */
public class AdvancedAeroChassisView extends BuildView implements ActionListener, ChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3329873021840561899L;
    
    private final List<AdvancedAeroBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(AdvancedAeroBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(AdvancedAeroBuildListener l) {
        listeners.remove(l);
    }
    
    public final static int TYPE_JUMPSHIP      = 0;
    public final static int TYPE_WARSHIP       = 1;
    public final static int TYPE_STATION       = 2;
    public final static int TYPE_SUBCOMPACT    = 3;
    
    private final SpinnerNumberModel spnTonnageModel = new SpinnerNumberModel(2000, 2000, null, 500);
    private final SpinnerNumberModel spnSIModel = new SpinnerNumberModel(1, 1, null, 1);
    
    final private JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    final private JComboBox<String> cbBaseType = new JComboBox<>();
    final private JLabel lblRange;
    final private JSpinner spnRange = new JSpinner(new SpinnerNumberModel(15, 15, 30, 1));
    final private JCheckBox chkLFBattery = new JCheckBox();
    final private JCheckBox chkModular = new JCheckBox();
    final private JCheckBox chkSail = new JCheckBox();
    final private JCheckBox chkMilitary = new JCheckBox();
    final private JSpinner spnSI = new JSpinner(spnSIModel);
    
    private ITechManager techManager;
    private int baseType;
    private int maxTonnage;
    private int minTonnage;
    private int stepTonnage;
    private int maxThrust;
    
    public AdvancedAeroChassisView(ITechManager techManager) {
        this.techManager = techManager;
        lblRange = createLabel("", labelSize);
        initUI();
    }
    
    public void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        cbBaseType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("AdvAeroChassisView.cbBaseType.values").split(",")));

        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap.getString("AdvAeroChassisView.spnTonnage.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        spnTonnage.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
        chkSail.setText(resourceMap.getString("AdvAeroChassisView.chkSail.text")); //$NON-NLS-1$
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkSail.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkSail.tooltip")); //$NON-NLS-1$
        add(chkSail, gbc);
        chkSail.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap.getString("AdvAeroChassisView.cbBaseType.text"), labelSize),gbc); //$NON-NLS-1$
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setFieldSize(cbBaseType, controlSize);
        cbBaseType.setToolTipText(resourceMap.getString("AdvAeroChassisView.cbBaseType.tooltip")); //$NON-NLS-1$
        add(cbBaseType, gbc);
        cbBaseType.addActionListener(this);

        chkMilitary.setText(resourceMap.getString("AdvAeroChassisView.chkMilitary.text")); //$NON-NLS-1$
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkMilitary.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkMilitary.tooltip")); //$NON-NLS-1$
        add(chkMilitary, gbc);
        chkMilitary.addActionListener(this);

        chkLFBattery.setText(resourceMap.getString("AdvAeroChassisView.chkLFBattery.text")); //$NON-NLS-1$
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        chkLFBattery.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkLFBattery.tooltip")); //$NON-NLS-1$
        add(chkLFBattery, gbc);
        chkLFBattery.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lblRange.setText(resourceMap.getString("AdvAeroChassisView.spnRange.text")); //$NON-NLS-1$
        gbc.gridwidth = 2;
        add(lblRange, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        spnRange.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnRange.tooltip")); //$NON-NLS-1$
        add(spnRange, gbc);
        spnRange.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap.getString("AdvAeroChassisView.spnSI.text"), labelSize),gbc); //$NON-NLS-1$
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        setFieldSize(spnSI, controlSize);
        spnSI.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnSI.tooltip")); //$NON-NLS-1$
        add(spnSI, gbc);
        spnSI.addChangeListener(this);

        chkModular.setText(resourceMap.getString("AdvAeroChassisView.chkModular.text")); //$NON-NLS-1$
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkModular.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkModular.tooltip")); //$NON-NLS-1$
        add(chkModular, gbc);
        chkModular.addActionListener(this);

    }
    
    public void setFromEntity(Jumpship craft) {
        switch (craft.getDriveCoreType()) {
            case Jumpship.DRIVE_CORE_STANDARD:
                baseType = TYPE_JUMPSHIP;
                break;
            case Jumpship.DRIVE_CORE_COMPACT:
            case Jumpship.DRIVE_CORE_PRIMITIVE:
                baseType = TYPE_WARSHIP;
                break;
            case Jumpship.DRIVE_CORE_SUBCOMPACT:
                baseType = TYPE_SUBCOMPACT;
                break;
            case Jumpship.DRIVE_CORE_NONE:
                baseType = TYPE_STATION;
                break;
        }
        maxTonnage = TestAero.getMaxTonnage(craft, techManager.getTechFaction());
        minTonnage = TestAdvancedAerospace.getMinTonnage(craft);
        stepTonnage = TestAdvancedAerospace.getWeightIncrement(craft);
        maxThrust = craft.getRunMP();
        refresh();
        spnTonnage.removeChangeListener(this);
        setTonnage(craft.getWeight());
        spnTonnage.addChangeListener(this);
        chkLFBattery.removeActionListener(this);
        chkLFBattery.setSelected(craft.hasLF());
        chkLFBattery.addActionListener(this);
        chkSail.removeActionListener(this);
        chkSail.setSelected(craft.hasSail());
        chkSail.addActionListener(this);
        chkMilitary.removeActionListener(this);
        chkMilitary.setSelected(craft.getDesignType() == Aero.MILITARY);
        chkMilitary.addActionListener(this);
        chkModular.removeActionListener(this);
        chkModular.setSelected(craft.hasETypeFlag(Entity.ETYPE_SPACE_STATION)
                && ((SpaceStation) craft).isModular());
        chkModular.addActionListener(this);
        
        
        cbBaseType.removeActionListener(this);
        cbBaseType.setSelectedIndex(baseType);
        cbBaseType.addActionListener(this);
        cbBaseType.setEnabled(!craft.isPrimitive());

        spnSIModel.setValue(craft.get0SI());
        if (craft.isPrimitive()) {
            spnRange.removeChangeListener(this);
            spnRange.setValue(craft.getJumpRange());
            spnRange.addChangeListener(this);
        }
        
        if (!techManager.isLegal(Jumpship.getJumpSailTA())) {
            chkSail.setVisible(false);
            if (craft.hasSail()) {
                chkSail.doClick();
            }
        } else {
            chkSail.setVisible((baseType == TYPE_STATION)
                    || craft.isPrimitive());
        }
        lblRange.setVisible(craft.isPrimitive());
        spnRange.setVisible(craft.isPrimitive());
    }
    
    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbBaseType.setEnabled(false);
    }
    
    public void refresh() {
        refreshTonnage();
        refreshSI();
        chkLFBattery.setVisible((baseType != TYPE_STATION)
                && techManager.isLegal(Jumpship.getLFBatteryTA()));
        chkMilitary.setVisible((baseType == TYPE_STATION));
        chkModular.setVisible((baseType == TYPE_STATION)
                && techManager.isLegal(SpaceStation.getModularTA()));
    }

    private void refreshTonnage() {
        int prevTonnage = spnTonnageModel.getNumber().intValue();
        spnTonnageModel.setMinimum(minTonnage);
        spnTonnageModel.setMaximum(maxTonnage);
        spnTonnageModel.setStepSize(stepTonnage);
        if (prevTonnage < minTonnage) {
            spnTonnage.setValue(minTonnage);
        }
        if (prevTonnage > maxTonnage) {
            spnTonnage.setValue(maxTonnage);
        }
    }
    
    private void refreshSI() {
        int prev = spnSIModel.getNumber().intValue();
        if ((baseType == TYPE_JUMPSHIP) || (baseType == TYPE_STATION)) {
            spnSI.setValue(1);
            spnSI.setEnabled(false);
        } else {
            spnSIModel.setMinimum(maxThrust);
            spnSIModel.setMaximum(maxThrust * 30);
            if (prev < maxThrust) {
                spnSI.setValue(maxThrust);
            }
            if (prev > maxThrust * 30) {
                spnSI.setValue(maxThrust * 30);
            }
            spnSI.setEnabled(true);
        }
    }
    
    public double getTonnage() {
        return spnTonnageModel.getNumber().doubleValue();
    }
    
    public void setTonnage(double tonnage) {
        spnTonnage.setValue(new Integer((int)Math.ceil(tonnage)));
    }
    
    public boolean hasLFBattery() {
        return chkLFBattery.isSelected() && chkLFBattery.isEnabled();
    }
    
    public void setLFBattery(boolean battery) {
        chkLFBattery.setSelected(battery);
    }
    
    public void setMaxThrust(int maxThrust) {
        this.maxThrust = maxThrust;
        refreshSI();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        } else if (e.getSource() == spnRange) {
            listeners.forEach(l -> l.rangeChanged(((Integer) spnRange.getValue()).intValue()));
        } else if (e.getSource() == spnSI) {
            listeners.forEach(l -> l.siChanged(spnSIModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkLFBattery) {
            listeners.forEach(l -> l.lfBatteryChanged(hasLFBattery()));
        } else if (e.getSource() == chkModular) {
            listeners.forEach(l -> l.modularChanged(chkModular.isSelected()));
        } else if (e.getSource() == chkSail) {
            listeners.forEach(l -> l.sailChanged(chkSail.isSelected()));
        } else if (e.getSource() == cbBaseType) {
            listeners.forEach(l -> l.baseTypeChanged(cbBaseType.getSelectedIndex()));
        }
    }
}
