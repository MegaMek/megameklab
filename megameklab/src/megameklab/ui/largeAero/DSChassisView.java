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
package megameklab.ui.largeAero;

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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.interfaces.ITechManager;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.SmallCraft;
import megamek.common.verifier.TestAero;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.DropshipBuildListener;

/**
 * Structure tab chassis panel for small craft and DropShips.
 *
 * @author Neoancient
 */
public class DSChassisView extends BuildView implements ActionListener, ChangeListener {
    private final List<DropshipBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(DropshipBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(DropshipBuildListener l) {
        listeners.remove(l);
    }

    public final static int TYPE_SMALL_CRAFT = 0;
    public final static int TYPE_DROPSHIP = 1;

    public final static int CHASSIS_AERODYNE = 0;
    public final static int CHASSIS_SPHEROID = 1;

    private final SpinnerNumberModel spnTonnageModel = new SpinnerNumberModel(200, 200, null, 100);
    private final SpinnerNumberModel spnSIModel = new SpinnerNumberModel(1, 1, null, 1);

    final private JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    final private JCheckBox chkMilitary = new JCheckBox();
    final private JCheckBox chkKFBoom = new JCheckBox();
    final private JComboBox<String> cbBaseType = new JComboBox<>();
    final private JComboBox<String> cbChassisType = new JComboBox<>();
    final private JSpinner spnSI = new JSpinner(spnSIModel);

    private final ITechManager techManager;
    private boolean dropship;
    private boolean primitive;
    private int maxTonnage;
    private int maxThrust;

    public DSChassisView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    public void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        cbBaseType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("DropshipChassisView.cbBaseType.values")
              .split(",")));
        cbChassisType.setModel(new DefaultComboBoxModel<>(resourceMap.getString(
              "DropshipChassisView.cbChassisType.values").split(",")));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblTonnage", "DropshipChassisView.spnTonnage.text",
              "DropshipChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        spnTonnage.setToolTipText(resourceMap.getString("DropshipChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        chkMilitary.setText(resourceMap.getString("DropshipChassisView.chkFunction.text"));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        chkMilitary.setToolTipText(resourceMap.getString("DropshipChassisView.chkFunction.tooltip"));
        add(chkMilitary, gbc);
        chkMilitary.addActionListener(this);

        chkKFBoom.setText(resourceMap.getString("DropshipChassisView.chkKFBoom.text"));
        gbc.gridx = 3;
        gbc.gridy = 0;
        chkKFBoom.setToolTipText(resourceMap.getString("DropshipChassisView.chkKFBoom.tooltip"));
        add(chkKFBoom, gbc);
        chkKFBoom.addActionListener(this);
        chkKFBoom.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblBaseType", "DropshipChassisView.cbBaseType.text",
              "DropshipChassisView.cbBaseType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        cbBaseType.setToolTipText(resourceMap.getString("DropshipChassisView.cbBaseType.tooltip"));
        add(cbBaseType, gbc);
        cbBaseType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblChassisType", "DropshipChassisView.cbChassisType.text",
              "DropshipChassisView.cbChassisType.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        cbChassisType.setToolTipText(resourceMap.getString("DropshipChassisView.cbChassisType.tooltip"));
        add(cbChassisType, gbc);
        cbChassisType.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblSI", "DropshipChassisView.spnSI.text",
              "DropshipChassisView.spnSI.tooltip"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        spnSI.setToolTipText(resourceMap.getString("DropshipChassisView.spnSI.tooltip"));
        add(spnSI, gbc);
        spnSI.addChangeListener(this);
    }

    public void setFromEntity(SmallCraft craft) {
        dropship = craft.hasETypeFlag(Entity.ETYPE_DROPSHIP);
        primitive = craft.isPrimitive();
        maxTonnage = TestAero.getMaxTonnage(craft, techManager.getTechFaction());
        maxThrust = craft.getRunMP();
        refresh();
        spnTonnage.removeChangeListener(this);
        setTonnage(craft.getWeight());
        spnTonnage.addChangeListener(this);
        chkMilitary.removeActionListener(this);
        setMilitary(craft.getDesignType() == SmallCraft.MILITARY);
        chkMilitary.addActionListener(this);

        if (dropship && primitive) {
            chkKFBoom.removeActionListener(this);
            chkKFBoom.setSelected(((Dropship) craft).getCollarType() != Dropship.COLLAR_NO_BOOM);
            chkKFBoom.addActionListener(this);
        }

        cbBaseType.removeActionListener(this);
        cbBaseType.setSelectedIndex(dropship ? TYPE_DROPSHIP : TYPE_SMALL_CRAFT);
        cbBaseType.addActionListener(this);

        cbChassisType.removeActionListener(this);
        cbChassisType.setSelectedIndex(craft.isSpheroid() ? CHASSIS_SPHEROID : CHASSIS_AERODYNE);
        cbChassisType.addActionListener(this);

        spnSIModel.setValue(craft.getOSI());
    }

    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbBaseType.setEnabled(false);
        cbChassisType.setEnabled(false);
    }

    public void refresh() {
        refreshTonnage();
        refreshSI();
        refreshKFBoom();
    }

    private void refreshTonnage() {
        int prevTonnage = spnTonnageModel.getNumber().intValue();
        int min = dropship ? 200 : 100;
        int max = maxTonnage;
        spnTonnageModel.setMinimum(min);
        spnTonnageModel.setMaximum(max);
        spnTonnageModel.setStepSize(dropship ? 100 : 5);
        if (prevTonnage < min) {
            spnTonnage.setValue(min);
        }
        if (prevTonnage > max) {
            spnTonnage.setValue(max);
        }
    }

    private void refreshSI() {
        int prev = spnSIModel.getNumber().intValue();
        spnSIModel.setMinimum(maxThrust);
        spnSIModel.setMaximum(maxThrust * 30);
        if (prev < maxThrust) {
            spnSI.setValue(maxThrust);
        }
        if (prev > maxThrust * 30) {
            spnSI.setValue(maxThrust * 30);
        }
    }

    private void refreshKFBoom() {
        chkMilitary.setVisible(!primitive);

        if (dropship && primitive) {
            chkKFBoom.setVisible(true);
            if (techManager.isLegal(Dropship.getCollarTA())) {
                chkKFBoom.setEnabled(true);
            } else {
                chkKFBoom.setSelected(false);
                chkKFBoom.setEnabled(false);
                // ActionListener does not respond to setSelected.
                listeners.forEach(l -> l.kfBoomChanged(chkKFBoom.isSelected()));
            }
        } else {
            chkKFBoom.setVisible(false);
        }
    }

    public boolean isDropship() {
        return cbBaseType.getSelectedIndex() == TYPE_SMALL_CRAFT;
    }

    public boolean isSpheroid() {
        return cbChassisType.getSelectedIndex() == CHASSIS_SPHEROID;
    }

    public double getTonnage() {
        return spnTonnageModel.getNumber().doubleValue();
    }

    public void setTonnage(double tonnage) {
        spnTonnage.setValue((int) Math.ceil(tonnage));
    }

    public boolean isMilitary() {
        return chkMilitary.isSelected() && chkMilitary.isEnabled();
    }

    public void setMilitary(boolean omni) {
        chkMilitary.setSelected(omni);
    }

    public void setMaxThrust(int maxThrust) {
        this.maxThrust = maxThrust;
        refreshSI();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.tonnageChanged(getTonnage()));
        } else if (e.getSource() == spnSI) {
            listeners.forEach(l -> l.siChanged(spnSIModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkMilitary) {
            listeners.forEach(l -> l.militaryChanged(isMilitary()));
        } else if (e.getSource() == chkKFBoom) {
            listeners.forEach(l -> l.kfBoomChanged(chkKFBoom.isSelected()));
        } else if (e.getSource() == cbBaseType) {
            listeners.forEach(l -> l.baseTypeChanged(cbBaseType.getSelectedIndex()));
        } else if (e.getSource() == cbChassisType) {
            listeners.forEach(l -> l.chassisTypeChanged(cbChassisType.getSelectedIndex()));
        }
    }
}
