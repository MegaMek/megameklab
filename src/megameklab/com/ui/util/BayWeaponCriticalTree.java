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
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.MechFileParser;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.weapons.bayweapons.BayWeapon;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.CConfig;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

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
    public static final int FORWARD = 0; // No rear-mounting allowed (nose, aft, spheroid forward side arcs
    public static final int BOTH    = 1; // Can be mounted forward or rear (aerodyne wing arcs)
    public static final int AFT     = 2; // Always rear mounted, displayed as forward (spheroid aft side arcs)

    // In the case of spheroid dropships side locations this represents either forward or aft weapons.
    private final int location;
    private int facing;
    
    private final EntitySource eSource;
    private final DefaultTreeModel model;
    private RefreshListener refresh;
    
    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh) {
        this(location, eSource, refresh, FORWARD);
    }
    
    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh, int facing) {
        this.location = location;
        this.facing = facing;
        this.eSource = eSource;
        this.refresh = refresh;
        
        setMinimumSize(new Dimension(110,15));
        TreeNode root = initRoot();
        setRootVisible(root.getChildCount() == 0);
        model = new DefaultTreeModel(root);
        setModel(model);
        setCellRenderer(renderer);
        addMouseListener(mouseListener);
    }
    
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public void updateRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }
    
    public void rebuild() {
        TreeNode root = initRoot();
        model.setRoot(root);
        setRootVisible(root.getChildCount() == 0);
    }
    
    private TreeNode initRoot() {
        MutableTreeNode root = new DefaultMutableTreeNode();
        Set<Integer> eqSet = new HashSet<>();
        for (Mounted bay : eSource.getEntity().getWeaponBayList()) {
            if ((bay.getLocation() == location)
                    && ((facing == BOTH)
                            || (bay.isRearMounted() == (facing == AFT)))) {
                eqSet.add(eSource.getEntity().getEquipmentNum(bay));
                EquipmentNode bayNode = new BayNode(bay);
                root.insert(bayNode, root.getChildCount());
                bayNode.setParent(root);
                for (Integer wNum : bay.getBayWeapons()) {
                    final Mounted weapon = eSource.getEntity().getEquipment(wNum);
                    EquipmentNode node = new EquipmentNode(weapon);
                    bayNode.insert(node, bayNode.getChildCount());
                    node.setParent(bayNode);
                    eqSet.add(wNum);
                    if (weapon.getLinkedBy() != null) {
                        node = new EquipmentNode(weapon.getLinkedBy());
                        bayNode.insert(node, bayNode.getChildCount());
                        node.setParent(bayNode);
                        eqSet.add(eSource.getEntity().getEquipmentNum(weapon.getLinkedBy()));
                    }
                }
                for (Integer aNum : bay.getBayAmmo()) {
                    final Mounted ammo = eSource.getEntity().getEquipment(aNum);
                    EquipmentNode node = new EquipmentNode(ammo);
                    bayNode.insert(node, bayNode.getChildCount());
                    node.setParent(bayNode);
                    eqSet.add(aNum);
                }
            }
        }
        for (int eqIndex = 0; eqIndex < eSource.getEntity().getEquipment().size(); eqIndex++) {
            final Mounted eq = eSource.getEntity().getEquipment(eqIndex);
            if (!eqSet.contains(eqIndex) && (eq.getLocation() == location)
                    && ((facing == BOTH)
                            || (eq.isRearMounted() == (facing == AFT)))) {
                EquipmentNode node = new EquipmentNode(eq);
                root.insert(node, root.getChildCount());
                node.setParent(root);
            }
        }
        return root;
    }
    
    private void removeBay(final EquipmentNode bayNode) {
        model.removeNodeFromParent(bayNode);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        for (Enumeration<MutableTreeNode> e = bayNode.children(); e.hasMoreElements(); ) {
            removeEquipment((EquipmentNode)e.nextElement(), false);
        }
        bayNode.getMounted().getBayWeapons().clear();
        UnitUtil.removeMounted(eSource.getEntity(), bayNode.getMounted());
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    private void removeEquipment(final EquipmentNode node) {
        removeEquipment(node, true);
    }
    
    private void removeEquipment(final EquipmentNode node, boolean shouldRefresh) {
        model.removeNodeFromParent(node);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        final Mounted mounted = node.getMounted();
        if (node.getParent() instanceof BayNode) {
            Mounted bay = ((BayNode)node.getParent()).getMounted();
            if (mounted.getType() instanceof WeaponType) {
                bay.getBayWeapons().removeElement(eSource.getEntity().getEquipmentNum(mounted));
            } else if (mounted.getType() instanceof AmmoType) {
                bay.getBayAmmo().removeElement(eSource.getEntity().getEquipmentNum(mounted));
            }
        }
        UnitUtil.removeCriticals(eSource.getEntity(), mounted);
        UnitUtil.changeMountStatus(eSource.getEntity(), mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
        UnitUtil.compactCriticals(eSource.getEntity());
        try {
            MechFileParser.postLoadInit(eSource.getEntity());
        } catch (EntityLoadingException ex)  {
            // do nothing
        }
        if (shouldRefresh) {
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
        }
    }
    
    private void setBayFacing(EquipmentNode node, boolean rear) {
        if (node.isLeaf() && (node.getParent() instanceof BayNode)) {
            node = (EquipmentNode)node.getParent();
        }
        for (Enumeration<MutableTreeNode> e = node.children(); e.hasMoreElements(); ) {
            final Mounted m = ((EquipmentNode)e.nextElement()).getMounted();
            UnitUtil.changeMountStatus(eSource.getEntity(), m,
                    location, Entity.LOC_NONE, rear);
        }
        UnitUtil.changeMountStatus(eSource.getEntity(), node.getMounted(),
                location, Entity.LOC_NONE, rear);
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
    }
    
    private void deleteEquipment(final EquipmentNode node) {
        model.removeNodeFromParent(node);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        final Mounted mounted = node.getMounted();

        UnitUtil.removeMounted(eSource.getEntity(), mounted);
        UnitUtil.compactCriticals(eSource.getEntity());
        // Check linkings after you remove everything.
        try {
            MechFileParser.postLoadInit(eSource.getEntity());
        } catch (EntityLoadingException ele) {
            // do nothing.
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //FIXME: This is supposed to draw the background color across the width of the tree
        for (int i = 0; i < getRowCount(); i++) {
            Object node = getPathForRow(i).getLastPathComponent();
            if (node instanceof EquipmentNode) {
                g.setColor(((EquipmentNode) node).getBackgroundColor());
                final Rectangle r = getRowBounds(i);
                g.fillRect(0, r.y, getWidth(), r.height);
            }
        }
        // draw the selection color across the width
        for (int i: getSelectionRows()) {
            Rectangle r = getRowBounds(i);
            g.setColor(renderer.getBackgroundSelectionColor());
            g.fillRect(0, r.y, getWidth(), r.height);
        }
        super.paintComponent(g);
        // Draw a separating line above top level nodes
        for (int i = 0; i < getRowCount(); i++) {
            Object node = getPathForRow(i).getLastPathComponent();
            if ((node == model.getRoot()) || ((EquipmentNode)node).getParent() == model.getRoot()) {
                g.setColor(Color.black);
            } else {
                g.setColor(Color.lightGray);
            }
            final Rectangle r = getRowBounds(i);
            g.drawLine(0, r.y, getWidth(), r.y);
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
        
        public boolean isCapital() {
            return ((WeaponType)getMounted().getType()).isCapital();
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
                final Mounted m = ((EquipmentNode)e.nextElement()).getMounted();
                if (m.getType() instanceof WeaponType) {
                    av += ((WeaponType)m.getType()).getShortAV();
                }
            }
            sb.append(" (").append((int)av).append("/");
            if (isCapital()) {
                sb.append("70)");
            } else {
                sb.append("700)");
            }
            if ((facing == BOTH) && getMounted().isRearMounted()) {
                sb.append(" (R)");
            }
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
                label.setText("-Empty-");
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

    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            final int row = getClosestRowForLocation(e.getX(), e.getY());
            final EquipmentNode node = (EquipmentNode)getPathForRow(row).getLastPathComponent();
            if ((e.getButton() == MouseEvent.BUTTON2)
                    || ((e.getButton() == MouseEvent.BUTTON3)
                            && ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0))) {
                if (node.isLeaf()) {
                    removeEquipment(node);
                } else {
                    removeBay(node);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                final Mounted mounted = node.getMounted();

                if ((facing == BOTH) && ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0)) {
                    setBayFacing(node, !node.getMounted().isRearMounted());
                }

                JPopupMenu popup = new JPopupMenu();
                popup.setAutoscrolls(true);
                JMenuItem info;

                
                if (node.isLeaf()) {
                    info = new JMenuItem("Remove " + mounted.getName());
                    if ((node.getParent() instanceof BayNode)
                            && (node.getParent().getChildCount() == 1)) {
                        info.addActionListener(ev -> removeBay((EquipmentNode)node.getParent()));
                    } else {
                        info.addActionListener(ev -> removeEquipment(node));
                    }
                    popup.add(info);

                    info = new JMenuItem("Delete " + mounted.getName());
                    info.addActionListener(ev -> deleteEquipment(node));
                } else {
                    info = new JMenuItem("Remove entire bay");
                    info.addActionListener(ev -> removeBay(node));
                    popup.add(info);
                }
                if (facing == BOTH) {
                    info = new JMenuItem("Change facing");
                    info.addActionListener(ev -> setBayFacing(node, !node.getMounted().isRearMounted()));
                    popup.add(info);
                }

                popup.add(info);

                if (popup.getComponentCount() > 0) {
                    popup.show(BayWeaponCriticalTree.this, e.getX(), e.getY());
                }
            }
        }
    };
}
