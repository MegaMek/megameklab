/*
 * MegaMekLab - Copyright (C) 2010
 * *
 * Original author - Sebastian Brocks (beerockxs@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.util;

import java.io.Serializable;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox.KeySelectionManager;

import megamek.common.EquipmentType;

public class EquipmentListCellKeySelectionManager implements KeySelectionManager, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3544837608256080482L;

    public int selectionForKey(char aKey,@SuppressWarnings("rawtypes") ComboBoxModel aModel) {
        int i,c;
        int currentSelection = -1;
        Object selectedItem = aModel.getSelectedItem();
        if ( selectedItem != null ) {
            String selectedText = ((EquipmentType) selectedItem).getName();
            String v;
            String pattern;


            for ( i=0,c=aModel.getSize();i<c;i++ ) {
                if ( selectedText.equals(((EquipmentType)aModel.getElementAt(i)).getName())) {
                    currentSelection  =  i;
                    break;
                }
            }


            pattern = ("" + aKey).toLowerCase();
            aKey = pattern.charAt(0);

            for ( i = ++currentSelection, c = aModel.getSize() ; i < c ; i++ ) {
                Object elem = aModel.getElementAt(i);
                if ((elem != null) && (elem.toString() != null)) {
                    v = ((EquipmentType) elem).getName().toLowerCase();
                    if ( (v.length() > 0) && (v.charAt(0) == aKey) ) {
                        return i;
                    }
                }
            }

            for ( i = 0 ; i < currentSelection ; i ++ ) {
                Object elem = aModel.getElementAt(i);
                if ((elem != null) && (elem.toString() != null)) {
                    v = ((EquipmentType) elem).getName().toLowerCase();
                    if ( (v.length() > 0) && (v.charAt(0) == aKey) ) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }


}
