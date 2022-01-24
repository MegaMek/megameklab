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

import megamek.common.util.EncodeControl;
import megameklab.com.printing.PaperSize;
import megameklab.com.ui.util.IntRangeTextField;
import megameklab.com.ui.util.SpringUtilities;
import megameklab.com.util.CConfig;
import megameklab.com.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

class ExportSettingsPanel extends JPanel {

    private final JComboBox<String> cbPaper = new JComboBox<>();
    private final JCheckBox chkColor = new JCheckBox();
    private final JComboBox<String> cbFont = new JComboBox<>();
    private final JTextArea txtFontDisplay = new JTextArea();
    private final JCheckBox chkProgressBar = new JCheckBox();
    private final JCheckBox chkShowReferenceTables = new JCheckBox();
    private final JCheckBox chkShowQuirks = new JCheckBox();
    private final JCheckBox chkShowPilotData = new JCheckBox();
    private final JCheckBox chkShowEraIcon = new JCheckBox();
    private final JCheckBox chkShowRole = new JCheckBox();
    private final JCheckBox chkHeatProfile = new JCheckBox();
    private final JCheckBox chkTacOpsHeat = new JCheckBox();
    private final JComboBox<String> cbRSScale = new JComboBox<>();
    private final IntRangeTextField txtScale = new IntRangeTextField(3);
    private final JCheckBox chkSummaryFormatTRO = new JCheckBox();

    ExportSettingsPanel() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs", new EncodeControl());

        chkSummaryFormatTRO.setText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.text"));
        chkSummaryFormatTRO.setToolTipText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.tooltip"));
        chkSummaryFormatTRO.setSelected(CConfig.getBooleanParam(CConfig.SUMMARY_FORMAT_TRO));

        for (PaperSize paper : PaperSize.values()) {
            cbPaper.addItem(paper.displayName);
        }
        String paper = CConfig.getParam(CConfig.RS_PAPER_SIZE, PaperSize.US_LETTER.name());
        try {
            cbPaper.setSelectedItem(PaperSize.valueOf(paper).displayName);
        } catch (Exception ex) {
            cbPaper.setSelectedItem(PaperSize.US_LETTER.displayName);
        }
        cbPaper.setToolTipText(resourceMap.getString("ConfigurationDialog.cbPaper.tooltip"));
        JPanel paperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        paperPanel.add(new JLabel(resourceMap.getString("ConfigurationDialog.cbPaper.text")));
        paperPanel.add(Box.createHorizontalStrut(25));
        paperPanel.add(cbPaper);

        for (String family : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            cbFont.addItem(family);
        }
        cbFont.setSelectedItem(UnitUtil.deriveFont(8).getFamily());
        cbFont.setToolTipText(resourceMap.getString("ConfigurationDialog.cbFont.tooltip"));
        cbFont.addActionListener(ev -> updateFont());

        txtFontDisplay.setEditable(false);
        txtFontDisplay.setText(resourceMap.getString("ConfigurationDialog.txtFontDisplay.text"));
        updateFont();

        JPanel fontPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        fontPanel.add(new JLabel(resourceMap.getString("ConfigurationDialog.cbFont.text")));
        fontPanel.add(Box.createHorizontalStrut(25));
        fontPanel.add(cbFont);
        fontPanel.add(Box.createHorizontalStrut(25));
        fontPanel.add(txtFontDisplay);

        chkProgressBar.setText(resourceMap.getString("ConfigurationDialog.chkProgressBar.text"));
        chkProgressBar.setToolTipText(resourceMap.getString("ConfigurationDialog.chkProgressBar.tooltip"));
        chkProgressBar.setSelected(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));

        chkColor.setText(resourceMap.getString("ConfigurationDialog.chkColor.text"));
        chkColor.setToolTipText(resourceMap.getString("ConfigurationDialog.chkColor.tooltip"));
        chkColor.setSelected(CConfig.getBooleanParam(CConfig.RS_COLOR));

        chkShowReferenceTables.setText(resourceMap.getString("ConfigurationDialog.chkShowReferenceTables.text"));
        chkShowReferenceTables.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowReferenceTables.tooltip"));
        chkShowReferenceTables.setSelected(CConfig.getBooleanParam(CConfig.RS_REFERENCE));

        chkShowQuirks.setText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.text"));
        chkShowQuirks.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.tooltip"));
        chkShowQuirks.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS));

        chkShowPilotData.setText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.text"));
        chkShowPilotData.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.tooltip"));
        chkShowPilotData.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA));

        chkShowEraIcon.setText(resourceMap.getString("ConfigurationDialog.chkShowEraIcon.text"));
        chkShowEraIcon.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowEraIcon.tooltip"));
        chkShowEraIcon.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_ERA));

        chkShowRole.setText(resourceMap.getString("ConfigurationDialog.chkShowRole.text"));
        chkShowRole.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowRole.tooltip"));
        chkShowRole.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_ROLE));

        chkHeatProfile.setText(resourceMap.getString("ConfigurationDialog.chkHeatProfile.text"));
        chkHeatProfile.setToolTipText(resourceMap.getString("ConfigurationDialog.chkHeatProfile.tooltip"));
        chkHeatProfile.setSelected(CConfig.getBooleanParam(CConfig.RS_HEAT_PROFILE));

        chkTacOpsHeat.setText(resourceMap.getString("ConfigurationDialog.chkTacOpsHeat.text"));
        chkTacOpsHeat.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTacOpsHeat.tooltip"));
        chkTacOpsHeat.setSelected(CConfig.getBooleanParam(CConfig.RS_TAC_OPS_HEAT));

        for (CConfig.RSScale val : CConfig.RSScale.values()) {
            cbRSScale.addItem(val.fullName);
        }
        cbRSScale.setSelectedIndex(CConfig.scaleUnits().ordinal());
        txtScale.setText(CConfig.getParam(CConfig.RS_SCALE_FACTOR));
        txtScale.setMaximum(100);
        txtScale.setMinimum(1);
        cbRSScale.setToolTipText(resourceMap.getString("ConfigurationDialog.cbRSScale.tooltip"));
        cbRSScale.addActionListener(ev -> {
            cbRSScale.setToolTipText(resourceMap.getString("ConfigurationDialog.txtScale.tooltip"));
            txtScale.setText("" + getDefaultScale());
        });

        JPanel scalePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        scalePanel.add(new JLabel(resourceMap.getString("ConfigurationDialog.cbRSScale.label")));
        scalePanel.add(Box.createHorizontalStrut(25));
        scalePanel.add(txtScale);
        scalePanel.add(cbRSScale);

        JPanel gridPanel = new JPanel(new SpringLayout());
        gridPanel.add(chkSummaryFormatTRO);
        gridPanel.add(chkProgressBar);
        gridPanel.add(paperPanel);
        gridPanel.add(fontPanel);
        gridPanel.add(chkColor);
        gridPanel.add(chkShowReferenceTables);
        gridPanel.add(chkShowQuirks);
        gridPanel.add(chkShowPilotData);
        gridPanel.add(chkShowEraIcon);
        gridPanel.add(chkShowRole);
        gridPanel.add(chkHeatProfile);
        gridPanel.add(chkTacOpsHeat);
        gridPanel.add(scalePanel);
        SpringUtilities.makeCompactGrid(gridPanel, 13, 1, 0, 0, 15, 10);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    Map<String, String> getRecordSheetSettings() {
        Map<String, String> recordSheetSettings = new HashMap<>();
        recordSheetSettings.put(CConfig.RS_PAPER_SIZE, PaperSize.values()[cbPaper.getSelectedIndex()].toString());
        recordSheetSettings.put(CConfig.RS_FONT, (String) cbFont.getSelectedItem());
        recordSheetSettings.put(CConfig.RS_PROGRESS_BAR, String.valueOf(chkProgressBar.isSelected()));
        recordSheetSettings.put(CConfig.RS_COLOR, Boolean.toString(chkColor.isSelected()));
        recordSheetSettings.put(CConfig.RS_REFERENCE, Boolean.toString(chkShowReferenceTables.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_QUIRKS, Boolean.toString(chkShowQuirks.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_PILOT_DATA, Boolean.toString(chkShowPilotData.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_ERA, Boolean.toString(chkShowEraIcon.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_ROLE, Boolean.toString(chkShowRole.isSelected()));
        recordSheetSettings.put(CConfig.RS_HEAT_PROFILE, Boolean.toString(chkHeatProfile.isSelected()));
        recordSheetSettings.put(CConfig.RS_TAC_OPS_HEAT, Boolean.toString(chkTacOpsHeat.isSelected()));
        recordSheetSettings.put(CConfig.RS_SCALE_UNITS, CConfig.RSScale.values()[cbRSScale.getSelectedIndex()].toString());
        recordSheetSettings.put(CConfig.RS_SCALE_FACTOR, Integer.toString(txtScale.getIntVal(getDefaultScale())));
        recordSheetSettings.put(CConfig.SUMMARY_FORMAT_TRO, Boolean.toString(chkSummaryFormatTRO.isSelected()));
        return recordSheetSettings;
    }

    private void updateFont() {
        Font font = Font.decode((String) cbFont.getSelectedItem());
        txtFontDisplay.setFont(font);
    }

    private int getDefaultScale() {
        if (CConfig.RSScale.INCHES.fullName.equals(cbRSScale.getSelectedItem())) {
            return 2;
        } else if (CConfig.RSScale.CENTIMETERS.fullName.equals(cbRSScale.getSelectedItem())) {
            return 5;
        } else {
            return 1;
        }
    }


}
