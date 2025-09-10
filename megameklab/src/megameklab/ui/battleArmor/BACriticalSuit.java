/*
 * Copyright (C) 2015-2025 The MegaMek Team. All Rights Reserved.
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

import megamek.common.CriticalSlot;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;

/**
 * Since BattleArmor is set up in a squad, the standard CriticalSlot system isn't used. For construction purposes, we
 * keep track of criticalSlots. In MM, for purposes and dealing with hits, the "locations" for BattleArmor must
 * correspond to the troopers in the squad. This means that the standard Mounted.location can't really be used, and it
 * causes problems with the criticalSlots as well. Since these really only matter for constructions, a separate critical
 * system is tracked in MML only for construction purposes.
 **/
public class BACriticalSuit {

    // store critical slots just like an entity
    protected CriticalSlot[][] crits; // [loc][slot]

    BattleArmor ba;

    public BACriticalSuit(BattleArmor ba) {
        this.ba = ba;
        crits = new CriticalSlot[locations()][];
        for (int i = 0; i < locations(); i++) {
            int numSlots;
            switch (i) {
                case BattleArmor.MOUNT_LOC_BODY:
                    crits[i] = new CriticalSlot[ba.getBodyCrits()];
                    break;
                case BattleArmor.MOUNT_LOC_RIGHT_ARM:
                    numSlots = ba.getArmCrits();
                    if (ba.getRightManipulator() != null) {
                        numSlots++;
                    }
                    crits[i] = new CriticalSlot[numSlots];
                    break;
                case BattleArmor.MOUNT_LOC_LEFT_ARM:
                    numSlots = ba.getArmCrits();
                    if (ba.getLeftManipulator() != null) {
                        numSlots++;
                    }
                    crits[i] = new CriticalSlot[numSlots];
                    break;
                case BattleArmor.MOUNT_LOC_TURRET:
                    crits[i] = new CriticalSlot[ba.getTurretCapacity()];
                    break;
            }

        }
    }

    public int locations() {
        return BattleArmor.MOUNT_NUM_LOCS;
    }

    public int getNumCriticalSlots(int loc) {
        return crits[loc].length;
    }

    public boolean canAddMounted(int loc, Mounted<?> m) {
        int critsToAdd;
        if (m.getType().isSpreadable()) {
            critsToAdd = 1;
        } else {
            critsToAdd = m.getNumCriticalSlots();
        }
        int critsAvailable = 0;
        for (int c = 0; c < getNumCriticalSlots(loc); c++) {
            if (crits[loc][c] == null) {
                critsAvailable++;
            }
        }
        return critsAvailable >= critsToAdd;
    }

    public void addMounted(int loc, Mounted<?> m) {
        // Don't mount unmounted equipment
        if (loc == BattleArmor.MOUNT_LOC_NONE) {
            return;
        }
        if (m == null) {
            return;
        }

        // AP Weapons that are mounted in an AP Mount don't take up slots
        if (m.isAPMMounted() && m.getLinkedBy() != null
              && m.getLinkedBy().getType().hasFlag(MiscType.F_AP_MOUNT)) {
            return;
        }

        // Manipulators will always go in the last slot in its location, as they get a
        // special slot
        // added for them
        if (m.getType().hasFlag(MiscType.F_BA_MANIPULATOR)) {
            int slot = crits[loc].length - 1;
            crits[loc][slot] = new CriticalSlot(m);
        }

        int critsToAdd;
        if (m.getType().isSpreadable()) {
            critsToAdd = 1;
        } else {
            critsToAdd = m.getNumCriticalSlots();
        }
        if (critsToAdd == 0) {
            return;
        }
        for (int slot = 0; slot < getNumCriticalSlots(loc); slot++) {
            if (crits[loc][slot] == null) {
                crits[loc][slot] = new CriticalSlot(m);
                critsToAdd--;
                if (critsToAdd <= 0) {
                    break;
                }
            }
        }
    }

    public CriticalSlot getCritical(int loc, int slot) {
        return crits[loc][slot];
    }
}
