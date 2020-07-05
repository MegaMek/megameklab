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
import megameklab.com.printing.PrintEntity;
import org.apache.batik.util.SVGConstants;

/**
 * To-Hit modifiers for all Meks and vehicles
 */
public class MekVeeToHitMods extends ReferenceTable {
    private final Entity entity;

    public MekVeeToHitMods(PrintEntity sheet) {
        super(sheet, 0.02, 0.08, 0.8);
        this.entity = sheet.getEntity();
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        if (entity instanceof Mech) {
            addMekAttackerMods();
        } else if (entity instanceof Tank) {
            addVeeAttackerMods();
        }
        addTerrainMods();
        addTargetMods();
        if (entity instanceof Mech) {
            addDamageMods();
        }
    }

    private void addMekAttackerMods() {
        addRow(bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("walked"), "+1");
        addRow("", bundle.getString("ran"), "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        if (!(entity instanceof QuadMech)) {
            addRow("", bundle.getString("prone"), "+2");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (((Mech) entity).getCockpitType() == Mech.COCKPIT_PRIMITIVE_INDUSTRIAL) {
            addRow("", bundle.getString("noFireCon"), "+2");
        } else if ((((Mech) entity).getCockpitType() == Mech.COCKPIT_PRIMITIVE)
                || (((Mech) entity).getCockpitType() == Mech.COCKPIT_INDUSTRIAL)
                || (((Mech) entity).getCockpitType() == Mech.COCKPIT_SUPERHEAVY_INDUSTRIAL)) {
            addRow("", bundle.getString("basicFireCon"), "+1");
        }
    }

    private void addVeeAttackerMods() {
        addRow(bundle.getString("attacker"), "", "");
        addRow("", bundle.getString("stationary"), "+0");
        addRow("", bundle.getString("cruised"), "+1");
        addRow("", bundle.getString("flanked"), "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", bundle.getString("jumped"), "+3");
        }
        addRow("", bundle.getString("skidding"), "+1");
        if (entity.isSupportVehicle() && !entity.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            if (entity.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
                addRow("", bundle.getString("basicFireCon"), "+1");
            } else {
                addRow("", bundle.getString("noFireCon"), "+2");
            }
        }
    }

    private void addTerrainMods() {
        addRow(bundle.getString("terrain"), "", "");
        addRow("", bundle.getString("lightWoods"), "+1/hex");
        addRow("", bundle.getString("heavyWoods"), "+2/hex");
        addRow("", bundle.getString("partialCover"), "+1");
    }

    private void addTargetMods() {
        addRow(bundle.getString("target"), "", "");
        addRow("", bundle.getString("proneAdjacent"), "-2");
        addRow("", bundle.getString("proneOther"), "+1");
        addRow("", bundle.getString("immobile"), "-4");
        addRow("", bundle.getString("skidding"), "-2");
        addRow("", String.format(bundle.getString("movedRange"), 0, 2), "0");
        addRow("", String.format(bundle.getString("movedRange"), 3, 4), "+1");
        addRow("", String.format(bundle.getString("movedRange"), 5, 6), "+2");
        addRow("", String.format(bundle.getString("movedRange"), 7, 9), "+3");
        addRow("", String.format(bundle.getString("movedRange"), 10, 17), "+4");
        addRow("", String.format(bundle.getString("movedRange"), 18, 24), "+5");
        addRow("", String.format(bundle.getString("movedFinal"), 25), "+6");
        addRow("", bundle.getString("jumped"), "+1");
        if (!(entity instanceof Infantry)) {
            addRow("", bundle.getString("baTarget"), "+1");
        }
    }

    private void addDamageMods() {
        addRow(bundle.getString("damage"), "", "");
        addRow("", bundle.getString("sensorHit"), "+2");
        addRow("", bundle.getString("shoulderHit"), "+4");
        addRow("", bundle.getString("armActuator"), "+1");
    }
}
