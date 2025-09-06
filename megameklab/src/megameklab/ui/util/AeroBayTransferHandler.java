/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;

import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Entity;
import megamek.common.weapons.bayWeapons.BayWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

/**
 * Handles drag-and-drop for aerospace units that use weapon bays. Most of the work of adding, removing, and changing
 * equipment locations is done by the JTree for the weapon arc.
 *
 * @author Neoancient
 */
public class AeroBayTransferHandler extends TransferHandler {
    private static final MMLogger LOGGER = MMLogger.create(AeroBayTransferHandler.class);

    private final EntitySource eSource;

    public static final String EMPTY_SLOT = "EmptySlot";

    /*
     * Aliases for local usage.
     * When moving ammo, the default is to move a single ton (or whatever the atomic
     * value is) at a time.
     * Holding the ctrl key will move all ammo of that type in that location.
     */
    public static final int AMMO_SINGLE = MOVE;
    public static final int AMMO_ALL = COPY;

    public AeroBayTransferHandler(EntitySource eSource) {
        this.eSource = eSource;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }

        // Fields are equipmentNum, node child index, bay child index
        String[] source;
        List<Mounted<?>> eqList = new ArrayList<>();

        try {
            source = ((String) support.getTransferable().getTransferData(DataFlavor.stringFlavor)).split(":");
            for (String field : source[0].split(",")) {
                int eqNum = Integer.parseInt(field);
                Mounted<?> m = eSource.getEntity().getEquipment(eqNum);
                if (null != m) {
                    eqList.add(m);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("", ex);
            return false;
        }
        if (eqList.isEmpty()) {
            return false;
        }

        if ((support.getComponent() instanceof BayWeaponCriticalTree tree)) {
            if (eSource.getEntity().usesWeaponBays() && (eqList.size() == 1)) {
                // If it's a bay we move it and its entire contents. Otherwise, we find the bay that was dropped on
                // and add it there. A weapon dropped on an illegal bay will create a new one and non-bay equipment
                // will be added at the top level regardless of the drop location.
                // Non-weapon bay equipment cannot be dropped on an illegal bay.
                final Mounted<?> mount = eqList.get(0);
                if (mount.getType() instanceof BayWeapon) {
                    tree.addBay((WeaponMounted) mount);
                } else if ((mount instanceof AmmoMounted ammo)
                      && (support.getUserDropAction() == AMMO_SINGLE)
                      && (ammo.getUsableShotsLeft() > ammo.getType().getShots())) {
                    // Default action for ammo is to prompt for the number of shots to move. Holding the ctrl key when
                    // dropping will create a AMMO_ALL command, which adds all the ammo of the type.
                    final int transferAmount = ammoTransferAmount(ammo);
                    if (transferAmount <= 0) {
                        return false;
                    }
                    tree.addAmmo(ammo,
                          transferAmount,
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
            for (Mounted<?> mount : eqList) {
                if (mount.getType() instanceof AmmoType at) {
                    final AmmoMounted ammoMounted = (AmmoMounted) mount;
                    int ammoAmount;
                    // Check whether we are moving one of multiple shots.
                    if (support.getUserDropAction() == AMMO_SINGLE) {
                        ammoAmount = at.getShots();
                    } else {
                        ammoAmount = ammoMounted.getUsableShotsLeft();
                    }
                    if (ammoAmount >= ammoMounted.getUsableShotsLeft()) {
                        ammoAmount = ammoMounted.getUsableShotsLeft();
                        UnitUtil.removeCriticalSlots(eSource.getEntity(), ammoMounted);
                        UnitUtil.removeMounted(eSource.getEntity(), ammoMounted);
                    } else {
                        ammoAmount = ammoTransferAmount(ammoMounted);
                        if (ammoAmount <= 0) {
                            return false;
                        }
                        if (ammoAmount == ammoMounted.getUsableShotsLeft()) {
                            UnitUtil.removeCriticalSlots(eSource.getEntity(), ammoMounted);
                            UnitUtil.removeMounted(eSource.getEntity(), ammoMounted);
                        } else {
                            ammoMounted.setShotsLeft(ammoMounted.getUsableShotsLeft() - ammoAmount);
                        }
                    }
                    Mounted<?> addMount = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), at);
                    if (null != addMount) {
                        addMount.setShotsLeft(addMount.getUsableShotsLeft() + ammoAmount);
                    } else {
                        try {
                            Mounted<?> m = eSource.getEntity().addEquipment(at, Entity.LOC_NONE);
                            m.setShotsLeft(ammoAmount);
                        } catch (LocationFullException e) {
                            LOGGER.error("Error creating target ammo", e);
                        }
                    }
                } else {
                    List<Mounted<?>> toRemove;
                    if (mount.getType() instanceof BayWeapon) {
                        toRemove = new ArrayList<>();
                        toRemove.addAll(((WeaponMounted) mount).getBayWeapons());
                        toRemove.addAll(((WeaponMounted) mount).getBayAmmo());
                    } else {
                        toRemove = Collections.singletonList(mount);
                    }
                    for (Mounted<?> m : toRemove) {
                        if (m.getType() instanceof AmmoType) {
                            Mounted<?> aMount = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), m.getType());
                            if (null != aMount) {
                                aMount.setShotsLeft(aMount.getUsableShotsLeft() + m.getUsableShotsLeft());
                                m.setShotsLeft(0);
                                continue;
                            }
                        }
                        UnitUtil.removeCriticalSlots(eSource.getEntity(), m);
                        UnitUtil.changeMountStatus(eSource.getEntity(), m, Entity.LOC_NONE, Entity.LOC_NONE, false);
                        if ((mount.getType() instanceof WeaponType) && (m.getLinkedBy() != null)) {
                            UnitUtil.removeCriticalSlots(eSource.getEntity(), m.getLinkedBy());
                            UnitUtil.changeMountStatus(eSource.getEntity(), m.getLinkedBy(),
                                  Entity.LOC_NONE, Entity.LOC_NONE, false);
                            m.getLinkedBy().setLinked(null);
                            m.setLinkedBy(null);
                        }
                    }
                    UnitUtil.compactCriticalSlots(eSource.getEntity());
                }
                if ((mount.getType() instanceof BayWeapon) && (mount instanceof WeaponMounted mountedWeapon)) {
                    mountedWeapon.getBayWeapons().clear();
                    mountedWeapon.getBayAmmo().clear();
                    UnitUtil.removeMounted(eSource.getEntity(), mount);
                }
            }
        }
        return true;
    }

    protected int ammoTransferAmount(AmmoMounted ammo) {
        return ammo.getType().getShots();
    }

    @Override
    public boolean canImport(TransferSupport support) {
        // Check for String flavor
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        // check if the dragged mounted should be transferable
        List<Mounted<?>> mounted = new ArrayList<>();
        try {
            String str = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            if (str.equals(EMPTY_SLOT)) {
                return false;
            }
            if (str.contains(":")) {
                str = str.substring(0, str.indexOf(":"));
            }
            for (String field : str.split(",")) {
                mounted.add(eSource.getEntity().getEquipment(Integer.parseInt(field)));
            }
        } catch (NumberFormatException | UnsupportedFlavorException | IOException e) {
            LOGGER.error("", e);
        }

        // not actually dragged a Mounted? not transferable
        if (mounted.isEmpty()) {
            return false;
        }

        // If allocating to an arc, make sure the bay can receive it
        if (support.getComponent() instanceof BayWeaponCriticalTree) {
            for (Mounted<?> m : mounted) {
                if (((BayWeaponCriticalTree) support.getComponent())
                      .isValidDropLocation((JTree.DropLocation) support.getDropLocation(), m)) {
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
            return new StringSelection(((BayWeaponCriticalTree) c).encodeSelection());
        } else {
            JTable table = (JTable) c;
            StringJoiner sj = new StringJoiner(",");
            for (int row : table.getSelectedRows()) {
                Mounted<?> mount = (Mounted<?>) table.getModel().getValueAt(row, CriticalTableModel.EQUIPMENT);
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
                ((BayWeaponCriticalTree) source).removeExported((String) data.getTransferData(DataFlavor.stringFlavor),
                      action);
            } catch (Exception ex) {
                LOGGER.error("", ex);
            }
        }
    }
}
