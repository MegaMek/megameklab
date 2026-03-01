/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
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
import megamek.common.annotations.Nullable;
import megamek.common.equipment.Mounted;
import megamek.common.units.Mek;
import megamek.common.units.TripodMek;
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

    private final Map<Integer, JComponent> mekPanels = Map.of(Mek.LOC_HEAD,
          hdPanel,
          Mek.LOC_LEFT_ARM,
          laPanel,
          Mek.LOC_RIGHT_ARM,
          raPanel,
          Mek.LOC_CENTER_TORSO,
          ctPanel,
          Mek.LOC_LEFT_TORSO,
          ltPanel,
          Mek.LOC_RIGHT_TORSO,
          rtPanel,
          Mek.LOC_LEFT_LEG,
          llPanel,
          Mek.LOC_RIGHT_LEG,
          rlPanel,
          Mek.LOC_CENTER_LEG,
          clPanel);

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

                for (int slot = 0; slot < getMek().getNumberOfCriticalSlots(location); slot++) {
                    CriticalSlot cs = getMek().getCritical(location, slot);
                    if (cs == null) {
                        critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> m = cs.getMount();
                        if (m == null) {
                            // Critical didn't get removed. Remove it now.
                            getMek().setCritical(location, slot, null);
                            critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
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

                BAASBMDropTargetCriticalList<String> criticalSlotList = getCriticalSlotList(
                      critNames,
                      location);
                if (mekPanels.containsKey(location)) {
                    mekPanels.get(location).add(criticalSlotList);
                    currentCritBlocks.add(criticalSlotList);
                }
            }

            validate();
        }
    }

    private BAASBMDropTargetCriticalList<String> getCriticalSlotList(Vector<String> critNames,
          int location) {
        BAASBMDropTargetCriticalList<String> criticalSlotList = new BAASBMDropTargetCriticalList<>(
              critNames, eSource, refresh, true, this);
        criticalSlotList.setVisibleRowCount(critNames.size());
        criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalSlotList.setName(location + "");
        criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
        return criticalSlotList;
    }

    private void setTitles() {
        String title = getMek().getLocationName(Mek.LOC_LEFT_ARM) + caseSuffix(Mek.LOC_LEFT_ARM);
        laPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_ARM) + caseSuffix(Mek.LOC_RIGHT_ARM);
        raPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LEFT_LEG) + caseSuffix(Mek.LOC_LEFT_LEG);
        llPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_LEG) + caseSuffix(Mek.LOC_RIGHT_LEG);
        rlPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CENTER_LEG) + caseSuffix(Mek.LOC_CENTER_LEG);
        clPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_LEFT_TORSO) + caseSuffix(Mek.LOC_LEFT_TORSO);
        ltPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_RIGHT_TORSO) + caseSuffix(Mek.LOC_RIGHT_TORSO);
        rtPanel.setBorder(CritCellUtil.locationBorderNoLine(title));
        title = getMek().getLocationName(Mek.LOC_CENTER_TORSO) + caseSuffix(Mek.LOC_CENTER_TORSO);
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
     * Darkens all crit blocks other than the one for the given location
     */
    public void markUnavailableLocations(int location) {
        currentCritBlocks.stream()
              .filter(b -> b.getCritLocation() != location)
              .forEach(b -> b.setDarkened(true));
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
