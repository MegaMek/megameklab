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
package megameklab.com.printing.reference;

import megameklab.com.printing.PrintMech;
import org.apache.batik.util.SVGConstants;

/**
 * Terrain movement cost table for meks
 */
public class MekTerrainTable extends ReferenceTable {

    public MekTerrainTable(PrintMech sheet, boolean advancedTerrain) {
        super(sheet, "TERRAIN", 0.1, 0.8);
        setFirstColAnchor(SVGConstants.SVG_START_VALUE);
        if (advancedTerrain) {
            addRow("Clear, Planted Fields,\nTundra, Sand", "+0");
        } else {
            addRow("Clear", "+0");
        }
        addRow("Paved/Bridge/Road", "+0");
        addRow("Rough", "+1");
        if (advancedTerrain) {
            addRow("Ultra-Rough", "+1");
        }
        addRow("Light Woods", "+1");
        if (advancedTerrain) {
            addRow("Heavy Woods, Light Jungle", "+2");
            addRow("Ultra-Heavy Woods,\nHeavy Jungle", "+3");
            addRow("Ultra-Heavy Jungle", "+4");
            addRow("Magma Crust", "+0");
            addRow("Magma, Liquid", "+2†");
        } else {
            addRow("Heavy Woods", "+2");
        }
        addRow("Water, Depth 0", "+0");
        addRow("Water, Depth 1", "+1*");
        addRow("Water, Depth 2+", "+3*");
        addRow("Rubble", "+1*");
        if (advancedTerrain) {
            addRow("Ultra Rubble", "+2*");
        }
        addRow("Light Building", "+1†");
        addRow("Medium Building", "+2†");
        addRow("Heavy Building", "+3†");
        addRow("Hardened Building", "+4†");
        addNote("*PSR required to prevent falling");
        addNote("†PSR required to prevent taking damage");
    }
}
