/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import java.awt.Color;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.client.ui.util.UIUtil.FixedYPanel;
import megamek.common.CriticalSlot;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.Mounted;
import megamek.common.units.FixedWingSupport;
import megamek.common.units.SuperHeavyTank;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.DropTargetCriticalList;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

/**
 * The Crit Slots view for a Support Vehicle (all motive types)
 *
 * @author neoancient
 * @author Simon (Juliez)
 */
public class SVCriticalView extends IView {

    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JPanel frontPanel = new JPanel();
    private final JPanel rearPanel = new JPanel();
    private final JPanel bodyPanel = new JPanel();
    private final JPanel rearLeftPanel = new JPanel();
    private final JPanel rearRightPanel = new JPanel();
    private final JPanel middlePanel2 = new JPanel();
    private final JPanel turretPanel = new FixedYPanel();
    private final JPanel dualTurretPanel = new FixedYPanel();
    private final JPanel rotorPanel = new FixedYPanel();
    private RefreshListener refresh;

    private final Map<Integer, JComponent> aeroLocations = Map.of(FixedWingSupport.LOC_NOSE, frontPanel,
          FixedWingSupport.LOC_LEFT_WING, leftPanel,
          FixedWingSupport.LOC_RIGHT_WING, rightPanel, FixedWingSupport.LOC_BODY, bodyPanel, FixedWingSupport.LOC_AFT,
          rearPanel);

    private final Map<Integer, JComponent> vtolLocations = Map.of(Tank.LOC_FRONT, frontPanel, Tank.LOC_LEFT, leftPanel,
          Tank.LOC_RIGHT, rightPanel, Tank.LOC_BODY, bodyPanel, Tank.LOC_REAR, rearPanel, VTOL.LOC_ROTOR, rotorPanel,
          VTOL.LOC_TURRET, turretPanel);

    private final Map<Integer, JComponent> tankLocations = Map.of(Tank.LOC_FRONT, frontPanel, Tank.LOC_LEFT, leftPanel,
          Tank.LOC_RIGHT, rightPanel, Tank.LOC_BODY, bodyPanel, Tank.LOC_REAR, rearPanel, Tank.LOC_TURRET,
          turretPanel,
          Tank.LOC_TURRET_2, dualTurretPanel);

    private final Map<Integer, JComponent> superHvyLocations = Map.of(Tank.LOC_FRONT, frontPanel,
          SuperHeavyTank.LOC_FRONT_LEFT, leftPanel, SuperHeavyTank.LOC_FRONT_RIGHT, rightPanel,
          Tank.LOC_BODY, bodyPanel, SuperHeavyTank.LOC_REAR, rearPanel, SuperHeavyTank.LOC_TURRET, turretPanel,
          SuperHeavyTank.LOC_TURRET_2, dualTurretPanel,
          SuperHeavyTank.LOC_REAR_LEFT, rearLeftPanel, SuperHeavyTank.LOC_REAR_RIGHT, rearRightPanel);

    SVCriticalView(EntitySource eSource, RefreshListener refresh) {
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
        turretPanel.setVisible((getEntity() instanceof Tank) && !getTank().hasNoTurret());
        dualTurretPanel.setVisible((getEntity() instanceof Tank) && !getTank().hasNoDualTurret());
        middlePanel2.setVisible((getEntity() instanceof Tank) && getTank().isSuperHeavy() && !isVTOL());
        if ((getEntity() instanceof Tank) && getTank().hasNoDualTurret()) {
            turretPanel.setBorder(CritCellUtil.locationBorderNoLine("Turret"));
        } else {
            turretPanel.setBorder(CritCellUtil.locationBorderNoLine("Rear Turret"));
        }

        synchronized (getEntity()) {
            for (int location = 0; location < getEntity().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getEntity().getNumberOfCriticalSlots(location); slot++) {
                    CriticalSlot cs = getEntity().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    }
                    if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> mounted = cs.getMount();
                        // Critical didn't get removed. Remove it now.
                        if (mounted == null) {
                            getEntity().setCritical(location, slot, null);
                            continue;
                        }
                        StringBuilder critName = getCritName(mounted);
                        critNames.add(critName.toString());
                    }
                }

                if (critNames.isEmpty()) {
                    critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                }
                DropTargetCriticalList<String> criticalSlotList = getCriticalSlotList(critNames,
                      location);
                if (panelForLocation(location) != null) {
                    panelForLocation(location).add(criticalSlotList);
                }
            }
            validate();
        }
    }

    private DropTargetCriticalList<String> getCriticalSlotList(Vector<String> critNames, int location) {
        DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(critNames, eSource,
              refresh, true);
        criticalSlotList.setVisibleRowCount(critNames.size());
        criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalSlotList.setName(location + "");
        criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
        return criticalSlotList;
    }

    private static StringBuilder getCritName(Mounted<?> m) {
        StringBuilder critName = new StringBuilder(m.getName());
        if (critName.length() > 25) {
            critName.setLength(25);
            critName.append("...");
        }
        if (m.isRearMounted()) {
            critName.append(" (R)");
        }
        if (m.isSponsonTurretMounted()) {
            critName.append(" (ST)");
        }
        if (m.isPintleTurretMounted()) {
            critName.append(" (PT)");
        }
        return critName;
    }

    private @Nullable JComponent panelForLocation(int location) {
        if (getEntity().isAero()) {
            return aeroLocations.get(location);
        } else if (isVTOL()) {
            return vtolLocations.get(location);
        } else if (getTank().isSuperHeavy()) {
            return superHvyLocations.get(location);
        } else {
            return tankLocations.get(location);
        }
    }
}
