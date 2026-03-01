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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.equipment.Engine;
import megamek.common.equipment.enums.FuelType;
import megamek.common.ui.SmallFontHelpTextLabel;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.Jumpship;
import megamek.common.units.Tank;
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

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");

    private final SpinnerNumberModel spnFuelModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnFuelCapacityModel = new SpinnerNumberModel(0, 0, 1000000, 1);
    private final JSpinner spnFuel = new JSpinner(spnFuelModel);
    private final JSpinner spnFuelCapacity = new JSpinner(spnFuelCapacityModel);
    private final JLabel lblFuelPoints = new JLabel("", SwingConstants.RIGHT);
    private final JLabel gameTurnsInfo = new SmallFontHelpTextLabel(SwingConstants.CENTER);

    private final JLabel burnDaysInfo = new SmallFontHelpTextLabel(SwingConstants.CENTER);

    private final JLabel lblFuelType = new JLabel(I18N.getString("FuelView.cbFuelType.text"), SwingConstants.RIGHT);
    private final CustomComboBox<FuelType> cbFuelType = new CustomComboBox<>(
          new FuelType[] { FuelType.PETROCHEMICALS, FuelType.ALCOHOL, FuelType.NATURAL_GAS },
          ft -> I18N.getString("FuelView.FuelType." + ft)
    );

    private boolean kgScale = false;

    public FuelView() {
        initUI();
    }

    private void initUI() {
        spnFuel.setToolTipText(I18N.getString("FuelView.spnFuel.tooltip"));
        spnFuel.addChangeListener(this);
        spnFuelCapacity.setToolTipText(I18N.getString("FuelView.lblFuelPoints.tooltip"));
        spnFuelCapacity.addChangeListener(this);
        gameTurnsInfo.setToolTipText(I18N.getString("FuelView.lblTurnsAtSafe.tooltip"));
        cbFuelType.setPrototypeDisplayValue(FuelType.PETROCHEMICALS);
        cbFuelType.setToolTipText(I18N.getString("FuelView.cbFuelType.tooltip"));
        cbFuelType.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = STANDARD_INSETS;
        gbc.gridy = 0;

        gbc.gridy++;
        add(lblFuelType, gbc);
        add(cbFuelType, gbc);

        gbc.gridy++;
        add(new JLabel(I18N.getString("FuelView.spnFuel.text"), SwingConstants.RIGHT), gbc);
        add(spnFuel, gbc);

        gbc.gridy++;
        add(lblFuelPoints, gbc);
        add(spnFuelCapacity, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        add(gameTurnsInfo, gbc);
        gbc.gridy++;
        add(burnDaysInfo, gbc);
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
        lblFuelPoints.setText(I18N.getString("FuelView.lblFuelPoints.text"));
        spnFuelCapacity.setToolTipText(I18N.getString("FuelView.lblFuelPoints.tooltip"));

        gameTurnsInfo.setVisible(!((aero instanceof FixedWingSupport fixedWingSupport)
              && (fixedWingSupport.kgPerFuelPoint() == 0))
              && !((aero instanceof Jumpship capitalCraft) && capitalCraft.hasStationKeepingDrive()));

        if ((aero instanceof FixedWingSupport fixedWingSupport) && (fixedWingSupport.kgPerFuelPoint() == 0)) {
            spnFuel.setEnabled(false);
            spnFuelCapacity.setEnabled(false);
        } else {
            gameTurnsInfo.setText(
                  MessageFormat.format(I18N.getString("FuelView.lblTurns.text"),
                        maxTurnsAtSafeThrust(aero),
                        maxTurnsAtMaxThrust(aero)));
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
            burnDaysInfo.setVisible(true);
            if ((aero instanceof Jumpship capitalCraft) && capitalCraft.hasStationKeepingDrive()) {
                burnDaysInfo.setText(MessageFormat.format(I18N.getString("FuelView.lblBurnDaysStationKeeping.text"),
                      TestAero.calculateDaysAtMax(aero)));
            } else {
                burnDaysInfo.setText(MessageFormat.format(I18N.getString("FuelView.lblBurnDays.text"),
                      TestAero.calculateDaysAt1G(aero), TestAero.calculateDaysAtMax(aero)));
            }
        } else {
            burnDaysInfo.setVisible(false);
        }
        lblFuelType.setVisible(false);
        cbFuelType.setVisible(false);
    }

    private void setFromTank(Tank tank) {
        lblFuelPoints.setText(I18N.getString("FuelView.lblRange.text"));
        spnFuelCapacity.setToolTipText(I18N.getString("FuelView.lblRange.tooltip"));
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

        gameTurnsInfo.setVisible(false);
        burnDaysInfo.setVisible(false);
        lblFuelType.setVisible(tank.getEngine().getEngineType() == Engine.COMBUSTION_ENGINE);
        cbFuelType.setVisible(tank.getEngine().getEngineType() == Engine.COMBUSTION_ENGINE);
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
