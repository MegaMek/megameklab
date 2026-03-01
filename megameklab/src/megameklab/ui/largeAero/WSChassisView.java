/*
 * Copyright (C) 2018-2026 The MegaMek Team. All Rights Reserved.
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
import java.awt.Insets;
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

import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Jumpship;
import megamek.common.units.SpaceStation;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.AdvancedAeroBuildListener;

/**
 * Structure tab chassis panel for jumpships, warships, and space stations.
 *
 * @author Neoancient
 */
public class WSChassisView extends BuildView implements ActionListener, ChangeListener {
    private final List<AdvancedAeroBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(AdvancedAeroBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(AdvancedAeroBuildListener l) {
        listeners.remove(l);
    }

    public final static int TYPE_JUMPSHIP = 0;
    public final static int TYPE_WARSHIP = 1;
    public final static int TYPE_STATION = 2;
    public final static int TYPE_SUBCOMPACT = 3;

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
    final private ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private final ITechManager techManager;
    private int baseType;
    private int maxTonnage;
    private int minTonnage;
    private int stepTonnage;
    private int maxThrust;

    public WSChassisView(ITechManager techManager) {
        this.techManager = techManager;
        lblRange = createLabel("lblRange", "");
        initUI();
    }

    public void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        cbBaseType.setModel(new DefaultComboBoxModel<>(resourceMap.getString("AdvAeroChassisView.cbBaseType.values")
              .split(",")));

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblTonnage", "AdvAeroChassisView.spnTonnage.text",
              "AdvAeroChassisView.spnTonnage.tooltip"), gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        spnTonnage.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnTonnage.tooltip"));
        add(spnTonnage, gbc);
        spnTonnage.addChangeListener(this);

        chkSail.setText(resourceMap.getString("AdvAeroChassisView.chkSail.text"));
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkSail.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkSail.tooltip"));
        add(chkSail, gbc);
        chkSail.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblBaseType", "AdvAeroChassisView.cbBaseType.text",
              "AdvAeroChassisView.cbBaseType.tooltip"), gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cbBaseType.setToolTipText(resourceMap.getString("AdvAeroChassisView.cbBaseType.tooltip"));
        add(cbBaseType, gbc);
        cbBaseType.addActionListener(this);

        chkMilitary.setText(resourceMap.getString("AdvAeroChassisView.chkMilitary.text"));
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkMilitary.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkMilitary.tooltip"));
        add(chkMilitary, gbc);
        chkMilitary.addActionListener(this);

        chkLFBattery.setText(resourceMap.getString("AdvAeroChassisView.chkLFBattery.text"));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        chkLFBattery.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkLFBattery.tooltip"));
        add(chkLFBattery, gbc);
        chkLFBattery.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lblRange.setText(resourceMap.getString("AdvAeroChassisView.spnRange.text"));
        gbc.gridwidth = 2;
        add(lblRange, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        spnRange.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnRange.tooltip"));
        add(spnRange, gbc);
        spnRange.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblSI", "AdvAeroChassisView.spnSI.text",
              "AdvAeroChassisView.spnSI.tooltip"), gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        spnSI.setToolTipText(resourceMap.getString("AdvAeroChassisView.spnSI.tooltip"));
        add(spnSI, gbc);
        spnSI.addChangeListener(this);

        chkModular.setText(resourceMap.getString("AdvAeroChassisView.chkModular.text"));
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        chkModular.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkModular.tooltip"));
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
              && ((SpaceStation) craft).isModularOrKFAdapter());
        chkModular.addActionListener(this);


        cbBaseType.removeActionListener(this);
        cbBaseType.setSelectedIndex(baseType);
        cbBaseType.addActionListener(this);
        cbBaseType.setEnabled(!craft.isPrimitive());

        spnSIModel.setValue(craft.getOSI());
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
        if (baseType == TYPE_STATION) {
            if ((getTonnage() <= SpaceStation.MODULAR_MINIMUM_WEIGHT)
                  && techManager.isLegal(SpaceStation.getKFAdapterTA())) {
                chkModular.setText(resourceMap.getString("AdvAeroChassisView.chkKFAdapter.text"));
                chkModular.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkKFAdapter.tooltip"));
            } else if ((getTonnage() > SpaceStation.MODULAR_MINIMUM_WEIGHT)
                  && techManager.isLegal(SpaceStation.getModularTA())) {
                chkModular.setText(resourceMap.getString("AdvAeroChassisView.chkModular.text"));
                chkModular.setToolTipText(resourceMap.getString("AdvAeroChassisView.chkModular.tooltip"));
            }
        }
        chkModular.setVisible((baseType == TYPE_STATION)
              && techManager.isLegal(getTonnage() <= 100000.0 ?
              SpaceStation.getKFAdapterTA() : SpaceStation.getModularTA()));
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
        if ((baseType == TYPE_JUMPSHIP) || (baseType == TYPE_STATION) || maxThrust == 0) {
            // maxthrust == 0 means it is a WS with a station-keeping drive, s. SO:AA p.135 and
            // https://battletech.com/forums/index.php?topic=37113.msg859523 (they use a fixed SI of 1)
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
        spnTonnage.setValue((int) Math.ceil(tonnage));
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
            listeners.forEach(l -> l.rangeChanged((Integer) spnRange.getValue()));
        } else if (e.getSource() == spnSI) {
            listeners.forEach(l -> l.siChanged(spnSIModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkLFBattery) {
            listeners.forEach(l -> l.lfBatteryChanged(hasLFBattery()));
        } else if (e.getSource() == chkMilitary) {
            listeners.forEach(l -> l.militaryChanged(chkMilitary.isSelected()));
        } else if (e.getSource() == chkModular) {
            listeners.forEach(l -> l.modularChanged(chkModular.isSelected()));
        } else if (e.getSource() == chkSail) {
            listeners.forEach(l -> l.sailChanged(chkSail.isSelected()));
        } else if (e.getSource() == cbBaseType) {
            listeners.forEach(l -> l.baseTypeChanged(cbBaseType.getSelectedIndex()));
        }
    }
}
