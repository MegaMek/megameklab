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

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.MechSummaryCache;
import megamek.common.SimpleTechLevel;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.VTOL;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Vehicle.tabs.BuildTab;
import megameklab.com.ui.Vehicle.tabs.EquipmentTab;
import megameklab.com.ui.Vehicle.tabs.PreviewTab;
import megameklab.com.ui.Vehicle.tabs.StructureTab;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.UnitUtil;

public class MainUI extends MegaMekLabMainUI {

    /**
     *
     */
    private static final long serialVersionUID = -5836932822468918198L;

    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private EquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private BuildTab buildTab;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {

        super();
        createNewUnit(Entity.ETYPE_TANK, false, false);
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
    public void reloadTabs() {
        masterPanel.removeAll();
        configPane.removeAll();

        masterPanel.setLayout(new BorderLayout());

        statusbar = new StatusBar(this);
        structureTab = new StructureTab(this);
        equipmentTab = new EquipmentTab(this);
        buildTab = new BuildTab(this, equipmentTab);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        
        previewTab = new PreviewTab(this);

        configPane.addTab("Structure/Armor", structureTab);
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", buildTab);
        configPane.addTab("Preview", previewTab);

        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void refreshAll() {
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        statusbar.refresh();
        previewTab.refresh();
        refreshHeader();
        repaint();
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
    public void refreshTransport() {
        // not used for vees
    }

    @Override
    public void refreshHeader() {
        String title = getEntity().getChassis() + " " + getEntity().getModel()
                + ".blk";

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
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        if (entityType == Entity.ETYPE_VTOL) {
            setEntity(new VTOL());
            getEntity().setTechLevel(TechConstants.T_INTRO_BOXSET);
            getEntity().setWeight(20);
            getEntity().setMovementMode(EntityMovementMode.VTOL);
        } else {
            if (entityType == Entity.ETYPE_SUPER_HEAVY_TANK) {
                setEntity(new SuperHeavyTank());
                getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);
                getEntity().setWeight(51);
            } else {
                setEntity(new Tank());
                getEntity().setTechLevel(TechConstants.T_INTRO_BOXSET);
                getEntity().setWeight(20);
            }
            getEntity().setMovementMode(EntityMovementMode.HOVER);
        }

        Tank tank = (Tank) getEntity();

        tank.setYear(3145);

        tank.setEngine(new Engine(Math.max(10, (int) getEntity().getWeight()
                - tank.getSuspensionFactor()), Engine.NORMAL_ENGINE,
                Engine.TANK_ENGINE));

        tank.autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            tank.initializeArmor(0, loc);
        }

        getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD);
        getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
        getEntity().setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        tank.setHasNoDualTurret(true);
        if (Entity.ETYPE_VTOL == entityType) {
            tank.setHasNoTurret(true);
        }
        if (null == oldEntity) {
            tank.setChassis("New");
            tank.setModel("Tank");
            tank.setYear(3145);
        } else {
            tank.setChassis(oldEntity.getChassis());
            tank.setModel(oldEntity.getModel());
            tank.setYear(Math.max(oldEntity.getYear(),
                    tank.getConstructionTechAdvancement().getIntroductionDate()));
            tank.setSource(oldEntity.getSource());
            tank.setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(tank.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            tank.setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            tank.setMixedTech(oldEntity.isMixedTech());
            tank.setMovementMode(oldEntity.getMovementMode());
        }
        tank.setOriginalWalkMP((tank.getEngine().getRating() + tank.getSuspensionFactor()) / (int)tank.getWeight());
    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
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
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

}