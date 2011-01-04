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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import megamek.common.BattleArmor;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megamek.common.TechConstants;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.BattleArmor.tabs.ArmorTab;
import megameklab.com.ui.BattleArmor.tabs.BuildTab;
import megameklab.com.ui.BattleArmor.tabs.EquipmentTab;
import megameklab.com.ui.BattleArmor.tabs.StructureTab;
import megameklab.com.ui.BattleArmor.tabs.WeaponTab;
import megameklab.com.util.CConfig;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class MainUI extends JFrame implements RefreshListener {

    /**
     *
     */
    private static final long serialVersionUID = -5836932822468918198L;

    BattleArmor entity = null;
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

        EquipmentType.initializeTypes();
        UnitUtil.loadFonts();
        new CConfig();
        System.out.println("Staring MegaMekLab version: " + MegaMekLab.VERSION);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Setting look and feel failed: ");
            e.printStackTrace();
        }

        setLocation(getLocation().x + 10, getLocation().y);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
                // Only save position and size if not maximized or minimized.
                if (getExtendedState() == Frame.NORMAL) {
                    CConfig.setParam("WINDOWHEIGHT", Integer.toString(getHeight()));
                    CConfig.setParam("WINDOWWIDTH", Integer.toString(getWidth()));
                    CConfig.setParam("WINDOWLEFT", Integer.toString(getX()));
                    CConfig.setParam("WINDOWTOP", Integer.toString(getY()));
                }
                CConfig.saveConfig();

                System.exit(0);
            }
        });

        // ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewBattleArmor(false);
        MechSummaryCache.getInstance();
        setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
        setJMenuBar(new MenuBarCreator(entity, this));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);
        Dimension maxSize = new Dimension(CConfig.getIntParam("WINDOWWIDTH"), CConfig.getIntParam("WINDOWHEIGHT"));
        // masterPanel.setPreferredSize(new Dimension(600,400));
        // scroll.setPreferredSize(maxSize);
        setResizable(true);
        setSize(maxSize);
        setMaximumSize(maxSize);
        setPreferredSize(maxSize);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
        setLocation(CConfig.getIntParam("WINDOWLEFT"), CConfig.getIntParam("WINDOWTOP"));

        reloadTabs();
        setVisible(true);
        repaint();
        refreshAll();

    }

    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        structureTab = new StructureTab(entity);

        armorTab = new ArmorTab(entity);
        armorTab.setArmorType(entity.getArmorType());
        armorTab.refresh();

        header = new Header(entity);
        statusbar = new StatusBar(entity);
        equipmentTab = new EquipmentTab(entity);
        weaponTab = new WeaponTab(entity);
        buildTab = new BuildTab(entity, equipmentTab, weaponTab);
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

    public void createNewBattleArmor(boolean isQuad) {

        entity = new BattleArmor();

        entity.setYear(2750);
        entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        entity.setWeightClass(0);
        entity.setWeight(EntityWeightClass.getClassLimit(0));
        entity.setTroopers(4);
        entity.setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            entity.setArmor(0, loc);
        }

        entity.setChassis("New");
        entity.setModel("BattleArmor");
    }

    public void refreshAll() {

        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        weaponTab.refresh();
        buildTab.refresh();
        refreshHeader();
    }

    public void refreshArmor() {
        armorTab.refresh();
    }

    public void refreshBuild() {
        buildTab.refresh();
    }

    public void refreshEquipment() {
        equipmentTab.refresh();

    }

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

    public void refreshStatus() {
        statusbar.refresh();
    }

    public void refreshStructure() {
        structureTab.refresh();
    }

    public void refreshWeapons() {
        weaponTab.refresh();
    }
}