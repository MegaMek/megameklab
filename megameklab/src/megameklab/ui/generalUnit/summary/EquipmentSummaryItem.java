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

import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megameklab.util.UnitUtil;

public class EquipmentSummaryItem extends AbstractSummaryItem {

    @Override
    public String getName() {
        return "Equipment";
    }

    @Override
    public void refresh(Entity entity) {
        double totalWeight = 0;
        int totalCrits = 0;

        for (Mounted<?> m : entity.getMisc()) {
            if (isEquipment(m)) {
                totalWeight += m.getTonnage();
                totalCrits += m.getNumCriticalSlots();
            }
        }
        for (Mounted<?> m : entity.getWeaponList()) {
            totalWeight += m.getTonnage();
            totalCrits += m.getNumCriticalSlots();
        }
        for (Mounted<?> m : entity.getAmmo()) {
            totalWeight += m.getTonnage();
            totalCrits += m.getNumCriticalSlots();
        }
        weightLabel.setText(formatWeight(totalWeight, entity));
        critLabel.setText(formatCrits(totalCrits));
    }

    private boolean isEquipment(Mounted<?> mounted) {
        MiscType miscType = (MiscType) mounted.getType();
        return !UnitUtil.isArmorOrStructure(miscType)
              && !miscType.hasFlag(MiscType.F_TSM)
              && !miscType.hasFlag(MiscType.F_INDUSTRIAL_TSM)
              && !miscType.hasFlag(MiscType.F_MASC)
              && !miscType.hasFlag(MiscType.F_JUMP_JET)
              && !miscType.hasFlag(MiscType.F_UMU)
              && !miscType.hasFlag(MiscType.F_JUMP_BOOSTER)
              && !miscType.hasFlag(MiscType.F_SPONSON_TURRET)
              && !miscType.hasFlag(MiscType.F_HEAT_SINK)
              && !miscType.hasFlag(MiscType.F_DOUBLE_HEAT_SINK);
    }
}
