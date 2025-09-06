/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing.reference;

import megamek.common.actions.ClubAttackAction;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.QuadMek;
import megamek.logging.MMLogger;
import megameklab.printing.PrintMek;
import megameklab.util.CConfig;
import megameklab.util.RSScale;
import megameklab.util.StringUtils;
import org.apache.batik.util.SVGConstants;

/**
 * Attack mods and damage for Mek physical attacks
 */
public class PhysicalAttacks extends ReferenceTable {

    private static final MMLogger logger = MMLogger.create(PhysicalAttacks.class);

    public PhysicalAttacks(PrintMek sheet) {
        super(sheet, 0.05, 0.5, 0.8);
        setHeaders(bundle.getString("attack"), bundle.getString("toHit"), bundle.getString("damage"));
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        int kickDamage = (int) Math.floor(sheet.getEntity().getWeight() / 5.0);
        int dfaDamage = (int) Math.ceil(sheet.getEntity().getWeight() / 10.0 * 3);
        if (sheet.getEntity().hasWorkingMisc(MiscType.F_TALON)) {
            kickDamage = (int) Math.round(kickDamage * 1.5);
            dfaDamage = (int) Math.round(dfaDamage * 1.5);
        }
        String kickDamageString = String.valueOf(kickDamage);
        if (sheet.getEntity().hasWorkingMisc(MiscType.F_TSM)) {
            kickDamageString += " [" + kickDamage * 2 + "]";
        }
        boolean hasTorsoSpikes = false;
        for (Mounted<?> mounted : sheet.getEntity().getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_SPIKES)
                  && ((Mek) sheet.getEntity()).locationIsTorso(mounted.getLocation())) {
                hasTorsoSpikes = true;
                break;
            }
        }
        addPunchAttacks(sheet.getEntity());
        addRow(bundle.getString("kick"), "-2", kickDamageString);
        if (!(sheet.getEntity() instanceof QuadMek)) {
            addRow(bundle.getString("push"), "-1", "\u2014");
        }
        if (sheet.getEntity().hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_LEFT_ARM)
              && sheet.getEntity().hasSystem(Mek.ACTUATOR_HAND, Mek.LOC_RIGHT_ARM)) {
            addRow(bundle.getString("club"),
                  "-1",
                  String.format("%.0f", Math.floor(sheet.getEntity().getWeight() / 5.0)));
        }
        addRow(bundle.getString("charge"), "+0*", String.format(hasTorsoSpikes ?
                    "%.0f/%s\u2020" : "%.0f/%s",
              Math.floor(sheet.getEntity().getWeight() / 10.0),
              CConfig.scaleUnits().equals(RSScale.HEXES) ?
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
        int left = countActuators(entity, Mek.LOC_LEFT_ARM);
        int right = countActuators(entity, Mek.LOC_RIGHT_ARM);
        int baseDamage = (int) Math.ceil(entity.getWeight() / 10.0);
        boolean hasTSM = entity.hasWorkingMisc(MiscType.F_TSM);
        if (left == right) {
            addPunchAttack(bundle.getString("punch"), left, baseDamage, hasTSM);
        } else {
            addPunchAttack(bundle.getString("punch") + " (LA)", left, baseDamage, hasTSM);
            addPunchAttack(bundle.getString("punch") + " (RA)", right, baseDamage, hasTSM);
        }
    }

    private int countActuators(Entity entity, int location) {
        if (entity.hasSystem(Mek.ACTUATOR_HAND, location)) {
            return 4;
        } else if (entity.hasSystem(Mek.ACTUATOR_LOWER_ARM, location)) {
            return 3;
        } else if (entity.hasSystem(Mek.ACTUATOR_UPPER_ARM, location)) {
            return 2;
        } else {
            return 1;
        }
    }

    private void addPunchAttack(String name, int actuators, int baseDamage, boolean hasTSM) {
        String modifier = "+3";
        if (actuators == 4) {
            modifier = "+0";
        } else if (actuators == 3) {
            modifier = "+1";
        } else if (actuators == 2) {
            baseDamage = Math.max(baseDamage / 2, 1);
        } else if (actuators == 1) {
            baseDamage = Math.max(baseDamage / 4, 1);
        }
        String tsmDamage = hasTSM ? " [" + baseDamage * 2 + "]" : "";
        addRow(name, modifier, baseDamage + tsmDamage);
    }

    private void addPhysicalWeapon(Entity entity) {
        for (Mounted<?> mounted : entity.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_CLUB)) {
                addRow(mounted.getName(),
                      String.format("%+d", ClubAttackAction.getHitModFor((MiscType) mounted.getType())),
                      StringUtils.getEquipmentInfo(entity, mounted));
            } else if (mounted.getType().hasFlag(MiscType.F_HAND_WEAPON)) {
                if (mounted.getType().hasSubType(MiscType.S_CLAW)) {
                    addRow(mounted.getName(), "+1", StringUtils.getEquipmentInfo(entity, mounted));
                } else {
                    logger.error("Unknown hand weapon {}!", mounted.getName());
                    addRow(mounted.getName(), "???", StringUtils.getEquipmentInfo(entity, mounted));
                }
            }
        }
    }
}
