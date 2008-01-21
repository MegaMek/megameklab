/*
 * MekWars - Copyright (C) 2008 
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

package ui.views;

import java.awt.Font;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.CriticalSlot;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.loaders.MtfFile;

public class CriticalView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6960975031034494605L;

    public CriticalView(Mech unit) {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headPanel = new JPanel();
        JPanel torsoPanel = new JPanel();
        JPanel legPanel = new JPanel();

        JPanel laPanel = new JPanel();
        JPanel raPanel = new JPanel();
        JPanel llPanel = new JPanel();
        JPanel rlPanel = new JPanel();
        JPanel ltPanel = new JPanel();
        JPanel rtPanel = new JPanel();
        JPanel ctPanel = new JPanel();

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
                    if (cs == null) {
                        critNames.add(MtfFile.EMPTY);
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(unit.getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted m = unit.getEquipment(cs.getIndex());
                        critNames.add(m.getDesc());
                    }
                }
                JList CriticalSlotList = new JList(critNames);
                CriticalSlotList.setVisibleRowCount(critNames.size());
                CriticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                CriticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                CriticalSlotList.setName(Integer.toString(location));
                switch (location) {
                case Mech.LOC_HEAD:
                    headPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_LARM:
                    laPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_RARM:
                    raPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_CT:
                    ctPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_LT:
                    ltPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_RT:
                    rtPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_LLEG:
                    llPanel.add(CriticalSlotList);
                    break;
                case Mech.LOC_RLEG:
                    rlPanel.add(CriticalSlotList);
                    break;
                }
            }
        }
        mainPanel.add(headPanel);

        torsoPanel.add(laPanel);
        torsoPanel.add(ltPanel);
        torsoPanel.add(ctPanel);
        torsoPanel.add(rtPanel);
        torsoPanel.add(raPanel);
        mainPanel.add(torsoPanel);

        legPanel.add(llPanel);
        legPanel.add(rlPanel);
        mainPanel.add(legPanel);

        this.add(mainPanel);
    }
}