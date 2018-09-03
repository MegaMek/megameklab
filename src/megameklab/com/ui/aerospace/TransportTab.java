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
package megameklab.com.ui.aerospace;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.TransferHandler;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import megamek.common.Bay;
import megamek.common.DockingCollar;
import megamek.common.Entity;
import megamek.common.InfantryBay;
import megamek.common.Jumpship;
import megamek.common.Transporter;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.BayData;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

/**
 * Tab for adding and modifying aerospace transport bays.
 * 
 * @author Neoancient
 *
 */
public class TransportTab extends IView implements ActionListener, ChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6288658666144030993L;
    
    private final JLabel lblDockingHardpoints = new JLabel();
    private final JLabel lblMaxHardpoints = new JLabel();
    private final SpinnerNumberModel spnHardpointsModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnDockingHardpoints = new JSpinner(spnHardpointsModel);
    private final JLabel lblMaxDoors = new JLabel();
    private final InstalledBaysModel modelInstalled = new InstalledBaysModel();
    private final JTable tblInstalled = new JTable(modelInstalled);
    private final AvailableBaysModel modelAvailable = new AvailableBaysModel();
    private final JTable tblAvailable = new JTable(modelAvailable);
    private final JButton btnRemoveBay = new JButton();
    private final JButton btnAddBay = new JButton();
    private final JButton btnAddToCargo = new JButton();
        
    private RefreshListener refresh = null;
    
    public TransportTab(EntitySource eSource) {
        super(eSource);
        initUI();
    }
    
    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs", new EncodeControl()); //$NON-NLS-1$
        
        setLayout(new BorderLayout());
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            JPanel panHardpoints = new JPanel();
            lblDockingHardpoints.setText(resourceMap.getString("TransportTab.spnDockingHardpoints.text")); //$NON-NLS-1$
            panHardpoints.add(lblDockingHardpoints);
            panHardpoints.add(spnDockingHardpoints);
            Dimension size = new Dimension(60, 25);
            spnDockingHardpoints.setPreferredSize(size);
            add(panHardpoints, BorderLayout.NORTH);
            spnDockingHardpoints.addChangeListener(this);
            panHardpoints.add(new JLabel(resourceMap.getString("TransportTab.spnMaxHardpoints.text"))); //$NON-NLS-1$
            panHardpoints.add(lblMaxHardpoints);
            lblMaxHardpoints.setToolTipText(resourceMap.getString("TransportTab.spnMaxHardpoints.tooltip")); //$NON-NLS-1$
        }
        
        JPanel bayPanel = new JPanel(new GridBagLayout());
        add(bayPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblCurrentBays.text")), gbc); //$NON-NLS-1$
        
        gbc.gridy++;
        gbc.gridwidth = 1;
        bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblMaxDoors.text")), gbc); //$NON-NLS-1$
        gbc.gridx = 1;
        bayPanel.add(lblMaxDoors, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        btnRemoveBay.setText(resourceMap.getString("TransportTab.btnRemoveBay.text")); //$NON-NLS-1$
        btnRemoveBay.setToolTipText(resourceMap.getString("TransportTab.btnRemoveBay.tooltip")); //$NON-NLS-1$
        bayPanel.add(btnRemoveBay, gbc);
        btnRemoveBay.addActionListener(this);
        
        gbc.gridx = 1;
        btnAddToCargo.setText(resourceMap.getString("TransportTab.btnAddToCargo.text")); //$NON-NLS-1$
        btnAddToCargo.setToolTipText(resourceMap.getString("TransportTab.btnAddToCargo.tooltip")); //$NON-NLS-1$
        bayPanel.add(btnAddToCargo, gbc);
        btnAddToCargo.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        bayPanel.add(new JScrollPane(tblInstalled), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblAvailableBays.text")), gbc); //$NON-NLS-1$
        
        gbc.gridy = 2;
        btnAddBay.setText(resourceMap.getString("TransportTab.btnAddBay.text")); //$NON-NLS-1$
        btnAddBay.setToolTipText(resourceMap.getString("TransportTab.btnAddBay.tooltip")); //$NON-NLS-1$
        bayPanel.add(btnAddBay, gbc);
        btnAddBay.addActionListener(this);
        
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        bayPanel.add(new JScrollPane(tblAvailable), gbc);
        
        tblInstalled.setRowHeight(24);
        TableColumn col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_SIZE);
        col.setCellEditor(new SpinnerCellEditor(InstalledBaysModel.COL_SIZE));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colSize.tooltip")); //$NON-NLS-1$
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_DOORS);
        col.setCellEditor(new SpinnerCellEditor(InstalledBaysModel.COL_DOORS));
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colDoors.tooltip")); //$NON-NLS-1$
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_TONNAGE);
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colTonnage.tooltip")); //$NON-NLS-1$
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_PERSONNEL);
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colPersonnel.tooltip")); //$NON-NLS-1$
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_FACING);
        if (getAero().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            JComboBox<Integer> cb = new JComboBox<>(new Integer[] { Jumpship.LOC_NOSE,
                    Jumpship.LOC_FLS, Jumpship.LOC_FRS,
                    Jumpship.LOC_ALS, Jumpship.LOC_ARS, Jumpship.LOC_AFT });
            col.setCellEditor(new DefaultCellEditor(cb));
            cb.setRenderer(new DefaultListCellRenderer() {
                private static final long serialVersionUID = 7950275386647167332L;

                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    int loc = Entity.LOC_NONE;
                    if (null != value) {
                        loc = (Integer) value;
                    }
                    setText(getAero().getLocationName(loc));
                    return this;
                }
                
            });
            renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText(resourceMap.getString("TransportTab.colFacing.tooltip"));
            col.setCellRenderer(renderer);
        } else {
            tblInstalled.getColumnModel().removeColumn(col);
        }
        tblInstalled.setShowGrid(false);
        tblInstalled.setIntercellSpacing(new Dimension(0, 0));
        tblAvailable.setShowGrid(false);
        tblAvailable.setIntercellSpacing(new Dimension(0, 0));
        
        tblInstalled.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblInstalled.getSelectionModel().addListSelectionListener(e -> checkButtons());
        tblAvailable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAvailable.getSelectionModel().addListSelectionListener(e -> checkButtons());
        
        tblInstalled.setDragEnabled(true);
        tblInstalled.setDropMode(DropMode.INSERT_ROWS);
        tblInstalled.setTransferHandler(new BayReorderTransferHandler());
        refresh();
    }
    
    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }
    
    public void refresh() {
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            int max = TestAdvancedAerospace.getMaxDockingHardpoints(getJumpship());
            spnHardpointsModel.setValue(Math.min(getJumpship().getDockingCollars().size(), max));
            spnHardpointsModel.setMaximum(max);
            lblMaxHardpoints.setText(Integer.toString(max));
        }
        
        lblMaxDoors.setText(String.valueOf(TestAero.maxBayDoors(getAero())));
        checkButtons();
        modelInstalled.refreshBays();
        modelAvailable.refreshBays();
        if (null != refresh) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();
        }
    }
    
    private void checkButtons() {
        btnRemoveBay.setEnabled(tblInstalled.getSelectedRow() >= 0);
        btnAddBay.setEnabled(canAddSelectedBay());
        btnAddToCargo.setEnabled(UnitUtil.getEntityVerifier(getAero()).calculateWeight() < getAero().getWeight());
    }
    
    private int doorsAvailable() {
        int total = TestAero.maxBayDoors(getAero());
        for (Bay bay : getAero().getTransportBays()) {
            total -= bay.getDoors();
        }
        return Math.max(total, 0);
    }
    
    private boolean canAddSelectedBay() {
        int selected = tblAvailable.getSelectedRow();
        if (selected < 0) {
            return false;
        }
        BayData bayType = modelAvailable.getBayType(tblAvailable.convertRowIndexToModel(selected));
        return (doorsAvailable() > 0) || (bayType == BayData.CARGO);
    }
    
    /**
     * Removes the bay from the vessel and adjusts the crew count.
     * 
     * @param bay
     */
    private void removeBay(Bay bay) {
        int personnel = bay.getPersonnel(getAero().isClan());
        if (getAero().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getSmallCraft().setNCrew(getSmallCraft().getNCrew() - personnel);
        }
        getAero().removeTransporter(bay);
    }
    
    /**
     * Adds the bay to the vessel and adjusts the crew count.
     * 
     * @param bay
     */
    private void addBay(Bay bay) {
        getAero().addTransporter(bay);
        int personnel = bay.getPersonnel(getAero().isClan());
        if (getAero().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getSmallCraft().setNCrew(getSmallCraft().getNCrew() + personnel);
        }
    }
    
    /**
     * Removing bays can cause undesirable gaps in bay numbers, and it would be nice to let the
     * user order the bays. Since bay numbers are immutable we have to instantiate a new bay to
     * alter it.
     */
    private void rebuildBays() {
        int bayNum = 1;
        List<Transporter> newTransporterList = new ArrayList<>();
        for (Iterator<Bay> iter = modelInstalled.getBays(); iter.hasNext(); ) {
            final Bay bay = iter.next();
            if (bay.getBayNumber() == bayNum) {
                newTransporterList.add(bay);
            } else {
                BayData bayType = BayData.getBayType(bay);
                Bay newBay = bayType.newBay(bay.getCapacity(), bayNum);
                newBay.setDoors(bay.getDoors());
                newTransporterList.add(newBay);
            }
            bayNum++;
        }
        for (Transporter transporter : getAero().getTransports()) {
            if (!(transporter instanceof Bay) || ((Bay) transporter).isQuarters()) {
                newTransporterList.add(transporter);
            }
        }
        getAero().removeAllTransporters();
        newTransporterList.forEach(b -> getAero().addTransporter(b));
        modelInstalled.refreshBays();
    }
    
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == btnAddBay) {
            int selected = tblAvailable.getSelectedRow();
            if (selected >= 0) {
                BayData bayType = modelAvailable.getBayType(tblAvailable.convertRowIndexToModel(selected));
                int num = 1;
                while (getAero().getBayById(num) != null) {
                    num++;
                }
                Bay newBay = bayType.newBay(1.0, num);
                if (doorsAvailable() > 0) {
                    newBay.setDoors(1);
                }
                addBay(newBay);
                modelInstalled.refreshBays();
                rebuildBays();
                refresh();
            }
        } else if (ev.getSource() == btnRemoveBay) {
            int selected = tblInstalled.getSelectedRow();
            if (selected >= 0) {
                Bay bay = modelInstalled.getBay(tblInstalled.convertRowIndexToModel(selected));
                removeBay(bay);
                modelInstalled.refreshBays();
                rebuildBays();
                refresh();
            }
        } else if (ev.getSource() == btnAddToCargo) {
            double size = getAero().getWeight() - UnitUtil.getEntityVerifier(getAero()).calculateWeight();
            if (size > 0) {
                int selected = tblInstalled.getSelectedRow();
                Bay bay = null;
                int bayNum = 1;
                if ((selected >= 0)
                        && (modelInstalled.getBayType(tblInstalled.convertRowIndexToModel(selected)) == BayData.CARGO)) {
                    bay = modelInstalled.getBay(tblInstalled.convertRowIndexToModel(selected));
                    size += bay.getCapacity();
                    bayNum = bay.getBayNumber();
                    removeBay(bay);
                } else {
                    while (getAero().getBayById(bayNum) != null) {
                        bayNum++;
                    }
                }
                bay = BayData.CARGO.newBay(size, bayNum);
                addBay(bay);
                refresh();
            }
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent ev) {
        if (ev.getSource() == spnDockingHardpoints) {
            int numCollars = spnHardpointsModel.getNumber().intValue();
            List<DockingCollar> collars = getJumpship().getDockingCollars();
            if (numCollars < collars.size()) {
                for (int i = 0; i < collars.size() - numCollars; i++) {
                    getJumpship().removeTransporter(collars.get(i));
                }
            } else {
                for (int i = 0; i < numCollars - collars.size(); i++) {
                    getJumpship().addTransporter(new DockingCollar(1));
                }
            }
            if (null != refresh) {
                refresh.refreshStructure();
                refresh.refreshStatus();
                refresh.refreshPreview();
            }
        }
    }
    
    private class InstalledBaysModel extends AbstractTableModel {
        
        /**
         * 
         */
        private static final long serialVersionUID = -8643492032818089043L;
        
        private static final int COL_NAME      = 0;
        private static final int COL_SIZE      = 1;
        private static final int COL_DOORS     = 2;
        private static final int COL_TONNAGE   = 3;
        private static final int COL_PERSONNEL = 4;
        private static final int COL_FACING    = 5;
        private static final int NUM_COLS      = 6;
        
        private final List<Bay> bayList = new ArrayList<>();
        private final List<BayData> bayTypeList = new ArrayList<>();
        
        public void refreshBays() {
            bayList.clear();
            bayTypeList.clear();
            // Find all the bays and sort them by bay number.
            // Entity.getTransportBays() iterates through all transports and builds a collection of
            // Bays so we're going to save ourselves a second list instantiation and iteration by
            // doing it all at once here.
            List<Bay> bays = getAero().getTransports().stream()
                    .filter(t -> (t instanceof Bay) && !((Bay) t).isQuarters())
                    .map(t -> (Bay) t).collect(Collectors.toList());
            Collections.sort(bays, (b1, b2) -> b1.getBayNumber() - b2.getBayNumber());
            for (Bay bay : bays) {
                BayData bayType = BayData.getBayType(bay);
                if (null != bayType) {
                    bayList.add(bay);
                    bayTypeList.add(bayType);
                }
            }
            fireTableDataChanged();
        }
        
        public Bay getBay(int row) {
            return bayList.get(row);
        }
        
        public Iterator<Bay> getBays() {
            return bayList.iterator();
        }
        
        public BayData getBayType(int row) {
            return bayTypeList.get(row);
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
                case COL_FACING:
                    return "Facing";
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
                    if (!bayTypeList.get(rowIndex).isCargoBay()) {
                        return (int)bayList.get(rowIndex).getUnusedSlots();
                    }
                    return bayList.get(rowIndex).getUnusedSlots();
                case COL_DOORS:
                    return bayList.get(rowIndex).getDoors();
                case COL_PERSONNEL:
                    if (bayList.get(rowIndex) instanceof InfantryBay) {
                        return "*";
                    }
                    return bayTypeList.get(rowIndex).getPersonnel()
                            * (int)bayList.get(rowIndex).getCapacity();
                case COL_TONNAGE:
                    if (!bayTypeList.get(rowIndex).isCargoBay()) {
                        return bayList.get(rowIndex).getWeight();
                    }
                    return Math.ceil(bayList.get(rowIndex).getWeight() * 2.0) * 0.5;
                case COL_FACING:
                    if (bayTypeList.get(rowIndex).requiresFacing()) {
                        return getAero().getLocationAbbr(bayList.get(rowIndex).getFacing());
                    } else {
                        return "";
                    }
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (columnIndex == COL_FACING) {
                bayList.get(rowIndex).setFacing((Integer) value);
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == COL_SIZE) {
                return bayTypeList.get(rowIndex).hasVariableSize();
            } else if (columnIndex == COL_FACING) {
                return bayTypeList.get(rowIndex).requiresFacing();
            } else {
                return (columnIndex == COL_DOORS);
            }
        }

        public void reorder(int from, int to) {
            Bay bay = bayList.remove(from);
            BayData bayType = bayTypeList.remove(from);
            if (to > from) {
                to--;
            }
            bayList.add(to, bay);
            bayTypeList.add(to, bayType);
            rebuildBays();
            refresh();
        }
        
    }
    
    private class AvailableBaysModel extends AbstractTableModel {
        
        /**
         * 
         */
        private static final long serialVersionUID = -5456813671712646392L;
        
        private static final int COL_NAME      = 0;
        private static final int COL_SIZE      = 1;
        private static final int COL_PERSONNEL = 2;
        private static final int NUM_COLS      = 3;
        
        private List<BayData> bayList = new ArrayList<>();
        
        public void refreshBays() {
            bayList.clear();
            for (BayData bay : BayData.values()) {
                if (eSource.getTechManager().isLegal(bay.getTechAdvancement())
                        && bay.isLegalFor(getAero())) {
                    bayList.add(bay);
                }
            }
            fireTableDataChanged();
        }

        public BayData getBayType(int row) {
            return bayList.get(row);
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
                        if (bayList.get(rowIndex).isCargoBay() && (bayList.get(rowIndex).getWeight() > 1)) {
                            return String.format("%.3f", bayList.get(rowIndex).getWeight());
                        }
                        return Double.toString(bayList.get(rowIndex).getWeight());
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
    
    private class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor, ChangeListener {
        
        /**
         * 
         */
        private static final long serialVersionUID = -5334192308060664513L;
        
        private final JSpinner spinner = new JSpinner();
        private final int column;
        
        SpinnerCellEditor(int column) {
            this.column = column;
            spinner.addChangeListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            int row = tblInstalled.convertRowIndexToModel(tblInstalled.getSelectedRow());
            if ((row < 0) || (row >= modelInstalled.bayList.size())) {
                return;
            }
            final Bay bay = modelInstalled.bayList.get(row);
            if (column == InstalledBaysModel.COL_SIZE) {
                Bay newBay = modelInstalled.bayTypeList.get(row).newBay((Double) getCellEditorValue(),
                        bay.getBayNumber());
                newBay.setDoors(bay.getDoors());
                removeBay(bay);
                addBay(newBay);
                modelInstalled.bayList.set(row, newBay);
            } else if (column == InstalledBaysModel.COL_DOORS) {
                modelInstalled.bayList.get(row).setDoors((Integer)getCellEditorValue());
            }
            modelInstalled.fireTableRowsUpdated(row, row);
            checkButtons();
            if (null != refresh) {
                refresh.refreshStructure();
                refresh.refreshStatus();
                refresh.refreshPreview();
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            boolean isCargo = modelInstalled.bayTypeList.get(row).isCargoBay();
            if (column == InstalledBaysModel.COL_DOORS) {
                int doors = ((Integer)modelInstalled.getValueAt(row,  column)).intValue();
                SpinnerNumberModel model = new SpinnerNumberModel(doors,
                        isCargo? 0 : 1, doorsAvailable() + doors, 1);
                spinner.removeChangeListener(this);
                spinner.setModel(model);
                spinner.addChangeListener(this);
                return spinner;
            } else if (column == InstalledBaysModel.COL_SIZE) {
                double step = isCargo? 0.5 : 1.0;
                SpinnerNumberModel model = new SpinnerNumberModel(modelInstalled.bayList.get(row).getUnusedSlots(),
                        step, null, step);
                spinner.removeChangeListener(this);
                spinner.setModel(model);
                spinner.addChangeListener(this);
                return spinner;
            }
            return null;
        }
        
    }
    
    private class BayReorderTransferHandler extends TransferHandler {
        
        /**
         * 
         */
        private static final long serialVersionUID = -6442201464476396078L;

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(String.valueOf(tblInstalled.getSelectedRow()));
        }

        @Override
        public boolean importData(TransferSupport support) {
            JTable target = (JTable) support.getComponent();
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            int index = dl.getRow();
            int max = modelInstalled.getRowCount();
            if (index < 0 || index > max)
               index = max;
            try {
               Integer rowFrom = Integer.parseInt((String) support.getTransferable().getTransferData(DataFlavor.stringFlavor));
               if (rowFrom != -1 && rowFrom != index) {
                  modelInstalled.reorder(rowFrom, index);
                  if (index > rowFrom)
                     index--;
                  target.getSelectionModel().addSelectionInterval(index, index);
                  return true;
               }
            } catch (Exception e) {
               MegaMekLab.getLogger().error(getClass(), "importData(TransferSupport)", e);
            }
            return false;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor)
                    && (support.getComponent() == tblInstalled)
                    && support.isDrop();
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }
    }

}
