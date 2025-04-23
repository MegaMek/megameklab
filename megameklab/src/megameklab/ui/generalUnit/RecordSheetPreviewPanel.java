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
import java.awt.Rectangle;
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
import java.awt.geom.Rectangle2D;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.GraphicsUtil;

import megamek.common.BTObject;
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
 *         Uses background rendering for performance, rendering each page
 *         independently.
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
            t.setPriority(Thread.MIN_PRIORITY + 1); // Render at lower priority
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

    private static class RenderResult {
        final BufferedImage image;
        final double zoomFactor; // The zoom used to render this image
    
        RenderResult(BufferedImage image, double zoomFactor) {
            this.image = image;
            this.zoomFactor = zoomFactor;
        }
    }

    private class SheetPageInfo {
        GraphicsNode graphicsNode; // The raw Batik node for this page
        final int originalSheetIndex; // Index of the PrintRecordSheet this page belongs to
        final int pageIndexInSheet; // Index of this page within its PrintRecordSheet
        final int globalPageIndex; // Overall index across all pages
        final double baseWidthPx; // Original width of the page in pixels (unscaled)
        final double baseHeightPx; // Original height of the page in pixels (unscaled)

        SoftReference<BufferedImage> cachedImage; // The rendered image
        volatile double imageRenderZoom = -1.0; // Zoom factor the cachedImage was rendered at
        volatile Future<?> pendingRenderTask = null; // Background task rendering this page
        volatile int renderVersion = 0; // To discard stale renders for this page

        // Calculated layout position (top-left corner, relative to panel origin at 1x
        // zoom)
        Point2D.Double layoutPosition = new Point2D.Double(0, 0);

        SheetPageInfo(GraphicsNode node, int sheetIdx, int pageIdx, int globalIdx, double width, double height) {
            this.graphicsNode = node;
            this.originalSheetIndex = sheetIdx;
            this.pageIndexInSheet = pageIdx;
            this.globalPageIndex = globalIdx;
            this.baseWidthPx = width;
            this.baseHeightPx = height;
            this.cachedImage = new SoftReference<>(null);
        }

        synchronized void cancelPendingRender() {
            if (pendingRenderTask != null) {
                pendingRenderTask.cancel(false); // Don't interrupt, just mark as cancelled
                pendingRenderTask = null;
            }
            renderVersion++; // Invalidate any render currently finishing
        }

        boolean isRenderTaskActive() {
            return pendingRenderTask != null && !pendingRenderTask.isDone();
        }

        boolean hasValidCacheForZoom(double targetZoom) {
            return cachedImage != null && cachedImage.get() != null && Math.abs(imageRenderZoom - targetZoom) < 0.001;
        }
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
            if ((e.getComponent() != null) && (e.getComponent().isShowing())) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Zoom and pan state
    public static final int MAX_PRINTABLE_ENTITIES = 50;
    private final double MIN_ZOOM = 0.1;
    private final double MAX_ZOOM = 4.0;
    private final double ZOOM_STEP = 0.2;
    private final double CLIPBOARD_ZOOM_SCALE = 4.0;
    private final int SPACE_BETWEEN_PAGES = 10; // Space between pages in pixels
    private final int DEFAULT_MARGINS = 5; // Default margins for the page

    private volatile double minFitZoom = 1.0; // Minimum zoom to fit content
    private volatile double zoomFactor = 1.0; // Current view zoom
    private Point2D panOffset = new Point2D.Double(0, 0);
    private Point lastMousePoint;
    private boolean isPanning = false;
    private volatile boolean isHighQualityPaint = true;
    private final AffineTransform paintTransform = new AffineTransform(); // Reusable transform for clipboard painting

    // Record Sheet Data & Caching
    private boolean oneUnitPerSheet = false;
    private Boolean includeC3inBV = false;
    private Boolean showPilotData = false;
    private List<BTObject> currentEntities = Collections.emptyList();
    private List<SheetPageInfo> sheetPages = Collections.synchronizedList(new ArrayList<>());
    private List<PrintRecordSheet> generatedSheets = null; // Cache generated sheets for clipboard
    private final ReentrantLock sheetGenerationLock = new ReentrantLock(); // Lock for sheet generation

    // Timers for debouncing actions
    private Timer resetViewTimer;
    private Timer zoomRenderDebounceTimer;
    private Timer updateTimer;
    private Timer regenerateTimer;
    private static final int RESET_VIEW_DELAY = 200; // ms delay before resetting view
    private static final int ZOOM_RENDER_DEBOUNCE_DELAY = 100; // ms delay before rendering after zoom
    private static final int REGENERATE_DEBOUNCE_DELAY = 100; // ms delay before rendering after entity set
    private static final int UPDATE_DEBOUNCE_DELAY = 300; // ms delay before rendering after entity set

    private boolean needsViewReset = false;
    private boolean pendingInPlaceUpdate = false;
    
    public RecordSheetPreviewPanel() {
        addMouseListener(new RightClickListener());
        setupMouseHandlers();
        setDoubleBuffered(true);

        resetViewTimer = new Timer(RESET_VIEW_DELAY, e -> {
            resetViewTimer.stop();
            performResetView();
        });
        resetViewTimer.setRepeats(false);
        updateTimer = new Timer(UPDATE_DEBOUNCE_DELAY, e -> {
            updateTimer.stop();
            performUpdateSheetContentInPlace();
        });
        updateTimer.setRepeats(false);
        regenerateTimer = new Timer(REGENERATE_DEBOUNCE_DELAY, e -> {
            regenerateTimer.stop();
            performRegenerateAndReset();
        });
        updateTimer.setRepeats(false);

        zoomRenderDebounceTimer = new Timer(ZOOM_RENDER_DEBOUNCE_DELAY, e -> {
            zoomRenderDebounceTimer.stop();
            requestRenderForAllPages(); // Request render for all pages after zoom stops
        });
        zoomRenderDebounceTimer.setRepeats(false);

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (isShowing()) {
                    if (needsViewReset) {
                        regenerateAndReset();
                    } else
                    if (pendingInPlaceUpdate) {
                        updateSheetContentInPlace();
                    } else
                    if (sheetPages.isEmpty() && !currentEntities.isEmpty()) {
                        // Maybe entities were set while hidden, trigger generation/render
                        regenerateAndReset();
                    } else {
                        // Re-check minimum zoom and potentially re-render if needed
                        minFitZoom = calculateMinimumFitZoom();
                        requestRenderForAllPages();
                    }
                } else {
                    // Became hidden
                    if (resetViewTimer != null) resetViewTimer.stop();
                    if (zoomRenderDebounceTimer != null) zoomRenderDebounceTimer.stop();
                }
            }
        });

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                double oldMinFitZoom = minFitZoom;
                minFitZoom = calculateMinimumFitZoom();
                // If we were fitted or zoomed out further, reset view to re-fit
                if (zoomFactor <= oldMinFitZoom + 0.01) {
                    scheduleResetView();
                }
            }
        });

        setMinimumSize(new java.awt.Dimension(200, 200));
        performResetView(); // Initial state
    }

    private void setupMouseHandlers() {
        // Zoom
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (sheetPages.isEmpty())
                    return;

                Point mousePoint = e.getPoint();
                double oldZoom = zoomFactor;

                double scroll = e.getPreciseWheelRotation();
                double newZoom = zoomFactor * Math.pow(1.0 - ZOOM_STEP, scroll);

                newZoom = Math.max(minFitZoom, Math.min(MAX_ZOOM, newZoom));

                if (Math.abs(oldZoom - newZoom) > 0.001) {
                    double zoomRatio = newZoom / oldZoom;
                    panOffset.setLocation(
                            mousePoint.getX() - (mousePoint.getX() - panOffset.getX()) * zoomRatio,
                            mousePoint.getY() - (mousePoint.getY() - panOffset.getY()) * zoomRatio);

                    zoomFactor = newZoom;

                    // Constrain pan offset to keep content visible
                    panOffset.setLocation(
                        constrainPanX(panOffset.getX()),
                        constrainPanY(panOffset.getY()));
                        
                    isHighQualityPaint = false;
                    repaint();
                    zoomRenderDebounceTimer.restart(); // Schedule high-res render after zoom stops
                }
            }
        });

        // Pan Start/Stop and Double-Click reset
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (sheetPages.isEmpty())
                        return;
                    lastMousePoint = e.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    isPanning = true;
                    isHighQualityPaint = false;
                    zoomRenderDebounceTimer.stop(); // Stop render requests during pan
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && isPanning) {
                    setCursor(Cursor.getDefaultCursor());
                    isPanning = false;
                    lastMousePoint = null;
                    isHighQualityPaint = true;
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    if (!sheetPages.isEmpty()) {
                        resetView();
                    }
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
                    
                    double newPanX = panOffset.getX() + dx;
                    double newPanY = panOffset.getY() + dy;
                    newPanX = constrainPanX(newPanX);
                    newPanY = constrainPanY(newPanY);

                    panOffset.setLocation(newPanX, newPanY);
                    lastMousePoint = e.getPoint();
                    repaint();
                }
            }
        });
    }
/**
 * Constrains horizontal panning to keep content visible.
 * @param panX The proposed X pan coordinate
 * @return Constrained X coordinate
 */
private double constrainPanX(double panX) {
    if (sheetPages.isEmpty()) {
        return 0;
    }
    
    // Calculate leftmost and rightmost content bounds
    double leftmostX = Double.MAX_VALUE;
    double rightmostX = Double.MIN_VALUE;
    
    for (SheetPageInfo page : sheetPages) {
        double pageLeft = page.layoutPosition.x * zoomFactor;
        double pageRight = pageLeft + (page.baseWidthPx * zoomFactor);
        
        leftmostX = Math.min(leftmostX, pageLeft);
        rightmostX = Math.max(rightmostX, pageRight);
    }
    
    // Left constraint: don't allow content to move right of viewport left edge
    double minPanX = getWidth() - rightmostX;
    // Right constraint: don't allow content to move left of viewport right edge
    double maxPanX = -leftmostX;
    
    // If content is narrower than viewport, center it
    if (rightmostX - leftmostX < getWidth()) {
        double centerOffset = (getWidth() - (rightmostX - leftmostX)) / 2.0;
        return centerOffset - leftmostX;
    }
    
    return Math.min(maxPanX, Math.max(minPanX, panX));
}

    /**
     * Constrains vertical panning to keep content visible.
     * @param panY The proposed Y pan coordinate
     * @return Constrained Y coordinate
     */
    private double constrainPanY(double panY) {
        if (sheetPages.isEmpty()) {
            return 0;
        }
        
        // Get the tallest page height
        double maxPageHeight = 0;
        for (SheetPageInfo page : sheetPages) {
            maxPageHeight = Math.max(maxPageHeight, page.baseHeightPx * zoomFactor);
        }
        
        // Top constraint: don't allow content to move below viewport top edge
        double minPanY = getHeight() - maxPageHeight;
        // Bottom constraint: don't allow content to move above viewport bottom edge
        double maxPanY = 0;
        
        // If content is shorter than viewport, center it
        if (maxPageHeight < getHeight()) {
            return (getHeight() - maxPageHeight) / 2.0;
        }
        
        return Math.min(maxPanY, Math.max(minPanY, panY));
    }

    /**
     * Set to true if only one unit should be printed per sheet.
     * 
     * @param oneUnitPerSheet
     */
    public void setOneUnitPerSheet(boolean oneUnitPerSheet) {
        if (this.oneUnitPerSheet == oneUnitPerSheet) {
            return;
        }
        this.oneUnitPerSheet = oneUnitPerSheet;
        regenerateAndReset();
    }
    
    /**
     * Set to true/false if you want C3 and other special equips to affect the BV
     * 
     * @param enabled
     */
    public void includeC3inBV(Boolean enabled) {
        if (this.includeC3inBV == enabled) {
            return;
        }
        this.includeC3inBV = enabled;
        updateSheetContentInPlace();
    }

    /**
     * Set to true/false if you want to show the pilot data on the record sheet.
     * 
     * @param enabled
     */
    public void showPilotData(Boolean enabled) {
        if (this.showPilotData == enabled) {
            return;
        }
        this.showPilotData = enabled;
        updateSheetContentInPlace();
    }

    /**
     * Set the entities to be displayed in the record sheet preview.
     * 
     * @param selectedEntities The list of entities to display.
     */
    public void setEntities(List<? extends BTObject> selectedEntities) {
        List<BTObject> processedEntities;
        if (selectedEntities == null) {
            processedEntities = Collections.emptyList();
        } else {
            // Create a new list to avoid external modifications affecting us
            processedEntities = new ArrayList<>(selectedEntities);
        }
        boolean entitiesChanged = !areEntityListsEffectivelyEqual(this.currentEntities, processedEntities);
        if (entitiesChanged) {
            this.currentEntities = processedEntities;
            regenerateAndReset();
        } else {
            updateSheetContentInPlace();
        }
    }

    /**
     * Set a single entity to be displayed in the record sheet preview.
     * 
     * @param entity
     */
    public void setEntity(Entity entity) {
        setEntities(entity == null ? null : List.of(entity));
    }

    // Helper to compare entity lists
    private boolean areEntityListsEffectivelyEqual(List<BTObject> list1, List<BTObject> list2) {
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
     * Debounce regeneration and reset of the view.
     */
    private void regenerateAndReset() {
        if (!isShowing()) {
            needsViewReset = true;
            return;
        }
        updateTimer.stop();
        regenerateTimer.restart();
    }

    /**
     * Clears current state, regenerates sheets and pages, then schedules a view
     * reset.
     */
    private void performRegenerateAndReset() {
        cleanupPageTasksAndData(); // Cancel tasks, clear page list
        generatedSheets = null; // Clear cached sheets

        if (currentEntities.isEmpty()) {
            minFitZoom = 1.0; // Reset fit zoom
            zoomFactor = 1.0;
            panOffset.setLocation(0, 0);
            if (isShowing()) {
                repaint();
            } else {
                needsViewReset = true; // Mark for reset when shown
            }
            return;
        }

        if (isShowing()) {
            // Generate sheets and pages in the background to avoid blocking EDT
            // Display a "Loading..." message while generating
            repaint(); // Show empty state or loading message immediately
            renderExecutor.submit(() -> {
                generateSheetPages(currentEntities);
                SwingUtilities.invokeLater(this::scheduleResetView); // Reset view once pages are generated
            });
        } else {
            needsViewReset = true; // Mark for reset, generation will happen when shown/reset
        }
    }

    /**
     * Returns the current RecordSheetOptions based on the current settings.
     * @return
     */
    public RecordSheetOptions getRecordSheetOptions() {
        RecordSheetOptions options = new RecordSheetOptions();
        if (includeC3inBV != null) {
            options.setC3inBV(includeC3inBV);
        }
        if (showPilotData != null) {
            options.setPilotData(showPilotData);
        }
        return options;
    }

    /**
     * Generates the PrintRecordSheet objects and populates the sheetPages list.
     */
    private void generateSheetPages(List<BTObject> entitiesToGenerate) {
        if (entitiesToGenerate == null || entitiesToGenerate.isEmpty()) {
            return;
        }

        sheetGenerationLock.lock(); // Ensure only one thread generates sheets at a time
        try {
            RecordSheetOptions options = getRecordSheetOptions();
            PaperSize pz = options.getPaperSize();

            // This is one of the slowest parts
            logger.debug("Starting UnitPrintManager.createSheets...");
            long start = System.nanoTime();
            List<PrintRecordSheet> tempGeneratedSheets = UnitPrintManager.createSheets(
                    entitiesToGenerate.subList(0, Math.min(entitiesToGenerate.size(), MAX_PRINTABLE_ENTITIES)),
                    oneUnitPerSheet, options, true);
            long end = System.nanoTime();
            logger.debug("Finished UnitPrintManager.createSheets in {} ms", (end - start) / 1_000_000);

            if (tempGeneratedSheets == null || tempGeneratedSheets.isEmpty()) {
                logger.warn("UnitPrintManager.createSheets returned no sheets.");
                this.generatedSheets = Collections.emptyList();
                // Clear existing pages safely on EDT
                SwingUtilities.invokeLater(() -> {
                    sheetPages.clear();
                    repaint();
                });
                return;
            }

            this.generatedSheets = tempGeneratedSheets; // Cache for clipboard

            PageFormat pf = new PageFormat();
            PaperSize paperDef = options.getPaperSize();
            List<SheetPageInfo> newPages = new ArrayList<>();
            int globalPageIndex = 0;
            double currentXOffset = 0;

            logger.debug("Generating GraphicsNodes for {} sheets...", generatedSheets.size());
            start = System.nanoTime();
            for (int sheetIndex = 0; sheetIndex < generatedSheets.size(); sheetIndex++) {
                PrintRecordSheet sheet = generatedSheets.get(sheetIndex);
                if (sheet == null)
                    continue;

                if (sheet instanceof PrintSmallUnitSheet) {
                    pf.setPaper(paperDef.createPaper());
                } else {
                    pf.setPaper(paperDef.createPaper(DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS));
                }

                int pageCount = sheet.getPageCount();
                for (int pageIndexInSheet = 0; pageIndexInSheet < pageCount; pageIndexInSheet++) {
                    try {
                        sheet.createDocument(pageIndexInSheet, pf, false);
                        GraphicsNode node = sheet.build(); // Can be slow

                        if (node != null) {
                            // Use paper size as base, node bounds might be slightly different
                            double baseWidth = pz.pxWidth;
                            double baseHeight = pz.pxHeight;

                            SheetPageInfo pageInfo = new SheetPageInfo(node, sheetIndex, pageIndexInSheet,
                                    globalPageIndex, baseWidth, baseHeight);
                            pageInfo.layoutPosition.setLocation(currentXOffset, 0); // Simple horizontal layout
                            newPages.add(pageInfo);

                            currentXOffset += baseWidth + SPACE_BETWEEN_PAGES; // Add spacing between pages
                            globalPageIndex++;
                        } else {
                            logger.warn("Failed to build GraphicsNode for sheet {}, page {}", sheetIndex,
                                    pageIndexInSheet);
                        }
                    } catch (Exception e) {
                        logger.error(
                                "Error generating GraphicsNode for sheet " + sheetIndex + ", page " + pageIndexInSheet,
                                e);
                    }
                }
            }
            end = System.nanoTime();
            logger.debug("Finished generating {} GraphicsNodes in {} ms", globalPageIndex, (end - start) / 1_000_000);

            // Update the main list on the EDT or ensure thread-safety
            // Here we replace the whole list which is okay since sheetPages is synchronized
            sheetPages.clear(); // Clear old pages before adding new ones
            sheetPages.addAll(newPages);

        } finally {
            sheetGenerationLock.unlock();
        }
    }

    private void updateSheetContentInPlace() {
        if (!isShowing()) {
            pendingInPlaceUpdate = true;
            return;
        }
        regenerateTimer.stop();
        updateTimer.restart(); // Restart update timer to debounce
    }

    private List<PrintRecordSheet> createSheetsInEDT(List<BTObject> entitiesToGenerate, boolean singlePrint,
            RecordSheetOptions options) {
        if (!SwingUtilities.isEventDispatchThread()) {
            // If not, use invokeAndWait to call this method on the EDT
            final AtomicReference<List<PrintRecordSheet>> resultHolder = new AtomicReference<>();
            try {
                SwingUtilities.invokeAndWait(() -> 
                    resultHolder.set(createSheetsInEDT(entitiesToGenerate, singlePrint, options))
                );
                return resultHolder.get();
            } catch (Exception e) {
                logger.error("Error dispatching createSheets to EDT", e);
                return Collections.emptyList();
            }
        }
        List<PrintRecordSheet> tempGeneratedSheets = UnitPrintManager.createSheets(entitiesToGenerate, singlePrint, options, true);
        return tempGeneratedSheets;
    }

    private void performUpdateSheetContentInPlace() {
        if (!isShowing()) {
            pendingInPlaceUpdate = true;
            return;
        }
        updateTimer.stop(); // Stop the timer to prevent multiple calls
        pendingInPlaceUpdate = false;
        final double currentZoom = this.zoomFactor;
        final Point2D currentPan = new Point2D.Double(panOffset.getX(), panOffset.getY());

        repaint(); // Ensure placeholders might show if needed

        renderExecutor.submit(() -> {
            List<PrintRecordSheet> newGeneratedSheets = null;
            List<SheetPageInfo> newPageInfos = new ArrayList<>();
            boolean structureChanged = false;

            // Step 1: Generate new sheets and nodes (Background Thread)
            sheetGenerationLock.lock();
            try {
                logger.debug("Starting in-place UnitPrintManager.createSheets...");
                long start = System.nanoTime();
                // Regenerate sheets based on potentially updated entity state
                RecordSheetOptions options = getRecordSheetOptions();
                newGeneratedSheets = createSheetsInEDT(currentEntities.subList(0, Math.min(currentEntities.size(), MAX_PRINTABLE_ENTITIES)), oneUnitPerSheet, options);
                long end = System.nanoTime();
                logger.debug("Finished in-place UnitPrintManager.createSheets in {} ms", (end - start) / 1_000_000);

                if (newGeneratedSheets == null) {
                    newGeneratedSheets = Collections.emptyList();
                }
                this.generatedSheets = newGeneratedSheets; // Update cached sheets

                // Generate new nodes and page info list
                if (!newGeneratedSheets.isEmpty()) {
                    PaperSize pz = options.getPaperSize();
                    PageFormat pf = new PageFormat();
                    PaperSize paperDef = options.getPaperSize();
                    int globalPageIndex = 0;
                    double currentXOffset = 0;

                    logger.debug("Generating GraphicsNodes in-place for {} sheets...", newGeneratedSheets.size());
                    start = System.nanoTime();
                    for (int sheetIndex = 0; sheetIndex < newGeneratedSheets.size(); sheetIndex++) {
                        PrintRecordSheet sheet = newGeneratedSheets.get(sheetIndex);
                        if (sheet == null)
                            continue;

                        if (sheet instanceof PrintSmallUnitSheet) {
                            pf.setPaper(paperDef.createPaper());
                        } else {
                            pf.setPaper(paperDef.createPaper(DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS));
                        }

                        int pageCount = sheet.getPageCount();
                        for (int pageIndexInSheet = 0; pageIndexInSheet < pageCount; pageIndexInSheet++) {
                            try {
                                sheet.createDocument(pageIndexInSheet, pf, false);
                                GraphicsNode node = sheet.build();
                                if (node != null) {
                                    double baseWidth = pz.pxWidth;
                                    double baseHeight = pz.pxHeight;
                                    SheetPageInfo pageInfo = new SheetPageInfo(node, sheetIndex, pageIndexInSheet,
                                            globalPageIndex, baseWidth, baseHeight);
                                    pageInfo.layoutPosition.setLocation(currentXOffset, 0);
                                    newPageInfos.add(pageInfo);
                                    currentXOffset += baseWidth + SPACE_BETWEEN_PAGES;
                                    globalPageIndex++;
                                } else {
                                    logger.warn("Failed to build GraphicsNode (in-place) for sheet {}, page {}",
                                            sheetIndex, pageIndexInSheet);
                                }
                            } catch (Exception e) {
                                logger.error("Error generating GraphicsNode (in-place) for sheet " + sheetIndex
                                        + ", page " + pageIndexInSheet, e);
                            }
                        }
                    }
                    end = System.nanoTime();
                    logger.debug("Finished generating {} GraphicsNodes in-place in {} ms", globalPageIndex,
                            (end - start) / 1_000_000);
                }

            } catch (Exception ex) {
                logger.error("Error during in-place sheet/node generation", ex);
                structureChanged = true; // Treat errors as potential structure change
            } finally {
                sheetGenerationLock.unlock();
            }

            // Step 2: Update Page List on EDT
            final List<SheetPageInfo> finalNewPageInfos = newPageInfos;
            final boolean finalStructureChanged = structureChanged || (sheetPages.size() != finalNewPageInfos.size());

            SwingUtilities.invokeLater(() -> {
                if (needsViewReset) {
                    logger.debug("In-place update completion detected needsViewReset=true, aborting in-place logic.");
                    return;
                }
                if (finalStructureChanged) {
                    // If structure changed (or error occurred), fall back to full reset.
                    // It can happen when the tab is re-attached
                    logger.debug(
                            "Sheet structure changed during in-place update or error occurred. Performing full reset.");
                    regenerateAndReset(); // Use the full reset logic
                    return;
                }

                if (finalNewPageInfos.isEmpty() && sheetPages.isEmpty()) {
                    // Nothing to do if both old and new are empty
                    return;
                }

                synchronized (sheetPages) { // Ensure atomic update
                    // Create a temporary list to hold pages that need rendering
                    List<SheetPageInfo> pagesToReRender = new ArrayList<>();

                    for (int i = 0; i < sheetPages.size(); i++) {
                        SheetPageInfo oldPageInfo = sheetPages.get(i);
                        SheetPageInfo newPageInfo = finalNewPageInfos.get(i); // Assume 1:1 mapping based on index

                        // Sanity check indices (optional but good)
                        if (oldPageInfo.globalPageIndex != newPageInfo.globalPageIndex) {
                            logger.warn(
                                    "Page index mismatch during in-place update ({}/{} vs {}/{}). Falling back to reset.",
                                    oldPageInfo.originalSheetIndex, oldPageInfo.pageIndexInSheet,
                                    newPageInfo.originalSheetIndex, newPageInfo.pageIndexInSheet);
                            regenerateAndReset();
                            return; // Exit the lambda
                        }

                        synchronized (oldPageInfo) {
                            oldPageInfo.cancelPendingRender(); // Cancel any old render for this page
                            // Update the node BUT keep the old cached image and zoom level for now
                            oldPageInfo.graphicsNode = newPageInfo.graphicsNode;
                            oldPageInfo.layoutPosition = newPageInfo.layoutPosition; // Update layout too
                            // Keep oldPageInfo.cachedImage
                            // Keep oldPageInfo.imageRenderZoom
                            pagesToReRender.add(oldPageInfo);
                        }
                    }

                    // Now request renders for all updated pages at the current zoom
                    for (SheetPageInfo pageToRender : pagesToReRender) {
                        requestRenderForPage(pageToRender, currentZoom);
                    }
                } // End synchronized block

                // Restore view state
                this.zoomFactor = currentZoom;
                this.panOffset.setLocation(currentPan.getX(), currentPan.getY());
                this.isHighQualityPaint = true;

                // Repaint to show old images (scaled if needed) while new ones render
                repaint();
            });
        });
    }

    private void scheduleResetView() {
        resetViewTimer.restart();
    }

    public void resetView() {
        scheduleResetView();
    }

    private void performResetView() {
        needsViewReset = false;
        isHighQualityPaint = true;

        if (sheetPages.isEmpty()) {
            // If no pages, just center the view area (or set to 0,0)
            minFitZoom = calculateMinimumFitZoom();
            zoomFactor = minFitZoom;
            panOffset.setLocation(0, 0);
            repaint();
            return;
        }

        minFitZoom = calculateMinimumFitZoom();
        zoomFactor = minFitZoom; // Set zoom to fit

        // Calculate total content dimensions at fit zoom
        double totalContentWidth = 0;
        double maxContentHeight = 0;
        if (!sheetPages.isEmpty()) {
            SheetPageInfo lastPage = sheetPages.get(sheetPages.size() - 1);
            totalContentWidth = (lastPage.layoutPosition.x + lastPage.baseWidthPx) * zoomFactor;
            // We assume all pages have the same height for simplicity
            maxContentHeight = sheetPages.get(0).baseHeightPx * zoomFactor;
        }

        // Center horizontally and vertically
        double xOffset = (getWidth() - totalContentWidth) / 2.0;
        double yOffset = (getHeight() - maxContentHeight) / 2.0;

        // Make sure the first page is visible
        panOffset.setLocation(Math.max(0, xOffset), Math.max(0, yOffset));

        requestRenderForAllPages(); // Render all pages at the new fit zoom
        repaint();
    }

    /**
     * Recalculate minimum zoom to fit all current pages.
     */
    private double calculateMinimumFitZoom() {
        final int VERTICAL_PADDING = 0; // Padding for vertical fit

        if (sheetPages.isEmpty() || getWidth() <= 0 || getHeight() <= 0) {
            return MIN_ZOOM;
        }

        RecordSheetOptions options = getRecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        double maxBaseHeight = 0;
        if (!sheetPages.isEmpty()) {
            SheetPageInfo firstPage = sheetPages.get(0);
            maxBaseHeight = firstPage.baseHeightPx;
        } else {
            // Fallback if pages somehow not populated yet
            maxBaseHeight = pz.pxHeight;
        }

        if (maxBaseHeight <= 0)
            return MIN_ZOOM;

        double availableHeight = getHeight() - VERTICAL_PADDING;
        double zoomY = (availableHeight > 0) ? availableHeight / maxBaseHeight : 1.0;
        return Math.max(MIN_ZOOM, zoomY);
    }

    /**
     * Request background rendering for all pages if their cache is invalid
     * for the current zoom factor.
     */
    private synchronized void requestRenderForAllPages() {
        final double targetZoom = this.zoomFactor;
        isHighQualityPaint = true;
        List<SheetPageInfo> pagesToRender = new ArrayList<>(sheetPages); // Copy list for safe iteration
        for (SheetPageInfo pageInfo : pagesToRender) {
            if (!pageInfo.hasValidCacheForZoom(targetZoom)) {
                requestRenderForPage(pageInfo, targetZoom);
            }
        }
        // Repaint immediately to show placeholders or potentially already cached images
        repaint();
    }

    /**
     * Submits a rendering task for a specific page at a specific zoom level.
     */
    private synchronized void requestRenderForPage(SheetPageInfo pageInfo, double targetZoom) {
        pageInfo.cancelPendingRender(); // Cancel previous render task for this page

        final int currentRenderVersion = pageInfo.renderVersion; // Capture version for this task

        Callable<RenderResult> renderTask = () -> {
            // Background Thread
            if (Thread.currentThread().isInterrupted())
                return new RenderResult(null, targetZoom); 

            GraphicsNode node = pageInfo.graphicsNode;
            if (node == null)
                return new RenderResult(null, targetZoom); 

            int renderWidth = (int) Math.ceil(pageInfo.baseWidthPx * targetZoom);
            int renderHeight = (int) Math.ceil(pageInfo.baseHeightPx * targetZoom);

            if (renderWidth <= 0 || renderHeight <= 0) {
                logger.warn("Invalid render dimensions for page {}: {}x{}", pageInfo.globalPageIndex, renderWidth,
                        renderHeight);
                return new RenderResult(null, targetZoom); // Cannot render
            }

            BufferedImage img = null;
            Graphics2D g = null;
            AffineTransform originalTransform = null;
            boolean success = false;
            try {
                img = createCompatibleImage(renderWidth, renderHeight);
                if (img == null) {
                    return new RenderResult(null, targetZoom); // Failed to create image, possible OOM
                }
                g = GraphicsUtil.createGraphics(img);
                setupRenderingHints(g, true, false, img);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, renderWidth, renderHeight);

                originalTransform = node.getTransform(); // Save original

                final Rectangle2D bounds = node.getBounds();
                if (bounds == null || bounds.isEmpty() || bounds.getWidth() <= 0 || bounds.getHeight() <= 0) {
                    logger.warn("Node bounds invalid for page {}", pageInfo.globalPageIndex);
                    return new RenderResult(null, targetZoom); // Return blank image
                }
                double nodePadding = 5 * targetZoom;
                double availableNodeWidth = Math.max(1, renderWidth - nodePadding * 2);
                double availableNodeHeight = Math.max(1, renderHeight - nodePadding * 2);
                double scaleX = availableNodeWidth / bounds.getWidth();
                double scaleY = availableNodeHeight / bounds.getHeight();
                double scale = Math.min(scaleX, scaleY);
                double nodeX = (renderWidth - (bounds.getWidth() * scale)) / 2.0;
                double nodeY = (renderHeight - (bounds.getHeight() * scale)) / 2.0;

                AffineTransform workTransform = new AffineTransform();
                workTransform.translate(nodeX, nodeY);
                workTransform.scale(scale, scale);
                workTransform.translate(-bounds.getX(), -bounds.getY());
                node.setTransform(workTransform);

                node.paint(g); // Render the node
                success = true; // Mark as successful render
            } catch (OutOfMemoryError oom) {
                logger.error("OOM rendering page {} at zoom {}", pageInfo.globalPageIndex, targetZoom, oom);
                img = null; // Discard partial image
            } catch (Exception ex) {
                logger.error("Error rendering page {}", pageInfo.globalPageIndex, ex);
                img = null; // Discard on error
            } finally {
                if (g != null)
                    g.dispose();
                // Restore original transform
                if (node != null && originalTransform != null) {
                    try {
                        node.setTransform(originalTransform);
                    } catch (Exception e) {
                        logger.error("Failed to restore transform for page {}", pageInfo.globalPageIndex, e);
                    }
                }
            }
            return new RenderResult(success ? img : null, targetZoom); // Return rendered image (or null on error)
        };

        final CompletableFuture<RenderResult> completableRenderFuture = CompletableFuture.supplyAsync(() -> {
            try {
                // Check interruption status again just before execution
                if (Thread.currentThread().isInterrupted()) {
                    return new RenderResult(null, targetZoom);
                }
                return renderTask.call(); // Execute the Callable
            } catch (InterruptedException e) {
                // This handles interruption during renderTask.call() execution
                Thread.currentThread().interrupt(); // Restore interrupt status
                return new RenderResult(null, targetZoom);
            } catch (Exception e) {
                logger.error("Exception during renderTask.call(), wrapping in CompletionException", e);
                throw new CompletionException(e);
            }
        }, renderExecutor);

        pageInfo.pendingRenderTask = completableRenderFuture;

        // Process the result asynchronously on the EDT
        completableRenderFuture.thenAcceptAsync(result -> {
            // ---- EDT Thread ----
            synchronized (pageInfo) { // Synchronize access to pageInfo state
                boolean isCancelled = completableRenderFuture.isDone() && completableRenderFuture.isCancelled(); // Check cancellation status

                // Check if this result is still valid (correct version AND this task hasn't been cancelled/replaced)
                if (pageInfo.renderVersion == currentRenderVersion && pageInfo.pendingRenderTask == completableRenderFuture && !isCancelled) {

                    if (result != null && result.image != null) {
                        // SUCCESS: Use image and the zoom FROM THE RESULT
                        pageInfo.cachedImage = new SoftReference<>(result.image);
                        pageInfo.imageRenderZoom = result.zoomFactor;
                    } else {
                        // Render failed, was interrupted, or produced null image
                        pageInfo.cachedImage = new SoftReference<>(null);
                        pageInfo.imageRenderZoom = -1.0; // Mark as invalid
                    }
                    // Mark task as completed ONLY IF IT IS STILL THE CURRENT TASK reference
                    if (pageInfo.pendingRenderTask == completableRenderFuture) {
                        pageInfo.pendingRenderTask = null;
                    }
                    repaint(); // Repaint the panel to show the new image or clear old one

                } else {
                    // Discarding stale/cancelled result
                    if (result != null && result.image != null) {
                        result.image.flush();
                    }
                    // Clear if a cancelled task somehow lingered as pending
                    if (isCancelled && pageInfo.pendingRenderTask == completableRenderFuture) {
                        pageInfo.pendingRenderTask = null;
                    }
                }
            }
        }, SwingUtilities::invokeLater) // Ensure execution on EDT
        .exceptionally(ex -> { // Handle exceptions during task execution or completion stage
            // ---- EDT Thread ----
            Throwable cause = (ex instanceof CompletionException) ? ex.getCause() : ex; // Unwrap CompletionException

            if (cause instanceof java.util.concurrent.CancellationException) {
                logger.trace("Render task v{} page {} explicitly cancelled (exceptionally).", currentRenderVersion, pageInfo.globalPageIndex);
            } else if (cause instanceof InterruptedException) {
                Thread.currentThread().interrupt();
                logger.trace("Render task v{} page {} interrupted (exceptionally).", currentRenderVersion, pageInfo.globalPageIndex);
            } else {
                logger.error("Exception completing render task v{} page {}", currentRenderVersion, pageInfo.globalPageIndex, cause);
            }

            // Ensure task state is cleared correctly on error/cancellation
            synchronized (pageInfo) {
                if (pageInfo.renderVersion == currentRenderVersion && pageInfo.pendingRenderTask == completableRenderFuture) {
                    pageInfo.pendingRenderTask = null;
                    pageInfo.cachedImage = new SoftReference<>(null);
                    pageInfo.imageRenderZoom = -1.0;
                    repaint(); // Repaint to show placeholder
                }
            }
            return null;
        });

    }

    /**
     * Creates a hardware-accelerated image if possible
     */
    private BufferedImage createCompatibleImage(int width, int height) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            BufferedImage img = gc.createCompatibleImage(width, height, java.awt.Transparency.OPAQUE);
            if (img == null) { // Fallback if compatible image fails
                img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }
            return img;
        } catch (OutOfMemoryError ex) {
            logger.error("OOM creating compatible image {}x{}", width, height, ex);
            return null;
        } catch (Exception ex) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // fallback
        }
    }

    /** Helper to set rendering hints for Graphics2D */
    private void setupRenderingHints(Graphics2D g, boolean highQuality, boolean forPrinting,
            BufferedImage targetImage) {
        RenderingHints rh = new RenderingHints(null);

        rh.put(RenderingHints.KEY_RENDERING,
                highQuality ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED);
        rh.put(RenderingHints.KEY_COLOR_RENDERING,
                highQuality ? RenderingHints.VALUE_COLOR_RENDER_QUALITY : RenderingHints.VALUE_COLOR_RENDER_SPEED);
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, highQuality ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
                : RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        rh.put(RenderingHints.KEY_ANTIALIASING,
                highQuality ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                highQuality ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_INTERPOLATION,
                highQuality ? RenderingHints.VALUE_INTERPOLATION_BICUBIC
                        : RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); // Or STROKE_NORMALIZE
        rh.put(RenderingHints.KEY_FRACTIONALMETRICS,
                highQuality ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        rh.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);

        // Batik specific hints
        rh.put(RenderingHintsKeyExt.KEY_TRANSCODING, forPrinting ? RenderingHintsKeyExt.VALUE_TRANSCODING_PRINTING
                : RenderingHintsKeyExt.VALUE_TRANSCODING_VECTOR);
        if (targetImage != null) {
            rh.put(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference<>(targetImage));
        }
        // Avoids expensive offscreen buffer creation by Batik if possible
        rh.put(RenderingHintsKeyExt.KEY_AVOID_TILE_PAINTING, RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_ON);

        g.setRenderingHints(rh);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        try {
            // Background
            g2d.setColor(getBackground()); // Use panel background
            g2d.fillRect(0, 0, getWidth(), getHeight());

            if (sheetPages.isEmpty()) {
                // Draw placeholder text if no sheets/pages
                g2d.setColor(getForeground()); // Use panel foreground color
                setupRenderingHints(g2d, true, false, null);
                String message;
                if (!currentEntities.isEmpty() && sheetGenerationLock.isLocked()) {
                    message = "Generating Sheets...";
                } else if (currentEntities.isEmpty()) {
                    message = "No Unit Selected";
                } else {
                    message = " "; // No message
                }
                int stringWidth = g2d.getFontMetrics().stringWidth(message);
                g2d.drawString(message, (getWidth() - stringWidth) / 2, getHeight() / 2);
                return; // Nothing more to draw
            }

            final double currentZoom = this.zoomFactor;
            final Point2D currentPan = this.panOffset;
            final boolean highQuality = this.isHighQualityPaint;
            // Create a copy for thread safety during iteration
            List<SheetPageInfo> pagesToDraw = new ArrayList<>(sheetPages);

            // Draw each page
            for (SheetPageInfo pageInfo : pagesToDraw) {
                BufferedImage pageImage = (pageInfo.cachedImage != null) ? pageInfo.cachedImage.get() : null;
                final double cachedZoom = pageInfo.imageRenderZoom;

                // Calculate position and size on screen
                double targetDrawX = currentPan.getX() + pageInfo.layoutPosition.x * currentZoom;
                double targetDrawY = currentPan.getY() + pageInfo.layoutPosition.y * currentZoom;
                double targetDrawWidth = pageInfo.baseWidthPx * currentZoom;
                double targetDrawHeight = pageInfo.baseHeightPx * currentZoom;

                int targetX = (int) Math.round(targetDrawX);
                int targetY = (int) Math.round(targetDrawY);
                int targetW = (int) Math.round(targetDrawWidth);
                int targetH = (int) Math.round(targetDrawHeight);
                
                // Clip drawing to visible area for efficiency
                Rectangle targetBoundsInt = new Rectangle(targetX, targetY, targetW, targetH);
                if (!g2d.getClipBounds().intersects(targetBoundsInt)) {
                    continue; // Skip drawing if page is entirely off-screen (creates issues)
                }

                if (pageImage != null && cachedZoom > 0 && targetW > 0 && targetH > 0 && pageImage.getWidth() > 0 && pageImage.getHeight() > 0) {
                    // We have a cached image
                    boolean zoomsMatch = Math.abs(currentZoom - cachedZoom) < 0.005; // Allow small tolerance for zoom match
                    
                    if (zoomsMatch && highQuality) {
                        setupRenderingHints(g2d, true, false, null);
                        int drawPosX = (int) Math.round(currentPan.getX() + pageInfo.layoutPosition.x * cachedZoom);
                        int drawPosY = (int) Math.round(currentPan.getY() + pageInfo.layoutPosition.y * cachedZoom);
                        // Draw image at its native size at the calculated position
                        g2d.drawImage(pageImage, drawPosX, drawPosY, null);
                    } else {
                        setupRenderingHints(g2d, false, false, null);
                        g2d.drawImage(pageImage,
                            targetX, targetY, targetX + targetW, targetY + targetH,     // Destination rect (target area)
                            0, 0, pageImage.getWidth(), pageImage.getHeight(),  // Source rect (full cached image)
                            null);
                    }
                } else {
                    // No cached image available, draw placeholder
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fillRect(targetX, targetY, targetW, targetH);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawRect(targetX, targetY, targetW - 1, targetH - 1);

                    if (pageInfo.isRenderTaskActive()) {
                        g2d.setColor(Color.BLACK);
                        setupRenderingHints(g2d, true, false, null);
                        String msg = "Rendering...";
                        int sw = g2d.getFontMetrics().stringWidth(msg);
                        g2d.drawString(msg, (int) Math.round(targetX + (targetW - sw) / 2),
                                (int) Math.round(targetY + targetH / 2));
                    } else if (pageInfo.graphicsNode != null) {
                        // Has node but no image and no task
                        g2d.setColor(Color.DARK_GRAY);
                        setupRenderingHints(g2d, true, false, null);
                        String msg = "Waiting...";
                        int sw = g2d.getFontMetrics().stringWidth(msg);
                        g2d.drawString(msg, (int) Math.round(targetX + (targetW - sw) / 2),
                                (int) Math.round(targetY + targetH / 2));
                    }
                }
            } // End loop over pages

        } catch (Exception e) {
            logger.error("Error during paintComponent", e);
        } finally {
            g2d.dispose();
        }
    }

    /**
     * Copies the current record sheet to the system clipboard as an image.
     */
    private void copyRecordSheetToClipboard() {
        // Use the already generated GraphicsNodes from sheetPages
        List<SheetPageInfo> pagesToCopy = new ArrayList<>(sheetPages);

        if (pagesToCopy.isEmpty()) {
            logger.warn("No pages available to copy to clipboard.");
            return;
        }

        RecordSheetOptions options = getRecordSheetOptions();
        PaperSize pz = options.getPaperSize();

        // Calculate total dimensions for the clipboard image
        int numPages = pagesToCopy.size();
        int pageImgWidth = (int) Math.ceil(pz.pxWidth * CLIPBOARD_ZOOM_SCALE);
        int pageImgHeight = (int) Math.ceil(pz.pxHeight * CLIPBOARD_ZOOM_SCALE);
        int totalWidth = pageImgWidth * numPages;

        BufferedImage img = null;
        Graphics2D g = null;
        try {
            img = new BufferedImage(totalWidth, pageImgHeight, BufferedImage.TYPE_INT_RGB);
            g = GraphicsUtil.createGraphics(img);

            setupRenderingHints(g, true, true, img);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, totalWidth, pageImgHeight);

            int k = 0;
            for (SheetPageInfo pageInfo : pagesToCopy) {
                GraphicsNode gn = pageInfo.graphicsNode;
                if (gn == null)
                    continue;

                AffineTransform originalTransform = null;
                try {
                    originalTransform = gn.getTransform(); // Save original

                    Rectangle2D bounds = gn.getBounds();
                    if (bounds == null || bounds.getWidth() <= 0 || bounds.getHeight() <= 0) {
                        logger.warn("Skipping page {} for clipboard due to invalid node bounds.",
                                pageInfo.globalPageIndex);
                        continue;
                    }

                    // Calculate scale to fit within the allocated space per page
                    double padding = 20 * CLIPBOARD_ZOOM_SCALE;
                    double availableW = pageImgWidth - padding;
                    double availableH = pageImgHeight - padding;

                    double scaleX = (availableW > 0) ? availableW / bounds.getWidth() : 1.0;
                    double scaleY = (availableH > 0) ? availableH / bounds.getHeight() : 1.0;
                    double scale = Math.min(scaleX, scaleY);

                    // Center within its allocated space
                    double pageStartX = k * pageImgWidth;
                    double nodeRenderWidth = bounds.getWidth() * scale;
                    double nodeRenderHeight = bounds.getHeight() * scale;
                    double centerX = pageStartX + (pageImgWidth - nodeRenderWidth) / 2.0;
                    double centerY = (pageImgHeight - nodeRenderHeight) / 2.0;

                    // Apply transform: translate to position, scale, account for node origin
                    paintTransform.setToIdentity();
                    paintTransform.translate(centerX, centerY);
                    paintTransform.scale(scale, scale);
                    paintTransform.translate(-bounds.getX(), -bounds.getY());

                    gn.setTransform(paintTransform);
                    gn.paint(g); // Render this node onto the big image
                    k++;
                } catch (Exception ex) {
                    logger.error("Error painting node for page {} to clipboard", pageInfo.globalPageIndex, ex);
                } finally {
                    // Restore original transform
                    if (gn != null && originalTransform != null) {
                        try {
                            gn.setTransform(originalTransform);
                        } catch (Exception e) {
                            logger.error("Failed to restore transform for page {} after clipboard render",
                                    pageInfo.globalPageIndex, e);
                        }
                    }
                }
            }

            // Copy to clipboard
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new TransferableImage(img), null);
            logger.info("Record sheet ({} pages) copied to clipboard.", k);

        } catch (OutOfMemoryError ex) {
            logger.error("OutOfMemoryError creating image for clipboard copy.", ex);
            // Consider showing a user message here
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

    /** 
     * Cancels all pending page render tasks and clears caches.
     */
    private void cleanupPageTasksAndData() {
        List<SheetPageInfo> pagesToClean = new ArrayList<>(sheetPages); // Copy for safe iteration/modification
        sheetPages.clear();
        for (SheetPageInfo pageInfo : pagesToClean) {
            pageInfo.cancelPendingRender();
            if (pageInfo.cachedImage != null) {
                pageInfo.cachedImage.clear();
                pageInfo.cachedImage = null;
            }
        }
    }

    /**
     * Cleans up resources and references to help with garbage collection.
     */
    public void cleanup() {
        if (resetViewTimer != null && resetViewTimer.isRunning())
            resetViewTimer.stop();
        if (zoomRenderDebounceTimer != null && zoomRenderDebounceTimer.isRunning())
            zoomRenderDebounceTimer.stop();
        if (updateTimer != null && updateTimer.isRunning())
            updateTimer.stop();

        cleanupPageTasksAndData();
        generatedSheets = null;
    }

    @Override
    public void removeNotify() {
        cleanup();
        super.removeNotify();
    }
}