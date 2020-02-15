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
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.WeaponType;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtomech;

public class CriticalTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 7615555055651822051L;

    private Mounted[] sortedEquipment = {};
    public Vector<Mounted> crits = new Vector<Mounted>();
    public Entity unit;

    public static final int NAME = 0;
    public static final int TONNAGE = 1;
    public static final int CRITS = 2;
    public static final int HEAT = 3;
    public static final int LOCATION = 4;
    public static final int EQUIPMENT = 5;

    public static final int EQUIPMENTTABLE = 0;
    public static final int WEAPONTABLE = 1;
    public static final int BUILDTABLE = 2;

    private int tableType = EQUIPMENTTABLE;
    private boolean kgStandard = false;

    private String[] columnNames = { "Name", "Tons", "Crits"};

    private String[] longValues = { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX"};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public CriticalTableModel(Entity unit, int tableType) {
        this.tableType = tableType;
        kgStandard = TestEntity.usesKgStandard(unit);

        if (tableType == WEAPONTABLE) {
            longValues = new String[] { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX",
                    "XXXXXXXXX", "XXX" };
            columnNames = new String[] { "Name", "Tons", "Slots", "Heat", 
                    "Loc" };
        }
        
        if (kgStandard) {
            columnNames[TONNAGE] = "Kg";
        }
        if ((unit instanceof Mech) || unit.isSupportVehicle()) {
            columnNames[CRITS] = "Crits";
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
        TableColumn column;
        Component comp;
        int headerWidth = 0;
        int cellWidth;
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

    @Override
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

    @Override
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
            double tonnage;
            if ((unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)
                    || unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) 
                    && (crit.getType() instanceof AmmoType)){
                tonnage = ((AmmoType)crit.getType()).getKgPerShot() * 
                        crit.getBaseShotsLeft() / 1000;
            } else if (crit.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                    && crit.getLinked() != null){
                tonnage = crit.getLinked().getType().getTonnage(unit) * 0.75;
            } else if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                // Round up to the next half ton
                tonnage = Math.ceil((crit.getType().getTonnage(unit) * crit.getUsableShotsLeft()
                        / ((AmmoType)crit.getType()).getShots()) * 2.0) * 0.5;
            } else {
                tonnage = crit.getType().getTonnage(unit);
            }
            if (kgStandard) {
                return Math.round(tonnage * 1000);
            } else {
                return tonnage;
            }
        case CRITS:
            if (unit.isSupportVehicle()) {
                return crit.getType().getSupportVeeSlots(unit);
            }
            if (unit instanceof Tank) {
                return crit.getType().getTankslots(unit);
            }
            if (unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
                return TestProtomech.requiresSlot(crit.getType())? 1 : 0;
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
                return ((WeaponType) crit.getType()).getHeat();
            }
            return 0;
        case LOCATION:
            if (unit instanceof BattleArmor){
                return BattleArmor.getBaMountLocAbbr(crit
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
            
            if ((crits.size() < row) || (row < 0)) {
                return c;
            }
            if (table.getModel().getValueAt(row, column) != null) {
                c.setText(table.getModel().getValueAt(row, column).toString());
            }

            Mounted mount = sortedEquipment[row];
            if ((unit instanceof BattleArmor) && column == NAME){
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
            } else if ((column == NAME) && unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)
                    && (mount.getType() instanceof AmmoType)) {
                c.setText(c.getText() + " (" + mount.getBaseShotsLeft() + ")");
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
    public void removeCrits(int[] locs) {
        Vector<Mounted> mounts = new Vector<>(locs.length);
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

    private int getAlignment(int col) {
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