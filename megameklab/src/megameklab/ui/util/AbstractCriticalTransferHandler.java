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
 */
package megameklab.ui.util;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

public class AbstractCriticalTransferHandler extends TransferHandler {

    protected final EntitySource eSource;
    protected RefreshListener refresh;

    public AbstractCriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        this.eSource = eSource;
        this.refresh = refresh;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    protected void changeMountStatus(Mounted<?> eq, int location) {
        changeMountStatus(eq, location, -1);
    }

    protected void changeMountStatus(Mounted<?> eq, int location, int secondaryLocation) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, false);
        doRefresh();
    }

    protected Entity getUnit() {
        return eSource.getEntity();
    }

    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    protected void doRefresh() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }
}
