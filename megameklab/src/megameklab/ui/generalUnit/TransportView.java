/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.bays.Bay;
import megamek.common.units.Entity;
import megamek.common.verifier.BayData;
import megameklab.ui.listeners.BuildListener;

/**
 * Panel for combat vehicle aero cargo and troop space. This includes aero
 *
 * @author Neoancient
 */
public abstract class TransportView extends BuildView implements ChangeListener {

    public abstract void addListener(BuildListener listener);

    public abstract void removeListener(BuildListener listener);

    protected final SpinnerNumberModel spnFixedTroopModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    protected final SpinnerNumberModel spnPodTroopModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    protected final Map<BayData, SpinnerNumberModel> fixedSpinnerModels = new HashMap<>();
    protected final Map<BayData, SpinnerNumberModel> podSpinnerModels = new HashMap<>();

    protected final JSpinner spnFixedTroop = new JSpinner(spnFixedTroopModel);
    protected final JSpinner spnPodTroop = new JSpinner(spnPodTroopModel);
    protected final Map<BayData, JSpinner> fixedSpinners = new HashMap<>();
    protected final Map<BayData, JSpinner> podSpinners = new HashMap<>();

    // Track unit tonnage to set max allowed carrying space.
    private double tonnage;

    public TransportView() {
        super();
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblFixed", "CVTransportView.lblFixed.text"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(createLabel(resourceMap, "lblPod", "CVTransportView.lblPod.text"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel(resourceMap, "lblTroopSpace", "CVTransportView.lblTroopSpace.text"), gbc);

        gbc.gridx = 1;
        add(spnFixedTroop, gbc);
        spnFixedTroop.addChangeListener(this);

        gbc.gridx = 2;
        add(spnPodTroop, gbc);
        spnPodTroop.addChangeListener(this);

        for (BayData bayType : BayData.values()) {
            if (!bayType.isCargoBay()) {
                continue;
            }
            String tooltip = String.format(resourceMap.getString("CVTransportView.bay.tooltipFormat"),
                  1 / bayType.getWeight());
            gbc.gridx = 0;
            gbc.gridy++;
            final JLabel lblBayType = createLabel("lbl" + bayType.name(), bayType.getDisplayName());
            lblBayType.setToolTipText(tooltip);
            add(lblBayType, gbc);

            gbc.gridx = 1;
            SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
            JSpinner spinner = new JSpinner(model);
            spinner.setToolTipText(tooltip);
            spinner.setName(bayType.toString());
            fixedSpinnerModels.put(bayType, model);
            fixedSpinners.put(bayType, spinner);
            add(spinner, gbc);
            spinner.addChangeListener(this);

            gbc.gridx = 2;
            model = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
            spinner = new JSpinner(model);
            spinner.setToolTipText(tooltip);
            spinner.setName(bayType.toString());
            podSpinnerModels.put(bayType, model);
            podSpinners.put(bayType, spinner);
            add(spinner, gbc);
            spinner.addChangeListener(this);
        }
    }

    public void setFromEntity(Entity entity) {
        double troops = entity.getTroopCarryingSpace();
        double podTroops = entity.getPodMountedTroopCarryingSpace();
        spnFixedTroop.setValue(troops - podTroops);
        spnPodTroop.setValue(podTroops);

        Map<BayData, Double> fixedCargo = new HashMap<>();
        Map<BayData, Double> podCargo = new HashMap<>();
        for (Bay b : entity.getTransportBays()) {
            BayData bayType = BayData.getBayType(b);
            if (null != bayType) {
                if (entity.isPodMountedTransport(b)) {
                    podCargo.merge(bayType, b.getCapacity(), Double::sum);
                } else {
                    fixedCargo.merge(bayType, b.getCapacity(), Double::sum);
                }
            }
        }
        for (Map.Entry<BayData, JSpinner> entry : fixedSpinners.entrySet()) {
            entry.getValue().setValue(fixedCargo.getOrDefault(entry.getKey(), 0.0));
        }
        for (Map.Entry<BayData, JSpinner> entry : podSpinners.entrySet()) {
            entry.getValue().setValue(podCargo.getOrDefault(entry.getKey(), 0.0));
        }
        setOmni(entity.isOmni());
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
        refresh();
    }

    public void setOmni(boolean omni) {
        spnPodTroop.setEnabled(omni);
        podSpinners.values().forEach(v -> v.setEnabled(omni));
        if (!omni) {
            clearPodSpace();
        }
    }

    public void clearPodSpace() {
        spnPodTroop.setValue(0.0);
        podSpinners.values().forEach(v -> v.setValue(0.0));
    }

    public void refresh() {
        resetMaxSize(spnFixedTroopModel, tonnage);
        resetMaxSize(spnPodTroopModel, tonnage);
        fixedSpinnerModels.values().forEach(m -> resetMaxSize(m, tonnage));
        podSpinnerModels.values().forEach(m -> resetMaxSize(m, tonnage));
    }

    private void resetMaxSize(SpinnerNumberModel model, double max) {
        model.setMaximum(max);
        if (model.getNumber().doubleValue() > max) {
            model.removeChangeListener(this);
            model.setValue(max);
            model.addChangeListener(this);
        }
    }

    @Override
    public abstract void stateChanged(ChangeEvent e);
}
