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

import java.awt.Component;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.WeaponType;

public class CriticalTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 7615555055651822051L;

    public Mounted[] sortedEquipment = {};
    public Vector<Mounted> crits = new Vector<Mounted>();
    public Entity unit;

    public final static int NAME = 0;
    public final static int TONNAGE = 1;
    public final static int CRITS = 2;
    public final static int HEAT = 3;
    public final static int LOCATION = 4;
    public final static int EQUIPMENT = 5;

    public final static int EQUIPMENTTABLE = 0;
    public final static int WEAPONTABLE = 1;
    public final static int BUILDTABLE = 2;

    private int tableType = EQUIPMENTTABLE;

    String[] columnNames = { "Name", "Tons", "Crits"};

    String[] longValues = { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX"};

    public int getColumnCount() {
        return columnNames.length;
    }

    public CriticalTableModel(Entity unit, int tableType) {
        this.tableType = tableType;

        if (tableType == WEAPONTABLE) {
            longValues = new String[] { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX",
                    "XXXXXXXXX", "XXX" };
            columnNames = new String[] { "Name", "Tons", "Crits", "Heat", 
                    "Loc" };
        }
        
        if (unit instanceof Tank) {
            columnNames[CRITS] = "Slots";
        }
        
        this.unit = unit;
    }

    public void updateUnit(Entity unit) {
        this.unit = unit;
    }

    public void refreshModel() {
        // do a resort
        sortedEquipment = new Mounted[] {};
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
            comp = table.getDefaultRenderer(model.getColumnClass(i))
                    .getTableCellRendererComponent(table, longValues[i], false,
                            false, 0, i);
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
        Mounted crit = sortedEquipment[row];
        switch (col) {
        case NAME:
            return UnitUtil.getCritName(unit, crit.getType());
        case TONNAGE:
            if ((unit instanceof BattleArmor) 
                    && (crit.getType() instanceof AmmoType)){
                return ((AmmoType)crit.getType()).getKgPerShot() * 
                        crit.getBaseShotsLeft() / 1000;
            } else if (crit.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && crit.getLinked() != null){
                return crit.getLinked().getType().getTonnage(unit) * 0.75;
            } else if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                return crit.getType().getTonnage(unit) * crit.getUsableShotsLeft()
                        / ((AmmoType)crit.getType()).getShots();
            } else {
                return crit.getType().getTonnage(unit);
            }
        case CRITS:
            if (unit instanceof Tank) {
                return crit.getType().getTankslots(unit);
            }
            if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                return crit.getUsableShotsLeft() / ((AmmoType)crit.getType()).getShots();
            }
            if (tableType == BUILDTABLE) {
                return UnitUtil.getCritsUsed(unit, crit.getType());
            }
            return crit.getType().getCriticals(unit);
        case EQUIPMENT:
            return crit;
        case HEAT:
            if (crit.getType() instanceof WeaponType) {
                return new Integer(((WeaponType) crit.getType()).getHeat());
            }
            return new Integer(0);
        case LOCATION:
            if (unit instanceof BattleArmor){
                return ((BattleArmor) unit).getBaMountLocAbbr(crit
                        .getBaMountLoc());
            } else {
                return unit.getLocationAbbr(crit.getLocation());
            }
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
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            JLabel c = (JLabel) super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, column);
            
            c.setOpaque(true);
            if ((crits.size() < row) || (row < 0)) {
                return c;
            }
            if (table.getModel().getValueAt(row, column) != null) {
                c.setText(table.getModel().getValueAt(row, column).toString());
            }

            Mounted mount = sortedEquipment[row];
            if (unit instanceof BattleArmor && column == NAME){
                String modifier = "";
                if (mount.getType() instanceof AmmoType){
                    modifier += " (" + mount.getBaseShotsLeft() + ")";
                }
                if (mount.getLocation() != BattleArmor.LOC_SQUAD) {
                    modifier += " (Personal)";
                } else {
                    modifier += " (Squad)";
                }
                if (mount.isDWPMounted()){
                    modifier += " (DWP)"; 
                }
                if (mount.isSquadSupportWeapon()){
                    modifier += " (Squad Support Weapon)"; 
                }
                if ((mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                        || mount.getType().hasFlag(MiscType.F_AP_MOUNT))
                        && mount.getLinked() != null){
                    modifier += " (attached " + mount.getLinked().getName() 
                            + ")";
                }
                if (mount.getType().hasFlag(WeaponType.F_INFANTRY) &&
                        mount.getLinkedBy() == null){
                    modifier += "*";
                }
                c.setText(c.getText() + modifier);
            }
            c.setToolTipText(UnitUtil.getToolTipInfo(unit, mount));
            c.setHorizontalAlignment(getAlignment(column));

            if (isSelected) {
                return c;
            }

            String equipmentType = CConfig.CONFIG_EQUIPMENT;

            if (mount.getType() instanceof WeaponType) {
                equipmentType = CConfig.CONFIG_WEAPONS;
            } else if (mount.getType() instanceof AmmoType) {
                equipmentType = CConfig.CONFIG_AMMO;
            }
            c.setBackground(CConfig.getBackgroundColor(equipmentType));
            c.setForeground(CConfig.getForegroundColor(equipmentType));
            return c;
        }
    }

    public void addCrit(Mounted mount) {
        crits.add(mount);
    }

    public void removeCrit(int location) {
        crits.removeElementAt(location);
    }
    
    /**
     * Remove a collection of crits specified by the given list of indices.
     * 
     * @param locs  An array of indices that specifies the crits to remove
     */
    public void removeCrits(int locs[]) {
        Vector<Mounted> mounts = new Vector<Mounted>(locs.length);
        for (Integer l : locs){
            mounts.add(crits.elementAt(l));
        }
        crits.removeAll(mounts);
    }

    public void removeAllCrits() {
        crits.removeAllElements();
    }

    public void removeMounted(int row) {
        UnitUtil.removeMounted(unit,
                (Mounted) getValueAt(row, CriticalTableModel.EQUIPMENT));
    }

    public Vector<Mounted> getCrits() {
        return crits;
    }

    public int getAlignment(int col) {
        switch (col) {
            case NAME:
                return SwingConstants.LEFT;
            case TONNAGE:
                return SwingConstants.RIGHT;
            default:
                return SwingConstants.CENTER;
        }
    }
}