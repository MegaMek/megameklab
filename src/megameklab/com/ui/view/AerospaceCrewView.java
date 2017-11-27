/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.BattleArmor;
import megamek.common.Bay;
import megamek.common.EntityWeightClass;
import megamek.common.ITechManager;
import megamek.common.SmallCraft;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestAero.Quarters;
import megamek.common.verifier.TestSmallCraft;
import megameklab.com.ui.view.listeners.AeroVesselBuildListener;

/**
 * Structure tab panel for setting crew levels and quarters, as well as life boats and escape pods.
 * 
 * @author Neoancient
 *
 */

public class AerospaceCrewView extends BuildView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5340962405770541772L;
    
    private List<AeroVesselBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(AeroVesselBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(AeroVesselBuildListener l) {
        listeners.remove(l);
    }
    
    private final JSpinner spnOfficers = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
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

    private final JLabel lblBAMarines = createLabel("", labelSize);
    private final ITechManager techManager;
    private boolean ignoreChangeEvents = false;
    
    public AerospaceCrewView(ITechManager techManager) {
        this.techManager = techManager;
        initUI();
    }
    
    public void initUI() {
        setLayout(new GridBagLayout());
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnBaseCrew.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnBaseCrew, spinnerSize);
        add(spnBaseCrew, gbc);
        spnBaseCrew.setToolTipText(resourceMap.getString("AerospaceCrewView.spnBaseCrew.tooltip"));
        spnBaseCrew.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnGunners.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnGunners, spinnerSize);
        add(spnGunners, gbc);
        spnGunners.setToolTipText(resourceMap.getString("AerospaceCrewView.spnGunners.tooltip"));
        spnGunners.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.lblTotalCrew.text"), labelSize), gbc);
        gbc.gridx = 1;
        add(lblTotalCrew, gbc);
        lblTotalCrew.setToolTipText(resourceMap.getString("AerospaceCrewView.lblTotalCrew.tooltip"));
        lblTotalCrew.setHorizontalAlignment(JLabel.RIGHT);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnOfficers.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnOfficers, spinnerSize);
        spnOfficers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnOfficers.tooltip"));
        add(spnOfficers, gbc);
        spnOfficers.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.lblBayPersonnel.text"), labelSize), gbc);
        gbc.gridx = 1;
        add(lblBayPersonnel, gbc);
        lblBayPersonnel.setToolTipText(resourceMap.getString("AerospaceCrewView.lblBayPersonnel.tooltip"));
        lblBayPersonnel.setHorizontalAlignment(JLabel.RIGHT);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnPassengers.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnPassengers, spinnerSize);
        spnPassengers.setToolTipText(resourceMap.getString("AerospaceCrewView.spnPassengers.tooltip"));
        add(spnPassengers, gbc);
        spnPassengers.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnMarines.text"), labelSize), gbc);
        gbc.gridx = 1;
        setFieldSize(spnMarines, spinnerSize);
        spnMarines.setToolTipText(resourceMap.getString("AerospaceCrewView.spnMarines.tooltip"));
        add(spnMarines, gbc);
        spnMarines.addChangeListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        lblBAMarines.setText(resourceMap.getString("AerospaceCrewView.spnBAMarines.text"));
        add(lblBAMarines, gbc);
        gbc.gridx = 1;
        setFieldSize(spnBAMarines, spinnerSize);
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
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersFirstClass.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersFirstClass, spinnerSize);
        spnQuartersFirstClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersFirstClass.tooltip"));
        add(spnQuartersFirstClass, gbc);
        spnQuartersFirstClass.addChangeListener(this);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersStandard.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersStandard, spinnerSize);
        spnQuartersStandard.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersStandard.tooltip"));
        add(spnQuartersStandard, gbc);
        spnQuartersStandard.addChangeListener(this);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersSecondClass.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersSecondClass, spinnerSize);
        spnQuartersSecondClass.setToolTipText(resourceMap.getString("AerospaceCrewView.spnQuartersSecondClass.tooltip"));
        add(spnQuartersSecondClass, gbc);
        spnQuartersSecondClass.addChangeListener(this);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnQuartersSteerage.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnQuartersSteerage, spinnerSize);
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
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnLifeBoats.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnLifeBoats, spinnerSize);
        spnLifeBoats.setToolTipText(resourceMap.getString("AerospaceCrewView.spnLifeBoats.tooltip"));
        add(spnLifeBoats, gbc);
        spnLifeBoats.addChangeListener(this);
        
        gbc.gridx = 2;
        gbc.gridy++;
        add(createLabel(resourceMap.getString("AerospaceCrewView.spnEscapePods.text"), labelSize), gbc);
        gbc.gridx = 3;
        setFieldSize(spnEscapePods, spinnerSize);
        spnEscapePods.setToolTipText(resourceMap.getString("AerospaceCrewView.spnEscapePods.tooltip"));
        add(spnEscapePods, gbc);
        spnEscapePods.addChangeListener(this);
    }
    
    public void setFromEntity(SmallCraft sc) {
        int minGunners = TestSmallCraft.requiredGunners(sc);
        int minBase = TestSmallCraft.minimumBaseCrew(sc);
        int nonBay = sc.getNCrew() - sc.getBayPersonnel();
        ((SpinnerNumberModel)spnBaseCrew.getModel()).setMinimum(minBase);
        ((SpinnerNumberModel)spnGunners.getModel()).setMinimum(minGunners);
        
        ignoreChangeEvents = true;
        spnOfficers.setValue(sc.getNOfficers());
        spnBaseCrew.setValue(nonBay - sc.getNGunners());
        spnGunners.setValue(sc.getNGunners());
        lblTotalCrew.setText(String.valueOf(nonBay));
        lblBayPersonnel.setText(String.valueOf(sc.getBayPersonnel()));
        spnPassengers.setValue(sc.getNPassenger());
        spnMarines.setValue(sc.getNMarines());
        
        if (techManager.isLegal(BattleArmor.getConstructionTechAdvancement(EntityWeightClass.WEIGHT_MEDIUM))) {
            spnBAMarines.setValue(sc.getNBattleArmor());
            lblBAMarines.setVisible(true);
            spnBAMarines.setVisible(true);
        } else {
            spnBAMarines.setValue(0);
            lblBAMarines.setVisible(false);
            spnBAMarines.setVisible(false);
        }
        
        EnumMap<TestAero.Quarters, Integer> sizes = new EnumMap<>(TestAero.Quarters.class);
        for (Bay bay : sc.getTransportBays()) {
            Quarters q = TestAero.Quarters.getQuartersForBay(bay);
            if (null != q) {
                sizes.merge(q, (int) bay.getCapacity(), Integer::sum);
            }
        }
        spnQuartersFirstClass.setValue(sizes.getOrDefault(TestAero.Quarters.FIRST_CLASS, 0));
        spnQuartersStandard.setValue(sizes.getOrDefault(TestAero.Quarters.STANDARD, 0));
        spnQuartersSecondClass.setValue(sizes.getOrDefault(TestAero.Quarters.SECOND_CLASS, 0));
        spnQuartersSteerage.setValue(sizes.getOrDefault(TestAero.Quarters.STEERAGE, 0));
        
        spnLifeBoats.setValue(sc.getLifeBoats());
        spnEscapePods.setValue(sc.getEscapePods());
        ignoreChangeEvents = false;
        
        // If we do not meet the minimum, set the values and trigger an event that will update the vessel.
        if (sc.getNGunners() < minGunners) {
            spnGunners.setValue(minGunners);
        }
        if (nonBay - sc.getNGunners() < minBase) {
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
