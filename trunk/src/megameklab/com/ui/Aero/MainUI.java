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

    public MainUI() {

        super();
        createNewUnit(Entity.ETYPE_AERO, false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
        menubarcreator = new MenuBarCreator(entity, this);
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

        Aero aero = (Aero) entity;

        structureTab = new StructureTab(aero);

        previewTab = new PreviewTab(aero);

        statusbar = new StatusBar(aero, this);
        equipmentTab = new EquipmentTab(aero);
        buildTab = new BuildTab(aero, equipmentTab);
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
    public void createNewUnit(long entityType, boolean isSuperHeavy) {

        if (entityType == Entity.ETYPE_AERO) {
            entity = new Aero();
            entity.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_CONV_FIGHTER) {
            entity = new ConvFighter();
            entity.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else {
            System.out.println("Aero.MainUI: Received incorrect entityType!");
            return;
        }
        
        Aero aero = (Aero) entity;

        entity.setYear(3145);
        entity.setWeight(25);
        aero.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setArmorTechLevel(entity.getTechLevel());
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        aero.setHeatSinks(10);
        aero.setHeatType(Aero.HEAT_SINGLE);

        entity.autoSetInternal();
        for (int loc = 0; loc < entity.locations(); loc++) {
            aero.initializeArmor(0, loc);
        }

        entity.setChassis("New");
        entity.setModel("Aero");
        if (menubarcreator != null) {
            menubarcreator.setUnit(entity);
        }

    }

    @Override
    public void refreshAll() {
       
//        String model = entity.getModel();
//        String chassis = entity.getChassis();
//
//        createNewUnit(Entity.ETYPE_AERO, false);
//
//        entity.setChassis(chassis);
//        entity.setModel(model);
//
//        reloadTabs();
//        repaint();
//        refreshAll();
   
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

        String title = entity.getChassis() + " " + entity.getModel() + ".mtf";
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

}