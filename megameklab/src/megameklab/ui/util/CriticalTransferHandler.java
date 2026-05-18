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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import megamek.common.CriticalSlot;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.Mounted;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.util.ProtoMekUtil;
import megameklab.util.UnitUtil;

public class CriticalTransferHandler extends AbstractCriticalTransferHandler {
    private static final MMLogger logger = MMLogger.create(CriticalTransferHandler.class);

    private final CriticalSlotsView critView;

    public CriticalTransferHandler(EntitySource eSource, RefreshListener refresh, CriticalSlotsView critView) {
        super(eSource, refresh);
        this.critView = critView;
    }

    public CriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        this(eSource, refresh, null);
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
                    changeMountStatus(mount, location);
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
                    changeMountStatus(mount, location);
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }

            return true;
        } else if ((info.getComponent() instanceof JTable)
              || (info.getComponent() instanceof JScrollPane)) {
            return doImport(getMountedFromTransferable(info.getTransferable()), info.getComponent());
        }
        return false;
    }

    @Override
    public boolean canImport(TransferSupport info) {
        Mounted<?> mounted = getMountedFromTransferable(info.getTransferable());
        return isDraggable(mounted) && info.isDrop();
    }

    @Override
    protected Transferable createTransferable(JComponent component) {
        Mounted<?> mount = null;
        if (component instanceof JTable table) {
            mount = (Mounted<?>) table.getModel().getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
        } else if (component instanceof ProtoMekMountList protoMekMountList) {
            mount = protoMekMountList.getMounted();
            if (mount == null
                  || UnitUtil.isFixedLocationSpreadEquipment(mount.getType())
                  || mount.getType() instanceof AmmoType) {
                return null;
            }
        }
        if (mount != null) {
            if (critView != null) {
                critView.markUnavailableLocations(mount);
            }
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else {
            return null;
        }
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (critView != null) {
            critView.unMarkAllLocations();
        }
    }
}
