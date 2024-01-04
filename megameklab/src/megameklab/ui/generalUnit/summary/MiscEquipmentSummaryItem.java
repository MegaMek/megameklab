/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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
import megameklab.util.UnitUtil;

/**
 * This Summary Item sums up all misctype equipment not handled elsewhere (it excludes JJ, UMU, heat sinks,
 * TSM) and without weapons and ammo.
 */
public class MiscEquipmentSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Equipment";
    }

    @Override
    public void refresh(Entity entity) {
        double totalWeight = 0;
        int totalCrits = 0;

        for (Mounted m : entity.getMisc()) {
            if (isEquipment(m)) {
                totalWeight += m.getTonnage();
                totalCrits += m.getCriticals();
            }
        }
        weightLabel.setText(formatWeight(totalWeight, entity));
        critLabel.setText(formatCrits(totalCrits));
    }

    private boolean isEquipment(Mounted mounted) {
        MiscType miscType = (MiscType) mounted.getType();
        return !UnitUtil.isArmorOrStructure(miscType)
                && !miscType.hasFlag(MiscType.F_TSM)
                && !miscType.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                && !miscType.hasFlag(MiscType.F_MASC)
                && !miscType.hasFlag(MiscType.F_JUMP_JET)
                && !miscType.hasFlag(MiscType.F_UMU)
                && !miscType.hasFlag(MiscType.F_JUMP_BOOSTER)
                && !miscType.hasFlag(MiscType.F_SPONSON_TURRET)
                && !miscType.hasFlag(MiscType.F_HEAT_SINK)
                && !miscType.hasFlag(MiscType.F_DOUBLE_HEAT_SINK);
    }
}
