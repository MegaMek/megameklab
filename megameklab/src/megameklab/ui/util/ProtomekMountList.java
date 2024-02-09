/*
 * MegaMekLab - Copyright (C) 2018 - The MegaMek Team
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
import megameklab.ui.EntitySource;
import megameklab.util.ProtoMekUtil;
import megameklab.util.UnitUtil;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static megameklab.ui.util.CritCellUtil.CRITCELL_ADD_HEIGHT;
import static megameklab.ui.util.CritCellUtil.CRITCELL_MIN_HEIGHT;
import static megameklab.ui.util.CritCellUtil.CRITCELL_WIDTH;

/**
 * The location crit block for ProtoMeks
 *
 * @author Neoancient
 * @author Simon (Juliez)
 */
public class ProtomekMountList extends JList<Mounted> {

    private final EntitySource eSource;
    private final int location;
    private RefreshListener refresh;
    
    public ProtomekMountList(EntitySource eSource, RefreshListener refresh, int location) {
        this.eSource = eSource;
        this.refresh = refresh;
        this.location = location;
        refreshContents();
        setCellRenderer(new MountCellRenderer());
        addMouseListener(mouseListener);
        setDragEnabled(true);
        setTransferHandler(new CriticalTransferHandler(eSource, refresh));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public Protomech getProtomech() {
        return (Protomech) eSource.getEntity();
    }
    
    public int getMountLocation() {
        return location;
    }
    
    public void setRefresh(RefreshListener refresh) {
        this.refresh = refresh;
    }
    
    private void refresh() {
        if (null != refresh) {
            refresh.refreshEquipment();
            refresh.refreshPreview();
            refresh.refreshBuild();
        }
    }
    
    public void refreshContents() {
        MountedListModel model = new MountedListModel();
        getProtomech().getEquipment().stream().filter(m -> m.getLocation() == location).forEach(model::add);
        setModel(model);
        setVisibleRowCount(model.getSize());
    }
    
    private void removeMount(Mounted<?> mount) {
        mount.setLocation(Entity.LOC_NONE, false);
        refresh();
    }
    
    private void deleteMount(Mounted<?> mount) {
        if ((mount.getType() instanceof WeaponType) && mount.isOneShot()) {
            Mounted<?> ammo = mount.getLinked();
            if (null != ammo) {
                UnitUtil.removeMounted(getProtomech(), ammo);
            }
        }
        UnitUtil.removeMounted(getProtomech(), mount);
        refresh();
    }
    
    private void changeFacing(Mounted<?> mount) {
        mount.setLocation(location, !mount.isRearMounted());
        refresh();
    }
    
    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            final Mounted<?> mounted = getModel().getElementAt(locationToIndex(e.getPoint()));
            if (e.isPopupTrigger() && isChangeable(mounted)) {
                showPopup(e, mounted);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            final Mounted<?> mounted = getModel().getElementAt(locationToIndex(e.getPoint()));
            if (!isChangeable(mounted)) {
                return;
            }

            if (SwingUtilities.isLeftMouseButton(e)) {
                if (e.isControlDown() && (mounted.getType() instanceof AmmoType)) {
                    try {
                        ProtoMekUtil.addProtoMechAmmo(getProtomech(), mounted.getType(), 1);
                    } catch (LocationFullException ex) {
                        LogManager.getLogger().error("", ex);
                    }
                    refresh();
                    return;
                }
            }

            if (SwingUtilities.isRightMouseButton(e)) {
                if (e.isAltDown() && isTorso()) {
                    changeFacing(mounted);
                    return;
                }
                if (e.isControlDown()) {
                    if ((mounted.getType() instanceof AmmoType)) {
                        ProtoMekUtil.reduceProtoMechAmmo(getProtomech(), mounted.getType(), 1);
                    } else {
                        removeMount(mounted);
                    }
                    refresh();
                    return;
                }
            }

            if (e.isPopupTrigger()) {
                showPopup(e, mounted);
            }
        }

        private boolean isChangeable(@Nullable Mounted mounted) {
            return (mounted != null)
                    && !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())
                    && !UnitUtil.isArmor(mounted.getType());
        }

        private void showPopup(MouseEvent e, Mounted mounted) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem menuItem;
            if (!(mounted.getType() instanceof AmmoType)) {
                menuItem = new JMenuItem("Remove " + mounted.getName());
                menuItem.addActionListener(ev -> removeMount(mounted));
                popup.add(menuItem);
            }

            menuItem = new JMenuItem("Delete " + mounted.getName());
            menuItem.addActionListener(ev -> deleteMount(mounted));
            popup.add(menuItem);

            if (isTorso() && (mounted.getType() instanceof WeaponType)) {
                menuItem = new JMenuItem("Change Facing");
                menuItem.addActionListener(ev -> changeFacing(mounted));
                popup.add(menuItem);
            }

            popup.show(ProtomekMountList.this, e.getX(), e.getY());
        }
    };

    private static class MountedListModel extends AbstractListModel<Mounted> {

        private final List<Mounted> list = new ArrayList<>();

        @Override
        public int getSize() {
            return Math.max(1, list.size());
        }

        @Override
        public Mounted getElementAt(int index) {
            return (index >= list.size()) ? null : list.get(index);
        }
        
        public void add(Mounted mounted) {
            list.add(mounted);
            fireContentsChanged(this, list.size() - 1, list.size() - 1);
        }
    }
    
    private static class MountCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
            final ProtomekMountList lstMount = (ProtomekMountList) list;
            final Entity entity = lstMount.eSource.getEntity();
            CritCellUtil.formatCell(this, (Mounted) value, true, entity, index);
            if ((index > 0) && (index < list.getModel().getSize())) {
                setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
            }
            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            int height = Math.max(CRITCELL_MIN_HEIGHT, super.getPreferredSize().height + CRITCELL_ADD_HEIGHT);
            return new Dimension(CRITCELL_WIDTH, height);
        }
    }

    /**
     * @return the selected item, or null if nothing is selected.
     */
    public @Nullable Mounted getMounted() {
        return getSelectedValue();
    }

    private boolean isTorso() {
        return location == Protomech.LOC_TORSO;
    }
}
