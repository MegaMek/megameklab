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
package megameklab.ui.protoMek;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megamek.common.verifier.TestEntity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for ProtoMeks. This table shows many columns and is suitable for use in the Equipment Tab.
 */
class PMEquipmentDatabaseView extends AbstractEquipmentDatabaseView {
    private static final MMLogger logger = MMLogger.create(PMEquipmentDatabaseView.class);

    private final List<Integer> fluffColumns = List.of(
          COL_NAME,
          COL_TECH,
          COL_TECH_LEVEL,
          COL_TECH_RATING,
          COL_DATE_PROTOTYPE,
          COL_DATE_PRODUCTION,
          COL_DATE_COMMON,
          COL_DATE_EXTINCT,
          COL_DATE_REINTRODUCED,
          COL_COST);

    private final List<Integer> statsColumns = List.of(
          COL_NAME,
          COL_DAMAGE,
          COL_HEAT,
          COL_MEDIUM_RANGE,
          COL_RANGE,
          COL_SHOTS,
          COL_TECH,
          COL_BV,
          COL_TON,
          COL_REF);

    public PMEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        try {
            if ((equip instanceof MiscType) && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
                int location = TestEntity.getSystemWideLocation(eSource.getEntity());
                Mounted<?> mount = Mounted.createMounted(eSource.getEntity(), equip);
                getEntity().addEquipment(mount, location, false);
            } else {
                if (equip instanceof AmmoType) {
                    addProtoMekAmmo(equip, 1);
                    return;
                }
                Mounted<?> mount = Mounted.createMounted(eSource.getEntity(), equip);
                getEntity().addEquipment(mount, Entity.LOC_NONE, false);
                UnitUtil.removeHiddenAmmo(mount);
            }
        } catch (LocationFullException ex) {
            PopupMessages.showLocationFullError(this, equip.getName());
            logger.error("Location full while trying to add {}", equip.getName());
        }
    }

    private void addProtoMekAmmo(EquipmentType ammo, int shots) throws LocationFullException {
        Mounted<?> aMount = getProtoMek().getAmmo().stream()
              .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted<?> mount = Mounted.createMounted(getProtoMek(), ammo);
            getProtoMek().addEquipment(mount, ProtoMek.LOC_BODY, false);
            mount.setShotsLeft(shots);
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
