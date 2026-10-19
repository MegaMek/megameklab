/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import megamek.common.equipment.AmmoType;
import megamek.common.equipment.Engine;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Mek;

/**
 * The estimated heat a Mek or aerospace unit builds up in a turn of moving and firing everything, as shown in the
 * status bar of the unit editors. It counts movement heat for Meks, every working weapon (one-shot weapons and rocket
 * launchers at a quarter, Ultra autocannon twice, rotary autocannon six times, Streak launchers at half) including
 * weapons held in bays, and heat-producing equipment. Shared so that the MekHQ unit editor shows the same figure.
 */
public final class HeatGenerationEstimate {
    private HeatGenerationEstimate() {}

    /**
     * Returns an estimated value of the total heat generation for Meks and Aerospace (0 for other types).
     *
     * @param entity the unit to estimate
     *
     * @return the estimated heat generated, rounded to a whole number
     */
    public static long estimate(Entity entity) {
        if (!(entity instanceof Mek) && !(entity instanceof Aero)) {
            return 0;
        }
        double heat = 0;

        if (entity instanceof Mek mek) {
            if (mek.getJumpMP() > 0) {
                heat += mek.getJumpHeat(mek.getJumpMP());
            } else if (mek.getEngineType() == Engine.XXL_ENGINE) {
                heat += 6;
            } else {
                heat += 2;
            }
        }

        for (Mounted<?> mounted : entity.getTotalWeaponList()) {
            WeaponType weaponType = (WeaponType) mounted.getType();
            double weaponHeat = weaponType.getHeat();

            if (mounted.isMissing() || mounted.isHit() || mounted.isDestroyed() || mounted.isBreached()) {
                continue;
            }

            if ((weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.ROCKET_LAUNCHER)
                  || weaponType.hasFlag(WeaponType.F_ONE_SHOT)) {
                weaponHeat *= 0.25;
            }

            if ((weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.AC_ULTRA) || (weaponType.getAmmoType()
                  == AmmoType.AmmoTypeEnum.AC_ULTRA_THB)) {
                weaponHeat *= 2;
            }

            if (weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.AC_ROTARY) {
                weaponHeat *= 6;
            }

            if ((weaponType.getAmmoType() == AmmoType.AmmoTypeEnum.SRM_STREAK) || (weaponType.getAmmoType()
                  == AmmoType.AmmoTypeEnum.LRM_STREAK)) {
                weaponHeat *= 0.5;
            }
            heat += weaponHeat;
        }

        for (Mounted<?> mounted : entity.getMisc()) {
            if (mounted.getType().hasFlag(MiscType.F_LASER_INSULATOR)) {
                heat--;
            } else if (mounted.getType().hasFlag(MiscType.F_PPC_CAPACITOR)) {
                heat += 5;
            } else {
                heat += mounted.getType().getHeat();
            }
        }
        return Math.round(heat);
    }
}
