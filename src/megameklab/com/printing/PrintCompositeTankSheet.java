/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package megameklab.com.printing;

import megamek.common.Tank;
import megamek.common.VTOL;
import megamek.common.annotations.Nullable;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates a single-page record sheet for two vehicles. If only one vehicle is provided,
 * the bottom half of the sheet contains tables.
 */
public class PrintCompositeTankSheet extends PrintRecordSheet {

    private final Tank tank1;
    private final Tank tank2;

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables.
     *
     * @param tank1      The first vehicle
     * @param tank2      The second vehicle; if {@code null}, fills the bottom half of the page
     *                   with tables.
     * @param startPage  The index of this page in the print job
     * @param options    Options for printing
     */
    public PrintCompositeTankSheet(Tank tank1, @Nullable Tank tank2, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.tank1 = tank1;
        this.tank2 = tank2;
    }

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables, with default
     * options
     *
     * @param tank1      The first vehicle
     * @param tank2      The second vehicle; if {@code null}, fills the bottom half of the page
     *                   with tables.
     * @param startPage  The index of this page in the print job
     */
    public PrintCompositeTankSheet(Tank tank1, @Nullable Tank tank2, int startPage) {
        this(tank1, tank2, startPage, new RecordSheetOptions());
    }

    @Override
    public List<String> getBookmarkNames() {
        List<String> retVal = new ArrayList<>();
        retVal.add(tank1.getShortNameRaw());
        if ((tank2 != null) && !tank2.getShortNameRaw().equals(tank1.getShortNameRaw())) {
            retVal.add(tank2.getShortNameRaw());
        }
        return retVal;
    }

    @Override
    Document loadTemplate(int pageIndex, PageFormat pageFormat) {
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        Document doc = domImpl.createDocument(svgNS, SVGConstants.SVG_SVG_TAG, null);
        Element svgRoot = doc.getDocumentElement();
        svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pageFormat.getWidth()));
        svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pageFormat.getHeight()));
        return doc;
    }

    @Override
    protected void processImage(int startPage, PageFormat pageFormat) {
        PrintRecordSheet sheet = new PrintTank(tank1, getFirstPage(), options);
        sheet.createDocument(startPage, pageFormat);
        Element g = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(0 %f)", SVGConstants.SVG_TRANSLATE_VALUE, pageFormat.getImageableY()));
        sheet.hideElement(FOOTER);
        g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
        getSVGDocument().getDocumentElement().appendChild(g);

        if (tank2 != null) {
            sheet = new PrintTank(tank2, getFirstPage(), options);
        } else if (tank1 instanceof VTOL) {
            sheet = new VTOLTables(options);
        } else {
            sheet = new TankTables(options);
        }
        sheet.createDocument(startPage, pageFormat);
        g = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                String.format("%s(0 %f)", SVGConstants.SVG_TRANSLATE_VALUE,
                        pageFormat.getImageableHeight() * 0.5));
        g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
        getSVGDocument().getDocumentElement().appendChild(g);
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        // Not used by composite sheet
        return "";
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used by composite sheet
        return "";
    }

    private static class TankTables extends PrintRecordSheet {

        TankTables(RecordSheetOptions options) {
            super(0, options);
        }

        @Override
        protected String getSVGFileName(int pageNumber) {
            return "tables_tank.svg";
        }

        @Override
        protected String getRecordSheetTitle() {
            return "";
        }

        @Override
        public List<String> getBookmarkNames() {
            return Collections.emptyList();
        }
    }

    private static class VTOLTables extends PrintRecordSheet {

        VTOLTables(RecordSheetOptions options) {
            super(0, options);
        }

        @Override
        protected String getSVGFileName(int pageNumber) {
            return "tables_vtol.svg";
        }

        @Override
        protected String getRecordSheetTitle() {
            return "";
        }

        @Override
        public List<String> getBookmarkNames() {
            return Collections.emptyList();
        }
    }
}
