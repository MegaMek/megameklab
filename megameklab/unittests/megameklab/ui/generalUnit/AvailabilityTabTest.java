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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import megamek.client.ratgenerator.MissionRole;
import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.interfaces.ITechManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.ForceGeneratorAvailability;
import megamek.common.units.Mek;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AvailabilityTableModel.AvailabilityRow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Builds the real Availability tab against a real unit.
 * <p>
 * The table model and the save path are tested elsewhere, but neither would notice if the tab itself threw while being
 * built or refreshed. Swing panels can be constructed without a display, so this runs headless and would catch exactly
 * that.
 * </p>
 */
class AvailabilityTabTest {

    @BeforeAll
    static void beforeAll() {
        EquipmentType.initializeTypes();
    }

    /** The tab only ever asks its source for the unit. */
    private record StubEntitySource(Entity entity) implements EntitySource {
        @Override
        public Entity getEntity() {
            return entity;
        }

        @Override
        public void createNewUnit(long entityType, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
            throw new UnsupportedOperationException("The Availability tab never creates units");
        }

        @Override
        public ITechManager getTechManager() {
            return null;
        }
    }

    private static Mek buildMek() {
        Mek mek = new BipedMek();
        mek.setChassis("Grimjack");
        mek.setModel("GRM-1A");
        mek.setYear(3049);
        mek.setWeight(20.0);
        mek.setEngine(new Engine(100, Engine.NORMAL_ENGINE, 0));

        return mek;
    }

    @Test
    void theTabBuildsForAUnitWithNoAvailability() {
        // The common case: a unit that has never been near this tab
        assertDoesNotThrow(() -> new AvailabilityTab(new StubEntitySource(buildMek())));
    }

    @Test
    void theTabLoadsWhatTheUnitAlreadyDeclares() {
        Mek mek = buildMek();
        mek.setForceGeneratorAvailability(List.of(ForceGeneratorAvailability.parse("FS:5,LA:3")));
        mek.setMissionRoles("fire_support");

        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(mek));

        assertNotNull(tab);
        // The unit declared two factions, so the table should be showing two rows
        assertEquals(2, tab.getTableModel().getRowCount());
        assertEquals("FS", tab.getTableModel().getRow(0).factionCode());
        assertEquals(5, tab.getTableModel().getRow(0).availability());
    }

    @Test
    void refreshingAfterTheYearChangesDoesNotThrow() {
        // The introduction year lives on Basic Info, so it can change under this tab at any time
        Mek mek = buildMek();
        mek.setForceGeneratorAvailability(List.of(ForceGeneratorAvailability.parse("FS:5")));
        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(mek));

        mek.setYear(3150);

        assertDoesNotThrow(tab::refresh);
    }

    @Test
    void onlyRolesThatMeanSomethingForTheUnitTypeAreOffered() {
        // A Mek has no business being offered "mek carrier" or "paratrooper". MissionRole.fitsUnitType() already knows
        // which roles apply where, so the tab asks it rather than showing all fifty.
        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(buildMek()));

        assertTrue(tab.isRoleOffered(MissionRole.FIRE_SUPPORT), "A Mek can be fire support");
        assertTrue(tab.isRoleOffered(MissionRole.URBAN), "A Mek can be an urban unit");
        assertFalse(tab.isRoleOffered(MissionRole.MEK_CARRIER), "A Mek does not carry Meks");
        assertFalse(tab.isRoleOffered(MissionRole.PARATROOPER), "Paratrooper is an infantry role");
    }

    @Test
    void theRoleGridHoldsOnlyTheOfferedRolesWithNoHoles() {
        // Hiding non-fitting roles with setVisible left holes in the grid. Now only the offered roles are added, so
        // the grid size equals the offered count and is well short of every role.
        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(buildMek()));

        long offered = Arrays.stream(MissionRole.values()).filter(tab::isRoleOffered).count();

        assertEquals(offered, tab.missionRoleGridSize(), "The grid should hold exactly the offered roles");
        assertTrue(tab.missionRoleGridSize() < MissionRole.values().length,
              "A Mek does not fit every role, so the grid must be smaller than the full list");
    }

    @Test
    void aRoleNoLongerInTheFileIsNotWrittenBack() {
        // A non-fitting role that was selected then removed must be deselected on reload, or missionRolesText would
        // write it back even though it is no longer shown.
        Mek mek = buildMek();
        mek.setMissionRoles("paratrooper");
        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(mek));
        assertTrue(tab.getMismatchedRoles().contains(MissionRole.PARATROOPER));

        mek.setMissionRoles("");
        tab.refresh();

        assertFalse(tab.currentMissionRolesText().contains("paratrooper"),
              "A hidden, no-longer-declared role must not leak back into the unit");
    }

    @Test
    void aFromYearAtTheIntroYearStaysTheSentinel() {
        // "Start at the intro year" is stored as UNSPECIFIED so it tracks the intro year. Leaving the spinner at the
        // intro year must not freeze it to a concrete value.
        int intro = 3049;
        assertEquals(ForceGeneratorAvailability.UNSPECIFIED_YEAR,
              AvailabilityTab.resolveFromYear(intro, ForceGeneratorAvailability.UNSPECIFIED_YEAR, intro));
        // Moving the spinner off the intro year makes it a real, concrete start year
        assertEquals(3055,
              AvailabilityTab.resolveFromYear(3055, ForceGeneratorAvailability.UNSPECIFIED_YEAR, intro));
        // A row that already had a concrete year keeps it, even when that year equals the intro year
        assertEquals(intro,
              AvailabilityTab.resolveFromYear(intro, intro, intro));
    }

    @Test
    void aRoleTheFileDeclaresIsShownEvenIfItDoesNotFit() {
        // Quietly dropping something out of somebody's file is not this tab's job. Show it, warn, let them decide.
        Mek mek = buildMek();
        mek.setMissionRoles("fire_support,paratrooper");

        AvailabilityTab tab = new AvailabilityTab(new StubEntitySource(mek));

        assertTrue(tab.isRoleOffered(MissionRole.PARATROOPER),
              "A role the file declares must stay visible so the player can remove it");
        assertTrue(tab.getMismatchedRoles().contains(MissionRole.PARATROOPER),
              "It should be flagged as not applying to this unit type");
        assertFalse(tab.getMismatchedRoles().contains(MissionRole.FIRE_SUPPORT));
    }

    @Test
    void aRangeThatEndsInsideAnEraIsFlagged() {
        // The QA case: eras at 3055, 3060, 3067. A range ending at 3062 stops inside the 3060-3066 era, so the change
        // is smeared across that era rather than happening at 3063.
        NavigableSet<Integer> eras = new TreeSet<>(List.of(3050, 3055, 3060, 3067, 3075));
        List<AvailabilityRow> rows = List.of(
              new AvailabilityRow("FS", "Federated Suns", 2, 3055, 3062, false));

        List<String> problems = AvailabilityTab.eraAlignmentProblems(rows, 3050, eras);

        assertEquals(1, problems.size());
        assertTrue(problems.getFirst().contains("3062"), problems.getFirst());
        assertTrue(problems.getFirst().contains("3060-3066"), problems.getFirst());
    }

    @Test
    void aRangeThatLinesUpWithErasIsNotFlagged() {
        // 3055-3059 is the whole 3055 era (3055 up to the year before 3060), so it is crisp.
        NavigableSet<Integer> eras = new TreeSet<>(List.of(3050, 3055, 3060, 3067, 3075));
        List<AvailabilityRow> rows = List.of(
              new AvailabilityRow("FS", "Federated Suns", 2, 3055, 3059, false));

        assertTrue(AvailabilityTab.eraAlignmentProblems(rows, 3050, eras).isEmpty());
    }

    @Test
    void theIntroYearIsNeverFlaggedAsAStartProblem() {
        // A range starting at the unit's own introduction year is natural, even if that year is not an era boundary.
        NavigableSet<Integer> eras = new TreeSet<>(List.of(3050, 3055, 3060));
        List<AvailabilityRow> rows = List.of(
              new AvailabilityRow("FS", "Federated Suns", 2,
                    megamek.common.units.ForceGeneratorAvailability.UNSPECIFIED_YEAR,
                    megamek.common.units.ForceGeneratorAvailability.UNSPECIFIED_YEAR, false));

        assertTrue(AvailabilityTab.eraAlignmentProblems(rows, 3052, eras).isEmpty());
    }

    @Test
    void theTabBuildsForAHandEditedFileItDoesNotOffer() {
        // The tab has no +/- control, but a hand-edited file may carry those. Opening such a unit must not throw.
        Mek mek = buildMek();
        mek.setForceGeneratorAvailability(List.of(ForceGeneratorAvailability.parse("CJF:5+,CSA:2-")));

        assertDoesNotThrow(() -> new AvailabilityTab(new StubEntitySource(mek)));
    }
}
