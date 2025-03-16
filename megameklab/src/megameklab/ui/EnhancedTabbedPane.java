package megameklab.ui.util;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * @author: Drake
 *
 *          Enhanced tabbed pane with closable, draggable tabs and additional
 *          action buttons.
 *          This component extends JTabbedPane to add the following features:
 *          - Closable tabs with an X button on each tab
 *          - Ability to drag and reorder tabs
 *          - Action buttons at the right side of the tab area (sticky and not)
 *          - Detachable tabs that can be dragged out (or right-click) into
 *          floating windows
 */
public class EnhancedTabbedPane extends JTabbedPane {

    // Track detached tabs
    public static class DetachedTabInfo {
        String title;
        Icon icon;
        int originalIndex;
        Component component;
        Window wrapperComponent;
        BiConsumer<Component, InputEvent> closeAction;
        boolean isEnhancedTab = false;

        /**
         * Creates a new DetachedTabInfo instance
         * 
         * @param title
         * @param icon
         * @param component
         * @param wrapperWindowComponent
         * @param originalIndex
         * @param closeAction
         * @param isEnhancedTab
         */
        public DetachedTabInfo(String title, Icon icon, Component component,
                Window wrapperComponent, int originalIndex, BiConsumer<Component, InputEvent> closeAction,
                boolean isEnhancedTab) {
            this.title = title;
            this.icon = icon;
            this.component = component;
            this.wrapperComponent = wrapperComponent;
            this.originalIndex = originalIndex;
            this.closeAction = closeAction;
            this.isEnhancedTab = isEnhancedTab;
        }

        public Component getComponent() {
            return component;
        }
    }

    private ConcurrentHashMap<Component, DetachedTabInfo> detachedTabs = new ConcurrentHashMap<>();
    private static final int BUTTON_SPACING = 2;
    private static final int GHOST_DRAG_THRESHOLD = 5;
    private static final float GHOST_OPACITY = 0.7f; // Semi-transparent

    // Button panel that sits outside the regular tabs
    private final JPanel actionButtonsPanel;
    private MouseInputAdapter dragEventsHandler;
    private JWindow ghostWindow;
    private boolean tabDetachingEnabled = false;
    private boolean tabReorderingEnabled = false;
    private boolean actionButtonsAlignAfterTabs = true;

    private class DragState {
        int tabIndex = -1;
        boolean isDragging = false;
        Point startPoint = null;
        Point dragOffset = null;
        boolean showingGhost = false;
    }

    private DragState dragState = new DragState();

    public EnhancedTabbedPane() {
        this(null, false, false);
    }

    public EnhancedTabbedPane(boolean tabDetachingEnabled, boolean tabReorderingEnabled) {
        this(null, tabDetachingEnabled, tabReorderingEnabled);
    }

    /**
     * Creates a new EnhancedTabbedPane with closable, draggable tabs and action
     * buttons.
     * 
     * @param actionButtons Action Buttons
     */
    public EnhancedTabbedPane(List<JButton> actionButtons, boolean tabDetachingEnabled, boolean tabReorderingEnabled) {
        super();
        // Create action buttons panel with New and Open buttons
        actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, BUTTON_SPACING, 0));
        actionButtonsPanel.setOpaque(false);
        this.tabDetachingEnabled = tabDetachingEnabled;
        this.tabReorderingEnabled = tabReorderingEnabled;

        if (actionButtons != null) {
            for (JButton button : actionButtons) {
                addHoverEffect(button);
                actionButtonsPanel.add(button);
            }
        }

        // Initialize drag and drop capabilities
        initDragEventsHandler();
        setupDragEventsListeners();

        // Add the component listener to handle positioning of action buttons
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                positionActionButtons();
            }
        });

        // Listen for tab changes to update action button positioning
        addChangeListener(e -> positionActionButtons());

        // We need to add the action buttons to the layered pane to position them
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (isShowing()) {
                    SwingUtilities.invokeLater(this::setupActionButtons);
                }
            }
        });
    }

    public void setTabDetachingEnabled(boolean enabled) {
        tabDetachingEnabled = enabled;
        setupDragEventsListeners();
    }

    public void setTabReorderingEnabled(boolean enabled) {
        tabReorderingEnabled = enabled;
        setupDragEventsListeners();
    }

    public void setActionButtonsAlignAfterTabs(boolean alignAfterTabs) {
        actionButtonsAlignAfterTabs = alignAfterTabs;
        deferredPositionActionButtons();
    }

    public void removeActionButtons() {
        actionButtonsPanel.removeAll();
        setupActionButtons();
    }

    public void setActionButtons(JButton... actionButtons) {
        actionButtonsPanel.removeAll();
        for (JButton button : actionButtons) {
            addHoverEffect(button);
            actionButtonsPanel.add(button);
        }
        setupActionButtons();
        deferredPositionActionButtons();
    }

    public void addActionButton(JButton button) {
        addHoverEffect(button);
        actionButtonsPanel.add(button);
        setupActionButtons();
        deferredPositionActionButtons();
    }

    public void removeActionButton(JButton button) {
        actionButtonsPanel.remove(button);
        setupActionButtons();
        deferredPositionActionButtons();
    }

    /**
     * Interface for listeners that are notified of tab state changes
     */
    public interface TabStateListener {
        /**
         * Called before a tab is detached from the pane
         * 
         * @param tabIndex  The index of the tab being detached
         * @param title     The title of the tab
         * @param component The component in the tab
         */
        default void onTabDetaching(int tabIndex, String title, Component component) {
        }

        /**
         * Called after a tab has been detached and placed in a window
         * 
         * @param window  The window containing the detached tab
         * @param tabInfo Information about the detached tab
         */
        default void onTabDetached(Window window, DetachedTabInfo tabInfo) {
        }

        /**
         * Called before a tab is reattached to the pane
         * 
         * @param tabInfo Information about the tab being reattached
         */
        default void onTabReattaching(DetachedTabInfo tabInfo) {
        }

        /**
         * Called after a tab has been reattached to the pane
         * 
         * @param tabIndex  The index where the tab was inserted
         * @param title     The title of the reattached tab
         * @param component The component that was reattached
         */
        default void onTabReattached(int tabIndex, String title, Component component) {
        }
    }

    private List<TabStateListener> tabStateListeners = new ArrayList<>();

    /**
     * Adds a listener that will be notified of tab state changes
     * 
     * @param listener The listener to add
     */
    public void addTabStateListener(TabStateListener listener) {
        if (listener != null && !tabStateListeners.contains(listener)) {
            tabStateListeners.add(listener);
        }
    }

    /**
     * Removes a previously added tab state listener
     * 
     * @param listener The listener to remove
     * @return true if the listener was found and removed
     */
    public boolean removeTabStateListener(TabStateListener listener) {
        return tabStateListeners.remove(listener);
    }

    // Event firing methods
    protected void fireTabDetaching(int tabIndex, String title, Component component) {
        for (TabStateListener listener : tabStateListeners) {
            listener.onTabDetaching(tabIndex, title, component);
        }
    }

    protected void fireTabDetached(Window window, DetachedTabInfo tabInfo) {
        for (TabStateListener listener : tabStateListeners) {
            listener.onTabDetached(window, tabInfo);
        }
    }

    protected void fireTabReattaching(DetachedTabInfo tabInfo) {
        for (TabStateListener listener : tabStateListeners) {
            listener.onTabReattaching(tabInfo);
        }
    }

    protected void fireTabReattached(int tabIndex, String title, Component component) {
        for (TabStateListener listener : tabStateListeners) {
            listener.onTabReattached(tabIndex, title, component);
        }
    }

    /**
     * Adds hover effect to a button
     * 
     * @param button The button to enhance with hover effect
     */
    protected void addHoverEffect(JButton button) {
        // Save original appearance
        final Color originalBackground = button.getBackground();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Apply hover appearance
                button.setContentAreaFilled(true);
                button.setBackground(UIManager.getColor("Button.highlight"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore original appearance
                button.setContentAreaFilled(false);
                button.setBackground(originalBackground);
            }
        });
    }

    /**
     * Sets up the action buttons on the layered pane to ensure they remain on top
     */
    private void setupActionButtons() {
        if (actionButtonsPanel.getComponentCount() == 0) {
            clearActionButtonsDisplay();
            return;
        }
        removeFromPreviousParent();
        addToLayeredPane();
        setupSpacerForButtons();
    }

    /**
     * Clears action buttons display when there are no buttons
     */
    private void clearActionButtonsDisplay() {
        putClientProperty("JTabbedPane.trailingComponent", null);
        actionButtonsPanel.setVisible(false);
    }

    /**
     * Removes the action buttons panel from its previous parent if it exists
     */
    private void removeFromPreviousParent() {
        Container oldParent = actionButtonsPanel.getParent();
        if (oldParent != null) {
            oldParent.remove(actionButtonsPanel);
        }
    }

    /**
     * Adds the action buttons panel to the appropriate layered pane
     */
    private void addToLayeredPane() {
        // First try to add to parent layered pane if available
        Container parent = getParent();
        if (parent instanceof JLayeredPane) {
            JLayeredPane layeredPane = (JLayeredPane) parent;
            layeredPane.add(actionButtonsPanel, JLayeredPane.PALETTE_LAYER);
            layeredPane.setComponentZOrder(actionButtonsPanel, 0);
        } else if (parent != null) {
            // Otherwise find the root pane and use its layered pane
            JRootPane rootPane = SwingUtilities.getRootPane(this);
            if (rootPane != null) {
                JLayeredPane layeredPane = rootPane.getLayeredPane();
                layeredPane.add(actionButtonsPanel, JLayeredPane.PALETTE_LAYER);
            }
        }

        // Position the buttons appropriately
        positionActionButtons();
    }

    /**
     * Sets up a spacer component to reserve space for the action buttons
     */
    private void setupSpacerForButtons() {
        actionButtonsPanel.setVisible(true);
        // Force the buttons panel to calculate its preferred size
        actionButtonsPanel.invalidate();
        actionButtonsPanel.validate();

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);

        // Add some extra padding to ensure enough space
        int width = actionButtonsPanel.getPreferredSize().width + 5;
        spacer.setPreferredSize(new Dimension(width, 1));

        putClientProperty("JTabbedPane.trailingComponent", spacer);
    }

    private void deferredPositionActionButtons() {
        if (actionButtonsPanel.getComponentCount() == 0) {
            return;
        }
        SwingUtilities.invokeLater(this::positionActionButtons);
    }

    /**
     * Positions the action buttons at the right side of the tab area
     */
    private void positionActionButtons() {
        // Check if the tabbed pane is showing, has a parent, and has action buttons
        if (actionButtonsPanel == null || !isShowing() || getParent() == null
                || actionButtonsPanel.getComponentCount() == 0) {
            return;
        }
        Container parent = actionButtonsPanel.getParent();
        if (parent == null) {
            return;
        }
        try {
            Point tabPosition = getLocationOnScreen();
            Point parentPosition = parent.getLocationOnScreen();
            int actionButtonsPanelWidth = actionButtonsPanel.getPreferredSize().width;
            int actionButtonsPanelHeight = actionButtonsPanel.getPreferredSize().height;
            int availableWidth = getWidth();

            int rightEdgePosition = availableWidth - actionButtonsPanelWidth - 5; // 5px padding from right edge

            int x = tabPosition.x - parentPosition.x + 5;
            int y = tabPosition.y - parentPosition.y + 3;

            // Make sure there's at least one tab before trying to get the bounds
            if (getTabCount() > 0) {
                boolean shouldPositionAtRight = false;

                if (!actionButtonsAlignAfterTabs) {
                    shouldPositionAtRight = true;
                } else if (getTabLayoutPolicy() == SCROLL_TAB_LAYOUT && getTabCount() > 1) {
                    // Calculate the total width of all tabs
                    int totalTabsWidth = 0;
                    for (int i = 0; i < getTabCount(); i++) {
                        Rectangle tabBounds = getBoundsAt(i);
                        if (tabBounds != null) {
                            totalTabsWidth += tabBounds.width;
                        }
                    }
                    if (totalTabsWidth + actionButtonsPanelWidth > availableWidth - 10) {
                        shouldPositionAtRight = true;
                    }
                }
                // Calculate the right edge of the last visible tab
                Rectangle lastTabBounds = getBoundsAt(getTabCount() - 1);
                if (lastTabBounds != null) {
                    int rightEdgeOfLastTab = lastTabBounds.x + lastTabBounds.width + 5;

                    if (shouldPositionAtRight || rightEdgeOfLastTab + actionButtonsPanelWidth > availableWidth - 5) {
                        x = tabPosition.x - parentPosition.x + rightEdgePosition;
                    } else {
                        x = tabPosition.x - parentPosition.x + rightEdgeOfLastTab;
                    }
                    // Position the buttons properly after the last tab, aligning vertically
                    y = tabPosition.y - parentPosition.y + lastTabBounds.y +
                            (lastTabBounds.height - actionButtonsPanelHeight) / 2;
                }
            }

            actionButtonsPanel.setBounds(x, y, actionButtonsPanelWidth, actionButtonsPanelHeight);
            actionButtonsPanel.revalidate();
            actionButtonsPanel.repaint();
        } catch (IllegalComponentStateException e) {
            // Component might not be showing on screen yet
        }
    }

    private boolean isDragFunctionalityEnabled() {
        return tabDetachingEnabled || tabReorderingEnabled;
    }

    private void setupDragEventsListeners() {
        removeMouseListener(dragEventsHandler);
        removeMouseMotionListener(dragEventsHandler);
        if (isDragFunctionalityEnabled()) {
            addMouseListener(dragEventsHandler);
            addMouseMotionListener(dragEventsHandler);
        }
    }

    /**
     * Initialize drag and drop functionality for tab reordering
     */
    private void initDragEventsHandler() {
        dragEventsHandler = new MouseInputAdapter() {
            private int targetIndex = -1; // Track potential drop target during drag

            @Override
            public void mousePressed(MouseEvent e) {
                if (!isDragFunctionalityEnabled()) {
                    return;
                }
                dragState.tabIndex = indexAtLocation(e.getX(), e.getY());
                if (dragState.tabIndex >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        dragState.startPoint = e.getPoint();
                        dragState.isDragging = true;
                        targetIndex = -1; // Reset target index
                    } else if (tabDetachingEnabled && SwingUtilities.isRightMouseButton(e)) {
                        Point locationOnScreen = e.getLocationOnScreen();
                        locationOnScreen.x -= 50;
                        locationOnScreen.y -= 10;
                        detachTab(dragState.tabIndex, locationOnScreen);
                        dragState.tabIndex = -1;
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!isDragFunctionalityEnabled()) {
                    return;
                }
                if (dragState.isDragging && dragState.tabIndex >= 0 && dragState.startPoint != null) {
                    int deltaX = Math.abs(e.getX() - dragState.startPoint.x);
                    int deltaY = Math.abs(e.getY() - dragState.startPoint.y);

                    if ((deltaX > GHOST_DRAG_THRESHOLD || deltaY > GHOST_DRAG_THRESHOLD) && !dragState.showingGhost) {
                        showGhostImage(dragState.tabIndex, e.getLocationOnScreen());
                    }

                    if (dragState.showingGhost) {
                        updateGhostLocation(e.getLocationOnScreen());
                    }
                    Rectangle bounds = getTabAreaBounds();
                    if (tabDetachingEnabled && !bounds.contains(e.getPoint())) {
                        // Mouse is outside the tabbed pane, detach the tab
                        hideGhostImage();
                        Point locationOnScreen = e.getLocationOnScreen();
                        locationOnScreen.x -= 50;
                        locationOnScreen.y -= 10;
                        detachTab(dragState.tabIndex, locationOnScreen);
                        dragState.isDragging = false;
                        dragState.startPoint = null;
                        dragState.tabIndex = -1;
                        targetIndex = -1;
                    } else if (tabReorderingEnabled) {
                        targetIndex = indexAtLocation(e.getX(), e.getY());
                        repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isDragFunctionalityEnabled()) {
                    return;
                }
                if (tabReorderingEnabled && dragState.isDragging && dragState.tabIndex >= 0 &&
                        targetIndex >= 0 && targetIndex != dragState.tabIndex) {
                    moveTab(dragState.tabIndex, targetIndex);
                }
                dragState.isDragging = false;
                dragState.startPoint = null;
                hideGhostImage();
                dragState.tabIndex = -1;
                targetIndex = -1;
            }
        };
    }

    /**
     * Calculate the bounds of just the tab header area
     * 
     * @return Rectangle representing the tab header area
     */
    private Rectangle getTabAreaBounds() {
        int tabCount = getTabCount();
        if (tabCount == 0) {
            return new Rectangle(0, 0, 0, 0);
        }

        int tabPlacement = getTabPlacement();
        Rectangle rect = getBoundsAt(0);

        Rectangle lastRect = getBoundsAt(tabCount - 1);

        // Calculate the area containing all tabs
        if (tabPlacement == JTabbedPane.TOP || tabPlacement == JTabbedPane.BOTTOM) {
            rect.width = lastRect.x + lastRect.width - rect.x;
        } else {
            rect.height = lastRect.y + lastRect.height - rect.y;
        }

        // Add a small margin around the tab area
        int margin = 2;
        rect.x -= margin;
        rect.y -= margin;
        rect.width += margin * 2;
        rect.height += margin * 2;

        return rect;
    }

    /**
     * Shows a ghost image of the tab being dragged
     * 
     * @param tabIndex The index of the tab being dragged
     * @param location The current mouse location
     */
    private void showGhostImage(int tabIndex, Point location) {
        if (dragState.showingGhost || tabIndex < 0 || tabIndex >= getTabCount()) {
            return;
        }

        if (ghostWindow == null) {
            Window parent = SwingUtilities.getWindowAncestor(this);
            ghostWindow = new JWindow(parent);
            ghostWindow.setOpacity(GHOST_OPACITY);
        }

        // Get tab bounds for sizing
        Rectangle tabBounds = getBoundsAt(tabIndex);
        if (tabBounds == null) {
            return;
        }

        // Calculate offset between mouse position and tab origin
        Point tabLocation = getLocationOnScreen();
        tabLocation.x += tabBounds.x;
        tabLocation.y += tabBounds.y;

        // Store the offset between the mouse location and the tab origin
        dragState.dragOffset = new Point(
                location.x - tabLocation.x,
                location.y - tabLocation.y);

        JPanel ghostPanel = new JPanel(new BorderLayout());

        // Get the tab component (our custom EnhancedTab or default tab)
        Component tabComponent = getTabComponentAt(tabIndex);
        String title = getTitleAt(tabIndex);
        Icon icon = getIconAt(tabIndex);
        boolean isSelected = (getSelectedIndex() == tabIndex);

        // If we have a custom tab component, try to match its appearance
        if (tabComponent instanceof EnhancedTab) {
            EnhancedTab enhancedTab = (EnhancedTab) tabComponent;
            title = enhancedTab.getTitle(); // Get the title from the enhanced tab

            JLabel titleLabel = new JLabel(title);
            titleLabel.setIcon(icon);
            titleLabel.setHorizontalTextPosition(JLabel.RIGHT);

            // Match component foreground
            titleLabel.setForeground(tabComponent.getForeground());
            titleLabel.setFont(tabComponent.getFont());

            ghostPanel.setOpaque(true);
            ghostPanel.setBackground(getBackground());
            ghostPanel.add(titleLabel, BorderLayout.WEST);
        }
        // We have a standard tab
        else {
            JLabel tabLabel = new JLabel(title);
            tabLabel.setIcon(icon);
            tabLabel.setHorizontalTextPosition(JLabel.RIGHT);

            // Look for tab colors based on current L&F
            Color background = null;
            Color foreground = null;

            // Try to get colors based on selection state and current L&F
            if (isSelected) {
                foreground = UIManager.getColor("TabbedPane.selectedForeground");
                background = UIManager.getColor("TabbedPane.selectedBackground");
            }

            // Fallback if selected colors aren't defined
            if (foreground == null) {
                foreground = UIManager.getColor("TabbedPane.foreground");
            }
            if (background == null) {
                background = UIManager.getColor("TabbedPane.background");
            }

            // Use component colors as final fallback
            if (foreground == null)
                foreground = getForeground();
            if (background == null)
                background = getBackground();

            tabLabel.setForeground(foreground);
            ghostPanel.setBackground(background);
            tabLabel.setFont(getFont());

            ghostPanel.add(tabLabel, BorderLayout.CENTER);
        }

        ghostPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        // Size the ghost panel to match the tab's size
        ghostPanel.setPreferredSize(new Dimension(tabBounds.width, tabBounds.height));

        ghostWindow.getContentPane().removeAll();
        ghostWindow.getContentPane().add(ghostPanel);
        ghostWindow.pack();

        updateGhostLocation(location);
        ghostWindow.setVisible(true);
        dragState.showingGhost = true;
    }

    /**
     * Updates the position of the ghost image
     * 
     * @param location The current mouse location
     */
    private void updateGhostLocation(Point location) {
        if (ghostWindow == null || !ghostWindow.isVisible() || location == null || dragState.dragOffset == null) {
            return;
        }
        // Position the ghost window so that it appears to be grabbed at the same
        // place where the user initially clicked
        try {
            ghostWindow.setLocation(
                    location.x - dragState.dragOffset.x,
                    location.y - dragState.dragOffset.y);

        } catch (IllegalComponentStateException e) {
            hideGhostImage();
        }
    }

    /**
     * Hides the ghost image
     */
    private void hideGhostImage() {
        if (ghostWindow != null) {
            ghostWindow.setVisible(false);
        }
        dragState.showingGhost = false;
    }

    /**
     * Checks if there are any detached tabs
     *
     * @return true if there are detached tabs, false otherwise
     */
    public boolean hasDetachedTabs() {
        return !detachedTabs.isEmpty();
    }

    /**
     * Updates the title of a detached tab window containing the given editor
     * 
     * @param frame The editor to find in detached windows
     * @param title The new title to set
     * @return true if a detached window was found and updated, false otherwise
     */
    public boolean setDetachedTabTitle(JFrame frame, String title) {
        for (Component component : detachedTabs.keySet()) {
            if (component instanceof JFrame detachedFrame) {
                DetachedTabInfo info = detachedTabs.get(detachedFrame);
                if (info.component == frame) {
                    detachedFrame.setTitle(title);
                    info.title = title;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets the title prefix for all the detached tab windows
     * 
     * @param prefix The prefix to set
     */
    public void setDetachedTabsPrefixTitle(String prefix) {
        for (Component component : detachedTabs.keySet()) {
            setDetachedTabPrefixTitle(detachedTabs.get(component), prefix);
        }
    }

    /**
     * Sets the title prefix for a detached tab window
     * 
     * @param detachedTab The detached tab info
     * @param prefix      The prefix to set
     */
    public void setDetachedTabPrefixTitle(DetachedTabInfo detachedTab, String prefix) {
        if (detachedTab.wrapperComponent instanceof JFrame frame) {
            frame.setTitle(prefix + " - " + detachedTab.title);
        } else if (detachedTab.wrapperComponent instanceof JDialog dialog) {
            dialog.setTitle(prefix + " - " + detachedTab.title);
        }
    }

    /**
     * Gets the actual visible content area size for a tab
     * 
     * @param tabIndex The tab index
     * @return Dimension representing the actual content area size
     */
    private Dimension getTabContentSize(int tabIndex) {
        if (tabIndex < 0 || tabIndex >= getTabCount()) {
            return new Dimension(400, 300); // Default fallback size
        }

        Rectangle contentRect = getBounds();
        Insets insets = getInsets();

        int width = contentRect.width - insets.left - insets.right;
        int height = contentRect.height - insets.top - insets.bottom;

        return new Dimension(width, height);
    }

    /**
     * Moves a tab from one position to another
     * 
     * @param fromIndex The index to move from
     * @param toIndex   The index to move to
     */
    private void moveTab(int fromIndex, int toIndex) {
        Component component = getComponentAt(fromIndex);
        String title = getTitleAt(fromIndex);
        Icon icon = getIconAt(fromIndex);
        String tooltip = getToolTipTextAt(fromIndex);
        boolean isEnabled = isEnabledAt(fromIndex);

        // Get the tab component (our custom EnhancedTab) if it exists
        Component tabComponent = getTabComponentAt(fromIndex);

        // Remove the tab
        remove(fromIndex);

        // Insert it at the new position
        insertTab(title, icon, component, tooltip, toIndex);
        setEnabledAt(toIndex, isEnabled);

        // Set the tab component back
        if (tabComponent != null) {
            setTabComponentAt(toIndex, tabComponent);
        }

        setSelectedIndex(toIndex);
    }

    public int addEnhancedTab(String title, Icon icon, Component component,
            BiConsumer<Component, InputEvent> closeTabAction) {
        return addEnhancedTab(title, icon, component, closeTabAction, getTabCount());
    }

    /**
     * Adds a closeable tab to this tabbed pane
     * 
     * @param title          The title of the tab
     * @param component      The component to display in the tab
     * @param closeTabAction Action to perform when the close button is clicked
     * @param tabIndex       The index at which to add the tab
     * @return The index of the newly added tab
     */
    public int addEnhancedTab(String title, Icon icon, Component component,
            BiConsumer<Component, InputEvent> closeTabAction, int tabIndex) {
        Component contentToAdd = null;
        if (component instanceof JFrame) {
            contentToAdd = ((JFrame) component).getContentPane();
        } else {
            contentToAdd = component;
        }
        int actualIndex = Math.min(tabIndex, getTabCount());
        insertTab(title, icon, contentToAdd, null, actualIndex);
        setTabComponentAt(actualIndex, new EnhancedTab(this, title, component, closeTabAction));
        return actualIndex;
    }

    /**
     * Window listener to handle reattaching detached tabs
     */
    private final WindowAdapter componentReattachmentListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            if (e.getSource() instanceof Component component) {
                reattachTab(component);
            }
        }
    };

    /**
     * Detaches a tab from the pane and creates a floating window
     * 
     * @param tab      An EnhancedTab instance representing the tab to detach
     * @param location The screen location where to position the new window
     */
    public void detachTab(EnhancedTab tab, Point location) {
        int tabIndex = tab.findTabIndex();
        if (tabIndex < 0) {
            return;
        }
        detachTab(tabIndex, location);
    }

    /**
     * Detaches a tab from the pane into a floating window
     * 
     * @param tabIndex The index of the tab to detach
     * @param location The screen location for the detached window
     */
    public void detachTab(int tabIndex, Point location) {
        // Validate parameters
        if (tabIndex < 0 || tabIndex >= getTabCount()) {
            return;
        }

        // Extract tab information once
        String title = getTitleAt(tabIndex);
        Icon icon = getIconAt(tabIndex);
        Component component = getComponentAt(tabIndex);
        Component componentWindow = null;
        Dimension compSize = ensureValidSize(getTabContentSize(tabIndex), 400, 300);
        Component tabComponent = getTabComponentAt(tabIndex);
        BiConsumer<Component, InputEvent> closeAction = null;
        if (tabComponent instanceof EnhancedTab enhancedTab) {
            title = enhancedTab.getTitle();
            closeAction = enhancedTab.getCloseAction();
            componentWindow = enhancedTab.component;
        }

        // Notify listeners before detaching
        fireTabDetaching(tabIndex, title, component);

        // Remove the tab from the pane before creating windows to avoid focus issues
        remove(tabIndex);

        // Create the detached window based on component type
        Window detachedWindow = createDetachedWindow(title, icon, componentWindow, component, compSize, location,
                tabComponent, closeAction, tabIndex);

        // Notify listeners after detaching
        DetachedTabInfo tabInfo = detachedTabs.get(detachedWindow);
        fireTabDetached(detachedWindow, tabInfo);
    }

    /**
     * Removes any existing listener and adds a fresh one
     */
    private void cleanupAndAddWindowListener(Window window) {
        // Remove any existing reattachment listeners
        for (WindowListener listener : window.getWindowListeners()) {
            if (listener == componentReattachmentListener) {
                window.removeWindowListener(listener);
            }
        }
        // Add the reattachment listener
        window.addWindowListener(componentReattachmentListener);
    }

    /**
     * Ensures window dimensions are valid
     */
    private Dimension ensureValidSize(Dimension size, int defaultWidth, int defaultHeight) {
        int width = (size.width <= 0) ? defaultWidth : size.width;
        int height = (size.height <= 0) ? defaultHeight : size.height;
        return new Dimension(width, height);
    }

    /**
     * Creates an appropriate window type for the detached tab
     */
    private Window createDetachedWindow(String title, Icon icon, Component window, Component component,
            Dimension size, Point location, Component tabComponent, BiConsumer<Component, InputEvent> closeAction,
            int tabIndex) {

        final boolean isEnhancedTab = tabComponent instanceof EnhancedTab;
        Window result;
        Component detachedComponent;
        // Determine window type based on component structure
        if ((window instanceof JFrame)) {
            JFrame frame = (JFrame) window;
            detachedComponent = frame;
            setupFrame(frame, title, icon, component, size, location);
            result = frame;
        } else {
            Window parent = SwingUtilities.getWindowAncestor(this);
            JDialog newWrapperDialog = new JDialog(parent, title);
            detachedComponent = component;
            setupDialog(newWrapperDialog, title, icon, component, size, location);
            result = newWrapperDialog;
        }
        // Store the detached tab information for later reattachment
        detachedTabs.put(result,
                new DetachedTabInfo(title, icon, detachedComponent, result, tabIndex, closeAction, isEnhancedTab));

        // Ensure listeners are properly managed - remove first then add
        cleanupAndAddWindowListener(result);
        result.setVisible(true);
        return result;
    }

    /**
     * Configures a JFrame for detached tab display
     */
    private void setupFrame(JFrame frame, String title, Icon icon, Component component, Dimension size,
            Point location) {
        frame.setContentPane((Container) component);
        if (icon instanceof ImageIcon imageIcon) {
            frame.setIconImage(imageIcon.getImage());
        }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(size);
        frame.setResizable(true);
        // frame.setTitle(title);
        frame.setLocation(location);
    }

    /**
     * Configures a JDialog for detached tab display
     */
    private void setupDialog(JDialog dialog, String title, Icon icon, Component component, Dimension size,
            Point location) {
        if (icon instanceof ImageIcon imageIcon) {
            dialog.setIconImage(imageIcon.getImage());
        }
        dialog.getContentPane().add(component);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(size);
        dialog.setResizable(true);
        dialog.setLocation(location);
    }

    /**
     * Reattaches a floating tab to the tabbed pane
     * 
     * @param component The floating window to reattach
     */
    private void reattachTab(Component component) {
        if (!detachedTabs.containsKey(component)) {
            return;
        }
        DetachedTabInfo tabInfo = detachedTabs.get(component);
        // Notify listeners that reattachment is starting
        fireTabReattaching(tabInfo);

        if (component instanceof JFrame frame) {
            frame.removeWindowListener(componentReattachmentListener);
        } else if (component instanceof JDialog dialog) {
            dialog.removeWindowListener(componentReattachmentListener);
        }
        int insertIndex = Math.min(tabInfo.originalIndex, getTabCount());
        if (tabInfo.component != null) {
            if (component instanceof JDialog dialog) {
                dialog.getContentPane().remove(tabInfo.component);
            }
            if (tabInfo.isEnhancedTab) {
                addEnhancedTab(tabInfo.title, tabInfo.icon, tabInfo.component, tabInfo.closeAction, insertIndex);
            } else {
                insertTab(tabInfo.title, tabInfo.icon, tabInfo.component, null, insertIndex);
            }
            setSelectedIndex(insertIndex);

            // Notify listeners that reattachment is complete
            fireTabReattached(insertIndex, tabInfo.title, tabInfo.component);
        }
        detachedTabs.remove(component);
        if (component instanceof JFrame frame) {
            frame.dispose();
        } else if (component instanceof JDialog dialog) {
            dialog.dispose();
        }
    }

    /**
     * Programmatically reattaches all detached tabs
     */
    public void reattachAllTabs() {
        for (Component component : detachedTabs.keySet()) {
            reattachTab(component);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        SwingUtilities.invokeLater(() -> {
            positionActionButtons();
            revalidate();
            repaint();
        });
    }

    /**
     * Override insertTab to ensure button positioning is updated
     */
    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index);
        deferredPositionActionButtons();
    }

    /**
     * Override remove to ensure button positioning is updated
     */
    @Override
    public void remove(int index) {
        super.remove(index);
        deferredPositionActionButtons();
    }

    @Override
    public void removeAll() {
        reattachAllTabs();
        super.removeAll();
        deferredPositionActionButtons();
    }

    @Override
    public void setTabLayoutPolicy(int tabLayoutPolicy) {
        super.setTabLayoutPolicy(tabLayoutPolicy);
        // If we're using scroll tabs, make sure action buttons are properly positioned
        if (tabLayoutPolicy == SCROLL_TAB_LAYOUT) {
            deferredPositionActionButtons();
        }
    }

    @Override
    public void removeNotify() {
        hideGhostImage();
        super.removeNotify();
    }

    public void dispose() {
        if (ghostWindow != null) {
            ghostWindow.dispose();
            ghostWindow = null;
        }
        for (Component component : detachedTabs.keySet()) {
            if (component instanceof JFrame frame) {
                frame.dispose();
            } else if (component instanceof JDialog dialog) {
                dialog.dispose();
            }
        }
        detachedTabs.clear();
    }

    /**
     * Custom component to represent a tab with a title and a close button
     */
    public static class EnhancedTab extends JPanel {
        private final EnhancedTabbedPane parentPane;
        private final Component component;
        private JLabel titleLabel;
        private BiConsumer<Component, InputEvent> closeAction;

        public EnhancedTab(EnhancedTabbedPane parentPane, String title, Component component) {
            this(parentPane, title, component, null);
        }

        /**
         * Creates a new closeable tab with the specified title
         * 
         * @param title       The title to display
         * @param component   The component associated with this tab
         * @param closeAction Action to perform when the close button is clicked
         */
        public EnhancedTab(EnhancedTabbedPane parentPane, String title, Component component,
                BiConsumer<Component, InputEvent> closeAction) {
            super(new BorderLayout(0, 0));
            this.parentPane = parentPane;
            this.component = component;
            this.closeAction = closeAction;
            setOpaque(false);

            // Create the title label
            titleLabel = new JLabel(title);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));

            // Add components to panel
            add(titleLabel, BorderLayout.WEST);
            if (closeAction != null) {
                JButton closeButton = createCloseButton();
                add(closeButton, BorderLayout.EAST);
            }

            MouseAdapter tabEventForwarder = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    forwardEventToTabbedPane(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    forwardEventToTabbedPane(e);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    forwardEventToTabbedPane(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    forwardEventToTabbedPane(e);
                }

                /**
                 * Forwards a mouse event to the parent tabbed pane
                 */
                private void forwardEventToTabbedPane(MouseEvent e) {
                    // Find the parent tabbed pane
                    Container parent = EnhancedTab.this.getParent();
                    while (parent != null && !(parent instanceof JTabbedPane)) {
                        parent = parent.getParent();
                    }

                    if (parent instanceof JTabbedPane) {
                        JTabbedPane tabbedPane = (JTabbedPane) parent;

                        // Convert to tabbed pane coordinates
                        Point tabPanePoint = SwingUtilities.convertPoint(
                                e.getComponent(), e.getPoint(), tabbedPane);

                        // Create a new event at the tabbed pane location
                        MouseEvent convertedEvent = new MouseEvent(
                                tabbedPane,
                                e.getID(),
                                e.getWhen(),
                                e.getModifiersEx(),
                                tabPanePoint.x,
                                tabPanePoint.y,
                                e.getClickCount(),
                                e.isPopupTrigger(),
                                e.getButton());

                        tabbedPane.dispatchEvent(convertedEvent);
                    }
                }
            };
            titleLabel.addMouseListener(tabEventForwarder);
            titleLabel.addMouseMotionListener(tabEventForwarder);
        }

        private JButton createCloseButton() {
            JButton closeButton = new JButton("×");
            closeButton.setFont(closeButton.getFont().deriveFont(Font.BOLD, 16f));
            closeButton.setPreferredSize(new Dimension(14, 16));
            closeButton.setToolTipText("Close this tab (Shift+click to skip save confirmation)");
            closeButton.setContentAreaFilled(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder(-2, 0, 0, -2));
            closeButton.setBorderPainted(false);
            closeButton.setFocusable(false);

            final Color originalColor = closeButton.getForeground();
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    closeButton.setForeground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    closeButton.setForeground(originalColor);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1 && closeAction != null) {
                        if (component instanceof JFrame frame) {
                            Component tabContent = frame.getContentPane();
                            closeAction.accept(tabContent, e);
                        } else {
                            closeAction.accept(component, e);
                        }
                        e.consume();
                    }
                }
            });

            return closeButton;
        }

        /**
         * Finds the index of this tab in the parent pane
         */
        private int findTabIndex() {
            // Find this tab's index in the parent pane
            for (int i = 0; i < parentPane.getTabCount(); i++) {
                if (parentPane.getTabComponentAt(i) == this) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Gets the close action for this tab
         */
        public BiConsumer<Component, InputEvent> getCloseAction() {
            return closeAction;
        }

        /**
         * Sets the title of this tab
         * 
         * @param title The new title to set
         */
        public void setTitle(String title) {
            titleLabel.setText(title);
            if (parentPane != null) {
                parentPane.positionActionButtons();
            }
        }

        /**
         * Gets the title of this tab
         */
        public String getTitle() {
            return titleLabel.getText();
        }

        /**
         * Gets the component associated with this tab
         */
        public Component getComponent() {
            return component;
        }
    }

}