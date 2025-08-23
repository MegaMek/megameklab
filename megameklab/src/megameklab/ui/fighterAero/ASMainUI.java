/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;

import megamek.common.*;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.ConvFighter;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.generalUnit.QuirksTab;
import megameklab.ui.util.TabScrollPane;

public class ASMainUI extends MegaMekLabMainUI {
    private static final MMLogger logger = MMLogger.create(ASMainUI.class);

    private ASStructureTab structureTab;
    private AbstractEquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private ASBuildTab buildTab;
    private FluffTab fluffTab;
    private ASStatusBar statusbar;
    private QuirksTab quirksTab;
    private FloatingEquipmentDatabaseDialog floatingEquipmentDatabase;

    public ASMainUI(Entity entity, String filename) {
        super();
        setEntity(entity, filename);
    }

    public ASMainUI(boolean primitive) {
        super();
        createNewUnit(Entity.ETYPE_AERO, primitive);
        requestDirtyCheck();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        removeAll();

        structureTab = new ASStructureTab(this);
        previewTab = new PreviewTab(this);
        statusbar = new ASStatusBar(this);
        equipmentTab = new ASEquipmentTab(this);
        buildTab = new ASBuildTab(this);
        fluffTab = new FluffTab(this);
        quirksTab = new QuirksTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);
        quirksTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Quirks", new TabScrollPane(quirksTab, quirksTab.refreshOnShow));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        if (floatingEquipmentDatabase != null) {
            floatingEquipmentDatabase.setVisible(false);
        }
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(getParentFrame(),
              new ASFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshAll();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        Aero newUnit;
        if (entityType == Entity.ETYPE_AERO) {
            newUnit = new AeroSpaceFighter();
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_CONV_FIGHTER) {
            newUnit = new ConvFighter();
            newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            logger.error("Received incorrect entityType!");
            return;
        }
        newUnit.setYear(3145);
        newUnit.setWeight(25);
        newUnit.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        if (isPrimitive) {
            newUnit.setCockpitType(Aero.COCKPIT_PRIMITIVE);
            newUnit.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_FIGHTER);
        } else {
            newUnit.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        }
        newUnit.setArmorTechLevel(newUnit.getTechLevel());
        newUnit.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        newUnit.setHeatSinks(10);
        newUnit.setHeatType(Aero.HEAT_SINGLE);

        newUnit.autoSetInternal();
        for (int loc = 0; loc < newUnit.locations(); loc++) {
            newUnit.initializeArmor(0, loc);
        }
        if (null == oldEntity) {
            newUnit.setChassis("New");
            newUnit.setModel("Aero");
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
        }
        setEntity(newUnit, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        quirksTab.refresh();
        fluffTab.refresh();
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
    public void refreshTransport() {
        super.refreshTransport();
    }

    @Override
    public void refreshPreview() {
        super.refreshPreview();
        previewTab.refresh();

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
    public void refreshSummary() {
        structureTab.refreshSummary();
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
