/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.ui.util;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.EntityLoadingException;
import megameklab.ui.EntitySource;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Vector;

public class DropTargetCriticalList<E> extends JList<E> implements MouseListener {
    private EntitySource eSource;
    private RefreshListener refresh;
    private boolean buildView;

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

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    private void changeMountStatus(Mounted eq, int location, boolean rear) {
        changeMountStatus(eq, location, -1, rear);
    }

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation, boolean rear) {
        UnitUtil.changeMountStatus(getUnit(), eq, location, secondaryLocation, rear);

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
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

                Mounted mount = getMounted();
                if (mount != null) {
                    if ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0) {
                        changeWeaponFacing(!mount.isRearMounted());
                        return;
                    }

                    if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
                        changeOmniMounting(!mount.isOmniPodMounted());
                        return;
                    }

                    popup.setAutoscrolls(true);
                    JMenuItem info;
                    if (!UnitUtil.isFixedLocationSpreadEquipment(mount.getType())) {
                        info = new JMenuItem("Remove " + mount.getName());
                        info.addActionListener(ev -> removeCrit());
                        popup.add(info);
                    }

                    if (!UnitUtil.isArmorOrStructure(mount.getType())) {
                        info = new JMenuItem("Delete " + mount.getName());
                        info.addActionListener(ev -> removeMount());
                        popup.add(info);
                    }

                    if (getUnit().hasWorkingMisc(MiscType.F_SPONSON_TURRET)
                            && ((mount.getLocation() == Tank.LOC_LEFT) || (mount.getLocation() == Tank.LOC_RIGHT))) {
                        if (!mount.isSponsonTurretMounted()) {
                            info = new JMenuItem("Mount " + mount.getName() + " in Sponson Turret");
                            info.addActionListener(evt -> changeSponsonTurretMount(true));
                            popup.add(info);
                        } else {
                            info = new JMenuItem("Remove " + mount.getName() + " from Sponson Turret");
                            info.addActionListener(evt -> changeSponsonTurretMount(false));
                            popup.add(info);
                        }
                    }
                    if (getUnit().countWorkingMisc(MiscType.F_PINTLE_TURRET,
                                    mount.getLocation()) > 0) {
                        if (!mount.isPintleTurretMounted()) {
                            info = new JMenuItem("Mount " + mount.getName() + " in Pintle Turret");
                            info.addActionListener(evt -> changePintleTurretMount(true));
                            popup.add(info);
                        } else {
                            info = new JMenuItem("Remove " + mount.getName() + " from Pintle Turret");
                            info.addActionListener(evt -> changePintleTurretMount(false));
                            popup.add(info);
                        }
                    }
                    
                    if (getUnit().isOmni() && !mount.getType().isOmniFixedOnly()) {
                        if (mount.isOmniPodMounted()) {
                            info = new JMenuItem("Change to fixed mount");
                            info.addActionListener(evt -> changeOmniMounting(false));
                            popup.add(info);
                        } else if (UnitUtil.canPodMount(getUnit(), mount)) {
                            info = new JMenuItem("Change to pod mount");
                            info.addActionListener(evt -> changeOmniMounting(true));
                            popup.add(info);
                        }                        
                    }
                }

                if ((getUnit() instanceof Mech) && UnitUtil.isArmorable(cs)
                        && eSource.getTechManager().isLegal(Entity.getArmoredComponentTechAdvancement())) {
                    popup.addSeparator();
                    if (cs.isArmored()) {
                        JMenuItem info = new JMenuItem("Remove Armoring");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(evt -> changeArmoring());
                        popup.add(info);

                    } else {
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
    public void mouseReleased(MouseEvent evt) {

    }

    private @Nullable Mounted getMounted() {
        CriticalSlot crit = getCrit();
        try {
            if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                return crit.getMount();
            }
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
        return null;
    }

    private CriticalSlot getCrit() {
        int slot = getSelectedIndex();
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < getUnit().getNumberOfCriticals(location))) {
            crit = getUnit().getCritical(location, slot);
        }

        return crit;
    }

    private void removeCrit() {
        CriticalSlot crit = getCrit();
        Mounted mounted = getMounted();

        if ((mounted == null)) {
            return;
        }

        // Cannot remove a mast mount
        if (mounted.getType().hasFlag(MiscType.F_MAST_MOUNT)) {
            return;
        }

        UnitUtil.removeCriticals(getUnit(), mounted);

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            changeMountStatus(mounted, Entity.LOC_NONE, false);
        }

        UnitUtil.compactCriticals(getUnit());
    }

    private void changeWeaponFacing(boolean rear) {
        Mounted mount = getMounted();
        int location = getCritLocation();
        changeMountStatus(mount, location, rear);
    }

    private void changeSponsonTurretMount(boolean turret) {
        getMounted().setSponsonTurretMounted(turret);
        if (getMounted().getLinkedBy() != null) {
            getMounted().getLinkedBy().setSponsonTurretMounted(turret);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void changePintleTurretMount(boolean turret) {
        getMounted().setPintleTurretMounted(turret);
        if (getMounted().getLinkedBy() != null) {
            getMounted().getLinkedBy().setPintleTurretMounted(turret);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void changeOmniMounting(boolean pod) {
        Mounted mount = getMounted();
        if (!pod || UnitUtil.canPodMount(getUnit(), mount)) {
            mount.setOmniPodMounted(pod);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private int getCritLocation() {
        return Integer.parseInt(getName());
    }

    private void changeArmoring() {
        CriticalSlot cs = getCrit();

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted mount = Objects.requireNonNull(getMounted());
                mount.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), mount);
            } else {
                cs.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(getUnit(), cs, getCritLocation());
            }
        }

        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void removeMount() {
        Mounted mounted = getMounted();

        if (mounted == null) {
            return;
        }

        UnitUtil.removeMounted(getUnit(), mounted);

        UnitUtil.compactCriticals(getUnit());
        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(getUnit());
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }
        if (refresh != null) {
            refresh.refreshAll();
        }

    }

    public Entity getUnit() {
        return eSource.getEntity();
    }
}
