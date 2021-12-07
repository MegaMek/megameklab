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

package megameklab.com.ui.Mek.views;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.CritCellUtil;
import megameklab.com.util.IView;
import megameklab.com.util.Mech.DropTargetCriticalList;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import java.util.Map;
import java.util.Vector;

/**
 * The Crit Slots view for a Mek (including Quad and Tripod)
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author Simon (Juliez)
 */
public class CriticalView extends IView {

    private final JPanel laPanel = new JPanel();
    private final JPanel raPanel = new JPanel();
    private final JPanel llPanel = new JPanel();
    private final JPanel rlPanel = new JPanel();
    private final JPanel clPanel = new JPanel();
    private final JPanel ltPanel = new JPanel();
    private final JPanel rtPanel = new JPanel();
    private final JPanel ctPanel = new JPanel();
    private final JPanel hdPanel = new JPanel();
    private RefreshListener refresh;

    private final Map<Integer, JComponent> mekPanels = Map.of(Mech.LOC_HEAD, hdPanel, Mech.LOC_LARM, laPanel,
            Mech.LOC_RARM, raPanel, Mech.LOC_CT, ctPanel, Mech.LOC_LT, ltPanel, Mech.LOC_RT, rtPanel,
            Mech.LOC_LLEG, llPanel, Mech.LOC_RLEG, rlPanel, Mech.LOC_CLEG, clPanel);

    public CriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        Box mainPanel = Box.createHorizontalBox();
        Box laAlignPanel = Box.createVerticalBox();
        Box leftAlignPanel = Box.createVerticalBox();
        Box centerAlignPanel = Box.createVerticalBox();
        Box rightAlignPanel = Box.createVerticalBox();
        Box raAlignPanel = Box.createVerticalBox();

        hdPanel.setBorder(CritCellUtil.locationBorderNoLine("Head"));
        ltPanel.setBorder(CritCellUtil.locationBorderNoLine("Left Torso"));
        ctPanel.setBorder(CritCellUtil.locationBorderNoLine("Center Torso"));
        rtPanel.setBorder(CritCellUtil.locationBorderNoLine("Right Torso"));
        clPanel.setBorder(CritCellUtil.locationBorderNoLine("Center Leg"));

        laAlignPanel.add(Box.createVerticalStrut(100));
        laAlignPanel.add(laPanel);
        
        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(ltPanel);
        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(llPanel);
        
        centerAlignPanel.add(hdPanel);
        centerAlignPanel.add(ctPanel);
        centerAlignPanel.add(clPanel);
        centerAlignPanel.add(Box.createVerticalStrut(75));
        
        rightAlignPanel.add(Box.createVerticalStrut(50));
        rightAlignPanel.add(rtPanel);
        rightAlignPanel.add(Box.createVerticalStrut(50));
        rightAlignPanel.add(rlPanel);
        
        raAlignPanel.add(Box.createVerticalStrut(100));
        raAlignPanel.add(raPanel);
        
        mainPanel.add(laAlignPanel);
        mainPanel.add(leftAlignPanel);
        mainPanel.add(centerAlignPanel);
        mainPanel.add(rightAlignPanel);
        mainPanel.add(raAlignPanel);

        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        laPanel.removeAll();
        raPanel.removeAll();
        llPanel.removeAll();
        rlPanel.removeAll();
        clPanel.removeAll();
        ltPanel.removeAll();
        rtPanel.removeAll();
        ctPanel.removeAll();
        hdPanel.removeAll();

        synchronized (getMech()) {
            clPanel.setVisible(getMech() instanceof TripodMech);
            String title = getMech() instanceof QuadMech ? "Front Left Leg" : "Left Arm";
            laPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
            title = getMech() instanceof QuadMech ? "Front Right Leg" : "Right Arm";
            raPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
            title = getMech() instanceof QuadMech ? "Rear Left Leg" : "Left Leg";
            llPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
            title = getMech() instanceof QuadMech ? "Rear Right Leg" : "Right Leg";
            rlPanel.setBorder(CritCellUtil.locationBorderNoLine(title));

            for (int location = 0; location < getMech().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getMech().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getMech().getCritical(location, slot);
                    if (cs == null) {
                        critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMech().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted m = cs.getMount();
                        if (m == null) {
                            // Critical didn't get removed. Remove it now.
                            getMech().setCritical(location, slot, null);
                            critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                        } else {
                            StringBuilder critName = new StringBuilder(m.getName());
                            if (m.isRearMounted()) {
                                critName.append(" (R)");
                            }
                            if (m.isMechTurretMounted()) {
                                critName.append(" (T)");
                            }
                            critNames.add(critName.toString());
                        }
                    }
                }

                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(
                        critNames, eSource, refresh, true);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + "");
                criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITCELL_BORDER_COLOR));
                if (mekPanels.containsKey(location)) {
                    mekPanels.get(location).add(criticalSlotList);
                }
            }
            
            validate();
        }
    }

}