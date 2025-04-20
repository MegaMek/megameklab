/*
 * Copyright (C) 2022-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.battleArmor;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.BattleArmor;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for BattleArmor. This table shows many columns and is suitable for use in the Equipment Tab.
 */
class BAEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME,
          COL_TECH,
          COL_TLEVEL,
          COL_TRATING,
          COL_DPROTOTYPE,
          COL_DPRODUCTION,
          COL_DCOMMON,
          COL_DEXTINCT,
          COL_DREINTRO,
          COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME,
          COL_DAMAGE,
          COL_MRANGE,
          COL_RANGE,
          COL_SHOTS,
          COL_TECH,
          COL_BV,
          COL_TON,
          COL_CRIT,
          COL_REF);

    public BAEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        if ((equip instanceof MiscType) && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getBattleArmor())) {
                UnitUtil.updateTC(getBattleArmor(), equip);
            }
        } else {
            try {
                if (BattleArmorUtil.isBAMultiMount(equip)) {
                    for (int t = 1; t <= getBattleArmor().getTroopers(); t++) {
                        Mounted<?> mount = Mounted.createMounted(getBattleArmor(), equip);
                        mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                        getBattleArmor().addEquipment(mount, t, false);
                    }
                } else {
                    Mounted<?> mount = Mounted.createMounted(getBattleArmor(), equip);
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                    getBattleArmor().addEquipment(mount, BattleArmor.LOC_SQUAD, false);
                }
            } catch (LocationFullException ignored) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        if ((equipment instanceof MiscType) &&
                  (equipment.hasFlag(MiscType.F_BA_MANIPULATOR) ||
                         equipment.hasFlag(MiscType.F_PARTIAL_WING) ||
                         equipment.hasFlag(MiscType.F_JUMP_BOOSTER) ||
                         equipment.hasFlag(MiscType.F_MECHANICAL_JUMP_BOOSTER) ||
                         UnitUtil.isJumpJet(equipment))) {
            return false;
        }
        return super.shouldShow(equipment);
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
