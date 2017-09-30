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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
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
import javax.swing.tree.TreePath;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.LocationFullException;
import megamek.common.MechFileParser;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.SmallCraft;
import megamek.common.WeaponType;
import megamek.common.annotations.Nullable;
import megamek.common.loaders.EntityLoadingException;
import megamek.common.logging.LogLevel;
import megamek.common.weapons.bayweapons.BayWeapon;
import megamek.common.weapons.bayweapons.PPCBayWeapon;
import megameklab.com.MegaMekLab;
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
        
        AeroBayTransferHandler cth = new AeroBayTransferHandler(eSource);
        setDragEnabled(true);
        setTransferHandler(cth);
    }
    
    /**
     * Sets whether this arc should show only forward-mounted, rear-mounted, or both
     * @param facing Either FORWARD, AFT, or BOTH
     */
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
    
    /**
     * @return The number of weapon slots required by equipment allocated to this arc.
     */
    public int getSlotCount() {
        int count = 0;
        for (Enumeration<?> e = ((MutableTreeNode)model.getRoot()).children(); e.hasMoreElements(); ) {
            final Object node = e.nextElement();
            if (node instanceof BayNode) {
                count += ((BayNode)node).getMounted().getBayWeapons().size();
            } else if (node instanceof EquipmentNode) {
                count += ((EquipmentNode)node).getMounted().getType().getCriticals(eSource.getEntity());
            }
        }
        return count;
    }
    
    /**
     * Runs through all equipment mounted on the vessel and adds nodes for the ones that match this
     * tree's location and facing.
     * 
     * @return A new root node
     */
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
    
    /**
     * Removes the bay node and all subnodes.
     * Removes all equipment in this bay by assigning it to LOC_NONE and deletes the bay itself.
     * @param bayNode
     */
    private void removeBay(final EquipmentNode bayNode) {
        removeBay(bayNode, true);
    }
    
    /**
     * Removes the bay node and all subnodes.
     *
     * @param bayNode     The bay node to remove
     * @param updateMount If true, will remove all equipment in the bay from the location and delete the bay itself
     */
    private void removeBay(final EquipmentNode bayNode, boolean updateMount) {
        model.removeNodeFromParent(bayNode);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        for (Enumeration<MutableTreeNode> e = bayNode.children(); e.hasMoreElements(); ) {
            removeEquipment((EquipmentNode)e.nextElement(), false, updateMount);
        }
        if (updateMount) {
            bayNode.getMounted().getBayWeapons().clear();
            UnitUtil.removeMounted(eSource.getEntity(), bayNode.getMounted());
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
        }
    }
    
    /**
     * Removes equipment node and assigns its mount to LOC_NONE.
     * @param node
     */
    private void removeEquipment(final EquipmentNode node) {
        removeEquipment(node, true, true);
    }
    
    /**
     * Removes equipment node.
     * 
     * @param node          The node to remove
     * @param shouldRefresh If false, will not trigger refreshes. This is used when removing
     *                      all equipment in a bay to hold the refresh until the end.
     * @param updateMount   If true, the mount location will be set to LOC_NONE. The transfer handler
     *                      takes care of this separately to keep from unallocating equipment that
     *                      has been transferred to another bay.
     */
    private void removeEquipment(final EquipmentNode node, boolean shouldRefresh, boolean updateMount) {
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
            // If we have Artemis, et. al. we want to remove it as well.
            if ((node.getMounted().getLinkedBy() != null)
                    && (node.getMounted().getType() instanceof WeaponType)) {
                for (Enumeration<?> e = node.getParent().children(); e.hasMoreElements(); ) {
                    final EquipmentNode n = (EquipmentNode)e.nextElement();
                    if (node.getMounted().getLinkedBy() == n.getMounted()) {
                        removeEquipment(n);
                        break;
                    }
                }
            } else if ((node.getMounted().getLinked() != null)
                    && (node.getMounted().getLinked().getType() instanceof WeaponType)) {
                node.getMounted().getLinked().setLinkedBy(null);
                node.getMounted().setLinked(null);
            }
        }
        UnitUtil.removeCriticals(eSource.getEntity(), mounted);
        if (updateMount) {
            UnitUtil.changeMountStatus(eSource.getEntity(), mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
            UnitUtil.compactCriticals(eSource.getEntity());
            try {
                MechFileParser.postLoadInit(eSource.getEntity());
            } catch (EntityLoadingException ex)  {
                // do nothing
            }
        }
        if (shouldRefresh) {
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
        }
    }
    
    /**
     * Sets the rearMounted flag on this bay and all equipment mounted in it.
     * 
     * @param node
     * @param rear
     */
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
    
    /**
     * Removes the node and removes the equipment from the unit entirely.
     * @param node
     */
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
    
    /**
     * Adds lines to separate the entries to resemble the other build allocation views.
     */
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
            if ((getMounted().getLinked() != null)
                    && (getMounted().getLinked().getType() instanceof WeaponType)) {
                return " + " + getMounted().getName();
            }
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
    
    /**
     * Sets node name and text/background colors.
     */
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

            setPreferredSize(new Dimension(180, 15));
            setMaximumSize(new Dimension(180, 15));
            setMinimumSize(new Dimension(180, 15));
            
            label.setBackground(node.getBackgroundColor());
            label.setForeground(node.getForegroundColor());
            
            return label;
        }
        
    };

    /**
     * Displays popup menu
     */
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

    /**
     * Used by the unallocated equipment list to show the name of the location on the popup menu
     * @return The name of the location
     */
    public String getLocationName() {
        if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            if (location == SmallCraft.LOC_LWING) {
                if (facing == FORWARD) {
                    return "Forward Left";
                } else if (facing == AFT){
                    return "Aft Left";
                } else {
                    return "Left Wing";
                }
            } else if (location == SmallCraft.LOC_RWING) {
                if (facing == FORWARD) {
                    return "Forward Right";
                } else if (facing == AFT){
                    return "Aft Right";
                } else {
                    return "Right Wing";
                }
            }
        }
        return eSource.getEntity().getLocationName(location);
    }
    
    /**
     * Used by the unallocated equipment list to determine whether the arc represented by this
     * tree is valid for aerodyne small craft and dropships.
     * @return false for small craft/dropship aft side arcs, true for all others
     */
    public boolean validForAerodyne() {
        return facing != AFT;
    }

    /**
     * Finds all bays in this arc where the given equipment can be added based on type and current AV.
     * 
     * @param eq  An equipment mount
     * @return    A list of valid bays for the equipment
     */
    public List<Mounted> baysFor(Mounted eq) {
        List<Mounted> retVal = new ArrayList<>();
        for (Enumeration<?> e = ((MutableTreeNode)model.getRoot()).children(); e.hasMoreElements(); ) {
            final Mounted bay = ((EquipmentNode)e.nextElement()).getMounted();
            if ((bay.getType() instanceof BayWeapon)
                    && (canTakeEquipment(bay, eq))) {
                retVal.add(bay);
            }
        }
        return retVal;
    }

    /**
     * Adds a new bay of the appropriate type to the unit and adds the equipment to the bay.
     * 
     * @param bayType The type of bay to be added.
     * @param eq      The equipment to be added.
     */
    public void addToNewBay(EquipmentType bayType, Mounted eq) {
        try {
            Mounted bay = eSource.getEntity().addEquipment(bayType, location, facing == AFT);
            BayNode bayNode = new BayNode(bay);
            model.insertNodeInto(bayNode, (MutableTreeNode)model.getRoot(), ((TreeNode)model.getRoot()).getChildCount());
            bayNode.setParent((MutableTreeNode)model.getRoot());
            addToBay(bay, eq);
        } catch (LocationFullException ex) {
            //should not happen
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    /**
     * Adds an equipment mount to a bay. Changes the equipment mount's location and updates the bay's
     * weapon or ammo list if necessary.
     * 
     * @param bay
     * @param eq
     */
    public void addToBay(@Nullable Mounted bay, Mounted eq) {
        // Check that we have a bay
        if ((null == bay) || !canTakeEquipment(bay, eq)) {
            if (eq.getType() instanceof WeaponType) {
                EquipmentType bayType = ((WeaponType)eq.getType()).getBayType();
                addToNewBay(bayType, eq);
            } else {
                addToLocation(eq);
            }
            return;
        }
        EquipmentNode bayNode = null;
        for (Enumeration<?> e = ((TreeNode)model.getRoot()).children(); e.hasMoreElements(); ) {
            final EquipmentNode node = (EquipmentNode)e.nextElement();
            if (node.getMounted() == bay) {
                bayNode = node;
                break;
            }
        }
        if (null != bayNode) {
            moveToArc(eq);
            EquipmentNode eqNode = new EquipmentNode(eq);
            model.insertNodeInto(eqNode, bayNode, bayNode.getChildCount());
            eqNode.setParent(bayNode);
            
            if (eq.getType() instanceof WeaponType) {
                bay.addWeaponToBay(eSource.getEntity().getEquipmentNum(eq));
                if (eq.getLinkedBy() != null) {
                    addToBay(bay, eq.getLinkedBy());
                }
            } else if (eq.getType() instanceof AmmoType) {
                bay.addAmmoToBay(eSource.getEntity().getEquipmentNum(eq));
            }
            if (eq.getType() instanceof MiscType) {
                try {
                    MechFileParser.postLoadInit(eSource.getEntity());
                } catch (EntityLoadingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            MegaMekLab.getLogger().log(BayWeaponCriticalTree.class, "addToBay(Mounted,Mounted)",    //$NON-NLS-1$
                    LogLevel.DEBUG, bay.getName() + "[" + eSource.getEntity().getEquipmentNum(bay)  //$NON-NLS-1$
                    + "] not found in " + getLocationName());                                       //$NON-NLS-1$
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }

    /**
     * Adds equipment to a location without a bay.
     * @param eq
     */
    public void addToLocation(Mounted eq) {
        moveToArc(eq);
        EquipmentNode node = new EquipmentNode(eq);
        model.insertNodeInto(node, (MutableTreeNode)model.getRoot(), ((TreeNode)model.getRoot()).getChildCount());
        node.setParent((MutableTreeNode)model.getRoot());
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
    }
    
    /**
     * Moves a bay and all its contents from another location
     * @param bay
     */
    public void addBay(Mounted bay) {
        // First move the bay here
        moveToArc(bay);
        BayNode bayNode = new BayNode(bay);
        model.insertNodeInto(bayNode, (MutableTreeNode)model.getRoot(),
                ((TreeNode)model.getRoot()).getChildCount());
        bayNode.setParent((MutableTreeNode)model.getRoot());
        // Now go through all the equipment in the bay
        for (Integer eqNum : bay.getBayWeapons()) {
            final Mounted weapon = eSource.getEntity().getEquipment(eqNum);
            moveToArc(weapon);
            EquipmentNode node = new EquipmentNode(weapon);
            model.insertNodeInto(node, bayNode, bayNode.getChildCount());
            node.setParent(bayNode);
            if (weapon.getLinkedBy() != null) {
                moveToArc(weapon.getLinkedBy());
                node = new EquipmentNode(weapon.getLinkedBy());
                model.insertNodeInto(node, bayNode, bayNode.getChildCount());
                node.setParent(bayNode);
            }
        }
        for (Integer eqNum : bay.getBayAmmo()) {
            final Mounted ammo = eSource.getEntity().getEquipment(eqNum);
            moveToArc(ammo);
            EquipmentNode node = new EquipmentNode(ammo);
            model.insertNodeInto(node, bayNode, bayNode.getChildCount());
            node.setParent(bayNode);
        }
    }
    
    private void moveToArc(Mounted eq) {
        UnitUtil.removeCriticals(eSource.getEntity(), eq);
        UnitUtil.changeMountStatus(eSource.getEntity(), eq, location,
                Entity.LOC_NONE, (facing == AFT) || ((facing == BOTH) && eq.isRearMounted()));
    }
    
    /**
     * Determines whether the equipment can be added to the bay. For a weapon this is determined
     * by bay type and AV limit. For ammo this is determined by the presence of a weapon in that location
     * that can use the ammo. For weapon enhancements this is determined by the presence of a weapon
     * that can use the enhancement that doesn't already have one.
     * 
     * @param bay
     * @param eq
     * @return
     */
    private boolean canTakeEquipment(Mounted bay, Mounted eq) {
        if (eq.getType() instanceof WeaponType) {
            if (((WeaponType)eq.getType()).getBayType() != bay.getType()) {
                return false;
            }
            // find current av
            double av = bay.getBayWeapons().stream().map(eqNum -> eSource.getEntity().getEquipment(eqNum))
                    .mapToDouble(m -> ((WeaponType)m.getType()).getShortAV()).sum();
            if (((WeaponType)eq.getType()).isCapital()) {
                return av + ((WeaponType)eq.getType()).getShortAV() <= 70;
            } else {
                return av + ((WeaponType)eq.getType()).getShortAV() <= 700;
            }
        } else if (eq.getType() instanceof AmmoType) {
            for (int eqNum : bay.getBayWeapons()) {
                final WeaponType weapon = (WeaponType)eSource.getEntity().getEquipment(eqNum).getType();
                if ((weapon.getAmmoType() == ((AmmoType)eq.getType()).getAmmoType())
                        && (weapon.getRackSize() == ((AmmoType)eq.getType()).getRackSize())) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && ((eq.getType().hasFlag(MiscType.F_ARTEMIS)
                        || eq.getType().hasFlag(MiscType.F_ARTEMIS_V))
                        || eq.getType().hasFlag(MiscType.F_ARTEMIS_PROTO))) {
            for (int eqNum : bay.getBayWeapons()) {
                final Mounted weapon = eSource.getEntity().getEquipment(eqNum);
                final WeaponType wtype = (WeaponType) weapon.getType();
                if ((weapon.getLinkedBy() == null)
                    && ((wtype.getAmmoType() == AmmoType.T_LRM)
                            || (wtype.getAmmoType() == AmmoType.T_SRM)
                            || (wtype.getAmmoType() == AmmoType.T_MML)
                        || (wtype.getAmmoType() == AmmoType.T_NLRM))) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_APOLLO)) {
            for (int eqNum : bay.getBayWeapons()) {
                final Mounted weapon = eSource.getEntity().getEquipment(eqNum);
                final WeaponType wtype = (WeaponType) weapon.getType();
                if ((weapon.getLinkedBy() == null)
                    && (wtype.getAmmoType() == AmmoType.T_MRM)) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)
                && (bay.getType() instanceof PPCBayWeapon)) {
            for (int eqNum : bay.getBayWeapons()) {
                if (eSource.getEntity().getEquipment(eqNum).getLinkedBy() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines whether the equipment can be dropped here. In the case of ammo or weapon
     * enhancement, there must be a matching weapon in the bay. A weapon that doesn't fit the
     * bay will be placed in a new bay.
     * 
     * @param loc The drop location from the TransferSupport object passed to the TransferHandler
     * @param eq  The equipment to be dropped
     * @return    Whether the equipment can be dropped in the location
     */
    public boolean isValidDropLocation(JTree.DropLocation loc, Mounted eq) {
        if ((eq.getType() instanceof AmmoType)
                || ((eq.getType() instanceof MiscType)
                        && (eq.getType().hasFlag(MiscType.F_ARTEMIS)
                                || eq.getType().hasFlag(MiscType.F_ARTEMIS_V)
                                || eq.getType().hasFlag(MiscType.F_ARTEMIS_PROTO)
                                || eq.getType().hasFlag(MiscType.F_APOLLO)
                                || eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)
                                || eq.getType().hasFlag(MiscType.F_RISC_LASER_PULSE_MODULE)))) {
            Mounted bay = null;
            TreePath path = loc.getPath();
            if (null != path) {
                bay = getBayFromPath(path);
                if (null != bay) {
                    return canTakeEquipment(bay, eq);
                }
            }
            return false;
        }
        return true;
    }
    
    public Mounted getBayFromPath(TreePath path) {
        if (null != path) {
            MutableTreeNode node = (MutableTreeNode)path.getLastPathComponent();
            if (node instanceof BayNode) {
                return ((BayNode)node).getMounted();
            } else if (node.getParent() instanceof BayNode) {
                return ((BayNode)node.getParent()).getMounted();
            }
        }
        return null;
    }
    
    /**
     * Builds a String representation of the selected node that can be used by the transfer handler
     * to find the node to be removed after export.
     * 
     * @return A String in the format equipmentNum,nodeIndex[,bayNodeIndex]
     */
    public String encodeSelection() {
        TreePath path = getSelectionPath();
        if ((null != path) && (path.getLastPathComponent() instanceof EquipmentNode)) {
            EquipmentNode node = (EquipmentNode)path.getLastPathComponent();
            StringJoiner sj = new StringJoiner(",");
            sj.add(String.valueOf(eSource.getEntity().getEquipmentNum(node.getMounted())));
            sj.add(String.valueOf(node.getParent().getIndex(node)));
            if (node.getParent() instanceof BayNode) {
                sj.add(String.valueOf(((MutableTreeNode)model.getRoot()).getIndex(node.getParent())));
            }
            return sj.toString();
        } else {
            return "-1"; //$NON-NLS-1$
        }
    }
    
    public void removeExported(String selection) {
        String[] sources = selection.split(",");
        if (sources.length < 2) {
            return;
        }
        // Check whether this is equipment in a bay.
        if (sources.length > 2) {
            BayNode bayNode = (BayNode)((MutableTreeNode)model.getRoot())
                    .getChildAt(Integer.parseInt(sources[2]));
            EquipmentNode node = (EquipmentNode)bayNode.getChildAt(Integer.parseInt(sources[1]));
            removeEquipment(node, true, false);
            // If this is the last weapon in the bay, we remove the bay also
            if (bayNode.getMounted().getBayWeapons().size() == 0) {
                removeBay(bayNode, false);
            }
        } else {
            EquipmentNode node = (EquipmentNode)((MutableTreeNode)model.getRoot())
                    .getChildAt(Integer.parseInt(sources[1]));
            // Check whether we're moving an entire bay
            if (node instanceof BayNode) {
                removeBay(node, false);
            } else {
                removeEquipment(node, true, false);
            }
        }
    }
}
