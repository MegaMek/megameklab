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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import megamek.common.units.EntityMovementMode;

/**
 * This enum is specifically for use in MML CI construction.
 *
 * @see CIPlatoonTypeView
 */
enum InfantryMotiveType {
    FOOT(EntityMovementMode.INF_LEG, false, "PlatoonTypeView.cbMotiveType.foot"),
    JUMP(EntityMovementMode.INF_JUMP, false, "PlatoonTypeView.cbMotiveType.jump"),
    MOTORIZED(EntityMovementMode.INF_MOTORIZED, true, "PlatoonTypeView.cbMotiveType.motorized"),
    HOVER(EntityMovementMode.HOVER, false, "PlatoonTypeView.cbMotiveType.hover"),
    TRACKED(EntityMovementMode.TRACKED, true, "PlatoonTypeView.cbMotiveType.tracked"),
    WHEELED(EntityMovementMode.WHEELED, true, "PlatoonTypeView.cbMotiveType.wheeled"),
    VTOL(EntityMovementMode.VTOL, false, "PlatoonTypeView.cbMotiveType.vtol"),
    MICROLITE(EntityMovementMode.VTOL, false, "PlatoonTypeView.cbMotiveType.microlite"),
    UMU(EntityMovementMode.INF_UMU, false, "PlatoonTypeView.cbMotiveType.umu"),
    UMU_MOTORIZED(EntityMovementMode.INF_UMU, false, "PlatoonTypeView.cbMotiveType.umu_motorized"),
    SUBMARINE(EntityMovementMode.SUBMARINE, false, "PlatoonTypeView.cbMotiveType.submarine"),
    BEAST_MOUNTED(EntityMovementMode.NONE, false, "PlatoonTypeView.cbMotiveType.beast_mounted");

    final EntityMovementMode mode;
    final boolean legalFieldGun;
    final String resourceId;

    InfantryMotiveType(EntityMovementMode mode, boolean legalFieldGun, String resourceId) {
        this.mode = mode;
        this.legalFieldGun = legalFieldGun;
        this.resourceId = resourceId;
    }
}
