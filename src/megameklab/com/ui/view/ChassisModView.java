/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMekTeam
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.com.ui.view.listeners.SVBuildListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for selecting support vehicle chassis modifcations
 */

public class ChassisModView extends BuildView implements ActionListener {

    private final List<SVBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(SVBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(SVBuildListener l) {
        listeners.remove(l);
    }

    private final Map<TestSupportVehicle.ChassisModification, JCheckBox> checkboxMap = new TreeMap<>();
    private final ITechManager techManager;

    public ChassisModView(ITechManager techManager) {
        this.techManager = techManager;
        initUi();
    }

    private void initUi() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (TestSupportVehicle.ChassisModification mod : TestSupportVehicle.ChassisModification.values()) {
            JCheckBox cb = new JCheckBox(mod.equipment.getShortName());
            cb.setActionCommand(mod.equipment.getInternalName());
            cb.addActionListener(this);
            checkboxMap.put(mod, cb);
            add(cb);
        }
    }

    public void setFromEntity(Entity en) {
        for (TestSupportVehicle.ChassisModification mod : checkboxMap.keySet()) {
            final JCheckBox cb = checkboxMap.get(mod);
            if (techManager.isLegal(mod) && mod.validFor(en)) {
                cb.setVisible(true);
                if (mod.requiredFor(en)) {
                    cb.setSelected(true);
                    cb.setEnabled(false);
                } else {
                    cb.setSelected(en.getMisc().stream().anyMatch(m -> m.getType().equals(mod.equipment)));
                    cb.setEnabled(true);
                }
            } else {
                cb.setVisible(false);
                cb.setSelected(false);
                en.removeMisc(mod.equipment.getName());
            }
        }
        refresh();
    }

    /**
     * Checks for compatibility with selected modifications and disables the checkbox for any that are
     * not compatible.
     */
    public void refresh() {
        // Build a list of all selected mods
        List<TestSupportVehicle.ChassisModification> checked = new ArrayList<>();
        for (Map.Entry<TestSupportVehicle.ChassisModification, JCheckBox> e : checkboxMap.entrySet()) {
            if (e.getValue().isSelected()) {
                checked.add(e.getKey());
            }
        }
        // Enable/disable each checkbox based on compatibility with other selected mods. If the box
        // is already selected, remove it.
        for (Map.Entry<TestSupportVehicle.ChassisModification, JCheckBox> e : checkboxMap.entrySet()) {
            if (checked.stream().allMatch(mod -> mod.compatibleWith(e.getKey()))) {
                e.getValue().setEnabled(true);
            } else {
                if (e.getValue().isSelected()) {
                    listeners.forEach(l -> l.setChassisMod(e.getKey().equipment, false));
                }
                e.getValue().setSelected(false);
                e.getValue().setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox) {
            final EquipmentType eq = EquipmentType.get(e.getActionCommand());
            if (null != eq) {
                listeners.forEach(l -> l.setChassisMod(eq, ((JCheckBox) e.getSource()).isSelected()));
            }
        }
    }
}
