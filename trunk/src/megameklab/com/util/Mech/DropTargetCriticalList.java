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

import megamek.common.BipedMech;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megameklab.com.util.CritListCellRenderer;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public class DropTargetCriticalList extends JList implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 6847511182922982125L;
    private Entity unit;
    private RefreshListener refresh;
    private boolean buildView = false;

    public DropTargetCriticalList(Vector<String> vector, Entity unit, RefreshListener refresh, boolean buildView) {
        super(vector);
        // new DropTarget(this, this);
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

    private void changeMountStatus(Mounted eq, int location, int secondaryLocation, boolean rear) {

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
                    JMenuItem info = new JMenuItem("Remove " + mount.getName());
                    info.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            removeCrit();
                        }
                    });
                    popup.add(info);

                    info = new JMenuItem("Delete " + mount.getName());
                    info.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            removeMount();
                        }
                    });
                    popup.add(info);

                    if ((mount.getLocation() != Mech.LOC_LARM) && (mount.getLocation() != Mech.LOC_RARM)) {

                        if (mount.getType() instanceof WeaponType) {
                            if (unit.hasWorkingMisc(MiscType.F_QUAD_TURRET, -1, mount.getLocation()) || unit.hasWorkingMisc(MiscType.F_SHOULDER_TURRET, -1, mount.getLocation()) || (unit.hasWorkingMisc(MiscType.F_HEAD_TURRET, -1, Mech.LOC_CT) && (mount.getLocation() == Mech.LOC_HEAD))) {
                                if (!mount.isTurretMounted()) {
                                    info = new JMenuItem("Mount " + mount.getName() + " in Turret");
                                    info.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            changeTurretMount(true);
                                        }
                                    });
                                    popup.add(info);
                                } else {
                                    info = new JMenuItem("Remove " + mount.getName() + " from Turret");
                                    info.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            changeTurretMount(false);
                                        }
                                    });
                                    popup.add(info);
                                }
                            }
                        }

                        if ((mount.getType() instanceof WeaponType) || ((mount.getType() instanceof MiscType) && mount.getType().hasFlag(MiscType.F_LIFTHOIST))) {
                            if (!mount.isRearMounted()) {
                                info = new JMenuItem("Make " + mount.getName() + " Rear Facing");
                                info.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        changeWeaponFacing(true);
                                    }
                                });
                                popup.add(info);
                            } else {
                                info = new JMenuItem("Make " + mount.getName() + " Forward Facing");
                                info.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        changeWeaponFacing(false);
                                    }
                                });
                                popup.add(info);
                            }
                        }
                    }
                }

                if ((unit instanceof BipedMech) && ((location == Mech.LOC_LARM) || (location == Mech.LOC_RARM))) {
                    popup.addSeparator();
                    popup.setAutoscrolls(true);
                    if ((unit.getCritical(location, 3) == null) || (unit.getCritical(location, 3).getType() != CriticalSlot.TYPE_SYSTEM)) {
                        JMenuItem info = new JMenuItem("Add Hand");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                addHand(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    } else if ((unit.getCritical(location, 3) != null) && (unit.getCritical(location, 3).getType() == CriticalSlot.TYPE_SYSTEM)) {
                        JMenuItem info = new JMenuItem("Remove Hand");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                removeHand(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    }

                    if ((unit.getCritical(location, 2) == null) || (unit.getCritical(location, 2).getType() != CriticalSlot.TYPE_SYSTEM)) {
                        JMenuItem info = new JMenuItem("Add Lower Arm");
                        info.setActionCommand(Integer.toString(location));
                        info.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                addArm(Integer.parseInt(e.getActionCommand()));
                            }
                        });
                        popup.add(info);
                    } else if ((unit.getCritical(location, 2) != null) && (unit.getCritical(location, 2).getType() == CriticalSlot.TYPE_SYSTEM)) {
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

                if (UnitUtil.isArmorable(cs) && ((UnitUtil.getUnitTechType(unit) == UnitUtil.TECH_EXPERIMENTAL) || (UnitUtil.getUnitTechType(unit) == UnitUtil.TECH_UNOFFICAL))) {
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
            if ((crit != null) && (crit.getType() == CriticalSlot.TYPE_EQUIPMENT)) {
                if (crit.getMount() != null) {
                    return crit.getMount();
                }
                mount = unit.getEquipment(crit.getIndex());
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

    private void removeMount() {
        Mounted mounted = getMounted();

        if (mounted == null) {
            return;
        }

        if (UnitUtil.isStructure(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(unit, true);
        }
        if (UnitUtil.isArmor(mounted.getType())) {
            UnitUtil.removeISorArmorMounts(unit, false);
        } else if (mounted.getType().isSpreadable()) {
            UnitUtil.removeAllMounteds(unit, mounted.getType());
        } else {
            UnitUtil.removeCriticals(unit, mounted);
            UnitUtil.removeMounted(unit, mounted);
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

    private void removeCrit() {
        CriticalSlot crit = getCrit();
        Mounted mounted = getMounted();

        if (mounted == null) {
            return;
        }

        UnitUtil.removeCriticals(unit, mounted);

        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(unit);
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
        getMounted().setTurretMounted(turret);
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private int getCritLocation() {
        return Integer.parseInt(getName());
    }

    private void addHand(int location) {
        CriticalSlot cs = unit.getCritical(location, 3);

        if (cs != null) {
            Mounted mount = unit.getEquipment(cs.getIndex());
            UnitUtil.removeCriticals(unit, mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }
        unit.setCritical(location, 3, new CriticalSlot(CriticalSlot.TYPE_SYSTEM, Mech.ACTUATOR_HAND));
        addArm(location);
    }

    private void removeHand(int location) {
        unit.setCritical(location, 3, null);
        if (refresh != null) {
            refresh.refreshAll();
        }
    }

    private void removeArm(int location) {
        unit.setCritical(location, 2, null);
        removeHand(location);
    }

    private void addArm(int location) {
        CriticalSlot cs = unit.getCritical(location, 2);

        if (cs != null) {
            Mounted mount = unit.getEquipment(cs.getIndex());
            UnitUtil.removeCriticals(unit, mount);
            changeMountStatus(mount, Entity.LOC_NONE, false);
        }

        unit.setCritical(location, 2, new CriticalSlot(CriticalSlot.TYPE_SYSTEM, Mech.ACTUATOR_LOWER_ARM));
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

}