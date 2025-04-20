/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.handheldWeapon;

import javax.swing.JLabel;

import megamek.client.ui.swing.GUIPreferences;
import megamek.common.AmmoType;
import megamek.common.MiscType;
import megameklab.ui.generalUnit.StatusBar;

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
        var curSlots = getEntity().getEquipment()
                             .stream()
                             .filter(m -> !(m.getType() instanceof AmmoType) &&
                                                !m.getType().hasFlag(MiscType.F_WEAPON_ENHANCEMENT))
                             .count();
        slotsLabel.setText(SLOTS_LABEL.formatted(maxSlots - curSlots, maxSlots));
        slotsLabel.setForeground(curSlots > maxSlots ? GUIPreferences.getInstance().getWarningColor() : null);
    }
}
