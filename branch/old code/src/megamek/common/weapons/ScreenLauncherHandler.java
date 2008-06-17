/**
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
package megamek.common.weapons;


import java.util.Enumeration;
import java.util.Vector;

import megamek.common.Coords;
import megamek.common.Entity;
import megamek.common.FighterSquadron;
import megamek.common.HitData;
import megamek.common.IGame;
import megamek.common.Report;
import megamek.common.ToHitData;
import megamek.common.actions.WeaponAttackAction;
import megamek.server.Server;

/**
 * @author Jay Lawson
 */
public class ScreenLauncherHandler extends AmmoWeaponHandler {

    /**
     * 
     */
    private static final long serialVersionUID = -2536312899803153911L;

    /**
     * @param t
     * @param w
     * @param g
     * @param s
     */
    public ScreenLauncherHandler(ToHitData t, WeaponAttackAction w, IGame g, Server s) {
        super(t, w, g, s);
    }
    
    /**
     * handle this weapons firing
     * 
     * @return a <code>boolean</code> value indicating wether this should be
     *         kept or not
     */
    public boolean handle(IGame.Phase phase, Vector<Report> vPhaseReport) {
        if (!this.cares(phase)) {
            return true;
        }
     
        //Report weapon attack and its to-hit value.
        r = new Report(3115);
        r.indent();
        r.newlines = 0;
        r.subject = subjectId;
        r.add(wtype.getName());
        r.messageId = 3120;
        r.add(target.getDisplayName(), true);
        vPhaseReport.addElement(r);
        if (toHit.getValue() == ToHitData.IMPOSSIBLE) {
            r = new Report(3135);
            r.subject = subjectId;
            r.add(toHit.getDesc());
            vPhaseReport.addElement(r);
            return false;
        } else if (toHit.getValue() == ToHitData.AUTOMATIC_FAIL) {
            r = new Report(3140);
            r.newlines = 0;
            r.subject = subjectId;
            r.add(toHit.getDesc());
            vPhaseReport.addElement(r);
        } else if (toHit.getValue() == ToHitData.AUTOMATIC_SUCCESS) {
            r = new Report(3145);
            r.newlines = 0;
            r.subject = subjectId;
            r.add(toHit.getDesc());
            vPhaseReport.addElement(r);
        }
        
        addHeat();
        
        //deliver screen
        Coords coords = target.getPosition();
        server.deliverScreen(coords, vPhaseReport);
        
        //damage any entities in the hex
        for (Enumeration<Entity> impactHexHits = game.getEntities(coords);impactHexHits.hasMoreElements();) {
            Entity entity = impactHexHits.nextElement();
            //if fighter squadron multiply damage by active fighters
            if(entity instanceof FighterSquadron) {
                FighterSquadron fs = (FighterSquadron)entity;
                attackValue *= fs.getNFighters();
            }          
            ToHitData toHit = new ToHitData();
            toHit.setHitTable(ToHitData.HIT_NORMAL);
            HitData hit = entity.rollHitLocation(toHit.getHitTable(), ToHitData.SIDE_FRONT);
            vPhaseReport.addAll( server.damageEntity(entity, hit, attackValue));
            server.creditKill(entity, ae);
        }
        return false;
    }

}
