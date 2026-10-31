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

import java.io.File;

import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The status bar's heat estimate, shared with MekHQ's unit editor, which used to count only weapons outside bays and
 * added Mek movement heat to aerospace units.
 */
@ExtendWith(value = InitializeTypes.class)
class HeatGenerationEstimateTest {

    private static Entity load(String fileName) throws Exception {
        return new MekFileParser(new File("testresources/" + fileName)).getEntity();
    }

    /** A Shilone SL-17 fires 23 heat of weapons and, as a fighter, adds no movement heat. */
    @Test
    void fighterCountsItsWeaponsWithoutMovementHeat() throws Exception {
        assertEquals(23, HeatGenerationEstimate.estimate(load("Shilone SL-17.blk")));
    }

    /** A DropShip's weapons sit in bays, and are still counted. */
    @Test
    void dropShipCountsTheWeaponsInItsBays() throws Exception {
        assertEquals(244, HeatGenerationEstimate.estimate(load("Union 'Pocket Warship'.blk")));
    }

    /** A Harpagos makes 2 heat running and 1 for each of its two Gauss Rifles. */
    @Test
    void mekAddsMovementHeat() throws Exception {
        assertEquals(4, HeatGenerationEstimate.estimate(load("Harpagos.mtf")));
    }

    @Test
    void vehicleHasNoHeatEstimate() throws Exception {
        assertEquals(0, HeatGenerationEstimate.estimate(load("Sleipnir APC.blk")));
    }
}
