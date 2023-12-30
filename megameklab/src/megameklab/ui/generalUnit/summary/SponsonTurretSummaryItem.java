/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;

public class SponsonTurretSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Sponson Turret";
    }

    @Override
    public void refresh(Entity entity) {
        if (entity instanceof Tank) {
            weightLabel.setText(formatWeight(getSponsonTurretWeight(entity)));
        }
    }

    private double getSponsonTurretWeight(Entity entity) {
        for (Mounted m : entity.getMisc()) {
            MiscType mt = (MiscType) m.getType();
            if (mt.hasFlag(MiscType.F_SPONSON_TURRET)) {
                return m.getTonnage();
            }
        }
        return 0;
    }
}