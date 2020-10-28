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
import megameklab.com.printing.PrintAero;
import org.apache.batik.util.SVGConstants;

/**
 * Table for attack roll mods for aerospace units
 */
public class AeroToHitMods extends ReferenceTable {
    private final Entity entity;
    private final boolean spaceOnly;

    public AeroToHitMods(PrintAero sheet) {
        super(sheet, 0.02, 0.08, 0.8);
        this.entity = sheet.getEntity();
        spaceOnly = (entity instanceof Jumpship)
                || entity.getMovementMode().equals(EntityMovementMode.STATION_KEEPING);
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);

        addMods();
    }

    private void addMods() {
        addRow(bundle.getString("range"), "", "");
        addRow("", bundle.getString("short"), "+0");
        addRow("", bundle.getString("medium"), "+2");
        addRow("", bundle.getString("long"), "+4");
        addRow("", bundle.getString("extreme"), "+6");

        addRow(bundle.getString("targetInterveningConditions"), "", "");
        addRow("", bundle.getString("attackAgainstAft"), "+0");
        addRow("", bundle.getString("attackAgainstSide"), "+1");
        addRow("", bundle.getString("attackAgainstNose"), "+2");
        if (!spaceOnly) {
            addRow("", bundle.getString("flyingAtNOE"),
                    (entity.isOmni() && !entity.isSupportVehicle()) ? "+1" : "+2");
        }
        if (!entity.isLargeCraft()) {
            addRow("", bundle.getString("secondaryTargetForward"), "+1");
            addRow("", bundle.getString("secondaryTarget"), "+2");
        }
        if (!spaceOnly) {
            addRow("", bundle.getString("targetAirToGround"), "-3");
        }
        addRow("", bundle.getString("target0velocity"), "-2");
        if (entity.getWeaponList().stream().map(m -> (WeaponType) m.getType())
                .filter(w -> w.getAtClass() != WeaponType.CLASS_CAPITAL_MISSILE)
                .anyMatch(WeaponType::isCapital)) {
            addRow("", bundle.getString("capitalAgainstSmallTarget"), "+5");
        }
        if (entity.getWeaponList().stream().map(m -> (WeaponType) m.getType())
                .filter(w -> w.getAtClass() != WeaponType.CLASS_CAPITAL_MISSILE)
                .anyMatch(WeaponType::isSubCapital)) {
            addRow("", bundle.getString("subcapitalAgainstSmallTarget"), "+3");
        }
        if (!(entity instanceof ConvFighter)) {
            addRow("", bundle.getString("firingThroughAtmosphere"), "+2" + bundle.getString("perHex"));
            addRow("", bundle.getString("screenHex"), "+2");
        }
        addRow("", bundle.getString("targetEvading"), bundle.getString("variable"));

        addRow(bundle.getString("attackerConditions"), "", "");
        addRow("", bundle.getString("excessThrust"), "+2");
        addRow("", bundle.getString("outOfControl"), "+2");
        if (entity.isFighter() && !entity.isSupportVehicle()) {
            addRow("", bundle.getString("pilotDamage"), "+2");
        } else {
            addRow("", bundle.getString("crewDamage"), "+2");
        }
        if (entity instanceof Jumpship) {
            addRow("", bundle.getString("cicDamage"), "+2" + bundle.getString("perHit"));
        } else {
            addRow("", bundle.getString("fcsDamage"), "+2" + bundle.getString("perHit"));
        }
        addRow("", bundle.getString("sensorsHit"), "+1" + bundle.getString("perHit"));
        addRow("", bundle.getString("sensorsDestroyed"), "+5");
        if (entity.isLargeCraft()) {
            addRow("", bundle.getString("attackerEvading"), "+2");
        } else if (!entity.getMovementMode().equals(EntityMovementMode.AIRSHIP)) {
            addRow("", bundle.getString("attackerEvading"), bundle.getString("prohibited"));
        }
    }
}
