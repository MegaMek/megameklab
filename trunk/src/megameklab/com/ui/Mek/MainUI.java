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
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.QuadMech;
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

        super();
        createNewUnit(Entity.ETYPE_BIPED_MECH, false);
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
    public void createNewUnit(long entityType, boolean isSuperHeavy) {

        if (entityType == Entity.ETYPE_TRIPOD_MECH) {
            setEntity(new TripodMech(Mech.GYRO_STANDARD, Mech.COCKPIT_TRIPOD));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_QUAD_MECH) {
            setEntity(new QuadMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        } else if (entityType == Entity.ETYPE_LAND_AIR_MECH) {
            setEntity(new LandAirMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD));
            getEntity().setTechLevel(TechConstants.T_IS_ADVANCED);
            getEntity().setManualBV(-1);
        } else { // type == 0
            setEntity(new BipedMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD));
            getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        }
        Mech mech = (Mech) getEntity();

        getEntity().setYear(3145);
        getEntity().setWeight(25);
        mech.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        getEntity().setArmorType(EquipmentType.T_ARMOR_STANDARD);
        getEntity().setArmorTechLevel(getEntity().getTechLevel());
        getEntity().setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        mech.addGyro();
        mech.addEngineCrits();
        mech.addCockpit();
        UnitUtil.updateHeatSinks(mech, 10, "Single");

        getEntity().autoSetInternal();
        for (int loc = 0; loc < getEntity().locations(); loc++) {
            mech.initializeArmor(0, loc);
            mech.initializeRearArmor(0, loc);
        }

        getEntity().setChassis("New");
        getEntity().setModel("Mek");

    }

    @Override
    public void refreshAll() {

        boolean isQuad = getEntity() instanceof QuadMech;
        boolean isLAM = getEntity() instanceof LandAirMech;
        boolean isTripod = getEntity() instanceof TripodMech;

        // Check to see if the current entity type matches the selected type
        if (((structureTab.isQuad() && !isQuad)
                    || (!structureTab.isQuad() && isQuad))
                || ((structureTab.isLAM() && !isLAM)
                        || (!structureTab.isLAM() && isLAM))
                || ((structureTab.isTripod() && !isTripod)
                        || (!structureTab.isTripod() && isTripod))) {
            // If no match, create a new entity of the right type
            String model = getEntity().getModel();
            String chassis = getEntity().getChassis();
            String source = getEntity().getSource();
            int year = getEntity().getYear();
            int techLevel = getEntity().getTechLevel();
            int mBV = getEntity().getManualBV();

            long eType;
            if (structureTab.isQuad()){
                eType = Entity.ETYPE_QUAD_MECH;
            } else if (structureTab.isLAM()){
                eType = Entity.ETYPE_LAND_AIR_MECH;
            } else if (structureTab.isTripod()){
                eType = Entity.ETYPE_TRIPOD_MECH;
            } else {
                eType = Entity.ETYPE_BIPED_MECH;
            }

            createNewUnit(eType, false);

            getEntity().setChassis(chassis);
            getEntity().setModel(model);
            getEntity().setSource(source);
            getEntity().setYear(year);
            getEntity().setTechLevel(techLevel);
            getEntity().setManualBV(mBV);

            reloadTabs();
            repaint();
            refreshAll();
        }
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

}