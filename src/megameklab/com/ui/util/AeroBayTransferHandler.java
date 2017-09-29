/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;

import megamek.common.Entity;
import megamek.common.MechFileParser;
import megamek.common.Mounted;
import megamek.common.loaders.EntityLoadingException;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.UnitUtil;

/**
 * Handles drag-and-drop for aerospace units that use weapon bays. Most of the work of adding, removing,
 * and changing equipment locations is done by the JTree for the weapon arc.
 * 
 * @author Neoancient
 *
 */
public class AeroBayTransferHandler extends TransferHandler {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2534394664060762469L;
    
    private EntitySource eSource;
    
    public AeroBayTransferHandler(EntitySource eSource) {
        this.eSource = eSource;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        
        Mounted mount = null;
        try {
            mount = eSource.getEntity().getEquipment(Integer.parseInt((String) support.getTransferable()
                    .getTransferData(DataFlavor.stringFlavor)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (null == mount) {
            return false;
        }

        if ((support.getComponent() instanceof BayWeaponCriticalTree)) {
            // If we're dragging to a different location, clear the critical slots from the previous location
            if (mount.getLocation() != Entity.LOC_NONE) {
                UnitUtil.removeCriticals(eSource.getEntity(), mount);
            }
            final BayWeaponCriticalTree tree = (BayWeaponCriticalTree) support.getComponent();
            Mounted bay = tree.getBayFromPath(((JTree.DropLocation) support.getDropLocation()).getPath());

            // We've already tested the bay in canImport
            tree.addToBay(bay, mount);
        } else {
            // Target is unallocated bay table.
            UnitUtil.removeCriticals(eSource.getEntity(), mount);
            UnitUtil.changeMountStatus(eSource.getEntity(), mount, Entity.LOC_NONE, Entity.LOC_NONE, false);
            UnitUtil.compactCriticals(eSource.getEntity());
            try {
                MechFileParser.postLoadInit(eSource.getEntity());
            } catch (EntityLoadingException ex)  {
                // do nothing
            }
        }
        return true;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        // Check for String flavor
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        // check if the dragged mounted should be transferrable
        Mounted mounted = null;
        try {
            mounted = eSource.getEntity().getEquipment(Integer
                    .parseInt((String) support.getTransferable().getTransferData(
                            DataFlavor.stringFlavor)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // not actually dragged a Mounted? not transferable
        if (mounted == null) {
            return false;
        }
        
        // If allocating to an arc, make sure the bay can receive it
        if (support.getComponent() instanceof BayWeaponCriticalTree) {
            return ((BayWeaponCriticalTree)support.getComponent())
                    .isValidDropLocation((JTree.DropLocation)support.getDropLocation(), mounted);
        }
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof BayWeaponCriticalTree) {
            Mounted mount = ((BayWeaponCriticalTree)c).getSelectedMount();
            return new StringSelection(Integer.toString(eSource.getEntity().getEquipmentNum(mount)));
        } else {
            JTable table = (JTable) c;
            Mounted mount = (Mounted) ((CriticalTableModel) table.getModel()).getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
            return new StringSelection(Integer.toString(eSource.getEntity().getEquipmentNum(mount)));
        }
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (source instanceof BayWeaponCriticalTree) {
            ((BayWeaponCriticalTree)source).removeSelected();
        }
    }
    
    

}
