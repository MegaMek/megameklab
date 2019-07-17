/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import megamek.common.ITechnology;
import megamek.common.util.EncodeControl;
import megameklab.com.ui.util.IntRangeTextField;

public final class ConfigurationDialog extends JDialog implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6504846822457360057L;

    private final static String saveCommand = "Save"; //$NON-NLS-1$
    private final static String cancelCommand = "Cancel"; //$NON-NLS-1$

    // BUTTONS
    private final JButton btnSave = new JButton(saveCommand);
    private final JButton btnCancel = new JButton(cancelCommand);
    private JButton baseButton;

    private final JTabbedPane panMain = new JTabbedPane();
    private final JPanel panColors = new JPanel(new SpringLayout());
    private final JPanel panTech = new JPanel(new GridBagLayout());
    private final JPanel panPrinting = new JPanel(new GridBagLayout());
    private final JPanel panExport = new JPanel();
    
    private final JCheckBox chkTechProgression = new JCheckBox();
    private final JCheckBox chkTechUseYear = new JCheckBox();
    private final IntRangeTextField txtTechYear = new IntRangeTextField();
    private final JCheckBox chkTechShowFaction = new JCheckBox();
    private final JCheckBox chkShowExtinct = new JCheckBox();
    private final JCheckBox chkUnofficialIgnoreYear = new JCheckBox();
    
    private final JComboBox<String> cbFont = new JComboBox<>();
    private final JTextArea txtFontDisplay = new JTextArea();
    private final JCheckBox chkShowQuirks = new JCheckBox();
    private final JCheckBox chkShowPilotData = new JCheckBox();
    private final JCheckBox chkShowEraIcon = new JCheckBox();
    private final JCheckBox chkShowRole = new JCheckBox();
    private final JLabel lblFeatureLimitation = new JLabel();
    
    private final JCheckBox chkSummaryFormatTRO = new JCheckBox();
    
    //Store changes in the color configuration to write only if the user clicks save
    private final Map<String,String> colorMap = new HashMap<>();

    public ConfigurationDialog(JFrame frame) {
        super(frame, true);
        
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs", new EncodeControl()); //$NON-NLS-1$
        setTitle(resourceMap.getString("ConfigurationDialog.windowName.text")); //$NON-NLS-1$
        
        getContentPane().setLayout(new BorderLayout());
        add(panMain, BorderLayout.CENTER);
        JPanel panButtons = new JPanel();
        btnSave.setText(resourceMap.getString("ConfigurationDialog.btnSave.text")); //$NON-NLS-1$
        btnSave.setToolTipText(resourceMap.getString("ConfigurationDialog.btnSave.tooltip")); //$NON-NLS-1$
        btnSave.setActionCommand(saveCommand);
        btnSave.addActionListener(this);
        panButtons.add(btnSave);

        btnCancel.setText(resourceMap.getString("ConfigurationDialog.btnCancel.text")); //$NON-NLS-1$
        btnCancel.setToolTipText(resourceMap.getString("ConfigurationDialog.btnCancel.tooltip")); //$NON-NLS-1$
        btnCancel.setActionCommand(cancelCommand);
        btnCancel.addActionListener(this);
        panButtons.add(btnCancel);
        add(panButtons, BorderLayout.SOUTH);

        panMain.addTab(resourceMap.getString("ConfigurationDialog.colorCodes.title"), panColors); //$NON-NLS-1$
        panMain.addTab(resourceMap.getString("ConfigurationDialog.techProgression.title"), panTech); //$NON-NLS-1$
        panMain.addTab(resourceMap.getString("ConfigurationDialog.printing.title"), panPrinting); //$NON-NLS-1$
        panMain.addTab(resourceMap.getString("ConfigurationDialog.export.title"), panExport); //$NON-NLS-1$
        
        loadColorPanel();
        loadTechPanel(resourceMap);
        loadPrintingPanel(resourceMap);
        loadExportPanel(resourceMap);

        pack();
    }

    private void loadColorPanel() {
        addFields(CConfig.CONFIG_WEAPONS);
        addFields(CConfig.CONFIG_EQUIPMENT);
        addFields(CConfig.CONFIG_AMMO);
        addFields(CConfig.CONFIG_SYSTEMS);
        addFields(CConfig.CONFIG_EMPTY);
        SpringLayoutHelper.setupSpringGrid(panColors, 3);
    }

    private void addFields(String fieldName) {
        JLabel baseLabel = new JLabel(fieldName);
        baseLabel.setName(fieldName);
        baseLabel.setOpaque(true);
        baseLabel.setBackground(CConfig.getBackgroundColor(fieldName));
        baseLabel.setForeground(CConfig.getForegroundColor(fieldName));

        panColors.add(baseLabel);
        baseButton = new JButton("Foreground");
        baseButton.setName(fieldName + CConfig.CONFIG_FOREGROUND);
        baseButton.addActionListener(this);
        panColors.add(baseButton);
        baseButton = new JButton("Background");
        baseButton.setName(fieldName + CConfig.CONFIG_BACKGROUND);
        baseButton.addActionListener(this);
        panColors.add(baseButton);
    }
    
    private void loadTechPanel(ResourceBundle resourceMap) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        chkTechProgression.addActionListener(e -> {
            chkTechUseYear.setEnabled(chkTechProgression.isSelected());
            txtTechYear.setEnabled(chkTechProgression.isSelected()
                    && chkTechUseYear.isSelected());
        });
        chkTechProgression.setText(resourceMap.getString("ConfigurationDialog.chkTechProgression.text")); //$NON-NLS-1$
        chkTechProgression.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechProgression.tooltip")); //$NON-NLS-1$
        chkTechProgression.setSelected(CConfig.getBooleanParam(CConfig.TECH_PROGRESSION));
        panTech.add(chkTechProgression, gbc);
        
        gbc.gridy++;
        gbc.gridwidth = 1;
        chkTechUseYear.addActionListener(e -> {
            txtTechYear.setEnabled(chkTechUseYear.isSelected());
        });
        chkTechUseYear.setText(resourceMap.getString("ConfigurationDialog.chkTechYear.text")); //$NON-NLS-1$
        chkTechUseYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechYear.tooltip")); //$NON-NLS-1$
        chkTechUseYear.setSelected(CConfig.getBooleanParam(CConfig.TECH_USE_YEAR));
        panTech.add(chkTechUseYear, gbc);
        gbc.gridx = 1;
        txtTechYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechYear.tooltip")); //$NON-NLS-1$
        txtTechYear.setMinimum(ITechnology.DATE_PS);
        txtTechYear.setMaximum(9999);
        String year = CConfig.getParam(CConfig.TECH_YEAR);
        if (year.length() == 0) {
            year = "3145";
        }
        txtTechYear.setText(year);
        panTech.add(txtTechYear, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        chkTechShowFaction.setText(resourceMap.getString("ConfigurationDialog.chkTechShowFaction.text")); //$NON-NLS-1$
        chkTechShowFaction.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechShowFaction.tooltip")); //$NON-NLS-1$
        chkTechShowFaction.setSelected(CConfig.getBooleanParam(CConfig.TECH_SHOW_FACTION));
        panTech.add(chkTechShowFaction, gbc);
        
        gbc.gridy++;
        chkShowExtinct.setText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.text")); //$NON-NLS-1$
        chkShowExtinct.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.tooltip")); //$NON-NLS-1$
        chkShowExtinct.setSelected(CConfig.getBooleanParam(CConfig.TECH_EXTINCT));
        panTech.add(chkShowExtinct, gbc);
        
        gbc.gridy++;
        chkUnofficialIgnoreYear.setText(resourceMap.getString("ConfigurationDialog.chkUnofficialIgnoreYear.text")); //$NON-NLS-1$
        chkUnofficialIgnoreYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkUnofficialIgnoreYear.tooltip")); //$NON-NLS-1$
        chkUnofficialIgnoreYear.setSelected(CConfig.getBooleanParam(CConfig.TECH_UNOFFICAL_NO_YEAR));
        panTech.add(chkUnofficialIgnoreYear, gbc);
    }
    
    private void loadPrintingPanel(ResourceBundle resourceMap) {
        GridBagConstraints gbc = new GridBagConstraints();
        for (String family : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            cbFont.addItem(family);
        }
        cbFont.setSelectedItem(UnitUtil.deriveFont(8).getFamily());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panPrinting.add(new JLabel(resourceMap.getString("ConfigurationDialog.cbFont.text")), gbc);
        gbc.gridx = 1;
        panPrinting.add(cbFont, gbc);
        cbFont.addActionListener(ev -> updateFont());
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        txtFontDisplay.setEditable(false);
        txtFontDisplay.setText(resourceMap.getString("ConfigurationDialog.txtFontDisplay.text"));
        updateFont();
        panPrinting.add(txtFontDisplay, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        chkShowQuirks.setText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.text"));
        chkShowQuirks.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowQuirks.tooltip"));
        chkShowQuirks.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS));
        panPrinting.add(chkShowQuirks, gbc);
        gbc.gridy++;

        chkShowPilotData.setText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.text"));
        chkShowPilotData.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowPilotData.tooltip"));
        chkShowPilotData.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA));
        panPrinting.add(chkShowPilotData, gbc);
        gbc.gridy++;

        chkShowEraIcon.setText(resourceMap.getString("ConfigurationDialog.chkShowEraIcon.text"));
        chkShowEraIcon.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowEraIcon.tooltip"));
        chkShowEraIcon.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_ERA));
        panPrinting.add(chkShowEraIcon, gbc);
        gbc.gridy++;

        chkShowRole.setText(resourceMap.getString("ConfigurationDialog.chkShowRole.text"));
        chkShowRole.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowRole.tooltip"));
        chkShowRole.setSelected(CConfig.getBooleanParam(CConfig.RS_SHOW_ROLE));
        panPrinting.add(chkShowRole, gbc);
        gbc.gridy++;

        // Inform user that these options are not yet limited for all units
        lblFeatureLimitation.setText(resourceMap.getString("ConfigurationDialog.lblFeatureLimitation.text"));
        panPrinting.add(lblFeatureLimitation, gbc);
    }
    
    private void loadExportPanel(ResourceBundle resourceMap) {
        chkSummaryFormatTRO.setText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.text"));
        chkSummaryFormatTRO.setToolTipText(resourceMap.getString("ConfigurationDialog.chkSummaryFormatTRO.tooltip"));
        chkSummaryFormatTRO.setSelected(CConfig.getBooleanParam(CConfig.SUMMARY_FORMAT_TRO));
        panExport.add(chkSummaryFormatTRO);
    }

    private JLabel findLabel(String name) {
        for (Component component : panColors.getComponents()) {

            if (component instanceof JLabel) {
                JLabel newLabel = (JLabel) component;
                if (newLabel.getName().equals(name)) {
                    return newLabel;
                }
            }
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveCommand)) {
            saveConfig();
            setVisible(false);
        } else if (command.equals(cancelCommand)) {
            CConfig.loadConfigFile();
            setVisible(false);
        } else if (e.getSource() instanceof JButton) {
            JButton newButton = (JButton) e.getSource();
            StringTokenizer st = new StringTokenizer(newButton.getName(), "-");
            String field = st.nextToken();
            String type = st.nextToken();

            JLabel label = findLabel(field);

            ColorConfigurationDialog colorConfig;
            if (type.equals("Foreground")) {
                colorConfig = new ColorConfigurationDialog(newButton.getName().replace("-", " "), label.getForeground());
                label.setForeground(colorConfig.getColor());
            } else {
                colorConfig = new ColorConfigurationDialog(newButton.getName().replace("-", " "), label.getBackground());
                label.setBackground(colorConfig.getColor());
            }
            colorMap.put(newButton.getName(), Integer.toString(colorConfig.getColor().getRGB()));
            colorConfig.dispose();
            label.repaint();
        }

    }
    
    private void saveConfig() {
        colorMap.forEach((k,v) -> CConfig.setParam(k, v));
        CConfig.setParam(CConfig.TECH_PROGRESSION, String.valueOf(chkTechProgression.isSelected()));
        CConfig.setParam(CConfig.TECH_USE_YEAR, String.valueOf(chkTechUseYear.isSelected()));
        CConfig.setParam(CConfig.TECH_YEAR, String.valueOf(txtTechYear.getIntVal()));
        CConfig.setParam(CConfig.TECH_SHOW_FACTION, String.valueOf(chkTechShowFaction.isSelected()));
        CConfig.setParam(CConfig.TECH_EXTINCT, String.valueOf(chkShowExtinct.isSelected()));
        CConfig.setParam(CConfig.TECH_UNOFFICAL_NO_YEAR, String.valueOf(chkUnofficialIgnoreYear.isSelected()));
        CConfig.setParam(CConfig.RS_FONT, (String) cbFont.getSelectedItem());
        UnitUtil.loadFonts();
        CConfig.setParam(CConfig.RS_SHOW_QUIRKS, Boolean.toString(chkShowQuirks.isSelected()));
        CConfig.setParam(CConfig.RS_SHOW_PILOT_DATA, Boolean.toString(chkShowPilotData.isSelected()));
        CConfig.setParam(CConfig.RS_SHOW_ERA, Boolean.toString(chkShowEraIcon.isSelected()));
        CConfig.setParam(CConfig.RS_SHOW_ROLE, Boolean.toString(chkShowRole.isSelected()));
        CConfig.setParam(CConfig.SUMMARY_FORMAT_TRO, Boolean.toString(chkSummaryFormatTRO.isSelected()));
        CConfig.saveConfig();
    }

    private void updateFont() {
        Font font = Font.decode((String) cbFont.getSelectedItem());
        txtFontDisplay.setFont(font.deriveFont(txtFontDisplay.getFont().getSize()));
    }
}