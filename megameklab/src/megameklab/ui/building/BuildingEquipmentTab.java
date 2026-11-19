/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package megameklab.ui.building;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.BuildingEquipmentType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.PowerGeneratorType;
import megamek.common.equipment.WeaponType;
import megamek.common.equipment.enums.StructureEngine;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.BuildingConstruction;
import megamek.common.units.Entity;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.BuildingUtil;
import megameklab.util.UnitUtil;

class BuildingEquipmentTab extends JPanel {
    static final String[] FACINGS = BuildingUtil.FACINGS.toArray(String[]::new);
    private static final String[] MOUNTS = { "Fixed", "Roof turret", "Pintle" };
    private final BuildingMainUI editor;
    private final Database database;
    private List<Mounted<?>> mounts = List.of();
    private final Loadout model = new Loadout();
    private final JTable table = new JTable(model);
    private final JCheckBox allLocations = new JCheckBox("Show all hexes and floors");

    BuildingEquipmentTab(BuildingMainUI editor) {
        this.editor = editor;
        database = new Database(editor);
        database.setRefresh(editor);
        JPanel loadout = new JPanel(new BorderLayout());
        loadout.setBorder(BorderFactory.createTitledBorder("Equipment placement"));
        allLocations.setName("Show all building locations");
        allLocations.addActionListener(e -> refreshPlacement());
        loadout.add(allLocations, BorderLayout.NORTH);
        table.setName("Building equipment");
        table.putClientProperty("terminateEditOnFocusLost", true);
        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setPreferredWidth(240);
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<>(FACINGS)));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JComboBox<>(MOUNTS)));
        loadout.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton remove = new JButton("Remove selected equipment");
        remove.addActionListener(e -> {
            for (int row : table.getSelectedRows()) {
                UnitUtil.removeMounted(editor.getEntity(), mounts.get(table.convertRowIndexToModel(row)));
            }
            editor.scheduleRefresh();
        });
        JPanel actions = new JPanel();
        actions.add(remove);
        JButton move = new JButton("Move to editing location");
        move.addActionListener(e -> {
            BuildingPlacementDialogs.stopEditing(table);
            for (int row : table.getSelectedRows()) {
                BuildingUtil.assignEquipment(editor.getEntity(), mounts.get(table.convertRowIndexToModel(row)), editor.selectedLocation());
            }
            editor.scheduleRefresh();
        });
        actions.add(move);
        JButton distribute = new JButton("Distribute equipment mass…");
        distribute.addActionListener(e -> {
            if (table.getSelectedRow() >= 0) {
                Mounted<?> mount = mounts.get(table.convertRowIndexToModel(table.getSelectedRow()));
                if (BuildingConstruction.canSpread(mount.getType())) {
                    BuildingPlacementDialogs.equipment(editor, mount);
                } else {
                    JOptionPane.showMessageDialog(this, "Generators, capital weapons and decks can span multiple hexes.");
                }
            }
        });
        actions.add(distribute);
        JButton sizeGenerator = new JButton("Size generator for building");
        sizeGenerator.addActionListener(e -> {
            if (table.getSelectedRow() >= 0) {
                Mounted<?> mount = mounts.get(table.convertRowIndexToModel(table.getSelectedRow()));
                if (mount.getType() instanceof PowerGeneratorType generator) {
                    UnitUtil.resizeMount(mount, BuildingConstruction.generatorTons(editor.getEntity(), generator.getStructureEngine()));
                    editor.scheduleRefresh();
                }
            }
        });
        actions.add(sizeGenerator);
        loadout.add(actions, BorderLayout.SOUTH);
        loadout.setPreferredSize(new Dimension(850, 250));
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, database, loadout);
        split.setResizeWeight(.6);
        setLayout(new BorderLayout());
        add(split, BorderLayout.CENTER);
    }

    void refresh() {
        refreshPlacement();
        database.refreshTable();
    }

    void refreshPlacement() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        mounts = editor.getEntity().getEquipment().stream().filter(m -> !m.isOneShotAmmo() && !m.isWeaponGroup()
              && (allLocations.isSelected() || BuildingConstruction.equipmentPositions(editor.getEntity(), m).stream()
                    .anyMatch(p -> p.hex().equals(editor.selectedHex()) && p.level() == editor.selectedFloor()))).toList();
        JComboBox<String> locations = new JComboBox<>();
        for (int loc = 0; loc < editor.getEntity().locations(); loc++) {
            locations.addItem(BuildingUtil.locationLabel(editor.getEntity(), loc));
        }
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(locations));
        model.fireTableDataChanged();
    }

    private class Loadout extends AbstractTableModel {
        private static final String[] COLUMNS = { "Equipment", "Primary hex/level", "Facing", "Mount", "Size", "Shots", "Item tons", "Automated", "PCMT source (t)" };

        @Override
        public Class<?> getColumnClass(int column) {
            return column == 7 ? Boolean.class : Object.class;
        }

        @Override
        public int getRowCount() {
            return mounts.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            Mounted<?> mount = mounts.get(row);
            return switch (column) {
                case 0 -> mount.getName();
                case 1 -> BuildingUtil.locationLabel(editor.getEntity(), mount.getLocation());
                case 2 -> BuildingConstruction.isCapital(mount.getType()) ? "Upward"
                      : mount.isSponsonTurretMounted() ? "360°"
                      : mount.getType() instanceof WeaponType && mount.getFacing() >= 0 ? FACINGS[mount.getFacing() % 6] : "";
                case 3 -> mount.getType() instanceof WeaponType
                      ? MOUNTS[mount.isPintleTurretMounted() ? 2 : mount.isSponsonTurretMounted() ? 1 : 0] : "";
                case 4 -> mount.getType().isVariableSize() ? mount.getSize() : "";
                case 5 -> mount.getType() instanceof AmmoType ? mount.getBaseShotsLeft() : "";
                case 6 -> mount.getTonnage();
                case 7 -> editor.getEntity().getDesign().getAutomatedWeapons().contains(mount);
                case 8 -> isPcmt(mount.getType()) ? editor.getEntity().getDesign().getPcmtSources().getOrDefault(mount, 0.0) : "";
                default -> "";
            };
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            EquipmentType equipment = mounts.get(row).getType();
            return column == 1 || (column == 3 && equipment instanceof WeaponType)
                  || (column == 2 && equipment instanceof WeaponType && !BuildingConstruction.isCapital(equipment)
                        && !mounts.get(row).isSponsonTurretMounted())
                  || (column == 4 && equipment.isVariableSize()) || (column == 5 && equipment instanceof AmmoType)
                  || (column == 7 && BuildingConstruction.canAutomate(equipment)) || (column == 8 && isPcmt(equipment));
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            Mounted<?> mount = mounts.get(row);
            switch (column) {
                case 1 -> {
                    int location = Entity.LOC_NONE;
                    for (int loc = 0; loc < editor.getEntity().locations(); loc++) {
                        if (BuildingUtil.locationLabel(editor.getEntity(), loc).equals(value)) {
                            location = loc;
                            break;
                        }
                    }
                    if (location != Entity.LOC_NONE) {
                        BuildingUtil.assignEquipment(editor.getEntity(), mount, location);
                    }
                }
                case 2 -> mount.setFacing(List.of(FACINGS).indexOf(value));
                case 3 -> {
                    mount.setSponsonTurretMounted(MOUNTS[1].equals(value));
                    mount.setPintleTurretMounted(MOUNTS[2].equals(value));
                }
                case 7 -> {
                    if (Boolean.TRUE.equals(value)) {
                        editor.getEntity().getDesign().getAutomatedWeapons().add(mount);
                    } else {
                        editor.getEntity().getDesign().getAutomatedWeapons().remove(mount);
                    }
                }
                case 4, 5, 8 -> {
                    try {
                        double number = Double.parseDouble(value.toString());
                        if (!Double.isFinite(number) || number < 0 || (column == 5 && number != Math.rint(number))) {
                            throw new NumberFormatException();
                        }
                        if (column == 4) {
                            double step = mount.getType().variableStepSize();
                            number = Math.max(step, mount.getType() instanceof PowerGeneratorType ? Math.ceil(number)
                                  : Math.floor(number / step) * step);
                            if (mount.getType().variableMaxSize() != null) {
                                number = Math.min(number, mount.getType().variableMaxSize());
                            }
                            UnitUtil.resizeMount(mount, number);
                        } else if (column == 5) {
                            number = Math.min(number, ((AmmoType) mount.getType()).getShots());
                            mount.setOriginalShots((int) number);
                            mount.setShotsLeft((int) number);
                        } else {
                            editor.getEntity().getDesign().getPcmtSources().put(mount, number);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(BuildingEquipmentTab.this,
                              column == 5 ? "Enter a non-negative whole number of shots."
                                    : column == 8 ? "Enter a non-negative transmitter mass in tons." : "Enter a non-negative size.");
                        return;
                    }
                }
                default -> {
                    return;
                }
            }
            editor.scheduleRefresh();
        }
    }

    private static boolean isPcmt(EquipmentType equipment) {
        return equipment instanceof PowerGeneratorType generator && generator.getStructureEngine() == StructureEngine.EXTERNAL_PCMT;
    }

    private static class Database extends AbstractEquipmentDatabaseView {
        private final BuildingMainUI editor;

        Database(BuildingMainUI editor) {
            super(editor);
            this.editor = editor;
            setBorder(BorderFactory.createTitledBorder("Equipment Database"));
        }

        @Override
        protected Collection<Integer> getVisibleTableColumns(boolean stats) {
            return stats ? List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_RANGE, COL_SHOTS, COL_TECH, COL_TON, COL_REF)
                  : List.of(COL_NAME, COL_TECH, COL_TECH_LEVEL, COL_TECH_RATING, COL_DATE_PROTOTYPE,
                        COL_DATE_PRODUCTION, COL_DATE_COMMON, COL_COST);
        }

        @Override
        protected boolean shouldShow(EquipmentType equipment) {
            return getEntity() instanceof AbstractBuildingEntity building && !BuildingConstruction.hasNoInterior(building)
                  && BuildingConstruction.canMount(equipment) && super.shouldShow(equipment);
        }

        @Override
        protected void addEquipment(EquipmentType equipment, int count) {
            for (int i = 0; i < count; i++) {
                try {
                    Mounted<?> mount = Mounted.createMounted(getEntity(), equipment);
                    UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
                    if (equipment instanceof BuildingEquipmentType facility
                          && facility.getFacility() == BuildingEquipmentType.Facility.LANDING_DECK) {
                        mount.setSize(7);
                    }
                    int location = editor.selectedLocation();
                    getEntity().addEquipment(mount, location, false);
                    mount.setFacing(BuildingConstruction.isCapital(equipment) ? -1
                          : BuildingUtil.exteriorFacing(editor.getEntity(), editor.selectedHex()));
                    if (equipment instanceof PowerGeneratorType generator) {
                        UnitUtil.resizeMount(mount, BuildingConstruction.generatorTons(editor.getEntity(), generator.getStructureEngine()));
                    }
                    UnitUtil.removeHiddenAmmo(mount);
                    UnitUtil.changeMountStatus(getEntity(), mount, location, Entity.LOC_NONE, false);
                } catch (LocationFullException ex) {
                    throw new IllegalStateException("Unable to add building equipment", ex);
                }
            }
        }
    }
}
