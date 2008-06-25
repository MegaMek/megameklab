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
 * Weather Conditions and their modifiers
 * Effects of wind are kept separatelyin WindConditions
 */
public class WeatherConditions {
    /*
	public static final String T_NONE        = Messages.getString("WeatherConditions.None");
    public static final String T_LIGHT_RAIN  = Messages.getString("WeatherConditions.LightRain");
    public static final String T_MOD_RAIN    = Messages.getString("WeatherConditions.ModerateRain");
    public static final String T_HEAVY_RAIN  = Messages.getString("WeatherConditions.HeavyRain");
    public static final String T_DOWNPOUR    = Messages.getString("WeatherConditions.TorrentialDownpour");
    public static final String T_LIGHT_SNOW  = Messages.getString("WeatherConditions.LightSnowfall");
    public static final String T_MOD_SNOW    = Messages.getString("WeatherConditions.ModerateSnowfall");
    public static final String T_HEAVY_SNOW  = Messages.getString("WeatherConditions.HeavySnowfall");
    public static final String T_SLEET       = Messages.getString("WeatherConditions.Sleet");
    public static final String T_ICE_STORM   = Messages.getString("WeatherConditions.IceStorm");

    /*
     * to-hit penalty
     */
    /*
    public static int getHitPenalty(String type, Entity en) {
    	if((type.equals(T_LIGHT_RAIN) || type.equals(T_LIGHT_SNOW)) 
    			&& en instanceof Infantry && !(en instanceof BattleArmor)) {
    		return 1;
    	}
    	else if(type.equals(T_MOD_RAIN) || type.equals(T_HEAVY_RAIN)
    			|| type.equals(T_MOD_SNOW) || type.equals(T_HEAVY_SNOW) 
    			|| type.equals(T_SLEET)) {
    		return 1;
    	}
    	else if(type.equals(T_DOWNPOUR)) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
      
    /*
     * piloting penalty
     */
    /*
    public static int getPilotPenalty(String type) {
    	if(type.equals(T_HEAVY_RAIN) || type.equals(T_HEAVY_SNOW)) {
    		return 1;
    	}
    	else if(type.equals(T_DOWNPOUR)) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
    
    //temperature
    //mud, snow, and ice
    */
}