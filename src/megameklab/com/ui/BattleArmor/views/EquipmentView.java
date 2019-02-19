/*
 * MegaMekLab
 * Copyright (C) 2008 - jtighe (torren@users.sourceforge.net)
 * Copyright (C) 2018 - The MegaMek Team
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
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

    @SuppressWarnings("unused")
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
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
        // equipmentScroll.setToolTipText("");
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

            if (UnitUtil.isUnitEquipment(eq, eSource.getEntity())) {
                masterEquipmentList.add(eq);
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.equipmentTypeComparator());
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
        equipmentTypes = new Vector<EquipmentType>();

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
            if (UnitUtil.isUnitEquipment(mount.getType(), getBattleArmor())) {
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

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADD_COMMAND)) {
            boolean success = false;
            Mounted mount = null;
            try {
                mount = getBattleArmor().addEquipment(equipmentTypes.elementAt(equipmentCombo.getSelectedIndex()),
                        Entity.LOC_NONE, false);
                success = mount != null;
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
            if (success) {
                equipmentList.addCrit(mount);
            }
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
