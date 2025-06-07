/*
 * MegaMekLab
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This program is  free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package megameklab.ui.combatVehicle;

import static megameklab.ui.util.EquipmentTableModel.COL_HEAT;
import static megameklab.ui.util.EquipmentTableModel.COL_NAME;
import static megameklab.ui.util.EquipmentTableModel.COL_TECH;
import static megameklab.ui.util.EquipmentTableModel.COL_TON;

import java.util.Collection;
import java.util.List;

import megameklab.ui.EntitySource;

/**
 * An Equipment Database for Combat Vehicles.
 * This table shows only few columns and is suitable for use in a detached dialog.
 */
class GEFloatingEquipmentDatabaseView extends GEEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_TECH, COL_TON);

    public GEFloatingEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return columns;
    }

    @Override
    protected boolean useSwitchTableColumns() {
        return false;
    }

    @Override
    protected boolean useTextFilter() {
        return false;
    }
}
