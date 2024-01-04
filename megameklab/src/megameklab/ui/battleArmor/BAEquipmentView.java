/*
 * MegaMekLab
 * Copyright (c) 2008-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.util.*;
import megameklab.util.BattleArmorUtil;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAEquipmentView extends IView implements ActionListener {
    @SuppressWarnings("unused")
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JComboBox<EquipmentType> equipmentCombo = new JComboBox<>();
    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private Vector<EquipmentType> equipmentTypes;

    private String ADD_COMMAND = "ADD";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    public BAEquipmentView(EntitySource eSource) {
        super(eSource);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        topPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        equipmentList = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.EQUIPMENT);

        equipmentTable.setModel(equipmentList);
        equipmentList.initColumnSizes(equipmentTable);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentScroll.setPreferredSize(new Dimension((getWidth() * 90) / 100, (getHeight() * 8) / 10));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        topPanel.add(equipmentCombo);
        topPanel.add(addButton);

        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        rightPanel.add(topPanel);
        rightPanel.add(equipmentScroll);
        rightPanel.add(buttonPanel);

        mainPanel.add(rightPanel);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();

            if (BattleArmorUtil.isBAEquipment(eq, getBattleArmor())) {
                masterEquipmentList.add(eq);
            }
        }

        masterEquipmentList.sort(StringUtils.equipmentTypeComparator());
        this.add(mainPanel);
        loadEquipmentTable();

        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('l');
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void loadEquipmentCombo() {
        equipmentCombo.setRenderer(new EquipmentListCellRenderer(getBattleArmor()));
        equipmentCombo.setKeySelectionManager(new EquipmentListCellKeySelectionManager());
        equipmentCombo.removeAllItems();
        equipmentTypes = new Vector<>();

        for (EquipmentType eq : masterEquipmentList) {
            if (UnitUtil.isLegal(getBattleArmor(), eq)) {
                equipmentTypes.add(eq);
                equipmentCombo.addItem(eq);
            }
        }
    }

    private void loadEquipmentTable() {
        for (Mounted mount : getBattleArmor().getMisc()) {
            if (UnitUtil.isArmorOrStructure(mount.getType())) {
                continue;
            }
            if (BattleArmorUtil.isBAEquipment(mount.getType(), getBattleArmor())) {
                equipmentList.addCrit(mount);
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
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(ADD_COMMAND)) {
            boolean success = false;
            Mounted mount = null;
            try {
                mount = getBattleArmor().addEquipment(equipmentTypes.elementAt(equipmentCombo.getSelectedIndex()), Entity.LOC_NONE, false);
                success = mount != null;
            } catch (LocationFullException ignored) {
                // this can't happen, we add to Entity.LOC_NONE
            }
            if (success) {
                equipmentList.addCrit(mount);
            }
        } else if (evt.getActionCommand().equals(REMOVE_COMMAND)) {
            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();

            for (; count > 0; count--) {
                if (startRow > -1) {
                    equipmentList.removeMounted(startRow);
                    equipmentList.removeCrit(startRow);
                }
            }
        } else if (evt.getActionCommand().equals(REMOVEALL_COMMAND)) {
            removeAllEquipment();
        } else {
            return;
        }
        fireTableRefresh();
    }

    public void updateEquipment() {
        equipmentList.removeAllCrits();
        loadEquipmentTable();
    }

    public void removeAllEquipment() {
        for (int count = 0; count < equipmentList.getRowCount(); count++) {
            equipmentList.removeMounted(count);
        }
        equipmentList.removeAllCrits();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getBattleArmor());
        equipmentList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension((getWidth() * 90) / 100, (getHeight() * 8) / 10));
        equipmentScroll.repaint();
    }

    public CriticalTableModel getEquipmentList() {
        return equipmentList;
    }
}
