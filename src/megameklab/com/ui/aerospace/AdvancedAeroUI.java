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
package megameklab.com.ui.aerospace;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Aero;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Jumpship;
import megamek.common.MechSummaryCache;
import megamek.common.SimpleTechLevel;
import megamek.common.SpaceStation;
import megamek.common.TechConstants;
import megamek.common.Warship;
import megamek.common.logging.LogLevel;
import megamek.common.verifier.TestAdvancedAerospace;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Aero.tabs.EquipmentTab;
import megameklab.com.ui.Aero.tabs.PreviewTab;
import megameklab.com.util.MenuBarCreator;

/**
 * MainUI for Jumpships, Warship, and Space Stations
 * 
 * @author Neoancient
 *
 */
public class AdvancedAeroUI extends MegaMekLabMainUI {
    /**
     * 
     */
    private static final long serialVersionUID = -91028543221939757L;
    
    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private AdvancedAeroStructureTab structureTab;
    private EquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private DropshipBuildTab buildTab;
    private TransportTab transportTab;
    private AdvancedAeroStatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;
    
    public AdvancedAeroUI(boolean primitive) {
        super();
        if (!primitive) {
            createNewUnit(Entity.ETYPE_JUMPSHIP, false, false);
        } else {
            createNewUnit(Entity.ETYPE_WARSHIP, true, false);
        }
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        menubarcreator = new MenuBarCreator(this);
        setJMenuBar(menubarcreator);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);

        reloadTabs();
        setVisible(true);
        repaint();
        refreshAll();
        MechSummaryCache.getInstance();
    }

    @Override
    public void refreshSummary() {
        structureTab.refreshSummary();
    }

    @Override
    public void refreshEquipmentTable() {
        equipmentTab.refreshTable();
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
            MegaMekLab.getLogger().log(AdvancedAeroUI.class, "createNewUnit(long)", LogLevel.ERROR,
                    "Received incorrect entityType!");
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
            ship.initializeArmor((int) Math.round(ship.get0SI() / 10.0), loc);
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
        masterPanel.removeAll();
        configPane.removeAll();

        masterPanel.setLayout(new BorderLayout());

        structureTab = new AdvancedAeroStructureTab(this);

        previewTab = new PreviewTab(this);

        statusbar = new AdvancedAeroStatusBar(this);
        equipmentTab = new EquipmentTab(this);
        buildTab = new DropshipBuildTab(this, equipmentTab);
        transportTab = new TransportTab(this);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        transportTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", structureTab);
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", buildTab);
        configPane.addTab("Transport Bays", transportTab);
        configPane.addTab("Preview", previewTab);

        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        equipmentTab.refresh();
        buildTab.refresh();
        previewTab.refresh();
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

}
