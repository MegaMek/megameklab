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

package megameklab.com.ui.BattleArmor.views;

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

import megamek.common.BattleArmor;
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
    private List<JSpinner> armorFieldList = new ArrayList<JSpinner>();

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
    private List<JLabel> armorMaxLabelList = new ArrayList<JLabel>();

    private JLabel currentArmorLabel = new JLabel();
    private JLabel maxArmorLabel = new JLabel();
    private JLabel unallocatedPointsLabel = new JLabel("Unallocated:", SwingConstants.TRAILING);
    private JLabel unallocatedPointsField = new JLabel();

    private RefreshListener refresh;

    public ArmorView(BattleArmor unit) {

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

        armorFieldList.add(laArmorField);
        armorFieldList.add(raArmorField);
        armorFieldList.add(llArmorField);
        armorFieldList.add(rlArmorField);
        armorFieldList.add(ltArmorField);
        armorFieldList.add(rtArmorField);
        armorFieldList.add(ctArmorField);
        armorFieldList.add(hdArmorField);
        armorFieldList.add(ltrArmorField);
        armorFieldList.add(ctrArmorField);
        armorFieldList.add(rtrArmorField);

        Dimension size = new Dimension(35, 20);
        for (JSpinner spinner : armorFieldList) {
            spinner.setToolTipText("Front Armor");
            spinner.setSize(size);
            spinner.setMaximumSize(size);
            spinner.setPreferredSize(size);
            spinner.setMinimumSize(size);
        }
        rtrArmorField.setToolTipText("Rear Armor");
        ltrArmorField.setToolTipText("Rear Armor");
        ctrArmorField.setToolTipText("Rear Armor");

        armorMaxLabelList.add(laArmorMaxLabel);
        armorMaxLabelList.add(raArmorMaxLabel);
        armorMaxLabelList.add(llArmorMaxLabel);
        armorMaxLabelList.add(rlArmorMaxLabel);
        armorMaxLabelList.add(ltArmorMaxLabel);
        armorMaxLabelList.add(rtArmorMaxLabel);
        armorMaxLabelList.add(ctArmorMaxLabel);
        armorMaxLabelList.add(ltrArmorMaxLabel);
        armorMaxLabelList.add(ctrArmorMaxLabel);
        armorMaxLabelList.add(rtrArmorMaxLabel);

        Dimension labelSize = new Dimension(15, 20);
        for (JLabel label : armorMaxLabelList) {
            label.setSize(labelSize);
            label.setMaximumSize(labelSize);
            label.setPreferredSize(labelSize);
            label.setMinimumSize(labelSize);
        }

        addAllListeners();

        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        JPanel masterPanel;

        rearPanel.add(ltrPanel);
        rearPanel.add(ctrPanel);
        rearPanel.add(rtrPanel);

        this.add(mainPanel);
        this.add(rearPanel);

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
        double pointsToAllocate = 0;
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
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner field = (JSpinner) e.getSource();
        int location = Integer.parseInt(field.getName());
        int value = (Integer) field.getModel().getValue();
        if (refresh != null) {
            refresh.refreshStatus();
        }
        refresh();
    }
}