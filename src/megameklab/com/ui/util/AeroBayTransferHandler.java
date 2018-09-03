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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.LocationFullException;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.MegaMekLab;
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
    
    /* Aliases for local usage.
     * When moving ammo, the default is to move a single ton (or whatever the atomic value is) at a time.
     * Holding the ctrl key will move all ammo of that type in that location.
     */
    public static final int AMMO_SINGLE = MOVE;
    public static final int AMMO_ALL    = COPY;
    
    public AeroBayTransferHandler(EntitySource eSource) {
        this.eSource = eSource;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        
        // Fields are equipmentNum, node child index, bay child index
        String[] source = null;
        List<Mounted> eqList = new ArrayList<>();
        
        try {
            source = ((String) support.getTransferable().getTransferData(DataFlavor.stringFlavor)).split(":");
            for (String field : source[0].split(",")) {
                int eqNum = Integer.parseInt(field);
                Mounted m = eSource.getEntity().getEquipment(eqNum);
                if (null != m) {
                    eqList.add(m);
                }
            }
        } catch (Exception ex) {
            MegaMekLab.getLogger().error(AeroBayTransferHandler.class,
                    "importData(TransferSupport)", //$NON-NLS-1$
                    ex);
            return false;
        }
        if (eqList.isEmpty()) {
            return false;
        }

        if ((support.getComponent() instanceof BayWeaponCriticalTree)) {
            final BayWeaponCriticalTree tree = (BayWeaponCriticalTree) support.getComponent();
            if (eSource.getEntity().usesWeaponBays() && (eqList.size() == 1)) {
                // If it's a bay we move it and its entire contents. Otherwise we find the bay that was
                // dropped on and add it there. A weapon dropped on an illegal bay will create a new one
                // and non-bay equipment will be added at the top level regardless of the drop location.
                // Non-weapon bay equipment cannot be dropped on an illegal bay.
                final Mounted mount = eqList.get(0);
                if (mount.getType() instanceof BayWeapon) {
                    tree.addBay(mount);
                } else if ((mount.getType() instanceof AmmoType) && (support.getUserDropAction() == AMMO_SINGLE)) {
                    // Default action for ammo is to move a single slot. Holding the ctrl key when dropping
                    // will create a AMMO_ALL command, which adds all the ammo of the type.
                    tree.addAmmo(mount, ((AmmoType)mount.getType()).getShots(),
                            ((JTree.DropLocation) support.getDropLocation()).getPath());
                } else {
                    tree.addToArc(mount, ((JTree.DropLocation) support.getDropLocation()).getPath());
                }
            } else {
                // Small craft don't use bays.
                tree.addToLocation(eqList);
            }
        } else {
            // Target is unallocated bay table.
            for (Mounted mount : eqList) {
                if (mount.getType() instanceof AmmoType) {
                    AmmoType at = (AmmoType)mount.getType();
                    // Check whether we are moving one of multiple slots. 
                    if ((support.getUserDropAction() == AMMO_SINGLE) && (mount.getUsableShotsLeft() > at.getShots())) {
                        mount.setShotsLeft(mount.getUsableShotsLeft() - at.getShots());
                    }
                    Mounted addMount = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), at);
                    if (null != addMount) {
                        if (support.getUserDropAction() == AMMO_SINGLE) {
                            addMount.setShotsLeft(addMount.getUsableShotsLeft() + at.getShots());
                        } else {
                            addMount.setShotsLeft(addMount.getUsableShotsLeft() + mount.getUsableShotsLeft());
                        }
                    } else {
                        try {
                            Mounted m = eSource.getEntity().addEquipment(at, Entity.LOC_NONE);
                            if (support.getUserDropAction() == AMMO_ALL) {
                                m.setShotsLeft(mount.getUsableShotsLeft());
                            }
                        } catch (LocationFullException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    List<Mounted> toRemove;
                    if (mount.getType() instanceof BayWeapon) {
                        toRemove = new ArrayList<>();
                        for (Integer num : mount.getBayWeapons()) {
                            toRemove.add(eSource.getEntity().getEquipment(num));
                        }
                        for (Integer num : mount.getBayAmmo()) {
                            toRemove.add(eSource.getEntity().getEquipment(num));
                        }
                    } else {
                        toRemove = Collections.singletonList(mount);
                    }
                    for (Mounted m : toRemove) {
                        if (m.getType() instanceof AmmoType) {
                            Mounted aMount = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), m.getType());
                            if (null != aMount) {
                                aMount.setShotsLeft(aMount.getUsableShotsLeft() + m.getUsableShotsLeft());
                                m.setShotsLeft(0);
                                continue;
                            }
                        }
                        UnitUtil.removeCriticals(eSource.getEntity(), m);
                        UnitUtil.changeMountStatus(eSource.getEntity(), m, Entity.LOC_NONE, Entity.LOC_NONE, false);
                        if ((mount.getType() instanceof WeaponType) && (m.getLinkedBy() != null)) {
                            UnitUtil.removeCriticals(eSource.getEntity(), m.getLinkedBy());
                            UnitUtil.changeMountStatus(eSource.getEntity(), m.getLinkedBy(),
                                    Entity.LOC_NONE, Entity.LOC_NONE, false);
                            m.getLinkedBy().setLinked(null);
                            m.setLinkedBy(null);
                        }
                    }
                    UnitUtil.compactCriticals(eSource.getEntity());
                }
                if (mount.getType() instanceof BayWeapon) {
                    mount.getBayWeapons().clear();
                    mount.getBayAmmo().clear();
                    UnitUtil.removeMounted(eSource.getEntity(), mount);
                }
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
        List<Mounted> mounted = new ArrayList<>();
        try {
            String str = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            if (str.contains(":")) {
                str = str.substring(0, str.indexOf(":"));
            }
            for (String field : str.split(",")) {
                mounted.add(eSource.getEntity().getEquipment(Integer.parseInt(field)));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // not actually dragged a Mounted? not transferable
        if (mounted.isEmpty()) {
            return false;
        }
        
        // If allocating to an arc, make sure the bay can receive it
        if (support.getComponent() instanceof BayWeaponCriticalTree) {
            for (Mounted m : mounted) {
                if (((BayWeaponCriticalTree)support.getComponent())
                        .isValidDropLocation((JTree.DropLocation)support.getDropLocation(), m)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof BayWeaponCriticalTree) {
            return new StringSelection(((BayWeaponCriticalTree)c).encodeSelection());
        } else {
            JTable table = (JTable) c;
            StringJoiner sj = new StringJoiner(",");
            for (int row : table.getSelectedRows()) {
                Mounted mount = (Mounted) ((CriticalTableModel) table.getModel()).getValueAt(row, CriticalTableModel.EQUIPMENT);
                sj.add(Integer.toString(eSource.getEntity().getEquipmentNum(mount)));
            }
            return new StringSelection(sj.toString());
        }
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (((action == MOVE) || (action == COPY)) && (source instanceof BayWeaponCriticalTree)) {
            try {
                ((BayWeaponCriticalTree)source).removeExported((String)data.getTransferData(DataFlavor.stringFlavor),
                        action);
            } catch (Exception ex) {
                MegaMekLab.getLogger().error(AeroBayTransferHandler.class,
                        "exportDone(JComponent,Transferable,action", //$NON-NLS-1$
                        ex);
            }
        }
    }
    
    

}
