/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later  version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Mek.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.LandAirMech;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.Weapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.Mek.tabs.BuildTab;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.CriticalTransferHandler;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

/**
 * This IView shows all the equipment that's not yet been assigned a location
 * @author beerockxs
 *
 */
public class BuildView extends IView implements ActionListener, MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<Mounted>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private int engineHeatSinkCount = 0;
    
    CriticalTransferHandler cth;

    public BuildView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        equipmentList = new CriticalTableModel(getMech(), CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        cth = new CriticalTransferHandler(eSource, refresh);
        equipmentTable.setTransferHandler(cth);
        TableColumn column = null;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if(i == 0) {
                column.setPreferredWidth(250);
            }
            column.setCellRenderer(equipmentList.getRenderer());

        }
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new java.awt.Dimension(300, 400));
        equipmentScroll.setPreferredSize(new java.awt.Dimension(300, 400));
        equipmentScroll.setTransferHandler(cth);

        equipmentTable.addMouseListener(this);

        setLayout(new BorderLayout());
        this.add(equipmentScroll, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Unallocated Equipment", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {
        cth.addRefreshListener(l);
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        engineHeatSinkCount = UnitUtil.getCriticalFreeHeatSinks(getMech(), getMech().hasCompactHeatSinks());
        for (Mounted mount : getMech().getMisc()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !isEngineHeatSink(mount) && !(mount.getType().getCriticals(getMech()) == 0)) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getMech().getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getMech().getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && ((mount.getUsableShotsLeft() > 1) || (((AmmoType)mount.getType()).getAmmoType() == AmmoType.T_COOLANT_POD))) {
                masterEquipmentList.add(mount);
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.mountedComparator());

        // Time to Sort
        // HeatSinks first
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isHeatSink(masterEquipmentList.get(pos))) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // Jump Jets
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isJumpJet(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // weapons and ammo
        Vector<Mounted> weaponsNAmmoList = new Vector<Mounted>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        Collections.sort(weaponsNAmmoList, StringUtils.mountedComparator());
        for (Mounted mount : weaponsNAmmoList) {
            equipmentList.addCrit(mount);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType) && !UnitUtil.isArmor(masterEquipmentList.get(pos).getType()) && !UnitUtil.isTSM(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // structure
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isStructure(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // armor
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isArmor(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else that is not TSM
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (!UnitUtil.isTSM(masterEquipmentList.get(pos).getType())) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // TSM
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            equipmentList.addCrit(masterEquipmentList.get(pos));
        }

    }

    private boolean isEngineHeatSink(Mounted mount) {
        if ((mount.getLocation() == Entity.LOC_NONE) && UnitUtil.isHeatSink(mount) && (engineHeatSinkCount > 0)) {
            if(mount.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK) && mount.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)) {
                //only single compact HS should be used for engine sinks
                return false;
            }
            engineHeatSinkCount--;
            return engineHeatSinkCount >= 0;
        }

        return false;
    }

    public void refresh() {
        removeAllListeners();
        loadEquipmentTable();
        fireTableRefresh();
        addAllListeners();
    }

    private void removeAllListeners() {
    }

    private void addAllListeners() {
    }

    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getMech());
        equipmentList.refreshModel();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            Mounted eq = (Mounted)equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);

            final int totalCrits = UnitUtil.getCritsUsed(getMech(), eq.getType());
            String[] locations = getMech().getLocationNames();
            String[] abbrLocations = getMech().getLocationAbbrs();

            if ((eq.getType().isSpreadable() || eq.isSplitable())
                    && (totalCrits > 1)
                    && !((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP))
                    && !(getMech() instanceof LandAirMech)) {
                int[] critSpace = UnitUtil.getHighestContinuousNumberOfCritsArray(getMech());
                if ((critSpace[Mech.LOC_RT] >= 1) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_RT)) {
                    JMenu rtMenu = new JMenu(locations[Mech.LOC_RT]);

                    if (critSpace[Mech.LOC_RT] >= totalCrits) {
                        item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_RT]));
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jMenuLoadComponent_actionPerformed(Mech.LOC_RT, selectedRow);
                            }
                        });
                        rtMenu.add(item);
                    }

                    int[] splitLocations = new int[]
                        { Mech.LOC_CT, Mech.LOC_RARM, Mech.LOC_RLEG };

                    for (int location = 0; location < 3; location++) {
                        JMenu subMenu = new JMenu(String.format("%1$s/%2$s", abbrLocations[Mech.LOC_RT], abbrLocations[splitLocations[location]]));
                        int subCrits = critSpace[splitLocations[location]];
                        for (int slots = 1; slots <= subCrits; slots++) {
                            final int primarySlots = totalCrits - slots;
                            item = new JMenuItem(String.format("%1$s (%2$s)/%3$s (%4$s)", abbrLocations[Mech.LOC_RT], primarySlots, abbrLocations[splitLocations[location]], slots));

                            final int secondaryLocation = splitLocations[location];
                            item.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RT, secondaryLocation, primarySlots, selectedRow);
                                }
                            });
                            subMenu.add(item);
                        }
                        rtMenu.add(subMenu);
                    }
                    popup.add(rtMenu);
                }

                if ((critSpace[Mech.LOC_RARM] >= totalCrits) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_RARM)) {
                    item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_RARM]));
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RARM, Mech.LOC_RARM, totalCrits, selectedRow);
                        }
                    });
                    popup.add(item);
                }

                if ((critSpace[Mech.LOC_LT] >= 1) && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_LT)) {
                    JMenu ltMenu = new JMenu(locations[Mech.LOC_LT]);

                    if (critSpace[Mech.LOC_LT] >= totalCrits) {
                        item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_LT]));
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jMenuLoadComponent_actionPerformed(Mech.LOC_LT, selectedRow);
                            }
                        });
                        ltMenu.add(item);
                    }

                    int[] splitLocations = new int[]
                        { Mech.LOC_CT, Mech.LOC_LARM, Mech.LOC_LLEG };

                    for (int location = 0; location < 3; location++) {
                        JMenu subMenu = new JMenu(String.format("%1$s/%2$s", abbrLocations[Mech.LOC_LT], abbrLocations[splitLocations[location]]));
                        int subCrits = critSpace[splitLocations[location]];
                        for (int slots = 1; slots <= subCrits; slots++) {
                            final int primarySlots = totalCrits - slots;
                            item = new JMenuItem(String.format("%1$s (%2$s)/%3$s (%4$s)", abbrLocations[Mech.LOC_LT], primarySlots, abbrLocations[splitLocations[location]], slots));

                            final int secondaryLocation = splitLocations[location];
                            item.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LT, secondaryLocation, primarySlots, selectedRow);
                                }
                            });
                            subMenu.add(item);
                        }
                        ltMenu.add(subMenu);
                    }
                    popup.add(ltMenu);

                }

                if ((critSpace[Mech.LOC_LARM] >= totalCrits)  && UnitUtil.isValidLocation(getMech(), eq.getType(), Mech.LOC_LARM)) {
                    item = new JMenuItem(String.format("Add to %1$s", locations[Mech.LOC_LARM]));
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LARM, Mech.LOC_LARM, totalCrits, selectedRow);
                        }
                    });
                    popup.add(item);
                }

            } else {
                for (int location = 0; location < getMech().locations(); location++) {

                    if ((UnitUtil.getHighestContinuousNumberOfCrits(getMech(), location) >= totalCrits)  && UnitUtil.isValidLocation(getMech(), eq.getType(), location)) {
                        item = new JMenuItem("Add to " + locations[location]);

                        final int loc = location;
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jMenuLoadComponent_actionPerformed(loc, selectedRow);
                            }
                        });
                        popup.add(item);
                    }
                }
            }
            popup.show(this, e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private void jMenuLoadSplitComponent_actionPerformed(int location, int secondaryLocation, int primarySlots, int selectedRow) {
        Mounted eq = (Mounted)equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        int crits = UnitUtil.getCritsUsed(getMech(), eq.getType());
        int openSlots = Math.min(primarySlots, UnitUtil.getHighestContinuousNumberOfCrits(getMech(), location));
        eq.setSecondLocation(secondaryLocation);

        for (int slot = 0; slot < openSlots; slot++) {
            try {
                UnitUtil.addMounted(getMech(), eq, location, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        crits -= openSlots;
        for (int slot = 0; slot < crits; slot++) {
            try {
                UnitUtil.addMounted(getMech(), eq, secondaryLocation, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        UnitUtil.changeMountStatus(getMech(), eq, location, secondaryLocation, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BuildTab) getParent().getParent()).refreshAll();

    }

    private void jMenuLoadComponent_actionPerformed(int location, int selectedRow) {
        Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT);
        if (eq.getType().isSpreadable() || eq.isSplitable()) {
            if (!(eq.getType() instanceof MiscType) || !eq.getType().hasFlag(MiscType.F_TARGCOMP)) {
                jMenuLoadSplitComponent_actionPerformed(location, Entity.LOC_NONE, 1,
                        selectedRow);
            } else {
                // Targetting computer is flagged as spreadable so the slots will be added one at a time when loaded,
                // since we don't have a way of indicating the number of slots until we know all the weapons. But
                // it's not really splittable, so we need to put add all the slots at once.
                jMenuLoadSplitComponent_actionPerformed(location, Entity.LOC_NONE, eq.getType().getCriticals(getMech()),
                        selectedRow);
            }
            return;
        }
        try {
            if ((eq.getType() instanceof WeaponType) && eq.getType().hasFlag(WeaponType.F_VGL)) {
                String[] facings;
                if (location == Mech.LOC_LT) {
                    facings = new String[4];
                    facings[0] = "Front";
                    facings[1] = "Front-Left";
                    facings[2] = "Rear-Left";
                    facings[3] = "Rear";
                } else if (location == Mech.LOC_RT) {
                    facings = new String[4];
                    facings[0] = "Front";
                    facings[1] = "Front-Right";
                    facings[2] = "Rear-Right";
                    facings[3] = "Rear";
                } else if (location == Mech.LOC_CT) {
                    facings = new String[2];
                    facings[0] = "Front";
                    facings[1] = "Rear";
                } else {
                    JOptionPane.showMessageDialog(this, "VGL must be placed in torso location!", "Invalid location", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String facing = (String)JOptionPane.showInputDialog(this, "Please choose the facing of the VGL", "Choose Facing", JOptionPane.QUESTION_MESSAGE, null, facings, facings[0]);
                if (facing == null) {
                    return;
                }
                UnitUtil.addMounted(getMech(), eq, location, false);
                if (facing.equals("Front-Left")) {
                    eq.setFacing(5);
                } else if (facing.equals("Front-Right")) {
                    eq.setFacing(1);
                } else if (facing.equals("Rear-Right")) {
                    eq.setFacing(2);
                } else if (facing.equals("Rear-Left")) {
                    eq.setFacing(4);
                } else if (facing.equals("Rear")) {
                    eq.setFacing(3);
                    UnitUtil.changeMountStatus(getMech(), eq, location, -1, true);
                }
            } else {
                UnitUtil.addMounted(getMech(), eq, location, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UnitUtil.changeMountStatus(getMech(), eq, location, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BuildTab) getParent().getParent()).refreshAll();
    }
}
