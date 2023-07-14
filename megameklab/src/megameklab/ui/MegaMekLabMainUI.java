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
import megameklab.ui.util.AppCloser;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;

public abstract class MegaMekLabMainUI extends JFrame implements RefreshListener, EntitySource, AppCloser {
    private Entity entity = null;
    protected MenuBar mmlMenuBar;
    protected boolean refreshRequired = false;
    
    public MegaMekLabMainUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitOnWindowClosingListener(this));
        setResizable(true);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
    }

    protected void finishSetup() {
        mmlMenuBar = new MenuBar(this);
        setJMenuBar(mmlMenuBar);
        reloadTabs();
        setSizeAndLocation();
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

    /**
     * When the setting "Disable save prompts" is active (see Misc Settings), returns true directly.
     * Otherwise, this shows a safety dialog prompting the user to consider saving the currently entered unit.
     * This method returns true only when MML should continue with the action that led to this dialog. This is
     * the case when the user selects NO or selects YES and actually saves the unit.
     * When the user closes the dialog (X or ESC) or presses CANCEL or presses YES but doesn't save the
     * unit, returns false which indicates that the current action (e.g. reset unit or switch unit or quit) should
     * be canceled. See also {@link megameklab.ui.dialog.settings.MiscSettingsPanel}.
     *
     * @return True only when the user agrees to continue or has deactivated these prompts, false otherwise
     */
    public boolean safetyPrompt() {
        if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS)) {
            return true;
        } else {
            int savePrompt = JOptionPane.showConfirmDialog(this,
                    "All unsaved changes in the current unit will be discarded. Save the unit first?",
                    "Save Unit Before Proceeding?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // When the user did not actually save the unit, return as if CANCEL was pressed
            return (savePrompt == JOptionPane.NO_OPTION)
                    || ((savePrompt == JOptionPane.YES_OPTION) && mmlMenuBar.saveEntity(entity));
        }
    }

    @Override
    public boolean exit() {
        if (!safetyPrompt()) {
            return false;
        }

        CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
        CConfig.setParam(CConfig.CONFIG_PLAF, UIManager.getLookAndFeel().getClass().getName());
        CConfig.saveConfig();
        PreferenceManager.getInstance().save();
        MegaMek.getMMPreferences().saveToFile(MMLConstants.MM_PREFERENCES_FILE);
        MegaMekLab.getMMLPreferences().saveToFile(MMLConstants.MML_PREFERENCES_FILE);
        return true;
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


    @Override
    public void scheduleRefresh() {
        refreshRequired = true;
        SwingUtilities.invokeLater(this::performRefresh);
    }

    private void performRefresh() {
        if (refreshRequired) {
            refreshRequired = false;
            refreshAll();
        }
    }
}