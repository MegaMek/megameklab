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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.handheldWeapon;

import static megameklab.ui.util.EquipmentTableModel.*;

import java.util.Collection;
import java.util.List;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.HandheldWeapon;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.BipedMek;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.other.innerSphere.ISAPDS;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class HHWEquipmentDatabaseView extends AbstractEquipmentDatabaseView {
    protected HHWEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TECH_LEVEL,
          COL_TECH_RATING, COL_DATE_PROTOTYPE,
          COL_DATE_PRODUCTION, COL_DATE_COMMON, COL_DATE_EXTINCT, COL_DATE_REINTRODUCED, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MEDIUM_RANGE, COL_RANGE,
          COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_REF);

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return tableMode ? statsColumns : fluffColumns;
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        Mounted<?> mount = Mounted.createMounted(getEntity(), equip);
        UnitUtil.setVariableSizeMiscTypeMinimumSize(mount);
        if (mount instanceof AmmoMounted am) {
            var shots = am.getType().getShots();
            am.setByShot(true);
            am.setOriginalShots(shots);
            am.setShotsLeft(shots);
            am.setAmmoCapacity(shots);
        }

        try {
            getEntity().addEquipment(mount, HandheldWeapon.LOC_GUN, false);
            // Link weapons with weapon enhancements (artemis etc)
            UnitUtil.changeMountStatus(getEntity(), mount, HandheldWeapon.LOC_GUN, HandheldWeapon.LOC_NONE, false);
            UnitUtil.removeHiddenAmmo(mount);
        } catch (LocationFullException e) {
            // Shouldn't happen, panic if it does.
            throw new RuntimeException(e);
        }

    }

    @Override
    protected boolean shouldShow(EquipmentType eq) {
        var valid = false;

        if (eq instanceof WeaponType wt) {
            // AMS has type PB so it's allowed
            // Except APDS has type P*D*, whatever that means.
            if (UnitUtil.isAMS(wt) && !(wt instanceof ISAPDS)) {
                return super.shouldShow(eq);
            }

            // isMekWeapon/isMekEquipment consider some WeaponTypes to be Equipment rather than a Weapon, and the
            // functions are mutually exclusive, so we check both to determine if the type is valid for a mek.
            if (!MekUtil.isMekWeapon(wt, new BipedMek()) && !MekUtil.isMekEquipment(wt, new BipedMek())) {
                return false;
            }

            var ammo = wt.getAmmoType();
            if (ammo == AmmoType.AmmoTypeEnum.GAUSS_HEAVY || ammo == AmmoType.AmmoTypeEnum.IGAUSS_HEAVY) {
                return false;
            }

            //  Items legal for meks to mount and belonging to one or more of the following types: AE, DB, DE, M,
            //  P, PB. Also allowed to are Mine Dispensers and TAG (including Light TAG) --TO:AUE
            valid = (wt instanceof ArtilleryWeapon || wt instanceof ArtilleryCannonWeapon)
                  || wt.hasAnyFlag(WeaponType.F_ENERGY,
                  WeaponType.F_PLASMA,
                  WeaponType.F_BALLISTIC,
                  WeaponType.F_MISSILE,
                  WeaponType.F_B_POD,
                  WeaponType.F_TAG);
        } else if (eq instanceof MiscType mt) {
            if (mt.hasFlag(MiscType.F_CLUB)) {
                if (mt.getTonnage(null) == EquipmentType.TONNAGE_VARIABLE) {
                    return false;
                }
                // These weapons have constant damage
                // Assuming vibroblades are out.
                valid = mt.hasAnySubType(MiscType.S_PILE_DRIVER,
                      MiscType.S_FLAIL,
                      MiscType.S_DUAL_SAW,
                      MiscType.S_CHAIN_WHIP,
                      MiscType.S_BACKHOE,
                      MiscType.S_MINING_DRILL,
                      MiscType.S_WRECKING_BALL,
                      MiscType.S_BUZZSAW,
                      MiscType.S_CHAIN_WHIP);
            } else {
                // Mine Dispensers are allowed explicitly, A-Pods have type PB, Weapon Enhancements are Artemis and PPC Caps and such
                valid = mt.hasAnyFlag(MiscType.F_VEHICLE_MINE_DISPENSER,
                      MiscType.F_AP_POD,
                      MiscType.F_WEAPON_ENHANCEMENT);
            }
        } else if (eq instanceof AmmoType at) {
            valid = !(at.getAmmoType() == AmmoType.AmmoTypeEnum.COOLANT_POD);
        }

        return valid && super.shouldShow(eq);
    }
}
