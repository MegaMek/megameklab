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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.Mounted;

public class CriticalTransferHandler extends TransferHandler {

    /**
     *
     */
    private static final long serialVersionUID = -5215375829853683877L;
    private Entity unit;
    private int location;
    private RefreshListener refresh;

    public CriticalTransferHandler(Entity unit, RefreshListener refresh) {
        this.unit = unit;
        this.refresh = refresh;
    }

    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

        if (info.getComponent() instanceof DropTargetCriticalList) {
            DropTargetCriticalList list = (DropTargetCriticalList) info.getComponent();
            location = Integer.parseInt(list.getName());
            Transferable t = info.getTransferable();
            try {
                String mountName = (String) t.getTransferData(DataFlavor.stringFlavor);

                Mounted eq = UnitUtil.getMounted(unit, mountName);

                int totalCrits = UnitUtil.getCritsUsed(unit, eq.getType());
                if (UnitUtil.getHighestContinuousNumberOfCrits(unit, location) >= totalCrits) {
                    // unit.addEquipment(eq.getType(), location, false);
                    changeMountStatus(eq, location, false);
                } else {
                    throw new LocationFullException(eq.getName() + " does not fit in " + unit.getLocationAbbr(location) + " on " + unit.getDisplayName());
                }
            } catch (LocationFullException lfe) {
                JOptionPane.showMessageDialog(null, lfe.getMessage(), "Location Full", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
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
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTable table = (JTable) c;
        EquipmentType eq = (EquipmentType) ((CriticalTableModel) table.getModel()).getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
        return new StringSelection(eq.getInternalName());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.LINK;
    }

    private void changeMountStatus(Mounted eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation, boolean rear) {

        UnitUtil.changeMountStatus(unit, eq, location, secondaryLocation, rear);

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

}