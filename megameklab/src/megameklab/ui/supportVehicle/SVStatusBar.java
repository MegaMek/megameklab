/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.supportVehicle;

import javax.swing.JLabel;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.StatusBar;

/**
 * The Status Bar for Support Vehicles
 */
class SVStatusBar extends StatusBar {

    private static final String MOVE_LABEL = "Movement: %d / %d / %d";
    private static final String SLOTS_LABEL = "Free Slots: %d / %d";

    private final JLabel move = new JLabel();
    private final JLabel slots = new JLabel();

    SVStatusBar(SVMainUI parent) {
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
        int walk = getEntity().getOriginalWalkMP();
        int run = getEntity().getOriginalRunMP();
        int jump = getEntity().getOriginalJumpMP();
        move.setText(String.format(MOVE_LABEL, walk, run, jump));
    }

    public void refreshSlots() {
        TestSupportVehicle testEntity = (TestSupportVehicle) getTestEntity();
        final int totalSlots = testEntity.totalSlotCount();
        final int currentSlots = testEntity.occupiedSlotCount();
        slots.setText(String.format(SLOTS_LABEL, totalSlots - currentSlots, totalSlots));
        slots.setForeground(currentSlots > totalSlots ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
