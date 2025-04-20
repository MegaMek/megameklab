/*
 * Copyright (C) 2008-2025 The MegaMek Team. All Rights Reserved.
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

import java.util.EventListener;

public interface RefreshListener extends EventListener {

    /**
     * Schedules a full refresh. This can be called any number of times, but the refresh itself will be performed only
     * once at the end of the current Swing UI Event (it is scheduled using
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
}
