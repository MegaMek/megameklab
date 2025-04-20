/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import java.util.function.Function;

import megamek.common.ITechnology;

/**
 * ComboBox for equipment that implement ITechnology. Has a boolean flag that can toggle prefixing "Clan" or "IS" to the
 * equipment name.
 *
 * @author Neoancient
 */
public class TechComboBox<T extends ITechnology> extends CustomComboBox<T> {
    private boolean showTechBase = false;

    public TechComboBox(Function<T, String> toString) {
        super();
        setRenderer(new Renderer<>(t -> getTechName(toString.apply(t), t.getTechBase())));
    }

    public void showTechBase(boolean show) {
        showTechBase = show;
    }

    private String getTechName(String name, int techBase) {
        StringBuilder sb = new StringBuilder();
        if (showTechBase) {
            if (techBase == ITechnology.TECH_BASE_CLAN) {
                sb.append("Clan ");
            } else if (techBase == ITechnology.TECH_BASE_IS) {
                sb.append("IS ");
            }
        }
        sb.append(name);
        return sb.toString();
    }
}
