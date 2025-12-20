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
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.units.Entity;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.logging.MMLogger;
import megameklab.MMLConstants;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.ui.util.SpringLayoutHelper;
import megameklab.ui.util.WeaponListCellRenderer;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;

public class CVWeaponView extends IView implements ActionListener, MouseListener, KeyListener {
    private static final MMLogger logger = MMLogger.create(CVWeaponView.class);

    private RefreshListener refresh;

    private final JButton laserWeaponAddButton = new JButton("Add");
    private final JButton laserAmmoAddButton = new JButton("Add");
    private final JButton missileWeaponAddButton = new JButton("Add");
    private final JButton missileAmmoAddButton = new JButton("Add");
    private final JButton ballisticWeaponAddButton = new JButton("Add");
    private final JButton ballisticAmmoAddButton = new JButton("Add");
    private final JButton artilleryWeaponAddButton = new JButton("Add");
    private final JButton artilleryAmmoAddButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");
    private final JButton removeAllButton = new JButton("Remove All");

    private final JList<String> laserWeaponCombo = new JList<>();
    private final JList<String> laserAmmoCombo = new JList<>();
    private final JList<String> missileWeaponCombo = new JList<>();
    private final JList<String> missileAmmoCombo = new JList<>();
    private final JList<String> ballisticWeaponCombo = new JList<>();
    private final JList<String> ballisticAmmoCombo = new JList<>();
    private final JList<String> artilleryWeaponCombo = new JList<>();
    private final JList<String> artilleryAmmoCombo = new JList<>();

    private final CriticalTableModel weaponList;
    private final Vector<EquipmentType> masterLaserWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> masterMissileWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> masterBallisticWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> masterArtilleryWeaponList = new Vector<>(10, 1);

    private final Vector<EquipmentType> subLaserWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subLaserAmmoList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subMissileWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subMissileAmmoList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subBallisticWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subBallisticAmmoList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subArtilleryWeaponList = new Vector<>(10, 1);
    private final Vector<EquipmentType> subArtilleryAmmoList = new Vector<>(10, 1);

    private final JTable equipmentTable = new JTable();

    private final String LASER_WEAPON_ADD_COMMAND = "ADD_LASER_WEAPON";
    private final String LASER_AMMO_ADD_COMMAND = "ADD_LASER_AMMO";
    private final String MISSILE_WEAPON_ADD_COMMAND = "ADD_MISSILE_WEAPON";
    private final String MISSILE_AMMO_ADD_COMMAND = "ADD_MISSILE_AMMO";
    private final String BALLISTIC_WEAPON_ADD_COMMAND = "ADD_BALLISTIC_WEAPON";
    private final String BALLISTIC_AMMO_ADD_COMMAND = "ADD_BALLISTIC_AMMO";
    private final String ARTILLERY_WEAPON_ADD_COMMAND = "ADD_ARTILLERY_WEAPON";
    private final String ARTILLERY_AMMO_ADD_COMMAND = "ADD_ARTILLERY_AMMO";
    private final String REMOVE_COMMAND = "REMOVE";
    private final String REMOVE_ALL_COMMAND = "REMOVE_ALL";

    public CVWeaponView(EntitySource eSource) {
        super(eSource);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new SpringLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new SpringLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new SpringLayout());

        JTabbedPane leftPanel = new JTabbedPane(SwingConstants.RIGHT);
        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        weaponList = new CriticalTableModel(eSource.getEntity(), CriticalTableModel.WEAPON_TABLE);

        equipmentTable.setModel(weaponList);
        weaponList.initColumnSizes(equipmentTable);
        for (int i = 0; i < weaponList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(weaponList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        JScrollPane equipmentScroll = new JScrollPane();
        equipmentScroll.setViewportView(equipmentTable);

        Dimension size = new Dimension(180, 150);
        List<JPanel> tPanels = new ArrayList<>();
        List<JScrollPane> tScrollPanes = new ArrayList<>();
        JPanel laserPane = new JPanel();
        tPanels.add(laserPane);
        JPanel laserAmmoPane = new JPanel();
        tPanels.add(laserAmmoPane);
        JPanel missilePane = new JPanel();
        tPanels.add(missilePane);
        JPanel missileAmmoPane = new JPanel();
        tPanels.add(missileAmmoPane);
        JPanel ballisticPane = new JPanel();
        tPanels.add(ballisticPane);
        JPanel ballisticAmmoPane = new JPanel();
        tPanels.add(ballisticAmmoPane);
        JPanel artilleryPane = new JPanel();
        tPanels.add(artilleryPane);
        JPanel artilleryAmmoPane = new JPanel();
        tPanels.add(artilleryAmmoPane);
        JScrollPane laserWeaponScroll = new JScrollPane();
        tScrollPanes.add(laserWeaponScroll);
        JScrollPane laserAmmoScroll = new JScrollPane();
        tScrollPanes.add(laserAmmoScroll);
        JScrollPane missileWeaponScroll = new JScrollPane();
        tScrollPanes.add(missileWeaponScroll);
        JScrollPane missileAmmoScroll = new JScrollPane();
        tScrollPanes.add(missileAmmoScroll);
        JScrollPane ballisticWeaponScroll = new JScrollPane();
        tScrollPanes.add(ballisticWeaponScroll);
        JScrollPane ballisticAmmoScroll = new JScrollPane();
        tScrollPanes.add(ballisticAmmoScroll);
        JScrollPane artilleryWeaponScroll = new JScrollPane();
        tScrollPanes.add(artilleryWeaponScroll);
        JScrollPane artilleryAmmoScroll = new JScrollPane();
        tScrollPanes.add(artilleryAmmoScroll);

        // match JPanels to JScrollPanes, and set ScrollPane properties
        for (JScrollPane tScrollPane : tScrollPanes) {
            tScrollPane.setWheelScrollingEnabled(true);
            tScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            tScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            tScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            tScrollPane.setPreferredSize(size);
            tScrollPane.setMaximumSize(size);
            tScrollPane.setBackground(UIManager.getColor("ScrollPane.background"));
            tPanels.get(tScrollPanes.indexOf(tScrollPane)).setBackground(UIManager.getColor("Panel.background"));
            tScrollPane.setViewportView(tPanels.get(tScrollPanes.indexOf(tScrollPane)));
        }

        Font listFont = new Font(MMLConstants.FONT_ARIAL, Font.PLAIN, 10);
        laserPane.add(laserWeaponCombo);
        laserWeaponCombo.setFixedCellWidth(155);
        laserWeaponCombo.setFont(listFont);
        laserWeaponCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));
        laserAmmoPane.add(laserAmmoCombo);
        laserAmmoCombo.setFixedCellWidth(155);
        laserAmmoCombo.setFont(listFont);
        laserAmmoCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));

        missilePane.add(missileWeaponCombo);
        missileWeaponCombo.setFixedCellWidth(155);
        missileWeaponCombo.setFont(listFont);
        missileWeaponCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));
        missileAmmoPane.add(missileAmmoCombo);
        missileAmmoCombo.setFixedCellWidth(155);
        missileAmmoCombo.setFont(listFont);
        missileAmmoCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));

        ballisticPane.add(ballisticWeaponCombo);
        ballisticWeaponCombo.setFixedCellWidth(155);
        ballisticWeaponCombo.setFont(listFont);
        ballisticWeaponCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));
        ballisticAmmoPane.add(ballisticAmmoCombo);
        ballisticAmmoCombo.setFixedCellWidth(155);
        ballisticAmmoCombo.setFont(listFont);
        ballisticAmmoCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));

        artilleryPane.add(artilleryWeaponCombo);
        artilleryWeaponCombo.setFixedCellWidth(155);
        artilleryWeaponCombo.setFont(listFont);
        artilleryWeaponCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));
        artilleryAmmoPane.add(artilleryAmmoCombo);
        artilleryAmmoCombo.setFixedCellWidth(155);
        artilleryAmmoCombo.setFont(listFont);
        artilleryAmmoCombo.setCellRenderer(new WeaponListCellRenderer(getTank()));

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
        Vector<EquipmentType> masterPhysicalWeaponList = new Vector<>(10, 1);
        while (weaponTypes.hasMoreElements()) {
            EquipmentType eq = weaponTypes.nextElement();

            if (!UnitUtil.isUnitWeapon(eq, getTank())) {
                continue;
            }

            if (eq instanceof WeaponType weapon) {
                if (weapon.hasFlag(WeaponType.F_ENERGY)
                      || (weapon.hasFlag(WeaponType.F_PLASMA) && (weapon.getAmmoType()
                      == AmmoType.AmmoTypeEnum.PLASMA))) {
                    masterLaserWeaponList.add(eq);
                } else if ((eq.hasFlag(WeaponType.F_BALLISTIC) && (weapon.getAmmoType() != AmmoType.AmmoTypeEnum.NA))) {
                    masterBallisticWeaponList.add(eq);
                } else if ((eq.hasFlag(WeaponType.F_MISSILE) && (weapon.getAmmoType() != AmmoType.AmmoTypeEnum.NA))) {
                    masterMissileWeaponList.add(eq);
                } else if (weapon instanceof ArtilleryWeapon) {
                    masterArtilleryWeaponList.add(eq);
                }
            } else if ((eq instanceof MiscType) && (eq.hasFlag(MiscType.F_CLUB) || eq.hasFlag(MiscType.F_HAND_WEAPON))
                  && eq.hasFlag(MiscType.F_TALON)) {
                if (eq.hasFlag(MiscType.F_CLUB)
                      && (eq.hasAnyFlag(MiscTypeFlag.S_CLUB, MiscTypeFlag.S_TREE_CLUB))) {
                    continue;
                }
                masterPhysicalWeaponList.add(eq);
            } else if (((eq instanceof MiscType) && eq.hasFlag(MiscType.F_AP_POD))) {
                masterBallisticWeaponList.add(eq);
            }
        }

        masterLaserWeaponList.sort(StringUtils.equipmentTypeComparator());
        masterBallisticWeaponList.sort(StringUtils.equipmentTypeComparator());
        masterMissileWeaponList.sort(StringUtils.equipmentTypeComparator());
        masterPhysicalWeaponList.sort(StringUtils.equipmentTypeComparator());
        masterArtilleryWeaponList.sort(StringUtils.equipmentTypeComparator());

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

        Vector<String> equipmentList = new Vector<>();
        for (EquipmentType eq : masterLaserWeaponList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                subLaserWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        laserWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<>();
        for (EquipmentType eq : masterMissileWeaponList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                subMissileWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        missileWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<>();
        for (EquipmentType eq : masterBallisticWeaponList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                subBallisticWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        ballisticWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<>();
        for (EquipmentType eq : masterArtilleryWeaponList) {
            if (UnitUtil.isLegal(getTank(), eq)) {
                subArtilleryWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        artilleryWeaponCombo.setListData(equipmentList);
    }

    private void loadWeaponsTable() {
        for (Mounted<?> mount : getTank().getWeaponList()) {
            if (UnitUtil.isUnitWeapon(mount.getType(), getTank())) {
                weaponList.addCrit(mount);
            }
        }

        for (Mounted<?> mount : getTank().getAmmo()) {
            if (mount.getUsableShotsLeft() > 1) {
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
        weaponList.updateUnit(getTank());
        weaponList.refreshModel();
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

        laserWeaponAddButton.setActionCommand(LASER_WEAPON_ADD_COMMAND);
        laserAmmoAddButton.setActionCommand(LASER_AMMO_ADD_COMMAND);
        missileWeaponAddButton.setActionCommand(MISSILE_WEAPON_ADD_COMMAND);
        missileAmmoAddButton.setActionCommand(MISSILE_AMMO_ADD_COMMAND);
        ballisticWeaponAddButton.setActionCommand(BALLISTIC_WEAPON_ADD_COMMAND);
        ballisticAmmoAddButton.setActionCommand(BALLISTIC_AMMO_ADD_COMMAND);
        artilleryWeaponAddButton.setActionCommand(ARTILLERY_WEAPON_ADD_COMMAND);
        artilleryAmmoAddButton.setActionCommand(ARTILLERY_AMMO_ADD_COMMAND);

        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVE_ALL_COMMAND);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(LASER_WEAPON_ADD_COMMAND)) {
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : laserWeaponCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subLaserWeaponList.elementAt(index), Entity.LOC_NONE,
                              false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(LASER_AMMO_ADD_COMMAND) && !subLaserAmmoList.isEmpty()) {
            try {
                if (laserWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : laserAmmoCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subLaserAmmoList.elementAt(index), Entity.LOC_NONE,
                              false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(MISSILE_WEAPON_ADD_COMMAND)) {
            try {
                if (missileWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : missileWeaponCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subMissileWeaponList.elementAt(index),
                              Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);

                        // MegaMek automatically adds a ton of ammo for one-shots
                        // for tracking. We do not need this in MLab
                        if (mount.getType().hasFlag(WeaponType.F_ONE_SHOT)) {
                            getTank().getEquipment().remove(getTank().getEquipment().size() - 1);
                            getTank().getAmmo().remove(getTank().getAmmo().size() - 1);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(MISSILE_AMMO_ADD_COMMAND)) {
            try {
                if (missileAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : missileAmmoCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subMissileAmmoList.elementAt(index), Entity.LOC_NONE,
                              false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(BALLISTIC_WEAPON_ADD_COMMAND)) {
            try {
                if (ballisticWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : ballisticWeaponCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subBallisticWeaponList.elementAt(index),
                              Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                        // MegaMek automatically adds a ton of ammo for one-shots
                        // for tracking. We do not need this in MLab
                        if (mount.getType().hasFlag(WeaponType.F_ONE_SHOT)) {
                            getTank().getEquipment().remove(getTank().getEquipment().size() - 1);
                            getTank().getAmmo().remove(getTank().getAmmo().size() - 1);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(BALLISTIC_AMMO_ADD_COMMAND)) {
            try {
                if (ballisticAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : ballisticAmmoCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subBallisticAmmoList.elementAt(index),
                              Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(ARTILLERY_WEAPON_ADD_COMMAND)) {
            try {
                if (artilleryWeaponCombo.getSelectedIndex() > -1) {
                    for (int index : artilleryWeaponCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subArtilleryWeaponList.elementAt(index),
                              Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(ARTILLERY_AMMO_ADD_COMMAND)) {
            try {
                if (artilleryAmmoCombo.getSelectedIndex() > -1) {
                    for (int index : artilleryAmmoCombo.getSelectedIndices()) {
                        Mounted<?> mount = getTank().addEquipment(subArtilleryAmmoList.elementAt(index),
                              Entity.LOC_NONE, false);
                        weaponList.addCrit(mount);
                    }
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        } else if (evt.getActionCommand().equals(REMOVE_COMMAND)) {
            int startRow = equipmentTable.getSelectedRow();
            int count = equipmentTable.getSelectedRowCount();

            for (; count > 0; count--) {
                if (startRow > -1) {
                    weaponList.removeMounted(startRow);
                    weaponList.removeCrit(startRow);
                }
            }
            refresh.refreshAll();
        } else if (evt.getActionCommand().equals(REMOVE_ALL_COMMAND)) {
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
        refresh.refreshAll();
    }

    public CriticalTableModel getWeaponList() {
        return weaponList;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {

    }

    @Override
    public void mouseEntered(MouseEvent evt) {

    }

    @Override
    public void mouseExited(MouseEvent evt) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {

    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        // FIXME : I'm very much prone to causing future issues
        if (evt.getSource() instanceof JList) {
            loadAmmo(evt.getComponent());
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        loadAmmo(evt.getComponent());
    }

    @Override
    public void keyTyped(KeyEvent evt) {

    }

    private void loadAmmo(Component component) {
        if (component instanceof JList<?> list) {
            if (list.equals(laserWeaponCombo)) {
                subLaserAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subLaserWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<>();
                if (weapon.getAmmoType() != AmmoType.AmmoTypeEnum.NA) {
                    for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                        if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(getTank(), ammo)
                              && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                            subLaserAmmoList.add(ammo);
                            equipmentList.add(ammo.getInternalName());
                        }
                    }
                }
                laserAmmoCombo.setListData(equipmentList);
            } else if (list.equals(missileWeaponCombo)) {
                subMissileAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subMissileWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize())
                          && UnitUtil.isLegal(getTank(), ammo)
                          && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)
                          && !weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                        subMissileAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                missileAmmoCombo.setListData(equipmentList);
            } else if (list.equals(ballisticWeaponCombo)) {
                subBallisticAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subBallisticWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize())
                          && UnitUtil.isLegal(getTank(), ammo)
                          && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subBallisticAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                ballisticAmmoCombo.setListData(equipmentList);
            } else if (list.equals(artilleryWeaponCombo)) {
                subArtilleryAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subArtilleryWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONE_SHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize())
                          && UnitUtil.isLegal(getTank(), ammo)
                          && !ammo.hasFlag(AmmoType.F_BATTLEARMOR)) {
                        subArtilleryAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                artilleryAmmoCombo.setListData(equipmentList);
            }
        }
    }
}
