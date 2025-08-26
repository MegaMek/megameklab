/*
 * Copyright (C) 2020-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.printing.reference;

import megamek.common.units.Aero;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megameklab.printing.PrintAero;
import org.apache.batik.util.SVGConstants;

/**
 * Hit mods and altitude requirements for air-to-ground attacks
 */
public class AirToGroundAttackTable extends ReferenceTable {

    private final Aero aero;

    public AirToGroundAttackTable(PrintAero sheet) {
        super(sheet, 0.02, 0.5, 0.8);
        this.aero = (Aero) sheet.getEntity();
        setHeaders(bundle.getString("attackType"), bundle.getString("modifier"),
              bundle.getString("altitude"));
        setColumnAnchor(0, SVGConstants.SVG_START_VALUE);
        addRows();
    }

    private void addRows() {
        if (canStrafe()) {
            addRow(bundle.getString("strafing"), "+4", "1-3");
        }
        addRow(bundle.getString("striking"), "+2", "2-5");
        if (canBomb()) {
            addRow(bundle.getString("diveBombing"), "+2", "3-5");
            addRow(bundle.getString("altitudeBombing"), "+2", "1+");
            addRow("", bundle.getString("plusAltitude"), "");
        }
    }

    private boolean canStrafe() {
        if (aero.isSpheroid()) {
            return false;
        }
        for (Mounted<?> mounted : aero.getIndividualWeaponList()) {
            if (mounted.getType().hasFlag(WeaponType.F_ENERGY)
                  && ((WeaponType) mounted.getType()).getAmmoType() == AmmoType.AmmoTypeEnum.NA) {
                return true;
            }
        }
        return false;
    }

    private boolean canBomb() {
        if (aero.isSupportVehicle()) {
            return aero.hasWorkingMisc(MiscType.F_EXTERNAL_STORES_HARDPOINT);
        } else {
            return aero.isFighter();
        }
    }
}
