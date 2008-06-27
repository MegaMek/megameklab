/*
 * MegaMek -
 * Copyright (C) 2000,2001,2002,2003,2004,2005 Ben Mazur (bmazur@sev.org)
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
package megamek.server;

import java.util.Vector;

import megamek.common.Coords;
import megamek.common.IBoard;
import megamek.common.IGame;
import megamek.common.IHex;
import megamek.common.ITerrainFactory;
import megamek.common.PlanetaryConditions;
import megamek.common.Report;
import megamek.common.Terrains;

/*
 * Cycle through hexes on a map and make any necessary adjustments based on weather
 * What will happen here:
 *  - add light and heavy snow for snowfall
 *  - add ice for snowfall, sleet, and ice storm
 *  - add/take away(?) rapids and torrent for winds
 *  - add mud, rapids, and torrent for rain (no that has to be done before play starts so it is in
 *    server.applyBoardSettings()
 *  - put out fires (DONE)
 */

public class WeatherProcessor extends DynamicTerrainProcessor {

    private IGame game;
    Vector<Report> vPhaseReport;
    
    //track turns of snow, sleet, and ice
    //do it this way because we may eventually implement more customizable conditions
    int modSnowTurn = 0;
    int heavySnowTurn = 0;
    int sleetTurn = 0;
    int iceTurn = 0;
    
    public WeatherProcessor(Server server) {
        super(server);
    }

    @Override
    void DoEndPhaseChanges(Vector<Report> vPhaseReport) {
        game = server.getGame();
        this.vPhaseReport = vPhaseReport;
        resolveWeather();
        this.vPhaseReport = null;
        
    }

    /**
     * This debug/profiling function will print the current time
     * (in milliseconds) to the log.  If the boolean is true, the
     * garbage collector will be called in an attempt to minimize
     * timing errors.  You should try and minimize applications
     * being run in the background when using this function.
     * Note that MS Windows only has 10 milisecond resolution.
     *
     * The function should be optimized completely out of the code
     * when the first if-statement below reads "if (false)...", so
     * performance shouldn't be impacted if you leave calls to this
     * function in the code (I think).
     */
    private void debugTime(String s, boolean collectGarbage) {
        //Change the "false" below to "true" to enable this function
        if (false) {
            if (collectGarbage)
                System.gc();
            System.out.println(s + ": " + System.currentTimeMillis());
        }
    }

    private void resolveWeather() {
    	ITerrainFactory tf = Terrains.getTerrainFactory();
        IBoard board = game.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();
        PlanetaryConditions conditions = game.getPlanetaryConditions();
        boolean lightSnow = false;
        boolean deepSnow = false;
        boolean ice = false;
        // Cycle through all hexes, checking for screens
        debugTime("resolve weather 1", true);

        if(conditions.getWeather() == PlanetaryConditions.WE_MOD_SNOW && game.getBoard().onGround()) {
        	modSnowTurn = modSnowTurn + 1;
        	if(modSnowTurn == 9) {
        		lightSnow = true;
        	}
        	if(modSnowTurn == 19) {
        		deepSnow = true;
        		ice = true;
        	}
        }
        if(conditions.getWeather() == PlanetaryConditions.WE_HEAVY_SNOW && game.getBoard().onGround()) {  
        	heavySnowTurn = heavySnowTurn + 1;
        	if(heavySnowTurn == 4) {
        		lightSnow = true;
        	}
        	if(heavySnowTurn == 14) {
        		deepSnow = true;
        	}
        	if(heavySnowTurn == 19) {
        		ice = true;
        	}
        }
        if(conditions.getWeather() == PlanetaryConditions.WE_SLEET && game.getBoard().onGround()) {  
        	sleetTurn = sleetTurn + 1;
        	if(sleetTurn == 14) {
        		ice = true;
        	}
        }
        if(conditions.getWeather() == PlanetaryConditions.WE_ICE_STORM && game.getBoard().onGround()) {  
        	iceTurn = iceTurn + 1;
        	if(iceTurn == 14) {
        		ice = true;
        	}
        }
        
        if(lightSnow) {
        	Report r = new Report(5505, Report.PUBLIC);
            vPhaseReport.addElement(r);
        }
        if(deepSnow) {
        	Report r = new Report(5510, Report.PUBLIC);
            vPhaseReport.addElement(r);
        }
        if(ice) {
        	Report r = new Report(5515, Report.PUBLIC);
            vPhaseReport.addElement(r);
        }
        	
        
        for (int currentXCoord = 0; currentXCoord < width; currentXCoord++ ) {
            for (int currentYCoord = 0; currentYCoord < height; currentYCoord++) {
                Coords currentCoords = new Coords(currentXCoord, currentYCoord);
                IHex currentHex = board.getHex(currentXCoord, currentYCoord);

                //check for fires and potentially put them out
                if (currentHex.containsTerrain(Terrains.FIRE)
                		&& conditions.putOutFire()) {
                	Report r = new Report(5500, Report.PUBLIC);
                    r.add(currentCoords.getBoardNum());
                    vPhaseReport.addElement(r);

                    currentHex.removeTerrain(Terrains.FIRE);
                    server.sendChangedHex(currentCoords);                   
                }   
                
                if(ice && !currentHex.containsTerrain(Terrains.ICE) 
                		&& currentHex.containsTerrain(Terrains.WATER)) {
                	currentHex.addTerrain(tf.createTerrain(Terrains.ICE, currentHex.getElevation()));
                	server.sendChangedHex(currentCoords);
                }
                
                if(lightSnow
                		&& !currentHex.containsTerrain(Terrains.THIN_SNOW) && !currentHex.containsTerrain(Terrains.DEEP_SNOW)
                		&& !(currentHex.containsTerrain(Terrains.WATER) && !currentHex.containsTerrain(Terrains.ICE))) {
                	currentHex.addTerrain(tf.createTerrain(Terrains.THIN_SNOW, currentHex.getElevation()));
                	server.sendChangedHex(currentCoords);
                }
                
                if(deepSnow && !currentHex.containsTerrain(Terrains.DEEP_SNOW) 
                		&& !(currentHex.containsTerrain(Terrains.WATER) && !currentHex.containsTerrain(Terrains.ICE))) {
                	currentHex.addTerrain(tf.createTerrain(Terrains.DEEP_SNOW, currentHex.getElevation()));
                	if(currentHex.containsTerrain(Terrains.THIN_SNOW)) {
                		currentHex.removeTerrain(Terrains.THIN_SNOW);
                	}
                	server.sendChangedHex(currentCoords);
                }
            }
        }
        debugTime("resolve weather 1 end", true);
    }

}
