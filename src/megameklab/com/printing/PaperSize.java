/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.com.printing;

import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import java.awt.print.Paper;

/**
 * A collection of constants used for setting up print jobs
 */
public enum PaperSize {
    US_LETTER (MediaSizeName.NA_LETTER, new MediaPrintableArea(0.25f, 0.25f, 8.0f, 10.5f,
            MediaPrintableArea.INCH), 612, 792, "US Letter",
            "templates_us"),
    ISO_A4 (MediaSizeName.ISO_A4, new MediaPrintableArea(6.35f, 6.35f, 197.2f, 284.3f,
            MediaPrintableArea.MM), 595, 842, "ISO A4",
            "templates_iso");

    public final MediaSizeName sizeName;
    public final MediaPrintableArea printableArea;
    public final int pxWidth;
    public final int pxHeight;
    public final String displayName;
    public final String dirName;

    PaperSize(MediaSizeName sizeName, MediaPrintableArea printableArea, int pxWidth, int pxHeight,
              String displayName, String dirName) {
        this.sizeName = sizeName;
        this.printableArea = printableArea;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        this.displayName = displayName;
        this.dirName = dirName;
    }

    public static PaperSize closestToAspect(double width, double height) {
        final double target = width / height;
        double match = Double.MAX_VALUE;
        PaperSize retVal = US_LETTER;
        for (PaperSize size : values()) {
            double delta = Math.abs(target - (double) size.pxWidth / size.pxHeight);
            if (delta < match) {
                match = delta;
                retVal = size;
            }
        }
        return retVal;
    }

    /**
     * Creates a {@link Paper} instance with 1/4" margins
     * @return A Paper for use with a {@link java.awt.print.PageFormat PageFormat}
     */
    public Paper createPaper() {
        return createPaper(18, 18, 18, 18);
    }

    /**
     * Creates a {@link Paper} instance with the provided margins. Units are in 1/72 inches.
     *
     * @param left   The left margin
     * @param right  The right margin
     * @param top    The top margin
     * @param bottom The bottom margin
     * @return A Paper for use with a {@link java.awt.print.PageFormat PageFormat}
     */
    public Paper createPaper(int left, int top, int right, int bottom) {
        Paper paper = new Paper();
        paper.setSize(pxWidth, pxHeight);
        paper.setImageableArea(left, top, pxWidth - left - right,
                pxHeight - top - bottom);
        return paper;
    }
}
