/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.Dropship.tabs;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import megamek.common.Bay;
import megamek.common.InfantryBay;
import megamek.common.verifier.TestAero;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;

/**
 * Tab for adding and modifying aerospace transport bays.
 * 
 * @author Neoancient
 *
 */
public class TransportTab extends IView {
    
    private final InstalledBaysModel modelInstalled = new InstalledBaysModel();
    private final JTable tblInstalled = new JTable(modelInstalled);
    private final AvailableBaysModel modelAvailable = new AvailableBaysModel();
    private final JTable tblAvailable = new JTable(modelAvailable);
    
    public TransportTab(EntitySource eSource) {
        super(eSource);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JScrollPane(tblInstalled));
        add(Box.createHorizontalGlue());
        add(new JScrollPane(tblAvailable));
        modelInstalled.refreshBays();
        modelAvailable.refreshBays();
    }
    
    private class InstalledBaysModel extends AbstractTableModel {
        
        private static final int COL_NAME      = 0;
        private static final int COL_SIZE      = 1;
        private static final int COL_DOORS     = 2;
        private static final int COL_PERSONNEL = 3;
        private static final int COL_TONNAGE   = 4;
        private static final int NUM_COLS      = 5;
        
        private final List<Bay> bayList = new ArrayList<>();
        private final List<TestAero.TransportBay> bayTypeList = new ArrayList<>();
        
        public void refreshBays() {
            bayList.clear();
            bayTypeList.clear();
            for (Bay bay : getAero().getTransportBays()) {
                TestAero.TransportBay bayType = TestAero.TransportBay.getBayType(bay);
                bayList.add(bay);
                bayTypeList.add(bayType);
            }
            fireTableDataChanged();
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column) {
                case COL_NAME:
                    return "Bay Type";
                case COL_SIZE:
                    return "Size";
                case COL_DOORS:
                    return "Doors";
                case COL_TONNAGE:
                    return "Tonnage";
                case COL_PERSONNEL:
                    return "Personnel";
            }
            return "";
        }

        @Override
        public int getRowCount() {
            return bayList.size();
        }
        
        @Override
        public int getColumnCount() {
            return NUM_COLS;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case COL_NAME:
                    return bayTypeList.get(rowIndex).getDisplayName();
                case COL_SIZE:
                    return bayList.get(rowIndex).getUnusedSlots();
                case COL_DOORS:
                    return bayList.get(rowIndex).getDoors();
                case COL_TONNAGE:
                    return bayList.get(rowIndex).getWeight();
                case COL_PERSONNEL:
                    if (bayList.get(rowIndex) instanceof InfantryBay) {
                        return "*";
                    }
                    return bayTypeList.get(rowIndex).getPersonnel()
                            * (int)bayList.get(rowIndex).getCapacity();
                default:
                    return null;
            }
        }

    }
    
    private class AvailableBaysModel extends AbstractTableModel {
        
        private static final int COL_NAME      = 0;
        private static final int COL_SIZE      = 1;
        private static final int COL_PERSONNEL = 2;
        private static final int NUM_COLS      = 3;
        
        private List<TestAero.TransportBay> bayList = new ArrayList<>();
        
        public void refreshBays() {
            bayList.clear();
            for (TestAero.TransportBay bay : TestAero.TransportBay.values()) {
                if (eSource.getTechManager().isLegal(bay.getTechAdvancement())) {
                    bayList.add(bay);
                }
            }
            fireTableDataChanged();
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case COL_NAME:
                    return "Bay Type";
                case COL_SIZE:
                    return "Unit Weight";
                case COL_PERSONNEL:
                    return "Personnel";
            }
            return "";
        }

        @Override
        public int getRowCount() {
            return bayList.size();
        }

        @Override
        public int getColumnCount() {
            return NUM_COLS;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < bayList.size()) {
                switch (columnIndex) {
                    case COL_NAME:
                        return bayList.get(rowIndex).getDisplayName();
                    case COL_SIZE:
                        return bayList.get(rowIndex).getWeight();
                    case COL_PERSONNEL:
                        if (bayList.get(rowIndex).getDisplayName().startsWith("Infantry")) {
                            return "*";
                        }
                        return bayList.get(rowIndex).getPersonnel();
                }
            }            
            return "?";
        }
        
    }

}
