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

package megameklab.ui.combatVehicle;

import megamek.common.*;
import megamek.common.verifier.TestTank;
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

public class CVMainUI extends MegaMekLabMainUI {

    private CVStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private CVBuildTab buildTab;
    private FluffTab fluffTab;
    private CVStatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public CVMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public CVMainUI() {
        super();
        createNewUnit(Entity.ETYPE_TANK, false, false);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        statusbar = new CVStatusBar(this);
        structureTab = new CVStructureTab(this);
        equipmentTab = new CVEquipmentTab(this);
        buildTab = new CVBuildTab(this);
        fluffTab = new FluffTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        previewTab = new PreviewTab(this);

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
                new CVFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        statusbar.refresh();
        quirksTab.refresh();
        fluffTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
        refreshHeader();
        repaint();
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
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        Tank newUnit;
        if (entityType == Entity.ETYPE_VTOL) {
            newUnit = new VTOL();
            newUnit.setTechLevel(TechConstants.T_INTRO_BOXSET);
            newUnit.setWeight(20);
            newUnit.setMovementMode(EntityMovementMode.VTOL);
        } else if (entityType == Entity.ETYPE_SUPER_HEAVY_TANK) {
            newUnit = new SuperHeavyTank();
            newUnit.setTechLevel(TechConstants.T_IS_ADVANCED);
            newUnit.setWeight(51);
            newUnit.setMovementMode(EntityMovementMode.HOVER);
        } else {
            newUnit = new Tank();
            newUnit.setTechLevel(TechConstants.T_INTRO_BOXSET);
            newUnit.setWeight(20);
            newUnit.setMovementMode(EntityMovementMode.HOVER);
        }
        newUnit.setYear(3145);
        newUnit.setEngine(new Engine(Math.max(10, (int) newUnit.getWeight()
                - newUnit.getSuspensionFactor()), Engine.NORMAL_ENGINE,
                Engine.TANK_ENGINE));

        newUnit.autoSetInternal();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
        }

        newUnit.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        newUnit.setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
        newUnit.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        newUnit.setHasNoDualTurret(true);
        if (Entity.ETYPE_VTOL == entityType) {
            newUnit.setHasNoTurret(true);
        }
        if (null == oldEntity) {
            newUnit.setChassis("New");
            newUnit.setModel("Tank");
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
            newUnit.setMovementMode(oldEntity.getMovementMode());
            newUnit.setWeight(
                    Math.min(newUnit.getWeight(),
                            TestTank.maxTonnage(newUnit.getMovementMode(), newUnit.isSuperHeavy())));
            if (newUnit.isSuperHeavy()) {
                newUnit.setWeight(
                        Math.max(newUnit.getWeight(), TestTank.maxTonnage(newUnit.getMovementMode(), false) + 1.0));
            }
        }
        newUnit.setOriginalWalkMP(
                (newUnit.getEngine().getRating() + newUnit.getSuspensionFactor()) / (int) newUnit.getWeight());
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshPreview() {
        super.refreshPreview();
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
    public List<Mounted<?>> getUnallocatedMounted() {
        return buildTab.getUnallocatedView().getEquipment();
    }
}
