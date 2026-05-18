/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JTable;

import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.Mounted;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

/**
 * The crit slot Transfer Handler for BA and ASF.
 *
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAASCriticalTransferHandler extends AbstractCriticalTransferHandler {
    private static final MMLogger logger = MMLogger.create(BAASCriticalTransferHandler.class);

    private final CriticalSlotsView critView;

    public BAASCriticalTransferHandler(EntitySource eSource, RefreshListener refresh, CriticalSlotsView critView) {
        super(eSource, refresh);
        this.critView = critView;
    }

    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop() || !canImport(info)) {
            return false;
        }

        Mounted<?> mounted = getMountedFromTransferable(info.getTransferable());
        if (mounted == null) {
            return false;
        }

        return doImport(mounted, info.getComponent());
    }

    @Override
    public boolean canImport(TransferSupport info) {
        // check the target component
        if (!(info.getComponent() instanceof BAASBMDropTargetCriticalList)) {
            logger.error("This handler must be attached to a BAASBMDropTargetCriticalList");
            return false;
        }

        Mounted<?> mounted = getMountedFromTransferable(info.getTransferable());

        // not actually dragged a Mounted? not transferable
        if (mounted == null) {
            return false;
        }
        // stuff that has a fixed location is also not transferable
        if (UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
            return false;
        }
        // no transfer in the same location
        if (getUnit() instanceof BattleArmor) {
            // Infantry weapons cannot be mounted directly, but must instead be mounted in an AP Mount
            if (mounted.getType() instanceof InfantryWeapon) {
                return false;
            }
            String[] split = info.getComponent().getName().split(":");
            if (split.length != 2) {
                return false;
            }
            return (Integer.parseInt(split[0]) != mounted.getBaMountLoc())
                  || (Integer.parseInt(split[1]) != mounted.getLocation());
        }
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent component) {
        if (component instanceof JTable table) {
            Mounted<?> mount = (Mounted<?>) table.getModel().getValueAt(table.getSelectedRow(),
                  CriticalTableModel.EQUIPMENT);
            if (critView != null) {
                critView.markUnavailableLocations(mount);
            }
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else if (component instanceof BAASBMDropTargetCriticalList list) {
            if (list.isVirtualSlotSelected()) {
                return null;
            }
            Mounted<?> mount = list.getSelectedMounted();
            if (mount != null) {
                if (critView != null) {
                    critView.markUnavailableLocations(mount);
                }
                return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
            }
        }
        return null;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (critView != null) {
            critView.unMarkAllLocations();
        }
    }
}
