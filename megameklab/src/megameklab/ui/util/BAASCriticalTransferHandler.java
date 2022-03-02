/*
 * Copyright (c) 2008-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.util;

import megamek.common.*;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Objects;

/**
 * The crit slot Transfer Handler for BA and AS.
 *
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BAASCriticalTransferHandler extends TransferHandler {
    private final EntitySource eSource;
    private int location = -1;
    private final RefreshListener refresh;

    public BAASCriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
        this.eSource = eSource;
        this.refresh = refresh;
    }

    @Override
    public void exportDone(JComponent source, Transferable data, int action) {
        if (data == null) {
            return;
        }
        Mounted mounted = null;
        try {
            mounted = getUnit().getEquipment(Integer.parseInt((String) data.getTransferData(DataFlavor.stringFlavor)));
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
            return;
        }

        if ((source instanceof BAASBMDropTargetCriticalList)
                && (mounted.getLocation() != Entity.LOC_NONE)) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>) source;
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
            Mounted linkedBy = mounted.getLinkedBy();
            if ((linkedBy != null) && !(getUnit() instanceof BattleArmor)) {
                UnitUtil.removeCriticals(getUnit(), linkedBy);
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

    private boolean addEquipmentBA(BattleArmor ba, Mounted newMount, int trooper) {
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

    private boolean addEquipmentAero(Aero aero, Mounted eq) throws LocationFullException {
        if (eq.getType() instanceof WeaponType) {
            int[] availSpace = Objects.requireNonNull(TestAero.availableSpace(aero));
            int[] weapCount = new int[aero.locations() - 1];
            for (Mounted m : aero.getWeaponList()) {
                if (m.getLocation() != Entity.LOC_NONE) {
                    weapCount[m.getLocation()]++;
                }
            }

            if ((weapCount[location] + 1) > availSpace[location]) {
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
        if (!info.isDrop() || !((getUnit() instanceof Mech) || (getUnit() instanceof Aero) ||
                (getUnit() instanceof BattleArmor))) {
            return false;
        }

        int trooper = 0;
        if (info.getComponent() instanceof BAASBMDropTargetCriticalList) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>) info.getComponent();
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
            int slotNumber = list.getDropLocation().getIndex();
            
            try {
                Mounted eq = getUnit().getEquipment(Integer.parseInt(
                        (String) t.getTransferData(DataFlavor.stringFlavor)));
                if (getUnit() instanceof BattleArmor) {
                    if ((location == eq.getBaMountLoc())
                            && (trooper == eq.getLocation())) {
                        return false;
                    }
                } else {
                    // If this equipment is already mounted, clear the criticals it's mounted in
                    if ((eq.getLocation() != Entity.LOC_NONE)
                            || (eq.getSecondLocation() != Entity.LOC_NONE)) {
                        UnitUtil.removeCriticals(getUnit(), eq);
                        changeMountStatus(eq, Entity.LOC_NONE);
                    } else {
                        eq.setOmniPodMounted(UnitUtil.canPodMount(getUnit(), eq));
                    }
                }

                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), location)) {
                    JOptionPane.showMessageDialog(null, eq.getName() +
                            " can't be placed in " +
                            getUnit().getLocationName(location) + "!",
                            "Invalid Location",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                
                if (getUnit() instanceof Aero) {
                    return addEquipmentAero((Aero) getUnit(), eq);
                } else if (getUnit() instanceof BattleArmor) {
                    return addEquipmentBA((BattleArmor) getUnit(), eq, trooper);
                }
            } catch (LocationFullException lfe) {
                JOptionPane.showMessageDialog(null, lfe.getMessage(), 
                        "Location Full", JOptionPane.INFORMATION_MESSAGE);
                return false;
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
        // check the target component
        if (!(info.getComponent() instanceof BAASBMDropTargetCriticalList)) {
            return false;
        }
        // check if the dragged mounted should be transferrable
        Mounted mounted = null;
        try {
            int index = Integer.parseInt((String) info.getTransferable().getTransferData(DataFlavor.stringFlavor));
            mounted = getUnit().getEquipment(index);
        } catch (Exception e) {
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
            if ((Integer.parseInt(split[0]) == mounted.getBaMountLoc())
                    && (Integer.parseInt(split[1]) == mounted.getLocation())) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JTable) {
            JTable table = (JTable) c;
            Mounted mount = (Mounted) table.getModel().getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else if (c instanceof BAASBMDropTargetCriticalList) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>) c;
            Mounted mount = list.getMounted();
            if (mount != null) {
                return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
            }
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    private void changeMountStatus(Mounted eq, int location) {
        changeMountStatus(eq, location, -1);
    }

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, false);
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public Entity getUnit() {
        return eSource.getEntity();
    }
}
