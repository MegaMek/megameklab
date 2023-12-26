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

package megameklab.ui.battleArmor;

import megamek.common.BattleArmor;
import megamek.common.verifier.TestBattleArmor;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.util.UnitUtil;

import javax.swing.*;

public class BAStatusBar extends StatusBar {

    private static final String MOVE_LABEL = "Movement: %d / %d";
    private static final String WEIGHT_LABEL_SUIT = "Suit Weight: %,.0f kg/%,.0f kg";
    private static final String REMAINDER = " (%,.0f kg Remaining)";

    private final JLabel move = new JLabel();
    private final JLabel suitWeight = new JLabel();

    public BAStatusBar(final BAMainUI parent) {
        super(parent);
        add(move);
        add(suitWeight);
    }

    @Override
    protected void additionalRefresh() {
        refreshMovement();
        refreshSuitWeight();
    }

    public void refreshMovement() {
        int walk = getBattleArmor().getOriginalWalkMP();
        int jump = getBattleArmor().getOriginalJumpMP();
        move.setText(String.format(MOVE_LABEL, walk, jump));
    }

    private void refreshSuitWeight() {
        final double maxKilos = getBattleArmor().getTrooperWeight() * 1000;
        double currentKilos = ((TestBattleArmor) getTestEntity()).calculateWeight(BattleArmor.LOC_SQUAD);
        currentKilos += UnitUtil.getUnallocatedAmmoTonnage(getBattleArmor());
        currentKilos *= 1000;
        String remaining = "";
        if (maxKilos - currentKilos > 0) {
            remaining = String.format(REMAINDER, maxKilos - currentKilos);
        }
        suitWeight.setText(String.format(WEIGHT_LABEL_SUIT, currentKilos, maxKilos) + remaining);
        suitWeight.setToolTipText("This represents the weight of all squad-level " +
                "equipment, it does not count individual equipment");
    }
}