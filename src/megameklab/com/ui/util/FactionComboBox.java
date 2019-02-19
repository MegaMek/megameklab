/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

package megameklab.com.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamek.client.ratgenerator.FactionRecord;
import megamek.client.ratgenerator.RATGenerator;
import megamek.common.ITechnology;

/**
 * Combo box that uses the RATGenerator faction data to provide a list of
 * factions appropriate to a unit's intro year and with the era-appropriate
 * name. The underlying data type is the ITechnology faction constant.
 *
 * @author Neoancient
 *
 */
public class FactionComboBox extends CustomComboBox<Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 4722914142736815170L;

    private Map<Integer, String> displayNames = new HashMap<>();

    public FactionComboBox() {
        super();
        setRenderer(new Renderer<Integer>(i -> displayNames.get(i)));
        while (!RATGenerator.getInstance().isInitialized()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

    public void refresh(int year, boolean clan) {
        displayNames.clear();
        for (int i = 0; i < ITechnology.MM_FACTION_CODES.length; i++) {
            final FactionRecord fRec = RATGenerator.getInstance().getFaction(ITechnology.MM_FACTION_CODES[i]);
            // TA will generate a null value because the RAT Generator doesn't distinguish
            // between TH and TA.
            if ((null != fRec) && (fRec.isClan() == clan) && (fRec.isActiveInYear(year))) {
                displayNames.put(i, fRec.getName(year));
            }
        }
        List<Integer> sorted = new ArrayList<>(displayNames.keySet());
        Collections.sort(sorted, (i1, i2) -> displayNames.get(i1).compareTo(displayNames.get(i2)));
        removeAllItems();
        addItem(-1);
        displayNames.put(-1, "Any");
        sorted.forEach(this::addItem);
    }
}
