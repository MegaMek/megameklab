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

package megameklab.com.ui.Infantry;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.TechConstants;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Infantry.tabs.PreviewTab;
import megameklab.com.ui.Infantry.tabs.StructureTab;
import megameklab.com.util.MenuBarCreator;

public class MainUI extends MegaMekLabMainUI {

    /**
	 *
	 */
    private static final long serialVersionUID = 5338040000652349619L;

    StructureTab structureTab;
    PreviewTab previewTab;
    StatusBar statusbar;
    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;

    public MainUI() {

        super();
        createNewUnit(Entity.ETYPE_INFANTRY);
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
        ConfigPane.removeAll();

        masterPanel.setLayout(new BorderLayout());

        statusbar = new StatusBar(this);
        structureTab = new StructureTab(this);
        previewTab = new PreviewTab(this);

        structureTab.addRefreshedListener(this);

        ConfigPane.addTab("Build", structureTab);
        ConfigPane.addTab("Preview", previewTab);

        masterPanel.add(ConfigPane, BorderLayout.CENTER);
        masterPanel.add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        this.repaint();
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
            getEntity().addEquipment(EquipmentType.get("InfantryAssaultRifle"),
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
