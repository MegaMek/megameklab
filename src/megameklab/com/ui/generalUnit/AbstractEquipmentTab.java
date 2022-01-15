/*
 * Copyright (c) 2008, 2021 - The MegaMek Team. All Rights Reserved.
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.com.ui.generalUnit;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.ITab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The base class for Equipment Tabs for all unit types. It shows the equipment database and the
 * current loadout list.
 * The loadout list is obtained through the abstract method getLoadout() and may be either the full
 * equipment of the unit or filtered somehow so that equipment controlled in the Structure tab cannot
 * be removed in the Equipment Tab.
 * An EquipmentDatabaseView must be provided through the abstract method getEquipmentDatabaseView.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author neoancient
 * @author Simon (Juliez)
 */
public abstract class AbstractEquipmentTab extends ITab {

    private RefreshListener refresh;

    protected final CriticalTableModel loadoutModel;
    private final JTable loadoutTable = new JTable();
    private final AbstractEquipmentDatabaseView equipDatabase;

    public AbstractEquipmentTab(EntitySource eSource) {
        super(eSource);

        loadoutModel = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.WEAPONTABLE);
        loadoutTable.setModel(loadoutModel);
        loadoutTable.setIntercellSpacing(new Dimension(0, 0));
        loadoutTable.setShowGrid(false);
        loadoutTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        loadoutTable.setDoubleBuffered(true);
        TableColumn column;
        for (int i = 0; i < loadoutModel.getColumnCount(); i++) {
            column = loadoutTable.getColumnModel().getColumn(i);
            if (i == CriticalTableModel.NAME) {
                column.setPreferredWidth(200);
            } else if (i == CriticalTableModel.SIZE) {
                column.setCellEditor(loadoutModel.new SpinnerCellEditor());
            }
            column.setCellRenderer(loadoutModel.getRenderer());

        }
        loadoutModel.addTableModelListener(ev -> refreshOtherTabs());
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(loadoutTable);
        getLoadout().forEach(loadoutModel::addCrit);

        JButton removeButton = new JButton("Remove");
        removeButton.setMnemonic('R');
        JButton removeAllButton = new JButton("Remove All");
        removeAllButton.setMnemonic('l');
        removeButton.addActionListener(this::removeSelectedEquipment);
        removeAllButton.addActionListener(this::removeAllEquipment);

        equipDatabase = getEquipmentDatabaseView();
        equipDatabase.refreshTable();

        JPanel databasePanel = new JPanel(new GridLayout(1, 1)) {
            @Override
            // Allow downsizing the database with the Splitpane for small screen sizes
            public Dimension getMinimumSize() {
                Dimension prefSize = super.getPreferredSize();
                return new Dimension(prefSize.width / 2, prefSize.height);
            }
        };
        databasePanel.setBorder(BorderFactory.createTitledBorder("Equipment Database"));
        databasePanel.add(equipDatabase);

        Box loadoutPanel = Box.createVerticalBox();
        loadoutPanel.setBorder(BorderFactory.createTitledBorder("Current Loadout"));

        var buttonPanel = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);
        loadoutPanel.add(buttonPanel);
        loadoutPanel.add(equipmentScroll);

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loadoutPanel, databasePanel);
        pane.setOneTouchExpandable(true);
        setLayout(new BorderLayout());
        add(pane, BorderLayout.CENTER);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        equipDatabase.setRefresh(refresh);
    }

    private List<Mounted> getLoadout() {
        return getEntity().getEquipment().stream().filter(this::showInLoadout).collect(toList());
    }

    /**
     * This method is called for all of a unit's equipment to determine if it is to be shown
     * in the loadout view. It may be overridden to hide some equipment in the Equipment Tab's
     * loadout view to prevent it from being removed here. Use to hide equipment that is
     * controlled from the Structure Tab.
     * By default, this method returns true.
     * @param mount the mounted to be checked
     * @return true when the given mounted may be shown in the loadout view
     */
    protected boolean showInLoadout(Mounted mount) {
        return true;
    }

    protected abstract AbstractEquipmentDatabaseView getEquipmentDatabaseView();

    private void removeHeatSinks() {
        for (int location = 0; location < loadoutModel.getRowCount(); ) {
            Mounted mount = (Mounted) loadoutModel.getValueAt(location, CriticalTableModel.EQUIPMENT);
            EquipmentType eq = mount.getType();
            if ((eq instanceof MiscType) && (UnitUtil.isHeatSink(mount))) {
                try {
                    loadoutModel.removeCrit(location);
                } catch (IndexOutOfBoundsException ignored) {
                    return;
                } catch (Exception ex) {
                    LogManager.getLogger().error("", ex);
                    return;
                }
            } else {
                location++;
            }
        }
    }

    private void removeSelectedEquipment(ActionEvent e) {
        int[] selectedRows = loadoutTable.getSelectedRows();
        for (Integer row : selectedRows) {
            loadoutModel.removeMounted(row);
        }
        loadoutModel.removeCrits(selectedRows);
        fireTableRefresh();
    }

    private void removeAllEquipment(ActionEvent e) {
        removeHeatSinks();
        for (int count = 0; count < loadoutModel.getRowCount(); count++) {
            loadoutModel.removeMounted(count);
        }
        loadoutModel.removeAllCrits();
        fireTableRefresh();
    }

    public void refresh() {
        removeHeatSinks();
        loadoutModel.removeAllCrits();
        getLoadout().forEach(loadoutModel::addCrit);
        fireTableRefresh();
    }

    public void refreshTable() {
        equipDatabase.refreshTable();
    }

    private void fireTableRefresh() {
        loadoutModel.updateUnit(getEntity());
        loadoutModel.refreshModel();
        refreshTable();
        refreshOtherTabs();
    }

    private void refreshOtherTabs() {
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshSummary();
        }
    }

}
