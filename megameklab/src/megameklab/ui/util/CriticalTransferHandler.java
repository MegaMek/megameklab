/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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
package megameklab.ui.util;

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.mek.BMCriticalView;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CriticalTransferHandler extends TransferHandler {
    private EntitySource eSource;
    private int location;
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

        if (info.getComponent() instanceof DropTargetCriticalList) {
            DropTargetCriticalList<?> list = (DropTargetCriticalList<?>) info.getComponent();
            location = Integer.parseInt(list.getName());
            Transferable t = info.getTransferable();
            try {
                Mounted mount = getUnit().getEquipment(Integer.parseInt((String) t
                        .getTransferData(DataFlavor.stringFlavor)));
                
                if (!UnitUtil.isValidLocation(getUnit(), mount.getType(), location)) {
                    JOptionPane.showMessageDialog(null, mount.getName() +
                            " can't be placed in " +
                            getUnit().getLocationName(location) + "!",
                            "Invalid Location",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                
                if (!getUnit().addCritical(location, new CriticalSlot(mount))) {
                    JOptionPane.showMessageDialog(null, "Location Full",
                            "Location Full", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    changeMountStatus(mount, location, false);
                }
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }

            return true;
        } else if (info.getComponent() instanceof ProtomekMountList) {
            ProtomekMountList list = (ProtomekMountList) info.getComponent();
            location = list.getMountLocation();
            Transferable t = info.getTransferable();
            try {
                Mounted mount = getUnit().getEquipment(Integer.parseInt((String) t
                        .getTransferData(DataFlavor.stringFlavor)));

                if (!UnitUtil.isValidLocation(getUnit(), mount.getType(), location)) {
                    JOptionPane.showMessageDialog(null, mount.getName() +
                            " can't be placed in " +
                            getUnit().getLocationName(location) + "!",
                            "Invalid Location",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

                if (!UnitUtil.protomechHasRoom(list.getProtomech(), location, mount)) {
                    JOptionPane.showMessageDialog(null, "Location Full",
                            "Not enough room", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    changeMountStatus(mount, location, false);
                }
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
            }

            return true;
        } else if ((info.getComponent() instanceof JTable)
                || (info.getComponent() instanceof JScrollPane)) {
            try {
                Transferable t = info.getTransferable();
                Mounted mount = getUnit().getEquipment(Integer.parseInt((String) t
                        .getTransferData(DataFlavor.stringFlavor)));

                if (getUnit() instanceof BattleArmor) {
                    mount.setBaMountLoc(BattleArmor.MOUNT_LOC_NONE);
                } else {
                    UnitUtil.removeCriticals(getUnit(), mount);
                    if (getUnit().isFighter() && mount.getLocation() != Entity.LOC_NONE) {
                        UnitUtil.compactCriticals(getUnit(), mount.getLocation());
                    }
                    changeMountStatus(mount, Entity.LOC_NONE, false);
                }
            } catch (Exception ex) {
                LogManager.getLogger().error("", ex);
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
        // check if the dragged mounted should be transferrable
        Mounted mounted = null;
        try {
            mounted = getUnit().getEquipment(Integer
                    .parseInt((String) info.getTransferable().getTransferData(
                            DataFlavor.stringFlavor)));
        } catch (NumberFormatException | UnsupportedFlavorException | IOException e) {
            LogManager.getLogger().error("", e);
        }
        // not actually dragged a Mounted? not transferable
        if (mounted == null) {
            return false;
        }
        // stuff that has a fixed location is also not transferable
        if (UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
            return false;
        }
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JTable) {
            JTable table = (JTable) c;
            Mounted mount = (Mounted) table.getModel().getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
            if (critView != null) {
                critView.markUnavailableLocations(mount);
            }
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else if (c instanceof ProtomekMountList) {
            Mounted mount = ((ProtomekMountList) c).getMounted();
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
    
    private void changeMountStatus(Mounted eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation, boolean rear) {

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
            critView.unmarkAllLocations();
        }
    }
}