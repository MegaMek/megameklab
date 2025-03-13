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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.ext.awt.image.GraphicsUtil;

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
    private final double MIN_ZOOM = 1.0;
    private final double MAX_ZOOM = 4.0;
    private final double ZOOM_STEP = 0.2;
    private final double CLIPBOARD_ZOOM_SCALE = 4.0;
    private double minZoom = getMinimumZoom();
    private double zoomFactor = minZoom;
    private double initialZoomFactor = zoomFactor;

    private Point2D panOffset = new Point2D.Double(0, 0);
    private Point lastMousePoint;
    private boolean isPanning = false;
    private boolean isHighQuality = true; // Track rendering quality mode

    // cached image for optimized rendering
    private BufferedImage cachedImage;

    public RecordSheetPreviewPanel() {
        addMouseListener(new RightClickListener());
        setupMouseHandlers();
        // Enable better rendering
        setDoubleBuffered(true);
    }

    /**
     * Calculate the minimum zoom level needed to fit the record sheet vertically
     * within the window.
     * 
     * @return The minimum zoom factor
     */
    private double getMinimumZoom() {
        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();
        double minZoom = getHeight() / (double) pz.pxHeight;
        return Math.max(MIN_ZOOM, minZoom);
    }

    /**
     * Creates a complete image of the record sheet and copies it to the clipboard
     */
    private void copyRecordSheetToClipboard() {
        if (entities == null || entities.isEmpty()) {
            return;
        }

        // Use standard paper size for the clipboard image
        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();

        // Render the record sheet directly without zoom/pan
        ArrayList<GraphicsNode> nodes = getRecordSheetGraphicsNodes(entities, options);

        if (nodes.size() == 0) {
            return;
        }

        int imgWidth = (int) Math.ceil(pz.pxWidth * CLIPBOARD_ZOOM_SCALE);
        int fullWidth = imgWidth * nodes.size();
        int imgHeight = (int) Math.ceil(pz.pxHeight * CLIPBOARD_ZOOM_SCALE);

        BufferedImage img = new BufferedImage(fullWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
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
        g.clearRect(0, 0, fullWidth, imgHeight);

        if (nodes != null && !nodes.isEmpty()) {
            int k = 0;
            for (GraphicsNode gn : nodes) {
                if (gn == null) {
                    continue;
                }
                // Scale to fit the clipboard image
                var bounds = gn.getBounds();
                var yscale = (imgHeight - 20) / bounds.getHeight();
                var xscale = (imgWidth - 20) / bounds.getWidth();
                var scale = Math.min(yscale, xscale);

                // Calculate position for this sheet (side by side horizontally)
                double xOffset = k * imgWidth;

                // Center the sheet in the image
                double centerX = (imgWidth - (bounds.getWidth() * scale)) / 2;
                double centerY = (imgHeight - (bounds.getHeight() * scale)) / 2;

                // Apply transform for this sheet - scale and position
                AffineTransform transform = new AffineTransform();
                transform.translate(centerX+xOffset, centerY);
                transform.scale(scale, scale);
                gn.setTransform(transform);

                // Draw to the clipboard image
                gn.paint(g);
                k++;
            }

        }

        g.dispose();

        // Copy to clipboard
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
                zoomFactor = Math.max(minZoom, Math.min(MAX_ZOOM, zoomFactor));

                if (oldZoom != zoomFactor) {
                    // Adjust pan to keep mouse position fixed
                    double zoomRatio = zoomFactor / oldZoom;

                    panOffset.setLocation(
                            mousePoint.getX() - (mousePoint.getX() - panOffset.getX()) * zoomRatio,
                            mousePoint.getY() - (mousePoint.getY() - panOffset.getY()) * zoomRatio);
                    cachedImage = null; // Invalidate cached image on zoom change
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
                    // repaint();
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
        isHighQuality = true;
        zoomFactor = getMinimumZoom();
        initialZoomFactor = zoomFactor;
        renderCachedImage();
        if (cachedImage != null) {

            // Calculate offsets to center the image
            double xOffset = (getWidth() - cachedImage.getWidth()) / 2;
            double yOffset = (getHeight() - cachedImage.getHeight()) / 2;

            // Set pan offset to center the image
            // Use max(0, value) to avoid negative offsets if image is bigger than panel
            panOffset.setLocation(
                    Math.max(0, xOffset),
                    Math.max(0, yOffset));
        } else {
            // Default to 0,0 if no image
            panOffset.setLocation(0, 0);
        }

        repaint();
    }

    private ArrayList<GraphicsNode> gnSheets;
    private ArrayList<Entity> entities = new ArrayList<>();
    private boolean needsViewReset = false;

    /**
     * Sets multiple entities to be displayed side by side horizontally
     * 
     * @param entities List of entities to display
     */
    public void setEntities(ArrayList<Entity> entities) {
        if (entities == null) {
            this.entities.clear();
        } else {
            this.entities = entities;
        }

        // Reset view and invalidate cached image when entities change
        gnSheets = null;
        cachedImage = null;

        if (isVisible()) {
            // If visible, update the view immediately
            resetView();
            needsViewReset = false;
        } else {
            // If not visible, mark for update when panel becomes visible
            needsViewReset = true;
        }
    }

    /**
     * Sets a single entity to display (clears any previous multi-entity display)
     * 
     * @param entity The entity to display
     */
    public void setEntity(Entity entity) {
        if (this.entities == null) {
            this.entities = new ArrayList<>();
        }
        this.entities.clear();
        if (entity != null) {
            this.entities.add(entity);
        }
        // Reset view and invalidate cached image when entity changes
        gnSheets = null;
        cachedImage = null;
        if (isVisible()) {
            // If visible, update the view immediately
            resetView();
            needsViewReset = false;
        } else {
            // If not visible, mark for update when panel becomes visible
            needsViewReset = true;
        }
    }

    private ArrayList<GraphicsNode> getRecordSheetGraphicsNodes(List<Entity> entities, RecordSheetOptions options) {
        List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(entities, true, options, true);
        ArrayList<GraphicsNode> gnSheets = new ArrayList<GraphicsNode>();
        PageFormat pf = new PageFormat();
        for (PrintRecordSheet sheet : sheets) {
            if (sheet instanceof PrintSmallUnitSheet) {
                pf.setPaper(options.getPaperSize().createPaper());
            } else {
                pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
            }
            int pagesCount = sheet.getPageCount();
            for (int i = 0; i < pagesCount; i++) {
                sheet.createDocument(i, pf, false);
                gnSheets.add(sheet.build());
            }
        }
        return gnSheets;
    }

    private void renderCachedImage() {
        // Check if we have any entity to render
        if ((entities == null || entities.isEmpty())) {
            cachedImage = null;
            return;
        }

        RecordSheetOptions options = new RecordSheetOptions();
        PaperSize pz = options.getPaperSize();

        if (gnSheets == null || gnSheets.isEmpty()) {
            gnSheets = getRecordSheetGraphicsNodes(entities, options);
        }

        if (gnSheets.size() == 0) {
            cachedImage = null;
            return;
        }

        int fullWidth = (int) Math.ceil(pz.pxWidth * zoomFactor);
        int totalWidth = fullWidth * gnSheets.size();
        int fullHeight = (int) Math.ceil(pz.pxHeight * zoomFactor);

        // Try to use hardware-accelerated image if possible
        try {
            // Get hardware acceleration configuration
            java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
            java.awt.GraphicsDevice gs = ge.getDefaultScreenDevice();
            java.awt.GraphicsConfiguration gc = gs.getDefaultConfiguration();

            // Create a compatible image (should work better with hardware)
            cachedImage = gc.createCompatibleImage(totalWidth, fullHeight, java.awt.Transparency.OPAQUE);
        } catch (Exception e) {
            // Fallback to standard image if hardware acceleration fails
            cachedImage = new BufferedImage(totalWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g = GraphicsUtil.createGraphics(cachedImage);
        g.setComposite(java.awt.AlphaComposite.SrcOver);

        // Set render quality
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
        g.setRenderingHints(rh);

        // White background
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, totalWidth, fullHeight);

        // if (entity != null) {
        if (gnSheets != null && !gnSheets.isEmpty()) {
            int k = 0;
            for (int i = 0; i < gnSheets.size(); i++) {
                GraphicsNode gnSheet = gnSheets.get(i);
                if (gnSheet == null) {
                    continue;
                }
                AffineTransform originalTransform = gnSheet.getTransform();
                try {
                    var bounds = gnSheet.getBounds();
                    var yscale = (fullHeight - 20) / bounds.getHeight();
                    var xscale = (fullWidth - 20) / bounds.getWidth();
                    var scale = Math.min(yscale, xscale);
                    // Calculate position for this sheet (side by side horizontally)
                    double xOffset = k * (pz.pxWidth * zoomFactor);

                    // Center the sheet in the image
                    double centerX = (fullWidth - (bounds.getWidth() * scale)) / 2;
                    double centerY = (fullHeight - (bounds.getHeight() * scale)) / 2;

                    // Apply transform for this sheet - scale and position
                    AffineTransform transform = new AffineTransform();
                    transform.translate(centerX+xOffset, centerY);
                    transform.scale(scale, scale);
                    gnSheet.setTransform(transform);

                    // Paint this sheet
                    gnSheet.paint(g);
                    k++;
                } finally {
                    if (originalTransform != null) {
                        gnSheet.setTransform(originalTransform);
                    }
                }
            }
        }

        g.dispose();
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

        if (entities != null && !entities.isEmpty()) {
            // Generate image if needed
            if (cachedImage == null) {
                renderCachedImage();
            }

            if (cachedImage != null) {
                // Store original transform for restoration later
                AffineTransform originalTransform = g.getTransform();

                // Calculate source region (in image coordinates)
                double srcX = Math.max(0, -panOffset.getX());
                double srcY = Math.max(0, -panOffset.getY());

                // Create a transform that handles positioning and scaling in one step
                AffineTransform at = new AffineTransform(originalTransform);
                at.translate(Math.max(0, panOffset.getX()), Math.max(0, panOffset.getY()));
                g.setTransform(at);

                // Draw the image
                g.drawImage(cachedImage, -(int) srcX, -(int) srcY, null);

                // Restore original transform
                g.setTransform(originalTransform);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintComponent((Graphics2D) g, getWidth(), getHeight());
    }

    // Add a component resize listener to adjust the view on resize
    @Override
    public void addNotify() {
        super.addNotify();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                minZoom = getMinimumZoom();
                // Only auto-fit if we haven't manually zoomed or panned yet
                // (This prevents resetting user's view when window is resized)
                if (cachedImage != null && zoomFactor == initialZoomFactor) {
                    resetView();
                }
            }

            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                // Perform deferred view reset if needed
                if (needsViewReset) {
                    resetView();
                    needsViewReset = false;
                }
            }
        });
    }
}
