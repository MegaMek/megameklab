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
package megameklab.ui.battleArmor;

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

import java.util.Collection;
import java.util.List;
import static megameklab.ui.util.EquipmentTableModel.*;

/**
 * An Equipment Database for BattleArmor. This table shows many columns
 * and is suitable for use in the Equipment Tab.
 */
class BAEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

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
                    getBattleArmor().addEquipment(mount, BattleArmor.LOC_SQUAD,false);
                }
            } catch (LocationFullException ignored) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        if ((equipment instanceof MiscType)
                && (equipment.hasFlag(MiscType.F_BA_MANIPULATOR)
                || equipment.hasFlag(MiscType.F_PARTIAL_WING)
                || equipment.hasFlag(MiscType.F_JUMP_BOOSTER)
                || equipment.hasFlag(MiscType.F_MECHANICAL_JUMP_BOOSTER)
                || UnitUtil.isJumpJet(equipment))) {
            return false;
        }
        return super.shouldShow(equipment);
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
