/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.battleArmor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.EquipmentListCellKeySelectionManager;
import megameklab.ui.util.EquipmentListCellRenderer;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.BattleArmorUtil;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

/**
 * @author jtighe (torren@users.sourceforge.net)
 * @deprecated No indicated uses.
 */
@Deprecated(since = "0.50.06", forRemoval = true)
public class BAEquipmentView extends IView implements ActionListener {

    private final JButton addButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");
    private final JButton removeAllButton = new JButton("Remove All");

    private final JComboBox<EquipmentType> equipmentCombo = new JComboBox<>();
    private final CriticalTableModel equipmentList;
    private final Vector<EquipmentType> masterEquipmentList = new Vector<>(10, 1);
    private final JTable equipmentTable = new JTable();
    private final JScrollPane equipmentScroll = new JScrollPane();
    private Vector<EquipmentType> equipmentTypes;

    private final String ADD_COMMAND = "ADD";
    private final String REMOVE_COMMAND = "REMOVE";
    private final String REMOVEALL_COMMAND = "REMOVEALL";

    public BAEquipmentView(EntitySource eSource) {
        super(eSource);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
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
        for (Mounted<?> mount : getBattleArmor().getMisc()) {
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
        switch (evt.getActionCommand()) {
            case ADD_COMMAND -> {
                boolean success = false;
                Mounted<?> mount = null;
                try {
                    mount = getBattleArmor().addEquipment(equipmentTypes.elementAt(equipmentCombo.getSelectedIndex()),
                          Entity.LOC_NONE,
                          false);
                    success = mount != null;
                } catch (LocationFullException ignored) {
                    // this can't happen, we add to Entity.LOC_NONE
                }
                if (success) {
                    equipmentList.addCrit(mount);
                }
            }
            case REMOVE_COMMAND -> {
                int startRow = equipmentTable.getSelectedRow();
                int count = equipmentTable.getSelectedRowCount();

                for (; count > 0; count--) {
                    if (startRow > -1) {
                        equipmentList.removeMounted(startRow);
                        equipmentList.removeCrit(startRow);
                    }
                }
            }
            case REMOVEALL_COMMAND -> removeAllEquipment();
            default -> {
                return;
            }
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
