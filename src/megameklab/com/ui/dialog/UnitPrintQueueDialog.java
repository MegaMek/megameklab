/*
 * MegaMekLab - Copyright (C) 2010
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.UnitSelectorDialog;
import megamek.common.Entity;
import megamek.common.MechFileParser;
import megameklab.com.util.UnitPrintManager;

/*
 * Allows a user to Select Multiple units to print
 */
public class UnitPrintQueueDialog extends JDialog implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 4812586858732825464L;

    JList<String> unitList = new JList<String>();
    JScrollPane listScrollPane;

    private JButton bCancel = new JButton("Close");
    private JButton bPrint = new JButton("Print");
    private JButton bSelectFile = new JButton("Select From File");
    private JButton bSelectCache = new JButton("Select From Cache");
    private JButton bRemove = new JButton("Remove");
    private JCheckBox chSinglePrint = new JCheckBox("Print Single");
    private JFrame clientgui;

    private JPanel buttonPanel = new JPanel();

    private Vector<Entity> units = new Vector<Entity>();

    public UnitPrintQueueDialog(JFrame frame) {

        super(frame, "Unit Print Queue", true);

        clientgui = frame;

        // construct a model and list
        JPanel masterPanel = new JPanel();
        masterPanel.add(unitList);

        // set list/scroll options
        listScrollPane = new JScrollPane(unitList);
        listScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        listScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // listScrollPane.setViewportView(masterPanel);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder());

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(chSinglePrint);
        buttonPanel.add(bSelectCache);
        buttonPanel.add(bSelectFile);
        buttonPanel.add(bRemove);
        buttonPanel.add(bPrint);
        buttonPanel.add(bCancel);

        Dimension newSize = new Dimension(121, 23);

        /*
         * bSelectFile.setSize(newSize); bRemove.setSize(newSize);
         * bCancel.setSize(newSize); bPrint.setSize(newSize);
         *
         * bSelectFile.setMinimumSize(newSize); bRemove.setMinimumSize(newSize);
         * bCancel.setMinimumSize(newSize); bPrint.setMinimumSize(newSize);
         *
         * bSelectFile.setPreferredSize(newSize);
         * bRemove.setPreferredSize(newSize); bCancel.setPreferredSize(newSize);
         * bPrint.setPreferredSize(newSize);
         */
        bSelectFile.setMaximumSize(newSize);
        bRemove.setMaximumSize(newSize);
        bCancel.setMaximumSize(newSize);
        bPrint.setMaximumSize(newSize);

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));

        setSize(295, 280);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        masterPanel.add(listScrollPane);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);

        // add all the listeners
        bCancel.addActionListener(this);
        bPrint.addActionListener(this);
        bSelectCache.addActionListener(this);
        bSelectFile.addActionListener(this);
        bRemove.addActionListener(this);

        bCancel.setMnemonic('c');
        bSelectCache.setMnemonic('s');
        bSelectFile.setMnemonic('s');
        bPrint.setMnemonic('p');
        bRemove.setMnemonic('r');

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
            UnitPrintManager.printAllUnits(units, chSinglePrint.isSelected());
            dispose();
        }

        if (ae.getSource().equals(bSelectCache)) {
            UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(clientgui);
            unitLoadingDialog.setVisible(true);
            UnitSelectorDialog viewer = new UnitSelectorDialog(clientgui, unitLoadingDialog, true);

            viewer.setVisible(false);
            Entity entity = viewer.getChosenEntity();

            if (entity != null) {
                units.add(entity);
                refresh();
            }
        } else if (ae.getSource().equals(bSelectFile)) {
            String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

            JFileChooser f = new JFileChooser(filePathName);
            f.setLocation(clientgui.getLocation().x + 150, clientgui.getLocation().y + 100);
            f.setDialogTitle("Print Unit File");
            f.setMultiSelectionEnabled(true);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf");

            // Add a filter
            f.setFileFilter(filter);

            int returnVal = f.showOpenDialog(clientgui);
            if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
                // I want a file, y'know!
                return;
            }

            for (File entityFile : f.getSelectedFiles()) {
                try {
                    Entity tempEntity = new MechFileParser(entityFile).getEntity();
                    units.add(tempEntity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            refresh();
        } else if (ae.getSource().equals(bRemove)) {
            if (unitList.getSelectedIndices().length > 0) {
                for (int pos = unitList.getSelectedIndices().length - 1; pos >= 0; pos--) {
                    units.remove(unitList.getSelectedIndices()[pos]);
                }
                refresh();
            }

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

    }

    private void refresh() {

        unitList.removeAll();
        Vector<String> unitNameList = new Vector<String>();

        for (Entity ent : units) {
            unitNameList.add(String.format("%1$s %2$s", ent.getChassis(), ent.getModel()).trim());
        }

        unitList.setListData(unitNameList);
        unitList.repaint();
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
