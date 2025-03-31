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
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(), new BMFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
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
            setEntity(new TripodMek(Mek.GYRO_STANDARD, Mek.COCKPIT_TRIPOD));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_QUAD_MEK) {
            setEntity(new QuadMek(Mek.GYRO_STANDARD, cockpit));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_LAND_AIR_MEK) {
            setEntity(new LandAirMek(Mek.GYRO_STANDARD, Mek.COCKPIT_STANDARD, LandAirMek.LAM_STANDARD));
            getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);
            getEntity().setManualBV(-1);
        } else if (entityType == Entity.ETYPE_QUADVEE) {
            setEntity(new QuadVee(Mek.GYRO_STANDARD, QuadVee.MOTIVE_TRACK));
            getEntity().setTechLevel(TechConstants.T_CLAN_ADVANCED);
            MekUtil.createSpreadMounts((Mek)getEntity(), EquipmentType.get(EquipmentTypeLookup.MEK_TRACKS));
            getEntity().setManualBV(-1);
        } else { // type == 0
            setEntity(new BipedMek(Mek.GYRO_STANDARD, cockpit));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        }
        Mek mek = (Mek) getEntity();
        getEntity().setWeight(25);
        if (entityType == Entity.ETYPE_LAND_AIR_MEK) {
            mek.setEngine(new Engine(75, Engine.NORMAL_ENGINE, 0));
            MekUtil.updateJumpJets(((Mek)getEntity()), 3, Mek.JUMP_STANDARD);
        } else {
            mek.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        }
        getEntity().setArmorType(at);
        getEntity().setArmorTechLevel(getEntity().getTechLevel());
        getEntity().setStructureType(st);

        mek.addGyro();
        mek.addEngineCrits();
        if (isPrimitive) {
            mek.addPrimitiveCockpit();
        } else if (Entity.ETYPE_QUADVEE == entityType) {
            mek.addQuadVeeCockpit();
        } else {
            mek.addCockpit();
        }
        MekUtil.updateHeatSinks(mek, 10, "Single");

        getEntity().autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            mek.initializeArmor(0, loc);
            mek.initializeRearArmor(0, loc);
        }

        if (null == oldEntity) {
            mek.setChassis("New");
            mek.setModel("Mek");
            mek.setYear(3145);
        } else {
            mek.setChassis(oldEntity.getChassis());
            mek.setModel(oldEntity.getModel());
            mek.setYear(Math.max(oldEntity.getYear(),
                    mek.getConstructionTechAdvancement().getIntroductionDate()));
            mek.setSource(oldEntity.getSource());
            mek.setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(mek.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            mek.setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            mek.setMixedTech(oldEntity.isMixedTech());
        }
        SwingUtilities.invokeLater(this::resetDirty);
    }

    @Override
    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        quirksTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() { }

    @Override
    public void refreshBuild() {
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        equipmentTab.refresh();
    }

    @Override
    public void refreshTransport() { }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
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
    public void refreshWeapons() { }

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

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return this.buildTab.getBuildView().getEquipment();
    }
}
