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
package megameklab.ui.generalUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.equipment.Engine;
import megamek.common.equipment.enums.FuelType;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.Jumpship;
import megamek.common.units.Tank;
import megamek.common.units.Warship;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.util.CustomComboBox;

/**
 * The fuel settings view for aerospace and support vehicles.
 *
 * @author Neoancient
 */
public class FuelView extends BuildView implements ActionListener, ChangeListener {
    private final List<BuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(BuildListener l) {
        listeners.add(l);
    }

    public void removeListener(BuildListener l) {
        listeners.remove(l);
    }

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    private final SpinnerNumberModel spnFuelModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnFuelCapacityModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnFuel = new JSpinner(spnFuelModel);
    private final JSpinner spnFuelCapacity = new JSpinner(spnFuelCapacityModel);
    private final JLabel lblFuelPoints = new JLabel();
    private final JLabel lblTurnsAtSafe = new JLabel();
    private final JLabel lblTurnsAtMax = new JLabel();
    private final JLabel lblBurnDays1G = new JLabel();
    private final JLabel lblBurnDaysMax = new JLabel();
    private final JLabel lblBurnDays1GText = new JLabel(resourceMap.getString("FuelView.lblBurnDays1G.text"));
    private final JLabel lblBurnDaysMaxText = new JLabel(resourceMap.getString("FuelView.lblBurnDaysMax.text"));
    private final CustomComboBox<FuelType> cbFuelType = new CustomComboBox<>(
          new FuelType[] { FuelType.PETROCHEMICALS, FuelType.ALCOHOL, FuelType.NATURAL_GAS },
          ft -> resourceMap.getString("FuelView.FuelType." + ft)
    );
    private final Box panInfoTurns = Box.createHorizontalBox();
    private final Box panBurnDays = Box.createHorizontalBox();
    private final Box panFuelType = Box.createHorizontalBox();

    private boolean kgScale = false;

    public FuelView() {
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        spnFuel.setToolTipText(resourceMap.getString("FuelView.spnFuel.tooltip"));
        spnFuel.addChangeListener(this);
        spnFuelCapacity.setToolTipText(resourceMap.getString("FuelView.lblFuelPoints.tooltip"));
        spnFuelCapacity.addChangeListener(this);

        Box fuelRangePanel = Box.createHorizontalBox();
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.add(new JLabel(resourceMap.getString("FuelView.spnFuel.text")));
        fuelRangePanel.add(spnFuel);
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.add(lblFuelPoints);
        fuelRangePanel.add(spnFuelCapacity);
        fuelRangePanel.add(Box.createHorizontalStrut(10));
        fuelRangePanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        add(fuelRangePanel);

        lblTurnsAtSafe.setToolTipText(resourceMap.getString("FuelView.lblTurnsAtSafe.tooltip"));
        lblTurnsAtMax.setToolTipText(resourceMap.getString("FuelView.lblTurnsAtMax.tooltip"));
        panInfoTurns.add(Box.createHorizontalStrut(10));
        panInfoTurns.add(new JLabel(resourceMap.getString("FuelView.lblTurnsAtSafe.text")));
        panInfoTurns.add(lblTurnsAtSafe);
        panInfoTurns.add(Box.createHorizontalStrut(10));
        panInfoTurns.add(new JLabel(resourceMap.getString("FuelView.lblTurnsAtMax.text")));
        panInfoTurns.add(lblTurnsAtMax);
        panInfoTurns.add(Box.createHorizontalStrut(10));
        panInfoTurns.setBorder(new EmptyBorder(0, 0, 10, 0));
        add(panInfoTurns);

        lblBurnDays1G.setToolTipText(resourceMap.getString("FuelView.lblBurnDays1G.tooltip"));
        lblBurnDaysMax.setToolTipText(resourceMap.getString("FuelView.lblBurnDaysMax.tooltip"));
        panBurnDays.add(Box.createHorizontalStrut(10));
        panBurnDays.add(lblBurnDays1GText);
        panBurnDays.add(lblBurnDays1G);
        panBurnDays.add(Box.createHorizontalStrut(10));
        panBurnDays.add(lblBurnDaysMaxText);
        panBurnDays.add(lblBurnDaysMax);
        panBurnDays.add(Box.createHorizontalStrut(10));
        panBurnDays.setBorder(new EmptyBorder(0, 0, 10, 0));
        add(panBurnDays);

        cbFuelType.setPrototypeDisplayValue(FuelType.PETROCHEMICALS);
        cbFuelType.setToolTipText(resourceMap.getString("FuelView.cbFuelType.tooltip"));
        cbFuelType.addActionListener(this);
        panFuelType.add(Box.createHorizontalStrut(10));
        panFuelType.add(new JLabel(resourceMap.getString("FuelView.cbFuelType.text")));
        panFuelType.add(cbFuelType);
        panFuelType.add(Box.createHorizontalGlue());
        panFuelType.add(Box.createHorizontalStrut(10));
        panFuelType.setBorder(new EmptyBorder(0, 0, 10, 0));
        add(panFuelType);
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
            panInfoTurns.setVisible(false);
            spnFuel.setEnabled(false);
            spnFuelCapacity.setEnabled(false);
        } else {
            int maxTurnsAtSafeThrust = maxTurnsAtSafeThrust(aero);
            lblTurnsAtSafe.setText(maxTurnsAtSafeThrust > 0 ? maxTurnsAtSafeThrust + "" : "--");
            int maxTurnsAtMaxThrust = maxTurnsAtMaxThrust(aero);
            lblTurnsAtMax.setText(maxTurnsAtMaxThrust > 0 ? maxTurnsAtMaxThrust + "" : "--");
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
            if ((aero instanceof Jumpship) && !(aero instanceof Warship)) {
                lblBurnDays1GText.setText(resourceMap.getString("FuelView.lblBurnDaysStationKeeping.text"));
                lblBurnDaysMaxText.setVisible(false);
                lblBurnDaysMax.setVisible(false);
            } else {
                lblBurnDays1GText.setText(resourceMap.getString("FuelView.lblBurnDays1G.text"));
                lblBurnDaysMaxText.setVisible(true);
                lblBurnDaysMax.setVisible(true);
            }
        } else {
            panBurnDays.setVisible(false);
        }
        panFuelType.setVisible(false);
    }

    private void setFromTank(Tank tank) {
        lblFuelPoints.setText(resourceMap.getString("FuelView.lblRange.text"));
        spnFuelCapacity.setToolTipText(resourceMap.getString("FuelView.lblRange.tooltip"));
        spnFuel.setEnabled(tank.fuelTonnagePer100km() > 0);
        spnFuelCapacity.setEnabled(tank.fuelTonnagePer100km() > 0);

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
            listeners.forEach(l -> l.fuelTonnageChanged(spnFuelModel.getNumber().doubleValue() / scaleMultiplier()));
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

    /** @return The maximum turns at maximum thrust that the aero's fuel allows or -1 if no value can be computed. */
    private int maxTurnsAtMaxThrust(Aero aero) {
        return maxTurnsAtThrust(aero, aero.getRunMP());
    }

    /** @return The maximum turns at safe thrust that the aero's fuel allows or -1 if no value can be computed. */
    private int maxTurnsAtSafeThrust(Aero aero) {
        return maxTurnsAtThrust(aero, aero.getWalkMP());
    }

    /** @return The maximum turns at the given thrust that the aero's fuel allows or -1 if no value can be computed. */
    private int maxTurnsAtThrust(Aero aero, int thrust) {
        int fuelPoints = aero.getFuel();
        float fuelPerTurn = aero.getFuelUsed(thrust);
        return fuelPerTurn != 0 ? (int) (fuelPoints / fuelPerTurn) : -1;
    }
}
