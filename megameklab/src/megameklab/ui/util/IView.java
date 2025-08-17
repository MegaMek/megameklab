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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */

package megameklab.ui.util;

import javax.swing.JPanel;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.GunEmplacement;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.SmallCraft;
import megamek.common.units.Tank;
import megamek.common.units.VTOL;
import megameklab.ui.EntitySource;

public class IView extends JPanel {

    protected EntitySource eSource;

    public IView(EntitySource eSource) {
        this.eSource = eSource;
    }

    public Entity getEntity() {
        return eSource.getEntity();
    }

    public Mek getMek() {
        return (Mek) eSource.getEntity();
    }

    public ProtoMek getProtoMek() {
        return (ProtoMek) eSource.getEntity();
    }

    public Tank getTank() {
        return (Tank) eSource.getEntity();
    }

    public GunEmplacement getGunEmplacement() {
        return (GunEmplacement) eSource.getEntity();
    }

    public VTOL getVTOL() {
        return (VTOL) eSource.getEntity();
    }

    public Aero getAero() {
        return (Aero) eSource.getEntity();
    }

    public SmallCraft getSmallCraft() {
        return (SmallCraft) eSource.getEntity();
    }

    public Jumpship getJumpship() {
        return (Jumpship) eSource.getEntity();
    }

    public BattleArmor getBattleArmor() {
        return (BattleArmor) eSource.getEntity();
    }

    public Infantry getInfantry() {
        return (Infantry) eSource.getEntity();
    }

    /**
     * @return true if the entity has the Small Craft type flag (no instanceof check). This includes DropShips.
     */
    public boolean isSmallCraft() {
        return getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT);
    }

    /**
     * @return true if the entity has the JumpShip type flag (no instanceof check). This includes WarShips and Space
     *       Stations.
     */
    public boolean isJumpShip() {
        return getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP);
    }

    /**
     * @return true if the entity has the WarShip type flag (no instanceof check).
     */
    public boolean isWarShip() {
        return getEntity().hasETypeFlag(Entity.ETYPE_WARSHIP);
    }

    /**
     * @return true if the entity is a VTOL Combat Vehicle. This is an instanceof check and includes Support VTOLs but
     *       not VTOL type Infantry.
     */
    public boolean isVTOL() {
        return getEntity() instanceof VTOL;
    }
}
