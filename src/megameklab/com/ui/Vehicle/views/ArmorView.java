/*
 * MegaMekLab - Copyright (C) 2009
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Tank;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class ArmorView extends IView implements ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel turretPanel = new JPanel();

    private JPanel frontPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel rearPanel = new JPanel();

    private SpinnerNumberModel frontArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel leftArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel rightArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel rearArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel turretArmorModel = new SpinnerNumberModel();

    private JSpinner frontArmorField = new JSpinner(frontArmorModel);
    private JSpinner leftArmorField = new JSpinner(leftArmorModel);
    private JSpinner rightArmorField = new JSpinner(rightArmorModel);
    private JSpinner rearArmorField = new JSpinner(rearArmorModel);
    private JSpinner turretArmorField = new JSpinner(turretArmorModel);

    private List<JSpinner> armorFieldList = new ArrayList<JSpinner>();

    private JLabel frontArmorMaxLabel = new JLabel();
    private JLabel leftArmorMaxLabel = new JLabel();
    private JLabel rightArmorMaxLabel = new JLabel();
    private JLabel rearArmorMaxLabel = new JLabel();
    private JLabel turretArmorMaxLabel = new JLabel();
    private List<JLabel> armorMaxLabelList = new ArrayList<JLabel>();

    private JLabel currentArmorLabel = new JLabel();
    private JLabel maxArmorLabel = new JLabel();
    private JLabel unallocatedPointsLabel = new JLabel("Unallocated:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsField = new JLabel();

    private RefreshListener refresh;

    public ArmorView(Tank unit) {

        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        frontPanel.setLayout(new BoxLayout(frontPanel, BoxLayout.Y_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rearPanel.setLayout(new BoxLayout(rearPanel, BoxLayout.Y_AXIS));

        mainPanel.add(topPanel);

        frontPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        topPanel.add(frontPanel);

        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        middlePanel.add(rightPanel);
        middlePanel.add(leftPanel);
        mainPanel.add(middlePanel);

        rearPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        bottomPanel.add(rearPanel);
        mainPanel.add(bottomPanel);

        frontArmorField.setName(Integer.toString(Tank.LOC_FRONT));
        leftArmorField.setName(Integer.toString(Tank.LOC_LEFT));
        rightArmorField.setName(Integer.toString(Tank.LOC_RIGHT));
        rearArmorField.setName(Integer.toString(Tank.LOC_REAR));
        turretArmorField.setName(Integer.toString(Tank.LOC_TURRET));

        armorFieldList.add(frontArmorField);
        armorFieldList.add(leftArmorField);
        armorFieldList.add(rightArmorField);
        armorFieldList.add(rearArmorField);
        armorFieldList.add(turretArmorField);

        Dimension size = new Dimension(35, 20);
        for (JSpinner spinner : armorFieldList) {
            spinner.setToolTipText("Front Armor");
            spinner.setSize(size);
            spinner.setMaximumSize(size);
            spinner.setPreferredSize(size);
            spinner.setMinimumSize(size);
        }

        armorMaxLabelList.add(frontArmorMaxLabel);
        armorMaxLabelList.add(leftArmorMaxLabel);
        armorMaxLabelList.add(rightArmorMaxLabel);
        armorMaxLabelList.add(rearArmorMaxLabel);
        armorMaxLabelList.add(turretArmorMaxLabel);

        Dimension labelSize = new Dimension(15, 20);
        for (JLabel label : armorMaxLabelList) {
            label.setSize(labelSize);
            label.setMaximumSize(labelSize);
            label.setPreferredSize(labelSize);
            label.setMinimumSize(labelSize);
        }

        addAllListeners();

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        JPanel masterPanel;

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {

                switch (location) {
                case Tank.LOC_FRONT:
                    masterPanel = new JPanel();
                    masterPanel.add(frontArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(frontArmorMaxLabel);
                    frontPanel.add(new JLabel(unit.getLocationName(location)));
                    frontPanel.add(masterPanel);
                    break;
                case Tank.LOC_REAR:
                    masterPanel = new JPanel();
                    masterPanel.add(rearArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(rearArmorMaxLabel);
                    rearPanel.add(new JLabel(unit.getLocationName(location)));
                    rearPanel.add(masterPanel);
                    break;
                case Tank.LOC_TURRET:
                    masterPanel = new JPanel();
                    masterPanel.add(turretArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(turretArmorMaxLabel);
                    turretPanel.add(new JLabel(unit.getLocationName(location)));
                    turretPanel.add(masterPanel);
                    break;
                case Tank.LOC_LEFT:
                    masterPanel = new JPanel();
                    masterPanel.add(leftArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(leftArmorMaxLabel);
                    leftPanel.add(new JLabel(unit.getLocationName(location)));
                    leftPanel.add(masterPanel);
                    break;
                case Tank.LOC_RIGHT:
                    masterPanel = new JPanel();
                    masterPanel.add(rightArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(rightArmorMaxLabel);
                    rightPanel.add(new JLabel(unit.getLocationName(location)));
                    rightPanel.add(masterPanel);
                    break;
                }
            }
        }

        this.add(mainPanel);
        this.add(turretPanel);

        JPanel totalArmorPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel pointsPanel = new JPanel();

        totalArmorPanel.setLayout(new BoxLayout(totalArmorPanel, BoxLayout.Y_AXIS));
        headerPanel.add(new JLabel("Current/Maximum Armor"));
        bottomPanel.add(currentArmorLabel);
        bottomPanel.add(new JLabel("/", SwingConstants.TRAILING));
        bottomPanel.add(maxArmorLabel);

        unallocatedPointsField.setHorizontalAlignment(SwingConstants.LEADING);
        pointsPanel.add(unallocatedPointsLabel);
        pointsPanel.add(unallocatedPointsField);

        totalArmorPanel.add(headerPanel);
        totalArmorPanel.add(bottomPanel);
        totalArmorPanel.add(pointsPanel);

        this.add(totalArmorPanel);
        // refresh();
    }

    private void addAllListeners() {
        for (JSpinner spinner : armorFieldList) {
            spinner.addChangeListener(this);
        }
    }

    private void removeAllListeners() {
        for (JSpinner spinner : armorFieldList) {
            spinner.removeChangeListener(this);
        }
    }

    public void refresh() {
        removeAllListeners();
        for (int location = 0; location < unit.locations(); location++) {

            int maxArmor = unit.getOInternal(location) * 2;
            switch (location) {
            case Tank.LOC_FRONT:
                frontArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                frontArmorModel.setMaximum(maxArmor);
                frontArmorModel.setStepSize(1);
                frontArmorModel.setMinimum(0);
                frontArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Tank.LOC_REAR:
                rearArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                rearArmorModel.setMaximum(maxArmor);
                rearArmorModel.setStepSize(1);
                rearArmorModel.setMinimum(0);
                rearArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Tank.LOC_TURRET:
                turretArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                turretArmorModel.setMaximum(maxArmor);
                turretArmorModel.setStepSize(1);
                turretArmorModel.setMinimum(0);
                turretArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Tank.LOC_LEFT:
                leftArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                leftArmorModel.setMaximum(maxArmor);
                leftArmorModel.setStepSize(1);
                leftArmorModel.setMinimum(0);
                leftArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Tank.LOC_RIGHT:
                rightArmorModel.setValue(Math.min(maxArmor, unit.getArmor(location)));
                rightArmorModel.setMaximum(maxArmor);
                rightArmorModel.setStepSize(1);
                rightArmorModel.setMinimum(0);
                rightArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            }
        }

        currentArmorLabel.setText(Integer.toString(unit.getTotalOArmor()));
        // Total Possible armor is Internal*2 +3 for the extra 3 armor the head
        // can support.
        maxArmorLabel.setText(Integer.toString((unit.getTotalOInternal() * 2) + 3));
        // unallocated armorpoints
        unallocatedPointsField.setText(Integer.toString(UnitUtil.getArmorPoints(unit, unit.getArmorWeight()) - unit.getTotalOArmor()));
        addAllListeners();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void allocateArmor(double tons) {
        double pointsToAllocate = UnitUtil.getArmorPoints(unit, tons);
        double totalArmor = (unit.getTotalOInternal() * 2) + 3;
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
            break;
        }
        allocateLeftoverPoints(pointsToAllocate);

        refresh();
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
        /*
         * while (points >= 1) { // if two or more are left, add armor to
         * symmetrical locations, // to torso, legs, arms, in that order if
         * (points >= 2) { if ((unit.getOArmor(Tank.LOC_LT) +
         * unit.getOArmor(Tank.LOC_LT, true) < (unit.getOInternal(Tank.LOC_LT)
         * 2)) && (unit.getOArmor(Tank.LOC_RT) + unit.getOArmor(Tank.LOC_RT,
         * true) < (unit.getOInternal(Tank.LOC_RT) 2))) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_LT) + 1, Tank.LOC_LT);
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_RT) + 1, Tank.LOC_RT);
         * points -= 2; } else if ((unit.getOArmor(Tank.LOC_LLEG) <
         * (unit.getOInternal(Tank.LOC_LLEG) 2)) &&
         * (unit.getOArmor(Tank.LOC_RLEG) < (unit.getOInternal(Tank.LOC_RLEG)
         * 2))) { unit.initializeArmor(unit.getOArmor(Tank.LOC_LLEG) + 1,
         * Tank.LOC_LLEG); unit.initializeArmor(unit.getOArmor(Tank.LOC_RLEG) +
         * 1, Tank.LOC_RLEG); points -= 2; } else if
         * ((unit.getOArmor(Tank.LOC_LARM) < (unit.getOInternal(Tank.LOC_LARM)
         * 2)) && (unit.getOArmor(Tank.LOC_RARM) <
         * (unit.getOInternal(Tank.LOC_RARM) 2))) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_LARM) + 1,
         * Tank.LOC_LARM); unit.initializeArmor(unit.getOArmor(Tank.LOC_RARM) +
         * 1, Tank.LOC_RARM); points -= 2; } // otherwise, first add to the
         * head, and then even out uneven // allocation } else if
         * (unit.getOArmor(Tank.LOC_HEAD) < 9) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_HEAD) + 1,
         * Tank.LOC_HEAD); points--; } else if (unit.getOArmor(Tank.LOC_LT) <
         * unit.getOArmor(Tank.LOC_RT)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_LT) + 1, Tank.LOC_LT);
         * points--; } else if (unit.getOArmor(Tank.LOC_RT) <
         * unit.getOArmor(Tank.LOC_LT)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_RT) + 1, Tank.LOC_RT);
         * points--; } else if (unit.getOArmor(Tank.LOC_RARM) <
         * unit.getOArmor(Tank.LOC_LARM)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_RARM) + 1,
         * Tank.LOC_RARM); points--; } else if (unit.getOArmor(Tank.LOC_LARM) <
         * unit.getOArmor(Tank.LOC_RARM)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_LARM) + 1,
         * Tank.LOC_LARM); points--; } else if (unit.getOArmor(Tank.LOC_RLEG) <
         * unit.getArmor(Tank.LOC_LLEG)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_RLEG) + 1,
         * Tank.LOC_RLEG); points--; } else if (unit.getOArmor(Tank.LOC_LLEG) <
         * unit.getOArmor(Tank.LOC_RLEG)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_LLEG) + 1,
         * Tank.LOC_LLEG); points--; // if nothing is uneven, add to the CT }
         * else if ((unit.getOArmor(Tank.LOC_CT) + unit.getOArmor(Tank.LOC_CT,
         * true) < (unit.getOInternal(Tank.LOC_CT) 2))) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_CT) + 1, Tank.LOC_CT);
         * points--; } // if only one is left, and head and CT have max, remove
         * one from CT // so symmetric locations can get extra, unless they are
         * already at // max if (points == 1) { if
         * ((unit.getOArmor(Tank.LOC_HEAD) == 9) &&
         * ((unit.getOArmor(Tank.LOC_CT) + unit.getOArmor(Tank.LOC_CT, true)) ==
         * unit.getOInternal(Tank.LOC_CT) 2)) {
         * unit.initializeArmor(unit.getOArmor(Tank.LOC_CT) - 1, Tank.LOC_CT);
         * points++; } } // if all locations have max, return boolean toReturn =
         * true; for (int location = 0; location < unit.locations(); location++)
         * { double is = (unit.getInternal(location) 2); if (is >
         * unit.getOArmor(location)) { toReturn = false; } } if (toReturn) {
         * return; } }
         */
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        int location = Integer.parseInt(field.getName());
        int value = (Integer) field.getModel().getValue();
        unit.initializeArmor(value, location);
        if (refresh != null) {
            refresh.refreshStatus();
        }
        refresh();
    }
}