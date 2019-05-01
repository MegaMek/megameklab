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
            if (techManager.isLegal(mod) && mod.isValidFor(en)) {
                cb.setVisible(true);
                cb.setSelected(en.getMisc().stream().anyMatch(m -> m.getType().equals(mod.equipment)));
            } else {
                cb.setVisible(false);
                cb.setSelected(false);
                en.removeMisc(mod.equipment.getName());
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
