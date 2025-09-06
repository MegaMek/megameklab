/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    public void addListener(AeroVesselBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(AeroVesselBuildListener l) {
        listeners.remove(l);
    }

    private final JSpinner spnOfficers = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnBaseCrew = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    private final JSpinner spnGunners = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    private final JLabel lblBayPersonnel = new JLabel();
    private final JSpinner spnMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnBAMarines = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel lblTotalCrew = new JLabel();
    private final JSpinner spnPassengers = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersStandard = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnQuartersFirstClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
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
        setLayout(new GridBagLayout());
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblBaseCrew", "AerospaceCrewView.spnBaseCrew.text",
              "AerospaceCrewView.spnBaseCrew.tooltip"), gbc);
        gbc.gridx = 1;
        add(spnBaseCrew, gbc);
        spnBaseCrew.setToolTipText(resourceMap.getString("AerospaceCrewView.spnBaseCrew.tooltip"));
        spnBaseCrew.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblGunners", "AerospaceCrewView.spnGunners.text",
              "AerospaceCrewView.spnGunners.tooltip"), gbc);
        gbc.gridx = 1;
        add(spnGunners, gbc);
        spnGunners.setToolTipText(resourceMap.getString("AerospaceCrewView.spnGunners.tooltip"));
        spnGunners.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblTotalCrew", "AerospaceCrewView.lblTotalCrew.text",
              "AerospaceCrewView.lblTotalCrew.tooltip"), gbc);
        gbc.gridx = 1;
        add(lblTotalCrew, gbc);
        lblTotalCrew.setToolTipText(resourceMap.getString("AerospaceCrewView.lblTotalCrew.tooltip"));
        lblTotalCrew.setHorizontalAlignment(JLabel.RIGHT);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblOfficers", "AerospaceCrewView.spnOfficers.text",
              "AerospaceCrewView.spnOfficers.tooltip"), gbc);
        gbc.gridx = 1;
        spnOfficers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnOfficers.tooltip"));
        add(spnOfficers, gbc);
        spnOfficers.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblBayPersonnel", "AerospaceCrewView.lblBayPersonnel.text",
              "AerospaceCrewView.lblBayPersonnel.tooltip"), gbc);
        gbc.gridx = 1;
        add(lblBayPersonnel, gbc);
        lblBayPersonnel.setToolTipText(resourceMap.getString("AerospaceCrewView.lblBayPersonnel.tooltip"));
        lblBayPersonnel.setHorizontalAlignment(JLabel.RIGHT);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblPassengers", "AerospaceCrewView.spnPassengers.text",
              "AerospaceCrewView.spnPassengers.tooltip"), gbc);
        gbc.gridx = 1;
        spnPassengers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnPassengers.tooltip"));
        add(spnPassengers, gbc);
        spnPassengers.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblMarines", "AerospaceCrewView.spnMarines.text",
              "AerospaceCrewView.spnMarines.tooltip"), gbc);
        gbc.gridx = 1;
        spnMarines.setToolTipText(resourceMap.getString("AerospaceCrewView.spnMarines.tooltip"));
        add(spnMarines, gbc);
        spnMarines.addChangeListener(this);

        gbc.gridx = 0;
        gbc.gridy++;
        lblBAMarines.setText(resourceMap.getString("AerospaceCrewView.spnBAMarines.text"));
        add(lblBAMarines, gbc);
        gbc.gridx = 1;
        spnBAMarines.setToolTipText(resourceMap.getString("AerospaceCrewView.spnBAMarines.tooltip"));
        add(spnBAMarines, gbc);
        spnBAMarines.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel(resourceMap.getString("AerospaceCrewView.lblQuarters.text")), gbc);

        gbc.gridx = 2;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblQuartersFirstClass", "AerospaceCrewView.spnQuartersFirstClass.text",
              "AerospaceCrewView.spnQuartersFirstClass.tooltip"), gbc);
        gbc.gridx = 3;
        spnQuartersFirstClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersFirstClass.tooltip"));
        add(spnQuartersFirstClass, gbc);
        spnQuartersFirstClass.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblQuartersStandard", "AerospaceCrewView.spnQuartersStandard.text",
              "AerospaceCrewView.spnQuartersStandard.tooltip"), gbc);
        gbc.gridx = 3;
        spnQuartersStandard.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersStandard.tooltip"));
        add(spnQuartersStandard, gbc);
        spnQuartersStandard.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblQuartersSecondClass", "AerospaceCrewView.spnQuartersSecondClass.text",
              "AerospaceCrewView.spnQuartersSecondClass.tooltip"), gbc);
        gbc.gridx = 3;
        spnQuartersSecondClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersSecondClass.tooltip"));
        add(spnQuartersSecondClass, gbc);
        spnQuartersSecondClass.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblQuartersSteerage", "AerospaceCrewView.spnQuartersSteerage.text",
              "AerospaceCrewView.spnQuartersSteerage.tooltip"), gbc);
        gbc.gridx = 3;
        spnQuartersSteerage.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersSteerage.tooltip"));
        add(spnQuartersSteerage, gbc);
        spnQuartersSteerage.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        gbc.gridwidth = 2;
        btnAssignQuarters.setText(resourceMap.getString("AerospaceCrewView.btnAssignQuarters.text"));
        btnAssignQuarters.setToolTipText(resourceMap.getString("AerospaceCrewView.btnAssignQuarters.tooltip"));
        add(btnAssignQuarters, gbc);
        btnAssignQuarters.addActionListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap, "lblLifeBoats", "AerospaceCrewView.spnLifeBoats.text",
              "AerospaceCrewView.spnLifeBoats.tooltip"), gbc);
        gbc.gridx = 3;
        spnLifeBoats.setToolTipText(resourceMap.getString("AerospaceCrewView.spnLifeBoats.tooltip"));
        add(spnLifeBoats, gbc);
        spnLifeBoats.addChangeListener(this);

        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap, "lblEscapePods", "AerospaceCrewView.spnEscapePods.text",
              "AerospaceCrewView.spnEscapePods.tooltip"), gbc);
        gbc.gridx = 3;
        spnEscapePods.setToolTipText(resourceMap.getString("AerospaceCrewView.spnEscapePods.tooltip"));
        add(spnEscapePods, gbc);
        spnEscapePods.addChangeListener(this);
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
