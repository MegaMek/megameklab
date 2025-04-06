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
import megamek.common.EquipmentType;
import megamek.common.Mek;
import megamek.common.MekFileParser;
import megamek.common.Mounted;
import megamek.common.loaders.BLKFile;
import megamek.common.loaders.MtfFile;
import megamek.common.util.BuildingBlock;
import megamek.logging.MMLogger;
import megameklab.ui.util.EnhancedTabbedPane;
import megameklab.ui.util.EnhancedTabbedPane.DetachedTabInfo;
import megameklab.ui.util.EnhancedTabbedPane.TabStateListener;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.RefreshListener;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private Deque<UnitMemento> undoStack = new LinkedList<>();
    private Deque<UnitMemento> redoStack = new LinkedList<>();
    private boolean ignoreNextStateChange = false;

    private static class UnitMemento {
        private final String entityState;
        private final String unallocatedEquipment;

        public UnitMemento(String entityState, String unallocatedEquipment) {
            this.entityState = entityState;
            this.unallocatedEquipment = unallocatedEquipment;
        }

        public String getEntityState() {
            return entityState;
        }

        public String getUnallocatedEquipment() {
            return unallocatedEquipment;
        }

        public boolean isEmpty() {
            return entityState == null || entityState.isEmpty();
        }

        public boolean equals(UnitMemento other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            // Compare entityState
            if (entityState == null) {
                if (other.entityState != null) {
                    return false;
                }
            } else if (!entityState.equals(other.entityState)) {
                return false;
            }
            // Compare unallocatedEquipment
            if (unallocatedEquipment == null) {
                if (other.unallocatedEquipment != null) {
                    return false;
                }
            } else if (!unallocatedEquipment.equals(other.unallocatedEquipment)) {
                return false;
            }
            // If we get here, both fields are equal
            return true;
        }
    }

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
        });
    }

    /**
     * Called when the panel is activated or shown for the first initialization
     * (lazy tab loading)
     */
    public void onActivated() {
        if (!initializedTabs) {
            initializedTabs = true;
            reloadTabs();
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
     * Requests a dirty check on the unit. This is done by scheduling a
     * dirtyCheck() call to be run on the event dispatch thread.
     */
    public void requestDirtyCheck() {
        if (!dirtyCheckPending) {
            dirtyCheckPending = true;
            SwingUtilities.invokeLater(this::dirtyCheck);
        }
    }

    /**
     * Checks if the unit has been modified since it was last saved. If the unit
     * has been modified, it updates the dirty state and refreshes the header.
     */
    private void dirtyCheck() {
        dirtyCheckPending = false;
        final UnitMemento newSnapshot = createMemento();
        final boolean dirtyState = newSnapshot == null || !newSnapshot.equals(savedUnitSnapshot);

        if (ignoreNextStateChange) {
            ignoreNextStateChange = false;
        } else
        // If we have a previous currentSnapshot, we need to push it to the undo stack
        // before overwriting it.
        if (newSnapshot != null && currentSnapshot != null && (!newSnapshot.equals(currentSnapshot))) {
            pushUndoState(currentSnapshot);
        } else
        // If we don't have a currentSnapshot, the undoStack is empty and we have a
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
            savedUnitSnapshot = createMemento();
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
     * @return true if the state was pushed, false if it was not (e.g., if it was
     *         the
     *         same as the previous state).
     */
    private boolean pushUndoState(UnitMemento state) {
        if (!undoStack.isEmpty() && undoStack.peek().equals(state)) {
            return false; // Avoid pushing the same state multiple times
        }
        // Clear redo stack when a new action is performed
        redoStack.clear();
        undoStack.push(state);
        // Limit stack size
        if (undoStack.size() > MAX_UNDO_HISTORY) {
            undoStack.removeLast();
        }
        updateUndoRedoMenuItems();
        return true;
    }

    /**
     * Checks if there is an undo operation available.
     * 
     * @return
     */
    public boolean hasUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Checks if there is a redo operation available.
     * 
     * @return
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
            final UnitMemento currentState = createMemento();
            redoStack.push(currentState);
            // Pop and apply state from undo stack
            final UnitMemento previousState = undoStack.pop();
            // Apply the state, ensuring we don't capture this as a new state
            ignoreNextStateChange = true;
            restoreUnitState(previousState);
            updateUndoRedoMenuItems();
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
            final UnitMemento currentState = createMemento();
            undoStack.push(currentState);
            // Pop and apply state from redo stack
            final UnitMemento nextState = redoStack.pop();
            // Apply the state, ensuring we don't capture this as a new state
            ignoreNextStateChange = true;
            restoreUnitState(nextState);
            updateUndoRedoMenuItems();
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
            final UnitMemento currentState = createMemento();
            if (savedUnitSnapshot == null || savedUnitSnapshot.equals(currentState)) {
                return; // No changes to reload
            }
            restoreUnitState(savedUnitSnapshot);
            updateUndoRedoMenuItems();
            requestDirtyCheck();
        } catch (Exception e) {
            logger.error("Error during reload operation", e);
        }
    }

    /**
     * Updates the undo and redo menu items in the tab owner.
     */
    private void updateUndoRedoMenuItems() {
        if (tabOwner != null) {
            SwingUtilities.invokeLater(tabOwner::refreshEditMenu);
        }
    }

    /**
     * Returns true if the unit has been modified since it was last saved.
     * 
     * @return
     */
    public boolean isDirty() {
        return dirty || forceDirtyUntilNextSave;
    }

    /**
     * Apply a saved unit memento snapshot to the current entity.
     */
    private void restoreUnitState(UnitMemento state) {
        try {
            final Entity restoredEntity = new MekFileParser(state.getEntityState()).getEntity();
            if (restoredEntity != null) {
                entity = restoredEntity;
                // Restore unallocated equipment if available
                String unallocatedEquipment = state.getUnallocatedEquipment();
                if (unallocatedEquipment != null && !unallocatedEquipment.isEmpty()) {
                    try (Scanner sc = new Scanner(unallocatedEquipment)) {
                        int unallocatedEquipmentCount = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < unallocatedEquipmentCount; i++) {
                            try {
                                String line = sc.nextLine();
                                String[] parts = line.split(Pattern.quote(MtfFile.SIZE));
                                EquipmentType type = EquipmentType.get(parts[0]);
                                Mounted<?> mounted = Mounted.createMounted(entity, type);
                                if (parts.length > 1) {
                                    mounted.setSize(Double.parseDouble(parts[1]));
                                }
                                entity.addEquipment(mounted, Entity.LOC_NONE, false);
                            } catch (Exception e) {
                                logger.warn("Could not restore unallocated equipment item", e);
                            }
                        }
                    } catch (Exception e) {
                        logger.warn("Could not restore unallocated equipment", e);
                    }
                }
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
        UnitUtil.compactCriticals(getEntity());
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
        UnitUtil.compactCriticals(entity);
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

    /**
     * Encodes the unit to a string.
     * 
     * @param entity The unit to encode
     * @return The encoded unit as a string, or null if the unit is null or an error
     */
    public String saveUnitToString(Entity entity, boolean includeGeneratorHeader) {
        if (entity == null) {
            return null;
        }
        try {
            String unitAsString;
            if (entity instanceof Mek) {
                unitAsString = ((Mek) entity).getMtf();
            } else {
                BuildingBlock blk = BLKFile.getBlock(entity);
                StringBuilder sb = new StringBuilder();
                String[] lines = blk.getAllDataAsString();
                for (String line : lines) {
                    sb.append(line).append(System.lineSeparator());
                }
                unitAsString = sb.toString();
            }
            if (!includeGeneratorHeader) {
                return unitAsString.substring(unitAsString.indexOf("\n") + 1);
            }
            return unitAsString;
        } catch (Exception ex) {
            logger.error("Error while taking unit snapshot", ex);
            return null;
        }
    }

    public UnitMemento createMemento() {
        final String unitState = saveUnitToString(entity, false);
        String unallocatedEquipment = null;
        // If the unit has unallocated equipment, save it to a string
        List<Mounted<?>> unallocatedMounted = getUnallocatedMounted();
        if (unallocatedMounted != null && !unallocatedMounted.isEmpty()) {
            StringWriter stringWriter = new StringWriter();
            try (PrintWriter pw = new PrintWriter(stringWriter)) {
                pw.println(unallocatedMounted.size());
                for (Mounted<?> mounted : unallocatedMounted) {
                    EquipmentType type = mounted.getType();
                    if (type.isVariableSize()) {
                        pw.printf("%s%s%f\n",
                                type.getInternalName(),
                                MtfFile.SIZE,
                                mounted.getSize());
                    } else {
                        pw.println(type.getInternalName());
                    }
                }
                unallocatedEquipment = stringWriter.toString();
            }
        }
        return new UnitMemento(unitState, unallocatedEquipment);
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
    }

    @Override
    public void refreshStatus() {
        requestDirtyCheck();
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
     * Retrieves a list of mounted components that are currently not assigned to a
     * location.
     * Such equipment would be deleted on save and reload.
     *
     * @return a List containing unallocated Mounted objects.
     */
    public abstract java.util.List<Mounted<?>> getUnallocatedMounted();
}