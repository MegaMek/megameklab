/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.protoMek;

import javax.swing.JLabel;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.verifier.TestProtoMek;
import megameklab.ui.generalUnit.StatusBar;

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
        for (int l = 0; l < getProtoMek().locations(); l++) {
            maxCrits += TestProtoMek.maxSlotsByLocation(l, getProtoMek());
        }
        long currentSlots = getProtoMek().getEquipment()
                                  .stream()
                                  .filter(m -> TestProtoMek.requiresSlot(m.getType()))
                                  .count();

        slots.setText(String.format(SLOTS_LABEL, maxCrits - currentSlots, maxCrits));
        slots.setForeground(currentSlots > maxCrits ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
