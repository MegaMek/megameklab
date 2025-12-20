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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.HandheldWeapon;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.Mek;
import megamek.common.units.Tank;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestProtoMek;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.util.UnitUtil;

public class CriticalTableModel extends AbstractTableModel {
    private final List<Mounted<?>> crits = new ArrayList<>();
    public Entity unit;

    public static final int NAME = 0;
    public static final int TONNAGE = 1;
    public static final int CRITS = 2;
    public static final int HEAT = 3;
    public static final int LOCATION = 4;
    public static final int SIZE = 5;
    // This column is never displayed
    public static final int EQUIPMENT = 6;
    public static final int N_COLS = 3;
    public static final int N_COLS_WEAPON_TABLE = 6;

    public static final int EQUIPMENT_TABLE = 0;
    public static final int WEAPON_TABLE = 1;
    public static final int BUILD_TABLE = 2;

    private final int tableType;
    private boolean kgStandard;

    private final String[] columnNames = { "Name", "Tons", "Slots", "Heat", "Loc", "Size" };

    private final String[] longValues = { "XXXXXXXXX", "XXXXXXXXX", "XXXXXXXXX",
                                          "XXXXXXXXX", "XXX", "XXXX" };

    @Override
    public int getColumnCount() {
        if (tableType != WEAPON_TABLE) {
            return N_COLS;
        } else {
            return N_COLS_WEAPON_TABLE;
        }
    }

    public CriticalTableModel(Entity unit, int tableType) {
        this.tableType = tableType;
        kgStandard = TestEntity.usesKgStandard(unit);

        if ((unit instanceof Mek) || unit.isSupportVehicle()) {
            columnNames[CRITS] = "Crits";
        }

        this.unit = unit;
    }

    public void updateUnit(Entity unit) {
        this.unit = unit;
    }

    public void refreshModel() {
        // Support vehicle may switch between kg and ton standards. Other units will be
        // constant
        if (kgStandard != TestEntity.usesKgStandard(unit)) {
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
        } else if (col == SIZE && unit.isHandheldWeapon()) {
            return "Shots";
        } else {
            return (columnNames[col]);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == SIZE) {
            return (row >= 0) && (row < crits.size())
                  && (crits.get(row).getType().isVariableSize()
                  || (crits.get(row).getType() instanceof InfantryWeapon)
                  || ((unit instanceof HandheldWeapon || unit instanceof BattleArmor)
                  && crits.get(row) instanceof AmmoMounted));
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
        Mounted<?> crit = crits.get(row);
        switch (col) {
            case NAME:
                return UnitUtil.getCritName(unit, crit.getType());
            case TONNAGE:
                double tonnage;
                if ((unit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)
                      || unit.hasETypeFlag(Entity.ETYPE_PROTOMEK))
                      && (crit.getType() instanceof AmmoType)) {
                    tonnage = ((AmmoType) crit.getType()).getKgPerShot() *
                          crit.getBaseShotsLeft() / 1000;
                } else if (crit.is(EquipmentTypeLookup.BA_DWP) && (crit.getLinked() != null)) {
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
                if (unit.hasETypeFlag(Entity.ETYPE_PROTOMEK)) {
                    return TestProtoMek.requiresSlot(crit.getType()) ? 1 : 0;
                }
                if (unit.usesWeaponBays() && (crit.getType() instanceof AmmoType)) {
                    return crit.getUsableShotsLeft() / ((AmmoType) crit.getType()).getShots();
                }
                if (tableType == BUILD_TABLE) {
                    return UnitUtil.getCritsUsed(crit);
                }
                return crit.getNumCriticalSlots();
            case EQUIPMENT:
                return crit;
            case HEAT:
                if (crit.getType() instanceof WeaponType) {
                    return crit.getType().getHeat();
                }
                return 0;
            case LOCATION:
                if (unit instanceof BattleArmor) {
                    return BattleArmor.getBaMountLocName(crit.getBaMountLoc());
                } else {
                    return unit.joinLocationAbbr(crit.allLocations(), 2);
                }
            case SIZE:
                if (crit.getType().isVariableSize() || (unit instanceof HandheldWeapon
                      && crit instanceof AmmoMounted)) {
                    return NumberFormat.getInstance().format(crit.getSize());
                } else if (unit instanceof BattleArmor && crit instanceof AmmoMounted am) {
                    return NumberFormat.getInstance().format(am.getOriginalShots());
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
            Mounted<?> crit = crits.get(rowIndex);
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
            } else if ((unit instanceof HandheldWeapon || unit instanceof BattleArmor)
                  && crit instanceof AmmoMounted am) {
                int shots = Integer.parseInt(aValue.toString());
                if (am.getSize() == shots) {
                    return;
                }
                am.setAmmoCapacity(shots);
                am.setOriginalShots(shots);
                am.setShotsLeft(shots);
                fireTableDataChanged();
            }
        }
    }

    public Renderer getRenderer() {
        return new Renderer(this);
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
            Mounted<?> mounted = (Mounted<?>) getValueAt(row, EQUIPMENT);
            spinner.removeChangeListener(this);
            if (mounted.getType() instanceof InfantryWeapon) {
                final int clipSize = ((InfantryWeapon) mounted.getType()).getShots();
                spinner.setModel(new SpinnerNumberModel((int) ((mounted.getSize() * clipSize)),
                      clipSize, null, clipSize));
            } else if (unit instanceof HandheldWeapon && (mounted instanceof AmmoMounted am)) {
                spinner.setModel(new SpinnerNumberModel((int) am.getSize(), 1, null, 1));
            } else if ((unit instanceof BattleArmor && (mounted instanceof AmmoMounted am))) {
                int maxShots;
                if (am.getType().getAmmoType() == AmmoType.AmmoTypeEnum.BA_TUBE) {
                    maxShots = TestBattleArmor.NUM_SHOTS_PER_CRIT_TA;
                } else {
                    maxShots = TestBattleArmor.NUM_SHOTS_PER_CRIT;
                }
                spinner.setModel(new SpinnerNumberModel(am.getOriginalShots(), 1, maxShots, 1));
            } else {
                spinner.setModel(new SpinnerNumberModel(Double.valueOf(mounted.getSize()),
                      mounted.getType().variableStepSize(), mounted.getType().variableMaxSize(),
                      mounted.getType().variableStepSize()));
            }
            spinner.addChangeListener(this);
            return spinner;
        }
    }

    public void addCrit(Mounted<?> mount) {
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
              .toList());
    }

    public void removeAllCrits() {
        crits.clear();
    }

    public void removeMounted(int row) {
        UnitUtil.removeMounted(unit,
              (Mounted<?>) getValueAt(row, CriticalTableModel.EQUIPMENT));
    }

    public List<Mounted<?>> getCrits() {
        return crits;
    }

    int getAlignment(int col) {
        return switch (col) {
            case NAME -> SwingConstants.LEFT;
            case TONNAGE -> SwingConstants.RIGHT;
            default -> SwingConstants.CENTER;
        };
    }
}
