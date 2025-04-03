/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.ui.largeAero;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import megamek.common.*;
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
 * Main UI for DropShips and Small Craft
 *
 * @author Neoancient
 */
public class DSMainUI extends MegaMekLabMainUI {
    private static final MMLogger logger = MMLogger.create(DSMainUI.class);

    private DSStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private LABuildTab buildTab;
    private TransportTab transportTab;
    private StatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public DSMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
        MekSummaryCache.getInstance();
    }

    public DSMainUI(boolean primitive) {
        super();
        createNewUnit(Entity.ETYPE_DROPSHIP, primitive, false);
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
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        SmallCraft newUnit;
        if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
            newUnit = new SmallCraft();
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entitytype == Entity.ETYPE_DROPSHIP) {
            newUnit = new Dropship();
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            logger.error("Received incorrect entityType!");
            return;
        }
        if (isPrimitive) {
            newUnit.setYear(2470);
            newUnit.setOriginalBuildYear(2470);
            newUnit.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
        } else {
            newUnit.setYear(3145);
            newUnit.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
        }
        newUnit.setWeight(200);
        newUnit.setOriginalWalkMP(2); // Start at 1G
        newUnit.setArmorTechLevel(newUnit.getTechLevel());
        newUnit.setOSI(3);
        newUnit.setDesignType(SmallCraft.MILITARY);

        newUnit.setHeatType(Aero.HEAT_SINGLE);

        newUnit.autoSetInternal();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            if (loc == SmallCraft.LOC_HULL) {
                newUnit.initializeArmor(IArmorState.ARMOR_NA, loc);
            } else {
                newUnit.initializeArmor(newUnit.getOSI(), loc);
            }
        }
        if (null == oldUnit) {
            newUnit.setChassis("New");
            if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
                newUnit.setModel("Small Craft");
            } else {
                newUnit.setModel("Dropship");
            }
            newUnit.setSpheroid(false);
            newUnit.setMovementMode(EntityMovementMode.AERODYNE);
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
            newUnit.setSpheroid(oldUnit.isSpheroid());
            newUnit.setMovementMode(oldUnit.getMovementMode());
        }
        setEntity(newUnit, "");
        invalidateSnapshot();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new DSStructureTab(this);

        previewTab = new PreviewTab(this);

        statusbar = new StatusBar(this);
        equipmentTab = new LAEquipmentTab(this);
        buildTab = new LABuildTab(this);
        transportTab = new TransportTab(this);
        quirksTab = new QuirksTab(this);
        FluffTab fluffTab = new FluffTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);

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

        statusbar.refresh();
        refreshHeader();
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
        return buildTab.getBuildView().getEquipment();
    }
}
