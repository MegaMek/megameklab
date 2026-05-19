/*
 * Copyright (C) 2024-2026 The MegaMek Team. All Rights Reserved.
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

import megamek.common.TechConstants;
import megamek.common.enums.AvailabilityValue;
import megamek.common.enums.Era;
import megamek.common.enums.TechRating;
import megamek.common.equipment.EquipmentType;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
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
        if ((entity instanceof Mek mek) && mek.isFrankenMek() && mek.hasHybridFrankenMekStructure()) {
            availabilityLabel.setText(getMergedFrankenMekAvailability(mek));
            critLabel.setText("");
            return;
        }
        int type = entity.getStructureType();
        if ((type >= 0) && (type < EquipmentType.structureNames.length)) {
            String structName = EquipmentType.getStructureTypeName(type,
                  TechConstants.isClan(entity.getStructureTechLevel()));
            EquipmentType structureType = EquipmentType.get(structName);
            availabilityLabel.setText(structureType.getFullRatingName(entity.isClan()));
            critLabel.setText(formatCrits(structureType.getNumCriticalSlots(entity)));
        } else {
            availabilityLabel.setText("");
            critLabel.setText("");
        }
    }

    private String getMergedFrankenMekAvailability(Mek mek) {
        TechRating techRating = TechRating.A;
        AvailabilityValue starLeagueAvailability = AvailabilityValue.A;
        AvailabilityValue successionWarsAvailability = AvailabilityValue.A;
        AvailabilityValue clanAvailability = AvailabilityValue.A;
        AvailabilityValue darkAgesAvailability = AvailabilityValue.A;

        for (int location = 0; location < mek.locations(); location++) {
            EquipmentType structureType = mek.getFrankenMekStructureEquipment(location);
            if (structureType == null) {
                continue;
            }
            techRating = worst(techRating, structureType.getTechRating());
            starLeagueAvailability = worst(starLeagueAvailability,
                  structureType.calcEraAvailability(Era.SL, mek.isClan()));
            successionWarsAvailability = worst(successionWarsAvailability,
                  structureType.calcEraAvailability(Era.SW, mek.isClan()));
            clanAvailability = worst(clanAvailability,
                  structureType.calcEraAvailability(Era.CLAN, mek.isClan()));
            darkAgesAvailability = worst(darkAgesAvailability,
                  structureType.calcEraAvailability(Era.DA, mek.isClan()));
        }

        return techRating.getName() + "/" + starLeagueAvailability.getName() + "-"
              + successionWarsAvailability.getName() + "-" + clanAvailability.getName() + "-"
              + darkAgesAvailability.getName();
    }

    private TechRating worst(TechRating first, TechRating second) {
        return first.getIndex() >= second.getIndex() ? first : second;
    }

    private AvailabilityValue worst(AvailabilityValue first, AvailabilityValue second) {
        return first.getIndex() >= second.getIndex() ? first : second;
    }
}
