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
package megameklab.ui.generalUnit;

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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import megamek.common.annotations.Nullable;
import megamek.common.bays.Bay;
import megamek.common.bays.InfantryBay;
import megamek.common.equipment.DockingCollar;
import megamek.common.equipment.Transporter;
import megamek.common.units.Entity;
import megamek.common.units.EntityWeightClass;
import megamek.common.units.InfantryCompartment;
import megamek.common.units.Jumpship;
import megamek.common.util.RoundWeight;
import megamek.common.verifier.BayData;
import megamek.common.verifier.Ceil;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSupportVehicle.SVType;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * Tab for adding and modifying aerospace and support vee transport bays.
 *
 * @author Neoancient
 */
public class TransportTab extends IView implements ActionListener, ChangeListener {
    private static final MMLogger LOGGER = MMLogger.create(TransportTab.class);

    private final JLabel lblDockingHardpoints = new JLabel();
    private final JLabel lblMaxHardpoints = new JLabel();
    private final SpinnerNumberModel spnHardpointsModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnDockingHardpoints = new JSpinner(spnHardpointsModel);
    private final JSpinner spnTroopSpace = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 0.5));
    private final JSpinner spnPodTroopSpace = new JSpinner(new SpinnerNumberModel(0.0, 0.0, null, 0.5));
    private final JLabel lblMaxDoors = new JLabel();
    private final JLabel lblMinDoors = new JLabel();
    private final InstalledBaysModel modelInstalled = new InstalledBaysModel();
    private final JTable tblInstalled = new JTable(modelInstalled);
    private final AvailableBaysModel modelAvailable = new AvailableBaysModel();
    private final JTable tblAvailable = new JTable(modelAvailable);
    private final JButton btnRemoveBay = new JButton();
    private final JButton btnAddBay = new JButton();
    private final JButton btnAddToCargo = new JButton();
    private final JPanel panTroopSpace = new JPanel();

    private TableColumn podColumn;

    private RefreshListener refresh = null;

    public TransportTab(EntitySource eSource) {
        super(eSource);
        initUI();
    }

    private void initUI() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs");

        setLayout(new BorderLayout());
        if (getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            JPanel panHardpoints = new JPanel();
            lblDockingHardpoints.setText(resourceMap.getString("TransportTab.spnDockingHardpoints.text"));
            panHardpoints.add(lblDockingHardpoints);
            panHardpoints.add(spnDockingHardpoints);
            Dimension size = new Dimension(60, 25);
            spnDockingHardpoints.setPreferredSize(size);
            add(panHardpoints, BorderLayout.NORTH);
            spnDockingHardpoints.addChangeListener(this);
            spnDockingHardpoints.setToolTipText(resourceMap.getString("TransportTab.spnDockingHardpoints.tooltip"));
            panHardpoints.add(new JLabel(resourceMap.getString("TransportTab.spnMaxHardpoints.text")));
            panHardpoints.add(lblMaxHardpoints);
            lblMaxHardpoints.setToolTipText(resourceMap.getString("TransportTab.spnMaxHardpoints.tooltip"));
        } else {
            Dimension size = new Dimension(60, 25);
            spnTroopSpace.setPreferredSize(size);
            spnPodTroopSpace.setPreferredSize(size);
            panTroopSpace.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            panTroopSpace.add(new JLabel(resourceMap.getString("TransportTab.spnTroopSpace.text")), gbc);
            spnTroopSpace.setToolTipText(resourceMap.getString("TransportTab.spnTroopSpace.tooltip"));
            spnTroopSpace.addChangeListener(this);
            gbc.gridx = 1;
            panTroopSpace.add(spnTroopSpace, gbc);
            if (getEntity().isSupportVehicle()) {
                gbc.gridx = 0;
                gbc.gridy = 1;
                panTroopSpace.add(new JLabel(resourceMap.getString("TransportTab.spnPodTroopSpace.text")), gbc);
                spnTroopSpace.setToolTipText(resourceMap.getString("TransportTab.spnPodTroopSpace.tooltip"));
                gbc.gridx = 1;
                panTroopSpace.add(spnPodTroopSpace, gbc);
                spnPodTroopSpace.addChangeListener(this);
            }
            add(panTroopSpace, BorderLayout.NORTH);
        }

        JPanel bayPanel = new JPanel(new GridBagLayout());
        add(bayPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblCurrentBays.text")), gbc);

        gbc.gridy++;
        if (getEntity().isAero()) {
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblMaxDoors.text")), gbc);
            gbc.gridx = GridBagConstraints.RELATIVE;
            bayPanel.add(lblMaxDoors, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
            bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblMinDoors.text")), gbc);
            gbc.gridx = GridBagConstraints.RELATIVE;
            bayPanel.add(lblMinDoors, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        btnRemoveBay.setText(resourceMap.getString("TransportTab.btnRemoveBay.text"));
        btnRemoveBay.setToolTipText(resourceMap.getString("TransportTab.btnRemoveBay.tooltip"));
        bayPanel.add(btnRemoveBay, gbc);
        btnRemoveBay.addActionListener(this);

        gbc.gridx = 1;
        btnAddToCargo.setText(resourceMap.getString("TransportTab.btnAddToCargo.text"));
        btnAddToCargo.setToolTipText(resourceMap.getString("TransportTab.btnAddToCargo.tooltip"));
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
        bayPanel.add(new JLabel(resourceMap.getString("TransportTab.lblAvailableBays.text")), gbc);

        gbc.gridy = (getEntity().isAero() ? 3 : 2);
        btnAddBay.setText(resourceMap.getString("TransportTab.btnAddBay.text"));
        btnAddBay.setToolTipText(resourceMap.getString("TransportTab.btnAddBay.tooltip"));
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
        renderer.setToolTipText(resourceMap.getString("TransportTab.colSize.tooltip"));
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_DOORS);
        col.setCellEditor(new SpinnerCellEditor(InstalledBaysModel.COL_DOORS));
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colDoors.tooltip"));
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_TONNAGE);
        col.setCellEditor(new SpinnerCellEditor(InstalledBaysModel.COL_TONNAGE));
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colTonnage.tooltip"));
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_PERSONNEL);
        renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText(resourceMap.getString("TransportTab.colPersonnel.tooltip"));
        col.setCellRenderer(renderer);
        col = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_FACING);
        podColumn = tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_POD);
        if (getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            JComboBox<Integer> cb = new JComboBox<>(new Integer[] { Jumpship.LOC_NOSE,
                                                                    Jumpship.LOC_FLS, Jumpship.LOC_FRS,
                                                                    Jumpship.LOC_ALS, Jumpship.LOC_ARS,
                                                                    Jumpship.LOC_AFT });
            col.setCellEditor(new DefaultCellEditor(cb));
            cb.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                      boolean isSelected, boolean cellHasFocus) {
                    int loc = Entity.LOC_NONE;
                    if (null != value) {
                        loc = (Integer) value;
                    }
                    setText(getEntity().getLocationName(loc));
                    return this;
                }

            });
            renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText(resourceMap.getString("TransportTab.colFacing.tooltip"));
            col.setCellRenderer(renderer);
        } else {
            tblInstalled.getColumnModel().removeColumn(col);
        }
        if (!getEntity().isSupportVehicle()) {
            tblInstalled.getColumnModel().removeColumn(podColumn);
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

    private boolean useKilogramStandard() {
        return getEntity().getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT;
    }

    public void refresh() {
        refreshDockingHardpoints();

        if (getEntity().isAero()) {
            lblMaxDoors.setText(String.valueOf(TestAero.maxBayDoors(getAero())));
            lblMinDoors.setText(String.valueOf(doorsRequired()));
        }
        if (canMountInfantryCompartment()) {
            panTroopSpace.setVisible(true);
            final double total = getEntity().getTroopCarryingSpace();
            final double pod = getEntity().getPodMountedTroopCarryingSpace();
            spnTroopSpace.setValue(total - pod);
            spnPodTroopSpace.setValue(pod);
            spnPodTroopSpace.setEnabled(getEntity().isOmni());
        } else {
            panTroopSpace.setVisible(false);
            spnTroopSpace.setValue(0.0);
            spnPodTroopSpace.setValue(0.0);
        }
        if (getEntity().isOmni()) {
            if (podColumn != tblInstalled.getColumnModel()
                  .getColumn(tblInstalled.getColumnModel().getColumnCount() - 1)) {
                tblInstalled.getColumnModel().addColumn(podColumn);
            }
        } else {
            tblInstalled.getColumnModel().removeColumn(podColumn);
        }
        checkButtons();
        modelInstalled.refreshBays();
        modelAvailable.refreshBays();
        if (null != refresh) {
            refresh.refreshStructure();
            refresh.refreshStatus();
            refresh.refreshPreview();

        }
        tblInstalled.getColumnModel().getColumn(InstalledBaysModel.COL_TONNAGE)
              .setHeaderValue(modelInstalled.getColumnName(InstalledBaysModel.COL_TONNAGE));
        tblInstalled.getTableHeader().resizeAndRepaint();
    }

    /**
     * This refreshes the count of docking hardpoints on the display
     */
    public void refreshDockingHardpoints() {
        if (getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            int max = TestAdvancedAerospace.getMaxDockingHardpoints(getJumpship());
            spnHardpointsModel.setValue(Math.min(getJumpship().getDockingCollars().size(), max));
            spnHardpointsModel.setMaximum(max);
            lblMaxHardpoints.setText(Integer.toString(max));
        }
    }

    /**
     * Checks whether the current unit type can mount infantry compartments (i.e. {@code TroopSpace}). Infantry
     * compartments cannot be used by DropShips or advanced aerospace vessels (i.e. large craft) or by large naval or
     * airship support vehicles.
     *
     * @return Whether the current unit can mount infantry compartments.
     */
    private boolean canMountInfantryCompartment() {
        if (getEntity().isLargeCraft()) {
            return false;
        } else if ((getEntity().isSupportVehicle()
              && (getEntity().getWeightClass() == EntityWeightClass.WEIGHT_LARGE_SUPPORT))) {
            final SVType type = SVType.getVehicleType(getEntity());
            return (type != SVType.NAVAL) && (type != SVType.AIRSHIP);
        } else {
            return true;
        }
    }

    private void checkButtons() {
        btnRemoveBay.setEnabled(tblInstalled.getSelectedRow() >= 0);
        btnAddBay.setEnabled(canAddSelectedBay());
        btnAddToCargo.setEnabled(UnitUtil.getEntityVerifier(getEntity())
              .calculateWeight() < getEntity().getWeight());
    }

    private int doorsAvailable() {
        if (getEntity().isAero()) {
            int total = TestAero.maxBayDoors(getAero());
            for (Bay bay : getAero().getTransportBays()) {
                total -= bay.getDoors();
            }
            return Math.max(total, 0);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int doorsRequired() {
        int minimum = 0;
        for (Bay bay : getAero().getTransportBays()) {
            minimum += bay.getMinDoors();
        }
        return minimum;
    }

    private boolean canAddSelectedBay() {
        int selected = tblAvailable.getSelectedRow();
        if (selected < 0) {
            return false;
        }
        BayData bayType = modelAvailable.getBayType(tblAvailable.convertRowIndexToModel(selected));
        return (doorsAvailable() >= bayType.getMinDoors());
    }

    /**
     * Removes the bay from the vessel and adjusts the crew count.
     *
     * @param bay The bay to remove
     */
    private void removeBay(Bay bay) {
        int personnel = bay.getPersonnel(getEntity().isClan());
        if (getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getSmallCraft().setNCrew(getSmallCraft().getNCrew() - personnel);
        } else if (getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            getJumpship().setNCrew(getJumpship().getNCrew() - personnel);
        }
        getEntity().removeTransporter(bay);
    }

    /**
     * Adds the bay to the vessel and adjusts the crew count.
     *
     * @param bay The bay to add
     */
    private void addBay(Bay bay, boolean pod) {
        getEntity().addTransporter(bay, pod);
        int personnel = bay.getPersonnel(getEntity().isClan());
        if (getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            getSmallCraft().setNCrew(getSmallCraft().getNCrew() + personnel);
        } else if (getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {
            getJumpship().setNCrew(getJumpship().getNCrew() + personnel);
        }
    }

    /**
     * Removing bays can cause undesirable gaps in bay numbers, and it would be nice to let the user order the bays.
     * Since bay numbers are immutable we have to instantiate a new bay to alter it.
     */
    private void rebuildBays() {
        int bayNum = 1;
        List<Transporter> fixedList = new ArrayList<>();
        List<Transporter> podList = new ArrayList<>();
        for (Iterator<Bay> iter = modelInstalled.getBays(); iter.hasNext(); ) {
            final Bay bay = iter.next();
            if (bay.getBayNumber() == bayNum) {
                if (getEntity().isPodMountedTransport(bay)) {
                    podList.add(bay);
                } else {
                    fixedList.add(bay);
                }
            } else {
                BayData bayType = BayData.getBayType(bay);
                Bay newBay = bayType.newBay(bay.getUnusedSlots(), bayNum);
                newBay.setDoors(bay.getDoors());
                newBay.setFacing(bay.getFacing());
                if (getEntity().isPodMountedTransport(bay)) {
                    podList.add(newBay);
                } else {
                    fixedList.add(newBay);
                }
            }
            bayNum++;
        }
        for (Transporter transporter : getEntity().getTransports()) {
            if (!(transporter instanceof Bay) || ((Bay) transporter).isQuarters()) {
                if (getEntity().isPodMountedTransport(transporter)) {
                    podList.add(transporter);
                } else {
                    fixedList.add(transporter);
                }
            }
        }
        getEntity().removeAllTransporters();
        fixedList.forEach(b -> getEntity().addTransporter(b, false));
        podList.forEach(b -> getEntity().addTransporter(b, true));
        modelInstalled.refreshBays();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnAddBay) {
            int selected = tblAvailable.getSelectedRow();
            if (selected >= 0) {
                BayData bayType = modelAvailable.getBayType(tblAvailable.convertRowIndexToModel(selected));
                int num = 1;
                while (getEntity().getBayById(num) != null) {
                    num++;
                }
                Bay newBay = bayType.newBay(1.0, num);
                if (doorsAvailable() > 0) {
                    newBay.setDoors(1);
                }
                addBay(newBay, false);
                modelInstalled.refreshBays();
                rebuildBays();
                refresh();
            }
        } else if (evt.getSource() == btnRemoveBay) {
            int selected = tblInstalled.getSelectedRow();
            if (selected >= 0) {
                Bay bay = modelInstalled.getBay(tblInstalled.convertRowIndexToModel(selected));
                removeBay(bay);
                modelInstalled.refreshBays();
                rebuildBays();
                refresh();
            }
        } else if (evt.getSource() == btnAddToCargo) {
            double size = getEntity().getWeight() - UnitUtil.getEntityVerifier(getEntity())
                  .calculateWeight();
            // Testing has shown some floating-point precision errors creeping in here.
            if (useKilogramStandard()) {
                size = TestEntity.round(size, Ceil.KILO);
            }
            if (size > 0) {
                int selected = tblInstalled.getSelectedRow();
                Bay bay;
                int bayNum = 1;
                if ((selected >= 0)
                      && (modelInstalled
                      .getBayType(tblInstalled.convertRowIndexToModel(selected)) == BayData.CARGO)) {
                    bay = modelInstalled.getBay(tblInstalled.convertRowIndexToModel(selected));
                    size += bay.getCapacity();
                    bayNum = bay.getBayNumber();
                    removeBay(bay);
                } else {
                    while (getEntity().getBayById(bayNum) != null) {
                        bayNum++;
                    }
                }
                bay = BayData.CARGO.newBay(size, bayNum);
                addBay(bay, false);
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
                    int num = 0;
                    while (getJumpship().getCollarById(num) != null) {
                        num++;
                    }
                    getJumpship().addTransporter(new DockingCollar(num));
                }
            }
            if (null != refresh) {
                refresh.refreshStructure();
                refresh.refreshStatus();
                refresh.refreshPreview();
            }
        } else if ((ev.getSource() == spnTroopSpace) || (ev.getSource() == spnPodTroopSpace)) {
            final double fixed = (Double) spnTroopSpace.getValue();
            final double pod = (Double) spnPodTroopSpace.getValue();

            List<Transporter> toRemove = getEntity().getTransports().stream()
                  .filter(t -> t instanceof InfantryCompartment).toList();
            toRemove.forEach(t -> getEntity().removeTransporter(t));
            double troopTons = TestEntity.round(fixed, Ceil.HALF_TON);
            if (troopTons > 0) {
                getEntity().addTransporter(new InfantryCompartment(troopTons), false);
            }
            troopTons = TestEntity.round(pod, Ceil.HALF_TON);
            if (troopTons > 0) {
                getEntity().addTransporter(new InfantryCompartment(troopTons), true);
            }
            if (null != refresh) {
                refresh.refreshStructure();
                refresh.refreshStatus();
                refresh.refreshPreview();
            }
        }
    }

    private class InstalledBaysModel extends AbstractTableModel {
        private static final int COL_NAME = 0;
        private static final int COL_SIZE = 1;
        private static final int COL_DOORS = 2;
        private static final int COL_TONNAGE = 3;
        private static final int COL_PERSONNEL = 4;
        private static final int COL_FACING = 5;
        private static final int COL_POD = 6;
        private static final int NUM_COLS = 7;

        private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs");

        private final List<Bay> bayList = new ArrayList<>();
        private final List<BayData> bayTypeList = new ArrayList<>();

        void refreshBays() {
            bayList.clear();
            bayTypeList.clear();
            // Find all the bays and sort them by bay number.
            // Entity.getTransportBays() iterates through all transports and builds a
            // collection of
            // Bays so we're going to save ourselves a second list instantiation and
            // iteration by
            // doing it all at once here.
            List<Bay> bays = getEntity().getTransports().stream()
                  .filter(t -> (t instanceof Bay) && !((Bay) t).isQuarters())
                  .map(t -> (Bay) t).sorted(Comparator.comparingInt(Bay::getBayNumber))
                  .toList();
            for (Bay bay : bays) {
                BayData bayType = BayData.getBayType(bay);
                if (null != bayType) {
                    bayList.add(bay);
                    bayTypeList.add(bayType);
                }
            }

            fireTableDataChanged();

            // We need to refresh the docking hardpoint count to ensure that the count is
            // correct
            // following the removal of any naval repair facilities
            refreshDockingHardpoints();
        }

        Bay getBay(int row) {
            return bayList.get(row);
        }

        Iterator<Bay> getBays() {
            return bayList.iterator();
        }

        BayData getBayType(int row) {
            return bayTypeList.get(row);
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case COL_NAME -> resourceMap.getString("TransportTab.colName.text");
                case COL_SIZE -> resourceMap.getString("TransportTab.colSize.text");
                case COL_DOORS -> resourceMap.getString("TransportTab.colDoors.text");
                case COL_TONNAGE -> useKilogramStandard() ? resourceMap.getString("TransportTab.colKilograms.text")
                      : resourceMap.getString("TransportTab.colTonnage.text");
                case COL_PERSONNEL -> resourceMap.getString("TransportTab.colPersonnel.text");
                case COL_FACING -> resourceMap.getString("TransportTab.colFacing.text");
                case COL_POD -> resourceMap.getString("TransportTab.colPod.text");
                default -> "";
            };
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
        public Class<?> getColumnClass(int columnIndex) {
            return switch (columnIndex) {
                case COL_SIZE, COL_TONNAGE -> Double.class;
                case COL_PERSONNEL, COL_DOORS, COL_FACING -> Integer.class;
                case COL_POD -> Boolean.class;
                default -> String.class;
            };
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case COL_NAME:
                    return bayTypeList.get(rowIndex).getDisplayName();
                case COL_SIZE:
                    if (!bayTypeList.get(rowIndex).isCargoBay()) {
                        return (int) bayList.get(rowIndex).getUnusedSlots();
                    } else if (useKilogramStandard()) {
                        return RoundWeight.nearestKg(bayList.get(rowIndex).getUnusedSlots() * 1000.0);
                    }
                    return RoundWeight.nearestKg(bayList.get(rowIndex).getUnusedSlots());
                case COL_DOORS:
                    return bayList.get(rowIndex).getDoors();
                case COL_PERSONNEL:
                    if (bayList.get(rowIndex) instanceof InfantryBay) {
                        return "*";
                    }
                    return bayList.get(rowIndex).getPersonnel(getEntity().isClan());
                case COL_TONNAGE:
                    final double weight = TestEntity.round(bayList.get(rowIndex).getWeight(), Ceil.KILO);
                    if (useKilogramStandard()) {
                        return RoundWeight.nearestKg(weight * 1000.0);
                    } else {
                        return weight;
                    }
                case COL_FACING:
                    if (bayTypeList.get(rowIndex).requiresFacing()) {
                        return getEntity().getLocationAbbr(bayList.get(rowIndex).getFacing());
                    } else {
                        return "";
                    }
                case COL_POD:
                    return getEntity().isPodMountedTransport(bayList.get(rowIndex));
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (columnIndex == COL_FACING) {
                bayList.get(rowIndex).setFacing((Integer) value);
            } else if (columnIndex == COL_POD) {
                Transporter t = bayList.get(rowIndex);
                getEntity().removeTransporter(t);
                getEntity().addTransporter(t, (Boolean) value);
                if (null != refresh) {
                    refresh.refreshPreview();
                }
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case COL_SIZE -> bayTypeList.get(rowIndex).hasVariableSize();
                case COL_TONNAGE -> bayTypeList.get(rowIndex).isCargoBay();
                case COL_FACING -> bayTypeList.get(rowIndex).requiresFacing();
                case COL_POD -> getEntity().isOmni();
                default -> (columnIndex == COL_DOORS);
            };
        }

        void reorder(int from, int to) {
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
        private static final int COL_NAME = 0;
        private static final int COL_SIZE = 1;
        private static final int COL_PERSONNEL = 2;
        private static final int NUM_COLS = 3;

        private final List<BayData> bayList = new ArrayList<>();

        void refreshBays() {
            bayList.clear();
            for (BayData bay : BayData.values()) {
                if (eSource.getTechManager().isLegal(bay.getTechAdvancement())
                      && bay.isLegalFor(getEntity())
                      && (!useKilogramStandard() || bay.isCargoBay())) {
                    bayList.add(bay);
                }
            }
            fireTableDataChanged();
        }

        BayData getBayType(int row) {
            return bayList.get(row);
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case COL_NAME -> "Bay Type";
                case COL_SIZE -> "Unit Weight";
                case COL_PERSONNEL -> "Personnel";
                default -> "";
            };
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
            if ((column == InstalledBaysModel.COL_SIZE) || (column == InstalledBaysModel.COL_TONNAGE)) {
                boolean pod = getEntity().isPodMountedTransport(bay);
                double size = (Double) getCellEditorValue();
                BayData bayType = modelInstalled.bayTypeList.get(row);
                if ((column == InstalledBaysModel.COL_TONNAGE) && useKilogramStandard()) {
                    size /= 1000.0;
                } else if ((column == InstalledBaysModel.COL_SIZE) && bayType.isCargoBay()) {
                    size *= bayType.getWeight();
                    if (useKilogramStandard()) {
                        size /= 1000.0;
                    }
                    size = RoundWeight.nearestKg(size);
                }
                Bay newBay = bayType.newBay(size, bay.getBayNumber());
                newBay.setDoors(bay.getDoors());
                newBay.setFacing(bay.getFacing());
                removeBay(bay);
                addBay(newBay, pod);
                modelInstalled.bayList.set(row, newBay);

                // We need to refresh the docking hardpoint count to ensure that the count is
                // correct
                // following the size change of any naval repair facilities
                refreshDockingHardpoints();

            } else if (column == InstalledBaysModel.COL_DOORS) {
                int value = (Integer) getCellEditorValue();
                modelInstalled.bayList.get(row).setDoors(
                      Math.max(value, bay.getMinDoors())
                );
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
        @Nullable
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
              int column) {
            boolean isCargo = modelInstalled.bayTypeList.get(row).isCargoBay();
            if (column == InstalledBaysModel.COL_DOORS) {
                Object installedValue = modelInstalled.getValueAt(row, column);

                if (installedValue instanceof Integer doors) {
                    SpinnerNumberModel model = new SpinnerNumberModel(doors.intValue(),
                          modelInstalled.bayList.get(row).getMinDoors(),
                          getEntity().isAero() ? doorsAvailable() + doors : Integer.MAX_VALUE, 1);
                    spinner.removeChangeListener(this);
                    spinner.setModel(model);
                    spinner.addChangeListener(this);
                    return spinner;
                }
            } else if ((column == InstalledBaysModel.COL_SIZE)
                  || (column == InstalledBaysModel.COL_TONNAGE)) {
                double step;
                if (modelInstalled.bayTypeList.get(row) == BayData.PROTOMEK) {
                    step = 5.0;
                } else if (isCargo && !useKilogramStandard()) {
                    step = 0.5;
                } else {
                    step = 1.0;
                }
                double current;
                if (column == InstalledBaysModel.COL_SIZE) {
                    current = modelInstalled.bayList.get(row).getUnusedSlots();
                } else {
                    current = modelInstalled.bayList.get(row).getWeight();
                }
                // We're assuming that the only bays available for kg-standard units are cargo.
                if (useKilogramStandard()) {
                    current *= 1000;
                }
                SpinnerNumberModel model = new SpinnerNumberModel(current, step, null, step);
                spinner.removeChangeListener(this);
                spinner.setModel(model);
                spinner.addChangeListener(this);
                return spinner;
            }
            return null;
        }

    }

    private class BayReorderTransferHandler extends TransferHandler {
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
            if ((index < 0) || (index > max)) {
                index = max;
            }

            try {
                int rowFrom = Integer
                      .parseInt((String) support.getTransferable().getTransferData(DataFlavor.stringFlavor));
                if (rowFrom != -1 && rowFrom != index) {
                    modelInstalled.reorder(rowFrom, index);
                    if (index > rowFrom) {
                        index--;
                    }
                    target.getSelectionModel().addSelectionInterval(index, index);
                    return true;
                }
            } catch (Exception e) {
                LOGGER.error("", e);
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
