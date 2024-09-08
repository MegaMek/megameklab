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
import megamek.common.EquipmentType;
import megamek.common.Mek;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.annotations.Nullable;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestMek;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class HeatSinkSummaryItem extends AbstractSummaryItem {
    @Override
    public String getName() {
        return "Heatsinks";
    }

    @Override
    public void refresh(Entity entity) {
        if (entity instanceof Mek) {
            TestMek testMek = (TestMek) UnitUtil.getEntityVerifier(entity);
            Mek mek = (Mek) entity;
            int numberSinks = MekUtil.countActualHeatSinks(mek);
            numberSinks = Math.max(0, numberSinks - UnitUtil.getCriticalFreeHeatSinks(mek, mek.hasCompactHeatSinks()));
            int critSinks = numberSinks;
            if (MekUtil.hasClanDoubleHeatSinks(mek)) {
                critSinks = numberSinks * 2;
            } else if (mek.hasDoubleHeatSinks()) {
                critSinks = numberSinks * 3;
            } else if (mek.hasCompactHeatSinks()) {
                critSinks = (critSinks / 2) + (critSinks % 2);
            }
            weightLabel.setText(formatWeight(testMek.getWeightHeatSinks(), entity));
            critLabel.setText(formatCrits(critSinks));
        } else {
            TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
            weightLabel.setText(formatWeight(testEntity.getWeightHeatSinks(), entity));
        }

        availabilityLabel.setText("");
        String heatSinkName = getHeatSinkType(entity);
        if (heatSinkName != null) {
            EquipmentType hsType = EquipmentType.get(getHeatSinkType(entity));
            if (hsType != null) {
                availabilityLabel.setText(hsType.getFullRatingName(entity.isClan()));
            }
        }
    }

    public @Nullable String getHeatSinkType(Entity entity) {
        for (Mounted<?> m : entity.getMisc()) {
            if (m.getType().hasFlag(MiscType.F_COMPACT_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                    || m.getType().hasFlag(MiscType.F_LASER_HEAT_SINK)) {
                return m.getType().getInternalName();
            }
        }
        return null;
    }
}
