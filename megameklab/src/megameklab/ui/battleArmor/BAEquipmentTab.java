/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.battleArmor;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
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
