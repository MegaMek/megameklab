/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.combatVehicle;

import javax.swing.JLabel;

import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.common.units.Tank;
import megameklab.ui.generalUnit.StatusBar;

/**
 * The Status Bar for Combat Vehicles
 */
public class CVStatusBar extends StatusBar {

    private static final String MOVE_LABEL = "Movement: %d / %d / %d";
    private static final String SLOTS_LABEL = "Free Slots: %d / %d";

    private final JLabel move = new JLabel();
    private final JLabel slots = new JLabel();

    public CVStatusBar(CVMainUI parent) {
        super(parent);
        add(move);
        add(slots);
    }

    @Override
    protected void additionalRefresh() {
        refreshMovement();
        refreshSlots();
    }

    public void refreshMovement() {
        int walk = getTank().getOriginalWalkMP();
        int run = getTank().getOriginalRunMP();
        int jump = getTank().getOriginalJumpMP();
        move.setText(String.format(MOVE_LABEL, walk, run, jump));
    }

    public void refreshSlots() {
        Tank tank = getTank();
        int freeSlots = tank.getFreeSlots();
        slots.setText(String.format(SLOTS_LABEL, freeSlots, tank.getTotalSlots()));
        slots.setForeground((freeSlots < 0) ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
