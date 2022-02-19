/*
 * Copyright (c) 2008, 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.com.ui.mek;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.CriticalTransferHandler;
import megameklab.com.ui.util.IView;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This IView shows all the equipment that's not yet been assigned a location
 * @author jtighe (torren@users.sourceforge.net)
 * @author beerockxs
 * @author Simon (Juliez)
 */
public class BMBuildView extends IView implements ActionListener, MouseListener {
    private final CriticalTableModel equipmentList = new CriticalTableModel(getMech(), CriticalTableModel.BUILDTABLE);
    private final JTable equipmentTable = new JTable(equipmentList);
    private int engineHeatSinkCount = 0;
    private final CriticalTransferHandler transferHandler;
    private RefreshListener refresh;

    public BMBuildView(EntitySource eSource, RefreshListener refresh, BMCriticalView critView) {
        super(eSource);
        this.refresh = refresh;
        transferHandler = new CriticalTransferHandler(eSource, refresh, critView);
        equipmentTable.setDragEnabled(true);
        equipmentTable.setTransferHandler(transferHandler);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            TableColumn column = equipmentTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            }
            column.setCellRenderer(equipmentList.getRenderer());

        }
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentTable.addMouseListener(this);
        JScrollPane equipmentScroll = new JScrollPane(equipmentTable);
        equipmentScroll.setTransferHandler(transferHandler);
        setLayout(new BorderLayout());
        this.add(equipmentScroll, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                "Unallocated Equipment", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {
        transferHandler.addRefreshListener(l);
        refresh = l;
    }

    private void loadEquipmentTable() {
        final List<Mounted> masterEquipmentList = new ArrayList<>();
        equipmentList.removeAllCrits();
        engineHeatSinkCount = UnitUtil.getCriticalFreeHeatSinks(getMech(), getMech().hasCompactHeatSinks());
        for (Mounted mount : getMech().getMisc()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !isEngineHeatSink(mount) && !(mount.getCriticals() == 0)) {
                masterEquipmentList.add(mount);
            }
        }
        getMech().getWeaponList().stream()
                .filter(m -> m.getLocation() == Entity.LOC_NONE)
                .forEach(masterEquipmentList::add);

        getMech().getAmmo().stream()
                .filter(m -> m.getLocation() == Entity.LOC_NONE)
                .filter(m -> !m.isOneShotAmmo())
                .forEach(masterEquipmentList::add);

        masterEquipmentList.sort(new BMUtils.MekMountedSorter(getMech()));
        masterEquipmentList.forEach(equipmentList::addCrit);
    }

    private boolean isEngineHeatSink(Mounted mount) {
        // Note: prototype DHS and compact DHS cannot be used as engine HS
        if ((mount.getType() instanceof MiscType)
                && (mount.getLocation() == Entity.LOC_NONE)
                && UnitUtil.isHeatSink(mount)
                && (engineHeatSinkCount > 0)
                && !(mount.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                && mount.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK))
                && !mount.getType().hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)) {
            engineHeatSinkCount--;
            return engineHeatSinkCount >= 0;
        } else {
            return false;
        }
    }

    public void refresh() {
        removeAllListeners();
        loadEquipmentTable();
        fireTableRefresh();
        addAllListeners();
    }

    private void removeAllListeners() { }

    private void addAllListeners() { }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getMech());
        equipmentList.refreshModel();
    }

    public JTable getTable() {
        return equipmentTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);

            final int totalCrits = UnitUtil.getCritsUsed(eq);
            String[] locations = getMech().getLocationNames();
            String[] abbrLocations = getMech().getLocationAbbrs();

            if ((eq.getType().isSpreadable() || eq.isSplitable())
                    && (totalCrits > 1)
                    && !((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP))
                    && !(getMech() instanceof LandAirMech)) {
                int[] critSpace = UnitUtil.getHighestContinuousNumberOfCritsArray(getMech());
                // Superheavy mechs may have enough space in the CT for the whole thing.
                if ((critSpace[Mech.LOC_CT] >= totalCrits) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_CT)) {
                    JMenu ctMenu = new JMenu(locations[Mech.LOC_CT]);

                    item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_CT]));
                    item.addActionListener(ev -> addSplitEquipment(Mech.LOC_CT, Mech.LOC_NONE, totalCrits, selectedRow));
                    ctMenu.add(item);
                    popup.add(ctMenu);
                }

                if ((critSpace[Mech.LOC_RT] >= 1) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_RT)) {
                    JMenu rtMenu = new JMenu(locations[Mech.LOC_RT]);

                    if (critSpace[Mech.LOC_RT] >= totalCrits) {
                        item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_RT]));
                        item.addActionListener(ev -> addSplitEquipment(Mech.LOC_RT, Mech.LOC_NONE, totalCrits, selectedRow));
                        rtMenu.add(item);
                    }

                    int[] splitLocations = new int[] { Mech.LOC_CT, Mech.LOC_RARM, Mech.LOC_RLEG };

                    for (int location = 0; location < 3; location++) {
                        if (!UnitUtil.isValidLocation(getMech(), eq.getType(), splitLocations[location])) {
                            continue;
                        }
                        JMenu subMenu = new JMenu(String.format("%1$s/%2$s", abbrLocations[Mech.LOC_RT], abbrLocations[splitLocations[location]]));
                        int subCrits = critSpace[splitLocations[location]];
                        for (int slots = 1; slots <= subCrits; slots++) {
                            final int primarySlots = totalCrits - slots;
                            item = new JMenuItem(String.format("%1$s (%2$s)/%3$s (%4$s)", abbrLocations[Mech.LOC_RT], primarySlots, abbrLocations[splitLocations[location]], slots));

                            final int secondaryLocation = splitLocations[location];
                            item.addActionListener(ev -> addSplitEquipment(Mech.LOC_RT, secondaryLocation, primarySlots, selectedRow));
                            subMenu.add(item);
                        }
                        rtMenu.add(subMenu);
                    }
                    popup.add(rtMenu);
                }

                if ((critSpace[Mech.LOC_RARM] >= totalCrits) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_RARM)) {
                    item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_RARM]));
                    item.addActionListener(ev -> addSplitEquipment(Mech.LOC_RARM, Mech.LOC_RARM, totalCrits, selectedRow));
                    popup.add(item);
                }

                if ((critSpace[Mech.LOC_LT] >= 1) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_LT)) {
                    JMenu ltMenu = new JMenu(locations[Mech.LOC_LT]);

                    if (critSpace[Mech.LOC_LT] >= totalCrits) {
                        item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_LT]));
                        item.addActionListener(ev -> addSplitEquipment(Mech.LOC_LT, Mech.LOC_NONE, totalCrits, selectedRow));
                        ltMenu.add(item);
                    }

                    int[] splitLocations = new int[] { Mech.LOC_CT, Mech.LOC_LARM, Mech.LOC_LLEG };

                    for (int location = 0; location < 3; location++) {
                        if (!UnitUtil.isValidLocation(getMech(), eq.getType(), splitLocations[location])) {
                            continue;
                        }
                        JMenu subMenu = new JMenu(String.format("%1$s/%2$s", abbrLocations[Mech.LOC_LT], abbrLocations[splitLocations[location]]));
                        int subCrits = critSpace[splitLocations[location]];
                        for (int slots = 1; slots <= subCrits; slots++) {
                            final int primarySlots = totalCrits - slots;
                            item = new JMenuItem(String.format("%1$s (%2$s)/%3$s (%4$s)", abbrLocations[Mech.LOC_LT], primarySlots, abbrLocations[splitLocations[location]], slots));

                            final int secondaryLocation = splitLocations[location];
                            item.addActionListener(ev -> addSplitEquipment(Mech.LOC_LT, secondaryLocation, primarySlots, selectedRow));
                            subMenu.add(item);
                        }
                        ltMenu.add(subMenu);
                    }
                    popup.add(ltMenu);
                }

                if ((critSpace[Mech.LOC_LARM] >= totalCrits)  && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_LARM)) {
                    item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_LARM]));
                    item.addActionListener(ev -> addSplitEquipment(Mech.LOC_LARM, Mech.LOC_LARM, totalCrits, selectedRow));
                    popup.add(item);
                }

            } else {
                for (int location = 0; location < getMech().locations(); location++) {
                    if (!UnitUtil.isValidLocation(getMech(), eq.getType(), location)) {
                        continue;
                    }
                    if ((UnitUtil.getHighestContinuousNumberOfCrits(getMech(), location) >= totalCrits)  && UnitUtil.isValidLocation(getMech(), eq.getType(), location)) {
                        item = new JMenuItem("Add to " + locations[location]);
                        final int loc = location;
                        item.addActionListener(ev -> addEquipment(loc, selectedRow));
                        popup.add(item);
                    }
                }
            }
            popup.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    private void addSplitEquipment(int location, int secondaryLocation, int primarySlots, int selectedRow) {
        Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        int crits = UnitUtil.getCritsUsed(eq);
        int openSlots = Math.min(primarySlots, UnitUtil.getHighestContinuousNumberOfCrits(getMech(), location));
        eq.setSecondLocation(secondaryLocation);

        for (int slot = 0; slot < openSlots; slot++) {
            try {
                UnitUtil.addMounted(getMech(), eq, location, false);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }
        }

        crits -= openSlots;
        for (int slot = 0; slot < crits; slot++) {
            try {
                UnitUtil.addMounted(getMech(), eq, secondaryLocation, false);
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }
        }

        UnitUtil.changeMountStatus(getMech(), eq, location, secondaryLocation, false);
        doRefresh();
    }

    private void addEquipment(int location, int selectedRow) {
        Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        if (eq.getType().isSpreadable() || eq.isSplitable()) {
            if (getMech() instanceof LandAirMech) {
                addSplitEquipment(location, Entity.LOC_NONE, eq.getCriticals(), selectedRow);
            } else if (!(eq.getType() instanceof MiscType) || !eq.getType().hasFlag(MiscType.F_TARGCOMP)) {
                addSplitEquipment(location, Entity.LOC_NONE, 1, selectedRow);
            } else {
                // Targetting computer is flagged as spreadable so the slots will be added one at a time when loaded,
                // since we don't have a way of indicating the number of slots until we know all the weapons. But
                // it's not really splittable, so we need to put add all the slots at once.
                addSplitEquipment(location, Entity.LOC_NONE, eq.getCriticals(), selectedRow);
            }
            return;
        }
        try {
            if ((eq.getType() instanceof WeaponType) && eq.getType().hasFlag(WeaponType.F_VGL)) {
                int slotNumber = BMUtils.findSlotWithContiguousNumOfCrits(getMech(), location,
                        UnitUtil.getCritsUsed(eq));
                BMUtils.addVGL(getMech(), eq, location, slotNumber);
            } else {
                UnitUtil.addMounted(getMech(), eq, location, false);
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
        UnitUtil.changeMountStatus(getMech(), eq, location, -1, false);
        doRefresh();
    }

    private void doRefresh() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }
}
