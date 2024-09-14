/*
 * MegaMekLab
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
package megameklab.ui.protoMek;

import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;

/**
 * The Equipment Tab for ProtoMek units showing the equipment database and the
 * current loadout list.
 *
 * @author Simon (Juliez)
 */
public class PMEquipmentTab extends AbstractEquipmentTab {

    public PMEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new PMEquipmentDatabaseView(eSource);
    }

    @Override
    protected boolean showInLoadOut(Mounted<?> mount) {
        EquipmentType etype = mount.getType();
        return !(etype instanceof MiscType) ||
                !(etype.hasFlag(MiscType.F_JUMP_JET)
                        || etype.hasFlag(MiscType.F_MASC)
                        || etype.hasFlag(MiscType.F_PARTIAL_WING)
                        || etype.hasFlag(MiscType.F_MAGNETIC_CLAMP)
                        || etype.hasFlag(MiscType.F_UMU));
    }

}
