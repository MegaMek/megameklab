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

import megamek.common.Entity;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.largeAero.DSMainUI;
import megameklab.com.ui.largeAero.WSMainUI;
import megameklab.com.ui.battleArmor.BAMainUI;
import megameklab.com.ui.combatVeh.CVMainUI;
import megameklab.com.ui.convInfantry.CIMainUI;
import megameklab.com.ui.fighterAero.ASMainUI;
import megameklab.com.ui.mek.BMMainUI;
import megameklab.com.ui.protoMek.PMMainUI;
import megameklab.com.ui.supportVeh.SVMainUI;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/**
 * A loading dialog to display until the mainUI has loaded.
 * @author Taharqa
 */
public class LoadingDialog extends JDialog {
    private static final long serialVersionUID = -3454307876761238915L;
    
    Task task;
    JFrame frame;
    long type;
    boolean primitive;
    boolean industrial;
    Entity newUnit;
    
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
     * @param en - a specific <code>Entity</code> to load in rather than default
     */
    public LoadingDialog(JFrame frame, long type, boolean primitive, boolean industrial, Entity en) {
        super(frame, "MML Loading"); //$NON-NLS-1$
        this.frame = frame;
        this.type = type;
        this.primitive = primitive;
        this.industrial = industrial;
        newUnit = en;
        
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
            MegaMekLabMainUI newUI;
            if (type == Entity.ETYPE_TANK) {
                newUI = new CVMainUI();
            } else if (type == Entity.ETYPE_SUPPORT_TANK) {
                newUI = new SVMainUI();
            } else if (type == Entity.ETYPE_PROTOMECH) {
                newUI = new PMMainUI();
            } else if (type == Entity.ETYPE_BATTLEARMOR) {
                newUI = new BAMainUI();
            } else if (type == Entity.ETYPE_INFANTRY) {
                newUI = new CIMainUI();
            } else if (type == Entity.ETYPE_AERO) {
                newUI = new ASMainUI(primitive);
            } else if (type == Entity.ETYPE_DROPSHIP) {
                newUI = new DSMainUI(primitive);
            } else if (type == Entity.ETYPE_JUMPSHIP) {
                newUI = new WSMainUI(primitive);
            } else {
                newUI = new BMMainUI(primitive, industrial);
            }
            setVisible(false);
            //update if we had a specific unit to load
            if (null != newUnit) {
                UnitUtil.updateLoadedUnit(newUnit);
                newUI.setEntity(newUnit);
                newUI.reloadTabs();
                newUI.repaint();
                newUI.refreshAll();
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            boolean interrupted = false;
            try {
                get();
            } catch (ExecutionException ex) {
                LogManager.getLogger().error("", ex);
            } catch (InterruptedException ex) {
                interrupted = true;
            } finally {
                frame.dispose();
                // Propogate interruption
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
