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
 import java.awt.GraphicsConfiguration;
 import java.awt.RenderingHints;
 import java.awt.Toolkit;
 import java.awt.Transparency;
 import java.awt.datatransfer.DataFlavor;
 import java.awt.datatransfer.Transferable;
 import java.awt.datatransfer.UnsupportedFlavorException;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseWheelEvent;
 import java.awt.event.MouseWheelListener;
 import java.awt.geom.AffineTransform;
 import java.awt.geom.Point2D;
 import java.awt.image.VolatileImage;
 import java.awt.print.PageFormat;
 import java.io.IOException;
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
 
 import org.apache.batik.gvt.GraphicsNode;
 
 import megamek.common.Entity;
 import megameklab.printing.PaperSize;
 import megameklab.printing.PrintRecordSheet;
 import megameklab.printing.PrintSmallUnitSheet;
 import megameklab.printing.RecordSheetOptions;
 import megameklab.util.UnitPrintManager;
 
 /**
  * @author pavelbraginskiy
  * Simply fills itself with the record sheet for the given unit.
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
     private static final int MAX_CACHE_SIZE = 5;  // Maximum number of zoom levels to cache
     private boolean renderInProgress = false;
 
     private LinkedHashMap<Double, VolatileImage> zoomCache = 
         new LinkedHashMap<Double, VolatileImage>(16, 0.75f, true) {
             @Override
             protected boolean removeEldestEntry(Map.Entry<Double, VolatileImage> eldest) {
                 boolean remove = size() > MAX_CACHE_SIZE;
                 if (remove && eldest.getValue() != null) {
                     eldest.getValue().flush();
                 }
                 return remove;
             }
         };
 
     private BufferedImage contentRaster;  // For ultra-fast transform mode during rapid interactions
 
     static {
         // Enable hardware acceleration
         System.setProperty("sun.java2d.opengl", "true");
         System.setProperty("sun.java2d.d3d", "true");
         System.setProperty("sun.java2d.ddscale", "true");
         System.setProperty("sun.java2d.translaccel", "true");
         System.setProperty("sun.java2d.ddforcevram", "true");
         System.setProperty("sun.java2d.accthreshold", "0");  // Accelerate everything
     }
 
     private class RightClickListener extends MouseAdapter {
         private final JPopupMenu popup = new JPopupMenu();
         {
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
             });
             popup.add(item);
             
             // Add zoom reset option
             var resetZoom = new JMenuItem("Reset view");
             resetZoom.addActionListener(l -> {
                 zoomFactor = 1.0;
                 panOffset.setLocation(0, 0);
                 isHighQuality = true;  // Ensure high quality rendering
                 bufferDirty = true;    // Mark buffer as dirty to force redraw
                 repaint();
             });
             popup.add(resetZoom);
         }
 
         @Override
         public void mouseClicked(MouseEvent e) {
             super.mouseClicked(e);
             if (e.getButton() == MouseEvent.BUTTON3) {
                 popup.show(e.getComponent(), e.getX(), e.getY());
             } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                 // Reset zoom and pan on double-click
                 zoomFactor = 1.0;
                 panOffset.setLocation(0, 0);
                 isHighQuality = true;  // Ensure high quality rendering
                 bufferDirty = true;    // Mark buffer as dirty to force redraw
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
                     panOffset.getY() + (vectorY - vectorY * zoomRatio) / oldZoom
                 );
                 
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
                     ((Timer)evt.getSource()).stop();
                     isHighQuality = true;
                     bufferDirty = true;
                     renderInProgress = false;  // Force allowing a new render
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
                     panOffset.getY() + deltaY / zoomFactor
                 );
                 
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
     }
 
     private Entity entity;
 
     public void setEntity(Entity entity) {
         this.entity = entity;
         zoomFactor = 1.0;
         panOffset.setLocation(0, 0);
         bufferDirty = true;
         isHighQuality = true;  // Always use high quality for initial render
         repaint();
     }
 
     private GraphicsNode cachedGraphicsNode;
     private Entity lastRenderedEntity;
     private double lastBaseScale = 0;
     private AffineTransform lastTransform;
 
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
 
         // White background for image
         g.setBackground(Color.WHITE);
         g.clearRect(0, 0, width, height);
 
         if (renderEntity == null) {
             return;
         }
 
         // Only regenerate SVG if entity changed
         if (cachedGraphicsNode == null || !renderEntity.equals(lastRenderedEntity)) {
             // Generate SVG
             RecordSheetOptions options = new RecordSheetOptions();
             PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(renderEntity), true, options)
                 .stream().findFirst().orElse(null);
             if (sheet == null) {
                 return;
             }
 
             // Create document
             PageFormat pf = new PageFormat();
             if (sheet instanceof PrintSmallUnitSheet) {
                 pf.setPaper(options.getPaperSize().createPaper());
                 sheet.createDocument(0, pf, false);
             } else {
                 pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
                 sheet.createDocument(0, pf, true);
             }
 
             cachedGraphicsNode = sheet.build();
             lastRenderedEntity = renderEntity;
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
         synchronized(renderLock) {
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
                         g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                         
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
                 if (img != null) img.flush();
             }
             zoomCache.clear();
             bufferDirty = true;
             lastGC = currentGC;
         }
     }
 }
 