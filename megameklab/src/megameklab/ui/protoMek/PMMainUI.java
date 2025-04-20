/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.protoMek;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Mounted;
import megamek.common.ProtoMek;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;

/**
 * Main UI for building ProtoMeks
 *
 * @author Neoancient
 */
public class PMMainUI extends MegaMekLabMainUI {

    private PMStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private PMBuildTab buildTab;
    private PMStatusBar statusbar;
    private QuirksTab quirksTab;
    private FluffTab fluffTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public PMMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public PMMainUI() {
        super();
        createNewUnit(Entity.ETYPE_PROTOMEK);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new PMStructureTab(this);
        previewTab = new PreviewTab(this);
        statusbar = new PMStatusBar(this);
        equipmentTab = new PMEquipmentTab(this);
        buildTab = new PMBuildTab(this, this);
        quirksTab = new QuirksTab(this);
        fluffTab = new FluffTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);

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
              new PMFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        ProtoMek newUnit = new ProtoMek();
        newUnit.setWeight(2);
        newUnit.setMovementMode(EntityMovementMode.BIPED);
        newUnit.setTechLevel(TechConstants.T_CLAN_TW);
        newUnit.setOriginalWalkMP(1);
        newUnit.setEngine(new Engine(TestProtoMek.calcEngineRating(newUnit), Engine.NORMAL_ENGINE, Engine.CLAN_ENGINE));
        newUnit.setArmorType(EquipmentType.T_ARMOR_STANDARD_PROTOMEK);
        newUnit.setArmorTechLevel(newUnit.getTechLevel());
        newUnit.autoSetInternal();
        newUnit.setHasMainGun(false);
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
        }
        if (null == oldEntity) {
            newUnit.setChassis("New");
            newUnit.setModel("Protomek");
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
        fluffTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
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

    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        if (buildTab == null) {
            return List.of();
        }
        return buildTab.getBuildView().getEquipment();
    }
}
