/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
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

/*
 * Thanks to Lost in space of the Solaris Sunk Works Project for the code snippet and idea.
 */

package megameklab.ui.util;

import static megameklab.ui.util.CritCellUtil.CRITICAL_CELL_WIDTH_STRING;
import static megameklab.ui.util.CritCellUtil.EMPTY_CRITICAL_CELL_TEXT;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import megamek.common.CriticalSlot;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.Mounted;
import megamek.common.units.Entity;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

public class CritListCellRenderer extends DefaultListCellRenderer {

    private JList<?> list = null;
    private final Entity unit;
    private final boolean useColor;

    public CritListCellRenderer(Entity unit, boolean useColor) {
        this.unit = unit;
        this.useColor = useColor;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
          boolean hasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        this.list = list;

        if (value.equals(CRITICAL_CELL_WIDTH_STRING)) {
            // For the "prototype" cell value, the text must stay unchanged to set the correct width of the list
            setText(CRITICAL_CELL_WIDTH_STRING);
            setBorder(new EmptyBorder(0, 0, 0, 0));
            return this;
        }

        String[] split = ((String) value).split(":");
        setText(split[0]);
        setToolTipText(null);

        CriticalSlot cs = null;
        if (split.length > 2) {
            int eqId = Integer.parseInt(split[2]);
            /*
             * safety against logic error where we try to redraw deleted equipment due to poor dupe slot handling
             **/
            Mounted<?> eq = unit.getEquipment(eqId);
            cs = eq != null ? new CriticalSlot(eq) : null;
        } else if (split.length > 1) {
            cs = getCrit(Integer.parseInt(split[1]));
        } else if (!value.equals(EMPTY_CRITICAL_CELL_TEXT)) {
            cs = getCrit(index);
        }

        if (cs != null) {
            if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                if (useColor) {
                    setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_SYSTEMS));
                    setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_SYSTEMS));
                }
                if (cs.isArmored()) {
                    setText(getText() + " (A)");
                }
                setText(" " + getText());
            } else if (cs.getMount() != null) {
                CritCellUtil.formatCell(this, cs.getMount(), useColor, unit, index);
                if (cs.getMount2() != null) {
                    setText(getText() + " | " + UnitUtil.getCritName(unit, cs.getMount2().getType()));
                }
            }
        } else {
            CritCellUtil.formatCell(this, null, useColor, unit, index);
        }

        int loc = getCritLocation();
        if ((cs != null) &&
              UnitUtil.isLastCrit(unit, cs, index, loc) &&
              UnitUtil.isPreviousCriticalSlotEmpty(unit, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isLastCrit(unit, cs, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isPreviousCriticalSlotEmpty(unit, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CritCellUtil.CRITICAL_CELL_BORDER_COLOR));
        } else {
            setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension superSize = super.getPreferredSize();
        return new Dimension(superSize.width, superSize.height + CritCellUtil.CRITICAL_CELL_ADD_HEIGHT);
    }

    private CriticalSlot getCrit(int slot) {
        int location = getCritLocation();
        CriticalSlot criticalSlot = null;

        if ((slot >= 0) && (slot < unit.getNumberOfCriticalSlots(location))) {
            criticalSlot = unit.getCritical(location, slot);
        }

        return criticalSlot;
    }

    private int getCritLocation() {
        if (unit instanceof BattleArmor) {
            String[] split = list.getName().split(":");
            return Integer.parseInt(split[0]);
        } else {
            return Integer.parseInt(list.getName());
        }
    }

}
