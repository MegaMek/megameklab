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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.TechConstants;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.Infantry.Header;
import megameklab.com.ui.Infantry.StatusBar;
import megameklab.com.ui.Infantry.tabs.ArmorTab;
import megameklab.com.ui.Infantry.tabs.StructureTab;
import megameklab.com.ui.Infantry.tabs.WeaponTab;
import megameklab.com.util.MenuBarCreator;


public class MainUI extends MegaMekLabMainUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5338040000652349619L;

	StructureTab structureTab;
	WeaponTab weaponTab;
	ArmorTab armorTab;
	Header header;
	StatusBar statusbar;
    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private MenuBarCreator menubarcreator;
	
	public MainUI() {

        super();
        createNewUnit(false);
        setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
        menubarcreator = new MenuBarCreator(entity, this);
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
        //ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        Infantry pbi = (Infantry) entity;

        header = new Header(pbi);
        statusbar = new StatusBar(pbi);
        structureTab = new StructureTab(pbi);
        armorTab = new ArmorTab(pbi);
        weaponTab = new WeaponTab(pbi);
        
        //header.addRefreshedListener(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        weaponTab.addRefreshedListener(this);

        ConfigPane.addTab("Characteristics", structureTab);
        ConfigPane.addTab("Armor", armorTab);
        ConfigPane.addTab("Weapons", weaponTab);

        masterPanel.add(header);
        masterPanel.add(ConfigPane);
        masterPanel.add(statusbar);

        refreshHeader();
        this.repaint();
	}

	@Override
	public void createNewUnit(boolean isNew) {
		createNewUnit(false, false);
	}

	@Override
	public void createNewUnit(boolean isNew, boolean isAlsoNew) {
		entity = new Infantry();
		entity.setYear(2750);
        entity.setTechLevel(TechConstants.T_IS_TW_NON_BOX);
        entity.setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
		((Infantry)entity).setSquadN(4);
		((Infantry)entity).setSquadSize(7);
		((Infantry)entity).setPrimaryWeapon((InfantryWeapon)EquipmentType.get("InfantryAssaultRifle"));
		try {
            entity.addEquipment(EquipmentType.get("InfantryAssaultRifle"), Infantry.LOC_INFANTRY);
        } catch (LocationFullException ex) {
        }
		entity.autoSetInternal();
		entity.setChassis("New");
		entity.setModel("Infantry");
	}

	@Override
	public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        weaponTab.refresh();
        armorTab.refresh();
	}

	@Override
	public void refreshArmor() {
		armorTab.refresh();
	}

	@Override
	public void refreshBuild() {
		
	}

	@Override
	public void refreshEquipment() {
		
	}

	@Override
	public void refreshHeader() {
		
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
		weaponTab.refresh();
	}

}
