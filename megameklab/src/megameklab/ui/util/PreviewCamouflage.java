/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.util;

import megamek.client.ui.util.PlayerColour;
import megamek.common.battlefieldSupport.OverlayStyle;
import megamek.common.icons.Camouflage;

/**
 * Creates the synthetic colors used to distinguish raw sprite sources in MegaMekLab previews. These are not
 * player/unit camouflage schemes, so Battlefield Support Asset marker overlays are disabled.
 */
public final class PreviewCamouflage {

    private PreviewCamouflage() {
    }

    public static Camouflage of(PlayerColour color) {
        Camouflage camouflage = Camouflage.of(color);
        camouflage.setOverlayStyle(OverlayStyle.NONE);
        return camouflage;
    }
}
