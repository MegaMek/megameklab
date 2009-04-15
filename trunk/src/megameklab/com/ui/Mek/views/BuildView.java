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

package megameklab.com.ui.Mek.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import megamek.common.AmmoType;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.weapons.Weapon;
import megameklab.com.ui.Mek.tabs.BuildTab;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.CriticalTransferHandler;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.StringUtils;
import megameklab.com.util.UnitUtil;

public class BuildView extends IView implements ActionListener, MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 799195356642563937L;

    private JPanel mainPanel = new JPanel();

    private CriticalTableModel equipmentList;
    private Vector<EquipmentType> masterEquipmentList = new Vector<EquipmentType>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private int engineHeatSinkCount = 0;

    public BuildView(Mech unit) {
        super(unit);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        equipmentList = new CriticalTableModel(this.unit, CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        CriticalTransferHandler cth = new CriticalTransferHandler(unit, null);
        equipmentTable.setTransferHandler(cth);

        equipmentList.initColumnSizes(equipmentTable);

        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // equipmentScroll.setToolTipText("");
        equipmentScroll.setPreferredSize(new Dimension(getWidth(), getHeight()));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);

        mainPanel.add(equipmentScroll);
        equipmentTable.addMouseListener(this);

        this.add(mainPanel);
        // loadEquipmentTable();

    }

    public void addRefreshedListener(RefreshListener l) {
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        engineHeatSinkCount = UnitUtil.getBaseChassisHeatSinks(unit);
        for (Mounted mount : unit.getMisc()) {
            if (mount.getLocation() == Entity.LOC_NONE && !isEngineHeatSink(mount)) {
                masterEquipmentList.add(mount.getType());
            }
        }
        for (Mounted mount : unit.getWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount.getType());
            }
        }
        for (Mounted mount : unit.getAmmo()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount.getType());
            }
        }

        Collections.sort(masterEquipmentList, StringUtils.equipmentTypeComparator());

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
            if (UnitUtil.isJumpJet(masterEquipmentList.get(pos))) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // weapons and ammo
        Vector<EquipmentType> weaponsNAmmoList = new Vector<EquipmentType>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (masterEquipmentList.get(pos) instanceof Weapon || masterEquipmentList.get(pos) instanceof AmmoType) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        Collections.sort(weaponsNAmmoList, StringUtils.equipmentTypeComparator());
        for (EquipmentType eq : weaponsNAmmoList) {
            equipmentList.addCrit(eq);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (masterEquipmentList.get(pos) instanceof MiscType && UnitUtil.isArmor(masterEquipmentList.get(pos)) && UnitUtil.isTSM(masterEquipmentList.get(pos))) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // structure
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (masterEquipmentList.get(pos) instanceof MiscType && masterEquipmentList.get(pos).hasFlag(MiscType.F_ENDO_STEEL)) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // armor
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (UnitUtil.isArmor(masterEquipmentList.get(pos))) {
                equipmentList.addCrit(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }

        // everything else that is not TSM
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if (!UnitUtil.isTSM(masterEquipmentList.get(pos))) {
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
        if (UnitUtil.isHeatSink(mount) && engineHeatSinkCount > 0) {
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
        equipmentList.updateMech(unit);
        equipmentList.refreshModel();
        equipmentScroll.setPreferredSize(new Dimension(getWidth() * 90 / 100, getHeight() * 90 / 100));
        equipmentScroll.repaint();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(e.getPoint());
            Mounted eq = UnitUtil.getMounted(unit, ((EquipmentType) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT)).getInternalName());

            int totalCrits = UnitUtil.getCritsUsed(unit, eq.getType());
            if (eq.getType().isSpreadable() || eq.isSplitable()) {
                for (int location = Mech.LOC_RT; location <= Mech.LOC_LLEG; location++) {

                    if (UnitUtil.getHighestContinuousNumberOfCrits(unit, location) >= totalCrits) {
                        if (unit instanceof BipedMech) {
                            item = new JMenuItem("Add to " + BipedMech.LOCATION_NAMES[location]);
                        } else {
                            item = new JMenuItem("Add to " + QuadMech.LOCATION_NAMES[location]);
                        }

                        final int loc = location;
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jMenuLoadComponent_actionPerformed(loc, selectedRow);
                            }
                        });
                        popup.add(item);
                    } else {
                        String[] locations;
                        int[] critSpace = UnitUtil.getHighestContinuousNumberOfCritsArray(unit);
                        if (unit instanceof BipedMech) {
                            locations = BipedMech.LOCATION_ABBRS;
                        } else {
                            locations = QuadMech.LOCATION_ABBRS;
                        }
                        switch (location) {
                        case Mech.LOC_RARM:
                            if (critSpace[location] + critSpace[Mech.LOC_RT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_RARM], locations[Mech.LOC_RT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RARM, Mech.LOC_RT, selectedRow);
                                    }
                                });
                                popup.add(item);
                                break;
                            }
                        case Mech.LOC_LARM:
                            if (critSpace[location] + critSpace[Mech.LOC_LT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_LARM], locations[Mech.LOC_LT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LARM, Mech.LOC_LT, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            break;
                        case Mech.LOC_RT:
                            if (critSpace[location] + critSpace[Mech.LOC_RARM] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_RT], locations[Mech.LOC_RARM]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RT, Mech.LOC_RARM, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            if (critSpace[location] + critSpace[Mech.LOC_RLEG] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_RT], locations[Mech.LOC_RLEG]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RT, Mech.LOC_RLEG, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            if (critSpace[location] + critSpace[Mech.LOC_CT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_RT], locations[Mech.LOC_CT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RT, Mech.LOC_CT, selectedRow);
                                    }
                                });
                                popup.add(item);
                                break;
                            }
                        case Mech.LOC_LT:
                            if (critSpace[location] + critSpace[Mech.LOC_LARM] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_LT], locations[Mech.LOC_LARM]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LT, Mech.LOC_LARM, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            if (critSpace[location] + critSpace[Mech.LOC_LLEG] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_LT], locations[Mech.LOC_LLEG]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LT, Mech.LOC_LLEG, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            if (critSpace[location] + critSpace[Mech.LOC_CT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_LT], locations[Mech.LOC_CT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LT, Mech.LOC_CT, selectedRow);
                                    }
                                });
                                popup.add(item);
                                break;
                            }
                        case Mech.LOC_RLEG:
                            if (critSpace[location] + critSpace[Mech.LOC_RT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_RLEG], locations[Mech.LOC_RT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_RLEG, Mech.LOC_RT, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            break;
                        case Mech.LOC_LLEG:
                            if (critSpace[location] + critSpace[Mech.LOC_LT] >= totalCrits) {
                                item = new JMenuItem(String.format("Add to %1$s/%2$s", locations[Mech.LOC_LLEG], locations[Mech.LOC_LT]));
                                item.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        jMenuLoadSplitComponent_actionPerformed(Mech.LOC_LLEG, Mech.LOC_LT, selectedRow);
                                    }
                                });
                                popup.add(item);
                            }
                            break;
                        }
                    }
                }

            } else {
                for (int location = 0; location <= Mech.LOC_LLEG; location++) {

                    if (UnitUtil.getHighestContinuousNumberOfCrits(unit, location) >= totalCrits) {
                        if (unit instanceof BipedMech) {
                            item = new JMenuItem("Add to " + BipedMech.LOCATION_NAMES[location]);
                        } else {
                            item = new JMenuItem("Add to " + QuadMech.LOCATION_NAMES[location]);
                        }

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

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private void jMenuLoadSplitComponent_actionPerformed(int location, int secondaryLocation, int selectedRow) {
        Mounted eq = UnitUtil.getMounted(unit, ((EquipmentType) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT)).getInternalName());
        int crits = UnitUtil.getCritsUsed(unit, eq.getType());
        int openSlots = UnitUtil.getHighestContinuousNumberOfCrits(unit, location);
        eq.setSecondLocation(secondaryLocation);

        for (int slot = 0; slot < openSlots; slot++) {
            try {
                unit.addEquipment(eq, location, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        crits -= openSlots;
        for (int slot = 0; slot < crits; slot++) {
            try {
                unit.addEquipment(eq, secondaryLocation, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        UnitUtil.changeMountStatus(unit, eq, location, secondaryLocation, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BuildTab) getParent().getParent()).refreshAll();

    }

    private void jMenuLoadComponent_actionPerformed(int location, int selectedRow) {
        Mounted eq = UnitUtil.getMounted(unit, ((EquipmentType) equipmentTable.getModel().getValueAt(selectedRow, CriticalTableModel.EQUIPMENT)).getInternalName());
        try {
            unit.addEquipment(eq, location, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UnitUtil.changeMountStatus(unit, eq, location, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((BuildTab) getParent().getParent()).refreshAll();
    }
}
