/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.com.ui.battlearmor;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EntityWeightClass;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.TechConstants;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.tabs.FluffTab;
import megameklab.com.ui.util.TabScrollPane;

public class BAMainUI extends MegaMekLabMainUI {

    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    private BAStructureTab structureTab;
    private BABuildTab buildTab;
    private BAEquipmentTab equipTab;
    private FluffTab fluffTab;
    private BAStatusBar statusbar;

    public BAMainUI() {

        super();
        createNewUnit(Entity.ETYPE_BATTLEARMOR);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
        finishSetup();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

        structureTab = new BAStructureTab(this);
        equipTab = new BAEquipmentTab(this);
        fluffTab = new FluffTab(this);

        statusbar = new BAStatusBar(this);
        buildTab = new BABuildTab(this);
        structureTab.addRefreshedListener(this);
        equipTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);

        configPane.addTab("Structure/Armor", new TabScrollPane(structureTab));
        configPane.addTab("Equipment", equipTab);
        configPane.addTab("Assign Criticals", new TabScrollPane(buildTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        setEntity(new BattleArmor());
        BattleArmor ba = (BattleArmor) getEntity();

        ba.setYear(3145);
        ba.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        ba.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);
        ba.setWeightClass(EntityWeightClass.WEIGHT_LIGHT);
        ba.setTroopers(4);
        ba.setChassisType(BattleArmor.CHASSIS_TYPE_BIPED);

        ba.autoSetInternal();
        for (int loc = 0; loc < ba.locations(); loc++) {
            ba.initializeArmor(0, loc);
        }

        ba.setChassis("New");
        ba.setModel("BattleArmor");
    }

    @Override
    public void refreshAll() {

        statusbar.refresh();
        structureTab.refresh();
        refreshEquipment();
        refreshBuild();
        refreshPreview();
        refreshHeader();
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
        equipTab.refresh();
    }
    
    @Override
    public void refreshTransport() {
        // not used for ba
    }

    @Override
    public void refreshHeader() {
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".blk");
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
        structureTab.refreshPreview();
    }
    
    @Override
    public void refreshSummary() {
    }
    
    @Override
    public void refreshEquipmentTable() {
        equipTab.refreshTable();
    }

    @Override
    public ITechManager getTechManager() {
        if (structureTab != null) {
            return structureTab.getTechManager();
        }
        return null;
    }

}