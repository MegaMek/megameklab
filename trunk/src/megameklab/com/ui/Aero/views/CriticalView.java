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
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.Aero;
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

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel nosePanel = new JPanel();
    private JPanel aftPanel = new JPanel();
    

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();   
    private JPanel bottomPanel = new JPanel();

    private RefreshListener refresh;

    private boolean showEmpty = false;

    public CriticalView(Aero unit, boolean showEmpty, RefreshListener refresh) {
        super(unit);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));       
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        

        topPanel.add(nosePanel);
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Nose"));
        mainPanel.add(topPanel);

        middlePanel.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Left Wing"));        
        middlePanel.add(rightPanel);
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Right Wing"));
        mainPanel.add(middlePanel);
        
        aftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Aft"));
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

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < unit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = unit.getCritical(location, slot);
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
                                    unit.setCritical(location, slot, null);
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
                DropTargetCriticalList criticalSlotList = null;

                criticalSlotList = new DropTargetCriticalList(critNames, getAero(), refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                
                switch (location) {
                    case Aero.LOC_NOSE:
                        nosePanel.add(criticalSlotList);
                        break;
                    case Aero.LOC_LWING:
                        leftPanel.add(criticalSlotList);
                        break;
                    case Aero.LOC_RWING:
                        rightPanel.add(criticalSlotList);
                        break;
                    case Aero.LOC_AFT:
                        aftPanel.add(criticalSlotList);
                        break;
                }
                    
                
            }
            nosePanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
            aftPanel.repaint();
        }
    }

}