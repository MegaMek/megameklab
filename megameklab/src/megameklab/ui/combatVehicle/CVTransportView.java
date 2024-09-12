/*
 * Copyright (c) 2018-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.combatVehicle;

import megamek.common.Bay;
import megamek.common.Tank;
import megamek.common.verifier.BayData;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.CVBuildListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for combat vehicle cargo and troop space.
 * 
 * @author Neoancient
 */
public class CVTransportView extends BuildView implements ChangeListener {
    List<CVBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(CVBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(CVBuildListener l) {
        listeners.remove(l);
    }

    private final SpinnerNumberModel spnFixedTroopModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final SpinnerNumberModel spnPodTroopModel = new SpinnerNumberModel(0.0, 0.0, null, 0.5);
    private final Map<BayData, SpinnerNumberModel> fixedSpinnerModels = new HashMap<>();
    private final Map<BayData, SpinnerNumberModel> podSpinnerModels = new HashMap<>();
    
    private final JSpinner spnFixedTroop = new JSpinner(spnFixedTroopModel);
    private final JSpinner spnPodTroop = new JSpinner(spnPodTroopModel);
    private final Map<BayData, JSpinner> fixedSpinners = new HashMap<>();
    private final Map<BayData, JSpinner> podSpinners = new HashMap<>();
    
    // Track unit tonnage to set max allowed carrying space.
    private double tonnage;

    public CVTransportView() {
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
    
    public void setFromEntity(Tank tank) {
        double troops = tank.getTroopCarryingSpace();
        double podTroops = tank.getPodMountedTroopCarryingSpace();
        spnFixedTroop.setValue(troops - podTroops);
        spnPodTroop.setValue(podTroops);

        Map<BayData, Double> fixedCargo = new HashMap<>();
        Map<BayData, Double> podCargo = new HashMap<>();
        for (Bay b : tank.getTransportBays()) {
            BayData bayType = BayData.getBayType(b);
            if (null != bayType) {
                if (tank.isPodMountedTransport(b)) {
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
        setOmni(tank.isOmni());
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
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource() == spnFixedTroop)
                || (e.getSource() == spnPodTroop)) {
            listeners.forEach(l -> l.troopSpaceChanged(spnFixedTroopModel.getNumber().doubleValue(),
                    spnPodTroopModel.getNumber().doubleValue()));
        } else if (e.getSource() instanceof Component) {
            BayData bayType = null;
            for (BayData bay : BayData.values()) {
                if (bay.toString().equals(((Component) e.getSource()).getName())) {
                    bayType = bay;
                    break;
                }
            }
            if (null != bayType) {
                for (CVBuildListener l : listeners) {
                    l.cargoSpaceChanged(bayType,
                        fixedSpinnerModels.get(bayType).getNumber().doubleValue(),
                        podSpinnerModels.get(bayType).getNumber().doubleValue());
                }
            }
        }
    }
}
