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
import megamek.common.Mounted;
import megamek.common.preference.PreferenceManager;
import megameklab.MMLConstants;
import megameklab.MegaMekLab;
import megameklab.ui.util.DetachableTabbedPane;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;

import javax.swing.*;
import java.awt.*;

public abstract class MegaMekLabMainUI extends JFrame implements RefreshListener, EntitySource, MenuBarOwner, FileNameManager {
    
    protected DetachableTabbedPane configPane = new DetachableTabbedPane(SwingConstants.TOP);
    private Entity entity = null;
    private String fileName = "";
    protected MenuBar mmlMenuBar;
    protected boolean refreshRequired = false;
    private String originalName = "";
    private MegaMekLabTabbedUI owner = null;

    public MegaMekLabMainUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // addWindowListener(new ExitOnWindowClosingListener(this));
        setExtendedState(CConfig.getIntParam(CConfig.GUI_FULLSCREEN));
    }

    protected void finishSetup() {
        mmlMenuBar = new MenuBar(this);
        setJMenuBar(mmlMenuBar);
        reattachAllTabs();
        reloadTabs();
        refreshAll();
        this.setTransferHandler(new MMLFileDropTransferHandler(this));
    }

    protected void setSizeAndLocation() {
        pack();
        restrictToScreenSize();
        setLocationRelativeTo(null);
        CConfig.getMainUiWindowSize(this).ifPresent(this::setSize);
        CConfig.getMainUiWindowPosition(this).ifPresent(this::setLocation);
    }

    private void restrictToScreenSize() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        int scaledMonitorW = UIUtil.getScaledScreenWidth(currentMonitor);
        int scaledMonitorH = UIUtil.getScaledScreenHeight(currentMonitor);
        int w = Math.min(getSize().width, scaledMonitorW);
        int h = Math.min(getSize().height, scaledMonitorH);
        setSize(new Dimension(w, h));
    }

    public DetachableTabbedPane getConfigPane() {
        return configPane;
    }

    public void reattachAllTabs() {
        if (configPane != null) {
            configPane.reattachAllTabs();
        }
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            setSizeAndLocation();
        }
        super.setVisible(b);
        if (!b && (getFloatingEquipmentDatabase() != null)) {
            getFloatingEquipmentDatabase().setVisible(false);
        }
    }

    @Override
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
                    || ((savePrompt == JOptionPane.YES_OPTION) && mmlMenuBar.saveUnit());
        }
    }

    @Override
    public boolean exit() {
        if (!safetyPrompt()) {
            return false;
        }

        CConfig.setParam(CConfig.GUI_FULLSCREEN, Integer.toString(getExtendedState()));
        CConfig.setParam(CConfig.GUI_PLAF, UIManager.getLookAndFeel().getClass().getName());
        CConfig.writeMainUiWindowSettings(this);
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
    public void refreshHeader() {
        String fileInfo = fileName.isBlank() ? "" : " (" + fileName + ")";
        setTitle(getEntity().getFullChassis() + " " + getEntity().getModel() + fileInfo);
        if (owner != null) {
            getEntity().generateDisplayName();
            owner.setTabName(getEntity().getDisplayName(), this);
        }
    }

    @Override
    public abstract void refreshStatus();

    @Override
    public abstract void refreshStructure();

    @Override
    public abstract void refreshWeapons();

    @Override
    public abstract void refreshPreview();

    public void setEntity(Entity en) {
        setEntity(en, "");
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity, String currentEntityFilename) {
        this.entity = entity;
        originalName = MegaMekLabFileSaver.createUnitFilename(entity);
        setFileName(currentEntityFilename);
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

    public abstract JDialog getFloatingEquipmentDatabase();

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
        // If the filename is reloaded, restart tracking of the unit name changing.
        this.originalName = MegaMekLabFileSaver.createUnitFilename(entity);
        refreshHeader();
    }

    @Override
    public void refreshMenuBar() {
        mmlMenuBar.refreshMenuBar();
    }

    @Override
    public boolean hasEntityNameChanged() {
        return !MegaMekLabFileSaver.createUnitFilename(entity).equals(originalName);
    }

    @Override
    public MenuBar getMMLMenuBar() {
        return mmlMenuBar;
    }

    public void setOwner(MegaMekLabTabbedUI owner) {
        this.owner = owner;
    }

    /**
     * Retrieves a list of mounted components that are currently not assigned to a location.
     * Such equipment would be deleted on save and reload.
     *
     * @return a List containing unallocated Mounted objects.
     */
    public abstract java.util.List<Mounted<?>> getUnallocatedMounted();
}
