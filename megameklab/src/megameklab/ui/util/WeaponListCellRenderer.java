/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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

/*
 * Thanks to Lost in space of the Solaris Sunk Works Project for the code snippet and idea.
 */
package megameklab.ui.util;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import megamek.common.units.Entity;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.UnitUtil;

public class WeaponListCellRenderer extends DefaultListCellRenderer {
    private final Entity unit;

    public WeaponListCellRenderer(Entity unit) {
        this.unit = unit;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
          boolean hasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

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
