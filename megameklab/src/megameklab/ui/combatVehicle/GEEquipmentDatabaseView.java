/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.combatVehicle;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.GunEmplacement;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.enums.MiscTypeFlag;
import megamek.common.exceptions.LocationFullException;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for Gun Emplacements. This table shows many columns and is suitable for use in the Equipment
 * Tab.
 */
class GEEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TECH_LEVEL,
          COL_TECH_RATING, COL_DATE_PROTOTYPE,
          COL_DATE_PRODUCTION, COL_DATE_COMMON, COL_DATE_EXTINCT, COL_DATE_REINTRODUCED, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MEDIUM_RANGE, COL_RANGE,
          COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    GEEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        Mounted<?> mount;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGETING_COMPUTER)) {
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
