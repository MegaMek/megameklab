/*
 * Copyright (C) 2008
 * Copyright (c) 2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.mek;

import megamek.common.*;
import megamek.common.verifier.TestEntity;
import megameklab.ui.EntitySource;
import megameklab.ui.util.BAASBMDropTargetCriticalList;
import megameklab.ui.util.CriticalTableModel;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.*;

/**
 * The crit slot Transfer Handler for BM.
 *
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BMCriticalTransferHandler extends TransferHandler {
    private final EntitySource eSource;
    private int location = -1;
    private final RefreshListener refresh;
    private final BMCriticalView parentView;

    public BMCriticalTransferHandler(EntitySource eSource, RefreshListener refresh, BMCriticalView parentView) {
        this.eSource = eSource;
        this.refresh = refresh;
        this.parentView = parentView;
    }

    private boolean addEquipmentMech(Mech mek, Mounted eq, int slotNumber) throws LocationFullException {
        int neededCrits = UnitUtil.getCritsUsed(eq);

        if ((eq.getType().isSpreadable() || eq.isSplitable()) && (neededCrits > 1)) {
            if (addSplitLocationEquipment(mek, eq, slotNumber)) {
                return true;

            } else {
                throw new LocationFullException("There is no room for this " + eq.getName() + " in the "
                        + getUnit().getLocationAbbr(location) + " and the adjacent locations.");
            }

        } else {
            // Move the slotnumber upwards if the drop location is too far down for the equipment (PPC in the last slot)
            int locationSize = mek.getNumberOfCriticals(location);
            if ((locationSize >= neededCrits) && (slotNumber + neededCrits > locationSize)) {
                slotNumber = locationSize - neededCrits;
            }

            if (BMUtils.isFMU(eq)) {
                // Endo Steel and the like don't move aside other Endo Steels
                mek.addEquipment(eq, location, false, slotNumber);
                changeMountStatus(eq, location);
                return true;

            } else if (BMUtils.canFreeContiguousCrits(mek, location, slotNumber, neededCrits)) {
                // The equipment can be placed at the drop slot, possibly by removing Endo Steel and the like
                return addSingleLocationEquipment(mek, eq, slotNumber);

            } else if (BMUtils.findSlotWithContiguousNumOfCrits(mek, location, neededCrits) > -1) {
                // The equipment can be placed elsewhere in the location by removing Endo Steel and the like
                slotNumber = BMUtils.findSlotWithContiguousNumOfCrits(mek, location, neededCrits);
                return addSingleLocationEquipment(mek, eq, slotNumber);

            } else {
                throw new LocationFullException("There is no room for this " + eq.getName() + " in the "
                        + getUnit().getLocationAbbr(location) + ".");
            }
        }
    }

    /** Adds standard (non-splittable/spreadable) equipment to the mek. */
    private boolean addSingleLocationEquipment(Mech mek, Mounted eq, int slotNumber)
            throws LocationFullException {
        int neededCrits = UnitUtil.getCritsUsed(eq);
        BMUtils.removeFMU(mek, location, slotNumber, neededCrits);
        if ((eq.getType() instanceof WeaponType) && eq.getType().hasFlag(WeaponType.F_VGL)) {
            boolean success = BMUtils.addVGL(mek, eq, location, slotNumber);
            doRefresh();
            return success;
        } else {
            mek.addEquipment(eq, location, false, slotNumber);
        }
        changeMountStatus(eq, location);
        return true;
    }

    private boolean addSplitLocationEquipment(Mech mek, Mounted eq, int slotNumber) throws LocationFullException {
        int neededTotalSlots = UnitUtil.getCritsUsed(eq);
        int freePrimarySlots = BMUtils.availableContiguousCrits(mek, location, slotNumber, true);
        // It's obvious that the equipment can't be placed on an occupied slot, so in that case
        // a good free slot can be chosen in the location
        if (freePrimarySlots == 0) {
            int maxSpace = BMUtils.getMaxContiguousNumOfCrits(mek, location, true);
            slotNumber = BMUtils.findSlotWithContiguousNumOfCrits(mek, location, maxSpace);
            freePrimarySlots = BMUtils.availableContiguousCrits(mek, location, slotNumber, true);
            if (freePrimarySlots == 0) {
                // This location is full
                return false;
            }
        }
        int neededPrimarySlots = Math.min(neededTotalSlots, freePrimarySlots);
        int neededSecondarySlots = Math.max(0, neededTotalSlots - freePrimarySlots);
        int secondLocation = Entity.LOC_NONE;

        // Determine if we should spread equipment over multiple locations
        if ((neededTotalSlots > freePrimarySlots)
                // TargComps are marked as spreadable as a workaround, see the MiscType comment
                && !((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP))
                && !(getUnit() instanceof LandAirMech)) {

            Set<Integer> secondLocationSet = new HashSet<>();
            secondLocationSet.add(mek.getTransferLocation(location));
            if (location == Mech.LOC_RT) {
                secondLocationSet.add(Mech.LOC_CT);
                secondLocationSet.add(Mech.LOC_RARM);
                secondLocationSet.add(Mech.LOC_RLEG);
            } else if (location == Mech.LOC_LT) {
                secondLocationSet.add(Mech.LOC_CT);
                secondLocationSet.add(Mech.LOC_LARM);
                secondLocationSet.add(Mech.LOC_LLEG);
            } else if (location == Mech.LOC_CT) {
                secondLocationSet.add(Mech.LOC_LT);
                secondLocationSet.add(Mech.LOC_RT);
                secondLocationSet.add(Mech.LOC_HEAD);
            } else if (location == Mech.LOC_HEAD) {
                secondLocationSet.add(Mech.LOC_CT);
            }
            secondLocationSet.removeIf(loc -> loc == Entity.LOC_DESTROYED);
            secondLocationSet.removeIf(loc -> !UnitUtil.isValidLocation(mek, eq.getType(), loc));
            secondLocationSet.removeIf(loc ->
                    BMUtils.getMaxContiguousNumOfCrits(mek, loc, true) < neededSecondarySlots);

            List<Integer> secondLocationsList = new ArrayList<>(secondLocationSet);
            if (secondLocationsList.isEmpty()) {
                return false;

            } else if (secondLocationsList.size() == 1) {
                secondLocation = secondLocationsList.get(0);

            } else {
                Vector<String> locations = new Vector<>();
                secondLocationSet.forEach(loc -> locations.add(mek.getLocationName(loc)));
                JComboBox<String> combo = new JComboBox<>(locations);
                JOptionPane jop = new JOptionPane(combo, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                JDialog dlg = jop.createDialog("Select secondary location.");
                dlg.setVisible(true);
                int value = (Integer) jop.getValue();
                if (value == JOptionPane.CANCEL_OPTION) {
                    return false;
                }
                secondLocation = secondLocationsList.get(combo.getSelectedIndex());
            }
        }

        BMUtils.removeFMU(mek, location, slotNumber, freePrimarySlots);
        for (int slot = slotNumber; slot < slotNumber + neededPrimarySlots; slot++) {
            mek.addEquipment(eq, location, false, slot);
        }
        int secondSlotNumber = BMUtils.findSlotWithContiguousNumOfCrits(mek, secondLocation, neededSecondarySlots);
        BMUtils.removeFMU(mek, secondLocation, secondSlotNumber, neededSecondarySlots);
        for (int slot = secondSlotNumber; slot < secondSlotNumber + neededSecondarySlots; slot++) {
            mek.addEquipment(eq, secondLocation, false, slot);
        }
        changeMountStatus(eq, location, secondLocation);
        return true;
    }

    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop() || !(getUnit() instanceof Mech)) {
            return false;
        }

        if (info.getComponent() instanceof BAASBMDropTargetCriticalList) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>) info.getComponent();
            location = Integer.parseInt(list.getName());
            Transferable t = info.getTransferable();
            int slotNumber = list.getDropLocation().getIndex();
            if ((slotNumber < 0) || (slotNumber >= getUnit().getNumberOfCriticals(location))) {
                return false;
            }
            
            try {
                Mounted eq = getUnit().getEquipment(Integer.parseInt(
                        (String) t.getTransferData(DataFlavor.stringFlavor)));

                // If this equipment is already mounted, clear the criticals it's mounted in
                if (eq.getLocation() != Entity.LOC_NONE
                        || eq.getSecondLocation() != Entity.LOC_NONE) {
                    UnitUtil.removeCriticals(getUnit(), eq);
                    UnitUtil.changeMountStatus(getUnit(), eq, Entity.LOC_NONE, -1, false);
                } else {
                    eq.setOmniPodMounted(UnitUtil.canPodMount(getUnit(), eq));
                }

                StringBuffer errors = new StringBuffer();
                if (!TestEntity.isValidLocation(getUnit(), eq.getType(), location, errors)) {
                    JOptionPane.showMessageDialog(null, eq.getName() +
                            " can't be placed in " + getUnit().getLocationAbbr(location) + ":\n"
                            + errors,
                            "Invalid Location", JOptionPane.INFORMATION_MESSAGE);
                    doRefresh();
                    return false;
                }

                // superheavies can put 2 ammobins or heatsinks in one crit
                if ((getUnit() instanceof Mech) && getUnit().isSuperHeavy()) {
                    CriticalSlot cs = getUnit().getCritical(location, slotNumber);
                    if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) && (cs.getMount2() == null)) {
                        EquipmentType etype = cs.getMount().getType();
                        EquipmentType etype2 = eq.getType();
                        boolean canDouble = false;
                        if ((etype instanceof AmmoType) && (etype2 instanceof AmmoType)) {
                            canDouble = (((AmmoType) etype).getAmmoType() == ((AmmoType) etype2).getAmmoType())
                                    && (((AmmoType) etype).getRackSize() == ((AmmoType) etype2).getRackSize());
                        } else if (etype.equals(etype2) && UnitUtil.isHeatSink(etype)) {
                            canDouble = etype.getCriticals(getUnit()) == 1;
                        }
                        if (canDouble) {
                            cs.setMount2(eq);
                            changeMountStatus(eq, location);
                            return true;
                        }
                    }
                }
                return addEquipmentMech((Mech) getUnit(), eq, slotNumber);
            } catch (LocationFullException lfe) {
                JOptionPane.showMessageDialog(null, lfe.getMessage(),
                        "Location Full", JOptionPane.INFORMATION_MESSAGE);
                doRefresh();
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
        return !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType());
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Mounted mount = null;
        if (c instanceof JTable) {
            JTable table = (JTable) c;
            mount = (Mounted) table.getModel().getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
        } else if (c instanceof BAASBMDropTargetCriticalList) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>) c;
            mount = list.getMounted();
        }
        if (mount != null) {
            parentView.markUnavailableLocations(mount);
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else {
            return null;
        }
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
        doRefresh();
    }

    public Entity getUnit() {
        return eSource.getEntity();
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        parentView.unmarkAllLocations();
    }

    private void doRefresh() {
        if (refresh != null) {
            refresh.refreshAll();
        }
    }
}