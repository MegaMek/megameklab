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
package megameklab.ui.mek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.CriticalSlot;
import megamek.common.Mek;
import megamek.common.Mounted;
import megamek.common.TripodMek;
import megamek.common.annotations.Nullable;
import megameklab.ui.EntitySource;
import megameklab.ui.util.BAASBMDropTargetCriticalList;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

/**
 * The Crit Slots view for a Mek (including Quad and Tripod)
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author Simon (Juliez)
 */
public class BMCriticalView extends IView {

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

    private final Map<Integer, JComponent> mekPanels = Map.of(Mek.LOC_HEAD, hdPanel, Mek.LOC_LARM, laPanel,
            Mek.LOC_RARM, raPanel, Mek.LOC_CT, ctPanel, Mek.LOC_LT, ltPanel, Mek.LOC_RT, rtPanel,
            Mek.LOC_LLEG, llPanel, Mek.LOC_RLEG, rlPanel, Mek.LOC_CLEG, clPanel);

    private final List<BAASBMDropTargetCriticalList<String>> currentCritBlocks = new ArrayList<>();

    public BMCriticalView(EntitySource eSource, RefreshListener refresh) {
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
        currentCritBlocks.clear();
        laPanel.removeAll();
        raPanel.removeAll();
        llPanel.removeAll();
        rlPanel.removeAll();
        clPanel.removeAll();
        ltPanel.removeAll();
        rtPanel.removeAll();
        ctPanel.removeAll();
        hdPanel.removeAll();

        synchronized (getMek()) {
            clPanel.setVisible(getMek() instanceof TripodMek);
            setTitles();

            for (int location = 0; location < getMek().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getMek().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getMek().getCritical(location, slot);
                    if (cs == null) {
                        critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> m = cs.getMount();
                        if (m == null) {
                            // Critical didn't get removed. Remove it now.
                            getMek().setCritical(location, slot, null);
                            critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                        } else {
                            StringBuilder critName = new StringBuilder(m.getName());
                            if (m.isRearMounted()) {
                                critName.append(" (R)");
                            }
                            if (m.isMekTurretMounted()) {
                                critName.append(" (T)");
                            }
                            critNames.add(critName.toString());
                        }
                    }
                }

                BAASBMDropTargetCriticalList<String> criticalSlotList = new BAASBMDropTargetCriticalList<>(
                        critNames, eSource, refresh, true, this);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + "");
                criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITCELL_BORDER_COLOR));
                criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITCELL_WIDTH_STRING);
                if (mekPanels.containsKey(location)) {
                    mekPanels.get(location).add(criticalSlotList);
                    currentCritBlocks.add(criticalSlotList);
                }
            }

            validate();
        }
    }

    private void setTitles() {
        String title = getMek().getLocationName(Mek.LOC_LARM) + caseSuffix(Mek.LOC_LARM);
        laPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RARM) + caseSuffix(Mek.LOC_RARM);
        raPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LLEG) + caseSuffix(Mek.LOC_LLEG);
        llPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RLEG) + caseSuffix(Mek.LOC_RLEG);
        rlPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CLEG) + caseSuffix(Mek.LOC_CLEG);
        clPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LT) + caseSuffix(Mek.LOC_LT);
        ltPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RT) + caseSuffix(Mek.LOC_RT);
        rtPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CT) + caseSuffix(Mek.LOC_CT);
        ctPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_HEAD) + caseSuffix(Mek.LOC_HEAD);
        hdPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
    }

    private String caseSuffix(int location) {
        if (getMek().hasCASEII(location)) {
            return " (CASE II)";
        } else if (getMek().locationHasCase(location)) {
            return " (CASE)";
        } else {
            return "";
        }
    }

    /**
     * Darkens all crit blocks that are unavailable to the given equipment, e.g. all but Torsos for CASE.
     */
    public void markUnavailableLocations(@Nullable Mounted<?> equipment) {
        if (equipment != null) {
            currentCritBlocks.stream()
                    .filter(b -> !UnitUtil.isValidLocation(getMek(), equipment.getType(), b.getCritLocation()))
                    .forEach(b -> b.setDarkened(true));
        }
    }

    /** Resets all crit blocks to not darkened. */
    public void unMarkAllLocations() {
        currentCritBlocks.forEach(b -> b.setDarkened(false));
    }

}
