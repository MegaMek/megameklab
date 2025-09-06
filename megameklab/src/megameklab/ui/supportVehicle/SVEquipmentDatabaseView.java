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
package megameklab.ui.supportVehicle;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megamek.common.verifier.TestEntity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * An Equipment Database for Support Vehicles. This table shows many columns and is suitable for use in the Equipment
 * Tab.
 */
class SVEquipmentDatabaseView extends AbstractEquipmentDatabaseView {
    private static final MMLogger logger = MMLogger.create(SVEquipmentDatabaseView.class);

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
          COL_CRIT,
          COL_REF);

    public SVEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        // TODO: This contains code for Large Craft. Not sure if needed for SV
        Mounted<?> mount;
        boolean isMisc = equip instanceof MiscType;
        try {
            if (isMisc && equip.hasFlag(MiscType.F_TARGETING_COMPUTER)) {
                if (!UnitUtil.hasTargComp(getEntity())) {
                    UnitUtil.updateTC(getEntity(), equip);
                }
            } else if (isMisc && equip.is("ISMastMount")) {
                mount = Mounted.createMounted(getEntity(), equip);
                getEntity().addEquipment(mount, VTOL.LOC_ROTOR, false);
            } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
                int location = TestEntity.getSystemWideLocation(getEntity());
                mount = Mounted.createMounted(getEntity(), equip);
                getEntity().addEquipment(mount, location, false);
            } else {
                if (equip instanceof AmmoType) {
                    if (getEntity().usesWeaponBays()) {
                        addLargeCraftAmmo(equip, count);
                        return;
                    } else if (getEntity().isAero()) {
                        addBodyAmmo(equip, count, Aero.LOC_FUSELAGE);
                        return;
                    } else if (getEntity() instanceof Tank) {
                        addBodyAmmo(equip, count, Tank.LOC_BODY);
                        return;
                    }
                }
                for (int i = 0; i < count; i++) {
                    mount = Mounted.createMounted(getEntity(), equip);
                    UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
                    if ((getEntity().isFighter()
                          && (equip instanceof MiscType)) && equip.hasFlag(MiscType.F_BLUE_SHIELD)) {
                        getAero().addEquipment(mount, Aero.LOC_FUSELAGE, false);
                    } else {
                        getEntity().addEquipment(mount, Entity.LOC_NONE, false);
                    }
                    UnitUtil.removeHiddenAmmo(mount);
                }
            }
        } catch (LocationFullException ex) {
            logger.error("Location full while trying to add {}", equip.getName());
            JOptionPane.showMessageDialog(
                  this, "Could not add " + equip.getName(),
                  "Location Full", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLargeCraftAmmo(EquipmentType ammo, int count) throws LocationFullException {
        Mounted<?> aMount = UnitUtil.findUnallocatedAmmo(getAero(), ammo);
        if (null != aMount) {
            aMount.setShotsLeft(aMount.getUsableShotsLeft() + ((AmmoType) ammo).getShots() * count);
        } else {
            Mounted<?> mount = Mounted.createMounted(getAero(), ammo);
            mount.setShotsLeft(((AmmoType) ammo).getShots() * count);
            getAero().addEquipment(mount, Entity.LOC_NONE, false);
        }
    }

    /**
     * Adds ammo to the correct location (body/fuselage) for aerospace and vehicles
     *
     * @param equip The {@link AmmoType} to add
     * @param count The number of slots of ammo (usually tons)
     * @param loc   The location to add it
     *
     * @throws LocationFullException If the location is full.
     */
    private void addBodyAmmo(EquipmentType equip, int count, int loc) throws LocationFullException {
        for (int i = 0; i < count; i++) {
            Mounted<?> mount = Mounted.createMounted(getEntity(), equip);
            getEntity().addEquipment(mount, loc, false);
        }
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

}
