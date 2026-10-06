/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
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
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.GunEmplacement;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.battlefieldSupport.BFSAssetSource;
import megameklab.ui.battlefieldSupport.BFSLinkedAssetSupport;
import megameklab.ui.battlefieldSupport.BFSLinkedEditor;
import megameklab.ui.battlefieldSupport.BFSStructureTab;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.AnalysisTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.ui.util.TabScrollPane;

public class GEMainUI extends MegaMekLabMainUI implements BFSLinkedEditor {
    private GEStructureTab structureTab;
    private GEEquipmentTab equipmentTab;
    private FluffTab fluffTab;
    private PreviewTab previewTab;
    private BFSStructureTab bfsTab;
    private java.awt.Component bfsTabScroll;
    private final BFSLinkedAssetSupport assetSupport = new BFSLinkedAssetSupport(this::getEntity);
    private AnalysisTab analysisTab;

    @Override
    protected FluffTab getFluffTab() {
        return fluffTab;
    }
    private StatusBar statusbar;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public GEMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public GEMainUI() {
        super();
        createNewUnit(Entity.ETYPE_GUN_EMPLACEMENT);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new GEStructureTab(this, this);
        equipmentTab = new GEEquipmentTab(this);
        equipmentTab.addRefreshedListener(this);
        fluffTab = new FluffTab(this);
        fluffTab.setRefreshedListener(this);
        previewTab = new PreviewTab(this);
        analysisTab = new AnalysisTab(this);
        structureTab.addRefreshedListener(this);
        bfsTab = new BFSStructureTab(this, assetSupport);
        bfsTab.addRefreshedListener(this);
        bfsTabScroll = new TabScrollPane(bfsTab);

        configPane.addTab("Structure", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", new TabScrollPane(equipmentTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", previewTab);
        configPane.addTab("Analysis", analysisTab);
        // The Asset tab is only shown while the asset is enabled; the checkbox in the Structure tab toggles it.
        BFSLinkedEditor.setAssetTabVisible(configPane, bfsTabScroll, "Asset", previewTab,
              assetSupport.isBattlefieldSupportAssetEnabled());

        statusbar = new GEStatusBar(this);
        statusbar.addRefreshedListener(this);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
              new GEFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshAll();
        validate();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        structureTab.refresh();
        fluffTab.refresh();
        previewTab.refresh();
        analysisTab.refresh();
        statusbar.refresh();
        equipmentTab.refresh();
        if (bfsTab != null) {
            bfsTab.refresh();
        }
        refreshHeader();
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
        structureTab.refresh();
    }

    @Override
    public void refreshBuild() {
        super.refreshBuild();
        structureTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        super.refreshEquipment();
        structureTab.refresh();
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
    }

    @Override
    public void refreshWeapons() {
        super.refreshWeapons();
        structureTab.refresh();
        equipmentTab.refresh();
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
        structureTab.refresh();
        if (bfsTab != null) {
            bfsTab.refresh();
        }
    }

    @Override
    public void refreshEquipmentTable() {
        super.refreshEquipmentTable();
        equipmentTab.refresh();
    }

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return List.of();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        var newUnit = new GunEmplacement();
        newUnit.setWeight(0);
        newUnit.setTechLevel(TechConstants.T_IS_TW_ALL);
        newUnit.autoSetInternal();
        newUnit.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        newUnit.initializeArmor(0, GunEmplacement.LOC_GUNS);

        if (null == oldUnit) {
            newUnit.setChassis("New");
            newUnit.setModel("Gun Emplacement");
            newUnit.setYear(3145);
        } else {
            copyUnitBasics(newUnit, oldUnit);
        }
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }
}
