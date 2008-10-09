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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.InfantryWeapon;
import megamek.common.weapons.NavalACWeapon;
import megamek.common.weapons.NavalGaussWeapon;
import megamek.common.weapons.NavalLaserWeapon;
import megamek.common.weapons.NavalPPCWeapon;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.UnitUtil;

public class WeaponView extends View implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton laserWeaponAddButton = new JButton("Add");
    private JButton laserAmmoAddButton = new JButton("Add");
    private JButton missileWeaponAddButton = new JButton("Add");
    private JButton missileAmmoAddButton = new JButton("Add");
    private JButton ballisticWeaponAddButton = new JButton("Add");
    private JButton ballisticAmmoAddButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JComboBox laserWeaponCombo = new JComboBox();
    private JComboBox laserAmmoCombo = new JComboBox();
    private JComboBox missileWeaponCombo = new JComboBox();
    private JComboBox missileAmmoCombo = new JComboBox();
    private JComboBox ballisticWeaponCombo = new JComboBox();
    private JComboBox ballisticAmmoCombo = new JComboBox();

    private JLabel laserWeaponLabel = new JLabel("Energy Weapons");
    private JLabel laserAmmoLabel = new JLabel("Energy Ammo");
    private JLabel missileWeaponLabel = new JLabel("Missile Weapons");
    private JLabel missileAmmoLabel = new JLabel("Missile Ammo");
    private JLabel ballisticWeaponLabel = new JLabel("Ballistic Weapons");
    private JLabel ballisticAmmoLabel = new JLabel("Ballistic Ammo");
    
    private CriticalTableModel weaponList;
    private Vector<EquipmentType> masterLaserWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterLaserAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterMissileWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterMissileAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterBallisticAmmoList = new Vector<EquipmentType>(10, 1);

    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private TreeMap<String, EquipmentType> equipmentTypes;

    private String LASERWEAPONADD_COMMAND = "ADDLASERWEAPON";
    private String LASERAMMOADD_COMMAND = "ADDLASERAMMO";
    private String MISSILEWEAPONADD_COMMAND = "ADDMISSILEWEAPON";
    private String MISSILEAMMOADD_COMMAND = "ADDMISSILEAMMO";
    private String BALLISTICWEAPONADD_COMMAND = "ADDBALLISTICWEAPON";
    private String BALLISTICAMMOADD_COMMAND = "ADDBALLISTICAMMO";
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    public WeaponView(Mech unit) {
        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        weaponList = new CriticalTableModel(unit,CriticalTableModel.WEAPONTABLE);

        this.equipmentTable.setModel(weaponList);
        this.weaponList.initColumnSizes(this.equipmentTable);
        for (int i = 0; i < this.weaponList.getColumnCount(); i++)
            this.equipmentTable.getColumnModel().getColumn(i).setCellRenderer(this.weaponList.getRenderer());

        this.equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // equipmentScroll.setToolTipText("");
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() * 3 / 4, this.getHeight() * 3 / 4));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        leftPanel.add(laserWeaponLabel);
        leftPanel.add(laserWeaponCombo);
        leftPanel.add(laserWeaponAddButton);
        leftPanel.add(laserAmmoLabel);
        leftPanel.add(laserAmmoCombo);
        leftPanel.add(laserAmmoAddButton);

        leftPanel.add(missileWeaponLabel);
        leftPanel.add(missileWeaponCombo);
        leftPanel.add(missileWeaponAddButton);
        leftPanel.add(missileAmmoLabel);
        leftPanel.add(missileAmmoCombo);
        leftPanel.add(missileAmmoAddButton);

        leftPanel.add(ballisticWeaponLabel);
        leftPanel.add(ballisticWeaponCombo);
        leftPanel.add(ballisticWeaponAddButton);
        leftPanel.add(ballisticAmmoLabel);
        leftPanel.add(ballisticAmmoCombo);
        leftPanel.add(ballisticAmmoAddButton);

        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        rightPanel.add(equipmentScroll);
        rightPanel.add(buttonPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        Enumeration<EquipmentType> weaponTypes = EquipmentType.getAllTypes();

        while (weaponTypes.hasMoreElements()) {
            EquipmentType eq = weaponTypes.nextElement();

            if (eq instanceof InfantryWeapon || eq instanceof NavalACWeapon || eq instanceof NavalGaussWeapon || eq instanceof NavalLaserWeapon || eq instanceof NavalPPCWeapon) {
                continue;
            }

            if (eq instanceof WeaponType) {

                WeaponType weapon = (WeaponType) eq;

                if ((weapon.getName().startsWith("LRM") || weapon.getName().startsWith("LRT") || weapon.getName().startsWith("RL")) && weapon.getRackSize() != 5 && weapon.getRackSize() != 10 && weapon.getRackSize() != 15 && weapon.getRackSize() != 20) {
                    continue;
                }
                if ((weapon.getName().startsWith("SRM") || weapon.getName().startsWith("SRT")) && weapon.getRackSize() != 2 && weapon.getRackSize() != 4 && weapon.getRackSize() != 6) {
                    continue;
                }
                if (weapon.getName().startsWith("MRM") && weapon.getRackSize() < 5) {
                    continue;
                }
                
                if (eq.hasFlag(WeaponType.F_ENERGY) ) {
                    
                    if ( weapon.hasFlag(WeaponType.F_PLASMA) && weapon.getAmmoType() == AmmoType.T_NA ){
                        continue;
                    }
                    masterLaserWeaponList.add(eq);
                } else if (eq.hasFlag(WeaponType.F_BALLISTIC) && weapon.getAmmoType() != AmmoType.T_NA ) {
                    masterBallisticWeaponList.add(eq);
                } else if (eq.hasFlag(WeaponType.F_MISSILE) && weapon.getAmmoType() != AmmoType.T_NA ) {
                    masterMissileWeaponList.add(eq);
                }
            } else if (eq instanceof AmmoType) {
                AmmoType ammo = (AmmoType) eq;
                if (ammo.hasFlag(AmmoType.F_BATTLEARMOR) || ammo.getAmmoType() == AmmoType.T_VEHICLE_FLAMER) {
                    continue;
                }
                if (ammo.getAmmoType() == AmmoType.T_PLASMA) {
                    masterLaserAmmoList.add(eq);
                } else if (ammo.hasFlag(AmmoType.F_HOTLOAD) || ammo.getAmmoType() == AmmoType.T_SRM  || ammo.getAmmoType() == AmmoType.T_SRM_ADVANCED  || ammo.getAmmoType() == AmmoType.T_SRM_STREAK  || ammo.getAmmoType() == AmmoType.T_SRM_TORPEDO) {
                    masterMissileAmmoList.add(eq);
                } else {
                    masterBallisticAmmoList.add(eq);
                }
            }
        }

        this.add(mainPanel);
        loadWeaponsTable();
    }

    private void loadWeaponCombos() {

        laserAmmoCombo.removeAllItems();
        laserWeaponCombo.removeAllItems();
        missileAmmoCombo.removeAllItems();
        missileWeaponCombo.removeAllItems();
        ballisticAmmoCombo.removeAllItems();
        ballisticWeaponCombo.removeAllItems();

        equipmentTypes = new TreeMap<String, EquipmentType>();

        for (EquipmentType eq : masterLaserWeaponList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                laserWeaponCombo.addItem(eq.getName());
            }
        }
        for (EquipmentType eq : masterLaserAmmoList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                laserAmmoCombo.addItem(eq.getName());
            }
        }
        for (EquipmentType eq : masterMissileWeaponList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                missileWeaponCombo.addItem(eq.getName());
            }
        }
        for (EquipmentType eq : masterMissileAmmoList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                missileAmmoCombo.addItem(eq.getName());
            }
        }
        for (EquipmentType eq : masterBallisticWeaponList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                ballisticWeaponCombo.addItem(eq.getName());
            }
        }
        for (EquipmentType eq : masterBallisticAmmoList) {
            if (UnitUtil.isLegal(unit,eq.getTechLevel())){
                equipmentTypes.put(eq.getName(), eq);
                ballisticAmmoCombo.addItem(eq.getName());
            }
        }
    }

    private void loadWeaponsTable() {
        for (Mounted mount : unit.getWeaponList()) {
            weaponList.addCrit(mount.getType());
        }
        for (Mounted mount : unit.getAmmo()) {
            weaponList.addCrit(mount.getType());
        }
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        loadWeaponCombos();
        addAllListeners();
        fireTableRefresh();

    }

    private void removeAllListeners() {
        laserWeaponAddButton.removeActionListener(this);
        laserAmmoAddButton.removeActionListener(this);
        missileWeaponAddButton.removeActionListener(this);
        missileAmmoAddButton.removeActionListener(this);
        ballisticWeaponAddButton.removeActionListener(this);
        ballisticAmmoAddButton.removeActionListener(this);
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void fireTableRefresh() {
        weaponList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() * 65 /100, this.getHeight()* 80/100));
        equipmentScroll.repaint();
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
        }
    }

    private void addAllListeners() {
        laserWeaponAddButton.addActionListener(this);
        laserAmmoAddButton.addActionListener(this);
        missileWeaponAddButton.addActionListener(this);
        missileAmmoAddButton.addActionListener(this);
        ballisticWeaponAddButton.addActionListener(this);
        ballisticAmmoAddButton.addActionListener(this);

        removeButton.addActionListener(this);
        removeAllButton.addActionListener(this);

        laserWeaponAddButton.setActionCommand(LASERWEAPONADD_COMMAND);
        laserAmmoAddButton.setActionCommand(LASERAMMOADD_COMMAND);
        missileWeaponAddButton.setActionCommand(MISSILEWEAPONADD_COMMAND);
        missileAmmoAddButton.setActionCommand(MISSILEAMMOADD_COMMAND);
        ballisticWeaponAddButton.setActionCommand(BALLISTICWEAPONADD_COMMAND);
        ballisticAmmoAddButton.setActionCommand(BALLISTICAMMOADD_COMMAND);

        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVEALL_COMMAND);

        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LASERWEAPONADD_COMMAND)) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(laserWeaponCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(laserWeaponCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(LASERAMMOADD_COMMAND) && laserAmmoCombo.getItemCount() > 0) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(laserAmmoCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(laserAmmoCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(MISSILEWEAPONADD_COMMAND)) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(missileWeaponCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(missileWeaponCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(MISSILEAMMOADD_COMMAND)) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(missileAmmoCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(missileAmmoCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(BALLISTICWEAPONADD_COMMAND)) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(ballisticWeaponCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(ballisticWeaponCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(BALLISTICAMMOADD_COMMAND)) {
            try{
                unit.addEquipment(new Mounted(unit, equipmentTypes.get(ballisticAmmoCombo.getSelectedItem().toString())), Entity.LOC_NONE,false);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            weaponList.addCrit(equipmentTypes.get(ballisticAmmoCombo.getSelectedItem().toString()));
        } else if (e.getActionCommand().equals(REMOVE_COMMAND)) {

            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();

            for (; count > 0; count--) {
                if (startRow > -1) {
                    weaponList.removeMounted(startRow);
                    weaponList.removeCrit(startRow);
                }
            }
        } else if (e.getActionCommand().equals(REMOVEALL_COMMAND)) {
            for ( int count = 0; count < weaponList.getRowCount(); count++ ){
                weaponList.removeMounted(count);
            }
            weaponList.removeAllCrits();
        } else {
            return;
        }
        if ( unit.hasTargComp() ){
            UnitUtil.updateTC(unit);
        }
        fireTableRefresh();

    }
    
    public CriticalTableModel getWeaponList(){
        return weaponList;
    }

}