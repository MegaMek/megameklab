/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.com.printing.reference;

import megamek.common.*;
import megameklab.com.printing.PrintAero;
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
        for (Mounted mounted : aero.getIndividualWeaponList()) {
            if (mounted.getType().hasFlag(WeaponType.F_ENERGY)
                    && ((WeaponType) mounted.getType()).getAmmoType() == AmmoType.F_NONE) {
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
