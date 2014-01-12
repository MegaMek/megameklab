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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

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

    private JPanel squadPanel = new JPanel();
    
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    
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

        squadPanel.setLayout(new BoxLayout(squadPanel, BoxLayout.X_AXIS));
        squadPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Trooper", TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_LARM]));
        squadPanel.add(leftPanel);

        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_BODY]));
        squadPanel.add(bodyPanel);
        
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_RARM]));
        squadPanel.add(rightPanel);
        
        
        mainPanel.add(squadPanel);
        this.add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        critSuit = new CriticalSuit(getBattleArmor());
        leftPanel.removeAll();
        rightPanel.removeAll();
        bodyPanel.removeAll();
        
        for (Mounted m : unit.getEquipment()){
            critSuit.addMounted(m.getBaMountLoc(), m);
        }

        synchronized (unit) {
            for (int location = 0; location < critSuit.locations(); location++) {
                Vector<String> critNames = new Vector<String>(1, 1);
                for (int slot = 0; slot < critSuit.getNumCriticals(location); 
                        slot++) {
                    CriticalSlot cs = critSuit.getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(MtfFile.EMPTY);
                        }
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                        // BattleArmor shouldn't have system type crits
                        continue;
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        try {
                            Mounted m = cs.getMount();
                            // Critical didn't get removed. Remove it now.
                            if (m == null) {
                                if (showEmpty) {
                                    critNames.add(MtfFile.EMPTY);
                                }
                                continue;
                            }
                            StringBuffer critName = 
                                    new StringBuffer(m.getName());
                            if (critName.length() > 25) {
                                critName.setLength(25);
                                critName.append("...");
                            }
                            if (m.isDWPMounted()) {
                                critName.append(" (DWP)");
                            }
                            if (m.isMechTurretMounted()) {
                                critName.append(" (T)");
                            }
                            critName.append(":" + slot);
                            critNames.add(critName.toString());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                DropTargetCriticalList criticalSlotList = null;                

                criticalSlotList = new DropTargetCriticalList(
                        critNames, getBattleArmor(), refresh, showEmpty);
                criticalSlotList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setSelectionMode(
                        ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setFont(new Font("Arial", Font.PLAIN, 10));
                criticalSlotList.setName(Integer.toString(location));
                criticalSlotList.setBorder(BorderFactory.createEtchedBorder(
                        Color.WHITE.brighter(), Color.BLACK.darker()));
                
                switch (location) {
                    case BattleArmor.MOUNT_LOC_LARM:
                        leftPanel.add(criticalSlotList);
                        break;
                    case  BattleArmor.MOUNT_LOC_RARM:
                        rightPanel.add(criticalSlotList);
                        break;
                    case  BattleArmor.MOUNT_LOC_BODY:
                        bodyPanel.add(criticalSlotList);
                        break;
                }
                    
                
            }
            
            // Hide the arm panels if we are a quad
            if (getBattleArmor().getChassisType() == 
                    BattleArmor.CHASSIS_TYPE_QUAD){
                leftPanel.setVisible(false);
                rightPanel.setVisible(false);
            } else {
                leftPanel.setVisible(true);
                rightPanel.setVisible(true);
            }
             
            leftPanel.add(Box.createVerticalStrut(8));
            rightPanel.add(Box.createVerticalStrut(8));
            bodyPanel.add(Box.createVerticalStrut(8));
            
            bodyPanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
        }
    }
    
    /*
     * Since BattleArmor is setup in a squad, the standard CriticalSlot system
     * isn't used.  For construction purposes, we keep track of criticals.
     */
    private class CriticalSuit {
        
        //store critical slots just like an entity
        protected CriticalSlot[][] crits; // [loc][slot]
        
        BattleArmor ba;

        public CriticalSuit(BattleArmor ba) {
            this.ba = ba;
            crits = new CriticalSlot[locations()][];
            for (int i = 0; i < locations(); i++) {
                if(i == BattleArmor.MOUNT_LOC_BODY) {
                    crits[i] = new CriticalSlot[ba.getBodyCrits()];
                } else {
                    crits[i] = new CriticalSlot[ba.getArmCrits()];
                }
            }
        }
        
        public int locations() {
            return BattleArmor.MOUNT_NUM_LOCS;
        }

        public int getNumCriticals(int loc) {
            return crits[loc].length;
        }
        
        public void addMounted(int loc, Mounted m){
            // Don't mount unmounted equipment
            if (loc == BattleArmor.MOUNT_LOC_NONE){
                return;
            }
            
            int critsToAdd = m.getType().getCriticals(ba);
            for (int slot = 0; slot < getNumCriticals(loc); slot++){
                if (crits[loc][slot] == null){
                    crits[loc][slot] = new CriticalSlot(m);
                    critsToAdd--;
                    if (critsToAdd <= 0){
                        break;
                    }
                }
            }
        }
        
        public CriticalSlot getCritical(int loc, int slot) {
            return crits[loc][slot];
        }
        
        
    }
}