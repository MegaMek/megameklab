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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TripodMech;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.ACWeapon;
import megamek.common.weapons.GaussWeapon;
import megamek.common.weapons.LBXACWeapon;
import megamek.common.weapons.PPCWeapon;
import megamek.common.weapons.UACWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CritListCellRenderer;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class DropTargetCriticalList<E> extends JList<E> implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 6847511182922982125L;
    private EntitySource eSource;
    private RefreshListener refresh;
    private boolean buildView = false;

    public DropTargetCriticalList(Vector<E> vector, EntitySource eSource,
            RefreshListener refresh, boolean buildView) {
        super(vector);
        setDragEnabled(true);
        this.eSource = eSource;
        this.refresh = refresh;
        this.buildView = buildView;
        setCellRenderer(new CritListCellRenderer(eSource.getEntity(), buildView));
        addMouseListener(this);
        setTransferHandler(new CriticalTransferHandler(eSource, refresh));
    }

    private void changeMountStatus(Mounted eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted eq, int location,
            int secondaryLocation, boolean rear) {

        if (getUnit() instanceof BattleArmor){
            eq.setBaMountLoc(location);
        } else {
            UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation,
                    rear);
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        if (buildView) {

            if (e.getButton() == MouseEvent.BUTTON2) {
                setSelectedIndex(locationToIndex(e.getPoint()));
                removeCrit();
            } else if (e.getButton() == MouseEvent.BUTTON3) {

                setSelectedIndex(locationToIndex(e.getPoint()));

                if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                    removeCrit();
                    return;
                }

                int location = getCritLocation();
                JPopupMenu popup = new JPopupMenu();

                CriticalSlot cs = getCrit();

                final Mounted mount = getMounted();
                if ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0) {
                    changeWeaponFacing(!mount.isRearMounted());
                    return;
                }

                if (mount != null) {
                    JMenuItem info;
                    if (!UnitUtil.isFixedLocationSpreadEquipment(mount
                            .getType())) {
                        popup.setAutoscrolls(true);
                        info = new JMenuItem("Remove " + mount.getName());
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                removeCrit();
                            }
                        });
                        popup.add(info);
                    }

                    info = new JMenuItem("Delete " + mount.getName());
                    info.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            removeMount();
                        }
                    });
                    if (!((getUnit() instanceof BattleArmor) 
                            && UnitUtil.isFixedLocationSpreadEquipment(mount
                                    .getType()))){
                        popup.add(info);
                    }
                    
                    // Allow making this a sort weapon
                    if ((mount.getType() instanceof WeaponType)
                            && !mount.isSquadSupportWeapon()
                            && mount.getLocation() == BattleArmor.LOC_SQUAD
                            && (getUnit() instanceof BattleArmor)
                            && ((BattleArmor)getUnit()).getChassisType() != 
                                BattleArmor.CHASSIS_TYPE_QUAD){
                        info = new JMenuItem("Mount as squad support weapon");
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                mount.setSquadSupportWeapon(true);
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            }
                        });
                        popup.add(info);
                    }
                    
                    // Adding ammo as a squad support mount is slightly different
                    if ((mount.getType() instanceof AmmoType)
                            && !mount.getType().hasFlag(WeaponType.F_MISSILE)
                            && !mount.isSquadSupportWeapon()
                            && mount.getLocation() == BattleArmor.LOC_SQUAD
                            && (getUnit() instanceof BattleArmor)
                            && ((BattleArmor)getUnit()).getChassisType() != 
                                BattleArmor.CHASSIS_TYPE_QUAD){
                        boolean enabled = false;
                        for (Mounted weapon : getUnit().getWeaponList()){
                            WeaponType wtype = (WeaponType)weapon.getType();
                            if (weapon.isSquadSupportWeapon() 
                                    && AmmoType.isAmmoValid(mount, wtype)){
                                enabled = true;
                            }
                        }
                        info = new JMenuItem("Mount as squad support weapon");
                        info.setEnabled(enabled);
                        info.setToolTipText("Ammo can only be squad mounted along " +
                                "with a weapon that uses it");
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                mount.setSquadSupportWeapon(true);
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            }
                        });
                        popup.add(info);
                    }
                    
                    // Allow removing squad support weapon
                    if (mount.isSquadSupportWeapon()){
                        info = new JMenuItem("Remove squad support weapon mount");
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                mount.setSquadSupportWeapon(false);
                                // Can't have squad support weapon ammo with no 
                                // squad support weapon
                                for (Mounted ammo : getUnit().getAmmo()){
                                    ammo.setSquadSupportWeapon(false);
                                }
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            }
                        });
                        popup.add(info);
                    }
                    
                    // Right-clicked on a DWP that has an attached weapon
                    if (mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK) 
                            && mount.getLinked() != null){
                        info = new JMenuItem("Remove attached weapon");
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Mounted attached = mount.getLinked();
                                attached.setDWPMounted(false);
                                mount.setLinked(null);
                                mount.setLinkedBy(null);
                                attached.setLinked(null);
                                attached.setLinkedBy(null);
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            }
                        });
                        popup.add(info);
                    }
                    
                    // Right-clicked on a AP Mount that has an attached weapon
                    if (mount.getType().hasFlag(MiscType.F_AP_MOUNT) 
                            && mount.getLinked() != null){
                        info = new JMenuItem("Remove attached weapon");
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Mounted attached = mount.getLinked();
                                attached.setAPMMounted(false);
                                mount.setLinked(null);
                                mount.setLinkedBy(null);
                                attached.setLinked(null);
                                attached.setLinkedBy(null);
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            }
                        });
                        popup.add(info);
                    }

                    if ((mount.getLocation() != Mech.LOC_LARM)
                            && (mount.getLocation() != Mech.LOC_RARM)) {

                        if (mount.getType() instanceof WeaponType) {
                            if (getUnit().hasWorkingMisc(MiscType.F_QUAD_TURRET, -1,
                                    mount.getLocation())
                                    || getUnit().hasWorkingMisc(
                                            MiscType.F_SHOULDER_TURRET, -1,
                                            mount.getLocation())
                                    || (getUnit().hasWorkingMisc(
                                            MiscType.F_HEAD_TURRET, -1,
                                            Mech.LOC_CT) && (mount
                                            .getLocation() == Mech.LOC_HEAD))) {
                                if (!mount.isMechTurretMounted()) {
                                    info = new JMenuItem("Mount "
                                            + mount.getName() + " in Turret");
                                    info.addActionListener(new ActionListener() {
                                        public void actionPerformed(
                                                ActionEvent e) {
                                            changeTurretMount(true);
                                        }
                                    });
                                    popup.add(info);
                                } else {
                                    info = new JMenuItem("Remove "
                                            + mount.getName() + " from Turret");
                                    info.addActionListener(new ActionListener() {
                                        public void actionPerformed(
                                                ActionEvent e) {
                                            changeTurretMount(false);
                                        }
                                    });
                                    popup.add(info);
                                }
                            }
                        }

                        if (!(getUnit() instanceof BattleArmor)
                                && ((mount.getType() instanceof WeaponType) || ((mount
                                        .getType() instanceof MiscType) && mount
                                        .getType()
                                        .hasFlag(MiscType.F_LIFTHOIST)))) {
                            if (!mount.isRearMounted()) {
                                info = new JMenuItem("Make " + mount.getName()
                                        + " Rear Facing");
                                info.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        changeWeaponFacing(true);
                                    }
                                });
                                popup.add(info);
                            } else {
                                info = new JMenuItem("Make " + mount.getName()
                                        + " Forward Facing");
                                info.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        changeWeaponFacing(false);
                                    }
                                });
                                popup.add(info);
                            }
                        }
                    }
                    
                    // Allow number of shots selection
                    if ((getUnit() instanceof BattleArmor) 
                            && mount.getType() instanceof AmmoType){
                        AmmoType at = (AmmoType) mount.getType();
                        int maxNumShots = TestBattleArmor.NUM_SHOTS_PER_CRIT;
                        int stepSize = 1;
                        if (at.getAmmoType() == AmmoType.T_BA_TUBE) {
                            maxNumShots = TestBattleArmor.NUM_SHOTS_PER_CRIT_TA;
                            stepSize = 2;
                        }
                        for (int i = at.getShots(); i <= maxNumShots; i += stepSize){
                            if (i == mount.getBaseShotsLeft()){
                                continue;
                            }
                            info = new JMenuItem("Set Shots: " + i);
                            final int shots = i;
                            info.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    mount.setShotsLeft(shots);
                                    if (refresh != null) {
                                        refresh.refreshAll();
                                    }
                                }
                            });
                            popup.add(info);
                        }
                    }
                }

                if ((getUnit() instanceof BipedMech || getUnit() instanceof TripodMech)
                        && ((location == Mech.LOC_LARM) || (location == Mech.LOC_RARM))) {

                    boolean canHaveLowerArm = true;
                    if (getUnit().isOmni()) {
                        int numCrits = getUnit().getNumberOfCriticals(location);
                        for (int slot = 0; slot < numCrits; slot++) {
                            CriticalSlot crit = getUnit().getCritical(location,
                                    slot);
                            if (crit == null) {
                                continue;
                            }
                            if (crit.getType() == CriticalSlot.TYPE_SYSTEM) {
                                continue;
                            }
                            Mounted m = crit.getMount();
                            if ((m.getType() instanceof GaussWeapon)
                                    || (m.getType() instanceof ACWeapon)
                                    || (m.getType() instanceof UACWeapon)
                                    || (m.getType() instanceof LBXACWeapon)
                                    || (m.getType() instanceof PPCWeapon)) {
                                canHaveLowerArm = false;
                            }
                        }
                    }

                    popup.addSeparator();
                    popup.setAutoscrolls(true);
                    if (canHaveLowerArm
                            && ((getUnit().getCritical(location, 3) == null) || (getUnit()
                                    .getCritical(location, 3).getType() != CriticalSlot.TYPE_SYSTEM))) {
                        JMenuItem info = new JMenuItem("Add Hand");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                addHand(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    } else if ((getUnit().getCritical(location, 3) != null)
                            && (getUnit().getCritical(location, 3).getType() == CriticalSlot.TYPE_SYSTEM)) {
                        JMenuItem info = new JMenuItem("Remove Hand");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                removeHand(Integer.parseInt(e
                                        .getActionCommand()));
                            }
                        });
                        popup.add(info);
                    }

                    if (canHaveLowerArm
                            && ((getUnit().getCritical(location, 2) == null) || (getUnit()
                                    .getCritical(location, 2).getType() != CriticalSlot.TYPE_SYSTEM))) {
                        JMenuItem info = new JMenuItem("Add Lower Arm");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                addArm(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    } else if ((getUnit().getCritical(location, 2) != null)
                            && (getUnit().getCritical(location, 2).getType() == CriticalSlot.TYPE_SYSTEM)) {
                        JMenuItem info = new JMenuItem("Remove Lower Arm");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                removeArm(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    }
                }

                if (UnitUtil.isArmorable(cs) && !(getUnit() instanceof BattleArmor)
                        && ((UnitUtil.getUnitTechType(getUnit()) == 
                                UnitUtil.TECH_EXPERIMENTAL) 
                            || (UnitUtil.getUnitTechType(getUnit()) == 
                                UnitUtil.TECH_UNOFFICAL))) {
                    popup.addSeparator();
                    if (cs.isArmored()) {
                        JMenuItem info = new JMenuItem("Remove Armoring");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                changeArmoring();
                            }
                        });
                        popup.add(info);

                    } else {
                        JMenuItem info = new JMenuItem("Add Armoring");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                changeArmoring();
                            }
                        });
                        popup.add(info);
                    }
                }

                if (popup.getComponentCount() > 0) {
                    popup.show(this, e.getX(), e.getY());
                }

            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public Mounted getMounted() {
        // BattleArmor doesn't have a proper critical system like other units
        //  so they are handled specially
        if (getUnit() instanceof BattleArmor){
            // The names for this list should be of the form <eq>:<slot>:<eqId>
            String[] split = ((String)this.getSelectedValue()).split(":");
            if (split.length > 2){
                int eqId = Integer.parseInt(split[2]);
                return getUnit().getEquipment(eqId);
            }
            return null;
        }
        CriticalSlot crit = getCrit();
        Mounted mount = null;
        try {
            if ((crit != null)
                    && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                return crit.getMount();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mount;
    }

    public CriticalSlot getCrit() {
        int slot = getSelectedIndex();
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < getUnit().getNumberOfCriticals(location))) {
            crit = getUnit().getCritical(location, slot);
        }

        return crit;
    }

    public void removeMount() {
        Mounted mounted = getMounted();

        if (mounted == null) {
            return;
        }

        if (UnitUtil.isStructure(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(getUnit(), true);
        }
        if (UnitUtil.isArmor(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(getUnit(), false);
        } else if (mounted.getType().isSpreadable()) {
            UnitUtil.removeAllMounteds(getUnit(), mounted.getType());
        } else {
            UnitUtil.removeCriticals(getUnit(), mounted);
            UnitUtil.removeMounted(getUnit(), mounted);
        }

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

    public void removeCrit() {
        CriticalSlot crit = getCrit();
        Mounted mounted = getMounted();

        if (mounted == null) {
            return;
        }
        
        // BattleArmor doens't use the crit system, we we can just remove the
        //  mounted and be done
        if (getUnit() instanceof BattleArmor){
            changeMountStatus(mounted, BattleArmor.MOUNT_LOC_NONE, false);
            return;
        }

        UnitUtil.removeCriticals(getUnit(), mounted);

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            changeMountStatus(mounted, Entity.LOC_NONE, false);
        }

    }

    private void changeWeaponFacing(boolean rear) {
        Mounted mount = getMounted();
        int location = getCritLocation();
        changeMountStatus(mount, location, rear);
    }

    private void changeTurretMount(boolean turret) {
        getMounted().setMechTurretMounted(turret);
        if (getMounted().getLinkedBy() != null) {
            getMounted().getLinkedBy().setMechTurretMounted(turret);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private int getCritLocation() {
        if (getUnit() instanceof BattleArmor){
            String[] split = getName().split(":");
            return Integer.parseInt(split[0]);
        } else {
            return Integer.parseInt(getName());
        }
    }

    private void addHand(int location) {
        CriticalSlot cs = getUnit().getCritical(location, 3);

        if (cs != null) {
            Mounted mount = cs.getMount();
            UnitUtil.removeCriticals(getUnit(), mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }
        getUnit().setCritical(location, 3, new CriticalSlot(
                CriticalSlot.TYPE_SYSTEM, Mech.ACTUATOR_HAND));
        addArm(location);
    }

    private void removeHand(int location) {
        if (getUnit() instanceof BipedMech || getUnit() instanceof TripodMech) {
            UnitUtil.removeHand((Mech) getUnit(), location);
            if (refresh != null) {
                refresh.refreshAll();
            }
        }

    }

    private void removeArm(int location) {
        if (getUnit() instanceof BipedMech || getUnit() instanceof TripodMech) {
            UnitUtil.removeArm((Mech)getUnit(),location);
            if (refresh != null) {
                refresh.refreshAll();
            }
        }
    }

    private void addArm(int location) {
        CriticalSlot cs = getUnit().getCritical(location, 2);

        if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            Mounted mount = cs.getMount();
            UnitUtil.removeCriticals(getUnit(), mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }

        getUnit().setCritical(location, 2, new CriticalSlot(
                CriticalSlot.TYPE_SYSTEM, Mech.ACTUATOR_LOWER_ARM));
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void changeArmoring() {
        CriticalSlot cs = getCrit();

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted mount = getMounted();
                mount.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), mount);
            } else {
                cs.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), cs, getCritLocation());
            }
        }

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