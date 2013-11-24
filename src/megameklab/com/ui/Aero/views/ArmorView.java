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

package megameklab.com.ui.Aero.views;

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

import megamek.common.Aero;
import megamek.common.EquipmentType;
import megamek.common.verifier.TestAero;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

public class ArmorView extends IView implements ChangeListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private JPanel nosePanel = new JPanel();
    private JPanel lWingPanel = new JPanel();
    private JPanel rWingPanel = new JPanel();
    private JPanel aftPanel = new JPanel();
    

    public SpinnerNumberModel noseArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel lwArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel rwArmorModel = new SpinnerNumberModel();
    public SpinnerNumberModel aftArmorModel = new SpinnerNumberModel();

    private JSpinner noseArmorField = new JSpinner(noseArmorModel);
    private JSpinner lwArmorField = new JSpinner(lwArmorModel);
    private JSpinner rwArmorField = new JSpinner(rwArmorModel);
    private JSpinner aftArmorField = new JSpinner(aftArmorModel);

    private List<JSpinner> armorFieldList = new ArrayList<JSpinner>();

    private JLabel noseArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel lwArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel rwArmorMaxLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel aftArmorMaxLabel = new JLabel("", SwingConstants.CENTER);

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

    private int armorPoints;
    private int wastedArmorPoints;

    private JButton allocateArmorButton = new JButton("Auto-Allocate Armor");

    private RefreshListener refresh;

    public ArmorView(Aero unit) {
        super(unit);

        setLayout(new GridLayout(1, 1));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        nosePanel.setLayout(new BoxLayout(nosePanel, BoxLayout.Y_AXIS));
        rWingPanel.setLayout(new BoxLayout(rWingPanel, BoxLayout.Y_AXIS));
        lWingPanel.setLayout(new BoxLayout(lWingPanel, BoxLayout.Y_AXIS));
        aftPanel.setLayout(new BoxLayout(aftPanel, BoxLayout.Y_AXIS));

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(nosePanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        mainPanel.add(lWingPanel, gbc);
        gbc.gridx = 1;
        mainPanel.add(Box.createHorizontalStrut(nosePanel.getWidth()), gbc);
        gbc.gridx = 2;
        mainPanel.add(rWingPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        mainPanel.add(aftPanel, gbc);

        noseArmorField.setName(Integer.toString(Aero.LOC_NOSE));
        rwArmorField.setName(Integer.toString(Aero.LOC_RWING));
        lwArmorField.setName(Integer.toString(Aero.LOC_LWING));
        aftArmorField.setName(Integer.toString(Aero.LOC_AFT));
        
        armorFieldList.add(noseArmorField);
        armorFieldList.add(rwArmorField);
        armorFieldList.add(lwArmorField);
        armorFieldList.add(aftArmorField);


        Dimension size = new Dimension(40, 25);
        for (JSpinner spinner : armorFieldList) {
            spinner.setToolTipText("Armor");
            // you don't set the size of the jspinner, but rather its internal
            // textfield
            ((JSpinner.DefaultEditor) spinner.getEditor()).setSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMaximumSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor())
                    .setPreferredSize(size);
            ((JSpinner.DefaultEditor) spinner.getEditor()).setMinimumSize(size);
        }

        armorMaxLabelList.add(noseArmorMaxLabel);
        armorMaxLabelList.add(lwArmorMaxLabel);
        armorMaxLabelList.add(rwArmorMaxLabel);
        armorMaxLabelList.add(aftArmorMaxLabel);

        Dimension labelSize = new Dimension(80, 20);
        for (JLabel label : armorMaxLabelList) {
            label.setSize(labelSize);
            label.setMaximumSize(labelSize);
            label.setPreferredSize(labelSize);
            label.setMinimumSize(labelSize);
        }

        addAllListeners();

        JPanel topPanel;

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {

                switch (location) {
                    case Aero.LOC_NOSE:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(noseArmorField);
                        topPanel.add(noseArmorMaxLabel);
                        nosePanel.add(topPanel);
                        nosePanel.setBorder(BorderFactory.createTitledBorder(
                                null, unit.getLocationAbbr(location),
                                TitledBorder.TOP,
                                TitledBorder.DEFAULT_POSITION));
                        break;
                    case Aero.LOC_LWING:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(lwArmorField);
                        topPanel.add(lwArmorMaxLabel);
                        lWingPanel.add(topPanel);
                        lWingPanel.setBorder(BorderFactory.createTitledBorder(
                                null, unit.getLocationAbbr(location),
                                TitledBorder.TOP, 
                                TitledBorder.DEFAULT_POSITION));
                        break;
                    case Aero.LOC_RWING:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(rwArmorField);
                        topPanel.add(rwArmorMaxLabel);
                        rWingPanel.add(topPanel);
                        rWingPanel.setBorder(BorderFactory.createTitledBorder(
                                null, unit.getLocationAbbr(location),
                                TitledBorder.TOP, 
                                TitledBorder.DEFAULT_POSITION));
                        break;
                    case Aero.LOC_AFT:
                        topPanel = new JPanel(new GridLayout(2, 0));
                        topPanel.add(aftArmorField);                        
                        topPanel.add(aftArmorMaxLabel);
                        aftPanel.add(topPanel);
                        aftPanel.setBorder(BorderFactory.createTitledBorder(
                                null, unit.getLocationAbbr(location),
                                TitledBorder.TOP, 
                                TitledBorder.DEFAULT_POSITION));                        
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
        removeAllListeners();
        for (int location = 0; location < unit.locations(); location++) {

            int maxArmor = TestAero.maxArmorPoints(unit, unit.getWeight());
            switch (location) {
                case Aero.LOC_NOSE:
                    noseArmorModel.setValue(
                            Math.min(maxArmor, unit.getArmor(location)));
                    if (isFullyAllocated()) {
                        noseArmorModel.setMaximum(
                                (Integer) noseArmorModel.getValue());
                    } else {
                        noseArmorModel.setMaximum(maxArmor);
                    }
                    noseArmorModel.setStepSize(1);
                    noseArmorModel.setMinimum(0);
                    noseArmorMaxLabel.setText("max: " + maxArmor);
                    break;
                case Aero.LOC_LWING:
                    lwArmorModel.setValue(
                            Math.min(maxArmor, unit.getArmor(location)));
                    if (isFullyAllocated()) {
                        lwArmorModel.setMaximum(
                                (Integer) lwArmorModel.getValue());
                    } else {
                        lwArmorModel.setMaximum(maxArmor);
                    }
                    lwArmorModel.setStepSize(1);
                    lwArmorModel.setMinimum(0);
                    lwArmorMaxLabel.setText("max: " + maxArmor);
                    break;
                case Aero.LOC_RWING:
                    rwArmorModel.setValue(
                            Math.min(maxArmor, unit.getArmor(location)));
                    if (isFullyAllocated()) {
                        rwArmorModel.setMaximum(
                                (Integer) rwArmorModel.getValue());
                    } else {
                        rwArmorModel.setMaximum(maxArmor);
                    }
                    rwArmorModel.setStepSize(1);
                    rwArmorModel.setMinimum(0);
                    rwArmorMaxLabel.setText("max: " + maxArmor);
                    break;
                case Aero.LOC_AFT:
                    aftArmorModel.setValue(
                            Math.min(maxArmor, unit.getArmor(location)));
                    if (isFullyAllocated()) {
                        aftArmorModel.setMaximum(
                                (Integer) aftArmorModel.getValue());
                    } else {
                        aftArmorModel.setMaximum(maxArmor);
                    }
                    aftArmorModel.setStepSize(1);
                    aftArmorModel.setMinimum(0);
                    aftArmorMaxLabel.setText("max: " + maxArmor);
                    break;
            }
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
        valueMaxArmor.setText("" + TestAero.maxArmorPoints(
                    unit, unit.getWeight()));
        valueWastedArmor.setText(Integer.toString(wastedArmorPoints));

        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void allocateArmor() {
        double pointsToAllocate = armorPoints;
        double totalArmor = TestAero.maxArmorPoints(
                unit, unit.getWeight());
        if (pointsToAllocate > totalArmor) {
            pointsToAllocate = totalArmor;
        }
        double percent = pointsToAllocate / totalArmor;
        // put 5 times the percentage of total possible armor into the head
        for (int location = 0; location < unit.locations(); location++) {
            double IS = (unit.getInternal(location) * 2);
            double allocate = Math.min(IS * percent, pointsToAllocate);
            unit.initializeArmor((int) allocate, location);
            pointsToAllocate -= (int) allocate;
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
        int [] locs = 
            {Aero.LOC_NOSE, Aero.LOC_AFT, Aero.LOC_LWING, Aero.LOC_RWING};
        
        int locIdx = 0;
        while (points > 0){
            int armor = unit.getArmor(locs[locIdx]) + 1;
            unit.initializeArmor(armor, locs[locIdx]);
            points--;
            locIdx = (locIdx + 1) % locs.length;            
        }
    }

    public void stateChanged(ChangeEvent e) {
        removeAllListeners();
        JSpinner field = (JSpinner) e.getSource();
        int location = Integer.parseInt(field.getName());
        int value = (Integer) field.getModel().getValue();
        unit.initializeArmor(value, location);
        if (unit.hasPatchworkArmor()) {
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

    public void setArmorPoints(int points) {
        int maxArmor = TestAero.maxArmorPoints(unit, unit.getWeight());
        wastedArmorPoints = Math.max(points - maxArmor, 0);
        armorPoints = Math.min(maxArmor, points);
    }

    private boolean isFullyAllocated() {
        if (!unit.hasPatchworkArmor()) {
            return armorPoints == unit.getTotalOArmor();
        }
        return false;
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