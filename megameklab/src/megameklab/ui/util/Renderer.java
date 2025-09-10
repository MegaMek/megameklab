/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.CConfig;

/*
 * Rendered cannot be static because it uses parent data structs.
 */
public class Renderer extends DefaultTableCellRenderer {
    private final CriticalTableModel criticalTableModel;

    public Renderer(CriticalTableModel criticalTableModel) {this.criticalTableModel = criticalTableModel;}

    @Override
    public Component getTableCellRendererComponent(JTable table,
          Object value, boolean isSelected, boolean hasFocus, int row,
          int column) {

        JLabel c = (JLabel) super.getTableCellRendererComponent(table,
              value, isSelected, hasFocus, row, column);

        if ((criticalTableModel.getCrits().size() < row) || (row < 0)) {
            return c;
        }
        if (table.getModel().getValueAt(row, column) != null) {
            c.setText(table.getModel().getValueAt(row, column).toString());
        }

        Mounted<?> mount = criticalTableModel.getCrits().get(row);
        if ((criticalTableModel.unit instanceof BattleArmor) && column == CriticalTableModel.NAME) {
            String modifier = "";
            if (mount.getType() instanceof AmmoType) {
                modifier += " (" + mount.getBaseShotsLeft() + ")";
            }
            if (mount.getLocation() != BattleArmor.LOC_SQUAD) {
                modifier += " (Personal)";
            } else {
                modifier += " (Squad)";
            }
            if (mount.isDWPMounted()) {
                modifier += " (DWP)";
            }
            if (mount.isSquadSupportWeapon()) {
                modifier += " (Squad Support Weapon)";
            }
            if ((mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                  || mount.getType().hasFlag(MiscType.F_AP_MOUNT))
                  && mount.getLinked() != null) {
                modifier += " (attached " + mount.getLinked().getName()
                      + ")";
            }
            if (mount.getType().hasFlag(WeaponType.F_INFANTRY) &&
                  mount.getLinkedBy() == null) {
                modifier += "*";
            }
            c.setText(c.getText() + modifier);
        } else if ((column == CriticalTableModel.NAME) && criticalTableModel.unit.hasETypeFlag(Entity.ETYPE_PROTOMEK)
              && (mount.getType() instanceof AmmoType)) {
            c.setText(c.getText() + " (" + mount.getBaseShotsLeft() + ")");
        }
        c.setToolTipText(EquipmentToolTip.getToolTipInfo(criticalTableModel.unit, mount));
        c.setHorizontalAlignment(criticalTableModel.getAlignment(column));

        if (isSelected) {
            return c;
        }

        String equipmentType = CConfig.GUI_COLOR_EQUIPMENT;

        if (!mount.getType().isHittable()) {
            equipmentType = CConfig.GUI_COLOR_NON_HITTABLE;
        } else if (mount.getType() instanceof WeaponType) {
            equipmentType = CConfig.GUI_COLOR_WEAPONS;
        } else if (mount.getType() instanceof AmmoType) {
            equipmentType = CConfig.GUI_COLOR_AMMO;
        }

        c.setBackground(CConfig.getBackgroundColor(equipmentType));
        c.setForeground(CConfig.getForegroundColor(equipmentType));
        return c;
    }
}
