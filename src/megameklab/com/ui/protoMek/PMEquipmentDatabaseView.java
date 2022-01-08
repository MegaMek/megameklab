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
package megameklab.com.ui.protoMek;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

/**
 * An Equipment Database for ProtoMeks. This table shows many columns
 * and is suitable for use in the Equipment Tab.
 */
class PMEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_REF);

    public PMEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        try {
            if ((equip instanceof MiscType) && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
                int location = TestEntity.getSystemWideLocation(eSource.getEntity());
                Mounted mount = new Mounted(eSource.getEntity(), equip);
                eSource.getEntity().addEquipment(mount, location, false);
            } else {
                if (equip instanceof AmmoType) {
                    addProtomechAmmo(equip, 1);
                    return;
                }
                Mounted mount = new Mounted(eSource.getEntity(), equip);
                eSource.getEntity().addEquipment(mount, Entity.LOC_NONE, false);
                if ((equip instanceof WeaponType) && (equip.hasFlag(WeaponType.F_ONESHOT)
                        || (((WeaponType) equip).getAmmoType() == AmmoType.T_INFANTRY))) {
                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
                }
            }
        } catch (LocationFullException ex) {
            LogManager.getLogger().error("Location full while trying to add " + equip.getName());
            JOptionPane.showMessageDialog(
                    this,"Could not add " + equip.getName(),
                    "Location Full", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProtomechAmmo(EquipmentType ammo, int shots) throws LocationFullException {
        Mounted aMount = getProtoMek().getAmmo().stream()
                .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted mount = new Mounted(getProtoMek(), ammo);
            getProtoMek().addEquipment(mount, Protomech.LOC_BODY, false);
            mount.setShotsLeft(shots);
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
