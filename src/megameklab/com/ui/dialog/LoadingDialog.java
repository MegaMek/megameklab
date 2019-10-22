/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMek Team
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

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import megamek.common.Entity;
import megameklab.com.MegaMekLab;
import megameklab.com.util.UnitUtil;

/**
 * A loading dialog to display until the mainUI has loaded.
 * @author Taharqa
 *
 */
public class LoadingDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -3454307876761238915L;
    
    Task task;
    JFrame frame;
    long type;
    boolean primitive;
    boolean industrial;
    
    /** A map of resolution widths to file names for the startup screen */
    private final TreeMap<Integer, String> loadScreenImages = new TreeMap<>();
    {
        loadScreenImages.put(0, "data/images/misc/mml_load_spooky_hd.jpg");
        loadScreenImages.put(1441, "data/images/misc/mml_load_spooky_fhd.jpg");
        loadScreenImages.put(1921, "data/images/misc/mml_load_spooky_uhd.jpg");
    }
    
    /**
     * 
     * @param frame - the frame that created this which will be disposed once loading is complete
     * @param type - the unit type to load the mainUI from, based on the types in StartupGUI.java
     * @param primitive - is unit primitive
     * @param industrial - is unit industrial
     */
    public LoadingDialog(JFrame frame, long type, boolean primitive, boolean industrial) {
        super(frame, "MML Loading"); //$NON-NLS-1$
        this.frame = frame;
        this.type = type;
        
        setUndecorated(true);

        // initialize loading image
        Image imgSplash = getToolkit().getImage(loadScreenImages.floorEntry((int)MegaMekLab.calculateMaxScreenWidth()).getValue());

        // wait for loading image to load completely
        MediaTracker tracker = new MediaTracker(frame);
        tracker.addImage(imgSplash, 0);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) {
            // really should never come here
        }
        // make splash image panel
        ImageIcon icon = new ImageIcon(imgSplash);
        JLabel splash = new JLabel(icon);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(splash, BorderLayout.CENTER);

        setSize(imgSplash.getWidth(null), imgSplash.getHeight(null));
        this.setLocationRelativeTo(frame);

        task = new Task();
        task.execute();
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */

        @Override
        public Void doInBackground() {
            if(type == Entity.ETYPE_TANK) {
                new megameklab.com.ui.Vehicle.MainUI();
                return null;
            } else if(type == Entity.ETYPE_SUPPORT_TANK) {
                new megameklab.com.ui.supportvehicle.SVMainUI();
                return null;
            } else if(type == Entity.ETYPE_PROTOMECH) {
                new megameklab.com.ui.protomek.ProtomekMainUI();
                return null;
            } else if(type == Entity.ETYPE_BATTLEARMOR) {
                new megameklab.com.ui.BattleArmor.MainUI();
                return null;
            } else if(type == Entity.ETYPE_INFANTRY) {
                new megameklab.com.ui.Infantry.MainUI();
                return null;
            } else if(type == Entity.ETYPE_AERO) {
                new megameklab.com.ui.Aero.MainUI(primitive);
                return null; 
            } else if(type == Entity.ETYPE_DROPSHIP) {
                new megameklab.com.ui.aerospace.DropshipMainUI(primitive);
                return null;
            } else if(type == Entity.ETYPE_JUMPSHIP) {
                new megameklab.com.ui.aerospace.AdvancedAeroUI(primitive);
                return null;
            } else {
                new megameklab.com.ui.Mek.MainUI(primitive, industrial);
                return null;
            }
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            setVisible(false);
            frame.dispose();
        }
    }
}
