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

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import megamek.MMConstants;
import megamek.client.ui.Messages;
import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.comboBoxes.MMComboBox;
import megamek.client.ui.dialogs.buttonDialogs.CommonSettingsDialog;
import megamek.client.ui.dialogs.helpDialogs.HelpDialog;
import megamek.common.preference.PreferenceManager;
import megamek.logging.MMLogger;
import megameklab.ui.MMLStartUp;
import megameklab.ui.MulDndBehaviour;
import megameklab.ui.util.SpringUtilities;
import megameklab.util.CConfig;

/**
 * A panel allowing to change MML's general preferences
 */
public class MiscSettingsPanel extends JPanel {
    private static final MMLogger logger = MMLogger.create(MiscSettingsPanel.class);

    private static final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Dialogs");

    private final MMComboBox<MMLStartUp> startUpMMComboBox = new MMComboBox<>("StartUp", MMLStartUp.values());
    private final JCheckBox chkSummaryFormatTRO = new JCheckBox();
    private final JCheckBox chkApplicationExitPrompt = new JCheckBox();
    private final JCheckBox chkSkipSavePrompts = new JCheckBox();
    private final JCheckBox chkIncludeLicense = new JCheckBox();
    private final JTextField txtUserDir = new JTextField(20);
    private final JSlider guiScale = new JSlider();
    private final MMComboBox<MulDndBehaviour> cbMulOpenBehaviour = new MMComboBox<>("MUL Drag and Drop behaviour",
          MulDndBehaviour.values());

    MiscSettingsPanel(JFrame parent) {
        startUpMMComboBox.setRenderer(miscComboBoxRenderer);
        startUpMMComboBox.setSelectedItem(CConfig.getStartUpType());
        startUpMMComboBox.setToolTipText(resources.getString("ConfigurationDialog.startup.tooltip"));
        JLabel startUpLabel = new JLabel(resources.getString("ConfigurationDialog.startup.text"));
        startUpLabel.setToolTipText(resources.getString("ConfigurationDialog.startup.tooltip"));
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");

        JPanel startUpLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        startUpLine.add(startUpLabel);
        startUpLine.add(Box.createHorizontalStrut(5));
        startUpLine.add(startUpMMComboBox);

        cbMulOpenBehaviour.setRenderer(miscComboBoxRenderer);
        cbMulOpenBehaviour.setToolTipText(resources.getString("ConfigurationDialog.cbMulOpenBehaviour.tooltip"));
        int behaviourValue = CConfig.getIntParam(CConfig.MISC_MUL_OPEN_BEHAVIOUR);
        MulDndBehaviour[] allValues = MulDndBehaviour.values();
        if (behaviourValue >= 0 && behaviourValue < allValues.length) {
            cbMulOpenBehaviour.setSelectedItem(allValues[behaviourValue]);
        }
        JLabel mulOpenLabel = new JLabel(resources.getString("ConfigurationDialog.cbMulOpenBehaviour.text"));
        mulOpenLabel.setToolTipText(resources.getString("ConfigurationDialog.cbMulOpenBehaviour.tooltip"));

        JPanel mulOpenLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mulOpenLine.add(mulOpenLabel);
        mulOpenLine.add(Box.createHorizontalStrut(5));
        mulOpenLine.add(cbMulOpenBehaviour);

        JLabel userDirLabel = new JLabel(resourceMap.getString("ConfigurationDialog.userDir.text"));
        userDirLabel.setToolTipText(resourceMap.getString("ConfigurationDialog.userDir.tooltip"));
        txtUserDir.setToolTipText(resourceMap.getString("ConfigurationDialog.userDir.tooltip"));
        txtUserDir.setText(PreferenceManager.getClientPreferences().getUserDir());
        JButton userDirChooser = new JButton("...");
        userDirChooser.addActionListener(e -> CommonSettingsDialog.fileChooseUserDir(txtUserDir, parent));
        userDirChooser.setToolTipText(resourceMap.getString("ConfigurationDialog.userDir.chooser.title"));
        JButton userDirHelp = new JButton("Help");
        try {
            String helpTitle = Messages.getString("UserDirHelpDialog.title");
            URL helpFile = new File(MMConstants.USER_DIR_README_FILE).toURI().toURL();
            userDirHelp.addActionListener(e -> new HelpDialog(helpTitle, helpFile, parent).setVisible(true));
        } catch (MalformedURLException e) {
            logger.error("Could not find the user data directory readme file at {}", MMConstants.USER_DIR_README_FILE);
        }
        JPanel userDirLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userDirLine.add(userDirLabel);
        userDirLine.add(Box.createHorizontalStrut(5));
        userDirLine.add(txtUserDir);
        userDirLine.add(Box.createHorizontalStrut(10));
        userDirLine.add(userDirChooser);
        userDirLine.add(Box.createHorizontalStrut(10));
        userDirLine.add(userDirHelp);

        chkApplicationExitPrompt.setText(resources.getString("ConfigurationDialog.chkApplicationExitPrompt.text"));
        chkApplicationExitPrompt.setToolTipText(resources.getString(
              "ConfigurationDialog.chkApplicationExitPrompt.tooltip"));
        chkApplicationExitPrompt.setSelected(CConfig.getBooleanParam(CConfig.MISC_APPLICATION_EXIT_PROMPT));

        chkSummaryFormatTRO.setText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.text"));
        chkSummaryFormatTRO.setToolTipText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.tooltip"));
        chkSummaryFormatTRO.setSelected(CConfig.getBooleanParam(CConfig.MISC_SUMMARY_FORMAT_TRO));

        chkSkipSavePrompts.setText(resources.getString("ConfigurationDialog.chkSkipSavePrompts.text"));
        chkSkipSavePrompts.setToolTipText(resources.getString("ConfigurationDialog.chkSkipSavePrompts.tooltip"));
        chkSkipSavePrompts.setSelected(CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS));

        chkIncludeLicense.setText(resources.getString("ConfigurationDialog.chkIncludeLicense.text"));
        chkIncludeLicense.setToolTipText(resources.getString("ConfigurationDialog.chkIncludeLicense.tooltip"));
        chkIncludeLicense.setSelected(CConfig.includeLicense());

        guiScale.setMajorTickSpacing(3);
        guiScale.setMinimum(7);
        guiScale.setMaximum(24);
        Hashtable<Integer, JComponent> table = new Hashtable<>();
        table.put(7, new JLabel("70%"));
        table.put(10, new JLabel("100%"));
        table.put(16, new JLabel("160%"));
        table.put(22, new JLabel("220%"));
        guiScale.setLabelTable(table);
        guiScale.setPaintTicks(true);
        guiScale.setPaintLabels(true);
        guiScale.setValue((int) (GUIPreferences.getInstance().getGUIScale() * 10));
        guiScale.setToolTipText(Messages.getString("CommonSettingsDialog.guiScaleTT"));
        JLabel guiScaleLabel = new JLabel(Messages.getString("CommonSettingsDialog.guiScale"));
        JPanel scaleLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        scaleLine.add(guiScaleLabel);
        scaleLine.add(Box.createHorizontalStrut(5));
        scaleLine.add(guiScale);

        JPanel gridPanel = new JPanel(new SpringLayout());
        gridPanel.add(startUpLine);
        gridPanel.add(userDirLine);
        gridPanel.add(mulOpenLine);
        gridPanel.add(chkApplicationExitPrompt);
        gridPanel.add(chkSummaryFormatTRO);
        gridPanel.add(chkSkipSavePrompts);
        gridPanel.add(chkIncludeLicense);
        gridPanel.add(scaleLine);

        SpringUtilities.makeCompactGrid(gridPanel, 8, 1, 0, 0, 15, 10);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    Map<String, String> getMiscSettings() {
        Map<String, String> miscSettings = new HashMap<>();
        miscSettings.put(CConfig.MISC_APPLICATION_EXIT_PROMPT, String.valueOf(chkApplicationExitPrompt.isSelected()));
        miscSettings.put(CConfig.MISC_SUMMARY_FORMAT_TRO, String.valueOf(chkSummaryFormatTRO.isSelected()));
        miscSettings.put(CConfig.MISC_SKIP_SAFETY_PROMPTS, String.valueOf(chkSkipSavePrompts.isSelected()));
        miscSettings.put(CConfig.MISC_INCLUDE_LICENSE, String.valueOf(chkIncludeLicense.isSelected()));
        MMLStartUp startUp = startUpMMComboBox.getSelectedItem() == null
              ? MMLStartUp.SPLASH_SCREEN
              : startUpMMComboBox.getSelectedItem();
        miscSettings.put(CConfig.MISC_STARTUP, startUp.name());
        MulDndBehaviour selectedMulBehaviour = cbMulOpenBehaviour.getSelectedItem();
        int ordinalToSave = (selectedMulBehaviour != null) ?
              selectedMulBehaviour.ordinal() :
              MulDndBehaviour.LOAD_FORCE.ordinal();
        miscSettings.put(CConfig.MISC_MUL_OPEN_BEHAVIOUR, String.valueOf(ordinalToSave));
        // User directory and gui scale are stored in MM's client settings, not in CConfig, therefore not added here
        return miscSettings;
    }

    String getUserDir() {
        return txtUserDir.getText();
    }

    float guiScale() {
        return 0.1f * guiScale.getValue();
    }

    DefaultListCellRenderer miscComboBoxRenderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
              boolean cellHasFocus) {
            return super.getListCellRendererComponent(list, displayName(value), index, isSelected, cellHasFocus);
        }
    };

    private String displayName(Object value) {
        if (value instanceof MMLStartUp su) {
            return su.getDisplayName();
        } else if (value instanceof MulDndBehaviour mdb) {
            return mdb.getDisplayName();
        }
        return "";
    }
}
