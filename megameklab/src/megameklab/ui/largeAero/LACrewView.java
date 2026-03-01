/*
 * Copyright (C) 2017-2026 The MegaMek Team. All Rights Reserved.
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.client.ui.util.DisplayTextField;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.EntityWeightClass;
import megamek.common.verifier.TestAero;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.AeroVesselBuildListener;

/**
 * Structure tab panel for setting crew levels and quarters, as well as lifeboats and escape pods.
 *
 * @author Neoancient
 */
public class LACrewView extends BuildView implements ActionListener, ChangeListener {
    private final List<AeroVesselBuildListener> listeners = new CopyOnWriteArrayList<>();

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    public void addListener(AeroVesselBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(AeroVesselBuildListener l) {
        listeners.remove(l);
    }

    private final JSpinner spnOfficers = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    // A maximum of 9999 is used to give the spinners enough width
    private final JSpinner spnBaseCrew = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
    private final JSpinner spnGunners = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    private final DisplayTextField lblBayPersonnel = new DisplayTextField();
    private final JSpinner spnMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnBAMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final DisplayTextField lblTotalCrew = new DisplayTextField();
    private final JSpinner spnPassengers = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersStandard = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersFirstClass = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
    private final JSpinner spnQuartersSecondClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersSteerage = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnLifeBoats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnEscapePods = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JButton btnAssignQuarters = new JButton();

    private final JLabel lblBAMarines = createLabel("lblBAMarines", "");
    private final ITechManager techManager;
    private boolean ignoreChangeEvents = false;

    public LACrewView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }

    public void initUI() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = STANDARD_INSETS;

        gbc.gridy = 0;
        leftPanel.add(createLabel(I18N, "lblBaseCrew", "AerospaceCrewView.spnBaseCrew.text"), gbc);
        leftPanel.add(spnBaseCrew, gbc);
        spnBaseCrew.addChangeListener(this);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N, "lblGunners", "AerospaceCrewView.spnGunners.text"), gbc);
        leftPanel.add(spnGunners, gbc);
        spnGunners.addChangeListener(this);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N,
              "lblTotalCrew",
              "AerospaceCrewView.lblTotalCrew.text",
              "AerospaceCrewView.lblTotalCrew.tooltip"), gbc);
        lblTotalCrew.setToolTipText(I18N.getString("AerospaceCrewView.lblTotalCrew.tooltip"));
        leftPanel.add(lblTotalCrew, gbc);
        lblTotalCrew.setHorizontalAlignment(JLabel.RIGHT);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N, "lblOfficers", "AerospaceCrewView.spnOfficers.text"), gbc);
        leftPanel.add(spnOfficers, gbc);
        spnOfficers.addChangeListener(this);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N, "lblBayPersonnel", "AerospaceCrewView.lblBayPersonnel.text"), gbc);
        leftPanel.add(lblBayPersonnel, gbc);
        lblBayPersonnel.setHorizontalAlignment(JLabel.RIGHT);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N, "lblPassengers", "AerospaceCrewView.spnPassengers.text"), gbc);
        leftPanel.add(spnPassengers, gbc);
        spnPassengers.addChangeListener(this);

        gbc.gridy++;
        leftPanel.add(createLabel(I18N, "lblMarines", "AerospaceCrewView.spnMarines.text"), gbc);
        leftPanel.add(spnMarines, gbc);
        spnMarines.addChangeListener(this);

        gbc.gridy++;
        lblBAMarines.setText(I18N.getString("AerospaceCrewView.spnBAMarines.text"));
        leftPanel.add(lblBAMarines, gbc);
        leftPanel.add(spnBAMarines, gbc);
        spnBAMarines.addChangeListener(this);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = STANDARD_INSETS;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JLabel quartersHeader = new JLabel(I18N.getString("AerospaceCrewView.lblQuarters.text")) {
            @Override
            public Dimension getPreferredSize() {
                // make the height align with the other text fields, so the whole grid aligns well
                return new Dimension(super.getPreferredSize().width, lblBayPersonnel.getPreferredSize().height);
            }
        };
        rightPanel.add(quartersHeader, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        gbc.gridy++;
        rightPanel.add(createLabel(I18N, "lblQuartersFirstClass", "AerospaceCrewView.spnQuartersFirstClass.text"), gbc);
        rightPanel.add(spnQuartersFirstClass, gbc);
        spnQuartersFirstClass.addChangeListener(this);

        gbc.gridy++;
        rightPanel.add(createLabel(I18N, "lblQuartersStandard", "AerospaceCrewView.spnQuartersStandard.text"), gbc);
        rightPanel.add(spnQuartersStandard, gbc);
        spnQuartersStandard.addChangeListener(this);

        gbc.gridy++;
        rightPanel.add(createLabel(I18N, "lblQuartersSecondClass", "AerospaceCrewView.spnQuartersSecondClass.text"),
              gbc);
        rightPanel.add(spnQuartersSecondClass, gbc);
        spnQuartersSecondClass.addChangeListener(this);

        gbc.gridy++;
        rightPanel.add(createLabel(I18N, "lblQuartersSteerage", "AerospaceCrewView.spnQuartersSteerage.text"), gbc);
        rightPanel.add(spnQuartersSteerage, gbc);
        spnQuartersSteerage.addChangeListener(this);

        gbc.gridy++;
        gbc.gridwidth = 2;
        btnAssignQuarters.setText(I18N.getString("AerospaceCrewView.btnAssignQuarters.text"));
        btnAssignQuarters.setToolTipText(I18N.getString("AerospaceCrewView.btnAssignQuarters.tooltip"));
        rightPanel.add(btnAssignQuarters, gbc);
        btnAssignQuarters.addActionListener(this);

        gbc.gridy++;
        gbc.gridwidth = 1;
        rightPanel.add(createLabel(I18N, "lblLifeBoats", "AerospaceCrewView.spnLifeBoats.text"), gbc);
        rightPanel.add(spnLifeBoats, gbc);
        spnLifeBoats.addChangeListener(this);

        gbc.gridy++;
        rightPanel.add(createLabel(I18N, "lblEscapePods", "AerospaceCrewView.spnEscapePods.text"), gbc);
        rightPanel.add(spnEscapePods, gbc);
        spnEscapePods.addChangeListener(this);

        setLayout(new GridLayout(1, 2, 10, 0));
        add(leftPanel);
        add(rightPanel);
    }

    public void setFromEntity(Aero aero) {
        int minGunners = TestAero.requiredGunners(aero);
        int minBase = TestAero.minimumBaseCrew(aero);
        int nonBay = aero.getNCrew() - aero.getBayPersonnel();
        ((SpinnerNumberModel) spnBaseCrew.getModel()).setMinimum(minBase);
        ((SpinnerNumberModel) spnGunners.getModel()).setMinimum(minGunners);

        ignoreChangeEvents = true;
        spnOfficers.setValue(aero.getNOfficers());
        spnBaseCrew.setValue(nonBay - aero.getNGunners());
        spnGunners.setValue(aero.getNGunners());
        lblTotalCrew.setText(String.valueOf(nonBay));
        lblBayPersonnel.setText(String.valueOf(aero.getBayPersonnel()));
        spnPassengers.setValue(aero.getNPassenger());
        spnMarines.setValue(aero.getNMarines());

        if (techManager.isLegal(BattleArmor.getConstructionTechAdvancement(EntityWeightClass.WEIGHT_MEDIUM))) {
            spnBAMarines.setValue(aero.getNBattleArmor());
            lblBAMarines.setVisible(true);
            spnBAMarines.setVisible(true);
        } else {
            spnBAMarines.setValue(0);
            lblBAMarines.setVisible(false);
            spnBAMarines.setVisible(false);
        }

        Map<TestAero.Quarters, Integer> sizes = TestAero.Quarters.getQuartersByType(aero);
        spnQuartersFirstClass.setValue(sizes.getOrDefault(TestAero.Quarters.FIRST_CLASS, 0));
        spnQuartersStandard.setValue(sizes.getOrDefault(TestAero.Quarters.STANDARD, 0));
        spnQuartersSecondClass.setValue(sizes.getOrDefault(TestAero.Quarters.SECOND_CLASS, 0));
        spnQuartersSteerage.setValue(sizes.getOrDefault(TestAero.Quarters.STEERAGE, 0));

        spnLifeBoats.setValue(aero.getLifeBoats());
        spnEscapePods.setValue(aero.getEscapePods());
        ignoreChangeEvents = false;

        // If we do not meet the minimum, set the values and trigger an event that will update the vessel.
        if (aero.getNGunners() < minGunners) {
            spnGunners.setValue(minGunners);
        }
        if (nonBay - aero.getNGunners() < minBase) {
            spnBaseCrew.setValue(minBase);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!ignoreChangeEvents) {
            if (e.getSource() == spnBaseCrew) {
                listeners.forEach(l -> l.baseCrewChanged((Integer) spnBaseCrew.getValue()));
            } else if (e.getSource() == spnOfficers) {
                listeners.forEach(l -> l.officersChanged((Integer) spnOfficers.getValue()));
            } else if (e.getSource() == spnGunners) {
                listeners.forEach(l -> l.gunnersChanged((Integer) spnGunners.getValue()));
            } else if (e.getSource() == spnPassengers) {
                listeners.forEach(l -> l.passengersChanged((Integer) spnPassengers.getValue()));
            } else if (e.getSource() == spnMarines) {
                listeners.forEach(l -> l.marinesChanged((Integer) spnMarines.getValue()));
            } else if (e.getSource() == spnBAMarines) {
                listeners.forEach(l -> l.baMarinesChanged((Integer) spnBAMarines.getValue()));
            } else if ((e.getSource() == spnLifeBoats)
                  || (e.getSource() == spnEscapePods)) {
                listeners.forEach(l -> l.escapeChanged((Integer) spnLifeBoats.getValue(),
                      (Integer) spnEscapePods.getValue()));
            } else {
                listeners.forEach(l -> l.quartersChanged(
                      (Integer) spnQuartersFirstClass.getValue(),
                      (Integer) spnQuartersStandard.getValue(),
                      (Integer) spnQuartersSecondClass.getValue(),
                      (Integer) spnQuartersSteerage.getValue()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAssignQuarters) {
            listeners.forEach(AeroVesselBuildListener::autoAssignQuarters);
        }
    }
}
