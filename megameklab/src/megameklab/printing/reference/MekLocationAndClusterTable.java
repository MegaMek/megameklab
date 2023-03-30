/*
 * MegaMekLab - Copyright (C) 2023 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.printing.reference;

import megamek.common.*;
import megameklab.printing.PrintEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adds hit location and cluster tables to the fluff image region instead of shrinking sheet and
 * adding them to the side.
 */
public class MekLocationAndClusterTable extends ClusterHitsTable {

    public MekLocationAndClusterTable(PrintEntity sheet) {
        super(sheet);
    }

    @Override
    protected void addTable(Entity entity) {
        List<Double> offsets = new ArrayList<>();
        double spacing = 0.9 / (clusterSizes.size() + 4);
        for (double o = 0.05 + spacing / 2.0; o <= 0.95; o += spacing) {
            offsets.add(o);
        }
        setColOffsets(offsets);
        List<String> headers = new ArrayList<>();
        headers.add(bundle.getString("dieRoll2d6"));
        headers.add(bundle.getString("leftSide"));
        headers.add(bundle.getString("frontRear"));
        headers.add(bundle.getString("rightSide"));
        for (int size : clusterSizes) {
            headers.add(String.valueOf(size));
        }
        setHeaders(headers);
        addRows(entity);
        addNotes(entity);
    }

    private void addRows(Entity entity) {
        String[][] locations;
        if (entity instanceof TripodMech) {
            locations = MekHitLocation.TRIPOD_LOCATIONS;
        } else if (entity instanceof QuadMech) {
            locations = MekHitLocation.QUAD_LOCATIONS;
        } else {
            locations = MekHitLocation.BIPED_LOCATIONS;
        }
        for (int roll = 2; roll <= 12; roll++) {
            List<String> row = new ArrayList<>(Arrays.asList(locations[roll - 2]));
            for (int size : clusterSizes) {
                row.add(String.valueOf(Compute.calculateClusterHitTableAmount(roll, size)));
            }
            addRow(row);
        }
    }

    @Override
    protected void addNotes(Entity entity) {
        addNote(bundle.getString("tacNote"));
        if (entity instanceof TripodMech) {
            addNote(bundle.getString("tripodLegNote"));
        }
        super.addNotes(entity);
    }
}
