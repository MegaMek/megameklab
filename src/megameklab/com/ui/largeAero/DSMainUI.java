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
package megameklab.com.ui.largeAero;

import megamek.common.*;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.combatVeh.CVFloatingEquipmentDatabaseView;
import megameklab.com.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.com.ui.generalUnit.*;
import megameklab.com.ui.util.TabScrollPane;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main UI for DropShips and Small Craft
 * 
 * @author Neoancient
 */
public class DSMainUI extends MegaMekLabMainUI {

    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    private DSStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private LABuildTab buildTab;
    private TransportTab transportTab;
    private DSStatusBar statusbar;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;
    
    public DSMainUI(boolean primitive) {
        super();
        createNewUnit(Entity.ETYPE_DROPSHIP, primitive, false);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        finishSetup();
        MechSummaryCache.getInstance();
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
        if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
            setEntity(new SmallCraft());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entitytype == Entity.ETYPE_DROPSHIP) {
            setEntity(new Dropship());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            LogManager.getLogger().error("Received incorrect entityType!");
            return;
        }

        SmallCraft smallCraft = (SmallCraft) getEntity();

        if (isPrimitive) {
            smallCraft.setYear(2470);
            smallCraft.setOriginalBuildYear(2470);
            smallCraft.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_AERO);
        } else {
            smallCraft.setYear(3145);
            smallCraft.setArmorType(EquipmentType.T_ARMOR_AEROSPACE);
        }
        smallCraft.setWeight(200);
        smallCraft.setOriginalWalkMP(2); // Start at 1G
        smallCraft.setArmorTechLevel(getEntity().getTechLevel());
        smallCraft.set0SI(3);
        smallCraft.setDesignType(SmallCraft.MILITARY);
        
        smallCraft.setHeatType(Aero.HEAT_SINGLE);

        smallCraft.autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            if (loc == SmallCraft.LOC_HULL) {
                smallCraft.initializeArmor(IArmorState.ARMOR_NA, loc);
            } else {
                smallCraft.initializeArmor(smallCraft.get0SI(), loc);
            }
        }

        if (null == oldUnit) {
            getEntity().setChassis("New");
            if (entitytype == Entity.ETYPE_SMALL_CRAFT) {
                smallCraft.setModel("Small Craft");
            } else {
                smallCraft.setModel("Dropship");
            }
            smallCraft.setSpheroid(false);
            smallCraft.setMovementMode(EntityMovementMode.AERODYNE);
        } else {
            smallCraft.setChassis(oldUnit.getChassis());
            smallCraft.setModel(oldUnit.getModel());
            smallCraft.setYear(Math.max(oldUnit.getYear(),
                    smallCraft.getConstructionTechAdvancement().getIntroductionDate()));
            smallCraft.setSource(oldUnit.getSource());
            smallCraft.setManualBV(oldUnit.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(smallCraft.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldUnit.getTechLevel()));
            smallCraft.setTechLevel(lvl.getCompoundTechLevel(oldUnit.isClan()));
            smallCraft.setMixedTech(oldUnit.isMixedTech());

            smallCraft.setSpheroid(((SmallCraft) oldUnit).isSpheroid());
            smallCraft.setMovementMode(oldUnit.getMovementMode());
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

        structureTab = new DSStructureTab(this);

        previewTab = new PreviewTab(this);

        statusbar = new DSStatusBar(this);
        equipmentTab = new LAEquipmentTab(this);
        buildTab = new LABuildTab(this);
        transportTab = new TransportTab(this);
        FluffTab fluffTab = new FluffTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Transport Bays", new TabScrollPane(transportTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(this, new LAFloatingEquipmentDatabaseView(this));
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
    public void refreshHeader() {
        String title = getEntity().getChassis() + " " + getEntity().getModel() + ".blk";
        setTitle(title);
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
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
    }
}
