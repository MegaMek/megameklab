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
    private static String[] lightNames = { "Daylight", "Dusk", "Full Moon Night", "Moonless Night",
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
    private static String[] weatherNames = { "None", "Light Rain", "Moderate Rain", "Heavy Rain", "Torrential Downpour",
    	                                     "Light Snowfall", "Moderate Snowfall", "Heavy Snowfall", "Sleet", "Ice Storm"};//,
    	                                     //"Light Hail", "Heavy Hail"};
    public static final int WE_SIZE = weatherNames.length;

    //Wind
    public static final int WI_NONE        = 0;
    public static final int WI_LIGHT_GALE  = 1;
    public static final int WI_MOD_GALE    = 2;
    public static final int WI_STRONG_GALE = 3;
    public static final int WI_STORM       = 4;
    public static final int WI_TORNADO_F13 = 5;
    public static final int WI_TORNADO_F4  = 6;   
    
    private static String[] windNames = { "Calm", "Light Gale", "Moderate Gale", "Strong Gale", "Storm", "Tornado F1-F3", "Tornado F4"};
    public static final int WI_SIZE = windNames.length;
    
    //wind direction  
    private static String[] dirNames = { "North", "Northeast", "Southeast", "South","Southwest", "Northwest" };
    public static final int DIR_SIZE = dirNames.length;
    
    //atmospheric pressure
    public static final int ATMO_VACUUM   = 0;
    public static final int ATMO_TRACE    = 1;
    public static final int ATMO_THIN     = 2;
    public static final int ATMO_STANDARD = 3;
    public static final int ATMO_HIGH     = 4;
    public static final int ATMO_VHIGH    = 5;
    
    private static String[] atmoNames = {"Vacuum", "Trace", "Thin", "Standard", "High", "Very High" };
    public static final int ATMO_SIZE = atmoNames.length;
    
    //set up the specific conditions
    private int lightConditions = WI_NONE;
    private int weatherConditions = WE_NONE;
    private int windStrength = WI_NONE;
    private int windDirection = -1;
    private boolean shiftWindDirection = false;
    private boolean shiftWindStrength = false;
    private int atmosphere = ATMO_STANDARD;
    private int temperature = 25;
    
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
    	this.windDirection = other.windDirection;
    	this.shiftWindDirection = other.shiftWindDirection;
    	this.shiftWindStrength = other.shiftWindStrength;
    	this.atmosphere = other.atmosphere;
    	this.temperature = other.temperature;
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
    
    public static String getAtmosphereDisplayableName(int type) {
        if (type >= 0 && type < ATMO_SIZE) {
            return Messages.getString("PlanetaryConditions." + atmoNames[type]);
        }
        throw new IllegalArgumentException("Unknown atmospheric pressure condition");
    }
    
    public String getWindDirName() {
            return dirNames[windDirection];
    }
    
    public String getLightCurrentName() {
    	return Messages.getString("PlanetaryConditions." + lightNames[lightConditions]);
    }
    
    public String getWeatherCurrentName() {
    	return Messages.getString("PlanetaryConditions." + weatherNames[weatherConditions]);
    }
    
    public String getWindCurrentName() {
    	return Messages.getString("PlanetaryConditions." + windNames[getWindStrength(true)]);
    }
    
    public String getAtmosphereCurrentName() {
    	return Messages.getString("PlanetaryConditions." + atmoNames[atmosphere]);
    }
    
    /*
     * to-hit penalty for light
     */
    public int getLightHitPenalty(boolean isWeapon) {
    	int penalty = 0;
    	if(isWeapon) {
    		if(lightConditions == L_DUSK) {
    			penalty = 1;
    		}
    		else if(lightConditions == L_FULL_MOON) {
    			penalty = 2;
    		}
    		else if(lightConditions == L_MOONLESS) {
    			penalty = 3;
    		}
    		else if(lightConditions == L_PITCH_BLACK) {
    			penalty = 4;
    		}
    	}
    	else {
    		if(lightConditions == L_MOONLESS) {
    			penalty = 1;
    		}
    		else if(lightConditions == L_PITCH_BLACK) {
    			penalty = 2;
    		}
    	}
    	
    	return penalty;
    }
    
    /*
     * heat bonus to hit for being overheated in darkness
     */
    public int getLightHeatBonus(int heat) {
    	double divisor = 10000.0;
    	if(lightConditions == L_DUSK) {
    		divisor = 25.0;
    	}
    	else if(lightConditions == L_FULL_MOON) {
    		divisor = 20.0;
    	}
    	else if(lightConditions == L_MOONLESS) {
    		divisor = 15.0;
    	}
    	else if(lightConditions == L_PITCH_BLACK) {
    		divisor = 10.0;
    	}
    	return (-1 * (int)Math.floor(heat / divisor));
    }
    
    /*
     * piloting penalty for running/flanking/etc for light
     */
    public int getLightPilotPenalty() {
    	if(lightConditions == L_MOONLESS) {
    		return 1;
    	}
    	else if(lightConditions == L_PITCH_BLACK) {
    		return 2;
    	}
    	return 0;
    }
    
    /*
     * to-hit penalty for weather
     */
    public int getWeatherHitPenalty(Entity en) {
    	if((weatherConditions == WE_LIGHT_RAIN || weatherConditions == WE_LIGHT_SNOW)	
    			&& en instanceof Infantry && !(en instanceof BattleArmor)) {
    		return 1;
    	}
    	else if(weatherConditions == WE_MOD_RAIN || weatherConditions == WE_HEAVY_RAIN
    			|| weatherConditions == WE_MOD_SNOW || weatherConditions == WE_HEAVY_SNOW
    			|| weatherConditions == WE_SLEET) {
    		return 1;
    	}
    	else if(weatherConditions == WE_DOWNPOUR) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
      
    /*
     * piloting penalty for weather
     */
    public int getWeatherPilotPenalty() {
    	if(weatherConditions == WE_HEAVY_RAIN || weatherConditions == WE_HEAVY_SNOW) {
    		return 1;
    	}
    	else if(weatherConditions == WE_DOWNPOUR) {
    		return 2;
    	}
    	else  {
    		return 0;
    	}
    }
    
    /*
     * piloting penalty for wind
     */
    public int getWindPilotPenalty(Entity en) {
    	int penalty = 0;
    	
    	switch(getWindStrength(true)) {
    	case (WI_MOD_GALE):
    		if(en instanceof VTOL || en.getMovementMode() == IEntityMovementMode.WIGE) {
    			penalty = 1;
    		}
    		break;
    	case (WI_STRONG_GALE):
    		if(en instanceof VTOL || en.getMovementMode() == IEntityMovementMode.WIGE 
    				|| en.getMovementMode() == IEntityMovementMode.HOVER) {
    			penalty = 2;
    		}
    		else if(en instanceof Mech || en instanceof Aero) {
    			penalty = 1;
    		}	
    		break;
    	case (WI_STORM):
    		if(en instanceof VTOL || en instanceof Mech || en.getMovementMode() == IEntityMovementMode.WIGE 
    				|| en.getMovementMode() == IEntityMovementMode.HOVER) {
    			penalty = 3;
    		}
    		else if(en instanceof Aero) {
    			penalty = 2;
    		}
    		break;
    	case (WI_TORNADO_F13):
    		penalty = 3;
    		break;
    	case (WI_TORNADO_F4):
    		penalty = 5;
    		break;
    	default:
    		penalty = 0;
    	}
    	return penalty;
    }
    
    public void determineWind() {  
        if (windDirection == -1) {
            // Initial wind direction. If using level 2 rules, this
            // will be the wind direction for the whole battle.
            windDirection = Compute.d6(1) - 1;
        } else if (shiftWindDirection) {
            // Wind direction changes on a roll of 1 or 6
            switch (Compute.d6()) {
                case 1: // rotate clockwise
                    windDirection = (windDirection + 1) % 6;
                    break;
                case 6: // rotate counter-clockwise
                    windDirection = (windDirection + 5) % 6;
            }
        }
        if (shiftWindStrength) {
        	// Wind strength changes on a roll of 1 or 6
        	switch (Compute.d6()) {
        	case 1: // weaker
        		if (windStrength > 0)
        			windStrength--;
        		break;
        	case 6: // stronger
        		if (windStrength < WI_SIZE)
        			windStrength++;
        	}
        }
    }
    
    /*
     * modifiers for fire ignition
     */
    public int getIgniteModifiers() {
    	int mod = 0;
    	
    	if(weatherConditions == WE_LIGHT_RAIN || weatherConditions == WE_MOD_RAIN) {
    		mod += 1;
    	}
    	if(weatherConditions == WE_HEAVY_RAIN || weatherConditions == WE_DOWNPOUR
    			|| weatherConditions == WE_LIGHT_SNOW || weatherConditions == WE_MOD_SNOW) {
    		mod += 2;
    	}
    	if(weatherConditions == WE_HEAVY_SNOW || weatherConditions == WE_LIGHT_HAIL || weatherConditions == WE_HEAVY_HAIL) {
    		mod += 3;
    	}
    	if(getWindStrength(true) == WI_LIGHT_GALE || getWindStrength(true) == WI_MOD_GALE) {
    		mod += 2;
    	}
    	if(getWindStrength(true) == WI_STRONG_GALE || getWindStrength(true) == WI_STORM || weatherConditions == WE_ICE_STORM) {
    		mod += 4;
    	}
    	mod += getTemperatureDifference(30,-30);
    	
    	return mod;
    }

    /*
     * Do a roll for these weather conditions putting out fire
     * return boolean 
     */
    public boolean putOutFire() {  		
    	int roll = Compute.d6(2);
    	switch(weatherConditions) {
    	case(WE_LIGHT_HAIL):
    	case(WE_LIGHT_RAIN):
    	case(WE_LIGHT_SNOW):
    		roll = roll + 1;
    		break;
    	case(WE_HEAVY_HAIL):
    	case(WE_MOD_RAIN):
    	case(WE_MOD_SNOW):
    		roll = roll + 2;
    		break;
    	case(WE_HEAVY_RAIN):
    	case(WE_HEAVY_SNOW):
    		roll = roll + 3;
    		break;
    	case(WE_DOWNPOUR):
    		roll = roll + 4;
    		break;
    	default:
    		roll = -1;
    	}
    	
    	if(roll > 10) {
    		return true;
    	}
    	
      	return false;
    }
    
    /**
     * Returns how much higher or lower than a given range, divided by
     * ten, rounded up, the temperature is
     */

    public int getTemperatureDifference(int high, int low) {
        int i = 0;
        //if the low is more than the high, reverse
        if(low > high) {
        	int tempLow = low;
        	low = high;
        	high = tempLow;
        	
        }
        if (getTemperature() >= low
                && getTemperature() <= high)
            return i;
        else if (getTemperature() < low) {
            do {
                i++;
            } while (getTemperature() + i * 10 < low);
            return i;
        } else
            do {
                i++;
            } while (getTemperature() - i * 10 > high);
        return i;
    }
    
    public boolean canStartFire() {
    	if(getWindStrength(true) > WI_STORM) {
    		return false;
    	}
    	if(atmosphere < ATMO_THIN) {
    		return false;
    	}
    	return true;   	
    }
    
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
    
    public void setWindStrength(int type) {
    	this.windStrength = type;
    }
    
    public int getWindStrength(boolean adjust) {
    	int strength = windStrength;
    	//in some cases I need to adjust this by the atmospheric pressure
    	//calm wind is always calm 
    	if(strength == WI_NONE) {
    		return strength;
    	}
    	
    	int mod = 0;
    	switch(atmosphere) {
    	case(ATMO_VACUUM):
    		//no wind in a vacuum, right?
    		mod = ATMO_SIZE;
    		break;
    	case(ATMO_TRACE):
    		mod = -2;
    		break;
    	case(ATMO_THIN):
    		mod = -1;
    		break;
    	case(ATMO_HIGH):
    		mod = 1;
    		break;
    	case(ATMO_VHIGH):
    		mod = 2;
    		break;
    	default:
    		mod = 0;
    	}
    	strength = strength + mod;
    	if(strength < WI_NONE) {
    		strength = WI_NONE;
    	}
    	if(strength > WI_TORNADO_F4) {
    		strength = WI_TORNADO_F4;
    	}
    	return strength;
    }
    
    public void setWindDirection(int type) {
    	this.windDirection = type;
    }
    
    public int getWindDirection() {
    	return windDirection;
    }
    
    public void setShiftingWindDirection(boolean b) {
    	this.shiftWindDirection = b;
    }
    
    public boolean shiftingWindDirection() {
    	return shiftWindDirection;
    }
    
    public void setShiftingWindStrength(boolean b) {
    	this.shiftWindStrength = b;
    }
    
    public boolean shiftingWindStrength() {
    	return shiftWindStrength;
    }
    
    public void setAtmosphere(int a) {
    	this.atmosphere = a;
    }
    
    public int getAtmosphere() {
    	return atmosphere;
    }
    
    public void setTemperature(int tem) {
    	this.temperature = tem;
    }
    
    public int getTemperature() {
    	return temperature;
    }   
    
    public boolean isVacuum() {
    	return atmosphere == ATMO_VACUUM || atmosphere == ATMO_TRACE;
    }
}