/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMek Team
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

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.Entity;
import megamek.common.Jumpship;
import megamek.common.Mech;
import megamek.common.Mounted;
import megamek.common.SpaceStation;
import megamek.common.UnitType;
import megamek.common.Warship;
import megamek.common.WeaponType;
import megameklab.com.printing.PrintRecordSheet.PipType;
import megameklab.com.ui.Aero.Printing.WeaponBayText;
import megameklab.com.ui.Aero.Printing.PrintWarship.ElementType;
import megameklab.com.ui.Aero.Printing.PrintWarship.PrintType;
import megameklab.com.ui.Aero.Printing.PrintWarship.WarshipPrintElements;
import megameklab.com.util.ImageHelper;

/**
 * Generates a record sheet image for jumpships, warships, and space stations.
 * 
 * @author arlith
 * @author Neoancient
 *
 */
public class PrintCapitalShip extends PrintEntity {
    
    /**
     * The ship being printed
     */
    private final Jumpship ship;
    
    // These are some global variables related to equipment printing, to cut down on method signature length
    // Column positions
    private int nameX;
    private int locX;
    private int htX;
    private int srvX;
    private int mrvX;
    private int lrvX;
    private int ervX;
    
    // Equipment rectangle bounds
    private int viewWidth;
    private int viewHeight;
    private int viewX;
    private int viewY;
    
    private PrintType cargoPrintType;
    private PrintType gravPrintType;

    private int eqNormalSize;
    private int eqHeaderSize;
    
    private List<WeaponBayText> capitalWeapTexts;
    private List<WeaponBayText> standardWeapTexts;

    /**
     * Creates an SVG object for the record sheet
     * 
     * @param ship The ship to print
     * @param startPage The print job page number for this sheet
     * @param options Overrides the global options for which elements are printed 
     */
    public PrintCapitalShip(Jumpship ship, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.ship = ship;
    }
    
    /**
     * Creates an SVG object for the record sheet using the global printing options
     * 
     * @param mech The mech to print
     * @param startPage The print job page number for this sheet
     */
    public PrintCapitalShip(Jumpship ship, int startPage) {
        this(ship, startPage, new RecordSheetOptions());
    }

    @Override
    protected Entity getEntity() {
        return ship;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return (loc == Jumpship.LOC_NOSE) || (loc == Jumpship.LOC_AFT);
    }

    @Override
    protected String getSVGFileName() {
        if (ship instanceof Warship) {
            return "warship_default.svg";
        } else if (ship instanceof SpaceStation) {
            return "space_station_default.svg";
        } else {
            return "jumpship_default.svg";
        }
    }

    @Override
    protected String getRecordSheetTitle() {
        return UnitType.getTypeDisplayableName(ship.getUnitType())
                + " Record Sheet";
    }
    
    @Override
    public void printImage(Graphics2D g2d, PageFormat pageFormat, int pageNum) {
        super.printImage(g2d, pageFormat, pageNum);
    }
}
