/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.BattleArmor.views;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.Mounted;
import megamek.common.loaders.MtfFile;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.Mech.DropTargetCriticalList;

public class CriticalView extends IView {

    /**
     *
     */
    private static final long serialVersionUID = -6960975031034494605L;

    private JPanel laPanel = new JPanel();
    private JPanel raPanel = new JPanel();
    private JPanel llPanel = new JPanel();
    private JPanel rlPanel = new JPanel();
    private JPanel ltPanel = new JPanel();
    private JPanel rtPanel = new JPanel();
    private JPanel ctPanel = new JPanel();
    private JPanel headPanel = new JPanel();
    private JPanel torsoPanel = new JPanel();
    private JPanel legPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty = false;

    public CriticalView(BattleArmor unit, boolean showEmpty, RefreshListener refresh) {
        super(unit);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setOpaque(false);
        headPanel.setOpaque(false);
        torsoPanel.setOpaque(false);
        legPanel.setOpaque(false);
        ltPanel.setOpaque(false);
        rtPanel.setOpaque(false);
        ctPanel.setOpaque(false);
        raPanel.setOpaque(false);
        laPanel.setOpaque(false);
        rlPanel.setOpaque(false);
        llPanel.setOpaque(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        headPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Head"));
        mainPanel.add(headPanel);

        torsoPanel.add(laPanel);
        ltPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Left Torso"));
        torsoPanel.add(ltPanel);
        ctPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Center Torso"));
        torsoPanel.add(ctPanel);
        rtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Right Torso"));
        torsoPanel.add(rtPanel);
        torsoPanel.add(raPanel);
        mainPanel.add(torsoPanel);

        legPanel.add(llPanel);
        legPanel.add(rlPanel);
        mainPanel.add(legPanel);

        this.add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        laPanel.removeAll();
        raPanel.removeAll();
        llPanel.removeAll();
        rlPanel.removeAll();
        ltPanel.removeAll();
        rtPanel.removeAll();
        ctPanel.removeAll();
        headPanel.removeAll();

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(MtfFile.EMPTY);
                        }
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {

                                m = unit.getEquipment(cs.getIndex());

                                if (m == null) {
                                    unit.setCritical(location, slot, null);
                                    if (showEmpty) {
                                        critNames.add(MtfFile.EMPTY);
                                    }
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
                            if (m.isTurretMounted()) {
                                critName.append(" (T)");
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
                DropTargetCriticalList criticalSlotList = new DropTargetCriticalList(critNames, getBattleArmor(), refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                switch (location) {
                    case BattleArmor.LOC_SQUAD:
                        headPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_1:
                        raPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 1, 1, 0), "Right Arm"));
                        raPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_2:
                        ctPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_3:
                        ltPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_4:
                        rtPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_5:
                        llPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Left Leg"));
                        llPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.LOC_TROOPER_6:
                        rlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Right Leg"));
                        rlPanel.add(criticalSlotList);
                        break;
                }
            }
            ctPanel.repaint();
            raPanel.repaint();
            headPanel.repaint();
            laPanel.repaint();
            ltPanel.repaint();
            rtPanel.repaint();
            llPanel.repaint();
            rlPanel.repaint();
        }
    }

}