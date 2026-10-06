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
package megameklab.ui.generalUnit;

import static megamek.common.units.ForceGeneratorAvailability.UNSPECIFIED_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Function;

import megamek.common.units.ForceGeneratorAvailability;
import megameklab.ui.generalUnit.AvailabilityTableModel.AvailabilityRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AvailabilityTableModelTest {

    /** In the tab this maps a code to a real faction name; the table's behaviour does not depend on it. */
    private static final Function<String, String> NAMER = code -> "Name of " + code;

    private AvailabilityTableModel model;

    @BeforeEach
    void setUp() {
        model = new AvailabilityTableModel();
    }

    @Test
    void loadSplitsEachFactionInAnEntryIntoItsOwnRow() {
        model.loadFrom(List.of(ForceGeneratorAvailability.parse("FS:5,LA:3")), NAMER);

        assertEquals(2, model.getRowCount());
        assertEquals("FS", model.getRow(0).factionCode());
        assertEquals(5, model.getRow(0).availability());
        assertEquals("LA", model.getRow(1).factionCode());
        assertEquals(3, model.getRow(1).availability());
    }

    @Test
    void loadKeepsTheYearRange() {
        model.loadFrom(List.of(ForceGeneratorAvailability.parse("3067-3085 FS:7")), NAMER);

        assertEquals(3067, model.getRow(0).fromYear());
        assertEquals(3085, model.getRow(0).toYear());
    }

    @Test
    void aFileWrittenByHandSurvivesBeingOpened() {
        // The tab does not offer the +/- suffixes, but a hand-edited file may carry them. Opening such a file must
        // not silently mangle the number.
        model.loadFrom(List.of(ForceGeneratorAvailability.parse("CJF:5+,CSA:2-")), NAMER);

        assertEquals(5, model.getRow(0).availability());
        assertEquals(2, model.getRow(1).availability());
    }

    @Test
    void anUnreadableNumberDoesNotFailTheLoad() {
        // A hand-edited value can overflow an int. Opening the file must not throw; the number reads as 0 instead.
        model.loadFrom(List.of(ForceGeneratorAvailability.parse("FS:99999999999")), NAMER);

        assertEquals(1, model.getRowCount());
        assertEquals(0, model.getRow(0).availability());
    }

    @Test
    void roundTripsThroughTheFileFormat() {
        List<ForceGeneratorAvailability> original = List.of(
              ForceGeneratorAvailability.parse("FS:5,LA:3"),
              ForceGeneratorAvailability.parse("3067-3085 FS:7"));

        model.loadFrom(original, NAMER);
        List<ForceGeneratorAvailability> written = model.toAvailabilityEntries();

        assertEquals(2, written.size());
        assertEquals("FS:5,LA:3", written.get(0).availabilityCodes());
        assertEquals(UNSPECIFIED_YEAR, written.get(0).startYear());
        assertEquals("FS:7", written.get(1).availabilityCodes());
        assertEquals(3067, written.get(1).startYear());
        assertEquals(3085, written.get(1).endYear());
    }

    @Test
    void factionsSharingAYearRangeAreWrittenAsOneLine() {
        // A hand-written file would put them on one line, so the tab should too
        model.addRow(new AvailabilityRow("FS", "Federated Suns", 5, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR, false));
        model.addRow(new AvailabilityRow("LA", "Lyran Commonwealth", 3, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR, false));
        model.addRow(new AvailabilityRow("CJF", "Clan Jade Falcon", 7, 3067, 3085, false));

        List<ForceGeneratorAvailability> written = model.toAvailabilityEntries();

        assertEquals(2, written.size());
        assertEquals("FS:5,LA:3", written.get(0).availabilityCodes());
        assertEquals("CJF:7", written.get(1).availabilityCodes());
    }

    @Test
    void addingTheSameFactionAndRangeTwiceOnlyAddsItOnce() {
        int first = model.addRow(new AvailabilityRow("FS", "Federated Suns", 5, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR,
              false));
        int second = model.addRow(new AvailabilityRow("FS", "Federated Suns", 9, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR,
              false));

        assertEquals(first, second, "Same faction and same range is an exact duplicate and is ignored");
        assertEquals(1, model.getRowCount());
        assertEquals(5, model.getRow(0).availability());
    }

    @Test
    void oneFactionCanHaveDifferentAvailabilityInDifferentYearRanges() {
        // The whole point of variable availability: the Federated Suns field this often early and rarely late.
        model.addRow(new AvailabilityRow("FS", "Federated Suns", 8, 3050, 3060, false));
        model.addRow(new AvailabilityRow("FS", "Federated Suns", 3, 3061, 3090, false));

        assertEquals(2, model.getRowCount(), "Same faction, different ranges, must both be kept");
        List<ForceGeneratorAvailability> entries = model.toAvailabilityEntries();
        assertEquals(2, entries.size());
        assertEquals("FS:8", entries.get(0).availabilityCodes());
        assertEquals(3050, entries.get(0).startYear());
        assertEquals("FS:3", entries.get(1).availabilityCodes());
        assertEquals(3061, entries.get(1).startYear());
    }

    @Test
    void rowsAreFlaggedByTheSuppliedStaleCheck() {
        // The check is supplied by the tab, which has the faction data. The model just applies it to each row.
        model.addRow(new AvailabilityRow("CW", "Clan Wolf", 5, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR, false));
        model.addRow(new AvailabilityRow("CWE", "Wolf Empire", 5, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR, false));

        model.markStaleRows(row -> row.factionCode().equals("CW"));

        assertTrue(model.getRow(0).stale());
        assertFalse(model.getRow(1).stale());
        assertTrue(model.hasStaleRows());
    }

    @Test
    void aRowStartingAtIntroShowsTheIntroYearInTheFromColumn() {
        model.setIntroYear(3050);
        model.addRow(new AvailabilityRow("FS", "Federated Suns", 5, UNSPECIFIED_YEAR, UNSPECIFIED_YEAR, false));

        assertEquals("3050", model.getValueAt(0, AvailabilityTableModel.COL_FROM),
              "A blank From column reads as empty; showing the intro year is clearer");
        assertEquals("", model.getValueAt(0, AvailabilityTableModel.COL_TO),
              "An open end still shows blank, meaning never stops");
    }

    @Test
    void anEmptyTableWritesNothing() {
        assertTrue(model.toAvailabilityEntries().isEmpty());
    }
}
