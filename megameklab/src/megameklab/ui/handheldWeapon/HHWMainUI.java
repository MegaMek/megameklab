/*
 * MegaMekLab - Copyright (C) 2025 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.ui.handheldWeapon;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.HandheldWeapon;
import megamek.common.ITechManager;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.util.TabScrollPane;

public class HHWMainUI extends MegaMekLabMainUI {
    private HHWStructureTab structureTab;
    private HHWEquipmentTab equipmentTab;
    private FluffTab fluffTab;
    private PreviewTab previewTab;
    private HHWStatusBar statusbar;

    public HHWMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public HHWMainUI() {
        super();
        createNewUnit(Entity.ETYPE_HANDHELD_WEAPON);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new HHWStructureTab(this, this);
        equipmentTab = new HHWEquipmentTab(this);
        equipmentTab.addRefreshedListener(this);
        fluffTab = new FluffTab(this);
        fluffTab.setRefreshedListener(this);
        previewTab = new PreviewTab(this);
        structureTab.addRefreshedListener(this);

        configPane.addTab("Structure", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", new TabScrollPane(equipmentTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", new TabScrollPane(previewTab));

        statusbar = new HHWStatusBar(this);
        statusbar.addRefreshedListener(this);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        statusbar.refresh();
        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        structureTab.refresh();
        previewTab.refresh();
        statusbar.refresh();
        equipmentTab.refresh();
        refreshHeader();
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
    }

    @Override
    public void refreshSummary() {
        structureTab.refresh();
    }

    @Override
    public void refreshEquipmentTable() {
        super.refreshEquipmentTable();
        equipmentTab.refresh();
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
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        HandheldWeapon newUnit = new HandheldWeapon();
        newUnit.setWeight(6);
        newUnit.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        newUnit.autoSetInternal();
        newUnit.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        newUnit.setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        newUnit.initializeArmor(0, HandheldWeapon.LOC_GUN);

        if (null == oldUnit) {
            newUnit.setChassis("New");
            newUnit.setModel("Handheld Weapon");
            newUnit.setYear(3145);
        } else {
            newUnit.setChassis(oldUnit.getChassis());
            newUnit.setModel(oldUnit.getModel());
            newUnit.setYear(Math.max(oldUnit.getYear(), newUnit.getConstructionTechAdvancement().getIntroductionDate()));
            newUnit.setSource(oldUnit.getSource());
            newUnit.setManualBV(oldUnit.getManualBV());
            newUnit.setTechLevel(oldUnit.getTechLevel());
            newUnit.setMixedTech(oldUnit.isMixedTech());
        }
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }
}
