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
import megamek.common.Jumpship;
import megamek.common.verifier.TestAdvancedAerospace;
import megameklab.util.UnitUtil;

public class LifeBoatSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Life Boats";
    }

    @Override
    public void refresh(Entity entity) {
        if (entity instanceof Jumpship) {
            TestAdvancedAerospace testShip = (TestAdvancedAerospace) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testShip.getWeightLifeBoats(), entity));
        }
    }
}
