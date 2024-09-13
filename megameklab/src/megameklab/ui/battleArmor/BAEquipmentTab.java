/*
 * MegaMekLab
 * Copyright (c) 2008
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
package megameklab.ui.battleArmor;

import megamek.common.AmmoType;
import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

/**
 * The Equipment Tab for BattleArmor units showing the equipment database and the current load out list.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Lawson
 * @author Simon (Juliez)
 */
public class BAEquipmentTab extends AbstractEquipmentTab {

    public BAEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new BAEquipmentDatabaseView(eSource);
    }

    @Override
    protected boolean showInLoadOut(Mounted<?> mount) {
        EquipmentType eType = mount.getType();
        return ((eType instanceof WeaponType) && showWeaponInLoadOut(mount))
                || ((eType instanceof AmmoType) && showAmmoInLoadOut(mount))
                || ((eType instanceof MiscType) && showMiscInLoadOut(mount));
    }

    private boolean showWeaponInLoadOut(Mounted<?> mount) {
        return BattleArmorUtil.isBattleArmorWeapon(mount.getType(), getBattleArmor())
                || BattleArmorUtil.isBattleArmorAPWeapon(mount.getType());
    }

    private boolean showAmmoInLoadOut(Mounted<?> mount) {
        return (mount.getLinkedBy() == null) || !mount.getLinkedBy().isOneShot();
    }

    private boolean showMiscInLoadOut(Mounted<?> mount) {
        EquipmentType etype = mount.getType();
        return !(etype instanceof MiscType) ||
                !(etype.hasFlag(MiscType.F_JUMP_JET)
                || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                || etype.hasFlag(MiscType.F_MECHANICAL_JUMP_BOOSTER)
                || etype.hasFlag(MiscType.F_MASC)
                || etype.hasFlag(MiscType.F_BA_MANIPULATOR)
                || etype.hasFlag(MiscType.F_PARTIAL_WING)
                || UnitUtil.isArmorOrStructure(etype));
    }

}
