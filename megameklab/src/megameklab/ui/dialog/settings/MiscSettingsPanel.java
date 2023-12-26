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

import megamek.client.ui.baseComponents.MMComboBox;
import megameklab.ui.MMLStartUp;
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
    private static final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Dialogs");

    private final MMComboBox<MMLStartUp> startUpMMComboBox = new MMComboBox<>("StartUp", MMLStartUp.values());
    private final JCheckBox chkSummaryFormatTRO = new JCheckBox();
    private final JCheckBox chkSkipSavePrompts = new JCheckBox();

    MiscSettingsPanel() {
        startUpMMComboBox.setRenderer(startUpRenderer);
        startUpMMComboBox.setSelectedItem(CConfig.getStartUpType());
        startUpMMComboBox.setToolTipText(resources.getString("ConfigurationDialog.startup.tooltip"));
        JLabel startUpLabel = new JLabel(resources.getString("ConfigurationDialog.startup.text"));
        startUpLabel.setToolTipText(resources.getString("ConfigurationDialog.startup.tooltip"));

        JPanel startUpLine = new JPanel();
        startUpLine.add(startUpLabel);
        startUpLine.add(startUpMMComboBox);

        chkSummaryFormatTRO.setText(resources.getString("ConfigurationDialog.chkSummaryFormatTRO.text"));
        chkSummaryFormatTRO.setToolTipText(resources.getString("ConfigurationDialog.chkSummaryFormatTRO.tooltip"));
        chkSummaryFormatTRO.setSelected(CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO));

        chkSkipSavePrompts.setText(resources.getString("ConfigurationDialog.chkSkipSavePrompts.text"));
        chkSkipSavePrompts.setToolTipText(resources.getString("ConfigurationDialog.chkSkipSavePrompts.tooltip"));
        chkSkipSavePrompts.setSelected(CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS));

        JPanel gridPanel = new JPanel(new SpringLayout());
        gridPanel.add(startUpLine);
        gridPanel.add(chkSummaryFormatTRO);
        gridPanel.add(chkSkipSavePrompts);

        SpringUtilities.makeCompactGrid(gridPanel, 3, 1, 0, 0, 15, 10);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    Map<String, String> getMiscSettings() {
        Map<String, String> miscSettings = new HashMap<>();
        miscSettings.put(CConfig.MISC_SUMMARY_FORMAT_TRO, String.valueOf(chkSummaryFormatTRO.isSelected()));
        miscSettings.put(CConfig.MISC_SKIP_SAFETY_PROMPTS, String.valueOf(chkSkipSavePrompts.isSelected()));
        MMLStartUp startUp = startUpMMComboBox.getSelectedItem() == null
                ? MMLStartUp.SPLASH_SCREEN
                : startUpMMComboBox.getSelectedItem();
        miscSettings.put(CConfig.STARTUP, startUp.name());
        return miscSettings;
    }

    DefaultListCellRenderer startUpRenderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof MMLStartUp) {
                value = ((MMLStartUp) value).getDisplayName();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    };

}