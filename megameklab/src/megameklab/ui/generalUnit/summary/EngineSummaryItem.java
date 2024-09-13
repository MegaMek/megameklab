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

import megamek.common.Engine;
import megamek.common.Entity;
import megamek.common.Mek;
import megamek.common.Tank;
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
