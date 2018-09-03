/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.aerospace;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Aero;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.MechSummaryCache;
import megamek.common.SimpleTechLevel;
import megamek.common.SmallCraft;
import megamek.common.TechConstants;
import megamek.common.logging.LogLevel;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Aero.tabs.EquipmentTab;
import megameklab.com.ui.Aero.tabs.PreviewTab;
import megameklab.com.util.MenuBarCreator;

/**
 * Main UI for Dropships and Small Craft
 * 
 * @author Neoancient
 *
 */
public class DropshipMainUI extends MegaMekLabMainUI {

    /**
     * 
     */
    private static final long serialVersionUID = -4014312789921114515L;
    
    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private DropshipStructureTab structureTab;
    private EquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private DropshipBuildTab buildTab;
    private TransportTab transportTab;
    private DropshipStatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;
    
    public DropshipMainUI(boolean primitive) {
        super();
        createNewUnit(Entity.ETYPE_DROPSHIP, primitive, false);
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
        MechSummaryCache.getInstance();
    }

    @Override
    public void refreshSummary() {
        structureTab.refreshSummary();
    }

    @Override
    public void refreshEquipmentTable() {
        equipmentTab.refreshTable();
    }
    
    @Override
    public void refreshTransport() {
        transportTab.refresh();
    }

    @Override
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
            setEntity(new SmallCraft());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entitytype == Entity.ETYPE_DROPSHIP) {
            setEntity(new Dropship());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            MegaMekLab.getLogger().log(DropshipMainUI.class, "createNewUnit(long)", LogLevel.ERROR,
                    "Received incorrect entityType!");
            return;
        }

        SmallCraft smallCraft = (SmallCraft) getEntity();

        if (isPrimitive) {
            smallCraft.setYear(2470);
            smallCraft.setOriginalBuildYear(2470);
            smallCraft.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
        } else {
            smallCraft.setYear(3145);
            smallCraft.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
        }
        smallCraft.setWeight(200);
        smallCraft.setOriginalWalkMP(2); // Start at 1G
        smallCraft.setArmorTechLevel(getEntity().getTechLevel());
        smallCraft.set0SI(3);
        smallCraft.setDesignType(SmallCraft.MILITARY);
        
        smallCraft.setHeatType(Aero.HEAT_SINGLE);

        smallCraft.autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            smallCraft.initializeArmor(smallCraft.get0SI(), loc);
        }

        if (null == oldUnit) {
            getEntity().setChassis("New");
            if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
                smallCraft.setModel("Small Craft");
            } else {
                smallCraft.setModel("Dropship");
            }
            smallCraft.setSpheroid(false);
            smallCraft.setMovementMode(EntityMovementMode.AERODYNE);
        } else {
            smallCraft.setChassis(oldUnit.getChassis());
            smallCraft.setModel(oldUnit.getModel());
            smallCraft.setYear(Math.max(oldUnit.getYear(),
                    smallCraft.getConstructionTechAdvancement().getIntroductionDate()));
            smallCraft.setSource(oldUnit.getSource());
            smallCraft.setManualBV(oldUnit.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(smallCraft.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldUnit.getTechLevel()));
            smallCraft.setTechLevel(lvl.getCompoundTechLevel(oldUnit.isClan()));
            smallCraft.setMixedTech(oldUnit.isMixedTech());

            smallCraft.setSpheroid(((SmallCraft) oldUnit).isSpheroid());
            smallCraft.setMovementMode(oldUnit.getMovementMode());
        }
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    @Override
    public void reloadTabs() {
        masterPanel.removeAll();
        configPane.removeAll();

        masterPanel.setLayout(new BorderLayout());

        structureTab = new DropshipStructureTab(this);

        previewTab = new PreviewTab(this);

        statusbar = new DropshipStatusBar(this);
        equipmentTab = new EquipmentTab(this);
        buildTab = new DropshipBuildTab(this, equipmentTab);
        transportTab = new TransportTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", structureTab);
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", buildTab);
        configPane.addTab("Transport Bays", transportTab);
        configPane.addTab("Preview", previewTab);

        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        previewTab.refresh();
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
        equipmentTab.refresh();
    }

    @Override
    public void refreshHeader() {
        String title = getEntity().getChassis() + " " + getEntity().getModel() + ".blk";
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
        previewTab.refresh();
    }

}
