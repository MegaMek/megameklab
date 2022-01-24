/*
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.com.ui.dialog.settings;

import megameklab.com.ui.util.CritCellUtil;
import megameklab.com.ui.util.SpringUtilities;
import megameklab.com.util.CConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorSettingsPanel extends JPanel {

    private final JPanel gridPanel = new JPanel(new SpringLayout());
    private final Map<String, String> allColors = new HashMap<>();

    ColorSettingsPanel() {
        addColorRow(CConfig.COLOR_WEAPONS);
        addColorRow(CConfig.COLOR_EQUIPMENT);
        addColorRow(CConfig.COLOR_AMMO);
        addColorRow(CConfig.COLOR_SYSTEMS);
        addColorRow(CConfig.COLOR_NONHITTABLE);
        addColorRow(CConfig.COLOR_EMPTY);
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
        coloredExample.setBorder(new EmptyBorder(0,10,0,25));

        JButton foregroundButton = new JButton("Foreground");
        foregroundButton.addActionListener(e ->
                callColorChooser(colorType, CConfig.CONFIG_FOREGROUND, coloredExample));

        JButton backgroundButton = new JButton("Background");
        backgroundButton.addActionListener(e ->
                callColorChooser(colorType, CConfig.CONFIG_BACKGROUND, coloredExample));

        gridPanel.add(typeLabel);
        gridPanel.add(foregroundButton);
        gridPanel.add(backgroundButton);
        gridPanel.add(coloredExample);
    }

    private String exampleText(String colorType) {
        switch (colorType) {
            case CConfig.COLOR_WEAPONS:
                return "Medium Pulse Laser";
            case CConfig.COLOR_EQUIPMENT:
                return "Heat Sink";
            case CConfig.COLOR_AMMO:
                return "AC/5 Ammo";
            case CConfig.COLOR_SYSTEMS:
                return "XL Engine";
            case CConfig.COLOR_NONHITTABLE:
                return "Endo Steel";
            default:
                return CritCellUtil.EMPTY_CRITCELL_TEXT;
        }
    }

    private void callColorChooser(String type, String fgOrBg, JLabel exampleLabel) {
        Color preset = fgOrBg.equals(CConfig.CONFIG_BACKGROUND) ? exampleLabel.getBackground() : exampleLabel.getForeground();
        Color newColor = JColorChooser.showDialog(this, "Choose a color", preset);
        if (newColor != null) {
            if (fgOrBg.equals(CConfig.CONFIG_BACKGROUND)) {
                exampleLabel.setBackground(newColor);
            } else {
                exampleLabel.setForeground(newColor);
            }
            allColors.put(type + fgOrBg, Integer.toString(newColor.getRGB()));
        }
    }

    /**
     * Returns a mapping of a String representing the type of color to an RGB Color string.
     * The type string is CConfig.COLOR_XYZ+CConfig.CONFIG_FOREGROUND or BACKGROUND.
     */
    Map<String, String> getAllColors() {
        return allColors;
    }
}
