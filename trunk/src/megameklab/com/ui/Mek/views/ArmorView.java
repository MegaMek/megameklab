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

package megameklab.com.ui.Mek.views;

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
import javax.swing.Box;
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
import megamek.common.TripodMech;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class ArmorView extends IView implements ChangeListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private JPanel headPanel = new JPanel();
    private JPanel laPanel = new JPanel();
    private JPanel raPanel = new JPanel();
    private JPanel llPanel = new JPanel();
    private JPanel rlPanel = new JPanel();
    private JPanel ltPanel = new JPanel();
    private JPanel rtPanel = new JPanel();
    private JPanel ctPanel = new JPanel();
    private JPanel clPanel = new JPanel();

    public SpinnerNumberModel laArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel raArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel llArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rlArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel ltArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rtArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel ctArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel hdArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel clArmorModel = new SpinnerNumberModel();

    private SpinnerNumberModel rtrArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel ltrArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel ctrArmorModel = new SpinnerNumberModel();

    private JSpinner laArmorField = new JSpinner(laArmorModel);
    private JSpinner raArmorField = new JSpinner(raArmorModel);
    private JSpinner llArmorField = new JSpinner(llArmorModel);
    private JSpinner rlArmorField = new JSpinner(rlArmorModel);
    private JSpinner ltArmorField = new JSpinner(ltArmorModel);
    private JSpinner rtArmorField = new JSpinner(rtArmorModel);
    private JSpinner ctArmorField = new JSpinner(ctArmorModel);
    private JSpinner hdArmorField = new JSpinner(hdArmorModel);
    private JSpinner clArmorField = new JSpinner(clArmorModel);

    private JSpinner rtrArmorField = new JSpinner(rtrArmorModel);
    private JSpinner ltrArmorField = new JSpinner(ltrArmorModel);
    private JSpinner ctrArmorField = new JSpinner(ctrArmorModel);
    private List<JSpinner> armorFieldList = new ArrayList<JSpinner>();

    private JLabel hdArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel laArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel raArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel llArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rlArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel ltArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rtArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel ctArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel clArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
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

    private JLabel unallocatedPointsLabelPatchworkHead = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkLa = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkLt = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkCt = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRt = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRa = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkLl = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkRl = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsLabelPatchworkCl = new JLabel(
            "Unalloc.:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsFieldHead = new JLabel();
    private JLabel unallocatedPointsFieldLa = new JLabel();
    private JLabel unallocatedPointsFieldLt = new JLabel();
    private JLabel unallocatedPointsFieldCt = new JLabel();
    private JLabel unallocatedPointsFieldRt = new JLabel();
    private JLabel unallocatedPointsFieldRa = new JLabel();
    private JLabel unallocatedPointsFieldLl = new JLabel();
    private JLabel unallocatedPointsFieldRl = new JLabel();
    private JLabel unallocatedPointsFieldCl = new JLabel();

    private int armorPoints;
    private int wastedArmorPoints;

    private JButton allocateArmorButton = new JButton("Auto-Allocate Armor");

    private RefreshListener refresh;

    public ArmorView(EntitySource eSource) {
        super(eSource);

        setLayout(new GridLayout(1, 1));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.Y_AXIS));
        laPanel.setLayout(new BoxLayout(laPanel, BoxLayout.Y_AXIS));
        raPanel.setLayout(new BoxLayout(raPanel, BoxLayout.Y_AXIS));
        ltPanel.setLayout(new BoxLayout(ltPanel, BoxLayout.Y_AXIS));
        ctPanel.setLayout(new BoxLayout(ctPanel, BoxLayout.Y_AXIS));
        rtPanel.setLayout(new BoxLayout(rtPanel, BoxLayout.Y_AXIS));
        llPanel.setLayout(new BoxLayout(llPanel, BoxLayout.Y_AXIS));
        rlPanel.setLayout(new BoxLayout(rlPanel, BoxLayout.Y_AXIS));
        clPanel.setLayout(new BoxLayout(clPanel, BoxLayout.Y_AXIS));

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(headPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(laPanel, gbc);
        gbc.gridx = 1;
        mainPanel.add(ltPanel, gbc);
        gbc.gridx = 2;
        mainPanel.add(ctPanel, gbc);
        gbc.gridx = 3;
        mainPanel.add(rtPanel, gbc);
        gbc.gridx = 4;
        mainPanel.add(raPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(llPanel, gbc);
        gbc.gridx = 2;
        mainPanel.add(clPanel, gbc);
        gbc.gridx = 3;
        mainPanel.add(rlPanel, gbc);

        laArmorField.setName(Integer.toString(Mech.LOC_LARM));
        raArmorField.setName(Integer.toString(Mech.LOC_RARM));
        llArmorField.setName(Integer.toString(Mech.LOC_LLEG));
        rlArmorField.setName(Integer.toString(Mech.LOC_RLEG));
        clArmorField.setName(Integer.toString(Mech.LOC_CLEG));
        ltArmorField.setName(Integer.toString(Mech.LOC_LT));
        rtArmorField.setName(Integer.toString(Mech.LOC_RT));
        ctArmorField.setName(Integer.toString(Mech.LOC_CT));
        hdArmorField.setName(Integer.toString(Mech.LOC_HEAD));
        rtrArmorField.setName(Integer.toString(Mech.LOC_RT));
        ltrArmorField.setName(Integer.toString(Mech.LOC_LT));
        ctrArmorField.setName(Integer.toString(Mech.LOC_CT));

        armorFieldList.add(laArmorField);
        armorFieldList.add(raArmorField);
        armorFieldList.add(llArmorField);
        armorFieldList.add(rlArmorField);
        armorFieldList.add(clArmorField);
        armorFieldList.add(ltArmorField);
        armorFieldList.add(rtArmorField);
        armorFieldList.add(ctArmorField);
        armorFieldList.add(hdArmorField);
        armorFieldList.add(ltrArmorField);
        armorFieldList.add(ctrArmorField);
        armorFieldList.add(rtrArmorField);

        Dimension size = new Dimension(40, 25);
        for (JSpinner spinner : armorFieldList) {
            spinner.setToolTipText("Front Armor");
            // you don't set the size of the jspinner, but rather its internal
            // textfield
            ((JSpinner.DefaultEditor) spinner.getEditor()).setSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMaximumSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor())
                    .setPreferredSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMinimumSize(size);
        }
        rtrArmorField.setToolTipText("Rear Armor");
        ltrArmorField.setToolTipText("Rear Armor");
        ctrArmorField.setToolTipText("Rear Armor");

        armorMaxLabelList.add(hdArmorMaxLabel);
        armorMaxLabelList.add(laArmorMaxLabel);
        armorMaxLabelList.add(raArmorMaxLabel);
        armorMaxLabelList.add(llArmorMaxLabel);
        armorMaxLabelList.add(rlArmorMaxLabel);
        armorMaxLabelList.add(clArmorMaxLabel);
        armorMaxLabelList.add(ltArmorMaxLabel);
        armorMaxLabelList.add(rtArmorMaxLabel);
        armorMaxLabelList.add(ctArmorMaxLabel);

        Dimension labelSize = new Dimension(40, 20);
        for (JLabel label : armorMaxLabelList) {
            label.setSize(labelSize);
            label.setMaximumSize(labelSize);
            label.setPreferredSize(labelSize);
            label.setMinimumSize(labelSize);
        }

        JPanel topPanel;
        JPanel bottomPanel;

        synchronized (getMech()) {
            for (int location = 0; location < getMech().locations(); location++) {

                switch (location) {
                    case Mech.LOC_HEAD:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(hdArmorField);
                        topPanel.add(hdArmorMaxLabel);
                        headPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkHead);
                        bottomPanel.add(unallocatedPointsFieldHead);

                        headPanel.add(bottomPanel);

                        headPanel
                                .setBorder(BorderFactory.createTitledBorder(
                                        null, getMech().getLocationAbbr(location),
                                        TitledBorder.TOP,
                                        TitledBorder.DEFAULT_POSITION));
                        
                        hdArmorModel.setStepSize(1);
                        hdArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_LARM:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(laArmorField);
                        topPanel.add(laArmorMaxLabel);
                        laPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkLa);
                        bottomPanel.add(unallocatedPointsFieldLa);
                        laPanel.add(bottomPanel);

                        laPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        
                        laArmorModel.setStepSize(1);
                        laArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_RARM:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(raArmorField);
                        topPanel.add(raArmorMaxLabel);
                        raPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkRa);
                        bottomPanel.add(unallocatedPointsFieldRa);
                        raPanel.add(bottomPanel);

                        raPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        
                        raArmorModel.setStepSize(1);
                        raArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_CT:
                        topPanel = new JPanel(new GridLayout(4, 0));
                        topPanel.add(ctArmorField);
                        topPanel.add(new JLabel("Rear", SwingConstants.CENTER));
                        topPanel.add(ctrArmorField);
                        topPanel.add(ctArmorMaxLabel);
                        ctPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        ctPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkCt);
                        bottomPanel.add(unallocatedPointsFieldCt);
                        ctPanel.add(bottomPanel);

                        ctArmorModel.setStepSize(1);
                        ctArmorModel.setMinimum(0);
                        ctrArmorModel.setStepSize(1);
                        ctrArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_LT:
                        topPanel = new JPanel(new GridLayout(4, 0));
                        topPanel.add(ltArmorField);
                        topPanel.add(new JLabel("Rear", SwingConstants.CENTER));
                        topPanel.add(ltrArmorField);
                        topPanel.add(ltArmorMaxLabel);
                        ltPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        ltPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkLt);
                        bottomPanel.add(unallocatedPointsFieldLt);
                        ltPanel.add(bottomPanel);

                        ltArmorModel.setStepSize(1);
                        ltArmorModel.setMinimum(0);
                        ltrArmorModel.setStepSize(1);
                        ltrArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_RT:
                        topPanel = new JPanel(new GridLayout(4, 0));
                        topPanel.add(rtArmorField);
                        topPanel.add(new JLabel("Rear", SwingConstants.CENTER));
                        topPanel.add(rtrArmorField);
                        topPanel.add(rtArmorMaxLabel);
                        rtPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        rtPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkRt);
                        bottomPanel.add(unallocatedPointsFieldRt);
                        rtPanel.add(bottomPanel);

                        rtArmorModel.setStepSize(1);
                        rtArmorModel.setMinimum(0);
                        rtrArmorModel.setStepSize(1);
                        rtrArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_LLEG:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(llArmorField);
                        topPanel.add(llArmorMaxLabel);
                        llPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkLl);
                        bottomPanel.add(unallocatedPointsFieldLl);
                        llPanel.add(bottomPanel);

                        llPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        
                        llArmorModel.setStepSize(1);
                        llArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_RLEG:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(rlArmorField);
                        topPanel.add(rlArmorMaxLabel);
                        rlPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkRl);
                        bottomPanel.add(unallocatedPointsFieldRl);
                        rlPanel.add(bottomPanel);

                        rlPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        
                        rlArmorModel.setStepSize(1);
                        rlArmorModel.setMinimum(0);
                        break;
                    case Mech.LOC_CLEG:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(clArmorField);
                        topPanel.add(clArmorMaxLabel);
                        clPanel.add(topPanel);

                        bottomPanel = new JPanel();
                        bottomPanel.add(unallocatedPointsLabelPatchworkCl);
                        bottomPanel.add(unallocatedPointsFieldCl);
                        clPanel.add(bottomPanel);

                        clPanel.setBorder(BorderFactory.createTitledBorder(
                                null, getMech().getLocationAbbr(location),
                                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        
                        clArmorModel.setStepSize(1);
                        clArmorModel.setMinimum(0);
                        break;
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
        totalArmorPanel.add(Box.createVerticalStrut(12), gbc);
        gbc.gridy++;
        totalArmorPanel.add(lblUnallocatedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueUnallocatedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        totalArmorPanel.add(lblAllocatedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueAllocatedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        totalArmorPanel.add(lblCurrentArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueCurrentArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        totalArmorPanel.add(lblMaxArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueMaxArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        totalArmorPanel.add(lblWastedArmor, gbc);
        gbc.gridx = 1;
        totalArmorPanel.add(valueWastedArmor, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        totalArmorPanel.add(Box.createVerticalStrut(15), gbc);
        gbc.gridy++;
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
        
        addAllListeners();
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
        removeAllListeners();
        clPanel.setVisible(getMech() instanceof TripodMech);
        for (int location = 0; location < getMech().locations(); location++) {
            int maxArmor = getMech().getOInternal(location) * 2;
            int headMaxArmor = 9;
            if (getMech().isSuperHeavy()) {
                headMaxArmor = 12;
            }
            int rearArmor;
            switch (location) {
                case Mech.LOC_HEAD:
                    hdArmorModel.setValue(Math.min(headMaxArmor, getMech().getArmor(location)));
                    hdArmorModel.setMaximum(headMaxArmor);                   
                    hdArmorMaxLabel.setText("max: "+headMaxArmor);
                    break;
                case Mech.LOC_LARM:
                    laArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    laArmorModel.setMaximum(maxArmor);                   
                    laArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_RARM:
                    raArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    raArmorModel.setMaximum(maxArmor);
                    raArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_CT:
                    ctArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    ctArmorModel.setMaximum(maxArmor);
                    ctrArmorModel.setMaximum(maxArmor);
                    rearArmor = Math.min(getMech().getArmor(location, true),
                            maxArmor - getMech().getArmor(location));
                    ctrArmorModel.setValue(rearArmor);
                    getMech().initializeRearArmor(rearArmor, location);
                                        
                    ctArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_LT:
                    ltArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));

                    ltArmorModel.setMaximum(maxArmor);
                    ltrArmorModel.setMaximum(maxArmor);
                    rearArmor = Math.min(getMech().getArmor(location, true),
                            maxArmor - getMech().getArmor(location));
                    ltrArmorModel.setValue(rearArmor);
                    getMech().initializeRearArmor(rearArmor, location);
                    
                    ltArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_RT:
                    rtArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));

                    rtArmorModel.setMaximum(maxArmor);
                    rtrArmorModel.setMaximum(maxArmor);
                    rearArmor = Math.min(getMech().getArmor(location, true),
                            maxArmor - getMech().getArmor(location));
                    rtrArmorModel.setValue(rearArmor);
                    getMech().initializeRearArmor(rearArmor, location);
                    
                    rtArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_LLEG:
                    llArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    llArmorModel.setMaximum(maxArmor);
                    llArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_RLEG:
                    rlArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    rlArmorModel.setMaximum(maxArmor);
                    rlArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
                case Mech.LOC_CLEG:
                    clArmorModel.setValue(Math.min(maxArmor,
                            getMech().getArmor(location)));
                    clArmorModel.setMaximum(maxArmor);
                    clArmorMaxLabel.setText("max: "
                            + Integer.toString(maxArmor));
                    break;
            }
        }

        // unallocated armorpoints
        if (getMech().hasPatchworkArmor()) {
            valueUnallocatedArmor.setVisible(false);
            lblUnallocatedArmor.setVisible(false);
            valueAllocatedArmor.setVisible(false);
            lblAllocatedArmor.setVisible(false);
            valueWastedArmor.setVisible(false);
            lblWastedArmor.setVisible(false);
            allocateArmorButton.setVisible(false);
            unallocatedPointsLabelPatchworkHead.setVisible(true);
            unallocatedPointsLabelPatchworkLa.setVisible(true);
            unallocatedPointsLabelPatchworkLt.setVisible(true);
            unallocatedPointsLabelPatchworkCt.setVisible(true);
            unallocatedPointsLabelPatchworkRt.setVisible(true);
            unallocatedPointsLabelPatchworkRa.setVisible(true);
            unallocatedPointsLabelPatchworkLl.setVisible(true);
            unallocatedPointsLabelPatchworkRl.setVisible(true);
            unallocatedPointsFieldHead.setVisible(true);
            unallocatedPointsFieldLa.setVisible(true);
            unallocatedPointsFieldLt.setVisible(true);
            unallocatedPointsFieldCt.setVisible(true);
            unallocatedPointsFieldRt.setVisible(true);
            unallocatedPointsFieldRa.setVisible(true);
            unallocatedPointsFieldLl.setVisible(true);
            unallocatedPointsFieldRl.setVisible(true);
            unallocatedPointsFieldHead.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_HEAD,
                            getMech().getArmorWeight(Mech.LOC_HEAD))
                    - getMech().getOArmor(Mech.LOC_HEAD)));
            unallocatedPointsFieldLa.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_LARM,
                            getMech().getArmorWeight(Mech.LOC_LARM))
                    - getMech().getOArmor(Mech.LOC_LARM)));
            unallocatedPointsFieldLt.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_LT,
                            getMech().getArmorWeight(Mech.LOC_LT))
                    - getMech().getOArmor(Mech.LOC_LT) - getMech().getOArmor(Mech.LOC_LT, true)));
            unallocatedPointsFieldCt.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_CT,
                            getMech().getArmorWeight(Mech.LOC_CT))
                    - getMech().getOArmor(Mech.LOC_CT) - getMech().getOArmor(Mech.LOC_CT, true)));
            unallocatedPointsFieldRt.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_RT,
                            getMech().getArmorWeight(Mech.LOC_RT))
                    - getMech().getOArmor(Mech.LOC_RT) - getMech().getOArmor(Mech.LOC_RT, true)));
            unallocatedPointsFieldRa.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_RARM,
                            getMech().getArmorWeight(Mech.LOC_RARM))
                    - getMech().getOArmor(Mech.LOC_RARM)));
            unallocatedPointsFieldLl.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_LLEG,
                            getMech().getArmorWeight(Mech.LOC_LLEG))
                    - getMech().getOArmor(Mech.LOC_LLEG)));
            unallocatedPointsFieldRl.setText(Integer.toString(UnitUtil
                    .getArmorPoints(getMech(), Mech.LOC_RLEG,
                            getMech().getArmorWeight(Mech.LOC_RLEG))
                    - getMech().getOArmor(Mech.LOC_RLEG)));
            if (getMech() instanceof TripodMech) {
                unallocatedPointsFieldCl.setVisible(true);
                unallocatedPointsFieldCl.setText(Integer.toString(UnitUtil
                        .getArmorPoints(getMech(), Mech.LOC_CLEG,
                                getMech().getArmorWeight(Mech.LOC_CLEG))
                        - getMech().getOArmor(Mech.LOC_CLEG)));
            }
        } else {
            valueUnallocatedArmor.setVisible(true);
            lblUnallocatedArmor.setVisible(true);
            valueAllocatedArmor.setVisible(true);
            lblAllocatedArmor.setVisible(true);
            allocateArmorButton.setVisible(true);
            valueWastedArmor.setVisible(true);
            lblWastedArmor.setVisible(true);
            unallocatedPointsLabelPatchworkHead.setVisible(false);
            unallocatedPointsLabelPatchworkLa.setVisible(false);
            unallocatedPointsLabelPatchworkLt.setVisible(false);
            unallocatedPointsLabelPatchworkCt.setVisible(false);
            unallocatedPointsLabelPatchworkRt.setVisible(false);
            unallocatedPointsLabelPatchworkRa.setVisible(false);
            unallocatedPointsLabelPatchworkLl.setVisible(false);
            unallocatedPointsLabelPatchworkRl.setVisible(false);
            unallocatedPointsFieldHead.setVisible(false);
            unallocatedPointsFieldLa.setVisible(false);
            unallocatedPointsFieldLt.setVisible(false);
            unallocatedPointsFieldCt.setVisible(false);
            unallocatedPointsFieldRt.setVisible(false);
            unallocatedPointsFieldRa.setVisible(false);
            unallocatedPointsFieldLl.setVisible(false);
            unallocatedPointsFieldRl.setVisible(false);
            if (getMech() instanceof TripodMech) {
                unallocatedPointsLabelPatchworkCl.setVisible(false);
                unallocatedPointsFieldCl.setVisible(false);
            }
        }
        valueAllocatedArmor.setText(Integer.toString(getMech().getTotalOArmor()));
        valueUnallocatedArmor.setText(Integer.toString(armorPoints
                - getMech().getTotalOArmor()));
        if (armorPoints != getMech().getTotalOArmor()) {
            valueUnallocatedArmor.setForeground(Color.RED);
            lblUnallocatedArmor.setForeground(Color.RED);
        } else {
            valueUnallocatedArmor.setForeground(Color.BLACK);
            lblUnallocatedArmor.setForeground(Color.BLACK);
        }
        valueCurrentArmor.setText(Integer.toString(armorPoints));
        // Total Possible armor is Internal*2 +3 for the extra 3 armor the head
        // can support.
        int headArmor = 3;
        if (getMech().isSuperHeavy()) {
            headArmor = 4;
        }
        valueMaxArmor.setText(Integer.toString((getMech().getTotalOInternal() * 2)
                + headArmor));
        valueWastedArmor.setText(Integer.toString(wastedArmorPoints));

        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void allocateArmor() {
        double pointsToAllocate = armorPoints;
        int headPoints = 3;
        if (getMech().isSuperHeavy()) {
            headPoints = 4;
        }
        double totalArmor = (getMech().getTotalOInternal() * 2) + headPoints;
        if (pointsToAllocate > totalArmor) {
            pointsToAllocate = totalArmor;
        }
        double percent = pointsToAllocate / totalArmor;
        int headMaxArmor = 9;
        if (getMech().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        // put 5 times the percentage of total possible armor into the head
        int headArmor = (int) Math.min(Math.floor(percent * headMaxArmor * 5), headMaxArmor);
        getMech().initializeArmor(headArmor, Mech.LOC_HEAD);
        pointsToAllocate -= headArmor;
        for (int location = 0; location < getMech().locations(); location++) {
            double IS = (getMech().getInternal(location) * 2);
            double allocate = Math.min(IS * percent, pointsToAllocate);
            switch (location) {
                case Mech.LOC_HEAD:
                    break;
                case Mech.LOC_CT:
                case Mech.LOC_LT:
                case Mech.LOC_RT:
                    double rear = Math.floor(allocate * .25);
                    double front = Math.ceil(allocate * .75);
                    pointsToAllocate -= (int) rear;
                    pointsToAllocate -= (int) front;
                    getMech().initializeArmor((int) front, location);
                    getMech().initializeRearArmor((int) rear, location);
                    break;
                default:
                    getMech().initializeArmor((int) allocate, location);
                    pointsToAllocate -= (int) allocate;
                    break;
            }
        }
        allocateLeftoverPoints(pointsToAllocate);

        if (refresh != null) {
            refresh.refreshStatus();
        }
    }

    /**
     * allocate any leftover points one-by-one
     *
     * @param points
     *            the amount of points left over
     */
    private void allocateLeftoverPoints(double points) {
        int headMaxArmor = 9;
        if (getMech().isSuperHeavy()) {
            headMaxArmor = 12;
        }
        while (points >= 1) {
            // if two or more are left, add armor to symmetrical locations,
            // to torso, legs, arms, in that order
            if (points >= 2) {
                if (((getMech().getOArmor(Mech.LOC_LT) + getMech().getOArmor(Mech.LOC_LT,
                        true)) < (getMech().getOInternal(Mech.LOC_LT) * 2))
                        && ((getMech().getOArmor(Mech.LOC_RT) + getMech().getOArmor(
                                Mech.LOC_RT, true)) < (getMech()
                                .getOInternal(Mech.LOC_RT) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LT) + 1,
                            Mech.LOC_LT);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RT) + 1,
                            Mech.LOC_RT);
                    points -= 2;
                } else if ((getMech().getOArmor(Mech.LOC_LLEG) < (getMech()
                        .getOInternal(Mech.LOC_LLEG) * 2))
                        && (getMech().getOArmor(Mech.LOC_RLEG) < (getMech()
                                .getOInternal(Mech.LOC_RLEG) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LLEG) + 1,
                            Mech.LOC_LLEG);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RLEG) + 1,
                            Mech.LOC_RLEG);
                    points -= 2;
                } else if ((getMech().getOArmor(Mech.LOC_LARM) < (getMech()
                        .getOInternal(Mech.LOC_LARM) * 2))
                        && (getMech().getOArmor(Mech.LOC_RARM) < (getMech()
                                .getOInternal(Mech.LOC_RARM) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LARM) + 1,
                            Mech.LOC_LARM);
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RARM) + 1,
                            Mech.LOC_RARM);
                    points -= 2;
                }
                // otherwise, first add to the head, and then even out uneven
                // allocation
            } else if (getMech().getOArmor(Mech.LOC_HEAD) < headMaxArmor) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_HEAD) + 1,
                        Mech.LOC_HEAD);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LT) < getMech()
                    .getOArmor(Mech.LOC_RT)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LT) + 1,
                        Mech.LOC_LT);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RT) < getMech()
                    .getOArmor(Mech.LOC_LT)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RT) + 1,
                        Mech.LOC_RT);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RARM) < getMech()
                    .getOArmor(Mech.LOC_LARM)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RARM) + 1,
                        Mech.LOC_RARM);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LARM) < getMech()
                    .getOArmor(Mech.LOC_RARM)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LARM) + 1,
                        Mech.LOC_LARM);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_RLEG) < getMech()
                    .getArmor(Mech.LOC_LLEG)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_RLEG) + 1,
                        Mech.LOC_RLEG);
                points--;
            } else if (getMech().getOArmor(Mech.LOC_LLEG) < getMech()
                    .getOArmor(Mech.LOC_RLEG)) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_LLEG) + 1,
                        Mech.LOC_LLEG);
                points--;
                // if nothing is uneven, add to the CT
            } else if (((getMech().getOArmor(Mech.LOC_CT) + getMech().getOArmor(
                    Mech.LOC_CT, true)) < (getMech().getOInternal(Mech.LOC_CT) * 2))) {
                getMech().initializeArmor(getMech().getOArmor(Mech.LOC_CT) + 1,
                        Mech.LOC_CT);
                points--;
            }
            // if only one is left, and head and CT have max, remove one from CT
            // so symmetric locations can get extra, unless they are already at
            // max
            if (points == 1) {
                if ((getMech().getOArmor(Mech.LOC_HEAD) == headMaxArmor)
                        && ((getMech().getOArmor(Mech.LOC_CT) + getMech().getOArmor(
                                Mech.LOC_CT, true)) == (getMech()
                                .getOInternal(Mech.LOC_CT) * 2))) {
                    getMech().initializeArmor(getMech().getOArmor(Mech.LOC_CT) - 1,
                            Mech.LOC_CT);
                    points++;
                }
            }
            // if all locations have max, return
            boolean toReturn = true;
            for (int location = 0; location < getMech().locations(); location++) {
                double is = (getMech().getInternal(location) * 2);
                switch (location) {
                    case Mech.LOC_HEAD:
                        int headPoints = 3;
                        if (getMech().isSuperHeavy()) {
                            headPoints = 4;
                        }
                        if ((is + headPoints) > getMech().getOArmor(location)) {
                            toReturn = false;
                        }
                        break;
                    case Mech.LOC_CT:
                    case Mech.LOC_LT:
                    case Mech.LOC_RT:
                        if (is > (getMech().getOArmor(location) + getMech().getOArmor(
                                location, true))) {
                            toReturn = false;
                        }
                        break;
                    default:
                        if (is > getMech().getOArmor(location)) {
                            toReturn = false;
                        }
                        break;
                }
            }
            if (toReturn) {
                return;
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        removeAllListeners();
        
        JSpinner field = (JSpinner) e.getSource();
        boolean isRear = field.equals(ctrArmorField)
                || field.equals(ltrArmorField) || field.equals(rtrArmorField);
        int location = Integer.parseInt(field.getName());
        int maxArmor = getMech().getOInternal(location) * 2;
        int value = (Integer) field.getModel().getValue();        

        // How much armor do we have without the value that was just changed
        int totalArmor = 0;
        for (int loc = 0; loc < getMech().locations(); loc++) {
            if (loc != location) {
                totalArmor += getMech().getOArmor(loc);
                if (getMech().hasRearArmor(loc)) {
                    totalArmor += getMech().getOArmor(loc, true);
                }
            } else if ((loc == location) && getMech().hasRearArmor(loc)) {
                totalArmor += getMech().getOArmor(loc, !isRear);
            }
        }
        
        // Do we have enough armor points to make this change?
        if ((armorPoints - (totalArmor + value)) < 0) {
            // See if we can pull armor from the opposite location
            if (getMech().hasRearArmor(location) 
                    && getMech().getOArmor(location,!isRear) > 0) {
                stealArmorFromOppositeSide(location, isRear);
            } else { // If we can't pull armor, just revert the change and end
                field.setValue(value - 1);
                addAllListeners();
                return;
            }
        }
        
        // If this change would put us over the per-location total, may have to
        // steal a point from the other side
        if (getMech().hasRearArmor(location) 
                && ((value + getMech().getOArmor(location, !isRear)) > maxArmor)) {
            stealArmorFromOppositeSide(location, isRear);
        }
        
        // Update armor in the changed location
        switch (location) {
            case Mech.LOC_CT:
                if (isRear) {
                    getMech().initializeRearArmor(value, location);
                } else {
                    getMech().initializeArmor(value, location);
                }
                break;
            case Mech.LOC_RT:
                if (isRear) {
                    getMech().initializeRearArmor(value, location);
                } else {
                    getMech().initializeArmor(value, location);
                }
                break;
            case Mech.LOC_LT:
                if (isRear) {
                    getMech().initializeRearArmor(value, location);
                } else {
                    getMech().initializeArmor(value, location);
                }
                break;
            default:
                getMech().initializeArmor(value, location);
                break;
        }
        if (getMech().hasPatchworkArmor()) {
            setArmorPoints(getMech().getTotalArmor());
        }
        if (refresh != null) {
            addAllListeners();
            refresh.refreshStructure();
            removeAllListeners();
            refresh.refreshStatus();
        }
        addAllListeners();
    }
    
    /**
     * Given a location and whether it's rear or not, take a point of armor from
     * the opposite side.  That is, given a rear CT location, reduce the front
     * CT by 1 point of armor.
     * 
     * @param location
     * @param isRear
     */
    private void stealArmorFromOppositeSide(int location, boolean isRear) {
        JSpinner opposite = getOppositeSpinner(location, isRear);
        int oppositeValue = (Integer)opposite.getValue();
        // "Steal" armor from the opposite side
        oppositeValue--;
        opposite.setValue(oppositeValue);
        // Make sure the Unit reflects the armor update
        if (!isRear) {
            getMech().initializeRearArmor(oppositeValue, location);
        } else {
            getMech().initializeArmor(oppositeValue, location);
        }
    }
    
    /**
     * For swapping armor between front and rear locations, we need to get the
     * JSpinner that correspondes to the opposite side of a location.  Ie, if
     * we are given the front CT, return the rear CT JSpinner.
     * 
     * @param location
     * @param isRear
     * @return
     */
    private JSpinner getOppositeSpinner(int location, boolean isRear) {
        JSpinner opposite;
        switch (location) {
            case Mech.LOC_CT:
                if (isRear) {
                    opposite = ctArmorField;
                } else {
                    opposite = ctrArmorField;
                }
                break;
            case Mech.LOC_LT:
                if (isRear) {
                    opposite = ltArmorField;
                } else {
                    opposite = ltrArmorField;
                }
                break;
            case Mech.LOC_RT:
                if (isRear) {
                    opposite = rtArmorField;
                } else {
                    opposite = rtrArmorField;
                }
                break;
            default :
                opposite = null;
        }
        return opposite;
    }

    public void setArmorPoints(int points) {
        int headPoints = 3;
        if (getMech().isSuperHeavy()) {
            headPoints = 4;
        }
        int maxArmor = (getMech().getTotalOInternal() * 2) + headPoints;
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
                getMech().getArmorType(0), getMech().getArmorTechLevel(0));
        setArmorPoints((int) Math
                .floor(getMech().getLabArmorTonnage() * armorPerTon));
    }
}