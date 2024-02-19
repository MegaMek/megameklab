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
package megameklab.ui.util;

import megamek.common.*;
import megamek.common.annotations.Nullable;
import megamek.common.equipment.AmmoMounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.verifier.TestAero;
import megamek.common.verifier.TestEntity;
import megamek.common.weapons.bayweapons.BayWeapon;
import megamek.common.weapons.bayweapons.PPCBayWeapon;
import megamek.common.weapons.lrms.LRMWeapon;
import megamek.common.weapons.ppc.PPCWeapon;
import megamek.common.weapons.srms.SRMWeapon;
import megameklab.ui.EntitySource;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

import static megameklab.ui.util.AeroBayTransferHandler.EMTPYSLOT;
import static megameklab.ui.util.CritCellUtil.CRITCELL_ADD_HEIGHT;
import static megameklab.ui.util.CritCellUtil.CRITCELL_MIN_HEIGHT;

/**
 * Variant of DropTargetCriticalList for aerospace units that groups weapons into bays. Also
 * includes support for treating spheroid small craft and dropships firing arcs separately
 * from their locations.
 * 
 * @author Neoancient
 */
public class BayWeaponCriticalTree extends JTree {
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

    /** Stores a unique transient ID for each Bay to allow restoring the expanded state when the loadout changes. */
    private final Map<WeaponMounted, Integer> bayIdMap = new HashMap<>();
    private int bayIdCounter;

    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh) {
        this(location, eSource, refresh, FORWARD);
    }
    
    public BayWeaponCriticalTree(int location, EntitySource eSource, RefreshListener refresh, int facing) {
        this.location = location;
        this.facing = facing;
        this.eSource = eSource;
        this.refresh = refresh;

        model = new DefaultTreeModel(initRoot());
        setModel(model);

        // Remove lines and icons as far as possible (depends on LaF)
        setShowsRootHandles(false);
        putClientProperty("JTree.lineStyle", "None");
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                clearSelection();
            }
        });

        setCellRenderer(renderer);
        addMouseListener(mouseListener);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        AeroBayTransferHandler cth = new AeroBayTransferHandler(eSource);
        setDragEnabled(true);
        setTransferHandler(cth);
        ToolTipManager.sharedInstance().registerComponent(this);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        List<Integer> expandedBays = getExpandedBayIds();
        setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_WEAPONS));
        TreeNode root = initRoot();
        model.setRoot(root);
        setRootVisible(root.getChildCount() == 0);
        restoreExpandedBays(expandedBays);
    }

    private int slotCount(WeaponMounted bay) {
        int count = 0;
        for (WeaponMounted mount : bay.getBayWeapons()) {
            if (mount.getType().hasFlag(WeaponType.F_MASS_DRIVER)) {
                count += 10;
            } else {
                count++;
            }
        }
        return count;
    }
    
    /**
     * @return The number of weapon slots required by equipment allocated to this arc.
     */
    public int getSlotCount() {
        int count = 0;
        for (Enumeration<?> e = ((MutableTreeNode) model.getRoot()).children(); e.hasMoreElements(); ) {
            final Object node = e.nextElement();
            if (node instanceof BayNode) {
                count += slotCount(((BayNode) node).getMounted());
            } else if ((node instanceof EquipmentNode)
                    && (TestAero.usesWeaponSlot(eSource.getEntity(),
                            ((EquipmentNode) node).getMounted().getType()))) {
                count++;
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
        for (WeaponMounted bay : eSource.getEntity().getWeaponBayList()) {
            if ((bay.getLocation() == location)
                    && ((facing == BOTH)
                            || (bay.isRearMounted() == (facing == AFT)))) {
                eqSet.add(eSource.getEntity().getEquipmentNum(bay));
                EquipmentNode bayNode = new BayNode(bay);
                root.insert(bayNode, root.getChildCount());
                bayNode.setParent(root);
                for (WeaponMounted weapon : bay.getBayWeapons()) {
                    EquipmentNode node = new EquipmentNode(weapon);
                    bayNode.insert(node, bayNode.getChildCount());
                    node.setParent(bayNode);
                    eqSet.add(weapon.getEquipmentNum());
                }
                for (AmmoMounted ammo : bay.getBayAmmo()) {
                    EquipmentNode node = new EquipmentNode(ammo);
                    bayNode.insert(node, bayNode.getChildCount());
                    node.setParent(bayNode);
                    eqSet.add(ammo.getEquipmentNum());
                }
            }
        }
        for (int eqIndex = 0; eqIndex < eSource.getEntity().getEquipment().size(); eqIndex++) {
            final Mounted<?> eq = eSource.getEntity().getEquipment(eqIndex);
            if ((eq.getLinked() != null) && (eq.getLinked().getType() instanceof WeaponType)) {
                continue;
            }
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
     * @param bayNode The bay node to remove
     */
    private void removeBay(final BayNode bayNode) {
        removeBay(bayNode, true, true);
    }
    
    /**
     * Removes the bay node and all subnodes.
     *
     * @param bayNode     The bay node to remove
     * @param shouldRefresh If false, will not trigger refreshes. This is used when removing
     *                      all equipment in a bay or finishing a DnD export to hold the refresh until the end.
     * @param updateMount If true, will remove all equipment in the bay from the location and delete the bay itself
     */
    private void removeBay(final BayNode bayNode, boolean shouldRefresh, boolean updateMount) {
        model.removeNodeFromParent(bayNode);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        List<EquipmentNode> children = new ArrayList<>();
        for (Enumeration<MutableTreeNode> e = bayNode.children(); e.hasMoreElements(); ) {
            children.add((EquipmentNode)e.nextElement());
        }
        children.forEach(c -> removeEquipment(c, false, updateMount));
        if (updateMount) {
            bayNode.getMounted().getBayWeapons().clear();
            UnitUtil.removeMounted(eSource.getEntity(), bayNode.getMounted());
        }
        if (shouldRefresh) {
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshStatus();
            refresh.refreshSummary();
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
        final Mounted<?> mounted = node.getMounted();
        // If the parent node is a bay, we need to remove the weapon or ammo from the bay. Unless
        // the parent node does not have a parent, in which case this equipment is being removed in the
        // process of removing a bay.
        if ((node.getParent() instanceof BayNode)
                && (node.getParent().getParent() != null)) {
            WeaponMounted bay = ((BayNode) node.getParent()).getMounted();
            bay.removeFromBay(mounted);
            if ((node.getMounted().getType() instanceof WeaponType)
                    && bay.getBayWeapons().isEmpty()) {
                removeBay((BayNode) node.getParent(), false, true);
            }
        }
        if (updateMount) {
            Mounted<?> moveTo = null;
            if (mounted.getType() instanceof AmmoType) {
                moveTo = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), mounted.getType());
                if (null != moveTo) {
                    moveTo.setShotsLeft(moveTo.getBaseShotsLeft() + mounted.getBaseShotsLeft());
                    UnitUtil.removeCriticals(eSource.getEntity(), mounted);
                    UnitUtil.removeMounted(eSource.getEntity(), mounted);
                }
            }
            if (null == moveTo) {
                UnitUtil.removeCriticals(eSource.getEntity(), mounted);
                UnitUtil.changeMountStatus(eSource.getEntity(), mounted, Entity.LOC_NONE, Entity.LOC_NONE, false);
                if ((node.getMounted().getType() instanceof WeaponType)
                        && (node.getMounted().getLinkedBy() != null)) {
                    UnitUtil.removeCriticals(eSource.getEntity(), node.getMounted().getLinkedBy());
                    UnitUtil.changeMountStatus(eSource.getEntity(), node.getMounted().getLinkedBy(),
                            Entity.LOC_NONE, Entity.LOC_NONE, false);
                }
            }
            UnitUtil.compactCriticals(eSource.getEntity());
        }

        if (shouldRefresh) {
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshStatus();
            refresh.refreshSummary();
        }
    }
    
    /**
     * Moves one or more shots of ammo from this location to unallocated. This should not be used if
     * there is only one slot of ammo left in the location;
     * use {@link #removeEquipment(EquipmentNode) removeEquipment}
     * instead.
     * 
     * @param ammo  The allocated ammo to remove.
     * @param shots The number of shots to remove.
     */
    private void removeAmmo(final AmmoMounted ammo, int shots) {
        AmmoType at = ammo.getType();
        ammo.setShotsLeft(ammo.getBaseShotsLeft() - shots);
        Mounted<?> unallocated = UnitUtil.findUnallocatedAmmo(eSource.getEntity(), at);
        for (AmmoMounted m : eSource.getEntity().getAmmo()) {
            if ((m.getLocation() == Entity.LOC_NONE)
                    && at.equals(m.getType())) {
                unallocated = m;
                break;
            }
        }
        if (null != unallocated) {
            unallocated.setShotsLeft(ammo.getBaseShotsLeft() + shots);
        } else {
            unallocated = Mounted.createMounted(eSource.getEntity(), at);
            unallocated.setShotsLeft(shots);
            try {
                eSource.getEntity().addEquipment(unallocated, Entity.LOC_NONE, false);
            } catch (Exception ignored) {

            }
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }
    
    /**
     * Deletes one or more shots of ammo from this location. This should not be used if
     * there is only one slot of ammo left in the location;
     * use {@link #deleteEquipment(EquipmentNode) deleteEquipment} instead.
     * 
     * @param ammo  The allocated ammo to remove.
     * @param shots The number of shots to remove.
     */
    private void deleteAmmo(final AmmoMounted ammo, int shots) {
        ammo.setShotsLeft(ammo.getBaseShotsLeft());
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
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
            final Mounted<?> m = ((EquipmentNode)e.nextElement()).getMounted();
            UnitUtil.changeMountStatus(eSource.getEntity(), m,
                    location, Entity.LOC_NONE, rear);
            if ((m.getType() instanceof WeaponType) && (m.getLinkedBy() != null)) {
                UnitUtil.changeMountStatus(eSource.getEntity(), m.getLinkedBy(),
                        location, Entity.LOC_NONE, rear);
            }
        }
        UnitUtil.changeMountStatus(eSource.getEntity(), node.getMounted(),
                location, Entity.LOC_NONE, rear);
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }
    
    /**
     * Removes the node and removes the equipment from the unit entirely.
     * @param node
     */
    private void deleteEquipment(final EquipmentNode node) {
        model.removeNodeFromParent(node);
        setRootVisible(((TreeNode)model.getRoot()).getChildCount() == 0);
        final Mounted<?> mounted = node.getMounted();

        UnitUtil.removeMounted(eSource.getEntity(), mounted);
        if ((mounted.getType() instanceof WeaponType) && (mounted.getLinkedBy() != null)) {
            UnitUtil.removeMounted(eSource.getEntity(), mounted.getLinkedBy());
        }
        UnitUtil.compactCriticals(eSource.getEntity());
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    /**
     * Node class used directly for individual mounts and serves as the base class for weapon
     * and ammo bays. Provides display name and color to the renderer.
     */
    private class EquipmentNode implements MutableTreeNode {
        private Object object;
        private MutableTreeNode parent;
        private final Vector<MutableTreeNode> children = new Vector<>();
        
        EquipmentNode(Object object) {
            this.object = object;
        }
        
        Mounted<?> getMounted() {
            return (Mounted<?>) object;
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
                    && (((Mounted<?>) object).getType() instanceof BayWeapon))
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
        
        @Override
        public String toString() {
            String name = getMounted().getName();
            if ((getMounted().getType() instanceof WeaponType)
                    && (getMounted().getLinkedBy() != null)) {
                name += " + " + getMounted().getLinkedBy().getName();
            } else if (getMounted().getType() instanceof AmmoType) {
                name += " (" + getMounted().getBaseShotsLeft() + " shots)";
            }
            if (!eSource.getEntity().usesWeaponBays() && getMounted().isRearMounted()) {
                name += " (R)";
            }
            return name;
        }

        public String getTooltip() {
            StringBuilder sb = new StringBuilder("<html>");
            sb.append(this);
            if (getMounted().getType() instanceof WeaponType) {
                final WeaponType wtype = (WeaponType) getMounted().getType();
                final int bonus = avMod(getMounted());
                sb.append("<br/>AV: ").append(wtype.getShortAV() + bonus).append("/")
                    .append(wtype.getMedAV() + bonus).append("/")
                    .append(wtype.getLongAV() + bonus);
                sb.append("<br/>Heat: ").append(wtype.getHeat());
            }
            sb.append("</html>");
            return sb.toString();
        }
    }
    
    /**
     * Node used for weapon bays. Display name shows the current and maximum AV for the bay.
     */
    private class BayNode extends EquipmentNode {
        BayNode(WeaponMounted object) {
            super(object);
            bayIdMap.computeIfAbsent(object, m -> bayIdCounter++);
        }
        
        public boolean isCapital() {
            return getMounted().getType().isCapital();
        }

        @Override
        public boolean isLeaf() {
            return false;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(getMounted().getName());
            double av = 0;
            for (Enumeration<MutableTreeNode> e = children(); e.hasMoreElements(); ) {
                final Mounted<?> m = ((EquipmentNode)e.nextElement()).getMounted();
                if (m.getType() instanceof WeaponType) {
                    // Plasma weapons add the average heat to the damage to compute bay AV limit.
                    // That's 2d6 for canon and 10 dmg + 1d6 for rifle.
                    if (m.getType().hasFlag(WeaponType.F_PLASMA)) {
                        if (((WeaponType)m.getType()).getDamage() == WeaponType.DAMAGE_VARIABLE) {
                            av += 7;
                        } else {
                            av += 13.5;
                        }
                    } else if (m.getType().hasFlag(WeaponType.F_ARTILLERY)) {
                        // No AV since they cannot be used air-to-air, but the ground damage needs
                        // to count against the bay limit.
                        av += ((WeaponType) m.getType()).getRackSize();
                    } else {
                        av += ((WeaponType) m.getType()).getShortAV();
                    }
                    // Capacitors in bays are always considered charged.
                    if (m.getLinkedBy() != null) {
                        av += avMod((WeaponType) m.getType(), m.getLinkedBy().getType());
                    }
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

        @Override
        WeaponMounted getMounted() {
            return (WeaponMounted) super.getMounted();
        }

        @Override
        public String getTooltip() {
            StringBuilder sb = new StringBuilder("<html>");
            sb.append(this);
            double shortAV = 0;
            double medAV = 0;
            double longAV = 0;
            int heat = 0;
            double weight = 0;
            for (WeaponMounted eq : getMounted().getBayWeapons()) {
                final WeaponType wtype = eq.getType();
                final int bonus = avMod(eq);
                shortAV += wtype.getShortAV() + bonus;
                medAV += wtype.getMedAV() + bonus;
                longAV += wtype.getLongAV() + bonus;
                heat += wtype.getHeat();
                weight += eq.getTonnage();
                if (eq.getLinkedBy() != null) {
                    weight += eq.getLinkedBy().getTonnage();
                }
            }
            for (AmmoMounted eq : getMounted().getBayAmmo()) {
                weight += eq.getTonnage() * eq.getBaseShotsLeft() / eq.getType().getShots();
            }
            sb.append("<br/>AV: ").append(shortAV).append("/").append(medAV).append("/")
                .append(longAV).append("<br/>Heat: ").append(heat)
                .append("<br/>Bay Weight: ").append(weight).append(" tons</html>");
            return sb.toString();
        }
    }

    private int avMod(Mounted<?> weaponMount) {
        if ((weaponMount.getType() instanceof WeaponType)
                && (weaponMount.getLinkedBy() != null)) {
            return avMod((WeaponType) weaponMount.getType(), weaponMount.getLinkedBy().getType());
        } else {
            return 0;
        }
    }

    private int avMod(WeaponType weapon, EquipmentType linkedBy) {
        if (linkedBy.hasFlag(MiscType.F_ARTEMIS)
                || linkedBy.hasFlag(MiscType.F_ARTEMIS_V)) {
            // The 9 and 10 rows of the cluster hits table is only different in the 3 column
            if (weapon.getAtClass() == WeaponType.CLASS_MML) {
                if (weapon.getRackSize() >= 7) {
                    return 2;
                } else if ((weapon.getRackSize() >= 5) || linkedBy.hasFlag(MiscType.F_ARTEMIS_V)) {
                    return 1;
                }
            } else if (weapon instanceof LRMWeapon) {
                return weapon.getRackSize() / 5;
            } else if (weapon instanceof SRMWeapon) {
                return 2;
            }
        } else if (linkedBy.hasFlag(MiscType.F_ARTEMIS_PROTO) && weapon.getRackSize() == 2) {
            // The +1 cluster hit bonus only adds a missile hit for SRM2
            return 2;
        } else if (linkedBy.hasFlag(MiscType.F_PPC_CAPACITOR)) {
            // PPC capacitors in weapon bays are treated as if always charged
            return 5;
        }
        return 0;
    }

    /**
     * Sets node name and text/background colors.
     */
    private final DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
        private EquipmentNode node;

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            setOpaque(true);
            setBorder(null);
            if (value instanceof EquipmentNode) {
                node = (EquipmentNode) value;
                CritCellUtil.formatCell(this, node.getMounted(), true, eSource.getEntity(), 0);

                if (node.isLeaf()) {
                    if (node.getParent() != null && node != node.getParent().getChildAt(node.getParent().getChildCount() - 1)) {
                        Border dashed = BorderFactory.createDashedBorder(CritCellUtil.CRITCELL_BORDER_COLOR, 5, 5);
                        Border empty = BorderFactory.createEmptyBorder(-1, -1, 0, -1);
                        Border compound = new CompoundBorder(empty, dashed);
                        setBorder(compound);
                    }
                } else  {
                    if (row != 0) {
                        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
                    }
                }
            } else {
                CritCellUtil.formatCell(this, null, true, eSource.getEntity(), 0);
            }
            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            // Make the Bays wider to prevent the tree from changing size upon opening a bay
            // Keep a minimum height to avoid empty sections from having no height
            int width = CritCellUtil.CRITCELL_WIDTH;
            width += (!eSource.getEntity().usesWeaponBays() || ((node != null) && node.isLeaf())) ? 0 : 20;
            int height = Math.max(CRITCELL_MIN_HEIGHT, super.getPreferredSize().height + CRITCELL_ADD_HEIGHT);
            return new Dimension(width, height);
        }
    };

    @Override
    public Dimension getMinimumSize() {
        // Prevents layout problems with empty locations
        return super.getPreferredSize();
    }

    /**
     * Displays popup menu
     */
    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            final int row = getClosestRowForLocation(e.getX(), e.getY());
            if (getPathForRow(row).getLastPathComponent() instanceof EquipmentNode) {
                final EquipmentNode node = (EquipmentNode)getPathForRow(row).getLastPathComponent();
                if ((e.getButton() == MouseEvent.BUTTON2)
                        || ((e.getButton() == MouseEvent.BUTTON3)
                                && ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0))) {
                    if (node.isLeaf()) {
                        removeEquipment(node);
                    } else {
                        removeBay((BayNode) node);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    final Mounted<?> mounted = node.getMounted();
    
                    if ((facing == BOTH) && ((e.getModifiersEx() & InputEvent.ALT_DOWN_MASK) != 0)) {
                        setBayFacing(node, !node.getMounted().isRearMounted());
                    }
    
                    JPopupMenu popup = new JPopupMenu();
                    popup.setAutoscrolls(true);
                    JMenuItem info;

                    if (node.isLeaf()) {
                        if (node.getMounted().getType() instanceof AmmoType) {
                            AmmoType at = (AmmoType)node.getMounted().getType();
                            if (node.getMounted().getBaseShotsLeft() > at.getShots()) {
                                JMenu remove = new JMenu("Remove");
                                JMenu delete = new JMenu("Delete");
                                for (int s = at.getShots();
                                        s < node.getMounted().getBaseShotsLeft();
                                        s += at.getShots()) {
                                    final int shots = s;
                                    info = new JMenuItem("Remove " + shots + ((shots > 1)?" shots" : " shot"));
                                    info.addActionListener(ev -> removeAmmo((AmmoMounted) node.getMounted(), shots));
                                    remove.add(info);
                                    info = new JMenuItem("Delete " + shots + ((shots > 1)?" shots" : " shot"));
                                    info.addActionListener(ev -> deleteAmmo((AmmoMounted) node.getMounted(), shots));
                                    delete.add(info);
                                }
                                info = new JMenuItem("Remove all");
                                info.addActionListener(ev -> removeEquipment(node));
                                remove.add(info);
                                info = new JMenuItem("Delete all");
                                info.addActionListener(ev -> deleteEquipment(node));
                                delete.add(info);
                                popup.add(remove);
                                popup.add(delete);
                            } else {
                                info = new JMenuItem("Remove all shots");
                                info.addActionListener(ev -> removeEquipment(node));
                                popup.add(info);
                                info = new JMenuItem("Delete all shots");
                                info.addActionListener(ev -> deleteEquipment(node));
                            }
                        } else {
                            info = new JMenuItem("Remove " + mounted.getName());
                            if ((node.getParent() instanceof BayNode)
                                    && (node.getParent().getChildCount() == 1)) {
                                info.addActionListener(ev -> removeBay((BayNode) node.getParent()));
                            } else {
                                info.addActionListener(ev -> removeEquipment(node));
                            }
                            popup.add(info);
        
                            info = new JMenuItem("Delete " + mounted.getName());
                            info.addActionListener(ev -> deleteEquipment(node));
                        }
                    } else {
                        info = new JMenuItem("Remove entire bay");
                        info.addActionListener(ev -> removeBay((BayNode) node));
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
                } else if (facing == AFT) {
                    return "Aft Left";
                } else {
                    return "Left Wing";
                }
            } else if (location == SmallCraft.LOC_RWING) {
                if (facing == FORWARD) {
                    return "Forward Right";
                } else if (facing == AFT) {
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
     * tree is valid for the aero unit. This filters out aft side arcs for aerodyne small
     * craft and broadsides for non-warships.
     * @param  aero The unit to check
     * @return Whether the arc is valid for the unit.
     */
    public boolean validForUnit(Aero aero) {
        if (aero.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                && !aero.isSpheroid()
                && (facing == AFT)) {
            return false;
        }
        return location < aero.locations();
    }

    /**
     * Finds all bays in this arc where the given equipment can be added based on type and current AV.
     * 
     * @param eq  An equipment mount
     * @return    A list of valid bays for the equipment
     */
    public List<WeaponMounted> baysFor(Mounted<?> eq) {
        List<WeaponMounted> retVal = new ArrayList<>();
        for (Enumeration<?> e = ((MutableTreeNode) model.getRoot()).children(); e.hasMoreElements(); ) {
            final Mounted<?> bay = ((EquipmentNode) e.nextElement()).getMounted();
            if ((bay.getType() instanceof BayWeapon) && (canTakeEquipment((WeaponMounted) bay, eq))) {
                retVal.add((WeaponMounted) bay);
            }
        }
        return retVal;
    }
    
    /**
     * Determines whether equipment can be added to this location. Ammo requires a bay with a suitable
     * weapon, as do weapon enhancements. All other equipment can be added.
     * 
     * @param eq Potential equipment to be added to the location.
     * @return   Whether the equipment can be added to the location.
     */
    public boolean canAdd(Mounted<?> eq) {
        if (eSource.getEntity().usesWeaponBays()
                && ((eq.getType() instanceof AmmoType)
                        || UnitUtil.isWeaponEnhancement(eq.getType()))) {
            return !baysFor(eq).isEmpty();
        }
        if (TestEntity.eqRequiresLocation(eSource.getEntity(), eq.getType())) {
            return location != TestEntity.getSystemWideLocation(eSource.getEntity());
        }
        return true;
    }

    /**
     * Adds a new bay of the appropriate type to the unit and adds the equipment to the bay.
     * 
     * @param bayType The type of bay to be added.
     * @param eq      The equipment to be added.
     */
    public void addToNewBay(EquipmentType bayType, WeaponMounted eq) {
        try {
            WeaponMounted bay = (WeaponMounted) eSource.getEntity().addEquipment(bayType, location, facing == AFT);
            BayNode bayNode = new BayNode(bay);
            model.insertNodeInto(bayNode, (MutableTreeNode)model.getRoot(), ((TreeNode)model.getRoot()).getChildCount());
            bayNode.setParent((MutableTreeNode)model.getRoot());
            if (isRootVisible()) {
                expandRow(0);
                setRootVisible(false);
            }
            addToBay(bay, eq);
        } catch (Exception ignored) {
            // should not happen
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    /**
     * Adds an equipment mount to a bay. Changes the equipment mount's location and updates the bay's
     * weapon or ammo list if necessary.
     * 
     * @param bay The receiving weapon bay
     * @param eq The equipment to add to the bay
     */
    public void addToBay(@Nullable WeaponMounted bay, Mounted<?> eq) {
        // Check that we have a bay
        if ((null == bay) || !canTakeEquipment(bay, eq)) {
            if (eq instanceof WeaponMounted) {
                EquipmentType bayType = ((WeaponType)eq.getType()).getBayType();
                addToNewBay(bayType, (WeaponMounted) eq);
            } else {
                addToLocation(eq);
            }
            return;
        } else if (eq.getType() instanceof MiscType) {
            if (eq.getType().hasFlag(MiscType.F_ARTEMIS)
                    || eq.getType().hasFlag(MiscType.F_ARTEMIS_PROTO)
                    || eq.getType().hasFlag(MiscType.F_ARTEMIS_V)) {
                for (WeaponMounted weapon : bay.getBayWeapons()) {
                    final WeaponType wtype = weapon.getType();
                    if ((weapon.getLinkedBy() == null)
                            && ((wtype.getAmmoType() == AmmoType.T_LRM)
                                || (wtype.getAmmoType() == AmmoType.T_SRM)
                                || (wtype.getAmmoType() == AmmoType.T_MML)
                                || (wtype.getAmmoType() == AmmoType.T_LRM_IMP)
                                || (wtype.getAmmoType() == AmmoType.T_SRM_IMP)
                                || (wtype.getAmmoType() == AmmoType.T_NLRM))) {
                        moveToArc(eq);
                        eq.setLinked(weapon);
                        break;
                    }
                }
            } else if (eq.getType().hasFlag(MiscType.F_APOLLO)
                    || eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)) {
                for (WeaponMounted weapon : bay.getBayWeapons()) {
                    if (weapon.getLinkedBy() == null) {
                        moveToArc(eq);
                        eq.setLinked(weapon);
                        break;
                    }
                }
            }
        } else {
            // If adding ammo, check for an existing mount for this ammo type first. If found, add
            // the shots there.
            if (eq instanceof AmmoMounted) {
                Optional<AmmoMounted> addMount = bay.getBayAmmo().stream()
                        .filter(m -> eq.getType().equals(m.getType()))
                        .findFirst();
                if (addMount.isPresent()) {
                    addMount.get().setShotsLeft(addMount.get().getBaseShotsLeft() + eq.getBaseShotsLeft());
                    updateAmmoCapacity(addMount.get());
                    UnitUtil.removeMounted(eSource.getEntity(), eq);
                    refresh.refreshEquipment();
                    refresh.refreshBuild();
                    refresh.refreshPreview();
                    refresh.refreshStatus();
                    refresh.refreshSummary();
                    return;
                }
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
                        moveToArc(eq.getLinkedBy());
                    }
                } else if (eq.getType() instanceof AmmoType) {
                    bay.addAmmoToBay(eSource.getEntity().getEquipmentNum(eq));
                }
            } else {
                LogManager.getLogger().debug(bay.getName() + "[" + eSource.getEntity().getEquipmentNum(bay)
                        + "] not found in " + getLocationName());
            }
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    private void updateAmmoCapacity(AmmoMounted ammoMount) {
        // Mounted#getTonnage will give us the weight of one slot of ammo; multiply it by the number
        // of slots to get the total capacity of the ammo bin.
        double weight = ammoMount.getTonnage()
                * Math.max(1, ammoMount.getBaseShotsLeft() / ((AmmoType) ammoMount.getType()).getShots());
        ammoMount.setAmmoCapacity(weight);
    }
    
    public void addAmmoToBay(WeaponMounted bay, AmmoMounted eq, int shots) {
        AmmoType at = eq.getType();
        eq.setShotsLeft(eq.getBaseShotsLeft() - shots);
        if (eq.getBaseShotsLeft() <= 0) {
            UnitUtil.removeMounted(eSource.getEntity(), eq);
        } else {
            updateAmmoCapacity(eq);
        }
        Optional<AmmoMounted> addMount = bay.getBayAmmo().stream().filter(m -> at.equals(m.getType())).findFirst();
        if (addMount.isPresent()) {
            addMount.get().setShotsLeft(addMount.get().getBaseShotsLeft() + shots);
            updateAmmoCapacity(addMount.get());
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshStatus();
            refresh.refreshSummary();
        } else {
            try {
                Mounted<?> m = eSource.getEntity().addEquipment(at, bay.getLocation());
                m.setShotsLeft(shots);
                addToBay(bay, m);
            } catch (Exception ignored) {

            }
        }
    }

    /**
     * Adds equipment to a location without a bay.
     * @param eq The equipment to add to the location of this tree
     */
    public void addToLocation(Mounted eq) {
        moveToArc(eq);
        EquipmentNode node = new EquipmentNode(eq);
        model.insertNodeInto(node, (MutableTreeNode) model.getRoot(), ((TreeNode) model.getRoot()).getChildCount());
        node.setParent((MutableTreeNode) model.getRoot());
        if (isRootVisible()) {
            expandRow(0);
            setRootVisible(false);
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }
    
    /**
     * Adds multiple equipment mounts to this location. Weapons and ammo will go into the first available
     * bay. Ammo that does not have a legal bay will be skipped.
     * 
     * @param eqList The equipment to add
     */
    public void addToLocation(List<Mounted<?>> eqList) {
        // We want to check for weapons first since they might make a new bay that creates a legal space
        // for non-weapon equipment that might not have a place otherwise.
        eqList.sort(Comparator.comparingInt(m -> ((m.getType() instanceof WeaponType) ? 0 : 1)));
        LIST:for (Mounted<?> eq : eqList) {
            if (eSource.getEntity().usesWeaponBays() && ((eq.getType() instanceof WeaponType)
                    || (eq.getType() instanceof AmmoType)
                    || (UnitUtil.isWeaponEnhancement(eq.getType())))) {
                for (Enumeration<?> e = ((MutableTreeNode) model.getRoot()).children(); e.hasMoreElements(); ) {
                    final Mounted<?> bay = ((EquipmentNode) e.nextElement()).getMounted();
                    if ((bay.getType() instanceof BayWeapon) && (canTakeEquipment((WeaponMounted) bay, eq))) {
                        addToBay((WeaponMounted) bay, eq);
                        continue LIST;
                    }
                }
                if (eq instanceof WeaponMounted) {
                    addToNewBay(((WeaponType) eq.getType()).getBayType(), (WeaponMounted) eq);
                }
                // Skip any ammo that can't go into a bay here
            } else {
                addToLocation(eq);
            }
        }
    }
    
    /**
     * Called by the transfer handler when equipment is dropped on this location.
     *  
     * @param eq The equipment dropped on this location
     * @param path The tree node under the drop point
     */
    public void addToArc(Mounted<?> eq, TreePath path) {
        if ((null == path) || !(path.getLastPathComponent() instanceof EquipmentNode)) {
            addToBay(null, eq);
            return;
        }
        EquipmentNode node = (EquipmentNode)path.getLastPathComponent();
        if (node instanceof BayNode) {
            addToBay(((BayNode) node).getMounted(), eq);
        } else if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)
                && (node.getMounted().getType() instanceof PPCWeapon)
                && (node.getMounted().getLinkedBy() == null)) {
            moveToArc(eq);
            eq.setLinked(node.getMounted());
            refresh.refreshEquipment();
            refresh.refreshBuild();
            refresh.refreshPreview();
            refresh.refreshStatus();
            refresh.refreshSummary();
        } else {
            addToBay(getBayFromPath(path), eq);
        }
    }

    /**
     * Called by the transfer handler when ammo is dropped on this location as a move command,
     * indicating a single slot should be moved. A copy command (ctrl key held) will transfer
     * all ammo using {@link #addToArc(Mounted,TreePath) addToArc}.
     *  
     * @param eq
     * @param path
     */
    public void addAmmo(AmmoMounted eq, int shots, TreePath path) {
        EquipmentNode node = (EquipmentNode) path.getLastPathComponent();
        if (node instanceof BayNode) {
            addAmmoToBay(((BayNode) node).getMounted(), eq, shots);
        } else {
            addAmmoToBay(getBayFromPath(path), eq, shots);
        }
    }

    /**
     * Moves a bay and all its contents from another location
     * @param bay The bay to move in
     */
    public void addBay(WeaponMounted bay) {
        // First move the bay here
        moveToArc(bay);
        BayNode bayNode = new BayNode(bay);
        model.insertNodeInto(bayNode, (MutableTreeNode) model.getRoot(),
                ((TreeNode) model.getRoot()).getChildCount());
        bayNode.setParent((MutableTreeNode) model.getRoot());
        if (isRootVisible()) {
            expandRow(0);
            setRootVisible(false);
        }
        // Now go through all the equipment in the bay
        for (WeaponMounted weapon : bay.getBayWeapons()) {
            moveToArc(weapon);
            EquipmentNode node = new EquipmentNode(weapon);
            model.insertNodeInto(node, bayNode, bayNode.getChildCount());
            node.setParent(bayNode);
            if (weapon.getLinkedBy() != null) {
                moveToArc(weapon.getLinkedBy());
            }
        }
        for (AmmoMounted ammo : bay.getBayAmmo()) {
            moveToArc(ammo);
            EquipmentNode node = new EquipmentNode(ammo);
            model.insertNodeInto(node, bayNode, bayNode.getChildCount());
            node.setParent(bayNode);
        }
    }
    
    private void moveToArc(Mounted<?> eq) {
        UnitUtil.removeCriticals(eSource.getEntity(), eq);
        try {
            eSource.getEntity().addEquipment(eq, location, false);
        } catch (Exception ex) {
            // We shouldn't be hitting any limits
            LogManager.getLogger().error("", ex);
        }
        UnitUtil.changeMountStatus(eSource.getEntity(), eq, location,
                Entity.LOC_NONE, (facing == AFT) || ((facing == BOTH) && eq.isRearMounted()));
    }
    
    /**
     * Determines whether the equipment can be added to the bay. For a weapon this is determined
     * by bay type and AV limit. For ammo this is determined by the presence of a weapon in that location
     * that can use the ammo. For weapon enhancements this is determined by the presence of a weapon
     * that can use the enhancement that doesn't already have one.
     * 
     * @param bay The target bay
     * @param eq The equipment to test
     */
    private boolean canTakeEquipment(WeaponMounted bay, Mounted<?> eq) {
        if (eq.getType() instanceof WeaponType) {
            if ((((WeaponType) eq.getType()).getBayType(false) != bay.getType())
                    && (((WeaponType) eq.getType()).getBayType(true) != bay.getType())) {
                return false;
            }
            // find current av
            double av = 0;
            for (WeaponMounted w : bay.getBayWeapons()) {
                // Plasma weapons add the average heat to the damage to compute bay AV limit.
                // That's 1d6 for rifle and 2d6 for canon.
                if (w.getType().hasFlag(WeaponType.F_PLASMA)) {
                    if (w.getType().getDamage() == WeaponType.DAMAGE_VARIABLE) {
                        av += 7;
                    } else {
                        av += 13.5;
                    }
                } else if (w.getType().hasFlag(WeaponType.F_ARTILLERY)) {
                    av += w.getType().getRackSize();
                } else {
                    av += w.getType().getShortAV() + avMod(w);
                }
            }
            if (eq.getType().hasFlag(WeaponType.F_MASS_DRIVER)) {
                // Limit is one per firing arc; the medium and heavy exceed the 70-point limit.
                return av == 0;
            } else if (((WeaponType) eq.getType()).isCapital()) {
                return av + ((WeaponType) eq.getType()).getShortAV() <= 70;
            } else {
                return av + ((WeaponType) eq.getType()).getShortAV() + avMod(eq) <= 700;
            }
        } else if (eq.getType() instanceof AmmoType) {
            for (WeaponMounted weapon : bay.getBayWeapons()) {
                if ((weapon.getType().getAmmoType() == ((AmmoType) eq.getType()).getAmmoType())
                        && (weapon.getType().getRackSize() == ((AmmoType) eq.getType()).getRackSize())) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && ((eq.getType().hasFlag(MiscType.F_ARTEMIS)
                        || eq.getType().hasFlag(MiscType.F_ARTEMIS_V))
                        || eq.getType().hasFlag(MiscType.F_ARTEMIS_PROTO))) {
            for (WeaponMounted weapon : bay.getBayWeapons()) {
                final int atype = weapon.getType().getAmmoType();
                if ((weapon.getLinkedBy() == null)
                    && ((atype == AmmoType.T_LRM)
                        || (atype == AmmoType.T_SRM)
                        || (atype == AmmoType.T_MML)
                        || (atype == AmmoType.T_LRM_IMP)
                        || (atype == AmmoType.T_SRM_IMP)
                        || (atype == AmmoType.T_NLRM))) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_APOLLO)) {
            for (WeaponMounted weapon : bay.getBayWeapons()) {
                if ((weapon.getLinkedBy() == null)
                    && (weapon.getType().getAmmoType() == AmmoType.T_MRM)) {
                    return true;
                }
            }
        } else if ((eq.getType() instanceof MiscType)
                && eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)
                && (bay.getType() instanceof PPCBayWeapon)) {
            for (WeaponMounted weapon : bay.getBayWeapons()) {
                if (weapon.getLinkedBy() == null) {
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
    public boolean isValidDropLocation(DropLocation loc, Mounted<?> eq) {
        if (!canAdd(eq)) {
            return false;
        } else if (!eSource.getEntity().usesWeaponBays()) {
            return true;
        }
        WeaponMounted bay = null;
        TreePath path = loc.getPath();
        if (null != path) {
            // If we're dropping a PPC capacitor on a PPC we need to check that it doesn't already
            // have one. If on a PPC bay, we need to check that there is a PPC in the bay
            // without a capacitor.
            if ((eq.getType() instanceof MiscType) && eq.getType().hasFlag(MiscType.F_PPC_CAPACITOR)
                    && (path.getLastPathComponent() instanceof EquipmentNode)) {
                EquipmentNode node = (EquipmentNode)path.getLastPathComponent();
                if (node.getMounted().getType() instanceof PPCWeapon) {
                    return node.getMounted().getLinkedBy() == null;
                } else if (node.getMounted().getType() instanceof PPCBayWeapon) {
                    for (WeaponMounted weapon : ((BayNode) node).getMounted().getBayWeapons()) {
                        if (weapon.getLinkedBy() == null) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            bay = getBayFromPath(path);
        }
        // disallow dropping a bay into its current arc or a weapon or ammo into its current bay
        if (null != bay) {
            if ((eq == bay)
                    || ((eq instanceof WeaponMounted)
                            && bay.getBayWeapons().contains(eq))
                    || ((eq instanceof AmmoMounted)
                            && bay.getBayAmmo().contains(eq))) {
                return false;
            }
        }

        if (eq.getType() instanceof AmmoType) {
            return (null != bay) && canTakeEquipment(bay, eq);
        }

        if (UnitUtil.isWeaponEnhancement(eq.getType())) {
            if ((null != bay) && canTakeEquipment(bay, eq)) {
                for (WeaponMounted weapon : bay.getBayWeapons()) {
                    if (weapon == eq.getLinked()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public WeaponMounted getBayFromPath(TreePath path) {
        if (null != path) {
            MutableTreeNode node = (MutableTreeNode) path.getLastPathComponent();
            if (node instanceof BayNode) {
                return ((BayNode) node).getMounted();
            } else if (node.getParent() instanceof BayNode) {
                return ((BayNode) node.getParent()).getMounted();
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
            StringJoiner sj = new StringJoiner(":");
            sj.add(String.valueOf(eSource.getEntity().getEquipmentNum(node.getMounted())));
            sj.add(String.valueOf(node.getParent().getIndex(node)));
            if (node.getParent() instanceof BayNode) {
                sj.add(String.valueOf(((MutableTreeNode)model.getRoot()).getIndex(node.getParent())));
            }
            return sj.toString();
        } else {
            return EMTPYSLOT;
        }
    }

    public void removeExported(String selection, int action) {
        String[] sources = selection.split(":");
        if (sources.length < 2) {
            return;
        }
        // Check whether this is equipment in a bay.
        if (sources.length > 2) {
            BayNode bayNode = (BayNode)((MutableTreeNode)model.getRoot())
                    .getChildAt(Integer.parseInt(sources[2]));
            EquipmentNode node = (EquipmentNode)bayNode.getChildAt(Integer.parseInt(sources[1]));
            // Don't remove ammo unless all the ammo is moved or there are no shots remaining.
            if (!(node.getMounted().getType() instanceof AmmoType)
                    || (action == AeroBayTransferHandler.AMMO_ALL)
                    || (node.getMounted().getBaseShotsLeft() == 0)) {
                removeEquipment(node, false, false);
            }
        } else {
            EquipmentNode node = (EquipmentNode)((MutableTreeNode)model.getRoot())
                    .getChildAt(Integer.parseInt(sources[1]));
            // If we're moving an entire bay we only need to get rid of the bay node.
            if (node instanceof BayNode) {
                model.removeNodeFromParent(node);
            } else {
                removeEquipment(node, false, false);
            }
        }
        refresh.refreshEquipment();
        refresh.refreshBuild();
        refresh.refreshPreview();
        refresh.refreshStatus();
        refresh.refreshSummary();
    }

    private void restoreExpandedBays(List<Integer> expandedBayIds) {
        TreeNode root = (TreeNode) model.getRoot();
        if ((root == null) || (expandedBayIds == null)) {
            return;
        }
        for (int n = 0; n < model.getChildCount(root); n++) {
            Object node = model.getChild(root, n);
            if (node instanceof BayNode) {
                if (expandedBayIds.contains(bayIdMap.get(((BayNode) node).getMounted()))) {
                    Object[] pathObjs = new Object[2];
                    pathObjs[0] = root;
                    pathObjs[1] = node;
                    expandPath(new TreePath(pathObjs));
                }
            }
        }
    }

    private List<Integer> getExpandedBayIds() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            TreePath currPath = getPathForRow(i);
            if (isExpanded(currPath)) {
                Object entry = currPath.getLastPathComponent();
                if (entry instanceof BayNode) {
                    result.add(bayIdMap.get(((BayNode) entry).getMounted()));
                }
            }
        }
        return result;
    }
}
