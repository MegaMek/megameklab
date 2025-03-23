/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

/*
 * Thanks to Lost in space of the Solaris Sunk Works Project for the code snippet and idea.
 */

package megameklab.ui.util;

import static megameklab.ui.util.CritCellUtil.CRITCELL_WIDTH_STRING;
import static megameklab.ui.util.CritCellUtil.EMPTY_CRITCELL_TEXT;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.Mounted;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

public class CritListCellRenderer extends DefaultListCellRenderer {

    private       JList<?> list = null;
    private final Entity   unit;
    private final boolean  useColor;

    public CritListCellRenderer(Entity unit, boolean useColor) {
        this.unit     = unit;
        this.useColor = useColor;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        this.list = list;

        if (value.equals(CRITCELL_WIDTH_STRING)) {
            // For the "prototype" cell value, the text must stay unchanged to set the correct width of the list
            setText(CRITCELL_WIDTH_STRING);
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
        } else if (!value.equals(EMPTY_CRITCELL_TEXT)) {
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
            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isLastCrit(unit, cs, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isPreviousCriticalSlotEmpty(unit, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else {
            setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension superSize = super.getPreferredSize();
        return new Dimension(superSize.width, superSize.height + CritCellUtil.CRITCELL_ADD_HEIGHT);
    }

    private CriticalSlot getCrit(int slot) {
        int          location     = getCritLocation();
        CriticalSlot criticalSlot = null;

        if ((slot >= 0) && (slot < unit.getNumberOfCriticals(location))) {
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
