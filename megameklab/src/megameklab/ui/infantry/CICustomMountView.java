/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.units.InfantryMount;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.util.CustomComboBox;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.WidthControlComponent;

public class CICustomMountView extends BuildView implements ChangeListener {

    final static int MAX_MOUNT_VALUES = 10000;

    private RefreshListener refresh = null;
    private final EntitySource entitySource;

    final JTextField txtMountName = new JTextField();
    private final JComboBox<InfantryMount.BeastSize> cbSize = new CustomComboBox<>(InfantryMount.BeastSize::displayName);
    final JTextField txtWeight = new JTextField();
    private final JSpinner spnMovementPoints = new JSpinner();
    private final JComboBox<EntityMovementMode> cbMovementMode = new JComboBox<>();
    private final JSpinner spnInfantryBonus = new JSpinner();
    private final JSpinner spnVehicleBonus = new JSpinner();
    private final JSpinner spnDamageDivisor = new JSpinner();
    private final JLabel lblMaxWaterDepth = new StandardBuildLabel();
    private final JSpinner spnMaxWaterDepth = new JSpinner();
    private final JLabel lblSecondaryGround = new StandardBuildLabel();
    private final JSpinner spnSecondaryGround = new JSpinner();
    private final JLabel lblUWEndurance = new StandardBuildLabel();
    private final JSpinner spnUWEndurance = new JSpinner();

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private final CIMountView mountView;

    public CICustomMountView(EntitySource eSource, CIMountView mountView) {
        entitySource = eSource;
        this.mountView = mountView;
        setUpPanels();
    }

    private void setUpPanels() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = BuildView.STANDARD_INSETS;

        gbc.gridy = 0;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.txtMountName.text")), gbc);
        txtMountName.setToolTipText(resourceMap.getString("CIMountView.txtMountName.tooltip"));
        txtMountName.addCaretListener(ev -> mountView.checkValid());
        add(txtMountName, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.cbSize.text")), gbc);
        cbSize.setToolTipText(resourceMap.getString("CIMountView.cbSize.tooltip"));
        for (var size : InfantryMount.BeastSize.values()) {
            cbSize.addItem(size);
        }
        add(cbSize, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.txtWeight.text")), gbc);
        txtWeight.setToolTipText(resourceMap.getString("CIMountView.txtWeight.tooltip"));
        txtWeight.addCaretListener(ev -> mountView.checkValid());
        add(txtWeight, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.spnMovementPoints.text")), gbc);
        spnMovementPoints.setToolTipText(resourceMap.getString("CIMountView.spnMovementPoints.tooltip"));
        initializeSpinner(spnMovementPoints);
        spnMovementPoints.setModel(new SpinnerNumberModel(1, 1, MAX_MOUNT_VALUES, 1));
        add(spnMovementPoints, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.cbMovementMode.text")), gbc);
        cbMovementMode.setToolTipText(resourceMap.getString("CIMountView.cbMovementMode.tooltip"));
        cbMovementMode.addItem(EntityMovementMode.INF_LEG);
        cbMovementMode.addItem(EntityMovementMode.INF_JUMP);
        cbMovementMode.addItem(EntityMovementMode.VTOL);
        cbMovementMode.addItem(EntityMovementMode.NAVAL);
        cbMovementMode.addItem(EntityMovementMode.SUBMARINE);
        cbMovementMode.addItemListener(ev -> movementModeChanged());
        add(cbMovementMode, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.spnInfantryBonus.text")), gbc);
        spnInfantryBonus.setToolTipText(resourceMap.getString("CIMountView.spnInfantryBonus.tooltip"));
        initializeSpinner(spnInfantryBonus);
        spnInfantryBonus.setModel(new SpinnerNumberModel(0, 0, MAX_MOUNT_VALUES, 1));
        add(spnInfantryBonus, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.spnVehicleBonus.text")), gbc);
        spnVehicleBonus.setToolTipText(resourceMap.getString("CIMountView.spnVehicleBonus.tooltip"));
        initializeSpinner(spnVehicleBonus);
        spnVehicleBonus.setModel(new SpinnerNumberModel(0, 0, MAX_MOUNT_VALUES, 1));
        add(spnVehicleBonus, gbc);

        gbc.gridy++;
        add(new StandardBuildLabel(resourceMap.getString("CIMountView.spnDamageDivisor.text")), gbc);
        spnDamageDivisor.setToolTipText(resourceMap.getString("CIMountView.spnDamageDivisor.tooltip"));
        initializeSpinner(spnDamageDivisor);
        spnDamageDivisor.setModel(new SpinnerNumberModel(1.0, 1.0, MAX_MOUNT_VALUES, 0.5));
        add(spnDamageDivisor, gbc);

        gbc.gridy++;
        lblMaxWaterDepth.setText(resourceMap.getString("CIMountView.spnMaxWaterDepth.text"));
        add(lblMaxWaterDepth, gbc);
        spnMaxWaterDepth.setToolTipText(resourceMap.getString("CIMountView.spnMaxWaterDepth.tooltip"));
        spnMaxWaterDepth.setModel(new SpinnerNumberModel(0, 0, MAX_MOUNT_VALUES, 1));
        initializeSpinner(spnMaxWaterDepth);
        add(spnMaxWaterDepth, gbc);

        gbc.gridy++;
        lblSecondaryGround.setText(resourceMap.getString("CIMountView.spnSecondaryGround.text"));
        add(lblSecondaryGround, gbc);
        spnSecondaryGround.setToolTipText(resourceMap.getString("CIMountView.spnSecondaryGround.tooltip"));
        initializeSpinner(spnSecondaryGround);
        spnSecondaryGround.setModel(new SpinnerNumberModel(0, 0, MAX_MOUNT_VALUES, 1));
        add(spnSecondaryGround, gbc);

        gbc.gridy++;
        lblUWEndurance.setText(resourceMap.getString("CIMountView.spnUWEndurance.text"));
        add(lblUWEndurance, gbc);
        spnUWEndurance.setToolTipText(resourceMap.getString("CIMountView.spnUWEndurance.tooltip"));
        initializeSpinner(spnUWEndurance);
        spnUWEndurance.setModel(new SpinnerNumberModel(0, 0, MAX_MOUNT_VALUES, 1));
        add(spnUWEndurance, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(new WidthControlComponent(), gbc);

        movementModeChanged();
    }

    private void initializeSpinner(JSpinner spinner) {
        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(UIManager.getColor("TextField.background"));
    }

    public void refresh() {
        InfantryMount mount = getInfantry().getMount();
        if (mount != null) {
            txtMountName.setText(mount.name());
            cbSize.setSelectedItem(mount.size());
            txtWeight.setText(String.valueOf(mount.weight()));
            spnMovementPoints.setValue(mount.getMP());
            cbMovementMode.setSelectedItem(mount.movementMode());
            spnInfantryBonus.setValue(mount.getBurstDamageDice());
            spnVehicleBonus.setValue(mount.vehicleDamage());
            spnDamageDivisor.setValue(mount.damageDivisor());
            spnMaxWaterDepth.setValue(mount.maxWaterDepth());
            spnSecondaryGround.setValue(mount.secondaryGroundMP());
            spnUWEndurance.setValue(mount.getUWEndurance());
        }
    }

    void movementModeChanged() {
        EntityMovementMode mode = Objects.requireNonNull((EntityMovementMode) cbMovementMode.getSelectedItem());
        lblMaxWaterDepth.setEnabled(!mode.isSubmarine());
        spnMaxWaterDepth.setEnabled(!mode.isSubmarine());
        lblSecondaryGround.setEnabled(!mode.isLegInfantry());
        spnSecondaryGround.setEnabled(!mode.isLegInfantry());
        lblUWEndurance.setEnabled(mode.isSubmarine());
        spnUWEndurance.setEnabled(mode.isSubmarine());
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private double getCustomWeight() {
        double weight = 0.5;
        try {
            weight = Double.parseDouble(txtWeight.getText());
        } catch (NumberFormatException ex) {
            txtWeight.setText(String.valueOf(weight));
        }
        return weight;
    }

    InfantryMount customMount() {
        EntityMovementMode mode = (EntityMovementMode) Objects.requireNonNull(cbMovementMode.getSelectedItem());
        return new InfantryMount(
              txtMountName.getText(),
              (InfantryMount.BeastSize) cbSize.getSelectedItem(),
              getCustomWeight(),
              (Integer) spnMovementPoints.getValue(),
              (EntityMovementMode) cbMovementMode.getSelectedItem(),
              (Integer) spnInfantryBonus.getValue(),
              (Integer) spnVehicleBonus.getValue(),
              (Double) spnDamageDivisor.getValue(),
              mode.isSubmarine() ? Integer.MAX_VALUE : (Integer) spnMaxWaterDepth.getValue(),
              mode.isLegInfantry() ? 0 : (Integer) spnSecondaryGround.getValue(),
              mode.isSubmarine() ? (Integer) spnUWEndurance.getValue() : 0
        );
    }

    private Infantry getInfantry() {
        return (Infantry) entitySource.getEntity();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        double value = (Double) field.getModel().getValue();

        getInfantry().setCustomArmorDamageDivisor(value);
        if (refresh != null) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
        refresh();
    }
}
