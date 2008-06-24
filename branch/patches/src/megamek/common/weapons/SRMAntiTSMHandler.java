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

import java.util.Vector;

import megamek.common.BattleArmor;
import megamek.common.Compute;
import megamek.common.IGame;
import megamek.common.Infantry;
import megamek.common.RangeType;
import megamek.common.Report;
import megamek.common.ToHitData;
import megamek.common.WeaponType;
import megamek.common.actions.WeaponAttackAction;
import megamek.server.Server;
import megamek.server.Server.DamageType;

/**
 * @author Sebastian Brocks
 */
public class SRMAntiTSMHandler extends SRMHandler {

    /**
     * 
     */
    private static final long serialVersionUID = 6380017303917455020L;

    /**
     * @param t
     * @param w
     * @param g
     * @param s
     */
    public SRMAntiTSMHandler(ToHitData t, WeaponAttackAction w, IGame g,
            Server s) {
        super(t, w, g, s);
        sSalvoType = " anti-TSM missile(s) ";
        damageType = DamageType.ANTI_TSM;
    }

    /*
     * (non-Javadoc)
     * 
     * @see megamek.common.weapons.WeaponHandler#calcHits(java.util.Vector)
     */
    protected int calcHits(Vector<Report> vPhaseReport) {
        // conventional infantry gets hit in one lump
        // BAs do one lump of damage per BA suit
        if (target instanceof Infantry && !(target instanceof BattleArmor)) {
            if (ae instanceof BattleArmor) {
                bSalvo = true;
                return ((BattleArmor) ae).getShootingStrength();
            }
            return 1;
        }
        int missilesHit;
        int nMissilesModifier = nSalvoBonus;
        boolean bWeather = false;
        boolean tacopscluster = game.getOptions().booleanOption(
                "tacops_clusterhitpen");
        if (tacopscluster) {
            if (nRange <= 1) {
                nMissilesModifier += 1;
            } else if (nRange <= wtype.getMediumRange()) {
                nMissilesModifier += 0;
            } else {
                nMissilesModifier -= 1;
            } 
        }
        
        if (bGlancing) {
            nMissilesModifier -= 4;
        }
        if (game.getOptions().booleanOption("tacops_range") && nRange > wtype.getRanges(weapon)[RangeType.RANGE_LONG]) {
            nMissilesModifier -= 2;
        }

        // weather checks
        if (game.getOptions().booleanOption("blizzard")
                && wtype.hasFlag(WeaponType.F_MISSILE)) {
            nMissilesModifier -= 4;
            bWeather = true;
        }

        if (game.getOptions().booleanOption("moderate_winds")
                && wtype.hasFlag(WeaponType.F_MISSILE)) {
            nMissilesModifier -= 2;
            bWeather = true;
        }

        if (game.getOptions().booleanOption("high_winds")
                && wtype.hasFlag(WeaponType.F_MISSILE)) {
            nMissilesModifier -= 4;
            bWeather = true;
        }

        if ( bDirect ){
            nMissilesModifier += (toHit.getMoS()/3)*2;
        }

        // Add ams mod
        nMissilesModifier += getAMSHitsMod(vPhaseReport);
        if (allShotsHit()) {
            missilesHit = wtype.getRackSize();
        } else {
            // anti tsm hit with half the normal number, round up
            missilesHit = Compute
                    .missilesHit(wtype.getRackSize(), nMissilesModifier,
                            bWeather || bGlancing || tacopscluster);
            missilesHit = (int) Math.ceil((double) missilesHit / 2);
        }
        r = new Report(3325);
        r.subject = subjectId;
        r.add(missilesHit);
        r.add(sSalvoType);
        r.add(toHit.getTableDesc());
        r.newlines = 0;
        vPhaseReport.addElement(r);
        if (nMissilesModifier != 0) {
            if (nMissilesModifier > 0)
                r = new Report(3340);
            else
                r = new Report(3341);
            r.subject = subjectId;
            r.add(nMissilesModifier);
            r.newlines = 0;
            vPhaseReport.addElement(r);
        }
        r = new Report(3345);
        r.subject = subjectId;
        r.newlines = 0;
        vPhaseReport.addElement(r);
        bSalvo = true;
        return missilesHit;
    }
}
