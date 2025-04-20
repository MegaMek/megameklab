/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamek.client.ratgenerator.FactionRecord;
import megamek.client.ratgenerator.RATGenerator;
import megamek.common.ITechnology;

/**
 * Combo box that uses the RATGenerator faction data to provide a list of factions appropriate to a unit's intro year
 * and with the era-appropriate name. The underlying data type is the ITechnology faction constant.
 *
 * @author Neoancient
 */
public class FactionComboBox extends CustomComboBox<Integer> {
    private final Map<Integer, String> displayNames = new HashMap<>();

    public FactionComboBox() {
        super();
        setRenderer(new Renderer<>(displayNames::get));
        while (!RATGenerator.getInstance().isInitialized()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void refresh(int year) {
        displayNames.clear();
        for (int i = 0; i < ITechnology.MM_FACTION_CODES.length; i++) {
            final FactionRecord fRec = RATGenerator.getInstance().getFaction(ITechnology.MM_FACTION_CODES[i]);
            // TA will generate a null value because the RAT Generator doesn't distinguish between TH and TA.
            if ((null != fRec) && (fRec.isActiveInYear(year))) {
                displayNames.put(i, fRec.getName(year));
            }
        }
        List<Integer> sorted = new ArrayList<>(displayNames.keySet());
        sorted.sort(Comparator.comparing(displayNames::get));
        removeAllItems();
        addItem(-1);
        displayNames.put(-1, "Any");
        sorted.forEach(this::addItem);
    }
}
