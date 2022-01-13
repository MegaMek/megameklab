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
package megameklab.com.ui.mek;

import megamek.common.Entity;
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
 * The Equipment Tab for Mek units showing the equipment database and the current loadout list.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author Taharqa
 * @author Simon (Juliez)
 */
class BMEquipmentTab extends AbstractEquipmentTab {

    public BMEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new BMEquipmentDatabaseView(eSource);
    }

    @Override
    protected List<Mounted> getLoadout() {
        List<Mounted> result = new ArrayList<>();
        result.addAll(getMech().getWeaponList());
        result.addAll(getMech().getAmmo());
        result.addAll(getMech().getMisc().stream().filter(this::showInLoadout).collect(toList()));
        return result;
    }

    private boolean showInLoadout(Mounted mount) {
        EquipmentType etype = mount.getType();
        return !(UnitUtil.isHeatSink(mount)
                || etype.hasFlag(MiscType.F_JUMP_JET)
                || etype.hasFlag(MiscType.F_JUMP_BOOSTER)
                || etype.hasFlag(MiscType.F_TSM)
                || etype.hasFlag(MiscType.F_INDUSTRIAL_TSM)
                || (etype.hasFlag(MiscType.F_MASC) && !etype.hasSubType(MiscType.S_SUPERCHARGER))
                || ((getMech().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE
                && etype.hasFlag(MiscType.F_TRACKS))
                || UnitUtil.isArmorOrStructure(etype)
                || (etype.hasFlag(MiscType.F_CASE) && etype.isClan()));
    }

}