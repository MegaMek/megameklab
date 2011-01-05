/*
 * MegaMekLab - Copyright (C) 2011
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

package megameklab.com.util;

import java.awt.Component;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import megamek.common.AmmoType;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megamek.common.WeaponType;

public class EquipmentTableModel extends AbstractTableModel {

    /**
     * 
     */
    private static final long serialVersionUID = -485714883739423617L;

    public Vector<EquipmentType> sortedEquipment = new Vector<EquipmentType>();

    public final static int TYPE = 0;
    public final static int TECH = 1;
    public final static int RULES = 2;
    public final static int NAME = 3;
    public final static int ALIASES = 4;

    public final static int EQUIPMENTTABLE = 0;

    String[] columnNames =
        { "Type", "Tech", "Rules", "Name", "Aliases", };

    String[] longValues =
        { "X", "XXXX", "XXXX", "XXXX", "XXXX", };

    public int getColumnCount() {
        return columnNames.length;
    }

    public EquipmentTableModel() {
    }

    public void refreshModel(String filter) {
        // do a resort
        sortedEquipment.removeAllElements();
        for (Enumeration<EquipmentType> e = EquipmentType.getAllTypes(); e.hasMoreElements();) {
            EquipmentType type = e.nextElement();

            if (type.getName().toLowerCase().contains(filter.toLowerCase())) {
                sortedEquipment.add(type);
            } else {
                for (Enumeration<String> names = type.getNames(); names.hasMoreElements();) {
                    String name = names.nextElement();
                    if (name.toLowerCase().contains(filter.toLowerCase())) {
                        sortedEquipment.add(type);
                        break;
                    }
                }
            }
        }
        fireTableDataChanged();
    }

    public void refreshModel() {
        refreshModel("");
    }

    public void initColumnSizes(JTable table) {
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        EquipmentTableModel model = this;
        for (int i = 0; i < getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i], false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public int getRowCount() {
        return sortedEquipment.size();
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
        if (row >= sortedEquipment.size()) {
            return "";
        }
        EquipmentType type = sortedEquipment.get(row);
        switch (col) {
            case NAME:
                return type.getName();
            case TYPE:
                if (type instanceof AmmoType) {
                    return ("A");
                }
                if (type instanceof WeaponType) {
                    return ("W");
                }
                return ("M");
            case TECH:
                return TechConstants.getTechName(type.getTechLevel());
            case RULES:
                return TechConstants.getLevelName(type.getTechLevel());
            case ALIASES:
                StringBuffer aliases = new StringBuffer();
                for (Enumeration<String> names = type.getNames(); names.hasMoreElements();) {
                    String name = names.nextElement();
                    aliases.append(name);
                    aliases.append(", ");
                }
                return aliases.toString();
        }
        return "";
    }

    public EquipmentTableModel.Renderer getRenderer() {
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
            if ((sortedEquipment.size() < row) || (row < 0)) {
                return c;
            }
            if (table.getModel().getValueAt(row, column) != null) {
                c.setText(table.getModel().getValueAt(row, column).toString());
            }

            if (isSelected) {
                c.setForeground(d.getForeground());
                c.setBackground(d.getBackground());
                return c;
            }

            return c;
        }
    }

}