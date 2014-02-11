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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.MtfFile;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestBattleArmor;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.Mech.DropTargetCriticalList;

public class CriticalView extends IView {

    /**
     *  Component for displaying information related to criticals on a suit of
     *  <code>BattleArmor</code>.  This will display a list of criticals for
     *  each location and what equipment is currently located there.  There 
     *  should be a <code>CriticalView</code> for each trooper in the squad.
     */
    private static final long serialVersionUID = -6960975031034494605L;

    /**
     * JPanel that holds all the components
     */
    private JPanel suitPanel = new JPanel();
    
    /**
     * JPanel for all of the left arm components
     */
    private JPanel leftPanel = new JPanel();
    /**
     * JPanel for all of hte right arm components
     */
    private JPanel rightPanel = new JPanel();
    /**
     * JPanel for all of the body components
     */
    private JPanel bodyPanel = new JPanel();
    
    private JLabel weightLabel = new JLabel();
    
    private RefreshListener refresh;

    private boolean showEmpty = false;
    
    private CriticalSuit critSuit;
    
    private Dimension lblSz = new Dimension(100, 25);
    
    /**
     * Keeps track of which trooper in the squad this <code>CriticalView</code>
     * is for.
     */
    int trooper;
    
    public CriticalView(BattleArmor unit, int t, boolean showEmpty,
            RefreshListener refresh) {
        super(unit);
        trooper = t;
        critSuit = new CriticalSuit(unit);
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        JPanel mainPanel = new JPanel();

        mainPanel.setOpaque(false);
        suitPanel.setOpaque(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        suitPanel.setLayout(new GridBagLayout());
        suitPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(), "Trooper " + trooper,
                TitledBorder.TOP, TitledBorder.DEFAULT_POSITION));

        GridBagConstraints gbc = new GridBagConstraints();
        
        
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = gbc.gridheight = 1;
        leftPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_LARM],
                TitledBorder.CENTER, TitledBorder.TOP));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        suitPanel.add(leftPanel,gbc);

        gbc.gridx++;
        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_BODY],
                TitledBorder.CENTER, TitledBorder.TOP));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        suitPanel.add(bodyPanel,gbc);
        
        gbc.gridx++;
        rightPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_RARM],
                TitledBorder.CENTER, TitledBorder.TOP));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        suitPanel.add(rightPanel,gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        weightLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        suitPanel.add(weightLabel,gbc);
        
        mainPanel.add(suitPanel);
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
        
        int [] numAPWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];
        int [] numAMWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];
         
        for (Mounted m : unit.getEquipment()){
            if (m.getLocation() == BattleArmor.LOC_SQUAD 
                    || m.getLocation() == trooper){
                critSuit.addMounted(m.getBaMountLoc(), m);
                if (m.getType() instanceof WeaponType 
                        && m.getBaMountLoc() != BattleArmor.MOUNT_LOC_NONE){
                    if (m.getType().hasFlag(WeaponType.F_INFANTRY)){
                        numAPWeapons[m.getBaMountLoc()]++;
                    } else {
                        numAMWeapons[m.getBaMountLoc()]++;
                    }
                }
            }            
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

                            critName.append(":" + slot + ":"
                                    + unit.getEquipmentNum(m));
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
                criticalSlotList.setName(location + ":" + trooper);
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
            
            String amTxt[] = new String[BattleArmor.MOUNT_NUM_LOCS];
            String apTxt[] = new String[BattleArmor.MOUNT_NUM_LOCS];
            for (int loc = 0; loc < BattleArmor.MOUNT_NUM_LOCS; loc++){
                amTxt[loc] = "AM Wpn: " + numAMWeapons[loc] + "/"
                        + getBattleArmor().getNumAllowedAntiMechWeapons(loc);
                apTxt[loc] = "AP Wpn: " + numAPWeapons[loc] + "/"
                        + getBattleArmor().getNumAllowedAntiPersonnelWeapons(
                                loc, trooper);
                if (numAMWeapons[loc] 
                        > getBattleArmor().getNumAllowedAntiMechWeapons(loc)){
                    amTxt[loc] = "<html><font color='C00000'>" + amTxt[loc] 
                            + "</font></html>";  
                }
                if (numAPWeapons[loc]
                        > getBattleArmor().getNumAllowedAntiMechWeapons(loc)){
                    apTxt[loc] = "<html><font color='C00000'>" + apTxt[loc] 
                            + "</font></html>";
                }
            }
                    

            leftPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_LARM], lblSz));
            leftPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_LARM], lblSz));
            
            rightPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_RARM], lblSz));
            rightPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_RARM], lblSz));
            
            bodyPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_BODY], lblSz));
            bodyPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_BODY], lblSz));


            // Hide the arm panels if we are a quad
            if (getBattleArmor().getChassisType() == 
                    BattleArmor.CHASSIS_TYPE_QUAD){
                leftPanel.setVisible(false);
                rightPanel.setVisible(false);
            } else {
                leftPanel.setVisible(true);
                rightPanel.setVisible(true);
            }
            
            EntityVerifier entityVerifier = new EntityVerifier(new File(
                    "data/mechfiles/UnitVerifierOptions.xml"));
            TestBattleArmor testBA = new TestBattleArmor(getBattleArmor(),
                    entityVerifier.baOption, null);
            
            String weightTxt = "Weight: "
                    + String.format("%1$.3f", testBA.calculateWeight(trooper))
                    + "/" + getBattleArmor().getTrooperWeight();
            if (testBA.calculateWeight(trooper) 
                    > getBattleArmor().getTrooperWeight()){
                weightTxt = "<html><font color='C00000'>" + weightTxt 
                        + "</font></html>";
            }
                    
            weightLabel.setText(weightTxt);
             
            leftPanel.add(Box.createVerticalStrut(8));
            rightPanel.add(Box.createVerticalStrut(8));
            bodyPanel.add(Box.createVerticalStrut(8));
            
            leftPanel.invalidate();
            leftPanel.invalidate();
            rightPanel.invalidate();
            
            bodyPanel.repaint();
            leftPanel.repaint();
            rightPanel.repaint();
        }
    }
    
    private JLabel makeLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        setFieldSize(label, maxSize);
        return label;
    }
    
    private void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }
    
    /**
     * Since BattleArmor is setup in a squad, the standard CriticalSlot system
     * isn't used.  For construction purposes, we keep track of criticals.
     **/
    private class CriticalSuit {
        
        //store critical slots just like an entity
        protected CriticalSlot[][] crits; // [loc][slot]
        
        BattleArmor ba;

        public CriticalSuit(BattleArmor ba) {
            this.ba = ba;
            crits = new CriticalSlot[locations()][];
            for (int i = 0; i < locations(); i++) {
                int numSlots;
                switch (i) {
                case BattleArmor.MOUNT_LOC_BODY:
                    crits[i] = new CriticalSlot[ba.getBodyCrits()];
                    break;
                case BattleArmor.MOUNT_LOC_RARM:
                    numSlots = ba.getArmCrits();
                    if (ba.getRightManipulator() != null) {
                        numSlots++;
                    }
                    crits[i] = new CriticalSlot[numSlots];
                    break;
                case BattleArmor.MOUNT_LOC_LARM:
                    numSlots = ba.getArmCrits();
                    if (ba.getLeftManipulator() != null) {
                        numSlots++;
                    }
                    crits[i] = new CriticalSlot[numSlots];
                    break;
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
            
            // AP Weapons that are mounted in an AP Mount don't take up slots
            if (m.isAPMMounted() && m.getLinkedBy() != null 
                    && m.getLinkedBy().getType().hasFlag(MiscType.F_AP_MOUNT)){
                return;
            }
            
            // Manipulators will always go in the last slot in its location,
            //  as they get a special slot added for them
            if (m.getType().hasFlag(MiscType.F_BA_MANIPULATOR)){
                int slot = crits[loc].length - 1;
                crits[loc][slot] = new CriticalSlot(m);
            }
            
            int critsToAdd;
            if (m.getType().isSpreadable()){
                critsToAdd = 1;
            } else {
                critsToAdd = m.getType().getCriticals(ba);
            }
            if (critsToAdd == 0){
                return;
            }
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