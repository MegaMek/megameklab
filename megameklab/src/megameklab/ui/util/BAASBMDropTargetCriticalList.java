/*
 * Copyright (C) 2008-2026 The MegaMek Team. All Rights Reserved.
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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

import megamek.common.CriticalSlot;
import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.BipedMek;
import megamek.common.units.Entity;
import megamek.common.units.Mek;
import megamek.common.units.TripodMek;
import megamek.common.verifier.TestBattleArmor;
import megamek.common.weapons.autoCannons.ACWeapon;
import megamek.common.weapons.autoCannons.LBXACWeapon;
import megamek.common.weapons.autoCannons.UACWeapon;
import megamek.common.weapons.gaussRifles.GaussWeapon;
import megamek.common.weapons.ppc.PPCWeapon;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.mek.BMCriticalTransferHandler;
import megameklab.util.BattleArmorUtil;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class BAASBMDropTargetCriticalList extends JList<String> implements MouseListener {
    private static final MMLogger LOGGER = MMLogger.create(BAASBMDropTargetCriticalList.class);

    private final EntitySource eSource;
    private RefreshListener refresh;
    private boolean darkened = false;
    private final AbstractCriticalTransferHandler transferHandler;
    /** 0-crit equipment assigned to this location, displayed as virtual slots beyond the normal crit count. */
    private final List<Mounted<?>> zeroCritMounts = new ArrayList<>();
    /** The number of normal (physical) critical slots in this location. */
    private int normalCritCount = Integer.MAX_VALUE;

    public BAASBMDropTargetCriticalList(List<String> vector, EntitySource eSource, RefreshListener refresh,
          CriticalSlotsView parentView) {

        super(new Vector<>(vector));
        setDragEnabled(true);
        this.eSource = eSource;
        this.refresh = refresh;
        setCellRenderer(new CritListCellRenderer(eSource, true));
        addMouseListener(this);
        if (eSource.getEntity() instanceof Mek) {
            transferHandler = new BMCriticalTransferHandler(eSource, refresh, parentView);
        } else {
            transferHandler = new BAASCriticalTransferHandler(eSource, refresh, parentView);
        }
        setTransferHandler(transferHandler);
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        setPrototypeCellValue(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
    }

    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
        transferHandler.setRefresh(refresh);
    }

    /**
     * Sets the 0-crit equipment displayed as virtual slots beyond the normal critical slot count.
     *
     * @param mounts          the list of 0-crit Mounted equipment for this location
     * @param normalCritCount the number of normal (physical) critical slots
     */
    public void setZeroCritMounts(List<Mounted<?>> mounts, int normalCritCount) {
        this.zeroCritMounts.clear();
        this.zeroCritMounts.addAll(mounts);
        this.normalCritCount = normalCritCount;
    }

    /**
     * Returns true if the currently selected index corresponds to a virtual (0-crit) slot rather than a normal critical
     * slot.
     */
    public boolean isVirtualSlotSelected() {
        return getSelectedIndex() >= normalCritCount;
    }

    private void changeMountStatus(Mounted<?> eq, int location, boolean rear) {
        if (getUnit() instanceof BattleArmor) {
            eq.setBaMountLoc(location);
        } else {
            UnitUtil.changeMountStatus(getUnit(), eq, location, -1, rear);
        }

        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            setSelectedIndex(locationToIndex(e.getPoint()));
            removeCrit();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            setSelectedIndex(locationToIndex(e.getPoint()));

            // Virtual (0-crit) slots should not show a popup
            if (isVirtualSlotSelected()) {
                return;
            }

            if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                removeCrit();
                return;
            }

            int location = getCritLocation();
            JPopupMenu popup = new JPopupMenu();

            CriticalSlot cs = getCrit();

            final Mounted<?> mount = getSelectedMounted();
            if ((mount != null) && ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0)) {
                if (canRearMount(mount)) {
                    changeWeaponFacing(!mount.isRearMounted());
                }
                return;
            }

            if ((mount != null) && ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0)) {
                changeOmniMounting(!mount.isOmniPodMounted());
                return;
            }

            if ((mount != null)
                  && !(((getUnit().getEntityType() & Entity.ETYPE_QUADVEE) == Entity.ETYPE_QUADVEE)
                  && (mount.getType() instanceof MiscType)
                  && mount.getType().hasFlag(MiscType.F_TRACKS))) {
                JMenuItem menuItem;
                if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                    popup.setAutoscrolls(true);
                    menuItem = new JMenuItem("Remove " + mount.getName());
                    menuItem.addActionListener(evt -> removeCrit());
                    popup.add(menuItem);
                }
                if (!((getUnit() instanceof BattleArmor)
                      && UnitUtil.isFixedLocationSpreadEquipment(mount.getType()))
                      && !UnitUtil.isHeatSink(mount)
                      && !UnitUtil.isJumpJet(mount)) {
                    menuItem = new JMenuItem("Delete " + mount.getName());
                    menuItem.addActionListener(ev -> removeMount());
                    popup.add(menuItem);
                }
                // Allow making this a sort weapon
                if ((mount.getType() instanceof WeaponType)
                      && !mount.isSquadSupportWeapon()
                      && mount.getLocation() == BattleArmor.LOC_SQUAD
                      && (getUnit() instanceof BattleArmor)
                      && ((BattleArmor) getUnit()).getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                    menuItem = new JMenuItem("Mount as squad support weapon");
                    menuItem.addActionListener(evt -> {
                        mount.setSquadSupportWeapon(true);
                        if (refresh != null) {
                            refresh.refreshAll();
                        }
                    });
                    popup.add(menuItem);
                }

                // Adding ammo as a squad support mount is slightly different
                if ((mount.getType() instanceof AmmoType)
                      && !mount.getType().hasFlag(WeaponType.F_MISSILE)
                      && !mount.isSquadSupportWeapon()
                      && mount.getLocation() == BattleArmor.LOC_SQUAD
                      && (getUnit() instanceof BattleArmor)
                      && ((BattleArmor) getUnit()).getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                    boolean enabled = false;
                    for (Mounted<?> weapon : getUnit().getWeaponList()) {
                        WeaponType weaponType = (WeaponType) weapon.getType();
                        if (weapon.isSquadSupportWeapon() && AmmoType.isAmmoValid(mount, weaponType)) {
                            enabled = true;
                        }
                    }
                    menuItem = new JMenuItem("Mount as squad support weapon");
                    menuItem.setEnabled(enabled);
                    menuItem.setToolTipText("Ammo can only be squad mounted along with a weapon that uses it");
                    menuItem.addActionListener(evt -> {
                        mount.setSquadSupportWeapon(true);
                        if (refresh != null) {
                            refresh.refreshAll();
                        }
                    });
                    popup.add(menuItem);
                }

                // Allow removing squad support weapon
                if (mount.isSquadSupportWeapon()) {
                    menuItem = new JMenuItem("Remove squad support weapon mount");
                    menuItem.addActionListener(evt -> {
                        mount.setSquadSupportWeapon(false);
                        // Can't have squad support weapon ammo with no
                        // squad support weapon
                        for (Mounted<?> ammo : getUnit().getAmmo()) {
                            ammo.setSquadSupportWeapon(false);
                        }
                        if (refresh != null) {
                            refresh.refreshAll();
                        }
                    });
                    popup.add(menuItem);
                }

                // Unattach from a DWP
                if (mount.is(EquipmentTypeLookup.BA_DWP) && (mount.getLinked() != null)) {
                    menuItem = new JMenuItem("Remove attached weapon");
                    menuItem.addActionListener(evt -> {
                        BattleArmorUtil.emptyDwpApm(mount);
                        doRefresh();
                    });
                    popup.add(menuItem);
                }

                // Right-clicked on an AP Mount (can also be an armored glove) that has an attached weapon
                if (mount.getType().hasFlag(MiscType.F_AP_MOUNT) && (mount.getLinked() != null)) {
                    menuItem = new JMenuItem("Remove attached weapon");
                    menuItem.addActionListener(evt -> {
                        BattleArmorUtil.emptyDwpApm(mount);
                        doRefresh();
                    });
                    popup.add(menuItem);
                }

                if ((mount.getLocation() != Mek.LOC_LEFT_ARM)
                      && (mount.getLocation() != Mek.LOC_RIGHT_ARM)) {
                    if (mount.getType() instanceof WeaponType) {
                        if (getUnit().hasWorkingMisc(MiscType.F_QUAD_TURRET, null,
                              mount.getLocation())
                              || getUnit().hasWorkingMisc(
                              MiscType.F_SHOULDER_TURRET, null,
                              mount.getLocation())
                              || (getUnit().hasWorkingMisc(
                              MiscType.F_HEAD_TURRET, null,
                              Mek.LOC_CENTER_TORSO)
                              && (mount
                              .getLocation() == Mek.LOC_HEAD))) {
                            if (!mount.isMekTurretMounted()) {
                                menuItem = new JMenuItem("Mount " + mount.getName() + " in Turret");
                                menuItem.addActionListener(evt -> changeTurretMount(true));
                                popup.add(menuItem);
                            } else {
                                menuItem = new JMenuItem("Remove " + mount.getName() + " from Turret");
                                menuItem.addActionListener(evt -> changeTurretMount(false));
                                popup.add(menuItem);
                            }
                        }
                    }

                    if (canRearMount(mount)) {
                        if (!mount.isRearMounted()) {
                            menuItem = new JMenuItem("Make " + mount.getName() + " Rear Facing");
                            menuItem.addActionListener(evt -> changeWeaponFacing(true));
                            popup.add(menuItem);
                        } else {
                            menuItem = new JMenuItem("Make " + mount.getName() + " Forward Facing");
                            menuItem.addActionListener(evt -> changeWeaponFacing(false));
                            popup.add(menuItem);
                        }
                    }
                }

                // Allow number of shots selection
                if ((getUnit() instanceof BattleArmor) && (mount.getType() instanceof AmmoType at)) {
                    int maxNumShots = TestBattleArmor.NUM_SHOTS_PER_CRIT;
                    int stepSize = 1;
                    if (at.getAmmoType() == AmmoType.AmmoTypeEnum.BA_TUBE) {
                        maxNumShots = TestBattleArmor.NUM_SHOTS_PER_CRIT_TA;
                        stepSize = 2;
                    }

                    for (int i = at.getShots(); i <= maxNumShots; i += stepSize) {
                        if (i == mount.getBaseShotsLeft()) {
                            continue;
                        }
                        menuItem = new JMenuItem("Set Shots: " + i);
                        final int shots = i;
                        menuItem.addActionListener(evt -> {
                            mount.setOriginalShots(shots);
                            mount.setShotsLeft(shots);
                            if (refresh != null) {
                                refresh.refreshAll();
                            }
                        });
                        popup.add(menuItem);
                    }
                }

                if (getUnit().isOmni() && !mount.getType().isOmniFixedOnly()) {
                    if (mount.isOmniPodMounted()) {
                        menuItem = new JMenuItem("Change to fixed mount");
                        menuItem.addActionListener(ev -> changeOmniMounting(false));
                        popup.add(menuItem);
                    } else if (UnitUtil.canPodMount(getUnit(), mount)) {
                        menuItem = new JMenuItem("Change to pod mount");
                        menuItem.addActionListener(ev -> changeOmniMounting(true));
                        popup.add(menuItem);
                    }
                }
            }

            if ((getUnit() instanceof BipedMek || getUnit() instanceof TripodMek)
                  && ((location == Mek.LOC_LEFT_ARM) || (location == Mek.LOC_RIGHT_ARM))) {
                boolean canHaveLowerArm = true;
                if (getUnit().isOmni()) {
                    int numCrits = getUnit().getNumberOfCriticalSlots(location);
                    for (int slot = 0; slot < numCrits; slot++) {
                        CriticalSlot crit = getUnit().getCritical(location, slot);
                        if (crit == null) {
                            continue;
                        } else if (crit.getType() == CriticalSlot.TYPE_SYSTEM) {
                            continue;
                        }
                        Mounted<?> m = crit.getMount();
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
                    info.addActionListener(evt -> addHand(Integer.parseInt(evt.getActionCommand())));
                    popup.add(info);
                } else if ((getUnit().getCritical(location, 3) != null)
                      && (getUnit().getCritical(location, 3).getType() == CriticalSlot.TYPE_SYSTEM)) {
                    JMenuItem info = new JMenuItem("Remove Hand");
                    info.setActionCommand(Integer.toString(location));
                    info.addActionListener(evt -> removeHand(Integer.parseInt(evt.getActionCommand())));
                    popup.add(info);
                }

                if (canHaveLowerArm
                      && ((getUnit().getCritical(location, 2) == null) || (getUnit()
                      .getCritical(location, 2).getType() != CriticalSlot.TYPE_SYSTEM))) {
                    JMenuItem info = new JMenuItem("Add Lower Arm");
                    info.setActionCommand(Integer.toString(location));
                    info.addActionListener(evt -> addArm(Integer.parseInt(evt.getActionCommand())));
                    popup.add(info);
                } else if ((getUnit().getCritical(location, 2) != null)
                      && (getUnit().getCritical(location, 2).getType() == CriticalSlot.TYPE_SYSTEM)) {
                    JMenuItem info = new JMenuItem("Remove Lower Arm");
                    info.setActionCommand(Integer.toString(location));
                    info.addActionListener(evt -> removeArm(Integer.parseInt(evt.getActionCommand())));
                    popup.add(info);
                }
            }

            if ((cs != null) && cs.isArmorable() && (getUnit() instanceof Mek)
                  && eSource.getTechManager().isLegal(Entity.getArmoredComponentTechAdvancement())) {
                popup.addSeparator();
                if (cs.isArmored()) {
                    JMenuItem info = new JMenuItem("Remove Armoring");
                    info.setActionCommand(Integer.toString(location));
                    info.addActionListener(evt -> changeArmoring());
                    popup.add(info);
                } else if (canAddArmoringToCriticalSlot(cs)) {
                    JMenuItem info = new JMenuItem("Add Armoring");
                    info.setActionCommand(Integer.toString(location));
                    info.addActionListener(evt -> changeArmoring());
                    popup.add(info);
                }
            }

            if (popup.getComponentCount() > 0) {
                popup.show(this, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public @Nullable Mounted<?> getSelectedMounted() {
        // BattleArmor doesn't have a proper critical system like other units
        // so they are handled specially
        if (getUnit() instanceof BattleArmor) {
            // The names for this list should be of the form <eq>:<slot>:<eqId>
            String[] split = this.getSelectedValue().split(":");
            if (split.length > 2) {
                int eqId = Integer.parseInt(split[2]);
                return getUnit().getEquipment(eqId);
            }
            return null;
        }

        // Check for virtual slot (0-crit equipment)
        int selectedIndex = getSelectedIndex();
        if (selectedIndex >= normalCritCount) {
            int virtualIndex = selectedIndex - normalCritCount;
            if (virtualIndex >= 0 && virtualIndex < zeroCritMounts.size()) {
                return zeroCritMounts.get(virtualIndex);
            }
            return null;
        }

        CriticalSlot crit = getCrit();
        try {
            if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                return crit.getMount();
            }
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        return null;
    }

    public CriticalSlot getCrit() {
        int slot = getSelectedIndex();
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < getUnit().getNumberOfCriticalSlots(location))) {
            crit = getUnit().getCritical(location, slot);
        }

        return crit;
    }

    public void removeMount() {
        Mounted<?> mounted = getSelectedMounted();

        if (mounted == null) {
            return;
        }

        if (UnitUtil.isStructure(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(getUnit(), true);
        }
        if (UnitUtil.isArmor(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(getUnit(), false);
        } else if (mounted.getType().isSpreadable()) {
            UnitUtil.removeAllMounted(getUnit(), mounted.getType());
        } else {
            UnitUtil.removeCriticalSlots(getUnit(), mounted);
            UnitUtil.removeMounted(getUnit(), mounted);
        }

        if (getUnit().isFighter() && mounted.getLocation() != Entity.LOC_NONE) {
            UnitUtil.compactCriticalSlots(getUnit(), mounted.getLocation());
        }

        // Check linking after you remove everything.
        try {
            MekFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    public void removeCrit() {
        CriticalSlot crit = getCrit();
        Mounted<?> mounted = getSelectedMounted();

        if (mounted == null) {
            return;
        }

        if (getUnit() instanceof BattleArmor battleArmor) {
            BattleArmorUtil.unallocateMounted(battleArmor, mounted);
            doRefresh();
            return;
        }

        // 0-crit virtual slot equipment, we do nothing
        if (crit == null && mounted.getNumCriticalSlots() == 0) {
            return;
        }

        UnitUtil.removeCriticalSlots(getUnit(), mounted);
        if (getUnit().isFighter() && mounted.getLocation() != Entity.LOC_NONE) {
            UnitUtil.compactCriticalSlots(getUnit(), mounted.getLocation());
        }

        // Check linking after you remove everything.
        try {
            MekFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ignored) {

        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            changeMountStatus(mounted, Entity.LOC_NONE, false);
        }
    }

    private void changeWeaponFacing(boolean rear) {
        Mounted<?> mount = getSelectedMounted();
        int location = getCritLocation();
        changeMountStatus(mount, location, rear);
    }

    private boolean canRearMount(Mounted<?> mount) {
        if ((mount.getEntity() instanceof BattleArmor) || mount.getEntity().isFighter()) {
            return false;
        }
        if (mount.getType() instanceof MiscType) {
            if (mount.getType().hasFlag(MiscType.F_MODULAR_ARMOR)) {
                return (mount.getEntity() instanceof Mek)
                      && ((Mek) mount.getEntity()).locationIsTorso(mount.getLocation());
            } else {
                return mount.getType().hasFlag(MiscType.F_LIFT_HOIST)
                      || mount.getType().hasFlag(MiscType.F_SPRAYER)
                      || mount.getType().hasFlag(MiscType.F_LIGHT_FLUID_SUCTION_SYSTEM);
            }
        } else {
            return mount.getType() instanceof WeaponType;
        }
    }

    private void changeOmniMounting(boolean pod) {
        Mounted<?> mount = getSelectedMounted();
        if (!pod || UnitUtil.canPodMount(getUnit(), mount)) {
            mount.setOmniPodMounted(pod);
            if (getCrit().getMount2() != null) {
                getCrit().getMount2().setOmniPodMounted(pod);
            }
        }
        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    private void changeTurretMount(boolean turret) {
        getSelectedMounted().setMekTurretMounted(turret);
        if (getSelectedMounted().getLinkedBy() != null) {
            getSelectedMounted().getLinkedBy().setMekTurretMounted(turret);
        }
        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    public int getCritLocation() {
        if (getUnit() instanceof BattleArmor) {
            String[] split = getName().split(":");
            return Integer.parseInt(split[0]);
        } else {
            return Integer.parseInt(getName());
        }
    }

    private void addHand(int location) {
        CriticalSlot cs = getUnit().getCritical(location, 3);

        if (cs != null) {
            Mounted<?> mount = cs.getMount();
            UnitUtil.removeCriticalSlots(getUnit(), mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }
        getUnit().setCritical(location, 3, new CriticalSlot(
              CriticalSlot.TYPE_SYSTEM, Mek.ACTUATOR_HAND));
        addArm(location);
    }

    private void removeHand(int location) {
        if (getUnit() instanceof BipedMek || getUnit() instanceof TripodMek) {
            MekUtil.removeHand((Mek) getUnit(), location);
            if (refresh != null) {
                refresh.scheduleRefresh();
            }
        }
    }

    private void removeArm(int location) {
        if (getUnit() instanceof BipedMek || getUnit() instanceof TripodMek) {
            MekUtil.removeArm((Mek) getUnit(), location);
            if (refresh != null) {
                refresh.scheduleRefresh();
            }
        }
    }

    private void addArm(int location) {
        CriticalSlot cs = getUnit().getCritical(location, 2);

        if ((cs != null) && (cs.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            Mounted<?> mount = cs.getMount();
            UnitUtil.removeCriticalSlots(getUnit(), mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }

        getUnit().setCritical(location, 2, new CriticalSlot(
              CriticalSlot.TYPE_SYSTEM, Mek.ACTUATOR_LOWER_ARM));
        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    /**
     * Determines if armoring can be added to a critical slot. Armoring is not allowed for Superheavy meks or Interface
     * Cockpit slots.
     *
     * @param cs The critical slot to check
     *
     * @return true if armoring is allowed, false otherwise
     */
    private boolean canAddArmoringToCriticalSlot(CriticalSlot cs) {
        if (!(getUnit() instanceof Mek mek) || mek.isSuperHeavy()) {
            return false;
        }

        // Interface Cockpit slots cannot be armored
        return mek.getCockpitType() != Mek.COCKPIT_INTERFACE
              || cs.getType() != CriticalSlot.TYPE_SYSTEM
              || cs.getIndex() != Mek.SYSTEM_COCKPIT;
    }

    private void changeArmoring() {
        CriticalSlot cs = getCrit();

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted<?> mount = getSelectedMounted();
                mount.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), mount);
            } else {
                cs.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), cs, getCritLocation());
            }
        }

        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }

    public Entity getUnit() {
        return eSource.getEntity();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (darkened) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    public void setDarkened(boolean darkened) {
        this.darkened = darkened;
        repaint();
    }

    private void doRefresh() {
        if (refresh != null) {
            refresh.scheduleRefresh();
        }
    }
}
