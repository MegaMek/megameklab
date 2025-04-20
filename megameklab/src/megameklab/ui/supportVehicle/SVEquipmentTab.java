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
 */
package megameklab.ui.supportVehicle;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.EquipmentTypeLookup;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * The Equipment Tab for Support Vehicles units showing the equipment database and the current loadout list.
 *
 * @author Simon (Juliez)
 */
public class SVEquipmentTab extends AbstractEquipmentTab {

    public SVEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new SVEquipmentDatabaseView(eSource);
    }

    @Override
    protected boolean showInLoadOut(Mounted<?> mount) {
        EquipmentType etype = mount.getType();
        return !(etype instanceof MiscType) ||
                     !(UnitUtil.isHeatSink(mount) ||
                             etype.is(EquipmentTypeLookup.PINTLE_TURRET) ||
                             etype.hasFlag(MiscType.F_JUMP_JET) ||
                             etype.hasFlag(MiscType.F_JUMP_BOOSTER) ||
                             etype.hasFlag(MiscType.F_TSM) ||
                             etype.hasFlag(MiscType.F_INDUSTRIAL_TSM) ||
                             (etype.hasFlag(MiscType.F_MASC) &&
                                    !etype.hasSubType(MiscType.S_SUPERCHARGER) &&
                                    !etype.hasSubType(MiscType.S_JETBOOSTER)) ||
                             (((eSource.getEntity().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE) &&
                                    etype.hasFlag(MiscType.F_TRACKS)) ||
                             etype.hasFlag(MiscType.F_CHASSIS_MODIFICATION) ||
                             etype.hasFlag(MiscType.F_ADVANCED_FIRECONTROL) ||
                             etype.hasFlag(MiscType.F_BASIC_FIRECONTROL) ||
                             UnitUtil.isArmorOrStructure(etype));
    }
}
