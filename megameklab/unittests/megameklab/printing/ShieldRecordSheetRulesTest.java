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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static megameklab.printing.RecordSheetOptions.IntrinsicPhysicalAttacksStyle.EQUIPMENT;
import static megameklab.printing.RecordSheetOptions.IntrinsicPhysicalAttacksStyle.FOOTER;
import static megameklab.printing.RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE;

import java.util.List;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.game.Game;
import megamek.common.rules.RulesManager;
import megamek.common.rules.core.CoreRulesManager;
import megamek.common.rules.totalwarfare.TWRulesManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import megameklab.util.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = InitializeTypes.class)
class ShieldRecordSheetRulesTest {

    private RulesManager originalRulesManager;

    @BeforeEach
    void rememberRulesManager() {
        originalRulesManager = Game.rulesManager;
    }

    @AfterEach
    void restoreRulesManager() {
        Game.rulesManager = originalRulesManager;
    }

    @Test
    void coreAddsShieldBonusBeforeTsmAndOmitsStandaloneExportDamage() throws Exception {
        BipedMek mek = mekWithMediumShieldAndTsm();
        Game.rulesManager = new CoreRulesManager();

        List<InventoryEntry> physicals = IntrinsicPhysicalInventoryEntry.getEntriesFor(mek);
        assertEquals("9 [18]", punchAt(physicals, "LA").getDamageField(0));
        assertEquals("7 [14]", punchAt(physicals, "RA").getDamageField(0));

        MiscMounted shield = shield(mek);
        assertEquals("\u2014", StringUtils.getEquipmentInfo(mek, shield));
        assertEquals("\u2014", new StandardInventoryEntry(shield).getDamageField(0));
        assertEquals("\u2014", new StandardInventoryEntry(shield, EQUIPMENT).getDamageField(0));
        assertEquals("\u2014", new StandardInventoryEntry(shield, FOOTER).getDamageField(0));
        assertEquals("+2", new StandardInventoryEntry(shield, NONE).getDamageField(0));
        SVGMassPrinter.ExportInventoryEntry exportedShield = exportedShield(mek);
        assertEquals("P", exportedShield.t);
        assertNull(exportedShield.d);
        assertNull(exportedShield.md);
    }

    @Test
    void totalWarfareLeavesPunchUnmodifiedAndExportsHistoricalShieldDamage() throws Exception {
        BipedMek mek = mekWithMediumShieldAndTsm();
        Game.rulesManager = new TWRulesManager();

        List<InventoryEntry> physicals = IntrinsicPhysicalInventoryEntry.getEntriesFor(mek);
        assertEquals("7 [14]", punchAt(physicals, "LA").getDamageField(0));
        assertEquals("7 [14]", punchAt(physicals, "RA").getDamageField(0));

        MiscMounted shield = shield(mek);
        assertEquals("5", StringUtils.getEquipmentInfo(mek, shield));
        assertEquals("5", new StandardInventoryEntry(shield).getDamageField(0));
        assertEquals("5", new StandardInventoryEntry(shield, NONE).getDamageField(0));
        SVGMassPrinter.ExportInventoryEntry exportedShield = exportedShield(mek);
        assertEquals("P", exportedShield.t);
        assertEquals("5", exportedShield.d);
        assertEquals("5", exportedShield.md);
    }

    @Test
    void coreStandaloneShieldRowsShowSignedBashModifierForEverySize() throws Exception {
        Game.rulesManager = new CoreRulesManager();

        assertEquals("+1", standaloneShieldDamage("ISSmallShield"));
        assertEquals("+2", standaloneShieldDamage("ISMediumShield"));
        assertEquals("+3", standaloneShieldDamage("ISLargeShield"));
    }

    private BipedMek mekWithMediumShieldAndTsm() throws Exception {
        BipedMek mek = new BipedMek();
        mek.setWeight(70);
        mek.initializeInternal(10, Mek.LOC_LEFT_ARM);
        mek.initializeInternal(10, Mek.LOC_RIGHT_ARM);
        mek.addEquipment(EquipmentType.get("ISMediumShield"), Mek.LOC_LEFT_ARM);
        mek.addEquipment(EquipmentType.get(EquipmentTypeLookup.TSM), Entity.LOC_NONE);
        return mek;
    }

    private String standaloneShieldDamage(String equipmentId) throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_LEFT_ARM);
        mek.addEquipment(EquipmentType.get(equipmentId), Mek.LOC_LEFT_ARM);
        return new StandardInventoryEntry(shield(mek), NONE).getDamageField(0);
    }

    private IntrinsicPhysicalInventoryEntry punchAt(List<InventoryEntry> entries, String location) {
        return entries.stream()
              .filter(IntrinsicPhysicalInventoryEntry.class::isInstance)
              .map(IntrinsicPhysicalInventoryEntry.class::cast)
              .filter(entry -> entry.name().equals("Punch") && entry.location().equals(location))
              .findFirst()
              .orElseThrow();
    }

    private MiscMounted shield(BipedMek mek) {
        return mek.getMisc().stream()
              .filter(mounted -> mounted.getType().hasFlag(megamek.common.equipment.MiscType.F_SHIELD))
              .findFirst()
              .orElseThrow();
    }

    private SVGMassPrinter.ExportInventoryEntry exportedShield(BipedMek mek) {
        return new SVGMassPrinter.Components(mek).getComp().stream()
              .filter(entry -> "ISMediumShield".equals(entry.id))
              .findFirst()
              .orElseThrow();
    }
}
