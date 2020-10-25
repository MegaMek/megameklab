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
import megameklab.com.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;

/**
 * General table for movement costs
 */
public class MovementCost extends ReferenceTable {
    private final Entity entity;

    public MovementCost(PrintRecordSheet sheet, Entity entity) {
        super(sheet, 0.02, 0.08, 0.14, 0.8);
        this.entity = entity;
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(2, SVGConstants.SVG_START_VALUE);
        if (entity.getMovementMode().equals(EntityMovementMode.NAVAL)
                || entity.getMovementMode().equals(EntityMovementMode.HYDROFOIL)
                || entity.getMovementMode().equals(EntityMovementMode.SUBMARINE)) {
            addNavalMods();
        } else if (entity.getMovementMode().equals(EntityMovementMode.VTOL)
                || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addAerialMods();
        } else {
            addGroundMods();
        }
        if (entity.hasMisc(MiscType.F_LIMITED_AMPHIBIOUS)) {
            addNote(bundle.getString("limitedAmphibious.note.1"));
            addNote(bundle.getString("limitedAmphibious.note.2"));
        }
    }

    public MovementCost(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    private void addGroundMods() {
        addRow(bundle.getString("costToEnterAnyHex"), "", "", "1");
        addRow(bundle.getString("terrainCost"), "", "", "");
        if (entity.isSupportVehicle() && !entity.hasMisc(MiscType.F_OFF_ROAD)) {
            addRow("", bundle.getString("clear"), "", "+1");
        } else {
            addRow("", bundle.getString("clear"), "", "0");
        }
        addRow("", bundle.getString("paved"), "", "0");
        addRow("", bundle.getString("road"), "", "0");
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)) {
            addRow("", bundle.getString("rough"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("rough"), "", "+1");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)
                || entity.getMovementMode().equals(EntityMovementMode.HOVER)
                || entity.getMovementMode().equals(EntityMovementMode.VTOL)
                || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("lightWoods"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("lightWoods"), "", "+1");
        }
        if (entity instanceof Tank) {
            addRow("", bundle.getString("heavyWoods"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("heavyWoods"), "", "+2");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.HOVER)
                || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("water"), "", "0");
        } else if (entity.hasMisc(MiscType.F_AMPHIBIOUS) || entity.hasMisc(MiscType.F_LIMITED_AMPHIBIOUS)) {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "0");
            addRow("", "", bundle.getString("depth1plus"), "+1");
        } else if (entity instanceof Tank) {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "0");
            addRow("", "", bundle.getString("depth1plus"), bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("water"), "", "");
            addRow("", "", bundle.getString("depth0"), "0");
            addRow("", "", bundle.getString("depth1"), "+1");
            addRow("", "", bundle.getString("depth2plus"), "+3");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.WHEELED)) {
            addRow("", bundle.getString("rubble"), "", bundle.getString("prohibited"));
        } else {
            addRow("", bundle.getString("rubble"), "", "+1");
        }
        if (!entity.getMovementMode().equals(EntityMovementMode.VTOL)
                && !entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("lightBuilding"), "", "+1");
            addRow("", bundle.getString("mediumBuilding"), "", "+2");
            addRow("", bundle.getString("heavyBuilding"), "", "+3");
            addRow("", bundle.getString("hardenedBuilding"), "", "+4");
        }
        if (entity.getMovementMode().equals(EntityMovementMode.VTOL)) {
            addRow(bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1PerLevel"));
        } else if ((entity instanceof Tank) || (entity instanceof Protomech)) {
            addRow(bundle.getString("levelChangeUpOrDown"), "", "", "");
            addRow("", bundle.getString("1level"), "",
                    (entity instanceof Protomech) ? "+1" : "+2");
            addRow("", bundle.getString("2plusLevels"), "", bundle.getString("prohibited"));
        } else {
            addRow(bundle.getString("levelChangeUpOrDown"), "", "", "");
            addRow("", bundle.getString("1level"), "", "+1");
            addRow("", bundle.getString("2levels"), "", "+2");
            addRow("", bundle.getString("3plusLevels"), "", bundle.getString("prohibited"));
        }
        addRow(bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexside"));
        if (entity instanceof Mech) {
            addRow("", bundle.getString("dropToGround"), "", "1");
            addRow("", bundle.getString("standUp"), "", bundle.getString("2perAttempt"));
            if (entity instanceof LandAirMech) {
                addRow("", bundle.getString("liftOffHover"), "", "5");
            }
        } else if ((entity instanceof Protomech) && ((Protomech) entity).isGlider()) {
            addRow("", bundle.getString("liftOffHover"), "", "3");
        } else if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("liftOff"), "", "5");
            addRow("", bundle.getString("maintainAltitude"), "", "+2");
        }
    }

    private void addNavalMods() {
        addRow(bundle.getString("water"), "", "", "");
        addRow("", bundle.getString("depth0"), "", bundle.getString("prohibited"));
        addRow("", bundle.getString("depth1plus"), "", "1");
        if (entity.getMovementMode().equals(EntityMovementMode.SUBMARINE)) {
            addRow(bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1PerLevel"));
        }
        addRow(bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexside"));
    }

    private void addAerialMods() {
        addRow(bundle.getString("costToEnterAirborne"), "", "", "1");
        addRow("", bundle.getString("woods"), "", bundle.getString("prohibited"));
        addRow("", "", bundle.getString("exceptAlongRoad"), "");
        addRow("", bundle.getString("building"), "", bundle.getString("prohibited"));
        if (entity.getMovementMode().equals(EntityMovementMode.VTOL)) {
            addRow(bundle.getString("levelChangeUpOrDown"), "", "", bundle.getString("1PerLevel"));
        } else if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow(bundle.getString("levelChange"), "", "", "");
            addRow("", bundle.getString("up1level"), "", "0");
            addRow("", bundle.getString("up2plusLevels"), "", bundle.getString("prohibited"));
            addRow("", bundle.getString("down1plusLevels"), "", "0");
        }
        addRow(bundle.getString("additionalMovementActions"), "", "", "");
        addRow("", bundle.getString("facingChange"), "", bundle.getString("1perHexside"));
        if (entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("liftOff"), "", "5");
            addRow("", bundle.getString("maintainAltitude"), "", "+2");
        }
    }
}
