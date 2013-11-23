/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - nwalczak (arlith@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Aero;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import megamek.common.Aero;
import megamek.common.Entity;

/**
 *   This static class defines various construction configuration for Aerospace
 *   fighters.
 * 
 * @author arlith
 *
 */
public class AeroConfig {

    public static int MAX_ENGINE_RATING = 400;
    
    /**
     * Computes the engine rating for the given entity type.
     * 
     * @param entity_type
     * @param tonnage
     * @param desiredSafeThrust
     * @return
     */
    public static int calculateEngineRating(Aero unit, int tonnage, 
            int desiredSafeThrust){
        int rating;
        long eType = unit.getEntityType();
        if (eType == Entity.ETYPE_CONV_FIGHTER){
            rating = (tonnage * desiredSafeThrust);
        } else if (eType == Entity.ETYPE_AERO){
            rating = (tonnage * (desiredSafeThrust - 2));
        } else {
            rating = 0;
        }
        
        if (unit.isPrimitive()){
            double dRating = rating;
            dRating *= 1.2;
            if ((dRating % 5) != 0) {
                dRating = (dRating - (dRating % 5)) + 5;
            }
            rating = (int) dRating;
        }
        return rating;
    }
    
    
    /**
     *  Computes the maximum number of armor points that a given entity type
     *   and tonnage can have.
     *   
     * @param entity_type
     * @param tonnage
     * @return
     */
    public static int maxArmorPoints(long entity_type, double tonnage){
        if (entity_type == Entity.ETYPE_CONV_FIGHTER){
            return (int)(tonnage * 1);
        } else if (entity_type == Entity.ETYPE_AERO){
            return (int)(tonnage * 8);
        } else {
            return 0;
        }
    }

    
}
