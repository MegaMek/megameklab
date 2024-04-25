/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.largeAero;

import megamek.common.*;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.weapons.Weapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.*;
import megameklab.util.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Shows unallocated equipment and presents menus options for adding equipment to bays.
 * 
 * @author Neoancient
 */
public class LABuildView extends IView implements MouseListener {
    private final List<BayWeaponCriticalTree> arcViews = new CopyOnWriteArrayList<>();
    public void addArcView(BayWeaponCriticalTree l) {
        arcViews.add(l);
    }

    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    public LABuildView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        equipmentList = new CriticalTableModel(getAero(), CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        AeroBayTransferHandler cth = new AeroBayTransferHandler(eSource);
        equipmentTable.setTransferHandler(cth);
        TableColumn column;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            }
            column.setCellRenderer(equipmentList.getRenderer());

        }
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setTransferHandler(cth);

        equipmentTable.addMouseListener(this);

        setLayout(new GridLayout(1, 1));
        this.add(equipmentScroll, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Unallocated Equipment", 
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {

    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted mount : getAero().getMisc()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getAero().getTotalWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getAero().getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()) {
                masterEquipmentList.add(mount);
            }
        }

        masterEquipmentList.sort(StringUtils.mountedComparator());

        // weapons and ammo
        Vector<Mounted> weaponsNAmmoList = new Vector<>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || 
                    (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        weaponsNAmmoList.sort(StringUtils.mountedComparator());
        for (Mounted mount : weaponsNAmmoList) {
            equipmentList.addCrit(mount);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (masterEquipmentList.get(pos).getType() instanceof MiscType) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            equipmentList.addCrit(masterEquipmentList.get(pos));
            masterEquipmentList.remove(pos);
            pos--;
        }
    }

    public void refresh() {
        loadEquipmentTable();
        fireTableRefresh();
    }

    public void actionPerformed(ActionEvent evt) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getAero());
        equipmentList.refreshModel();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {

    }

    @Override
    public void mouseEntered(MouseEvent evt) {

    }

    @Override
    public void mouseExited(MouseEvent evt) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {
        // On right-click, we want to generate menu items to add to specific locations, but only if
        // those locations are make sense
        if (evt.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            if (equipmentTable.getSelectedRowCount() > 1) {
                List<Mounted<?>> list = new ArrayList<>();
                for (int row : equipmentTable.getSelectedRows()) {
                    list.add((Mounted<?>) equipmentTable.getModel().getValueAt(row, CriticalTableModel.EQUIPMENT));
                }

                for (BayWeaponCriticalTree l : arcViews) {
                    // Aerodyne small craft and DropShips skip the aft side arcs
                    if (!l.validForUnit(getAero())) {
                        continue;
                    }

                    if (list.stream().anyMatch(l::canAdd)) {
                        item = new JMenuItem(l.getLocationName());
                        item.addActionListener(ev -> l.addToLocation(list));
                        popup.add(item);
                    }
                }
            } else {
                final int selectedRow = equipmentTable.rowAtPoint(evt.getPoint());
                Mounted<?> eq = (Mounted<?>) equipmentTable.getModel().getValueAt(
                        selectedRow, CriticalTableModel.EQUIPMENT);
                for (BayWeaponCriticalTree l : arcViews) {
                    // Aerodyne small craft and DropShips skip the aft side arcs
                    if (!l.validForUnit(getAero()) || !l.canAdd(eq)) {
                        continue;
                    }

                    if (getAero().usesWeaponBays()) {
                        JMenu menu = new JMenu(l.getLocationName());
                        for (WeaponMounted bay : l.baysFor(eq)) {
                            if (eq instanceof AmmoMounted) {
                                final int shotCount = ((AmmoType) eq.getType()).getShots();
                                JMenu locMenu = new JMenu(bay.getName());
                                for (int shots = shotCount; shots <= eq.getUsableShotsLeft(); shots += shotCount) {
                                    item = new JMenuItem("Add " + shots + ((shots > 1)?" shots" : " shot"));
                                    final int addShots = shots;
                                    item.addActionListener(ev -> l.addAmmoToBay(bay, (AmmoMounted) eq, addShots));
                                    locMenu.add(item);
                                }
                                menu.add(locMenu);
                            } else {
                                item = new JMenuItem(bay.getName());
                                item.addActionListener(ev -> l.addToBay(bay, eq));
                                menu.add(item);
                            }
                        }

                        if (eq instanceof WeaponMounted) {
                            final EquipmentType bayType = ((WeaponType) eq.getType()).getBayType();
                            item = new JMenuItem("New " + bayType.getName());
                            item.addActionListener(ev -> l.addToNewBay(bayType, (WeaponMounted) eq));
                            menu.add(item);
                        }

                        if (menu.getItemCount() > 0) {
                            popup.add(menu);
                        } else if ((eq.getType() instanceof MiscType) && l.canAdd(eq)) {
                            item = new JMenuItem(l.getLocationName());
                            item.addActionListener(ev -> l.addToLocation(eq));
                            popup.add(item);
                        }                           
                    } else {
                        item = new JMenuItem(l.getLocationName());
                        item.addActionListener(ev -> l.addToLocation(eq));
                        popup.add(item);
                    }
                }
            }

            popup.show(equipmentTable, evt.getX(), evt.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {

    }
}
