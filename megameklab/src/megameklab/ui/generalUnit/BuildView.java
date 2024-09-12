/*
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMekLab. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.EquipmentTypeLookup;
import megamek.common.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Base class that has some common layout methods for main ui component views.
 * 
 * @author Neoancient
 */
public abstract class BuildView extends JPanel {

    protected static final String CB_SIZE_VALUE = "XXXXXXXXXXXXXXXXXXXXXXX";
    protected static final Engine CB_SIZE_ENGINE = new Engine(100, Engine.NORMAL_ENGINE, 0);
    protected static final EquipmentType CB_SIZE_EQUIPMENT = EquipmentType.get(EquipmentTypeLookup.JUMP_JET);
    protected static final Insets STANDARD_INSETS = new Insets(0, 2, 1, 2);

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
