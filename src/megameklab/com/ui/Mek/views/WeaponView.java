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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.InfantryWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.LRTWeapon;
import megamek.common.weapons.MRMWeapon;
import megamek.common.weapons.NavalACWeapon;
import megamek.common.weapons.NavalGaussWeapon;
import megamek.common.weapons.NavalLaserWeapon;
import megamek.common.weapons.NavalPPCWeapon;
import megamek.common.weapons.RLWeapon;
import megamek.common.weapons.SRMWeapon;
import megamek.common.weapons.SRTWeapon;

import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.UnitUtil;

public class WeaponView extends View implements ActionListener, MouseListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JTabbedPane leftPanel = new JTabbedPane(JTabbedPane.RIGHT);
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

    private JScrollPane laserWeaponScroll = new JScrollPane();
    private JScrollPane laserAmmoScroll = new JScrollPane();
    private JScrollPane missileWeaponScroll = new JScrollPane();
    private JScrollPane missileAmmoScroll = new JScrollPane();
    private JScrollPane ballisticWeaponScroll = new JScrollPane();
    private JScrollPane ballisticAmmoScroll = new JScrollPane();

    private JPanel laserPane = new JPanel();
    private JPanel laserAmmoPane = new JPanel();
    private JPanel missilePane = new JPanel();
    private JPanel missileAmmoPane = new JPanel();
    private JPanel ballisticPane = new JPanel();
    private JPanel ballisticAmmoPane = new JPanel();

    private JList laserWeaponCombo = new JList();
    private JList laserAmmoCombo = new JList();
    private JList missileWeaponCombo = new JList();
    private JList missileAmmoCombo = new JList();
    private JList ballisticWeaponCombo = new JList();
    private JList ballisticAmmoCombo = new JList();

    private CriticalTableModel weaponList;
    private Vector<EquipmentType> masterLaserWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterMissileWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterBallisticWeaponList = new Vector<EquipmentType>(10, 1);

    private Vector<EquipmentType> subLaserWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subLaserAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subMissileWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subMissileAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subBallisticAmmoList = new Vector<EquipmentType>(10, 1);

    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

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

        mainPanel.setLayout(new SpringLayout());
        rightPanel.setLayout(new SpringLayout());
        buttonPanel.setLayout(new SpringLayout());

        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        weaponList = new CriticalTableModel(unit, CriticalTableModel.WEAPONTABLE);

        this.equipmentTable.setModel(weaponList);
        this.weaponList.initColumnSizes(this.equipmentTable);
        for (int i = 0; i < this.weaponList.getColumnCount(); i++)
            this.equipmentTable.getColumnModel().getColumn(i).setCellRenderer(this.weaponList.getRenderer());

        this.equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // equipmentScroll.setToolTipText("");
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() * 3 / 4, this.getHeight() * 3 / 4));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        Dimension size = new Dimension(180, 150);

        laserWeaponScroll.setWheelScrollingEnabled(true);
        laserWeaponScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        laserWeaponScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        laserWeaponScroll.setPreferredSize(size);
        laserWeaponScroll.setMaximumSize(size);
        laserWeaponScroll.setBackground(Color.WHITE);
        laserPane.setBackground(Color.WHITE);
        laserWeaponScroll.setViewportView(laserPane);

        laserAmmoScroll.setWheelScrollingEnabled(true);
        laserAmmoScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        laserAmmoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        laserAmmoScroll.setPreferredSize(size);
        laserAmmoScroll.setMaximumSize(size);
        laserAmmoScroll.setBackground(Color.WHITE);
        laserAmmoPane.setBackground(Color.WHITE);
        laserAmmoScroll.setViewportView(laserAmmoPane);

        missileWeaponScroll.setWheelScrollingEnabled(true);
        missileWeaponScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        missileWeaponScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        missileWeaponScroll.setPreferredSize(size);
        missileWeaponScroll.setMaximumSize(size);
        missileWeaponScroll.setBackground(Color.WHITE);
        missilePane.setBackground(Color.WHITE);
        missileWeaponScroll.setViewportView(missilePane);

        missileAmmoScroll.setWheelScrollingEnabled(true);
        missileAmmoScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        missileAmmoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        missileAmmoScroll.setPreferredSize(size);
        missileAmmoScroll.setMaximumSize(size);
        missileAmmoScroll.setBackground(Color.WHITE);
        missileAmmoPane.setBackground(Color.WHITE);
        missileAmmoScroll.setViewportView(missileAmmoPane);

        ballisticWeaponScroll.setWheelScrollingEnabled(true);
        ballisticWeaponScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ballisticWeaponScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ballisticWeaponScroll.setPreferredSize(size);
        ballisticWeaponScroll.setMaximumSize(size);
        ballisticWeaponScroll.setBackground(Color.WHITE);
        ballisticPane.setBackground(Color.WHITE);
        ballisticWeaponScroll.setViewportView(ballisticPane);

        ballisticAmmoScroll.setWheelScrollingEnabled(true);
        ballisticAmmoScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ballisticAmmoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ballisticAmmoScroll.setPreferredSize(size);
        ballisticAmmoScroll.setMaximumSize(size);
        ballisticAmmoScroll.setBackground(Color.WHITE);
        ballisticAmmoPane.setBackground(Color.WHITE);
        ballisticAmmoScroll.setViewportView(ballisticAmmoPane);

        Font listFont = new Font("Eurostyle Lt Std Bold", Font.BOLD, 10);
        laserPane.add(laserWeaponCombo);
        laserWeaponCombo.setFixedCellWidth(165);
        laserWeaponCombo.setAutoscrolls(true);
        laserWeaponCombo.setFont(listFont);
        laserAmmoPane.add(laserAmmoCombo);
        laserAmmoCombo.setFixedCellWidth(165);
        laserAmmoCombo.setAutoscrolls(true);
        laserAmmoCombo.setFont(listFont);

        missilePane.add(missileWeaponCombo);
        missileWeaponCombo.setFixedCellWidth(165);
        missileWeaponCombo.setAutoscrolls(true);
        missileWeaponCombo.setFont(listFont);
        missileAmmoPane.add(missileAmmoCombo);
        missileAmmoCombo.setFixedCellWidth(165);
        missileAmmoCombo.setAutoscrolls(true);
        missileAmmoCombo.setFont(listFont);

        ballisticPane.add(ballisticWeaponCombo);
        ballisticWeaponCombo.setFixedCellWidth(165);
        ballisticWeaponCombo.setAutoscrolls(true);
        ballisticWeaponCombo.setFont(listFont);
        ballisticAmmoPane.add(ballisticAmmoCombo);
        ballisticAmmoCombo.setFixedCellWidth(165);
        ballisticAmmoCombo.setAutoscrolls(true);
        ballisticAmmoCombo.setFont(listFont);

        JPanel tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(laserWeaponScroll);
        tab.add(laserWeaponAddButton);
        tab.add(laserAmmoScroll);
        tab.add(laserAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Laser", tab);

        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(missileWeaponScroll);
        tab.add(missileWeaponAddButton);
        tab.add(missileAmmoScroll);
        tab.add(missileAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Missile", tab);

        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(ballisticWeaponScroll);
        tab.add(ballisticWeaponAddButton);
        tab.add(ballisticAmmoScroll);
        tab.add(ballisticAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Ballistic", tab);

        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        rightPanel.add(equipmentScroll);
        rightPanel.add(buttonPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        SpringLayoutHelper.setupSpringGrid(rightPanel, 1);
        SpringLayoutHelper.setupSpringGrid(buttonPanel, 2);
        SpringLayoutHelper.setupSpringGrid(mainPanel, 2);

        this.add(mainPanel);

        Enumeration<EquipmentType> weaponTypes = EquipmentType.getAllTypes();
        while (weaponTypes.hasMoreElements()) {
            EquipmentType eq = weaponTypes.nextElement();

            if (eq instanceof InfantryWeapon || eq instanceof NavalACWeapon || eq instanceof NavalGaussWeapon || eq instanceof NavalLaserWeapon || eq instanceof NavalPPCWeapon) {
                continue;
            }

            if (eq instanceof WeaponType) {

                WeaponType weapon = (WeaponType) eq;
                
                if ( weapon.getTonnage(unit) <= 0 ){
                    continue;
                }

                if ((weapon instanceof LRMWeapon || weapon instanceof LRTWeapon) && weapon.getRackSize() != 5 && weapon.getRackSize() != 10 && weapon.getRackSize() != 15 && weapon.getRackSize() != 20) {
                    continue;
                }
                if ((weapon instanceof SRMWeapon || weapon instanceof SRTWeapon) && weapon.getRackSize() != 2 && weapon.getRackSize() != 4 && weapon.getRackSize() != 6) {
                    continue;
                }
                if (weapon instanceof MRMWeapon && weapon.getRackSize() < 10) {
                    continue;
                }

                if (weapon instanceof RLWeapon && weapon.getRackSize() < 10) {
                    continue;
                }

                if (weapon.hasFlag(WeaponType.F_ENERGY) || (weapon.hasFlag(WeaponType.F_PLASMA) && weapon.getAmmoType() == AmmoType.T_PLASMA)) {

                    if (weapon.hasFlag(WeaponType.F_ENERGY) && weapon.hasFlag(WeaponType.F_PLASMA) && weapon.getAmmoType() == AmmoType.T_NA) {
                        continue;
                    }
                    masterLaserWeaponList.add(eq);
                } else if (eq.hasFlag(WeaponType.F_BALLISTIC) && weapon.getAmmoType() != AmmoType.T_NA) {
                    masterBallisticWeaponList.add(eq);
                } else if (eq.hasFlag(WeaponType.F_MISSILE) && weapon.getAmmoType() != AmmoType.T_NA) {
                    masterMissileWeaponList.add(eq);
                }
            }
        }

        loadWeaponsTable();
    }

    private void loadWeaponCombos() {

        subLaserWeaponList.removeAllElements();
        subMissileWeaponList.removeAllElements();
        subBallisticWeaponList.removeAllElements();

        Vector<String> equipmentList = new Vector<String>();
        for (EquipmentType eq : masterLaserWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subLaserWeaponList.add(eq);
                equipmentList.add(eq.getName());
            }
        }
        laserWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterMissileWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subMissileWeaponList.add(eq);
                equipmentList.add(eq.getName());
            }
        }
        missileWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterBallisticWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subBallisticWeaponList.add(eq);
                equipmentList.add(eq.getName());
            }
        }
        ballisticWeaponCombo.setListData(equipmentList);
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
        laserWeaponCombo.removeMouseListener(this);
        laserWeaponCombo.removeKeyListener(this);
        missileWeaponAddButton.removeActionListener(this);
        missileAmmoAddButton.removeActionListener(this);
        missileWeaponCombo.removeMouseListener(this);
        missileWeaponCombo.removeKeyListener(this);
        ballisticWeaponAddButton.removeActionListener(this);
        ballisticAmmoAddButton.removeActionListener(this);
        ballisticWeaponCombo.removeMouseListener(this);
        ballisticWeaponCombo.removeKeyListener(this);
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void fireTableRefresh() {
        weaponList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(this.getWidth() * 65 / 100, this.getHeight() * 80 / 100));
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

        laserWeaponCombo.addMouseListener(this);
        missileWeaponCombo.addMouseListener(this);
        ballisticWeaponCombo.addMouseListener(this);
        laserWeaponCombo.addKeyListener(this);
        missileWeaponCombo.addKeyListener(this);
        ballisticWeaponCombo.addKeyListener(this);

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
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subLaserWeaponList.elementAt(laserWeaponCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subLaserWeaponList.elementAt(laserWeaponCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(LASERAMMOADD_COMMAND) && subLaserAmmoList.size() > 0) {
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subLaserAmmoList.elementAt(laserAmmoCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subLaserAmmoList.elementAt(laserAmmoCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(MISSILEWEAPONADD_COMMAND)) {
            try {
                if (missileWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subMissileWeaponList.elementAt(missileWeaponCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subMissileWeaponList.elementAt(missileWeaponCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(MISSILEAMMOADD_COMMAND)) {
            try {
                if (missileAmmoCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subMissileAmmoList.elementAt(missileAmmoCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subMissileAmmoList.elementAt(missileAmmoCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(BALLISTICWEAPONADD_COMMAND)) {
            try {
                if (ballisticWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subBallisticWeaponList.elementAt(ballisticWeaponCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subBallisticWeaponList.elementAt(ballisticWeaponCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(BALLISTICAMMOADD_COMMAND)) {
            try {
                if (ballisticAmmoCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subBallisticAmmoList.elementAt(ballisticAmmoCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subBallisticAmmoList.elementAt(ballisticAmmoCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
            removeAllWeapons();
        } else {
            return;
        }
        
        if ( UnitUtil.hasTargComp(unit)) {
            UnitUtil.updateTC(unit);
        }
        fireTableRefresh();

    }

    public void removeAllWeapons() {
        for (int count = 0; count < weaponList.getRowCount(); count++) {
            weaponList.removeMounted(count);
        }
        weaponList.removeAllCrits();
    }
    
    public CriticalTableModel getWeaponList() {
        return weaponList;
    }

    public void mouseClicked(MouseEvent e) {
        loadAmmo(e.getComponent());
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        loadAmmo(arg0.getComponent());
    }

    public void keyTyped(KeyEvent arg0) {
    }

    private void loadAmmo(Component o) {
        subLaserAmmoList.removeAllElements();
        subMissileAmmoList.removeAllElements();
        subBallisticAmmoList.removeAllElements();

        if (o instanceof JList) {
            JList list = (JList) o;
            if (list.equals(laserWeaponCombo)) {
                WeaponType weapon = (WeaponType) subLaserWeaponList.elementAt(list.getSelectedIndex());

                Vector<String> equipmentList = new Vector<String>();
                if (weapon.getAmmoType() != AmmoType.T_NA) {
                    for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                        if (ammo.getRackSize() == weapon.getRackSize() && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                            subLaserAmmoList.add(ammo);
                            equipmentList.add(ammo.getName());
                        }
                    }
                }
                laserAmmoCombo.setListData(equipmentList);
            } else if (list.equals(missileWeaponCombo)) {
                WeaponType weapon = (WeaponType) subMissileWeaponList.elementAt(list.getSelectedIndex());
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if (ammo.getRackSize() == weapon.getRackSize() && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR) && !weapon.hasFlag(WeaponType.F_ONESHOT)) {
                        subMissileAmmoList.add(ammo);
                        equipmentList.add(ammo.getName());
                    }
                }
                missileAmmoCombo.setListData(equipmentList);
            } else if (list.equals(ballisticWeaponCombo)) {
                WeaponType weapon = (WeaponType) subBallisticWeaponList.elementAt(list.getSelectedIndex());
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if (ammo.getRackSize() == weapon.getRackSize() && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subBallisticAmmoList.add(ammo);
                        equipmentList.add(ammo.getName());
                    }
                }
                ballisticAmmoCombo.setListData(equipmentList);
            }
        }
    }

}