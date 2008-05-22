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

package megameklab.com.ui.util;


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

    final String[] columnNames = {
            "Name",
            "Tonnage",
            "Crits",
    };

    final String[] longValues = {
            "XXXXXXXXX",
            "XXXXXXXXX",
            "XXXXXXXXX",
    };

    public int getColumnCount() {
        return this.columnNames.length;
    }

    public CriticalTableModel(Mech unit)
    {
        this.unit = unit;
    }

    public void refreshModel() {
        //do a resort
        this.sortedEquipment = new EquipmentType[] {};
        if ( this.crits.size() > 0 ){
            this.sortedEquipment = this.crits.toArray(sortedEquipment);
        }
        this.fireTableDataChanged();
    }

    public void initColumnSizes(JTable table) {
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        CriticalTableModel model = this;
        for (int i = 0; i < this.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            comp = table.getDefaultRenderer(model.getColumnClass(i)).
            getTableCellRendererComponent(
                    table, longValues[i],
                    false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public int getRowCount() {
        int count = this.sortedEquipment.length;
        return this.sortedEquipment.length;
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
        if (row < 0) return "";
        if (row >= sortedEquipment.length) return "";
        EquipmentType crit = this.sortedEquipment[row];
        switch (col) {
            case NAME:
                return crit.getName();
            case TONNAGE:
                return crit.getTonnage(unit);
            case CRITS:
                return new Integer(crit.getCriticals(unit));
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
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component d =  super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

            JLabel c = new JLabel(); //use a new label for everything (should be made better later)
            c.setOpaque(true);
            if (crits.size() < row || row < 0) return c;
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
    
    public void addCrit(EquipmentType eq){
        crits.add(eq);
    }
    
    public void removeCrit(int location){
        crits.removeElementAt(location);
    }
    
    public void removeAllCrits(){
        crits.removeAllElements();
    }
}