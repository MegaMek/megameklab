/*
 * MegaMekLab - Copyright (C) 2008
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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

import megamek.common.*;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.BattleArmor.CriticalSuit;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.CritCellUtil;
import megameklab.com.util.IView;
import megameklab.com.util.Mech.DropTargetCriticalList;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.util.Vector;

/**
 * The Crit Slots view for a single suit of BattleArmor
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author Simon (Juliez)
 */
public class CriticalView extends IView {

    private final Box leftArmPanel = Box.createVerticalBox();
    private final Box rightArmPanel = Box.createVerticalBox();
    private final Box bodyPanel = Box.createVerticalBox();
    private final Box turretPanel = Box.createVerticalBox();
    private final JLabel weightLabel = new JLabel();
    
    private RefreshListener refresh;
    private final boolean showEmpty;
    private CriticalSuit critSuit;
    
    /** The trooper in the squad this CriticalView is for. */
    int trooper;
    
    public CriticalView(EntitySource eSource, int t, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        trooper = t;
        critSuit = new CriticalSuit(getBattleArmor());
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        Box mainPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box middlePanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();

        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, CritCellUtil.CRITCELL_BORDERCOLOR),
                " Trooper " + trooper + " ",
                TitledBorder.TOP,
                TitledBorder.DEFAULT_POSITION));

        leftArmPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_LARM]));
        bodyPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_BODY]));
        turretPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_TURRET]));
        rightArmPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_RARM]));
        weightLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        leftPanel.add(leftArmPanel);
        middlePanel.add(bodyPanel);
        middlePanel.add(weightLabel);
        rightPanel.add(rightArmPanel);
        rightPanel.add(turretPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }

    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    public void refresh() {
        critSuit = new CriticalSuit(getBattleArmor());
        leftArmPanel.removeAll();
        rightArmPanel.removeAll();
        bodyPanel.removeAll();
        turretPanel.removeAll();
        
        int[] numAPWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];
        int[] numAMWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];
         
        for (Mounted m : getBattleArmor().getEquipment()) {
            if (m.getLocation() == BattleArmor.LOC_SQUAD || m.getLocation() == trooper) {
                critSuit.addMounted(m.getBaMountLoc(), m);
                // Weapons mounted in a quad turret count against the body limits
                int useLoc = m.getBaMountLoc();
                if (useLoc == BattleArmor.MOUNT_LOC_TURRET) {
                    useLoc = BattleArmor.MOUNT_LOC_BODY;
                }
                if (useLoc != BattleArmor.MOUNT_LOC_NONE) {
                    if ((m.getType() instanceof WeaponType) && !(m.getType() instanceof InfantryWeapon)) {
                        numAMWeapons[useLoc]++;
                    }
                    if (m.getType().hasFlag(MiscType.F_AP_MOUNT)) {
                        numAPWeapons[useLoc]++;  
                    }
                }
            }            
        }

        synchronized (getBattleArmor()) {
            for (int location = 0; location < critSuit.locations(); location++) {
                Vector<String> critNames = new Vector<>(1, 1);
                for (int slot = 0; slot < critSuit.getNumCriticals(location); slot++) {
                    CriticalSlot cs = critSuit.getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(CritCellUtil.EMPTYCELLTEXT);
                        }
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted m = cs.getMount();
                        if (m == null) {
                            if (showEmpty) {
                                critNames.add(CritCellUtil.EMPTYCELLTEXT);
                            }
                        } else {
                            critNames.add(m.getName() + ":" + slot + ":" + getBattleArmor().getEquipmentNum(m));
                        }
                    }
                }

                DropTargetCriticalList<String> criticalSlotList = new DropTargetCriticalList<>(
                        critNames, eSource, refresh, showEmpty);
                criticalSlotList.setVisibleRowCount(critNames.size());
                criticalSlotList.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                criticalSlotList.setName(location + ":" + trooper);
                criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITCELL_BORDERCOLOR));
                
                switch (location) {
                    case BattleArmor.MOUNT_LOC_LARM:
                        leftArmPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.MOUNT_LOC_RARM:
                        rightArmPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.MOUNT_LOC_BODY:
                        bodyPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.MOUNT_LOC_TURRET:
                        turretPanel.add(criticalSlotList);
                        break;
                }
            }
            
            String[] amTxt = new String[BattleArmor.MOUNT_NUM_LOCS];
            String[] apTxt = new String[BattleArmor.MOUNT_NUM_LOCS];
            for (int loc = 0; loc < BattleArmor.MOUNT_NUM_LOCS; loc++) {
                amTxt[loc] = "Anti-Mech Weapons: " + numAMWeapons[loc] + "/" 
                        + getBattleArmor().getNumAllowedAntiMechWeapons(loc);
                apTxt[loc] = "Anti-Personnel Weapons: " + numAPWeapons[loc] + "/" 
                        + getBattleArmor().getNumAllowedAntiPersonnelWeapons(loc, trooper);
                if (numAMWeapons[loc] > getBattleArmor().getNumAllowedAntiMechWeapons(loc)) {
                    amTxt[loc] = "<html><font color='C00000'>" + amTxt[loc] + "</font></html>";
                }
                if (numAPWeapons[loc] > getBattleArmor().getNumAllowedAntiMechWeapons(loc)) {
                    apTxt[loc] = "<html><font color='C00000'>" + apTxt[loc] + "</font></html>";
                }
            }

            leftArmPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_LARM]));
            leftArmPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_LARM]));
            
            rightArmPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_RARM]));
            rightArmPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_RARM]));
            
            bodyPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_BODY]));
            bodyPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_BODY]));

            // Hide the arm panels if we are a quad
            boolean isQuad = getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD;
            leftArmPanel.setVisible(!isQuad);
            rightArmPanel.setVisible(!isQuad);
            turretPanel.setVisible(isQuad && (getBattleArmor().getTurretCapacity() > 0));
            
            EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
                    "data/mechfiles/UnitVerifierOptions.xml"));
            TestBattleArmor testBA = new TestBattleArmor(getBattleArmor(), entityVerifier.baOption, null);
            
            String weightTxt = "Weight: "
                    + String.format("%1$.3f", testBA.calculateWeight(trooper))
                    + "/" + getBattleArmor().getTrooperWeight();
            if (testBA.calculateWeight(trooper) > getBattleArmor().getTrooperWeight()) {
                weightTxt = "<html><font color='C00000'>" + weightTxt + "</font></html>";
            }
            weightLabel.setText(weightTxt);

            validate();
        }
    }
    
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        return label;
    }
}