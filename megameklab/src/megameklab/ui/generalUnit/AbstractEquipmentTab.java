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
package megameklab.ui.generalUnit;

import static java.util.stream.Collectors.toList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import megamek.client.ui.util.UIUtil;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * The base class for Equipment Tabs for all unit types. It shows the equipment database and the current load out list.
 * The load out list is obtained through the abstract method getLoadOut() and may be either the full equipment of the
 * unit or filtered somehow so that equipment controlled in the Structure tab cannot be removed in the Equipment Tab. An
 * EquipmentDatabaseView must be provided through the abstract method getEquipmentDatabaseView.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author neoancient
 * @author Simon (Juliez)
 */
public abstract class AbstractEquipmentTab extends ITab {
    private static final MMLogger LOGGER = MMLogger.create(AbstractEquipmentTab.class);

    protected RefreshListener refresh;

    protected final CriticalTableModel loadOutModel;
    private final JTable loadOutTable = new JTable();
    private final AbstractEquipmentDatabaseView equipDatabase;

    public AbstractEquipmentTab(EntitySource eSource) {
        super(eSource);

        loadOutModel = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.WEAPON_TABLE);
        loadOutTable.setModel(loadOutModel);
        loadOutTable.setIntercellSpacing(new Dimension(0, 0));
        loadOutTable.setShowGrid(false);
        loadOutTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        loadOutTable.setDoubleBuffered(true);
        setupTableColumns();
        loadOutModel.addTableModelListener(ev -> {
            refreshOtherTabs();
            setupTableColumns();
        });
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(loadOutTable);
        getLoadOut().forEach(loadOutModel::addCrit);

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
            // Allow downsizing the database with the Split pane for small screen sizes
            public Dimension getMinimumSize() {
                Dimension prefSize = super.getPreferredSize();
                return new Dimension(prefSize.width / 2, prefSize.height);
            }
        };
        databasePanel.setBorder(BorderFactory.createTitledBorder("Equipment Database"));
        databasePanel.add(equipDatabase);

        Box loadOutPanel = Box.createVerticalBox();
        loadOutPanel.setBorder(BorderFactory.createTitledBorder("Current Load out"));

        var buttonPanel = new UIUtil.FixedYPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);
        loadOutPanel.add(buttonPanel);
        loadOutPanel.add(equipmentScroll);

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loadOutPanel, databasePanel);
        pane.setOneTouchExpandable(true);
        setLayout(new BorderLayout());
        add(pane, BorderLayout.CENTER);
    }

    private void setupTableColumns() {
        TableColumn column;
        for (int i = 0; i < loadOutModel.getColumnCount(); i++) {
            column = loadOutTable.getColumnModel().getColumn(i);
            if (i == CriticalTableModel.NAME) {
                column.setPreferredWidth(200);
            } else if (i == CriticalTableModel.SIZE) {
                column.setCellEditor(loadOutModel.new SpinnerCellEditor());
            }
            column.setCellRenderer(loadOutModel.getRenderer());

        }
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
        equipDatabase.setRefresh(refresh);
    }

    private List<Mounted<?>> getLoadOut() {
        return getEntity().getEquipment().stream().filter(this::showInLoadOut).collect(toList());
    }

    /**
     * This method is called for all of a unit's equipment to determine if it is to be shown in the load out view. It
     * may be overridden to hide some equipment in the Equipment Tab's load out view to prevent it from being removed
     * here. Use to hide equipment that is controlled from the Structure Tab. By default, this method returns true.
     *
     * @param mount the mounted to be checked
     *
     * @return true when the given mounted may be shown in the load out view
     */
    protected boolean showInLoadOut(Mounted<?> mount) {
        return true;
    }

    protected abstract AbstractEquipmentDatabaseView getEquipmentDatabaseView();

    private void removeHeatSinks() {
        for (int location = 0; location < loadOutModel.getRowCount(); ) {
            Mounted<?> mount = (Mounted<?>) loadOutModel.getValueAt(location, CriticalTableModel.EQUIPMENT);
            EquipmentType eq = mount.getType();
            if ((eq instanceof MiscType) && (UnitUtil.isHeatSink(mount))) {
                try {
                    loadOutModel.removeCrit(location);
                } catch (IndexOutOfBoundsException ignored) {
                    return;
                } catch (Exception ex) {
                    LOGGER.error("", ex);
                    return;
                }
            } else {
                location++;
            }
        }
    }

    private void removeSelectedEquipment(ActionEvent e) {
        int[] selectedRows = loadOutTable.getSelectedRows();
        for (int row : selectedRows) {
            loadOutModel.removeMounted(row);
        }
        loadOutModel.removeCrits(selectedRows);
        fireTableRefresh();
    }

    private void removeAllEquipment(ActionEvent e) {
        removeHeatSinks();
        for (int count = 0; count < loadOutModel.getRowCount(); count++) {
            loadOutModel.removeMounted(count);
        }
        loadOutModel.removeAllCrits();
        fireTableRefresh();
    }

    public void refresh() {
        removeHeatSinks();
        loadOutModel.removeAllCrits();
        getLoadOut().forEach(loadOutModel::addCrit);
        fireTableRefresh();
    }

    public void refreshTable() {
        equipDatabase.refreshTable();
    }

    private void fireTableRefresh() {
        loadOutModel.updateUnit(getEntity());
        loadOutModel.refreshModel();
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
