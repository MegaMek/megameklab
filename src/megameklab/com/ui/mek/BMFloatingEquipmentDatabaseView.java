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
package megameklab.com.ui.mek;

import megameklab.com.ui.EntitySource;

import java.util.Collection;
import java.util.List;
import static megameklab.com.ui.util.EquipmentTableModel.*;

/**
 * An Equipment Database for Meks.
 * This table shows only few columns and is suitable for use in a detached dialog.
 */
class BMFloatingEquipmentDatabaseView extends BMEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_CRIT, COL_TECH, COL_TON);

    public BMFloatingEquipmentDatabaseView(EntitySource eSource) {
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
