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
package megameklab.com.ui.battleArmor;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.generalUnit.AbstractEquipmentTab;
import megameklab.com.ui.util.*;
import megameklab.com.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Equipment Tab for BattleArmor units showing the equipment database and the current loadout list.
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
    protected boolean showInLoadout(Mounted mount) {
        EquipmentType eType = mount.getType();
        return ((eType instanceof WeaponType) && showWeaponInLoadout(mount))
                || ((eType instanceof AmmoType) && showAmmoInLoadout(mount))
                || ((eType instanceof MiscType) && showMiscInLoadout(mount));
    }

    private boolean showWeaponInLoadout(Mounted mount) {
        return UnitUtil.isBattleArmorWeapon(mount.getType(), getBattleArmor())
                || UnitUtil.isBattleArmorAPWeapon(mount.getType());
    }

    private boolean showAmmoInLoadout(Mounted mount) {
        return (mount.getLinkedBy() == null) || !mount.getLinkedBy().isOneShot();
    }

    private boolean showMiscInLoadout(Mounted mount) {
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