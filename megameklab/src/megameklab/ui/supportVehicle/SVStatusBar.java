/*
 * MegaMekLab
 * Copyright (C) 2019 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.ui.supportVehicle;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;

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