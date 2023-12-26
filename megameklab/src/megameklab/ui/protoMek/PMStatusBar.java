/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
package megameklab.ui.protoMek;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.verifier.TestProtomech;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;

/**
 * Status bar for ProtoMek construction
 * 
 * @author Neoancient
 */
public class PMStatusBar extends StatusBar {

    private static final String SLOTS_LABEL = "Free Slots: %d / %d";

    private final JLabel slots = new JLabel();

    public PMStatusBar(PMMainUI parent) {
        super(parent);
        add(slots);
    }

    @Override
    protected void additionalRefresh() {
        refreshSlots();
    }

    public void refreshSlots() {
        int maxCrits = 0;
        for (int l = 0; l < getProtomech().locations(); l++) {
            maxCrits += TestProtomech.maxSlotsByLocation(l, getProtomech());
        }
        long currentSlots = getProtomech().getEquipment().stream()
                .filter(m -> TestProtomech.requiresSlot(m.getType())).count();

        slots.setText(String.format(SLOTS_LABEL, currentSlots, maxCrits));
        slots.setForeground(currentSlots > maxCrits ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}