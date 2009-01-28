/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.util;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.WeaponType;

public class CriticalTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 7615555055651822051L;

    public EquipmentType[] sortedEquipment = {};
    public Vector<EquipmentType> crits = new Vector<EquipmentType>();
    public Mech unit;
    public final static int NAME = 0;
    public final static int TONNAGE = 1;
    public final static int CRITS = 2;
    public final static int HEAT = 3;
    public final static int EQUIPMENT = 4;

    public final static int EQUIPMENTTABLE = 0;
    public final static int WEAPONTABLE = 1;
    public final static int BUILDTABLE = 2;

    private int tableType = EQUIPMENTTABLE;

    String[] columnNames = { "Name", "Tons", "Crits", };

    String[] longValues = { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX", };

    public int getColumnCount() {
        return columnNames.length;
    }

    public CriticalTableModel(Mech unit, int tableType) {
        this.tableType = tableType;
        if (tableType == WEAPONTABLE) {
            longValues = new String[] { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX", };
            columnNames = new String[] { "Name", "Tons", "Crits", "Heat", };
        }
        this.unit = unit;
    }

    public void updateMech(Mech unit) {
        this.unit = unit;
    }

    public void refreshModel() {
        // do a resort
        sortedEquipment = new EquipmentType[] {};
        if (crits.size() > 0) {
            sortedEquipment = crits.toArray(sortedEquipment);
        }
        fireTableDataChanged();
    }

    public void initColumnSizes(JTable table) {
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        CriticalTableModel model = this;
        for (int i = 0; i < getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i], false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public int getRowCount() {
        return sortedEquipment.length;
    }

    @Override
    public String getColumnName(int col) {
        return (columnNames[col]);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Object getValueAt(int row, int col) {
        if (row < 0) {
            return "";
        }
        if (row >= sortedEquipment.length) {
            return "";
        }
        EquipmentType crit = sortedEquipment[row];
        switch (col) {
        case NAME:
            return crit.getName();
        case TONNAGE:
            return crit.getTonnage(unit);
        case CRITS:
            if (tableType == BUILDTABLE) {
                return UnitUtil.getCritsUsed(unit, crit);
            }
            return crit.getCriticals(unit);
        case EQUIPMENT:
            return crit;
        case HEAT:
            if (crit instanceof WeaponType) {
                return new Integer(((WeaponType) crit).getHeat());
            }
            return new Integer(0);
        }
        return "";
    }

    public CriticalTableModel.Renderer getRenderer() {
        return new Renderer();
    }

    /*
     * Rendered cannot be static because it uses parent data structs.
     */
    private class Renderer extends DefaultTableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 149542030113164984L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component d = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            JLabel c = new JLabel();
            c.setOpaque(true);
            if (crits.size() < row || row < 0) {
                return c;
            }
            if (table.getModel().getValueAt(row, column) != null) {
                c.setText(table.getModel().getValueAt(row, column).toString());
            }
            c.setToolTipText("");

            if (isSelected) {
                c.setForeground(d.getForeground());
                c.setBackground(d.getBackground());
                return c;
            }

            c.setBackground(Color.white);
            return c;
        }
    }

    public void addCrit(EquipmentType eq) {
        crits.add(eq);
    }

    public void removeCrit(int location) {
        crits.removeElementAt(location);
    }

    public void removeAllCrits() {
        crits.removeAllElements();
    }

    public void removeMounted(int row) {
        UnitUtil.removeMounted(unit, (EquipmentType) getValueAt(row, CriticalTableModel.EQUIPMENT));
    }

    public Vector<EquipmentType> getCrits() {
        return crits;
    }
}