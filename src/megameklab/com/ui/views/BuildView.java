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

package megameklab.com.ui.views;

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

import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.com.ui.util.CriticalTable;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;

public class BuildView extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private Mech unit;

    private JPanel mainPanel = new JPanel();
    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<EquipmentType>(10, 1);
    private CriticalTable equipmentTable = new CriticalTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private int engineHeatSinkCount = 0;

    public BuildView(Mech unit) {
        this.unit = unit;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        equipmentList = new CriticalTableModel(this.unit, false);

        this.equipmentTable.setModel(equipmentList);
        this.equipmentList.initColumnSizes(this.equipmentTable);
        for (int i = 0; i < this.equipmentList.getColumnCount(); i++)
            this.equipmentTable.getColumnModel().getColumn(i).setCellRenderer(this.equipmentList.getRenderer());

        this.equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // equipmentScroll.setToolTipText("");
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
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
            if (mount.getLocation() == Mech.LOC_NONE) {
                equipmentList.addCrit(mount.getType());
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Mech.LOC_NONE) {
                equipmentList.addCrit(mount.getType());
            }
        }
        for (Mounted mount : unit.getMisc()) {
            if (mount.getLocation() == Mech.LOC_NONE && !isEngineHeatSink(mount)) {
                equipmentList.addCrit(mount.getType());
            }
        }
    }

    private boolean isEngineHeatSink(Mounted mount) {
        if ((mount.getType().hasFlag(MiscType.F_HEAT_SINK) || mount.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK) || mount.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) && engineHeatSinkCount > 0) {
            engineHeatSinkCount--;
            return engineHeatSinkCount >= 0;
        }
        
        return false;
    }

    public void refresh() {
        removeAllListeners();
        addAllListeners();
        loadEquipmentTable();
        fireTableRefresh();
    }

    private void removeAllListeners() {
    }

    private void addAllListeners() {
    }

    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() * 90 / 100, this.getHeight() * 90 / 100));
        equipmentScroll.repaint();
    }

    public CriticalTableModel getTableModel() {
        return this.equipmentList;
    }

    public JTable getTable() {
        return this.equipmentTable;
    }
}
