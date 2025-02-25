package megameklab.ui.handheldWeapon;

import megamek.common.*;
import megamek.common.equipment.AmmoMounted;
import megamek.common.weapons.artillery.ArtilleryCannonWeapon;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.other.ISAPDS;
import megameklab.ui.EntitySource;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

import java.util.Collection;
import java.util.List;

import static megameklab.ui.util.EquipmentTableModel.*;
import static megameklab.ui.util.EquipmentTableModel.COL_COST;

public class HHWEquipmentDatabaseView extends AbstractEquipmentDatabaseView {
    protected HHWEquipmentDatabaseView(EntitySource eSource) {
        super(eSource);
    }

    private final List<Integer> fluffColumns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
        COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    private final List<Integer> statsColumns = List.of(COL_NAME, COL_DAMAGE, COL_HEAT, COL_MRANGE, COL_RANGE,
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
                valid = true;
            } else if (!MekUtil.isMekWeapon(wt, new BipedMek())) {
                return false;
            }

            var ammo = wt.getAmmoType();
            if (ammo == AmmoType.T_GAUSS_HEAVY || ammo == AmmoType.T_IGAUSS_HEAVY) {
                return false;
            }

            //  Items legal for ’Mechs to mount and belonging to one or more of the following types: AE, DB, DE, M,
            //  P, PB. Also allowed are Mine Dispensers and TAG (including Light TAG) --TO:AUE
            valid = (wt instanceof ArtilleryWeapon || wt instanceof ArtilleryCannonWeapon)
                || wt.hasAnyFlag(WeaponType.F_ENERGY, WeaponType.F_PLASMA, WeaponType.F_BALLISTIC, WeaponType.F_MISSILE, WeaponType.F_B_POD, WeaponType.F_TAG);
        } else if (eq instanceof MiscType mt) {
            if (mt.hasFlag(MiscType.F_CLUB)) {
                if(mt.getTonnage(null) == EquipmentType.TONNAGE_VARIABLE) {
                    return false;
                }
                // These weapons have constant damage
                // Assuming vibroblades are out.
                valid = mt.hasAnySubType(MiscType.S_PILE_DRIVER, MiscType.S_FLAIL, MiscType.S_DUAL_SAW, MiscType.S_CHAIN_WHIP,
                    MiscType.S_BACKHOE, MiscType.S_MINING_DRILL, MiscType.S_WRECKING_BALL, MiscType.S_BUZZSAW,
                    MiscType.S_CHAIN_WHIP);
            } else {
                // Mine Dispensers are allowed explicitly, A-Pods have type PB, Weapon Enhancements are Artemis and PPC Caps and such
                valid = mt.hasAnyFlag(MiscType.F_VEHICLE_MINE_DISPENSER, MiscType.F_AP_POD, MiscType.F_WEAPON_ENHANCEMENT);
            }
        } else if (eq instanceof AmmoType at) {
            valid = !(at.getAmmoType() == AmmoType.T_COOLANT_POD);
        }

        return valid && super.shouldShow(eq);
    }
}
