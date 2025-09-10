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

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import megamek.common.interfaces.ITechnology;
import megameklab.ui.util.IntRangeTextField;
import megameklab.ui.util.SpringUtilities;
import megameklab.util.CConfig;

/**
 * A panel allowing to change MML's Tech Level preferences
 */
public class TechSettingsPanel extends JPanel {

    private final JCheckBox chkTechProgression = new JCheckBox();
    private final JCheckBox chkTechUseYear = new JCheckBox();
    private final IntRangeTextField txtTechYear = new IntRangeTextField();
    private final JCheckBox chkTechShowFaction = new JCheckBox();
    private final JCheckBox chkShowExtinct = new JCheckBox();
    private final JCheckBox chkUnofficialIgnoreYear = new JCheckBox();

    TechSettingsPanel() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");
        chkTechProgression.addActionListener(e -> {
            chkTechUseYear.setEnabled(chkTechProgression.isSelected());
            txtTechYear.setEnabled(chkTechProgression.isSelected()
                  && chkTechUseYear.isSelected());
        });
        chkTechProgression.setText(resourceMap.getString("ConfigurationDialog.chkTechProgression.text"));
        chkTechProgression.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechProgression.tooltip"));
        chkTechProgression.setSelected(CConfig.getBooleanParam(CConfig.TECH_PROGRESSION));

        chkTechUseYear.addActionListener(e -> txtTechYear.setEnabled(chkTechUseYear.isSelected()));
        chkTechUseYear.setText(resourceMap.getString("ConfigurationDialog.chkTechYear.text"));
        chkTechUseYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechYear.tooltip"));
        chkTechUseYear.setSelected(CConfig.getBooleanParam(CConfig.TECH_USE_YEAR));

        txtTechYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechYear.tooltip"));
        txtTechYear.setColumns(12);
        txtTechYear.setMinimum(ITechnology.DATE_PS);
        txtTechYear.setMaximum(9999);
        String year = CConfig.getParam(CConfig.TECH_YEAR);
        if (year.isBlank()) {
            year = "3145";
        }
        txtTechYear.setText(year);
        JPanel techYearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        techYearPanel.add(Box.createHorizontalStrut(25));
        techYearPanel.add(chkTechUseYear);
        techYearPanel.add(Box.createHorizontalStrut(25));
        techYearPanel.add(txtTechYear);

        chkTechShowFaction.setText(resourceMap.getString("ConfigurationDialog.chkTechShowFaction.text"));
        chkTechShowFaction.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechShowFaction.tooltip"));
        chkTechShowFaction.setSelected(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));

        chkShowExtinct.setText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.text"));
        chkShowExtinct.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.tooltip"));
        chkShowExtinct.setSelected(CConfig.getBooleanParam(CConfig.TECH_EXTINCT));

        chkUnofficialIgnoreYear.setText(resourceMap.getString("ConfigurationDialog.chkUnofficialIgnoreYear.text"));
        chkUnofficialIgnoreYear.setToolTipText(resourceMap.getString(
              "ConfigurationDialog.chkUnofficialIgnoreYear.tooltip"));
        chkUnofficialIgnoreYear.setSelected(CConfig.getBooleanParam(CConfig.TECH_UNOFFICIAL_NO_YEAR));

        JPanel gridPanel = new JPanel(new SpringLayout());
        gridPanel.add(chkTechProgression);
        gridPanel.add(techYearPanel);
        gridPanel.add(chkTechShowFaction);
        gridPanel.add(chkShowExtinct);
        gridPanel.add(chkUnofficialIgnoreYear);

        SpringUtilities.makeCompactGrid(gridPanel, 5, 1, 0, 0, 15, 10);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    Map<String, String> getTechSettings() {
        Map<String, String> techSettings = new HashMap<>();
        techSettings.put(CConfig.TECH_PROGRESSION, String.valueOf(chkTechProgression.isSelected()));
        techSettings.put(CConfig.TECH_USE_YEAR, String.valueOf(chkTechUseYear.isSelected()));
        techSettings.put(CConfig.TECH_YEAR, String.valueOf(txtTechYear.getIntVal()));
        techSettings.put(CConfig.TECH_SHOW_FACTION, String.valueOf(chkTechShowFaction.isSelected()));
        techSettings.put(CConfig.TECH_EXTINCT, String.valueOf(chkShowExtinct.isSelected()));
        techSettings.put(CConfig.TECH_UNOFFICIAL_NO_YEAR, String.valueOf(chkUnofficialIgnoreYear.isSelected()));
        return techSettings;
    }
}
