/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megameklab.com.util.CriticalTable;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class BuildView extends IView implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<EquipmentType>(10, 1);
    private CriticalTable equipmentTable = new CriticalTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private int engineHeatSinkCount = 0;

    public BuildView(Mech unit) {
        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(this.unit, CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentList.initColumnSizes(equipmentTable);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // equipmentScroll.setToolTipText("");
        equipmentScroll.setPreferredSize(new Dimension(getWidth(), getHeight()));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        mainPanel.add(equipmentScroll);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();

        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();
            masterEquipmentList.add(eq);
        }

        this.add(mainPanel);
        // loadEquipmentTable();
    }

    public void addRefreshedListener(RefreshListener l) {
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        engineHeatSinkCount = unit.getEngine().integralHeatSinkCapacity();
        for (Mounted mount : unit.getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                equipmentList.addCrit(mount.getType());
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                equipmentList.addCrit(mount.getType());
            }
        }
        for (Mounted mount : unit.getMisc()) {
            if (mount.getLocation() == Entity.LOC_NONE && !isEngineHeatSink(mount)) {
                equipmentList.addCrit(mount.getType());
            }
        }
    }

    private boolean isEngineHeatSink(Mounted mount) {
        if (UnitUtil.isHeatSink(mount) && engineHeatSinkCount > 0) {
            engineHeatSinkCount--;
            return engineHeatSinkCount >= 0;
        }

        return false;
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
        equipmentList.updateMech(unit);
        equipmentList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(getWidth() * 90 / 100, getHeight() * 90 / 100));
        equipmentScroll.repaint();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }
}
