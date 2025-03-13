/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.ui.supportVehicle;

import megamek.common.*;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.dialog.FloatingEquipmentDatabaseDialog;
import megameklab.ui.generalUnit.*;
import megameklab.ui.util.TabScrollPane;
import megameklab.ui.util.DraggableTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main window for support vehicle construction
 */
public class SVMainUI extends MegaMekLabMainUI {
    private final DraggableTabbedPane configPane = new DraggableTabbedPane(SwingConstants.TOP);
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

    public SVMainUI() {
        super();
        createNewUnit(Entity.ETYPE_SUPPORT_TANK, false, false);
        finishSetup();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

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
        floatingEquipmentDatabase = new FloatingEquipmentDatabaseDialog(this, new SVFloatingEquipmentDatabaseView(this));
        floatingEquipmentDatabase.setRefresh(this);

        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        transportTab.refresh();
        statusbar.refresh();
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
        buildTab.refresh();
    }

    @Override
    public void refreshEquipment() {
        equipmentTab.refresh();
    }

    @Override
    public void refreshTransport() {
        transportTab = new TransportTab(this);
        transportTab.addRefreshedListener(this);
        int idx = configPane.indexOfTab("Transport");
        configPane.removeTabAt(idx);
        configPane.insertTab("Transport", null, new TabScrollPane(transportTab), null, idx);
        transportTab.refresh();
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
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        if (entityType == Entity.ETYPE_SUPPORT_VTOL) {
            setEntity(new SupportVTOL());
            getEntity().setMovementMode(EntityMovementMode.VTOL);
            ((SupportVTOL) getEntity()).setHasNoDualTurret(true);
            ((SupportVTOL) getEntity()).setHasNoTurret(true);
        } else if (entityType == Entity.ETYPE_FIXED_WING_SUPPORT) {
            setEntity(new FixedWingSupport());
        } else if (entityType == Entity.ETYPE_LARGE_SUPPORT_TANK) {
            setEntity(new LargeSupportTank());
            getEntity().setWeight(51);
            getEntity().setMovementMode(EntityMovementMode.WHEELED);
            ((SupportTank) getEntity()).setHasNoDualTurret(true);
            ((SupportTank) getEntity()).setHasNoTurret(true);
        } else {
            setEntity(new SupportTank());
            getEntity().setMovementMode(EntityMovementMode.WHEELED);
            ((SupportTank) getEntity()).setHasNoDualTurret(true);
            ((SupportTank) getEntity()).setHasNoTurret(true);
        }
        if (entityType != Entity.ETYPE_LARGE_SUPPORT_TANK) {
            getEntity().setWeight(20);
        }

        getEntity().setEngine(new Engine(0, Engine.COMBUSTION_ENGINE,
                Engine.SUPPORT_VEE_ENGINE));

        getEntity().autoSetInternal();
        getEntity().setArmorType(EquipmentType.T_ARMOR_SV_BAR_2);
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            getEntity().initializeArmor(0, loc);
        }
        getEntity().setBARRating(2);

        if (null == oldEntity) {
            getEntity().setChassis("New");
            getEntity().setModel("Support Tank");
            getEntity().setYear(3145);
            getEntity().setStructuralTechRating(ITechnology.RATING_D);
            getEntity().setArmorTechRating(ITechnology.RATING_D);
            getEntity().setOriginalWalkMP(1);
        } else {
            getEntity().setChassis(oldEntity.getChassis());
            getEntity().setModel(oldEntity.getModel());
            getEntity().setYear(Math.max(oldEntity.getYear(),
                    getEntity().getConstructionTechAdvancement().getIntroductionDate()));
            getEntity().setSource(oldEntity.getSource());
            getEntity().setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(getEntity().getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            getEntity().setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            getEntity().setMixedTech(oldEntity.isMixedTech());
            getEntity().setMovementMode(oldEntity.getMovementMode());
            getEntity().setStructuralTechRating(oldEntity.getStructuralTechRating());
            getEntity().setArmorTechRating(oldEntity.getArmorTechRating());
            getEntity().setEngineTechRating(oldEntity.getEngineTechRating());
            getEntity().setOriginalWalkMP(oldEntity.getOriginalWalkMP());
        }
        getEntity().recalculateTechAdvancement();
    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
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
        return buildTab.getUnallocatedView().getEquipment();
    }
}
