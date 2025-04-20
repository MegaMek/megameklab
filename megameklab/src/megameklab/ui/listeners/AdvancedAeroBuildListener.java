/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.listeners;

import java.util.List;

/**
 * Listener for views used by jumpships, warships, and space stations.
 *
 * @author Neoancient
 */
public interface AdvancedAeroBuildListener extends AeroVesselBuildListener {

    void tonnageChanged(double tonnage);

    void lfBatteryChanged(boolean battery);

    void modularChanged(boolean modular);

    void sailChanged(boolean sail);

    void baseTypeChanged(int type);

    void rangeChanged(int range);

    void siChanged(int si);

    void gravDecksChanged(List<Integer> deckSizes);

}
