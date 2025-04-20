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

import java.util.Optional;

import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;

public class MyomerEnhancementSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Myomer";
    }

    @Override
    public void refresh(Entity entity) {
        Optional<Mounted<?>> enhancement = getEnhancement(entity);
        if (enhancement.isPresent()) {
            availabilityLabel.setText(enhancement.get().getType().getFullRatingName(entity.isClan()));
            weightLabel.setText(formatWeight(enhancement.get().getTonnage(), entity));
            critLabel.setText(formatCrits(enhancement.get().getCriticals()));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private Optional<Mounted<?>> getEnhancement(Entity entity) {
        for (Mounted<?> m : entity.getMisc()) {
            if (isMyomerEnhancement(m)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    private boolean isMyomerEnhancement(Mounted<?> mounted) {
        MiscType miscType = (MiscType) mounted.getType();
        return miscType.hasFlag(MiscType.F_TSM) ||
                     miscType.hasFlag(MiscType.F_INDUSTRIAL_TSM) ||
                     miscType.hasFlag(MiscType.F_MASC);
    }
}
