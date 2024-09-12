/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MekFileParser;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megameklab.testing.util.InitializeTypes;
import megameklab.ui.EntitySource;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.generalUnit.BasicInfoView;

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

    private Entity configureFromFilename(String fname) throws EntityLoadingException {
        // Load as InputStream
        // testresources and Classpath are not working together in this project somehow.
        // This only works if the file is in the test resources root
        InputStream is = getClass().getResourceAsStream(fname);
        assertNotNull(is);

        // Read InputStream into Entity
        Entity testEntity = new MekFileParser(is, fname).getEntity();
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

        for (String fname : testItems) {
            e = configureFromFilename(fname);
            assertNotNull(e);
            for (int i = 0; i < etm.getColumnCount(); i++) {
                etm.getValueAt(0, i);
            }
        }
    }

    @Test
    void testGetDamageStringForAero() throws EntityLoadingException {
        // Test reading damage strings for ASF weapons
        String fname = "/Kirghiz C.blk";
        Entity te = configureFromFilename(fname);
        assertTrue(te.isAero());
        for (Mounted weapon : te.getWeaponList()) {
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
        String fname = "/Jump Squad (LRM).blk";
        Entity te = configureFromFilename(fname);
        assertFalse(te.isAero());
        for (Mounted weapon : te.getWeaponList()) {
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
        String fname = "/Long Tom Cannon Turret (Quad).blk";
        Entity te = configureFromFilename(fname);
        assertFalse(te.isAero());
        for (Mounted weapon : te.getWeaponList()) {
            WeaponType weaponType = (WeaponType) weapon.getType();
            String dString = EquipmentTableModel.getDamageString(weaponType, te.isAero());
            // Confirm can convert to double
            assertEquals(weaponType.getRackSize() + "A", dString);
        }
    }
}
