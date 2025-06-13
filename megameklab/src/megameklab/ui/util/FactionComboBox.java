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
import megamek.client.ratgenerator.FactionRecord;
import megamek.client.ratgenerator.RATGenerator;
import megamek.common.ITechnology.Faction;

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
        for (Faction f : Faction.values()) {
            if (f.equals(Faction.NONE)) {
                continue;
            }
            final FactionRecord fRec = RATGenerator.getInstance().getFaction(f.getCodeMM());
            // TA will generate a null value because the RAT Generator doesn't distinguish between TH and TA.
            if ((null != fRec) && (fRec.isActiveInYear(year))) {
                displayNames.put(f, fRec.getName(year));
            }
        }
        List<Faction> sorted = new ArrayList<>(displayNames.keySet());
        sorted.sort(Comparator.comparing(displayNames::get));
        removeAllItems();
        addItem(Faction.NONE);
        displayNames.put(Faction.NONE, "Any");
        ((DefaultComboBoxModel<Integer>) getModel()).addAll(sorted);
    }
}
