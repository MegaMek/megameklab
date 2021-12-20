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
package megameklab.com.ui.supportVeh;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.util.List;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class SVEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> allColumns = List.of(COL_NAME, COL_DAMAGE, COL_DIVISOR, COL_SPECIAL, COL_HEAT,
            COL_MRANGE, COL_RANGE, COL_SHOTS, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE, COL_DPRODUCTION,
            COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_CREW, COL_BV, COL_TON, COL_CRIT, COL_REF);

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    public SVEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
        updateVisibleColumns();
    }

    @Override
    protected void addEquipment(EquipmentType equip) {
        //TODO: This contains code for ProtoMeks. Clear up
        Mounted mount;
        boolean isMisc = equip instanceof MiscType;
        try {
            if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
                if (!UnitUtil.hasTargComp(eSource.getEntity())) {
                    UnitUtil.updateTC(eSource.getEntity(), equip);
                }
            } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
                if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_MECH)) {
                    UnitUtil.createSpreadMounts(getMech(), equip);
                } else {
                    int location = TestEntity.getSystemWideLocation(eSource.getEntity());
                    mount = new Mounted(eSource.getEntity(), equip);
                    eSource.getEntity().addEquipment(mount, location, false);
                }
            } else {
//                int count = (Integer)spnAddCount.getValue();
                int count = 1;
                if (equip instanceof AmmoType) {
                    if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_PROTOMECH)) {
                        addProtomechAmmo(equip, count);
                        return;
                    } else if (eSource.getEntity().usesWeaponBays()) {
                        addLargeCraftAmmo(equip, count);
                        return;
                    } else if (eSource.getEntity().isAero()) {
                        addBodyAmmo(equip, count, Aero.LOC_FUSELAGE);
                        return;
                    } else if (eSource.getEntity() instanceof Tank) {
                        addBodyAmmo(equip, count, Tank.LOC_BODY);
                        return;
                    }
                }
                for (int i = 0; i < count; i++) {
                    mount = new Mounted(eSource.getEntity(), equip);
                    if ((eSource.getEntity().isFighter()
                            && equip instanceof MiscType) && equip.hasFlag(MiscType.F_BLUE_SHIELD)) {
                        getAero().addEquipment(mount, Aero.LOC_FUSELAGE, false);
                    } else {
                        eSource.getEntity().addEquipment(mount, Entity.LOC_NONE, false);
                    }
                    if ((equip instanceof WeaponType) && (equip.hasFlag(WeaponType.F_ONESHOT)
                            || (((WeaponType) equip).getAmmoType() == AmmoType.T_INFANTRY))) {
                        UnitUtil.removeOneShotAmmo(eSource.getEntity());
                    }
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
        Mounted aMount = getProtomech().getAmmo().stream()
                .filter(m -> ammo.equals(m.getType())).findFirst().orElse(null);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + shots);
        } else {
            Mounted mount = new Mounted(getProtomech(), ammo);
            getProtomech().addEquipment(mount, Protomech.LOC_BODY, false);
            mount.setShotsLeft(shots);
        }
    }

    private void addLargeCraftAmmo(EquipmentType ammo, int count) throws LocationFullException {
        Mounted aMount = UnitUtil.findUnallocatedAmmo(getAero(), ammo);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + ((AmmoType)ammo).getShots() * count);
        } else {
            Mounted mount = new Mounted(getAero(), ammo);
            mount.setShotsLeft(((AmmoType)ammo).getShots() * count);
            getAero().addEquipment(mount, Entity.LOC_NONE, false);
        }
    }

    /**
     * Adds ammo to the correct location (body/fuselage) for aerospace and vehicles
     * @param equip The {@link AmmoType} to add
     * @param count The number of slots of ammo (usually tons)
     * @param loc   The location to add it
     * @throws LocationFullException If the location is full.
     */
    private void addBodyAmmo(EquipmentType equip, int count, int loc) throws LocationFullException {
        for (int i = 0; i < count; i++) {
            Mounted mount = new Mounted(getEntity(), equip);
            getEntity().addEquipment(mount, loc, false);
//            equipmentList.addCrit(mount);
        }
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }


}
