/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.battleArmor;

import java.io.File;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import megamek.common.CriticalSlot;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.verifier.EntityVerifier;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.BAASBMDropTargetCriticalList;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.IView;
import megameklab.ui.util.RefreshListener;

/**
 * The Crit Slots view for a single suit of BattleArmor
 * <p>
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * @author arlith
 * @author Simon (Juliez)
 */
public class BACriticalView extends IView {

    private final Box leftArmPanel = Box.createVerticalBox();
    private final Box rightArmPanel = Box.createVerticalBox();
    private final Box bodyPanel = Box.createVerticalBox();
    private final Box turretPanel = Box.createVerticalBox();
    private final JLabel weightLabel = new JLabel();

    private RefreshListener refresh;
    private final boolean showEmpty;
    private BACriticalSuit critSuit;

    /** The trooper in the squad this CriticalView is for. */
    int trooper;

    public BACriticalView(EntitySource eSource, int t, boolean showEmpty, RefreshListener refresh) {
        super(eSource);
        trooper = t;
        critSuit = new BACriticalSuit(getBattleArmor());
        this.showEmpty = showEmpty;
        this.refresh = refresh;

        Box mainPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box middlePanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();

        mainPanel.setBorder(BorderFactory.createTitledBorder(
              BorderFactory.createMatteBorder(2, 0, 0, 0, CritCellUtil.CRITICAL_CELL_BORDER_COLOR),
              " Trooper " + trooper + " ",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION));

        leftArmPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_LEFT_ARM]));
        bodyPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_BODY]));
        turretPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_TURRET]));
        rightArmPanel.setBorder(CritCellUtil.locationBorder(BattleArmor.MOUNT_LOC_NAMES[BattleArmor.MOUNT_LOC_RIGHT_ARM]));
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
        critSuit = new BACriticalSuit(getBattleArmor());
        leftArmPanel.removeAll();
        rightArmPanel.removeAll();
        bodyPanel.removeAll();
        turretPanel.removeAll();

        int[] numAPWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];
        int[] numAMWeapons = new int[BattleArmor.MOUNT_NUM_LOCS];

        for (Mounted<?> m : getBattleArmor().getEquipment()) {
            if ((m.getLocation() == BattleArmor.LOC_SQUAD) || (m.getLocation() == trooper)) {
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
                for (int slot = 0; slot < critSuit.getNumCriticalSlots(location); slot++) {
                    CriticalSlot cs = critSuit.getCritical(location, slot);
                    if (cs == null) {
                        if (showEmpty) {
                            critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                        }
                    } else if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                        Mounted<?> m = cs.getMount();
                        if (m == null) {
                            if (showEmpty) {
                                critNames.add(CritCellUtil.EMPTY_CRITICAL_CELL_TEXT);
                            }
                        } else {
                            critNames.add(m.getName() + ":" + slot + ":" + getBattleArmor().getEquipmentNum(m));
                        }
                    }
                }

                BAASBMDropTargetCriticalList<String> criticalSlotList = getCriticalSlotList(critNames, location);
                switch (location) {
                    case BattleArmor.MOUNT_LOC_LEFT_ARM:
                        leftArmPanel.add(criticalSlotList);
                        break;
                    case BattleArmor.MOUNT_LOC_RIGHT_ARM:
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
                amTxt[loc] = "Anti-Mek Weapons: " + numAMWeapons[loc] + "/"
                      + getBattleArmor().getNumAllowedAntiMekWeapons(loc);
                apTxt[loc] = "Anti-Personnel Weapons: " + numAPWeapons[loc] + "/"
                      + getBattleArmor().getNumAllowedAntiPersonnelWeapons(loc, trooper);
                if (numAMWeapons[loc] > getBattleArmor().getNumAllowedAntiMekWeapons(loc)) {
                    amTxt[loc] = "<html><font color='C00000'>" + amTxt[loc] + "</font></html>";
                }
                if (numAPWeapons[loc] > getBattleArmor().getNumAllowedAntiMekWeapons(loc)) {
                    apTxt[loc] = "<html><font color='C00000'>" + apTxt[loc] + "</font></html>";
                }
            }

            leftArmPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_LEFT_ARM]));
            leftArmPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_LEFT_ARM]));

            rightArmPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_RIGHT_ARM]));
            rightArmPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_RIGHT_ARM]));

            bodyPanel.add(makeLabel(amTxt[BattleArmor.MOUNT_LOC_BODY]));
            bodyPanel.add(makeLabel(apTxt[BattleArmor.MOUNT_LOC_BODY]));

            // Hide the arm panels if we are a quad
            boolean isQuad = getBattleArmor().getChassisType() == BattleArmor.CHASSIS_TYPE_QUAD;
            leftArmPanel.setVisible(!isQuad);
            rightArmPanel.setVisible(!isQuad);
            turretPanel.setVisible(isQuad && (getBattleArmor().getTurretCapacity() > 0));

            EntityVerifier entityVerifier = EntityVerifier.getInstance(new File(
                  "data/mekfiles/UnitVerifierOptions.xml"));
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

    private BAASBMDropTargetCriticalList<String> getCriticalSlotList(Vector<String> critNames,
          int location) {
        BAASBMDropTargetCriticalList<String> criticalSlotList = new BAASBMDropTargetCriticalList<>(
              critNames, eSource, refresh, showEmpty, this);
        criticalSlotList.setVisibleRowCount(critNames.size());
        criticalSlotList.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        criticalSlotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalSlotList.setName(location + ":" + trooper);
        criticalSlotList.setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        criticalSlotList.setPrototypeCellValue(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
        return criticalSlotList;
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        return label;
    }
}
