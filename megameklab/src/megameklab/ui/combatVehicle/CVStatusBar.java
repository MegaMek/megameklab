/*
 * MegaMekLab - Copyright (C) 2009
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
package megameklab.ui.combatVehicle;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.Tank;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;

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
        int currentSlots = tank.getTotalSlots() - tank.getFreeSlots();
        slots.setText(String.format(SLOTS_LABEL, currentSlots, tank.getTotalSlots()));
        slots.setForeground(currentSlots > tank.getTotalSlots() ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}