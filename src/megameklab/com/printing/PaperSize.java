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

/**
 * A collection of constants used for setting up print jobs
 */
public enum PaperSize {
    US_LETTER (MediaSizeName.NA_LETTER, new MediaPrintableArea(0, 0, 8.5f, 11, MediaPrintableArea.INCH),
            612, 792, "US Letter"),
    ISO_A4 (MediaSizeName.ISO_A4, new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM),
            595, 842, "ISO A4");

    public final MediaSizeName sizeName;
    public final MediaPrintableArea printableArea;
    public final int pxWidth;
    public final int pxHeight;
    public final String displayName;

    PaperSize(MediaSizeName sizeName, MediaPrintableArea printableArea, int pxWidth, int pxHeight,
              String displayName) {
        this.sizeName = sizeName;
        this.printableArea = printableArea;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        this.displayName = displayName;
    }
}
