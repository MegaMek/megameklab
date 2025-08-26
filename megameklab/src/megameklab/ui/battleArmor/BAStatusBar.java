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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.battleArmor;

import javax.swing.JLabel;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.verifier.TestBattleArmor;
import megameklab.ui.generalUnit.StatusBar;
import megameklab.util.UnitUtil;

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
