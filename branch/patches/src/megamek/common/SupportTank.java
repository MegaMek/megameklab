/*
 * MegaMek - Copyright (C) 2000-2003 Ben Mazur (bmazur@sev.org)
 * 
 *  This program is free software; you can redistribute it and/or modify it 
 *  under the terms of the GNU General Public License as published by the Free 
 *  Software Foundation; either version 2 of the License, or (at your option) 
 *  any later version.
 * 
 *  This program is distributed in the hope that it will be useful, but 
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 *  for more details.
 */

package megamek.common;

import java.io.Serializable;

/**
 * This is a support vehicle
 * @author beerockxs
 */
public class SupportTank extends Tank implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -9028127010133768714L;
    private int barRating;
    
    public void setBARRating(int rating) {
        barRating = rating;
    }
    
    public int getBARRating() {
        return barRating;
    }

    public boolean hasArmoredChassis() {
        for (Mounted misc : miscList) {
            if (misc.getType().hasFlag(MiscType.F_ARMORED_CHASSIS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tanks have all sorts of prohibited terrain.
     */
    public boolean isHexProhibited(IHex hex) {
        if (hex.containsTerrain(Terrains.IMPASSABLE))
            return true;
        switch (movementMode) {
            case IEntityMovementMode.TRACKED:
                return hex.terrainLevel(Terrains.WOODS) > 1
                        || (hex.terrainLevel(Terrains.WATER) > 0
                                && !hex.containsTerrain(Terrains.ICE)
                                && !hasEnvironmentalSealing())
                        || hex.containsTerrain(Terrains.JUNGLE)
                        || hex.terrainLevel(Terrains.MAGMA) > 1;
            case IEntityMovementMode.WHEELED:
                return hex.containsTerrain(Terrains.WOODS)
                        || hex.containsTerrain(Terrains.ROUGH)
                        || (hex.terrainLevel(Terrains.WATER) > 0
                                && !hex.containsTerrain(Terrains.ICE)
                                && !hasEnvironmentalSealing())
                        || hex.containsTerrain(Terrains.RUBBLE)
                        || hex.containsTerrain(Terrains.MAGMA)
                        || hex.containsTerrain(Terrains.JUNGLE)
                        || hex.containsTerrain(Terrains.DEEP_SNOW)
                        || hex.terrainLevel(Terrains.GEYSER) == 2;
            case IEntityMovementMode.HOVER:
                return hex.containsTerrain(Terrains.WOODS)
                        || hex.containsTerrain(Terrains.JUNGLE)
                        || hex.terrainLevel(Terrains.MAGMA) > 1;
            case IEntityMovementMode.NAVAL:
            case IEntityMovementMode.HYDROFOIL:
                return (hex.terrainLevel(Terrains.WATER) <= 0)
                        || hex.containsTerrain(Terrains.ICE);
            case IEntityMovementMode.SUBMARINE:
                return (hex.terrainLevel(Terrains.WATER) <= 0);
            case IEntityMovementMode.WIGE:
                return (hex.containsTerrain(Terrains.WOODS)
                        || hex.containsTerrain(Terrains.BUILDING));
            default:
                return false;
        }
    }
    
    public boolean hasEnvironmentalSealing() {
        for (Mounted misc : miscList) {
            if (misc.getType().hasFlag(MiscType.F_ENVIRONMENTAL_SEALING)) {
                return true;
            }
        }
        return false;
    }
    
    
}
