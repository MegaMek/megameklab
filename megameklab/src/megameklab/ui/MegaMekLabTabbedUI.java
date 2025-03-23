/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.MegaMek;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.preference.PreferenceManager;
import megameklab.MMLConstants;
import megameklab.MegaMekLab;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.ui.util.TabUtil;
import megameklab.ui.util.EnhancedTabbedPane;
import megameklab.ui.util.EnhancedTabbedPane.DetachedTabInfo;
import megameklab.ui.util.EnhancedTabbedPane.EnhancedTab;
import megameklab.ui.util.EnhancedTabbedPane.TabStateListener;
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;
import megameklab.util.UnitUtil;

/**
 * Replaces {@link MegaMekLabMainUI} as the top-level window for MML.
 * Holds several {@link MegaMekLabMainUI}s as tabs, allowing many units to be
 * open at once.
 */
public class MegaMekLabTabbedUI extends JFrame implements MenuBarOwner, ChangeListener {

    private final List<MegaMekLabMainUI> editors = new ArrayList<>();

    private final ReopenTabStack closedEditors = new ReopenTabStack();

    // Replace the existing JTabbedPane with our enhanced version
    private final EnhancedTabbedPane tabs;

    private final MenuBar menuBar;

    /**
     * Constructs a new MegaMekLabTabbedUI instance, which serves as the main tabbed
     * UI
     * for managing multiple MegaMekLabMainUI editors. Automatically initializes a
     * default
     * BMMainUI instance if no entities are provided.
     *
     * @param entities A variable number of MegaMekLabMainUI instances that will be
     *                 added
     *                 as tabs to the UI. If no entities are provided, a default
     *                 BMMainUI
     *                 instance will be created and added.
     */
    public MegaMekLabTabbedUI(MegaMekLabMainUI... entities) {
        super("MegaMekLab");

        JButton newButton = createNewButton();
        JButton openButton = createOpenButton();
        // Initialize tabs with action handlers
        tabs = new EnhancedTabbedPane(List.of(newButton, openButton), true, true);
        tabs.setMinimumTabsCount(1);
        tabs.setDragDockingToVisibleTabsAreaOnly(true);

        // If there are more tabs than can fit, show a scroll bar
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // Register tab reattachment listener
        tabs.addTabStateListener(new TabStateListener() {
            @Override
            public void onTabReattaching(DetachedTabInfo tabInfo) {
                if (tabInfo.getComponent() instanceof MegaMekLabMainUI mainUI) {
                    mainUI.reattachAllTabs();
                }
            }
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

        // ...and save that size and position on exit
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitOnWindowClosingListener(this));
        setExtendedState(CConfig.getIntParam(CConfig.GUI_FULLSCREEN));

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
                    MegaMekLabMainUI newUi = UiLoader.getUI(Entity.ETYPE_MEK, false, false);
                    newUi.setOwner(MegaMekLabTabbedUI.this);
                    addTab(newUi);
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
        Icon openIcon = UIManager.getIcon("FileView.directoryIcon");
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
        item.addActionListener(e -> {
            MegaMekLabMainUI newUi = UiLoader.getUI(entityType, primitive, false);
            newUi.setOwner(MegaMekLabTabbedUI.this);
            addTab(newUi);
        });
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
     * Retrieves the currently selected editor from the tabbed user interface.
     *
     * @return The currently selected MegaMekLabMainUI instance, which represents
     *         the active editor in the tabbed UI, or null if no tab is selected.
     */
    public MegaMekLabMainUI currentEditor() {
        int selectedIndex = tabs.getSelectedIndex();
        // Check if the selected index is valid
        if (selectedIndex >= 0) {
            Component tabComponent = tabs.getTabComponentAt(selectedIndex);
            if (tabComponent instanceof EnhancedTab tab) {
                if (tab.getComponent() instanceof MegaMekLabMainUI mainUI) {
                    return mainUI;
                }
            }
        }
        return null;
    }

    /**
     * Updates the name of the currently selected tab in the tabbed user interface.
     * Should typically be called when the name of the unit being edited changes.
     *
     * @param tabName The new name to be set for the currently selected tab.
     * @param editor  The editor for which the tab name needs to be set
     */
    public void setTabName(String tabName, MegaMekLabMainUI editor) {
        Component contentPane = editor.getContentPane();
        int tabIndex = tabs.indexOfComponent(contentPane);
        if (tabIndex >= 0) {
            EnhancedTab tab = (EnhancedTab) tabs.getTabComponentAt(tabIndex);
            tab.setTitle(tabName);
        }
    }

    /**
     * Adds a new editor tab to the tabbed UI. This includes adding the editor
     * to the internal editor collection, refreshing it, setting the ownership,
     * and adding the tab to the tabs UI.
     *
     * @param editor The MegaMekLabMainUI instance to be added as a new tab.
     */
    private void addTab(MegaMekLabMainUI editor) {
        editors.add(editor);
        Entity entity = editor.getEntity();

        // Use the enhanced tabbed pane to add a closeable tab
        String tabName = entity.getDisplayName();
        tabs.addEnhancedTab(tabName, null, editor, (component, e) -> {
            int index = tabs.indexOfComponent(component);
            if (index >= 0) {
                if (e.isShiftDown() || editor.safetyPrompt()) {
                    closeTabAt(index);
                }
            }
        });
        tabs.setSelectedIndex(tabs.getTabCount() - 1);
        editor.setOwner(this);
        editor.refreshAll();
    }

    /**
     * The name is misleading, this is actually the Switch Unit Type operation!
     * Replaces the current editor with a new blank one of the given unit type.
     * Disposes of the old editor UI after the new one is initialized.
     *
     * @param type       the type of unit to load for the new editor UI
     * @param primitive  whether the unit is primitive
     * @param industrial whether the unit is an IndustrialMek
     */
    private void newUnit(long type, boolean primitive, boolean industrial) {
        var oldUi = editors.get(tabs.getSelectedIndex());

        var newUi = UiLoader.getUI(type, primitive, industrial);
        newUi.setOwner(this);
        editors.set(tabs.getSelectedIndex(), newUi);
        tabs.setComponentAt(tabs.getSelectedIndex(), newUi.getContentPane());
        tabs.setEnabledAt(tabs.getSelectedIndex(), true);
        oldUi.dispose();
        refreshMenuBar();
    }

    /**
     * The name is misleading, this is actually the Switch Unit Type operation!
     * Replaces the current editor with a new blank one of the given unit type.
     * Disposes of the old editor UI after the new one is initialized.
     *
     * @param type      the type of unit to load for the new editor UI
     * @param primitive whether the unit is primitive
     */
    @Override
    public void newUnit(long type, boolean primitive) {
        newUnit(type, primitive, false);
    }

    /**
     * Adds a new tab with the given unit to the tabbed user interface.
     *
     * @param entity   The Entity object representing the unit to be added.
     * @param filename The name of the file associated with the unit being added.
     */
    public void addUnit(Entity entity, String filename) {
        UnitUtil.updateLoadedUnit(entity);

        var ui = UiLoader.getUI(UnitUtil.getEditorTypeForEntity(entity), entity.isPrimitive(),
                entity.isIndustrialMek());
        addTab(ui);
        ui.setOwner(this);
        ui.setEntity(entity, filename);
        ui.reloadTabs();
        ui.refreshAll();
        refreshMenuBar();
    }

    private boolean exitPrompt() {
        if (CConfig.getBooleanParam(CConfig.MISC_SKIP_SAFETY_PROMPTS)) {
            return true;
        }
        int savePrompt = JOptionPane.showConfirmDialog(this,
                "All unsaved changes to open units will be discarded. Are you sure you would like to exit?",
                "Save Units Before Proceeding?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return savePrompt == JOptionPane.YES_OPTION;
    }

    @Override
    public boolean exit() {
        if (!exitPrompt()) {
            return false;
        }

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

        return true;
    }

    private void restrictToScreenSize() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        int scaledMonitorW = UIUtil.getScaledScreenWidth(currentMonitor);
        int scaledMonitorH = UIUtil.getScaledScreenHeight(currentMonitor);
        int w = Math.min(getSize().width, scaledMonitorW);
        int h = Math.min(getSize().height, scaledMonitorH);
        setSize(new Dimension(w, h));
    }

    public void newTab() {
        addTab(new BMMainUI(false, false));
        refreshMenuBar();
    }

    /**
     * Deletes the current tab.
     * This does not issue the safety prompt, it is up to the caller to do so!
     */
    public void closeCurrentTab() {
        closeTabAt(tabs.getSelectedIndex());
    }

    private void closeTabAt(int position) {
        // If you try to close the last tab, create a new blank mek tab
        // Since the UI can't exist in a meaningful state without a tab open
        if (tabs.getTabCount() <= 1) {
            newTab();
        }

        Component tabComponent = tabs.getTabComponentAt(position);
        if (tabComponent instanceof EnhancedTab tab) {
            if (tab.getComponent() instanceof MegaMekLabMainUI) {
                MegaMekLabMainUI editor = (MegaMekLabMainUI) tab.getComponent();
                editor.reattachAllTabs();
                tabs.remove(position);
                if (tabs.getSelectedIndex() == tabs.getTabCount() - 1) {
                    tabs.setSelectedIndex(tabs.getSelectedIndex() - 1);
                }
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
        MegaMekLabMainUI editor = currentEditor();
        if (editor == null) {
            return null;
        }
        return editor.getEntity();
    }

    @Override
    public String getFileName() {
        MegaMekLabMainUI editor = currentEditor();
        return editor != null ? editor.getFileName() : null;
    }

    @Override
    public boolean hasEntityNameChanged() {
        MegaMekLabMainUI editor = currentEditor();
        return editor != null && editor.hasEntityNameChanged();
    }

    @Override
    public void refreshMenuBar() {
        if (menuBar != null && currentEditor() != null) {
            menuBar.refreshMenuBar();
        }
    }

    @Override
    public MenuBar getMMLMenuBar() {
        return menuBar;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tabs && !editors.isEmpty() && currentEditor() != null) {
            refreshMenuBar();
        }
    }

    public List<Entity> getAllEntities() {
        return editors.stream().map(MegaMekLabMainUI::getEntity).toList();
    }

    /**
     * ReopenTabStack is a utility class that manages a fixed-capacity stack of
     * closed MegaMekLabMainUI editors.
     * It allows for storing references to recently closed editors and retrieving
     * them in reverse order of their closure, resembling a "reopen tab"
     * functionality.
     * <p>
     * This stack maintains a circular buffer of references with a maximum capacity
     * defined by the constant STACK_CAPACITY. If the capacity is exceeded, the
     * oldest editor will be disposed of and removed to make room for new entries.
     */
    private static class ReopenTabStack {
        public static final int STACK_CAPACITY = 20;

        private final MegaMekLabMainUI[] closedEditors = new MegaMekLabMainUI[STACK_CAPACITY];
        private int size = 0;
        private int start = 0;

        public void push(MegaMekLabMainUI editor) {
            int pos = start + size % closedEditors.length;
            if (size == closedEditors.length) {
                closedEditors[pos].dispose();
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