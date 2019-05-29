/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.ui.supportvehicle;

import megamek.common.EntityWeightClass;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;

/**
 * Shows current weight and space of support vehicle components.
 */
public class SVSummaryView extends IView {
    private final JTextField txtStructTon = new JTextField();
    private final JTextField txtEngineTon = new JTextField();
    private final JTextField txtFuelTon = new JTextField();
    private final JTextField txtControlsTon = new JTextField();
    private final JTextField txtHeatTon = new JTextField();
    private final JTextField txtArmorTon = new JTextField();
    private final JTextField txtTurretTon = new JTextField();
    private final JTextField txtPowerAmpTon = new JTextField();
    private final JTextField txtWeaponTon = new JTextField();
    private final JTextField txtAmmoTon = new JTextField();
    private final JTextField txtEquipTon = new JTextField();
    private final JTextField txtBaysTon = new JTextField();

    private final JTextField txtControlsCrit = new JTextField();
    private final JTextField txtArmorCrit = new JTextField();
    private final JTextField txtWeaponCrit = new JTextField();
    private final JTextField txtAmmoCrit = new JTextField();
    private final JTextField txtEquipCrit = new JTextField();
    private final JTextField txtBaysCrit = new JTextField();

    private final JLabel lblWeightUnits = createLabel("Ton:", new Dimension(45, 25), SwingConstants.CENTER);


    private final EntityVerifier entityVerifier = EntityVerifier.getInstance(new File("data/mechfiles/UnitVerifierOptions.xml"));

    public SVSummaryView(EntitySource eSource) {
        super(eSource);

        Dimension size = new Dimension(60,25);
        initValueField(txtStructTon, size);
        initValueField(txtEngineTon, size);
        initValueField(txtFuelTon, size);
        initValueField(txtControlsTon, size);
        initValueField(txtHeatTon, size);
        initValueField(txtArmorTon, size);
        initValueField(txtTurretTon, size);
        initValueField(txtPowerAmpTon, size);
        initValueField(txtWeaponTon, size);
        initValueField(txtAmmoTon, size);
        initValueField(txtEquipTon, size);
        initValueField(txtBaysTon, size);

        initValueField(txtControlsCrit, size);
        initValueField(txtArmorCrit, size);
        initValueField(txtWeaponCrit, size);
        initValueField(txtAmmoCrit, size);
        initValueField(txtEquipCrit, size);
        initValueField(txtBaysCrit, size);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        size = new Dimension(120,25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,5);
        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        this.add(createLabel("Chassis", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Engine:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Fuel:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Controls:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Turrets:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Power Amplifiers:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Weapons:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Ammo:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Equipment:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy++;
        this.add(createLabel("Bays:", size, SwingConstants.RIGHT), gbc);

        size = new Dimension(60,25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,1);
        this.add(lblWeightUnits, gbc);
        gbc.gridy++;
        this.add(txtStructTon, gbc);
        gbc.gridy++;
        this.add(txtEngineTon, gbc);
        gbc.gridy++;
        this.add(txtFuelTon, gbc);
        gbc.gridy++;
        this.add(txtControlsTon, gbc);
        gbc.gridy++;
        this.add(txtHeatTon, gbc);
        gbc.gridy++;
        this.add(txtArmorTon, gbc);
        gbc.gridy++;
        this.add(txtTurretTon, gbc);
        gbc.gridy++;
        this.add(txtPowerAmpTon, gbc);
        gbc.gridy++;
        this.add(txtWeaponTon, gbc);
        gbc.gridy++;
        this.add(txtAmmoTon, gbc);
        gbc.gridy++;
        this.add(txtEquipTon, gbc);
        gbc.gridy++;
        this.add(txtBaysTon, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(createLabel("Crit", size, SwingConstants.CENTER), gbc);
        gbc.gridy++;
        // Structure
        gbc.gridy++;
        // Engine
        this.add(Box.createGlue(), gbc);
        gbc.gridy++;
        // Fuel
        this.add(Box.createGlue(), gbc);
        // Controls
        gbc.gridy++;
        this.add(txtControlsCrit, gbc);
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Heat sinks
        gbc.gridy++;
        this.add(txtArmorCrit, gbc);
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Turret
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Power Amps
        gbc.gridy++;
        this.add(Box.createGlue(), gbc);
        // Equipment
        gbc.gridy++;
        this.add(txtWeaponCrit, gbc);
        gbc.gridy++;
        this.add(txtAmmoCrit, gbc);
        gbc.gridy++;
        this.add(txtEquipCrit, gbc);

        setBorder(BorderFactory.createTitledBorder("Summary"));
    }

    private void initValueField(JTextField field, Dimension size) {
        field.setEditable(false);
        field.setSize(size);
        field.setPreferredSize(size);
        field.setMinimumSize(size);
        field.setMaximumSize(size);
        field.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private JLabel createLabel(String text, Dimension size, int align) {
        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, size);
        label.setHorizontalAlignment(align);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    private String formatWeight(double weight) {
        if (eSource.getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            return NumberFormat.getInstance().format(weight * 1000);
        } else {
            return String.format("%.1f", weight);
        }
    }

    public void refresh() {
        TestSupportVehicle testSV = new TestSupportVehicle(eSource.getEntity(),
                entityVerifier.tankOption, null);
        if (eSource.getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT) {
            lblWeightUnits.setText("Kg");
        } else {
            lblWeightUnits.setText("Tons");
        }

        txtStructTon.setText(formatWeight(testSV.getWeightStructure()));
        txtEngineTon.setText(formatWeight(testSV.getWeightEngine()));
        txtFuelTon.setText(formatWeight(testSV.getFuelTonnage()));
        txtControlsTon.setText(formatWeight(testSV.getWeightControls()));
        txtHeatTon.setText(formatWeight(testSV.getWeightHeatSinks()));
        txtArmorTon.setText(formatWeight(testSV.getWeightArmor()));
        txtTurretTon.setText(formatWeight(testSV.getTankWeightTurret()
            + testSV.getTankWeightDualTurret()));
        txtPowerAmpTon.setText(formatWeight(testSV.getWeightPowerAmp()));
        txtWeaponTon.setText(formatWeight(testSV.getWeightWeapon()));
        txtAmmoTon.setText(formatWeight(testSV.getWeightAmmo()));
        txtEquipTon.setText(formatWeight(testSV.getWeightMiscEquip()));
        txtBaysTon.setText(formatWeight(testSV.getWeightCarryingSpace()));

        txtControlsCrit.setText(Integer.toString(testSV.getCrewSlots()));
        txtArmorCrit.setText(Integer.toString(testSV.getArmorSlots()));
        txtWeaponCrit.setText(Integer.toString(testSV.getWeaponSlots()));
        txtAmmoCrit.setText(Integer.toString(testSV.getAmmoSlots()));
        txtEquipCrit.setText(Integer.toString(testSV.getMiscEquipSlots()));
        txtBaysCrit.setText(Integer.toString(testSV.getTransportSlots()));
    }
}
