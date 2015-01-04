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

package megameklab.com.util;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.Tank;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;

public class DropTargetCriticalList<E> extends JList<E> implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 6847511182922982125L;
    private Entity unit;
    private RefreshListener refresh;
    private boolean buildView = false;

    public DropTargetCriticalList(Vector<E> vector, Entity unit,
            RefreshListener refresh, boolean buildView) {
        super(vector);
        this.unit = unit;
        this.refresh = refresh;
        this.buildView = buildView;
        setCellRenderer(new CritListCellRenderer(unit, buildView));
        addMouseListener(this);
        setTransferHandler(new CriticalTransferHandler(unit, refresh));
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

    private void changeMountStatus(Mounted eq, int location,
            int secondaryLocation, boolean rear) {

        UnitUtil.changeMountStatus(unit, eq, location, secondaryLocation, rear);

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
                if ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0) {
                    changeWeaponFacing(!mount.isRearMounted());
                    return;
                }

                if (mount != null) {
                    popup.setAutoscrolls(true);
                    JMenuItem info;
                    if (!UnitUtil.isFixedLocationSpreadEquipment(mount
                            .getType())) {
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
                    popup.add(info);
                    if ((mount.getType() instanceof WeaponType)
                            && unit.hasWorkingMisc(MiscType.F_SPONSON_TURRET)
                            && ((mount.getLocation() == Tank.LOC_LEFT) || (mount
                                    .getLocation() == Tank.LOC_RIGHT))) {
                        if (!mount.isSponsonTurretMounted()) {
                            info = new JMenuItem("Mount " + mount.getName()
                                    + " in Sponson Turret");
                            info.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    changeSponsonTurretMount(true);
                                }
                            });
                            popup.add(info);
                        } else {
                            info = new JMenuItem("Remove " + mount.getName()
                                    + " from Sponson Turret");
                            info.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    changeSponsonTurretMount(false);
                                }
                            });
                            popup.add(info);
                        }
                    }
                    if ((mount.getType() instanceof WeaponType)
                            && unit.hasWorkingMisc(MiscType.F_PINTLE_TURRET,
                                    mount.getLocation())) {
                        if (!mount.isPintleTurretMounted()) {
                            info = new JMenuItem("Mount " + mount.getName()
                                    + " in Pintle Turret");
                            info.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    changePintleTurretMount(true);
                                }
                            });
                            popup.add(info);
                        } else {
                            info = new JMenuItem("Remove " + mount.getName()
                                    + " from Pintle Turret");
                            info.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    changePintleTurretMount(false);
                                }
                            });
                            popup.add(info);
                        }
                    }
                }

                if (UnitUtil.isArmorable(cs)
                        && ((UnitUtil.getUnitTechType(unit) == UnitUtil.TECH_EXPERIMENTAL) || (UnitUtil
                                .getUnitTechType(unit) == UnitUtil.TECH_UNOFFICAL))) {
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

    private Mounted getMounted() {
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

    private CriticalSlot getCrit() {
        int slot = getSelectedIndex();
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < unit.getNumberOfCriticals(location))) {
            crit = unit.getCritical(location, slot);
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

        UnitUtil.removeCriticals(unit, mounted);

        if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
            changeMountStatus(mounted, Entity.LOC_NONE, false);
        }

        UnitUtil.compactCriticals(unit);

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(unit);
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {

            ex.printStackTrace();
        }

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

    private int getCritLocation() {
        return Integer.parseInt(getName());
    }

    private void changeArmoring() {
        CriticalSlot cs = getCrit();

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_EQUIPMENT) {
                Mounted mount = getMounted();
                mount.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(unit, mount);
            } else {
                cs.setArmored(!cs.isArmored());
                UnitUtil.updateCritsArmoredStatus(unit, cs, getCritLocation());
            }
        }

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(unit);
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {

            ex.printStackTrace();
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

        UnitUtil.removeMounted(unit, mounted);

        UnitUtil.compactCriticals(unit);
        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(unit);
        } catch (EntityLoadingException ele) {
            // do nothing.
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        if (refresh != null) {
            refresh.refreshAll();
        }

    }

}