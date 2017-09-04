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
import megamek.common.util.EncodeControl;
import megameklab.com.ui.util.CustomComboBox;

/**
 * Structure tab chassis view for BattleArmor
 * 
 * @author Neoancient
 *
 */
public class BAChassisView extends MainUIView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2230418263440532689L;

    public interface ChassisListener {
        void chassisTypeChanged(int bodyType);
        void weightClassChanged(int weightClass);
        void exoskeletonChanged(boolean exoskeleton);
        void harjelChanged(boolean harjel);
        void squadSizeChanged(int squadSize);
    }
    private final List<ChassisListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ChassisListener l) {
        listeners.add(l);
    }
    public void removeListener(ChassisListener l) {
        listeners.remove(l);
    }
    
    private final JComboBox<String> cbChassisType = new JComboBox<>();
    private final CustomComboBox<Integer> cbWeightClass = new CustomComboBox<>(i -> EntityWeightClass.getClassName(i));
    private final JCheckBox chkExoskeleton = new JCheckBox();
    private final JCheckBox chkHarjel = new JCheckBox();
    private final JSpinner spnSquadSize = new JSpinner(new SpinnerNumberModel(4, 4, 6, 1));
    
    private ITechManager techManager;
    
    public BAChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        cbChassisType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("BAChassisView.cbBodyType.values").split(","))); //$NON-NLS-1$
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("BAChassisView.cbChassisType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        setFieldSize(cbChassisType, controlSize);
        add(cbChassisType, gbc);
        cbChassisType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.cbWeightClass.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        setFieldSize(cbWeightClass, controlSize);
        add(cbWeightClass, gbc);
        cbWeightClass.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("BAChassisView.spnSquadSize.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        setFieldSize(spnSquadSize, controlSize);
        add(spnSquadSize, gbc);
        spnSquadSize.addChangeListener(this);

        JPanel chassisOptions = new JPanel();
        chkExoskeleton.setText(resourceMap.getString("BAChassisView.chkExoskeleton.text")); //$NON-NLS-1$
        chkExoskeleton.addActionListener(this);
        chassisOptions.add(chkExoskeleton);
        chkHarjel.setText(resourceMap.getString("BAChassisView.chkHarjel.text")); //$NON-NLS-1$
        chkHarjel.addActionListener(this);
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
        
        chkHarjel.setEnabled(!techManager.isClan() || isExoskeleton());
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbChassisType) {
            listeners.forEach(l -> l.chassisTypeChanged(cbChassisType.getSelectedIndex()));
        } else if (e.getSource() == cbWeightClass) {
            listeners.forEach(l -> l.weightClassChanged(getWeightClass()));
        } else if (e.getSource() == chkExoskeleton) {
            listeners.forEach(l -> l.exoskeletonChanged(chkExoskeleton.isSelected()));
        } else if (e.getSource() == chkHarjel) {
            listeners.forEach(l -> l.harjelChanged(chkHarjel.isSelected()));
        }
    }
}
