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
        super(sheet, "TO-HIT MODIFIERS", 0.02, 0.08, 0.8);
        this.entity = sheet.getEntity();
        setAnchor(SVGConstants.SVG_START_VALUE);
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
        addRow("Attacker", "", "");
        addRow("", "Stationary", "+0");
        addRow("", "Walked", "+1");
        addRow("", "Ran", "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", "Jumped", "+3");
        }
        if (!(entity instanceof QuadMech)) {
            addRow("", "Prone", "+2");
        }
        addRow("", "Skidding", "+1");
        if (((Mech) entity).getCockpitType() == Mech.COCKPIT_PRIMITIVE_INDUSTRIAL) {
            addRow("", "Weapons (No fire control)", "+2");
        } else if ((((Mech) entity).getCockpitType() == Mech.COCKPIT_PRIMITIVE)
                || (((Mech) entity).getCockpitType() == Mech.COCKPIT_INDUSTRIAL)
                || (((Mech) entity).getCockpitType() == Mech.COCKPIT_SUPERHEAVY_INDUSTRIAL)) {
            addRow("", "Weapons (Basic fire control)", "+1");
        }
    }

    private void addVeeAttackerMods() {
        addRow("Attacker", "", "");
        addRow("", "Stationary", "+0");
        addRow("", "Cruised", "+1");
        addRow("", "Flanked", "+2");
        if (entity.getOriginalJumpMP() > 0) {
            addRow("", "Jumped", "+3");
        }
        addRow("", "Skidding", "+1");
        if (entity.isSupportVehicle() && !entity.hasWorkingMisc(MiscType.F_ADVANCED_FIRECONTROL)) {
            if (entity.hasWorkingMisc(MiscType.F_BASIC_FIRECONTROL)) {
                addRow("", "Weapons (Basic fire control)", "+1");
            } else {
                addRow("", "Weapons (No fire control)", "+2");
            }
        }
    }

    private void addTerrainMods() {
        addRow("Terrain", "", "");
        addRow("", "Light Woods", "+1/hex");
        addRow("", "Heavy Woods", "+2/hex");
        addRow("", "Partial Cover", "+1");
    }

    private void addTargetMods() {
        addRow("Target", "", "");
        addRow("", "Prone (adjacent hex)", "-2");
        addRow("", "Prone (other hex)", "+1");
        addRow("", "Immobile", "-4");
        addRow("", "Skidding", "-2");
        addRow("", "Moved 0-2 hexes", "0");
        addRow("", "Moved 3-4 hexes", "+1");
        addRow("", "Moved 5-6 hexes", "+2");
        addRow("", "Moved 7-9 hexes", "+3");
        addRow("", "Moved 10-17 hexes", "+4");
        addRow("", "Moved 18-24 hexes", "+5");
        addRow("", "Moved 25+ hexes", "+6");
        addRow("", "Jumped", "+1");
        if (!(entity instanceof Infantry)) {
            addRow("", "BattleArmor unit", "+1");
        }
    }

    private void addDamageMods() {
        addRow("Damage", "", "");
        addRow("", "Sensor hit", "+2");
        addRow("", "Shoulder hit", "+4");
        addRow("", "Arm actuator", "+1");
    }
}
