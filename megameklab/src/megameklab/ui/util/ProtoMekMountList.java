/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import megamek.common.annotations.Nullable;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponMounted;
import megamek.common.equipment.WeaponType;
import megamek.common.exceptions.LocationFullException;
import megamek.common.units.Entity;
import megamek.common.units.ProtoMek;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.util.ProtoMekUtil;
import megameklab.util.UnitUtil;

/**
 * The location crit block for ProtoMeks
 *
 * @author Neoancient
 * @author Simon (Juliez)
 */
public class ProtoMekMountList extends JList<Mounted<?>> {
    private static final MMLogger logger = MMLogger.create(ProtoMekMountList.class);

    private final EntitySource eSource;
    private final int location;
    private RefreshListener refresh;
    private static final WeaponType widthWeaponType = new WeaponType();

    public ProtoMekMountList(EntitySource eSource, RefreshListener refresh, int location) {
        this.eSource = eSource;
        this.refresh = refresh;
        this.location = location;
        refreshContents();
        setCellRenderer(new MountCellRenderer());
        MouseListener mouseListener = new MouseAdapter() {
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
                            ProtoMekUtil.addProtoMekAmmo(getProtoMek(), mounted.getType(), 1);
                        } catch (LocationFullException ex) {
                            logger.error("", ex);
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
                            ProtoMekUtil.reduceProtoMekAmmo(getProtoMek(), mounted.getType(), 1);
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

            private boolean isChangeable(@Nullable Mounted<?> mounted) {
                return (mounted != null)
                      && !UnitUtil.isFixedLocationSpreadEquipment(mounted.getType())
                      && !UnitUtil.isArmor(mounted.getType());
            }

            private void showPopup(MouseEvent e, Mounted<?> mounted) {
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

                popup.show(ProtoMekMountList.this, e.getX(), e.getY());
            }
        };
        addMouseListener(mouseListener);
        setDragEnabled(true);
        setTransferHandler(new CriticalTransferHandler(eSource, refresh));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        WeaponMounted criticalCellWidthMounted = new WeaponMounted(eSource.getEntity(), widthWeaponType);
        setPrototypeCellValue(criticalCellWidthMounted);
    }

    public ProtoMek getProtoMek() {
        return (ProtoMek) eSource.getEntity();
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
        getProtoMek().getEquipment().stream().filter(m -> m.getLocation() == location).forEach(model::add);
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
                UnitUtil.removeMounted(getProtoMek(), ammo);
            }
        }
        UnitUtil.removeMounted(getProtoMek(), mount);
        refresh();
    }

    private void changeFacing(Mounted<?> mount) {
        mount.setLocation(location, !mount.isRearMounted());
        refresh();
    }

    private static class MountedListModel extends AbstractListModel<Mounted<?>> {

        private final List<Mounted<?>> list = new ArrayList<>();

        @Override
        public int getSize() {
            return Math.max(1, list.size());
        }

        @Override
        public Mounted<?> getElementAt(int index) {
            return (index >= list.size()) ? null : list.get(index);
        }

        public void add(Mounted<?> mounted) {
            list.add(mounted);
            fireContentsChanged(this, list.size() - 1, list.size() - 1);
        }
    }

    private static class MountCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
              boolean hasFocus) {
            final ProtoMekMountList lstMount = (ProtoMekMountList) list;
            final Entity entity = lstMount.eSource.getEntity();
            if ((value instanceof Mounted<?> mounted) && (mounted.getType() == widthWeaponType)) {
                // For the "prototype" cell value, use the prototype text to set the correct width of the list
                setText(CritCellUtil.CRITICAL_CELL_WIDTH_STRING);
                setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
            } else {
                if (value instanceof Mounted<?> mounted) {
                    CritCellUtil.formatCell(this, mounted, true, entity, index);
                }

                if ((index > 0) && (index < list.getModel().getSize())) {
                    setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
                }
            }
            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension superSize = super.getPreferredSize();
            return new Dimension(superSize.width, superSize.height + CritCellUtil.CRITICAL_CELL_ADD_HEIGHT);
        }
    }

    /**
     * @return the selected item, or null if nothing is selected.
     */
    public @Nullable Mounted<?> getMounted() {
        return getSelectedValue();
    }

    private boolean isTorso() {
        return location == ProtoMek.LOC_TORSO;
    }
}
