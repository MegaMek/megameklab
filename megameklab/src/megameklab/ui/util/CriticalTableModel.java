/*
 * MegaMekLab
 * Copyright (c) 2008-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtomech;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CriticalTableModel extends AbstractTableModel {
    private final List<Mounted> crits = new ArrayList<>();
    public Entity unit;

    public final static int NAME = 0;
    public final static int TONNAGE = 1;
    public final static int CRITS = 2;
    public final static int HEAT = 3;
    public final static int LOCATION = 4;
    public final static int SIZE = 5;
    // This column is never displayed
    public final static int EQUIPMENT = 6;
    public final static int N_COLS = 3;
    public final static int N_COLS_WEAPON_TABLE = 6;

    public final static int EQUIPMENTTABLE = 0;
    public final static int WEAPONTABLE = 1;
    public final static int BUILDTABLE = 2;

    private final int tableType;
    private boolean kgStandard;

    private final String[] columnNames = { "Name", "Tons", "Slots", "Heat", "Loc", "Size" };

    private final String[] longValues = { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX",
            "XXXXXXXXX", "XXX", "XXXX" };

    @Override
    public int getColumnCount() {
        if (tableType != WEAPONTABLE) {
            return N_COLS;
        } else {
            return N_COLS_WEAPON_TABLE;
        }
    }

    public CriticalTableModel(Entity unit, int tableType) {
        this.tableType = tableType;
        kgStandard = TestEntity.usesKgStandard(unit);

        if ((unit instanceof Mech) || unit.isSupportVehicle()) {
            columnNames[CRITS] = "Crits";
        }
        
        this.unit = unit;
    }

    public void updateUnit(Entity unit) {
        this.unit = unit;
    }

    public void refreshModel() {
        // Support vehicle may switch between kg and ton standards. Other units will be constant
        if (kgStandard != (unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)) {
            kgStandard = !kgStandard;
            fireTableStructureChanged();
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
        return crits.size();
    }

    @Override
    public String getColumnName(int col) {
        if ((col == TONNAGE) && kgStandard) {
            return "Kg";
        } else if ((col == SIZE) && (unit.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)) {
            return "Size/Shots";
        } else {
            return (columnNames[col]);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == SIZE) {
            return (row >= 0) && (row < crits.size())
                    && (crits.get(row).getType().isVariableSize()
                    || (crits.get(row).getType() instanceof InfantryWeapon));
        } else {
            return false;
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (row < 0) {
            return "";
        }
        if (row >= crits.size()) {
            return "";
        }
        Mounted crit = crits.get(row);
        switch (col) {
            case NAME:
                return UnitUtil.getCritName(unit, crit.getType());
            case TONNAGE:
                double tonnage;
                if ((unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)
                        || unit.hasETypeFlag(Entity.ETYPE_PROTOMECH))
                        && (crit.getType() instanceof AmmoType)) {
                    tonnage = ((AmmoType)crit.getType()).getKgPerShot() *
                            crit.getBaseShotsLeft() / 1000;
                } else if (crit.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                        && crit.getLinked() != null) {
                    tonnage = crit.getLinked().getTonnage() * 0.75;
                } else if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                    // Round up to the next half ton
                    tonnage = Math.ceil((crit.getTonnage() * crit.getUsableShotsLeft()
                            / ((AmmoType) crit.getType()).getShots()) * 2.0) * 0.5;
                } else {
                    tonnage = crit.getTonnage();
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
                    return crit.getType().getTankSlots(unit);
                }
                if (unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
                    return TestProtomech.requiresSlot(crit.getType())? 1 : 0;
                }
                if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                    return crit.getUsableShotsLeft() / ((AmmoType)crit.getType()).getShots();
                }
                if (tableType == BUILDTABLE) {
                    return UnitUtil.getCritsUsed(crit);
                }
                return crit.getCriticals();
            case EQUIPMENT:
                return crit;
            case HEAT:
                if (crit.getType() instanceof WeaponType) {
                    return crit.getType().getHeat();
                }
                return 0;
            case LOCATION:
                if (unit instanceof BattleArmor) {
                    return BattleArmor.getBaMountLocAbbr(crit.getBaMountLoc());
                } else {
                    return unit.joinLocationAbbr(crit.allLocations(), 2);
                }
            case SIZE:
                if (crit.getType().isVariableSize()) {
                    return NumberFormat.getInstance().format(crit.getSize());
                } else if (crit.getType() instanceof InfantryWeapon) {
                    return (int) (crit.getSize() * ((InfantryWeapon) crit.getType()).getShots());
                } else {
                    return null;
                }
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if ((rowIndex >= 0) && (rowIndex < getRowCount()) && (columnIndex == SIZE)) {
            Mounted crit = crits.get(rowIndex);
            if (crit.getType().isVariableSize()) {
                double newSize = Double.parseDouble(aValue.toString());
                double step = crit.getType().variableStepSize();
                newSize = Math.max(Math.floor(newSize / step) * step, step);
                if ((crit.getType().variableMaxSize() != null) && (newSize > crit.getType().variableMaxSize())) {
                    newSize = crit.getType().variableMaxSize();
                }
                if (crit.getSize() == newSize) {
                    return;
                }
                UnitUtil.resizeMount(crit, newSize);
                fireTableDataChanged();
            } else if (crit.getType() instanceof InfantryWeapon) {
                int shots = Integer.parseInt(aValue.toString());
                int clipSize = ((InfantryWeapon) crit.getType()).getShots();
                int newSize = Math.max(shots / clipSize, 1);
                if (crit.getSize() == newSize) {
                    return;
                }
                UnitUtil.resizeMount(crit, newSize);
                fireTableDataChanged();
            }
        }
    }

    public CriticalTableModel.Renderer getRenderer() {
        return new Renderer();
    }

    /*
     * Rendered cannot be static because it uses parent data structs.
     */
    private class Renderer extends DefaultTableCellRenderer {
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

            Mounted mount = crits.get(row);
            if ((unit instanceof BattleArmor) && column == NAME) {
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
            } else if ((column == NAME) && unit.hasETypeFlag(Entity.ETYPE_PROTOMECH)
                    && (mount.getType() instanceof AmmoType)) {
                c.setText(c.getText() + " (" + mount.getBaseShotsLeft() + ")");
            }
            c.setToolTipText(EquipmentToolTip.getToolTipInfo(unit, mount));
            c.setHorizontalAlignment(getAlignment(column));

            if (isSelected) {
                return c;
            }

            String equipmentType = CConfig.GUI_COLOR_EQUIPMENT;

            if (!mount.getType().isHittable()) {
                equipmentType = CConfig.GUI_COLOR_NONHITTABLE;
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

    /**
     * Cell editor for the size column
     */
    public class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor, ChangeListener {
        private final JSpinner spinner = new JSpinner();
        private int rowIndex = 0;

        public SpinnerCellEditor() {
            spinner.addChangeListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            setValueAt(getCellEditorValue(), rowIndex, SIZE);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {
            this.rowIndex = row;
            Mounted mounted = (Mounted) getValueAt(row, EQUIPMENT);
            spinner.removeChangeListener(this);
            if (mounted.getType() instanceof InfantryWeapon) {
                final int clipSize = ((InfantryWeapon) mounted.getType()).getShots();
                spinner.setModel(new SpinnerNumberModel((int) ((mounted.getSize() * clipSize)),
                        clipSize, null, clipSize));
            } else {
                spinner.setModel(new SpinnerNumberModel(Double.valueOf(mounted.getSize()),
                        mounted.getType().variableStepSize(), mounted.getType().variableMaxSize(),
                        mounted.getType().variableStepSize()));
            }
            spinner.addChangeListener(this);
            return spinner;
        }
    }

    public void addCrit(Mounted mount) {
        crits.add(mount);
    }

    public void removeCrit(int location) {
        crits.remove(location);
    }
    
    /**
     * Remove a collection of crits specified by the given list of indices.
     * 
     * @param locs An array of indices that specifies the crits to remove
     */
    public void removeCrits(int... locs) {
        crits.removeAll(Arrays.stream(locs)
                .mapToObj(crits::get)
                .collect(Collectors.toList()));
    }

    public void removeAllCrits() {
        crits.clear();
    }

    public void removeMounted(int row) {
        UnitUtil.removeMounted(unit,
                (Mounted) getValueAt(row, CriticalTableModel.EQUIPMENT));
    }

    public List<Mounted> getCrits() {
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