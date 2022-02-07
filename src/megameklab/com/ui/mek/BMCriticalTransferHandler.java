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
package megameklab.com.ui.mek;

import megamek.common.*;
import megameklab.com.ui.EntitySource;
import megameklab.com.ui.util.BAASBMDropTargetCriticalList;
import megameklab.com.ui.util.CriticalTableModel;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * The crit slot Transfer Handler for BM.
 *
 * @author jtighe (torren@users.sourceforge.net)
 */
public class BMCriticalTransferHandler extends TransferHandler {
    private final EntitySource eSource;
    private int location = -1;
    private final RefreshListener refresh;

    public BMCriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
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
        } catch (NumberFormatException | UnsupportedFlavorException | IOException e) {
            LogManager.getLogger().error("", e);
        }
        if ((source instanceof BAASBMDropTargetCriticalList)
                && (mounted.getLocation() != Entity.LOC_NONE)) {
            BAASBMDropTargetCriticalList<?> list = (BAASBMDropTargetCriticalList<?>)source;
            int loc = Integer.parseInt(list.getName());
            if (loc == mounted.getLocation()) {
                return;
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
            for (int i = startSlot; i < (startSlot+UnitUtil.getCritsUsed(mounted)); i++) {
                getUnit().setCritical(loc, i, null);
            }
            Mounted linkedBy = mounted.getLinkedBy();
            if (linkedBy != null) {
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

    private boolean addEquipmentMech(Mech mech, Mounted eq, int slotNumber) throws LocationFullException {
        int neededCrits = UnitUtil.getCritsUsed(eq);

        if ((eq.getType().isSpreadable() || eq.isSplitable()) && (neededCrits > 1)) {
            return addSplittableEquipment(mech, eq, slotNumber);

        } else {
            // Move the slotnumber upwards if the drop location is too far down for the equipment (PPC in the last slot)
            int locationSize = mech.getNumberOfCriticals(location);
            if ((locationSize >= neededCrits) && (slotNumber + neededCrits > locationSize)) {
                slotNumber = locationSize - neededCrits;
            }

            if (canFreeContiguousCrits(mech, location, slotNumber, neededCrits)) {
                // The equipment can be placed, possibly by removing Endo Steel and the like
                return addSingleLocationEquipment(mech, eq, slotNumber);

            } else if (getSlotWithContiguousNumberOfCrits(mech, location, neededCrits) > -1) {
                // The equipment can be placed elsewhere in the location by removing Endo Steel and the like
                slotNumber = getSlotWithContiguousNumberOfCrits(mech, location, neededCrits);
                return addSingleLocationEquipment(mech, eq, slotNumber);

            } else {
                throw new LocationFullException(eq.getName() +
                        " does not fit there in " + getUnit().getLocationAbbr(location) +
                        " on " + getUnit().getDisplayName());
            }
        }
    }

    /** Adds standard (non-splittable/spreadable) equipment to the mek. */
    private boolean addSingleLocationEquipment(Mech mech, Mounted eq, int slotNumber)
            throws LocationFullException {
        int neededCrits = UnitUtil.getCritsUsed(eq);
        removeUnhittables(mech, location, slotNumber, neededCrits);
        if ((eq.getType() instanceof WeaponType) && eq.getType().hasFlag(WeaponType.F_VGL)) {
            return addVGL(mech, eq, slotNumber);
        } else {
            mech.addEquipment(eq, location, false, slotNumber);
        }
        changeMountStatus(eq, location);
        return true;
    }

    /**
     * Returns the first slot in the location that together with following slots
     * forms a contiguous block of the given length as size where all slotes
     * are either empty of contain freely movable crits such as Endo Steel.
     * Returns -1 if there is no such slot.
     */
    private int getSlotWithContiguousNumberOfCrits(Entity mech, int location, int length) {
        for (int slot = 0; slot < mech.getNumberOfCriticals(location); slot++) {
            if (canFreeContiguousCrits(mech, location, slot, length)) {
                return slot;
            }
        }
        return -1;
    }

    /**
     * Returns the number of contiguous criticals in the given
     * location, starting at the given critical slot
     */
    private int getContiguousNumberOfCrits(Entity unit, int location, int startingSlot) {
        int numCritSlots = unit.getNumberOfCriticals(location);
        int contiguousCrits = 0;
        for (int slot = startingSlot; slot < numCritSlots; slot++) {
            if (unit.getCritical(location, slot) == null) {
                contiguousCrits++;
            } else {
                break;
            }
        }
        return contiguousCrits;
    }

    /**
     * Returns true when numOfSlots contiguous slots starting from startingSlot are either free or
     * can be freed by removing unhittable and movable equipment such as Endo Steel.
     */
    private boolean canFreeContiguousCrits(Entity mech, int location, int startingSlot, int numOfSlots) {
        for (int slot = startingSlot; slot < startingSlot + numOfSlots; slot++) {
            CriticalSlot critSlot = mech.getCritical(location, slot);
            if ((critSlot != null) && !isFreelyMovable(critSlot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true when numOfSlots contiguous slots starting from startingSlot are either free or
     * can be freed by removing unhittable and movable equipment such as Endo Steel.
     */
    private void removeUnhittables(Entity mech, int location, int startingSlot, int numOfSlots) {
        for (int slot = startingSlot; slot < startingSlot + numOfSlots; slot++) {
            CriticalSlot critSlot = mech.getCritical(location, slot);
            if ((critSlot != null) && isFreelyMovable(critSlot)) {
                Mounted mounted = critSlot.getMount();
                UnitUtil.removeCriticals(mech, mounted);
                UnitUtil.changeMountStatus(mech, mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
            }
        }
    }

    /** Returns true when a slot's equipment is not hittable and freely movable. */
    //TODO: Keep only if specific equipment must be excluded. Dont know if there's any.
    private boolean isFreelyMovable(CriticalSlot critSlot) {
        return (critSlot.getMount() != null) && !critSlot.getMount().getType().isHittable();
    }

    /** Adds splittable/spreadable equipment to the mek. */
    private boolean addSplittableEquipment(Mech mech, Mounted eq, int slotNumber)
            throws LocationFullException {
        int totalCrits = UnitUtil.getCritsUsed(eq);
        int primaryLocSpace = getContiguousNumberOfCrits(getUnit(), location, slotNumber);
        int critsUsed = 0;
        int primaryLocation = location;
        int nextLocation = getUnit().getTransferLocation(location);

        // Determine if we should spread equipment over multiple locations
        if ((totalCrits > primaryLocSpace)
                && !((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP))
                && !(getUnit() instanceof LandAirMech)) {
            if (location == Mech.LOC_RT) {
                // Catch torso-only equipment (e.g. heavy gauss)
                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), Mech.LOC_RARM)) {
                    nextLocation = Mech.LOC_CT;
                } else {
                    String[] locations =
                            { "Center Torso", "Right Leg", "Right Arm" };
                    JComboBox<String> combo = new JComboBox<>(locations);
                    JOptionPane jop = new JOptionPane(combo,
                            JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);

                    JDialog dlg = jop.createDialog("Select secondary location.");
                    combo.grabFocus();
                    combo.getEditor().selectAll();

                    dlg.setVisible(true);

                    int value = (Integer) jop.getValue();

                    if (value == JOptionPane.CANCEL_OPTION) {
                        return false;
                    }

                    if (combo.getSelectedIndex() == 1) {
                        nextLocation = Mech.LOC_RLEG;
                    } else if (combo.getSelectedIndex() == 2) {
                        nextLocation = Mech.LOC_RARM;
                    }
                }
            } else if (location == Mech.LOC_LT) {
                // Catch torso-only equipment (e.g. heavy gauss)
                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), Mech.LOC_LARM)) {
                    nextLocation = Mech.LOC_CT;
                } else {
                    String[] locations =
                            { "Center Torso", "Left Leg", "Left Arm" };
                    JComboBox<String> combo = new JComboBox<>(locations);
                    JOptionPane jop = new JOptionPane(combo,
                            JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);

                    JDialog dlg = jop.createDialog("Select secondary location.");
                    combo.grabFocus();
                    combo.getEditor().selectAll();

                    dlg.setVisible(true);

                    int value = (Integer) jop.getValue();

                    if (value == JOptionPane.CANCEL_OPTION) {
                        return false;
                    }

                    if (combo.getSelectedIndex() == 1) {
                        nextLocation = Mech.LOC_LLEG;
                    } else if (combo.getSelectedIndex() == 2) {
                        nextLocation = Mech.LOC_LARM;
                    }
                }
            } else if (location == Mech.LOC_CT) {
                String[] locations =
                        { "Left Torso", "Right Torso" };
                JComboBox<String> combo = new JComboBox<>(locations);
                JOptionPane jop = new JOptionPane(combo,
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION);

                JDialog dlg = jop.createDialog(null,
                        "Select secondary location.");
                combo.grabFocus();
                combo.getEditor().selectAll();

                dlg.setVisible(true);

                int value = (Integer) jop.getValue();

                if (value == JOptionPane.CANCEL_OPTION) {
                    return false;
                }

                if (combo.getSelectedIndex() == 1) {
                    nextLocation = Mech.LOC_RT;
                } else {
                    nextLocation = Mech.LOC_LT;
                }
            }
        }


        // Determine how much usable space we have in both locations
        int secondarySpace = UnitUtil.getHighestContinuousNumberOfCrits(
                getUnit(), nextLocation);

        // Check for available space
        if ((primaryLocSpace < totalCrits)
                && ((nextLocation == Entity.LOC_DESTROYED) || ((primaryLocSpace + secondarySpace) < totalCrits))) {
            throw new LocationFullException(eq.getName() + " does not fit there in "
                    + getUnit().getLocationAbbr(location) + " on " + getUnit().getDisplayName());
        }

        int currLoc = location;
        for (; critsUsed < totalCrits; critsUsed++) {
            mech.addEquipment(eq, currLoc, false, slotNumber);
            slotNumber = (slotNumber + 1) % mech.getNumberOfCriticals(currLoc);
            primaryLocSpace--;
            if (primaryLocSpace == 0) {
                slotNumber = 0;
                currLoc = nextLocation;
                totalCrits -= critsUsed;
                critsUsed = 0;
            }
        }
        int secondary = Entity.LOC_NONE;
        if ((primaryLocSpace <= 0) && (slotNumber > 0)) {
            secondary = nextLocation;
        }
        changeMountStatus(eq, primaryLocation, secondary);
        return true;
    }


    /** Add a vehicular grenade launcher, asking the user for the facing. */
    private boolean addVGL(Mech mech, Mounted vgl, int slotNumber) throws LocationFullException {
        String[] facings;
        if (location == Mech.LOC_LT) {
            facings = new String[4];
            facings[0] = "Front";
            facings[1] = "Front-Left";
            facings[2] = "Rear-Left";
            facings[3] = "Rear";
        } else if (location == Mech.LOC_RT) {
            facings = new String[4];
            facings[0] = "Front";
            facings[1] = "Front-Right";
            facings[2] = "Rear-Right";
            facings[3] = "Rear";
        } else if (location == Mech.LOC_CT) {
            facings = new String[2];
            facings[0] = "Front";
            facings[1] = "Rear";
        }  else {
            JOptionPane.showMessageDialog(null,
                    "VGL must be placed in torso location!",
                    "Invalid location",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String facing = (String)JOptionPane.showInputDialog(null,
                "Please choose the facing of the VGL",
                "Choose Facing", JOptionPane.QUESTION_MESSAGE,
                null, facings, facings[0]);
        if (facing == null) {
            return false;
        }
        mech.addEquipment(vgl, location, false, slotNumber);
        changeMountStatus(vgl, location);
        if (facing.equals("Front-Left")) {
            vgl.setFacing(5);
        } else if (facing.equals("Front-Right")) {
            vgl.setFacing(1);
        } else if (facing.equals("Rear-Right")) {
            vgl.setFacing(2);
        } else if (facing.equals("Rear-Left")) {
            vgl.setFacing(4);
        } else if (facing.equals("Rear")) {
            vgl.setFacing(3);
            UnitUtil.changeMountStatus(getUnit(), vgl, location, -1, true);
        }
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
                    changeMountStatus(eq, Entity.LOC_NONE);
                } else {
                    eq.setOmniPodMounted(UnitUtil.canPodMount(getUnit(), eq));
                }

                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), location)) {
                    JOptionPane.showMessageDialog(null, eq.getName() +
                            " can't be placed in " +
                            getUnit().getLocationName(location) + "!",
                            "Invalid Location",
                            JOptionPane.INFORMATION_MESSAGE);
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
                            canDouble = (((AmmoType)etype).getAmmoType() == ((AmmoType)etype2).getAmmoType())
                                    && (((AmmoType)etype).getRackSize() == ((AmmoType)etype2).getRackSize());
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