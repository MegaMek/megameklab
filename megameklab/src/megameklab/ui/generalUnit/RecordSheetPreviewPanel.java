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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.apache.batik.gvt.GraphicsNode;

import megamek.common.Entity;
import megameklab.printing.PaperSize;
import megameklab.printing.PrintRecordSheet;
import megameklab.printing.PrintSmallUnitSheet;
import megameklab.printing.RecordSheetOptions;
import megameklab.util.UnitPrintManager;

/**
 * @author pavelbraginskiy
 *
 *         Simply fills itself with the record sheet for the given unit.
 */
public class RecordSheetPreviewPanel extends JPanel {
    private class RightClickListener extends MouseAdapter {
        private final JPopupMenu popup = new JPopupMenu();
        {
            var item = new JMenuItem("Copy to clipboard");
            item.addActionListener(l -> {
                var parent = RecordSheetPreviewPanel.this;
                PaperSize pz = new RecordSheetOptions().getPaperSize();
                BufferedImage img = new BufferedImage(pz.pxWidth * 2, pz.pxHeight * 2, BufferedImage.TYPE_INT_ARGB);
                parent.paintComponent(img.createGraphics(), pz.pxWidth * 2, pz.pxHeight * 2);

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

    public RecordSheetPreviewPanel() {
        addMouseListener(new RightClickListener());
    }

    private Entity entity;

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    private void paintComponent(Graphics2D g, int width, int height) {
        // Set render quality, antialiasing is extremely necessary
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        // White background for image
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, width, height);

        if (entity != null) {
            RecordSheetOptions options = new RecordSheetOptions();
            PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(entity), true, options).get(0);

            // 5-pixel margin around rs
            // Except for SmallUnitSheets which have weird clipping issues with nonstandard
            // margin
            PageFormat pf = new PageFormat();
            if (sheet instanceof PrintSmallUnitSheet) {
                pf.setPaper(options.getPaperSize().createPaper());
                sheet.createDocument(0, pf, false);
            } else {
                pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
                sheet.createDocument(0, pf, true);
            }

            GraphicsNode gn = sheet.build();

            // Scale record sheet to the size of the panel
            var bounds = gn.getBounds();
            var yscale = (height - 5) / bounds.getHeight();
            var xscale = (width - 5) / bounds.getWidth();
            var scale = Math.min(yscale, xscale);
            gn.setTransform(AffineTransform.getScaleInstance(scale, scale));

            // Draw the completed record sheet SVG to the panel
            gn.paint(g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintComponent((Graphics2D) g, getWidth(), getHeight());
    }
}
