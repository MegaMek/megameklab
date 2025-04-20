/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 */

package megameklab.ui.handheldWeapon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

import megamek.common.AmmoType;
import megamek.common.HandheldWeapon;
import megamek.common.Mounted;
import megameklab.ui.util.CritCellUtil;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

public class HHWEquipmentList extends JList<String> implements MouseListener {
    private final HandheldWeapon hhw;
    private final RefreshListener refresh;

    public HHWEquipmentList(HandheldWeapon hhw, RefreshListener refresh) {
        super(equipNames(hhw));
        this.hhw = hhw;
        this.refresh = refresh;
        setCellRenderer(new CritListCellRenderer());
        setVisibleRowCount(getModel().getSize());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setPrototypeCellValue(CritCellUtil.CRITCELL_WIDTH_STRING);
        addMouseListener(this);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, CritCellUtil.CRITCELL_BORDER_COLOR));
    }

    private static Vector<String> equipNames(HandheldWeapon hhw) {
        Vector<String> critNames = new Vector<>();
        for (var m : hhw.getEquipment()) {
            if (m.getType() instanceof AmmoType) {
                critNames.add("%s (%d)".formatted(m.getName(), (int) m.getSize()));
            } else {
                critNames.add(m.getName());
            }
        }
        if (critNames.isEmpty()) {
            critNames.add(CritCellUtil.EMPTY_CRITCELL_TEXT);
        }
        return critNames;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setSelectedIndex(locationToIndex(e.getPoint()));
        if (e.getButton() == MouseEvent.BUTTON3 && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
            deleteItem();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            var popup = new JPopupMenu();
            var item = new JMenuItem("Delete " + getMount().getName());
            item.addActionListener(ev -> deleteItem());
            popup.add(item);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void deleteItem() {
        UnitUtil.removeMounted(hhw, hhw.getEquipment().get(getSelectedIndex()));
        refresh.refreshEquipment();
        refresh.refreshStructure();
    }

    private Mounted<?> getMount() {
        return hhw.getEquipment().get(getSelectedIndex());
    }

    private class CritListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
              boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value.equals(CritCellUtil.CRITCELL_WIDTH_STRING)) {
                setText(CritCellUtil.CRITCELL_WIDTH_STRING);
                setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                return this;
            }

            if (index >= hhw.getEquipment().size()) {
                CritCellUtil.formatCell(this, null, true, hhw, index);
            } else {
                var m = hhw.getEquipment().get(index);
                CritCellUtil.formatCell(this, m, true, hhw, index);
            }
            setBorder(BorderFactory.createMatteBorder(index == 0 ? 1 : 0, 0, 1, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension superSize = super.getPreferredSize();
            return new Dimension(superSize.width, superSize.height + CritCellUtil.CRITCELL_ADD_HEIGHT);
        }
    }
}
