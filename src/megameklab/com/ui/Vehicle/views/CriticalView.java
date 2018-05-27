/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.CriticalSlot;
import megamek.common.Mounted;
import megamek.common.SuperHeavyTank;
import megamek.common.Tank;
import megamek.common.VTOL;
import megamek.common.loaders.MtfFile;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.DropTargetCriticalList;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

public class CriticalView extends IView {

    /**
     *
     */
    private static final long serialVersionUID = -6960975031034494605L;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel frontPanel = new JPanel();
    private JPanel rearPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    private JPanel turretPanel = new JPanel();
    private JPanel dualTurretPanel = new JPanel();

    private JPanel rearLeftPanel = new JPanel();
    private JPanel rearRightPanel = new JPanel();

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel middlePanel2 = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel fullTurretPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty = false;

    public CriticalView(EntitySource eSource, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel2.setLayout(new BoxLayout(middlePanel2, BoxLayout.X_AXIS));
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

        middlePanel2.setVisible(getTank().isSuperHeavy() && !(getTank() instanceof VTOL));

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

        if (getTank() instanceof VTOL) {
            if (getTank().hasNoTurret()){
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
        } else if (!getTank().hasNoDualTurret()) {
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

        synchronized (getTank()) {
            for (int location = 0; location < getTank().locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < getTank().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getTank().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMech().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {

                                m = cs.getMount();

                                if (m == null) {
                                    getTank().setCritical(location, slot, null);
                                    continue;
                                }
                                cs.setMount(m);
                            }
                            StringBuffer critName = new StringBuffer(m.getName());
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
                DropTargetCriticalList<String> criticalSlotList = null;

                DropTargetCriticalList<String> dropTargetCriticalList = new DropTargetCriticalList<String>(critNames, eSource, refresh, showEmpty);
                criticalSlotList = dropTargetCriticalList;
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                if (!(getTank()).isSuperHeavy()) {
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
                } else if (getTank() instanceof VTOL) {
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
            middlePanel2.setVisible(getTank().isSuperHeavy() && !(getTank() instanceof VTOL));
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