package megameklab.ui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 *  author:  Drake
 *  A JTabbedPane that allows tabs to be dragged out of the pane and into a floating window.
 */
public class DraggableTabbedPane extends JTabbedPane {

    private boolean isDragging = false;
    private int draggedTabIndex = -1;
    private Point dragStartPoint = null;
    private JWindow ghostWindow;
    private boolean showingGhost = false;

    private static class DetachedTabInfo {
        String title;
        Icon icon;
        Component component;
        int originalIndex;

        public DetachedTabInfo(String title, Icon icon, Component component, int originalIndex, JDialog frame) {
            this.title = title;
            this.icon = icon;
            this.component = component;
            this.originalIndex = originalIndex;
        }
    }

    private HashMap<JDialog, DetachedTabInfo> detachedTabs = new HashMap<>();

    public DraggableTabbedPane() {
        super();
        initListeners();
    }

    public DraggableTabbedPane(int tabPlacement) {
        super(tabPlacement);
        initListeners();
    }

    public DraggableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
        initListeners();
    }

    /**
     * Initialize mouse listeners for tab drag detection
     */
    private void initListeners() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedTabIndex = indexAtLocation(e.getX(), e.getY());
                if (draggedTabIndex >= 0) {
                    dragStartPoint = e.getPoint();
                    isDragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                dragStartPoint = null;
                hideGhostImage();
                draggedTabIndex = -1;
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
                        detachTab(draggedTabIndex, e.getLocationOnScreen());
                        isDragging = false;
                        dragStartPoint = null;
                        draggedTabIndex = -1;
                    }
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
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
                    location.y + 5  // Slight offset down
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
     * Detaches a tab from the pane and creates a floating window
     * 
     * @param tabIndex The index of the tab to detach
     * @param location The screen location where the drag ended
     */
    private void detachTab(int tabIndex, Point location) {
        if (tabIndex < 0 || tabIndex >= getTabCount()) {
            return;
        }

        String title = getTitleAt(tabIndex);
        Icon icon = getIconAt(tabIndex);
        Component component = getComponentAt(tabIndex);

        Dimension compSize = component.getSize();

        remove(tabIndex);

        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog frame = new JDialog(parent, title);

        if (icon instanceof ImageIcon) {
            frame.setIconImage(((ImageIcon) icon).getImage());
        }

        frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().add(component);
        frame.setSize(compSize);

        int titleBarHeight = 25;
        Point adjustedLocation = new Point(
                location.x - 25, // offset positions cursor over title area
                location.y - (titleBarHeight / 2));
        frame.setLocation(adjustedLocation);

        DetachedTabInfo tabInfo = new DetachedTabInfo(title, icon, component, tabIndex, frame);
        detachedTabs.put(frame, tabInfo);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDialog frame = (JDialog) e.getSource();
                reattachTab(frame);
            }
        });

        frame.setVisible(true);
    }

    /**
     * Reattaches a floating tab to the tabbed pane
     * 
     * @param frame The floating window to reattach
     */
    private void reattachTab(JDialog frame) {
        if (!detachedTabs.containsKey(frame)) {
            return;
        }

        DetachedTabInfo tabInfo = detachedTabs.get(frame);

        frame.getContentPane().remove(tabInfo.component);

        int insertIndex = Math.min(tabInfo.originalIndex, getTabCount());

        insertTab(tabInfo.title, tabInfo.icon, tabInfo.component, null, insertIndex);

        setSelectedIndex(insertIndex);
        detachedTabs.remove(frame);

        frame.dispose();
    }

    /**
     * Programmatically reattaches all detached tabs
     */
    public void reattachAllTabs() {
        JDialog[] frames = detachedTabs.keySet().toArray(new JDialog[0]);
        for (JDialog frame : frames) {
            reattachTab(frame);
        }
    }
}