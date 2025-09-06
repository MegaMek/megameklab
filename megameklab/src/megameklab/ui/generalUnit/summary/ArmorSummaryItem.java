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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit.summary;

import megamek.common.equipment.ArmorType;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.Tank;
import megamek.common.verifier.TestEntity;
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

        ArmorType armor = ArmorType.forEntity(entity);
        availabilityLabel.setText(armor.getFullRatingName());

        if (entity.isSupportVehicle()) {
            // FIXME: This doesn't account for patchwork armor crits.
            TestSupportVehicle testSupportVehicle = (TestSupportVehicle) testEntity;
            critLabel.setText(formatCrits(testSupportVehicle.getArmorSlots()));
            weightLabel.setText(formatWeight(testSupportVehicle.getWeightArmor(), entity));
        } else if (entity instanceof Mek) {
            critLabel.setText(formatCrits(armor.getNumCriticalSlots(entity)));
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
        } else {
            return getArmorType(entity, entity.firstArmorIndex()).getTankSlots(entity);
        }
    }

    private String getFighterCrits(Entity entity) {
        if (entity.hasPatchworkArmor()) {
            int slots = 0;
            for (int loc = 0; loc < Aero.LOC_WINGS; loc++) {
                ArmorType aeroArmor = getArmorType(entity, loc);
                slots += aeroArmor.getPatchworkSlotsCVFtr();
            }
            return formatCrits(slots);
        } else {
            ArmorType aeroArmor = getArmorType(entity, Aero.LOC_NOSE);
            return formatCrits(aeroArmor.getFighterSlots());
        }
    }

    private ArmorType getArmorType(Entity entity, int location) {
        return ArmorType.of(entity.getArmorType(location), entity.isClanArmor(location));
    }
}
