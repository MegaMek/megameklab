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
package megameklab.ui.fighterAero;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for Aerospace and Conventional Fighters. This table shows many columns and is suitable for use
 * in the Equipment Tab.
 */
class ASEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

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
          COL_HEAT,
          COL_RANGE,
          COL_SHOTS,
          COL_TECH,
          COL_BV,
          COL_TON,
          COL_REF);

    ASEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        if ((equip instanceof MiscType) && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getAero())) {
                UnitUtil.updateTC(getAero(), equip);
            }
        } else {
            try {
                Mounted<?> mount = Mounted.createMounted(getAero(), equip);
                UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
                int location = (equip instanceof AmmoType) ? Aero.LOC_FUSELAGE : Aero.LOC_NONE;
                getAero().addEquipment(mount, location, false);
                UnitUtil.removeHiddenAmmo(mount);
            } catch (LocationFullException ignored) {
                // location maximum is currently checked in menus and drag-and-drop
            }
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }
}
