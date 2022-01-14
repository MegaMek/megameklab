/*
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
package megameklab.com.ui.fighterAero;

import megamek.common.Mounted;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.generalUnit.AbstractEquipmentTab;
import megameklab.com.ui.util.AbstractEquipmentDatabaseView;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Equipment Tab for Aerospace and Conventional Fighters showing the equipment
 * database and the current loadout list.
 *
 * @author jtighe (torren@users.sourceforge.net)
 * @author arlith
 * @author neoancient
 * @author Simon (Juliez)
 */
public class ASEquipmentTab extends AbstractEquipmentTab {

    public ASEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new ASEquipmentDatabaseView(eSource);
    }

}
