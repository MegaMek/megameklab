/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.MegaMek;
import megamek.client.ui.util.UIUtil;
import megamek.common.preference.PreferenceManager;
import megamek.common.ui.CloseableTab;
import megamek.common.ui.DetachedTabInfo;
import megamek.common.ui.EnhancedTabbedPane;
import megamek.common.ui.EnhancedTabbedPane.TabStateListener;
import megamek.common.units.Entity;
import megameklab.MMLConstants;
import megameklab.MegaMekLab;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.util.TabUtil;
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;
import megameklab.util.UnitUtil;

/**
 * Replaces {@link MegaMekLabMainUI} as the top-level window for MML. Holds several {@link MegaMekLabMainUI}s as tabs,
 * allowing many units to be open at once.
 */
public class MegaMekLabTabbedUI extends JFrame implements MenuBarOwner, ChangeListener {

    private static final Set<MegaMekLabTabbedUI> openWindows = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final List<MegaMekLabMainUI> editors = new CopyOnWriteArrayList<>();
    private static final ReopenTabStack closedEditors = new ReopenTabStack();

    // Replace the existing JTabbedPane with our enhanced version
    private final EnhancedTabbedPane tabs;

    private final MenuBar menuBar;

    /**
     * Constructs a new MegaMekLabTabbedUI instance, which serves as the main tabbed UI for managing multiple
     * MegaMekLabMainUI editors. Automatically initializes a default BMMainUI instance if no entities are provided.
     *
     * @param entities A variable number of MegaMekLabMainUI instances that will be added as tabs to the UI. If no
     *                 entities are provided, a default BMMainUI instance will be created and added.
     */
    public MegaMekLabTabbedUI(MegaMekLabMainUI... entities) {
        super("MegaMekLab");
        openWindows.add(this);
        JButton newButton = createNewButton();
        JButton openButton = createOpenButton();
        // Initialize tabs with action handlers
        tabs = new EnhancedTabbedPane(List.of(newButton, openButton), true, true);
        tabs.setDockGroupId("MegaMekLabTabbedUI.tabs");

        // If there are more tabs than can fit, show a scroll bar
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tabs.addChangeListener(new ChangeListener() {
            private WeakReference<Component> lastSelectedComponent = null;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabs.getSelectedIndex() < 0) {
                    return;
                }
                final Component selectedComponent = tabs.getSelectedComponent();
                final Component previousComponent = lastSelectedComponent != null ? lastSelectedComponent.get() : null;

                if (selectedComponent != previousComponent) {
                    if (selectedComponent instanceof MegaMekLabMainUI mainUI) {
                        mainUI.onActivated();
                        refreshMenuBar();
                    }
                }
                lastSelectedComponent = new WeakReference<>(selectedComponent);
            }
        });
        // Register tab reattachment listener
        tabs.addTabStateListener(new TabStateListener() {
            @Override
            public boolean onTabReattaching(DetachedTabInfo tabInfo) {
                if (tabInfo.getComponent() instanceof MegaMekLabMainUI mainUI) {
                    mainUI.reattachAllTabs();
                }
                return true;
            }

            @Override
            public void onTabReattached(int tabIndex, Component component) {
                if (component instanceof MegaMekLabMainUI editor) {
                    editor.setTabOwner(MegaMekLabTabbedUI.this);
                    if (!editors.contains(editor)) {
                        editors.add(editor);
                    }
                }
            }

            @Override
            public boolean onTabDetaching(int tabIndex, Component component) {
                // If there is only one tab, don't allow detachment
                return tabs.getTabCount() > 1;
            }

            @Override
            public void onTabCloseRequest(int tabIndex, Component component, InputEvent e) {
                if (component instanceof MegaMekLabMainUI editor) {
                    if (e.isShiftDown() || editor.safetyPrompt()) {
                        closeTabAt(tabIndex);
                    }
                }
            }

            @Override
            public void onTabRemoved(int tabIndex, Component component) {
                // If you try to close the last tab, we close this window
                if (tabs.getTabCount() < 1) {
                    final boolean willTerminate = (openWindows.size() == 1) &&
                          (!ForceBuildUI.hasInstance() ||
                                !ForceBuildUI.getInstance().isShowing() ||
                                ForceBuildUI.getForceSize() == 0);
                    if (willTerminate &&
                          (CConfig.getBooleanParam(CConfig.MISC_APPLICATION_EXIT_PROMPT)) &&
                          !noTabsOpenExitPrompt()) {
                        newTab();
                    } else {
                        cleanupAndDispose();
                    }
                }
            }
        });
        tabs.setDetachedWindowFactory((title, icon, component, size, location) -> {
            if (component instanceof MegaMekLabMainUI mainUI) {
                // Create a new tabbed UI to host this detached tab
                MegaMekLabTabbedUI newTabbedUI = new MegaMekLabTabbedUI();
                newTabbedUI.setLocation(location);
                newTabbedUI.addTab(mainUI);
                return newTabbedUI;
            }
            return null; // Return null to use default window creation
        });

        // Add initial tabs
        for (MegaMekLabMainUI e : entities) {
            addTab(e);
        }

        tabs.addChangeListener(this);
        setContentPane(tabs);

        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        // Enable opening unit and mul files by drag-and-drop
        setTransferHandler(new MMLFileDropTransferHandler(this));

        // Remember the size and position of the window from last time MML was launched
        pack();
        restrictToScreenSize();
        setLocationRelativeTo(null);
        CConfig.getMainUiWindowSize(this).ifPresent(this::setSize);
        CConfig.getMainUiWindowPosition(this).ifPresent(this::setLocation);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(); // Call exit() to handle closing the window
            }
        });
        setExtendedState(CConfig.getIntParam(CConfig.GUI_FULLSCREEN));

    }

    public static boolean isOpen() {
        return !openWindows.isEmpty();
    }

    /**
     * Checks if the given editor is the currently selected tab in the tabbed UI.
     *
     * @param editor The MegaMekLabMainUI instance to check against the currently selected
     *
     * @return True if the given editor is the currently selected tab, false otherwise.
     */
    public boolean isTabEditorSelected(MegaMekLabMainUI editor) {
        Component tab = tabs.getTabComponentAt(tabs.getSelectedIndex());
        if (tab instanceof CloseableTab closeableTab) {
            return closeableTab.isTabSelected();
        }
        return false;
    }

    /**
     * Creates a configured "New" button
     */
    private JButton createNewButton() {
        Icon newIcon = UIManager.getIcon("FileView.fileIcon");
        JButton button = new JButton(newIcon);
        button.setToolTipText("<html>New Blank Mek<br>Right Click: Select Unit Type");
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Left click - directly create a new unit
                    createNewUnit(Entity.ETYPE_MEK, false, false);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Right click - show popup menu
                    showNewUnitPopupMenu(button);
                }
            }
        });
        button.setPreferredSize(new Dimension(32, 32));
        return button;
    }

    /**
     * Creates a configured "Open" button
     */
    private JButton createOpenButton() {
        Icon openIcon = UIManager.getIcon("Tree.openIcon");
        JButton button = new JButton(openIcon);
        button.setToolTipText("<html>Load unit from cache<br>Right Click: Load Unit Menu");
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Left click - directly load from cache
                    StartupGUI.selectAndLoadUnitFromCache(MegaMekLabTabbedUI.this);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Right click - show popup menu
                    showLoadUnitPopupMenu(button);
                }
            }
        });
        button.setPreferredSize(new Dimension(32, 32));
        return button;
    }

    /**
     * Displays the popup menu for creating new units
     */
    private void showNewUnitPopupMenu(Component source) {
        JPopupMenu menu = createNewUnitPopupMenu();
        Point location = new Point(5, source.getHeight());
        menu.show(source, location.x, location.y);
    }

    /**
     * Creates the popup menu with options for creating different unit types
     */
    private JPopupMenu createNewUnitPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(newUnitItem("New Mek", Entity.ETYPE_MEK, false));
        menu.add(newUnitItem("New Fighter", Entity.ETYPE_AERO, false));
        menu.add(newUnitItem("New DropShip/Small Craft", Entity.ETYPE_DROPSHIP, false));
        menu.add(newUnitItem("New Advanced Aerospace", Entity.ETYPE_JUMPSHIP, false));
        menu.add(newUnitItem("New Tank", Entity.ETYPE_TANK, false));
        menu.add(newUnitItem("New Support Vehicle", Entity.ETYPE_SUPPORT_TANK, false));
        menu.add(newUnitItem("New Battle Armor", Entity.ETYPE_BATTLEARMOR, false));
        menu.add(newUnitItem("New Conventional Infantry", Entity.ETYPE_INFANTRY, false));
        menu.add(newUnitItem("New ProtoMek", Entity.ETYPE_PROTOMEK, false));
        menu.add(newUnitItem("New Handheld Weapon", Entity.ETYPE_HANDHELD_WEAPON, false));
        menu.add(newUnitItem("New Gun Emplacement", Entity.ETYPE_GUN_EMPLACEMENT, false));

        JMenu primitive = new JMenu("New Primitive...");
        primitive.add(newUnitItem("New Mek", Entity.ETYPE_MEK, true));
        primitive.add(newUnitItem("New Fighter", Entity.ETYPE_AERO, true));
        primitive.add(newUnitItem("New DropShip/Small Craft", Entity.ETYPE_DROPSHIP, true));
        primitive.add(newUnitItem("New JumpShip", Entity.ETYPE_JUMPSHIP, true));

        menu.add(primitive);
        return menu;
    }

    /**
     * Creates a menu item for creating a specific unit type
     */
    private JMenuItem newUnitItem(String name, long entityType, boolean primitive) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(e -> createNewUnit(entityType, primitive, primitive));
        return item;
    }

    /**
     * Displays the popup menu for loading units
     */
    private void showLoadUnitPopupMenu(Component source) {
        JPopupMenu menu = createLoadUnitPopupMenu();
        Point location = new Point(5, source.getHeight());
        menu.show(source, location.x, location.y);
    }

    /**
     * Creates the popup menu with options for loading units
     */
    private JPopupMenu createLoadUnitPopupMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem fromCache = new JMenuItem("Load from cache");
        fromCache.addActionListener(e -> StartupGUI.selectAndLoadUnitFromCache(this));
        menu.add(fromCache);

        JMenuItem fromFile = new JMenuItem("Load from file");
        fromFile.addActionListener(e -> getMMLMenuBar().loadUnitFromFile(-1));
        menu.add(fromFile);

        return menu;
    }

    /**
     * Refreshes the display of the given entity. This method is called when the entity is modified externally.
     *
     * @param entityToFind {@link Entity} to be refreshed.
     */
    public static synchronized void refreshEntity(Entity entityToFind) {
        if (entityToFind == null) {
            return;
        }
        // Try to find the entity in the open editors
        for (MegaMekLabMainUI editor : editors) {
            if (editor.getEntity() == entityToFind) {
                SwingUtilities.invokeLater(editor::refreshAll);
                return; // Found and refreshed
            }
        }
    }

    /**
     * Finds an open editor tab for the given entity
     *
     * @param entityToFind The entity
     */
    public static synchronized MegaMekLabMainUI getEditorForEntity(Entity entityToFind) {
        if (entityToFind == null) {
            return null;
        }
        // Try to find the entity in the open editors
        for (MegaMekLabMainUI editor : editors) {
            if (editor.getEntity() == entityToFind) {
                return editor;
            }
        }
        return null;
    }

    /**
     * Finds an open editor tab for the given entity, brings its window to the front, and selects the tab. If no editor
     * is found, creates a new tab in the first available window.
     *
     * @param entityToFind The entity to show the editor for.
     */
    public static synchronized void showEditorForEntity(Entity entityToFind) {
        MegaMekLabMainUI editor = getEditorForEntity(entityToFind);
        if (editor != null) {
            MegaMekLabTabbedUI owner = editor.getTabOwner();
            if (owner != null) {
                owner.setVisible(true);
                owner.toFront();
                owner.requestFocus();
                owner.tabs.setSelectedComponent(editor);
                return; // Found and activated
            }
        }

        // If not found, create a new tab in the first available window
        MegaMekLabTabbedUI targetWindow = openWindows.stream().findFirst().orElse(null);
        if (targetWindow == null) {
            // If no windows are open, create a new one
            targetWindow = new MegaMekLabTabbedUI();
            targetWindow.setVisible(true);
        }

        if (StartupGUI.hasInstance()) {
            StartupGUI.getInstance().getFrame().setVisible(false);
            StartupGUI.getInstance().getFrame().dispose();
        }

        MegaMekLabMainUI newUi = UiLoader.getUI(entityToFind, "");
        targetWindow.addTab(newUi, true);

        targetWindow.setVisible(true);
        targetWindow.toFront();
        targetWindow.requestFocus();
    }

    /**
     * Retrieves the currently selected editor from the tabbed user interface.
     *
     * @return The currently selected MegaMekLabMainUI instance, which represents the active editor in the tabbed UI, or
     *       null if no tab is selected.
     */
    public MegaMekLabMainUI getActiveEditor() {
        int selectedIndex = tabs.getSelectedIndex();
        // Check if the selected index is valid
        if (selectedIndex >= 0) {
            Component tabComponent = tabs.getTabComponentAt(selectedIndex);
            if (tabComponent instanceof CloseableTab tab) {
                if (tab.getComponent() instanceof MegaMekLabMainUI mainUI) {
                    return mainUI;
                }
            }
        }
        return null;
    }

    /**
     * Updates the name of the currently selected tab in the tabbed user interface. Should typically be called when the
     * name of the unit being edited changes.
     *
     * @param editor  The editor for which the tab name needs to be set
     * @param tabName The new name to be set for the currently selected tab.
     */
    public void setTabName(MegaMekLabMainUI editor, String tabName) {
        int tabIndex = tabs.indexOfComponent(editor);
        if (tabIndex >= 0) {
            CloseableTab tab = (CloseableTab) tabs.getTabComponentAt(tabIndex);
            if (tab != null) {
                tab.setTitle(tabName);
                tab.setDirty(editor.isDirty());
            }
        }
    }

    /**
     * Adds a new editor tab to the tabbed UI. This includes adding the editor to the internal editor collection,
     * refreshing it, setting the ownership, and adding the tab to the tabs UI.
     *
     * @param editor      The MegaMekLabMainUI instance to be added as a new tab.
     * @param setSelected Whether to set the new tab as the currently selected tab.
     */
    private void addTab(MegaMekLabMainUI editor, boolean setSelected) {
        if (!editors.contains(editor)) {
            editors.add(editor);
        }
        final Entity entity = editor.getEntity();
        final String tabName = entity.getShortNameRaw();
        tabs.addCloseableTab(tabName, null, editor);
        editor.setTabOwner(this);
        if (setSelected) {
            tabs.setSelectedIndex(tabs.getTabCount() - 1);
        }
        // editor.refreshAll(); // not needed?
    }

    private void addTab(MegaMekLabMainUI editor) {
        addTab(editor, true);
    }

    /**
     * Create a new blank editor of the given unit type.
     *
     * @param type       the type of unit to load for the new editor UI
     * @param primitive  whether the unit is primitive
     * @param industrial whether the unit is an IndustrialMek
     */
    public void createNewUnit(long type, boolean primitive, boolean industrial) {
        createNewUnit(type, primitive, industrial, tabs.getTabCount());
    }

    /**
     * Create a new blank editor of the given unit type.
     *
     * @param type       the type of unit to load for the new editor UI
     * @param primitive  whether the unit is primitive
     * @param industrial whether the unit is an IndustrialMek
     * @param tabIndex   the index at which to insert the new tab
     */
    private void createNewUnit(long type, boolean primitive, boolean industrial, int tabIndex) {
        MegaMekLabMainUI editor = UiLoader.getUI(type, primitive, industrial);
        editors.add(editor);
        final Entity entity = editor.getEntity();
        final String tabName = entity.getShortNameRaw();
        tabs.addCloseableTab(tabName, null, editor, tabIndex);
        editor.setTabOwner(this);
        tabs.setSelectedIndex(tabIndex);
    }

    /**
     * Replaces the current editor with a new blank one of the given unit type. Disposes of the old editor UI after the
     * new one is initialized.
     *
     * @param type       the type of unit to load for the new editor UI
     * @param primitive  whether the unit is primitive
     * @param industrial whether the unit is an IndustrialMek
     */
    private void switchUnit(long type, boolean primitive, boolean industrial) {
        final int index = tabs.getSelectedIndex();
        if (index < 0) {
            return; // No tab selected, nothing to do
        }
        createNewUnit(type, primitive, industrial, index);
        closeTabAt(index + 1); // Close the old tab
    }

    /**
     * Replaces the current editor with a new blank one of the given unit type. Disposes of the old editor UI after the
     * new one is initialized.
     *
     * @param type      the type of unit to load for the new editor UI
     * @param primitive whether the unit is primitive
     */
    public void switchUnit(long type, boolean primitive) {
        switchUnit(type, primitive, false);
    }

    /**
     * Adds a new tab with the given unit to the tabbed user interface.
     *
     * @param entity      The Entity object representing the unit to be added.
     * @param filename    The name of the file associated with the unit being added.
     * @param setSelected Whether to set the new tab as the currently selected tab.
     */
    public void addUnit(Entity entity, String filename, boolean setSelected) {
        UnitUtil.updateLoadedUnit(entity);
        var ui = UiLoader.getUI(entity, filename);
        addTab(ui, setSelected);
        refreshMenuBar();
    }

    private boolean noTabsOpenExitPrompt() {
        if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS)) {
            return true;
        }
        int exitPrompt = JOptionPane.showConfirmDialog(this,
              "Would you like to exit MegaMekLab?",
              "Confirm Close",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE);
        return exitPrompt == JOptionPane.YES_OPTION;
    }

    private boolean exitPrompt() {
        if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS)) {
            return true;
        }
        int savePrompt = JOptionPane.showConfirmDialog(this,
              "All unsaved changes to open units will be discarded. Close anyway?",
              "Confirm Close",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE);
        return savePrompt == JOptionPane.YES_OPTION;
    }

    private synchronized void saveConfig() {
        CConfig.setParam(CConfig.GUI_FULLSCREEN, Integer.toString(getExtendedState()));
        CConfig.setParam(CConfig.GUI_PLAF, UIManager.getLookAndFeel().getClass().getName());
        CConfig.writeMainUiWindowSettings(this);
        CConfig.saveConfig();
        PreferenceManager.getInstance().save();
        MegaMek.getMMPreferences().saveToFile(MMLConstants.MM_PREFERENCES_FILE);
        MegaMekLab.getMMLPreferences().saveToFile(MMLConstants.MML_PREFERENCES_FILE);

        if (CConfig.getStartUpType() == MMLStartUp.RESTORE_TABS) {
            try {
                TabUtil.saveTabState(editors.stream().limit(editors.size()).toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cleanupAndDispose() {
        openWindows.remove(this);
        final boolean willTerminate = (openWindows.isEmpty() &&
              (!ForceBuildUI.hasInstance() ||
                    !ForceBuildUI.getInstance().isShowing() ||
                    ForceBuildUI.getForceSize() == 0));
        if (willTerminate) {
            saveConfig(); // Save settings before closing
        }
        // We dispose all tabs, we already prompted the user for saving
        for (int i = editors.size() - 1; i >= 0; i--) {
            MegaMekLabMainUI editor = editors.get(i);
            if (editor.getTabOwner() != MegaMekLabTabbedUI.this) {
                continue;
            }
            remove(editor);
            editor.setTabOwner(null);
            editors.remove(editor);
            closedEditors.push(editor);
        }
        if (willTerminate) {
            System.exit(0);
        } else {
            dispose();
        }
    }

    @Override
    public boolean exit() {
        if (!exitPrompt()) {
            return false;
        }
        cleanupAndDispose();
        return true;
    }

    @Override
    public void refreshAll() {
        for (MegaMekLabMainUI editor : editors) {
            editor.refreshAll();
        }
    }

    private void restrictToScreenSize() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        int scaledMonitorW = UIUtil.getScaledScreenWidth(currentMonitor);
        int scaledMonitorH = UIUtil.getScaledScreenHeight(currentMonitor);
        if (scaledMonitorH <= 0 || scaledMonitorW <= 0) {
            return; // Invalid monitor size, do not resize
        }
        int w = Math.min(getSize().width, scaledMonitorW);
        int h = Math.min(getSize().height, scaledMonitorH);
        setSize(new Dimension(w, h));
    }

    public void newTab() {
        addTab(new BMMainUI(false, false));
        refreshMenuBar();
    }

    /**
     * Deletes the current tab. This does not issue the safety prompt, it is up to the caller to do so!
     */
    public void closeCurrentTab() {
        closeTabAt(tabs.getSelectedIndex());
    }

    private void closeTabAt(int position) {
        Component tabComponent = tabs.getTabComponentAt(position);
        if (tabComponent instanceof CloseableTab tab) {
            if (tab.getComponent() instanceof MegaMekLabMainUI editor) {
                editor.reattachAllTabs();
                final int currentIndex = tabs.getSelectedIndex();
                tabs.remove(position);
                if (currentIndex == position) {
                    // If the tab we just closed was the selected tab, select the previous one
                    // (or the next one if it was the first tab)
                    if (currentIndex > 0 && currentIndex < tabs.getTabCount()) {
                        tabs.setSelectedIndex(currentIndex - 1);
                    } else if (tabs.getTabCount() > 0) {
                        tabs.setSelectedIndex(0);
                    }
                }
                remove(editor);
                editor.setTabOwner(null);
                editors.remove(editor);
                closedEditors.push(editor);
            }
        }

        // Tell the menu bar to enable the "reopen tab" shortcut
        refreshMenuBar();
    }

    public void reopenTab() {
        var editor = closedEditors.pop();
        if (editor != null) {
            addTab(editor);
            refreshMenuBar();
        }
        tabs.revalidate();
        tabs.repaint();
    }

    public boolean hasClosedTabs() {
        return !closedEditors.isEmpty();
    }

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public Entity getEntity() {
        MegaMekLabMainUI editor = getActiveEditor();
        if (editor == null) {
            return null;
        }
        return editor.getEntity();
    }

    @Override
    public String getFileName() {
        MegaMekLabMainUI editor = getActiveEditor();
        return editor != null ? editor.getFileName() : null;
    }

    @Override
    public boolean hasEntityNameChanged() {
        MegaMekLabMainUI editor = getActiveEditor();
        return editor != null && editor.hasEntityNameChanged();
    }

    @Override
    public void refreshMenuBar() {
        if (menuBar != null && getActiveEditor() != null) {
            menuBar.refreshMenuBar();
        }
    }

    @Override
    public MenuBar getMMLMenuBar() {
        return menuBar;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tabs && !editors.isEmpty() && getActiveEditor() != null) {
            refreshMenuBar();
        }
    }

    public List<Entity> getAllEntities() {
        return editors.stream().map(MegaMekLabMainUI::getEntity).toList();
    }

    /**
     * ReopenTabStack is a utility class that manages a fixed-capacity stack of closed MegaMekLabMainUI editors. It
     * allows for storing references to recently closed editors and retrieving them in reverse order of their closure,
     * resembling a "reopen tab" functionality.
     * <p>
     * This stack maintains a circular buffer of references with a maximum capacity defined by the constant
     * STACK_CAPACITY. If the capacity is exceeded, the oldest editor will be disposed of and removed to make room for
     * new entries.
     */
    private static class ReopenTabStack {
        public static final int STACK_CAPACITY = 20;

        private final MegaMekLabMainUI[] closedEditors = new MegaMekLabMainUI[STACK_CAPACITY];
        private int size = 0;
        private int start = 0;

        public void push(MegaMekLabMainUI editor) {
            int pos = start + size % closedEditors.length;
            if (size == closedEditors.length) {
                start++;
                start %= closedEditors.length;
            } else {
                size++;
            }
            closedEditors[pos] = editor;
        }

        public MegaMekLabMainUI pop() {
            if (size == 0) {
                return null;
            }
            int pos = start + size - 1 % closedEditors.length;
            var ret = closedEditors[pos];

            closedEditors[pos] = null;
            size--;

            return ret;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
