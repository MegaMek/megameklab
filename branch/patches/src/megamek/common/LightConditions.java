/*
 * MegaMek - Copyright (C) 2005 Ben Mazur (bmazur@sev.org)
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

/**
 * Light Conditions and their modifiers
 */
public class LightConditions {
    
    public static final String T_DAYLIGHT     = Messages.getString("LightConditions.Daylight");
    public static final String T_DUSK         = Messages.getString("LightConditions.Dusk");
    public static final String T_FULL_MOON    = Messages.getString("LightConditions.FullMoon");
    public static final String T_MOONLESS     = Messages.getString("LightConditions.Moonless");
    public static final String T_PITCH_BLACK  = Messages.getString("LightConditions.PitchBlack");

    /*
     * to-hit penalty
     */
    public static int getHitPenalty(String type, boolean isWeapon) {
    	int penalty = 0;
    	if(isWeapon) {
    		if(type.equals(T_DUSK)) {
    			penalty = 1;
    		}
    		else if(type.equals(T_FULL_MOON)) {
    			penalty = 2;
    		}
    		else if(type.equals(T_MOONLESS)) {
    			penalty = 3;
    		}
    		else if(type.equals(T_PITCH_BLACK)) {
    			penalty = 4;
    		}
    	}
    	else {
    		if(type.equals(T_MOONLESS)) {
    			penalty = 1;
    		}
    		else if(type.equals(T_PITCH_BLACK)) {
    			penalty = 2;
    		}
    	}
    	
    	return penalty;
    }
    
    /*
     * heat penalty for being overheated
     */
    public static int getHeatBonus(String type, int heat) {
    	double divisor = 10000.0;
    	if(type.equals(T_DUSK)) {
    		divisor = 25.0;
    	}
    	else if(type.equals(T_FULL_MOON)) {
    		divisor = 20.0;
    	}
    	else if(type.equals(T_MOONLESS)) {
    		divisor = 15.0;
    	}
    	else if(type.equals(T_PITCH_BLACK)) {
    		divisor = 10.0;
    	}
    	return (-1 * (int)Math.floor(heat / divisor));
    }
    
    /*
     * piloting penalty for running/flanking/etc
     */
    public static int getPilotPenalty(String type) {
    	if(type.equals(T_MOONLESS)) {
    		return 1;
    	}
    	else if(type.equals(T_PITCH_BLACK)) {
    		return 2;
    	}
    	return 0;
    }
    
    /*
     * additional movement costs
     */
    /*
     * TODO: awaiting a ruling on whether these are supposed to be on table in TacOps, pg. 36
    public static int getMovementCost(String type) {
    	if(type.equals(T_FULL_MOON)) {
    		return 1;
    	} 
    	else if(type.equals(T_MOONLESS)) {
    		return 2;
    	}
    	else if(type.equals(T_PITCH_BLACK)) {
    		return 3;
    	}
    	return 0;
    }
    */
    
    
    
}