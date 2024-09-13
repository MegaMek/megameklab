/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
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
        if ((entity instanceof Mek) && (((Mek) entity).getCockpitType() != Mek.COCKPIT_UNKNOWN)) {
            Mek mek = (Mek) entity;
            availabilityLabel.setText(mek.getCockpitTechAdvancement().getFullRatingName(entity.isClan()));
            TestMek testMek = (TestMek) UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testMek.getWeightCockpit(), entity));
            critLabel.setText(formatCrits(getCockpitCrits((Mek) entity)));
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
