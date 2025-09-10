/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megamek.common.weapons.Weapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.CriticalTransferHandler;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.ProtoMekUtil;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

/**
 * Shows unallocated equipment that needs to be assigned to a slot.
 *
 * @author Neoancient
 */
public class PMBuildView extends IView implements ActionListener, MouseListener {

    private final CriticalTableModel equipmentList;

    public List<Mounted<?>> getEquipment() {
        return equipmentList.getCrits();
    }

    private final Vector<Mounted<?>> masterEquipmentList = new Vector<>(10, 1);
    private final JTable equipmentTable = new JTable();

    CriticalTransferHandler cth;

    public PMBuildView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(getProtoMek(), CriticalTableModel.BUILD_TABLE);

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
        JScrollPane equipmentScroll = new JScrollPane();
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
        for (Mounted<?> mount : getProtoMek().getMisc()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted<?> mount : getProtoMek().getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted<?> mount : getProtoMek().getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()) {
                masterEquipmentList.add(mount);
            }
        }

        masterEquipmentList.sort(StringUtils.mountedComparator());

        // Time to Sort
        // weapons and ammo
        Vector<Mounted<?>> weaponsNAmmoList = new Vector<>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon)
                  || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        weaponsNAmmoList.sort(StringUtils.mountedComparator());
        for (Mounted<?> mount : weaponsNAmmoList) {
            equipmentList.addCrit(mount);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType)
                  && UnitUtil.isArmor(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else
        for (Mounted<?> mounted : masterEquipmentList) {
            equipmentList.addCrit(mounted);
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
            Mounted<?> mount = (Mounted<?>) equipmentTable.getModel().getValueAt(selectedRow,
                  CriticalTableModel.EQUIPMENT);

            for (int location = 0; location < getProtoMek().locations(); location++) {
                if (ProtoMekUtil.protoMekHasRoom(getProtoMek(), location, mount)) {
                    item = new JMenuItem("Add to " + locations[location]);
                    final int loc = location;
                    item.addActionListener(ev -> addToLocation(loc, mount));
                    popup.add(item);
                }
            }

            popup.show(equipmentTable, evt.getX(), evt.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {

    }

    private void addToLocation(int location, Mounted<?> mount) {
        UnitUtil.changeMountStatus(getProtoMek(), mount, location, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((PMBuildTab) getParent().getParent()).refreshAll();
    }
}
