/**
 * MegaMek - Copyright (C) 2004,2005 Ben Mazur (bmazur@sev.org)
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
/*
 * Created on Sep 23, 2004
 *
 */
package megamek.common.weapons;

import java.util.Vector;

import megamek.common.BattleArmor;
import megamek.common.Building;
import megamek.common.Compute;
import megamek.common.Entity;
import megamek.common.HitData;
import megamek.common.IGame;
import megamek.common.Infantry;
import megamek.common.Mech;
import megamek.common.Report;
import megamek.common.ToHitData;
import megamek.common.actions.WeaponAttackAction;
import megamek.server.Server;

/**
 * @author Andrew Hunter
 */
public class VehicleFlamerHeatHandler extends AmmoWeaponHandler {
    /**
     * 
     */
    private static final long serialVersionUID = -4478740737686392126L;

    /**
     * @param toHit
     * @param waa
     * @param g
     */
    public VehicleFlamerHeatHandler(ToHitData toHit, WeaponAttackAction waa,
            IGame g, Server s) {
        super(toHit, waa, g, s);
        generalDamageType = HitData.DAMAGE_ENERGY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see megamek.common.weapons.WeaponHandler#handleEntityDamage(megamek.common.Entity,
     *      java.util.Vector, megamek.common.Building, int, int, int, int)
     */
    protected void handleEntityDamage(Entity entityTarget,
            Vector<Report> vPhaseReport, Building bldg, int hits, int nCluster,
            int nDamPerHit, int bldgAbsorbs) {
        if (entityTarget instanceof Mech
                && game.getOptions().booleanOption("flamer_heat")) {

            HitData hit = entityTarget.rollHitLocation(toHit.getHitTable(),
                    toHit.getSideTable(), waa.getAimedLocation(), waa
                            .getAimingMode());

            if (entityTarget.removePartialCoverHits(hit.getLocation(), toHit
                    .getCover(), Compute.targetSideTable(ae, entityTarget))) {
                // Weapon strikes Partial Cover.
                r = new Report(3460);
                r.subject = subjectId;
                r.add(entityTarget.getShortName());
                r.add(entityTarget.getLocationAbbr(hit));
                r.newlines = 0;
                r.indent(2);
                vPhaseReport.addElement(r);
                missed = true;
                return;
            }

            // heat
            int heat = wtype.getHeat();
            r = new Report(3400);
            r.subject = subjectId;
            r.indent(2);
            r.add(heat);
            r.newlines = 0;
            r.choose(true);
            vPhaseReport.addElement(r);
            entityTarget.heatBuildup += heat;
        } else {
            super.handleEntityDamage(entityTarget, vPhaseReport, bldg, hits,
                    nCluster, nDamPerHit, bldgAbsorbs);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see megamek.common.weapons.WeaponHandler#calcDamagePerHit()
     */
    protected int calcDamagePerHit() {
        double toReturn;
        if (target instanceof Infantry && !(target instanceof BattleArmor)) {
            if (ae instanceof BattleArmor)
                toReturn = Compute.d6(3);
            else
                toReturn = Compute.d6(4);
            if ( bDirect )
                toReturn += toHit.getMoS()/3;
            // pain shunted infantry get half damage
            if (((Entity) target).getCrew().getOptions().booleanOption("pain_shunt")) {
                toReturn /= 2;
            }
            if (bGlancing)
                toReturn =  Math.ceil(toReturn / 2.0);
        }
        else {
            toReturn = super.calcDamagePerHit();
        }
        return (int) toReturn;
    }
}
