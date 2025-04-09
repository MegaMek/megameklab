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

package megameklab.ui.generalUnit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.GraphicsUtil;

import megamek.common.Entity;
import megamek.logging.MMLogger;
import megameklab.printing.PaperSize;
import megameklab.printing.PrintRecordSheet;
import megameklab.printing.PrintSmallUnitSheet;
import megameklab.printing.RecordSheetOptions;
import megameklab.util.UnitPrintManager;

/**
 * @author pavelbraginskiy
 * @author drake
 *         Simply fills itself with the record sheet for the given unit.
 *         Uses background rendering for performance.
 */
public class RecordSheetPreviewPanel extends JPanel {
    private static final MMLogger logger = MMLogger.create(RecordSheetPreviewPanel.class);
    private static final int N_THREADS = Math.max(1, Runtime.getRuntime().availableProcessors() / 2);
    private static final ExecutorService renderExecutor = Executors.newFixedThreadPool(N_THREADS, new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "RecordSheetRenderer-" + threadNumber.getAndIncrement());
            t.setDaemon(true); // Allow JVM exit even if rendering threads are active
            t.setPriority(Thread.MIN_PRIORITY); // Render at lower priority
            return t;
        }
    });

    static {
        // Shutdown hook to gracefully terminate the executor
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("Shutting down RecordSheetRenderer executor...");
            renderExecutor.shutdown();
            try {
                if (!renderExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    renderExecutor.shutdownNow();
                    logger.warn("RecordSheetRenderer executor did not terminate gracefully.");
                } else {
                    logger.debug("RecordSheetRenderer executor shut down successfully.");
                }
            } catch (InterruptedException e) {
                renderExecutor.shutdownNow();
                Thread.currentThread().interrupt();
                logger.error("Interrupted while shutting down RecordSheetRenderer executor.", e);
            }
        }, "RecordSheetRenderer-ShutdownHook"));
    }

    private class RightClickListener extends MouseAdapter {
        private final JPopupMenu popup = new JPopupMenu();
        {
            var copyItem = new JMenuItem("Copy to clipboard");
            copyItem.addActionListener(l -> copyRecordSheetToClipboard());
            popup.add(copyItem);

            var resetItem = new JMenuItem("Reset view");
            resetItem.addActionListener(l -> resetView());
            popup.add(resetItem);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getButton() != MouseEvent.BUTTON3) {
                return;
            }
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    // Zoom and pan state
    public static final int MAX_PRINTABLE_ENTITIES = 10;
    private final double MIN_ZOOM = 1.0;
    private final double MAX_ZOOM = 4.0;
    private final double ZOOM_STEP = 0.2;
    private final double CLIPBOARD_ZOOM_SCALE = 4.0;
    private volatile double minZoom = calculateMinimumZoom();
    private volatile double zoomFactor = minZoom;
    private volatile double cachedImageZoomFactor = 1.0;

    private Point2D panOffset = new Point2D.Double(0, 0);
    private Point lastMousePoint;
    private boolean isPanning = false;
    private volatile boolean isHighQualityPaint = true;
    private final AffineTransform workTransform = new AffineTransform(); // Reusable transform for rendering
    private final AffineTransform tempTransform = new AffineTransform(); // Reusable transform for painting

    // Record Sheet Data & Caching
    private SoftReference<ArrayList<GraphicsNode>> gnSheets;
    private List<Entity> entities = new ArrayList<>();
    private SoftReference<BufferedImage> cachedImage; // Cache for the rendered image
    private volatile Future<?> pendingRenderTask = null; // Track background rendering
    private volatile int renderVersion = 0; // Used to discard stale render results

    // Timers for debouncing actions
    private Timer resetViewTimer;
    private Timer zoomRenderDebounceTimer;
    private static final int RESET_VIEW_DELAY = 200; // 200ms delay
    private static final int ZOOM_RENDER_DEBOUNCE_DELAY = 150; // 150ms delay before triggering render after zoom stops

    private boolean needsViewReset = false;

    public RecordSheetPreviewPanel() {
        addMouseListener(new RightClickListener());
        setupMouseHandlers();
        setDoubleBuffered(true);

        // Debounce timer for view reset
        resetViewTimer = new Timer(RESET_VIEW_DELAY, e -> {
            resetViewTimer.stop();
            performResetView();
        });
        resetViewTimer.setRepeats(false);

        // Debounce timer for zoom rendering
        zoomRenderDebounceTimer = new Timer(ZOOM_RENDER_DEBOUNCE_DELAY, e -> {
            zoomRenderDebounceTimer.stop();
            requestRender();
        });
        zoomRenderDebounceTimer.setRepeats(false);

        // Hierarchy listener for visibility changes
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (isShowing()) {
                    // Became visible
                    minZoom = calculateMinimumZoom(); // Recalculate min zoom based on current size
                    if (needsViewReset) {
                        // If a reset was pending, do it now
                        resetView(); // Will schedule the reset
                    } else if (cachedImage == null || cachedImage.get() == null) {
                        requestRender();
                    }
                } else {
                    // Became hidden - cancel any pending render
                    if (pendingRenderTask != null) {
                        pendingRenderTask.cancel(false);
                    }
                }
            }
        });

        // Component listener for resize events
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                double oldMinZoom = minZoom;
                minZoom = calculateMinimumZoom();
                if (cachedImage != null && zoomFactor <= oldMinZoom + 0.01) { // If we were at min zoom (or very close)
                    scheduleResetView(); // Auto-adjust view if we were fitted before resize
                }
            }
        });

        // Initial state
        setMinimumSize(new java.awt.Dimension(200, 200));
        performResetView();
    }

    /**
     * Calculate the minimum zoom level needed to fit the record sheet vertically
     * within the window.
     * 
     * @return The minimum zoom factor
     */
    private double calculateMinimumZoom() {
        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        double availableHeight = getHeight() > 0 ? getHeight() : 600;
        // Add some padding
        double padding = 20.0;
        double targetHeight = availableHeight - padding;
        if (targetHeight <= 0)
            return MIN_ZOOM;

        double calculatedZoom = targetHeight / pz.pxHeight;

        if (entities == null || entities.isEmpty()) {
            return Math.max(MIN_ZOOM, calculatedZoom);
        }
        int numSheets = Math.min(entities.size(), MAX_PRINTABLE_ENTITIES);
        if (numSheets > 1) {
            double availableWidth = getWidth() > 0 ? getWidth() : 800;
            double targetWidth = availableWidth - padding;
            if (targetWidth <= 0)
                return Math.max(MIN_ZOOM, calculatedZoom);

            double widthPerSheet = targetWidth / numSheets;
            double widthZoom = widthPerSheet / pz.pxWidth;
            calculatedZoom = Math.min(calculatedZoom, widthZoom);
        }

        return Math.max(MIN_ZOOM, calculatedZoom);
    }

    /**
     * Creates a complete image (synchronously) for clipboard copy.
     */
    private void copyRecordSheetToClipboard() {
        List<Entity> currentEntities = entities;
        if (currentEntities == null || currentEntities.isEmpty()) {
            return;
        }

        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        ArrayList<GraphicsNode> sheetNodes = getRecordSheetGraphicsNodes(currentEntities, options, true);

        if (sheetNodes == null || sheetNodes.isEmpty()) {
            logger.warn("Failed to generate GraphicsNodes for clipboard copy.");
            return;
        }

        int numSheets = sheetNodes.size();
        int imgWidth = (int) Math.ceil(pz.pxWidth * CLIPBOARD_ZOOM_SCALE);
        int imgHeight = (int) Math.ceil(pz.pxHeight * CLIPBOARD_ZOOM_SCALE);
        int totalWidth = imgWidth * numSheets;

        BufferedImage img = null;
        Graphics2D g = null;
        try {
            img = new BufferedImage(totalWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            g = GraphicsUtil.createGraphics(img);

            // Set highest quality rendering hints
            setupRenderingHints(g, true, true, img);

            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, totalWidth, imgHeight);

            int k = 0;
            for (GraphicsNode gn : sheetNodes) {
                if (gn == null)
                    continue;
                AffineTransform originalTransform = gn.getTransform(); // Save original
                try {
                    var bounds = gn.getBounds();
                    if (bounds == null || bounds.getWidth() <= 0 || bounds.getHeight() <= 0) {
                        logger.warn("Skipping node with invalid bounds for clipboard copy.");
                        continue; // Skip if bounds are invalid
                    }

                    // Calculate scale to fit within the allocated space per sheet
                    double padding = 20 * CLIPBOARD_ZOOM_SCALE; // Scaled padding
                    double scaleX = (imgWidth - padding) / bounds.getWidth();
                    double scaleY = (imgHeight - padding) / bounds.getHeight();
                    double scale = Math.min(scaleX, scaleY);

                    // Center within its allocated space
                    double sheetStartX = k * imgWidth;
                    double centerX = sheetStartX + (imgWidth - (bounds.getWidth() * scale)) / 2.0;
                    double centerY = (imgHeight - (bounds.getHeight() * scale)) / 2.0;

                    // Apply transform: translate to position, then scale
                    tempTransform.setToIdentity();
                    tempTransform.translate(centerX, centerY);
                    tempTransform.scale(scale, scale);
                    tempTransform.translate(-bounds.getX(), -bounds.getY());

                    gn.setTransform(tempTransform);
                    gn.paint(g);
                    k++;
                } catch (Exception ex) {
                    logger.error("Error painting node for clipboard copy", ex);
                } finally {
                    if (originalTransform != null)
                        gn.setTransform(originalTransform); // Restore original
                }
            }

            // Copy to clipboard
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new TransferableImage(img), null);
        } catch (OutOfMemoryError ex) {
            logger.error("OutOfMemoryError creating image for clipboard copy.", ex);
        } catch (Exception ex) {
            logger.error("Error copying record sheet to clipboard.", ex);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    // Helper class for Transferable Image
    private static class TransferableImage implements Transferable {
        private final BufferedImage image;

        public TransferableImage(BufferedImage image) {
            this.image = image;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { DataFlavor.imageFlavor };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return image;
        }
    }

    private void setupMouseHandlers() {
        // Zoom
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Point mousePoint = e.getPoint();
                double oldZoom = zoomFactor;

                // Calculate new zoom with slight acceleration
                double scroll = e.getPreciseWheelRotation();
                double zoomDelta = scroll * ZOOM_STEP * (1 + Math.abs(scroll) * 0.1); // Faster scroll = bigger step
                double newZoom = zoomFactor * (1 - zoomDelta); // Multiplicative zoom

                newZoom = Math.max(minZoom, Math.min(MAX_ZOOM, newZoom));

                if (Math.abs(oldZoom - newZoom) > 0.001) { // Check for actual change
                    // Adjust pan to keep mouse position fixed relative to the content
                    double zoomRatio = newZoom / oldZoom;
                    panOffset.setLocation(
                            mousePoint.getX() - (mousePoint.getX() - panOffset.getX()) * zoomRatio,
                            mousePoint.getY() - (mousePoint.getY() - panOffset.getY()) * zoomRatio);

                    zoomFactor = newZoom;
                    // Don't render immediately. Debounce.
                    zoomRenderDebounceTimer.restart();
                    // Repaint immediately for visual feedback of the zoom/pan adjustment,
                    // even if the underlying image isn't re-rendered yet.
                    isHighQualityPaint = false;
                    repaint();
                }
            }
        });

        // Pan Start/Stop and Double-Click reset
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lastMousePoint = e.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    isPanning = true;
                    isHighQualityPaint = false;
                    zoomRenderDebounceTimer.stop(); // Stop any pending render from zoom
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && isPanning) {
                    setCursor(Cursor.getDefaultCursor());
                    isPanning = false;
                    lastMousePoint = null;
                    // Schedule a high-quality repaint after panning stops
                    SwingUtilities.invokeLater(() -> {
                        repaint();
                    });
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    resetView();
                }
            }
        });

        // Panning
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isPanning && lastMousePoint != null) {
                    int dx = e.getX() - lastMousePoint.x;
                    int dy = e.getY() - lastMousePoint.y;

                    panOffset.setLocation(panOffset.getX() + dx, panOffset.getY() + dy);

                    lastMousePoint = e.getPoint();
                    repaint();
                }
            }
        });
    }

    /**
     * Schedule a view reset after a delay to avoid excessive resets during zooming
     * or panning.
     */
    private void scheduleResetView() {
        resetViewTimer.restart();
    }

    /**
     * Request a reset of the view. This will be scheduled to happen after a short
     * delay.
     */
    public void resetView() {
        scheduleResetView();
    }

    /**
     * Perform the actual reset of the view, adjusting zoom and pan to fit the
     * content.
     */
    private void performResetView() {
        needsViewReset = false;
        isHighQualityPaint = true;
        zoomFactor = calculateMinimumZoom();
        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        int numSheets = Math.min(entities.size(), MAX_PRINTABLE_ENTITIES);
        if (numSheets == 0)
            numSheets = 1;

        double contentWidth = pz.pxWidth * zoomFactor * numSheets;
        double contentHeight = pz.pxHeight * zoomFactor;

        double xOffset = (getWidth() - contentWidth) / 2.0;
        double yOffset = (getHeight() - contentHeight) / 2.0;

        panOffset.setLocation(Math.max(0, xOffset), Math.max(0, yOffset));
        requestRender();
        repaint();
    }

    /**
     * Set the entities to be displayed in the record sheet preview.
     * 
     * @param newEntities The list of entities to display.
     */
    public void setEntities(List<Entity> newEntities) {
        List<Entity> processedEntities;
        if (newEntities == null) {
            processedEntities = Collections.emptyList();
        } else {
            // Create a new list to avoid external modifications affecting us
            processedEntities = new ArrayList<>(newEntities);
        }
        // Basic check if entities actually changed
        boolean entitiesChanged = !areEntityListsEffectivelyEqual(this.entities, processedEntities);
        this.entities = processedEntities;
        if (entitiesChanged) {
            gnSheets = null;
            cachedImage = null;
            if (pendingRenderTask != null) {
                pendingRenderTask.cancel(false); // Don't interrupt, just cancel if pending
                pendingRenderTask = null;
            }
            if (isShowing()) {
                scheduleResetView(); // Reset view and trigger render when entities change
            } else {
                needsViewReset = true; // Mark for reset when shown
            }
        } else {
            // Entities are the same, but maybe their internal state changed.
            // Force a re-render but don't reset zoom/pan.
            gnSheets = null;
            // cachedImage = null; // No need to clear cached image, so we don't have a flicker refresh
            if (isShowing()) {
                requestRender();
                repaint();
            }
        }
    }

    // Helper to compare entity lists
    private boolean areEntityListsEffectivelyEqual(List<Entity> list1, List<Entity> list2) {
        if (list1 == list2)
            return true;
        if (list1 == null || list2 == null)
            return false;
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set a single entity to be displayed in the record sheet preview.
     * 
     * @param entity
     */
    public void setEntity(Entity entity) {
        if (entity == null) {
            this.setEntities(null);
        } else {
            this.setEntities(List.of(entity));
        }
    }

    // Gets or generates GraphicsNodes
    private synchronized ArrayList<GraphicsNode> getRecordSheetGraphicsNodes(List<Entity> entitiesToRender,
            RecordSheetOptions options,
            boolean forceGenerate) {
        ArrayList<GraphicsNode> nodes = (gnSheets != null && !forceGenerate) ? gnSheets.get() : null;
        if (nodes != null && !nodes.isEmpty()) {
            return nodes;
        }

        // Generation logic
        if (entitiesToRender == null || entitiesToRender.isEmpty()) {
            return new ArrayList<>(); // Return empty list, not null
        }
        List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(
                entitiesToRender.subList(0, Math.min(entitiesToRender.size(), MAX_PRINTABLE_ENTITIES)),
                false, options, true);

        ArrayList<GraphicsNode> generatedNodes = new ArrayList<>();
        PageFormat pf = new PageFormat();
        PaperSize paperDef = options.getPaperSize();

        for (PrintRecordSheet sheet : sheets) {
            if (sheet instanceof PrintSmallUnitSheet) {
                pf.setPaper(paperDef.createPaper());
            } else {
                pf.setPaper(paperDef.createPaper(5, 5, 5, 5));
            }

            // Handle multi-page sheets if necessary
            int pagesCount = sheet.getPageCount();
            for (int i = 0; i < pagesCount; i++) {
                try {
                    sheet.createDocument(i, pf, false);
                    GraphicsNode node = sheet.build();
                    if (node != null) {
                        generatedNodes.add(node);
                    } else {
                        logger.warn("Failed to build GraphicsNode for a sheet.");
                    }
                } catch (Exception e) {
                    logger.error("Error generating GraphicsNode for sheet", e);
                }
            }
        }
        gnSheets = new SoftReference<>(generatedNodes);
        return new ArrayList<>(generatedNodes); // Return a copy of the list
    }

    /**
     * Central method to request a background render.
     */
    private synchronized void requestRender() {
        final List<Entity> entitiesToRender = new ArrayList<>(this.entities); // Create snapshot copy
        final double currentZoomFactor = this.zoomFactor;
        final int currentRenderVersion = ++this.renderVersion;

        // Cancel any previously submitted render task that hasn't completed
        if (pendingRenderTask != null) {
            pendingRenderTask.cancel(false); // false = don't interrupt if running, just cancel if pending
            pendingRenderTask = null;
        }

        // If no entities, clear cache and repaint immediately
        if (entitiesToRender == null || entitiesToRender.isEmpty()) {
            cachedImage = null;
            cachedImageZoomFactor = -1;
            gnSheets = null;
            pendingRenderTask = null; // No task needed
            if (SwingUtilities.isEventDispatchThread()) {
                repaint();
            } else {
                SwingUtilities.invokeLater(this::repaint);
            }
            return;
        }

        // Create the rendering task
        Callable<BufferedImage> renderTask = () -> {
            // --- This code runs on a background thread ---
            long startTime = System.nanoTime();
            // Check for interruption before heavy work
            if (Thread.currentThread().isInterrupted()) {
                logger.debug("Render task v" + currentRenderVersion + " interrupted before drawing.");
                return null;
            }

            RecordSheetOptions options = new RecordSheetOptions();
            final ArrayList<GraphicsNode> sheetNodesToRender = getRecordSheetGraphicsNodes(entitiesToRender, options,
                    false);

            if (sheetNodesToRender == null || sheetNodesToRender.isEmpty()) {
                logger.warn("No GraphicsNodes available to render for v" + currentRenderVersion
                        + ", skipping background task.");
                cachedImage = null;
                cachedImageZoomFactor = -1;
                pendingRenderTask = null;
                return null;
            }

            PaperSize pz = options.getPaperSize();
            int numSheets = sheetNodesToRender.size();
            int sheetWidthPx = (int) Math.ceil(pz.pxWidth * currentZoomFactor);
            int sheetHeightPx = (int) Math.ceil(pz.pxHeight * currentZoomFactor);
            int totalWidth = sheetWidthPx * numSheets;
            int totalHeight = sheetHeightPx;

            if (totalWidth <= 0 || totalHeight <= 0) {
                logger.warn("Invalid render dimensions calculated for v" + currentRenderVersion + ". W=" + totalWidth
                        + ", H=" + totalHeight);
                return null;
            }

            BufferedImage img = null;
            Graphics2D g = null;
            try {
                // Create image. Try to use hardware-accelerated image if possible
                try {
                    // Get hardware acceleration configuration
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice gs = ge.getDefaultScreenDevice();
                    GraphicsConfiguration gc = gs.getDefaultConfiguration();

                    // Create a compatible image (should work better with hardware)
                    img = gc.createCompatibleImage(totalWidth, totalHeight, java.awt.Transparency.OPAQUE);
                } catch (Exception e) {
                    // Fallback to standard image if hardware acceleration fails
                    img = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
                }
                g = GraphicsUtil.createGraphics(img);
                setupRenderingHints(g, true, false, img);
                g.setBackground(Color.WHITE);
                g.clearRect(0, 0, totalWidth, totalHeight);

                int k = 0;
                for (int i = 0; i < sheetNodesToRender.size(); i++) {
                    GraphicsNode gnSheet = sheetNodesToRender.get(i);
                    if (gnSheet == null)
                        continue;

                    // Check for interruption between sheets
                    if (Thread.currentThread().isInterrupted()) {
                        logger.debug("Render task v" + currentRenderVersion + " interrupted during drawing.");
                        return null; // Abort rendering
                    }

                    AffineTransform originalTransform = null;
                    try {
                        originalTransform = gnSheet.getTransform(); // Save original
                        var bounds = gnSheet.getBounds();
                        if (bounds == null || bounds.getWidth() <= 0 || bounds.getHeight() <= 0) {
                            logger.warn("Skipping node with invalid bounds during render v" + currentRenderVersion);
                            continue; // Skip node if bounds are invalid
                        }

                        // Calculate scale to fit, similar to paintComponent but for the cache zoom
                        // level
                        double padding = 20 * currentZoomFactor; // Scaled padding
                        double scaleX = (sheetWidthPx > padding) ? (sheetWidthPx - padding) / bounds.getWidth() : 1.0;
                        double scaleY = (sheetHeightPx > padding) ? (sheetHeightPx - padding) / bounds.getHeight() : 1.0;
                        double scale = Math.min(scaleX, scaleY);

                        // Center within its allocated space
                        double sheetStartX = k * sheetWidthPx;
                        double centerX = sheetStartX + (sheetWidthPx - (bounds.getWidth() * scale)) / 2.0;
                        double centerY = (sheetHeightPx - (bounds.getHeight() * scale)) / 2.0;

                        // Apply transform: translate to position, scale, and account for node origin
                        workTransform.setToIdentity();
                        workTransform.translate(centerX, centerY);
                        workTransform.scale(scale, scale);
                        workTransform.translate(-bounds.getX(), -bounds.getY()); // Translate node origin to 0,0 before scaling

                        gnSheet.setTransform(workTransform);
                        gnSheet.paint(g); // The expensive part
                        k++;
                    } catch (Exception ex) {
                        logger.error("Error painting node during render v" + currentRenderVersion, ex);
                    } finally {
                        // IMPORTANT: Restore original transform to node for next render cycle
                        try {
                            if (originalTransform != null)
                                gnSheet.setTransform(originalTransform);
                        } catch (Exception ex) {
                            logger.error("Error restoring original transform for node index " + i + " during render v"
                                    + currentRenderVersion, ex);
                        }
                    }
                }
            } catch (OutOfMemoryError ex) {
                logger.error("OutOfMemoryError during background rendering v" + currentRenderVersion, ex);
                if (g != null)
                    g.dispose();
                img = null;
            } catch (Exception ex) {
                logger.error("Unexpected error during background rendering v" + currentRenderVersion, ex);
                img = null;
            } finally {
                if (g != null) {
                    g.dispose();
                }
            }

            long endTime = System.nanoTime();
            logger.debug("Finished background render v" + currentRenderVersion + " in "
                    + (endTime - startTime) / 1_000_000 + " ms");
            return img;
        };

        // Submit the task to the shared executor
        pendingRenderTask = renderExecutor.submit(() -> {
            try {
                final BufferedImage resultImage = renderTask.call(); // Execute the task

                // --- Post-processing back on the EDT ---
                SwingUtilities.invokeLater(() -> {
                    // Check if this result is still valid (correct version and task hasn't been cancelled)
                    if (this.renderVersion == currentRenderVersion && pendingRenderTask != null
                            && !pendingRenderTask.isCancelled()) {
                        if (resultImage != null) {
                            cachedImage = new SoftReference<>(resultImage);
                            cachedImageZoomFactor = currentZoomFactor;
                        } else {
                            // Render failed or produced null image, clear cache
                            cachedImage = null;
                            cachedImageZoomFactor = -1;
                            logger.warn("Render v" + currentRenderVersion + " resulted in null image.");
                        }
                        pendingRenderTask = null; // Mark task as completed
                        isHighQualityPaint = true; // Ensure next paint is high quality
                        repaint(); // Update the display with the new image (or lack of)
                    } else {
                        // Stale result, discard it
                        logger.debug("Discarding stale render result v" + currentRenderVersion + " (current is v"
                                + this.renderVersion + ")");
                    }
                });
            } catch (Exception e) {
                if (!(e instanceof InterruptedException || e instanceof java.util.concurrent.CancellationException)) {
                    logger.error("Exception in background render task execution v" + currentRenderVersion, e);
                } else {
                    logger.debug("Render task v" + currentRenderVersion + " caught InterruptedException.");
                }
                // Ensure task is cleared on error, update UI on EDT
                SwingUtilities.invokeLater(() -> {
                    if (this.renderVersion == currentRenderVersion) { // Only clear if it's still the relevant task
                        pendingRenderTask = null;
                        cachedImage = null;
                        cachedImageZoomFactor = -1;
                        repaint();
                    }
                });
            }
        });
    }

    /** Helper to set rendering hints for Graphics2D */
    private void setupRenderingHints(Graphics2D g, boolean highQuality, boolean forPrinting,
            BufferedImage targetImage) {
        RenderingHints rh = new RenderingHints(null);

        // Basic Quality vs Speed
        rh.put(RenderingHints.KEY_RENDERING,
                highQuality ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED);
        rh.put(RenderingHints.KEY_COLOR_RENDERING,
                highQuality ? RenderingHints.VALUE_COLOR_RENDER_QUALITY : RenderingHints.VALUE_COLOR_RENDER_SPEED);
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, highQuality ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
                : RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);

        // Anti-aliasing
        rh.put(RenderingHints.KEY_ANTIALIASING,
                highQuality ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                highQuality ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        // Interpolation (for image scaling if any happens implicitly)
        rh.put(RenderingHints.KEY_INTERPOLATION, highQuality ? RenderingHints.VALUE_INTERPOLATION_BICUBIC
                : RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Bilinear might be compromise

        // Stroking and Metrics
        rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        rh.put(RenderingHints.KEY_FRACTIONALMETRICS,
                highQuality ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

        rh.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        rh.put(RenderingHintsKeyExt.KEY_TRANSCODING,
                forPrinting ? RenderingHintsKeyExt.VALUE_TRANSCODING_PRINTING
                        : RenderingHintsKeyExt.VALUE_TRANSCODING_VECTOR);
        if (targetImage != null) {
            rh.put(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference<>(targetImage));
        }
        g.setRenderingHints(rh);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        try {
            BufferedImage img = (cachedImage != null) ? cachedImage.get() : null;
            boolean scalePlaceholder = false;
            double placeholderRatio = 1.0;
            if (img != null && cachedImageZoomFactor > 0 && Math.abs(cachedImageZoomFactor - zoomFactor) > 0.001) {
                scalePlaceholder = true;
                placeholderRatio = zoomFactor / cachedImageZoomFactor;
            }
            boolean useHighQualityHints = isHighQualityPaint && !scalePlaceholder;
            setupRenderingHints(g2d, useHighQualityHints, false, null);
            if (scalePlaceholder) {
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }

            // Background
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            if (img != null) {
                double drawX = panOffset.getX();
                double drawY = panOffset.getY();

                int sx1 = (int) Math.max(0, Math.floor(-drawX / placeholderRatio));
                int sy1 = (int) Math.max(0, Math.floor(-drawY / placeholderRatio));
                int sx2 = (int) Math.min(img.getWidth(), Math.ceil((-drawX + getWidth()) / placeholderRatio));
                int sy2 = (int) Math.min(img.getHeight(), Math.ceil((-drawY + getHeight()) / placeholderRatio));

                int dx1 = (int) Math.max(0, Math.floor(drawX + sx1 * placeholderRatio));
                int dy1 = (int) Math.max(0, Math.floor(drawY + sy1 * placeholderRatio));
                int dx2 = (int) Math.min(getWidth(), Math.ceil(drawX + sx2 * placeholderRatio));
                int dy2 = (int) Math.min(getHeight(), Math.ceil(drawY + sy2 * placeholderRatio));

                // Ensure valid rectangles before drawing
                if ((sx2 > sx1) && (sy2 > sy1) && (dx2 > dx1) && (dy2 > dy1)) {
                    g2d.drawImage(img,
                            dx1, dy1, dx2, dy2, // Destination rect (on screen)
                            sx1, sy1, sx2, sy2, // Source rect (from cached image)
                            null);
                }

            } else {
                // No image available - show status
                if (pendingRenderTask != null && !pendingRenderTask.isDone()) {
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.drawString("Rendering...", getWidth() / 2 - 30, getHeight() / 2);
                } else if (entities == null || entities.isEmpty()) {
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("No Unit Selected", getWidth() / 2 - 50, getHeight() / 2);
                } else if (!resetViewTimer.isRunning() && !needsViewReset) {
                    // No image, no pending task, but has entities - maybe render failed? It can
                    // happen during detach/attach of the tab
                    // A refresh should happen shortly after to fix this
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.drawString("No Sheet Image", getWidth() / 2 - 50, getHeight() / 2);
                }
            }
        } finally {
            g2d.dispose();
        }
    }

    /**
     * Cleans up resources and references to help with garbage collection.
     */
    public void cleanup() {
        if (pendingRenderTask != null) {
            pendingRenderTask.cancel(true); // Attempt to interrupt running task
            pendingRenderTask = null;
        }
        cachedImage = null;
        cachedImageZoomFactor = -1;
        gnSheets = null;
        if (resetViewTimer != null && resetViewTimer.isRunning())
            resetViewTimer.stop();
        if (zoomRenderDebounceTimer != null && zoomRenderDebounceTimer.isRunning())
            zoomRenderDebounceTimer.stop();
        // Do NOT shutdown the static executor here, as it's shared!!! (We rely on the
        // Runtime shutdown hook)
    }

    @Override
    public void removeNotify() {
        cleanup();
        super.removeNotify();
    }
}
