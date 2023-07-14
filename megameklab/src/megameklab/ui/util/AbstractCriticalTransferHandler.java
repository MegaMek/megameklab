/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.util;

import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

import javax.swing.*;

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

    protected void changeMountStatus(Mounted eq, int location) {
        changeMountStatus(eq, location, -1);
    }

    protected void changeMountStatus(Mounted eq, int location, int secondaryLocation) {
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
