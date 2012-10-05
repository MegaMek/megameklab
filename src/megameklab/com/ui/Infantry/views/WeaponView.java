/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Infantry.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.weapons.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.WeaponListCellRenderer;

public class WeaponView extends IView implements ActionListener, MouseListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;
    private RefreshListener refresh;

    private JPanel mainPanel = new JPanel();
    private JTabbedPane leftPanel = new JTabbedPane(SwingConstants.RIGHT);
    private JPanel rightPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton primaryPersonalSetButton = new JButton("Set as Primary");
    private JButton secondaryPersonalSetButton = new JButton("Set as Secondary");
    private JButton secondarySupportSetButton = new JButton("Set as Secondary");
    private JButton primaryPhysicalSetButton = new JButton("Set as Primary");
    private JButton secondaryPhysicalSetButton = new JButton("Set as Secondary");
    private JButton removeButton = new JButton("Remove");
    private JButton removeAllButton = new JButton("Remove All");

    private JScrollPane physicalWeaponScroll = new JScrollPane();
    private JScrollPane personalWeaponScroll = new JScrollPane();
    private JScrollPane supportWeaponScroll = new JScrollPane();
    //private JScrollPane artilleryWeaponScroll = new JScrollPane();
    //private JScrollPane artilleryAmmoScroll = new JScrollPane();

    private JPanel physicalWeaponPane = new JPanel();
    private JPanel personalWeaponPane = new JPanel();
    private JPanel supportWeaponPane = new JPanel();
    //private JPanel artilleryPane = new JPanel();
    //private JPanel artilleryAmmoPane = new JPanel();

    private JList physicalWeaponCombo = new JList();
    private JList personalWeaponCombo = new JList();
    private JList supportWeaponCombo = new JList();
    //private JList ballisticWeaponCombo = new JList();
    //private JList ballisticAmmoCombo = new JList();
    //private JList artilleryWeaponCombo = new JList();
    //private JList artilleryAmmoCombo = new JList();

    private Vector<EquipmentType> masterPersonalWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterSupportWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> masterBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> masterArtilleryWeaponList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> masterPhysicalWeaponList = new Vector<EquipmentType>(10, 1);

    private Vector<EquipmentType> subPersonalWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subLaserAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subSupportWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subMissileAmmoList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subBallisticWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subBallisticAmmoList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subArtilleryWeaponList = new Vector<EquipmentType>(10, 1);
    //private Vector<EquipmentType> subArtilleryAmmoList = new Vector<EquipmentType>(10, 1);
    private Vector<EquipmentType> subPhysicalWeaponList = new Vector<EquipmentType>(10, 1);

    private JTextField primaryWeaponTxt = new JTextField();
    private JTextField secondaryWeaponTxt = new JTextField();

    private static final String PRIMARYPERSONALWEAPON_COMMAND = "SETPRIMARYPERSONAL";
    private static final String SECONDARYPERSONALWEAPON_COMMAND = "SETSECONDARYPERSONAL";
    private static final String SECONDARYSUPPORTWEAPON_COMMAND = "SETSECONDARYSUPPORT";
    private static final String PRIMARYPHYSICALWEAPON_COMMAND = "SETPRIMARYPHYSICAL";
    private static final String SECONDARYPHYSICALWEAPON_COMMAND = "SETSECONDARYPHYSICAL";

    private static final String REMOVE_COMMAND = "REMOVE";
    private static final String REMOVEALL_COMMAND = "REMOVEALL";

    public WeaponView(Infantry unit) {
        super(unit);

        mainPanel.setLayout(new GridLayout(0,2));
        rightPanel.setLayout(new GridLayout(2,0));
        //buttonPanel.setLayout(new FLayout());

        leftPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        if(null != unit.getPrimaryWeapon()) {
            primaryWeaponTxt.setText(unit.getPrimaryWeapon().getName());
        } else {
        	primaryWeaponTxt.setText("");
        }
        if(null != unit.getSecondaryWeapon()) {
            secondaryWeaponTxt.setText(unit.getSecondaryWeapon().getName());
        } else {
        	secondaryWeaponTxt.setText("");
        }
        primaryWeaponTxt.setEditable(false);
        secondaryWeaponTxt.setEditable(false);
        
        Dimension size = new Dimension(250, 300);
        List<JPanel> tPanels = new ArrayList<JPanel>();
        List<JScrollPane> tScrollPanes = new ArrayList<JScrollPane>();
        tPanels.add(personalWeaponPane);
        //tPanels.add(laserAmmoPane);
        tPanels.add(supportWeaponPane);
        //tPanels.add(missileAmmoPane);
        //tPanels.add(ballisticPane);
        //tPanels.add(ballisticAmmoPane);
        //tPanels.add(artilleryPane);
        //tPanels.add(artilleryAmmoPane);
        tPanels.add(physicalWeaponPane);
        tScrollPanes.add(personalWeaponScroll);
        //tScrollPanes.add(laserAmmoScroll);
        tScrollPanes.add(supportWeaponScroll);
        //tScrollPanes.add(missileAmmoScroll);
        //tScrollPanes.add(ballisticWeaponScroll);
        //tScrollPanes.add(ballisticAmmoScroll);
        //tScrollPanes.add(artilleryWeaponScroll);
        //tScrollPanes.add(artilleryAmmoScroll);
        tScrollPanes.add(physicalWeaponScroll);

        // match JPanels to JScrollPanes, and set
        // scrollpane properties
        for (JScrollPane tScrollPane : tScrollPanes) {
            tScrollPane.setWheelScrollingEnabled(true);
            tScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            tScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            tScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            tScrollPane.setPreferredSize(size);
            tScrollPane.setMaximumSize(size);
            tScrollPane.setBackground(Color.WHITE);
            tPanels.get(tScrollPanes.indexOf(tScrollPane)).setBackground(Color.WHITE);
            tScrollPane.setViewportView(tPanels.get(tScrollPanes.indexOf(tScrollPane)));
        }

        Font listFont = new Font("Arial", Font.PLAIN, 10);
        personalWeaponPane.add(personalWeaponCombo);
        personalWeaponCombo.setFixedCellWidth(240);
        personalWeaponCombo.setFont(listFont);
        personalWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        /*
        laserAmmoPane.add(laserAmmoCombo);
        laserAmmoCombo.setFixedCellWidth(155);
        laserAmmoCombo.setFont(listFont);
        laserAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));*/

        supportWeaponPane.add(supportWeaponCombo);
        supportWeaponCombo.setFixedCellWidth(240);
        supportWeaponCombo.setFont(listFont);
        supportWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
       /* missileAmmoPane.add(missileAmmoCombo);
        missileAmmoCombo.setFixedCellWidth(155);
        missileAmmoCombo.setFont(listFont);
        missileAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        ballisticPane.add(ballisticWeaponCombo);
        ballisticWeaponCombo.setFixedCellWidth(155);
        ballisticWeaponCombo.setFont(listFont);
        ballisticWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        ballisticAmmoPane.add(ballisticAmmoCombo);
        ballisticAmmoCombo.setFixedCellWidth(155);
        ballisticAmmoCombo.setFont(listFont);
        ballisticAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        artilleryPane.add(artilleryWeaponCombo);
        artilleryWeaponCombo.setFixedCellWidth(155);
        artilleryWeaponCombo.setFont(listFont);
        artilleryWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));
        artilleryAmmoPane.add(artilleryAmmoCombo);
        artilleryAmmoCombo.setFixedCellWidth(155);
        artilleryAmmoCombo.setFont(listFont);
        artilleryAmmoCombo.setCellRenderer(new WeaponListCellRenderer(unit));
*/
        physicalWeaponPane.add(physicalWeaponCombo);
        physicalWeaponCombo.setFixedCellWidth(240);
        physicalWeaponCombo.setFont(listFont);
        physicalWeaponCombo.setCellRenderer(new WeaponListCellRenderer(unit));

        JPanel tab = new JPanel();
        tab.add(personalWeaponScroll);
        tab.setLayout(new SpringLayout());
        tab.add(primaryPersonalSetButton);
        tab.add(secondaryPersonalSetButton);
        //tab.add(laserAmmoScroll);
        //tab.add(laserAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Personal", tab);

        tab = new JPanel();
        tab.add(supportWeaponScroll);
        tab.setLayout(new SpringLayout());
        tab.add(secondarySupportSetButton);
        //tab.add(secondaryWeaponAddButton);
        //tab.add(missileAmmoScroll);
        //tab.add(missileAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Support", tab);
/*
        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(ballisticWeaponScroll);
        tab.add(ballisticWeaponAddButton);
        tab.add(ballisticAmmoScroll);
        tab.add(ballisticAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Ballistic", tab);

        tab = new JPanel();
        tab.setLayout(new SpringLayout());
        tab.add(artilleryWeaponScroll);
        tab.add(artilleryWeaponAddButton);
        tab.add(artilleryAmmoScroll);
        tab.add(artilleryAmmoAddButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Artillery", tab);
*/
        tab = new JPanel();
        tab.add(physicalWeaponScroll);
        tab.setLayout(new SpringLayout());
        tab.add(primaryPhysicalSetButton);
        tab.add(secondaryPhysicalSetButton);
        SpringLayoutHelper.setupSpringGrid(tab, 1);
        leftPanel.addTab("Physical", tab);

        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        rightPanel.add(primaryWeaponTxt);
        rightPanel.add(secondaryWeaponTxt);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        //SpringLayoutHelper.setupSpringGrid(rightPanel, 1);
        //SpringLayoutHelper.setupSpringGrid(buttonPanel, 2);
        //SpringLayoutHelper.setupSpringGrid(mainPanel, 2);

        this.add(mainPanel);

        Enumeration<EquipmentType> weaponTypes = EquipmentType.getAllTypes();
        while (weaponTypes.hasMoreElements()) {
            EquipmentType eq = weaponTypes.nextElement();

            if (!UnitUtil.isUnitWeapon(eq, unit)) {
                continue;
            }

            if (eq instanceof WeaponType) {

                WeaponType weapon = (WeaponType) eq;

                if(weapon instanceof InfantryWeapon) {
                	if(weapon.hasFlag(WeaponType.F_INF_SUPPORT)) {
                		masterSupportWeaponList.add(eq);
                	}
                	else if(weapon.hasFlag(WeaponType.F_INF_ARCHAIC)) {
                		masterPhysicalWeaponList.add(eq);
                	} 
                	else {
                		masterPersonalWeaponList.add(eq);
                	}
                }
            }
        }

        Collections.sort(masterPersonalWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterPhysicalWeaponList, StringUtils.equipmentTypeComparator());
        Collections.sort(masterSupportWeaponList, StringUtils.equipmentTypeComparator());
 
        removeButton.setMnemonic('R');
        removeAllButton.setMnemonic('L');
    /*    laserWeaponAddButton.setMnemonic('A');
        laserAmmoAddButton.setMnemonic('d');
        missileWeaponAddButton.setMnemonic('A');
        missileAmmoAddButton.setMnemonic('d');
        ballisticWeaponAddButton.setMnemonic('A');
        ballisticAmmoAddButton.setMnemonic('d');
        artilleryWeaponAddButton.setMnemonic('A');
        artilleryAmmoAddButton.setMnemonic('d');
        physicalWeaponButton.setMnemonic('A');
*/
    }

    private void loadWeaponCombos() {

        subPersonalWeaponList.removeAllElements();
        subSupportWeaponList.removeAllElements();
        subPhysicalWeaponList.removeAllElements();
        //subArtilleryWeaponList.removeAllElements();

        Vector<String> equipmentList = new Vector<String>();
        for (EquipmentType eq : masterPersonalWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subPersonalWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        personalWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterSupportWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subSupportWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        supportWeaponCombo.setListData(equipmentList);
/*
        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterBallisticWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subBallisticWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        ballisticWeaponCombo.setListData(equipmentList);

        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterArtilleryWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subArtilleryWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        artilleryWeaponCombo.setListData(equipmentList);
*/
        equipmentList = new Vector<String>();
        for (EquipmentType eq : masterPhysicalWeaponList) {
            if (UnitUtil.isLegal(unit, eq.getTechLevel())) {
                subPhysicalWeaponList.add(eq);
                equipmentList.add(eq.getInternalName());
            }
        }
        physicalWeaponCombo.setListData(equipmentList);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void refresh() {
        removeAllListeners();
        loadWeaponCombos();
        addAllListeners();
        fireTableRefresh();
    }

    private void removeAllListeners() {
    	primaryPersonalSetButton.removeActionListener(this);
        secondaryPersonalSetButton.removeActionListener(this);
        secondarySupportSetButton.removeActionListener(this);
        primaryPhysicalSetButton.removeActionListener(this);
        secondaryPhysicalSetButton.removeActionListener(this);
        removeButton.removeActionListener(this);
        removeAllButton.removeActionListener(this);
    }

    private void fireTableRefresh() {
    	if(null != getInfantry().getPrimaryWeapon()) {
            primaryWeaponTxt.setText(getInfantry().getPrimaryWeapon().getName());
        } else {
        	primaryWeaponTxt.setText("");
        }
        if(null != getInfantry().getSecondaryWeapon()) {
            secondaryWeaponTxt.setText(getInfantry().getSecondaryWeapon().getName());
        } else {
        	secondaryWeaponTxt.setText("");
        }
        if (refresh != null) {
            refresh.refreshStatus();
            refresh.refreshBuild();
        }
    }

    private void addAllListeners() {

        primaryPersonalSetButton.addActionListener(this);
        secondaryPersonalSetButton.addActionListener(this);
        secondarySupportSetButton.addActionListener(this);
        primaryPhysicalSetButton.addActionListener(this);
        secondaryPhysicalSetButton.addActionListener(this);

        removeButton.addActionListener(this);
        removeAllButton.addActionListener(this);

        primaryPersonalSetButton.setActionCommand(PRIMARYPERSONALWEAPON_COMMAND);
        secondaryPersonalSetButton.setActionCommand(SECONDARYPERSONALWEAPON_COMMAND);
        secondarySupportSetButton.setActionCommand(SECONDARYSUPPORTWEAPON_COMMAND);
        primaryPhysicalSetButton.setActionCommand(PRIMARYPHYSICALWEAPON_COMMAND);
        secondaryPhysicalSetButton.setActionCommand(SECONDARYPHYSICALWEAPON_COMMAND);

        removeButton.setActionCommand(REMOVE_COMMAND);
        removeAllButton.setActionCommand(REMOVEALL_COMMAND);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PRIMARYPERSONALWEAPON_COMMAND)) {
            try {
                if (personalWeaponCombo.getSelectedIndex() > -1) {
                	replaceMainWeapon((InfantryWeapon)subPersonalWeaponList.elementAt(personalWeaponCombo.getSelectedIndex()), false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(SECONDARYPERSONALWEAPON_COMMAND)) {
            try {
                if (personalWeaponCombo.getSelectedIndex() > -1) {
                	replaceMainWeapon((InfantryWeapon)subPersonalWeaponList.elementAt(personalWeaponCombo.getSelectedIndex()), true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(SECONDARYSUPPORTWEAPON_COMMAND)) {
            try {
                if (supportWeaponCombo.getSelectedIndex() > -1) {
                	replaceMainWeapon((InfantryWeapon)subSupportWeaponList.elementAt(supportWeaponCombo.getSelectedIndex()), true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(PRIMARYPHYSICALWEAPON_COMMAND)) {
            try {
                if (physicalWeaponCombo.getSelectedIndex() > -1) {
                	replaceMainWeapon((InfantryWeapon)subPhysicalWeaponList.elementAt(physicalWeaponCombo.getSelectedIndex()), false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals(SECONDARYPHYSICALWEAPON_COMMAND)) {
            try {
                if (physicalWeaponCombo.getSelectedIndex() > -1) {
                	replaceMainWeapon((InfantryWeapon)subPhysicalWeaponList.elementAt(physicalWeaponCombo.getSelectedIndex()), true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            return;
        }
        fireTableRefresh();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JList) {
            loadAmmo(e.getComponent());
        }
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        loadAmmo(arg0.getComponent());
    }

    public void keyTyped(KeyEvent arg0) {
    }

    private void loadAmmo(Component o) {
/*
        if (o instanceof JList) {
            JList list = (JList) o;
            if (list.equals(laserWeaponCombo)) {
                subLaserAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subLaserWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                if (weapon.getAmmoType() != AmmoType.T_NA) {
                    for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                        if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_PROTOMECH)) {
                            subLaserAmmoList.add(ammo);
                            equipmentList.add(ammo.getInternalName());
                        }
                    }
                }
                laserAmmoCombo.setListData(equipmentList);
            } else if (list.equals(missileWeaponCombo)) {
                subMissileAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subMissileWeaponList.elementAt(list.getSelectedIndex());

                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !weapon.hasFlag(WeaponType.F_ONESHOT) && !ammo.hasFlag(AmmoType.F_PROTOMECH)) {
                        subMissileAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                missileAmmoCombo.setListData(equipmentList);
            } else if (list.equals(ballisticWeaponCombo)) {
                subBallisticAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subBallisticWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR) && !ammo.hasFlag(AmmoType.F_PROTOMECH)) {
                        subBallisticAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                ballisticAmmoCombo.setListData(equipmentList);
            } else if (list.equals(artilleryWeaponCombo)) {
                subArtilleryAmmoList.removeAllElements();
                WeaponType weapon = (WeaponType) subArtilleryWeaponList.elementAt(list.getSelectedIndex());
                if (weapon.hasFlag(WeaponType.F_ONESHOT)) {
                    return;
                }
                Vector<String> equipmentList = new Vector<String>();
                for (AmmoType ammo : AmmoType.getMunitionsFor(weapon.getAmmoType())) {
                    if ((ammo.getRackSize() == weapon.getRackSize()) && UnitUtil.isLegal(unit, ammo.getTechLevel()) && !ammo.hasFlag(AmmoType.F_BATTLEARMOR) && !ammo.hasFlag(AmmoType.F_PROTOMECH)) {
                        subArtilleryAmmoList.add(ammo);
                        equipmentList.add(ammo.getInternalName());
                    }
                }
                artilleryAmmoCombo.setListData(equipmentList);
            }
        }
        */
    }
    
    private void replaceMainWeapon(InfantryWeapon weapon, boolean secondary) {
    	Mounted existingInfantryMount = null;
    	for(Mounted m : unit.getWeaponList()) {
    		if(m.getType() instanceof InfantryWeapon && m.getLocation() == Infantry.LOC_INFANTRY) {
    			existingInfantryMount = m;
    			break;
    		}
    	}
    	if(null != existingInfantryMount) {
    		UnitUtil.removeMounted(unit, existingInfantryMount);
    	}
    	if(secondary) {
    		getInfantry().setSecondaryWeapon(weapon);
    	} else {
    		getInfantry().setPrimaryWeapon(weapon);
    	}
    	//if there is more than one secondary weapon per squad, then add that
        // to the unit
        // otherwise add the primary weapon
    	if ((getInfantry().getSecondaryN() < 2) || (null == getInfantry().getSecondaryWeapon())) {
    		try {
                getInfantry().addEquipment(getInfantry().getPrimaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {
            	
            }
    	} else {
    		try {
                getInfantry().addEquipment(getInfantry().getSecondaryWeapon(), Infantry.LOC_INFANTRY);
            } catch (LocationFullException ex) {
            	
            }
    	}
    }

}