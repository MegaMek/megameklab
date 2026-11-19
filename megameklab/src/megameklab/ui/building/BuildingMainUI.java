/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.building;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import megamek.common.board.CubeCoords;
import megamek.common.equipment.Mounted;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.AbstractBuildingEntity;
import megamek.common.units.Entity;
import megamek.common.units.MobileStructure;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.generalUnit.FluffTab;
import megameklab.ui.generalUnit.RecordSheetPreviewPanel;
import megameklab.ui.util.TabScrollPane;
import megameklab.util.BuildingUtil;

/** Construction editor for advanced buildings. */
public class BuildingMainUI extends MegaMekLabMainUI {
    private BuildingStructureTab structure;
    private BuildingEquipmentTab equipment;
    private BuildingTransportTab transport;
    private BuildingSystemsTab systems;
    private FluffTab fluff;
    private RecordSheetPreviewPanel preview;
    private JLabel status;
    private boolean refreshing;
    private JSplitPane editorSplit;
    private JLabel editingLocation;
    private CubeCoords selectedHex;
    private int selectedFloor;
    private boolean absoluteCoordinates;

    public BuildingMainUI() {
        createNewUnit(Entity.ETYPE_BUILDING_ENTITY);
    }

    public BuildingMainUI(boolean mobile) {
        createNewUnit(mobile ? Entity.ETYPE_MOBILE_STRUCTURE : Entity.ETYPE_BUILDING_ENTITY);
    }

    public BuildingMainUI(Entity entity, String filename) {
        setEntity(entity, filename);
    }

    @Override
    public AbstractBuildingEntity getEntity() {
        return (AbstractBuildingEntity) super.getEntity();
    }

    void changeStructureType(boolean mobile) {
        if ((getEntity() instanceof MobileStructure) == mobile) {
            return;
        }
        createNewUnit(mobile ? Entity.ETYPE_MOBILE_STRUCTURE : Entity.ETYPE_BUILDING_ENTITY,
              false, false, getEntity());
        reloadTabs();
    }

    @Override
    protected FluffTab getFluffTab() {
        return fluff;
    }

    @Override
    public void reloadTabs() {
        int navigatorWidth = editorSplit == null ? 360 : editorSplit.getRightComponent().getWidth();
        configPane.removeAll();
        removeAll();
        structure = new BuildingStructureTab(this);
        equipment = new BuildingEquipmentTab(this);
        transport = new BuildingTransportTab(this);
        systems = new BuildingSystemsTab(this);
        fluff = new FluffTab(this);
        fluff.setRefreshedListener(this);
        preview = new RecordSheetPreviewPanel();
        preview.setFullAsyncMode(true);
        status = new JLabel();
        status.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        configPane.addTab("Structure", structure);
        configPane.addTab("Equipment", equipment);
        configPane.addTab("Transport & Quarters", new TabScrollPane(transport));
        configPane.addTab("Capacity, crew, power & validation", systems.getTotalsPanel());
        configPane.addTab("Construction & Services", systems);
        configPane.addTab("Fluff", new TabScrollPane(fluff));
        configPane.addTab("Record Sheet", preview);
        JPanel navigator = createLocationNavigator();
        navigator.setPreferredSize(new Dimension(Math.max(240, navigatorWidth), 450));
        navigator.setMinimumSize(new Dimension(240, 160));
        configPane.setMinimumSize(new Dimension(systems.getMinimumSize().width, 160));
        editorSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, configPane, navigator);
        editorSplit.setName("Building editor split");
        editorSplit.setBorder(BorderFactory.createEmptyBorder());
        editorSplit.setContinuousLayout(true);
        editorSplit.setResizeWeight(1);
        add(editorSplit, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
        preview.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent event) {
                preview.setEntity(getEntity());
            }
        });
        refreshAll();
        revalidate();
    }

    @Override
    public void refreshAll() {
        super.refreshAll();
        if (structure == null || refreshing) {
            return;
        }
        refreshing = true;
        getEntity().getDesign().removeDeletedComponents(getEntity());
        int crewSize = megamek.common.compute.Compute.getFullCrewSize(getEntity());
        getEntity().getCrew().setSize(crewSize);
        getEntity().getCrew().setCurrentSize(crewSize);
        refreshLocationNavigator();
        structure.refresh();
        equipment.refresh();
        transport.refresh();
        systems.refresh();
        fluff.refresh();
        if (preview.isShowing()) {
            preview.setEntity(getEntity());
        }
        List<String> issues = BuildingUtil.constructionIssues(getEntity());
        status.setText("Installed: %.2f / %.2f tons    |    Power: %s    |    %s".formatted(
              BuildingUtil.equipmentWeight(getEntity()), getEntity().getWeight(),
              BuildingUtil.powerDescription(getEntity()), issues.isEmpty() ? "" : issues.getFirst()));
        status.setToolTipText("<html>" + String.join("<br>", issues) + "</html>");
        refreshing = false;
        refreshHeader();
    }

    private JPanel createLocationNavigator() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setName("Building location navigator");
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Pancake view"),
              BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        editingLocation = new JLabel();
        editingLocation.setName("Editing location");
        panel.add(editingLocation, BorderLayout.NORTH);
        panel.add(structure.createPancakePane(), BorderLayout.CENTER);
        return panel;
    }

    private void refreshLocationNavigator() {
        if (editingLocation == null) {
            return;
        }
        var hexes = getEntity().getInternalBuilding().getOriginalCoordsList();
        if (!hexes.contains(selectedHex)) {
            selectedHex = hexes.getFirst();
        }
        int height = getEntity().getInternalBuilding().getHeight(selectedHex);
        selectedFloor = Math.max(0, Math.min(selectedFloor, height - 1));
        boolean bridge = getEntity().getBldgClass() == megamek.common.units.IBuilding.BRIDGE;
        int level = bridge ? getEntity().getDesign().bridgeDeck(selectedHex) : selectedFloor;
        editingLocation.setText("Editing: " + hexLabel(selectedHex) + " / " + (bridge ? "Deck " : "Level ")
              + getEntity().getLevelLabel(level));
    }

    CubeCoords selectedHex() {
        return selectedHex == null ? CubeCoords.ZERO : selectedHex;
    }

    boolean absoluteCoordinates() {
        return absoluteCoordinates;
    }

    void setAbsoluteCoordinates(boolean absolute) {
        absoluteCoordinates = absolute;
        refreshLocationNavigator();
        structure.refresh();
    }

    String hexLabel(CubeCoords hex) {
        return absoluteCoordinates ? BuildingUtil.absoluteHexLabel(hex)
              : BuildingUtil.sheetGrid(getEntity().getInternalBuilding().getOriginalCoordsList()).label(hex);
    }

    int selectedFloor() {
        return selectedFloor;
    }

    int selectedLocation() {
        return getEntity().getInternalBuilding().getOriginalCoordsList().indexOf(selectedHex())
              * getEntity().getInternalBuilding().getBuildingHeight() + selectedFloor;
    }

    void selectLocation(CubeCoords hex, int floor) {
        selectedHex = hex;
        selectedFloor = floor;
        refreshLocationNavigator();
        // Navigation is not a construction change: do not schedule an undo snapshot or dirty the unit.
        structure.refresh();
        equipment.refreshPlacement();
        systems.refreshPlacement();
    }

    void showEquipment() {
        configPane.setSelectedComponent(equipment);
    }

    @Override
    public void refreshBuild() {
        scheduleRefresh();
    }

    @Override
    public void refreshEquipment() {
        scheduleRefresh();
    }

    @Override
    public void refreshEquipmentTable() {
        scheduleRefresh();
    }

    @Override
    public void refreshStatus() {
        if (!refreshing) {
            scheduleRefresh();
        }
    }

    @Override
    public void refreshStructure() {
        if (!refreshing) {
            scheduleRefresh();
        }
    }

    @Override
    public void refreshPreview() {
        if (!refreshing) {
            scheduleRefresh();
        }
    }

    @Override
    public void refreshSummary() {
        scheduleRefresh();
    }

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return null;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return getEntity().getEquipment().stream().filter(m -> m.getLocation() == Entity.LOC_NONE).toList();
    }

    @Override
    public void createNewUnit(long entityType, boolean primitive, boolean industrial, Entity oldUnit) {
        AbstractBuildingEntity building = entityType == Entity.ETYPE_MOBILE_STRUCTURE
              ? BuildingUtil.newMobileStructure() : BuildingUtil.newBuilding();
        if (oldUnit != null) {
            copyUnitBasics(building, oldUnit);
        }
        setEntity(building, "");
        forceDirtyUntilNextSave();
    }

    @Override
    public ITechManager getTechManager() {
        return structure.getTechManager();
    }
}
