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
package megameklab.ui.dialog.settings;

import megameklab.ui.util.SpringUtilities;
import megameklab.util.CConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A panel allowing to change MML's general preferences
 */
public class MiscSettingsPanel extends JPanel {

    private final JCheckBox chkSummaryFormatTRO = new JCheckBox();
    private final JCheckBox chkSkipSavePrompts = new JCheckBox();

    MiscSettingsPanel() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");

        chkSummaryFormatTRO.setText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.text"));
        chkSummaryFormatTRO.setToolTipText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.tooltip"));
        chkSummaryFormatTRO.setSelected(CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO));

        chkSkipSavePrompts.setText(resourceMap.getString("ConfigurationDialog.chkSkipSavePrompts.text"));
        chkSkipSavePrompts.setToolTipText(resourceMap.getString("ConfigurationDialog.chkSkipSavePrompts.tooltip"));
        chkSkipSavePrompts.setSelected(CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS));

        JPanel gridPanel = new JPanel(new SpringLayout());
        gridPanel.add(chkSummaryFormatTRO);
        gridPanel.add(chkSkipSavePrompts);

        SpringUtilities.makeCompactGrid(gridPanel, 2, 1, 0, 0, 15, 10);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    Map<String, String> getMiscSettings() {
        Map<String, String> miscSettings = new HashMap<>();
        miscSettings.put(CConfig.MISC_SUMMARY_FORMAT_TRO, String.valueOf(chkSummaryFormatTRO.isSelected()));
        miscSettings.put(CConfig.MISC_SKIP_SAFETY_PROMPTS, String.valueOf(chkSkipSavePrompts.isSelected()));
        return miscSettings;
    }
}
