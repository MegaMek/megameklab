/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
package megameklab.ui.protoMek;

import megamek.common.*;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main UI for building protomeks
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
    private FluffTab  fluffTab;
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
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(), new PMFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
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
            newUnit.setYear(Math.max(oldEntity.getYear(), newUnit.getConstructionTechAdvancement().getIntroductionDate()));
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
        return buildTab.getBuildView().getEquipment();
    }
}
