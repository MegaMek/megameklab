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
package megameklab.ui.infantry;

import java.text.DecimalFormat;
import javax.swing.JLabel;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.generalUnit.StatusBar;

public class CIStatusBar extends StatusBar {

    private static final String MOVE_LABEL = "Movement: %d / %d";
    private static final String DAMAGE_LABEL = "Damage per Trooper: ";
    private static final String INF_WEIGHT_LABEL = "Weight: %s t";
    private static final DecimalFormat roundFormat = new DecimalFormat("#.##");

    private final JLabel move = new JLabel();
    private final JLabel damage = new JLabel();

    public CIStatusBar(MegaMekLabMainUI parent) {
        super(parent);
        add(move);
        add(damage);
    }

    @Override
    protected void additionalRefresh() {
        refreshMovement();
        refreshDamage();
    }

    public void refreshMovement() {
        int walk = getInfantry().getWalkMP();
        int jump = getInfantry().getJumpMP();
        move.setText(String.format(MOVE_LABEL, walk, jump));
    }

    public void refreshDamage() {
        damage.setText(DAMAGE_LABEL + roundFormat.format(getInfantry().getDamagePerTrooper()));
    }

    @Override
    protected void refreshWeight() {
        double tonnage = getEntity().getWeight();
        String full = CalculationReport.formatForReport(tonnage);
        tons.setText(String.format(INF_WEIGHT_LABEL, full));
        tons.setToolTipText("Click to show the weight calculation.");
    }
}
