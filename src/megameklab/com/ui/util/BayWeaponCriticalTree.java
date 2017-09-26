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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import megamek.common.AmmoType;
import megamek.common.EquipmentType;
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
        setModel(new DefaultTreeModel(createRoot()));
        setCellRenderer(renderer);
    }
    
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public void rebuild() {
        ((DefaultTreeModel)getModel()).setRoot(createRoot());
    }
    
    private TreeNode createRoot() {
        Map<EquipmentType,Node> ammoNodes = new HashMap<>();
        int bayCount = 0;
        int ammoCount = 0;
        MutableTreeNode newRoot = new DefaultMutableTreeNode();
        for (Mounted m : eSource.getEntity().getEquipment()) {
            if ((m.getLocation() == location)
                    && ((facing == BOTH)
                            || (m.isRearMounted() == (facing == AFT)))) {
                if (m.getType() instanceof BayWeapon) {
                    Node bayNode = new Node(m);
                    for (int index = 0; index < m.getBayWeapons().size(); index++) {
                        final Mounted weapon = eSource.getEntity().getEquipment(m.getBayWeapons().get(index));
                        bayNode.insert(new Node(weapon), index);
                    }
                    newRoot.insert(bayNode, bayCount);
                    bayCount++;
                    
                } else if (m.getType() instanceof AmmoType) {
                    Node ammoNode = ammoNodes.get(m.getType());
                    if (null == ammoNode) {
                        ammoNode = new Node(m.getType());
                        ammoNodes.put(m.getType(), ammoNode);
                        newRoot.insert(ammoNode, bayCount + ammoCount);
                        ammoCount++;
                    }
                    Node node = new Node(m);
                    ammoNode.insert(node, ammoNode.getChildCount());
                    
                } else if (!(m.getType() instanceof WeaponType)
                        || (((WeaponType)m.getType()).getBayType() == null)) {
                    Node node = new Node(m);
                    newRoot.insert(node, newRoot.getChildCount());
                }
            }
        }
        return newRoot;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //FIXME: This is supposed to draw the background color across the width of the tree
        for (int i = 0; i < getRowCount(); i++) {
            Object object = ((Node)getPathForRow(i).getLastPathComponent()).getObject();
            if (object instanceof Mounted) {
                final Mounted m = (Mounted)object;
                if (m.getType() instanceof WeaponType) {
                    g.setColor(CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS));
                } else if (m.getType() instanceof AmmoType) {
                    g.setColor(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
                } else {
                    g.setColor(CConfig.getBackgroundColor(CConfig.CONFIG_EQUIPMENT));
                }
            } else if (object instanceof AmmoType) {
                g.setColor(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
            }
            final Rectangle r = getRowBounds(i);
            g.fillRect(0, r.y, getWidth(), r.height);
        }
        // draw the selection color across the width
        for (int i: getSelectionRows()) {
            Rectangle r = getRowBounds(i);
            g.setColor(renderer.getBackgroundSelectionColor());
            g.fillRect(0, r.y, getWidth(), r.height);
        }
        super.paintComponent(g);
        // Draw a separating line above top level nodes
        g.setColor(Color.black);
        for (int i = 0; i < getRowCount(); i++) {
            if (((Node)getPathForRow(i).getLastPathComponent()).getParent() == ((DefaultTreeModel)getModel()).getRoot()) {
                final Rectangle r = getRowBounds(i);
                g.drawLine(0, r.y, getWidth(), r.y);
            }
        }
    }
    
    private class Node implements MutableTreeNode {
        
        private Object object;
        private MutableTreeNode parent;
        private final Vector<MutableTreeNode> children = new Vector<>();
        
        Node(Object object) {
            this.object = object;
        }
        
        Object getObject() {
            return object;
        }

        @Override
        public TreeNode getChildAt(int childIndex) {
            return children.get(childIndex);
        }

        @Override
        public int getChildCount() {
            return children.size();
        }

        @Override
        public TreeNode getParent() {
            return parent;
        }

        @Override
        public int getIndex(TreeNode node) {
            return children.indexOf(node);
        }

        @Override
        public boolean getAllowsChildren() {
            return ((object instanceof Mounted)
                    && (((Mounted)object).getType() instanceof BayWeapon))
                    || (object instanceof AmmoType);
        }

        @Override
        public boolean isLeaf() {
            return children.isEmpty();
        }

        @Override
        public Enumeration<MutableTreeNode> children() {
            return children.elements();
        }

        @Override
        public void insert(MutableTreeNode child, int index) {
            if ((index < 0) || (index >= children.size())) {
                children.add(child);
            } else {
                children.add(index, child);
            }
        }

        @Override
        public void remove(int index) {
            children.remove(index);
        }

        @Override
        public void remove(MutableTreeNode node) {
            children.remove(node);
        }

        @Override
        public void setUserObject(Object object) {
            this.object = object;
        }

        @Override
        public void removeFromParent() {
            if (null != parent) {
                parent.remove(this);
            }
        }

        @Override
        public void setParent(MutableTreeNode newParent) {
            this.parent = newParent;
        }
        
    }
    
    private DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {

        /**
         * 
         */
        private static final long serialVersionUID = 718540539581341886L;
        
        @Override
        public void setTextNonSelectionColor(Color c) {
            // we want the renderer to set the color based on component type
        }


        @Override
        public void setBackgroundNonSelectionColor(Color c) {
            // we want the renderer to set the color based on component type
        }
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded,
                    leaf, row, hasFocus);
            if (!(value instanceof Node)) {
                return label;
            }

            setPreferredSize(new Dimension(180,15));
            setMaximumSize(new Dimension(180,15));
            setMinimumSize(new Dimension(180,15));
                        Object object = ((Node)value).getObject();
            
            if (object instanceof Mounted) {
                final Mounted mounted = (Mounted)object;
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
            } else if (object instanceof AmmoType) {
                StringBuilder text = new StringBuilder(((AmmoType)object).getName());
                int shots = 0;
                for (Enumeration<MutableTreeNode> e = ((Node)value).children(); e.hasMoreElements(); ) {
                    Node n = (Node)e.nextElement();
                    shots += ((Mounted)n.getObject()).getBaseShotsLeft();
                }
                text.append(" (").append(shots).append(" Shots)");
                label.setText(text.toString());
                label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
                label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_AMMO));
            }

            return label;
        }
        
    };
}
