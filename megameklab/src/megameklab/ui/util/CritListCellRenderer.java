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

import megamek.common.*;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static megameklab.ui.util.CritCellUtil.*;

public class CritListCellRenderer extends DefaultListCellRenderer {

    private JList<?> list = null;
    private final Entity unit;
    private final boolean useColor;

    public CritListCellRenderer(Entity unit, boolean useColor) {
        this.unit = unit;
        this.useColor = useColor;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        this.list = list;

        String[] split = ((String)value).split(":");
        setText(split[0]);
        setToolTipText(null);

        CriticalSlot cs = null;
        if (split.length > 2) {
            int eqId = Integer.parseInt(split[2]);
            /** safety against logic error where we try to redraw deleted equipment due to poor dupe slot handling **/
            Mounted eq = unit.getEquipment(eqId);
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
        if ((cs != null)
                && UnitUtil.isLastCrit(unit, cs, index, loc)
                && UnitUtil.isPreviousCritEmpty(unit, cs, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isLastCrit(unit, cs, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else if ((cs != null) && UnitUtil.isPreviousCritEmpty(unit, cs, index, loc)) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CritCellUtil.CRITCELL_BORDER_COLOR));
        } else {
            setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        int width = CRITCELL_WIDTH;
        width = (unit instanceof Mech) ? CRITCELL_MEK_WIDTH : width;
        width = (unit instanceof Tank) ? CRITCELL_VEH_WIDTH : width;
        int height = Math.max(CRITCELL_MIN_HEIGHT, super.getPreferredSize().height + CRITCELL_ADD_HEIGHT);
        return new Dimension(width, height);
    }

    private CriticalSlot getCrit(int slot) {
        int location = getCritLocation();
        CriticalSlot crit = null;
        if ((slot >= 0) && (slot < unit.getNumberOfCriticals(location))) {
            crit = unit.getCritical(location, slot);
        }
        return crit;
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