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
package megameklab.ui.largeAero;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Entity;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for all Large Aerospace units (JumpShips, WarShips, DropShips, SmallCraft and others) This
 * table shows many columns and is suitable for use in the Equipment Tab.
 */
class LAEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TECH_LEVEL,
          COL_TECH_RATING, COL_DATE_PROTOTYPE,
          COL_DATE_PRODUCTION, COL_DATE_COMMON, COL_DATE_EXTINCT, COL_DATE_REINTRODUCED, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_RANGE,
          COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_REF);

    LAEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        return !(equipment instanceof BayWeapon) && super.shouldShow(equipment);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        Mounted<?> mount;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGETING_COMPUTER)) {
            if (!UnitUtil.hasTargComp(getAero())) {
                UnitUtil.updateTC(getAero(), equip);
            }
        } else {
            if (equip instanceof AmmoType) {
                Mounted<?> aMount = UnitUtil.findUnallocatedAmmo(getAero(), equip);
                if ((null != aMount) && getAero().usesWeaponBays()) {
                    aMount.setShotsLeft(aMount.getUsableShotsLeft() + ((AmmoType) equip).getShots() * count);
                } else {
                    mount = Mounted.createMounted(getAero(), equip);
                    mount.setShotsLeft(((AmmoType) equip).getShots() * count);
                    try {
                        getAero().addEquipment(mount, Entity.LOC_NONE, false);
                    } catch (LocationFullException ignored) {
                        // this can't happen, we add to Entity.LOC_NONE
                    }
                }
            } else {
                try {
                    for (int i = 0; i < count; i++) {
                        mount = Mounted.createMounted(getAero(), equip);
                        UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
                        getAero().addEquipment(mount, Entity.LOC_NONE, false);
                        UnitUtil.removeHiddenAmmo(mount);
                    }
                } catch (LocationFullException ignored) {
                    // Shouldn't happen when adding to LOC_NONE
                }
            }
        }
    }

    @Override
    protected boolean useAddMultipleButton() {
        return true;
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }
}
