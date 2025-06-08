/*
 * Copyright (c) 2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.ui.combatVehicle;

import megamek.client.ui.clientGUI.calculationReport.CalculationReport;
import megamek.common.Mounted;
import megameklab.ui.generalUnit.StatusBar;

public class GEStatusBar extends StatusBar {

    private static final String WEIGHT_LABEL = "Weight: %s t";

    public GEStatusBar(GEMainUI parent) {
        super(parent);
    }

    @Override
    protected void refreshWeight() {
        double tonnage = getEntity().getEquipment().stream().mapToDouble(Mounted::getTonnage).sum();
        String full = CalculationReport.formatForReport(tonnage);
        tons.setText(String.format(WEIGHT_LABEL, full));
        tons.setToolTipText("Click to show the weight calculation.");
    }
}
