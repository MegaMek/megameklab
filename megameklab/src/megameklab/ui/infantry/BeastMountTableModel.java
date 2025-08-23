/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import megamek.common.units.InfantryMount;

final class BeastMountTableModel implements TableModel {
    private enum Columns {
        TYPE("CIMountView.colType", "CIMountView.colType.tooltip", SwingConstants.LEFT),
        SIZE("CIMountView.colSize", "CIMountView.colSize.tooltip", SwingConstants.LEFT),
        WEIGHT("CIMountView.colWeight", "CIMountView.colWeight.tooltip", SwingConstants.LEFT),
        MP("CIMountView.colMP", "CIMountView.colMP.tooltip", SwingConstants.LEFT),
        BONUS_DAMAGE("CIMountView.colBonusDamage", "CIMountView.colBonusDamage.tooltip", SwingConstants.CENTER),
        DIVISOR("CIMountView.colDivisor", "CIMountView.colDivisor.tooltip", SwingConstants.CENTER),
        TERRAIN("CIMountView.colTerrain", "CIMountView.colTerrain.tooltip", SwingConstants.LEFT);

        final String resId;
        final String tooltipId;
        final int alignment;

        Columns(String resId, String tooltipId, int alignment) {
            this.resId = resId;
            this.tooltipId = tooltipId;
            this.alignment = alignment;
        }
    }

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");

    @Override
    public int getRowCount() {
        return InfantryMount.sampleMounts.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return resourceMap.getString(Columns.values()[columnIndex].resId);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var creature = InfantryMount.sampleMounts.get(rowIndex);
        switch (Columns.values()[columnIndex]) {
            case TYPE:
                return creature.name();
            case SIZE:
                return creature.size().displayName();
            case WEIGHT:
                return NumberFormat.getInstance().format(creature.weight());
            case MP:
                return String.format("%d (%s)", creature.getMP(), creature.movementMode().toString());
            case BONUS_DAMAGE:
                return String.format("%+d%s (%d)", creature.getBurstDamageDice(),
                      creature.getBurstDamageDice() > 0 ? "D6" : "", creature.vehicleDamage());
            case DIVISOR:
                return NumberFormat.getInstance().format(creature.damageDivisor());
            case TERRAIN:
                if (creature.secondaryGroundMP() > 0) {
                    return resourceMap.getString("CIMountView.1groundMP");
                } else if (creature.movementMode().isSubmarine()) {
                    return resourceMap.getString("CIMountView.asSubmarines");
                } else if (creature.movementMode().isVTOL()) {
                    return resourceMap.getString("CIMountView.asVTOL");
                } else {
                    return String.format(resourceMap.getString("CIMountView.waterDepth.format"),
                          creature.maxWaterDepth() + 1);
                }
        }
        return "?";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}

    int getAlignment(int columnIndex) {
        return Columns.values()[columnIndex].alignment;
    }

    String getTooltip(int columnIndex) {
        return resourceMap.getString(Columns.values()[columnIndex].tooltipId);
    }

    public class Renderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
              boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int actualCol = table.convertColumnIndexToModel(column);
            setHorizontalAlignment(getAlignment(actualCol));
            setToolTipText(getTooltip(actualCol));
            return this;
        }
    }
}
