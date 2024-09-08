/*
 * Copyright (c) 2019-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.ui.supportVehicle;

import megamek.client.ui.swing.util.UIUtil.FixedYPanel;
import megamek.common.*;
import megamek.common.annotations.Nullable;
import megameklab.ui.EntitySource;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.DropTargetCriticalList;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Vector;

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

    private final Map<Integer, JComponent> aeroLocations = Map.of(FixedWingSupport.LOC_NOSE, frontPanel, FixedWingSupport.LOC_LWING, leftPanel,
            FixedWingSupport.LOC_RWING, rightPanel, FixedWingSupport.LOC_BODY, bodyPanel, FixedWingSupport.LOC_AFT, rearPanel);

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
            LogManager.getLogger().info(getEntity().locations());
            for (int location = 0; location < getEntity().locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getEntity().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getEntity().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    }
                    if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMek().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted m = cs.getMount();
                        // Critical didn't get removed. Remove it now.
                        if (m == null) {
                            getEntity().setCritical(location, slot, null);
                            continue;
                        }
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
                        critNames.add(critName.toString());
                    }
                }

                if (critNames.isEmpty()) {
                    critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
                }
                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(critNames, eSource, refresh, true);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + "");
                criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (panelForLocation(location) != null) {
                    panelForLocation(location).add(criticalSlotList);
                }
            }
            validate();
        }
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
