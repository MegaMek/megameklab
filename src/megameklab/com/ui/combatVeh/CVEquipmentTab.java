/*
 * MegaMekLab - Copyright (C) 2008
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

import megamek.common.EquipmentType;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.generalUnit.AbstractEquipmentTab;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Equipment Tab for Combat vehicle units showing the equipment database and the current loadout list.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Taharqa
 * @author Simon (Juliez)
 */
public class CVEquipmentTab extends AbstractEquipmentTab {

    public CVEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new CVEquipmentDatabaseView(eSource);
    }

    @Override
    protected boolean showInLoadout(Mounted mount) {
        EquipmentType etype = mount.getType();
        return !(etype instanceof MiscType) ||
                (!etype.hasFlag(MiscType.F_JUMP_JET) && !UnitUtil.isArmorOrStructure(etype));
    }

}