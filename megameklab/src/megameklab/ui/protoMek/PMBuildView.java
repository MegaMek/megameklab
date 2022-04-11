/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
package megameklab.ui.protoMek;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.weapons.Weapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.CriticalTransferHandler;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Shows unallocated equipment that needs to be assigned to a slot.
 * 
 * @author Neoancient
 */
public class PMBuildView extends IView implements ActionListener, MouseListener {
    private JPanel mainPanel = new JPanel();

    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    CriticalTransferHandler cth;

    public PMBuildView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(getProtoMek(), CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        cth = new CriticalTransferHandler(eSource, refresh);
        equipmentTable.setTransferHandler(cth);
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        equipmentList.initColumnSizes(equipmentTable);

        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setTransferHandler(cth);

        mainPanel.add(equipmentScroll);
        equipmentTable.addMouseListener(this);

        this.add(mainPanel);
    }

    public void addRefreshedListener(RefreshListener l) {
        cth.addRefreshListener(l);
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted mount : getProtoMek().getMisc()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getProtoMek().getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getProtoMek().getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()) {
                masterEquipmentList.add(mount);
            }
        }

        masterEquipmentList.sort(StringUtils.mountedComparator());

        // Time to Sort
        // weapons and ammo
        Vector<Mounted> weaponsNAmmoList = new Vector<>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
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
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType) && UnitUtil.isArmor(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            equipmentList.addCrit(masterEquipmentList.get(pos));
        }
    }

    public void refresh() {
        removeAllListeners();
        loadEquipmentTable();
        fireTableRefresh();
        addAllListeners();
    }

    private void removeAllListeners() {

    }

    private void addAllListeners() {

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getProtoMek());
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
        if (evt.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(evt.getPoint());

            String[] locations = getProtoMek().getLocationNames();
            Mounted mount = (Mounted)equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);

            for (int location = 0; location < getProtoMek().locations(); location++) {
                if (UnitUtil.protomechHasRoom(getProtoMek(), location, mount)) {
                    item = new JMenuItem("Add to " + locations[location]);
                    final int loc = location;
                    item.addActionListener(ev -> addToLocation(loc, mount));
                    popup.add(item);
                }
            }

            popup.show(this, evt.getX(), evt.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {

    }

    private void addToLocation(int location, Mounted mount) {
        UnitUtil.changeMountStatus(getProtoMek(), mount, location, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((PMBuildTab) getParent().getParent()).refreshAll();
    }
}
