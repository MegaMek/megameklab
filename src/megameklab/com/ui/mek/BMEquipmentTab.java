/*
 * MegaMekLab - Copyright (C) 2008
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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
package megameklab.com.ui.mek;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
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

/*
 * The Equipment Tab for Mek units showing the equipment database and the current loadout list.
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author Taharqa
 * @author Simon (Juliez)
 */
public class BMEquipmentTab extends ITab {

    private RefreshListener refresh;

    private final CriticalTableModel loadoutModel;
    private final JTable loadoutTable = new JTable();
    private final BMEquipmentDatabaseView equipDatabase;

    public BMEquipmentTab(EntitySource eSource) {
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
        loadEquipmentTable();

        JButton removeButton = new JButton("Remove");
        removeButton.setMnemonic('R');
        JButton removeAllButton = new JButton("Remove All");
        removeAllButton.setMnemonic('l');
        removeButton.addActionListener(this::removeSelectedEquipment);
        removeAllButton.addActionListener(this::removeAllEquipment);

        equipDatabase = new BMEquipmentDatabaseView(eSource);

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

    private void loadEquipmentTable() {
        getMech().getWeaponList().forEach(loadoutModel::addCrit);
        getMech().getAmmo().forEach(loadoutModel::addCrit);

        List<EquipmentType> spreadAlreadyAdded = new ArrayList<>();
        for (Mounted mount : getMech().getMisc()) {
            EquipmentType etype = mount.getType();
            if (UnitUtil.isHeatSink(mount)
                    || etype.hasFlag(MiscType.F_JUMP_JET)
                    || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                    || etype.hasFlag(MiscType.F_TSM)
                    || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || (etype.hasFlag(MiscType.F_MASC) && !etype.hasSubType(MiscType.S_SUPERCHARGER))
                    || ((getMech().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE
                    && etype.hasFlag(MiscType.F_TRACKS))
                    || UnitUtil.isArmorOrStructure(etype)
                    || (etype.hasFlag(MiscType.F_CASE) && etype.isClan())) {
                continue;
            }

            if (UnitUtil.isFixedLocationSpreadEquipment(etype) && !spreadAlreadyAdded.contains(etype)) {
                loadoutModel.addCrit(mount);
                // keep spreadable equipment from showing up multiple times in the table
                spreadAlreadyAdded.add(etype);
            } else {
                loadoutModel.addCrit(mount);
            }
        }
    }

    private void removeHeatSinks() {
        for (int location = 0; location < loadoutModel.getRowCount(); ) {
            Mounted mount = (Mounted) loadoutModel.getValueAt(location, CriticalTableModel.EQUIPMENT);
            EquipmentType eq = mount.getType();
            if ((eq instanceof MiscType) && (UnitUtil.isHeatSink(mount))) {
                try {
                    loadoutModel.removeCrit(location);
                } catch (IndexOutOfBoundsException ignored) {
                    return;
                } catch (Exception e) {
                    LogManager.getLogger().error(e);
                    return;
                }
            } else {
                location++;
            }
        }
    }

    private void removeSelectedEquipment(ActionEvent e) {
        int[] selectedRows = loadoutTable.getSelectedRows();
        for (Integer row : selectedRows){
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
        loadEquipmentTable();
        fireTableRefresh();
    }

    public void refreshTable() {
        equipDatabase.refreshTable();
    }

    private void fireTableRefresh() {
        loadoutModel.updateUnit(getMech());
        loadoutModel.refreshModel();
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