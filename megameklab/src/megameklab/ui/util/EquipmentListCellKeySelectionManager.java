/*
 * Copyright (C) 2010-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox.KeySelectionManager;

import megamek.common.equipment.EquipmentType;

public class EquipmentListCellKeySelectionManager implements KeySelectionManager {
    @Override
    public int selectionForKey(char aKey, ComboBoxModel aModel) {
        int i, c;
        int currentSelection = -1;
        Object selectedItem = aModel.getSelectedItem();
        if (selectedItem != null) {
            String selectedText = ((EquipmentType) selectedItem).getName();
            String v;

            for (i = 0, c = aModel.getSize(); i < c; i++) {
                if (selectedText.equals(((EquipmentType) aModel.getElementAt(i)).getName())) {
                    currentSelection = i;
                    break;
                }
            }

            String pattern = ("" + aKey).toLowerCase();
            aKey = pattern.charAt(0);

            for (i = ++currentSelection, c = aModel.getSize(); i < c; i++) {
                Object elem = aModel.getElementAt(i);
                if ((elem != null) && (elem.toString() != null)) {
                    v = ((EquipmentType) elem).getName().toLowerCase();
                    if (!v.isBlank() && (v.charAt(0) == aKey)) {
                        return i;
                    }
                }
            }

            for (i = 0; i < currentSelection; i++) {
                Object elem = aModel.getElementAt(i);
                if ((elem != null) && (elem.toString() != null)) {
                    v = ((EquipmentType) elem).getName().toLowerCase();
                    if (!v.isBlank() && (v.charAt(0) == aKey)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
