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
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JTable;

import megamek.common.CriticalSlot;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Aero;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.infantry.InfantryWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.util.UnitUtil;

/**
 * The crit slot Transfer Handler for BA and AS.
 *
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAASCriticalTransferHandler extends AbstractCriticalTransferHandler {
    private static final MMLogger logger = MMLogger.create(BAASCriticalTransferHandler.class);

    private int location = -1;

    public BAASCriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        super(eSource, refresh);
    }

    @Override
    public void exportDone(JComponent source, Transferable data, int action) {
        if (data == null) {
            return;
        }
        Mounted<?> mounted;
        try {
            mounted = getUnit().getEquipment(Integer.parseInt((String) data.getTransferData(DataFlavor.stringFlavor)));
        } catch (Exception ex) {
            logger.error("", ex);
            return;
        }

        if ((source instanceof BAASBMDropTargetCriticalList<?> list) && (mounted.getLocation() != Entity.LOC_NONE)) {
            int loc;
            if (getUnit() instanceof BattleArmor) {
                String[] split = list.getName().split(":");
                loc = Integer.parseInt(split[0]);
            } else {
                loc = Integer.parseInt(list.getName());
                if (loc == mounted.getLocation()) {
                    return;
                }
            }
            int slot = list.getSelectedIndex();
            int startSlot = slot;
            mounted = list.getMounted();
            if (mounted == null) {
                return;
            }
            if (UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
                return;
            }
            while (slot > 0) {
                slot--;
                CriticalSlot cs = getUnit().getCritical(loc, slot);
                if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && cs.getMount().equals(mounted)) {
                    startSlot = slot;
                }
            }
            if (!(getUnit() instanceof BattleArmor)) {
                for (int i = startSlot; i < (startSlot + UnitUtil.getCritsUsed(mounted)); i++) {
                    getUnit().setCritical(loc, i, null);
                }
            }
            Mounted<?> linkedBy = mounted.getLinkedBy();
            if ((linkedBy != null) && !(getUnit() instanceof BattleArmor)) {
                UnitUtil.removeCriticalSlots(getUnit(), linkedBy);
                try {
                    UnitUtil.addMounted(getUnit(), linkedBy, mounted.getLocation(), linkedBy.isRearMounted());
                } catch (LocationFullException e) {
                    UnitUtil.changeMountStatus(getUnit(), linkedBy, Entity.LOC_NONE, Entity.LOC_NONE, false);
                    linkedBy.setLinked(null);
                    mounted.setLinkedBy(null);
                }
            }
            refresh.refreshBuild();
        }
    }

    private boolean addEquipmentBA(BattleArmor ba, Mounted<?> newMount, int trooper) {
        if (TestBattleArmor.isMountLegal(ba, newMount, location, trooper)) {
            newMount.setBaMountLoc(location);
            if (newMount.getLocation() == BattleArmor.LOC_SQUAD) {
                changeMountStatus(newMount, newMount.getLocation());
            } else {
                changeMountStatus(newMount, trooper);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean addEquipmentAero(Aero aero, Mounted<?> eq) throws LocationFullException {
        if (eq.getType() instanceof WeaponType) {
            int[] availSpace = Objects.requireNonNull(TestAero.availableSpace(aero));
            int[] weaponCount = new int[aero.locations() - 1];
            for (Mounted<?> m : aero.getWeaponList()) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    weaponCount[m.getLocation()]++;
                }
            }

            if ((weaponCount[location] + 1) > availSpace[location]) {
                throw new LocationFullException(eq.getName() +
                      " does not fit in " + getUnit().getLocationAbbr(location) +
                      " on " + getUnit().getDisplayName());
            } else {
                UnitUtil.addMounted(getUnit(), eq, location, false);
            }
        } else {
            UnitUtil.addMounted(getUnit(), eq, location, false);
        }
        changeMountStatus(eq, location);
        return true;
    }

    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop() || !((getUnit() instanceof Mek) || (getUnit() instanceof Aero) ||
              (getUnit() instanceof BattleArmor))) {
            return false;
        }

        int trooper = 0;
        if (info.getComponent() instanceof BAASBMDropTargetCriticalList<?> list) {
            if (getUnit() instanceof BattleArmor) {
                String[] split = list.getName().split(":");
                if (split.length != 2) {
                    return false;
                }
                location = Integer.parseInt(split[0]);
                trooper = Integer.parseInt(split[1]);
            } else {
                location = Integer.parseInt(list.getName());
            }
            Transferable t = info.getTransferable();

            try {
                Mounted<?> eq = getUnit().getEquipment(Integer.parseInt(
                      (String) t.getTransferData(DataFlavor.stringFlavor)));
                if (getUnit() instanceof BattleArmor) {
                    if ((location == eq.getBaMountLoc())
                          && (trooper == eq.getLocation())) {
                        return false;
                    }
                } else {
                    // If this equipment is already mounted, clear the criticalSlots it's mounted in
                    if ((eq.getLocation() != Entity.LOC_NONE)
                          || (eq.getSecondLocation() != Entity.LOC_NONE)) {
                        UnitUtil.removeCriticalSlots(getUnit(), eq);
                        if (getUnit().isFighter() && eq.getLocation() != Entity.LOC_NONE) {
                            UnitUtil.compactCriticalSlots(getUnit(), eq.getLocation());
                        }
                        changeMountStatus(eq, Entity.LOC_NONE);
                    } else {
                        eq.setOmniPodMounted(UnitUtil.canPodMount(getUnit(), eq));
                    }
                }

                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), location)) {
                    PopupMessages.showInvalidLocationInfo(null, eq.getName(), getUnit().getLocationName(location));
                    return false;
                }

                if (getUnit() instanceof Aero) {
                    return addEquipmentAero((Aero) getUnit(), eq);
                } else if (getUnit() instanceof BattleArmor) {
                    return addEquipmentBA((BattleArmor) getUnit(), eq, trooper);
                }
            } catch (LocationFullException lfe) {
                PopupMessages.showLocationFullError(null);
                return false;
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
        // check the target component
        if (!(info.getComponent() instanceof BAASBMDropTargetCriticalList)) {
            return false;
        }
        // check if the dragged mounted should be transferable
        Mounted<?> mounted = null;
        try {
            int index = Integer.parseInt((String) info.getTransferable().getTransferData(DataFlavor.stringFlavor));
            mounted = getUnit().getEquipment(index);
        } catch (Exception e) {
            logger.error("", e);
        }
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
            // Infantry weapons cannot be mounted directly, but must instead be mounted in
            // an AP Mount
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
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else if (component instanceof BAASBMDropTargetCriticalList<?> list) {
            Mounted<?> mount = list.getMounted();
            if (mount != null) {
                return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
            }
        }
        return null;
    }
}
