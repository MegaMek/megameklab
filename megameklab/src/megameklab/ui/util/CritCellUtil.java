/*
 * Copyright (C) 2021-2025 The MegaMek Team. All Rights Reserved.
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
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import megamek.common.annotations.Nullable;
import megamek.common.equipment.AmmoType;
import megamek.common.equipment.EquipmentTypeLookup;
import megamek.common.equipment.MiscMounted;
import megamek.common.equipment.MiscType;
import megamek.common.equipment.Mounted;
import megamek.common.equipment.WeaponType;
import megamek.common.units.Entity;
import megameklab.ui.EquipmentToolTip;
import megameklab.util.CConfig;
import megameklab.util.UnitUtil;

/**
 * Contains constants and utils for a unified crit cell display across unit types.
 */
public final class CritCellUtil {

    /** The base width of Crit Cells across units with 3 columns of crit lists */
    public static final String CRITICAL_CELL_WIDTH_STRING = "X".repeat(22);

    /** The height added to the text height of Crit Cells (padding) */
    public static final int CRITICAL_CELL_ADD_HEIGHT = 5;

    public static final Color CRITICAL_CELL_BORDER_COLOR = Color.BLACK;
    public static final String EMPTY_CRITICAL_CELL_TEXT = "- Empty -";

    /**
     * @param title the title for this component
     *
     * @return a titled border using the given string as title placed centered atop the Component and using a
     *       {@link LocationBorder} as a border. To be used for crit location blocks, especially when they have
     *       additional information ("Slots: 0/2") above or below the crits to group them visually.
     */
    public static Border locationBorder(String title) {
        return BorderFactory.createTitledBorder(
              new LocationBorder(CRITICAL_CELL_BORDER_COLOR, 2f),
              " " + title + " ",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION);
    }

    /**
     * @param title the title for this Component
     *
     * @return a titled but otherwise empty border using the given string as the title placed centered atop the
     *       Component.
     */
    public static Border locationBorderNoLine(String title) {
        return BorderFactory.createTitledBorder(
              BorderFactory.createEmptyBorder(),
              " " + title + " ",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION);
    }

    /**
     * Applies crit cell formatting to the given JLabel cell, which is assumed to display the given mounted in the given
     * entity at the given crit cell index. The JLabel cell should be a ListCellRenderer or TreeCellRenderer return
     * value.
     */
    public static void formatCell(JLabel cell, @Nullable Mounted<?> mounted, boolean useColor,
          Entity entity, int index) {
        if (useColor) {
            if (mounted == null) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_EMPTY));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_EMPTY));
            } else if (!mounted.getType().isHittable()) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_NON_HITTABLE));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_NON_HITTABLE));
            } else if (mounted.getType() instanceof WeaponType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_WEAPONS));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_WEAPONS));
            } else if (mounted.getType() instanceof AmmoType) {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_AMMO));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_AMMO));
            } else {
                cell.setBackground(CConfig.getBackgroundColor(CConfig.GUI_COLOR_EQUIPMENT));
                cell.setForeground(CConfig.getForegroundColor(CConfig.GUI_COLOR_EQUIPMENT));
            }
        }

        if (mounted == null) {
            cell.setText(" " + EMPTY_CRITICAL_CELL_TEXT);
            cell.setToolTipText(null);
        } else {
            String name = UnitUtil.getCritName(entity, mounted.getType());
            name += mounted.isRearMounted() ? " (R)" : "";
            name += mounted.isArmored() ? " (A)" : "";
            name += mounted.isMekTurretMounted() ? " (T)" : "";
            name += mounted.isSponsonTurretMounted() ? " (ST)" : "";
            name += mounted.isPintleTurretMounted() ? " (PT)" : "";
            name += mounted.isDWPMounted() ? " (DWP)" : "";
            if ((mounted.getType() instanceof AmmoType) && !mounted.is(EquipmentTypeLookup.COOLANT_POD)) {
                name = mounted.getBaseShotsLeft() + " shot" + (mounted.getBaseShotsLeft() > 1 ? "s " : " ") + name;
            }
            if (entity.isOmni() && !mounted.getType().isOmniFixedOnly()) {
                if (mounted.isOmniPodMounted()) {
                    name += " (Pod)";
                } else {
                    name += " (Fixed)";
                    cell.setFont(cell.getFont().deriveFont(Font.ITALIC));
                }
            }
            if (mounted instanceof MiscMounted
                  && (mounted.getType().hasFlag(MiscType.F_DETACHABLE_WEAPON_PACK) || mounted.getType()
                  .hasFlag(MiscType.F_AP_MOUNT))
                  && mounted.getLinked() != null) {
                name += " (attached " + mounted.getLinked().getName() + ")";
            }

            cell.setText(" " + name);

            String toolTipText = EquipmentToolTip.getToolTipInfo(entity, mounted);
            // distinguish tooltips of equal adjacent one-slot equipment (e.g. ammo) to make
            // the tip renew itself
            // when crossing from one such slot to the next (avoids them feeling like a
            // single equipment)
            if (mounted.getNumCriticalSlots() == 1) {
                toolTipText += " ".repeat(index);
            }
            cell.setToolTipText(toolTipText);
        }
    }

}
