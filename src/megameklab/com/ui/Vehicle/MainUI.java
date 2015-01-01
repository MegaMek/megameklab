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
import megamek.common.MechSummaryCache;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.TechConstants;
import megamek.common.VTOL;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Vehicle.tabs.BuildTab;
import megameklab.com.ui.Vehicle.tabs.EquipmentTab;
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
    private BuildTab buildTab;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {

        super();
        createNewUnit(Entity.ETYPE_TANK, false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".blk");
        menubarcreator = new MenuBarCreator(entity, this);
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

        Tank tank = (Tank) entity;
        statusbar = new StatusBar(tank);
        equipmentTab = new EquipmentTab(tank);
        buildTab = new BuildTab(tank, equipmentTab);
        structureTab = new StructureTab(tank);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);

        configPane.addTab("Structure", structureTab);
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", buildTab);

        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void refreshAll() {
        Tank tank = (Tank)entity;
        if (structureTab.isVTOL() && (entity instanceof VTOL)) {
            if ((structureTab.isSuperHeavy() && !tank.isSuperHeavy())) {
                tank.setWeight(31);
            } else if (!structureTab.isSuperHeavy() && tank.isSuperHeavy()){
                tank.setWeight(30);
            }
        } else if ((structureTab.isVTOL() && !(entity instanceof VTOL))
                || (!structureTab.isVTOL() && (entity instanceof VTOL))) {
            String model = entity.getModel();
            String chassis = entity.getChassis();
            String source = entity.getSource();
            int year = entity.getYear();
            int techLevel = entity.getTechLevel();
            int mBV = entity.getManualBV();
            createNewUnit(
                    structureTab.isVTOL() ? Entity.ETYPE_VTOL
                            : tank.isSuperHeavy() ? Entity.ETYPE_SUPER_HEAVY_TANK
                                    : Entity.ETYPE_TANK, tank.isSuperHeavy());
            entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
            entity.setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
            entity.setChassis(chassis);
            entity.setModel(model);
            entity.setSource(source);
            entity.setYear(year);
            entity.setTechLevel(techLevel);
            entity.setManualBV(mBV);
            reloadTabs();
            repaint();
            refreshAll();
        } else if ((structureTab.isSuperHeavy() && !(tank.isSuperHeavy()))
                || (!structureTab.isSuperHeavy() && (tank.isSuperHeavy()))) {
            String model = entity.getModel();
            String chassis = entity.getChassis();
            String source = entity.getSource();
            int year = entity.getYear();
            int techLevel = entity.getTechLevel();
            int mBV = entity.getManualBV();
            createNewUnit(
                    structureTab.isSuperHeavy() ? Entity.ETYPE_SUPER_HEAVY_TANK
                            : Entity.ETYPE_TANK, structureTab.isSuperHeavy());
            entity.setChassis(chassis);
            entity.setModel(model);
            entity.setSource(source);
            entity.setYear(year);
            entity.setTechLevel(techLevel);
            entity.setManualBV(mBV);
            reloadTabs();
            repaint();
            refreshAll();
        }

        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        statusbar.refresh();
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
    public void refreshHeader() {
        String title = entity.getChassis() + " " + entity.getModel() + ".blk";

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
    }

    @Override
    public void createNewUnit(long entityType, boolean isSuperHeavy) {
        if (entityType == Entity.ETYPE_VTOL) {
            entity = new VTOL();
            entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
            if (isSuperHeavy) {
                entity.setWeight(31);
                entity.setTechLevel(TechConstants.T_IS_ADVANCED);
            }
            entity.setWeight(20);
            entity.setMovementMode(EntityMovementMode.VTOL);
        } else {
            if (entityType == Entity.ETYPE_SUPER_HEAVY_TANK) {
                entity = new SuperHeavyTank();
                entity.setTechLevel(TechConstants.T_IS_ADVANCED);
                entity.setWeight(51);
            } else {
                entity = new Tank();
                entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
                entity.setWeight(20);
            }
            entity.setMovementMode(EntityMovementMode.HOVER);
        }

        Tank tank = (Tank) entity;

        entity.setYear(3145);

        tank.setEngine(new Engine(Math.max(10, (int)entity.getWeight() - tank.getSuspensionFactor()), Engine.NORMAL_ENGINE, Engine.TANK_ENGINE));
        entity.setOriginalWalkMP(1);

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            entity.initializeArmor(0, loc);
        }

        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        tank.setHasNoDualTurret(true);
        if (entity instanceof VTOL) {
            tank.setHasNoTurret(true);
        }
        entity.setChassis("New");
        entity.setModel("Tank");
        if (menubarcreator != null) {
            menubarcreator.setUnit(entity);
        }

    }

    @Override
    public void refreshPreview() {

    }

}