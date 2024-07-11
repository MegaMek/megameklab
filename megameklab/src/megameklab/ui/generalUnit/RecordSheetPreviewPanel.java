/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
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

import megamek.common.Entity;
import megameklab.printing.PrintRecordSheet;
import megameklab.printing.RecordSheetOptions;
import megameklab.util.UnitPrintManager;
import org.apache.batik.gvt.GraphicsNode;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.util.List;


/**
 * @author pavelbraginskiy
 *
 * Simply fills itself with the record sheet for the given unit.
 */
public class RecordSheetPreviewPanel extends JPanel {
    private Entity entity;

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set render quality, antialiasing is extremely necessary
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        // White background for image
        g2d.setColor(Color.WHITE);
        g2d.clearRect(0, 0, getWidth(), getHeight());


        if (entity != null) {
            RecordSheetOptions options = new RecordSheetOptions();
            PrintRecordSheet sheet = UnitPrintManager.createSheets(List.of(entity), true, options).get(0);

            // 5-pixel margin around rs
            PageFormat pf = new PageFormat();
            pf.setPaper(options.getPaperSize().createPaper(5, 5, 5, 5));
            sheet.createDocument(0, pf, true);

            GraphicsNode gn = sheet.build();

            // Scale record sheet to the size of the panel, taking into account the 10 pixels taken up by margin
            var bounds = gn.getBounds();
            var yscale = (getHeight() - 10) / bounds.getHeight();
            var xscale = (getWidth() - 10) / bounds.getWidth();
            var scale = Math.min(yscale, xscale);
            gn.setTransform(AffineTransform.getScaleInstance(scale, scale));

            // Draw the completed record sheet SVG to the panel
            gn.paint(g2d);
        }
    }
}
