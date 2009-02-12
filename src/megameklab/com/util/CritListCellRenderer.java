/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

/*
 * Thanks to Lost in space of the Solaris Sunk Works Project for the code snippet and idea.
 */

package megameklab.com.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import megamek.common.AmmoType;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megameklab.com.ui.Mek.MainUI;

public class CritListCellRenderer extends DefaultListCellRenderer {

    private JList list = null;
    private int index = -1;
    private Entity unit = null;
    private boolean useColor = false;

    /**
     *
     */
    private static final long serialVersionUID = 1599368063832366744L;

    public CritListCellRenderer(Entity unit, boolean useColor) {
        this.unit = unit;
        this.useColor = useColor;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        this.list = list;
        this.index = index;

        CriticalSlot cs = getCrit();

        if (cs != null) {

            if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                if (useColor) {
                    label.setBackground(MainUI.config.getBackgroundColor(CConfig.CONFIG_SYSTEMS));
                    label.setForeground(MainUI.config.getForegroundColor(CConfig.CONFIG_SYSTEMS));
                }
                if (cs.isArmored()) {
                    label.setText(label.getText() + " (A)");
                }
            } else if (cs.getMount() != null) {

                Mounted mount = cs.getMount();

                if (useColor) {
                    String name = UnitUtil.getCritName(unit, mount.getType());

                    if (mount.isRearMounted()) {
                        name += "(R)";
                    }
                    if (mount.isArmored()) {
                        name += " (A)";
                    }
                    label.setText(name);

                    if (mount.getType() instanceof WeaponType) {
                        label.setBackground(MainUI.config.getBackgroundColor(CConfig.CONFIG_WEAPONS));
                        label.setForeground(MainUI.config.getForegroundColor(CConfig.CONFIG_WEAPONS));
                    } else if (mount.getType() instanceof AmmoType) {
                        label.setBackground(MainUI.config.getBackgroundColor(CConfig.CONFIG_AMMO));
                        label.setForeground(MainUI.config.getForegroundColor(CConfig.CONFIG_AMMO));
                    } else {
                        label.setBackground(MainUI.config.getBackgroundColor(CConfig.CONFIG_EQUIPMENT));
                        label.setForeground(MainUI.config.getForegroundColor(CConfig.CONFIG_EQUIPMENT));
                    }
                }
                label.setToolTipText(UnitUtil.getToolTipInfo(unit, mount));
            }

        }


        if (cs != null && UnitUtil.isLastCrit(unit, cs, index, getCritLocation())) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
        }
        return label;
    }

    private CriticalSlot getCrit() {
        int slot = index;
        int location = getCritLocation();
        CriticalSlot crit = null;
        if (slot >= 0 && slot < unit.getNumberOfCriticals(location)) {
            crit = unit.getCritical(location, slot);
        }

        return crit;
    }

    private int getCritLocation() {
        return Integer.parseInt(list.getName());
    }

}