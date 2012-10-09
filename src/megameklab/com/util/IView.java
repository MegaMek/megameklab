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

import javax.swing.JPanel;

import megamek.common.Aero;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.Mech;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.VTOL;

public class IView extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -6741722012756653309L;
    public Entity unit;

    public IView(Mech unit) {
        this.unit = unit;
    }

    public IView(Tank unit) {
        this.unit = unit;
    }

    public IView(BattleArmor unit) {
        this.unit = unit;
    }

    public IView(Infantry unit) {
        this.unit = unit;
    }

    public void updateUnit(Entity unit) {
        this.unit = unit;
    }

    public Mech getMech() {
        return (Mech) unit;
    }

    public Tank getTank() {
        return (Tank) unit;
    }

    public VTOL getVTOL() {
        return (VTOL) unit;
    }

    public Aero getAero() {
        return (Aero) unit;
    }

    public BattleArmor getBattleArmor() {
        return (BattleArmor) unit;
    }

    public boolean isHeatSink(Mounted mounted) {
        EquipmentType eq = mounted.getType();
        if (eq.hasFlag(MiscType.F_HEAT_SINK)
                || eq.hasFlag(MiscType.F_DOUBLE_HEAT_SINK)
                || eq.hasFlag(MiscType.F_IS_DOUBLE_HEAT_SINK_PROTOTYPE)
                || eq.hasFlag(MiscType.F_LASER_HEAT_SINK)) {
            return true;
        }
        return false;
    }

    public Infantry getInfantry() {
    	return (Infantry) unit;
    }
}