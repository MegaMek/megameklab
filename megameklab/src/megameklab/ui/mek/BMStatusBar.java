/*
 * MegaMekLab - Copyright (C) 2008
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
package megameklab.ui.mek;

import megamek.client.ui.swing.GUIPreferences;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.util.UnitUtil;

import javax.swing.*;

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
        int currentSlots = UnitUtil.countUsedCriticals(getMech());
        slots.setText(String.format(SLOTS_LABEL, maxCrits - currentSlots, maxCrits));
        slots.setForeground(currentSlots > maxCrits ? GUIPreferences.getInstance().getWarningColor() : null);
    }

    public void refreshHeat() {
        int heatCapacity = getMech().getHeatCapacity();
        long totalHeat = estimatedHeatGeneration();
        heat.setText(String.format(HEAT_LABEL, totalHeat, heatCapacity));
        heat.setToolTipText("Estimated Total Heat Generated / Total Heat Dissipated");
    }
}