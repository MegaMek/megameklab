/*
 * MekBuilder - unit design companion of MegaMek
 * Copyright (C) 2017 The MegaMek Team
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

import megamek.common.*;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGRectElement;

import java.awt.print.PageFormat;

/**
 * Lays out record sheet for non-capital aerospace units
 */
public class PrintAero extends PrintEntity {

    /**
     * The aerospace unit being printed
     */
    private final Aero aero;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param aero      The aerospace unit to print
     * @param startPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    public PrintAero(Aero aero, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.aero = aero;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param aero      The aerospace unit to print
     * @param startPage The print job page number for this sheet
     */
    public PrintAero(Aero aero, int startPage) {
        this(aero, startPage, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        return "fighter_aerospace_default.svg";
    }

    @Override
    protected String getRecordSheetTitle() {
        StringBuilder sb = new StringBuilder();
        if (aero instanceof SmallCraft) {
            if (aero.isSpheroid()) {
                sb.append("Spheroid");
            } else {
                sb.append("Aerodyne");
            }
            if (aero instanceof Dropship) {
                sb.append(" Dropship");
            } else {
                sb.append(" Small Craft");
            }
        } else if (aero.isSupportVehicle()) {
            if (aero.getMovementMode().equals(EntityMovementMode.STATION_KEEPING)) {
                sb.append("Satellite Support Vehicle");
            } else if (aero.getMovementMode().equals(EntityMovementMode.AIRSHIP)) {
                sb.append("Airship Support Vehicle");
            } else {
                sb.append("Fixed Wing Support Vehicle");

            }
        } else if (aero instanceof ConvFighter) {
            sb.append("Conventional Fighter");
        } else if (aero.isOmni()) {
            sb.append("OmniFighter");
        } else {
            sb.append("Aerospace Fighter");
        }
        sb.append(" Record Sheet");
        return sb.toString();
    }

    @Override
    protected Entity getEntity() {
        return aero;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return loc == Aero.LOC_NOSE || loc == Aero.LOC_AFT;
    }

    @Override
    public void processImage(int pageNum, PageFormat pageFormat) {
        super.processImage(pageNum, pageFormat);
        if (aero.tracksHeat()) {
            Element hsRect = getSVGDocument().getElementById(HEAT_SINK_PIPS);
            if (hsRect instanceof SVGRectElement) {
                drawHeatSinkPips((SVGRectElement) hsRect, aero.getHeatSinks());
            }
        }
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();
        setTextField(HS_TYPE, formatHeatSinkType());
        setTextField(HS_COUNT, formatHeatSinkCount());
    }

    @Override
    protected void drawArmorStructurePips() {
        super.drawArmorStructurePips();
        Element element = getSVGDocument().getElementById(SI_PIPS);
        if (null != element) {
            ArmorPipLayout.addPips(this, element, aero.get0SI(),
                    PipType.CIRCLE, 0.5);
        }
    }

    @Override
    void writeArmorStructureTextFields() {
        final String FORMAT = "%d ( %d )";
        for (int loc = firstArmorLocation(); loc < getEntity().locations(); loc++) {
            setTextField(TEXT_ARMOR + getEntity().getLocationAbbr(loc),
                    String.format(FORMAT, aero.getThresh(loc), getEntity().getOArmor(loc)));
        }
        setTextField(TEXT_SI, aero.get0SI());
    }

    private String formatHeatSinkType() {
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return "Double Heat Sinks:";
        } else {
            return "Heat Sinks:";
        }
    }

    private String formatHeatSinkCount() {
        int hsCount = aero.getHeatSinks();
        if (aero.getHeatType() == Aero.HEAT_DOUBLE) {
            return String.format("%d (%d)", hsCount, hsCount * 2);
        } else {
            return Integer.toString(hsCount);
        }
    }


}