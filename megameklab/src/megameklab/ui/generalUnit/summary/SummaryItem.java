/*
 * Copyright (C) 2023-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit.summary;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import megamek.client.ui.clientGUI.calculationReport.CalculationReport;
import megamek.common.units.Entity;
import megamek.common.verifier.TestEntity;

/**
 * This interface is implemented by items that each make up one line in the unit weight/crit/avail summary on the
 * structure tab. For example, {@link EngineSummaryItem} controls the "Engine:" line in the unit summary for all unit
 * types, i.e. it calculates and shows weight, crits and availability of the unit's engine. A base implementation is
 * present in {@link AbstractSummaryItem}.
 */
public interface SummaryItem {

    Border outerLabelBorder = new LineBorder(UIManager.getColor("Separator.foreground"));
    Border innerLabelBorder = new EmptyBorder(0, 10, 0, 10);
    Border labelBorder = new CompoundBorder(outerLabelBorder, innerLabelBorder);

    String getName();

    JComponent getWeightComponent();

    JComponent getCritsComponent();

    JComponent getAvailabilityComponent();

    void refresh(Entity entity);

    default String formatCrits(int crits) {
        return (crits == 0) ? "" : Integer.toString(crits);
    }

    default String formatWeight(double weight, Entity entity) {
        if (TestEntity.usesKgStandard(entity)) {
            return formatWeightKg(weight);
        } else {
            return formatWeight(weight);
        }
    }

    private static String formatWeight(double weight) {
        return (weight == 0 ? "" : CalculationReport.formatForReport(weight) + " t");
    }

    private static String formatWeightKg(double weight) {
        return (weight == 0 ? "" : CalculationReport.formatForReport(weight * 1000) + " kg");
    }
}
