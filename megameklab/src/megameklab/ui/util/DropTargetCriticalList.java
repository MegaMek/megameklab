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

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import megamek.common.CriticalSlot;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.loaders.MekFileParser;
import megamek.common.units.Entity;
import megamek.common.units.Tank;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;

/**
 * This list displays a critical slot block for a location in the "Assign Criticals" tab of CV and SV units.
 */
public class DropTargetCriticalList<E> extends JList<E> implements MouseListener {

    private static final MMLogger logger = MMLogger.create(DropTargetCriticalList.class);

    private final EntitySource eSource;
    private final RefreshListener refresh;
    private final boolean buildView;

    public DropTargetCriticalList(Vector<E> vector, EntitySource eSource, RefreshListener refresh,
          boolean buildView) {
        super(vector);
        this.eSource = eSource;
        this.refresh = refresh;
        this.buildView = buildView;
        setCellRenderer(new CritListCellRenderer(eSource.getEntity(), buildView));
        addMouseListener(this);
        setTransferHandler(new CriticalTransferHandler(eSource, refresh));
    }

    private void changeMountStatus(Mounted<?> eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted<?> eq, int location, int secondaryLocation, boolean rear) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, rear);

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evt) {}

    @Override
    public void mouseEntered(MouseEvent evt) {}

    @Override
    public void mouseExited(MouseEvent evt) {}

    @Override
    public void mouseReleased(MouseEvent evt) {}

    @Override
    public void mousePressed(MouseEvent evt) {
        if (buildView) {
            if (evt.getButton() == MouseEvent.BUTTON2) {
                setSelectedIndex(locationToIndex(evt.getPoint()));
                removeCrit();
            } else if (evt.getButton() == MouseEvent.BUTTON3) {
                setSelectedIndex(locationToIndex(evt.getPoint()));

                if ((evt.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                    removeCrit();
                    return;
                }

                JPopupMenu popup = new JPopupMenu();

                Mounted<?> mount = getMounted();
                if (mount != null) {
                    if ((evt.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0) {
                        changeWeaponFacing(!mount.isRearMounted());
                        return;
                    }

                    if ((evt.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
                        changeOmniMounting(!mount.isOmniPodMounted());
                        return;
                    }

                    popup.setAutoscrolls(true);
                    JMenuItem info;
                    if (isRemovable(mount)) {
                        info = new JMenuItem("Remove " + mount.getName());
                        info.addActionListener(ev -> removeCrit());
                        popup.add(info);
                    }

                    if (isDeletable(mount)) {
                        info = new JMenuItem("Delete " + mount.getName());
                        info.addActionListener(ev -> deleteMount());
                        popup.add(info);
                    }

                    if (!mount.isTurret() && getUnit().hasMisc(EquipmentTypeLookup.SPONSON_TURRET)) {
                        if (mount.isSponsonTurretMounted()) {
                            info = new JMenuItem("Remove " + mount.getName() + " from Sponson Turret");
                            info.addActionListener(evt2 -> changeSponsonTurretMount(false));
                            popup.add(info);
                        } else if ((getUnit() instanceof Tank tank) && tank.isSideLocation(mount.getLocation())) {
                            // Sponson turrets may only be mounted on ground CV/SV = Tank, TO:AUE p.160
                            info = new JMenuItem("Mount " + mount.getName() + " in Sponson Turret");
                            info.addActionListener(evt2 -> changeSponsonTurretMount(true));
                            popup.add(info);
                        }
                    }

                    if (!mount.isTurret() && getUnit().hasMisc(EquipmentTypeLookup.PINTLE_TURRET,
                          mount.getLocation())) {
                        if (!mount.isPintleTurretMounted()) {
                            info = new JMenuItem("Mount " + mount.getName() + " in Pintle Turret");
                            info.addActionListener(evt2 -> changePintleTurretMount(true));
                            popup.add(info);
                        } else {
                            info = new JMenuItem("Remove " + mount.getName() + " from Pintle Turret");
                            info.addActionListener(evt2 -> changePintleTurretMount(false));
                            popup.add(info);
                        }
                    }

                    if (getUnit().isOmni() && !mount.getType().isOmniFixedOnly()) {
                        if (mount.isOmniPodMounted()) {
                            info = new JMenuItem("Change to fixed mount");
                            info.addActionListener(evt2 -> changeOmniMounting(false));
                            popup.add(info);
                        } else if (UnitUtil.canPodMount(getUnit(), mount)) {
                            info = new JMenuItem("Change to pod mount");
                            info.addActionListener(evt2 -> changeOmniMounting(true));
                            popup.add(info);
                        }
                    }
                }

                if (popup.getComponentCount() > 0) {
                    popup.show(this, evt.getX(), evt.getY());
                }
            }
        }
    }

    private @Nullable Mounted<?> getMounted() {
        CriticalSlot crit = getCrit();
        try {
            if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                return crit.getMount();
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return null;
    }

    private CriticalSlot getCrit() {
        int slot = getSelectedIndex();
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < getUnit().getNumberOfCriticalSlots(location))) {
            crit = getUnit().getCritical(location, slot);
        }
        return crit;
    }

    private void removeCrit() {
        Mounted<?> mounted = getMounted();
        if (!isRemovable(mounted)) {
            return;
        }

        CriticalSlot crit = getCrit();
        UnitUtil.removeCriticalSlots(getUnit(), mounted);
        mounted.setPintleTurretMounted(false);
        mounted.setSponsonTurretMounted(false);
        mounted.setMekTurretMounted(false);

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            changeMountStatus(mounted, Entity.LOC_NONE, false);
        }

        UnitUtil.compactCriticalSlots(getUnit());
    }

    private void changeWeaponFacing(boolean rear) {
        Mounted<?> mount = getMounted();
        int location = getCritLocation();
        changeMountStatus(mount, location, rear);
    }

    private void changeSponsonTurretMount(boolean turret) {
        Mounted<?> mount = getMounted();

        if (mount != null) {
            mount.setSponsonTurretMounted(turret);
            if (mount.getLinkedBy() != null) {
                mount.getLinkedBy().setSponsonTurretMounted(turret);
            }
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void changePintleTurretMount(boolean turret) {
        Mounted<?> mount = getMounted();

        if (mount != null) {
            mount.setPintleTurretMounted(turret);
            if (mount.getLinkedBy() != null) {
                mount.getLinkedBy().setPintleTurretMounted(turret);
            }
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void changeOmniMounting(boolean pod) {
        Mounted<?> mount = getMounted();

        if (mount != null && (!pod || UnitUtil.canPodMount(getUnit(), mount))) {
            mount.setOmniPodMounted(pod);
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private int getCritLocation() {
        return Integer.parseInt(getName());
    }

    private void deleteMount() {
        Mounted<?> mounted = getMounted();
        if (!isDeletable(mounted)) {
            return;
        }

        UnitUtil.removeMounted(getUnit(), mounted);
        UnitUtil.compactCriticalSlots(getUnit());
        try {
            // Check linking after you remove anything
            MekFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ignored) {
            // do nothing.
        } catch (Exception ex) {
            logger.error("", ex);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private boolean isRemovable(@Nullable Mounted<?> mounted) {
        return (mounted != null) && !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())
              && !mounted.is(EquipmentTypeLookup.PINTLE_TURRET)
              && !((mounted instanceof MiscMounted) && mounted.getType().hasFlag(MiscType.F_CHASSIS_MODIFICATION));
    }

    private boolean isDeletable(@Nullable Mounted<?> mounted) {
        return (mounted != null) && !UnitUtil.isArmorOrStructure(mounted.getType())
              && !mounted.is(EquipmentTypeLookup.OMNI_CHASSIS_MOD);
    }

    private Entity getUnit() {
        return eSource.getEntity();
    }
}
