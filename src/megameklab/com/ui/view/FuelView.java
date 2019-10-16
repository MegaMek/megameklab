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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.*;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.util.CustomComboBox;
import megameklab.com.ui.view.listeners.BuildListener;

/**
 * Structure tab panel for aerospace and support vehicles.
 * 
 * @author Neoancient
 *
 */
public class FuelView extends BuildView implements ActionListener, ChangeListener {
    
    private static final long serialVersionUID = -3321986392656071192L;

    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(BuildListener l) {
        listeners.add(l);
    }
    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl()); //$NON-NLS-1$

    private final SpinnerNumberModel spnFuelModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnFuelCapacityModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnFuel = new JSpinner(spnFuelModel);
    private final JSpinner spnFuelCapacity = new JSpinner(spnFuelCapacityModel);
    private final JLabel lblFuelPoints = createLabel("", labelSize);
    private final JLabel lblTurnsAtSafe = new JLabel("", JLabel.CENTER);
    private final JLabel lblTurnsAtMax = new JLabel("", JLabel.CENTER);
    private final JLabel lblBurnDays1G = new JLabel("", JLabel.CENTER);
    private final JLabel lblBurnDaysMax = new JLabel("", JLabel.CENTER);
    private final CustomComboBox<FuelType> cbFuelType = new CustomComboBox<>(
            new FuelType[] {FuelType.PETROCHEMICALS, FuelType.ALCOHOL, FuelType.NATURAL_GAS},
            ft -> resourceMap.getString("FuelView.FuelType." + ft)
    );
    private final JPanel panInfoTurns = new JPanel();
    private final JPanel panBurnDays = new JPanel();
    private final JPanel panFuelType = new JPanel();

    private boolean kgScale = false;

    public FuelView() {
        initUI();
    }
    
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createLabel(resourceMap.getString("FuelView.spnFuel.text"), labelSize), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        setFieldSize(spnFuel, spinnerSizeLg);
        spnFuel.setToolTipText(resourceMap.getString("FuelView.spnFuel.tooltip")); //$NON-NLS-1$
        add(spnFuel, gbc);
        spnFuel.addChangeListener(this);
        
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(lblFuelPoints, gbc);
        gbc.gridx = 3;
        gbc.insets = new Insets(0,10,0,20);
        spnFuelCapacity.setToolTipText(resourceMap.getString("FuelView.lblFuelPoints.tooltip"));
        setFieldSize(spnFuelCapacity, spinnerSizeLg);
        add(spnFuelCapacity, gbc);
        spnFuelCapacity.addChangeListener(this);

        gbc.insets = new Insets(0,0,0,0);
        panInfoTurns.setLayout(new GridLayout(0, 2));
        panInfoTurns.add(new JLabel(resourceMap.getString("FuelView.lblTurnsAtSafe.text")), gbc); //$NON-NLS-1$
        panInfoTurns.add(new JLabel(resourceMap.getString("FuelView.lblTurnsAtMax.text")), gbc); //$NON-NLS-1$
        lblTurnsAtSafe.setToolTipText(resourceMap.getString("FuelView.lblTurnsAtSafe.tooltip")); //$NON-NLS-1$
        lblTurnsAtMax.setToolTipText(resourceMap.getString("FuelView.lblTurnsAtMax.tooltip")); //$NON-NLS-1$
        panInfoTurns.add(lblTurnsAtSafe);
        panInfoTurns.add(lblTurnsAtMax);

        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        add(panInfoTurns, gbc);

        panBurnDays.setLayout(new GridLayout(0, 2));
        panBurnDays.add(new JLabel(resourceMap.getString("FuelView.lblBurnDays1G.text")), gbc); //$NON-NLS-1$
        panBurnDays.add(new JLabel(resourceMap.getString("FuelView.lblBurnDaysMax.text")), gbc); //$NON-NLS-1$
        lblBurnDays1G.setToolTipText(resourceMap.getString("FuelView.lblBurnDays1G.tooltip")); //$NON-NLS-1$
        lblBurnDaysMax.setToolTipText(resourceMap.getString("FuelView.lblBurnDaysMax.tooltip")); //$NON-NLS-1$
        panBurnDays.add(lblBurnDays1G);
        panBurnDays.add(lblBurnDaysMax);

        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        add(panInfoTurns, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        add(panBurnDays, gbc);

        panFuelType.add(createLabel(resourceMap.getString("FuelView.cbFuelType.text"), labelSize)); //$NON-NLS-1$
        panFuelType.add(cbFuelType);
        cbFuelType.setToolTipText(resourceMap.getString("FuelView.cbFuelType.tooltip")); //$NON-NLS-1$
        cbFuelType.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        add(panFuelType, gbc);
    }

    private int scaleMultiplier() {
        return kgScale ? 1000 : 1;
    }

    public void setFromEntity(Entity entity) {
        kgScale = TestEntity.usesKgStandard(entity);

        spnFuel.removeChangeListener(this);
        spnFuelModel.setMaximum(entity.getWeight() * scaleMultiplier());
        if (!kgScale) {
            spnFuelModel.setStepSize(0.5);
        } else if (entity instanceof FixedWingSupport) {
            spnFuelModel.setStepSize((double) ((FixedWingSupport) entity).kgPerFuelPoint());
        } else {
            spnFuelModel.setStepSize(1.0);
        }
        spnFuel.addChangeListener(this);

        if (entity instanceof Aero) {
            setFromAero((Aero) entity);
        } else {
            setFromTank((Tank) entity);
        }
    }

    private void setFromAero(Aero aero) {
        lblFuelPoints.setText(resourceMap.getString("FuelView.lblFuelPoints.text"));
        spnFuelCapacity.setToolTipText(resourceMap.getString("FuelView.lblFuelPoints.tooltip"));
        panInfoTurns.setVisible(true);
        if ((aero instanceof FixedWingSupport) && (((FixedWingSupport) aero).kgPerFuelPoint() == 0)) {
            lblTurnsAtSafe.setText("N/A");
            lblTurnsAtMax.setText("N/A");
            spnFuel.setEnabled(false);
            spnFuelCapacity.setEnabled(false);
        } else {
            lblTurnsAtSafe.setText(String.format(
                    "%1$.2f", TestAero.calculateMaxTurnsAtSafe(aero)));
            lblTurnsAtMax.setText(String.format(
                    "%1$.2f", TestAero.calculateMaxTurnsAtMax(aero)));
            spnFuel.setEnabled(true);
            spnFuel.setEnabled(true);
        }

        spnFuel.removeChangeListener(this);
        spnFuelCapacity.removeChangeListener(this);
        spnFuelModel.setValue(aero.getFuelTonnage() * scaleMultiplier());
        spnFuelCapacityModel.setValue(aero.getFuel());
        if (kgScale) {
            spnFuelCapacityModel.setStepSize(Math.ceil(aero.getFuelPointsPerTon() / 1000.0));
        } else {
            spnFuelCapacityModel.setStepSize(Math.ceil(Math.ceil(aero.getFuelPointsPerTon() * 0.5)));
        }
        spnFuel.addChangeListener(this);
        spnFuelCapacity.addChangeListener(this);

        if (aero.getStrategicFuelUse() > 0) {
            lblBurnDays1G.setText(String.format("%3.2f", TestAero.calculateDaysAt1G(aero)));
            lblBurnDaysMax.setText(String.format("%3.2f", TestAero.calculateDaysAtMax(aero)));
            panBurnDays.setVisible(true);
        } else {
            panBurnDays.setVisible(false);
        }
        panFuelType.setVisible(false);
    }

    private void setFromTank(Tank tank) {
        lblFuelPoints.setText(resourceMap.getString("FuelView.lblRange.text"));
        spnFuelCapacity.setToolTipText(resourceMap.getString("FuelView.lblRange.tooltip"));
        if (tank.fuelTonnagePer100km() > 0) {
            spnFuel.setEnabled(true);
            spnFuelCapacity.setEnabled(true);
        } else {
            spnFuel.setEnabled(true);
            spnFuelCapacity.setEnabled(true);
        }

        spnFuel.removeChangeListener(this);
        spnFuelCapacity.removeChangeListener(this);
        spnFuelModel.setValue(tank.getFuelTonnage() * scaleMultiplier());
        spnFuelCapacityModel.setValue(tank.operatingRange());
        spnFuelCapacityModel.setStepSize(100.0);
        spnFuel.addChangeListener(this);
        spnFuelCapacity.addChangeListener(this);

        cbFuelType.removeActionListener(this);
        cbFuelType.setSelectedItem(tank.getICEFuelType());
        cbFuelType.addActionListener(this);

        panInfoTurns.setVisible(false);
        panBurnDays.setVisible(false);
        panFuelType.setVisible(tank.getEngine().getEngineType() == Engine.COMBUSTION_ENGINE);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnFuel) {
            listeners.forEach(l -> l.fuelTonnageChanged(spnFuelModel.getNumber()
                    .doubleValue() / scaleMultiplier()));
        } else if (e.getSource() == spnFuelCapacity) {
            listeners.forEach(l -> l.fuelCapacityChanged(spnFuelCapacityModel.getNumber().intValue()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbFuelType) {
            if (null != cbFuelType.getSelectedItem()) {
                listeners.forEach(l -> l.fuelTypeChanged((FuelType) cbFuelType.getSelectedItem()));
            }
        }
    }
}
