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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import megameklab.ui.mek.BMCriticalView;
import megameklab.util.MekUtil;
import megameklab.util.UnitUtil;

public class BAASBMDropTargetCriticalList<E> extends JList<E> implements MouseListener {
    private static final MMLogger LOGGER = MMLogger.create(BAASBMDropTargetCriticalList.class);

    private final EntitySource eSource;
    private RefreshListener refresh;
    private final boolean buildView;
    private boolean darkened = false;
    private final AbstractCriticalTransferHandler transferHandler;

    public BAASBMDropTargetCriticalList(List<E> vector, EntitySource eSource,
          RefreshListener refresh, boolean buildView,
          IView parentView) {
        super(new Vector<>(vector));
        setDragEnabled(true);
        this.eSource = eSource;
        this.refresh = refresh;
        this.buildView = buildView;
        setCellRenderer(new CritListCellRenderer(eSource.getEntity(), buildView));
        addMouseListener(this);
        if (eSource.getEntity() instanceof Mek) {
            transferHandler = new BMCriticalTransferHandler(eSource, refresh, (BMCriticalView) parentView);
        } else {
            transferHandler = new BAASCriticalTransferHandler(eSource, refresh);
        }
        setTransferHandler(transferHandler);
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBorder(BorderFactory.createLineBorder(CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
    }

    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
        transferHandler.setRefresh(refresh);
    }

    private void changeMountStatus(Mounted<?> eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted<?> eq, int location, int secondaryLocation, boolean rear) {
        if (getUnit() instanceof BattleArmor) {
            eq.setBaMountLoc(location);
        } else {
            UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, rear);
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

                final Mounted<?> mount = getMounted();
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
                    JMenuItem info;
                    if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                        popup.setAutoscrolls(true);
                        info = new JMenuItem("Remove " + mount.getName());
                        info.addActionListener(evt -> removeCrit());
                        popup.add(info);
                    }
                    if (!((getUnit() instanceof BattleArmor)
                          && UnitUtil.isFixedLocationSpreadEquipment(mount.getType()))
                          && !UnitUtil.isHeatSink(mount) && !UnitUtil.isJumpJet(mount)) {
                        info = new JMenuItem("Delete " + mount.getName());
                        info.addActionListener(ev -> removeMount());
                        popup.add(info);
                    }
                    // Allow making this a sort weapon
                    if ((mount.getType() instanceof WeaponType)
                          && !mount.isSquadSupportWeapon()
                          && mount.getLocation() == BattleArmor.LOC_SQUAD
                          && (getUnit() instanceof BattleArmor)
                          && ((BattleArmor) getUnit()).getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                        info = new JMenuItem("Mount as squad support weapon");
                        info.addActionListener(evt -> {
                            mount.setSquadSupportWeapon(true);
                            if (refresh != null) {
                                refresh.refreshAll();
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
                          && ((BattleArmor) getUnit()).getChassisType() != BattleArmor.CHASSIS_TYPE_QUAD) {
                        boolean enabled = false;
                        for (Mounted<?> weapon : getUnit().getWeaponList()) {
                            WeaponType weaponType = (WeaponType) weapon.getType();
                            if (weapon.isSquadSupportWeapon() && AmmoType.isAmmoValid(mount, weaponType)) {
                                enabled = true;
                            }
                        }
                        info = new JMenuItem("Mount as squad support weapon");
                        info.setEnabled(enabled);
                        info.setToolTipText("Ammo can only be squad mounted along with a weapon that uses it");
                        info.addActionListener(evt -> {
                            mount.setSquadSupportWeapon(true);
                            if (refresh != null) {
                                refresh.refreshAll();
                            }
                        });
                        popup.add(info);
                    }

                    // Allow removing squad support weapon
                    if (mount.isSquadSupportWeapon()) {
                        info = new JMenuItem("Remove squad support weapon mount");
                        info.addActionListener(evt -> {
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
                        popup.add(info);
                    }

                    // Right-clicked on a DWP that has an attached weapon
                    if (mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                          && (mount.getLinked() != null)) {
                        info = new JMenuItem("Remove attached weapon");
                        info.addActionListener(evt -> {
                            Mounted<?> attached = mount.getLinked();
                            attached.setDWPMounted(false);
                            mount.setLinked(null);
                            mount.setLinkedBy(null);
                            attached.setLinked(null);
                            attached.setLinkedBy(null);
                            if (refresh != null) {
                                refresh.refreshAll();
                            }
                        });
                        popup.add(info);
                    }

                    // Right-clicked on an AP Mount that has an attached weapon
                    if (mount.getType().hasFlag(MiscType.F_AP_MOUNT)
                          && (mount.getLinked() != null)) {
                        info = new JMenuItem("Remove attached weapon");
                        info.addActionListener(evt -> {
                            Mounted<?> attached = mount.getLinked();
                            attached.setAPMMounted(false);
                            mount.setLinked(null);
                            mount.setLinkedBy(null);
                            attached.setLinked(null);
                            attached.setLinkedBy(null);
                            if (refresh != null) {
                                refresh.refreshAll();
                            }
                        });
                        popup.add(info);
                    }

                    if ((mount.getLocation() != Mek.LOC_LEFT_ARM)
                          && (mount.getLocation() != Mek.LOC_RIGHT_ARM)) {
                        if (mount.getType() instanceof WeaponType) {
                            if (getUnit().hasWorkingMisc(MiscType.F_QUAD_TURRET, -1,
                                  mount.getLocation())
                                  || getUnit().hasWorkingMisc(
                                  MiscType.F_SHOULDER_TURRET, -1,
                                  mount.getLocation())
                                  || (getUnit().hasWorkingMisc(
                                  MiscType.F_HEAD_TURRET, -1,
                                  Mek.LOC_CENTER_TORSO)
                                  && (mount
                                  .getLocation() == Mek.LOC_HEAD))) {
                                if (!mount.isMekTurretMounted()) {
                                    info = new JMenuItem("Mount " + mount.getName() + " in Turret");
                                    info.addActionListener(evt -> changeTurretMount(true));
                                    popup.add(info);
                                } else {
                                    info = new JMenuItem("Remove " + mount.getName() + " from Turret");
                                    info.addActionListener(evt -> changeTurretMount(false));
                                    popup.add(info);
                                }
                            }
                        }

                        if (canRearMount(mount)) {
                            if (!mount.isRearMounted()) {
                                info = new JMenuItem("Make " + mount.getName() + " Rear Facing");
                                info.addActionListener(evt -> changeWeaponFacing(true));
                                popup.add(info);
                            } else {
                                info = new JMenuItem("Make " + mount.getName() + " Forward Facing");
                                info.addActionListener(evt -> changeWeaponFacing(false));
                                popup.add(info);
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
                            info = new JMenuItem("Set Shots: " + i);
                            final int shots = i;
                            info.addActionListener(evt -> {
                                mount.setOriginalShots(shots);
                                mount.setShotsLeft(shots);
                                if (refresh != null) {
                                    refresh.refreshAll();
                                }
                            });
                            popup.add(info);
                        }
                    }

                    if (getUnit().isOmni() && !mount.getType().isOmniFixedOnly()) {
                        if (mount.isOmniPodMounted()) {
                            info = new JMenuItem("Change to fixed mount");
                            info.addActionListener(ev -> changeOmniMounting(false));
                            popup.add(info);
                        } else if (UnitUtil.canPodMount(getUnit(), mount)) {
                            info = new JMenuItem("Change to pod mount");
                            info.addActionListener(ev -> changeOmniMounting(true));
                            popup.add(info);
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
                    } else if (!((getUnit() instanceof Mek) && getUnit().isSuperHeavy())) {
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public @Nullable Mounted<?> getMounted() {
        // BattleArmor doesn't have a proper critical system like other units
        // so they are handled specially
        if (getUnit() instanceof BattleArmor) {
            // The names for this list should be of the form <eq>:<slot>:<eqId>
            String[] split = ((String) this.getSelectedValue()).split(":");
            if (split.length > 2) {
                int eqId = Integer.parseInt(split[2]);
                return getUnit().getEquipment(eqId);
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
        Mounted<?> mounted = getMounted();

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
        Mounted<?> mounted = getMounted();

        if (mounted == null) {
            return;
        }

        // BattleArmor doesn't use the crit system, so we can just remove the mounted
        // and be done
        if (getUnit() instanceof BattleArmor) {
            changeMountStatus(mounted, BattleArmor.MOUNT_LOC_NONE, false);
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
        Mounted<?> mount = getMounted();
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
        Mounted<?> mount = getMounted();
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
        getMounted().setMekTurretMounted(turret);
        if (getMounted().getLinkedBy() != null) {
            getMounted().getLinkedBy().setMekTurretMounted(turret);
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

    private void changeArmoring() {
        CriticalSlot cs = getCrit();

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted<?> mount = getMounted();
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
}
