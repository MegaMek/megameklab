/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.infantry;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import megamek.client.ui.models.XTableColumnModel;
import megamek.common.TechAdvancement;
import megamek.common.units.Infantry;
import megamek.common.verifier.TestInfantry;
import megameklab.ui.EntitySource;
import megameklab.ui.listeners.InfantryBuildListener;
import megameklab.ui.util.IView;
import megameklab.util.InfantryUtil;

/**
 * View for selecting infantry specializations, including Xeno-Planetary conditions training (XCT).
 *
 * @author Neoancient
 */
public class CISpecializationView extends IView implements TableModelListener {
    private final List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }

    private final SpecializationModel model;
    private final TableRowSorter<SpecializationModel> sorter;

    public CISpecializationView(EntitySource eSource) {
        super(eSource);

        model = new SpecializationModel();
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        XTableColumnModel equipColumnModel = new XTableColumnModel();
        table.setColumnModel(equipColumnModel);
        table.createDefaultColumnsFromModel();
        TableColumn column;
        for (int i = 1; i < model.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnWidth(i));
            column.setCellRenderer(model.getRenderer());
        }
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(false);
        table.setDoubleBuffered(true);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table);

        model.addTableModelListener(this);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(scroll);
        add(Box.createHorizontalGlue());
    }

    public void refresh() {
        filterSpecializations();
    }

    private void filterSpecializations() {
        RowFilter<SpecializationModel, Integer> filter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends SpecializationModel, ? extends Integer> entry) {
                SpecializationModel specModel = entry.getModel();
                TechAdvancement techAdvancement = specModel.getTechAdvancement(entry.getIdentifier());
                final int bitFlag = 1 << entry.getIdentifier();
                if ((bitFlag == Infantry.TAG_TROOPS)
                      && (TestInfantry.maxSecondaryWeapons(getInfantry()) < 2)) {
                    return false;
                }
                if (((bitFlag == Infantry.PARATROOPS) || (bitFlag == Infantry.MOUNTAIN_TROOPS))
                      && !getInfantry().getMovementMode().isLegInfantry()) {
                    return false;
                }
                return (null != eSource.getTechManager())
                      && eSource.getTechManager().isLegal(techAdvancement);
            }
        };
        sorter.setRowFilter(filter);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        listeners.forEach(InfantryBuildListener::specializationsChanged);
    }

    private class SpecializationModel extends AbstractTableModel {
        // Don't include SCUBA
        private final String[][] rows = new String[Infantry.NUM_SPECIALIZATIONS - 1][];
        private final TechAdvancement[] specTAs = new TechAdvancement[Infantry.NUM_SPECIALIZATIONS];
        private final String[] tooltips = new String[Infantry.NUM_SPECIALIZATIONS - 1];

        SpecializationModel() {
            super();
            for (int i = 0; i < rows.length; i++) {
                int spec = 1 << i;
                String[] fields = new String[4];
                fields[0] = Infantry.getSpecializationName(spec);
                if ((spec == Infantry.PARATROOPS) || (spec == Infantry.TAG_TROOPS)
                      || (spec == Infantry.XCT)) {
                    fields[1] = "-";
                    fields[2] = "-";
                    fields[3] = "-";
                } else if (spec == Infantry.MARINES) {
                    fields[1] = "10";
                    fields[2] = "4";
                    fields[3] = "2";
                } else if (spec == Infantry.MOUNTAIN_TROOPS) {
                    fields[1] = "10";
                    fields[2] = "2";
                    fields[3] = "1";
                } else if (spec == Infantry.PARAMEDICS) {
                    fields[1] = "10";
                    fields[2] = "4";
                    fields[3] = "1";
                } else {
                    fields[1] = "10";
                    fields[2] = "2";
                    fields[3] = "0";
                }
                rows[i] = fields;
                if ((Infantry.COMBAT_ENGINEERS & spec) != 0) {
                    specTAs[i] = Infantry.getCombatEngineerTA();
                } else if (Infantry.MARINES == spec) {
                    specTAs[i] = Infantry.getMarineTA();
                } else if (Infantry.MOUNTAIN_TROOPS == spec) {
                    specTAs[i] = Infantry.getMountainTA();
                } else if (Infantry.PARATROOPS == spec) {
                    specTAs[i] = Infantry.getParatrooperTA();
                } else if (Infantry.PARAMEDICS == spec) {
                    specTAs[i] = Infantry.getParamedicTA();
                } else if (Infantry.TAG_TROOPS == spec) {
                    specTAs[i] = Infantry.getTAGTroopsTA();
                } else {
                    specTAs[i] = getInfantry().getConstructionTechAdvancement();
                }
            }
        }

        TechAdvancement getTechAdvancement(int row) {
            return specTAs[row];
        }

        @Override
        public int getRowCount() {
            return rows.length;
        }

        @Override
        public int getColumnCount() {
            return rows[0].length + 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return getInfantry().hasSpecialization(1 << rowIndex);
            } else {
                return rows[rowIndex][columnIndex - 1];
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Boolean.class;
            } else {
                return String.class;
            }
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case 1 -> "Specialization";
                case 2 -> "Max Squad Size";
                case 3 -> "Max # Squads";
                case 4 -> "Max Secondary Weapons";
                default -> "";
            };
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            int spec = 1 << row;
            if ((Boolean) model.getValueAt(row, 0)) {
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~spec);
            } else {
                getInfantry().setSpecializations(getInfantry().getSpecializations() | spec);
            }
            // If we have selected a specialization that does not allow two support weapons
            // we need to remove TAG if present.
            if ((Infantry.TAG_TROOPS != spec)
                  && getInfantry().hasSpecialization(Infantry.TAG_TROOPS)
                  && TestInfantry.maxSecondaryWeapons(getInfantry()) < 2) {
                InfantryUtil.replaceMainWeapon(getInfantry(), null, true);
                getInfantry().setSecondaryWeaponsPerSquad(0);
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.TAG_TROOPS);
            } else if (TestInfantry.maxSecondaryWeapons(getInfantry()) > getInfantry().getSecondaryWeaponsPerSquad()) {
                getInfantry().setSecondaryWeaponsPerSquad(TestInfantry.maxSecondaryWeapons(getInfantry()));
                if (getInfantry().getSecondaryWeaponsPerSquad() == 0) {
                    InfantryUtil.replaceMainWeapon(getInfantry(), null, true);
                }
            }
            fireTableCellUpdated(row, col);
        }

        public int getColumnWidth(int c) {
            if (c == 1) {
                return 512;
            } else {
                return 256;
            }
        }

        public int getAlignment(int col) {
            if (col == 1) {
                return SwingConstants.LEFT;
            } else {
                return SwingConstants.CENTER;
            }
        }

        public String getTooltip(int row, int col) {
            return tooltips[row];
        }

        public SpecializationModel.Renderer getRenderer() {
            return new SpecializationModel.Renderer();
        }

        public class Renderer extends DefaultTableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                  Object value, boolean isSelected, boolean hasFocus, int row,
                  int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                      hasFocus, row, column);
                int actualCol = table.convertColumnIndexToModel(column);
                int actualRow = table.convertRowIndexToModel(row);
                setHorizontalAlignment(getAlignment(actualCol));
                setToolTipText(getTooltip(actualRow, actualCol));
                return this;
            }
        }
    }
}
