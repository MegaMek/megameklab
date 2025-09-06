/*
 * Copyright (C) 2021-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.supportVehicle;

import static megameklab.ui.util.EquipmentTableModel.COL_HEAT;
import static megameklab.ui.util.EquipmentTableModel.COL_NAME;
import static megameklab.ui.util.EquipmentTableModel.COL_TECH;
import static megameklab.ui.util.EquipmentTableModel.COL_TON;

import java.util.Collection;
import java.util.List;

import megameklab.ui.EntitySource;

/**
 * An Equipment Database for Support Vehicles. This table shows only few columns and is suitable for use in a detached
 * dialog.
 */
class SVFloatingEquipmentDatabaseView extends SVEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_HEAT, COL_TECH, COL_TON);

    public SVFloatingEquipmentDatabaseView(EntitySource eSource) {
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
