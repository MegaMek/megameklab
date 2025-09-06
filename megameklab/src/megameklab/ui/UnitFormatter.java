/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui;

import static megamek.client.ui.util.UIUtil.CONNECTED_SIGN;
import static megamek.client.ui.util.UIUtil.DOT_SPACER;
import static megamek.client.ui.util.UIUtil.UNCONNECTED_SIGN;
import static megamek.client.ui.util.UIUtil.fontHTML;
import static megamek.client.ui.util.UIUtil.uiC3Color;

import java.text.MessageFormat;

import megamek.client.ui.util.UIUtil;
import megamek.common.units.Entity;

class UnitFormatter {

    public static String getString(String key, Object... args) {
        return MessageFormat.format(ForceBuildUI.getDialogResources().getString(key), args);
    }

    static boolean dotSpacer(StringBuilder current, boolean firstElement) {
        if (!firstElement) {
            current.append(DOT_SPACER);
        }
        return false;
    }

    public static String getCell(Entity entity) {
        StringBuilder result = new StringBuilder("<HTML><NOBR>" + fontHTML());
        result.append(entity.getShortNameRaw()).append("</FONT>");
        if (entity.hasC3i() || entity.hasNavalC3()) {
            result.append(DOT_SPACER).append(UIUtil.fontHTML(uiC3Color()));
            String msg_c3i = getString("ForceBuildDialog.cell.C3i");
            String msg_nc3 = getString("ForceBuildDialog.cell.NC3");

            String c3Name = entity.hasC3i() ? msg_c3i : msg_nc3;
            if (entity.calculateFreeC3Nodes() >= 5) {
                result.append(c3Name).append(UNCONNECTED_SIGN);
            } else {
                result.append(c3Name).append(CONNECTED_SIGN).append(entity.getC3NetId());
            }
            result.append("</FONT>");
        }
        if (entity.hasC3()) {
            String msg_c3sabrv = getString("ForceBuildDialog.cell.C3SAbrv");
            String msg_c3m = getString("ForceBuildDialog.cell.C3M");
            String msg_c3mcc = getString("ForceBuildDialog.cell.C3MCC");

            result.append(DOT_SPACER).append(UIUtil.fontHTML(uiC3Color()));
            if (entity.getC3Master() == null) {
                if (entity.hasC3S()) {
                    result.append(msg_c3sabrv).append(UNCONNECTED_SIGN);
                }
                if (entity.hasC3M()) {
                    result.append(msg_c3m);
                }
            } else if (entity.C3MasterIs(entity)) {
                result.append(msg_c3mcc);
            } else {
                if (entity.hasC3S()) {
                    result.append(msg_c3sabrv).append(CONNECTED_SIGN);
                } else {
                    result.append(msg_c3m).append(CONNECTED_SIGN);
                }
                result.append(entity.getC3Master().getChassis());
            }
            result.append("</FONT>");
        }

        return result.toString();
    }
}
