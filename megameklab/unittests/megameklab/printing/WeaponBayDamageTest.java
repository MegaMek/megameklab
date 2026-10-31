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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.WeaponMounted;
import megamek.common.units.Aero;
import megamek.common.units.Warship;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InitializeTypes.class)
class WeaponBayDamageTest {
    @Test
    void roundsTheCapitalBayTotalAfterAddingFractionalWeaponDamage() throws Exception {
        assertDamage("Naval Laser 55", 3, "17"); // SO:AA p. 88 example: 3 x 5.5 = 16.5, rounds to 17.
        assertDamage("Naval Laser 45", 4, "18");
    }

    private static void assertDamage(String name, int count, String expected) throws Exception {
        Warship ship = new Warship();
        WeaponBayText bay = new WeaponBayText(Aero.LOC_NOSE, false);
        for (int i = 0; i < count; i++) {
            bay.addBayWeapon((WeaponMounted) ship.addEquipment(EquipmentType.get(name), Aero.LOC_NOSE));
        }
        WeaponBayInventoryEntry entry = new WeaponBayInventoryEntry(ship, 1, bay, true);
        assertEquals(expected, entry.getShortField(0));
        assertEquals(expected, entry.getMediumField(0));
        assertEquals(expected, entry.getLongField(0));
        assertEquals(expected, entry.getExtremeField(0));
    }
}
