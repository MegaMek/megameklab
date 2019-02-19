/*
 * MegaMekLab
 * - Copyright (C) 2009 jtighe (torren@users.sourceforge.net)
 * - Copyright (C) 2018 The MegaMek Team
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

/*
 * Thanks to Lost in space of the Solaris Sunk Works Project for the code snippet and idea.
 */

package megameklab.com.util;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;

public class EquipmentListCellRenderer extends DefaultListCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3787675734141978289L;

    private Entity unit;

    public EquipmentListCellRenderer(Entity unit) {
        this.unit = unit;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        StringBuffer text = new StringBuffer();
        EquipmentType etype = (EquipmentType) value;
        text.append(etype.getName());
        if (unit.isClan() && (!TechConstants.isClan(etype.getTechLevel(unit.getTechLevelYear())))) {
            text.append(" (IS)");
        }
        if (!unit.isClan() && (TechConstants.isClan(etype.getTechLevel(unit.getTechLevelYear())))) {
            text.append(" (Clan)");
        }
        label.setText(text.toString());
        return label;
    }

}
