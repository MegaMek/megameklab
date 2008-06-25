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

import java.io.Serializable;

/**
 * This class will hold all the information on planetary conditions and a variety of helper functions
 * for those conditions
 */
public class PlanetaryConditions implements Serializable {
    
	private static final long serialVersionUID = 6838624193286089781L;
	
	//What will go here
	//-Terrain Conditions
	//  -atmospheric pressure
	//  -Gravity
	//  -EMI
	//-Weather Conditions
	//   -Light
	//   -Fog
	//   -Weather (Hail, Rain, Snow)
	//   -Wind
	//   -Lightning
	//   -Blowing Sands
	//-Temperature
	
	//light
	public static final int L_DAY          = 0;
    public static final int L_DUSK         = 1;
    public static final int L_FULL_MOON    = 2;
    public static final int L_MOONLESS     = 3;
    public static final int L_PITCH_BLACK  = 4;
    private static String[] lightNames = { "None", "Dusk", "Full Moon Night", "Moonless Night",
    	                                   "Pitch Black"};
    public static final int L_SIZE = lightNames.length;
	
	//Weather 
	public static final int WE_NONE        = 0;
    public static final int WE_LIGHT_RAIN  = 1;
    public static final int WE_MOD_RAIN    = 2;
    public static final int WE_HEAVY_RAIN  = 3;
    public static final int WE_DOWNPOUR    = 4;
    public static final int WE_LIGHT_SNOW  = 5;
    public static final int WE_MOD_SNOW    = 6;
    public static final int WE_HEAVY_SNOW  = 7;
    public static final int WE_SLEET       = 8;
    public static final int WE_ICE_STORM   = 9;
    public static final int WE_LIGHT_HAIL  = 10;
    public static final int WE_HEAVY_HAIL  = 11;
    private static String[] weatherNames = { "None", "Light Rain", "Moderate Rain", "Heavy Rain", "Torrential Downpour"};//,
    	                                    // "Light Snowfall", "Moderate Snowfall", "Heavy Snowfall", "Sleet", "Ice Storm",
    	                                     //"Light Hail", "Heavy Hail"};
    public static final int WE_SIZE = weatherNames.length;

    //Wind
    public static final int WI_NONE        = 0;
    public static final int WI_LIGHT_GALE  = 1;
    public static final int WI_MOD_GALE    = 2;
    public static final int WI_HEAVY_GALE  = 3;
    public static final int WI_STORM       = 4;
    public static final int WI_TORNADO_F13 = 5;
    public static final int WI_TORNADO_F4  = 6;   
    
    //set up the specific conditions
    int lightConditions = WI_NONE;
    int weatherConditions = WE_NONE;
    int windStrength = WI_NONE;
    
    
    private static String[] windNames = { "None", "Light Gale", "Moderate Gale", "Heavy Gale", "Storm", "Tornado F1-F3", "Tornado F4"};
    public static final int WI_SIZE = windNames.length;
    
    
    /**
     * Constructor
     */
    public PlanetaryConditions() {

	}
    
    /** Creates new PlanetaryConditions that is a duplicate of another */
    public PlanetaryConditions(PlanetaryConditions other) {
    	this.lightConditions = other.lightConditions;
    	this.weatherConditions = other.weatherConditions;
    	this.windStrength = other.windStrength;
    }
    
    /** clone! */
    public Object clone() {
        return new PlanetaryConditions(this);
    }
    
    public static String getLightDisplayableName(int type) {
        if (type >= 0 && type < L_SIZE) {
            return Messages.getString("PlanetaryConditions." + lightNames[type]);
        }
        throw new IllegalArgumentException("Unknown light condition");
    }
    
    public static String getWeatherDisplayableName(int type) {
        if (type >= 0 && type < WE_SIZE) {
            return Messages.getString("PlanetaryConditions." + weatherNames[type]);
        }
        throw new IllegalArgumentException("Unknown weather condition");
    }
    
    public static String getWindDisplayableName(int type) {
        if (type >= 0 && type < WI_SIZE) {
            return Messages.getString("PlanetaryConditions." + windNames[type]);
        }
        throw new IllegalArgumentException("Unknown wind condition");
    }
    
    /*
     * to-hit penalty for light
     */
    public static int getLightHitPenalty(int type, boolean isWeapon) {
    	int penalty = 0;
    	if(isWeapon) {
    		if(type == L_DUSK) {
    			penalty = 1;
    		}
    		else if(type == L_FULL_MOON) {
    			penalty = 2;
    		}
    		else if(type == L_MOONLESS) {
    			penalty = 3;
    		}
    		else if(type == L_PITCH_BLACK) {
    			penalty = 4;
    		}
    	}
    	else {
    		if(type == L_MOONLESS) {
    			penalty = 1;
    		}
    		else if(type == L_PITCH_BLACK) {
    			penalty = 2;
    		}
    	}
    	
    	return penalty;
    }
    
    /*
     * heat bonus to hit for being overheated in darkness
     */
    public static int getLightHeatBonus(int type, int heat) {
    	double divisor = 10000.0;
    	if(type == L_DUSK) {
    		divisor = 25.0;
    	}
    	else if(type == L_FULL_MOON) {
    		divisor = 20.0;
    	}
    	else if(type == L_MOONLESS) {
    		divisor = 15.0;
    	}
    	else if(type == L_PITCH_BLACK) {
    		divisor = 10.0;
    	}
    	return (-1 * (int)Math.floor(heat / divisor));
    }
    
    /*
     * piloting penalty for running/flanking/etc for light
     */
    public static int getLightPilotPenalty(int type) {
    	if(type == L_MOONLESS) {
    		return 1;
    	}
    	else if(type == L_PITCH_BLACK) {
    		return 2;
    	}
    	return 0;
    }
    
    /*
     * to-hit penalty for weather
     */
    public static int getWeatherHitPenalty(int type, Entity en) {
    	if((type == WE_LIGHT_RAIN || type == WE_LIGHT_SNOW)	
    			&& en instanceof Infantry && !(en instanceof BattleArmor)) {
    		return 1;
    	}
    	else if(type == WE_MOD_RAIN || type == WE_HEAVY_RAIN
    			|| type == WE_MOD_SNOW || type == WE_HEAVY_SNOW
    			|| type == WE_SLEET) {
    		return 1;
    	}
    	else if(type == WE_DOWNPOUR) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
      
    /*
     * piloting penalty for weather
     */
    public static int getWeatherPilotPenalty(int type) {
    	if(type == WE_HEAVY_RAIN || type == WE_HEAVY_SNOW) {
    		return 1;
    	}
    	else if(type == WE_DOWNPOUR) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
    
    //temperature
    //mud, snow, and ice
    
    public void setLight(int type) {
    	this.lightConditions = type;
    }
    
    public int getLight() {
    	return lightConditions;
    }
    
    public void setWeather(int type) {
    	this.weatherConditions = type;
    }
    
    public int getWeather() {
    	return weatherConditions;
    }
    
    public void setWindStength(int type) {
    	this.windStrength = type;
    }
    
    public int getWindStrength(int type) {
    	return windStrength;
    }
    
    
    
}