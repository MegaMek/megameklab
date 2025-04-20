/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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
 */

package megameklab.ui.combatVehicle;

import java.awt.Color;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.CriticalSlot;
import megamek.common.Mounted;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.VTOL;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.DropTargetCriticalList;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

/**
 * The Crit Slots view for a Combat Vehicle (including VTOL)
 * <p>
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * @author arlith
 * @author Simon (Juliez)
 */
public final class CVCriticalView extends IView {

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

    private final Map<Integer, JComponent> vtolLocations = Map.of(Tank.LOC_FRONT,
          frontPanel,
          Tank.LOC_LEFT,
          leftPanel,
          Tank.LOC_RIGHT,
          rightPanel,
          Tank.LOC_BODY,
          bodyPanel,
          Tank.LOC_REAR,
          rearPanel,
          VTOL.LOC_ROTOR,
          rotorPanel,
          VTOL.LOC_TURRET,
          turretPanel);

    private final Map<Integer, JComponent> tankLocations = Map.of(Tank.LOC_FRONT,
          frontPanel,
          Tank.LOC_LEFT,
          leftPanel,
          Tank.LOC_RIGHT,
          rightPanel,
          Tank.LOC_BODY,
          bodyPanel,
          Tank.LOC_REAR,
          rearPanel,
          Tank.LOC_TURRET,
          turretPanel,
          Tank.LOC_TURRET_2,
          dualTurretPanel);

    private final Map<Integer, JComponent> superHvyLocations = Map.of(Tank.LOC_FRONT,
          frontPanel,
          SuperHeavyTank.LOC_FRONTLEFT,
          leftPanel,
          SuperHeavyTank.LOC_FRONTRIGHT,
          rightPanel,
          Tank.LOC_BODY,
          bodyPanel,
          SuperHeavyTank.LOC_REAR,
          rearPanel,
          SuperHeavyTank.LOC_TURRET,
          turretPanel,
          SuperHeavyTank.LOC_TURRET_2,
          dualTurretPanel,
          SuperHeavyTank.LOC_REARLEFT,
          rearLeftPanel,
          SuperHeavyTank.LOC_REARRIGHT,
          rearRightPanel);

    public CVCriticalView(EntitySource eSource, RefreshListener refresh) {
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
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> m = cs.getMount();
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
                
                DropTargetCriticalList<String> criticalSlotList = getStringDropTargetCriticalList(critNames, location);
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

    private DropTargetCriticalList<String> getStringDropTargetCriticalList(Vector<String> critNames, int location) {
        if (critNames.isEmpty()) {
            critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
        }

        DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(critNames,
              eSource,
              refresh,
              true);
        criticalSlotList.setVisibleRowCount(critNames.size());
        criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalSlotList.setName(location + "");
        criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITCELL_WIDTH_STRING);
        return criticalSlotList;
    }
}
