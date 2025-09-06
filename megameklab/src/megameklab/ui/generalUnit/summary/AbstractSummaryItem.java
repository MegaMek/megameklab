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
import javax.swing.JLabel;

import megamek.common.units.Entity;

/**
 * This is a base implementation for {@link SummaryItem}. It offers three text items {@link #weightLabel},
 * {@link #critLabel} and {@link #availabilityLabel} that should be updated in {@link SummaryItem#refresh(Entity)}.
 * These are currently JLabels but JTextFields could also be used (they obviously have to support showing a text but the
 * common base class of both is JComponent which does not allow setting a text).
 */
public abstract class AbstractSummaryItem implements SummaryItem {

    protected final JLabel weightLabel = new SummaryWeightLabel();
    protected final JLabel critLabel = new SummaryWeightLabel();
    protected final JLabel availabilityLabel = new SummaryAvailabilityLabel();

    @Override
    public JComponent getWeightComponent() {
        return weightLabel;
    }

    @Override
    public JComponent getCritsComponent() {
        return critLabel;
    }

    @Override
    public JComponent getAvailabilityComponent() {
        return availabilityLabel;
    }
}
