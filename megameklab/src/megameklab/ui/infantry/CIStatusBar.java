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
package megameklab.ui.infantry;

import megamek.client.ui.clientGUI.calculationReport.CalculationReport;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.generalUnit.StatusBar;

import javax.swing.*;
import java.text.DecimalFormat;

public class CIStatusBar extends StatusBar {

    private static final String MOVE_LABEL = "Movement: %d / %d";
    private static final String DAMAGE_LABEL = "Damage per Trooper: ";
    private static final String INF_WEIGHT_LABEL = "Weight: %s t";
    private static final String PLATOON_SIZE = "Platoon Size: %d";
    private static final DecimalFormat roundFormat = new DecimalFormat("#.##");

    private final JLabel move = new JLabel();
    private final JLabel damage = new JLabel();
    private final JLabel platoonSize = new JLabel();

    public CIStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        add(move);
        add(damage);
        add(platoonSize);
    }

    @Override
    protected void additionalRefresh() {
        refreshMovement();
        refreshDamage();
        refreshPlatoonSize();
    }

    private void refreshMovement() {
        int walk = getInfantry().getWalkMP();
        int jump = getInfantry().getJumpMP();
        move.setText(String.format(MOVE_LABEL, walk, jump));
    }

    private void refreshDamage() {
        damage.setText(DAMAGE_LABEL + roundFormat.format(getInfantry().getDamagePerTrooper()));
    }

    private void refreshPlatoonSize() {
        platoonSize.setText(PLATOON_SIZE.formatted(getInfantry().getOriginalTrooperCount()));
    }

    @Override
    protected void refreshWeight() {
        double tonnage = getEntity().getWeight();
        String full = CalculationReport.formatForReport(tonnage);
        tons.setText(String.format(INF_WEIGHT_LABEL, full));
        tons.setToolTipText("Click to show the weight calculation.");
    }
}
