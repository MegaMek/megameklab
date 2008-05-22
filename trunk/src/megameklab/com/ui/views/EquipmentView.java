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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;

public class EquipmentView extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private Mech unit;
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JComboBox equipmentCombo = new JComboBox();
    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<EquipmentType>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private TreeMap<String, EquipmentType> equipmentTypes;

    private String ADD_COMMAND = "ADD";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    public EquipmentView(Mech unit) {
        this.unit = unit;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        topPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        equipmentList = new CriticalTableModel(unit);

        this.equipmentTable.setModel(equipmentList);
        this.equipmentList.initColumnSizes(this.equipmentTable);
        for (int i = 0; i < this.equipmentList.getColumnCount(); i++)
            this.equipmentTable.getColumnModel().getColumn(i).setCellRenderer(this.equipmentList.getRenderer());

        // sorter.addMouseListenerToHeaderInTable(this.equipmentTable);
        this.equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // ListSelectionModel rowSM = this.equipmentTable.getSelectionModel();
        equipmentScroll.setToolTipText("Click on the column header to sort.");
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() * 3 / 4));
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

            if (eq instanceof MiscType && !eq.hasFlag(MiscType.F_ASSAULT_CLAW) && !eq.hasFlag(MiscType.F_BOARDING_CLAW) && !eq.hasFlag(MiscType.F_CLUB) && !eq.hasFlag(MiscType.F_MAGNETIC_CLAMP) && !eq.hasFlag(MiscType.F_PARAFOIL) && !eq.hasFlag(MiscType.F_MINE) && !eq.hasFlag(MiscType.F_TOOLS) && !eq.hasFlag(MiscType.F_REACTIVE) && !eq.hasFlag(MiscType.F_REFLECTIVE) && !eq.hasFlag(MiscType.F_HAND_WEAPON) && !eq.hasFlag(MiscType.F_FERRO_FIBROUS) && !eq.hasFlag(MiscType.F_FIRE_RESISTANT) && !eq.hasFlag(MiscType.F_ARMORED_CHASSIS) && !eq.hasFlag(MiscType.F_ENDO_STEEL) && !eq.hasFlag(MiscType.F_LIFTHOIST) && !eq.hasFlag(MiscType.F_SEARCHLIGHT) && !eq.hasFlag(MiscType.F_TRACTOR_MODIFICATION) && !eq.hasFlag(MiscType.F_VACUUM_PROTECTION) && !eq.hasFlag(MiscType.F_HEAT_SINK) && !eq.hasFlag(MiscType.F_LASER_HEAT_SINK) && !eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK) && !eq.hasFlag(MiscType.F_STEALTH)
                    && !eq.getName().equals(EquipmentType.getStructureTypeName(MiscType.T_STRUCTURE_STANDARD)) && !eq.getName().equals(EquipmentType.getArmorTypeName(EquipmentType.T_ARMOR_HARDENED)) && !eq.getName().equals(BattleArmor.SINGLE_HEX_ECM)) {
                masterEquipmentList.add(eq);
            }
        }

        this.add(mainPanel);
        loadEquipmentTable();
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void loadEquipmentCombo() {

        equipmentCombo.removeAllItems();
        equipmentTypes = new TreeMap<String, EquipmentType>();

        for (EquipmentType eq : masterEquipmentList) {
            if ((eq.getTechLevel() == TechConstants.T_ALL || eq.getTechLevel() == TechConstants.T_ALLOWED_ALL || eq.getTechLevel() == TechConstants.T_TECH_UNKNOWN
            // IS
                    || (unit.getTechLevel() == TechConstants.T_IS_LEVEL_1 && eq.getTechLevel() == TechConstants.T_IS_LEVEL_1) || (unit.getTechLevel() == TechConstants.T_IS_LEVEL_2 && (eq.getTechLevel() == TechConstants.T_IS_LEVEL_1 || eq.getTechLevel() == TechConstants.T_IS_LEVEL_2)) || (unit.getTechLevel() == TechConstants.T_IS_LEVEL_3 && (eq.getTechLevel() == TechConstants.T_IS_LEVEL_1 || eq.getTechLevel() == TechConstants.T_IS_LEVEL_2 || eq.getTechLevel() == TechConstants.T_IS_LEVEL_3))

                    // Clan
                    || (unit.getTechLevel() == TechConstants.T_CLAN_LEVEL_2 && eq.getTechLevel() == TechConstants.T_CLAN_LEVEL_2) || (unit.getTechLevel() == TechConstants.T_CLAN_LEVEL_3 && (eq.getTechLevel() == TechConstants.T_CLAN_LEVEL_2 || eq.getTechLevel() == TechConstants.T_CLAN_LEVEL_3))) && eq instanceof MiscType) {
                equipmentTypes.put(eq.getName(), eq);
                equipmentCombo.addItem(eq.getName());
            }
        }
    }

    private void loadEquipmentTable(){
        for ( Mounted mount : unit.getMisc() ){
            equipmentList.addCrit(mount.getType());
        }
    }
    
    public void refresh() {
        removeAllListeners();
        loadEquipmentCombo();
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
            equipmentList.addCrit(equipmentTypes.get(equipmentCombo.getSelectedItem().toString()));
            fireTableRefresh();
        } else if (e.getActionCommand().equals(REMOVE_COMMAND)) {
            
            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();
            
            for (; count > 0; count--) {
                if (startRow > -1) {
                    equipmentList.removeCrit(startRow );
                }
            }
            fireTableRefresh();
        } else if (e.getActionCommand().equals(REMOVEALL_COMMAND)) {
            equipmentList.removeAllCrits();
            fireTableRefresh();
        }
    }

    private void fireTableRefresh() {
        equipmentList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() * 8 / 10));
        equipmentScroll.repaint();
        if (refresh != null) {
            refresh.refreshStatus();
        }
    }

}