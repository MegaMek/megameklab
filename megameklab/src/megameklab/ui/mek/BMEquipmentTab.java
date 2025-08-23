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
package megameklab.ui.mek;

import megamek.common.units.Entity;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;
import megameklab.util.UnitUtil;

/**
 * The Equipment Tab for Mek units showing the equipment database and the current loadout list.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Taharqa
 * @author Simon (Juliez)
 */
public class BMEquipmentTab extends AbstractEquipmentTab {

    public BMEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new BMEquipmentDatabaseView(eSource);
    }

    @Override
    protected boolean showInLoadOut(Mounted<?> mount) {
        EquipmentType etype = mount.getType();
        return !(etype instanceof MiscType) ||
              !(UnitUtil.isHeatSink(mount)
                    || etype.isAnyOf(EquipmentTypeLookup.LAM_FUEL_TANK)
                    || etype.hasFlag(MiscType.F_JUMP_JET)
                    || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                    || etype.hasFlag(MiscType.F_TSM)
                    || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                    || (etype.hasFlag(MiscType.F_MASC) && !etype.hasSubType(MiscType.S_SUPERCHARGER))
                    || ((getMek().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE
                    && etype.hasFlag(MiscType.F_TRACKS))
                    || UnitUtil.isArmorOrStructure(etype)
                    || (etype.hasFlag(MiscType.F_CASE) && etype.isClan()));
    }

}
