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

import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megameklab.com.printing.PrintEntity;
import megameklab.com.printing.PrintRecordSheet;
import org.apache.batik.util.SVGConstants;

/**
 * Table for mods to vehicle driving skill checks
 */
public class DrivingSkillRollMods extends ReferenceTable {
    private final Entity entity;

    public DrivingSkillRollMods(PrintRecordSheet sheet, Entity entity) {
        super(sheet, 0.02, 0.08, 0.14, 0.8);
        this.entity = entity;
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(1, SVGConstants.SVG_START_VALUE);
        setColumnAnchor(2, SVGConstants.SVG_START_VALUE);

        addMods();
    }

    public DrivingSkillRollMods(PrintEntity sheet) {
        this(sheet, sheet.getEntity());
    }

    private void addMods() {
        addRow(bundle.getString("unitActions"), "", "", "");
        if (entity.getMovementMode().equals(EntityMovementMode.HOVER)
                || entity.getMovementMode().equals(EntityMovementMode.VTOL)
                || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow("", bundle.getString("flankingMovement"), "",
                    bundle.getString("possibleSideslip"));
            addRow("", bundle.getString("afterFacingChange"), "", "");
            addRow(bundle.getString("sideslipMovement"), "", "", "");
        } else {
            addRow("", bundle.getString("flankingMovement"), "",
                    bundle.getString("possibleSkid"));
            addRow("", bundle.getString("afterFacingChange"), "", "");
            addRow("", bundle.getString("onPavement"), "", "");
            addRow(bundle.getString("skiddingMovement"), "", "", "");
        }
        addRow("", bundle.getString("hexesMovedInTurn"), "", "");
        addRow("", "", "0-2", "-1");
        addRow("", "", "3-4", "0");
        addRow("", "", "5-7", "+1");
        addRow("", "", "8-10", "+2");
        addRow("", "", "11-17", "+3");
        addRow("", "", "18-24", "+4");
        addRow("", "", "25+", "+5");
        addRow(bundle.getString("enteringLeavingBuilding"), "", "", "");
        addRow("", bundle.getString("lightBuilding"), "", "0");
        addRow("", bundle.getString("mediumBuilding"), "", "+1");
        addRow("", bundle.getString("heavyBuilding"), "", "+2");
        addRow("", bundle.getString("hardenedBuilding"), "", "+3");
        addRow("", bundle.getString("hexesMovedInTurn"), "", "");
        addRow("", "", "1-2", "0");
        addRow("", "", "3-4", "+1");
        addRow("", "", "5-6", "+2");
        addRow("", "", "7-9", "+3");
        addRow("", "", "10-17", "+4");
        addRow("", "", "18-24", "+5");
        addRow("", "", "25+", "+6");
    }
}
