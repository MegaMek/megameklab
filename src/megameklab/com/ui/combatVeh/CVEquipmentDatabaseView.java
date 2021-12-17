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
package megameklab.com.ui.combatVeh;

import megamek.common.*;
import megamek.common.verifier.TestTank;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.EnumSet;
import java.util.List;

import static megameklab.com.ui.util.AbstractEquipmentDatabaseView.Toggles.*;
import static megameklab.com.ui.util.EquipmentTableModel.*;

public class CVEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> allColumns = List.of(COL_NAME, COL_DAMAGE, COL_DIVISOR, COL_SPECIAL, COL_HEAT,
            COL_MRANGE, COL_RANGE, COL_SHOTS, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE, COL_DPRODUCTION,
            COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_CREW, COL_BV, COL_TON, COL_CRIT, COL_REF);

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    public CVEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
        updateVisibleColumns();
    }

    @Override
    protected void addEquipment(EquipmentType equip) {
        boolean success = false;
        Mounted mount = null;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getTank())) {
                mount = UnitUtil.updateTC(getTank(), equip);
                success = mount != null;
            }
        } else {
            try {
                mount = new Mounted(getTank(), equip);
                int loc = Entity.LOC_NONE;
                if (isMisc && equip.hasFlag(MiscType.F_MAST_MOUNT)) {
                    loc = VTOL.LOC_ROTOR;
                } else if (TestTank.isBodyEquipment(equip)) {
                    loc = Tank.LOC_BODY;
                }
                getTank().addEquipment(mount, loc, false);
                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
                }
                success = true;
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
        if (success) {
//            equipmentList.addCrit(mount);
        }
    }

    @Override
    protected boolean shouldShow(EquipmentType eType) {
        if (UnitUtil.isHeatSink(eType) || UnitUtil.isJumpJet(eType)) {
            return false;
        }
        if ((shouldShowEnergy(eType) || shouldShowMissile(eType) || shouldShowBallistic(eType) || shouldShowAmmo(eType)
                || shouldShowArtillery(eType) || shouldShowOther(eType))
                && !shouldHidePrototype(eType) && !shouldHideOneShot(eType) && !shouldHideTorpedo(eType)) {

            if (!eSource.getTechManager().isLegal(eType) && hideUnavailButton.isSelected()) {
                return false;
            }
            if (txtFilter.getText().length() > 0) {
                String text = txtFilter.getText();
                return eType.getName().toLowerCase().contains(text.toLowerCase());
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean shouldShowEnergy(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showEnergyButton.isSelected() && UnitUtil.isTankWeapon(eType, getTank()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_ENERGY)
                || (wtype.hasFlag(WeaponType.F_PLASMA) && (wtype.getAmmoType() == AmmoType.T_PLASMA))));
    }

    private boolean shouldShowMissile(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showMissileButton.isSelected() && UnitUtil.isTankWeapon(eType, getTank()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_MISSILE)
                && (wtype.getAmmoType() != AmmoType.T_NA)) || (wtype.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR));
    }

    private boolean shouldShowBallistic(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showBallisticButton.isSelected() && UnitUtil.isTankWeapon(eType, getTank()) && (wtype != null)
                && (wtype.hasFlag(WeaponType.F_BALLISTIC) && (wtype.getAmmoType() != AmmoType.T_NA));
    }

    private boolean shouldShowArtillery(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showArtilleryButton.isSelected() && UnitUtil.isTankWeapon(eType, getTank())
                && (wtype instanceof ArtilleryWeapon);
    }

    private boolean shouldShowAmmo(EquipmentType eType) {
        AmmoType atype = (eType instanceof AmmoType) ? (AmmoType) eType : null;
        return showAmmoButton.isSelected() && (atype != null) && UnitUtil.canUseAmmo(getTank(), atype, false);
    }

    private boolean shouldShowOther(EquipmentType eType) {
        return showOtherButton.isSelected() && UnitUtil.isTankMiscEquipment(eType, getTank());
    }

    private boolean shouldHidePrototype(EquipmentType eType) {
        return hideProtoButton.isSelected() && (eType instanceof WeaponType) && eType.hasFlag(WeaponType.F_PROTOTYPE);
    }

    private boolean shouldHideOneShot(EquipmentType eType) {
        return hideOneShotButton.isSelected() && (eType instanceof WeaponType) && eType.hasFlag(WeaponType.F_ONESHOT);
    }

    private boolean shouldHideTorpedo(EquipmentType eType) {
        return hideTorpedoButton.isSelected() && (eType instanceof WeaponType)
                && (((WeaponType) eType).getAmmoType() == AmmoType.T_LRM_TORPEDO
                || ((WeaponType) eType).getAmmoType() == AmmoType.T_SRM_TORPEDO);
    }

    @Override
    protected void updateVisibleColumns() {
        setColumnsVisible(allColumns, false);
        setColumnsVisible(tableMode ? statsColumns : fluffColumns, true);
    }

    @Override
    protected EnumSet<AbstractEquipmentDatabaseView.Toggles> getUsedButtons() {
        return EnumSet.of(ENERGY, MISSILE, BALLISTIC, ARTILLERY, AMMO, OTHER, PROTOTYPE,
                ONE_SHOT, TORPEDOES, UNAVAILABLE);
    }
}
