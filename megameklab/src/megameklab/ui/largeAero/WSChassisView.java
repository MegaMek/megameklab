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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatClientProperties;
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

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

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

    private final JSpinner spnTonnage = new JSpinner(spnTonnageModel);
    private final JComboBox<String> cbBaseType = new JComboBox<>();
    private final JLabel lblRange;
    private final JSpinner spnRange = new JSpinner(new SpinnerNumberModel(15, 15, 30, 1));
    private final JCheckBox chkLFBattery = new JCheckBox(I18N.getString("AdvAeroChassisView.chkLFBattery.text"));
    private final JCheckBox chkModular = new JCheckBox(I18N.getString("AdvAeroChassisView.chkModular.text"));
    private final JCheckBox chkSail = new JCheckBox(I18N.getString("AdvAeroChassisView.chkSail.text"));
    private final JCheckBox chkMilitary = new JCheckBox(I18N.getString("AdvAeroChassisView.chkMilitary.text"));
    private final JSpinner spnSI = new JSpinner(spnSIModel);

    private final JLabel primitiveLabel = new JLabel(I18N.getString("AdvAeroChassisView.primitiveInfo.text"));
    private final JLabel tonnageRangeLabel = new JLabel();

    private final ITechManager techManager;
    private int baseType;
    private int maxTonnage;
    private int minTonnage;
    private int stepTonnage;
    private int maxThrust;

    public WSChassisView(ITechManager techManager) {
        this.techManager = techManager;

        lblRange = createLabel("lblRange", I18N.getString("AdvAeroChassisView.spnRange.text"));
        spnTonnage.setToolTipText(I18N.getString("AdvAeroChassisView.spnTonnage.tooltip"));
        chkSail.setToolTipText(I18N.getString("AdvAeroChassisView.chkSail.tooltip"));
        cbBaseType.setToolTipText(I18N.getString("AdvAeroChassisView.cbBaseType.tooltip"));
        primitiveLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "mini");
        primitiveLabel.setEnabled(false);
        primitiveLabel.setBorder(new EmptyBorder(0, 10, 4, 0));
        primitiveLabel.setToolTipText(I18N.getString("AdvAeroChassisView.primitiveInfo.tooltip"));
        tonnageRangeLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "mini");
        tonnageRangeLabel.setEnabled(false);
        tonnageRangeLabel.setBorder(new EmptyBorder(0, 10, 4, 0));
        chkMilitary.setToolTipText(I18N.getString("AdvAeroChassisView.chkMilitary.tooltip"));
        chkLFBattery.setToolTipText(I18N.getString("AdvAeroChassisView.chkLFBattery.tooltip"));
        spnRange.setToolTipText(I18N.getString("AdvAeroChassisView.spnRange.tooltip"));
        spnSI.setToolTipText(I18N.getString("AdvAeroChassisView.spnSI.tooltip"));
        chkModular.setToolTipText(I18N.getString("AdvAeroChassisView.chkModular.tooltip"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] typeNames = I18N.getString("AdvAeroChassisView.cbBaseType.values").split(",");
        cbBaseType.setModel(new DefaultComboBoxModel<>(typeNames));

        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = STANDARD_INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        add(createLabel(I18N,
              "lblBaseType",
              "AdvAeroChassisView.cbBaseType.text",
              "AdvAeroChassisView.cbBaseType.tooltip"), gbc);
        add(cbBaseType, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(primitiveLabel, gbc);

        gbc.gridy++;
        add(createLabel(I18N,
              "lblTonnage",
              "AdvAeroChassisView.spnTonnage.text",
              "AdvAeroChassisView.spnTonnage.tooltip"), gbc);
        add(spnTonnage, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(tonnageRangeLabel, gbc);

        gbc.gridy++;
        add(createLabel(I18N, "lblSI", "AdvAeroChassisView.spnSI.text", "AdvAeroChassisView.spnSI.tooltip"), gbc);
        add(spnSI, gbc);

        gbc.gridy++;
        add(Box.createVerticalStrut(4), gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(chkSail, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(chkMilitary, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(chkLFBattery, gbc);

        gbc.gridy++;
        add(lblRange, gbc);
        add(spnRange, gbc);

        gbc.gridy++;
        add(new JLabel(), gbc);
        add(chkModular, gbc);

        spnTonnage.addChangeListener(this);
        chkSail.addActionListener(this);
        cbBaseType.addActionListener(this);
        chkMilitary.addActionListener(this);
        chkLFBattery.addActionListener(this);
        spnRange.addChangeListener(this);
        spnSI.addChangeListener(this);
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
        primitiveLabel.setVisible(craft.isPrimitive());

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
        tonnageRangeLabel.setText(MessageFormat.format(
              I18N.getString("AdvAeroChassisView.tonnageRange"), minTonnage, maxTonnage));

    }

    public void setAsCustomization() {
        spnTonnage.setEnabled(false);
        cbBaseType.setEnabled(false);
    }

    public void refresh() {
        refreshTonnage();
        refreshSI();
        chkLFBattery.setVisible((baseType != TYPE_STATION) && techManager.isLegal(Jumpship.getLFBatteryTA()));
        chkMilitary.setVisible((baseType == TYPE_STATION));
        if (baseType == TYPE_STATION) {
            if ((getTonnage() <= SpaceStation.MODULAR_MINIMUM_WEIGHT)
                  && techManager.isLegal(SpaceStation.getKFAdapterTA())) {
                chkModular.setText(I18N.getString("AdvAeroChassisView.chkKFAdapter.text"));
                chkModular.setToolTipText(I18N.getString("AdvAeroChassisView.chkKFAdapter.tooltip"));
            } else if ((getTonnage() > SpaceStation.MODULAR_MINIMUM_WEIGHT)
                  && techManager.isLegal(SpaceStation.getModularTA())) {
                chkModular.setText(I18N.getString("AdvAeroChassisView.chkModular.text"));
                chkModular.setToolTipText(I18N.getString("AdvAeroChassisView.chkModular.tooltip"));
            }
        }
        chkModular.setVisible((baseType == TYPE_STATION) && techManager.isLegal(getTonnage() <= 100000.0
              ? SpaceStation.getKFAdapterTA()
              : SpaceStation.getModularTA()));
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
