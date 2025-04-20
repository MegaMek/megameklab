/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit.summary;

import megamek.common.AeroSpaceFighter;
import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.verifier.TestMek;
import megameklab.util.UnitUtil;

public class CockpitSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Cockpit";
    }

    @Override
    public void refresh(Entity entity) {
        if ((entity instanceof Mek mek) && (mek.getCockpitType() != Mek.COCKPIT_UNKNOWN)) {
            availabilityLabel.setText(mek.getCockpitTechAdvancement().getFullRatingName(entity.isClan()));
            TestMek testMek = (TestMek) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testMek.getWeightCockpit(), entity));
            critLabel.setText(formatCrits(getCockpitCrits(mek)));
        } else if (entity instanceof AeroSpaceFighter fighter) {
            availabilityLabel.setText(fighter.getCockpitTechAdvancement().getFullRatingName(entity.isClan()));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private int getCockpitCrits(Mek mek) {
        return (mek.getCockpitType() == Mek.COCKPIT_COMMAND_CONSOLE) ? 2 : 1;
    }
}
