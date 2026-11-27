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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing.reference;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megameklab.printing.PrintRecordSheet;

/**
 * Condensed lookup for the hit locations and cluster rack sizes of the units on a ProtoMek sheet.
 */
public class ProtoMekLocationAndClusterTable extends ClusterHitsTable {
    private enum Chassis {
        BIPED("biped"), GLIDER("glider"), QUAD("quad");

        private final String labelKey;

        Chassis(String labelKey) {
            this.labelKey = labelKey;
        }

        static Chassis of(ProtoMek proto) {
            if (proto.isQuad()) {
                return QUAD;
            }
            return proto.isGlider() ? GLIDER : BIPED;
        }
    }

    private enum MainGun {
        INSTALLED, ABSENT, MIXED
    }

    public ProtoMekLocationAndClusterTable(PrintRecordSheet sheet, List<? extends Entity> entities) {
        super(sheet, entities, true);
    }

    @Override
    public boolean required() {
        return true;
    }

    @Override
    protected void addTable(List<? extends Entity> entities) {
        Map<Chassis, MainGun> mainGuns = new EnumMap<>(Chassis.class);
        for (Entity entity : entities) {
            if (!(entity instanceof ProtoMek proto)) {
                throw new IllegalArgumentException("ProtoMek hit location tables require ProtoMek units");
            }
            MainGun mainGun = proto.hasMainGun() ? MainGun.INSTALLED : MainGun.ABSENT;
            mainGuns.merge(Chassis.of(proto), mainGun, (a, b) -> a == b ? a : MainGun.MIXED);
        }

        List<String> headers = new ArrayList<>();
        headers.add(bundle.getString("2d6"));
        for (Chassis chassis : mainGuns.keySet()) {
            String label = bundle.getString(chassis.labelKey);
            headers.add(label + "\n" + bundle.getString("regular"));
            headers.add(label + "\n" + bundle.getString("special"));
        }
        int locationColumns = headers.size() - 1;
        for (int size : clusterSizes) {
            headers.add(String.valueOf(size));
        }
        setColOffsets(columnOffsets(locationColumns, headers.size()));
        setHeaders(headers);

        for (int roll = 2; roll <= 12; roll++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(roll));
            for (var entry : mainGuns.entrySet()) {
                row.add(bundle.getString(locationKey(roll, entry.getKey(), entry.getValue(), false)));
                row.add(bundle.getString(locationKey(roll, entry.getKey(), entry.getValue(), true)));
            }
            addClusterResults(row, roll);
            addRow(row);
        }
        if (mainGuns.containsValue(MainGun.MIXED)) {
            addNote(bundle.getString("mainGun.note"));
        }
        if (!clusterSizes.isEmpty()) {
            addNotes(entities);
        }
    }

    private static List<Double> columnOffsets(int locationColumns, int columns) {
        // Location names need more space than the roll and rack numbers.
        double numericWidth = 0.9 / (columns + 1.5 * locationColumns);
        double edge = 0.05;
        List<Double> offsets = new ArrayList<>();
        for (int col = 0; col < columns; col++) {
            double width = (col > 0 && col <= locationColumns) ? 2.5 * numericWidth : numericWidth;
            offsets.add(edge + width / 2);
            edge += width;
        }
        return offsets;
    }

    private static String locationKey(int roll, Chassis chassis, MainGun mainGun, boolean special) {
        if (roll == 2) {
            return switch (mainGun) {
                case INSTALLED -> "mainGun";
                case ABSENT -> "torso";
                case MIXED -> "mainGunOrTorso";
            };
        }
        String key = special ? ProtoMekSpecialHitLocation.locationKey(roll) : regularLocationKey(roll, chassis);
        return (chassis == Chassis.QUAD && ("leftArm".equals(key) || "rightArm".equals(key))) ? "legs" : key;
    }

    private static String regularLocationKey(int roll, Chassis chassis) {
        return switch (roll) {
            case 3, 11 -> chassis == Chassis.GLIDER ? "wings" : "nearMiss";
            case 4 -> "rightArm";
            case 5, 9 -> "legs";
            case 6, 7, 8 -> "torso";
            case 10 -> "leftArm";
            case 12 -> "head";
            default -> throw new IllegalArgumentException("Invalid 2D6 roll: " + roll);
        };
    }
}
