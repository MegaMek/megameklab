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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.WeaponType;
import megamek.common.weapons.ArtilleryWeapon;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.WeaponListCellRenderer;

public class WeaponView extends IView implements ActionListener, MouseListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JTabbedPane leftPanel = new JTabbedPane(SwingConstants.RIGHT);
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

    private JPanel laserPane = new JPanel();
    private JPanel laserAmmoPane = new JPanel();
    private JPanel missilePane = new JPanel();
    private JPanel missileAmmoPane = new JPanel();
    private JPanel ballisticPane = new JPanel();
    private JPanel ballisticAmmoPane = new JPanel();
    private JPanel artilleryPane = new JPanel();
    private JPanel artilleryAmmoPane = new JPanel();

    private JList laserWeaponCombo = new JList();
    private JList laserAmmoCombo = new JList();
    private JList missileWeaponCombo = new JList();
    private JList missileAmmoCombo = new JList();
    private JList ballisticWeaponCombo = new JList();
    private JList ballisticAmmoCombo = new JList();
    private JList artilleryWeaponCombo = new JList();
    private JList artilleryAmmoCombo = new JList();

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
    private String REMOVE_COMMAND = "REMOVE";
    private String REMOVEALL_COMMAND = "REMOVEALL";

    public WeaponView(Tank unit) {
        super(unit);

        mainPanel.setLayout(new SpringLayout());
        rightPanel.setLayout(new SpringLayout());
        buttonPanel.setLayout(new SpringLayout());

        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        weaponList = new CriticalTableModel(unit, CriticalTableModel.WEAPONTABLE);

        equipmentTable.setModel(weaponList);
        weaponList.initColumnSizes(equipmentTable);
        for (int i = 0; i < weaponList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(weaponList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // equipmentScroll.setToolTipText("");
        //equipmentScroll.setPreferredSize(new Dimension(getWidth() * 3 / 4, getHeight() * 3 / 4));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        Dimension size = new Dimension(180, 150);
        List<JPanel> tPanels = new ArrayList<JPanel>();
        List<JScrollPane> tScrollPanes = new ArrayList<JScrollPane>();
        tPanels.add(laserPane);
        tPanels.add(laserAmmoPane);
        tPanels.add(missilePane);
        tPanels.add(missileAmmoPane);
        tPanels.add(ballisticPane);
        tPanels.add(ballisticAmmoPane);
        tPanels.add(artilleryPane);
        tPanels.add(artilleryAmmoPane);
        tScrollPanes.add(laserWeaponScroll);
        tScrollPanes.add(laserAmmoScroll);
        tScrollPanes.add(missileWeaponScroll);
        tScrollPanes.add(missileAmmoScroll);
        tScrollPanes.add(ballisticWeaponScroll);
        tScrollPanes.add(ballisticAmmoScroll);
        tScrollPanes.add(artilleryWeaponScroll);
        tScrollPanes.add(artilleryAmmoScroll);

        // match JPanels to JScrollPanes, and set
        // scrollpane properties
        for (JScrollPane tScrollPane : tScrollPanes) {
            tScrollPane.setWheelScrollingEnabled(true);
            tScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            tScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            tScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            tScrollPane.setPreferredSize(size);
            tScrollPane.setMaximumSize(size);
            tScrollPane.setBackground(Color.WHITE);
            tPanels.get(tScrollPanes.indexOf(tScrollPane)).setBackground(Color.WHITE);
            tScrollPane.setViewportView(tPanels.get(tScrollPanes.indexOf(tScrollPane)));
        }

        Font listFont = new Font("Arial", Font.PLAIN, 10);
        laserPane.add(laserWeaponCombo);
        laserWeaponCombo.setFixedCellWidth(155);
        laserWeaponCombo.setFont(listFont);
        laserWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        laserAmmoPane.add(laserAmmoCombo);
        laserAmmoCombo.setFixedCellWidth(155);
        laserAmmoCombo.setFont(listFont);
        laserAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        missilePane.add(missileWeaponCombo);
        missileWeaponCombo.setFixedCellWidth(155);
        missileWeaponCombo.setFont(listFont);
        missileWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        missileAmmoPane.add(missileAmmoCombo);
        missileAmmoCombo.setFixedCellWidth(155);
        missileAmmoCombo.setFont(listFont);
        missileAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        ballisticPane.add(ballisticWeaponCombo);
        ballisticWeaponCombo.setFixedCellWidth(155);
        ballisticWeaponCombo.setFont(listFont);
        ballisticWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        ballisticAmmoPane.add(ballisticAmmoCombo);
        ballisticAmmoCombo.setFixedCellWidth(155);
        ballisticAmmoCombo.setFont(listFont);
        ballisticAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        artilleryPane.add(artilleryWeaponCombo);
        artilleryWeaponCombo.setFixedCellWidth(155);
        artilleryWeaponCombo.setFont(listFont);
        artilleryWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        artilleryAmmoPane.add(artilleryAmmoCombo);
        artilleryAmmoCombo.setFixedCellWidth(155);
        artilleryAmmoCombo.setFont(listFont);
        artilleryAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

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

            if (!UnitUtil.isUnitWeapon(eq, unit)) {
                continue;
            }

            if (eq instanceof WeaponType) {

                WeaponType weapon = (WeaponType) eq;

                if (weapon.hasFlag(WeaponType.F_ENERGY) || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType() == AmmoType.T_PLASMA))) {
                    masterLaserWeaponList.add(eq);
                } else if ((eq.hasFlag(WeaponType.F_BALLISTIC) && (weapon.getAmmoType() != AmmoType.T_NA))) {
                    masterBallisticWeaponList.add(eq);
                } else if ((eq.hasFlag(WeaponType.F_MISSILE) && (weapon.getAmmoType() != AmmoType.T_NA))) {
                    masterMissileWeaponList.add(eq);
                } else if (weapon instanceof ArtilleryWeapon) {
                    masterArtilleryWeaponList.add(eq);
                }
            } else if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON)) && eq.hasFlag(MiscType.F_TALON)) {
                if (eq.hasFlag(MiscType.F_CLUB) && (eq.hasSubType(MiscType.S_CLUB) || eq.hasSubType(MiscType.S_TREE_CLUB))) {
                    continue;
                }
                masterPhysicalWeaponList.add(eq);
            } else if (((eq instanceof MiscType) && eq.hasFlag(MiscType.F_AP_POD))) {
                masterBallisticWeaponList.add(eq);
            }
        }

        Collections.sort(masterLaserWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterBallisticWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterMissileWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterPhysicalWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterArtilleryWeaponList, StringUtils.equipmentTypeComparator());

        loadWeaponsTable();
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
        laserWeaponAddButton.setMnemonic('A');
        laserAmmoAddButton.setMnemonic('d');
        missileWeaponAddButton.setMnemonic('A');
        missileAmmoAddButton.setMnemonic('d');
        ballisticWeaponAddButton.setMnemonic('A');
        ballisticAmmoAddButton.setMnemonic('d');
        artilleryWeaponAddButton.setMnemonic('A');
        artilleryAmmoAddButton.setMnemonic('d');

    }

    private void loadWeaponCombos() {

        subLaserWeaponList.removeAllElements();
        subMissileWeaponList.removeAllElements();
        subBallisticWeaponList.removeAllElements();
        subArtilleryWeaponList.removeAllElements();

        Vector<String> equipmentList = new Vector<String>();
        for (EquipmentType eq : masterLaserWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subLaserWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        laserWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterMissileWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subMissileWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        missileWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterBallisticWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subBallisticWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        ballisticWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterArtilleryWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subArtilleryWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        artilleryWeaponCombo.setListData(equipmentList);

    }

    private void loadWeaponsTable() {
        for (Mounted mount : unit.getWeaponList()) {
            if (UnitUtil.isUnitWeapon(mount.getType(), getTank())) {
                weaponList.addCrit(mount);
            }
        }

        for (Mounted mount : unit.getAmmo()) {
            if (mount.getShotsLeft() > 1) {
                weaponList.addCrit(mount);
            }
        }
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        loadWeaponCombos();
        weaponList.removeAllCrits();
        loadWeaponsTable();
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
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void fireTableRefresh() {
        weaponList.updateUnit(unit);
        weaponList.refreshModel();
        //equipmentScroll.setPreferredSize(new Dimension(getWidth() * 65 / 100, getHeight() * 80 / 100));
        //equipmentScroll.repaint();
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

        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVEALL_COMMAND);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LASERWEAPONADD_COMMAND)) {
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : laserWeaponCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subLaserWeaponList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(LASERAMMOADD_COMMAND) && (subLaserAmmoList.size() > 0)) {
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : laserAmmoCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subLaserAmmoList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(MISSILEWEAPONADD_COMMAND)) {
            try {
                if (missileWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : missileWeaponCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subMissileWeaponList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);

                        // MegaMek automatically adds a ton of ammo for oneshots
                        // for tracking. We do not need this in MLab
                        if (mount.getType().hasFlag(WeaponType.F_ONESHOT)) {
                            unit.getEquipment().remove(unit.getEquipment().size() - 1);
                            unit.getAmmo().remove(unit.getAmmo().size() - 1);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(MISSILEAMMOADD_COMMAND)) {
            try {
                if (missileAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : missileAmmoCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subMissileAmmoList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(BALLISTICWEAPONADD_COMMAND)) {
            try {
                if (ballisticWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : ballisticWeaponCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subBallisticWeaponList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                        // MegaMek automatically adds a ton of ammo for oneshots
                        // for tracking. We do not need this in MLab
                        if (mount.getType().hasFlag(WeaponType.F_ONESHOT)) {
                            unit.getEquipment().remove(unit.getEquipment().size() - 1);
                            unit.getAmmo().remove(unit.getAmmo().size() - 1);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(BALLISTICAMMOADD_COMMAND)) {
            try {
                if (ballisticAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : ballisticAmmoCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subBallisticAmmoList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(ARTILLERYWEAPONADD_COMMAND)) {
            try {
                if (artilleryWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : artilleryWeaponCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subArtilleryWeaponList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(ARTILLERYAMMOADD_COMMAND)) {
            try {
                if (artilleryAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : artilleryAmmoCombo.getSelectedIndices()) {
                        Mounted mount = unit.addEquipment(subArtilleryAmmoList.elementAt(index), Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
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
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JList) {
            loadAmmo(e.getComponent());
        }
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        loadAmmo(arg0.getComponent());
    }

    public void keyTyped(KeyEvent arg0) {
    }

    private void loadAmmo(Component o) {

        if (o instanceof JList) {
            JList list = (JList) o;
            if (list.equals(laserWeaponCombo)) {
                subLaserAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subLaserWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                if (weapon.getAmmoType() != AmmoType.T_NA) {
                    for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                        if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                            subLaserAmmoList.add(ammo);
                            equipmentList.add(ammo.getInternalName());
                        }
                    }
                }
                laserAmmoCombo.setListData(equipmentList);
            } else if (list.equals(missileWeaponCombo)) {
                subMissileAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subMissileWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR) && !weapon.hasFlag(WeaponType.F_ONESHOT)) {
                        subMissileAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                missileAmmoCombo.setListData(equipmentList);
            } else if (list.equals(ballisticWeaponCombo)) {
                subBallisticAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subBallisticWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subBallisticAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                ballisticAmmoCombo.setListData(equipmentList);
            } else if (list.equals(artilleryWeaponCombo)) {
                subArtilleryAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subArtilleryWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subArtilleryAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                artilleryAmmoCombo.setListData(equipmentList);
            }
        }
    }

}