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
import java.util.Collections;
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
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.ArtilleryWeapon;
import megamek.common.weapons.InfantryWeapon;
import megamek.common.weapons.LRMWeapon;
import megamek.common.weapons.LRTWeapon;
import megamek.common.weapons.MRMWeapon;
import megamek.common.weapons.RLWeapon;
import megamek.common.weapons.SRMWeapon;
import megamek.common.weapons.SRTWeapon;

import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

public class WeaponView extends IView implements ActionListener, MouseListener, KeyListener {

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
    private JButton artilleryWeaponAddButton = new JButton("Add");
    private JButton artilleryAmmoAddButton = new JButton("Add");
    private JButton physicalWeaponButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JScrollPane laserWeaponScroll = new JScrollPane();
    private JScrollPane laserAmmoScroll = new JScrollPane();
    private JScrollPane missileWeaponScroll = new JScrollPane();
    private JScrollPane missileAmmoScroll = new JScrollPane();
    private JScrollPane ballisticWeaponScroll = new JScrollPane();
    private JScrollPane ballisticAmmoScroll = new JScrollPane();
    private JScrollPane artilleryWeaponScroll = new JScrollPane();
    private JScrollPane artilleryAmmoScroll = new JScrollPane();
    private JScrollPane physicalWeaponScroll = new JScrollPane();

    private JPanel laserPane = new JPanel();
    private JPanel laserAmmoPane = new JPanel();
    private JPanel missilePane = new JPanel();
    private JPanel missileAmmoPane = new JPanel();
    private JPanel ballisticPane = new JPanel();
    private JPanel ballisticAmmoPane = new JPanel();
    private JPanel artilleryPane = new JPanel();
    private JPanel artilleryAmmoPane = new JPanel();
    private JPanel physicalWeaponPane = new JPanel();

    private JList laserWeaponCombo = new JList();
    private JList laserAmmoCombo = new JList();
    private JList missileWeaponCombo = new JList();
    private JList missileAmmoCombo = new JList();
    private JList ballisticWeaponCombo = new JList();
    private JList ballisticAmmoCombo = new JList();
    private JList artilleryWeaponCombo = new JList();
    private JList artilleryAmmoCombo = new JList();
    private JList physicalWeaponCombo = new JList();

    private CriticalTableModel weaponList;
    private Vector<EquipmentType> masterLaserWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterMissileWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterArtilleryWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterPhysicalWeaponList = new Vector<EquipmentType>(10, 1);

    private Vector<EquipmentType> subLaserWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subLaserAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subMissileWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subMissileAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subBallisticAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subArtilleryWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subArtilleryAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subPhysicalWeaponList = new Vector<EquipmentType>(10, 1);

    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();

    private String LASERWEAPONADD_COMMAND = "ADDLASERWEAPON";
    private String LASERAMMOADD_COMMAND = "ADDLASERAMMO";
    private String MISSILEWEAPONADD_COMMAND = "ADDMISSILEWEAPON";
    private String MISSILEAMMOADD_COMMAND = "ADDMISSILEAMMO";
    private String BALLISTICWEAPONADD_COMMAND = "ADDBALLISTICWEAPON";
    private String BALLISTICAMMOADD_COMMAND = "ADDBALLISTICAMMO";
    private String ARTILLERYWEAPONADD_COMMAND = "ADDARTILLERYWEAPON";
    private String ARTILLERYAMMOADD_COMMAND = "ADDARTILLERYAMMO";
    private String PHYSICALWEAPONADD_COMMAND = "ADDPHYSICALWEAPON";
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

        artilleryWeaponScroll.setWheelScrollingEnabled(true);
        artilleryWeaponScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        artilleryWeaponScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        artilleryWeaponScroll.setPreferredSize(size);
        artilleryWeaponScroll.setMaximumSize(size);
        artilleryWeaponScroll.setBackground(Color.WHITE);
        artilleryPane.setBackground(Color.WHITE);
        artilleryWeaponScroll.setViewportView(artilleryPane);

        artilleryAmmoScroll.setWheelScrollingEnabled(true);
        artilleryAmmoScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        artilleryAmmoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        artilleryAmmoScroll.setPreferredSize(size);
        artilleryAmmoScroll.setMaximumSize(size);
        artilleryAmmoScroll.setBackground(Color.WHITE);
        artilleryAmmoPane.setBackground(Color.WHITE);
        artilleryAmmoScroll.setViewportView(artilleryAmmoPane);

        physicalWeaponScroll.setWheelScrollingEnabled(true);
        physicalWeaponScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        physicalWeaponScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        physicalWeaponScroll.setPreferredSize(new Dimension(180, 350));
        physicalWeaponScroll.setMaximumSize(new Dimension(180, 350));
        physicalWeaponScroll.setBackground(Color.WHITE);
        physicalWeaponPane.setBackground(Color.WHITE);
        physicalWeaponScroll.setViewportView(physicalWeaponPane);

        Font listFont = new Font("Arial",Font.PLAIN,10);
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

        artilleryPane.add(artilleryWeaponCombo);
        artilleryWeaponCombo.setFixedCellWidth(165);
        artilleryWeaponCombo.setAutoscrolls(true);
        artilleryWeaponCombo.setFont(listFont);
        artilleryAmmoPane.add(artilleryAmmoCombo);
        artilleryAmmoCombo.setFixedCellWidth(165);
        artilleryAmmoCombo.setAutoscrolls(true);
        artilleryAmmoCombo.setFont(listFont);

        physicalWeaponPane.add(physicalWeaponCombo);
        physicalWeaponCombo.setFixedCellWidth(165);
        physicalWeaponCombo.setAutoscrolls(true);
        physicalWeaponCombo.setFont(listFont);

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

        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(artilleryWeaponScroll);
        tab.add(artilleryWeaponAddButton);
        tab.add(artilleryAmmoScroll);
        tab.add(artilleryAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Artillery", tab);

        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(physicalWeaponScroll);
        tab.add(physicalWeaponButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Physical", tab);


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

            if (eq instanceof InfantryWeapon ) {
                continue;
            }

            if (eq instanceof WeaponType) {

                WeaponType weapon = (WeaponType) eq;
                
                if ( weapon.hasFlag(WeaponType.F_BA_WEAPON) ) {
                    continue;
                }
                
                if ( weapon.getTonnage(unit) <= 0 ){
                    continue;
                }
                
                if (  weapon.isCapital() || weapon.isSubCapital() ){
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
                } else if ( weapon instanceof ArtilleryWeapon ) {
                    masterArtilleryWeaponList.add(eq);
                }
            }else if ( eq instanceof MiscType && ( eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON)) ){
                if ( eq.hasFlag(MiscType.F_CLUB) && ( eq.hasSubType(MiscType.S_CLUB) || eq.hasSubType(MiscType.S_TREE_CLUB)) ){
                    continue;
                }
                masterPhysicalWeaponList.add(eq);
            }
        }

        Collections.sort(masterLaserWeaponList,StringUtils.equipmentTypeComparator());
        Collections.sort(masterBallisticWeaponList,StringUtils.equipmentTypeComparator());
        Collections.sort(masterMissileWeaponList,StringUtils.equipmentTypeComparator());
        Collections.sort(masterPhysicalWeaponList,StringUtils.equipmentTypeComparator());
        Collections.sort(masterArtilleryWeaponList,StringUtils.equipmentTypeComparator());
        
        loadWeaponsTable();
    }

    private void loadWeaponCombos() {

        subLaserWeaponList.removeAllElements();
        subMissileWeaponList.removeAllElements();
        subBallisticWeaponList.removeAllElements();
        subPhysicalWeaponList.removeAllElements();
        subArtilleryWeaponList.removeAllElements();
        
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

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterArtilleryWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subArtilleryWeaponList.add(eq);
                equipmentList.add(eq.getName());
            }
        }
        artilleryWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterPhysicalWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subPhysicalWeaponList.add(eq);
                equipmentList.add(eq.getName());
            }
        }
        physicalWeaponCombo.setListData(equipmentList);
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
        artilleryWeaponAddButton.removeActionListener(this);
        artilleryAmmoAddButton.removeActionListener(this);
        artilleryWeaponCombo.removeMouseListener(this);
        artilleryWeaponCombo.removeKeyListener(this);
        physicalWeaponButton.removeActionListener(this);
        physicalWeaponCombo.removeMouseListener(this);
        physicalWeaponCombo.removeKeyListener(this);
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
        artilleryWeaponAddButton.addActionListener(this);
        artilleryAmmoAddButton.addActionListener(this);

        laserWeaponCombo.addMouseListener(this);
        missileWeaponCombo.addMouseListener(this);
        ballisticWeaponCombo.addMouseListener(this);
        artilleryWeaponCombo.addMouseListener(this);
        laserWeaponCombo.addKeyListener(this);
        missileWeaponCombo.addKeyListener(this);
        ballisticWeaponCombo.addKeyListener(this);
        artilleryWeaponCombo.addKeyListener(this);
        
        physicalWeaponButton.addActionListener(this);
        physicalWeaponCombo.addMouseListener(this);
        physicalWeaponCombo.addKeyListener(this);


        removeButton.addActionListener(this);
        removeAllButton.addActionListener(this);

        laserWeaponAddButton.setActionCommand(LASERWEAPONADD_COMMAND);
        laserAmmoAddButton.setActionCommand(LASERAMMOADD_COMMAND);
        missileWeaponAddButton.setActionCommand(MISSILEWEAPONADD_COMMAND);
        missileAmmoAddButton.setActionCommand(MISSILEAMMOADD_COMMAND);
        ballisticWeaponAddButton.setActionCommand(BALLISTICWEAPONADD_COMMAND);
        ballisticAmmoAddButton.setActionCommand(BALLISTICAMMOADD_COMMAND);
        artilleryWeaponAddButton.setActionCommand(ARTILLERYWEAPONADD_COMMAND);
        artilleryAmmoAddButton.setActionCommand(ARTILLERYAMMOADD_COMMAND);
        physicalWeaponButton.setActionCommand(PHYSICALWEAPONADD_COMMAND);

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
                    Mounted mount = new Mounted(unit, subMissileWeaponList.elementAt(missileWeaponCombo.getSelectedIndex())); 
                    unit.addEquipment(mount, Entity.LOC_NONE, false);
                    weaponList.addCrit(subMissileWeaponList.elementAt(missileWeaponCombo.getSelectedIndex()));
                    
                    //MegaMek automatically adds a ton of ammo for onshots for tracking. We do not need this in MLab
                    if ( mount.getType().hasFlag(WeaponType.F_ONESHOT) ){
                        unit.getEquipment().remove(unit.getEquipment().size()-1);
                        unit.getAmmo().remove(unit.getAmmo().size()-1);
                    }
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
                    Mounted mount = new Mounted(unit, subBallisticWeaponList.elementAt(ballisticWeaponCombo.getSelectedIndex())); 
                    unit.addEquipment(mount, Entity.LOC_NONE, false);
                    weaponList.addCrit(subBallisticWeaponList.elementAt(ballisticWeaponCombo.getSelectedIndex()));
                    //MegaMek automatically adds a ton of ammo for onshots for tracking. We do not need this in MLab
                    if ( mount.getType().hasFlag(WeaponType.F_ONESHOT) ){
                        unit.getEquipment().remove(unit.getEquipment().size()-1);
                        unit.getAmmo().remove(unit.getAmmo().size()-1);
                    }
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
        } else if (e.getActionCommand().equals(ARTILLERYWEAPONADD_COMMAND)) {
            try {
                if (artilleryWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subArtilleryWeaponList.elementAt(artilleryWeaponCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subArtilleryWeaponList.elementAt(artilleryWeaponCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(ARTILLERYAMMOADD_COMMAND)) {
            try {
                if (artilleryAmmoCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subArtilleryAmmoList.elementAt(artilleryAmmoCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subArtilleryAmmoList.elementAt(artilleryAmmoCombo.getSelectedIndex()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(PHYSICALWEAPONADD_COMMAND)) {
            try {
                if (physicalWeaponCombo.getSelectedIndex() > -1) {
                    unit.addEquipment(new Mounted(unit, subPhysicalWeaponList.elementAt(physicalWeaponCombo.getSelectedIndex())), Entity.LOC_NONE, false);
                    weaponList.addCrit(subPhysicalWeaponList.elementAt(physicalWeaponCombo.getSelectedIndex()));
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
        subArtilleryAmmoList.removeAllElements();

        if (o instanceof JList) {
            JList list = (JList) o;
            if (list.equals(laserWeaponCombo)) {
                WeaponType weapon = (WeaponType) subLaserWeaponList.elementAt(list.getSelectedIndex());

                if ( weapon.hasFlag(WeaponType.F_ONESHOT) ){
                	return;
                }
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
                
                if ( weapon.hasFlag(WeaponType.F_ONESHOT) ){
                	return;
                }
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
                if ( weapon.hasFlag(WeaponType.F_ONESHOT) ){
                	return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if (ammo.getRackSize() == weapon.getRackSize() && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subBallisticAmmoList.add(ammo);
                        equipmentList.add(ammo.getName());
                    }
                }
                ballisticAmmoCombo.setListData(equipmentList);
            } else if (list.equals(artilleryWeaponCombo)) {
                WeaponType weapon = (WeaponType) subArtilleryWeaponList.elementAt(list.getSelectedIndex());
                if ( weapon.hasFlag(WeaponType.F_ONESHOT) ){
                	return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if (ammo.getRackSize() == weapon.getRackSize() && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subArtilleryAmmoList.add(ammo);
                        equipmentList.add(ammo.getName());
                    }
                }
                artilleryAmmoCombo.setListData(equipmentList);
            }
        }
    }
    
}