/*
 * MegaMekLab - Copyright (C) 2025 The MegaMek Team
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

package megameklab.ui.handheldWeapon;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.AmmoType;
import megamek.common.MiscType;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;

public class HHWStatusBar extends StatusBar {
    private static final String SLOTS_LABEL = "Free Slots: %d / %d";
    private final JLabel slotsLabel = new JLabel();

    public HHWStatusBar(HHWMainUI parent) {
        super(parent);
        add(slotsLabel);
    }

    @Override
    protected void additionalRefresh() {
        refreshSlots();
    }

    public void refreshSlots() {
        var maxSlots = 6;
        if (!getEntity().getMiscEquipment(MiscType.F_CLUB).isEmpty()) {
            maxSlots = 1;
        }
        var curSlots = getEntity().getEquipment().stream()
            .filter(m -> !(m.getType() instanceof AmmoType) && !m.getType().hasFlag(MiscType.F_WEAPON_ENHANCEMENT))
            .count();
        slotsLabel.setText(SLOTS_LABEL.formatted(maxSlots - curSlots, maxSlots));
        slotsLabel.setForeground(curSlots > maxSlots ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
