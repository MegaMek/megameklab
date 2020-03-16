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

import megamek.common.Entity;
import megamek.common.Infantry;

/**
 * Lays out a record sheet block for a single infantry unit
 */
public class PrintInfantry extends PrintEntity {

    private final Infantry infantry;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param infantry The infantry to print
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed
     */
    public PrintInfantry(Infantry infantry, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.infantry = infantry;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param infantry The infantry to print
     * @param startPage The print job page number for this sheet
     */
    public PrintInfantry(Infantry infantry, int startPage) {
        this(infantry, startPage, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "conventional_infantry_platoon.svg";
    }


    @Override
    protected Entity getEntity() {
        return infantry;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return false;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }
}
