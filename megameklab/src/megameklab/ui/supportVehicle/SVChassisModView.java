/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

import megamek.common.equipment.EquipmentType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.SVBuildListener;

/**
 * Panel for selecting support vehicle chassis modifications
 */

public class SVChassisModView extends BuildView implements ItemListener {

    private final List<SVBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(SVBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(SVBuildListener l) {
        listeners.remove(l);
    }

    private final Map<TestSupportVehicle.ChassisModification, JCheckBox> checkboxMap = new TreeMap<>();
    private final ITechManager techManager;

    public SVChassisModView(ITechManager techManager) {
        this.techManager = techManager;
        initUi();
    }

    private void initUi() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (TestSupportVehicle.ChassisModification mod : TestSupportVehicle.ChassisModification.values()) {
            JCheckBox cb = new JCheckBox(mod.equipment.getShortName());
            cb.setActionCommand(mod.equipment.getInternalName());
            cb.addItemListener(this);
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
     * Checks for compatibility with selected modifications and disables the checkbox for any that are not compatible.
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
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JCheckBox) {
            final EquipmentType eq = EquipmentType.get(((JCheckBox) e.getSource()).getActionCommand());
            if (null != eq) {
                listeners.forEach(l -> l.setChassisMod(eq, ((JCheckBox) e.getSource()).isSelected()));
            }
        }
    }
}
