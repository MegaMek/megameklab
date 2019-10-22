/*
 * MegaMek - Copyright (C) 2019 MegaMek Team
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */

/*
 * My own version of a UnitLoadingDialog using a progress bar
 *  based on the one in MegaMek
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

import megameklab.com.MegaMekLab;
import megameklab.com.ui.StartupGUI;


public class LoadingDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -3454307876761238915L;
    
    Task task;
    JFrame frame;
    int type;
    
    /** A map of resolution widths to file names for the startup screen */
    private final TreeMap<Integer, String> loadScreenImages = new TreeMap<>();
    {
        loadScreenImages.put(0, "data/images/misc/mml_load_spooky_hd.jpg");
        loadScreenImages.put(1441, "data/images/misc/mml_load_spooky_fhd.jpg");
        loadScreenImages.put(1921, "data/images/misc/mml_load_spooky_uhd.jpg");
    }

    public LoadingDialog(JFrame frame, int type) {
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
            switch(type) {
            case StartupGUI.T_VEE:
                new megameklab.com.ui.Vehicle.MainUI();
                return null;
            case StartupGUI.T_SVEE:
                new megameklab.com.ui.supportvehicle.SVMainUI();
                return null;
            case StartupGUI.T_PROTO:
                new megameklab.com.ui.protomek.ProtomekMainUI();
                return null;
            case StartupGUI.T_BA:
                new megameklab.com.ui.BattleArmor.MainUI();
                return null;
            case StartupGUI.T_PBI:
                new megameklab.com.ui.Infantry.MainUI();
                return null;
            case StartupGUI.T_AERO:
                new megameklab.com.ui.Aero.MainUI(false);
                return null;
            case StartupGUI.T_DROP:
                new megameklab.com.ui.aerospace.DropshipMainUI(false);
                return null;
            case StartupGUI.T_LCRAFT:
                new megameklab.com.ui.aerospace.AdvancedAeroUI(false);
                return null;
            case StartupGUI.T_MEK:
            default:
                new megameklab.com.ui.Mek.MainUI();
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
