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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.ITechManager;
import megamek.common.Protomech;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestProtomech;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.view.listeners.ProtomekBuildListener;

/**
 * Construction options and systems for Protomechs.
 * 
 * @author Neoancient
 *
 */
public class ProtomekChassisView extends BuildView implements ActionListener, ChangeListener {

    private static final long serialVersionUID = -5064588356330519374L;
    
    List<ProtomekBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(ProtomekBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(ProtomekBuildListener l) {
        listeners.remove(l);
    }
    
    public static final int MOTIVE_TYPE_BIPED          = 0;
    public static final int MOTIVE_TYPE_QUAD           = 1;
    public static final int MOTIVE_TYPE_GLIDER         = 2;

    private String[] motiveTypeNames;
    final private SpinnerNumberModel tonnageModel = new SpinnerNumberModel(2, 2, 15, 1);
    final private JSpinner spnTonnage = new JSpinner(tonnageModel);
    final private CustomComboBox<Integer> cbMotiveType = new CustomComboBox<>(i -> motiveTypeNames[i]);
    
    private final ITechManager techManager;
    
    public ProtomekChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        motiveTypeNames = resourceMap.getString("ProtomekChassisView.cbMotiveType.values").split(",");
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("ProtomekChassisView.spnTonnage.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        setFieldSize(spnTonnage, spinnerSize);
        spnTonnage.setToolTipText(resourceMap.getString("ProtomekChassisView.spnTonnage.tooltip")); //$NON-NLS-1$
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("ProtomekChassisView.cbMotiveType.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        setFieldSize(cbMotiveType, controlSize);
        cbMotiveType.setToolTipText(resourceMap.getString("ProtomekChassisView.cbMotiveType.tooltip")); //$NON-NLS-1$
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);
    }

    public void setFromEntity(Protomech mech) {
        refresh();
        setTonnage(mech.getWeight());
        cbMotiveType.removeActionListener(this);
        cbMotiveType.removeAllItems();
        cbMotiveType.addItem(MOTIVE_TYPE_BIPED);
        if (techManager.isLegal(Protomech.TA_QUAD)) {
            cbMotiveType.addItem(MOTIVE_TYPE_QUAD);
        }
        if (techManager.isLegal(Protomech.TA_GLIDER)) {
            cbMotiveType.addItem(MOTIVE_TYPE_GLIDER);
        }
        cbMotiveType.addActionListener(this);
    }
    
    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbMotiveType.setEnabled(false);
    }
    
    public boolean isUltraHeavy() {
        return getTonnage() > TestProtomech.MAX_STD_TONNAGE;
    }
    
    public void refresh() {
        refreshTonnage();
    }

    private void refreshTonnage() {
        int min = (int) TestProtomech.MIN_TONNAGE;
        int max = (int) TestProtomech.MAX_STD_TONNAGE;
        spnTonnage.removeChangeListener(this);
        if (techManager.isLegal(Protomech.TA_ULTRA)) {
            max = (int) TestProtomech.MAX_TONNAGE;
        }
        tonnageModel.setMinimum(min);
        tonnageModel.setMaximum(max);
        spnTonnage.addChangeListener(this);
        if (tonnageModel.getNumber().doubleValue() < min) {
            tonnageModel.setValue(min);
        } else if (tonnageModel.getNumber().doubleValue() > max) {
            tonnageModel.setValue(max);
        }
    }
    
    public double getTonnage() {
        return tonnageModel.getNumber().doubleValue();
    }
    
    public void setTonnage(double tonnage) {
        spnTonnage.setValue(Integer.valueOf((int)Math.ceil(tonnage)));
    }
    
    public int getMotiveTypeIndex() {
        return cbMotiveType.getSelectedIndex();
    }
    
    public void setMotiveTypeIndex(int index) {
        cbMotiveType.setSelectedIndex(index);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbMotiveType) {
            listeners.forEach(l -> l.typeChanged(getMotiveTypeIndex()));
        }
        refresh();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        }
    }
}
