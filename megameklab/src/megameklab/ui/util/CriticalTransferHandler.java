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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import megamek.common.CriticalSlot;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.mek.BMCriticalView;
import megameklab.util.ProtoMekUtil;
import megameklab.util.UnitUtil;

public class CriticalTransferHandler extends TransferHandler {
    private static final MMLogger logger = MMLogger.create(CriticalTransferHandler.class);

    private final EntitySource eSource;
    private RefreshListener refresh;
    private final BMCriticalView critView;

    public CriticalTransferHandler(EntitySource eSource, RefreshListener refresh, BMCriticalView critView) {
        this.eSource = eSource;
        this.refresh = refresh;
        this.critView = critView;
    }

    public CriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        this.eSource = eSource;
        this.refresh = refresh;
        this.critView = null;
    }

    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

        int location;
        if (info.getComponent() instanceof DropTargetCriticalList<?> list) {
            location = Integer.parseInt(list.getName());
            Transferable t = info.getTransferable();
            try {
                Mounted<?> mount = getUnit().getEquipment(Integer.parseInt((String) t
                      .getTransferData(DataFlavor.stringFlavor)));

                if (!UnitUtil.isValidLocation(getUnit(), mount.getType(), location)) {
                    PopupMessages.showInvalidLocationInfo(null, mount.getName(), getUnit().getLocationName(location));
                    return false;
                }

                if (!getUnit().addCritical(location, new CriticalSlot(mount))) {
                    PopupMessages.showLocationFullError(null, mount.getName());
                } else {
                    changeMountStatus(mount, location, false);
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }

            return true;
        } else if (info.getComponent() instanceof ProtoMekMountList list) {
            location = list.getMountLocation();
            Transferable t = info.getTransferable();
            try {
                Mounted<?> mount = getUnit().getEquipment(Integer.parseInt((String) t
                      .getTransferData(DataFlavor.stringFlavor)));

                if (!UnitUtil.isValidLocation(getUnit(), mount.getType(), location)) {
                    PopupMessages.showInvalidLocationInfo(null, mount.getName(), getUnit().getLocationName(location));
                    return false;
                }

                if (!ProtoMekUtil.protoMekHasRoom(list.getProtoMek(), location, mount)) {
                    PopupMessages.showLocationFullError(null, mount.getName());
                } else {
                    changeMountStatus(mount, location, false);
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }

            return true;
        } else if ((info.getComponent() instanceof JTable)
              || (info.getComponent() instanceof JScrollPane)) {
            try {
                Transferable t = info.getTransferable();
                Mounted<?> mount = getUnit().getEquipment(Integer.parseInt((String) t
                      .getTransferData(DataFlavor.stringFlavor)));

                if (getUnit() instanceof BattleArmor) {
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                } else {
                    UnitUtil.removeCriticalSlots(getUnit(), mount);
                    if (getUnit().isFighter() && mount.getLocation() != Entity.LOC_NONE) {
                        UnitUtil.compactCriticalSlots(getUnit(), mount.getLocation());
                    }
                    changeMountStatus(mount, Entity.LOC_NONE, false);
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canImport(TransferSupport info) {
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        // check if the dragged mounted should be transferable
        Mounted<?> mounted = null;
        try {
            mounted = getUnit().getEquipment(Integer
                  .parseInt((String) info.getTransferable().getTransferData(
                        DataFlavor.stringFlavor)));
        } catch (NumberFormatException | UnsupportedFlavorException | IOException e) {
            logger.error("", e);
        }
        // not actually dragged a Mounted? not transferable
        if (mounted == null) {
            return false;
        }
        // stuff that has a fixed location is also not transferable
        return !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType());
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
        } else if (component instanceof ProtoMekMountList) {
            Mounted<?> mount = ((ProtoMekMountList) component).getMounted();
            if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())
                  && !(mount.getType() instanceof AmmoType)) {
                return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
            }
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.LINK;
    }

    private void changeMountStatus(Mounted<?> eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted<?> eq, int location, int secondaryLocation, boolean rear) {

        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, rear);

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public void addRefreshListener(RefreshListener r) {
        refresh = r;
    }

    public Entity getUnit() {
        return eSource.getEntity();
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (critView != null) {
            critView.unMarkAllLocations();
        }
    }
}
