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
package megameklab.ui.util;

import java.util.EventListener;

public interface RefreshListener extends EventListener {

    /**
     * Schedules a full refresh. This can be called any number of times but the refresh itself will be performed
     * only once at the end of the current Swing UI Event (it is scheduled using
     * {@link javax.swing.SwingUtilities#invokeLater(Runnable)}) and prevented from running several times in a row.
     */
    void scheduleRefresh();

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

    void requestDirtyCheck();
}