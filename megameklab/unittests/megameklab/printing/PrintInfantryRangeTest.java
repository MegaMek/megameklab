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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.print.PageFormat;

import megamek.common.equipment.EquipmentType;
import megamek.common.units.ConvInfantry;
import megamek.common.units.EntityMovementMode;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(InitializeTypes.class)
class PrintInfantryRangeTest {
    @ParameterizedTest
    @CsvSource({ "0, -2", "1, -1", "2, -1" })
    void crewOperatedWeaponsAddOneAtRangeZeroWithoutChangingOtherRanges(int secondaryCount,
          String expectedPointBlank) throws Exception {
        PrintInfantry sheet = sheet(secondaryCount, false);
        assertTrue(sheet.createDocument(0, new PageFormat(), true));
        assertEquals(expectedPointBlank, text(sheet, "range_mod_0"));
        assertEquals("0", text(sheet, "range_mod_1"));
        assertEquals(secondaryCount > 1 ? "+2" : InventoryEntry.DASH, text(sheet, "range_mod_4"));
        assertEquals(InventoryEntry.DASH, text(sheet, "range_mod_7"));
    }

    @Test
    void underwaterRangeHalvingRetainsTheCrewPenalty() throws Exception {
        PrintInfantry sheet = sheet(2, true);
        assertTrue(sheet.createDocument(0, new PageFormat(), true));
        assertEquals("-1", text(sheet, "uw_range_mod_0"));
        assertEquals("+2", text(sheet, "uw_range_mod_2"));
        assertEquals(InventoryEntry.DASH, text(sheet, "uw_range_mod_4"));
        assertEquals("0", text(sheet, "range_mod_2"));
    }

    private PrintInfantry sheet(int secondaryCount, boolean underwater) {
        ConvInfantry infantry = new ConvInfantry();
        infantry.setChassis("Infantry range test");
        infantry.setSquadSize(5);
        infantry.setSquadCount(4);
        infantry.setPrimaryWeapon((InfantryWeapon) EquipmentType.get("InfantryAssaultRifle"));
        if (secondaryCount > 0) {
            infantry.setSecondaryWeapon((InfantryWeapon) EquipmentType.get("InfantryMk2PortableAA"));
            infantry.setSecondaryWeaponsPerSquad(secondaryCount);
        }
        if (underwater) {
            infantry.setMovementMode(EntityMovementMode.INF_UMU);
        }
        infantry.autoSetInternal();
        RecordSheetOptions options = new RecordSheetOptions();
        options.setReferenceCharts(false);
        return new PrintInfantry(infantry, 0, options) {
            @Override
            String getSVGDirectoryName(boolean testDirectory) {
                return "../../mm-data/data/images/recordsheets/" + PaperSize.US_LETTER.dirName;
            }
        };
    }

    private String text(PrintInfantry sheet, String id) {
        return sheet.getSVGDocument().getElementById(id).getTextContent();
    }
}
