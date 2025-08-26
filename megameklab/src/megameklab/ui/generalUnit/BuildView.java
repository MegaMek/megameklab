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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.generalUnit;

import java.awt.Insets;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import megamek.common.equipment.Engine;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.annotations.Nullable;

/**
 * Base class that has some common layout methods for main ui component views.
 *
 * @author Neoancient
 */
public abstract class BuildView extends JPanel {

    protected static final String CB_SIZE_VALUE = "X".repeat(23);
    protected static final Engine CB_SIZE_ENGINE = new Engine(100, Engine.NORMAL_ENGINE, 0);
    protected static final EquipmentType CB_SIZE_EQUIPMENT = EquipmentType.get(EquipmentTypeLookup.JUMP_JET);
    public static final Insets STANDARD_INSETS = new Insets(0, 2, 1, 2);

    public JLabel createLabel(ResourceBundle resources, String name, String text) {
        return createLabel(resources, name, text, null);
    }

    public JLabel createLabel(ResourceBundle resources, String name, String text, @Nullable String toolTipText) {
        return createLabel(name, resources.getString(text),
              (toolTipText == null) ? null : resources.getString(toolTipText));
    }

    public JLabel createLabel(String name, String text) {
        return createLabel(name, text, null);
    }

    public JLabel createLabel(String name, String text, @Nullable String toolTipText) {
        JLabel label = new JLabel(text + " ", SwingConstants.RIGHT);
        label.setToolTipText(toolTipText);
        if (!name.isBlank()) {
            label.setName(name);
        }
        return label;
    }
}
