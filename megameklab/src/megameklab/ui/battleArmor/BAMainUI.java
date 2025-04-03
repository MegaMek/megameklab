/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.ui.battleArmor;

import megamek.common.*;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BAMainUI extends MegaMekLabMainUI {

    private BAStructureTab structureTab;
    private BABuildTab buildTab;
    private BAEquipmentTab equipTab;
    private FluffTab fluffTab;
    private BAStatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public BAMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public BAMainUI() {
        super();
        createNewUnit(Entity.ETYPE_BATTLEARMOR);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new BAStructureTab(this);
        equipTab = new BAEquipmentTab(this);
        fluffTab = new FluffTab(this);
        quirksTab = new QuirksTab(this);
        statusbar = new BAStatusBar(this);
        buildTab = new BABuildTab(this);
        structureTab.addRefreshedListener(this);
        equipTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(), new BAFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        BattleArmor newUnit = new BattleArmor();
        newUnit.setYear(3145);
        newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        newUnit.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        newUnit.setWeightClass(EntityWeightClass.WEIGHT_LIGHT);
        newUnit.setTroopers(4);
        newUnit.setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);
        newUnit.autoSetInternal();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
        }
        newUnit.setChassis("New");
        newUnit.setModel("BattleArmor");
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        refreshStatus();
        refreshStructure();
        refreshEquipmentTable();
        quirksTab.refresh();
        refreshBuild();
        refreshPreview();
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
        equipTab.refresh();
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
    public void refreshPreview() {
        super.refreshPreview();
        structureTab.refreshPreview();
    }

    @Override
    public void refreshSummary() {
        super.refreshSummary();
    }

    @Override
    public void refreshEquipmentTable() {
        super.refreshEquipmentTable();
        equipTab.refreshTable();
        floatingEquipmentDatabase.refresh();
    }

    @Override
    public ITechManager getTechManager() {
        return (structureTab != null) ? structureTab.getTechManager() : null;
    }

    public JDialog getFloatingEquipmentDatabase() {
        return floatingEquipmentDatabase;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return buildTab.getBuildView().getEquipment();
    }
}
