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

package megameklab.com.ui.Aero;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Aero;
import megamek.common.ConvFighter;
import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.SimpleTechLevel;
import megamek.common.TechConstants;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Aero.tabs.BuildTab;
import megameklab.com.ui.Aero.tabs.EquipmentTab;
import megameklab.com.ui.Aero.tabs.PreviewTab;
import megameklab.com.ui.Aero.tabs.StructureTab;
import megameklab.com.util.MenuBarCreator;

public class MainUI extends MegaMekLabMainUI {


    /**
     *
     */
    private static final long serialVersionUID = -2117599432007026106L;

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

    public MainUI(boolean primitive) {

        super();
        createNewUnit(Entity.ETYPE_AERO, primitive);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        menubarcreator = new MenuBarCreator(this);
        setJMenuBar(menubarcreator);
        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        configPane.addTab("Equipment", equipmentTab);
        configPane.addTab("Assign Criticals", buildTab);
        configPane.addTab("Preview", previewTab);

        masterPanel.add(configPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {

        if (entityType == Entity.ETYPE_AERO) {
            setEntity(new Aero());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_CONV_FIGHTER) {
            setEntity(new ConvFighter());
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            System.out.println("Aero.MainUI: Received incorrect entityType!");
            return;
        }

        Aero aero = (Aero) getEntity();

        aero.setYear(3145);
        aero.setWeight(25);
        aero.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        if (isPrimitive) {
            aero.setCockpitType(Aero.COCKPIT_PRIMITIVE);
            aero.setArmorType(EquipmentType.T_ARMOR_PRIMITIVE_FIGHTER);
        } else {
            aero.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        }
        aero.setArmorTechLevel(getEntity().getTechLevel());
        aero.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        aero.setHeatSinks(10);
        aero.setHeatType(Aero.HEAT_SINGLE);

        aero.autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            aero.initializeArmor(0, loc);
        }

        if (null == oldEntity) {
            getEntity().setChassis("New");
            getEntity().setModel("Aero");
        } else {
            aero.setChassis(oldEntity.getChassis());
            aero.setModel(oldEntity.getModel());
            aero.setYear(Math.max(oldEntity.getYear(),
                    aero.getConstructionTechAdvancement().getIntroductionDate()));
            aero.setSource(oldEntity.getSource());
            aero.setManualBV(oldEntity.getManualBV());
            SimpleTechLevel lvl = SimpleTechLevel.max(aero.getStaticTechLevel(),
                    SimpleTechLevel.convertCompoundToSimple(oldEntity.getTechLevel()));
            aero.setTechLevel(lvl.getCompoundTechLevel(oldEntity.isClan()));
            aero.setMixedTech(oldEntity.isMixedTech());
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
    public void refreshPreview() {
        previewTab.refresh();

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