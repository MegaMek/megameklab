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

import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.ui.util.EnhancedTabbedPane;
import megameklab.ui.util.EnhancedTabbedPane.DetachedTabInfo;
import megameklab.ui.util.EnhancedTabbedPane.TabStateListener;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public abstract class MegaMekLabMainUI extends JPanel
        implements RefreshListener, EntitySource, FileNameManager {

    protected EnhancedTabbedPane configPane = new EnhancedTabbedPane(true, true);
    private Entity entity = null;
    private String fileName = "";
    protected boolean refreshRequired = false;
    private String originalName = "";
    private MegaMekLabTabbedUI tabOwner = null;
    private boolean initializedTabs = false;

    public MegaMekLabMainUI() {
        setLayout(new BorderLayout());
        // Register tab reattachment listener
        configPane.addTabStateListener(new TabStateListener() {
            @Override
            public void onTabDetached(Window window, DetachedTabInfo tabInfo) {
                getEntity().generateDisplayName();
                final String displayName = getEntity().getDisplayName();
                configPane.setDetachedTabPrefixTitle(tabInfo, displayName);
            }
        });
    }
    
    public void onActivated() {
        if (!initializedTabs) {
            initializedTabs = true;
            reloadTabs();
        }
    }

    public EnhancedTabbedPane getConfigPane() {
        return configPane;
    }

    public void reattachAllTabs() {
        if (configPane != null) {
            configPane.reattachAllTabs();
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && (getFloatingEquipmentDatabase() != null)) {
            getFloatingEquipmentDatabase().setVisible(false);
        }
    }

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
                    || ((savePrompt == JOptionPane.YES_OPTION) && tabOwner.getMMLMenuBar().saveUnit(this));
        }
    }

    public boolean exit() {
        if (!safetyPrompt()) {
            return false;
        }
        reattachAllTabs();
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
        if (configPane.hasDetachedTabs()) {
            configPane.setDetachedTabsPrefixTitle(getEntity().getShortNameRaw());
        }
        if (tabOwner != null) {
            tabOwner.setTabName(getEntity().getShortNameRaw(), this);
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
    public boolean hasEntityNameChanged() {
        return !MegaMekLabFileSaver.createUnitFilename(entity).equals(originalName);
    }

    public void setTabOwner(MegaMekLabTabbedUI owner) {
        this.tabOwner = owner;
    }

    public MegaMekLabTabbedUI getTabOwner() {
        return tabOwner;
    }

    public JFrame getParentFrame() {
        return tabOwner != null ? tabOwner : (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
    }

    /**
     * Retrieves a list of mounted components that are currently not assigned to a
     * location.
     * Such equipment would be deleted on save and reload.
     *
     * @return a List containing unallocated Mounted objects.
     */
    public abstract java.util.List<Mounted<?>> getUnallocatedMounted();
}