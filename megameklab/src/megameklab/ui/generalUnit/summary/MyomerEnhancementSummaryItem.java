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

public class MyomerEnhancementSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Myomer Enhancement";
    }

    @Override
    public void refresh(Entity entity) {
        double totalWeight = 0;
        int totalCrits = 0;

        for (Mounted m : entity.getMisc()) {
            if (isMyomerEnhancement(m)) {
                totalWeight = m.getTonnage();
                totalCrits = m.getCriticals();
                break;
            }
        }
        weightLabel.setText(formatWeight(totalWeight));
        critLabel.setText(formatCrits(totalCrits));
    }

    private boolean isMyomerEnhancement(Mounted mounted) {
        MiscType miscType = (MiscType) mounted.getType();
        return miscType.hasFlag(MiscType.F_TSM)
                || miscType.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                || miscType.hasFlag(MiscType.F_MASC);
    }
}
