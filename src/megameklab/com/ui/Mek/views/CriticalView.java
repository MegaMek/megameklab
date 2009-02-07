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

package megameklab.com.ui.Mek.views;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import megamek.common.CriticalSlot;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.loaders.MtfFile;
import megameklab.com.util.CConfig;
import megameklab.com.util.DropTargetCriticalList;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

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

    private CConfig config;
    private boolean showEmpty = false;

    public CriticalView(Mech unit, boolean showEmpty, RefreshListener refresh, CConfig config) {
        super(unit);
        this.showEmpty = showEmpty;
        this.refresh = refresh;
        this.config = config;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

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

    public void updateRefresh(RefreshListener refresh){
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
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        critNames.add(unit.getSystemName(cs.getIndex()));
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            //Critical didn't get removed.  Remove it now.
                            if ( m == null ) {

                                m = unit.getEquipment(cs.getIndex());

                                if ( m == null ) {
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
                            if ( m.isRearMounted() ){
                                critName.append("(R)");
                            }

                            critNames.add(critName.toString());

                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                DropTargetCriticalList CriticalSlotList = new DropTargetCriticalList(critNames, unit, refresh, showEmpty, config);
                CriticalSlotList.setVisibleRowCount(critNames.size());
                CriticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                CriticalSlotList.setFont(new Font("Arial",Font.PLAIN,10));
                CriticalSlotList.setName(Integer.toString(location));
                CriticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                switch (location) {
                case Mech.LOC_HEAD:
                    headPanel.add(CriticalSlotList);
                    headPanel.repaint();
                    break;
                case Mech.LOC_LARM:
                    laPanel.add(CriticalSlotList);
                    laPanel.repaint();
                    break;
                case Mech.LOC_RARM:
                    raPanel.add(CriticalSlotList);
                    raPanel.repaint();
                    break;
                case Mech.LOC_CT:
                    ctPanel.add(CriticalSlotList);
                    ctPanel.repaint();
                    break;
                case Mech.LOC_LT:
                    ltPanel.add(CriticalSlotList);
                    ltPanel.repaint();
                    break;
                case Mech.LOC_RT:
                    rtPanel.add(CriticalSlotList);
                    rtPanel.repaint();
                    break;
                case Mech.LOC_LLEG:
                    llPanel.add(CriticalSlotList);
                    llPanel.repaint();
                    break;
                case Mech.LOC_RLEG:
                    rlPanel.add(CriticalSlotList);
                    rlPanel.repaint();
                    break;
                }
            }
        }
    }
}