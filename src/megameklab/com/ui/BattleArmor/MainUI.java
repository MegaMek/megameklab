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

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.TechConstants;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.BattleArmor.tabs.BuildTab;
import megameklab.com.ui.BattleArmor.tabs.EquipmentTab;
import megameklab.com.ui.BattleArmor.tabs.StructureTab;
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
    private BuildTab buildTab;
    private EquipmentTab equipTab;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {

        super();
        // ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewUnit(Entity.ETYPE_BATTLEARMOR);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        menubarcreator = new MenuBarCreator(this);
        setJMenuBar(menubarcreator);
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

        masterPanel.setLayout(new BorderLayout());
        structureTab = new StructureTab(this);
        equipTab = new EquipmentTab(this);

        statusbar = new StatusBar(this);
        buildTab = new BuildTab(this);
        structureTab.addRefreshedListener(this);
        equipTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);

        ConfigPane.addTab("Structure/Armor", structureTab);
        ConfigPane.addTab("Equipment", equipTab);
        ConfigPane.addTab("Assign Criticals", buildTab);

        masterPanel.add(ConfigPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        setEntity(new BattleArmor());
        BattleArmor ba = (BattleArmor) getEntity();

        ba.setYear(3145);
        ba.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        ba.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        ba.setWeightClass(EntityWeightClass.WEIGHT_LIGHT);
        ba.setTroopers(4);
        ba.setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);

        ba.autoSetInternal();
        for (int loc = 0; loc < ba.locations(); loc++) {
            ba.initializeArmor(0, loc);
        }

        ba.setChassis("New");
        ba.setModel("BattleArmor");
    }

    @Override
    public void refreshAll() {

        statusbar.refresh();
        structureTab.refresh();
        refreshEquipment();
        refreshBuild();
        refreshPreview();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {

    }

    @Override
    public void refreshBuild() {
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        equipTab.refresh();
    }
    
    @Override
    public void refreshTransport() {
        // not used for ba
    }

    @Override
    public void refreshHeader() {

        String title = getEntity().getChassis() + " " + getEntity().getModel() + ".blk";

        if (UnitUtil.validateUnit(getEntity()).length() > 0) {
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
    }

    @Override
    public void refreshPreview() {
        structureTab.refreshPreview();
    }
    
    @Override
    public void refreshSummary() {
    }
    
    @Override
    public void refreshEquipmentTable() {
        equipTab.refreshTable();
    }

    @Override
    public ITechManager getTechManager() {
        if (structureTab != null) {
            return structureTab.getTechManager();
        }
        return null;
    }

}