/*
 * MegaMekLab
 * - Copyright (C) 2009 jtighe (torren@users.sourceforge.net)
 * - Copyright (C) 2018 The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class ColorConfigurationDialog extends JDialog implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -6504846822457360057L;

    private final static String saveCommand = "Save";
    private final static String cancelCommand = "Cancel";
    private final static String windowName = "Color Configuration";

    // BUTTONS
    private final JButton saveButton = new JButton(saveCommand);
    private final JButton cancelButton = new JButton(cancelCommand);

    // STOCK DIALOUG AND PANE
    private JDialog dialog;
    private JOptionPane pane;
    private JPanel MasterPanel = new JPanel();
    private JPanel ConfigPane = new JPanel();

    private JColorChooser colors;
    private Color masterColor;

    public ColorConfigurationDialog(String title, Color color) {

        // stored values.
        masterColor = color;

        setTitle(windowName);

        // Set the tooltips and actions for dialouge buttons
        saveButton.setActionCommand(saveCommand);
        cancelButton.setActionCommand(cancelCommand);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.setToolTipText("Save");
        cancelButton.setToolTipText("Exit without saving changes");

        colors = new JColorChooser();

        ConfigPane.add(createPanelForComponent(colors, title));
        colors.setColor(color);

        MasterPanel.add(ConfigPane);

        // Set the user's options
        Object[] options = { saveButton, cancelButton };

        Dimension dim = new Dimension(100, 200);

        ConfigPane.setMaximumSize(dim);

        // Create the pane containing the buttons
        pane = new JOptionPane(ConfigPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, null);

        pane.setMaximumSize(dim);

        MasterPanel.setMaximumSize(dim);

        // Create the main dialog and set the default button
        dialog = pane.createDialog(MasterPanel, windowName);
        dialog.getRootPane().setDefaultButton(cancelButton);

        dialog.setMaximumSize(dim);
        // Show the dialog and get the user's input
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private JPanel createPanelForComponent(JComponent comp, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(comp, BorderLayout.CENTER);
        if (title != null) {
            panel.setBorder(BorderFactory.createTitledBorder(title));
        }
        return panel;
    }

    public Color getColor() {
        return masterColor;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveCommand)) {
            masterColor = colors.getColor();
            dialog.setVisible(false);
            return;
        } else if (command.equals(cancelCommand)) {
            dialog.setVisible(false);
            return;
        }

    }

}
