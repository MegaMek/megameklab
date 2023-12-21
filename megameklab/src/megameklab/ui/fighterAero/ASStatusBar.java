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
package megameklab.ui.fighterAero;

import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;

public class ASStatusBar extends StatusBar {

    private static final String HEAT_LABEL = "Heat: %d / %d";

    private final JLabel heat = new JLabel();

    public ASStatusBar(ASMainUI parent) {
        super(parent);
        add(heat);
    }

    @Override
    protected void additionalRefresh() {
        refreshHeat();
    }

    public void refreshHeat() {
        int heatCapacity = getAero().getHeatCapacity();
        long totalHeat = estimatedHeatGeneration();
        heat.setText(String.format(HEAT_LABEL, totalHeat, heatCapacity));
        heat.setToolTipText("Estimated Total Heat Generated / Total Heat Dissipated");
        heat.setVisible(getEntity().tracksHeat());
    }
}