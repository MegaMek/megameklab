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
package megameklab.ui.largeAero;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import megamek.common.*;
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
    private FluffTab fluffTab;
    private QuirksTab quirksTab;
    private StatusBar statusbar;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public WSMainUI(boolean primitive) {
        super();
        if (!primitive) {
            createNewUnit(Entity.ETYPE_JUMPSHIP, false, false);
        } else {
            createNewUnit(Entity.ETYPE_WARSHIP, true, false);
        }
        finishSetup();
        MekSummaryCache.getInstance();
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
    public void refreshTransport() {
        transportTab.refresh();
    }

    @Override
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        if (entitytype == Entity.ETYPE_JUMPSHIP) {
            setEntity(new Jumpship());
        } else if (entitytype == Entity.ETYPE_WARSHIP) {
            setEntity(new Warship());
            if ((null != oldUnit)
                    && (((Jumpship) oldUnit).getDriveCoreType() == Jumpship.DRIVE_CORE_SUBCOMPACT)) {
                ((Jumpship) getEntity()).setDriveCoreType(Jumpship.DRIVE_CORE_SUBCOMPACT);
            }
        } else if (entitytype == Entity.ETYPE_SPACE_STATION) {
            setEntity(new SpaceStation());
        } else {
            logger.error("Received incorrect entityType!");
            return;
        }
        getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);

        Jumpship ship = (Jumpship) getEntity();

        if (isPrimitive) {
            ship.setYear(2470);
            ship.setOriginalBuildYear(2470);
            ship.setDriveCoreType(Jumpship.DRIVE_CORE_PRIMITIVE);
            ship.setJumpRange(30);
        } else {
            ship.setYear(3145);
        }
        if (isPrimitive) {
            ship.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
        } else {
            ship.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
        }
        ship.setWeight(TestAdvancedAerospace.getMinTonnage(ship));
        if (entitytype == Entity.ETYPE_WARSHIP) {
            ship.setOriginalWalkMP(2); // Start at 1G
            ship.set0SI(3);
        } else {
            ship.setOriginalWalkMP(0);
            ship.set0SI(1);
        }
        ship.setArmorTechLevel(getEntity().getTechLevel());

        ship.setHeatType(Aero.HEAT_SINGLE);

        ship.autoSetInternal();
        ship.initializeKFIntegrity();
        ship.initializeSailIntegrity();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            if (loc >= Jumpship.LOC_HULL) {
                ship.initializeArmor(IArmorState.ARMOR_NA, loc);
            } else {
                ship.initializeArmor((int) Math.round(ship.get0SI() / 10.0), loc);
            }
        }

        if (null == oldUnit) {
            getEntity().setChassis("New");
            if ((entitytype == Entity.ETYPE_WARSHIP) && !isPrimitive) {
                ship.setModel("Warship");
            } else if (entitytype == Entity.ETYPE_SPACE_STATION) {
                ship.setModel("Station");
            } else {
                ship.setModel("Jumpship");
            }
        } else {
            ship.setChassis(oldUnit.getChassis());
            ship.setModel(oldUnit.getModel());
            ship.setYear(Math.max(oldUnit.getYear(),
                    ship.getConstructionTechAdvancement().getIntroductionDate()));
            ship.setSource(oldUnit.getSource());
            ship.setManualBV(oldUnit.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(ship.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldUnit.getTechLevel()));
            ship.setTechLevel(lvl.getCompoundTechLevel(oldUnit.isClan()));
            ship.setMixedTech(oldUnit.isMixedTech());
        }
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

        structureTab = new WSStructureTab(this);
        previewTab = new PreviewTab(this);
        statusbar = new StatusBar(this);
        equipmentTab = new LAEquipmentTab(this);
        buildTab = new LABuildTab(this);
        fluffTab = new FluffTab(this);
        transportTab = new TransportTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Transport Bays", new TabScrollPane(transportTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        configPane.addTab("Preview", previewTab);
        configPane.setParentUI(this);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(this,
                new LAFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        previewTab.refresh();
        floatingEquipmentDatabase.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {
    }

    @Override
    public void refreshBuild() {
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        equipmentTab.refresh();
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
    public void refreshWeapons() {
    }

    @Override
    public void refreshPreview() {
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
