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

import megamek.common.units.ConvFighter;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Jumpship;
import megamek.common.units.SmallCraft;
import megameklab.printing.PrintAero;
import org.apache.batik.util.SVGConstants;

/**
 * Control roll situations and modifiers for aerospace units
 */
public class ControlRollTable extends ReferenceTable {

    private final Entity entity;
    private final boolean spaceOnly;
    private final boolean atmosphereOnly;

    public ControlRollTable(PrintAero sheet) {
        super(sheet, 0.02, 0.08, 0.14, 0.8);
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(2, SVGConstants.SVG_START_VALUE);
        entity = sheet.getEntity();
        // Capital ships and satellite SVs
        spaceOnly = (entity instanceof Jumpship) || entity.getMovementMode().equals(EntityMovementMode.STATION_KEEPING);
        // Conventional fighters, fixed wing support, and airships
        atmosphereOnly = (entity instanceof ConvFighter) && !spaceOnly;
        addMods();
    }

    private void addMods() {
        addRow(SECTION_HEADER + bundle.getString("situation"), "", "", "");
        if (entity.getWalkMP() > 0) {
            addRow(NO_SHADE, bundle.getString("movement"), "", "");
            if (atmosphereOnly) {
                addRow("", "", bundle.getString("exceedCeiling"), "");
            }
            addRow("", "", bundle.getString("rollMoreThanOnce"), "");
            if (entity.getWalkMP() > 0) {
                addRow("", "", bundle.getString("thrustExceedsSI"), "");
            }
            if (!spaceOnly) {
                addRow("", "", bundle.getString("velocityOver2xThrust"), "");
                addRow("", "", bundle.getString("stalling"), "");
                addRow("", "", bundle.getString("descending3plus"), "");
            }
        }
        addRow(NO_SHADE, bundle.getString("damage"), "", "");
        addRow("", "", bundle.getString("avionicsCritical"), "");
        addRow("", "", bundle.getString("controlCritical"), "");
        if (!spaceOnly) {
            addRow("", "", bundle.getString("damagedInAtmosphere"), "");
        }

        addRow(SECTION_HEADER + bundle.getString("modifiers"), "", "", "");
        if ((entity instanceof SmallCraft) || (entity instanceof Jumpship)
              || entity.isSupportVehicle()) {
            addRow("", bundle.getString("crewDamage"), "", "+1" + bundle.getString("perHit"));
        } else {
            addRow("", bundle.getString("pilotDamage"), "", "+1" + bundle.getString("perHit"));
        }
        addRow("", bundle.getString("avionicsDamage"), "", "+1" + bundle.getString("perHit"));
        addRow("", bundle.getString("avionicsDestroyed"), "", "+5");
        addRow("", bundle.getString("lifeSupportDamage"), "", "+2");
        if (entity.getWalkMP() > 0) {
            addRow("", bundle.getString("aboveSafeThrust"), "", "+1");
        }
        if (!spaceOnly) {
            // Combining +2 atmospheric mod with the unit type adjustment
            if (entity instanceof Dropship dropship) {
                addRow("",
                      bundle.getString("atmosphericOperations"),
                      "",
                      dropship.isSpheroid() ? "+3" : "+2");
            } else {
                addRow("", bundle.getString("atmosphericOperations"), "", "+1");
            }
            addRow("", "", bundle.getString("per20points"), "+1");
            addRow("", "", bundle.getString("above2xSafeThrust"), "+1" + bundle.getString("perPoint"));
        }
    }
}
