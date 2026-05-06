/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.TechConstants;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class EntitySourceTest {

    @Test
    void copyUnitBasicsCopiesSharedFields() {
        EntitySource entitySource = new TestEntitySource();
        Mek oldUnit = new BipedMek();
        oldUnit.setChassis("Test Chassis");
        oldUnit.setModel("Test Model");
        oldUnit.setYear(3050);
        oldUnit.setOriginalBuildYear(3000);
        oldUnit.setSource("TM");
        oldUnit.setPublished("Record Sheets: 3050");
        oldUnit.setManualBV(1234);
        oldUnit.setTechLevel(TechConstants.T_IS_ADVANCED);
        oldUnit.setMixedTech(true);

        Mek newUnit = new BipedMek();
        newUnit.setTechLevel(TechConstants.T_IS_TW_NON_BOX);

        entitySource.copyUnitBasics(newUnit, oldUnit);

        assertEquals(oldUnit.getChassis(), newUnit.getChassis());
        assertEquals(oldUnit.getModel(), newUnit.getModel());
        assertEquals(oldUnit.getYear(), newUnit.getYear());
        assertEquals(oldUnit.getOriginalBuildYear(), newUnit.getOriginalBuildYear());
        assertEquals(oldUnit.getSource(), newUnit.getSource());
        assertEquals(oldUnit.getPublished(), newUnit.getPublished());
        assertEquals(oldUnit.getManualBV(), newUnit.getManualBV());
        assertEquals(TechConstants.T_IS_ADVANCED, newUnit.getTechLevel());
        assertTrue(newUnit.isMixedTech());
    }

    private static class TestEntitySource implements EntitySource {
        private final Entity entity = new BipedMek();

        @Override
        public Entity getEntity() {
            return entity;
        }

        @Override
        public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        }

        @Override
        public ITechManager getTechManager() {
            return null;
        }
    }
}