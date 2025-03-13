package megameklab.ui.util;

import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.MegaMekLabTabbedUI;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author: Drake
 *
 *          Enhanced tabbed pane with closable, draggable tabs and additional
 *          action
 *          buttons.
 *          This component extends JTabbedPane to add the following features:
 *          - Closable tabs with an X button on each tab
 *          - Ability to drag and reorder tabs
 *          - Action buttons at the right side of the tab area
 *          - Detachable tabs that can be dragged out (or right-click) into
 *          floating
 *          windows
 */
public class EnhancedTabbedPane extends JTabbedPane {

    // Track detached tabs
    public static class DetachedTabInfo {
        String title;
        Icon icon;
        int originalIndex;
        MegaMekLabMainUI mainUI;
        BiConsumer<Component, InputEvent> closeAction;

        public DetachedTabInfo(String title, Icon icon, MegaMekLabMainUI mainUI,
                int originalIndex, BiConsumer<Component, InputEvent> closeAction) {
            this.title = title;
            this.icon = icon;
            this.mainUI = mainUI;
            this.originalIndex = originalIndex;
            this.closeAction = closeAction;
        }

        public MegaMekLabMainUI getMainUI() {
            return mainUI;
        }
    }

    private HashMap<MegaMekLabMainUI, DetachedTabInfo> detachedTabs = new HashMap<>();

    // Button panel that sits outside the regular tabs
    private final JPanel actionButtonsPanel;

    // Drag and drop support fields
    private int draggedTabIndex = -1;
    private boolean isDragging = false;
    private Point dragStartPoint = null;
    private JWindow ghostWindow;
    private boolean showingGhost = false;

    /**
     * Creates a new EnhancedTabbedPane with closable, draggable tabs and action
     * buttons.
     * 
     * @param actionButtons Action Buttons
     */
    public EnhancedTabbedPane(List<JButton> actionButtons) {
        super();
        // Create action buttons panel with New and Open buttons
        actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        actionButtonsPanel.setOpaque(false);

        // Add buttons to panel
        for (JButton button : actionButtons) {
            addHoverEffect(button);
            actionButtonsPanel.add(button);
        }

        // Initialize drag and drop capabilities
        initDragAndDrop();

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

    public interface TabReattachmentListener {
        void onTabReattaching(DetachedTabInfo tabInfo);
    }

    private List<TabReattachmentListener> reattachmentListeners = new ArrayList<>();

    /**
     * Adds a listener that will be notified when a tab is reattached to the pane
     * 
     * @param listener The listener to add
     */
    public void addTabReattachmentListener(TabReattachmentListener listener) {
        reattachmentListeners.add(listener);
    }

    /**
     * Notifies all registered listeners that a tab has been reattached
     * 
     * @param tabInfo The tabInfo that was reattached
     */
    protected void fireTabReattaching(DetachedTabInfo tabInfo) {
        for (TabReattachmentListener listener : reattachmentListeners) {
            listener.onTabReattaching(tabInfo);
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
        // Remove the action buttons panel from any previous parent
        Container oldParent = actionButtonsPanel.getParent();
        if (oldParent != null) {
            oldParent.remove(actionButtonsPanel);
        }

        // First try to add to parent layered pane if available
        Container parent = getParent();
        if (parent instanceof JLayeredPane) {
            JLayeredPane layeredPane = (JLayeredPane) parent;
            layeredPane.add(actionButtonsPanel, JLayeredPane.PALETTE_LAYER);
            layeredPane.setComponentZOrder(actionButtonsPanel, 0);
            positionActionButtons();
        } else if (parent != null) {
            // Otherwise find the root pane and use its layered pane
            JRootPane rootPane = SwingUtilities.getRootPane(this);
            if (rootPane != null) {
                JLayeredPane layeredPane = rootPane.getLayeredPane();
                layeredPane.add(actionButtonsPanel, JLayeredPane.PALETTE_LAYER);
                positionActionButtons();
            }
        }

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(actionButtonsPanel.getWidth() + 5, 1));
        putClientProperty("JTabbedPane.trailingComponent", spacer);
    }

    /**
     * Positions the action buttons at the right side of the tab area
     */
    private void positionActionButtons() {
        // Check if the tabbed pane is showing and has a parent
        if (!isShowing() || getParent() == null) {
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

                if (getTabLayoutPolicy() == SCROLL_TAB_LAYOUT && getTabCount() > 1) {
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

    /**
     * Initialize drag and drop functionality for tab reordering
     */
    private void initDragAndDrop() {
        MouseInputAdapter dragHandler = new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedTabIndex = indexAtLocation(e.getX(), e.getY());
                if (draggedTabIndex >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        dragStartPoint = e.getPoint();
                        isDragging = true;
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        Point locationOnScreen = e.getLocationOnScreen();
                        locationOnScreen.x -= 50;
                        locationOnScreen.y -= 10;
                        detachTab(draggedTabIndex, locationOnScreen);
                        draggedTabIndex = -1;
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && draggedTabIndex >= 0 && dragStartPoint != null) {
                    int deltaX = Math.abs(e.getX() - dragStartPoint.x);
                    int deltaY = Math.abs(e.getY() - dragStartPoint.y);

                    if ((deltaX > 5 || deltaY > 5) && !showingGhost) {
                        showGhostImage(draggedTabIndex, e.getLocationOnScreen());
                    }

                    if (showingGhost) {
                        updateGhostLocation(e.getLocationOnScreen());
                    }
                    Rectangle bounds = getTabAreaBounds();
                    if (!bounds.contains(e.getPoint())) {
                        // Mouse is outside the tabbed pane, detach the tab
                        hideGhostImage();
                        Point locationOnScreen = e.getLocationOnScreen();
                        locationOnScreen.x -= 50;
                        locationOnScreen.y -= 10;
                        detachTab(draggedTabIndex, locationOnScreen);
                        isDragging = false;
                        dragStartPoint = null;
                        draggedTabIndex = -1;
                    }
                    int targetIndex = indexAtLocation(e.getX(), e.getY());
                    if (targetIndex >= 0 && targetIndex != draggedTabIndex) {
                        // Reorder the tabs
                        moveTab(draggedTabIndex, targetIndex);
                        draggedTabIndex = targetIndex;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                dragStartPoint = null;
                hideGhostImage();
                draggedTabIndex = -1;
            }
        };

        addMouseListener(dragHandler);
        addMouseMotionListener(dragHandler);
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
        if (showingGhost || tabIndex < 0 || tabIndex >= getTabCount()) {
            return;
        }

        if (ghostWindow == null) {
            Window parent = SwingUtilities.getWindowAncestor(this);
            ghostWindow = new JWindow(parent);
            ghostWindow.setOpacity(0.7f); // Semi-transparent
        }

        String title = getTitleAt(tabIndex);
        Icon icon = getIconAt(tabIndex);

        JPanel ghostPanel = new JPanel(new BorderLayout());

        JLabel tabLabel = new JLabel(title);
        tabLabel.setIcon(icon);
        tabLabel.setHorizontalTextPosition(JLabel.RIGHT);

        ghostPanel.setBackground(new Color(230, 230, 230));
        ghostPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        ghostPanel.add(tabLabel, BorderLayout.CENTER);

        ghostWindow.getContentPane().removeAll();
        ghostWindow.getContentPane().add(ghostPanel);
        ghostWindow.pack();

        updateGhostLocation(location);

        ghostWindow.setVisible(true);
        showingGhost = true;
    }

    /**
     * Updates the position of the ghost image
     * 
     * @param location The current mouse location
     */
    private void updateGhostLocation(Point location) {
        if (ghostWindow != null && ghostWindow.isVisible()) {
            ghostWindow.setLocation(
                    location.x + 5, // Slight offset to the right
                    location.y + 5 // Slight offset down
            );
        }
    }

    /**
     * Hides the ghost image
     */
    private void hideGhostImage() {
        if (ghostWindow != null) {
            ghostWindow.setVisible(false);
        }
        showingGhost = false;
    }

    /**
     * Updates the title of a detached tab window containing the given editor
     * 
     * @param mainUI The editor to find in detached windows
     * @param title  The new title to set
     * @return true if a detached window was found and updated, false otherwise
     */
    public boolean setDetachedTabTitle(MegaMekLabMainUI mainUI, String title) {
        for (MegaMekLabMainUI mmMainUI : detachedTabs.keySet()) {
            DetachedTabInfo info = detachedTabs.get(mmMainUI);
            if (info.mainUI == mainUI) {
                mmMainUI.setTitle(title);
                info.title = title;
                return true;
            }
        }
        return false;
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

        // Get the tab component (our custom CloseableTab) if it exists
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

    /**
     * Adds a closeable tab to this tabbed pane
     * 
     * @param title          The title of the tab
     * @param component      The component to display in the tab
     * @param closeTabAction Action to perform when the close button is clicked
     * @return The index of the newly added tab
     */
    public int addCloseableTab(String title, Icon icon, MegaMekLabMainUI mainUI,
            BiConsumer<Component, InputEvent> closeTabAction) {
        Component contentToAdd = mainUI.getContentPane();
        addTab(title, icon, contentToAdd);
        int index = getTabCount() - 1;
        setTabComponentAt(index, new CloseableTab(this, title, mainUI, closeTabAction));
        return index;
    }

    /**
     * Detaches a tab from the pane and creates a floating window
     * 
     * @param tabIndex The index of the tab to detach
     * @param location The screen location where to position the new window
     */
    private void detachTab(int tabIndex, Point location) {
        if (tabIndex < 0 || tabIndex >= getTabCount()) {
            return;
        }

        String title = getTitleAt(tabIndex);
        Icon icon = getIconAt(tabIndex);
        Component component = getComponentAt(tabIndex);

        // Determine the editor from the tab component
        Component tabComponent = getTabComponentAt(tabIndex);
        MegaMekLabMainUI mmMainUI = null;
        BiConsumer<Component, InputEvent> closeAction = null;
        if (tabComponent instanceof CloseableTab) {
            CloseableTab closeableTab = (CloseableTab) tabComponent;
            mmMainUI = closeableTab.mainUI;
            closeAction = closeableTab.getCloseAction();
            title = closeableTab.getTitle();
        }

        if (mmMainUI == null) {
            return;
        }
        Dimension compSize = component.getSize();
        remove(tabIndex);
        mmMainUI.setContentPane((Container)component);
        mmMainUI.setDefaultCloseOperation(MegaMekLabMainUI.DO_NOTHING_ON_CLOSE);
        mmMainUI.setSize(compSize);
        mmMainUI.setResizable(true);
        mmMainUI.setLocation(location);
        DetachedTabInfo tabInfo = new DetachedTabInfo(title, icon, mmMainUI, tabIndex, closeAction);
        detachedTabs.put(mmMainUI, tabInfo);
        mmMainUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (e.getSource() instanceof MegaMekLabMainUI mmMainUI) {
                    reattachTab(mmMainUI);
                    mmMainUI.dispose();
                }
            }
        });
        mmMainUI.setVisible(true);
    }

    /**
     * Reattaches a floating tab to the tabbed pane
     * 
     * @param mainUI The floating window to reattach
     */
    private void reattachTab(MegaMekLabMainUI mainUI) {
        if (!detachedTabs.containsKey(mainUI)) {
            return;
        }

        DetachedTabInfo tabInfo = detachedTabs.get(mainUI);
        int insertIndex = Math.min(tabInfo.originalIndex, getTabCount());
        if (tabInfo.mainUI != null) {
            fireTabReattaching(tabInfo);
            addCloseableTab(tabInfo.title, tabInfo.icon, tabInfo.mainUI, tabInfo.closeAction);
            setSelectedIndex(insertIndex);
            detachedTabs.remove(tabInfo.mainUI);
        }
    }

    /**
     * Programmatically reattaches all detached tabs
     */
    public void reattachAllTabs() {
        MegaMekLabMainUI[] mmMainUIs = detachedTabs.keySet().toArray(new MegaMekLabMainUI[0]);
        for (MegaMekLabMainUI mainUI : mmMainUIs) {
            reattachTab(mainUI);
        }
    }

    /**
     * Override insertTab to ensure button positioning is updated
     */
    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index);
        SwingUtilities.invokeLater(this::positionActionButtons);
    }

    /**
     * Override remove to ensure button positioning is updated
     */
    @Override
    public void remove(int index) {
        super.remove(index);
        SwingUtilities.invokeLater(this::positionActionButtons);
    }

    @Override
    public void setTabLayoutPolicy(int tabLayoutPolicy) {
        super.setTabLayoutPolicy(tabLayoutPolicy);
        // If we're using scroll tabs, make sure action buttons are properly positioned
        if (tabLayoutPolicy == SCROLL_TAB_LAYOUT) {
            SwingUtilities.invokeLater(this::positionActionButtons);
        }
    }

    /**
     * Custom component to represent a tab with a title and a close button
     */
    public static class CloseableTab extends JPanel {
        private final EnhancedTabbedPane parentPane;
        private final MegaMekLabMainUI mainUI;
        private JLabel titleLabel;
        private BiConsumer<Component, InputEvent> closeAction;

        /**
         * Creates a new closeable tab with the specified title
         * 
         * @param title       The title to display
         * @param component   The component associated with this tab
         * @param closeAction Action to perform when the close button is clicked
         */
        public CloseableTab(EnhancedTabbedPane parentPane, String title, MegaMekLabMainUI mainUI,
                BiConsumer<Component, InputEvent> closeAction) {
            super(new BorderLayout(0, 0));
            this.parentPane = parentPane;
            this.mainUI = mainUI;
            this.closeAction = closeAction;
            setOpaque(false);

            // Create the title label
            titleLabel = new JLabel(title);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));

            // Create the close button
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
                    // Apply hover appearance
                    closeButton.setForeground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restore original appearance
                    closeButton.setForeground(originalColor);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() != MouseEvent.BUTTON1) {
                        return;
                    }
                    if (closeAction == null) {
                        return;
                    }
                    Component tabContent = mainUI.getContentPane();
                    closeAction.accept(tabContent, e);
                    e.consume();
                }
            });

            // Add components to panel
            add(titleLabel, BorderLayout.WEST);
            add(closeButton, BorderLayout.EAST);

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
                    Container parent = CloseableTab.this.getParent();
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

        public String getTitle() {
            return titleLabel.getText();
        }
    }

}
