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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public final class ConfigurationDialog extends JDialog implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6504846822457360057L;

    private final static String saveCommand = "Save";
    private final static String cancelCommand = "Cancel";
    private final static String windowName = "Configuration Dialog";

    // BUTTONS
    private final JButton saveButton = new JButton(saveCommand);
    private final JButton cancelButton = new JButton(cancelCommand);
    private JButton baseButton;

    // STOCK DIALOUG AND PANE
    private JDialog dialog;
    private JOptionPane pane;
    private JPanel MasterPanel = new JPanel();
    private JPanel ConfigPane = new JPanel(new SpringLayout());

    private CConfig config;

    public ConfigurationDialog(CConfig config) {

        //stored values.
        this.config = config;
        setTitle(windowName);

        //Set the tooltips and actions for dialouge buttons
        saveButton.setActionCommand(saveCommand);
        cancelButton.setActionCommand(cancelCommand);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.setToolTipText("Save");
        cancelButton.setToolTipText("Exit without saving changes");

        loadPanel();
        MasterPanel.add(ConfigPane);

        // Set the user's options
        Object[] options = { saveButton, cancelButton };

        Dimension dim = new Dimension(100,200);

        ConfigPane.setMaximumSize(dim);

        // Create the pane containing the buttons
        pane = new JOptionPane(ConfigPane,JOptionPane.PLAIN_MESSAGE,JOptionPane.DEFAULT_OPTION, null, options, null);

        pane.setMaximumSize(dim);


        MasterPanel.setMaximumSize(dim);


        // Create the main dialog and set the default button
        dialog = pane.createDialog(MasterPanel, windowName);
        dialog.getRootPane().setDefaultButton(cancelButton);

        dialog.setMaximumSize(dim);
        //Show the dialog and get the user's input
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void loadPanel() {
        addFields(CConfig.CONFIG_WEAPONS);
        addFields(CConfig.CONFIG_EQUIPMENT);
        addFields(CConfig.CONFIG_AMMO);
        addFields(CConfig.CONFIG_SYSTEMS);
        SpringLayoutHelper.setupSpringGrid(ConfigPane, 3);
    }

    private void addFields(String fieldName) {
        JLabel baseLabel = new JLabel(fieldName);
        baseLabel.setName(fieldName);
        baseLabel.setOpaque(true);
        try {
            baseLabel.setBackground(Color.getColor("", Integer.parseInt(config.getParam(fieldName + CConfig.CONFIG_BACKGROUND))));
        } catch (Exception ex) {

        }
        try {
            baseLabel.setForeground(Color.getColor("", Integer.parseInt(config.getParam(fieldName + CConfig.CONFIG_FOREGROUND))));
        } catch (Exception ex) {

        }
        ConfigPane.add(baseLabel);
        baseButton = new JButton("Foreground");
        baseButton.setName(fieldName + CConfig.CONFIG_FOREGROUND);
        baseButton.addActionListener(this);
        ConfigPane.add(baseButton);
        baseButton = new JButton("Background");
        baseButton.setName(fieldName + CConfig.CONFIG_BACKGROUND);
        baseButton.addActionListener(this);
        ConfigPane.add(baseButton);
    }

    private JLabel findLabel(String name){
        for ( Component component : ConfigPane.getComponents()){

            if (component instanceof JLabel) {
                JLabel newLabel = (JLabel) component;
                if ( newLabel.getName().equals(name) ){
                    return newLabel;
                }
            }
        }

        return null;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveCommand)) {
            config.saveConfig();
            dialog.dispose();
            return;
        } else if (command.equals(cancelCommand)) {
            config.loadConfigFile();
            dialog.dispose();
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
            config.setParam(newButton.getName(), Integer.toString(colorConfig.getColor().getRGB()));
            colorConfig.dispose();
            label.repaint();
        }


    }

}