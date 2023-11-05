/*
 * MegaMekLab - Copyright (C) 2017-2022 - The MegaMek Team
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
import megamek.common.InfantryMount;
import megamek.common.verifier.TestInfantry;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.InfantryBuildListener;

/**
 * Infantry structure tab panel for selecting platoon movement type and number/size of squads.
 * 
 * @author Neoancient
 */
public class CIPlatoonTypeView extends BuildView implements ActionListener, ChangeListener {
    List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }
    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private enum InfantryMotiveType {
        FOOT(EntityMovementMode.INF_LEG, false, "PlatoonTypeView.cbMotiveType.foot"),
        JUMP(EntityMovementMode.INF_JUMP, false, "PlatoonTypeView.cbMotiveType.jump"),
        MOTORIZED(EntityMovementMode.INF_MOTORIZED, true, "PlatoonTypeView.cbMotiveType.motorized"),
        HOVER(EntityMovementMode.HOVER, false, "PlatoonTypeView.cbMotiveType.hover"),
        TRACKED(EntityMovementMode.TRACKED, true, "PlatoonTypeView.cbMotiveType.tracked"),
        WHEELED(EntityMovementMode.WHEELED, true, "PlatoonTypeView.cbMotiveType.wheeled"),
        VTOL(EntityMovementMode.VTOL, false, "PlatoonTypeView.cbMotiveType.vtol"),
        MICROLITE(EntityMovementMode.VTOL, false, "PlatoonTypeView.cbMotiveType.microlite"),
        UMU(EntityMovementMode.INF_UMU, false, "PlatoonTypeView.cbMotiveType.umu"),
        UMU_MOTORIZED(EntityMovementMode.INF_UMU, false, "PlatoonTypeView.cbMotiveType.umu_motorized"),
        SUBMARINE(EntityMovementMode.SUBMARINE, false, "PlatoonTypeView.cbMotiveType.submarine"),
        BEAST_MOUNTED(EntityMovementMode.NONE, false, "PlatoonTypeView.cbMotiveType.beast_mounted");

        final EntityMovementMode mode;
        final boolean legalFieldGun;
        final String resourceId;

        InfantryMotiveType(EntityMovementMode mode, boolean legalFieldGun, String resourceId) {
            this.mode = mode;
            this.legalFieldGun = legalFieldGun;
            this.resourceId = resourceId;
        }
    }

    private final SpinnerNumberModel spnNumSquadsModel = new SpinnerNumberModel(4, 1, null, 1);
    private final SpinnerNumberModel spnSquadSizeModel = new SpinnerNumberModel(7, 1, 10, 1);
    
    private final CustomComboBox<InfantryMotiveType> cbMotiveType = new CustomComboBox<>
        (i -> resourceMap.getString(i.resourceId));
    private final JSpinner spnNumSquads = new JSpinner(spnNumSquadsModel);
    private final JSpinner spnSquadSize = new JSpinner(spnSquadSizeModel);
    private final JLabel lblMaxSize = new JLabel();
    private final JLabel lblMaxSquadSize = new JLabel();
    private final JLabel lblBeastMountLabel = new JLabel();
    private final JLabel lblBeastMountType = new JLabel();
    
    private final ITechManager techManager;

    private int specialization = 0;
    private boolean isFieldGunner = false;
    private InfantryMount mount = null;

    public CIPlatoonTypeView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 1, 2);
        add(new JLabel(resourceMap.getString("PlatoonTypeView.cbMotiveType.text")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        setFieldSize(cbMotiveType, controlSize);
        cbMotiveType.setToolTipText(resourceMap.getString("PlatoonTypeView.cbMotiveType.tooltip"));
        add(cbMotiveType, gbc);
        cbMotiveType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        lblBeastMountLabel.setText(resourceMap.getString("PlatoonTypeView.lblBeastMountLabel.text"));
        add(lblBeastMountLabel, gbc);
        gbc.gridx++;
        add(lblBeastMountType, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.spnNumSquads.text")), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(spnNumSquads, spinnerSize);
        spnNumSquads.setToolTipText(resourceMap.getString("PlatoonTypeView.spnNumSquads.tooltip"));
        add(spnNumSquads, gbc);
        spnNumSquads.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.spnSquadSize.text")), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        setFieldSize(spnSquadSize, spinnerSize);
        spnSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.spnSquadSize.tooltip"));
        add(spnSquadSize, gbc);
        spnSquadSize.addChangeListener(this);

        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSize.text")), gbc);
        gbc.gridx = 1;
        lblMaxSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSize.tooltip"));
        add(lblMaxSize, gbc);
        
        gbc.gridx = 2;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.text")), gbc);
        gbc.gridx = 3;
        lblMaxSquadSize.setToolTipText(resourceMap.getString("PlatoonTypeView.lblMaxSquadSize.tooltip"));
        add(lblMaxSquadSize, gbc);
        
    }
    
    public void setFromEntity(Infantry inf) {
        specialization = inf.getSpecializations();
        isFieldGunner = inf.hasFieldWeapon();
        mount = inf.getMount();
        refresh();
        cbMotiveType.removeActionListener(this);
        if (inf.getMount() != null) {
            cbMotiveType.setSelectedItem(InfantryMotiveType.BEAST_MOUNTED);
        } else if (inf.getMovementMode() == EntityMovementMode.VTOL) {
            cbMotiveType.setSelectedItem(inf.hasMicrolite() ?
                    InfantryMotiveType.MICROLITE : InfantryMotiveType.VTOL);
        } else if (inf.getMovementMode() == EntityMovementMode.INF_UMU) {
            cbMotiveType.setSelectedItem((inf.getOriginalJumpMP() > 1) ?
                    InfantryMotiveType.UMU_MOTORIZED : InfantryMotiveType.UMU);
       } else {
            for (var type : InfantryMotiveType.values()) {
                if (type.mode == inf.getMovementMode()) {
                    cbMotiveType.setSelectedItem(type);
                }
            }
        }
        cbMotiveType.addActionListener(this);
        
        if (inf.getSquadCount() <= (Integer) spnNumSquadsModel.getMaximum()) {
            spnNumSquads.removeChangeListener(this);
            spnNumSquads.setValue(inf.getSquadCount());
            spnNumSquads.addChangeListener(this);
        } else {
            spnNumSquads.setValue(spnNumSquadsModel.getMaximum());
        }
        if (inf.getSquadSize() <= (Integer) spnSquadSizeModel.getMaximum()) {
            spnSquadSize.removeChangeListener(this);
            spnSquadSize.setValue(inf.getSquadSize());
            spnSquadSize.addChangeListener(this);
        } else {
            spnSquadSize.setValue(spnSquadSizeModel.getMaximum());
        }
    }

    private boolean legalMotiveType(InfantryMotiveType motiveType) {
        if ((specialization & (Infantry.MOUNTAIN_TROOPS | Infantry.PARATROOPS)) != 0) {
            return motiveType == InfantryMotiveType.FOOT;
        } else {
            return techManager.isLegal(Infantry.getMotiveTechAdvancement(motiveType.mode))
                    && (!isFieldGunner || motiveType.legalFieldGun);
        }
    }

    public void refresh() {
        InfantryMotiveType prevMotive = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        cbMotiveType.removeActionListener(this);
        cbMotiveType.removeAllItems();
        for (var type : InfantryMotiveType.values()) {
            if (legalMotiveType(type)) {
                cbMotiveType.addItem(type);
            }
        }
        cbMotiveType.setSelectedItem(prevMotive);
        cbMotiveType.addActionListener(this);
        if (cbMotiveType.getSelectedIndex() < 0) {
            cbMotiveType.setSelectedIndex(0);
        }
        
        int maxSize = TestInfantry.maxUnitSize(getMovementMode(), isAltMode(),
            (specialization & (Infantry.COMBAT_ENGINEERS | Infantry.MOUNTAIN_TROOPS)) != 0, mount);
        int maxSquad = TestInfantry.maxSquadSize(getMovementMode(), isAltMode(), mount);
        spnNumSquads.removeChangeListener(this);
        spnSquadSize.removeChangeListener(this);
        spnNumSquadsModel.setMaximum(maxSize / spnSquadSizeModel.getNumber().intValue());
        spnSquadSizeModel.setMaximum(maxSquad);
        spnNumSquads.addChangeListener(this);
        spnSquadSize.addChangeListener(this);
        
        lblMaxSize.setText(String.valueOf(maxSize));
        lblMaxSquadSize.setText(String.valueOf(maxSquad));
        if (getMovementMode().isNone()) {
            lblBeastMountType.setText(mount.getName());
            lblBeastMountLabel.setVisible(true);
            lblBeastMountType.setVisible(true);
        } else {
            lblBeastMountLabel.setVisible(false);
            lblBeastMountType.setVisible(false);
        }
    }
    
    public EntityMovementMode getMovementMode() {
        InfantryMotiveType type = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        if (type == null) {
            return EntityMovementMode.INF_LEG;
        } else {
            return type.mode;
        }
    }
    
    public boolean isAltMode() {
        InfantryMotiveType type = (InfantryMotiveType) cbMotiveType.getSelectedItem();
        return (type == InfantryMotiveType.MICROLITE) || (type == InfantryMotiveType.UMU_MOTORIZED);
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
            listeners.forEach(l -> l.motiveTypeChanged(getMovementMode(), isAltMode()));
        }
    }
}
