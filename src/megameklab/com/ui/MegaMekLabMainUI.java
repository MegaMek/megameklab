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

package megameklab.com.ui;

import megamek.common.Entity;
import megamek.common.preference.PreferenceManager;
import megameklab.com.util.CConfig;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.RefreshListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class MegaMekLabMainUI extends JFrame implements RefreshListener, EntitySource {

    private Entity entity = null;
    protected MenuBarCreator menubarcreator;
    
    public MegaMekLabMainUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });
        setResizable(true);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
    }

    protected void finishSetup() {
        menubarcreator = new MenuBarCreator(this);
        setJMenuBar(menubarcreator);
        reloadTabs();
        setSizeAndLocation();
        setVisible(true);
        refreshAll();
    }
    
    protected void setSizeAndLocation() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        
        //figure out size dimensions
        pack();
        int w = getSize().width;
        int h = getSize().height;
        if(currentMonitor.getWidth() < w) {
            w = currentMonitor.getWidth();
        }
        if(currentMonitor.getHeight() < h) {
            h = currentMonitor.getHeight();
        }
        Dimension size = new Dimension(w, h);
        setSize(size);
        setPreferredSize(size);
        
        //set location - put in center not top left
        int x = (currentMonitor.getWidth()-w)/2;
        int y = (currentMonitor.getHeight()-h)/2;
        setLocation(x, y);
    }
    
    /**
     * Sets the look and feel for the application.
     * 
     * @param plaf The look and feel to use for the application.
     */
    public void changeTheme(LookAndFeelInfo plaf) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(plaf.getClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this,
                        "Can't change look and feel", "Invalid PLAF",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
    }
    
    public void exit() {
        String quitMsg = "Do you really want to quit MegaMekLab?"; 
        int response = JOptionPane.showConfirmDialog(null, quitMsg,
                "Quit?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE); 
        
        if (response == JOptionPane.YES_OPTION) {
            CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
            // Only save position and size if not maximized or minimized.
            if (getExtendedState() == Frame.NORMAL) {
                /*
                 * Not saving this anymore because this is set always to the center of
                 * screen and big enough to fit all the components for a given MainUI
                CConfig.setParam("WINDOWHEIGHT", Integer.toString(getHeight()));
                CConfig.setParam("WINDOWWIDTH", Integer.toString(getWidth()));
                CConfig.setParam("WINDOWLEFT", Integer.toString(getX()));
                CConfig.setParam("WINDOWTOP", Integer.toString(getY()));
                */
            }
            CConfig.setParam(CConfig.CONFIG_PLAF, UIManager.getLookAndFeel().getClass().getName());
            CConfig.saveConfig();
            PreferenceManager.getInstance().save();

            System.exit(0);
        }
    }
    
    public abstract void reloadTabs();

    public abstract void refreshAll();

    public abstract void refreshArmor();

    public abstract void refreshBuild();

    public abstract void refreshEquipment();

    public abstract void refreshHeader();

    public abstract void refreshStatus();

    public abstract void refreshStructure();

    public abstract void refreshWeapons();

    public abstract void refreshPreview();

    public void setEntity(Entity en) {
        entity = en;
    }

    public Entity getEntity() {
        return entity;
    }

}