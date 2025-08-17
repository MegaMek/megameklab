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

import megamek.common.equipment.Engine;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.Tank;
import megamek.common.verifier.TestEntity;
import megameklab.util.UnitUtil;

public class EngineSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Engine";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
        if (entity.hasEngine()) {
            availabilityLabel.setText(entity.getEngine().getFullRatingName(entity.isClan()));
            weightLabel.setText(formatWeight(testEntity.getWeightEngine(), entity));
            critLabel.setText(formatCrits(getEngineCrits(entity)));
        } else {
            availabilityLabel.setText("");
            weightLabel.setText("");
            critLabel.setText("");
        }
    }

    private int getEngineCrits(Entity entity) {
        if (entity instanceof Mek) {
            return getMekEngineCrits(entity);
        } else if (entity instanceof Tank) {
            return getTankEngineCrits(entity);
        } else {
            return 0;
        }
    }

    private int getMekEngineCrits(Entity entity) {
        if (entity.getEngine().getEngineType() == Engine.COMPACT_ENGINE) {
            return 3;
        } else {
            return 6 + (2 * entity.getEngine().getSideTorsoCriticalSlots().length);
        }
    }

    private int getTankEngineCrits(Entity entity) {
        Engine engine = entity.getEngine();
        int usedSlots = 0;
        if (engine.isFusion()) {
            if (engine.getEngineType() == Engine.LIGHT_ENGINE) {
                usedSlots++;
            }

            if (engine.getEngineType() == Engine.XL_ENGINE) {
                usedSlots += engine.hasFlag(Engine.CLAN_ENGINE) ? 1 : 2;
            }

            if (engine.getEngineType() == Engine.XXL_ENGINE) {
                usedSlots += engine.hasFlag(Engine.CLAN_ENGINE) ? 2 : 4;
            }
        }

        if (engine.hasFlag(Engine.LARGE_ENGINE)) {
            usedSlots++;
        }

        if (engine.getEngineType() == Engine.COMPACT_ENGINE) {
            usedSlots--;
        }
        return usedSlots;
    }
}
