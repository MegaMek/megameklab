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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import megamek.common.Building;
import megamek.common.Compute;
import megamek.common.Coords;
import megamek.common.Entity;
import megamek.common.IBoard;
import megamek.common.IGame;
import megamek.common.IHex;
import megamek.common.PlanetaryConditions;
import megamek.common.Report;
import megamek.common.TargetRoll;
import megamek.common.Terrains;

public class FireProcessor extends DynamicTerrainProcessor {

    private IGame game;
    Vector<Report> vPhaseReport;

    public FireProcessor(Server server) {
        super(server);
    }

    @Override
    void DoEndPhaseChanges(Vector<Report> vPhaseReport) {
        game = server.getGame();
        if (game.getOptions().booleanOption("tacops_start_fire")) {
            this.vPhaseReport = vPhaseReport;
            resolveFire();
            this.vPhaseReport = null;
        }
    }

    /**
     * This debug/profiling function will print the current time (in
     * milliseconds) to the log. If the boolean is true, the garbage collector
     * will be called in an attempt to minimize timing errors. You should try
     * and minimize applications being run in the background when using this
     * function. Note that MS Windows only has 10 milisecond resolution. The
     * function should be optimized completely out of the code when the first
     * if-statement below reads "if (false)...", so performance shouldn't be
     * impacted if you leave calls to this function in the code (I think).
     */
    private void debugTime(String s, boolean collectGarbage) {
        // Change the "false" below to "true" to enable this function
        if (false) {
            if (collectGarbage)
                System.gc();
            System.out.println(s + ": " + System.currentTimeMillis());
        }
    }

    /**
     * Make fires spread, smoke spread, and make sure that all fires started
     * this turn are marked as "burning" for next turn. A "FIRE" terrain has one
     * of two levels: 1 (Created this turn, and so can't spread of generate
     * heat) 2 (Created as a result of spreading fire or on a previous turn)
     * Since fires created at end of turn act normally in the following turn,
     * spread fires have level 2. At NO TIME should any fire created outside
     * this function have a level of 2, nor should anything except this function
     * SET fires to level 2. Newly created "spread" fires have a level of 1, so
     * that they do not spread in the turn they are created. After all spreading
     * has been completed, all burning hexes are set to level 2.
     */
    private void resolveFire() {
        IBoard board = game.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();
        int windDirection = game.getPlanetaryConditions().getWindDirection();
        int windStrength = game.getPlanetaryConditions().getWindStrength();
        Report r;

        // Get the position map of all entities in the game.
        Hashtable<Coords, Vector<Entity>> positionMap = game.getPositionMap();

        // Build vector to send for updated buildings at once.
        Vector<Building> burningBldgs = new Vector<Building>();

        // process smoke FIRST, before any fires spread or
        // smoke is produced.
        resolveSmoke();

        // Cycle through all buildings, checking for fire.
        // ASSUMPTION: buildings don't lose 2 CF on the turn a fire starts.
        // ASSUMPTION: multi-hex buildings lose 2 CF max, regardless of # fires
        Enumeration<Building> buildings = game.getBoard().getBuildings();
        while (buildings.hasMoreElements()) {
            Building bldg = buildings.nextElement();
            if (bldg.isBurning()) {
                int cf = Math.max(bldg.getCurrentCF() - 2, 0);
                bldg.setCurrentCF(cf);

                // Does the building burned down?
                if (cf == 0) {
                    r = new Report(5120, Report.PUBLIC);
                    r.add(bldg.getName());
                    vPhaseReport.addElement(r);
                    server.collapseBuilding(bldg, positionMap);
                }

                // If it doesn't collapse under its load, mark it for update.
                else if (!server.checkForCollapse(bldg, positionMap)) {
                    bldg.setPhaseCF(cf);
                    burningBldgs.addElement(bldg);
                }
            }
        }

        debugTime("resolve fire 1", true);

        // Cycle through all hexes, checking for fire.
        for (int currentXCoord = 0; currentXCoord < width; currentXCoord++) {

            for (int currentYCoord = 0; currentYCoord < height; currentYCoord++) {
                Coords currentCoords = new Coords(currentXCoord, currentYCoord);
                IHex currentHex = board.getHex(currentXCoord, currentYCoord);
                boolean infernoBurning = board.burnInferno(currentCoords);

                // optional rule, woods burn down
                if ((currentHex.containsTerrain(Terrains.WOODS) || currentHex
                        .containsTerrain(Terrains.JUNGLE))
                        && currentHex.terrainLevel(Terrains.FIRE) == 2
                        && game.getOptions().booleanOption("woods_burn_down")) {
                    burnDownWoods(currentCoords);
                }

                // If the woods has been cleared, or the building
                // has collapsed put non-inferno fires out.
                if (currentHex.containsTerrain(Terrains.FIRE)
                        && !infernoBurning
                        && !(currentHex.containsTerrain(Terrains.WOODS))
                        && !(currentHex.containsTerrain(Terrains.JUNGLE))
                        && !(currentHex.containsTerrain(Terrains.FUEL_TANK))
                        && !(currentHex.containsTerrain(Terrains.BUILDING))) {
                    server.removeFire(currentXCoord, currentYCoord, currentHex);
                }

                // Was the fire started on a previous turn?
                else if (currentHex.terrainLevel(Terrains.FIRE) == 2) {
                    r = new Report(5125, Report.PUBLIC);
                    if (infernoBurning)
                        r.messageId = 5130;
                    r.add(currentCoords.getBoardNum());
                    vPhaseReport.addElement(r);
                    spreadFire(currentXCoord, currentYCoord, windDirection, windStrength);
                }
            }
        }

        debugTime("resolve fire 1 end, begin resolve fire 2", true);

        // Loop a second time, to set all fires to level 2 before next turn, and
        // add smoke.
        for (int currentXCoord = 0; currentXCoord < width; currentXCoord++) {

            for (int currentYCoord = 0; currentYCoord < height; currentYCoord++) {
                Coords currentCoords = new Coords(currentXCoord, currentYCoord);
                IHex currentHex = board.getHex(currentXCoord, currentYCoord);
                // if the fire in the hex was started this turn
                if (currentHex.terrainLevel(Terrains.FIRE) == 1) {
                    currentHex.removeTerrain(Terrains.FIRE);
                    currentHex.addTerrain(Terrains.getTerrainFactory()
                            .createTerrain(Terrains.FIRE, 2));
                    server.sendChangedHex(currentCoords);
                    // fire started this round
                    r = new Report(5135, Report.PUBLIC);
                    r.add(currentCoords.getBoardNum());
                    vPhaseReport.addElement(r);

                    // If the hex contains a building, set it on fire.
                    Building bldg = game.getBoard()
                            .getBuildingAt(currentCoords);
                    if (bldg != null) {
                        bldg.setBurning(true);
                        burningBldgs.addElement(bldg);
                    }
                }
                // If the L3 smoke rule is off, add smoke normally, otherwise
                // call the L3 method
                //If we are in a tornado then no smoke at all
                boolean isTornado = (game.getPlanetaryConditions().getWindStrength() > PlanetaryConditions.WI_STORM); 
                if (currentHex.containsTerrain(Terrains.FIRE) && !isTornado) {
                    server.addSmoke(currentXCoord, currentYCoord, windDirection);
                    server.addSmoke(currentXCoord, currentYCoord,
                            (windDirection + 1) % 6);
                    server.addSmoke(currentXCoord, currentYCoord,
                            (windDirection + 5) % 6);
                    board.initializeAround(currentXCoord, currentYCoord);
                }
            }
        }

        debugTime("resolve fire 2 end", false);

        // If any buildings are burning, update the clients.
        if (!burningBldgs.isEmpty()) {
            server.sendChangedCFBuildings(burningBldgs);
        }

    } // End the ResolveFire() method

    public void burnDownWoods(Coords coords) {
        /*
         * TODO Replace this code once Tactical ops is released. Players are now
         * allowed to set how much damage woods will burn down per round
         * Defaults to 5 CF like the original command. -- Torren
         */
        int burnDamage = 5;
        try {
            burnDamage = game.getOptions().intOption("woods_burn_down_amount");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        vPhaseReport.addAll(server.tryClearHex(coords, burnDamage, Entity.NONE));
    }

    /**
     * Spreads the fire around the specified coordinates.
     */
    public void spreadFire(int x, int y, int windDir, int windStr) {
        Coords src = new Coords(x, y);
        Coords nextCoords = src.translated(windDir);
        
        //TODO: check for height differences between hexes

        TargetRoll directroll = new TargetRoll(9, "spread downwind");
        TargetRoll obliqueroll = new TargetRoll(11, "spread 60 degrees to downwind");
        
        if(windStr > PlanetaryConditions.WI_NONE && windStr < PlanetaryConditions.WI_STRONG_GALE) {
        	directroll.addModifier(-2, "light/moderate gale");
        	obliqueroll.addModifier(-1, "light/moderate gale");
        }
        else if(windStr > PlanetaryConditions.WI_MOD_GALE) {
        	directroll.addModifier(-3, "strong gale+");
        	directroll.addModifier(-2, "strong gale+");
        }
        
        spreadFire(nextCoords, directroll);

        // Spread to the next hex downwind on a 12 if the first hex wasn't
        // burning...
        IHex nextHex = game.getBoard().getHex(nextCoords);
        if (nextHex != null && !(nextHex.containsTerrain(Terrains.FIRE))) {
            // we've already gone one step in the wind direction, now go another
        	directroll.addModifier(3, "crossing non-burning hex");
            spreadFire(nextCoords.translated(windDir), directroll);
        }

        // spread fire 60 degrees clockwise....
        spreadFire(src.translated((windDir + 1) % 6), obliqueroll);

        // spread fire 60 degrees counterclockwise
        spreadFire(src.translated((windDir + 5) % 6), obliqueroll);
    }

    /**
     * Spreads the fire, and reports the spread, to the specified hex, if
     * possible, if the hex isn't already on fire, and the fire roll is made.
     */
    public void spreadFire(Coords coords, TargetRoll roll) {
        IHex hex = game.getBoard().getHex(coords);
        if (hex == null) {
            // Don't attempt to spread fire off the board.
            return;
        }
        if (!(hex.containsTerrain(Terrains.FIRE)) && server.ignite(hex, roll)) {
            Report r = new Report(5150, Report.PUBLIC);
            r.add(coords.getBoardNum());
            vPhaseReport.addElement(r);
        }
    }

    /**
     * Under L3 rules, smoke drifts in the direction of the wind and has a
     * chance to dissipate. This function will keep track of hexes to have smoke
     * removed and added, since there's no other way to tell if a certain smoke
     * cloud has drifted that turn. This method creates the class SmokeDrift to
     * store hex and size data for the smoke clouds. This method calls functions
     * driftAddSmoke, driftSmokeDissipate, driftSmokeReport
     */
    private void resolveSmoke() {
        IBoard board = game.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();
        int windDir = game.getPlanetaryConditions().getWindDirection();
        int windStr = game.getPlanetaryConditions().getWindStrength();
        class SmokeDrift { // hold the hex and level of the smoke cloud
            public Coords coords;
            public int size;

            public SmokeDrift(Coords c, int s) {
                coords = c;
                size = s;
            }

            /*
             * public SmokeDrift(SmokeDrift sd) { sd.coords = coords; sd.size =
             * size; }
             */
        }

        Vector<SmokeDrift> smokeToAdd = new Vector<SmokeDrift>();

        // Cycle through all hexes, checking for smoke
        debugTime("resolve smoke 1", true);

        for (int currentXCoord = 0; currentXCoord < width; currentXCoord++) {

        	for (int currentYCoord = 0; currentYCoord < height; currentYCoord++) {
        		Coords currentCoords = new Coords(currentXCoord,currentYCoord);
        		IHex currentHex = board.getHex(currentXCoord, currentYCoord);
        		int tempWindStr = windStr;
        		int tempWindDir = windDir;
        		// check for existence of smoke, then add it to the
        		// vector...if the wind is not Calm!
        		if (currentHex.containsTerrain(Terrains.SMOKE)) {
        			int smokeLevel = currentHex.terrainLevel(Terrains.SMOKE);
        			Coords smokeCoords = driftAddSmoke(currentXCoord,currentYCoord, tempWindDir, tempWindStr);
                        
        			// System.out.println(currentCoords.toString() + " to "
        			// + smokeCoords.toString());
        			//Smoke has Dissipated by moving into a hex with a greater then 4 elevation drop.
        			if ( smokeCoords == null ){
        				Report r = new Report(5220, Report.PUBLIC);
        				r.add(currentCoords.getBoardNum());
        				vPhaseReport.addElement(r);
                            
        			}
        			else if (board.contains(smokeCoords)) { // don't add it to
        				// the vector if
        				// it's not on
        				// board!
        				smokeToAdd.addElement(new SmokeDrift(new Coords(smokeCoords), smokeLevel));
        			} else {
        				// report that the smoke has blown off the map
        				Report r = new Report(5230, Report.PUBLIC);
        				r.add(currentCoords.getBoardNum());
        				vPhaseReport.addElement(r);
        			}
        			currentHex.removeTerrain(Terrains.SMOKE);
        			server.sendChangedHex(currentCoords);
        		}

        	} // end the loop through Y coordinates
        } // end the loop through X coordinates

        debugTime("resolve smoke 1 end, resolve smoke 2 begin", true);

        // Cycle through the vector and add the drifted smoke
        for (int sta = 0; sta < smokeToAdd.size(); sta++) {
        	SmokeDrift drift = smokeToAdd.elementAt(sta);
        	Coords smokeCoords = drift.coords;
        	int smokeSize = drift.size;
        	IHex smokeHex = game.getBoard().getHex(smokeCoords);
        	smokeHex.addTerrain(Terrains.getTerrainFactory().createTerrain(Terrains.SMOKE, smokeSize));
        	server.sendChangedHex(smokeCoords);
        }

        debugTime("resolve smoke 2 end, resolve smoke 3 begin", true);

        // Cycle through the vector again and dissipate the smoke, then
        // reporting it
        for (int dis = 0; dis < smokeToAdd.size(); dis++) {
        	SmokeDrift drift = smokeToAdd.elementAt(dis);
        	Coords smokeCoords = drift.coords;
        	int smokeSize = drift.size;
        	IHex smokeHex = game.getBoard().getHex(smokeCoords);
        	int roll = Compute.d6(2);

        	boolean smokeDis = driftSmokeDissipate(smokeHex, roll,smokeSize, windStr);
        	driftSmokeReport(smokeCoords, smokeSize, smokeDis);
        	server.sendChangedHex(smokeCoords);
        }

        debugTime("resolve smoke 3 end", false);

    } // end smoke resolution

    /**
     * Override for the main driftAddSmoke to allow for 0 direction changes
     * @param x
     * @param y
     * @param windDir
     * @param windStr
     * @return
     */
    public Coords driftAddSmoke(int x, int y, int windDir, int windStr) {
        return driftAddSmoke(x, y, windDir, windStr, 0); 
    }
    
    /**
     * Smoke cannot climb more then 4 hexes if the next hex is more then 4 in elevation then
     * The smoke will try to go right. If it cannot go right it'll try to go left
     * if it cannot go left it'll stay put.
     * 
     * @param x
     * @param y
     * @param windDir
     * @param windStr
     * @param directionChanges How many times the smoke has tried to change directions to get around an obsticle.
     * @return
     */
    public Coords driftAddSmoke(int x, int y, int windDir, int windStr, int directionChanges) {
        Coords src = new Coords(x, y);
        Coords nextCoords = src.translated(windDir);
        IBoard board = game.getBoard(); 

        //if the wind conditions are calm, then don't drift it
        if(windStr == PlanetaryConditions.WI_NONE) {
        	return src;
        }
        
        //if it is no longer on the board then return it now to avoid getting null arguments later
        if(!board.contains(nextCoords)) {
        	return nextCoords;
        }
        
        //If the smoke moves into a hex that has a greate then 4 elevation drop it dissipates.
        if ( board.getHex(src).getElevation() - board.getHex(nextCoords).getElevation() > 4){
            return null;
        }
        
        if ( board.getHex(src).getElevation() - board.getHex(nextCoords).getElevation() < -4){
            //Try Right
            if ( directionChanges == 0 ){
                return driftAddSmoke(x, y, (windDir + 1) % 6, windStr, directionChanges++);
            }
            //Try Left
            else if ( directionChanges == 1)
                return driftAddSmoke(x, y, (windDir - 2 ) % 6, windStr, directionChanges++);
            //Stay put
            else
                return src;
        }

        // stronger wind causes smoke to drift farther
        if (windStr > PlanetaryConditions.WI_MOD_GALE) {
            return driftAddSmoke(nextCoords.x, nextCoords.y, windDir, --windStr); 
        }

        return nextCoords;
    }

    /**
     * TODO
     * This method does not currently support "smoke clouds" as specified in
     * TacOps under "Dissipation" on page 47. The added
     */
    public boolean driftSmokeDissipate(IHex smokeHex, int roll, int smokeSize, int windStr) {
        // Dissipate in various winds
        if (roll > 10 || (roll > 9 && windStr == PlanetaryConditions.WI_MOD_GALE)
                || (roll > 7 && windStr == PlanetaryConditions.WI_STRONG_GALE)
                || (roll > 5 && windStr == PlanetaryConditions.WI_STORM)) {
            smokeHex.removeTerrain(Terrains.SMOKE);

            if (smokeSize == 2) {
                smokeHex.addTerrain(Terrains.getTerrainFactory().createTerrain(
                        Terrains.SMOKE, 1));
                return true;
            }
            return true;
        }
        //All smoke goes bye bye in Tornados
        if ( windStr > PlanetaryConditions.WI_STORM ) {
            smokeHex.removeTerrain(Terrains.SMOKE);
            smokeSize = 1;
            return true;
        }
        return false;
    }

    public void driftSmokeReport(Coords smokeCoords, int size, boolean dis) {
        Report r;
        if (size == 2 && dis == true) {
            // heavy smoke drifts and dissipates to light
            r = new Report(5210, Report.PUBLIC);
            r.add(smokeCoords.getBoardNum());
            vPhaseReport.addElement(r);
        } else if (size == 2 && dis == false) {
            // heavy smoke drifts
            r = new Report(5215, Report.PUBLIC);
            r.add(smokeCoords.getBoardNum());
            vPhaseReport.addElement(r);
        } else if (size == 1 && dis == true) {
            // light smoke drifts and dissipates
            r = new Report(5220, Report.PUBLIC);
            r.add(smokeCoords.getBoardNum());
            vPhaseReport.addElement(r);
        } else if (size == 1 && dis == false) {
            // light smoke drifts
            r = new Report(5225, Report.PUBLIC);
            r.add(smokeCoords.getBoardNum());
            vPhaseReport.addElement(r);
        }
    }
}
