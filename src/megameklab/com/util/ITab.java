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
import megamek.common.Mech;
import megamek.common.Tank;
import megamek.common.VTOL;

public class ITab extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5771036622436158024L;
    public Entity unit;

    public ITab() {

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

    public BattleArmor getBA() {
        return (BattleArmor) unit;
    }

}