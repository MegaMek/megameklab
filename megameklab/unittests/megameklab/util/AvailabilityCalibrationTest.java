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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import megameklab.util.AvailabilityCalibration.ComparableChassis;
import org.junit.jupiter.api.Test;

class AvailabilityCalibrationTest {

    @Test
    void describeGivesAWordForEveryStepOfTheScale() {
        // The slider shows these, so a player never has to know the scale is logarithmic
        assertEquals("none", AvailabilityCalibration.describe(0));
        assertEquals("rare", AvailabilityCalibration.describe(3));
        assertEquals("uncommon", AvailabilityCalibration.describe(5));
        assertEquals("typical", AvailabilityCalibration.describe(6));
        assertEquals("common", AvailabilityCalibration.describe(8));
        assertEquals("ubiquitous", AvailabilityCalibration.describe(10));
    }

    @Test
    void describeClampsOutOfRangeValues() {
        // A hand-edited file can carry anything; the tab must not blow up on it
        assertEquals("none", AvailabilityCalibration.describe(-4));
        assertEquals("ubiquitous", AvailabilityCalibration.describe(99));
    }

    @Test
    void comparableUnitsAreOrderedByHowCloseTheyAre() {
        List<String> picked = AvailabilityCalibration.pickComparable(List.of(
              new ComparableChassis("Zeus", 1),
              new ComparableChassis("Wolverine", 0),
              new ComparableChassis("Archer", 1)));

        // Exact match first, then alphabetical among the equally-close ones
        assertEquals(List.of("Wolverine", "Archer", "Zeus"), picked);
    }

    @Test
    void comparableUnitsStopAtThree() {
        List<String> picked = AvailabilityCalibration.pickComparable(List.of(
              new ComparableChassis("Archer", 0),
              new ComparableChassis("Banshee", 0),
              new ComparableChassis("Catapult", 0),
              new ComparableChassis("Dervish", 0),
              new ComparableChassis("Enforcer", 0)));

        assertEquals(AvailabilityCalibration.COMPARABLE_UNIT_COUNT, picked.size());
        assertEquals(List.of("Archer", "Banshee", "Catapult"), picked);
    }

    @Test
    void theSameChassisIsOnlyOfferedOnce() {
        // One chassis can hold several models, and the player does not need to be told about the Archer three times
        List<String> picked = AvailabilityCalibration.pickComparable(List.of(
              new ComparableChassis("Archer", 0),
              new ComparableChassis("Archer", 0),
              new ComparableChassis("Banshee", 1)));

        assertEquals(List.of("Archer", "Banshee"), picked);
    }

    @Test
    void nothingComparableGivesAnEmptyList() {
        assertTrue(AvailabilityCalibration.pickComparable(List.of()).isEmpty());
    }
}
