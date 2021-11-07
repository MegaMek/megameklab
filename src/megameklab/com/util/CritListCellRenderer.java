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

package megameklab.com.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.WeaponType;

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
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        this.list = list;

        setPreferredSize(new Dimension(110,15));
        setMaximumSize(new Dimension(110,15));
        setMinimumSize(new Dimension(110,15));

        String[] split = ((String)value).split(":");
        label.setText(split[0]);
        label.setToolTipText(null);

        CriticalSlot cs;
        if (split.length > 2) {
            int eqId = Integer.parseInt(split[2]);
            cs = new CriticalSlot(unit.getEquipment(eqId));
        } else if (split.length > 1) {
            cs = getCrit(Integer.parseInt(split[1]));
        } else if (value.equals("-Empty-")) {
            cs = null;
        } else {
            cs = getCrit(index);
        }

        if (cs != null) {

            if (cs.getType() == CriticalSlot.TYPE_SYSTEM) {
                if (useColor) {
                    label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_SYSTEMS));
                    label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_SYSTEMS));
                }
                if (cs.isArmored()) {
                    label.setText(label.getText() + " (A)");
                }
            } else if (cs.getMount() != null) {

                Mounted mount = cs.getMount();

                if (useColor) {
                    if (mount.getType() instanceof WeaponType) {
                        label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_WEAPONS));
                        label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_WEAPONS));
                    } else if (mount.getType() instanceof AmmoType) {
                        label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_AMMO));
                        label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_AMMO));
                    } else {
                        label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_EQUIPMENT));
                        label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_EQUIPMENT));
                    }
                }
                String name = UnitUtil.getCritName(unit, mount.getType());

                if (mount.isRearMounted()) {
                    name += " (R)";
                }
                if (mount.isArmored()) {
                    name += " (A)";
                }
                if (mount.isMechTurretMounted()) {
                    name += " (T)";
                }
                if (mount.isSponsonTurretMounted()) {
                    name += " (ST)";
                }
                if (mount.isPintleTurretMounted()) {
                    name += " (PT)";
                }
                if (mount.isDWPMounted()) {
                    name += " (DWP)";
                }
                if (unit.isOmni() && !mount.getType().isOmniFixedOnly()) {
                    if (mount.isOmniPodMounted()) {
                        name += " (Pod)";
                    } else {
                        name += " (Fixed)";
                        label.setFont(label.getFont().deriveFont(Font.ITALIC));
                    }
                }
                if ((mount.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK)
                        || mount.getType().hasFlag(MiscType.F_AP_MOUNT))
                        && mount.getLinked() != null){
                    name += " (attached " + mount.getLinked().getName() + ")";
                }
                // For ammo on BA, show the number of shots
                if ((unit instanceof BattleArmor) && (mount.getType() instanceof AmmoType)) {
                    name += " (" + mount.getBaseShotsLeft() + ")";
                }
                String toolTipText = UnitUtil.getToolTipInfo(unit, mount);
                // distinguish tooltips of equal adjacent one-slot equipment (e.g. ammo) to make the tip renew itself
                // when crossing from one such slot to the next (avoids them feeling like a single equipment)
                if (mount.getCriticals() == 1) {
                    toolTipText += " ".repeat(index);
                }
                if (cs.getMount2() != null) {
                    mount = cs.getMount2();
                    name += " | "+ UnitUtil.getCritName(unit, mount.getType());
                }
                label.setText(name);
                label.setToolTipText(toolTipText);
            }
        } else if (useColor) {
            label.setBackground(CConfig.getBackgroundColor(CConfig.CONFIG_EMPTY));
            label.setForeground(CConfig.getForegroundColor(CConfig.CONFIG_EMPTY));
        }

        int loc = getCritLocation();
        if ((cs != null) 
                && UnitUtil.isLastCrit(unit, cs, index, loc) 
                && UnitUtil.isPreviousCritEmpty(unit, cs, index, loc)) {
            label.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
        } else if ((cs != null) && UnitUtil.isLastCrit(unit, cs, index, loc)) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        } else if ((cs != null) && UnitUtil.isPreviousCritEmpty(unit, cs, index, loc)){
            label.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        } 

        return label;
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
        if (unit instanceof BattleArmor){
            String[] split = list.getName().split(":");
            return Integer.parseInt(split[0]);
        } else {
            return Integer.parseInt(list.getName());
        }
    }

}