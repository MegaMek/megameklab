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

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megameklab.util.UnitUtil;

public class ArmorSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Armor";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
        if (entity.hasPatchworkArmor()) {
            weightLabel.setText(formatWeight(testEntity.getWeightAllocatedArmor()));
        } else {
            weightLabel.setText(formatWeight(testEntity.getWeightArmor()));
        }
        // FIXME: This doesn't account for patchwork armor crits.
        int armorType = entity.getArmorType(0);
        if ((armorType >= 0) && (armorType < EquipmentType.armorNames.length)) {
            String armorName = EquipmentType.getArmorTypeName(armorType,
                    TechConstants.isClan(entity.getArmorTechLevel(0)));
            EquipmentType armor = EquipmentType.get(armorName);
            if (entity instanceof Mech) {
                critLabel.setText(formatCrits(armor.getCriticals(entity)));
            } else if (entity instanceof Tank) {
                critLabel.setText(formatCrits(getTankArmorCrits(entity)));
            }
            availabilityLabel.setText(armor.getFullRatingName(entity.isClan()));
        } else {
            critLabel.setText("-");
            availabilityLabel.setText("-");
        }
    }

    private int getTankArmorCrits(Entity entity) {
        //TODO this should be implemented in EquipmentType armor.getCriticals(entity)
        int usedSlots = 0;
        if (!entity.hasPatchworkArmor()) {
            int type = entity.getArmorType(1);
            switch (type) {
                case EquipmentType.T_ARMOR_FERRO_FIBROUS:
                case EquipmentType.T_ARMOR_REACTIVE:
                    if (TechConstants.isClan(entity.getArmorTechLevel(1))) {
                        usedSlots++;
                    } else {
                        usedSlots += 2;
                    }
                    break;
                case EquipmentType.T_ARMOR_HEAVY_FERRO:
                    usedSlots += 3;
                    break;
                case EquipmentType.T_ARMOR_LIGHT_FERRO:
                case EquipmentType.T_ARMOR_FERRO_LAMELLOR:
                case EquipmentType.T_ARMOR_REFLECTIVE:
                case EquipmentType.T_ARMOR_HARDENED:
                case EquipmentType.T_ARMOR_ANTI_PENETRATIVE_ABLATION:
                case EquipmentType.T_ARMOR_BALLISTIC_REINFORCED:
                    usedSlots++;
                    break;
                case EquipmentType.T_ARMOR_STEALTH:
                    usedSlots += 2;
                    break;
                default:
                    break;
            }
        }

        return usedSlots;
    }
}
