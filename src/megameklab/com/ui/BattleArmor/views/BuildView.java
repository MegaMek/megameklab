/*
 * MegaMekLab - Copyright (C) 2008
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.BattleArmor.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.weapons.Weapon;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.CriticalTransferHandler;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

public class BuildView extends IView implements ActionListener, MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<Mounted>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    public BuildView(BattleArmor unit) {
        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(this.unit, CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        CriticalTransferHandler cth = new CriticalTransferHandler(unit, null);
        equipmentTable.setTransferHandler(cth);

        equipmentList.initColumnSizes(equipmentTable);

        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // equipmentScroll.setToolTipText("");
        // equipmentScroll.setPreferredSize(new Dimension(getWidth(),
        // getHeight()));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        mainPanel.add(equipmentScroll);
        equipmentTable.addMouseListener(this);

        this.add(mainPanel);
        // loadEquipmentTable();

    }

    public void addRefreshedListener(RefreshListener l) {
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted mount : unit.getMisc()) {
            if ((mount.getLocation() == Entity.LOC_NONE)) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : unit.getWeaponList()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && UnitUtil.isBattleArmorWeapon(mount.getType(), unit)) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.mountedComparator());

        // Jump Jets
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isJumpJet(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // weapons and ammo
        Vector<Mounted> weaponsNAmmoList = new Vector<Mounted>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        Collections.sort(weaponsNAmmoList, StringUtils.mountedComparator());
        for (Mounted mount : weaponsNAmmoList) {
            equipmentList.addCrit(mount);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType) && !UnitUtil.isArmor(masterEquipmentList.get(pos).getType()) && !UnitUtil.isTSM(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // armor
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isArmor(masterEquipmentList.get(pos).getType())) {
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

    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(unit);
        equipmentList.refreshModel();
        // equipmentScroll.setPreferredSize(new Dimension(getWidth() * 90 / 100,
        // getHeight() * 90 / 100));
        // equipmentScroll.setBounds(0, 0, getWidth(), getHeight());
        // equipmentScroll.repaint();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            // JPopupMenu popup = new JPopupMenu();
            // JMenuItem item;

            // final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            // Mounted eq = UnitUtil.getMounted(unit, ((EquipmentType)
            // equipmentTable.getModel().getValueAt(selectedRow,
            // CriticalTableModel.EQUIPMENT)).getInternalName());

        }
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
