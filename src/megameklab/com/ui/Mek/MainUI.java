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

package megameklab.com.ui.Mek;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.BipedMech;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.QuadMech;
import megamek.common.QuadVee;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megamek.common.TripodMech;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Mek.tabs.BuildTab;
import megameklab.com.ui.Mek.tabs.EquipmentTab;
import megameklab.com.ui.Mek.tabs.PreviewTab;
import megameklab.com.ui.Mek.tabs.StructureTab;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.UnitUtil;

public class MainUI extends MegaMekLabMainUI {

    /**
     *
     */
    private static final long serialVersionUID = -5836932822468918198L;

    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private EquipmentTab equipmentTab;
    private PreviewTab previewTab;
    private BuildTab buildTab;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {
        this(false, false);
    }

    public MainUI(boolean primitive, boolean industrial) {
        super();
        createNewUnit(Entity.ETYPE_BIPED_MECH, primitive, industrial);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".mtf");
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
    }

    @Override
    public void reloadTabs() {
        masterPanel.removeAll();
        configPane.removeAll();

        masterPanel.setLayout(new BorderLayout());

        structureTab = new StructureTab(this);

        previewTab = new PreviewTab(this);

        statusbar = new StatusBar(this);
        equipmentTab = new EquipmentTab(this);
        buildTab = new BuildTab(this, equipmentTab);
        structureTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        statusbar.addRefreshedListener(this);

        configPane.addTab("Structure/Armor", structureTab);
        //ConfigPane.addTab("Armor", armorTab);
        configPane.addTab("Equipment", equipmentTab);
        //ConfigPane.addTab("Weapons", weaponTab);
        configPane.addTab("Assign Criticals", buildTab);
        configPane.addTab("Preview", previewTab);

        //masterPanel.add(header);
        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        
        int cockpit = Mech.COCKPIT_STANDARD;
        int at = EquipmentType.T_ARMOR_STANDARD;
        int st = EquipmentType.T_STRUCTURE_STANDARD;
        if (isPrimitive && isIndustrial) {
            cockpit = Mech.COCKPIT_PRIMITIVE_INDUSTRIAL;
            at = EquipmentType.T_ARMOR_PRIMITIVE;
            st = EquipmentType.T_STRUCTURE_INDUSTRIAL;
        } else if (isPrimitive) {
            cockpit = Mech.COCKPIT_PRIMITIVE;
            at = EquipmentType.T_ARMOR_PRIMITIVE;
        } else if (isIndustrial) {
            cockpit = Mech.COCKPIT_INDUSTRIAL;
            at = EquipmentType.T_ARMOR_INDUSTRIAL;
            st = EquipmentType.T_STRUCTURE_INDUSTRIAL;
        }

        if (entityType == Entity.ETYPE_TRIPOD_MECH) {
            setEntity(new TripodMech(Mech.GYRO_STANDARD, Mech.COCKPIT_TRIPOD));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_QUAD_MECH) {
            setEntity(new QuadMech(Mech.GYRO_STANDARD, cockpit));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_LAND_AIR_MECH) {
            setEntity(new LandAirMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD, LandAirMech.LAM_STANDARD));
            getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);
            getEntity().setManualBV(-1);
        } else if (entityType == Entity.ETYPE_QUADVEE) {
            setEntity(new QuadVee(Mech.GYRO_STANDARD, QuadVee.MOTIVE_TRACK));
            getEntity().setTechLevel(TechConstants.T_CLAN_ADVANCED);
            UnitUtil.createSpreadMounts((Mech)getEntity(), EquipmentType.get("Tracks"));
            getEntity().setManualBV(-1);
        } else { // type == 0
            setEntity(new BipedMech(Mech.GYRO_STANDARD, cockpit));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        }
        Mech mech = (Mech) getEntity();
        getEntity().setWeight(25);
        if (entityType == Entity.ETYPE_LAND_AIR_MECH) {
            mech.setEngine(new Engine(75, Engine.NORMAL_ENGINE, 0));
            UnitUtil.updateJumpJets(((Mech)getEntity()), 3, Mech.JUMP_STANDARD);
        } else {
            mech.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        }
        getEntity().setArmorType(at);
        getEntity().setArmorTechLevel(getEntity().getTechLevel());
        getEntity().setStructureType(st);

        mech.addGyro();
        mech.addEngineCrits();
        if (isPrimitive) {
            mech.addPrimitiveCockpit();
        } else if (isIndustrial) {
            mech.addIndustrialCockpit();
        } else if (Entity.ETYPE_QUADVEE == entityType) {
            mech.addQuadVeeCockpit();
        } else {
            mech.addCockpit();
        }
        UnitUtil.updateHeatSinks(mech, 10, "Single");

        getEntity().autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            mech.initializeArmor(0, loc);
            mech.initializeRearArmor(0, loc);
        }

        if (null == oldEntity) {
            mech.setChassis("New");
            mech.setModel("Mek");
            mech.setYear(3145);
        } else {
            mech.setChassis(oldEntity.getChassis());
            mech.setModel(oldEntity.getModel());
            mech.setYear(Math.max(oldEntity.getYear(),
                    mech.getConstructionTechAdvancement().getIntroductionDate()));
            mech.setSource(oldEntity.getSource());
            mech.setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(mech.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            mech.setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            mech.setMixedTech(oldEntity.isMixedTech());
        }

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
    public void refreshTransport() {
        // not used for mechs
    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();

    }

    @Override
    public void refreshHeader() {

        String title = getEntity().getChassis() + " " + getEntity().getModel()
                + ".mtf";
        /*  
        if (UnitUtil.validateUnit(entity).length() > 0) {
            title += "  (Invalid)";
            setForeground(Color.red);
        } else {
            setForeground(Color.BLACK);
        }
        */
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
    public void refreshSummary() {
        structureTab.refreshSummary();
    }
    
    @Override
    public void refreshEquipmentTable() {
        equipmentTab.refreshTable();
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }

}