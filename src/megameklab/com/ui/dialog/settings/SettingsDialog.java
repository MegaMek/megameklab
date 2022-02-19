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

import megamek.client.ui.baseComponents.MMButton;
import megameklab.com.ui.dialog.AbstractMMLButtonDialog;
import megameklab.com.util.CConfig;

import javax.swing.*;
import java.awt.*;

/**
 * This is MML's general preferences dialog with color, record sheet and other settings.
 */
public class SettingsDialog extends AbstractMMLButtonDialog {

    private final ColorSettingsPanel colorPreferences = new ColorSettingsPanel();
    private final TechSettingsPanel techSettings = new TechSettingsPanel();
    private final ExportSettingsPanel exportSettingsPanel = new ExportSettingsPanel();
    private final MiscSettingsPanel miscSettingsPanel = new MiscSettingsPanel();

    public SettingsDialog(JFrame frame) {
        super(frame, true, "ConfigurationDialog", "ConfigurationDialog.windowName.text");
        initialize();
    }

    @Override
    protected Container createCenterPane() {
        JTabbedPane panMain = new JTabbedPane();
        panMain.addTab(resources.getString("ConfigurationDialog.misc.title"), miscSettingsPanel);
        panMain.addTab(resources.getString("ConfigurationDialog.colorCodes.title"), colorPreferences);
        panMain.addTab(resources.getString("ConfigurationDialog.techProgression.title"), techSettings);
        panMain.addTab(resources.getString("ConfigurationDialog.printing.title"), exportSettingsPanel);
        return panMain;
    }

    @Override
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var okButton = new MMButton("okButton", resources.getString("ConfigurationDialog.Ok.text"),
                resources.getString("ConfigurationDialog.Ok.toolTipText"), this::okButtonActionPerformed);
        panel.add(okButton);
        panel.add(new MMButton("cancelButton", resources.getString("ConfigurationDialog.Cancel.text"),
                resources.getString("ConfigurationDialog.Cancel.toolTipText"), this::cancelActionPerformed));
        getRootPane().setDefaultButton(okButton);
        return panel;
    }

    @Override
    protected void okAction() {
        colorPreferences.getAllColors().forEach(CConfig::setParam);
        techSettings.getTechSettings().forEach(CConfig::setParam);
        exportSettingsPanel.getRecordSheetSettings().forEach(CConfig::setParam);
        miscSettingsPanel.getMiscSettings().forEach(CConfig::setParam);
        CConfig.saveConfig();
    }

    @Override
    protected void cancelAction() {
        CConfig.loadConfigFile();
    }
}
