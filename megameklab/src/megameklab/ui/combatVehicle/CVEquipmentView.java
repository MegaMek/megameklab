/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.combatVehicle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.EquipmentListCellKeySelectionManager;
import megameklab.ui.util.EquipmentListCellRenderer;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.StringUtils;
import megameklab.util.TankUtil;
import megameklab.util.UnitUtil;

public class CVEquipmentView extends IView implements ActionListener {
    private static final MMLogger logger = MMLogger.create(CVEquipmentView.class);

    private RefreshListener refresh;

    private final JButton addButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");
    private final JButton removeAllButton = new JButton("Remove All");

    private final JComboBox<EquipmentType> equipmentCombo = new JComboBox<>();
    private final CriticalTableModel equipmentList;
    private final Vector<EquipmentType> masterEquipmentList = new Vector<>(10, 1);
    private final JTable equipmentTable = new JTable();

    private final String ADD_COMMAND = "ADD";
    private final String REMOVE_COMMAND = "REMOVE";
    private final String REMOVE_ALL_COMMAND = "REMOVE_ALL";

    public CVEquipmentView(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        equipmentList = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.EQUIPMENT);

        equipmentTable.setModel(equipmentList);
        equipmentList.initColumnSizes(equipmentTable);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentTable.setMaximumSize(new Dimension(800, 500));
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(equipmentTable);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(equipmentCombo, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        add(topPanel, BorderLayout.NORTH);
        add(equipmentScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Enumeration<EquipmentType> miscTypes = EquipmentType.getAllTypes();
        while (miscTypes.hasMoreElements()) {
            EquipmentType eq = miscTypes.nextElement();

            if (TankUtil.isTankEquipment(eq, getTank())) {
                masterEquipmentList.add(eq);
            }
        }

        masterEquipmentList.sort(StringUtils.equipmentTypeComparator());
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

        for (EquipmentType eq : masterEquipmentList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                equipmentCombo.addItem(eq);
            }
        }
    }

    private void loadEquipmentTable() {
        for (Mounted<?> mount : getTank().getMisc()) {

            if (UnitUtil.isHeatSink(mount) || UnitUtil.isArmorOrStructure(mount.getType())) {
                continue;
            }
            if (TankUtil.isTankEquipment(mount.getType(), getTank())) {
                equipmentList.addCrit(mount);
            }
        }
    }

    private void loadHeatSinks() {
        int engineHeatSinks = 10;
        for (Mounted<?> mount : getTank().getMisc()) {
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
        while (location < equipmentList.getRowCount()) {
            Mounted<?> mount = (Mounted<?>) equipmentList.getValueAt(location, CriticalTableModel.EQUIPMENT);
            if (UnitUtil.isHeatSink(mount)) {
                try {
                    equipmentList.removeCrit(location);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return;
                } catch (Exception ex) {
                    logger.error("", ex);
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
        removeAllButton.setActionCommand(REMOVE_ALL_COMMAND);
        addButton.setMnemonic('A');
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ADD_COMMAND)) {
            EquipmentType equip = (EquipmentType) equipmentCombo.getSelectedItem();
            Mounted<?> mount = null;
            boolean isMisc = equip instanceof MiscType;
            if (isMisc && equip.hasFlag(MiscType.F_TARGETING_COMPUTER)) {
                if (!UnitUtil.hasTargComp(getTank())) {
                    mount = UnitUtil.updateTC(getTank(), equip);
                }
            } else {
                try {
                    mount = getTank().addEquipment(equip, Entity.LOC_NONE, false);
                } catch (Exception ex) {
                    logger.error("", ex);
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
        } else if (e.getActionCommand().equals(REMOVE_ALL_COMMAND)) {
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
