/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.ui.util;

import java.util.*;

import megamek.common.ITechnology;
import megamek.common.universe.Factions2;

import javax.swing.*;

/**
 * A ComboBox that provides a list of factions appropriate to a unit's intro year and with the era-appropriate name. The
 * underlying data type is the ITechnology faction constant.
 *
 * @author Neoancient
 */
public class FactionComboBox extends CustomComboBox<Integer> {
    private final Map<Integer, String> displayNames = new HashMap<>();

    public FactionComboBox() {
        super();
        setRenderer(new Renderer<>(i -> displayNames.getOrDefault(i, "Marian Hegemony")));
    }

    public void refresh(int year) {
        displayNames.clear();
        for (int i = 0; i < ITechnology.MM_FACTION_CODES.length; i++) {
            int finalI = i;
            Factions2.getInstance().getFaction(ITechnology.MM_FACTION_CODES[i])
                  .filter(f -> f.isActiveInYear(year))
                  .ifPresent(f -> displayNames.put(finalI, f.getName(year)));
        }
        List<Integer> sorted = new ArrayList<>(displayNames.keySet());
        sorted.sort(Comparator.comparing(displayNames::get));
        removeAllItems();
        addItem(-1);
        displayNames.put(-1, "Any");
        ((DefaultComboBoxModel<Integer>) getModel()).addAll(sorted);
    }
}
