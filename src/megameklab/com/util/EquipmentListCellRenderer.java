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

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import sun.swing.DefaultLookup;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;

public class EquipmentListCellRenderer extends DefaultListCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3787675734141978289L;

    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Entity unit;


    public EquipmentListCellRenderer(Entity unit) {
        this.unit = unit;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

         StringBuffer text = new StringBuffer();
         EquipmentType etype = (EquipmentType)value;
         text.append(etype.getName());
         if (unit.isClan() && ((etype.getTechLevel() == TechConstants.T_INTRO_BOXSET)
                 || (etype.getTechLevel() == TechConstants.T_IS_TW_NON_BOX)
                 || (etype.getTechLevel() == TechConstants.T_IS_ADVANCED)
                 || (etype.getTechLevel() == TechConstants.T_IS_EXPERIMENTAL)
                 || (etype.getTechLevel() == TechConstants.T_IS_UNOFFICIAL))) {
             text.append(" (IS)");
         }
         if (!unit.isClan() && ((etype.getTechLevel() == TechConstants.T_CLAN_TW)
                 || (etype.getTechLevel() == TechConstants.T_CLAN_ADVANCED)
                 || (etype.getTechLevel() == TechConstants.T_CLAN_EXPERIMENTAL)
                 || (etype.getTechLevel() == TechConstants.T_CLAN_UNOFFICIAL))) {
             text.append(" (Clan)");
         }
         setText(text.toString());
         setComponentOrientation(list.getComponentOrientation());

         Color bg = null;
         Color fg = null;

         JList.DropLocation dropLocation = list.getDropLocation();
         if (dropLocation != null
                 && !dropLocation.isInsert()
                 && dropLocation.getIndex() == index) {

             bg = DefaultLookup.getColor(this, ui, "List.dropCellBackground");
             fg = DefaultLookup.getColor(this, ui, "List.dropCellForeground");

             isSelected = true;
         }
         if (isSelected) {
             setBackground(bg == null ? list.getSelectionBackground() : bg);
             setForeground(fg == null ? list.getSelectionForeground() : fg);

         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }
         setEnabled(list.isEnabled());
         setFont(list.getFont());

             Border border = null;
             if (cellHasFocus) {
                 if (isSelected) {
                     border = DefaultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
                 }
                 if (border == null) {
                     border = DefaultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
                 }
             } else {
                 border = getNoFocusBorder();
             }
         setBorder(border);


         return this;
      }

    private Border getNoFocusBorder() {
        Border border = DefaultLookup.getBorder(this, ui, "List.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) {
                return border;
            }
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null &&
                    (noFocusBorder == null ||
                    noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }
}