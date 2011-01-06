/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.com.ui.BattleArmor;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.BattleArmor;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.BattleArmor.tabs.ArmorTab;
import megameklab.com.ui.BattleArmor.tabs.BuildTab;
import megameklab.com.ui.BattleArmor.tabs.EquipmentTab;
import megameklab.com.ui.BattleArmor.tabs.StructureTab;
import megameklab.com.ui.BattleArmor.tabs.WeaponTab;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.UnitUtil;

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
        // ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewUnit(false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
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

    }

    @Override
    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        BattleArmor ba = (BattleArmor)entity;
        structureTab = new StructureTab(ba);

        armorTab = new ArmorTab(ba);
        armorTab.setArmorType(ba.getArmorType());
        armorTab.refresh();

        header = new Header(ba);
        statusbar = new StatusBar(ba);
        equipmentTab = new EquipmentTab(ba);
        weaponTab = new WeaponTab(ba);
        buildTab = new BuildTab(ba, equipmentTab, weaponTab);
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
    public void createNewUnit(boolean isQuad) {
        createNewUnit(isQuad, false);
    }

    @Override
    public void createNewUnit(boolean isQuad, boolean isBiped) {

        entity = new BattleArmor();
        BattleArmor ba = (BattleArmor)entity;

        ba.setYear(2750);
        ba.setTechLevel(TechConstants.T_INTRO_BOXSET);
        ba.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        ba.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        ba.setWeightClass(0);
        entity.setWeight(EntityWeightClass.getClassLimit(0));
        ba.setTroopers(4);
        ba.setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);

        ba.autoSetInternal();
        for (int loc = 0; loc < ba.locations(); loc++) {
            ba.setArmor(0, loc);
        }

        ba.setChassis("New");
        ba.setModel("BattleArmor");
    }

    @Override
    public void refreshAll() {

        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        weaponTab.refresh();
        buildTab.refresh();
        refreshHeader();
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

        String title = entity.getChassis() + " " + entity.getModel() + ".mtf";

        if (UnitUtil.validateUnit(entity).length() > 0) {
            title += "  (Invalid)";
            setForeground(Color.red);
        } else {
            setForeground(Color.BLACK);
        }
        setTitle(title);

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