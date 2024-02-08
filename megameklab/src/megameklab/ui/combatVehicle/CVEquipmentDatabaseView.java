/*
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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

import megamek.common.*;
import megamek.common.verifier.TestTank;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

import java.util.Collection;
import java.util.List;

import static megameklab.ui.util.EquipmentTableModel.*;

/**
 * An Equipment Database for Combat Vehicles. This table shows many columns
 * and is suitable for use in the Equipment Tab.
 */
class CVEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    CVEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        Mounted<?> mount;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getTank())) {
                UnitUtil.updateTC(getTank(), equip);
            }
        } else {
            try {
                mount = Mounted.createMounted(getTank(), equip);
                UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
                int loc = Entity.LOC_NONE;
                if (isMisc && equip.hasFlag(MiscType.F_MAST_MOUNT)) {
                    loc = VTOL.LOC_ROTOR;
                } else if (TestTank.isBodyEquipment(equip)) {
                    loc = Tank.LOC_BODY;
                }
                getTank().addEquipment(mount, loc, false);
                UnitUtil.removeHiddenAmmo(mount);
            } catch (LocationFullException ignored) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
