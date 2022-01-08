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

package megameklab.com.ui.combatVeh;

import megamek.common.*;
import megamek.common.verifier.TestTank;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.com.ui.generalUnit.AbstractEquipmentTab;
import megameklab.com.ui.generalUnit.FluffTab;
import megameklab.com.ui.generalUnit.PreviewTab;
import megameklab.com.ui.util.TabScrollPane;

import javax.swing.*;
import java.awt.*;

public class CVMainUI extends MegaMekLabMainUI {

    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    private CVStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private CVBuildTab buildTab;
    private FluffTab fluffTab;
    private CVStatusBar statusbar;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public CVMainUI() {
        super();
        createNewUnit(Entity.ETYPE_TANK, false, false);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        finishSetup();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

        statusbar = new CVStatusBar(this);
        structureTab = new CVStructureTab(this);
        equipmentTab = new CVEquipmentTab(this);
        buildTab = new CVBuildTab(this);
        fluffTab = new FluffTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        statusbar.setRefreshListener(this);
        
        previewTab = new PreviewTab(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(this, new CVFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        statusbar.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
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
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
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
            tank.setWeight(Math.min(tank.getWeight(), TestTank.maxTonnage(tank.getMovementMode(), tank.isSuperHeavy())));
            if (tank.isSuperHeavy()) {
                tank.setWeight(Math.max(tank.getWeight(), TestTank.maxTonnage(tank.getMovementMode(), false) + 1.0));
            }
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
        floatingEquipmentDatabase.refresh();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
    }

}