/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.ui.handheldWeapon;

import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.AbstractEquipmentTab;
import megameklab.ui.util.AbstractEquipmentDatabaseView;

public class HHWEquipmentTab extends AbstractEquipmentTab {

    public HHWEquipmentTab(EntitySource eSource) {
        super(eSource);
    }

    @Override
    protected AbstractEquipmentDatabaseView getEquipmentDatabaseView() {
        return new HHWEquipmentDatabaseView(eSource);
    }
}
