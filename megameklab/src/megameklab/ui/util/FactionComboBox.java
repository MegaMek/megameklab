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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

import megamek.common.enums.Faction;
import megamek.common.universe.Factions2;

/**
 * A ComboBox that provides a list of factions appropriate to a unit's intro year and with the era-appropriate name. The
 * underlying data type is the ITechnology Faction ENUM.
 *
 * @author Neoancient
 */
public class FactionComboBox extends CustomComboBox<Faction> {

    private final Map<Faction, String> displayNames = new HashMap<>();

    public FactionComboBox() {
        super();
        setRenderer(new Renderer<>(i -> displayNames.getOrDefault(i, "Marian Hegemony")));
    }

    public void refresh(int year) {
        displayNames.clear();
        for (Faction faction : Faction.values()) {
            Factions2.getInstance().getFaction(faction.getCodeIO())
                  .filter(f2 -> f2.isActiveInYear(year))
                  .ifPresent(f2 -> displayNames.put(faction, f2.getName(year)));
        }
        List<Faction> sorted = new ArrayList<>(displayNames.keySet());
        sorted.sort(Comparator.comparing(displayNames::get));
        removeAllItems();
        addItem(Faction.NONE);
        ((DefaultComboBoxModel<Faction>) getModel()).addAll(sorted);
        displayNames.put(Faction.NONE, "Any");
    }
}
