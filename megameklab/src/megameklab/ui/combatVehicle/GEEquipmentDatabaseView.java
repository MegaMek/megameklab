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

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.EquipmentType;
import megamek.common.GunEmplacement;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.MiscTypeFlag;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for Gun Emplacements. This table shows many columns and is suitable for use in the Equipment
 * Tab.
 */
class GEEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    GEEquipmentDatabaseView(EntitySource eSource) {
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
                getGunEmplacement().addEquipment(mount, GunEmplacement.LOC_GUNS, false);
                UnitUtil.removeHiddenAmmo(mount);
                UnitUtil.changeMountStatus(getTank(), mount, GunEmplacement.LOC_GUNS, -1, false);
            } catch (LocationFullException ignored) {
                // GE have no limit
            }
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        if ((equipment instanceof MiscType)
                  && (equipment.hasFlag(MiscTypeFlag.F_HARJEL)
                            || equipment.hasFlag(MiscType.F_HARJEL_II)
                            || equipment.hasFlag(MiscType.F_HARJEL_III)
                            || equipment.hasFlag(MiscType.F_MODULAR_ARMOR)
                            || equipment.hasFlag(MiscType.F_SPONSON_TURRET)
                            || equipment.hasFlag(MiscType.F_PINTLE_TURRET)
                            || equipment.hasFlag(MiscType.F_JUMP_BOOSTER)
                            || equipment.hasFlag(MiscType.F_ARMORED_MOTIVE_SYSTEM)
                            || equipment.hasFlag(MiscType.F_MECHANICAL_JUMP_BOOSTER)
                            || UnitUtil.isJumpJet(equipment))) {
            return false;
        }
        return super.shouldShow(equipment);
    }
}
