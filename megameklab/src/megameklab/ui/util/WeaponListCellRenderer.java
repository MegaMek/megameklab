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
package megameklab.ui.util;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mounted;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.UnitUtil;

public class WeaponListCellRenderer extends DefaultListCellRenderer {
    private final Entity unit;

    public WeaponListCellRenderer(Entity unit) {
        this.unit = unit;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

        EquipmentType eq = EquipmentType.get(value.toString());


        if (eq == null) {
            return label;
        }

        label.setText(UnitUtil.getCritName(unit, eq));
        label.setName(value.toString());

        label.setToolTipText(EquipmentToolTip.getToolTipInfo(unit, Mounted.createMounted(unit, eq)));
        return label;
    }

}