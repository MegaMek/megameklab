/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.combatVehicle;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.TechConstants;
import megamek.common.annotations.Nullable;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.SuperHeavyTank;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megamek.common.verifier.TestTank;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.battlefieldSupport.BFSAssetSource;
import megameklab.ui.battlefieldSupport.BFSLinkedAssetSupport;
import megameklab.ui.battlefieldSupport.BFSLinkedEditor;
import megameklab.ui.battlefieldSupport.BFSStructureTab;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.AnalysisTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.AvailabilityTab;
import megameklab.util.CConfig;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;

public class CVMainUI extends MegaMekLabMainUI implements BFSLinkedEditor {

    private CVStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private AnalysisTab analysisTab;
    private CVBuildTab buildTab;
    private FluffTab fluffTab;
    private CVStatusBar statusbar;
    private BFSStructureTab bfsTab;
    private java.awt.Component bfsTabScroll;
    private final BFSLinkedAssetSupport assetSupport = new BFSLinkedAssetSupport(this::getEntity);

    @Override
    protected FluffTab getFluffTab() {
        return fluffTab;
    }
    private QuirksTab quirksTab;
    private AvailabilityTab availabilityTab;
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
        availabilityTab = new AvailabilityTab(this);
        structureTab.addRefreshedListener(this);
        bfsTab = new BFSStructureTab(this, assetSupport);
        bfsTab.addRefreshedListener(this);
        bfsTabScroll = new TabScrollPane(bfsTab);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);
        availabilityTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        previewTab = new PreviewTab(this);
        analysisTab = new AnalysisTab(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        if (CConfig.showAvailabilityTab()) {
            configPane.addTab("Availability", new TabScrollPane(availabilityTab, availabilityTab.refreshOnShow));
        }
        configPane.addTab("Preview", previewTab);
        // The Asset tab is only shown while the asset is enabled; the checkbox in the Structure tab toggles it.
        BFSLinkedEditor.setAssetTabVisible(configPane, bfsTabScroll, "Asset", previewTab,
              assetSupport.isBattlefieldSupportAssetEnabled());
        configPane.addTab("Analysis", analysisTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
              new CVFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshAll();
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
        availabilityTab.refresh();
        fluffTab.refresh();
        previewTab.refresh();
        if (bfsTab != null) {
            bfsTab.refresh();
        }
        analysisTab.refresh();
        floatingEquipmentDatabase.refresh();
        refreshHeader();
        repaint();
    }

    @Override
    protected BFSAssetSource getBattlefieldSupportAssetSource() {
        return assetSupport;
    }

    @Override
    protected void applyRestoredAsset(@Nullable BattlefieldSupportAsset asset) {
        assetSupport.adoptAsset(asset);
        BFSLinkedEditor.setAssetTabVisible(configPane, bfsTabScroll, "Asset", previewTab,
              assetSupport.isBattlefieldSupportAssetEnabled());
        if (bfsTab != null) {
            bfsTab.refresh();
        }
    }

    @Override
    public void setBattlefieldSupportAssetLinked(boolean enabled) {
        assetSupport.setBattlefieldSupportAssetEnabled(enabled);
        BFSLinkedEditor.setAssetTabVisible(configPane, bfsTabScroll, "Asset", previewTab, enabled);
        if (enabled && (bfsTab != null)) {
            bfsTab.refresh();
        }
        requestDirtyCheck();
        refreshHeader();
    }

    @Override
    public boolean isBattlefieldSupportAssetLinked() {
        return assetSupport.isBattlefieldSupportAssetEnabled();
    }

    @Override
    public boolean isBattlefieldSupportAssetMotiveEligible() {
        return BFSLinkedAssetSupport.isMotiveEligible(getEntity());
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
            newUnit.setTechLevel(TechConstants.T_INTRO_BOX_SET);
            newUnit.setWeight(20);
            newUnit.setMovementMode(EntityMovementMode.VTOL);
        } else if (entityType == Entity.ETYPE_SUPER_HEAVY_TANK) {
            newUnit = new SuperHeavyTank();
            newUnit.setTechLevel(TechConstants.T_IS_ADVANCED);
            newUnit.setWeight(51);
            newUnit.setMovementMode(EntityMovementMode.HOVER);
        } else {
            newUnit = new Tank();
            newUnit.setTechLevel(TechConstants.T_INTRO_BOX_SET);
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
        newUnit.setArmorTechLevel(TechConstants.T_INTRO_BOX_SET);
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
            copyUnitBasics(newUnit, oldEntity);
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
        if (bfsTab != null) {
            bfsTab.refresh();
        }
        analysisTab.refresh();
    }

    @Override
    public void refreshSummary() {
        structureTab.refreshSummary();
        if (bfsTab != null) {
            bfsTab.refresh();
        }
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
        if (buildTab == null) {
            return List.of();
        }
        return buildTab.getUnallocatedView().getEquipment();
    }
}
