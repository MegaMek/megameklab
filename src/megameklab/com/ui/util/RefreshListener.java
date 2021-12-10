/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.util;

import java.util.EventListener;

public interface RefreshListener extends EventListener{
    void refreshHeader();
    void refreshStatus();
    void refreshAll();
    void refreshStructure();
    void refreshArmor();
    void refreshWeapons();
    void refreshEquipment();
    void refreshTransport();
    void refreshBuild();
    void refreshPreview();

    // Refreshers for just one thing on a tab
    void refreshSummary();
    void refreshEquipmentTable();
}