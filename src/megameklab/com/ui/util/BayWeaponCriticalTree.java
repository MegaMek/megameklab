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
        Map<EquipmentType,EquipmentNode> ammoNodes = new HashMap<>();
        int bayCount = 0;
        int ammoCount = 0;
        MutableTreeNode newRoot = new DefaultMutableTreeNode();
        for (Mounted m : eSource.getEntity().getEquipment()) {
            if ((m.getLocation() == location)
                    && ((facing == BOTH)
                            || (m.isRearMounted() == (facing == AFT)))) {
                if (m.getType() instanceof BayWeapon) {
                    EquipmentNode bayNode = new BayNode(m);
                    for (int index = 0; index < m.getBayWeapons().size(); index++) {
                        final Mounted weapon = eSource.getEntity().getEquipment(m.getBayWeapons().get(index));
                        bayNode.insert(new EquipmentNode(weapon), index);
                    }
                    newRoot.insert(bayNode, bayCount);
                    bayCount++;
                    
                } else if (m.getType() instanceof AmmoType) {
                    EquipmentNode ammoNode = ammoNodes.get(m.getType());
                    if (null == ammoNode) {
                        ammoNode = new AmmoNode(m.getType());
                        ammoNodes.put(m.getType(), ammoNode);
                        newRoot.insert(ammoNode, bayCount + ammoCount);
                        ammoCount++;
                    }
                    EquipmentNode node = new EquipmentNode(m);
                    ammoNode.insert(node, ammoNode.getChildCount());
                    
                } else if (!(m.getType() instanceof WeaponType)
                        || (((WeaponType)m.getType()).getBayType() == null)) {
                    EquipmentNode node = new EquipmentNode(m);
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
            EquipmentNode node = (EquipmentNode)getPathForRow(i).getLastPathComponent();
            g.setColor(node.getBackgroundColor());
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
            if (((EquipmentNode)getPathForRow(i).getLastPathComponent()).getParent() == ((DefaultTreeModel)getModel()).getRoot()) {
                final Rectangle r = getRowBounds(i);
                g.drawLine(0, r.y, getWidth(), r.y);
            }
        }
    }
    
    /**
     * Node class used directly for individual mounts and serves as the base class for weapon
     * and ammo bays. Provides display name and color to the renderer.
     *
     */
    private class EquipmentNode implements MutableTreeNode {
        
        private Object object;
        private MutableTreeNode parent;
        private final Vector<MutableTreeNode> children = new Vector<>();
        
        EquipmentNode(Object object) {
            this.object = object;
        }
        
        Mounted getMounted() {
            return (Mounted)object;
        }
        
        WeaponType getWeapon() {
            return (WeaponType)((Mounted)object).getType();
        }
        
        AmmoType getAmmoType() {
            return (AmmoType)object;
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
        
        @Override
        public boolean isLeaf() {
            return true;
        }
        
        public Color getBackgroundColor() {
            if (getMounted().getType() instanceof WeaponType) {
                return CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS);
            } else if (getMounted().getType() instanceof AmmoType) {
                return CConfig.getBackgroundColor(CConfig.CONFIG_AMMO);
            } else {
                return CConfig.getBackgroundColor(CConfig.CONFIG_EQUIPMENT);
            }
        }
        public Color getForegroundColor() {
            if (getMounted().getType() instanceof WeaponType) {
                return CConfig.getForegroundColor(CConfig.CONFIG_WEAPONS);
            } else if (getMounted().getType() instanceof AmmoType) {
                return CConfig.getForegroundColor(CConfig.CONFIG_AMMO);
            } else {
                return CConfig.getForegroundColor(CConfig.CONFIG_EQUIPMENT);
            }
        }
        
        @Override
        public String toString() {
            return getMounted().getName();
        }
        
    }
    
    /**
     * Node used for weapon bays. Display name shows the current and maximum AV for the bay.
     *
     */
    private class BayNode extends EquipmentNode {

        BayNode(Mounted object) {
            super(object);
        }

        @Override
        public boolean isLeaf() {
            return false;
        }

        @Override
        public Color getBackgroundColor() {
            return CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS);
        }

        @Override
        public Color getForegroundColor() {
            return CConfig.getForegroundColor(CConfig.CONFIG_WEAPONS);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(getMounted().getName());
            double av = 0;
            for (Enumeration<MutableTreeNode> e = children(); e.hasMoreElements(); ) {
                av += ((EquipmentNode)e.nextElement()).getWeapon().getShortAV();
            }
            sb.append(" (").append((int)av).append("/");
            if (getWeapon().isCapital()) {
                sb.append("70)");
            } else {
                sb.append("700");
            }
            return sb.toString();
        }
    }
    
    /**
     * Node used for ammo "bays" which conserve screen space but grouping all the ammo of a given
     * type in the location into a single collapsible node.
     * 
     */
    private class AmmoNode extends EquipmentNode {

        AmmoNode(EquipmentType object) {
            super(object);
        }

        @Override
        public boolean isLeaf() {
            return false;
        }

        @Override
        public Color getBackgroundColor() {
            return CConfig.getBackgroundColor(CConfig.CONFIG_AMMO);
        }

        @Override
        public Color getForegroundColor() {
            return CConfig.getForegroundColor(CConfig.CONFIG_AMMO);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(getAmmoType().getName());
            int shots = 0;
            for (Enumeration<MutableTreeNode> e = children(); e.hasMoreElements(); ) {
                shots += ((EquipmentNode)e.nextElement()).getMounted().getBaseShotsLeft();
            }
            sb.append(" (").append(shots).append(")");
            return sb.toString();
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
            if (!(value instanceof EquipmentNode)) {
                return label;
            }
            EquipmentNode node = (EquipmentNode)value;

            setPreferredSize(new Dimension(180,15));
            setMaximumSize(new Dimension(180,15));
            setMinimumSize(new Dimension(180,15));
            
            label.setBackground(node.getBackgroundColor());
            label.setForeground(node.getForegroundColor());

            return label;
        }
        
    };
}
