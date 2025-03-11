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
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.geom.Point2D;
import java.awt.image.VolatileImage;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.LinkedHashMap;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;

import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.GraphicsUtil;
import org.apache.fop.svg.GraphicsConfiguration;

import megamek.common.Entity;
import megameklab.printing.PaperSize;
import megameklab.printing.PrintRecordSheet;
import megameklab.printing.PrintSmallUnitSheet;
import megameklab.printing.RecordSheetOptions;
import megameklab.util.UnitPrintManager;

/**
 * @author pavelbraginskiy
 *         Simply fills itself with the record sheet for the given unit.
 */
public class RecordSheetPreviewPanel extends JPanel {
    private double zoomFactor = 1.0;
    private static final double ZOOM_FACTOR_STEP = 0.2;
    private static final double MIN_ZOOM = 1.0;
    private static final double MAX_ZOOM = 4.0;
    private Point2D zoomPoint = new Point2D.Double(0, 0);

    private Point2D panOffset = new Point2D.Double(0, 0);
    private Point2D lastDragPoint = new Point2D.Double(0, 0);
    private boolean isDragging = false;

    private VolatileImage buffer;
    private boolean bufferDirty = true;
    private Timer highQualityRenderTimer;
    private boolean isHighQuality = true;

    private ExecutorService renderExecutor = Executors.newSingleThreadExecutor();
    private static final int MAX_CACHE_SIZE = 5; // Maximum number of zoom levels to cache
    private boolean renderInProgress = false;

    private LinkedHashMap<Double, VolatileImage> zoomCache = new LinkedHashMap<Double, VolatileImage>(16, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Double, VolatileImage> eldest) {
            boolean remove = size() > MAX_CACHE_SIZE;
            if (remove && eldest.getValue() != null) {
                eldest.getValue().flush();
            }
            return remove;
        }
    };

    private BufferedImage contentRaster; // For ultra-fast transform mode during rapid interactions

    static {
        // Enable hardware acceleration
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.d3d", "true");
        System.setProperty("sun.java2d.ddscale", "true");
        System.setProperty("sun.java2d.translaccel", "true");
        System.setProperty("sun.java2d.ddforcevram", "true");
        System.setProperty("sun.java2d.accthreshold", "0"); // Accelerate everything
    }

    private class RightClickListener extends MouseAdapter {
        private final JPopupMenu popup = new JPopupMenu();
        {
            var copyItem = new JMenuItem("Copy to clipboard");
            copyItem.addActionListener(l -> {
                copyRecordSheetToClipboard();
            });
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
    private final double INITIAL_ZOOM = 1.0;
    private final double MIN_ZOOM = 1.0;
    private final double MAX_ZOOM = 4.0;
    private final double ZOOM_STEP = 0.2;
    private final double CLIPBOARD_ZOOM_SCALE = 4.0;
    private double zoomFactor = INITIAL_ZOOM;

    private Point2D panOffset = new Point2D.Double(0, 0);
    private Point lastMousePoint;
    private boolean isPanning = false;
    private boolean isHighQuality = true; // Track rendering quality mode

    // High-resolution cached image for optimized rendering
    private BufferedImage cachedImage;
    private boolean needsRedraw = true;

    public RecordSheetPreviewPanel() {
        addMouseListener(new RightClickListener());
        setupMouseHandlers();
        // Enable better rendering
        setDoubleBuffered(true);
        // Request optimal GPU acceleration
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.d3d", "true");
        System.setProperty("sun.java2d.ddforcevram", "true");
        System.setProperty("sun.java2d.managedimages", "true");
    }

    /**
     * Calculate the minimum zoom level needed to fit the record sheet vertically
     * within the window.
     * 
     * @return The minimum zoom factor
     */
    private double getMinimumZoom() {
        if (cachedImage == null || getHeight() <= 0) {
            return INITIAL_ZOOM; // Default if we can't calculate
        }

        // Calculate the zoom factor needed to fit the height of the component
        double imageHeight = cachedImage.getHeight();
        double availableHeight = getHeight();

        // Calculate how much we need to scale the high-res image to fit the height
        double minZoom = availableHeight / imageHeight * MAX_ZOOM;
        return Math.max(MIN_ZOOM, minZoom);
    }

    /**
     * Creates a complete image of the record sheet and copies it to the clipboard
     */
    private void copyRecordSheetToClipboard() {
        if (entity == null) {
            return;
        }

        // Use standard paper size for the clipboard image
        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        int imgWidth = (int) Math.ceil(pz.pxWidth * CLIPBOARD_ZOOM_SCALE);
        int imgHeight = (int) Math.ceil(pz.pxHeight * CLIPBOARD_ZOOM_SCALE);

        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = GraphicsUtil.createGraphics(img);

        // Set high quality rendering
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        rh.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        rh.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        rh.put(org.apache.batik.ext.awt.RenderingHintsKeyExt.KEY_TRANSCODING,
                org.apache.batik.ext.awt.RenderingHintsKeyExt.VALUE_TRANSCODING_PRINTING);
        g.setRenderingHints(rh);

        // White background
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, imgWidth, imgHeight);

        // Render the record sheet directly without zoom/pan
        PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(entity), true, options)
                .stream().findFirst().orElse(null);

        if (sheet != null) {
            PageFormat pf = new PageFormat();
            if (sheet instanceof PrintSmallUnitSheet) {
                pf.setPaper(options.getPaperSize().createPaper());
                sheet.createDocument(0, pf, false);
            } else {
                pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
                sheet.createDocument(0, pf, true);
            }

            GraphicsNode gn = sheet.build();

            // Scale to fit the clipboard image
            var bounds = gn.getBounds();
            var yscale = (imgHeight - 20) / bounds.getHeight();
            var xscale = (imgWidth - 20) / bounds.getWidth();
            var scale = Math.min(yscale, xscale);

            // Center the sheet in the image
            double centerX = (imgWidth - (bounds.getWidth() * scale)) / 2;
            double centerY = (imgHeight - (bounds.getHeight() * scale)) / 2;

            AffineTransform transform = new AffineTransform();
            transform.translate(centerX, centerY);
            transform.scale(scale, scale);
            gn.setTransform(transform);

            // Draw to the clipboard image
            gn.paint(g);
        }

        g.dispose();

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] { DataFlavor.imageFlavor };
            }
            var item = new JMenuItem("Copy to clipboard");
            item.addActionListener(l -> {
                var parent = RecordSheetPreviewPanel.this;
                PaperSize pz = new RecordSheetOptions().getPaperSize();
                BufferedImage img = new BufferedImage(pz.pxWidth * 2, pz.pxHeight * 2, BufferedImage.TYPE_INT_ARGB);

                // Create and properly dispose Graphics2D
                Graphics2D g2d = img.createGraphics();
                try {
                    parent.paintComponent(g2d, pz.pxWidth * 2, pz.pxHeight * 2);
                } finally {
                    g2d.dispose(); // Always dispose Graphics2D objects
                }

                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[] { DataFlavor.imageFlavor };
                    }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (!isDataFlavorSupported(flavor)) {
                    throw new UnsupportedFlavorException(flavor);
                }
                return img;
            }
        }, null);
    }

    private void setupMouseHandlers() {
        // Set up mouse wheel for zooming
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Calculate zoom centered on mouse position
                Point mousePoint = e.getPoint();
                double oldZoom = zoomFactor;

                // Adjust zoom by scroll amount
                zoomFactor -= e.getPreciseWheelRotation() * ZOOM_STEP;
                double minZoom = getMinimumZoom();
                zoomFactor = Math.max(minZoom, Math.min(MAX_ZOOM, zoomFactor));

                if (oldZoom != zoomFactor) {
                    // Adjust pan to keep mouse position fixed
                    double zoomRatio = zoomFactor / oldZoom;

                    panOffset.setLocation(
                            mousePoint.getX() - (mousePoint.getX() - panOffset.getX()) * zoomRatio,
                            mousePoint.getY() - (mousePoint.getY() - panOffset.getY()) * zoomRatio);

                    repaint();
                }
            }
        });

        // Mouse listener for double-click and drag init
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lastMousePoint = e.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    isPanning = true;
                    // Switch to low quality during panning for better performance
                    isHighQuality = false;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    setCursor(Cursor.getDefaultCursor());
                    isPanning = false;
                    isHighQuality = true;
                    repaint();
                }
            }
                    @Override
                    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                        if (!isDataFlavorSupported(flavor)) {
                            throw new UnsupportedFlavorException(flavor);
                        }
                        return img;
                    }
                }, null);
            });
            popup.add(item);

            // Add zoom reset option
            var resetZoom = new JMenuItem("Reset view");
            resetZoom.addActionListener(l -> {
                zoomFactor = 1.0;
                panOffset.setLocation(0, 0);
                isHighQuality = true; // Ensure high quality rendering
                bufferDirty = true; // Mark buffer as dirty to force redraw
                repaint();
            });
            popup.add(resetZoom);
        }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    resetView();
                }
            }
        });

        // Mouse motion listener for panning
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

    private void resetView() {
        calculateFitToHeightZoom();
        panOffset.setLocation(0, 0);
        repaint();
    }

    private void calculateFitToHeightZoom() {
        if (cachedImage == null || getHeight() <= 0) {
            zoomFactor = INITIAL_ZOOM; // Default if we can't calculate
            return;
        }
        double minZoom = getMinimumZoom();
        zoomFactor = Math.min(MAX_ZOOM, minZoom);
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getButton() == MouseEvent.BUTTON3) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                // Reset zoom and pan on double-click
                zoomFactor = 1.0;
                panOffset.setLocation(0, 0);
                isHighQuality = true; // Ensure high quality rendering
                bufferDirty = true; // Mark buffer as dirty to force redraw
                repaint();
            }
        }
    }

    // Inner class for zoom handling
    private class ZoomHandler implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            // Save the point where the zoom is happening
            zoomPoint.setLocation(e.getX(), e.getY());

            // Calculate scaling factors
            double oldZoom = zoomFactor;
            double newZoom = zoomFactor;

            // Update zoom factor based on scroll direction
            if (e.getWheelRotation() < 0) {
                // Scroll up - zoom in
                newZoom = Math.min(MAX_ZOOM, zoomFactor + ZOOM_FACTOR_STEP);
            } else {
                // Scroll down - zoom out
                newZoom = Math.max(MIN_ZOOM, zoomFactor - ZOOM_FACTOR_STEP);
            }

            // Only process if zoom level actually changed
            if (Math.abs(newZoom - oldZoom) > 0.001) { // Using epsilon comparison for floating point
                zoomFactor = newZoom;

                // Calculate the zoom ratio
                double zoomRatio = zoomFactor / oldZoom;

                // Get panel center
                double centerX = getWidth() / 2.0;
                double centerY = getHeight() / 2.0;

                // Calculate vector from center to cursor
                double vectorX = zoomPoint.getX() - centerX;
                double vectorY = zoomPoint.getY() - centerY;

                // Adjust pan offset to maintain cursor position over same document point
                panOffset.setLocation(
                        panOffset.getX() + (vectorX - vectorX * zoomRatio) / oldZoom,
                        panOffset.getY() + (vectorY - vectorY * zoomRatio) / oldZoom);

                // Use low quality during interaction
                setLowQualityMode();
                bufferDirty = true;
                repaint();
            }
        }
    }

    // Class for panning with mouse drag
    private class PanHandler extends MouseAdapter {
        private Timer finalRenderTimer;

        @Override
        public void mousePressed(MouseEvent e) {
            // Only respond to left mouse button
            if (e.getButton() == MouseEvent.BUTTON1) {
                lastDragPoint.setLocation(e.getX(), e.getY());
                isDragging = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                isDragging = false;
                setCursor(Cursor.getDefaultCursor());

                // Cancel any pending timer
                highQualityRenderTimer.stop();

                // Release fast mode resources
                contentRaster = null;

                // Force high quality rendering with a guaranteed execution
                if (finalRenderTimer != null) {
                    finalRenderTimer.stop();
                }

                finalRenderTimer = new Timer(50, evt -> {
                    ((Timer) evt.getSource()).stop();
                    isHighQuality = true;
                    bufferDirty = true;
                    renderInProgress = false; // Force allowing a new render
                    repaint();
                });
                finalRenderTimer.setRepeats(false);
                finalRenderTimer.start();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (isDragging) {
                // Calculate drag delta
                double deltaX = e.getX() - lastDragPoint.getX();
                double deltaY = e.getY() - lastDragPoint.getY();

                // Update pan offset based on the drag delta
                panOffset.setLocation(
                        panOffset.getX() + deltaX / zoomFactor,
                        panOffset.getY() + deltaY / zoomFactor);

                // Update last drag point
                lastDragPoint.setLocation(e.getX(), e.getY());

                // Use ultra-fast mode for continuous panning
                setUltraFastMode();
                bufferDirty = true;
                repaint();
            }
        }
    }

    private void setLowQualityMode() {
        if (isHighQuality) {
            isHighQuality = false;
        }
        highQualityRenderTimer.restart();
    }

    // For extremely rapid interactions like continuous scrolling
    private void setUltraFastMode() {
        if (contentRaster == null && buffer != null) {
            // Create a snapshot of current content for fastest possible transforms
            contentRaster = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = contentRaster.createGraphics();
            g2d.drawImage(buffer, 0, 0, null);
            g2d.dispose();
        }

        isHighQuality = false;
        highQualityRenderTimer.restart();
    }

    public RecordSheetPreviewPanel() {
        PanHandler panHandler = new PanHandler();
        addMouseListener(new RightClickListener());
        addMouseListener(panHandler);
        addMouseMotionListener(panHandler);
        addMouseWheelListener(new ZoomHandler());
        highQualityRenderTimer = new Timer(250, e -> {
            isHighQuality = true;
            bufferDirty = true;
            repaint();
        });
        highQualityRenderTimer.setRepeats(false);

        // Enable double buffering
        setDoubleBuffered(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                documentChanged = true;
                bufferDirty = true;
            }
        });
    }

    // Override addNotify to detect when component is added to container hierarchy
    @Override
    public void addNotify() {
        super.addNotify();
        documentChanged = true;
        bufferDirty = true;

        // Add a hierarchy listener to detect tab/window activation
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (isShowing()) {
                    documentChanged = true;
                    bufferDirty = true;
                }
            }
        });

        // Add ancestor listener to detect when parent becomes visible
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                documentChanged = true;
                bufferDirty = true;
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                // Not needed
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                // Not needed
            }
        });
    }

    private Entity entity;

    public void setEntity(Entity entity) {
        this.entity = entity;
        // Reset view and invalidate cached image when entity changes
        needsRedraw = true;
        cachedImage = null;
        if (entity != null) {
            renderHighResolutionImage();
        }
        resetView();
        zoomFactor = 1.0;
        panOffset.setLocation(0, 0);
        bufferDirty = true;
        isHighQuality = true; // Always use high quality for initial render
        repaint();
    }

    private GraphicsNode cachedGraphicsNode;
    private boolean documentChanged = true;
    private double lastBaseScale = 0;
    private AffineTransform lastTransform;

    private void renderHighResolutionImage() {
        if (entity == null) {
            cachedImage = null;
            return;
        }

        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        int fullWidth = (int) Math.ceil(pz.pxWidth * MAX_ZOOM);
        int fullHeight = (int) Math.ceil(pz.pxHeight * MAX_ZOOM);

        // Try to use hardware-accelerated image if possible
        try {
            // Get hardware acceleration configuration
            java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
            java.awt.GraphicsDevice gs = ge.getDefaultScreenDevice();
            java.awt.GraphicsConfiguration gc = gs.getDefaultConfiguration();

            // Create a compatible image (should work better with hardware)
            cachedImage = gc.createCompatibleImage(fullWidth, fullHeight, java.awt.Transparency.OPAQUE);
        } catch (Exception e) {
            // Fallback to standard image if hardware acceleration fails
            cachedImage = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g = GraphicsUtil.createGraphics(cachedImage);
        g.setComposite(java.awt.AlphaComposite.SrcOver);

        // Set render quality for the high-res image
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        rh.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        rh.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        rh.put(org.apache.batik.ext.awt.RenderingHintsKeyExt.KEY_TRANSCODING,
                org.apache.batik.ext.awt.RenderingHintsKeyExt.VALUE_TRANSCODING_VECTOR);
        rh.put(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference<BufferedImage>(cachedImage));
    private void paintComponent(Graphics2D g, int width, int height) {
        final Entity renderEntity = this.entity; // Create a snapshot
        if (renderEntity == null) {
            return;
        }

        // Set render quality, antialiasing is necessary
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING,
                isHighQuality ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED);
        rh.put(RenderingHints.KEY_ANTIALIASING,
                isHighQuality ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                isHighQuality ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g.setRenderingHints(rh);

        // White background
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, fullWidth, fullHeight);

        // Render the record sheet to the high-res image
        PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(entity), true, options)
        // Generate SVG
        RecordSheetOptions options = new RecordSheetOptions();
        PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(renderEntity), true, options)
                .stream().findFirst().orElse(null);
        if (sheet == null) {
            return;
        }

        if (sheet != null) {
        // Create document
        if (cachedGraphicsNode == null || documentChanged) {
            PageFormat pf = new PageFormat();
            if (sheet instanceof PrintSmallUnitSheet) {
                pf.setPaper(options.getPaperSize().createPaper());
                sheet.createDocument(0, pf, false);
            } else {
                pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
                sheet.createDocument(0, pf, true);
            }

            GraphicsNode gn = sheet.build();

            // Scale to fit the high-resolution image
            var bounds = gn.getBounds();
            var yscale = (fullHeight - 20) / bounds.getHeight();
            var xscale = (fullWidth - 20) / bounds.getWidth();
            var scale = Math.min(yscale, xscale);

            // Center the sheet in the image
            double centerX = (fullWidth - (bounds.getWidth() * scale)) / 2;
            double centerY = (fullHeight - (bounds.getHeight() * scale)) / 2;

            AffineTransform transform = new AffineTransform();
            transform.translate(centerX, centerY);
            transform.scale(scale, scale);
            gn.setTransform(transform);

            // Draw to the high-res image
            gn.paint(g);
        }

        g.dispose();
        needsRedraw = false;
    }

    private void paintComponent(Graphics2D g, int width, int height) {
        // Set render hints based on quality mode
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_RENDERING,
                isHighQuality ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED);
        rh.put(RenderingHints.KEY_INTERPOLATION,
                isHighQuality ? RenderingHints.VALUE_INTERPOLATION_BICUBIC
                        : RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        rh.put(RenderingHints.KEY_ANTIALIASING,
                isHighQuality ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                isHighQuality ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        rh.put(RenderingHints.KEY_FRACTIONALMETRICS,
                isHighQuality ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g.setRenderingHints(rh);

        // White background
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, width, height);

        if (entity != null && cachedImage != null) {
            // Generate high-resolution image if needed
            if (needsRedraw) {
                renderHighResolutionImage();
            }

            if (cachedImage != null) {
                // Store original transform for restoration later
                AffineTransform originalTransform = g.getTransform();

                // Create transform that includes zoom and pan
                AffineTransform at = new AffineTransform();
                at.translate(panOffset.getX(), panOffset.getY());
                at.scale(zoomFactor / MAX_ZOOM, zoomFactor / MAX_ZOOM);

                // Calculate visible region for clipping
                Rectangle visibleRect = getVisibleRect();
                Rectangle2D imageRect = new Rectangle2D.Double(
                        panOffset.getX(),
                        panOffset.getY(),
                        cachedImage.getWidth() * (zoomFactor / MAX_ZOOM),
                        cachedImage.getHeight() * (zoomFactor / MAX_ZOOM));

                // Only draw if the image intersects the visible area
                if (visibleRect.intersects(new Rectangle(
                        (int) imageRect.getX(),
                        (int) imageRect.getY(),
                        (int) imageRect.getWidth(),
                        (int) imageRect.getHeight()))) {

                    // Calculate source region (what part of the image to draw)
                    double srcX = Math.max(0, -panOffset.getX() * (MAX_ZOOM / zoomFactor));
                    double srcY = Math.max(0, -panOffset.getY() * (MAX_ZOOM / zoomFactor));
                    double srcWidth = Math.min(
                            cachedImage.getWidth(),
                            visibleRect.getWidth() * (MAX_ZOOM / zoomFactor) + srcX);
                    double srcHeight = Math.min(
                            cachedImage.getHeight(),
                            visibleRect.getHeight() * (MAX_ZOOM / zoomFactor) + srcY);

                    // Destination region (where to draw in the panel)
                    double destX = Math.max(0, panOffset.getX());
                    double destY = Math.max(0, panOffset.getY());
                    double destWidth = srcWidth * (zoomFactor / MAX_ZOOM);
                    double destHeight = srcHeight * (zoomFactor / MAX_ZOOM);

                    // Draw only the visible part of the image

                    g.drawImage(
                            cachedImage,
                            (int) destX, (int) destY,
                            (int) (destX + destWidth), (int) (destY + destHeight),
                            (int) srcX, (int) srcY,
                            (int) (srcX + srcWidth), (int) (srcY + srcHeight),
                            null);
                }

                // Restore original transform
                g.setTransform(originalTransform);
            }
        }
            cachedGraphicsNode = sheet.build();
            documentChanged = false;
        }

        // Apply transformation
        var bounds = cachedGraphicsNode.getBounds();
        var yscale = (height - 5) / bounds.getHeight();
        var xscale = (width - 5) / bounds.getWidth();
        var baseScale = Math.min(yscale, xscale);

        // Apply zoom transformation
        AffineTransform at = new AffineTransform();
        double centerX = width / 2.0;
        double centerY = height / 2.0;
        double finalScale = baseScale * zoomFactor;
        double sheetCenterX = bounds.getWidth() / 2.0;
        double sheetCenterY = bounds.getHeight() / 2.0;

        at.translate(centerX, centerY);
        at.scale(finalScale, finalScale);
        at.translate(panOffset.getX(), panOffset.getY());
        at.translate(-sheetCenterX, -sheetCenterY);

        cachedGraphicsNode.setTransform(at);
        lastTransform = at;
        lastBaseScale = baseScale;

        // Draw the SVG
        cachedGraphicsNode.paint(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (entity == null) {
            return;
        }

        int w = getWidth();
        int h = getHeight();

        // Create or recreate buffer if needed
        if (buffer == null || bufferDirty || buffer.getWidth() != w || buffer.getHeight() != h) {
            createBuffer(w, h);
        }

        // Draw the buffered image
        g.drawImage(buffer, 0, 0, null);
    }

    // Add synchronization to prevent race conditions
    private final Object renderLock = new Object();

    private void createBuffer(int width, int height) {
        synchronized (renderLock) {
            if (width <= 0 || height <= 0 || renderInProgress) {
                return;
            }
            renderInProgress = true;
        }

        // Use a low-resolution preview immediately if available
        final double roundedZoom = Math.round(zoomFactor * 5) / 5.0; // Round to nearest 0.2
        VolatileImage cachedImage = zoomCache.get(roundedZoom);

        if (cachedImage != null && !bufferDirty) {
            // We can use the cached version immediately
            buffer = cachedImage;
            renderInProgress = false;
            return;
        }

        // Schedule high-quality rendering on background thread
        renderExecutor.submit(() -> {
            try {
                // Create a hardware-accelerated volatile image
                GraphicsConfiguration gc = getGraphicsConfiguration();
                VolatileImage newBuffer = gc.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);

                do {
                    if (newBuffer.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                        newBuffer = gc.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
                    }

                    Graphics2D g2d = newBuffer.createGraphics();
                    try {
                        // Always use high quality for cached images
                        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                        // Render content to the buffer
                        paintComponent(g2d, width, height);
                    } finally {
                        g2d.dispose();
                    }
                } while (newBuffer.contentsLost());

                // Cache the result for this zoom level
                zoomCache.put(roundedZoom, newBuffer);

                // Limit cache size by removing oldest entries
                if (zoomCache.size() > MAX_CACHE_SIZE) {
                    zoomCache.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .limit(1)
                            .forEach(entry -> {
                                VolatileImage oldImage = entry.getValue();
                                if (oldImage != null) {
                                    oldImage.flush();
                                }
                                zoomCache.remove(entry.getKey());
                            });
                }

                // Update the main buffer and trigger repaint
                buffer = newBuffer;
                bufferDirty = false;
                SwingUtilities.invokeLater(() -> repaint());
            } catch (Exception ex) {
                // Log the error
                ex.printStackTrace();
            } finally {
                renderInProgress = false;
            }
        });
    }

    // Dispose method to clean up resources
    public void dispose() {
        renderExecutor.shutdown();
        try {
            renderExecutor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Clean up cached images
        zoomCache.clear();
    }

    private GraphicsConfiguration lastGC;

    private void checkGraphicsConfigChanged() {
        GraphicsConfiguration currentGC = getGraphicsConfiguration();
        if (currentGC != lastGC) {
            // Configuration changed, invalidate caches
            for (VolatileImage img : zoomCache.values()) {
                if (img != null)
                    img.flush();
            }
            zoomCache.clear();
            bufferDirty = true;
            lastGC = currentGC;
        }
    }

    // Add a component resize listener to adjust the view on resize
    @Override
    public void addNotify() {
        super.addNotify();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                // Only auto-fit if we haven't manually zoomed or panned yet
                // (This prevents resetting user's view when window is resized)
                if (cachedImage != null && panOffset.getX() == 0.0 && panOffset.getY() == 0.0
                        && zoomFactor == INITIAL_ZOOM) {
                    resetView();
                }
            }
        });
    }
}
