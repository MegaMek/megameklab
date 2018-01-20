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

package megameklab.com.ui.Aero.views;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.Aero;
import megamek.common.CriticalSlot;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.MtfFile;
import megamek.common.verifier.TestAero;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.Mech.DropTargetCriticalList;

public class CriticalView extends IView {

    /**
     *
     */
    private static final long serialVersionUID = -6960975031034494605L;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel nosePanel = new JPanel();
    private JPanel aftPanel = new JPanel();

    private JLabel noseSpace = new JLabel("",JLabel.LEFT);
    private JLabel leftSpace = new JLabel("",JLabel.LEFT);
    private JLabel rightSpace = new JLabel("",JLabel.LEFT);
    private JLabel aftSpace = new JLabel("",JLabel.LEFT);


    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private RefreshListener refresh;

    private boolean showEmpty = false;

    public CriticalView(EntitySource eSource, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalStrut(50));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        leftSpace.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        rightSpace.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        noseSpace.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        aftSpace.setAlignmentX(JLabel.CENTER_ALIGNMENT);


        topPanel.add(nosePanel);
        nosePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Nose", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        nosePanel.setLayout(new BoxLayout(nosePanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);

        middlePanel.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Left Wing"));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        middlePanel.add(rightPanel);
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Right Wing"));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        mainPanel.add(middlePanel);

        aftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Aft", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));
        aftPanel.setLayout(new BoxLayout(aftPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(aftPanel);
        mainPanel.add(bottomPanel);

        this.add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        leftPanel.removeAll();
        rightPanel.removeAll();
        nosePanel.removeAll();
        aftPanel.removeAll();

        int[] availSpace = TestAero.availableSpace(getAero());

        if (availSpace == null){
            // Shouldn't happen, since we only allow valid armor types to be
            //  selected...
            System.err.println("Error in CriticalView: Invalid armor type!");
            return;
        }

        synchronized (getAero()) {
            // Aeros have 5 locs, the 5th is "wings" which should be ignored
            int numLocs = getAero().locations() - 1;
            for (int location = 0; location < numLocs; location++) {
                Vector<String> critNames = new Vector<String>(1, 1);
                int numWeapons = 0;
                for (int slot = 0; slot < getAero().getNumberOfCriticals(location);
                        slot++) {
                    CriticalSlot cs = getAero().getCritical(location, slot);
                    if (cs == null) {
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        // Aeros shouldn't have system types
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {

                                m = cs.getMount();

                                if (m == null) {
                                    getAero().setCritical(location, slot, null);
                                    continue;
                                }
                                cs.setMount(m);
                            }
                            // Ignore weapon groups
                            if (m.isWeaponGroup()){
                                continue;
                            }
                            if (m.getType() instanceof WeaponType){
                                numWeapons++;
                            }
                            StringBuffer critName =
                                    new StringBuffer(m.getName());
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
                            critName.append(":" + slot);
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

                DropTargetCriticalList<String> dropTargetCriticalList = new DropTargetCriticalList<String>(
                        critNames, eSource, refresh, showEmpty);
                criticalSlotList = dropTargetCriticalList;
                criticalSlotList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(
                        ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(
                        Color.WHITE.brighter(), Color.BLACK.darker()));

                switch (location) {
                    case Aero.LOC_NOSE:
                        nosePanel.add(criticalSlotList);
                        noseSpace.setText("Weapons: " +
                                numWeapons + "/" + availSpace[location]);
                        break;
                    case Aero.LOC_LWING:
                        leftPanel.add(criticalSlotList);
                        leftSpace.setText("Weapons: " +
                                numWeapons + "/" + availSpace[location]);
                        break;
                    case Aero.LOC_RWING:
                        rightPanel.add(criticalSlotList);
                        rightSpace.setText("Weapons: " +
                                numWeapons + "/" + availSpace[location]);
                        break;
                    case Aero.LOC_AFT:
                        aftPanel.add(criticalSlotList);
                        aftSpace.setText("Weapons: " +
                                numWeapons + "/" + availSpace[location]);
                        break;
                }


            }

            leftPanel.add(leftSpace);
            leftPanel.add(Box.createVerticalStrut(8));
            rightPanel.add(rightSpace);
            rightPanel.add(Box.createVerticalStrut(8));
            nosePanel.add(noseSpace);
            nosePanel.add(Box.createVerticalStrut(8));
            aftPanel.add(aftSpace);
            aftPanel.add(Box.createVerticalStrut(8));

            nosePanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
            aftPanel.repaint();

            nosePanel.invalidate();
            leftPanel.invalidate();
            rightPanel.invalidate();
            aftPanel.invalidate();
        }
    }

}
