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

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.Entity;
import megamek.common.units.Tank;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Equipment the fighter and vehicle equipment lists add to an Omni unit goes in as a pod, as it does on a Mek (MekHQ
 * #10770). The lists added it as fixed, so a fighter's new ammunition, or any new weapon on an Omni vehicle, altered
 * the base chassis and made a MekHQ refit Class F. The lists add equipment through {@link UnitUtil#addMounted}.
 */
@ExtendWith(value = InitializeTypes.class)
class OmniPodDefaultTest {

    private static Mounted<?> add(Entity unit, String equipmentName, int location) throws LocationFullException {
        Mounted<?> mounted = Mounted.createMounted(unit, EquipmentType.get(equipmentName));
        UnitUtil.addMounted(unit, mounted, location, false);
        return mounted;
    }

    @Test
    void ammunitionAddedToAnOmniFighterIsAPod() throws LocationFullException {
        AeroSpaceFighter fighter = new AeroSpaceFighter();
        fighter.setOmni(true);

        assertTrue(add(fighter, "IS Ammo LRM-10", Aero.LOC_FUSELAGE).isOmniPodMounted());
    }

    @Test
    void aWeaponAddedToAnOmniVehicleIsAPod() throws LocationFullException {
        Tank tank = new Tank();
        tank.setOmni(true);

        assertTrue(add(tank, "ISLRM10", Entity.LOC_NONE).isOmniPodMounted());
    }

    @Test
    void ammunitionAddedToAnOmniVehicleIsAPod() throws LocationFullException {
        Tank tank = new Tank();
        tank.setOmni(true);

        assertTrue(add(tank, "IS Ammo LRM-10", Tank.LOC_BODY).isOmniPodMounted());
    }

    @Test
    void equipmentAddedToAStandardVehicleIsFixed() throws LocationFullException {
        Tank tank = new Tank();

        assertFalse(add(tank, "ISLRM10", Entity.LOC_NONE).isOmniPodMounted());
    }
}
