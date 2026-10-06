/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.AeroBuildListener;
import megameklab.util.UnitUtil;

/**
 * Panel for selecting conventional fighter chassis modifications
 */
public class ASChassisModView extends BuildView implements ItemListener {

    private final List<AeroBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(AeroBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(AeroBuildListener l) {
        listeners.remove(l);
    }

    private final Map<EquipmentType, JCheckBox> checkboxMap =
          new TreeMap<>(Comparator.comparing(EquipmentType::getShortName));
    private final ITechManager techManager;

    public ASChassisModView(ITechManager techManager) {
        this.techManager = techManager;
        initUi();
    }

    private void initUi() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (EquipmentType equipment : EquipmentType.allTypes()) {
            if (equipment.hasFlag(MiscType.F_FLOTATION_HULL)) {
                JCheckBox cb = new JCheckBox(equipment.getShortName());
                cb.setActionCommand(equipment.getInternalName());
                cb.addItemListener(this);
                checkboxMap.put(equipment, cb);
            }
        }
        for (JCheckBox checkBox : checkboxMap.values()) {
            panel.add(checkBox);
        }
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    public void setFromEntity(Entity en) {
        for (EquipmentType equipment : checkboxMap.keySet()) {
            final JCheckBox cb = checkboxMap.get(equipment);
            if (UnitUtil.isEntityEquipment(equipment, en) && techManager.isLegal(equipment)) {
                cb.setVisible(true);
                cb.setSelected(en.getMisc().stream().anyMatch(m -> m.getType().equals(equipment)));
                cb.setEnabled(true);
            } else {
                cb.setVisible(false);
                cb.setSelected(false);
                en.removeMisc(equipment.getName());
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
