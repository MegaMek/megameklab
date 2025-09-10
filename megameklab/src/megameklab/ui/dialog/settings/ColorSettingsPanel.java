/*
 * Copyright (C) 2022-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.dialog.settings;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.SpringUtilities;
import megameklab.util.CConfig;

/**
 * A panel allowing to change MML's color preferences
 */
public class ColorSettingsPanel extends JPanel {

    private final JPanel gridPanel = new JPanel(new SpringLayout());
    private final Map<String, String> allColors = new HashMap<>();

    ColorSettingsPanel() {
        addColorRow(CConfig.GUI_COLOR_WEAPONS);
        addColorRow(CConfig.GUI_COLOR_EQUIPMENT);
        addColorRow(CConfig.GUI_COLOR_AMMO);
        addColorRow(CConfig.GUI_COLOR_SYSTEMS);
        addColorRow(CConfig.GUI_COLOR_NON_HITTABLE);
        addColorRow(CConfig.GUI_COLOR_EMPTY);
        SpringUtilities.makeCompactGrid(gridPanel, 6, 4, 0, 0, 15, 10);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new EmptyBorder(20, 30, 20, 30));
        add(gridPanel);
    }

    private void addColorRow(String colorType) {
        JLabel typeLabel = new JLabel(colorType);

        JLabel coloredExample = new JLabel(exampleText(colorType));
        coloredExample.setName(colorType);
        coloredExample.setOpaque(true);
        coloredExample.setBackground(CConfig.getBackgroundColor(colorType));
        coloredExample.setForeground(CConfig.getForegroundColor(colorType));
        coloredExample.setBorder(new EmptyBorder(0, 10, 0, 25));

        JButton foregroundButton = new JButton("Foreground");
        foregroundButton.addActionListener(e ->
              callColorChooser(colorType, CConfig.GUI_FOREGROUND, coloredExample));

        JButton backgroundButton = new JButton("Background");
        backgroundButton.addActionListener(e ->
              callColorChooser(colorType, CConfig.GUI_BACKGROUND, coloredExample));

        gridPanel.add(typeLabel);
        gridPanel.add(foregroundButton);
        gridPanel.add(backgroundButton);
        gridPanel.add(coloredExample);
    }

    private String exampleText(String colorType) {
        return switch (colorType) {
            case CConfig.GUI_COLOR_WEAPONS -> "Medium Pulse Laser";
            case CConfig.GUI_COLOR_EQUIPMENT -> "Heat Sink";
            case CConfig.GUI_COLOR_AMMO -> "AC/5 Ammo";
            case CConfig.GUI_COLOR_SYSTEMS -> "XL Engine";
            case CConfig.GUI_COLOR_NON_HITTABLE -> "Endo Steel";
            default -> CritCellUtil.EMPTY_CRITICAL_CELL_TEXT;
        };
    }

    private void callColorChooser(String type, String fgOrBg, JLabel exampleLabel) {
        Color preset = fgOrBg.equals(CConfig.GUI_BACKGROUND) ?
              exampleLabel.getBackground() :
              exampleLabel.getForeground();
        Color newColor = JColorChooser.showDialog(this, "Choose a color", preset);
        if (newColor != null) {
            if (fgOrBg.equals(CConfig.GUI_BACKGROUND)) {
                exampleLabel.setBackground(newColor);
            } else {
                exampleLabel.setForeground(newColor);
            }
            allColors.put(type + fgOrBg, Integer.toString(newColor.getRGB()));
        }
    }

    /**
     * @return a mapping of a String representing the type of color to an RGB Color string. The type string is
     *       CConfig.COLOR_XYZ+CConfig.CONFIG_FOREGROUND or BACKGROUND.
     */
    Map<String, String> getAllColors() {
        return allColors;
    }
}
