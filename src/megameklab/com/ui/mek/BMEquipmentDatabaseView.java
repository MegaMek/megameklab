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
package megameklab.com.ui.mek;

import megamek.common.*;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;
import static megameklab.com.ui.util.EquipmentTableModel.*;
import static megameklab.com.ui.util.AbstractEquipmentDatabaseView.Toggles.*;

import java.util.EnumSet;
import java.util.List;


public class BMEquipmentDatabaseView extends AbstractEquipmentDatabaseView {

    private final List<Integer> allColumns = List.of(COL_NAME, COL_DAMAGE, COL_DIVISOR, COL_SPECIAL, COL_HEAT,
            COL_MRANGE, COL_RANGE, COL_SHOTS, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE, COL_DPRODUCTION,
            COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_CREW, COL_BV, COL_TON, COL_CRIT, COL_REF);

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST, COL_REF);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
            COL_SHOTS, COL_TECH, COL_BV, COL_TON, COL_CRIT, COL_REF);

    public BMEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
        updateVisibleColumns();
    }

    @Override
    protected void addEquipment(EquipmentType equip) {
        Mounted mount;
        boolean isMisc = equip instanceof MiscType;
        if (isMisc && equip.hasFlag(MiscType.F_TARGCOMP)) {
            if (!UnitUtil.hasTargComp(getMech())) {
                UnitUtil.updateTC(getMech(), equip);
            }
        } else if (isMisc && UnitUtil.isFixedLocationSpreadEquipment(equip)) {
            UnitUtil.createSpreadMounts(getMech(), equip);
        } else {
            try {
                mount = new Mounted(getMech(), equip);
                getMech().addEquipment(mount, Entity.LOC_NONE, false);
                if ((equip instanceof WeaponType) && equip.hasFlag(WeaponType.F_ONESHOT)) {
                    UnitUtil.removeOneShotAmmo(eSource.getEntity());
                }
            } catch (LocationFullException lfe) {
                // this can't happen, we add to Entity.LOC_NONE
            }
        }
    }

    @Override
    protected boolean shouldShow(EquipmentType eType) {
        if (UnitUtil.isHeatSink(eType) || UnitUtil.isJumpJet(eType)) {
            return false;
        }
        if ((eType instanceof MiscType) && (eType.hasFlag(MiscType.F_TSM)
                || eType.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                || eType.hasFlag(MiscType.F_SCM)
                || (eType.hasFlag(MiscType.F_MASC) && !eType.hasSubType(MiscType.S_SUPERCHARGER)))) {
            return false;
        }
        if (eType instanceof MiscType && eType.hasFlag(MiscType.F_TRACKS)) {
            if (getMech() instanceof QuadVee) {
                return false;
            } else if (eType.hasSubType(MiscType.S_QUADVEE_WHEELS)) {
                return false;
            }
        }
        if ((shouldShowEnergy(eType) || shouldShowMissile(eType) || shouldShowBallistic(eType) || shouldShowAmmo(eType)
                || shouldShowArtillery(eType) || shouldShowPhysical(eType) || shouldShowOther(eType))
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
        return showEnergyButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_ENERGY)
                || (wtype.hasFlag(WeaponType.F_PLASMA) && (wtype.getAmmoType() == AmmoType.T_PLASMA))));
    }

    private boolean shouldShowMissile(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showMissileButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && ((wtype.hasFlag(WeaponType.F_MISSILE)
                && (wtype.getAmmoType() != AmmoType.T_NA)) || (wtype.getAmmoType() == AmmoType.T_C3_REMOTE_SENSOR));
    }

    private boolean shouldShowBallistic(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showBallisticButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech()) && (wtype != null)
                && (wtype.hasFlag(WeaponType.F_BALLISTIC) && (wtype.getAmmoType() != AmmoType.T_NA));
    }

    private boolean shouldShowArtillery(EquipmentType eType) {
        WeaponType wtype = (eType instanceof WeaponType) ? (WeaponType) eType : null;
        return showArtilleryButton.isSelected() && UnitUtil.isMechWeapon(eType, getMech())
                && (wtype instanceof ArtilleryWeapon);
    }

    private boolean shouldShowPhysical(EquipmentType eType) {
        return showPhysicalButton.isSelected() && UnitUtil.isPhysicalWeapon(eType);
    }

    private boolean shouldShowAmmo(EquipmentType eType) {
        AmmoType atype = (eType instanceof AmmoType) ? (AmmoType) eType : null;
        return showAmmoButton.isSelected() && (atype != null) && UnitUtil.canUseAmmo(getMech(), atype, false);
    }

    private boolean shouldShowOther(EquipmentType eType) {
        return showOtherButton.isSelected() && UnitUtil.isMechEquipment(eType, getMech());
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
    protected EnumSet<Toggles> getUsedButtons() {
        return EnumSet.of(ENERGY, MISSILE, BALLISTIC, ARTILLERY, PHYSICAL, AMMO, OTHER, PROTOTYPE,
                ONE_SHOT, TORPEDOES, UNAVAILABLE);
    }

}
