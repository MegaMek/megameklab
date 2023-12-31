/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
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
package megameklab.ui.generalUnit.summary;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megamek.common.Entity;
import megamek.common.verifier.TestEntity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
