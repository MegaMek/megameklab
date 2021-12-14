/*
 * MegaMekLab - Copyright (C) 2008
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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
package megameklab.com.ui.fighteraero;

import megamek.common.*;
import megamek.common.verifier.TestAero;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.CritCellUtil;
import megameklab.com.ui.util.IView;
import megameklab.com.util.Mech.DropTargetCriticalList;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * The Crit Slots view for a Fighter (Aerospace and Conventional)
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author neoancient
 * @author Simon (Juliez)
 */
public class ASCriticalView extends IView {

    private final Box leftWingPanel = Box.createVerticalBox();
    private final Box rightWingPanel = Box.createVerticalBox();
    private final Box nosePanel = Box.createVerticalBox();
    private final Box aftPanel = Box.createVerticalBox();
    private final Box fuselagePanel = Box.createVerticalBox();
    
    private final JLabel noseSpace = new JLabel();
    private final JLabel leftSpace = new JLabel();
    private final JLabel rightSpace = new JLabel();
    private final JLabel aftSpace = new JLabel();
    
    private final JButton btnCopyLW = new JButton("Copy from Left Wing");
    private final JButton btnCopyRW = new JButton("Copy from Right Wing");

    private RefreshListener refresh;

    public ASCriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        Box mainPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box middlePanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();

        leftSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        rightSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        noseSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        aftSpace.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        nosePanel.setBorder(CritCellUtil.locationBorder("Nose"));
        leftWingPanel.setBorder(CritCellUtil.locationBorder("Left Wing"));
        fuselagePanel.setBorder(CritCellUtil.locationBorder("Fuselage"));
        rightWingPanel.setBorder(CritCellUtil.locationBorder("Right Wing"));
        aftPanel.setBorder(CritCellUtil.locationBorder("Aft"));

        btnCopyLW.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCopyRW.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCopyLW.addActionListener(ev -> copyLocation(Aero.LOC_LWING, Aero.LOC_RWING));
        btnCopyRW.addActionListener(ev -> copyLocation(Aero.LOC_RWING, Aero.LOC_LWING));

        leftPanel.add(leftWingPanel);
        middlePanel.add(nosePanel);
        middlePanel.add(fuselagePanel);
        middlePanel.add(aftPanel);
        rightPanel.add(rightWingPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        leftWingPanel.removeAll();
        rightWingPanel.removeAll();
        nosePanel.removeAll();
        aftPanel.removeAll();
        fuselagePanel.removeAll();
        
        int[] availSpace = TestAero.availableSpace(getAero());

        if (availSpace == null) {
            // Shouldn't happen, since we only allow valid armor types to be selected...
            LogManager.getLogger().error("Invalid armour type!");
            return;
        }

        synchronized (getAero()) {
            for (int location = 0; location < getAero().locations(); location++) {
                if (location == Aero.LOC_WINGS) {
                    continue;
                }
                Vector<String> critNames = new Vector<>(1, 1);
                int numWeapons = 0;
                for (int slot = 0; slot < getAero().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getAero().getCritical(location, slot);
                    if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                        Mounted m = cs.getMount();
                        // Critical didn't get removed. Remove it now.
                        if (m == null) {
                            getAero().setCritical(location, slot, null);
                            continue;
                        }
                        // Ignore weapon groups
                        if (m.isWeaponGroup()) {
                            continue;
                        }
                        if (m.getType() instanceof WeaponType) {
                            numWeapons++;
                        }
                        StringBuilder critName = new StringBuilder(m.getName());
                        if (m.isRearMounted()) {
                            critName.append(" (R)");
                        }
                        if (m.isSponsonTurretMounted()) {
                            critName.append(" (ST)");
                        }
                        if (m.isPintleTurretMounted()) {
                            critName.append(" (PT)");
                        }
                        critName.append(":").append(slot);
                        critNames.add(critName.toString());
                    }
                }

                if (critNames.size() == 0) {
                    critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                }
                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(
                        critNames, eSource, refresh, true);
                criticalSlotList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + "");
                criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                switch (location) {
                    case Aero.LOC_NOSE:
                        nosePanel.add(criticalSlotList);
                        noseSpace.setText("Weapons: " + numWeapons + " / " + availSpace[location]);
                        break;
                    case Aero.LOC_LWING:
                        leftWingPanel.add(criticalSlotList);
                        leftSpace.setText("Weapons: " + numWeapons + " / " + availSpace[location]);
                        break;
                    case Aero.LOC_RWING:
                        rightWingPanel.add(criticalSlotList);
                        rightSpace.setText("Weapons: " + numWeapons + " / " + availSpace[location]);
                        break;
                    case Aero.LOC_AFT:
                        aftPanel.add(criticalSlotList);
                        aftSpace.setText("Weapons: " + numWeapons + " / " + availSpace[location]);
                        break;
                    case Aero.LOC_FUSELAGE:
                        fuselagePanel.add(criticalSlotList);
                        break;
                }
            }
            
            leftWingPanel.add(leftSpace);
            leftWingPanel.add(btnCopyRW);
            rightWingPanel.add(rightSpace);
            rightWingPanel.add(btnCopyLW);
            nosePanel.add(noseSpace);
            aftPanel.add(aftSpace);

            validate();
        }
    }
    
    private void copyLocation(int from, int to) {
        try {
            UnitUtil.copyLocationEquipment(getAero(), from, to);
        } catch (LocationFullException ex) {
            JOptionPane.showMessageDialog(this, "Insufficient space", "Location Full", JOptionPane.WARNING_MESSAGE);
        }
        refresh.refreshAll();
    }
}