/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.ui.util;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CConfig;
import megameklab.com.util.RefreshListener;

/**
 * Variant of DropTargetCriticalList for aerospace units that groups weapons into bays. Also
 * includes support for treating spheroid small craft and dropships firing arcs separately
 * from their locations.
 * 
 * @author Neoancient
 *
 */
public class BayWeaponCriticalTree extends JTree {
    
    /**
     * 
     */
    private static final long serialVersionUID = -223615170732243552L;
    // Spheroids show only forward or only aft on the side arcs
    public static final int BOTH    = 0;
    public static final int FORWARD = 1;
    public static final int AFT     = 2;

    // In the case of spheroid dropships side locations this represents either forward or aft weapons.
    private final int location;
    private int facing;
    
    private final EntitySource eSource;
    private final RefreshListener refresh;
    private final BayTreeModel model;
    
    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh) {
        this(location, eSource, refresh, BOTH);
    }
    
    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh, int facing) {
        this.location = location;
        this.facing = facing;
        this.eSource = eSource;
        this.refresh = refresh;
        
        setRootVisible(false);
        setMinimumSize(new Dimension(110,15));
        model = new BayTreeModel();
        setModel(model);
        model.refresh();
        setCellRenderer(renderer);
    }
    
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public void refresh() {
        model.refresh();
        // expand all the bays to show the weapons
        for (int i = 0; i < model.getChildCount(model.getRoot()); i++) {
            expandPath(new TreePath(new Object[] {model.getRoot(), model.getChild(model.getRoot(), i)}));
        }
    }
    
    private class BayTreeModel implements TreeModel {
        
        private final List<Mounted> bays = new ArrayList<>();
        private final List<Mounted> otherEquipment = new ArrayList<>();
        
        private final List<TreeModelListener> listeners = new CopyOnWriteArrayList<>();
        
        public void refresh() {
            bays.clear();
            otherEquipment.clear();
            for (Mounted m : eSource.getEntity().getEquipment()) {
                if ((m.getLocation() == location)
                        && ((facing == BOTH)
                                || (m.isRearMounted() == (facing == AFT)))) {
                    if (m.getType() instanceof BayWeapon) {
                        bays.add(m);
                    } else if (!(m.getType() instanceof WeaponType)
                            || (((WeaponType)m.getType()).getBayType() == null)) {
                        otherEquipment.add(m);
                    }
                }
            }
            TreeModelEvent e = new TreeModelEvent(this, new Object[] { getRoot() });
            listeners.forEach(l -> l.treeStructureChanged(e));
        }
        
        @Override
        public Object getRoot() {
            return eSource.getEntity();
        }

        @Override
        public Object getChild(Object parent, int index) {
            if (parent instanceof Entity) {
                if (index < bays.size()) {
                    return bays.get(index);
                } else {
                    return otherEquipment.get(index - bays.size());
                }
            } else if ((parent instanceof Mounted) && (((Mounted)parent).getType() instanceof BayWeapon)) {
                return eSource.getEntity().getEquipment(((Mounted)parent).getBayWeapons().get(index));
            }
            return null;
        }

        @Override
        public int getChildCount(Object parent) {
            if (parent instanceof Entity) {
                return bays.size() + otherEquipment.size();
            } else if ((parent instanceof Mounted) && (((Mounted)parent).getType() instanceof BayWeapon)) {
                return ((Mounted)parent).getBayWeapons().size();
            }
            return 0;
        }

        @Override
        public boolean isLeaf(Object node) {
            return (node instanceof Mounted) && !(((Mounted)node).getType() instanceof BayWeapon);
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            if ((parent instanceof Mounted) && ((Mounted)parent).getType() instanceof BayWeapon) {
                final int equipNum = eSource.getEntity().getEquipmentNum((Mounted)child);
                return ((Mounted)parent).getBayWeapons().indexOf(equipNum);
            }
            return 0;
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {
            listeners.add(l);
        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {
            listeners.remove(l);
        }
    }
    
    private TreeCellRenderer renderer = new DefaultTreeCellRenderer() {

        /**
         * 
         */
        private static final long serialVersionUID = 718540539581341886L;

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded,
                    leaf, row, hasFocus);

            setPreferredSize(new Dimension(180,15));
            setMaximumSize(new Dimension(180,15));
            setMinimumSize(new Dimension(180,15));
            
            if (value instanceof Mounted) {
                final Mounted mounted = (Mounted)value;
                StringBuilder text = new StringBuilder(mounted.getName());
                if (mounted.getType() instanceof BayWeapon) {
                    double av = 0.0;
                    for (int eqNum : mounted.getBayWeapons()) {
                        av += ((WeaponType)eSource.getEntity().getEquipment(eqNum).getType()).getShortAV();
                    }
                    text.append(" (").append((int)Math.ceil(av)).append("/");
                    if (((WeaponType)mounted.getType()).isCapital()) {
                        text.append("70)");
                    } else {
                        text.append("700)");
                    }
                }
                if ((facing == BOTH) && mounted.isRearMounted()) {
                    text.append(" (R)");
                }
                label.setText(text.toString());
                
                if (mounted.getType() instanceof WeaponType) {
                    label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS));
                    label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_WEAPONS));
                } else if (mounted.getType() instanceof AmmoType) {
                    label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
                    label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_AMMO));
                } else {
                    label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_EQUIPMENT));
                    label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_EQUIPMENT));
                }

            }

            return label;
        }
        
    };
}
