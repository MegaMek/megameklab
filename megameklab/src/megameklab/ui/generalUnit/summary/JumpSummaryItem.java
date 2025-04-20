/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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

import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;

public class JumpSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Jump Jets";
    }

    @Override
    public void refresh(Entity entity) {
        double totalWeight = 0.0f;
        int totalCrits = 0;
        availabilityLabel.setText("");
        for (Mounted<?> m : entity.getMisc()) {
            MiscType mt = (MiscType) m.getType();
            if (mt.hasFlag(MiscType.F_JUMP_JET) || mt.hasFlag(MiscType.F_JUMP_BOOSTER) || mt.hasFlag(MiscType.F_UMU)) {
                totalWeight += m.getTonnage();
                totalCrits += m.getCriticals();
                availabilityLabel.setText(mt.getFullRatingName(entity.isClan()));
            }
        }
        weightLabel.setText(formatWeight(totalWeight, entity));
        if (entity instanceof Tank) {
            totalCrits = Math.min(totalCrits, 1);
        }
        critLabel.setText(formatCrits(totalCrits));
    }
}
