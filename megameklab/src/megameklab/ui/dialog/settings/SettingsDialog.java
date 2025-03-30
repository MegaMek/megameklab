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

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import megamek.client.ui.baseComponents.MMButton;
import megamek.client.ui.swing.GUIPreferences;
import megamek.common.preference.PreferenceManager;
import megameklab.MegaMekLab;
import megameklab.ui.dialog.AbstractMMLButtonDialog;
import megameklab.util.CConfig;

/**
 * This is MML's general preferences dialog with color, record sheet and other settings.
 */
public class SettingsDialog extends AbstractMMLButtonDialog {

    private final ColorSettingsPanel colorPreferences = new ColorSettingsPanel();
    private final TechSettingsPanel techSettings = new TechSettingsPanel();
    private final ExportSettingsPanel exportSettingsPanel = new ExportSettingsPanel();
    private final MiscSettingsPanel miscSettingsPanel;

    public SettingsDialog(JFrame frame) {
        super(frame, true, "ConfigurationDialog", "ConfigurationDialog.windowName.text");
        miscSettingsPanel = new MiscSettingsPanel(frame);
        initialize();
    }

    @Override
    protected Container createCenterPane() {
        JTabbedPane panMain = new JTabbedPane();
        panMain.addTab(resources.getString("ConfigurationDialog.misc.title"), new SettingsScrollPane(miscSettingsPanel));
        panMain.addTab(resources.getString("ConfigurationDialog.colorCodes.title"), new SettingsScrollPane(colorPreferences));
        panMain.addTab(resources.getString("ConfigurationDialog.techProgression.title"), new SettingsScrollPane(techSettings));
        panMain.addTab(resources.getString("ConfigurationDialog.printing.title"), new SettingsScrollPane(exportSettingsPanel));
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
        PreferenceManager.getClientPreferences().setUserDir(miscSettingsPanel.getUserDir());
        if (miscSettingsPanel.guiScale() != GUIPreferences.getInstance().getGUIScale()) {
            GUIPreferences.getInstance().setValue(GUIPreferences.GUI_SCALE, miscSettingsPanel.guiScale());
            MegaMekLab.updateGuiScaling();
        }
    }

    @Override
    protected void cancelAction() {
        CConfig.loadConfigFile();
    }

    private static class SettingsScrollPane extends JScrollPane {
        SettingsScrollPane(Component view) {
            super(view);
            setBorder(null);
            getVerticalScrollBar().setUnitIncrement(16);
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
        pack();
    }
}
