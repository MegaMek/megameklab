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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.Mech;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class ArmorView extends IView implements ChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private JPanel headPanel = new JPanel();
    private JPanel torsoPanel = new JPanel();
    private JPanel legPanel = new JPanel();
    private JPanel rearPanel = new JPanel();

    private JPanel laPanel = new JPanel();
    private JPanel raPanel = new JPanel();
    private JPanel llPanel = new JPanel();
    private JPanel rlPanel = new JPanel();
    private JPanel ltPanel = new JPanel();
    private JPanel rtPanel = new JPanel();
    private JPanel ctPanel = new JPanel();

    private JPanel ltrPanel = new JPanel();
    private JPanel ctrPanel = new JPanel();
    private JPanel rtrPanel = new JPanel();

    private SpinnerNumberModel laArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel raArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel llArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel rlArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel ltArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel rtArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel ctArmorModel = new SpinnerNumberModel();
    private SpinnerNumberModel hdArmorModel = new SpinnerNumberModel();

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

    private JSpinner rtrArmorField = new JSpinner(rtrArmorModel);
    private JSpinner ltrArmorField = new JSpinner(ltrArmorModel);
    private JSpinner ctrArmorField = new JSpinner(ctrArmorModel);

    private JLabel laArmorMaxLabel = new JLabel();
    private JLabel raArmorMaxLabel = new JLabel();
    private JLabel llArmorMaxLabel = new JLabel();
    private JLabel rlArmorMaxLabel = new JLabel();
    private JLabel ltArmorMaxLabel = new JLabel();
    private JLabel rtArmorMaxLabel = new JLabel();
    private JLabel ctArmorMaxLabel = new JLabel();

    private JLabel ltrArmorMaxLabel = new JLabel();
    private JLabel rtrArmorMaxLabel = new JLabel();
    private JLabel ctrArmorMaxLabel = new JLabel();

    private JLabel currentArmorLabel = new JLabel();
    private JLabel maxArmorLabel = new JLabel();
    private JLabel unallocatedPointsLabel = new JLabel("Unallocated:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsField = new JLabel();

    private RefreshListener refresh;

    public ArmorView(Mech unit) {

        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        laPanel.setLayout(new BoxLayout(laPanel, BoxLayout.Y_AXIS));
        raPanel.setLayout(new BoxLayout(raPanel, BoxLayout.Y_AXIS));
        ltPanel.setLayout(new BoxLayout(ltPanel, BoxLayout.Y_AXIS));
        ctPanel.setLayout(new BoxLayout(ctPanel, BoxLayout.Y_AXIS));
        rtPanel.setLayout(new BoxLayout(rtPanel, BoxLayout.Y_AXIS));

        rtrPanel.setLayout(new BoxLayout(rtrPanel, BoxLayout.Y_AXIS));
        ctrPanel.setLayout(new BoxLayout(ctrPanel, BoxLayout.Y_AXIS));
        ltrPanel.setLayout(new BoxLayout(ltrPanel, BoxLayout.Y_AXIS));

        mainPanel.add(headPanel);

        laPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        raPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        ltPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rtPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        ctPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        ltrPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rtrPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        ctrPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        torsoPanel.add(laPanel);
        torsoPanel.add(ltPanel);
        torsoPanel.add(ctPanel);
        torsoPanel.add(rtPanel);
        torsoPanel.add(raPanel);
        mainPanel.add(torsoPanel);

        legPanel.add(llPanel);
        legPanel.add(rlPanel);
        mainPanel.add(legPanel);

        laArmorField.setName(Integer.toString(Mech.LOC_LARM));
        raArmorField.setName(Integer.toString(Mech.LOC_RARM));
        llArmorField.setName(Integer.toString(Mech.LOC_LLEG));
        rlArmorField.setName(Integer.toString(Mech.LOC_RLEG));
        ltArmorField.setName(Integer.toString(Mech.LOC_LT));
        rtArmorField.setName(Integer.toString(Mech.LOC_RT));
        ctArmorField.setName(Integer.toString(Mech.LOC_CT));
        hdArmorField.setName(Integer.toString(Mech.LOC_HEAD));
        rtrArmorField.setName(Integer.toString(Mech.LOC_RT));
        ltrArmorField.setName(Integer.toString(Mech.LOC_LT));
        ctrArmorField.setName(Integer.toString(Mech.LOC_CT));

        Dimension size = new Dimension(38, 20);
        laArmorField.setPreferredSize(size);
        raArmorField.setPreferredSize(size);
        llArmorField.setPreferredSize(size);
        rlArmorField.setPreferredSize(size);
        ltArmorField.setPreferredSize(size);
        rtArmorField.setPreferredSize(size);
        ctArmorField.setPreferredSize(size);
        hdArmorField.setPreferredSize(size);
        rtrArmorField.setPreferredSize(size);
        ltrArmorField.setPreferredSize(size);
        ctrArmorField.setPreferredSize(size);
        laArmorField.setMaximumSize(size);
        raArmorField.setMaximumSize(size);
        llArmorField.setMaximumSize(size);
        rlArmorField.setMaximumSize(size);
        ltArmorField.setMaximumSize(size);
        rtArmorField.setMaximumSize(size);
        ctArmorField.setMaximumSize(size);
        hdArmorField.setMaximumSize(size);
        rtrArmorField.setMaximumSize(size);
        ltrArmorField.setMaximumSize(size);
        ctrArmorField.setMaximumSize(size);
        laArmorField.setMinimumSize(size);
        raArmorField.setMinimumSize(size);
        llArmorField.setMinimumSize(size);
        rlArmorField.setMinimumSize(size);
        ltArmorField.setMinimumSize(size);
        rtArmorField.setMinimumSize(size);
        ctArmorField.setMinimumSize(size);
        hdArmorField.setMinimumSize(size);
        rtrArmorField.setMinimumSize(size);
        ltrArmorField.setMinimumSize(size);
        ctrArmorField.setMinimumSize(size);

        laArmorField.addChangeListener(this);
        raArmorField.addChangeListener(this);
        llArmorField.addChangeListener(this);
        rlArmorField.addChangeListener(this);
        ltArmorField.addChangeListener(this);
        rtArmorField.addChangeListener(this);
        ctArmorField.addChangeListener(this);
        hdArmorField.addChangeListener(this);
        rtrArmorField.addChangeListener(this);
        ltrArmorField.addChangeListener(this);
        ctrArmorField.addChangeListener(this);

        laArmorField.setToolTipText("Front Armor");
        raArmorField.setToolTipText("Front Armor");
        llArmorField.setToolTipText("Front Armor");
        rlArmorField.setToolTipText("Front Armor");
        ltArmorField.setToolTipText("Front Armor");
        rtArmorField.setToolTipText("Front Armor");
        ctArmorField.setToolTipText("Front Armor");
        hdArmorField.setToolTipText("Front Armor");
        rtrArmorField.setToolTipText("Rear Armor");
        ltrArmorField.setToolTipText("Rear Armor");
        ctrArmorField.setToolTipText("Rear Armor");

        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        JPanel masterPanel;

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {

                switch (location) {
                case Mech.LOC_HEAD:
                    masterPanel = new JPanel();
                    JPanel topPanel = new JPanel();
                    JPanel bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(hdArmorField);
                    topPanel.add(new JLabel("/ 9", SwingConstants.TRAILING));
                    masterPanel.add(new JLabel(unit.getLocationName(location)));
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(masterPanel);
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    break;
                case Mech.LOC_LARM:
                    masterPanel = new JPanel();
                    masterPanel.add(laArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(laArmorMaxLabel);
                    laPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    laPanel.add(masterPanel);
                    break;
                case Mech.LOC_RARM:
                    masterPanel = new JPanel();
                    masterPanel.add(raArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(raArmorMaxLabel);
                    raPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    raPanel.add(masterPanel);
                    break;
                case Mech.LOC_CT:
                    masterPanel = new JPanel();
                    masterPanel.add(ctArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(ctArmorMaxLabel);
                    ctPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    ctPanel.add(masterPanel);
                    masterPanel = new JPanel();
                    masterPanel.add(ctrArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(ctrArmorMaxLabel);
                    ctrPanel.add(new JPanel());
                    ctrPanel.add(new JPanel());
                    ctrPanel.add(new JLabel(unit.getLocationAbbr(location)+"r"));
                    ctrPanel.add(masterPanel);
                    ctrPanel.add(new JPanel());
                    break;
                case Mech.LOC_LT:
                    masterPanel = new JPanel();
                    masterPanel.add(ltArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(ltArmorMaxLabel);
                    ltPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    ltPanel.add(masterPanel);
                    masterPanel = new JPanel();
                    masterPanel.add(ltrArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(ltrArmorMaxLabel);
                    ltrPanel.add(new JPanel());
                    ltrPanel.add(new JLabel(unit.getLocationAbbr(location)+"r"));
                    ltrPanel.add(masterPanel);
                    ltrPanel.add(new JPanel());
                    break;
                case Mech.LOC_RT:
                    masterPanel = new JPanel();
                    masterPanel.add(rtArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(rtArmorMaxLabel);
                    rtPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    rtPanel.add(masterPanel);
                    masterPanel = new JPanel();
                    masterPanel.add(rtrArmorField);
                    masterPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    masterPanel.add(rtrArmorMaxLabel);
                    rtrPanel.add(new JPanel());
                    rtrPanel.add(new JLabel(unit.getLocationAbbr(location)+"r"));
                    rtrPanel.add(masterPanel);
                    rtrPanel.add(new JPanel());
                    break;
                case Mech.LOC_LLEG:
                    masterPanel = new JPanel();
                    topPanel = new JPanel();
                    bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(llArmorField);
                    topPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    topPanel.add(llArmorMaxLabel);
                    masterPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    llPanel.add(new JPanel());
                    llPanel.add(new JPanel());
                    llPanel.add(masterPanel);
                    llPanel.add(new JPanel());
                    llPanel.add(new JPanel());
                    break;
                case Mech.LOC_RLEG:
                    masterPanel = new JPanel();
                    topPanel = new JPanel();
                    bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(rlArmorField);
                    topPanel.add(new JLabel("/", SwingConstants.TRAILING));
                    topPanel.add(rlArmorMaxLabel);
                    masterPanel.add(new JLabel(unit.getLocationAbbr(location)));
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    rlPanel.add(new JPanel());
                    rlPanel.add(new JPanel());
                    rlPanel.add(masterPanel);
                    rlPanel.add(new JPanel());
                    rlPanel.add(new JPanel());
                    break;
                }
            }
        }

        rearPanel.add(ltrPanel);
        rearPanel.add(ctrPanel);
        rearPanel.add(rtrPanel);

        this.add(mainPanel);
        this.add(rearPanel);

        JPanel totalArmorPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel pointsPanel = new JPanel();

        totalArmorPanel.setLayout(new BoxLayout(totalArmorPanel,BoxLayout.Y_AXIS));
        headerPanel.add(new JLabel("Current/Maximum Armor"));
        bottomPanel.add(currentArmorLabel);
        bottomPanel.add(new JLabel("/",SwingConstants.TRAILING));
        bottomPanel.add(maxArmorLabel);

        unallocatedPointsField.setHorizontalAlignment(SwingConstants.LEADING);
        pointsPanel.add(unallocatedPointsLabel);
        pointsPanel.add(unallocatedPointsField);

        totalArmorPanel.add(headerPanel);
        totalArmorPanel.add(bottomPanel);
        totalArmorPanel.add(pointsPanel);

        this.add(totalArmorPanel);
        //refresh();
    }

    public void refresh() {

        for (int location = 0; location < unit.locations(); location++) {

            int maxArmor = unit.getOInternal(location) * 2;
            switch (location) {
            case Mech.LOC_HEAD:
                hdArmorModel.setValue(Math.min(9,unit.getArmor(location)));
                hdArmorModel.setMaximum(9);
                hdArmorModel.setStepSize(1);
                hdArmorModel.setMinimum(0);
                break;
            case Mech.LOC_LARM:
                laArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                laArmorModel.setMaximum(maxArmor);
                laArmorModel.setStepSize(1);
                laArmorModel.setMinimum(0);
                laArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_RARM:
                raArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                raArmorModel.setMaximum(maxArmor);
                raArmorModel.setStepSize(1);
                raArmorModel.setMinimum(0);
                raArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_CT:
                ctArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                ctArmorModel.setMaximum(maxArmor);
                ctArmorModel.setStepSize(1);
                ctArmorModel.setMinimum(0);
                ctrArmorModel.setValue(Math.min(unit.getArmor(location, true), maxArmor-unit.getArmor(location)));
                ctrArmorModel.setMaximum(maxArmor-unit.getArmor(location));
                ctrArmorModel.setStepSize(1);
                ctrArmorModel.setMinimum(0);
                ctArmorMaxLabel.setText(Integer.toString(maxArmor));
                ctrArmorMaxLabel.setText(Integer.toString(maxArmor-unit.getArmor(location)));
                break;
            case Mech.LOC_LT:
                ltArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                ltArmorModel.setMaximum(maxArmor);
                ltArmorModel.setStepSize(1);
                ltArmorModel.setMinimum(0);
                ltrArmorModel.setValue(Math.min(unit.getArmor(location, true), maxArmor-unit.getArmor(location)));
                ltrArmorModel.setMaximum(maxArmor-unit.getArmor(location));
                ltrArmorModel.setStepSize(1);
                ltrArmorModel.setMinimum(0);
                ltArmorMaxLabel.setText(Integer.toString(maxArmor));
                ltrArmorMaxLabel.setText(Integer.toString(maxArmor-unit.getArmor(location)));
                break;
            case Mech.LOC_RT:
                rtArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                rtArmorModel.setMaximum(maxArmor);
                rtArmorModel.setStepSize(1);
                rtArmorModel.setMinimum(0);
                rtrArmorModel.setValue(Math.min(unit.getArmor(location, true), maxArmor-unit.getArmor(location)));
                rtrArmorModel.setMaximum(maxArmor-unit.getArmor(location));
                rtrArmorModel.setStepSize(1);
                rtrArmorModel.setMinimum(0);
                rtArmorMaxLabel.setText(Integer.toString(maxArmor));
                rtrArmorMaxLabel.setText(Integer.toString(maxArmor-unit.getArmor(location)));
                break;
            case Mech.LOC_LLEG:
                llArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                llArmorModel.setMaximum(maxArmor);
                llArmorModel.setStepSize(1);
                llArmorModel.setMinimum(0);
                llArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_RLEG:
                rlArmorModel.setValue(Math.min(maxArmor,unit.getArmor(location)));
                rlArmorModel.setMaximum(maxArmor);
                rlArmorModel.setStepSize(1);
                rlArmorModel.setMinimum(0);
                rlArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            }

        }

        currentArmorLabel.setText(Integer.toString(unit.getTotalOArmor()));
        //Total Possible armor is Internal*2 +3 for the extra 3 armor the head can support.
        maxArmorLabel.setText(Integer.toString((unit.getTotalOInternal()*2)+3));
        //unallocated armorpoints
        unallocatedPointsField.setText(Integer.toString(UnitUtil.getArmorPoints(unit, unit.getArmorWeight()) - unit.getTotalOArmor()));
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void allocateArmor(double tons) {
        double pointsToAllocate = UnitUtil.getArmorPoints(unit, tons);
        double totalArmor = (unit.getTotalOInternal()*2)+3;
        if (pointsToAllocate > totalArmor) {
            pointsToAllocate = totalArmor;
        }
        double percent = pointsToAllocate/totalArmor;
        //put 5 times the percentage of total possible armor into the head
        int headArmor = (int)Math.min(Math.floor(percent*9*5), 9);
        unit.initializeArmor(headArmor, Mech.LOC_HEAD);
        pointsToAllocate -= headArmor;
        for (int location = 0; location <= Mech.LOC_LLEG; location++) {
            double IS = (unit.getInternal(location) * 2);
            double allocate = Math.min(IS * percent,pointsToAllocate);
            switch (location) {
            case Mech.LOC_HEAD:
                break;
            case Mech.LOC_CT:
            case Mech.LOC_LT:
            case Mech.LOC_RT:
                double rear = Math.floor(allocate * .25);
                double front = Math.ceil(allocate * .75);
                pointsToAllocate -= (int)rear;
                pointsToAllocate -= (int)front;
                unit.initializeArmor((int)front, location);
                unit.initializeRearArmor((int)rear, location);
                break;
            default:
                unit.initializeArmor((int)allocate, location);
                pointsToAllocate -= (int)allocate;
                break;
            }
        }
        allocateLeftoverPoints(pointsToAllocate);

        refresh();
        if (refresh != null) {
            refresh.refreshStatus();
        }
    }

    /**
     * allocate any leftover points one-by-one
     * @param points the amount of points left over
     */
    private void allocateLeftoverPoints(double points) {
        while (points >= 1) {
            // if two or more are left, add armor to symmetrical locations,
            // to torso, legs, arms, in that order
            if (points >= 2) {
                if ((unit.getOArmor(Mech.LOC_LT) + unit.getOArmor(Mech.LOC_LT, true) < (unit.getOInternal(Mech.LOC_LT) * 2)) &&
                        (unit.getOArmor(Mech.LOC_RT) + unit.getOArmor(Mech.LOC_RT, true) < (unit.getOInternal(Mech.LOC_RT) * 2))) {
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_LT)+1, Mech.LOC_LT);
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_RT)+1, Mech.LOC_RT);
                    points -= 2;
                } else if ((unit.getOArmor(Mech.LOC_LLEG) < (unit.getOInternal(Mech.LOC_LLEG) * 2)) &&
                        (unit.getOArmor(Mech.LOC_RLEG) < (unit.getOInternal(Mech.LOC_RLEG) * 2))) {
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_LLEG)+1, Mech.LOC_LLEG);
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_RLEG)+1, Mech.LOC_RLEG);
                    points -= 2;
                } else if ((unit.getOArmor(Mech.LOC_LARM) < (unit.getOInternal(Mech.LOC_LARM) * 2)) &&
                        (unit.getOArmor(Mech.LOC_RARM) < (unit.getOInternal(Mech.LOC_RARM) * 2))) {
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_LARM)+1, Mech.LOC_LARM);
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_RARM)+1, Mech.LOC_RARM);
                    points -= 2;
                }
            // otherwise, first add to the head, and then even out uneven allocation
            } else if (unit.getOArmor(Mech.LOC_HEAD) < 9) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_HEAD)+1, Mech.LOC_HEAD);
                points--;
            } else if (unit.getOArmor(Mech.LOC_LT) < unit.getOArmor(Mech.LOC_RT)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_LT)+1, Mech.LOC_LT);
                points--;
            } else if (unit.getOArmor(Mech.LOC_RT) < unit.getOArmor(Mech.LOC_LT)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_RT)+1, Mech.LOC_RT);
                points--;
            } else if (unit.getOArmor(Mech.LOC_RARM) < unit.getOArmor(Mech.LOC_LARM)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_RARM)+1, Mech.LOC_RARM);
                points--;
            } else if (unit.getOArmor(Mech.LOC_LARM) < unit.getOArmor(Mech.LOC_RARM)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_LARM)+1, Mech.LOC_LARM);
                points--;
            } else if (unit.getOArmor(Mech.LOC_RLEG) < unit.getArmor(Mech.LOC_LLEG)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_RLEG)+1, Mech.LOC_RLEG);
                points--;
            } else if (unit.getOArmor(Mech.LOC_LLEG) < unit.getOArmor(Mech.LOC_RLEG)) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_LLEG)+1, Mech.LOC_LLEG);
                points--;
            // if nothing is uneven, add to the CT
            } else if ((unit.getOArmor(Mech.LOC_CT) + unit.getOArmor(Mech.LOC_CT, true) < (unit.getOInternal(Mech.LOC_CT) * 2))) {
                unit.initializeArmor(unit.getOArmor(Mech.LOC_CT)+1, Mech.LOC_CT);
                points--;
            }
            // if only one is left, and head and CT have max, remove one from CT
            // so symmetric locations can get extra, unless they are already at max
            if (points == 1) {
                if ((unit.getOArmor(Mech.LOC_HEAD) == 9) &&
                        ((unit.getOArmor(Mech.LOC_CT) + unit.getOArmor(Mech.LOC_CT, true)) == unit.getOInternal(Mech.LOC_CT) * 2)) {
                    unit.initializeArmor(unit.getOArmor(Mech.LOC_CT)-1, Mech.LOC_CT);
                    points++;
                }
            }
            // if all locations have max, return
            boolean toReturn = true;
            for (int location = 0; location <= Mech.LOC_LLEG; location++) {
                double is = (unit.getInternal(location) * 2);
                switch (location) {
                case Mech.LOC_HEAD:
                    if (is+3 > unit.getOArmor(location)) {
                        toReturn = false;
                    }
                    break;
                case Mech.LOC_CT:
                case Mech.LOC_LT:
                case Mech.LOC_RT:
                    if (is > unit.getOArmor(location)+unit.getOArmor(location, true)) {
                        toReturn = false;
                    }
                    break;
                default:
                    if (is > unit.getOArmor(location)) {
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
        JSpinner field = (JSpinner) e.getSource();
        int location = Integer.parseInt(field.getName());
        int value = (Integer)field.getModel().getValue();
        switch (location) {
        case Mech.LOC_CT:
            if (field.equals(ctrArmorField)) {
                unit.initializeRearArmor(value, location);
            } else {
                unit.initializeArmor(value, location);
            }
            break;
        case Mech.LOC_RT:
            if (field.equals(rtrArmorField)) {
                unit.initializeRearArmor(value, location);
            } else {
                unit.initializeArmor(value, location);
            }
            break;
        case Mech.LOC_LT:
            if (field.equals(ltrArmorField)) {
                unit.initializeRearArmor(value, location);
            } else {
                unit.initializeArmor(value, location);
            }
            break;
        default:
            unit.initializeArmor(value, location);
            break;
        }
        if (refresh != null) {
            refresh.refreshStatus();
        }
        refresh();
    }
}