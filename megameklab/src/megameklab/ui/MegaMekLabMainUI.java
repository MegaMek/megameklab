/*
 * Copyright (C) 2011-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.common.equipment.Mounted;
import megamek.common.ui.DetachedTabInfo;
import megamek.common.ui.EnhancedTabbedPane;
import megamek.common.ui.EnhancedTabbedPane.TabStateListener;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.UnitMemento;
import megameklab.util.UnitUtil;

public abstract class MegaMekLabMainUI extends JPanel
      implements RefreshListener, EntitySource, FileNameManager {
    private static final int MAX_UNDO_HISTORY = 1000;

    private static final MMLogger logger = MMLogger.create(MegaMekLabMainUI.class);

    protected EnhancedTabbedPane configPane = new EnhancedTabbedPane(true, true);
    private Entity entity = null;
    private String fileName = "";
    protected boolean refreshRequired = false;
    private String originalName = "";
    private MegaMekLabTabbedUI tabOwner = null;
    private boolean initializedTabs = false;
    private boolean dirty = false;
    private boolean dirtyCheckPending = false;
    private boolean forceDirtyUntilNextSave = false;
    private UnitMemento savedUnitSnapshot = null;
    private UnitMemento currentSnapshot = null;
    private final Deque<UnitMemento> undoStack = new LinkedList<>();
    private final Deque<UnitMemento> redoStack = new LinkedList<>();
    private boolean ignoreNextStateChange = false;

    public MegaMekLabMainUI() {
        setLayout(new BorderLayout());
        // Register tab reattachment listener
        configPane.addTabStateListener(new TabStateListener() {
            @Override
            public void onTabDetached(Window window, DetachedTabInfo tabInfo) {
                entity.generateDisplayName();
                final String displayName = entity.getDisplayName();
                configPane.setDetachedTabPrefixTitle(tabInfo, displayName);
            }

            @Override
            public void onTabMoved(int oldIndex, int newIndex, Component component) {
                List<String> tabsOrder = configPane.getTabOrder();
                GUIPreferences.getInstance().setTabOrder(MegaMekLabMainUI.this.getClass().getName(), tabsOrder);
            }
        });
    }

    /**
     * Called when the panel is activated or shown for the first initialization (lazy tab loading)
     */
    public void onActivated() {
        if (!initializedTabs) {
            initializedTabs = true;
            reloadTabs();
            List<String> tabsOrder = GUIPreferences.getInstance().getTabOrder(this.getClass().getName());
            configPane.setTabOrder(tabsOrder);

        }
    }

    public EnhancedTabbedPane getConfigPane() {
        return configPane;
    }

    /**
     * Reattaches all tabs to the main window.
     */
    public void reattachAllTabs() {
        if (configPane != null) {
            configPane.reattachAllTabs();
        }
    }

    /**
     * Requests a dirty check on the unit. This is done by scheduling a dirtyCheck() call to be run on the event
     * dispatch thread.
     */
    public void requestDirtyCheck() {
        if (!dirtyCheckPending) {
            dirtyCheckPending = true;
            SwingUtilities.invokeLater(this::dirtyCheck);
        }
    }

    /**
     * Checks if the unit has been modified since it was last saved. If the unit has been modified, it updates the dirty
     * state and refreshes the header.
     */
    private void dirtyCheck() {
        dirtyCheckPending = false;
        final UnitMemento newSnapshot = new UnitMemento(entity, this);
        final boolean dirtyState = newSnapshot == null || !newSnapshot.equals(savedUnitSnapshot);

        if (ignoreNextStateChange) {
            ignoreNextStateChange = false;
        } else
            // If we have a previous currentSnapshot, we need to push it to the undo stack
            // before overwriting it.
            if (newSnapshot != null && currentSnapshot != null && (!newSnapshot.equals(currentSnapshot))) {
                pushUndoState(currentSnapshot);
            } else
                // If we don't have a currentSnapshot, the undoStack is empty, and we have a
                // savedUnitSnapshot, this is the first undo point
                if (currentSnapshot == null && savedUnitSnapshot != null && undoStack.isEmpty()) {
                    pushUndoState(savedUnitSnapshot);
                }
        currentSnapshot = newSnapshot;
        if (dirty != dirtyState) {
            dirty = dirtyState;
            refreshHeader();
        }
    }

    /**
     * Resets the dirty state of the unit.
     */
    private void resetDirty() {
        SwingUtilities.invokeLater(() -> {
            savedUnitSnapshot = new UnitMemento(entity, this);
            if (dirty) {
                dirty = false;
                refreshHeader();
            }
        });
    }

    /**
     * Invalidates the current snapshot of the unit.
     */
    public void forceDirtyUntilNextSave() {
        forceDirtyUntilNextSave = true;
    }

    /**
     * Pushes the state of the unit to the undo stack.
     *
     * @param state The state to push to the undo stack.
     */
    private void pushUndoState(UnitMemento state) {
        if (!undoStack.isEmpty() && undoStack.peek().equals(state)) {
            return; // Avoid pushing the same state multiple times
        }
        // Clear redo stack when a new action is performed
        redoStack.clear();
        undoStack.push(state);
        // Limit stack size
        if (undoStack.size() > MAX_UNDO_HISTORY) {
            undoStack.removeLast();
        }
        refreshMenuBar();
    }

    /**
     * Checks if there is an undo operation available.
     *
     */
    public boolean hasUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Checks if there is a redo operation available.
     *
     */
    public boolean hasRedo() {
        return !redoStack.isEmpty();
    }

    public boolean canReload() {
        return savedUnitSnapshot != null && !savedUnitSnapshot.isEmpty() && dirty;
    }

    /**
     * Performs undo operation if available.
     */
    public void undo() {
        if (!hasUndo()) {
            return;
        }
        try {
            // Push current state to redo stack
            final UnitMemento currentState = new UnitMemento(entity, this);
            redoStack.push(currentState);
            // Pop and apply state from undo stack
            final UnitMemento previousState = undoStack.pop();
            // Apply the state, ensuring we don't capture this as a new state
            ignoreNextStateChange = true;
            restoreUnitState(previousState);
            refreshMenuBar();
        } catch (Exception e) {
            logger.error("Error during undo operation", e);
        }
    }

    /**
     * Performs redo operation if available.
     */
    public void redo() {
        if (!hasRedo()) {
            return;
        }
        try {
            // Push current state to undo stack
            final UnitMemento currentState = new UnitMemento(entity, this);
            undoStack.push(currentState);
            // Pop and apply state from redo stack
            final UnitMemento nextState = redoStack.pop();
            // Apply the state, ensuring we don't capture this as a new state
            ignoreNextStateChange = true;
            restoreUnitState(nextState);
            refreshMenuBar();
        } catch (Exception e) {
            logger.error("Error during redo operation", e);
        }
    }

    /**
     * Performs reload operation if available.
     */
    public void reload() {
        if (!canReload()) {
            return;
        }
        try {
            final UnitMemento currentState = new UnitMemento(entity, this);
            if (savedUnitSnapshot == null || savedUnitSnapshot.equals(currentState)) {
                return; // No changes to reload
            }
            restoreUnitState(savedUnitSnapshot);
            refreshMenuBar();
            requestDirtyCheck();
        } catch (Exception e) {
            logger.error("Error during reload operation", e);
        }
    }

    /**
     * Updates the undo and redo menu items in the tab owner.
     */
    private void refreshMenuBar() {
        if (tabOwner != null) {
            SwingUtilities.invokeLater(tabOwner::refreshMenuBar);
        }
    }

    /**
     * Returns true if the unit has been modified since it was last saved.
     *
     */
    public boolean isDirty() {
        return dirty || forceDirtyUntilNextSave;
    }

    /**
     * Apply a saved unit memento snapshot to the current entity.
     */
    private void restoreUnitState(UnitMemento state) {
        try {
            final Entity restoredEntity = state.createUnit();
            if (restoredEntity != null) {
                entity = restoredEntity;
                refreshAll();
            }
        } catch (Exception e) {
            logger.error("Failed to apply saved state", e);
        }
    }

    /**
     * Clears the undo and redo history stacks.
     */
    public void clearUndoRedoHistory() {
        undoStack.clear();
        redoStack.clear();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && (getFloatingEquipmentDatabase() != null)) {
            getFloatingEquipmentDatabase().setVisible(false);
        }
    }

    public boolean safetyPrompt() {
        if (!isDirty()) {
            return true;
        }
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
                  || ((savePrompt == JOptionPane.YES_OPTION) && saveUnit());
        }
    }

    private void warnOnInvalid() {
        var report = UnitUtil.validateUnit(getEntity());
        if (!report.isBlank()) {
            PopupMessages.showUnitInvalidWarning(getParentFrame(), report);
        }
    }

    public boolean saveUnitAs() {
        warnOnInvalid();
        UnitUtil.compactCriticalSlots(getEntity());
        refreshAll();
        final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");
        final MegaMekLabFileSaver fileSaver = new MegaMekLabFileSaver(logger,
              resources.getString("dialog.saveAs.title"));
        String file = fileSaver.saveUnitAs(getParentFrame(), entity);
        if (file == null) {
            return false;
        }
        forceDirtyUntilNextSave = false;
        setFileName(file);
        resetDirty();
        return true;

    }

    public boolean saveUnit() {
        if (getEntity() == null) {
            logger.error("Tried to save null entity.");
            return false;
        } else {
            warnOnInvalid();
        }
        UnitUtil.compactCriticalSlots(entity);
        final ResourceBundle resources = ResourceBundle.getBundle("megameklab.resources.Menu");
        final MegaMekLabFileSaver fileSaver = new MegaMekLabFileSaver(logger,
              resources.getString("dialog.saveAs.title"));
        refreshAll(); // The crits may have moved
        String file = fileSaver.saveUnit(getParentFrame(), this, getEntity());
        if (file == null) {
            return false;
        }
        forceDirtyUntilNextSave = false;
        setFileName(file);
        resetDirty();
        return true;
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
    public void refreshAll() {
        requestDirtyCheck();
    }

    @Override
    public void refreshArmor() {
        requestDirtyCheck();
    }

    @Override
    public void refreshBuild() {
        requestDirtyCheck();
    }

    @Override
    public void refreshEquipment() {
        requestDirtyCheck();
    }

    @Override
    public void refreshEquipmentTable() {
        requestDirtyCheck();
    }

    @Override
    public void refreshHeader() {
        if (configPane.hasDetachedTabs()) {
            configPane.setDetachedTabsPrefixTitle(getEntity().getShortNameRaw());
        }
        if (tabOwner != null) {
            tabOwner.setTabName(this, getEntity().getShortNameRaw());
        }
        ForceBuildUI.refresh();
    }

    @Override
    public void refreshStatus() {
        requestDirtyCheck();
        ForceBuildUI.refresh();
    }

    @Override
    public void refreshTransport() {
        requestDirtyCheck();
    }

    @Override
    public void refreshStructure() {
        requestDirtyCheck();
    }

    @Override
    public void refreshWeapons() {
        requestDirtyCheck();
    }

    @Override
    public void refreshPreview() {
        requestDirtyCheck();
    }

    @Override
    public void refreshSummary() {
        requestDirtyCheck();
    }

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
        resetDirty();
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
     * Retrieves a list of mounted components that are currently not assigned to a location. Such equipment would be
     * deleted on save and reload.
     *
     * @return a List containing unallocated Mounted objects.
     */
    public abstract java.util.List<Mounted<?>> getUnallocatedMounted();
}
