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

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megamek.common.verifier.TestEntity;
import megameklab.util.UnitUtil;

public class StructureSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Structure";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
        weightLabel.setText(formatWeight(testEntity.getWeightStructure(), entity));
        int type = entity.getStructureType();
        if ((type >= 0) && (type < EquipmentType.structureNames.length)) {
            String structName = EquipmentType.getStructureTypeName(type,
                    TechConstants.isClan(entity.getStructureTechLevel()));
            EquipmentType structureType = EquipmentType.get(structName);
            availabilityLabel.setText(structureType.getFullRatingName(entity.isClan()));
            critLabel.setText(formatCrits(structureType.getCriticals(entity)));
        } else {
            availabilityLabel.setText("");
            critLabel.setText("");
        }
    }
}
