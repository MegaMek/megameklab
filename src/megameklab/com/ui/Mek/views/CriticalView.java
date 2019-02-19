/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.CriticalSlot;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.QuadMech;
import megamek.common.loaders.MtfFile;
import megameklab.com.ui.EntitySource;
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
    private JPanel clPanel = new JPanel();
    private JPanel ltPanel = new JPanel();
    private JPanel rtPanel = new JPanel();
    private JPanel ctPanel = new JPanel();
    private JPanel headPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty = false;

    public CriticalView(EntitySource eSource, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();
        JPanel laAlignPanel = new JPanel();
        JPanel leftAlignPanel = new JPanel();
        JPanel centerAlignPanel = new JPanel();
        JPanel rightAlignPanel = new JPanel();
        JPanel raAlignPanel = new JPanel();

        mainPanel.setOpaque(false);
        headPanel.setOpaque(false);
        ltPanel.setOpaque(false);
        rtPanel.setOpaque(false);
        ctPanel.setOpaque(false);
        raPanel.setOpaque(false);
        laPanel.setOpaque(false);
        rlPanel.setOpaque(false);
        clPanel.setOpaque(false);
        llPanel.setOpaque(false);

        // Set panel layouts
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        laAlignPanel.setLayout(new BoxLayout(laAlignPanel, BoxLayout.Y_AXIS));
        leftAlignPanel.setLayout(new BoxLayout(leftAlignPanel, BoxLayout.Y_AXIS));
        centerAlignPanel.setLayout(new BoxLayout(centerAlignPanel, BoxLayout.Y_AXIS));
        rightAlignPanel.setLayout(new BoxLayout(rightAlignPanel, BoxLayout.Y_AXIS));
        raAlignPanel.setLayout(new BoxLayout(raAlignPanel, BoxLayout.Y_AXIS));

        // Set Borders, used for titles
        headPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Head",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        ltPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Left Torso",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        ctPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Center Torso",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
        rtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Right Torso",
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));

        laAlignPanel.add(Box.createVerticalStrut(100));
        laAlignPanel.add(laPanel);

        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(ltPanel);
        leftAlignPanel.add(Box.createVerticalStrut(50));
        leftAlignPanel.add(llPanel);

        centerAlignPanel.add(headPanel);
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
        clPanel.removeAll();
        ltPanel.removeAll();
        rtPanel.removeAll();
        ctPanel.removeAll();
        headPanel.removeAll();
        clPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

        // the CritListCellRenderer has a default size of 110x15 and
        // the border has a width of 1 so this should make each one the right
        // size
        Dimension size = new Dimension(112, 182);
        Dimension legSize = new Dimension(112, 92);

        synchronized (getMech()) {
            for (int location = 0; location < getMech().locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < getMech().getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = getMech().getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(MtfFile.EMPTY);
                        }
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(getMech().getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {

                                m = cs.getMount();

                                if (m == null) {
                                    getMech().setCritical(location, slot, null);
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
                            if (m.isMechTurretMounted()) {
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
                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<String>(critNames, eSource,
                        refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                switch (location) {
                case Mech.LOC_HEAD:
                    criticalSlotList.setSize(legSize);
                    criticalSlotList.setPreferredSize(legSize);
                    criticalSlotList.setMaximumSize(legSize);
                    headPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_LARM:
                    if (getMech() instanceof QuadMech) {
                        laPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Front Left Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        criticalSlotList.setSize(legSize);
                        criticalSlotList.setPreferredSize(legSize);
                        criticalSlotList.setMaximumSize(legSize);
                    } else {
                        laPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Left Arm", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        criticalSlotList.setSize(size);
                        criticalSlotList.setPreferredSize(size);
                        criticalSlotList.setMaximumSize(size);
                    }
                    laPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_RARM:
                    if (getMech() instanceof QuadMech) {
                        raPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Front Right Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        criticalSlotList.setSize(legSize);
                        criticalSlotList.setPreferredSize(legSize);
                        criticalSlotList.setMaximumSize(legSize);
                    } else {
                        raPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Right Arm", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                        criticalSlotList.setSize(size);
                        criticalSlotList.setPreferredSize(size);
                        criticalSlotList.setMaximumSize(size);
                    }

                    raPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_CT:
                    criticalSlotList.setSize(size);
                    criticalSlotList.setPreferredSize(size);
                    criticalSlotList.setMaximumSize(size);
                    ctPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_LT:
                    criticalSlotList.setSize(size);
                    criticalSlotList.setPreferredSize(size);
                    criticalSlotList.setMaximumSize(size);
                    ltPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_RT:
                    criticalSlotList.setSize(size);
                    criticalSlotList.setPreferredSize(size);
                    criticalSlotList.setMaximumSize(size);
                    rtPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_LLEG:
                    if (getMech() instanceof QuadMech) {
                        llPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Rear Left Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                    } else {
                        llPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Left Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                    }
                    criticalSlotList.setSize(legSize);
                    criticalSlotList.setPreferredSize(legSize);
                    criticalSlotList.setMaximumSize(legSize);
                    llPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_RLEG:
                    if (getMech() instanceof QuadMech) {
                        rlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Rear Right Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                    } else {
                        rlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                                "Right Leg", TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                    }
                    criticalSlotList.setSize(legSize);
                    criticalSlotList.setPreferredSize(legSize);
                    criticalSlotList.setMaximumSize(legSize);
                    rlPanel.add(criticalSlotList);
                    break;
                case Mech.LOC_CLEG:
                    clPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Center Leg",
                            TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));
                    criticalSlotList.setSize(legSize);
                    criticalSlotList.setPreferredSize(legSize);
                    criticalSlotList.setMaximumSize(legSize);
                    clPanel.add(criticalSlotList);
                    break;
                }
            }

            ctPanel.invalidate();
            raPanel.invalidate();
            headPanel.invalidate();
            laPanel.invalidate();
            ltPanel.invalidate();
            rtPanel.invalidate();
            llPanel.invalidate();
            rlPanel.invalidate();
            clPanel.invalidate();

            ctPanel.repaint();
            raPanel.repaint();
            headPanel.repaint();
            laPanel.repaint();
            ltPanel.repaint();
            rtPanel.repaint();
            llPanel.repaint();
            rlPanel.repaint();
            clPanel.invalidate();
        }
    }

}
