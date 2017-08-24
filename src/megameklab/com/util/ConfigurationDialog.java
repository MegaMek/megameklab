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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import megamek.common.util.EncodeControl;

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
    private final JPanel panTech = new JPanel(new SpringLayout());
    
    private final JCheckBox chkTechProgression = new JCheckBox();
    private final JCheckBox chkShowExtinct = new JCheckBox();
    private final JCheckBox chkUnofficialIgnoreYear = new JCheckBox();
    
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
        
        loadColorPanel();
        loadTechPanel(resourceMap);

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
        chkTechProgression.setText(resourceMap.getString("ConfigurationDialog.chkTechProgression.text")); //$NON-NLS-1$
        chkTechProgression.setToolTipText(resourceMap.getString("ConfigurationDialog.chkTechProgression.tooltip")); //$NON-NLS-1$
        chkTechProgression.setSelected(CConfig.getBooleanParam(CConfig.CONFIG_TECH_PROGRESSION));
        panTech.add(chkTechProgression);
        chkShowExtinct.setText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.text")); //$NON-NLS-1$
        chkShowExtinct.setToolTipText(resourceMap.getString("ConfigurationDialog.chkShowExtinct.tooltip")); //$NON-NLS-1$
        chkShowExtinct.setSelected(CConfig.getBooleanParam(CConfig.CONFIG_TECH_EXTINCT));
        panTech.add(chkShowExtinct);
        chkUnofficialIgnoreYear.setText(resourceMap.getString("ConfigurationDialog.chkUnofficialIgnoreYear.text")); //$NON-NLS-1$
        chkUnofficialIgnoreYear.setToolTipText(resourceMap.getString("ConfigurationDialog.chkUnofficialIgnoreYear.tooltip")); //$NON-NLS-1$
        chkUnofficialIgnoreYear.setSelected(CConfig.getBooleanParam(CConfig.CONFIG_TECH_UNOFFICAL_NO_YEAR));
        panTech.add(chkUnofficialIgnoreYear);
        SpringLayoutHelper.setupSpringGrid(panTech, 1);
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
        CConfig.setParam(CConfig.CONFIG_TECH_PROGRESSION, String.valueOf(chkTechProgression.isSelected()));
        CConfig.setParam(CConfig.CONFIG_TECH_EXTINCT, String.valueOf(chkShowExtinct.isSelected()));
        CConfig.setParam(CConfig.CONFIG_TECH_UNOFFICAL_NO_YEAR, String.valueOf(this.chkUnofficialIgnoreYear.isSelected()));
        CConfig.saveConfig();
    }

}