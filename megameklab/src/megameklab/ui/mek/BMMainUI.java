/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.mek;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.LandAirMek;
import megamek.common.units.Mek;
import megamek.common.units.QuadMek;
import megamek.common.units.QuadVee;
import megamek.common.units.TripodMek;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;
import megameklab.util.MekUtil;

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

        refreshAll();
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
            MekUtil.createSpreadMounts(newUnit, EquipmentType.get(EquipmentTypeLookup.MEK_TRACKS));
            newUnit.setManualBV(-1);
        } else { // type == 0
            newUnit = new BipedMek(Mek.GYRO_STANDARD, cockpit);
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        }
        newUnit.setWeight(25);
        if (entityType == Entity.ETYPE_LAND_AIR_MEK) {
            newUnit.setEngine(new Engine(75, Engine.NORMAL_ENGINE, 0));
            MekUtil.updateJumpJets(newUnit, 3, Mek.JUMP_STANDARD);
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
        if (buildTab == null) {
            return List.of();
        }
        return this.buildTab.getBuildView().getEquipment();
    }
}
