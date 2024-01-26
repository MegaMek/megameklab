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
import megamek.common.equipment.ArmorType;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.verifier.TestSmallCraft;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.util.UnitUtil;

public class ArmorSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Armor";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);

        weightLabel.setText("");
        critLabel.setText("");
        availabilityLabel.setText("");

        EquipmentType armor = null;
        int armorType = entity.getArmorType(0);
        if ((armorType >= 0) && (armorType < EquipmentType.armorNames.length)) {
            String armorName = EquipmentType.getArmorTypeName(armorType,
                    TechConstants.isClan(entity.getArmorTechLevel(0)));
            armor = EquipmentType.get(armorName);
            availabilityLabel.setText(armor.getFullRatingName(entity.isClan()));
        }

        if (entity.isSupportVehicle()) {
            // FIXME: This doesn't account for patchwork armor crits.
            TestSupportVehicle testSupportVehicle = (TestSupportVehicle) testEntity;
            critLabel.setText(formatCrits(testSupportVehicle.getArmorSlots()));
            weightLabel.setText(formatWeight(testSupportVehicle.getWeightArmor(), entity));
        } else if (entity instanceof Mech) {
            if (armor != null) {
                critLabel.setText(formatCrits(armor.getCriticals(entity)));
            }
        } else if (entity instanceof Tank) {
            critLabel.setText(formatCrits(getTankArmorCrits(entity)));
        } else if (entity instanceof AeroSpaceFighter) {
            critLabel.setText(getFighterCrits(entity));
        }

        if (entity.hasPatchworkArmor()) {
            weightLabel.setText(formatWeight(testEntity.getWeightAllocatedArmor(), entity));
        } else {
            weightLabel.setText(formatWeight(testEntity.getWeightArmor(), entity));
        }
    }

    private int getTankArmorCrits(Entity entity) {
        if (entity.hasPatchworkArmor()) {
            return 0;
        } else  {
            return getArmorType(entity, entity.firstArmorIndex()).getTankSlots(entity);
        }
    }

    private String getFighterCrits(Entity entity) {
        if (entity.hasPatchworkArmor()) {
            int slots = 0;
            for (int loc = 0; loc < Aero.LOC_WINGS; loc++) {
                ArmorType aeroArmor = getArmorType(entity, loc);
                if (aeroArmor == null) {
                    return "?";
                }
                slots += aeroArmor.getPatchworkSlotsCVFtr();
            }
            return formatCrits(slots);
        } else {
            ArmorType aeroArmor = getArmorType(entity, Aero.LOC_NOSE);
            if (aeroArmor == null) {
                return "?";
            }
            return formatCrits(aeroArmor.getFighterSlots());
        }
    }

    private ArmorType getArmorType(Entity entity, int location) {
        return ArmorType.of(entity.getArmorType(location), entity.isClanArmor(location));
    }
}
