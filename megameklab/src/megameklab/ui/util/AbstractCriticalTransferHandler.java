/*
 * Copyright (C) 2023-2026 The MegaMek Team. All Rights Reserved.
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

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.AeroSpaceFighter;
import megamek.common.units.Entity;
import megamek.common.equipment.Mounted;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestBattleArmor;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.util.AeroUtil;
import megameklab.util.BattleArmorUtil;
import megameklab.util.UnitUtil;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * This is a base class for some of the transfer handlers used in MML. There is a conflict of targets between unifying
 * them and keeping them readable because of the different handling the different Entity types require.
 */
public class AbstractCriticalTransferHandler extends TransferHandler {

    private static final MMLogger LOGGER = MMLogger.create(AbstractCriticalTransferHandler.class);

    protected final EntitySource eSource;
    protected RefreshListener refresh;

    public AbstractCriticalTransferHandler(EntitySource eSource, @Nullable RefreshListener refresh) {
        this.eSource = eSource;
        this.refresh = refresh;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    /**
     * Moves the given equipment to the given location (removing it from any former location). Forwards to UnitUtil
     * .changeMountStatus() and calls for a refresh using the present refresh listener, if any.
     *
     * @param eq       The Mounted to move
     * @param location The new location; may be LOC_NONE
     *
     * @see UnitUtil#changeMountStatus(Entity, Mounted, int, int, boolean)
     */
    protected void changeMountStatus(Mounted<?> eq, int location) {
        changeMountStatus(eq, location, -1);
    }

    /**
     * Moves the given equipment to the given location (removing it from any former location) and secondary location.
     * Forwards to UnitUtil.changeMountStatus() and calls for a refresh using the present refresh listener, if any.
     *
     * @param eq                The Mounted to move
     * @param location          The new location; may be LOC_NONE
     * @param secondaryLocation The new secondary location
     *
     * @see UnitUtil#changeMountStatus(Entity, Mounted, int, int, boolean)
     */
    protected void changeMountStatus(Mounted<?> eq, int location, int secondaryLocation) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, false);
        doRefresh();
    }

    /**
     * @return The unit that is currently edited.
     */
    protected Entity getUnit() {
        return eSource.getEntity();
    }

    /**
     * Sets a refresh listener to forward refresh calls to. Calls to doRefresh() will use it.
     *
     * @param refresh The new refresh listener
     */
    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }

    /**
     * Calls for a refresh using the present refresh listener, if any. If no refresh listener has been set, does
     * nothing.
     */
    protected void doRefresh() {
        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    /**
     * Gets the Mounted equipment from the given transferable. All exceptions are caught and null is returned instead if
     * an exception occurred. This implicitly checks if the String data flavor is supported.
     *
     * @param transferable The Transferable from the present transfer action
     *
     * @return The equipment that is being dragged or null in the case of any error
     */
    protected Mounted<?> getMountedFromTransferable(Transferable transferable) {
        try {
            int index = Integer.parseInt((String) transferable.getTransferData(DataFlavor.stringFlavor));
            return getUnit().getEquipment(index);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * Returns true when the given mounted is allowed to be dragged on the present unit type, false otherwise. Returns
     * false when the given mounted is null.
     *
     * @param mounted The Mounted equipment
     *
     * @return True when dragging is allowed, false otherwise
     */
    protected boolean isDraggable(@Nullable Mounted<?> mounted) {
        return (mounted != null) && !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType());
    }

    protected boolean doImport(Mounted<?> mounted, Component target) {
        if (mounted == null) {
            return false;
        }

        if (getUnit() instanceof AeroSpaceFighter fighter) {
            return importFighter(fighter, mounted, target);
        } else if (getUnit() instanceof BattleArmor battleArmor) {
            return importBA(battleArmor, mounted, target);
        }

        // other unit types
        try {
            UnitUtil.removeCriticalSlots(getUnit(), mounted);
            changeMountStatus(mounted, Entity.LOC_NONE);
            return true;
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }
        return false;
    }

    private boolean importFighter(AeroSpaceFighter aero, Mounted<?> mounted, Component target) {
        int location = Entity.LOC_NONE;
        try {
            location = Integer.parseInt(target.getName());
        } catch (NumberFormatException e) {
            // the target is the unallocated equipment list
        }

        if (location != Entity.LOC_NONE) {
            if (!UnitUtil.isValidLocation(aero, mounted.getType(), location)) {
                PopupMessages.showInvalidLocationInfo(SwingUtilities.getWindowAncestor(target),
                      mounted.getName(),
                      aero.getLocationName(location));
                return false;
            }

            if (TestAero.usesWeaponSlot(aero, mounted.getType()) && !TestAero.hasFreeWeaponSlot(aero, location)) {
                PopupMessages.showLocationFullError(SwingUtilities.getWindowAncestor(target));
                return false;
            }
        }

        try {
            AeroUtil.moveOrAddEquipmentOnFighter(aero, mounted, location);
            doRefresh();
            return true;
        } catch (LocationFullException e) {
            // on Aero, this is currently not actually thrown; also, weapon space is already tested
            LOGGER.error("Unexpected LocationFullException", e);
            return false;
        }
    }

    private boolean importBA(BattleArmor battleArmor, Mounted<?> mounted, Component target) {
        // BA use a special placement encoding to deliver both location and trooper
        int location;
        int trooper;
        try {
            String[] split = target.getName().split(":");
            location = Integer.parseInt(split[0]);
            trooper = Integer.parseInt(split[1]);
        } catch (Exception e) {
            // the target is the unallocated equipment list
            BattleArmorUtil.unallocateMounted(battleArmor, mounted);
            doRefresh();
            return true;
        }

        if ((location == mounted.getBaMountLoc()) && (trooper == mounted.getLocation())) {
            // dropped into the same location
            return false;
        }

        if (location != Entity.LOC_NONE) {
            if (!UnitUtil.isValidLocation(battleArmor, mounted.getType(), location)
                  || !TestBattleArmor.isMountLegal(battleArmor, mounted, location, trooper)) {

                PopupMessages.showInvalidLocationInfo(SwingUtilities.getWindowAncestor(target),
                      mounted.getName(),
                      getUnit().getLocationName(location));
                return false;
            }
        }

        try {
            mounted.setBaMountLoc(location);
            if (mounted.getLocation() == BattleArmor.LOC_SQUAD) {
                changeMountStatus(mounted, mounted.getLocation());
            } else {
                changeMountStatus(mounted, trooper);
            }
            return true;
        } catch (Exception ex) {
            LOGGER.error("", ex);
            return false;
        }
    }
}
