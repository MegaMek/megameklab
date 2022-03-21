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
package megameklab.ui.dialog;

import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megameklab.MegaMekLab;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.largeAero.DSMainUI;
import megameklab.ui.largeAero.WSMainUI;
import megameklab.ui.battleArmor.BAMainUI;
import megameklab.ui.combatVehicle.CVMainUI;
import megameklab.ui.infantry.CIMainUI;
import megameklab.ui.fighterAero.ASMainUI;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.protoMek.PMMainUI;
import megameklab.ui.supportVehicle.SVMainUI;
import megameklab.util.UnitUtil;
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
        super(frame, "MML Loading");
        this.frame = frame;
        this.type = type;
        this.primitive = primitive;
        this.industrial = industrial;
        newUnit = en;
        
        setUndecorated(true);

        JLabel splash = UIUtil.createSplashComponent(loadScreenImages, frame);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(splash, BorderLayout.CENTER);

        setSize(splash.getPreferredSize());
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
                newUI.setLocationRelativeTo(frame);
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
