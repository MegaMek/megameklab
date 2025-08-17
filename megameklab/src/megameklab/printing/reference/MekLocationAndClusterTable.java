/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.printing.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import megamek.common.compute.Compute;
import megamek.common.units.Entity;
import megamek.common.units.QuadMek;
import megamek.common.units.TripodMek;
import megameklab.printing.PrintEntity;

/**
 * Adds hit location and cluster tables to the fluff image region instead of shrinking sheet and adding them to the
 * side.
 */
public class MekLocationAndClusterTable extends ClusterHitsTable {

    public MekLocationAndClusterTable(PrintEntity sheet) {
        super(sheet, true);
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
        if (entity instanceof TripodMek) {
            locations = MekHitLocation.TRIPOD_LOCATIONS;
        } else if (entity instanceof QuadMek) {
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
        if (entity instanceof TripodMek) {
            addNote(bundle.getString("tripodLegNote"));
        }
        super.addNotes(entity);
    }
}
