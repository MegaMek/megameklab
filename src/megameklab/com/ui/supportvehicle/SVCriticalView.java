/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
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
package megameklab.com.ui.supportvehicle;

import megamek.common.*;
import megamek.common.loaders.MtfFile;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.DropTargetCriticalList;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

/**
 *
 */
public class SVCriticalView extends IView {

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel frontPanel = new JPanel();
    private JPanel rearPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    private JPanel turretPanel = new JPanel();
    private JPanel dualTurretPanel = new JPanel();

    private JPanel rearLeftPanel = new JPanel();
    private JPanel rearRightPanel = new JPanel();

    private JPanel middlePanel2 = new JPanel();
    private JPanel fullTurretPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty;

    SVCriticalView(EntitySource eSource, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel2.setLayout(new BoxLayout(middlePanel2, BoxLayout.X_AXIS));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        fullTurretPanel.setLayout(new BoxLayout(fullTurretPanel, BoxLayout.Y_AXIS));

        topPanel.add(frontPanel);
        topPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Front", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        mainPanel.add(topPanel);

        middlePanel.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Left Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        middlePanel.add(bodyPanel);
        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Body", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        middlePanel.add(rightPanel);
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Right Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        mainPanel.add(middlePanel);

        middlePanel2.add(rearLeftPanel);
        rearLeftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Rear Left Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        middlePanel2.add(new JPanel());
        middlePanel2.add(rearRightPanel);
        rearRightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Rear Right Side",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        mainPanel.add(middlePanel2);

        middlePanel2.setVisible(getEntity().isSuperHeavy() && !(getEntity() instanceof VTOL));

        rearPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Rear", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        bottomPanel.add(rearPanel);
        mainPanel.add(bottomPanel);

        this.add(mainPanel);

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
        fullTurretPanel.removeAll();
        rearLeftPanel.removeAll();
        rearRightPanel.removeAll();
        this.remove(fullTurretPanel);

        if (getEntity() instanceof VTOL) {
            if (getVTOL().hasNoTurret()){
                turretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Rotor",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(turretPanel);
                this.add(fullTurretPanel);
            } else {
                dualTurretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Turret",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(dualTurretPanel);
                turretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Rotor",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(turretPanel);
                this.add(fullTurretPanel);
            }
        } else if (getEntity() instanceof Tank) {
            if (!getTank().hasNoDualTurret()) {
                dualTurretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Front Turret",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(dualTurretPanel);
                turretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Rear Turret",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(turretPanel);
                this.add(fullTurretPanel);
            } else if (!getTank().hasNoTurret()) {
                turretPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Turret",
                        TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                fullTurretPanel.add(turretPanel);
                this.add(fullTurretPanel);
            }
        }

        synchronized (getEntity()) {
            for (int location = 0; location < getEntity().locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<>(1, 1);

                for (int slot = 0; slot < getEntity().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getEntity().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMech().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
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

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                if (critNames.size() == 0) {
                    critNames.add(MtfFile.EMPTY);
                }
                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(critNames, eSource, refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                if (getEntity().isAero()) {
                    switch (location) {
                        case FixedWingSupport.LOC_NOSE:
                            frontPanel.add(criticalSlotList);
                            break;
                        case FixedWingSupport.LOC_LWING:
                            leftPanel.add(criticalSlotList);
                            break;
                        case FixedWingSupport.LOC_RWING:
                            rightPanel.add(criticalSlotList);
                            break;
                        case FixedWingSupport.LOC_BODY:
                            bodyPanel.add(criticalSlotList);
                            break;
                        case FixedWingSupport.LOC_AFT:
                            rearPanel.add(criticalSlotList);
                            break;
                    }
                } else if (!(getEntity()).isSuperHeavy()) {
                    switch (location) {
                        case Tank.LOC_FRONT:
                            frontPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_LEFT:
                            leftPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_RIGHT:
                            rightPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_BODY:
                            bodyPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_REAR:
                            rearPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_TURRET:
                            turretPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_TURRET_2:
                            dualTurretPanel.add(criticalSlotList);
                            break;
                    }
                } else if (getEntity() instanceof VTOL) {
                    switch (location) {
                        case Tank.LOC_FRONT:
                            frontPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_LEFT:
                            leftPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_RIGHT:
                            rightPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_BODY:
                            bodyPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_REAR:
                            rearPanel.add(criticalSlotList);
                            break;
                        case VTOL.LOC_ROTOR:
                            turretPanel.add(criticalSlotList);
                            break;
                        case VTOL.LOC_TURRET:
                            dualTurretPanel.add(criticalSlotList);
                            break;
                    }
                } else {
                    switch (location) {
                        case Tank.LOC_FRONT:
                            frontPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_FRONTLEFT:
                            leftPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_FRONTRIGHT:
                            rightPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_REARLEFT:
                            rearLeftPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_REARRIGHT:
                            rearRightPanel.add(criticalSlotList);
                            break;
                        case Tank.LOC_BODY:
                            bodyPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_REAR:
                            rearPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_TURRET:
                            turretPanel.add(criticalSlotList);
                            break;
                        case SuperHeavyTank.LOC_TURRET_2:
                            dualTurretPanel.add(criticalSlotList);
                            break;
                    }
                }
            }
            middlePanel2.setVisible(getEntity().isSuperHeavy() && !(getEntity() instanceof VTOL));
            frontPanel.repaint();
            bodyPanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
            rearLeftPanel.repaint();
            rearRightPanel.repaint();
            rearPanel.repaint();
            turretPanel.repaint();
            dualTurretPanel.repaint();

        }
    }
}