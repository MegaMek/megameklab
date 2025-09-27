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
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import megamek.client.ui.comboBoxes.MMComboBox;
import megamek.common.enums.WeaponSortOrder;
import megameklab.printing.MekChassisArrangement;
import megameklab.printing.PaperSize;
import megameklab.printing.PrintEntity;
import megameklab.printing.RecordSheetOptions;
import megameklab.ui.util.IntRangeTextField;
import megameklab.ui.util.SpringUtilities;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import megameklab.util.UnitUtil;

/**
 * A panel allowing to change MML's export (record sheet and clipboard) preferences
 */
class ExportSettingsPanel extends JPanel {

    private final JComboBox<String> cbPaper = new JComboBox<>();
    private final JComboBox<String> cbFont = new JComboBox<>();
    private final JTextArea txtFontDisplay = new JTextArea();
    private final JCheckBox chkProgressBar = new JCheckBox();
    private final JCheckBox chkShowReferenceTables = new JCheckBox();
    private final JCheckBox chkShowCondensedTables = new JCheckBox();
    private final JCheckBox chkShowQuirks = new JCheckBox();
    private final JCheckBox chkShowPilotData = new JCheckBox();
    private final JCheckBox chkShowForceData = new JCheckBox();
    private final JCheckBox chkShowDamage = new JCheckBox();
    private final JButton btnDamageColor = new JButton();
    private final JCheckBox chkShowEraIcon = new JCheckBox();
    private final JCheckBox chkShowRole = new JCheckBox();
    private final JCheckBox chkHeatProfile = new JCheckBox();
    private final JCheckBox chkTacOpsHeat = new JCheckBox();
    private final JComboBox<String> cbRSScale = new JComboBox<>();
    private final IntRangeTextField txtScale = new IntRangeTextField(3);
    private final MMComboBox<MekChassisArrangement> mekChassis =
          new MMComboBox<>("Mek Names", MekChassisArrangement.values());
    private final JCheckBox chkRowShading = new JCheckBox();
    private final JCheckBox chkAlternateArmorGrouping = new JCheckBox();
    private final JCheckBox chkFrameless = new JCheckBox();
    private final JCheckBox chkBoldType = new JCheckBox();
    private final JCheckBox chkMergeIdenticalEquipment = new JCheckBox();
    private final MMComboBox<WeaponSortOrder> comboDefaultWeaponSortOrder;
    private final MMComboBox<RecordSheetOptions.ColorMode> comboColorMode;
    private final MMComboBox<RecordSheetOptions.HeatScaleMarker> comboHeatScaleMarker;
    private final MMComboBox<RecordSheetOptions.HitModStyle> comboHitMod;
    private final MMComboBox<RecordSheetOptions.IntrinsicPhysicalAttacksStyle> comboIntrinsicPhysicals;
    private final MMComboBox<RecordSheetOptions.ExplicitZeroModifierStyle> comboExplicitZeroModifier;
    private final JCheckBox chkExtraPhysicals = new JCheckBox();
    private final JCheckBox chkFancyPips = new JCheckBox();

    ExportSettingsPanel() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");

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

        JLabel defaultSortOrderLabel = new JLabel(resourceMap.getString("ConfigurationDialog.weaponsOrder.text"));
        String toolTip = resourceMap.getString("ConfigurationDialog.weaponsOrder.tooltip");
        defaultSortOrderLabel.setToolTipText(toolTip);

        final DefaultComboBoxModel<WeaponSortOrder> defaultWeaponSortOrderModel = new DefaultComboBoxModel<>(
              WeaponSortOrder.values());
        defaultWeaponSortOrderModel.removeElement(WeaponSortOrder.CUSTOM); // Custom makes no sense as a default
        comboDefaultWeaponSortOrder = new MMComboBox<>("comboDefaultWeaponSortOrder", defaultWeaponSortOrderModel);
        comboDefaultWeaponSortOrder.setToolTipText(toolTip);
        comboDefaultWeaponSortOrder.setSelectedItem(CConfig.getEnumParam(CConfig.RS_WEAPONS_ORDER,
              WeaponSortOrder.class,
              WeaponSortOrder.DEFAULT));

        JPanel weaponSortOrderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        weaponSortOrderPanel.add(defaultSortOrderLabel);
        weaponSortOrderPanel.add(Box.createHorizontalStrut(25));
        weaponSortOrderPanel.add(comboDefaultWeaponSortOrder);

        chkProgressBar.setText(resourceMap.getString("ConfigurationDialog.chkProgressBar.text"));
        chkProgressBar.setToolTipText(resourceMap.getString("ConfigurationDialog.chkProgressBar.tooltip"));
        chkProgressBar.setSelected(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));


        JLabel colorModeLabel = new JLabel(resourceMap.getString("ConfigurationDialog.chkColor.text"));
        String colorModeToolTip = resourceMap.getString("ConfigurationDialog.chkColor.tooltip");
        colorModeLabel.setToolTipText(colorModeToolTip);
        final DefaultComboBoxModel<RecordSheetOptions.ColorMode> defaultColorModeModel = new DefaultComboBoxModel<>(
              RecordSheetOptions.ColorMode.values());
        comboColorMode = new MMComboBox<>("comboColorMode", defaultColorModeModel);
        comboColorMode.setToolTipText(colorModeToolTip);
        comboColorMode.setSelectedItem(CConfig.getEnumParam(CConfig.RS_COLOR, RecordSheetOptions.ColorMode.class,
              RecordSheetOptions.ColorMode.ALL));

        JPanel colorModePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        colorModePanel.add(colorModeLabel);
        colorModePanel.add(Box.createHorizontalStrut(25));
        colorModePanel.add(comboColorMode);

        ComboBoxPanel<RecordSheetOptions.HeatScaleMarker> heatScaleMarkerCombo = createComboPanel(
              "comboHeatScaleMarker",
              RecordSheetOptions.HeatScaleMarker.class,
              CConfig.getEnumParam(CConfig.RS_HEAT_SCALE_MARKER, RecordSheetOptions.HeatScaleMarker.class,
                    RecordSheetOptions.HeatScaleMarker.ASTERISK),
              resourceMap.getString("ConfigurationDialog.heatScaleMarker.text"),
              resourceMap.getString("ConfigurationDialog.heatScaleMarker.tooltip")
        );

        comboHeatScaleMarker = heatScaleMarkerCombo.comboBox;
        JPanel heatScaleMarkerPanel = heatScaleMarkerCombo.panel;

        chkAlternateArmorGrouping.setText(resourceMap.getString("ConfigurationDialog.chkAlternateArmorGrouping.text"));
        chkAlternateArmorGrouping.setToolTipText(resourceMap.getString(
              "ConfigurationDialog.chkAlternateArmorGrouping.tooltip"));
        chkAlternateArmorGrouping.setSelected(CConfig.getBooleanParam(CConfig.RS_ARMOR_GROUPING));

        chkFrameless.setText(resourceMap.getString("ConfigurationDialog.chkFrameless.text"));
        chkFrameless.setToolTipText(resourceMap.getString("ConfigurationDialog.chkFrameless.tooltip"));
        chkFrameless.setSelected(CConfig.getBooleanParam(CConfig.RS_FRAMELESS));

        chkBoldType.setText(resourceMap.getString("ConfigurationDialog.chkBoldType.text"));
        chkBoldType.setToolTipText(resourceMap.getString("ConfigurationDialog.chkBoldType.tooltip"));
        chkBoldType.setSelected(CConfig.getBooleanParam(CConfig.RS_BOLD_TYPE));

        chkMergeIdenticalEquipment.setText(resourceMap.getString("ConfigurationDialog.chkMergeIdenticalEquipment.text"));
        chkMergeIdenticalEquipment.setToolTipText(resourceMap.getString(
              "ConfigurationDialog.chkMergeIdenticalEquipment.tooltip"));
        chkMergeIdenticalEquipment.setSelected(CConfig.getBooleanParam(CConfig.RS_MERGE_IDENTICAL_EQUIPMENT));

        ComboBoxPanel<RecordSheetOptions.HitModStyle> hitModCombo = createComboPanel(
              "comboHitMod",
              RecordSheetOptions.HitModStyle.class,
              CConfig.getEnumParam(CConfig.RS_HIT_MOD, RecordSheetOptions.HitModStyle.class,
                    RecordSheetOptions.HitModStyle.NONE),
              resourceMap.getString("ConfigurationDialog.chkHitMod.text"),
              resourceMap.getString("ConfigurationDialog.chkHitMod.tooltip")
        );

        comboHitMod = hitModCombo.comboBox;
        JPanel hitModPanel = hitModCombo.panel;


        ComboBoxPanel<RecordSheetOptions.IntrinsicPhysicalAttacksStyle> intrinsicPhysicalsCombo = createComboPanel(
              "comboIntrinsicPhysicals",
              RecordSheetOptions.IntrinsicPhysicalAttacksStyle.class,
              CConfig.getEnumParam(CConfig.RS_INTRINSIC_PHYSICALS,
                    RecordSheetOptions.IntrinsicPhysicalAttacksStyle.class,
                    RecordSheetOptions.IntrinsicPhysicalAttacksStyle.NONE),
              resourceMap.getString("ConfigurationDialog.comboIntrinsicPhysicals.text"),
              resourceMap.getString("ConfigurationDialog.comboIntrinsicPhysicals.tooltip")
        );

        comboIntrinsicPhysicals = intrinsicPhysicalsCombo.comboBox;
        JPanel intrinsicPhysicalsPanel = intrinsicPhysicalsCombo.panel;

        chkExtraPhysicals.setText(resourceMap.getString("ConfigurationDialog.chkExtraPhysicals.text"));
        chkExtraPhysicals.setToolTipText(resourceMap.getString("ConfigurationDialog.chkExtraPhysicals.tooltip"));
        chkExtraPhysicals.setSelected(CConfig.getBooleanParam(CConfig.RS_EXTRA_PHYSICALS));

        ComboBoxPanel<RecordSheetOptions.ExplicitZeroModifierStyle> explicitZeroModifierCombo = createComboPanel(
              "comboExplicitZeroModifier",
              RecordSheetOptions.ExplicitZeroModifierStyle.class,
              CConfig.getEnumParam(CConfig.RS_EXPLICIT_ZERO_MOD, RecordSheetOptions.ExplicitZeroModifierStyle.class,
                    RecordSheetOptions.ExplicitZeroModifierStyle.DASH),
              resourceMap.getString("ConfigurationDialog.comboExplicitZeroModifier.text"),
              resourceMap.getString("ConfigurationDialog.comboExplicitZeroModifier.tooltip")
        );

        comboExplicitZeroModifier = explicitZeroModifierCombo.comboBox;
        JPanel explicitZeroModifierPanel = explicitZeroModifierCombo.panel;

        chkRowShading.setText(resourceMap.getString("ConfigurationDialog.chkRowShading.text"));
        chkRowShading.setToolTipText(resourceMap.getString("ConfigurationDialog.chkRowShading.tooltip"));
        chkRowShading.setSelected(CConfig.getBooleanParam(CConfig.RS_ROW_SHADING));

        chkShowReferenceTables.setText(resourceMap.getString("ConfigurationDialog.chkShowReferenceTables.text"));
        chkShowReferenceTables.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowReferenceTables.tooltip"));
        chkShowReferenceTables.setSelected(CConfig.getBooleanParam(CConfig.RS_REFERENCE));

        chkShowCondensedTables.setText(resourceMap.getString("ConfigurationDialog.chkShowCondensedTables.text"));
        chkShowCondensedTables.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowCondensedTables.tooltip"));
        chkShowCondensedTables.setSelected(CConfig.getBooleanParam(CConfig.RS_CONDENSED_REFERENCE));

        chkShowQuirks.setText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.text"));
        chkShowQuirks.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.tooltip"));
        chkShowQuirks.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS));

        chkShowPilotData.setText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.text"));
        chkShowPilotData.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.tooltip"));
        chkShowPilotData.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA));

        chkShowForceData.setText(resourceMap.getString("ConfigurationDialog.chkShowForceData.text"));
        chkShowForceData.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowForceData.tooltip"));
        chkShowForceData.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_C3BV));

        chkShowDamage.setText(resourceMap.getString("ConfigurationDialog.chkShowDamage.text"));
        chkShowDamage.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowDamage.tooltip"));
        chkShowDamage.setSelected(CConfig.getBooleanParam(CConfig.RS_DAMAGE));

        btnDamageColor.setText(resourceMap.getString("ConfigurationDialog.btnDamageColor.text"));
        btnDamageColor.setToolTipText(resourceMap.getString("ConfigurationDialog.btnDamageColor.tooltip"));
        Color initial = Color.decode(CConfig.getParam(CConfig.RS_DAMAGE_COLOR, PrintEntity.FILL_RED));
        btnDamageColor.setBackground(initial);
        btnDamageColor.addActionListener(ev -> {
            Color chosen = JColorChooser.showDialog(
                  ExportSettingsPanel.this,
                  resourceMap.getString("ConfigurationDialog.btnDamageColor.text"),
                  btnDamageColor.getBackground());
            if (chosen != null) {
                btnDamageColor.setBackground(chosen);
            }
        });

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

        chkFancyPips.setText(resourceMap.getString("ConfigurationDialog.chkFancyPips.text"));
        chkFancyPips.setToolTipText(resourceMap.getString("ConfigurationDialog.chkFancyPips.tooltip"));
        chkFancyPips.setSelected(CConfig.getBooleanParam(CConfig.RS_FANCY_PIPS));

        mekChassis.setRenderer(mekNameArrangementRenderer);
        mekChassis.setSelectedItem(CConfig.getMekNameArrangement());
        mekChassis.setToolTipText(resourceMap.getString("ConfigurationDialog.mekChassis.tooltip"));
        JLabel startUpLabel = new JLabel(resourceMap.getString("ConfigurationDialog.mekChassis.text"));
        startUpLabel.setToolTipText(resourceMap.getString("ConfigurationDialog.mekChassis.tooltip"));

        JPanel mekNameLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mekNameLine.add(startUpLabel);
        mekNameLine.add(Box.createHorizontalStrut(5));
        mekNameLine.add(mekChassis);

        for (RSScale val : RSScale.values()) {
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

        JPanel damagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        damagePanel.add(chkShowDamage, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        damagePanel.add(btnDamageColor, gbc);

        JPanel gridPanel = new JPanel(new SpringLayout());
        JPanel innerGridPanel = new JPanel(new SpringLayout());
        gridPanel.add(chkProgressBar);
        gridPanel.add(paperPanel);
        gridPanel.add(fontPanel);
        gridPanel.add(weaponSortOrderPanel);
        gridPanel.add(new JLabel(resourceMap.getString("ConfigurationDialog.txtOptions.label")));
        innerGridPanel.add(colorModePanel);
        innerGridPanel.add(heatScaleMarkerPanel);
        innerGridPanel.add(chkBoldType);
        innerGridPanel.add(chkRowShading);
        innerGridPanel.add(chkShowReferenceTables);
        innerGridPanel.add(chkShowCondensedTables);
        innerGridPanel.add(chkShowQuirks);
        innerGridPanel.add(chkShowPilotData);
        innerGridPanel.add(chkShowForceData);
        innerGridPanel.add(damagePanel);
        innerGridPanel.add(chkShowEraIcon);
        innerGridPanel.add(chkShowRole);
        innerGridPanel.add(chkHeatProfile);
        innerGridPanel.add(chkTacOpsHeat);
        innerGridPanel.add(chkAlternateArmorGrouping);
        innerGridPanel.add(chkFancyPips);
        innerGridPanel.add(chkFrameless);
        innerGridPanel.add(chkMergeIdenticalEquipment);
        innerGridPanel.add(hitModPanel);
        innerGridPanel.add(intrinsicPhysicalsPanel);
        innerGridPanel.add(chkExtraPhysicals);
        innerGridPanel.add(explicitZeroModifierPanel);
        innerGridPanel.add(new JLabel(""));
        gridPanel.add(innerGridPanel);
        gridPanel.add(mekNameLine);
        gridPanel.add(scalePanel);

        SpringUtilities.makeCompactGrid(innerGridPanel, 11, 2, 0, 0, 15, 6);
        SpringUtilities.makeCompactGrid(gridPanel, 8, 1, 0, 0, 15, 6);
        gridPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(gridPanel);
    }

    private record ComboBoxPanel<T extends Enum<T>>(JPanel panel, MMComboBox<T> comboBox) {
    }

    private <T extends Enum<T>> ComboBoxPanel<T> createComboPanel(
          String comboName,
          Class<T> enumClass,
          T defaultValue,
          String labelText,
          String tooltipText) {

        JLabel label = new JLabel(labelText);
        label.setToolTipText(tooltipText);

        final DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>(enumClass.getEnumConstants());
        MMComboBox<T> comboBox = new MMComboBox<>(comboName, model);
        comboBox.setToolTipText(tooltipText);
        comboBox.setSelectedItem(defaultValue);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.add(label);
        panel.add(Box.createHorizontalStrut(25));
        panel.add(comboBox);

        return new ComboBoxPanel<>(panel, comboBox);
    }


    Map<String, String> getRecordSheetSettings() {
        Map<String, String> recordSheetSettings = new HashMap<>();
        recordSheetSettings.put(CConfig.RS_PAPER_SIZE, PaperSize.values()[cbPaper.getSelectedIndex()].toString());
        recordSheetSettings.put(CConfig.RS_FONT, (String) cbFont.getSelectedItem());
        recordSheetSettings.put(CConfig.RS_PROGRESS_BAR, String.valueOf(chkProgressBar.isSelected()));
        recordSheetSettings.put(CConfig.RS_COLOR, Objects.requireNonNull(comboColorMode.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_ROW_SHADING, Boolean.toString(chkRowShading.isSelected()));
        recordSheetSettings.put(CConfig.RS_REFERENCE, Boolean.toString(chkShowReferenceTables.isSelected()));
        recordSheetSettings.put(CConfig.RS_CONDENSED_REFERENCE, Boolean.toString(chkShowCondensedTables.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_QUIRKS, Boolean.toString(chkShowQuirks.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_PILOT_DATA, Boolean.toString(chkShowPilotData.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_C3BV, Boolean.toString(chkShowForceData.isSelected()));
        recordSheetSettings.put(CConfig.RS_DAMAGE, Boolean.toString(chkShowDamage.isSelected()));
        recordSheetSettings.put(CConfig.RS_DAMAGE_COLOR,
              String.format("#%06X", btnDamageColor.getBackground().getRGB() & 0xFFFFFF));
        recordSheetSettings.put(CConfig.RS_SHOW_ERA, Boolean.toString(chkShowEraIcon.isSelected()));
        recordSheetSettings.put(CConfig.RS_SHOW_ROLE, Boolean.toString(chkShowRole.isSelected()));
        recordSheetSettings.put(CConfig.RS_HEAT_PROFILE, Boolean.toString(chkHeatProfile.isSelected()));
        recordSheetSettings.put(CConfig.RS_TAC_OPS_HEAT, Boolean.toString(chkTacOpsHeat.isSelected()));
        recordSheetSettings.put(CConfig.RS_SCALE_UNITS, RSScale.values()[cbRSScale.getSelectedIndex()].toString());
        recordSheetSettings.put(CConfig.RS_SCALE_FACTOR, Integer.toString(txtScale.getIntVal(getDefaultScale())));
        recordSheetSettings.put(CConfig.RS_MEK_NAMES,
              Objects.requireNonNullElse(mekChassis.getSelectedItem(), MekChassisArrangement.CLAN_IS).name());
        recordSheetSettings.put(CConfig.RS_ARMOR_GROUPING, Boolean.toString(chkAlternateArmorGrouping.isSelected()));
        recordSheetSettings.put(CConfig.RS_FRAMELESS, Boolean.toString(chkFrameless.isSelected()));
        recordSheetSettings.put(CConfig.RS_BOLD_TYPE, Boolean.toString(chkBoldType.isSelected()));
        recordSheetSettings.put(CConfig.RS_WEAPONS_ORDER,
              Objects.requireNonNull(comboDefaultWeaponSortOrder.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_MERGE_IDENTICAL_EQUIPMENT,
              Boolean.toString(chkMergeIdenticalEquipment.isSelected()));
        recordSheetSettings.put(CConfig.RS_HIT_MOD, Objects.requireNonNull(comboHitMod.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_INTRINSIC_PHYSICALS,
              Objects.requireNonNull(comboIntrinsicPhysicals.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_EXPLICIT_ZERO_MOD,
              Objects.requireNonNull(comboExplicitZeroModifier.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_HEAT_SCALE_MARKER,
              Objects.requireNonNull(comboHeatScaleMarker.getSelectedItem()).name());
        recordSheetSettings.put(CConfig.RS_EXTRA_PHYSICALS, Boolean.toString(chkExtraPhysicals.isSelected()));
        recordSheetSettings.put(CConfig.RS_FANCY_PIPS, Boolean.toString(chkFancyPips.isSelected()));
        return recordSheetSettings;
    }

    private void updateFont() {
        Font font = Font.decode((String) cbFont.getSelectedItem());
        txtFontDisplay.setFont(font);
    }

    private int getDefaultScale() {
        if (RSScale.INCHES.fullName.equals(cbRSScale.getSelectedItem())) {
            return 2;
        } else if (RSScale.CENTIMETERS.fullName.equals(cbRSScale.getSelectedItem())) {
            return 5;
        } else {
            return 1;
        }
    }

    DefaultListCellRenderer mekNameArrangementRenderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
              boolean cellHasFocus) {
            return super.getListCellRendererComponent(list, displayName(value), index, isSelected, cellHasFocus);
        }
    };

    private String displayName(Object value) {
        return (value instanceof MekChassisArrangement) ? ((MekChassisArrangement) value).getDisplayName() : "";
    }
}
