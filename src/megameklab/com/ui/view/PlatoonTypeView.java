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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.EntityMovementMode;
import megamek.common.ITechManager;
import megamek.common.Infantry;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestInfantry;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.view.listeners.InfantryBuildListener;

/**
 * Infantry structure tab panel for selecting platoon movement type and number/size of squads.
 * 
 * @author Neoancient
 *
 */
public class PlatoonTypeView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7227731728613831387L;
    
    List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }
    
    public static final int M_FOOT          = 0;
    public static final int M_JUMP          = 1;
    public static final int M_MOTOR         = 2;
    public static final int M_HOVER         = 3;
    public static final int M_TRACKED       = 4;
    public static final int M_WHEELED       = 5;
    public static final int M_VTOL          = 6;
    public static final int M_MICROLITE     = 7;
    public static final int M_UMU           = 8;
    public static final int M_UMU_MOTORIZED = 9;
    public static final int M_SUBMARINE     = 10;
    public static final int NUM_MOTIVE      = 11;
    
    public static final EntityMovementMode[] MOVEMENT_MODES = {
            EntityMovementMode.INF_LEG, EntityMovementMode.INF_JUMP,
            EntityMovementMode.INF_MOTORIZED, EntityMovementMode.HOVER,
            EntityMovementMode.TRACKED, EntityMovementMode.WHEELED,
            EntityMovementMode.VTOL, EntityMovementMode.VTOL,
            EntityMovementMode.INF_UMU, EntityMovementMode.INF_UMU,
            EntityMovementMode.SUBMARINE
    };
    
    private String[] motiveNames;
    private final SpinnerNumberModel spnNumSquadsModel = new SpinnerNumberModel(4, 1, null, 1);
    private final SpinnerNumberModel spnSquadSizeModel = new SpinnerNumberModel(7, 1, 10, 1);
    
    private final CustomComboBox<Integer> cbMotiveType = new CustomComboBox<>(i -> motiveNames[i]);
    private final JSpinner spnNumSquads = new JSpinner(spnNumSquadsModel);
    private final JSpinner spnSquadSize = new JSpinner(spnSquadSizeModel);
    private final JLabel lblMaxSize = new JLabel();
    private final JLabel lblMaxSquadSize = new JLabel();
    
    private ITechManager techManager;
    
    private boolean isEngOrMountain = false;
    
    public PlatoonTypeView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        motiveNames = resourceMap.getString("PlatoonTypeView.cbMotiveType.values").split(","); //$NON-NLS-1$
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        add(new JLabel(resourceMap.getString("PlatoonTypeView.cbMotiveType.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        setFieldSize(cbMotiveType, controlSize);
        cbMotiveType.setToolTipText(resourceMap.getString("PlatoonTypeView.cbMotiveType.tooltip")); //$NON-NLS-1$
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.spnNumSquads.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        setFieldSize(spnNumSquads, spinnerSize);
        spnNumSquads.setToolTipText(resourceMap.getString("PlatoonTypeView.spnNumSquads.tooltip")); //$NON-NLS-1$
        add(spnNumSquads, gbc);
        spnNumSquads.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.spnSquadSize.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        setFieldSize(spnSquadSize, spinnerSize);
        spnSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.spnSquadSize.tooltip")); //$NON-NLS-1$
        add(spnSquadSize, gbc);
        spnSquadSize.addChangeListener(this);

        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSize.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.gridy = 3;
        lblMaxSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSize.tooltip")); //$NON-NLS-1$
        add(lblMaxSize, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 3;
        gbc.gridy = 3;
        lblMaxSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.tooltip")); //$NON-NLS-1$
        add(lblMaxSquadSize, gbc);
        
    }
    
    public void setFromEntity(Infantry inf) {
        isEngOrMountain = inf.hasSpecialization(Infantry.COMBAT_ENGINEERS | Infantry.MOUNTAIN_TROOPS);
        refresh();
        cbMotiveType.removeActionListener(this);
        if (inf.getMovementMode() == EntityMovementMode.VTOL) {
            cbMotiveType.setSelectedItem(inf.hasMicrolite()? M_MICROLITE : M_VTOL);
        } else if (inf.getMovementMode() == EntityMovementMode.INF_UMU) {
            cbMotiveType.setSelectedItem((inf.getOriginalJumpMP() > 1)? M_UMU_MOTORIZED : M_UMU);
        } else {
            for (int i = 0; i < NUM_MOTIVE; i++) {
                if (MOVEMENT_MODES[i] == inf.getMovementMode()) {
                    cbMotiveType.setSelectedItem(i);
                    break;
                }
            }
        }
        cbMotiveType.addActionListener(this);
        
        if (inf.getSquadN() <= (Integer)spnNumSquadsModel.getMaximum()) {
            spnNumSquads.removeChangeListener(this);
            spnNumSquads.setValue(inf.getSquadN());
            spnNumSquads.addChangeListener(this);
        } else {
            spnNumSquads.setValue(spnNumSquadsModel.getMaximum());
        }
        if (inf.getSquadSize() <= (Integer)spnSquadSizeModel.getMaximum()) {
            spnSquadSize.removeChangeListener(this);
            spnSquadSize.setValue(inf.getSquadSize());
            spnSquadSize.addChangeListener(this);
        } else {
            spnSquadSize.setValue(spnSquadSizeModel.getMaximum());
        }
    }
    
    public void refresh() {
        Integer prevMotive = (Integer)cbMotiveType.getSelectedItem();
        cbMotiveType.removeActionListener(this);
        cbMotiveType.removeAllItems();
        for (int i = 0; i < NUM_MOTIVE; i++) {
            if (techManager.isLegal(Infantry.getMotiveTechAdvancement(MOVEMENT_MODES[i]))) {
                cbMotiveType.addItem(i);
            }
        }
        cbMotiveType.setSelectedItem(prevMotive);
        cbMotiveType.addActionListener(this);
        if (cbMotiveType.getSelectedIndex() < 0) {
            cbMotiveType.setSelectedIndex(0);
        }
        
        int maxSize = TestInfantry.maxUnitSize(getMovementMode(), isAltMode(), isEngOrMountain);
        int maxSquad = TestInfantry.maxSquadSize(getMovementMode(), isAltMode());
        spnNumSquads.removeChangeListener(this);
        spnSquadSize.removeChangeListener(this);
        spnNumSquadsModel.setMaximum(maxSize / spnSquadSizeModel.getNumber().intValue());
        spnSquadSizeModel.setMaximum(maxSquad);
        spnNumSquads.addChangeListener(this);
        spnSquadSize.addChangeListener(this);
        
        lblMaxSize.setText(String.valueOf(maxSize));
        lblMaxSquadSize.setText(String.valueOf(maxSquad));
    }
    
    public EntityMovementMode getMovementMode() {
        return MOVEMENT_MODES[getMotiveTypeIndex()];
    }
    
    public boolean isAltMode() {
        int index = getMotiveTypeIndex();
        return (index == M_MICROLITE) || (index == M_UMU_MOTORIZED);
    }
    
    public int getMotiveTypeIndex() {
        return (Integer)cbMotiveType.getSelectedItem();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource() == spnNumSquads) ||
                (e.getSource() == spnSquadSize)) {
            listeners.forEach(l -> l.platoonSizeChanged(spnNumSquadsModel.getNumber().intValue(),
                    spnSquadSizeModel.getNumber().intValue()));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbMotiveType) {
            int index = getMotiveTypeIndex();
            listeners.forEach(l -> l.motiveTypeChanged(MOVEMENT_MODES[index],
                    (index == M_MICROLITE) || (index == M_UMU_MOTORIZED)));
        }
    }
}
