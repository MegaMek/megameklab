/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
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

import megamek.common.battleArmor.BattleArmor;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.EntityWeightClass;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.BABuildListener;
import megameklab.ui.util.CustomComboBox;

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

    public final static int TURRET_NONE = 0;
    public final static int TURRET_STANDARD = 1;
    public final static int TURRET_MODULAR = 2;

    private final SpinnerNumberModel spnTurretSizeModel = new SpinnerNumberModel(0, 0, 10, 1);
    private final JComboBox<String> cbChassisType = new JComboBox<>();
    private final CustomComboBox<Integer> cbWeightClass = new CustomComboBox<>(EntityWeightClass::getClassName);
    private final JCheckBox chkExoskeleton = new JCheckBox();
    private final JCheckBox chkHarjel = new JCheckBox();
    private final JSpinner spnSquadSize = new JSpinner(new SpinnerNumberModel(4, 1, 6, 1));
    private final JComboBox<String> cbTurretType = new JComboBox<>();
    private final JSpinner spnTurretSize = new JSpinner(spnTurretSizeModel);

    private final ITechManager techManager;

    public BAChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        cbChassisType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("BAChassisView.cbBodyType.values")
              .split(",")));
        cbTurretType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("BAChassisView.cbTurretType.values")
              .split(",")));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblChassisType", "BAChassisView.cbChassisType.text",
              "BAChassisView.cbChassisType.tooltip"), gbc);
        gbc.gridx = 1;
        cbChassisType.setToolTipText(resourceMap.getString("BAChassisView.cbChassisType.tooltip"));
        add(cbChassisType, gbc);
        cbChassisType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblWeightClass", "BAChassisView.cbWeightClass.text",
              "BAChassisView.cbWeightClass.tooltip"), gbc);
        gbc.gridx = 1;
        cbWeightClass.setToolTipText(resourceMap.getString("BAChassisView.cbWeightClass.tooltip"));
        add(cbWeightClass, gbc);
        cbWeightClass.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblSquadSize", "BAChassisView.spnSquadSize.text",
              "BAChassisView.spnSquadSize.tooltip"), gbc);
        gbc.gridx = 1;
        spnSquadSize.setToolTipText(resourceMap.getString("BAChassisView.spnSquadSize.tooltip"));
        add(spnSquadSize, gbc);
        spnSquadSize.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTurretType", "BAChassisView.cbTurretType.text",
              "BAChassisView.cbTurretType.tooltip"), gbc);
        gbc.gridx = 1;
        cbTurretType.setToolTipText(resourceMap.getString("BAChassisView.cbTurretType.tooltip"));
        add(cbTurretType, gbc);
        cbTurretType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTurretSize", "BAChassisView.spnTurretSize.text",
              "BAChassisView.spnTurretSize.tooltip"), gbc);
        gbc.gridx = 1;
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
        return (Integer) cbWeightClass.getSelectedItem();
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
            listeners.forEach(l -> l.squadSizeChanged((Integer) spnSquadSize.getValue()));
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
