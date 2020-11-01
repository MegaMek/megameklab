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
        addRow(bundle.getString("situation"), "", "", "");
        if (entity.getWalkMP() > 0) {
            addRow("", bundle.getString("movement"), "", "");
            if (atmosphereOnly) {
                addRow("", "", bundle.getString("exceedCeiling"), "");
            }
            addRow("", "", bundle.getString("rollMoreThanOnce") + "", "");
            if (entity.getWalkMP() > 0) {
                addRow("", "", bundle.getString("thrustExceedsSI") + "", "");
            }
            if (!spaceOnly) {
                addRow("", "", bundle.getString("velocityOver2xThrust") + "", "");
                addRow("", "", bundle.getString("stalling") + "", "");
                addRow("", "", bundle.getString("descending3plus") + "", "");
            }
        }
        addRow("", bundle.getString("damage"), "", "");
        addRow("", "", bundle.getString("avionicsCritical"), "");
        addRow("", "", bundle.getString("controlCritical"), "");
        if (!spaceOnly) {
            addRow("", "", bundle.getString("damagedInAtmosphere"), "");
        }

        addRow(bundle.getString("modifiers"), "", "", "");
        if ((entity instanceof SmallCraft) || (entity instanceof Jumpship)
                || entity.isSupportVehicle()) {
            addRow("", bundle.getString("crewDamage"), "", "+1" + bundle.getString("perHit"));
        } else {
            addRow("", bundle.getString("pilotDamage"), "", "+1" + bundle.getString("perHit"));
        }
        addRow("", bundle.getString("avionicsDamage"), "", "+1" + bundle.getString("perHit"));
        addRow("", bundle.getString("lifeSupportDamage"), "", "+1" + bundle.getString("perHit"));
        if (entity.getWalkMP() > 0) {
            addRow("", bundle.getString("aboveSafeThrust"), "", "+1");
        }
        if (!spaceOnly) {
            // Combining +2 atmospheric mod with the unit type adjustment
            if (entity instanceof Dropship) {
                addRow("", bundle.getString("atmosphericOperations"), "", ((Dropship) entity).isSpheroid() ? "+3" : "+2");
            } else {
                addRow("", bundle.getString("atmosphericOperations"), "", "+1");
            }
            addRow("", "", bundle.getString("per20points"), "+1");
            addRow("", "", bundle.getString("above2xSafeThrust"), "+1" + bundle.getString("perPoint"));
        }
    }
}
