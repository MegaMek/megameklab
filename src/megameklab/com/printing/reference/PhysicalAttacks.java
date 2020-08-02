/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.printing.reference;

import megamek.common.*;
import megamek.common.actions.ClubAttackAction;
import megameklab.com.printing.PrintMech;
import megameklab.com.util.CConfig;
import megameklab.com.util.StringUtils;
import org.apache.batik.util.SVGConstants;

/**
 * Attack mods and damage for Mek physical attacks
 */
public class PhysicalAttacks extends ReferenceTable {

    public PhysicalAttacks(PrintMech sheet) {
        super(sheet, 0.05, 0.5, 0.8);
        setHeaders(bundle.getString("attack"), bundle.getString("toHit"), bundle.getString("damage"));
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        int kickDamage = (int) Math.floor(sheet.getEntity().getWeight() / 5.0);
        int dfaDamage = (int) Math.floor(sheet.getEntity().getWeight() / 10.0) * 3;
        if (sheet.getEntity().hasWorkingMisc(MiscType.F_TALON)) {
            kickDamage = (int) Math.floor(kickDamage * 1.5);
            dfaDamage = (int) Math.floor(dfaDamage * 1.5);
        }
        boolean hasTorsoSpikes = false;
        for (Mounted mounted : sheet.getEntity().getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_SPIKES)
                    && ((Mech) sheet.getEntity()).locationIsTorso(mounted.getLocation())) {
                hasTorsoSpikes = true;
                break;
            }
        }
        addPunchAttacks(sheet.getEntity());
        addRow(bundle.getString("kick"), "-2", String.valueOf(kickDamage));
        if (!(sheet.getEntity() instanceof QuadMech)) {
            addRow(bundle.getString("push"), "-1", "—");
        }
        if (sheet.getEntity().hasSystem(Mech.ACTUATOR_HAND, Mech.LOC_LARM)
               && sheet.getEntity().hasSystem(Mech.ACTUATOR_HAND, Mech.LOC_RARM)) {
            addRow(bundle.getString("club"), "-1", String.format("%.0f", Math.floor(sheet.getEntity().getWeight() / 5.0)));
        }
        addRow(bundle.getString("charge"), "+0*", String.format(hasTorsoSpikes ?
                        "%.0f/%s†" : "%.0f/%s",
                Math.floor(sheet.getEntity().getWeight() / 10.0),
                CConfig.scaleUnits().equals(CConfig.RSScale.HEXES) ?
                        bundle.getString("hex") :
                        CConfig.getIntParam(CConfig.RS_SCALE_FACTOR) + CConfig.scaleUnits().abbreviation));
        if (sheet.getEntity().getOriginalJumpMP() > 0) {
            addRow(bundle.getString("dfa"), "+0*", String.valueOf(dfaDamage));
        }
        addPhysicalWeapon(sheet.getEntity());
        addNote(bundle.getString("pilotingModNote"));
        if (hasTorsoSpikes) {
            addNote(bundle.getString("spikesNote"));
        }
    }

    private void addPunchAttacks(Entity entity) {
        int left = countActuators(entity, Mech.LOC_LARM);
        int right = countActuators(entity, Mech.LOC_RARM);
        int baseDamage = (int) Math.floor(entity.getWeight() / 10.0);
        if (left == right) {
            addPunchAttack(bundle.getString("punch"), left, baseDamage);
        } else {
            addPunchAttack(bundle.getString("punch") + " (LA)", left, baseDamage);
            addPunchAttack(bundle.getString("punch") + " (RA)", right, baseDamage);
        }
    }

    private int countActuators(Entity entity, int location) {
        if (entity.hasSystem(Mech.ACTUATOR_HAND, location)) {
            return 4;
        } else if (entity.hasSystem(Mech.ACTUATOR_LOWER_ARM, location)) {
            return 3;
        } else if (entity.hasSystem(Mech.ACTUATOR_UPPER_ARM, location)) {
            return 2;
        } else {
            return 1;
        }
    }

    private void addPunchAttack(String name, int actuators, int baseDamage) {
        if (actuators == 4) {
            addRow(name, "+0", String.valueOf(baseDamage));
        } else if (actuators == 3) {
            addRow(name, "+1", String.valueOf(baseDamage));
        } else if (actuators == 2) {
            addRow(name, "+2", String.valueOf(baseDamage / 2));
        }
    }

    private void addPhysicalWeapon(Entity entity) {
        for (Mounted mounted : entity.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_CLUB) || mounted.getType().hasFlag(MiscType.F_HAND_WEAPON)) {
                addRow(mounted.getName(),
                        String.format("%+d", ClubAttackAction.getHitModFor((MiscType) mounted.getType())),
                        StringUtils.getEquipmentInfo(entity, mounted));
            }
        }
    }
}
