/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.SimpleTechLevel;
import megamek.common.enums.TechRating;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.LargeSupportTank;
import megamek.common.units.SupportTank;
import megamek.common.units.SupportVTOL;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.generalUnit.TransportTab;
import megameklab.ui.util.TabScrollPane;

/**
 * Main window for support vehicle construction
 */
public class SVMainUI extends MegaMekLabMainUI {

    private SVStructureTab structureTab;
    private SVArmorTab armorTab;
    private AbstractEquipmentTab equipmentTab;
    private TransportTab transportTab;
    private PreviewTab previewTab;
    private SVBuildTab buildTab;
    private FluffTab fluffTab;
    private SVStatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public SVMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public SVMainUI() {
        super();
        createNewUnit(Entity.ETYPE_SUPPORT_TANK, false, false);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        statusbar = new SVStatusBar(this);
        structureTab = new SVStructureTab(this);
        armorTab = new SVArmorTab(this, structureTab.getTechManager());
        equipmentTab = new SVEquipmentTab(this);
        buildTab = new SVBuildTab(this);
        transportTab = new TransportTab(this);
        fluffTab = new FluffTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        previewTab = new PreviewTab(this);

        configPane.addTab("Structure", new TabScrollPane(structureTab));
        configPane.addTab("Armor", new TabScrollPane(armorTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Transport", new TabScrollPane(transportTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
              new SVFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshAll();
        validate();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        transportTab.refresh();
        statusbar.refresh();
        quirksTab.refresh();
        fluffTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {
        armorTab.refresh();
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
        transportTab = new TransportTab(this);
        transportTab.addRefreshedListener(this);
        int idx = configPane.indexOfTab("Transport");
        configPane.removeTabAt(idx);
        configPane.insertTab("Transport", null, new TabScrollPane(transportTab), null, idx);
        transportTab.refresh();
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
        Entity newUnit;
        if (entityType == Entity.ETYPE_SUPPORT_VTOL) {
            newUnit = new SupportVTOL();
            newUnit.setMovementMode(EntityMovementMode.VTOL);
            ((SupportVTOL) newUnit).setHasNoDualTurret(true);
            ((SupportVTOL) newUnit).setHasNoTurret(true);
        } else if (entityType == Entity.ETYPE_FIXED_WING_SUPPORT) {
            newUnit = new FixedWingSupport();
        } else if (entityType == Entity.ETYPE_LARGE_SUPPORT_TANK) {
            newUnit = new LargeSupportTank();
            newUnit.setWeight(51);
            newUnit.setMovementMode(EntityMovementMode.WHEELED);
            ((SupportTank) newUnit).setHasNoDualTurret(true);
            ((SupportTank) newUnit).setHasNoTurret(true);
        } else {
            newUnit = new SupportTank();
            newUnit.setMovementMode(EntityMovementMode.WHEELED);
            ((SupportTank) newUnit).setHasNoDualTurret(true);
            ((SupportTank) newUnit).setHasNoTurret(true);
        }
        if (entityType != Entity.ETYPE_LARGE_SUPPORT_TANK) {
            newUnit.setWeight(20);
        }

        newUnit.setEngine(new Engine(0, Engine.COMBUSTION_ENGINE,
              Engine.SUPPORT_VEE_ENGINE));

        newUnit.autoSetInternal();
        newUnit.setArmorType(EquipmentType.T_ARMOR_SV_BAR_2);
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
        }
        newUnit.setBARRating(2);

        if (null == oldEntity) {
            newUnit.setChassis("New");
            newUnit.setModel("Support Tank");
            newUnit.setYear(3145);
            newUnit.setStructuralTechRating(TechRating.D);
            newUnit.setArmorTechRating(TechRating.D);
            newUnit.setOriginalWalkMP(1);
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
            newUnit.setStructuralTechRating(oldEntity.getStructuralTechRating());
            newUnit.setArmorTechRating(oldEntity.getArmorTechRating());
            newUnit.setEngineTechRating(oldEntity.getEngineTechRating());
            newUnit.setOriginalWalkMP(oldEntity.getOriginalWalkMP());
        }
        newUnit.recalculateTechAdvancement();
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
        return buildTab.getUnallocatedView().getEquipment();
    }
}
