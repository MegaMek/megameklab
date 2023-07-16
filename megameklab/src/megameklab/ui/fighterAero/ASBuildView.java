/*
 * Copyright (c) 2008-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import megamek.common.*;
import megamek.common.verifier.TestAero;
import megamek.common.weapons.Weapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.CriticalTransferHandler;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.StringUtils;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * This IView shows all the equipment that's not yet been assigned a location
 * @author beerockxs
 * @author arlith
 */
public class ASBuildView extends IView implements ActionListener, MouseListener {
    private CriticalTableModel equipmentList;
    private Vector<Mounted> masterEquipmentList = new Vector<>(10, 1);
    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private int engineHeatSinkCount = 0;
    
    CriticalTransferHandler cth;

    public ASBuildView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);

        equipmentList = new CriticalTableModel(getAero(), CriticalTableModel.BUILDTABLE);

        equipmentTable.setModel(equipmentList);
        equipmentTable.setDragEnabled(true);
        cth = new CriticalTransferHandler(eSource, refresh);
        equipmentTable.setTransferHandler(cth);
        TableColumn column = null;
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            column = equipmentTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            }
            column.setCellRenderer(equipmentList.getRenderer());

        }
        equipmentTable.setIntercellSpacing(new Dimension(0, 0));
        equipmentTable.setShowGrid(false);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        equipmentScroll.setMinimumSize(new Dimension(400, 400));
        equipmentScroll.setPreferredSize(new Dimension(400, 400));
        equipmentScroll.setTransferHandler(cth);

        equipmentTable.addMouseListener(this);

        setLayout(new BorderLayout());
        this.add(equipmentScroll, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Unallocated Equipment", 
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
    }

    public void addRefreshedListener(RefreshListener l) {
        cth.addRefreshListener(l);
    }

    private void loadEquipmentTable() {
        equipmentList.removeAllCrits();
        masterEquipmentList.clear();
        for (Mounted mount : getAero().getMisc()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && 
                    !isEngineHeatSink(mount)) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getAero().getTotalWeaponList()) {
            if (mount.getLocation() == Entity.LOC_NONE) {
                masterEquipmentList.add(mount);
            }
        }
        for (Mounted mount : getAero().getAmmo()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && !mount.isOneShotAmmo()) {
                masterEquipmentList.add(mount);
            }
        }

        masterEquipmentList.sort(StringUtils.mountedComparator());

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
        Vector<Mounted> weaponsNAmmoList = new Vector<>(10, 1);
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof Weapon) || 
                    (masterEquipmentList.get(pos).getType() instanceof AmmoType)) {
                weaponsNAmmoList.add(masterEquipmentList.get(pos));
                masterEquipmentList.remove(pos);
                pos--;
            }
        }
        weaponsNAmmoList.sort(StringUtils.mountedComparator());
        for (Mounted mount : weaponsNAmmoList) {
            equipmentList.addCrit(mount);
        }

        // Equipment
        for (int pos = 0; pos < masterEquipmentList.size(); pos++) {
            if ((masterEquipmentList.get(pos).getType() instanceof MiscType) && 
                    !UnitUtil.isArmor(masterEquipmentList.get(pos).getType()) && 
                    !UnitUtil.isTSM(masterEquipmentList.get(pos).getType())) {
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
        if ((mount.getLocation() == Entity.LOC_NONE)
                && UnitUtil.isHeatSink(mount) && (engineHeatSinkCount > 0)
                && !(mount.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                && mount.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK))) {
            engineHeatSinkCount--;
            return true;
        } else {
            return false;
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        fireTableRefresh();
    }

    private void fireTableRefresh() {
        equipmentList.updateUnit(getAero());
        equipmentList.refreshModel();
    }

    public CriticalTableModel getTableModel() {
        return equipmentList;
    }

    public JTable getTable() {
        return equipmentTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {
        // On right-click, we want to generate menu items to add to specific 
        //  locations, but only if those locations are make sense
        if (evt.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem item;

            final int selectedRow = equipmentTable.rowAtPoint(evt.getPoint());
            Mounted eq = (Mounted)equipmentTable.getModel().getValueAt(
                    selectedRow, CriticalTableModel.EQUIPMENT);

            String[] locNames = getAero().getLocationNames();
            // A list of the valid locations we can add the selected eq to
            ArrayList<Integer> validLocs = new ArrayList<>();
            // The number of possible locations, Aeros' have LOC_WINGS and LOC_FUSELAGE, which we
            // want to ignore for now, hence -2
            int numLocs = getAero().locations() - 2;
            // If it's a weapon, there are restrictions
            if (eq.getType() instanceof WeaponType) {
                int[] availSpace = TestAero.availableSpace(getAero());

                if (availSpace != null) {
                    int[] numWeapons = new int[availSpace.length];

                    for (Mounted m : getAero().getWeaponList()) {
                        if (m.getLocation() != Aero.LOC_NONE) {
                            numWeapons[m.getLocation()]++;
                        }
                    }

                    for (int loc = 0; loc < numLocs; loc++) {
                        if ((numWeapons[loc] + 1) < availSpace[loc]) {
                            validLocs.add(loc);
                        }
                    }
                }
            // If it's not a weapon there are no space requirements
            } else {
                for (int loc = 0; loc < numLocs; loc++) {
                    validLocs.add(loc);
                }

                if (!UnitUtil.isWeaponEnhancement(eq.getType())) {
                    validLocs.add(Aero.LOC_FUSELAGE);
                }
            }

            // Add a menu item for each potential location
            for (Integer location: validLocs) {
                if (UnitUtil.isValidLocation(getAero(), eq.getType(), location)) {
                    item = new JMenuItem("Add to " + locNames[location]);

                    final int loc = location;
                    item.addActionListener(evt2 -> jMenuLoadComponent_actionPerformed(loc, selectedRow));
                    popup.add(item);
                }
            }
            popup.show(equipmentTable, evt.getX(), evt.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {

    }
    
    /**
     * When the user right-clicks on the equipment table, a context menu is
     * generated that his menu items for each possible location that is clicked.
     * When the location is clicked, this is the method that adds the selected
     * equipment to the desired location.
     * 
     * @param location
     * @param selectedRow
     */
    private void jMenuLoadComponent_actionPerformed(int location, int selectedRow) {
        Mounted eq = (Mounted) equipmentTable.getModel().getValueAt(selectedRow,
                CriticalTableModel.EQUIPMENT);
        try {
            getAero().addEquipment(eq, location, false);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
        UnitUtil.changeMountStatus(getAero(), eq, location, -1, false);

        // go back up to grandparent build tab and fire a full refresh.
        ((ASBuildTab) getParent().getParent()).refreshAll();
    }
}
