/*
 * MegaMekLab - Copyright (C) 2009
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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

package megameklab.com.ui.Vehicle.views;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.CritCellUtil;
import megameklab.com.util.DropTargetCriticalList;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Vector;

/**
 * The Crit Slots view for a Combat Vehicle (including VTOL)
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author Simon (Juliez)
 */
public final class CriticalView extends IView {

    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel frontPanel = new JPanel();
    private final JPanel rearPanel = new JPanel();
    private final JPanel bodyPanel = new JPanel();
    private final JPanel turretPanel = new UIUtil.FixedYPanel();
    private final JPanel dualTurretPanel = new UIUtil.FixedYPanel();
    private final JPanel rotorPanel = new UIUtil.FixedYPanel();

    private final JPanel rearLeftPanel = new JPanel();
    private final JPanel rearRightPanel = new JPanel();

    private final Box middlePanel2 = Box.createHorizontalBox();
    private RefreshListener refresh;

    private final Map<Integer, JComponent> vtolLocations = Map.of(Tank.LOC_FRONT, frontPanel, Tank.LOC_LEFT, leftPanel,
            Tank.LOC_RIGHT, rightPanel, Tank.LOC_BODY, bodyPanel, Tank.LOC_REAR, rearPanel, VTOL.LOC_ROTOR, rotorPanel,
            VTOL.LOC_TURRET, turretPanel);

    private final Map<Integer, JComponent> tankLocations = Map.of(Tank.LOC_FRONT, frontPanel, Tank.LOC_LEFT, leftPanel,
            Tank.LOC_RIGHT, rightPanel, Tank.LOC_BODY, bodyPanel, Tank.LOC_REAR, rearPanel, Tank.LOC_TURRET, turretPanel,
            Tank.LOC_TURRET_2, dualTurretPanel);

    private final Map<Integer, JComponent> superHvyLocations = Map.of(Tank.LOC_FRONT, frontPanel,
            SuperHeavyTank.LOC_FRONTLEFT, leftPanel, SuperHeavyTank.LOC_FRONTRIGHT, rightPanel,
            Tank.LOC_BODY, bodyPanel, SuperHeavyTank.LOC_REAR, rearPanel, SuperHeavyTank.LOC_TURRET, turretPanel,
            SuperHeavyTank.LOC_TURRET_2, dualTurretPanel,
            SuperHeavyTank.LOC_REARLEFT, rearLeftPanel, SuperHeavyTank.LOC_REARRIGHT, rearRightPanel);

    public CriticalView(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;

        frontPanel.setBorder(CritCellUtil.locationBorderNoLine("Front"));
        leftPanel.setBorder(CritCellUtil.locationBorderNoLine("Left Side"));
        bodyPanel.setBorder(CritCellUtil.locationBorderNoLine("Body"));
        rightPanel.setBorder(CritCellUtil.locationBorderNoLine("Right Side"));
        rearLeftPanel.setBorder(CritCellUtil.locationBorderNoLine("Rear Left Side"));
        rearRightPanel.setBorder(CritCellUtil.locationBorderNoLine("Rear Right Side"));
        rearPanel.setBorder(CritCellUtil.locationBorderNoLine("Rear"));
        rotorPanel.setBorder(CritCellUtil.locationBorderNoLine("Rotor"));
        dualTurretPanel.setBorder(CritCellUtil.locationBorderNoLine("Front Turret"));

        Box topPanel = Box.createVerticalBox();
        topPanel.add(frontPanel);

        Box middlePanel = Box.createHorizontalBox();
        middlePanel.add(leftPanel);
        middlePanel.add(bodyPanel);
        middlePanel.add(rightPanel);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        middlePanel2.add(rearLeftPanel);
        middlePanel2.add(new JPanel());
        middlePanel2.add(rearRightPanel);
        middlePanel2.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        Box bottomPanel = Box.createHorizontalBox();
        bottomPanel.add(rearPanel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        Box chassisPanel = Box.createVerticalBox();
        chassisPanel.add(topPanel);
        chassisPanel.add(middlePanel);
        chassisPanel.add(middlePanel2);
        chassisPanel.add(bottomPanel);

        Box fullTurretPanel = Box.createVerticalBox();
        fullTurretPanel.add(dualTurretPanel);
        fullTurretPanel.add(Box.createVerticalStrut(10));
        fullTurretPanel.add(turretPanel);
        fullTurretPanel.add(Box.createVerticalStrut(10));
        fullTurretPanel.add(rotorPanel);

        Box mainPanel = Box.createHorizontalBox();
        mainPanel.add(chassisPanel);
        mainPanel.add(fullTurretPanel);
        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        leftPanel.removeAll();
        rightPanel.removeAll();
        bodyPanel.removeAll();
        frontPanel.removeAll();
        rearPanel.removeAll();
        turretPanel.removeAll();
        dualTurretPanel.removeAll();
        rearLeftPanel.removeAll();
        rearRightPanel.removeAll();
        rotorPanel.removeAll();

        rotorPanel.setVisible(isVTOL());
        turretPanel.setVisible(!getTank().hasNoTurret());
        dualTurretPanel.setVisible(!getTank().hasNoDualTurret());
        middlePanel2.setVisible(getTank().isSuperHeavy() && !isVTOL());
        if (getTank().hasNoDualTurret()) {
            turretPanel.setBorder(CritCellUtil.locationBorderNoLine("Turret"));
        } else {
            turretPanel.setBorder(CritCellUtil.locationBorderNoLine("Rear Turret"));
        }

        synchronized (getTank()) {
            for (int location = 0; location < getTank().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getTank().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getTank().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    }
                    if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMech().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted m = cs.getMount();
                        // Critical didn't get removed. Remove it now.
                        if (m == null) {
                            getTank().setCritical(location, slot, null);
                            continue;
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
                        critNames.add(critName.toString());
                    }
                }

                if (critNames.size() == 0) {
                    critNames.add(CritCellUtil.EMPTYCELLTEXT);
                }
                DropTargetCriticalList<String> criticalSlotList =
                        new DropTargetCriticalList<>(critNames, eSource, refresh, true);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + "");
                criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (isVTOL()) {
                    if (vtolLocations.containsKey(location)) {
                        vtolLocations.get(location).add(criticalSlotList);
                    }
                } else if (getTank().isSuperHeavy()) {
                    if (superHvyLocations.containsKey(location)) {
                        superHvyLocations.get(location).add(criticalSlotList);
                    }
                } else {
                    if (tankLocations.containsKey(location)) {
                        tankLocations.get(location).add(criticalSlotList);
                    }
                }
            }

            validate();
        }
    }
}