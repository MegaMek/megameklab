/*
 * MegaMekLab - Copyright (C) 2008
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
package megameklab.ui.mek;

import megamek.common.*;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;
import megameklab.util.MekUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BMMainUI extends MegaMekLabMainUI {

    private BMStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private BMBuildTab buildTab;
    private FluffTab fluffTab;
    private BMStatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public BMMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public BMMainUI(boolean primitive, boolean industrial) {
        super();
        createNewUnit(Entity.ETYPE_BIPED_MEK, primitive, industrial);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new BMStructureTab(this);
        previewTab = new PreviewTab(this);
        statusbar = new BMStatusBar(this);
        equipmentTab = new BMEquipmentTab(this);
        buildTab = new BMBuildTab(this);
        fluffTab = new FluffTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
                new BMFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        Mek newUnit;
        int cockpit = Mek.COCKPIT_STANDARD;
        int at = EquipmentType.T_ARMOR_STANDARD;
        int st = EquipmentType.T_STRUCTURE_STANDARD;
        if (isPrimitive && isIndustrial) {
            cockpit = Mek.COCKPIT_PRIMITIVE_INDUSTRIAL;
            at = EquipmentType.T_ARMOR_PRIMITIVE;
            st = EquipmentType.T_STRUCTURE_INDUSTRIAL;
        } else if (isPrimitive) {
            cockpit = Mek.COCKPIT_PRIMITIVE;
            at = EquipmentType.T_ARMOR_PRIMITIVE;
        } else if (isIndustrial) {
            cockpit = Mek.COCKPIT_INDUSTRIAL;
            at = EquipmentType.T_ARMOR_INDUSTRIAL;
            st = EquipmentType.T_STRUCTURE_INDUSTRIAL;
        }
        if (entityType == Entity.ETYPE_TRIPOD_MEK) {
            newUnit = new TripodMek(Mek.GYRO_STANDARD, Mek.COCKPIT_TRIPOD);
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_QUAD_MEK) {
            newUnit = new QuadMek(Mek.GYRO_STANDARD, cockpit);
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_LAND_AIR_MEK) {
            newUnit = new LandAirMek(Mek.GYRO_STANDARD, Mek.COCKPIT_STANDARD, LandAirMek.LAM_STANDARD);
            newUnit.setTechLevel(TechConstants.T_IS_ADVANCED);
            newUnit.setManualBV(-1);
        } else if (entityType == Entity.ETYPE_QUADVEE) {
            newUnit = new QuadVee(Mek.GYRO_STANDARD, QuadVee.MOTIVE_TRACK);
            newUnit.setTechLevel(TechConstants.T_CLAN_ADVANCED);
            MekUtil.createSpreadMounts((Mek) newUnit, EquipmentType.get(EquipmentTypeLookup.MEK_TRACKS));
            newUnit.setManualBV(-1);
        } else { // type == 0
            newUnit = new BipedMek(Mek.GYRO_STANDARD, cockpit);
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        }
        newUnit.setWeight(25);
        if (entityType == Entity.ETYPE_LAND_AIR_MEK) {
            newUnit.setEngine(new Engine(75, Engine.NORMAL_ENGINE, 0));
            MekUtil.updateJumpJets(((Mek) newUnit), 3, Mek.JUMP_STANDARD);
        } else {
            newUnit.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        }
        newUnit.setArmorType(at);
        newUnit.setArmorTechLevel(newUnit.getTechLevel());
        newUnit.setStructureType(st);

        newUnit.addGyro();
        newUnit.addEngineCrits();
        if (isPrimitive) {
            newUnit.addPrimitiveCockpit();
        } else if (Entity.ETYPE_QUADVEE == entityType) {
            newUnit.addQuadVeeCockpit();
        } else {
            newUnit.addCockpit();
        }
        MekUtil.updateHeatSinks(newUnit, 10, "Single");

        newUnit.autoSetInternal();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
            newUnit.initializeRearArmor(0, loc);
        }

        if (null == oldEntity) {
            newUnit.setChassis("New");
            newUnit.setModel("Mek");
            newUnit.setYear(3145);
        } else {
            newUnit.setChassis(oldEntity.getChassis());
            newUnit.setModel(oldEntity.getModel());
            newUnit.setYear(Math.max(oldEntity.getYear(),
                    newUnit.getConstructionTechAdvancement().getIntroductionDate()));
            newUnit.setSource(oldEntity.getSource());
            newUnit.setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(newUnit.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            newUnit.setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            newUnit.setMixedTech(oldEntity.isMixedTech());
        }
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        quirksTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
        fluffTab.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {
        super.refreshArmor();
    }

    @Override
    public void refreshBuild() {
        super.refreshBuild();
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        super.refreshEquipment();
        equipmentTab.refresh();
    }

    @Override
    public void refreshTransport() {
        super.refreshTransport();
    }

    @Override
    public void refreshPreview() {
        super.refreshPreview();
        previewTab.refresh();
    }

    @Override
    public void refreshStatus() {
        super.refreshStatus();
        statusbar.refresh();
    }

    @Override
    public void refreshStructure() {
        super.refreshStructure();
        structureTab.refresh();
    }

    @Override
    public void refreshWeapons() {
        super.refreshWeapons();
    }

    @Override
    public void refreshSummary() {
        structureTab.refreshSummary();
    }

    @Override
    public void refreshEquipmentTable() {
        super.refreshEquipmentTable();
        equipmentTab.refreshTable();
        floatingEquipmentDatabase.refresh();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return this.buildTab.getBuildView().getEquipment();
    }
}
