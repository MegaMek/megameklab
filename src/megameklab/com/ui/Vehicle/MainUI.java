/*
 * MegaMekLab - Copyright (C) 2009
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.Vehicle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Engine;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Vehicle.tabs.ArmorTab;
import megameklab.com.ui.Vehicle.tabs.BuildTab;
import megameklab.com.ui.Vehicle.tabs.EquipmentTab;
import megameklab.com.ui.Vehicle.tabs.StructureTab;
import megameklab.com.ui.Vehicle.tabs.WeaponTab;
import megameklab.com.util.MenuBarCreator;

public class MainUI extends MegaMekLabMainUI {

    /**
     *
     */
    private static final long serialVersionUID = -5836932822468918198L;

    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private ArmorTab armorTab;
    private EquipmentTab equipmentTab;
    private WeaponTab weaponTab;
    private BuildTab buildTab;
    private Header header;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();

    public MainUI() {

        super();
        createNewUnit(false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".blk");
        setJMenuBar(new MenuBarCreator(entity, this));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);

        reloadTabs();
        setVisible(true);
        repaint();
        refreshAll();
        MechSummaryCache.getInstance();
    }

    @Override
    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        Tank tank = (Tank) entity;

        structureTab = new StructureTab(tank);

        armorTab = new ArmorTab(tank);
        armorTab.setArmorType(entity.getArmorType(0));
        armorTab.refresh();

        header = new Header(tank);
        statusbar = new StatusBar(tank);
        equipmentTab = new EquipmentTab(tank);
        weaponTab = new WeaponTab(tank);
        buildTab = new BuildTab(tank, equipmentTab, weaponTab);
        header.addRefreshedListener(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        weaponTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);

        ConfigPane.addTab("Structure", structureTab);
        ConfigPane.addTab("Armor", armorTab);
        ConfigPane.addTab("Equipment", equipmentTab);
        ConfigPane.addTab("Weapons", weaponTab);
        ConfigPane.addTab("Build", buildTab);

        masterPanel.add(header);
        masterPanel.add(ConfigPane);
        masterPanel.add(statusbar);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void createNewUnit(boolean hasTurret) {
        createNewUnit(hasTurret, false);
    }

    @Override
    public void createNewUnit(boolean hasTurret, boolean hasDualTurret) {

        entity = new Tank();
        Tank tank = (Tank) entity;

        tank.setHasNoTurret(!hasTurret);
        tank.setHasNoDualTurret(!hasDualTurret);
        entity.setYear(2750);
        entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setWeight(25);
        entity.setMovementMode(EntityMovementMode.HOVER);
        tank.setEngine(new Engine(Math.max(10, 25 - tank.getSuspensionFactor()), Engine.NORMAL_ENGINE, Engine.TANK_ENGINE));
        entity.setOriginalWalkMP(1);
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            entity.setArmor(0, loc);
            entity.setArmor(0, loc, true);
        }

        entity.setChassis("New");
        entity.setModel("Tank");

    }

    @Override
    public void refreshAll() {

        Tank tank = (Tank) entity;
        if ((structureTab.hasTurret() && tank.hasNoTurret()) || (!structureTab.hasTurret() && !tank.hasNoTurret()) || (!structureTab.hasDualTurret() && !tank.hasNoDualTurret()) || (!structureTab.hasDualTurret() && !tank.hasNoDualTurret())) {

            /*
             * String chassis = entity.getChassis(); String model =
             * entity.getModel();
             *
             * createNewTank(structureTab.hasTurret(),
             * structureTab.hasDualTurret());
             *
             * entity.setChassis(chassis); entity.setModel(model); //
             * setVisible(false); reloadTabs(); // setVisible(true); repaint();
             * refreshAll();
             */
        }
        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        weaponTab.refresh();
        buildTab.refresh();
        repaint();

    }

    @Override
    public void refreshArmor() {
        armorTab.refresh();
    }

    @Override
    public void refreshBuild() {
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        equipmentTab.refresh();

    }

    @Override
    public void refreshHeader() {
        setTitle(entity.getChassis() + " " + entity.getModel() + ".blk");
    }

    @Override
    public void refreshStatus() {
        statusbar.refresh();
    }

    @Override
    public void refreshStructure() {
        structureTab.refresh();
    }

    @Override
    public void refreshWeapons() {
        weaponTab.refresh();
    }

}