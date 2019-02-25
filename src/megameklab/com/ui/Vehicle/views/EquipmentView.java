/*
 * MegaMekLab - Copyright (C) 2009
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

package megameklab.com.ui.Vehicle.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.EquipmentListCellKeySelectionManager;
import megameklab.com.util.EquipmentListCellRenderer;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

public class EquipmentView extends IView implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private RefreshListener refresh;

    private JPanel topPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JComboBox<EquipmentType> equipmentCombo = new JComboBox<EquipmentType>();
    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<EquipmentType>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private Vector<EquipmentType> equipmentTypes;

    private String ADD_COMMAND = "ADD";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    public EquipmentView(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());

        topPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        equipmentList = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.EQUIPMENT);

        equipmentTable.setModel(equipmentList);
        equipmentList.initColumnSizes(equipmentTable);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentTable.setMaximumSize(new Dimension(800,500));
        equipmentScroll.setViewportView(equipmentTable);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(equipmentCombo, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        add(topPanel, BorderLayout.NORTH);
        add(equipmentScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();

            if (UnitUtil.isUnitEquipment(eq, eSource.getEntity())) {
                masterEquipmentList.add(eq);
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.equipmentTypeComparator());
        loadEquipmentTable();

        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('l');
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void loadEquipmentCombo() {
        equipmentCombo.setRenderer(new EquipmentListCellRenderer(getTank()));
        equipmentCombo.setKeySelectionManager(new EquipmentListCellKeySelectionManager());
        equipmentCombo.removeAllItems();
        equipmentTypes = new Vector<EquipmentType>();

        for (EquipmentType eq : masterEquipmentList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                equipmentTypes.add(eq);
                equipmentCombo.addItem(eq);
            }
        }
    }

    private void loadEquipmentTable() {
        for (Mounted mount : getTank().getMisc()) {

            if (UnitUtil.isHeatSink(mount) || UnitUtil.isArmorOrStructure(mount.getType())) {
                continue;
            }
            if (UnitUtil.isUnitEquipment(mount.getType(), getTank())) {
                equipmentList.addCrit(mount);
            }
        }
    }

    private void loadHeatSinks() {
        int engineHeatSinks = 10;// UnitUtil.getBaseChassisHeatSinks(unit);
        for (Mounted mount : getTank().getMisc()) {
            if (UnitUtil.isHeatSink(mount)) {
                if (engineHeatSinks-- > 0) {
                    continue;
                }
                equipmentList.addCrit(mount);
            }
        }

    }

    private void removeHeatSinks() {
        int location = 0;
        for (; location < equipmentList.getRowCount();) {

            Mounted mount = (Mounted) equipmentList.getValueAt(location, CriticalTableModel.EQUIPMENT);
            if (UnitUtil.isHeatSink(mount)) {
                try {
                    equipmentList.removeCrit(location);
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                location++;
            }
        }
    }

    public void refresh() {
        removeAllListeners();
        loadEquipmentCombo();
        updateEquipment();
        addAllListeners();
        fireTableRefresh();
    }

    public void updateEquipment() {
        removeHeatSinks();
        equipmentList.removeAllCrits();
        loadHeatSinks();
        loadEquipmentTable();
    }

    private void removeAllListeners() {
        addButton.removeActionListener(this);
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void addAllListeners() {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        removeAllButton.addActionListener(this);
        addButton.setActionCommand(ADD_COMMAND);
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVEALL_COMMAND);
        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADD_COMMAND)) {

            EquipmentType equip = (EquipmentType) equipmentCombo.getSelectedItem();
            Mounted mount = null;
            boolean isMisc = equip instanceof MiscType;
            if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
                if (!UnitUtil.hasTargComp(getTank())) {
                    mount = UnitUtil.updateTC(getTank(), equip);
                }
            } else {
                try {
                    mount = getTank().addEquipment(equip, Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            equipmentList.addCrit(mount);
        } else if (e.getActionCommand().equals(REMOVE_COMMAND)) {

            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();

            for (; count > 0; count--) {
                if (startRow > -1) {
                    equipmentList.removeMounted(startRow);
                    equipmentList.removeCrit(startRow);
                }
            }
        } else if (e.getActionCommand().equals(REMOVEALL_COMMAND)) {
            removeAllEquipment();
        } else {
            return;
        }
        fireTableRefresh();
    }

    public void removeAllEquipment() {
        removeHeatSinks();
        for (int count = 0; count < equipmentList.getRowCount(); count++) {
            equipmentList.removeMounted(count);
        }
        equipmentList.removeAllCrits();
        loadHeatSinks();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getTank());
        equipmentList.refreshModel();
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
        }
    }


    public CriticalTableModel getEquipmentList() {
        return equipmentList;
    }

}