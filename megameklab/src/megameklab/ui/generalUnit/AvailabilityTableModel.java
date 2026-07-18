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

import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.table.AbstractTableModel;

import megamek.common.units.ForceGeneratorAvailability;
import megameklab.util.AvailabilityCalibration;

/**
 * The rows of the Availability tab: one faction, how common the unit is for them, and the years it applies.
 * <p>
 * A row maps to one faction code inside a {@link ForceGeneratorAvailability} entry. The file format groups codes by
 * year range, so several rows can share one entry; {@link #toAvailabilityEntries()} regroups them on the way out.
 * </p>
 */
public class AvailabilityTableModel extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final int COL_FACTION = 0;
    public static final int COL_AVAILABILITY = 1;
    public static final int COL_FROM = 2;
    public static final int COL_TO = 3;
    private static final int COLUMN_COUNT = 4;

    private final List<AvailabilityRow> rows = new ArrayList<>();

    /** The unit's introduction year, shown in the From column for rows that start at intro rather than a set year. */
    private int introYear = ForceGeneratorAvailability.UNSPECIFIED_YEAR;

    /**
     * One faction's availability, as the player sees it.
     *
     * @param factionCode  the faction key, e.g. "FS", or an umbrella key such as "IS"
     * @param factionName  what to show the player, e.g. "Federated Suns"
     * @param availability 0 to 10
     * @param fromYear     first year, or {@link ForceGeneratorAvailability#UNSPECIFIED_YEAR} for the unit's intro year
     * @param toYear       last year, or {@link ForceGeneratorAvailability#UNSPECIFIED_YEAR} for never stops
     * @param stale        true when this faction does not exist in the unit's current year
     */
    public record AvailabilityRow(String factionCode, String factionName, int availability, int fromYear, int toYear,
                                  boolean stale) {

        public AvailabilityRow withAvailability(int newAvailability) {
            return new AvailabilityRow(factionCode, factionName, newAvailability, fromYear, toYear, stale);
        }

        public AvailabilityRow withYears(int newFromYear, int newToYear) {
            return new AvailabilityRow(factionCode, factionName, availability, newFromYear, newToYear, stale);
        }

        public AvailabilityRow withStale(boolean nowStale) {
            return new AvailabilityRow(factionCode, factionName, availability, fromYear, toYear, nowStale);
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case COL_FACTION -> "Faction";
            case COL_AVAILABILITY -> "How common";
            case COL_FROM -> "From";
            case COL_TO -> "To";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AvailabilityRow row = rows.get(rowIndex);

        return switch (columnIndex) {
            case COL_FACTION -> row.factionName() + " (" + row.factionCode() + ")";
            case COL_AVAILABILITY -> row.availability() + "  " + AvailabilityCalibration.describe(row.availability());
            // A row that starts at the unit's intro year shows that year rather than a blank, so the column is not
            // confusingly empty. The stored value stays unspecified, so it still tracks the intro year if it changes.
            case COL_FROM -> yearText((row.fromYear() == ForceGeneratorAvailability.UNSPECIFIED_YEAR)
                  ? introYear : row.fromYear());
            case COL_TO -> yearText(row.toYear());
            default -> "";
        };
    }

    /**
     * Sets the unit's introduction year, used to fill in the From column for rows that start at intro.
     *
     * @param introYear the unit's introduction year
     */
    public void setIntroYear(int introYear) {
        if (this.introYear != introYear) {
            this.introYear = introYear;
            fireTableDataChanged();
        }
    }

    private static String yearText(int year) {
        return (year == ForceGeneratorAvailability.UNSPECIFIED_YEAR) ? "" : String.valueOf(year);
    }

    public AvailabilityRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public List<AvailabilityRow> getRows() {
        return List.copyOf(rows);
    }

    public void setRow(int rowIndex, AvailabilityRow row) {
        rows.set(rowIndex, row);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    /**
     * Adds a row, unless a row for the same faction AND the same year range is already present. One faction can appear
     * more than once, as long as the ranges differ: that is how a player gives it a different availability early and
     * late. Only an exact duplicate is ignored.
     *
     * @param row the row to add
     *
     * @return the index of the row, whether it was added now or was already there
     */
    public int addRow(AvailabilityRow row) {
        for (int index = 0; index < rows.size(); index++) {
            AvailabilityRow existing = rows.get(index);
            if (existing.factionCode().equals(row.factionCode())
                  && (existing.fromYear() == row.fromYear())
                  && (existing.toYear() == row.toYear())) {
                return index;
            }
        }

        rows.add(row);
        fireTableRowsInserted(rows.size() - 1, rows.size() - 1);

        return rows.size() - 1;
    }

    public void removeRow(int rowIndex) {
        rows.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clear() {
        int previousSize = rows.size();
        rows.clear();
        if (previousSize > 0) {
            fireTableRowsDeleted(0, previousSize - 1);
        }
    }

    /**
     * Marks rows whose faction is never active during the years that row applies. A future faction added on purpose,
     * with a year range that reaches the years it exists, is NOT stale; a faction that died before the unit's time is.
     * The check is passed in because it needs the Force Generator's faction data, which the model does not hold.
     *
     * @param staleCheck returns true when a row's faction cannot be used in that row's years
     */
    public void markStaleRows(Predicate<AvailabilityRow> staleCheck) {
        for (int index = 0; index < rows.size(); index++) {
            AvailabilityRow row = rows.get(index);
            boolean stale = staleCheck.test(row);
            if (stale != row.stale()) {
                rows.set(index, row.withStale(stale));
                fireTableRowsUpdated(index, index);
            }
        }
    }

    public boolean hasStaleRows() {
        return rows.stream().anyMatch(AvailabilityRow::stale);
    }

    /**
     * Fills the table from a unit's declared availability. Each entry can name several factions, and each becomes its
     * own row.
     *
     * @param entries       the unit's availability entries
     * @param factionNamer  turns a faction code into a display name
     */
    public void loadFrom(List<ForceGeneratorAvailability> entries, Function<String, String> factionNamer) {
        clear();

        for (ForceGeneratorAvailability entry : entries) {
            for (String code : entry.availabilityCodes().split(",")) {
                String trimmed = code.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }

                int separator = trimmed.lastIndexOf(':');
                if (separator < 0) {
                    continue;
                }

                String factionCode = trimmed.substring(0, separator);
                int availability = parseAvailability(trimmed.substring(separator + 1));

                rows.add(new AvailabilityRow(factionCode,
                      factionNamer.apply(factionCode),
                      availability,
                      entry.startYear(),
                      entry.endYear(),
                      false));
            }
        }

        fireTableDataChanged();
    }

    /**
     * Reads the number out of an availability code, tolerating the +/- suffixes the file format allows. The tab does
     * not offer those, but a hand-edited file may carry them and must not be mangled by being opened.
     *
     * @param value the part of the code after the colon
     *
     * @return the availability value, or 0 if it cannot be read
     */
    private static int parseAvailability(String value) {
        StringBuilder digits = new StringBuilder();
        for (char character : value.toCharArray()) {
            if (Character.isDigit(character)) {
                digits.append(character);
            }
        }

        if (digits.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(digits.toString());
        } catch (NumberFormatException exception) {
            // Character.isDigit accepts non-ASCII digits, and a very long run of digits overflows an int. Either way
            // the value cannot be read, so return 0 per the method contract rather than failing the whole load.
            return 0;
        }
    }

    /**
     * Turns the rows back into the file's shape. Rows that share a year range are grouped into one entry, which is how
     * a hand-written file would look.
     *
     * @return the entries to store on the unit
     */
    public List<ForceGeneratorAvailability> toAvailabilityEntries() {
        Map<String, List<AvailabilityRow>> byYearRange = new LinkedHashMap<>();

        for (AvailabilityRow row : rows) {
            String rangeKey = row.fromYear() + "-" + row.toYear();
            byYearRange.computeIfAbsent(rangeKey, key -> new ArrayList<>()).add(row);
        }

        List<ForceGeneratorAvailability> entries = new ArrayList<>();
        for (List<AvailabilityRow> group : byYearRange.values()) {
            StringBuilder codes = new StringBuilder();
            for (AvailabilityRow row : group) {
                if (!codes.isEmpty()) {
                    codes.append(',');
                }
                codes.append(row.factionCode()).append(':').append(row.availability());
            }

            entries.add(new ForceGeneratorAvailability(group.getFirst().fromYear(),
                  group.getFirst().toYear(),
                  codes.toString()));
        }

        return entries;
    }
}
