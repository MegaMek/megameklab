/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Vehicle.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.VTOL;
import megamek.common.verifier.TestTank;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class ArmorView extends IView implements ChangeListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private JPanel frontPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel rearPanel = new JPanel();
    private JPanel turretPanel = new JPanel();
    private JPanel turret2Panel = new JPanel();
    private JPanel rearLeftPanel = new JPanel();
    private JPanel rearRightPanel = new JPanel();

    public SpinnerNumberModel leftArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rightArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rearArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel turretArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel turret2ArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rearLeftArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rearRightArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel frontArmorModel = new SpinnerNumberModel();

    private JSpinner leftArmorField = new JSpinner(leftArmorModel);
    private JSpinner rightArmorField = new JSpinner(rightArmorModel);
    private JSpinner rearArmorField = new JSpinner(rearArmorModel);
    private JSpinner turretArmorField = new JSpinner(turretArmorModel);
    private JSpinner turret2ArmorField = new JSpinner(turret2ArmorModel);
    private JSpinner rearLeftArmorField = new JSpinner(rearLeftArmorModel);
    private JSpinner rearRightArmorField = new JSpinner(rearRightArmorModel);
    private JSpinner frontArmorField = new JSpinner(frontArmorModel);

    private List<JSpinner> armorFieldList = new ArrayList<JSpinner>();

    private JLabel frontArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel leftArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rightArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rearArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel turretArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel turret2ArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rearLeftArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rearRightArmorMaxLabel = new JLabel("", SwingConstants.CENTER);

    private List<JLabel> armorMaxLabelList = new ArrayList<JLabel>();

    private JLabel lblAllocatedArmor = new JLabel("Allocated Armor Points:");
    private JTextField valueAllocatedArmor = new JTextField();
    private JLabel lblUnallocatedArmor = new JLabel("Unallocated Armor Points:");
    private JTextField valueUnallocatedArmor = new JTextField();
    private JLabel lblCurrentArmor = new JLabel("Total Armor Points:");
    private JTextField valueCurrentArmor = new JTextField();
    private JLabel lblMaxArmor = new JLabel("Maximum Possible Armor Points:");
    private JTextField valueMaxArmor = new JTextField();
    private JLabel lblWastedArmor = new JLabel("Wasted Armor Points:");
    private JTextField valueWastedArmor = new JTextField();

    private JLabel unallocatedPointsLabelPatchworkFront = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkLeft = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkTurret2 = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRearRight = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRearLeft = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRight = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRear = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkTurret = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsFieldFront = new JLabel();
    private JLabel unallocatedPointsFieldLeft = new JLabel();
    private JLabel unallocatedPointsFieldTurret2 = new JLabel();
    private JLabel unallocatedPointsFieldRearRight = new JLabel();
    private JLabel unallocatedPointsFieldRearLeft = new JLabel();
    private JLabel unallocatedPointsFieldRight = new JLabel();
    private JLabel unallocatedPointsFieldRear = new JLabel();
    private JLabel unallocatedPointsFieldTurret = new JLabel();

    private int armorPoints;
    private int wastedArmorPoints;

    private JButton allocateArmorButton = new JButton("Auto-Allocate Armor");

    private RefreshListener refresh;

    public ArmorView(Tank unit) {
        super(unit);

        setLayout(new GridLayout(1, 1));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        frontPanel.setLayout(new BoxLayout(frontPanel, BoxLayout.Y_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        turret2Panel.setLayout(new BoxLayout(turret2Panel, BoxLayout.Y_AXIS));
        rearRightPanel.setLayout(new BoxLayout(rearRightPanel, BoxLayout.Y_AXIS));
        rearLeftPanel.setLayout(new BoxLayout(rearLeftPanel, BoxLayout.Y_AXIS));
        rearPanel.setLayout(new BoxLayout(rearPanel, BoxLayout.Y_AXIS));
        turretPanel.setLayout(new BoxLayout(turretPanel, BoxLayout.Y_AXIS));


        if (unit instanceof SuperHeavyTank) {
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            mainPanel.add(frontPanel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(leftPanel, gbc);
            gbc.gridx = 1;
            mainPanel.add(turret2Panel, gbc);
            gbc.gridx = 2;
            mainPanel.add(rightPanel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(rearLeftPanel, gbc);
            gbc.gridx = 1;
            mainPanel.add(turretPanel, gbc);
            gbc.gridx = 2;
            mainPanel.add(rearRightPanel, gbc);
            gbc.gridy = 3;
            gbc.gridx = 1;
            mainPanel.add(rearPanel, gbc);

            leftArmorField.setName(Integer.toString(SuperHeavyTank.LOC_FRONTLEFT));
            rightArmorField.setName(Integer.toString(SuperHeavyTank.LOC_FRONTRIGHT));
            rearArmorField.setName(Integer.toString(SuperHeavyTank.LOC_REAR));
            turretArmorField.setName(Integer.toString(SuperHeavyTank.LOC_TURRET));
            turret2ArmorField.setName(Integer.toString(SuperHeavyTank.LOC_TURRET_2));
            rearLeftArmorField.setName(Integer.toString(SuperHeavyTank.LOC_REARLEFT));
            rearRightArmorField.setName(Integer.toString(SuperHeavyTank.LOC_REARRIGHT));
            frontArmorField.setName(Integer.toString(Tank.LOC_FRONT));
            armorFieldList.add(leftArmorField);
            armorFieldList.add(rightArmorField);
            armorFieldList.add(rearArmorField);
            armorFieldList.add(turretArmorField);
            armorFieldList.add(turret2ArmorField);
            armorFieldList.add(rearLeftArmorField);
            armorFieldList.add(rearRightArmorField);
            armorFieldList.add(frontArmorField);
        } else {
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            mainPanel.add(frontPanel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridheight = 2;
            mainPanel.add(leftPanel, gbc);
            gbc.gridx = 1;
            gbc.gridheight = 1;
            mainPanel.add(turret2Panel, gbc);
            gbc.gridx = 2;
            gbc.gridheight = 2;
            mainPanel.add(rightPanel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            mainPanel.add(turretPanel, gbc);
            gbc.gridy = 3;
            gbc.gridx = 1;
            mainPanel.add(rearPanel, gbc);
            leftArmorField.setName(Integer.toString(Tank.LOC_LEFT));
            rightArmorField.setName(Integer.toString(Tank.LOC_RIGHT));
            rearArmorField.setName(Integer.toString(Tank.LOC_REAR));
            turretArmorField.setName(Integer.toString(Tank.LOC_TURRET));
            turret2ArmorField.setName(Integer.toString(Tank.LOC_TURRET_2));
            frontArmorField.setName(Integer.toString(Tank.LOC_FRONT));
            armorFieldList.add(leftArmorField);
            armorFieldList.add(rightArmorField);
            armorFieldList.add(rearArmorField);
            armorFieldList.add(turretArmorField);
            armorFieldList.add(turret2ArmorField);
            armorFieldList.add(frontArmorField);
        }
        Dimension size = new Dimension(40, 25);
        for (JSpinner spinner : armorFieldList) {
            // you don't set the size of the jspinner, but rather its internal
            // textfield
            ((JSpinner.DefaultEditor) spinner.getEditor()).setSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMaximumSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor())
                    .setPreferredSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMinimumSize(size);
        }
        armorMaxLabelList.add(frontArmorMaxLabel);
        armorMaxLabelList.add(leftArmorMaxLabel);
        armorMaxLabelList.add(rightArmorMaxLabel);
        armorMaxLabelList.add(rearArmorMaxLabel);
        armorMaxLabelList.add(turretArmorMaxLabel);
        armorMaxLabelList.add(turret2ArmorMaxLabel);
        armorMaxLabelList.add(rearLeftArmorMaxLabel);
        armorMaxLabelList.add(rearRightArmorMaxLabel);

        Dimension labelSize = new Dimension(40, 20);
        for (JLabel label : armorMaxLabelList) {
            label.setSize(labelSize);
            label.setMaximumSize(labelSize);
            label.setPreferredSize(labelSize);
            label.setMinimumSize(labelSize);
        }

        addAllListeners();

        JPanel topPanel;
        JPanel bottomPanel;

        synchronized (unit) {
            for (int location = 1; location <= unit.locations(); location++) {

                if (unit instanceof SuperHeavyTank) {
                    switch (location) {
                        case Tank.LOC_FRONT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(frontArmorField);
                            topPanel.add(frontArmorMaxLabel);
                            frontPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkFront);
                            bottomPanel.add(unallocatedPointsFieldFront);

                            frontPanel.add(bottomPanel);

                            frontPanel
                                    .setBorder(BorderFactory.createTitledBorder(
                                            null, unit.getLocationAbbr(location),
                                            TitledBorder.TOP,
                                            TitledBorder.DEFAULT_POSITION));
                            break;
                        case SuperHeavyTank.LOC_FRONTLEFT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(leftArmorField);
                            topPanel.add(leftArmorMaxLabel);
                            leftPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkLeft);
                            bottomPanel.add(unallocatedPointsFieldLeft);
                            leftPanel.add(bottomPanel);

                            leftPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case SuperHeavyTank.LOC_FRONTRIGHT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rightArmorField);
                            topPanel.add(rightArmorMaxLabel);
                            rightPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRight);
                            bottomPanel.add(unallocatedPointsFieldRight);
                            rightPanel.add(bottomPanel);

                            rightPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case SuperHeavyTank.LOC_REARRIGHT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rearRightArmorField);
                            topPanel.add(rearRightArmorMaxLabel);
                            rearRightPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            rearRightPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRearRight);
                            bottomPanel.add(unallocatedPointsFieldRearRight);
                            rearRightPanel.add(bottomPanel);

                            break;
                        case SuperHeavyTank.LOC_TURRET_2:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(turret2ArmorField);
                            topPanel.add(turret2ArmorMaxLabel);
                            turret2Panel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            turret2Panel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkTurret2);
                            bottomPanel.add(unallocatedPointsFieldTurret2);
                            turret2Panel.add(bottomPanel);

                            break;
                        case SuperHeavyTank.LOC_REARLEFT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rearLeftArmorField);
                            topPanel.add(rearLeftArmorMaxLabel);
                            rearLeftPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            rearLeftPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRearLeft);
                            bottomPanel.add(unallocatedPointsFieldRearLeft);
                            rearLeftPanel.add(bottomPanel);

                            break;
                        case SuperHeavyTank.LOC_REAR:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rearArmorField);
                            topPanel.add(rearArmorMaxLabel);
                            rearPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRear);
                            bottomPanel.add(unallocatedPointsFieldRear);
                            rearPanel.add(bottomPanel);

                            rearPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case SuperHeavyTank.LOC_TURRET:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(turretArmorField);
                            topPanel.add(turretArmorMaxLabel);
                            turretPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkTurret);
                            bottomPanel.add(unallocatedPointsFieldTurret);
                            turretPanel.add(bottomPanel);

                            turretPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                    }
                } else {
                    switch (location) {
                        case Tank.LOC_FRONT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(frontArmorField);
                            topPanel.add(frontArmorMaxLabel);
                            frontPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkFront);
                            bottomPanel.add(unallocatedPointsFieldFront);

                            frontPanel.add(bottomPanel);

                            frontPanel
                                    .setBorder(BorderFactory.createTitledBorder(
                                            null, unit.getLocationAbbr(location),
                                            TitledBorder.TOP,
                                            TitledBorder.DEFAULT_POSITION));
                            break;
                        case Tank.LOC_LEFT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(leftArmorField);
                            topPanel.add(leftArmorMaxLabel);
                            leftPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkLeft);
                            bottomPanel.add(unallocatedPointsFieldLeft);
                            leftPanel.add(bottomPanel);

                            leftPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case Tank.LOC_RIGHT:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rightArmorField);
                            topPanel.add(rightArmorMaxLabel);
                            rightPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRight);
                            bottomPanel.add(unallocatedPointsFieldRight);
                            rightPanel.add(bottomPanel);

                            rightPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case Tank.LOC_TURRET_2:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(turret2ArmorField);
                            topPanel.add(turret2ArmorMaxLabel);
                            turret2Panel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            turret2Panel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkTurret2);
                            bottomPanel.add(unallocatedPointsFieldTurret2);
                            turret2Panel.add(bottomPanel);

                            break;
                        case Tank.LOC_REAR:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(rearArmorField);
                            topPanel.add(rearArmorMaxLabel);
                            rearPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkRear);
                            bottomPanel.add(unallocatedPointsFieldRear);
                            rearPanel.add(bottomPanel);

                            rearPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                        case Tank.LOC_TURRET:
                            topPanel = new JPanel(new GridLayout(2, 0));
                            topPanel.add(turretArmorField);
                            topPanel.add(turretArmorMaxLabel);
                            turretPanel.add(topPanel);

                            bottomPanel = new JPanel();
                            bottomPanel.add(unallocatedPointsLabelPatchworkTurret);
                            bottomPanel.add(unallocatedPointsFieldTurret);
                            turretPanel.add(bottomPanel);

                            turretPanel.setBorder(BorderFactory.createTitledBorder(
                                    null, unit.getLocationAbbr(location),
                                    TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                            break;
                    }
                }

            }
        }

        JPanel totalArmorPanel = new JPanel();

        Vector<JTextField> valueFields = new Vector<JTextField>();
        valueFields.add(valueUnallocatedArmor);
        valueFields.add(valueAllocatedArmor);
        valueFields.add(valueCurrentArmor);
        valueFields.add(valueMaxArmor);
        valueFields.add(valueWastedArmor);

        Dimension valueSize = new Dimension(45, 20);
        for (JTextField field : valueFields) {
            field.setEditable(false);
            field.setSize(valueSize);
            field.setPreferredSize(valueSize);
            field.setMinimumSize(valueSize);
            field.setMaximumSize(valueSize);
            field.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        totalArmorPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        totalArmorPanel.add(lblUnallocatedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueUnallocatedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        totalArmorPanel.add(lblAllocatedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueAllocatedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        totalArmorPanel.add(lblCurrentArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueCurrentArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        totalArmorPanel.add(lblMaxArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueMaxArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        totalArmorPanel.add(lblWastedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueWastedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        totalArmorPanel.add(allocateArmorButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(totalArmorPanel, gbc);
        this.add(mainPanel);

        resetArmorPoints();
    }

    private void addAllListeners() {
        allocateArmorButton.addActionListener(this);
        for (JSpinner spinner : armorFieldList) {
            spinner.addChangeListener(this);
        }
    }

    private void removeAllListeners() {
        allocateArmorButton.removeActionListener(this);
        for (JSpinner spinner : armorFieldList) {
            spinner.removeChangeListener(this);
        }
    }

    public void refresh() {
        turretPanel.setVisible(!getTank().hasNoTurret()||(unit instanceof VTOL));
        turret2Panel.setVisible(!getTank().hasNoDualTurret()|| ((unit instanceof VTOL) && !getTank().hasNoTurret()));
        removeAllListeners();
        int maxArmor = armorPoints;
        if (unit.hasPatchworkArmor()) {
            maxArmor = (int)Math.floor((unit.getWeight() * 3.5) + 40);
        }
        for (int location = 0; location < unit.locations(); location++) {

            if (unit instanceof SuperHeavyTank) {
                switch (location) {
                    case Tank.LOC_FRONT:
                        frontArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                        frontArmorModel.setMaximum(maxArmor);
                        frontArmorModel.setStepSize(1);
                        frontArmorModel.setMinimum(0);
                        frontArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_FRONTLEFT:
                        leftArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        leftArmorModel.setMaximum(maxArmor);
                        leftArmorModel.setStepSize(1);
                        leftArmorModel.setMinimum(0);
                        leftArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_FRONTRIGHT:
                        rightArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rightArmorModel.setMaximum(maxArmor);
                        rightArmorModel.setStepSize(1);
                        rightArmorModel.setMinimum(0);
                        rightArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_REARRIGHT:
                        rearRightArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rearRightArmorModel.setMaximum(maxArmor);
                        rearRightArmorModel.setStepSize(1);
                        rearRightArmorModel.setMinimum(0);
                        rearRightArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_TURRET_2:
                        turret2ArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        turret2ArmorModel.setMaximum(maxArmor);
                        turret2ArmorModel.setStepSize(1);
                        turret2ArmorModel.setMinimum(0);
                        turret2ArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_REARLEFT:
                        rearLeftArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rearLeftArmorModel.setMaximum(maxArmor);
                        rearLeftArmorModel.setStepSize(1);
                        rearLeftArmorModel.setMinimum(0);

                        rearLeftArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_REAR:
                        rearArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rearArmorModel.setMaximum(maxArmor);
                        rearArmorModel.setStepSize(1);
                        rearArmorModel.setMinimum(0);
                        rearArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case SuperHeavyTank.LOC_TURRET:
                        turretArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        turretArmorModel.setMaximum(maxArmor);
                        turretArmorModel.setStepSize(1);
                        turretArmorModel.setMinimum(0);
                        turretArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                }
            } else {
                switch (location) {
                    case Tank.LOC_FRONT:
                        frontArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                        frontArmorModel.setMaximum(maxArmor);
                        frontArmorModel.setStepSize(1);
                        frontArmorModel.setMinimum(0);
                        frontArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case Tank.LOC_LEFT:
                        leftArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        leftArmorModel.setMaximum(maxArmor);
                        leftArmorModel.setStepSize(1);
                        leftArmorModel.setMinimum(0);
                        leftArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case Tank.LOC_RIGHT:
                        rightArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rightArmorModel.setMaximum(maxArmor);
                        rightArmorModel.setStepSize(1);
                        rightArmorModel.setMinimum(0);
                        rightArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case Tank.LOC_TURRET_2:
                        turret2ArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        turret2ArmorModel.setMaximum(maxArmor);
                        turret2ArmorModel.setStepSize(1);
                        turret2ArmorModel.setMinimum(0);
                        turret2ArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case Tank.LOC_REAR:
                        rearArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        rearArmorModel.setMaximum(maxArmor);
                        rearArmorModel.setStepSize(1);
                        rearArmorModel.setMinimum(0);
                        rearArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                    case Tank.LOC_TURRET:
                        turretArmorModel.setValue(Math.min(maxArmor,
                                unit.getArmor(location)));
                        if (unit instanceof VTOL){
                        turretArmorModel
                                .setMaximum(TestTank.VTOL_MAX_ROTOR_ARMOR);
                        } else {
                            turretArmorModel.setMaximum(maxArmor);
                        }
                        turretArmorModel.setStepSize(1);
                        turretArmorModel.setMinimum(0);
                        turretArmorMaxLabel.setText("max: "
                                + Integer.toString(maxArmor));
                        break;
                }
            }
        }

        // unallocated armorpoints
        if (unit.hasPatchworkArmor()) {
            valueUnallocatedArmor.setVisible(false);
            lblUnallocatedArmor.setVisible(false);
            valueAllocatedArmor.setVisible(false);
            lblAllocatedArmor.setVisible(false);
            valueWastedArmor.setVisible(false);
            lblWastedArmor.setVisible(false);
            allocateArmorButton.setVisible(false);
            unallocatedPointsLabelPatchworkFront.setVisible(true);
            unallocatedPointsLabelPatchworkLeft.setVisible(true);
            unallocatedPointsLabelPatchworkTurret2.setVisible(true);
            unallocatedPointsLabelPatchworkRearRight.setVisible(true);
            unallocatedPointsLabelPatchworkRearLeft.setVisible(true);
            unallocatedPointsLabelPatchworkRight.setVisible(true);
            unallocatedPointsLabelPatchworkRear.setVisible(true);
            unallocatedPointsLabelPatchworkTurret.setVisible(true);
            unallocatedPointsFieldFront.setVisible(true);
            unallocatedPointsFieldLeft.setVisible(true);
            unallocatedPointsFieldTurret2.setVisible(true);
            unallocatedPointsFieldRearRight.setVisible(true);
            unallocatedPointsFieldRearLeft.setVisible(true);
            unallocatedPointsFieldRight.setVisible(true);
            unallocatedPointsFieldRear.setVisible(true);
            unallocatedPointsFieldTurret.setVisible(true);
            unallocatedPointsFieldFront.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, Tank.LOC_FRONT,
                            unit.getArmorWeight(Tank.LOC_FRONT))
                    - unit.getOArmor(Mech.LOC_HEAD)));
            unallocatedPointsFieldLeft.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT,
                            unit.getArmorWeight(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT))
                    - unit.getOArmor(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTLEFT:Tank.LOC_LEFT)));
            unallocatedPointsFieldTurret2.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET_2:Tank.LOC_TURRET_2,
                            unit.getArmorWeight(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET_2:Tank.LOC_TURRET_2))
                    - unit.getOArmor(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET_2:Tank.LOC_TURRET_2)));
            unallocatedPointsFieldRearRight.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, SuperHeavyTank.LOC_REARRIGHT,
                            unit.getArmorWeight(SuperHeavyTank.LOC_REARRIGHT))
                    - unit.getOArmor(SuperHeavyTank.LOC_REARRIGHT)));
            unallocatedPointsFieldRearLeft.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, SuperHeavyTank.LOC_REARLEFT,
                            unit.getArmorWeight(SuperHeavyTank.LOC_REARLEFT))
                    - unit.getOArmor(SuperHeavyTank.LOC_REARLEFT)));
            unallocatedPointsFieldRight.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT,
                            unit.getArmorWeight(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT))
                    - unit.getOArmor(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_FRONTRIGHT:Tank.LOC_RIGHT)));
            unallocatedPointsFieldRear.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_REAR:Tank.LOC_REAR,
                            unit.getArmorWeight(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_REAR:Tank.LOC_REAR))
                    - unit.getOArmor(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_REAR:Tank.LOC_REAR)));
            unallocatedPointsFieldTurret.setText(Integer.toString(UnitUtil
                    .getArmorPoints(unit, unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET:Tank.LOC_TURRET,
                            unit.getArmorWeight(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET:Tank.LOC_TURRET))
                    - unit.getOArmor(unit instanceof SuperHeavyTank?SuperHeavyTank.LOC_TURRET:Tank.LOC_TURRET)));
        } else {
            valueUnallocatedArmor.setVisible(true);
            lblUnallocatedArmor.setVisible(true);
            valueAllocatedArmor.setVisible(true);
            lblAllocatedArmor.setVisible(true);
            allocateArmorButton.setVisible(true);
            valueWastedArmor.setVisible(true);
            lblWastedArmor.setVisible(true);
            unallocatedPointsLabelPatchworkFront.setVisible(false);
            unallocatedPointsLabelPatchworkLeft.setVisible(false);
            unallocatedPointsLabelPatchworkTurret2.setVisible(false);
            unallocatedPointsLabelPatchworkRearRight.setVisible(false);
            unallocatedPointsLabelPatchworkRearLeft.setVisible(false);
            unallocatedPointsLabelPatchworkRight.setVisible(false);
            unallocatedPointsLabelPatchworkRear.setVisible(false);
            unallocatedPointsLabelPatchworkTurret.setVisible(false);
            unallocatedPointsFieldFront.setVisible(false);
            unallocatedPointsFieldLeft.setVisible(false);
            unallocatedPointsFieldTurret2.setVisible(false);
            unallocatedPointsFieldRearRight.setVisible(false);
            unallocatedPointsFieldRearLeft.setVisible(false);
            unallocatedPointsFieldRight.setVisible(false);
            unallocatedPointsFieldRear.setVisible(false);
            unallocatedPointsFieldTurret.setVisible(false);
        }
        valueAllocatedArmor.setText(Integer.toString(unit.getTotalOArmor()));
        valueUnallocatedArmor.setText(Integer.toString(armorPoints
                - unit.getTotalOArmor()));
        if (armorPoints != unit.getTotalOArmor()) {
            valueUnallocatedArmor.setForeground(Color.RED);
            lblUnallocatedArmor.setForeground(Color.RED);
        } else {
            valueUnallocatedArmor.setForeground(Color.BLACK);
            lblUnallocatedArmor.setForeground(Color.BLACK);
        }
        valueCurrentArmor.setText(Integer.toString(armorPoints));
        valueMaxArmor
                .setText(Integer.toString(maxArmor));
        valueWastedArmor.setText(Integer.toString(wastedArmorPoints));

        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void allocateArmor() {
        int pointsToAllocate = armorPoints;

        for (int location = 0; location < unit.locations(); location++) {
            unit.initializeArmor(0, location);
        }
        if (unit instanceof VTOL) {
            unit.initializeArmor(Math.min(pointsToAllocate, 2), VTOL.LOC_ROTOR);
            pointsToAllocate -= 2;
        }

        while (pointsToAllocate > 0) {
            for (int location = 1; location < unit.locations(); location++) {
                if ((unit instanceof VTOL) && (location == VTOL.LOC_ROTOR)) {
                    continue;
                }
                int points = unit.getOArmor(location);
                if ((location == Tank.LOC_FRONT) && (pointsToAllocate >= 2)) {
                    unit.initializeArmor(++points, location);
                    pointsToAllocate--;
                }
                unit.initializeArmor(++points, location);
                if (--pointsToAllocate < 1) {
                    break;
                }
            }

        }
    }

    public void stateChanged(ChangeEvent e) {
        removeAllListeners();
        JSpinner field = (JSpinner) e.getSource();
        int location = Integer.parseInt(field.getName());
        int value = (Integer) field.getModel().getValue();
        unit.initializeArmor(value, location);
        if (unit.hasPatchworkArmor()) {
            setArmorPoints(getTank().getTotalArmor());
        }
        if (refresh != null) {
            addAllListeners();
            refresh.refreshStructure();
            removeAllListeners();
            refresh.refreshStatus();
        }
        addAllListeners();
    }

    public void setArmorPoints(int points) {
        int maxArmor = (int) Math.floor((unit.getWeight() * 3.5) +40);
        wastedArmorPoints = Math.max(points - maxArmor, 0);
        armorPoints = Math.min(maxArmor, points);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeAllListeners();
        if (e.getSource().equals(allocateArmorButton)) {
            allocateArmor();
        }
        addAllListeners();
        refresh.refreshAll();
    }

    public void resetArmorPoints() {
        double armorPerTon = 16.0 * EquipmentType.getArmorPointMultiplier(
                unit.getArmorType(0), unit.getArmorTechLevel(0));
        setArmorPoints((int) Math
                .floor(unit.getLabArmorTonnage() * armorPerTon));
    }
}