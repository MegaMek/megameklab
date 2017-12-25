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

package megameklab.com.util.Mech;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LandAirMech;
import megamek.common.LocationFullException;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CriticalTableModel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class CriticalTransferHandler extends TransferHandler {

    /**
     *
     */
    private static final long serialVersionUID = -5215375829853683877L;
    private EntitySource eSource;
    private int location = -1;
    private RefreshListener refresh;

    public CriticalTransferHandler(EntitySource eSource, RefreshListener refresh) {
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
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ((source instanceof DropTargetCriticalList)
                && (mounted.getLocation() != Entity.LOC_NONE)) {
            DropTargetCriticalList<?> list = (DropTargetCriticalList<?>)source;
            int loc;
            if (getUnit() instanceof BattleArmor){
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
            if (!(getUnit() instanceof BattleArmor)){
                for (int i = startSlot; i < (startSlot+UnitUtil.getCritsUsed(getUnit(), mounted.getType())); i++) {
                    getUnit().setCritical(loc, i, null);
                }
            }
            Mounted linkedBy = mounted.getLinkedBy();
            if (linkedBy != null && !(getUnit() instanceof BattleArmor)) {
                UnitUtil.removeCriticals(getUnit(), linkedBy);
                try {
                    UnitUtil.addMounted(getUnit(), linkedBy, mounted.getLocation(), linkedBy.isRearMounted());
                } catch (LocationFullException e) {
                    UnitUtil.changeMountStatus(getUnit(), linkedBy, Entity.LOC_NONE, Entity.LOC_NONE, false);
                    linkedBy.setLinked(null);
                    mounted.setLinkedBy(null);
                }
            }
            //UnitUtil.compactCriticals(unit);
            refresh.refreshBuild();
        }
    }

    /**
     *
     * @param mech
     * @param eq
     * @return
     */
    private boolean addEquipmentMech(Mech mech, Mounted eq, int slotNumber)
            throws LocationFullException{
        int totalCrits = UnitUtil.getCritsUsed(getUnit(), eq.getType());
        // How much space we have in the selected location
        int primaryLocSpace = 
                UnitUtil.getContiguousNumberOfCrits(getUnit(), location, slotNumber);
        
        if ((eq.getType().isSpreadable() || eq.isSplitable()) &&
                (totalCrits > 1)) {
            int critsUsed = 0;
            int primaryLocation = location;
            int nextLocation = getUnit().getTransferLocation(location);
            
            // Determine if we should spread equipment over multiple locations
            if ((eq.getType().getCriticals(getUnit()) > primaryLocSpace)
                    && !((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_TARGCOMP))
                    && !(getUnit() instanceof LandAirMech)) {
                if (location == Mech.LOC_RT) {
                    String[] locations =
                        { "Center Torso", "Right Leg", "Right Arm" };
                    JComboBox<String> combo = new JComboBox<String>(locations);
                    JOptionPane jop = new JOptionPane(combo,
                            JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);

                    JDialog dlg = jop.createDialog("Select secondary location.");
                    combo.grabFocus();
                    combo.getEditor().selectAll();

                    dlg.setVisible(true);

                    int value = ((Integer) jop.getValue()).intValue();

                    if (value == JOptionPane.CANCEL_OPTION) {
                        return false;
                    }

                    if (combo.getSelectedIndex() == 1) {
                        nextLocation = Mech.LOC_RLEG;
                    } else if (combo.getSelectedIndex() == 2) {
                        nextLocation = Mech.LOC_RARM;
                    }

                } else if (location == Mech.LOC_LT) {
                    String[] locations =
                        { "Center Torso", "Left Leg", "Left Arm" };
                    JComboBox<String> combo = new JComboBox<String>(locations);
                    JOptionPane jop = new JOptionPane(combo,
                            JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);

                    JDialog dlg = jop.createDialog("Select secondary location.");
                    combo.grabFocus();
                    combo.getEditor().selectAll();

                    dlg.setVisible(true);

                    int value = ((Integer) jop.getValue()).intValue();

                    if (value == JOptionPane.CANCEL_OPTION) {
                        return false;
                    }

                    if (combo.getSelectedIndex() == 1) {
                        nextLocation = Mech.LOC_LLEG;
                    } else if (combo.getSelectedIndex() == 2) {
                        nextLocation = Mech.LOC_LARM;
                    }

                } else if (location == Mech.LOC_CT) {
                    String[] locations =
                        { "Left Torso", "Right Torso" };
                    JComboBox<String> combo = new JComboBox<String>(locations);
                    JOptionPane jop = new JOptionPane(combo,
                            JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);

                    JDialog dlg = jop.createDialog(null,
                            "Select secondary location.");
                    combo.grabFocus();
                    combo.getEditor().selectAll();

                    dlg.setVisible(true);

                    int value = ((Integer) jop.getValue()).intValue();

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
            if ((primaryLocSpace < totalCrits) &&
                    ((nextLocation == Entity.LOC_DESTROYED)
                        || ((primaryLocSpace + secondarySpace) < totalCrits))) {
                throw new LocationFullException(eq.getName() +
                        " does not fit there in " 
                        + getUnit().getLocationAbbr(location) 
                        + " on " + getUnit().getDisplayName());
            }

            int currLoc = location;
            for (; critsUsed < totalCrits; critsUsed++) {
                mech.addEquipment(eq, currLoc, false, slotNumber);
                slotNumber = 
                        (slotNumber + 1) % mech.getNumberOfCriticals(currLoc);
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
            changeMountStatus(eq, primaryLocation, secondary, false);
        } else if (primaryLocSpace >= totalCrits) {
            if ((eq.getType() instanceof WeaponType) 
                    && eq.getType().hasFlag(WeaponType.F_VGL)) {
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
                    mech.addEquipment(eq, location, false, slotNumber);
                    if (facing.equals("Front-Left")) {
                        eq.setFacing(5);
                    } else if (facing.equals("Front-Right")) {
                        eq.setFacing(1);
                    } else if (facing.equals("Rear-Right")) {
                        eq.setFacing(2);
                    } else if (facing.equals("Rear-Left")) {
                        eq.setFacing(4);
                    } else if (facing.equals("Rear")) {
                        eq.setFacing(3);
                        UnitUtil.changeMountStatus(getUnit(), eq, location, -1, true);
                    }
            } else {
                mech.addEquipment(eq, location, false, slotNumber);
            }
            changeMountStatus(eq, location, false);
        } else {
            throw new LocationFullException(eq.getName() +
                    " does not fit there in " + getUnit().getLocationAbbr(location) +
                    " on " + getUnit().getDisplayName());
        }
        return true;
    }

    /**
     *
     * @param ba
     * @param m
     * @return
     */
    private boolean addEquipmentBA(BattleArmor ba, Mounted newMount, int trooper) {
        if (TestBattleArmor.isMountLegal(ba, newMount, location, trooper)){
            newMount.setBaMountLoc(location);
            if (newMount.getLocation() == BattleArmor.LOC_SQUAD){
                changeMountStatus(newMount, newMount.getLocation(), false);
            } else {
                changeMountStatus(newMount, trooper, false);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param aero
     * @return
     */
    private boolean addEquipmentAero(Aero aero, Mounted eq)
            throws LocationFullException{
        if (eq.getType() instanceof WeaponType){
            int[] availSpace = TestAero.availableSpace(aero);
            int[] weapCount = new int[aero.locations() - 1];
            for (Mounted m : aero.getWeaponList()){
                if (m.getLocation() != Entity.LOC_NONE){
                    weapCount[m.getLocation()]++;
                }
            }
            if ((weapCount[location] +1) > availSpace[location]){
                throw new LocationFullException(eq.getName() +
                        " does not fit in " + getUnit().getLocationAbbr(location) +
                        " on " + getUnit().getDisplayName());
            } else {
                UnitUtil.addMounted(getUnit(), eq, location, false);
            }
        } else {
            UnitUtil.addMounted(getUnit(), eq, location, false);
        }
        changeMountStatus(eq, location, false);
        return true;
    }


    /**
     *
     */
    @Override
    public boolean importData(TransferSupport info) {
        if (!info.isDrop() || !((getUnit() instanceof Mech) || (getUnit() instanceof Aero) ||
                (getUnit() instanceof BattleArmor))) {
            return false;
        }

        int trooper = 0;
        if (info.getComponent() instanceof DropTargetCriticalList) {
            DropTargetCriticalList<?> list = (DropTargetCriticalList<?>) info.getComponent();
            if (getUnit() instanceof BattleArmor){
                String[] split = list.getName().split(":");
                if (split.length != 2){
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
                if (getUnit() instanceof BattleArmor){
                    if ((location == eq.getBaMountLoc())
                            && (trooper == eq.getLocation())){
                        return false;
                    }
                } else {
                    // If this equipment is already mounted, we need to clear
                    //  the criticals its mounted in
                    if (eq.getLocation() != Entity.LOC_NONE 
                            || eq.getSecondLocation() != Entity.LOC_NONE){
                        UnitUtil.removeCriticals(getUnit(), eq);
                        changeMountStatus(eq,Entity.LOC_NONE,false);
                    } else {
                        eq.setOmniPodMounted(UnitUtil.canPodMount(getUnit(), eq));
                    }
                }
                /*if (UnitUtil.isFixedLocationSpreadEquipment(eq.getType())) {
                    return false;
                }*/

                if (!UnitUtil.isValidLocation(getUnit(), eq.getType(), location)) {
                    JOptionPane.showMessageDialog(null, eq.getName() +
                            " can't be placed in " +
                            getUnit().getLocationName(location) + "!",
                            "Invalid Location",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                
                if (getUnit() instanceof Aero){
                    return addEquipmentAero((Aero)getUnit(), eq);
                } else if (getUnit() instanceof Mech) {
                    // superheavies can put 2 ammobins or heatsinks in one crit
                    if ((getUnit() instanceof Mech) && ((Mech)getUnit()).isSuperHeavy()) {
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
                                changeMountStatus(eq, location, false);
                                return true;
                            }
                        }
                    }
                    return addEquipmentMech((Mech)getUnit(), eq, slotNumber);
                } else if (getUnit() instanceof BattleArmor){
                    return addEquipmentBA((BattleArmor)getUnit(), eq, trooper);
                }


            } catch (LocationFullException lfe) {
                JOptionPane.showMessageDialog(null, lfe.getMessage(), 
                        "Location Full", JOptionPane.INFORMATION_MESSAGE);
                return false;
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
        // check the target component
        if (!(info.getComponent() instanceof DropTargetCriticalList)) {
            return false;
        }
        // check if the dragged mounted should be transferrable
        Mounted mounted = null;
        try {
            mounted = getUnit().getEquipment(Integer
                    .parseInt((String) info.getTransferable().getTransferData(
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
        // stuff that has a fixed location is also not transferable
        if (UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())) {
            return false;
        }
        // no transfer in the same location
        if (getUnit() instanceof BattleArmor){
            // Infantry weapons cannot be mounted directly, but must instead
            //  be mounted in an AP Mount
            if (mounted.getType() instanceof InfantryWeapon){
                return false;
            }
            String[] split = info.getComponent().getName().split(":");
            if (split.length != 2){
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
            Mounted mount = (Mounted) ((CriticalTableModel) table.getModel()).getValueAt(table.getSelectedRow(), CriticalTableModel.EQUIPMENT);
            return new StringSelection(Integer.toString(getUnit().getEquipmentNum(mount)));
        } else if (c instanceof DropTargetCriticalList) {
            DropTargetCriticalList<?> list = (DropTargetCriticalList<?>)c;
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

    private void changeMountStatus(Mounted eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation, boolean rear) {

        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, rear);

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public Entity getUnit() {
        return eSource.getEntity();
    }

}