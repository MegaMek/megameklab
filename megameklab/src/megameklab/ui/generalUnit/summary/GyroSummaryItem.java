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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit.summary;

import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.verifier.TestMek;
import megameklab.util.UnitUtil;

public class GyroSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Gyro";
    }

    @Override
    public void refresh(Entity entity) {
        if ((entity instanceof Mek mek) && (entity.getGyroType() != Mek.GYRO_NONE)) {
            availabilityLabel.setText(mek.getGyroTechAdvancement().getFullRatingName(entity.isClan()));
            TestMek testMek = (TestMek) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testMek.getWeightGyro(), entity));
            critLabel.setText(formatCrits(getGyroCrits(entity)));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private int getGyroCrits(Entity entity) {
        return switch (entity.getGyroType()) {
            case Mek.GYRO_COMPACT -> 2;
            case Mek.GYRO_XL -> 6;
            default -> 4;
        };
    }
}
