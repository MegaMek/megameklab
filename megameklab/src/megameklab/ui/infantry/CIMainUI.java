/*
 * MegaMekLab - Copyright (C) 2008
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

package megameklab.ui.infantry;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

import megamek.common.*;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.PopupMessages;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.util.TabScrollPane;

public class CIMainUI extends MegaMekLabMainUI {

    CIStructureTab structureTab;
    PreviewTab previewTab;
    FluffTab fluffTab;
    CIStatusBar statusbar;

    public CIMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public CIMainUI() {
        super();
        createNewUnit(Entity.ETYPE_INFANTRY);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        statusbar = new CIStatusBar(this);
        structureTab = new CIStructureTab(this);
        fluffTab = new FluffTab(this);
        previewTab = new PreviewTab(this);

        structureTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);

        configPane.addTab("Build", new TabScrollPane(structureTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        Infantry newUnit = new Infantry();
        newUnit.setYear(3145);
        newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        newUnit.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
        newUnit.setSquadCount(4);
        newUnit.setSquadSize(7);
        newUnit.setPrimaryWeapon((InfantryWeapon) EquipmentType.get("InfantryAssaultRifle"));
        try {
            newUnit.addEquipment(EquipmentType.get(EquipmentTypeLookup.INFANTRY_ASSAULT_RIFLE), Infantry.LOC_INFANTRY);
        } catch (LocationFullException ex) {
            PopupMessages.showLocationFullError(this, EquipmentType.get("InfantryAssaultRifle").getName());
        }
        newUnit.autoSetInternal();
        newUnit.setChassis("New");
        newUnit.setModel("Infantry");
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        statusbar.refresh();
        structureTab.refresh();
        fluffTab.refresh();
        previewTab.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {
        super.refreshArmor();
    }

    @Override
    public void refreshBuild() {
        super.refreshBuild();
    }

    @Override
    public void refreshEquipment() {
        super.refreshEquipment();
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
        previewTab.refresh();
    }

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return null;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return List.of();
    }

    @Override
    public void refreshSummary() {
        structureTab.refreshSummary();
    }

    @Override
    public void refreshEquipmentTable() {
        super.refreshEquipmentTable();
        structureTab.refreshEquipmentTable();
    }

    @Override
    public ITechManager getTechManager() {
        if (null != structureTab) {
            return structureTab.getTechManager();
        }
        return null;
    }
}
