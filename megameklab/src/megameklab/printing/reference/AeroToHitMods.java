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

import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.ConvFighter;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Jumpship;
import megameklab.printing.PrintAero;
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
        addRow(SECTION_HEADER + bundle.getString("range"), "", "");
        addRow("", bundle.getString("short"), "+0");
        addRow("", bundle.getString("medium"), "+2");
        addRow("", bundle.getString("long"), "+4");
        addRow("", bundle.getString("extreme"), "+6");

        addRow(SECTION_HEADER + bundle.getString("targetInterveningConditions"), "", "");
        addRow("", bundle.getString("attackAgainstAft"), "+0");
        addRow("", bundle.getString("attackAgainstSide"), "+2");
        addRow("", bundle.getString("attackAgainstNose"), "+1");
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
        if (entity.getWeaponList().stream().map(Mounted::getType)
              .filter(w -> w.getAtClass() != WeaponType.CLASS_CAPITAL_MISSILE)
              .anyMatch(WeaponType::isCapital)) {
            addRow("", bundle.getString("capitalAgainstSmallTarget"), "+5");
        }
        if (entity.getWeaponList().stream().map(Mounted::getType)
              .filter(w -> w.getAtClass() != WeaponType.CLASS_CAPITAL_MISSILE)
              .anyMatch(WeaponType::isSubCapital)) {
            addRow("", bundle.getString("subcapitalAgainstSmallTarget"), "+3");
        }
        if (!(entity instanceof ConvFighter)) {
            addRow("", bundle.getString("firingThroughAtmosphere"), "+2" + bundle.getString("perHex"));
            addRow("", bundle.getString("screenHex"), "+2");
        }
        addRow("", bundle.getString("targetEvading"), bundle.getString("variable"));

        addRow(SECTION_HEADER + bundle.getString("attackerConditions"), "", "");
        addRow("", bundle.getString("excessThrust"), "+2");
        addRow("", bundle.getString("outOfControl"), "+2");
        if (entity.isFighter() && !entity.isSupportVehicle()) {
            addRow("", bundle.getString("pilotDamage"), "+1" + bundle.getString("perHit"));
        } else {
            addRow("", bundle.getString("crewDamage"), "+1" + bundle.getString("perHit"));
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
