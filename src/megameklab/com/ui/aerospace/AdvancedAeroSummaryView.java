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
package megameklab.com.ui.aerospace;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestAdvancedAerospace;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;

/**
 * @author Neoancient
 *
 */
public class AdvancedAeroSummaryView extends IView {

    private static final long serialVersionUID = 3332719282944878899L;
    
    private JTextField txtEngineTon = new JTextField("?");
    private JTextField txtFuelTon = new JTextField("?");   
    private JTextField txtStructureTon = new JTextField("?");
    private JTextField txtKFDriveTon = new JTextField("?");
    private JTextField txtLFBatteryTon = new JTextField("?");
    private JTextField txtSailTon = new JTextField("?");
    private JTextField txtControlTon = new JTextField("?");   
    private JTextField txtQuartersTon = new JTextField("?");
    private JTextField txtHeatTon = new JTextField("?");
    private JTextField txtArmorTon = new JTextField("?");
    private JTextField txtWeaponsTon = new JTextField("?");
    private JTextField txtAmmoTon = new JTextField("?");
    private JTextField txtFireControlTon = new JTextField("?");
    private JTextField txtTransportTon = new JTextField("?");
    private JTextField txtHardpointTon = new JTextField("?");
    private JTextField txtGravDeckTon = new JTextField("?");
    private JTextField txtLifeBoatTon = new JTextField("?");
    private JTextField txtMiscTon = new JTextField("?");

    private EntityVerifier entityVerifier = EntityVerifier.getInstance(
            new File("data/mechfiles/UnitVerifierOptions.xml"));

    public AdvancedAeroSummaryView(EntitySource eSource) {
        super(eSource);

        Vector<JTextField> valueFields = new Vector<JTextField>();

        valueFields.add(txtEngineTon);
        valueFields.add(txtFuelTon);   
        valueFields.add(txtStructureTon);
        valueFields.add(txtKFDriveTon);
        valueFields.add(txtLFBatteryTon);
        valueFields.add(txtSailTon);
        valueFields.add(txtControlTon);   
        valueFields.add(txtQuartersTon);
        valueFields.add(txtHeatTon);
        valueFields.add(txtArmorTon);
        valueFields.add(txtWeaponsTon);
        valueFields.add(txtAmmoTon);
        valueFields.add(txtFireControlTon);
        valueFields.add(txtTransportTon);
        valueFields.add(txtHardpointTon);
        valueFields.add(txtGravDeckTon);
        valueFields.add(txtLifeBoatTon);
        valueFields.add(txtMiscTon);

        Dimension size = new Dimension(100,25);
        for(JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
            field.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        size = new Dimension(100,25);
        for(JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(size);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
            field.setMaximumSize(size);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        size = new Dimension(120,25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,5);
        this.add(createLabel("Category", size, SwingConstants.CENTER), gbc);
        gbc.gridy = 1;
        this.add(createLabel("Transit Drive:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 2;
        this.add(createLabel("Fuel:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 3;
        this.add(createLabel("Structure:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 4;
        this.add(createLabel("KF Drive:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 5;
        this.add(createLabel("LF Battery:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 6;
        this.add(createLabel("Sail:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 7;
        this.add(createLabel("Control:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 8;
        this.add(createLabel("Crew Quarters:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 9;
        this.add(createLabel("Heat Sinks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 10;
        this.add(createLabel("Armor:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 11;
        this.add(createLabel("Weapons:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 12;
        this.add(createLabel("Ammo:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 13;
        this.add(createLabel("Fire Control:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 14;
        this.add(createLabel("Tranport Bays:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 15;
        this.add(createLabel("Docking Hardpoints:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 16;
        this.add(createLabel("Grav Decks:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 17;
        this.add(createLabel("Life Boats:", size, SwingConstants.RIGHT), gbc);
        gbc.gridy = 18;
        this.add(createLabel("Misc:", size, SwingConstants.RIGHT), gbc);

        size = new Dimension(45,25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        this.add(createLabel("Ton", size, SwingConstants.CENTER), gbc);
        for (JTextField field : valueFields) {
            gbc.gridy++;
            this.add(field, gbc);
        }

        setBorder(BorderFactory.createTitledBorder("Summary"));

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

    public void refresh() {
        final TestAdvancedAerospace testShip = 
                new TestAdvancedAerospace(getJumpship(), entityVerifier.aeroOption, null);
        final NumberFormat df = DecimalFormat.getInstance();
       
        txtEngineTon.setText(df.format(testShip.getWeightEngine()));
        txtFuelTon.setText(df.format(testShip.getWeightFuel()));
        txtStructureTon.setText(df.format(testShip.getWeightStructure()));
        txtKFDriveTon.setText(df.format(testShip.getWeightKFDrive()));
        txtLFBatteryTon.setText(df.format(testShip.getWeightLFBattery()));
        txtSailTon.setText(df.format(testShip.getWeightSail()));
        txtControlTon.setText(df.format(testShip.getWeightControls()));
        txtQuartersTon.setText(df.format(testShip.getWeightQuarters()));
        txtHeatTon.setText(df.format(testShip.getWeightHeatSinks()));        
        txtArmorTon.setText(df.format(testShip.getWeightArmor()));
        txtWeaponsTon.setText(df.format(testShip.getWeightWeapon()));
        txtAmmoTon.setText(df.format(testShip.getWeightAmmo()));
        txtFireControlTon.setText(df.format(testShip.getWeightFireControl()));
        txtTransportTon.setText(df.format(testShip.getWeightCarryingSpace()));
        txtHardpointTon.setText(df.format(testShip.getWeightHardpoints()));
        txtGravDeckTon.setText(df.format(testShip.getWeightGravDecks()));
        txtLifeBoatTon.setText(df.format(testShip.getWeightLifeBoats()));
        txtMiscTon.setText(df.format(testShip.getWeightMiscEquip()));
    }
}
