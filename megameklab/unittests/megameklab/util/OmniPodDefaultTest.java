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
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Aero;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.BipedMek;
import megamek.common.units.ConvFighter;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.SupportTank;
import megamek.common.units.Tank;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Equipment MegaMekLab adds to an Omni unit goes in as a pod unless the rules fix it to the chassis, for every unit
 * type that can be an Omni: Meks, aerospace and conventional fighters, combat vehicles and support vehicles. The
 * equipment lists, the vehicle jump jets and the Mek CASE selector added new equipment as fixed, so a new weapon,
 * ammunition bin or jump jet altered the base chassis and made a MekHQ refit Class F (MekHQ #10770 and #10774). They
 * now add equipment through {@link UnitUtil#addMounted}, or ask {@link UnitUtil#canPodMount} directly.
 */
@ExtendWith(value = InitializeTypes.class)
class OmniPodDefaultTest {

    private static Mounted<?> add(Entity unit, String equipmentName, int location) throws LocationFullException {
        Mounted<?> mounted = Mounted.createMounted(unit, EquipmentType.get(equipmentName));
        UnitUtil.addMounted(unit, mounted, location, false);
        return mounted;
    }

    private static <T extends Entity> T omni(T unit) {
        unit.setOmni(true);
        return unit;
    }

    @Test
    void aWeaponAddedToAnOmniMekIsAPodBeforeItIsPlaced() throws LocationFullException {
        assertTrue(add(omni(new BipedMek()), "CLSRM4", Entity.LOC_NONE).isOmniPodMounted());
    }

    @Test
    void caseAddedToAnOmniMekCanBeAPod() {
        Mek mek = omni(new BipedMek());

        assertTrue(UnitUtil.canPodMount(mek,
              Mounted.createMounted(mek, EquipmentType.get(EquipmentTypeLookup.IS_CASE))));
    }

    @Test
    void equipmentTheRulesFixToTheChassisStaysFixed() throws LocationFullException {
        assertFalse(add(omni(new BipedMek()), EquipmentTypeLookup.IS_MASC, Entity.LOC_NONE).isOmniPodMounted());
    }

    @Test
    void ammunitionAddedToAnOmniFighterIsAPod() throws LocationFullException {
        assertTrue(add(omni(new AeroSpaceFighter()), "IS Ammo LRM-10", Aero.LOC_FUSELAGE).isOmniPodMounted());
    }

    @Test
    void ammunitionAddedToAnOmniConventionalFighterIsAPod() throws LocationFullException {
        assertTrue(add(omni(new ConvFighter()), "IS Ammo LRM-10", Aero.LOC_FUSELAGE).isOmniPodMounted());
    }

    @Test
    void aWeaponAddedToAnOmniVehicleIsAPod() throws LocationFullException {
        assertTrue(add(omni(new Tank()), "ISLRM10", Entity.LOC_NONE).isOmniPodMounted());
    }

    @Test
    void ammunitionAddedToAnOmniVehicleIsAPod() throws LocationFullException {
        assertTrue(add(omni(new Tank()), "IS Ammo LRM-10", Tank.LOC_BODY).isOmniPodMounted());
    }

    @Test
    void jumpJetsAddedToAnOmniVehicleArePods() throws LocationFullException {
        assertTrue(add(omni(new Tank()), EquipmentTypeLookup.VEHICLE_JUMP_JET, Tank.LOC_BODY).isOmniPodMounted());
    }

    @Test
    void aWeaponAddedToAnOmniSupportVehicleIsAPod() throws LocationFullException {
        assertTrue(add(omni(new SupportTank()), "ISLRM10", Entity.LOC_NONE).isOmniPodMounted());
    }

    @Test
    void equipmentAddedToAStandardVehicleIsFixed() throws LocationFullException {
        assertFalse(add(new Tank(), "ISLRM10", Entity.LOC_NONE).isOmniPodMounted());
    }
}
