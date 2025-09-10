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

import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megameklab.printing.PrintRecordSheet;
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

    private void addMods() {
        addRow(SECTION_HEADER + bundle.getString("unitActions"), "", "", "");
        if (entity.getMovementMode().equals(EntityMovementMode.HOVER)
              || entity.getMovementMode().equals(EntityMovementMode.VTOL)
              || entity.getMovementMode().equals(EntityMovementMode.WIGE)) {
            addRow(NO_SHADE, bundle.getString("flankingMovement") + "\n" + bundle.getString("afterFacingChange"), "",
                  bundle.getString("possibleSideslip"));
            addRow(SECTION_HEADER + bundle.getString("sideslipMovement"), "", "", "");
        } else {
            addRow(NO_SHADE,
                  bundle.getString("flankingMovement")
                        + "\n"
                        + bundle.getString("afterFacingChange")
                        + "\n"
                        + bundle.getString("onPavement"),
                  "",
                  bundle.getString("possibleSkid"));
            addRow(SECTION_HEADER + bundle.getString("skiddingMovement"), "", "", "");
        }
        addRow("", bundle.getString("hexesMovedInTurn"), "", "");
        addRow("", "", "0-2", "-1");
        addRow("", "", "3-4", "0");
        addRow("", "", "5-7", "+1");
        addRow("", "", "8-10", "+2");
        addRow("", "", "11-17", "+3");
        addRow("", "", "18-24", "+4");
        addRow("", "", "25+", "+5");
        addRow(SECTION_HEADER + bundle.getString("enteringLeavingBuilding"), "", "", "");
        addRow("", bundle.getString("lightBuilding"), "", "0");
        addRow("", bundle.getString("mediumBuilding"), "", "+1");
        addRow("", bundle.getString("heavyBuilding"), "", "+2");
        addRow("", bundle.getString("hardenedBuilding"), "", "+3");
        addRow(SECTION_HEADER, bundle.getString("hexesMovedInTurn"), "", "");
        addRow("", "", "1-2", "0");
        addRow("", "", "3-4", "+1");
        addRow("", "", "5-6", "+2");
        addRow("", "", "7-9", "+3");
        addRow("", "", "10-17", "+4");
        addRow("", "", "18-24", "+5");
        addRow("", "", "25+", "+6");
    }
}
