/*
 * MegaMekLab - Copyright (C) 2008
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
import megamek.common.WeaponType;
import megamek.common.loaders.MtfFile;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import megameklab.com.util.Mech.DropTargetCriticalList;

public class CriticalView extends IView {

    /**
     *
     */
    private static final long serialVersionUID = -6960975031034494605L;

    private JPanel squadPanel = new JPanel();
    private RefreshListener refresh;

    private boolean showEmpty = false;
    private CriticalSuit critSuit;
    
    public CriticalView(BattleArmor unit, boolean showEmpty, RefreshListener refresh) {
        super(unit);
        critSuit = new CriticalSuit(unit);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setOpaque(false);
        squadPanel.setOpaque(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        squadPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Trooper"));
        mainPanel.add(squadPanel);
        this.add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        critSuit = new CriticalSuit(getBattleArmor());
        squadPanel.removeAll();

        synchronized (unit) {
            for (int location = 0; location < critSuit.locations(); location++) {
                // JPanel locationPanel = new JPanel();
                Vector<String> critNames = new Vector<String>(1, 1);

                for (int slot = 0; slot < critSuit.getNumberOfCriticals(location); slot++) {
                    CriticalSlot cs = critSuit.getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(MtfFile.EMPTY);
                        }
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {

                                //m = getBattleArmor()cs.getMount();

                                if (m == null) {
                                    //getBattleArmor().setCritical(location, slot, null);
                                    if (showEmpty) {
                                        critNames.add(MtfFile.EMPTY);
                                    }
                                    continue;
                                }
                                //cs.setMount(m);
                            }
/*
                            if ((m.getType() instanceof WeaponType) && !UnitUtil.isBattleArmorWeapon(m.getType(), unit)) {
                                continue;
                            }
*/
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
                DropTargetCriticalList criticalSlotList = new DropTargetCriticalList(critNames, getBattleArmor(), refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.BLACK.darker()));
                squadPanel.add(criticalSlotList);
            }
            squadPanel.repaint();
        }
    }
    
    /*
     * This is just temporary - and non-functional - until we get crits proper on BattleArmor
     */
    private class CriticalSuit {
        
        public static final int LOC_LARM = 0;
        public static final int LOC_BODY = 1;
        public static final int LOC_RARM = 2;
        public static final int LOC_N    = 3;
        
        //store critical slots just like an entity
        protected CriticalSlot[][] crits; // [loc][slot]

        public CriticalSuit(BattleArmor ba) {
            crits = new CriticalSlot[locations()][];
            for (int i = 0; i < locations(); i++) {
                if(i == LOC_BODY) {
                    crits[i] = new CriticalSlot[ba.getBodyCrits()];
                } else {
                    crits[i] = new CriticalSlot[ba.getArmCrits()];
                }
            }
        }
        
        public int locations() {
            return LOC_N;
        }
        
        public String getName(int loc) {
            switch(loc) {
            case LOC_LARM:
                return "Left Arm";
            case LOC_RARM:
                return "Right Arm";
            case LOC_BODY:
                return "Body";
            default:
                return "?";
            }
        }
        
        public int getNumberOfCriticals(int loc) {
            return crits[loc].length;
        }
        
        public CriticalSlot getCritical(int loc, int slot) {
            return crits[loc][slot];
        }
        
        
    }
}