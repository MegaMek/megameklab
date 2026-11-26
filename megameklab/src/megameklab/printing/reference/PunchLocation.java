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

import java.util.List;

import megamek.common.ToHitData;
import megamek.common.game.Game;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.QuadMek;
import megameklab.printing.PrintMek;

/**
 * Hit location table for punch attacks
 */
public class PunchLocation extends ReferenceTable {

    public PunchLocation(PrintMek sheet) {
        super(sheet, 0.1, 0.35, 0.6, 0.85);
        setHeaders(bundle.getString("dieRoll1d6"), bundle.getString("leftSide"),
              bundle.getString("frontRear"), bundle.getString("rightSide"));
        for (int roll = 1; roll <= 6; roll++) {
            addRow(punchRow(sheet.getEntity(), roll));
        }
    }

    static List<String> punchRow(Entity entity, int roll) {
        boolean quad = entity instanceof QuadMek;
        var charts = Game.rulesManager.getRulesCharts();
        int left = charts.getPunchHitLocation(roll, ToHitData.SIDE_LEFT, quad);
        int right = charts.getPunchHitLocation(roll, ToHitData.SIDE_RIGHT, quad);
        int front = charts.getPunchHitLocation(roll, ToHitData.SIDE_FRONT, quad);
        int rear = charts.getPunchHitLocation(roll, ToHitData.SIDE_REAR, quad);
        String frontRear = locationAbbr(entity, front);
        if (front != rear) {
            frontRear += "/" + locationAbbr(entity, rear);
        }
        return List.of(Integer.toString(roll), locationAbbr(entity, left), frontRear, locationAbbr(entity, right));
    }

    private static String locationAbbr(Entity entity, int location) {
        if (entity instanceof QuadMek) {
            // Use the same leg abbreviations as the other record sheet reference tables.
            return switch (location) {
                case Mek.LOC_LEFT_ARM -> "LFL";
                case Mek.LOC_RIGHT_ARM -> "RFL";
                case Mek.LOC_LEFT_LEG -> "LRL";
                case Mek.LOC_RIGHT_LEG -> "RRL";
                default -> entity.getLocationAbbr(location);
            };
        }
        return entity.getLocationAbbr(location);
    }
}
