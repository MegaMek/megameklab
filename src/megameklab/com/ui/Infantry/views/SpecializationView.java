/*
 * MegaMekLab - Copyright (C) 2017 The MegaMek Team
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
package megameklab.com.ui.Infantry.views;

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

import megamek.common.Infantry;
import megamek.common.TechAdvancement;
import megamek.common.verifier.TestInfantry;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.view.listeners.InfantryBuildListener;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.XTableColumnModel;

/**
 * View for selecting infantry specializations, including xenoplanetary conditions training (XCT).
 * 
 * @author Neoancient
 *
 */
public class SpecializationView extends IView implements TableModelListener {

    private static final long serialVersionUID = -5851020780074510576L;
    
    private List<InfantryBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(InfantryBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(InfantryBuildListener l) {
        listeners.remove(l);
    }

    private final JTable table = new JTable();
    private final SpecializationModel model;
    private final TableRowSorter<SpecializationModel> sorter;
    
    public SpecializationView(EntitySource eSource) {
        super(eSource);
        
        model = new SpecializationModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        XTableColumnModel equipColumnModel = new XTableColumnModel();
        table.setColumnModel(equipColumnModel);
        table.createDefaultColumnsFromModel();
        TableColumn column = null;
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
    
    public void addRefreshedListener(RefreshListener l) {
        // not used
    }
    
    public void refresh() {
        filterSpecializations();
    }
    
    private void filterSpecializations() {
        RowFilter<SpecializationModel, Integer> filter = new RowFilter<SpecializationModel, Integer>() {
            @Override
            public boolean include(Entry<? extends SpecializationModel, ? extends Integer> entry) {
                SpecializationModel specModel = entry.getModel();
                TechAdvancement techAdvancement = specModel.getTechAdvancement(entry.getIdentifier());
                if ((1 << entry.getIdentifier() == Infantry.TAG_TROOPS)
                        && TestInfantry.maxSecondaryWeapons(getInfantry()) < 2) {
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
        
        /**
         * 
         */
        private static final long serialVersionUID = -4321737516108874027L;
        
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
            switch (column) {
                case 1: return "Specialization";
                case 2: return "Max Squad Size";
                case 3: return "Max # Squads";
                case 4: return "Max Secondary Weapons";
                default: return "";
            }
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            int spec = 1 << row;
            if ((Boolean)model.getValueAt(row, 0)) {
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~spec);
            } else {
                getInfantry().setSpecializations(getInfantry().getSpecializations() | spec);
            }
            // If we have selected a specialization that does not allow two support weapons
            // we need to remove TAG if present.
            if ((Infantry.TAG_TROOPS != spec)
                    && getInfantry().hasSpecialization(Infantry.TAG_TROOPS)
                    && TestInfantry.maxSecondaryWeapons(getInfantry()) < 2) {
                UnitUtil.replaceMainWeapon(getInfantry(), null, true);
                getInfantry().setSecondaryN(0);
                getInfantry().setSpecializations(getInfantry().getSpecializations() & ~Infantry.TAG_TROOPS);
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

            private static final long serialVersionUID = 9054581142945717303L;

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row,
                    int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                setOpaque(true);
                // setFont(new Font("Arial", Font.PLAIN, 12));
                int actualCol = table.convertColumnIndexToModel(column);
                int actualRow = table.convertRowIndexToModel(row);
                setHorizontalAlignment(getAlignment(actualCol));
                setToolTipText(getTooltip(actualRow, actualCol));
                return this;
            }
        }

    }
}
