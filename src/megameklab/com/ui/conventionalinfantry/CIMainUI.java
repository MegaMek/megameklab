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

package megameklab.com.ui.conventionalinfantry;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import megamek.common.*;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.generalunit.PreviewTab;
import megameklab.com.ui.generalunit.FluffTab;
import megameklab.com.ui.util.TabScrollPane;

public class CIMainUI extends MegaMekLabMainUI {

    CIStructureTab structureTab;
    PreviewTab previewTab;
    FluffTab fluffTab;
    CIStatusBar statusbar;
    JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);

    public CIMainUI() {
        super();
        createNewUnit(Entity.ETYPE_INFANTRY);
        setTitle(getEntity().getChassis() + " " + getEntity().getModel() + ".mtf");
        finishSetup();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

        statusbar = new CIStatusBar(this);
        structureTab = new CIStructureTab(this);
        fluffTab = new FluffTab(this);
        previewTab = new PreviewTab(this);

        structureTab.addRefreshedListener(this);
        fluffTab.setRefreshedListener(this);

        configPane.addTab("Build", new TabScrollPane(structureTab));
        configPane.addTab("Fluff", new TabScrollPane(fluffTab));
        configPane.addTab("Preview", previewTab);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        validate();
    }

    @Override
    public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldEntity) {
        setEntity(new Infantry());
        getEntity().setYear(3145);
        getEntity().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        getEntity().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
        ((Infantry) getEntity()).setSquadN(4);
        ((Infantry) getEntity()).setSquadSize(7);
        ((Infantry) getEntity()).setPrimaryWeapon((InfantryWeapon) EquipmentType
                .get("InfantryAssaultRifle"));
        try {
            getEntity().addEquipment(EquipmentType.get(EquipmentTypeLookup.INFANTRY_ASSAULT_RIFLE),
                    Infantry.LOC_INFANTRY);
        } catch (LocationFullException ex) {
        }
        getEntity().autoSetInternal();
        getEntity().setChassis("New");
        getEntity().setModel("Infantry");
    }

    @Override
    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        previewTab.refresh();
    }

    @Override
    public void refreshArmor() {
        // armorTab.refresh();
    }

    @Override
    public void refreshBuild() {

    }

    @Override
    public void refreshEquipment() {

    }

    @Override
    public void refreshTransport() {
        // not used for infantry
    }

    @Override
    public void refreshHeader() {
        String title = getEntity().getChassis() + " " + getEntity().getModel()
                + ".blk";
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
        // weaponTab.refresh();
    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
    }

    @Override
    public void refreshSummary() {
    }

    @Override
    public void refreshEquipmentTable() {
        structureTab.refreshEquipmentTable();
    }

    @Override
    public ITechManager getTechManager() {
        if (null != structureTab) {
            return structureTab.getTechManager();
        }
        return null;
    }

}
