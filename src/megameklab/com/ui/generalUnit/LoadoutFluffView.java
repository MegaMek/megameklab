/*
 * MegaMekLab
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.com.ui.generalUnit;

import megamek.common.EquipmentType;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;
import megameklab.com.ui.util.EquipmentDatabaseCategory;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static megameklab.com.ui.util.EquipmentTableModel.*;

public class LoadoutFluffView extends AbstractEquipmentDatabaseView {

    private final List<Integer> columns = List.of(COL_NAME, COL_TECH, COL_TLEVEL, COL_TRATING, COL_DPROTOTYPE,
            COL_DPRODUCTION, COL_DCOMMON, COL_DEXTINCT, COL_DREINTRO, COL_COST);

    public LoadoutFluffView(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected void addEquipment(EquipmentType equip, int count) {
        // empty, as adding equipment is not allowed for this View
        LogManager.getLogger().warn("Adding equipment should not be possible in this View.");
    }

    @Override
    protected Collection<Integer> getVisibleTableColumns(boolean tableMode) {
        return columns;
    }

    @Override
    protected boolean useSwitchTableColumns() {
        return false;
    }

    @Override
    protected boolean useTextFilter() {
        return false;
    }

    @Override
    protected Set<EquipmentDatabaseCategory> getUsedButtons() {
        return Collections.EMPTY_SET;
    }

    @Override
    protected boolean shouldShow(EquipmentType equipment) {
        return getEntity().getEquipment().stream()
                .filter(m -> !(m.getType() instanceof BayWeapon))
                .anyMatch(m -> m.getType().equals(equipment));
    }

    @Override
    protected boolean allowAdd() {
        return false;
    }
}
