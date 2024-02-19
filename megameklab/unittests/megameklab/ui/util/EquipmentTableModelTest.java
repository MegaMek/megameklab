package megameklab.ui.util;

import megamek.common.*;
import megamek.common.loaders.BLKAeroSpaceFighterFile;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.weapons.Weapon;
import megameklab.ui.EntitySource;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.generalUnit.BasicInfoView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentTableModelTest {

    private BasicInfoView techManager;
    private EntitySource eSource;
    private EquipmentTableModel etm;

    @BeforeAll
    static void before() {
        // Has to be done so all types are populated for lookups
        EquipmentType.initializeTypes();
    }

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
        Entity testEntity = new MechFileParser(is, fname).getEntity();
        doReturn(testEntity).when(eSource).getEntity();

        // Set up EquipmentTypeModel instance
        etm = new EquipmentTableModel(testEntity, techManager);
        etm.setData(Collections.list(EquipmentType.getAllTypes()));

        return testEntity;
    }

    @Test
    void testGetValueAtForAll() throws EntityLoadingException {
        // Test getValue() in EquipmentTableModel; the exercises conversions and lookups
        ArrayList<String> testItems = new ArrayList<String>(Arrays.asList(
                "/Kirghiz C.blk",                                           // ASF
                "/Beast Infantry (Elephant)(Laser Rifle_Support PPC).blk",  // Beast Inf
                "/Black Wolf BA (ER Pulse) (Sqd5).blk",                     // BA, Clan
                "/Fusilier BA (Sqd4).blk",                                  // BA, IS
                "/Raubvogel Aerobomber AB-18C.blk",                         // ConventionalFighter
                "/Union 'Pocket Warship'.blk",                              // DropShip
                "/Clan Field Artillery (Thumper).blk",          // Field Gun/Artillery CI
                "/Long Tom Cannon Turret (Quad).blk",                       // Generic Clan Turret
                "/Explorer JumpShip (HPG).blk",                             // JumpShip
                "/Jump Squad (LRM).blk",                                    // Jump CI
                "/Siren 4.blk",                                             // ProtoMech
                "/Aquarius Escort.blk",                                     // Small Craft
                "/Olympus Recharge Station (3072).blk",                     // SpaceStation
                "/Harpagos.mtf",                                            // QuadMech
                "/Sleipnir APC.blk",                                        // Vehicle
                "/Peregrine Corvette.blk"                                   // WarShip
        ));
        Entity e;

        for (String fname: testItems) {
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
        assertEquals(true, te.isAero());
        for (Mounted weapon: te.getWeaponList()) {
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
        assertEquals(false, te.isAero());
        for (Mounted weapon: te.getWeaponList()) {
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
        assertEquals(false, te.isAero());
        for (Mounted weapon: te.getWeaponList()) {
            WeaponType weaponType = (WeaponType) weapon.getType();
            String dString = EquipmentTableModel.getDamageString(weaponType, te.isAero());
            // Confirm can convert to double
            assertEquals(Integer.toString(weaponType.getRackSize())+"A",
                    dString);
        }
    }
}