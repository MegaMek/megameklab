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

package megameklab.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import megamek.common.TechAdvancement;
import megamek.common.TechConstants;
import megamek.common.enums.TechBase;
import megamek.common.interfaces.ITechnology;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class UnitUtilTest {

    @Test
    void isLegalUsesOriginalBuildYear() {
        ITechnology lostech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(2500, 2600, 2700, 3025, ITechnology.DATE_NONE);
        ITechnology currenttech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(3030, 3040, 3050, ITechnology.DATE_NONE, ITechnology.DATE_NONE);
        ITechnology futuretech = new TechAdvancement(TechBase.IS)
              .setISAdvancement(3100, 3110, 3150, ITechnology.DATE_NONE, ITechnology.DATE_NONE);
        Mek mek = new BipedMek();
        mek.setYear(3050);
        mek.setTechLevel(TechConstants.T_IS_TW_NON_BOX);

        assertFalse(UnitUtil.isLegal(mek, lostech));
        assertTrue(UnitUtil.isLegal(mek, currenttech));
        assertFalse(UnitUtil.isLegal(mek, futuretech));
        mek.setOriginalBuildYear(3000);

        assertTrue(UnitUtil.isLegal(mek, lostech));
        assertTrue(UnitUtil.isLegal(mek, currenttech));
        assertFalse(UnitUtil.isLegal(mek, futuretech));
    }
}