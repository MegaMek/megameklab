/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.EntitySource;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.generalUnit.BasicInfoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class EquipmentTableModelTest {

    private BasicInfoView techManager;
    private EntitySource eSource;
    private EquipmentTableModel etm;

    @BeforeEach
    void setUp() {
        techManager = mock(BasicInfoView.class);
        eSource = mock(ASMainUI.class);
    }

    private Entity configureFromFilename(String fileName) throws EntityLoadingException {
        // Load as InputStream
        // testresources and Classpath are not working together in this project somehow.
        // This only works if the file is in the test resources root
        InputStream is = getClass().getResourceAsStream(fileName);
        assertNotNull(is);

        // Read InputStream into Entity
        Entity testEntity = new MekFileParser(is, fileName).getEntity();
        doReturn(testEntity).when(eSource).getEntity();

        // Set up EquipmentTypeModel instance
        etm = new EquipmentTableModel(testEntity, techManager);
        etm.setData(Collections.list(EquipmentType.getAllTypes()));

        return testEntity;
    }

    @Test
    void testGetValueAtForAll() throws EntityLoadingException {
        // Test getValue() in EquipmentTableModel; the exercises conversions and lookups
        ArrayList<String> testItems = new ArrayList<>(Arrays.asList(
              "/Kirghiz C.blk", // ASF
              "/Beast Infantry (Elephant)(Laser Rifle_Support PPC).blk", // Beast Inf
              "/Black Wolf BA (ER Pulse) (Sqd5).blk", // BA, Clan
              "/Fusilier BA (Sqd4).blk", // BA, IS
              "/Raubvogel Aerobomber AB-18C.blk", // ConventionalFighter
              "/Union 'Pocket Warship'.blk", // DropShip
              "/Clan Field Artillery (Thumper).blk", // Field Gun/Artillery CI
              "/Long Tom Cannon Turret (Quad).blk", // Generic Clan Turret
              "/Explorer JumpShip (HPG).blk", // JumpShip
              "/Jump Squad (LRM).blk", // Jump CI
              "/Siren 4.blk", // ProtoMek
              "/Aquarius Escort.blk", // Small Craft
              "/Olympus Recharge Station (3072).blk", // SpaceStation
              "/Harpagos.mtf", // QuadMek
              "/Sleipnir APC.blk", // Vehicle
              "/Peregrine Corvette.blk" // WarShip
        ));
        Entity e;

        for (String fileName : testItems) {
            e = configureFromFilename(fileName);
            assertNotNull(e);
            for (int i = 0; i < etm.getColumnCount(); i++) {
                etm.getValueAt(0, i);
            }
        }
    }

    @Test
    void testGetDamageStringForAero() throws EntityLoadingException {
        // Test reading damage strings for ASF weapons
        String fileName = "/Kirghiz C.blk";
        Entity te = configureFromFilename(fileName);
        assertTrue(te.isAero());
        for (Mounted<?> weapon : te.getWeaponList()) {
            WeaponType weaponType = (WeaponType) weapon.getType();
            String dString = EquipmentTableModel.getDamageString(weaponType, te.isAero());
            if (dString.contains("/")) {
                assertTrue(dString.contains(Integer.toString(weaponType.getRoundShortAV())));
                assertTrue(dString.contains(Integer.toString(weaponType.getRoundMedAV())));
                assertTrue(dString.contains(Integer.toString(weaponType.getRoundLongAV())));
                assertTrue(dString.contains(Integer.toString(weaponType.getRoundExtAV())));
            } else {
                assertTrue(dString.contains(Integer.toString(weaponType.getRoundShortAV())));
            }
        }
    }

    @Test
    void testGetDamageStringForCI() throws EntityLoadingException {
        // Test reading damage strings for CI weapons
        String fileName = "/Jump Squad (LRM).blk";
        Entity te = configureFromFilename(fileName);
        assertFalse(te.isAero());
        for (Mounted<?> weapon : te.getWeaponList()) {
            WeaponType weaponType = (WeaponType) weapon.getType();
            String dString = EquipmentTableModel.getDamageString(weaponType, te.isAero());
            // Confirm can convert to double
            if (!dString.contains("Special")) {
                assertNotEquals(0, Double.valueOf(dString));
            }
        }
    }

    @Test
    void testGetDamageStringForGEArty() throws EntityLoadingException {
        // Test reading damage strings for CI weapons
        String fileName = "/Long Tom Cannon Turret (Quad).blk";
        Entity te = configureFromFilename(fileName);
        assertFalse(te.isAero());
        for (Mounted<?> weapon : te.getWeaponList()) {
            WeaponType weaponType = (WeaponType) weapon.getType();
            String dString = EquipmentTableModel.getDamageString(weaponType, te.isAero());
            // Confirm can convert to double
            assertEquals(weaponType.getRackSize() + "A", dString);
        }
    }
}
