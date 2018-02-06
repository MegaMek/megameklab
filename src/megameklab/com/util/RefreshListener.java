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

package megameklab.com.util;

import java.util.EventListener;

public interface RefreshListener extends EventListener{
    public void refreshHeader();
    public void refreshStatus();
    public void refreshAll();
    public void refreshStructure();
    public void refreshArmor();
    public void refreshWeapons();
    public void refreshEquipment();
    public void refreshTransport();
    public void refreshBuild();
    public void refreshPreview();

    // Refreshers for just one thing on a tab
    public void refreshSummary();
    public void refreshEquipmentTable();
}