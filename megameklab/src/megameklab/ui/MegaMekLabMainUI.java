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

package megameklab.ui;

import megamek.MegaMek;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.preference.PreferenceManager;
import megameklab.MMLConstants;
import megameklab.MegaMekLab;
import megameklab.util.CConfig;
import megameklab.ui.util.RefreshListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class MegaMekLabMainUI extends JFrame implements RefreshListener, EntitySource {

    private Entity entity = null;
    protected MenuBar menubarcreator;
    
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
        menubarcreator = new MenuBar(this);
        setJMenuBar(menubarcreator);
        reloadTabs();
        setSizeAndLocation();
        setVisible(true);
        refreshAll();
    }
    
    protected void setSizeAndLocation() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        int scaledMonitorW = UIUtil.getScaledScreenWidth(currentMonitor);
        int scaledMonitorH = UIUtil.getScaledScreenHeight(currentMonitor);

        // figure out size dimensions
        pack();
        setLocationRelativeTo(null);
        int w = getSize().width;
        int h = getSize().height;

        if (scaledMonitorW < w) {
            w = scaledMonitorW;
        }
        if (scaledMonitorH < h) {
            h = scaledMonitorH;
        }
        Dimension size = new Dimension(w, h);
        setSize(size);
        setPreferredSize(size);

        // center on screen
        setLocationRelativeTo(null);
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
        int savePrompt = JOptionPane.showConfirmDialog(null,
                "Do you want to save the unit before quitting MegaMekLab?",
                "Save First?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ((savePrompt == JOptionPane.CANCEL_OPTION) || (savePrompt == JOptionPane.CLOSED_OPTION)) {
            return;
        } else if ((savePrompt == JOptionPane.YES_OPTION) && !menubarcreator.saveEntity(entity)) {
            // When the user did not actually save the unit, don't close MML
            return;
        }

        CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
        CConfig.setParam(CConfig.CONFIG_PLAF, UIManager.getLookAndFeel().getClass().getName());
        CConfig.saveConfig();

        PreferenceManager.getInstance().save();

        MegaMek.getMMPreferences().saveToFile(MMLConstants.MM_PREFERENCES_FILE);
        MegaMekLab.getMMLPreferences().saveToFile(MMLConstants.MML_PREFERENCES_FILE);

        System.exit(0);
    }
    
    public abstract void reloadTabs();

    @Override
    public abstract void refreshAll();

    @Override
    public abstract void refreshArmor();

    @Override
    public abstract void refreshBuild();

    @Override
    public abstract void refreshEquipment();

    @Override
    public abstract void refreshHeader();

    @Override
    public abstract void refreshStatus();

    @Override
    public abstract void refreshStructure();

    @Override
    public abstract void refreshWeapons();

    @Override
    public abstract void refreshPreview();

    public void setEntity(Entity en) {
        entity = en;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}
