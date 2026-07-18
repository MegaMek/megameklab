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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import megamek.client.ratgenerator.AvailabilityRating;
import megamek.client.ratgenerator.ChassisRecord;
import megamek.client.ratgenerator.FactionRecord;
import megamek.client.ratgenerator.RATGenerator;

/**
 * Turns a raw availability number into something a player can judge.
 * <p>
 * Availability runs 0 to 10 on a base-2 log scale, so the number on its own means nothing to anyone who has not read
 * the rules. Two things fix that, and both work by pointing at units the player already knows:
 * </p>
 * <ul>
 *     <li>{@link #comparableUnits}: which canon designs are about this common for this faction, right now.</li>
 *     <li>{@link #ratingsOf}: what a canon design's own numbers are, so they can be used as a starting point.</li>
 * </ul>
 * <p>
 * This class is deliberately free of Swing so it can be tested without a display.
 * </p>
 */
public final class AvailabilityCalibration {

    /** How many comparable designs to offer. Enough to calibrate against, few enough to read at a glance. */
    public static final int COMPARABLE_UNIT_COUNT = 3;

    /** How far a design's rating may sit from the one being judged and still be worth showing as comparable. */
    private static final int MAX_COMPARABLE_DIFFERENCE = 1;

    private static final String[] COMMONNESS_WORDS = {
          "none", "very rare", "very rare", "rare", "rare", "uncommon",
          "typical", "common", "common", "ubiquitous", "ubiquitous"
    };

    private AvailabilityCalibration() {
    }

    /**
     * Describes an availability value in words. This is what the slider shows, so a player never has to know that the
     * scale is logarithmic.
     *
     * @param availability the availability value, 0 to 10
     *
     * @return a word for it, for example "typical"
     */
    public static String describe(int availability) {
        int clamped = Math.clamp(availability, 0, COMMONNESS_WORDS.length - 1);

        return COMMONNESS_WORDS[clamped];
    }

    /**
     * Finds canon designs that are about as common as the given rating, for one faction in one era. Showing these next
     * to the slider is what tells a player whether they have set a sane number.
     * <p>
     * Only chassis of the same unit type are offered, since a Mek is no help in judging how common a DropShip is.
     * </p>
     *
     * @param unitType     the unit type to compare within, from {@code UnitType}
     * @param factionCode  the faction the number is for
     * @param year         the year to judge in
     * @param availability the availability value to match
     *
     * @return up to {@link #COMPARABLE_UNIT_COUNT} chassis names, closest match first; empty if nothing is comparable
     */
    public static List<String> comparableUnits(int unitType, String factionCode, int year, int availability) {
        RATGenerator ratGenerator = RATGenerator.getInstance();
        if (!ratGenerator.isInitialized()) {
            return List.of();
        }

        FactionRecord factionRecord = ratGenerator.getFaction(factionCode);
        if (factionRecord == null) {
            return List.of();
        }

        int era = ratGenerator.eraForYear(year);
        List<ComparableChassis> matches = new ArrayList<>();

        for (ChassisRecord chassisRecord : ratGenerator.getChassisList()) {
            if (chassisRecord.getUnitType() != unitType) {
                continue;
            }
            if (chassisRecord.getIntroYear() > year) {
                continue;
            }

            AvailabilityRating rating = ratGenerator.findChassisAvailabilityRecord(era,
                  chassisRecord.getKey(),
                  factionRecord,
                  year);
            if (rating == null) {
                continue;
            }

            int difference = Math.abs(rating.getAvailability() - availability);
            if (difference <= MAX_COMPARABLE_DIFFERENCE) {
                matches.add(new ComparableChassis(chassisRecord.getChassis(), difference));
            }
        }

        return pickComparable(matches);
    }

    /**
     * Chooses which of the candidate designs to show: closest rating first, then alphabetical so the list is stable,
     * with duplicate chassis names collapsed.
     * <p>
     * Split out from {@link #comparableUnits} so the choosing can be tested without a loaded Force Generator.
     * </p>
     *
     * @param candidates the designs that are close enough to be worth showing
     *
     * @return up to {@link #COMPARABLE_UNIT_COUNT} chassis names
     */
    static List<String> pickComparable(List<ComparableChassis> candidates) {
        List<ComparableChassis> sorted = new ArrayList<>(candidates);
        sorted.sort(Comparator.comparingInt(ComparableChassis::difference)
              .thenComparing(ComparableChassis::chassis));

        List<String> names = new ArrayList<>();
        for (ComparableChassis candidate : sorted) {
            if (names.size() >= COMPARABLE_UNIT_COUNT) {
                break;
            }
            if (!names.contains(candidate.chassis())) {
                names.add(candidate.chassis());
            }
        }

        return names;
    }

    /**
     * Reads the chassis availability a canon design already has, so a player can start from a design they know rather
     * than from a blank table.
     *
     * @param chassisKey the chassis key, as produced by {@code AbstractUnitRecord.getChassisKey()}
     * @param year       the year to read in
     *
     * @return the design's ratings, or empty if the Force Generator has nothing for it
     */
    public static List<AvailabilityRating> ratingsOf(String chassisKey, int year) {
        RATGenerator ratGenerator = RATGenerator.getInstance();
        if (!ratGenerator.isInitialized()) {
            return List.of();
        }

        int era = ratGenerator.eraForYear(year);
        Collection<AvailabilityRating> ratings = ratGenerator.getChassisFactionRatings(era, chassisKey);

        return (ratings == null) ? List.of() : new ArrayList<>(ratings);
    }

    /**
     * A canon chassis and how far its rating is from the one being judged.
     *
     * @param chassis    the chassis name
     * @param difference how many availability points away it is
     */
    record ComparableChassis(String chassis, int difference) {
    }
}
