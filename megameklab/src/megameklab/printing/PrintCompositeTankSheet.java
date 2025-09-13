/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import megamek.common.units.EntityMovementMode;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megamek.common.annotations.Nullable;
import megameklab.printing.reference.ClusterHitsTable;
import megameklab.printing.reference.DrivingSkillRollMods;
import megameklab.printing.reference.GroundMovementRecord;
import megameklab.printing.reference.GroundToHitMods;
import megameklab.printing.reference.MovementCost;
import megameklab.printing.reference.NotesTable;
import megameklab.printing.reference.ReferenceTable;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Creates a single-page record sheet for two vehicles. If only one vehicle is provided, the bottom half of the sheet
 * contains tables.
 */
public class PrintCompositeTankSheet extends PrintRecordSheet {

    private final Tank tank1;
    private final Tank tank2;

    /**
     * Create a record sheet for two vehicles, or one vehicle and tables.
     *
     * @param tank1     The first vehicle
     * @param tank2     The second vehicle; if {@code null}, fills the bottom half of the page with tables.
     * @param startPage The index of this page in the print job
     * @param options   Options for printing
     */
    public PrintCompositeTankSheet(Tank tank1, @Nullable Tank tank2, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.tank1 = tank1;
        this.tank2 = tank2;
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
    protected @Nullable Document loadTemplate(int pageIndex, PageFormat pageFormat,
          boolean useUnitTestTemplateDirectory) {
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        Document doc = domImpl.createDocument(svgNS, SVGConstants.SVG_SVG_TAG, null);
        Element svgRoot = doc.getDocumentElement();
        svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pageFormat.getWidth()));
        svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pageFormat.getHeight()));
        return doc;
    }

    @Override
    protected void processImage(int startPage, PageFormat pageFormat) {
        double ratio = includeReferenceCharts() ? TABLE_RATIO : 1.0;
        RecordSheetOptions subOptions = new RecordSheetOptions(options);
        subOptions.setReferenceCharts(false);
        Element g;

        // First Sheet
        PrintRecordSheet sheet = new PrintTank(tank1, getFirstPage(), subOptions);
        if (sheet.createDocument(startPage, pageFormat, false)) {
            g = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                  String.format("%s(%f 0 0 %f %f %f)", SVGConstants.SVG_MATRIX_VALUE, ratio,
                        ratio, pageFormat.getImageableX(), pageFormat.getImageableY()));
            sheet.hideElement(FOOTER);
            g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
            getSVGDocument().getDocumentElement().appendChild(g);
        }

        // Second Sheet
        if (tank2 != null) {
            sheet = new PrintTank(tank2, getFirstPage(), subOptions);
        } else if (tank1 instanceof VTOL) {
            sheet = new VTOLTables(options);
        } else {
            sheet = new TankTables(options);
        }

        if (sheet.createDocument(startPage, pageFormat, false)) {
            g = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
            g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                  String.format("%s(%f 0 0 %f %f %f)", SVGConstants.SVG_MATRIX_VALUE, ratio,
                        ratio, pageFormat.getImageableX(),
                        pageFormat.getImageableY() + pageFormat.getImageableHeight() * 0.5 * ratio));
            g.appendChild(getSVGDocument().importNode(sheet.getSVGDocument().getDocumentElement(), true));
            getSVGDocument().getDocumentElement().appendChild(g);
        }

        // Reference Charts
        if (includeReferenceCharts()) {
            addReferenceCharts(pageFormat);
        }
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

    @Override
    protected boolean includeReferenceCharts() {
        return options.showReferenceCharts();
    }

    @Override
    protected List<ReferenceTable> getRightSideReferenceTables() {
        List<ReferenceTable> list = new ArrayList<>();
        list.add(new GroundToHitMods(this, tank1));
        list.add(new MovementCost(this, tank1));
        if (!tank1.getMovementMode().equals(EntityMovementMode.RAIL)
              && !tank1.getMovementMode().equals(EntityMovementMode.MAGLEV)) {
            list.add(new DrivingSkillRollMods(this, tank1));
        } else {
            list.add(new NotesTable(this, 12));
        }
        ClusterHitsTable table = new ClusterHitsTable(this, tank1, false);
        if (table.required()) {
            list.add(table);
        }
        return list;
    }

    @Override
    protected void addReferenceCharts(PageFormat pageFormat) {
        super.addReferenceCharts(pageFormat);
        GroundMovementRecord table = new GroundMovementRecord(this, false, true);
        getSVGDocument().getDocumentElement().appendChild(table.createTable(pageFormat.getImageableX(),
              pageFormat.getImageableY() + pageFormat.getImageableHeight() * TABLE_RATIO + 3.0,
              pageFormat.getImageableWidth() * TABLE_RATIO, pageFormat.getImageableHeight() * 0.2 - 3.0));
    }
}
