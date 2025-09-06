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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.largeAero;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.IArmorState;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.loaders.MekSummaryCache;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Jumpship;
import megamek.common.units.SpaceStation;
import megamek.common.units.Warship;
import megamek.common.verifier.TestAdvancedAerospace;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.ui.generalUnit.TransportTab;
import megameklab.ui.util.TabScrollPane;

/**
 * MainUI for JumpShips, WarShips, and Space Stations
 *
 * @author Neoancient
 */
public class WSMainUI extends MegaMekLabMainUI {
    private static final MMLogger logger = MMLogger.create(WSMainUI.class);

    private WSStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private LABuildTab buildTab;
    private TransportTab transportTab;
    private QuirksTab quirksTab;
    private StatusBar statusbar;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public WSMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
        MekSummaryCache.getInstance();
    }

    public WSMainUI(boolean primitive) {
        super();
        if (!primitive) {
            createNewUnit(Entity.ETYPE_JUMPSHIP, false, false);
        } else {
            createNewUnit(Entity.ETYPE_WARSHIP, true, false);
        }
        MekSummaryCache.getInstance();
        requestDirtyCheck();
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
    public void refreshTransport() {
        super.refreshTransport();
        transportTab.refresh();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        Jumpship newUnit;
        if (entityType == Entity.ETYPE_JUMPSHIP) {
            newUnit = new Jumpship();
        } else if (entityType == Entity.ETYPE_WARSHIP) {
            newUnit = new Warship();
            if ((null != oldUnit)
                  && (((Jumpship) oldUnit).getDriveCoreType() == Jumpship.DRIVE_CORE_SUBCOMPACT)) {
                newUnit.setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
            }
        } else if (entityType == Entity.ETYPE_SPACE_STATION) {
            newUnit = new SpaceStation();
        } else {
            logger.error("Received incorrect entityType!");
            return;
        }
        newUnit.setTechLevel(TechConstants.T_IS_ADVANCED);
        if (isPrimitive) {
            newUnit.setYear(2470);
            newUnit.setOriginalBuildYear(2470);
            newUnit.setDriveCoreType(Jumpship.DRIVE_CORE_PRIMITIVE);
            newUnit.setJumpRange(30);
        } else {
            newUnit.setYear(3145);
        }
        if (isPrimitive) {
            newUnit.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
        } else {
            newUnit.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
        }
        newUnit.setWeight(TestAdvancedAerospace.getMinTonnage(newUnit));
        if (entityType == Entity.ETYPE_WARSHIP) {
            newUnit.setOriginalWalkMP(2); // Start at 1G
            newUnit.setOSI(3);
        } else {
            newUnit.setOriginalWalkMP(0);
            newUnit.setOSI(1);
        }
        newUnit.setArmorTechLevel(newUnit.getTechLevel());
        newUnit.setHeatType(Aero.HEAT_SINGLE);
        newUnit.autoSetInternal();
        newUnit.initializeKFIntegrity();
        newUnit.initializeSailIntegrity();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            if (loc >= Jumpship.LOC_HULL) {
                newUnit.initializeArmor(IArmorState.ARMOR_NA, loc);
            } else {
                newUnit.initializeArmor((int) Math.round(newUnit.getOSI() / 10.0), loc);
            }
        }
        if (null == oldUnit) {
            newUnit.setChassis("New");
            if ((entityType == Entity.ETYPE_WARSHIP) && !isPrimitive) {
                newUnit.setModel("Warship");
            } else if (entityType == Entity.ETYPE_SPACE_STATION) {
                newUnit.setModel("Station");
            } else {
                newUnit.setModel("Jumpship");
            }
        } else {
            newUnit.setChassis(oldUnit.getChassis());
            newUnit.setModel(oldUnit.getModel());
            newUnit.setYear(Math.max(oldUnit.getYear(),
                  newUnit.getConstructionTechAdvancement().getIntroductionDate()));
            newUnit.setSource(oldUnit.getSource());
            newUnit.setManualBV(oldUnit.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(newUnit.getStaticTechLevel(),
                  SimpleTechLevel.convertCompoundToSimple(oldUnit.getTechLevel()));
            newUnit.setTechLevel(lvl.getCompoundTechLevel(oldUnit.isClan()));
            newUnit.setMixedTech(oldUnit.isMixedTech());
        }
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new WSStructureTab(this);
        previewTab = new PreviewTab(this);
        statusbar = new StatusBar(this);
        equipmentTab = new LAEquipmentTab(this);
        buildTab = new LABuildTab(this);
        FluffTab fluffTab = new FluffTab(this);
        transportTab = new TransportTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Transport Bays", new TabScrollPane(transportTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
              new LAFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshAll();
        validate();
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
    public void refreshPreview() {
        super.refreshPreview();
        previewTab.refresh();
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
