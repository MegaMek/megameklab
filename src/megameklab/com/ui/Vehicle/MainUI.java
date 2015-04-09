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
import megameklab.com.ui.Vehicle.tabs.PreviewTab;
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
    private PreviewTab previewTab;
    private BuildTab buildTab;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {

        super();
        createNewUnit(Entity.ETYPE_TANK, false);
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

        Tank tank = (Tank) getEntity();
        statusbar = new StatusBar(tank, this);
        equipmentTab = new EquipmentTab(tank);
        buildTab = new BuildTab(tank, equipmentTab);
        structureTab = new StructureTab(tank);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        
        previewTab = new PreviewTab(tank);

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
        Tank tank = (Tank)getEntity();
        if (structureTab.isVTOL() && (getEntity() instanceof VTOL)) {
            if ((structureTab.isSuperHeavy() && !tank.isSuperHeavy())) {
                tank.setWeight(31);
            } else if (!structureTab.isSuperHeavy() && tank.isSuperHeavy()){
                tank.setWeight(30);
            }
        } else if ((structureTab.isVTOL() && !(getEntity() instanceof VTOL))
                || (!structureTab.isVTOL() && (getEntity() instanceof VTOL))) {
            String model = getEntity().getModel();
            String chassis = getEntity().getChassis();
            String source = getEntity().getSource();
            int year = getEntity().getYear();
            int techLevel = getEntity().getTechLevel();
            int mBV = getEntity().getManualBV();
            createNewUnit(
                    structureTab.isVTOL() ? Entity.ETYPE_VTOL
                            : tank.isSuperHeavy() ? Entity.ETYPE_SUPER_HEAVY_TANK
                                    : Entity.ETYPE_TANK, tank.isSuperHeavy());
            getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD);
            getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
            getEntity().setChassis(chassis);
            getEntity().setModel(model);
            getEntity().setSource(source);
            getEntity().setYear(year);
            getEntity().setTechLevel(techLevel);
            getEntity().setManualBV(mBV);
            reloadTabs();
            repaint();
            refreshAll();
        } else if ((structureTab.isSuperHeavy() && !(tank.isSuperHeavy()))
                || (!structureTab.isSuperHeavy() && (tank.isSuperHeavy()))) {
            String model = getEntity().getModel();
            String chassis = getEntity().getChassis();
            String source = getEntity().getSource();
            int year = getEntity().getYear();
            int techLevel = getEntity().getTechLevel();
            int mBV = getEntity().getManualBV();
            createNewUnit(
                    structureTab.isSuperHeavy() ? Entity.ETYPE_SUPER_HEAVY_TANK
                            : Entity.ETYPE_TANK, structureTab.isSuperHeavy());
            getEntity().setChassis(chassis);
            getEntity().setModel(model);
            getEntity().setSource(source);
            getEntity().setYear(year);
            getEntity().setTechLevel(techLevel);
            getEntity().setManualBV(mBV);
            reloadTabs();
            repaint();
            refreshAll();
        }

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
    public void createNewUnit(long entityType, boolean isSuperHeavy) {
        if (entityType == Entity.ETYPE_VTOL) {
            setEntity(new VTOL());
            getEntity().setTechLevel(TechConstants.T_INTRO_BOXSET);
            if (isSuperHeavy) {
                getEntity().setWeight(31);
                getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);
            }
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

        getEntity().setYear(3145);

        tank.setEngine(new Engine(Math.max(10, (int) getEntity().getWeight()
                - tank.getSuspensionFactor()), Engine.NORMAL_ENGINE,
                Engine.TANK_ENGINE));
        getEntity().setOriginalWalkMP(1);

        getEntity().autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            getEntity().initializeArmor(0, loc);
        }

        getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD);
        getEntity().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
        getEntity().setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        tank.setHasNoDualTurret(true);
        if (getEntity() instanceof VTOL) {
            tank.setHasNoTurret(true);
        }
        getEntity().setChassis("New");
        getEntity().setModel("Tank");

    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
    }

}