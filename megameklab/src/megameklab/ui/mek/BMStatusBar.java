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
 */
package megameklab.ui.mek;

import javax.swing.JLabel;

import megamek.client.ui.swing.GUIPreferences;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.util.MekUtil;

public class BMStatusBar extends StatusBar {

    private static final String HEAT_LABEL = "Heat: %d / %d";
    private static final String SLOTS_LABEL = "Free Slots: %d / %d";

    private final JLabel slots = new JLabel();
    private final JLabel heat = new JLabel();

    public BMStatusBar(BMMainUI parent) {
        super(parent);
        add(slots);
        add(heat);
    }

    @Override
    protected void additionalRefresh() {
        refreshSlots();
        refreshHeat();
    }

    public void refreshSlots() {
        int maxCrits = getTestEntity().totalCritSlotCount();
        int currentSlots = MekUtil.countUsedCriticals(getMek());
        slots.setText(String.format(SLOTS_LABEL, maxCrits - currentSlots, maxCrits));
        slots.setForeground(currentSlots > maxCrits ? GUIPreferences.getInstance().getWarningColor() : null);
    }

    public void refreshHeat() {
        int heatCapacity = getMek().getHeatCapacity();
        long totalHeat = estimatedHeatGeneration();
        heat.setText(String.format(HEAT_LABEL, totalHeat, heatCapacity));
        heat.setToolTipText("Estimated Total Heat Generated / Total Heat Dissipated");
    }
}
