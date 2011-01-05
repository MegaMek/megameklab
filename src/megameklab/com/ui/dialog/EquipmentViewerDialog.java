/*
 * MegaMekLab - Copyright (C) 2011
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui.dialog;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import megameklab.com.util.EquipmentTableModel;

public class EquipmentViewerDialog extends JDialog implements ActionListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 4812586858732825464L;

    JList unitList = new JList();
    JScrollPane listScrollPane;

    private JButton bCancel = new JButton("Close");
    private JButton bPrint = new JButton("Create Master File");
    private JFrame clientgui;

    private JPanel buttonPanel = new JPanel();

    private JTable equipmentTable = new JTable();
    private JScrollPane equipmentScroll = new JScrollPane();
    private EquipmentTableModel equipmentList;
    private JTextField filterBox = new JTextField();

    public EquipmentViewerDialog(JFrame frame) {

        super(frame, "Equipment List", true);

        clientgui = frame;

        // construct a model and list
        JPanel masterPanel = new JPanel();

        equipmentList = new EquipmentTableModel();

        equipmentTable.setModel(equipmentList);
        equipmentList.initColumnSizes(equipmentTable);
        for (int i = 0; i < equipmentList.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(equipmentList.getRenderer());
        }

        setSize(clientgui.getWidth() * 3 / 4, clientgui.getHeight() * 3 / 4);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // equipmentScroll.setToolTipText("");
        // equipmentScroll.setPreferredSize(new Dimension(getWidth() * 3 / 4,
        // getHeight() * 3 / 4));
        equipmentTable.setDoubleBuffered(true);
        equipmentScroll.setViewportView(equipmentTable);
        masterPanel.add(equipmentScroll);

        filterBox.addKeyListener(this);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(new JLabel("Filter", SwingConstants.LEADING));
        buttonPanel.add(filterBox);
        buttonPanel.add(bPrint);
        buttonPanel.add(bCancel);

        Dimension newSize = new Dimension(121, 23);

        bCancel.setMaximumSize(newSize);
        bPrint.setMaximumSize(newSize);
        filterBox.setMaximumSize(newSize);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        // setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        masterPanel.add(buttonPanel);
        this.add(masterPanel);

        // add all the listeners
        bCancel.addActionListener(this);
        bPrint.addActionListener(this);

        bCancel.setMnemonic('c');
        bPrint.setMnemonic('p');

        equipmentList.refreshModel();
        // pPreview.setVisible(true);
        setLocationRelativeTo(clientgui);
        setModal(true);
        setVisible(true);
        unitList.requestFocus();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bCancel) {
            dispose();
        }

        if (ae.getSource() == bPrint) {
            FileDialog fDialog = new FileDialog(clientgui, "Save As", FileDialog.SAVE);

            String filePathName = new File(System.getProperty("user.dir").toString() + "/data/").getAbsolutePath();

            fDialog.setDirectory(filePathName);
            fDialog.setFile("equipment.txt");
            fDialog.setLocationRelativeTo(clientgui);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
            } else {
                return;
            }
            megamek.common.EquipmentType.writeEquipmentDatabase(new File(filePathName));
            JOptionPane.showMessageDialog(clientgui, "Equipment file saved to " + filePathName);
        }

    }

    public void keyReleased(java.awt.event.KeyEvent ke) {
        // no action on release
    }

    public void keyPressed(java.awt.event.KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent event = new ActionEvent(bPrint, ActionEvent.ACTION_PERFORMED, "");
            actionPerformed(event);
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ActionEvent event = new ActionEvent(bCancel, ActionEvent.ACTION_PERFORMED, "");
            actionPerformed(event);
        }

        if (ke.getSource() == filterBox) {
            equipmentList.refreshModel(filterBox.getText().trim());
        }

    }

    public void keyTyped(java.awt.event.KeyEvent ke) {
    }

    // WindowListener
    public void windowActivated(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        dispose();
    }

    public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowIconified(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowOpened(java.awt.event.WindowEvent windowEvent) {
    }

}
